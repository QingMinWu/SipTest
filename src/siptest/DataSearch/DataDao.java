/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package siptest.DataSearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wuqm
 */
public class DataDao {
     public ResultSet DataFind(Map DBmap, String table, String selectsql) {
        System.out.print(selectsql);
        String driver = "oracle.jdbc.OracleDriver";
        String url = DBmap.get("DBURL").toString();
        String user = DBmap.get("DBusername").toString();
        String password = DBmap.get("DBpassword").toString();
        Connection con = null;
        Statement pstm = null;
        ResultSet rs = null;
        ResultSet rt = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            pstm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pstm.executeQuery(selectsql);
            rt = rs;

        } catch (Exception e) {
            e.printStackTrace();
            AlarmDialog print = new AlarmDialog(e.getMessage());
            print.setVisible(true);
        }
        return rt;
    }

    /*
     * @description 对数据进行executeUpdate操作，可以实现数据的增删查改
     * @param DBmap数据库配置，包括DBURL,DBusername,DBpassword
     * @param table 需要操作的表
     * @param sql 操作的sql
     */
    public void DataExecute(Map DBmap, String sql) {
        //获取数据库信息
        String driver = "oracle.jdbc.OracleDriver";
        String url = DBmap.get("DBURL").toString();
        String user = DBmap.get("DBusername").toString();
        String password = DBmap.get("DBpassword").toString();
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            int remark;
            pstm = con.prepareStatement(sql);
            remark = pstm.executeUpdate(sql);
//            System.out.println(remark);
            pstm.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            AlarmDialog print = new AlarmDialog(e.getMessage());
            print.setVisible(true);
        }

    }

    //获取数据
    public Map DataCollect(ResultSet rt) throws Exception {
        Map Result = new HashMap();
        //获取行的个数
        rt.last();
        int row = rt.getRow();
        rt.beforeFirst();
        //获取列集
        ResultSetMetaData rsmd = rt.getMetaData();
        //获取列的个数
        int col = rsmd.getColumnCount();
        String[] column = new String[col];
        //获取列名作为表头
        for (int i = 0; i < col; i++) {//列从1开始算
            column[i] = rsmd.getColumnName(i + 1);
            System.out.println(column[i]);
        }
//        if (row > 10) {
//            row = 10;
//        }
        //获取列数据
        String[][] data = new String[row][col];
        if (rt.next()) {
            for (int m = 0; m < row; m++) {
                for (int n = 0; n < col; n++) {
                    data[m][n] = rt.getString(n + 1);
                    System.out.println(data[m][n]);
                }
                rt.next();
            }
        }

        rt.close();

        Result.put("column", column);//列名
        Result.put("data", data);//数据
        Result.put("row", row);//行数
        Result.put("col", col);//列数
        return Result;
    }
}
