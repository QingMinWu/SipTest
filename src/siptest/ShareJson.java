/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package siptest;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author Administrator
 */
public class ShareJson {

    /*
     * @description 更新数据库中的报文内容
     * @param name 接口名
     * @param map 数据库信息
     * @param inputjson 需要更新的请求报文
     * @param outputjson 需要更新的响应报文
     * @param interfacetype 渠道type
     * @return 数据库操作结果（0为失败）
     * @version V1.2
     */
    public int UpdateJson(String name, Map map, String inputjson, String outputjson, String interfacetype) {
        String driver = "oracle.jdbc.OracleDriver";
        String url = map.get("DBURL").toString();
        String user = map.get("DBusername").toString();
        String password = map.get("DBpassword").toString();
        String sql = "update base.soa_message set input_json='" + inputjson + "',output_json='" + outputjson + "',create_date=sysdate where busi_code='" + name + "' and interface_type='" + interfacetype + "'";
        Connection con = null;
        PreparedStatement pstm = null;
        //执行成功标示
        int remark = 0;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            pstm = con.prepareStatement(sql);
            remark = pstm.executeUpdate(sql);
            pstm.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
        return remark;
    }

    /*
     * @description 插入JSON报文到数据库
     * @param name接口名
     * @param map数据库信息
     * @param inputjson请求报文
     * @param outputjson响应报文
     * @param interfacetype渠道类型
     * @return 数据库插入结果（如果为0则失败）
     * version V1.2
     */
    public int InsertJson(String name, Map map, String inputjson, String outputjson, String interfacetype) {
        String driver = "oracle.jdbc.OracleDriver";
        String url = map.get("DBURL").toString();
        String user = map.get("DBusername").toString();
        String password = map.get("DBpassword").toString();
        Connection con = null;
        PreparedStatement pstm = null;
        int remark = 0;
        String busi_code = name;
        String input_json = inputjson;
        String output_json = outputjson;
        String interface_type = interfacetype;
        String describe = "";

        //查询接口说明
        String selectsql = map.get("DBinterfacesql").toString();
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            ResultSet rs = null;
            selectsql = selectsql.replace("$OIname$", name);
            pstm = con.prepareStatement(selectsql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                describe = rs.getString("busi_access_name");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        String sql = "insert into base.soa_message values('" + busi_code + "','" + describe + "','" + input_json + "','" + output_json + "','" + interface_type + "',sysdate)";
        try {
            pstm = con.prepareStatement(sql);
            remark = pstm.executeUpdate(sql);
            pstm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return remark;
    }

    /*
     * @description 确认是否替换已存在的Json报文
     * @param name 接口名字
     * @param map 数据库信息
     * @param Json 插入的报文
     * @return 
     */
    public void Confirm(String name, Map map, String inputjson, String outputjson, String interfacetype) {

        MyDialog dlg = new MyDialog(name, map, inputjson, outputjson, interfacetype);

        dlg.show();

    }

    /*
     * @description 确认框控件，如果点击确定就替换已存在的报文
     * @version V1.2
     */
    public class MyDialog extends JDialog implements ActionListener {

        JLabel label1 = new JLabel("报文已存在，是否覆盖？");
        JButton ok = new JButton("确定");
        String name;//接口BUSI_CODE
        Map map = new HashMap();
        String inputjson;//请求报文
        String outputjson;//响应报文
        String interfacetype;//渠道TYPE

        MyDialog(String name, Map map, String inputjson, String outputjson, String interfacetype) {
            this.name = name;
            this.map = map;
            this.inputjson = inputjson;
            this.outputjson = outputjson;
            this.interfacetype = interfacetype;
            setTitle("提示框");

            setBounds(120, 100, 260, 150);
            this.setBackground(Color.white);
            setResizable(false);
            setLayout(null);
            label1.setFont(new Font("微软雅黑", 0, 14));
            add(label1);
            label1.setBounds(40, 30, 150, 20);
            add(ok);
            ok.setBounds(80, 60, 60, 25);
            ok.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == ok) {
                UpdateJson(name, map, inputjson, outputjson, interfacetype);
            }

            dispose();

        }
    }

    /*
     * @description 删除数据库中的报文内容
     * @param name 接口名
     * @param map 数据库信息
     * @param interfacetype 渠道type
     * @return 数据库操作结果（0为失败）
     * @version V1.42
     */
    public int DeleteJson(String name, Map map, String interfacetype) {
        String driver = "oracle.jdbc.OracleDriver";
        String url = map.get("DBURL").toString();
        String user = map.get("DBusername").toString();
        String password = map.get("DBpassword").toString();
        String sql = "delete from base.soa_message where busi_code='" + name + "' and interface_type='" + interfacetype + "'";
        Connection con = null;
        PreparedStatement pstm = null;
        //执行成功标示
        int remark = 0;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            pstm = con.prepareStatement(sql);
            remark = pstm.executeUpdate(sql);
            pstm.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
        return remark;
    }
}
