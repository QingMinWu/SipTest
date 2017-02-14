/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package siptest.DataSearch;

import siptest.DataSearch.AlarmDialog;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author wuqm
 */
public class SourceDataDao {
     /*
     * @description 拼装SQL插数据
     * @param DBmap数据库配置信息，包括阿DBURL,DBusername,DBpassword
     * @param table需要操作的表
     * @param datamap插入的数据 key表示列明，value表示列值
     */
    public void DataInsert(Map DBmap, String table, Map datamap) {
        //获取数据库信息
        String driver = "oracle.jdbc.OracleDriver";
        String url = DBmap.get("DBURL").toString();
        String user = DBmap.get("DBusername").toString();
        String password = DBmap.get("DBpassword").toString();
        Connection con = null;
        PreparedStatement pstm = null;
        //增加插入时间
        datamap.put("create_date", "sysdate");

        StringBuilder columns = new StringBuilder();//插入的列
        StringBuilder values = new StringBuilder();//插入的值
        columns.append("(");
        values.append("(");
        Set entrys = new HashSet(datamap.entrySet());
        //提取传入的数据
        for (Iterator it = entrys.iterator(); it.hasNext();) {
            String entry = it.next().toString();
            int flag = entry.indexOf("=");
            String key = entry.substring(0, flag);//获取列
            String value = entry.substring(flag + 1);//获取值
            columns.append(key).append(",");
            if (value == null || "".equals(value)) {
                
                values.append("'',");
                continue;
            }
            if ("sysdate".equals(value)) {
                values.append(value).append(",");
                continue;
            }
            values.append("'").append(value).append("'").append(",");
           

        }
        columns.setLength(columns.length() - 1);//去掉最后一个逗号
        values.setLength(values.length() - 1);//去掉最后一个逗号
        columns.append(")");
        values.append(")");

        String insertsql = "insert into " + table + " " + columns.toString() + " values " + values.toString();
        //替换掉单引号
        System.out.println(insertsql);


        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            int remark;
            pstm = con.prepareStatement(insertsql);
            remark = pstm.executeUpdate(insertsql);
//            System.out.println(remark);
            pstm.close();
            con.close();


        } catch (Exception e) {
            e.printStackTrace();
            AlarmDialog print = new AlarmDialog(e.getMessage());
            print.setVisible(true);
        }

    }

    /*
     * @description 拼装SQL更新数据
     * @param DBmap数据库配置信息，包括阿DBURL,DBusername,DBpassword
     * @param table需要操作的表
     //* @param oldata旧数据，key表示列名，value表示列值，拼装放在where 后
     * @param newdata更新的数据,key表示列名，value表示列值，拼装放在set后
     */
    public void DataUpdate(Map DBmap, String table, Map olddata, Map newdata) {
        //获取数据库信息
        String driver = "oracle.jdbc.OracleDriver";
        String url = DBmap.get("DBURL").toString();
        String user = DBmap.get("DBusername").toString();
        String password = DBmap.get("DBpassword").toString();
        Connection con = null;
        PreparedStatement pstm = null;

        Set olds = olddata.entrySet();//更新条件
        Set news = newdata.entrySet();//更新的数据
        StringBuilder tmp = new StringBuilder();
        tmp.append("update ").append(table).append(" set ");
        //提取更新后的数据
        for (Iterator it = news.iterator(); it.hasNext();) {
            String entry = it.next().toString();
            int flag = entry.indexOf("=");
            String key = entry.substring(0, flag);
            String value = entry.substring(flag + 1);
            tmp.append(key).append("=").append("'").append(value).append("'").append(",");
        }
        ;
        
        tmp.setLength(tmp.length() - 1);//去掉最后一个逗号
        tmp.append(" where ");
        
          //提取更新条件
        for (Iterator it = olds.iterator(); it.hasNext();) {
            String entry = it.next().toString();
            int flag = entry.indexOf("=");
            String key = entry.substring(0, flag);
            String value = entry.substring(flag + 1);
            tmp.append(key).append("=").append("'").append(value).append("'").append(" and ");
        }
        //去掉最后的" and ",占5个字节
        tmp.setLength(tmp.length() - 5);
        String updatesql = tmp.toString();
        //转义
//        updatesql = updatesql.replace("\'{", "\'\'{");
//        updatesql = updatesql.replace("}\'", "}\'\'");

//        updatesql = updatesql.toUpperCase();

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            pstm = con.prepareStatement(updatesql);
            System.out.println(updatesql);
            int remark = pstm.executeUpdate();
//            System.out.println(remark);
            pstm.close();
            con.close();
            

        } catch (Exception e) {
            e.printStackTrace();
            AlarmDialog print = new AlarmDialog(e.getMessage());
            print.setVisible(true);
        }
    }

    /*
     * @description 拼装SQL删除数据
     * @param DBmap数据库配置信息，包括阿DBURL,DBusername,DBpassword
     * @param table需要操作的表
     * @datamap 删除的条件，key表示列名，value表示列值，拼装后放在where后
     */
    public void DataDelete(Map DBmap, String table, Map datamap) {
        //获取数据库配置
        String driver = "oracle.jdbc.OracleDriver";
        String url = DBmap.get("DBURL").toString();
        String user = DBmap.get("DBusername").toString();
        String password = DBmap.get("DBpassword").toString();
        Connection con = null;
        PreparedStatement pstm = null;

        Set data = datamap.entrySet();
        StringBuilder deletesql = new StringBuilder();
        deletesql.append("delete from ").append(table).append(" where ");
        //提取删除的条件
        for (Iterator it = data.iterator(); it.hasNext();) {
            String entry = it.next().toString();
            int flag = entry.indexOf("=");
            String key = entry.substring(0, flag);
            String value = entry.substring(flag + 1);
            deletesql.append(key).append("=").append("'").append(value).append("'").append(" and ");
        }

        deletesql.setLength(deletesql.length() - 5);

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            pstm = con.prepareStatement(deletesql.toString());
            int remark = pstm.executeUpdate();
//            System.out.println(remark);
            pstm.close();
            con.close();
            AlarmDialog print = new AlarmDialog("删除成功！");
            print.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            AlarmDialog print = new AlarmDialog(e.getMessage());
            print.setVisible(true);

        }


    }

    /*
     * @description 拼装SQL查找数据
     * @param DBmap数据库配置信息，包括阿DBURL,DBusername,DBpassword
     * @param table需要操作的表
     * @param datamap 查询条件key表示列名，value表示列值
     * @param resultcolumn 结果列名
     * @return ResultSet 查询的结果集
     */
    public ResultSet DataFind(Map DBmap, String table, Map datamap, Set resultcolumn) {
        String driver = "oracle.jdbc.OracleDriver";
        String url = DBmap.get("DBURL").toString();
        String user = DBmap.get("DBusername").toString();
        String password = DBmap.get("DBpassword").toString();
        Connection con = null;
        Statement pstm = null;
        ResultSet rs = null;
        ResultSet rt=null;
        StringBuilder selectsql = new StringBuilder();
        selectsql.append("select ");

        for (Iterator it = resultcolumn.iterator(); it.hasNext();) {
            String entry = it.next().toString();
            selectsql.append(entry).append(",");
        }
        selectsql.setLength(selectsql.length() - 1);
        selectsql.append(" from ").append(table).append(" where ");

        Set data = datamap.entrySet();
        for (Iterator it = data.iterator(); it.hasNext();) {
            String entry = it.next().toString();
            int flag = entry.indexOf("=");
            String key = entry.substring(0, flag);
            String value = entry.substring(flag + 1);
            selectsql.append(key).append("=").append("'").append(value).append("'").append(" and ");
        }
        //将最后一个 and 去掉
        selectsql.setLength(selectsql.length() - 5);

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            pstm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pstm.executeQuery(selectsql.toString());
            rt=rs;
//            rs.close();
//            pstm.close();
//            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            AlarmDialog print = new AlarmDialog(e.getMessage());
            print.setVisible(true);
        }
        return rt;
    }
}
