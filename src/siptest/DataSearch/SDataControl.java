/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package siptest.DataSearch;

import siptest.DataSearch.AlarmDialog;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import siptest.GetUIElement;

/**
 *
 * @author wuqm
 */
public class SDataControl {
    public void Find(Map DBmap, String businame, String param, JTextArea result, JTextArea sdatasql) throws Exception {
        String table = "base.source_data";
        Map datamap = new HashMap();
        datamap.put("busi_name", businame);
        Set column = new HashSet();
        column.add("*");
        SourceDataDao select = new SourceDataDao();
        ResultSet rt = null;
        String searchsql = null;
        //查找source_data数据的SQL
        rt = select.DataFind(DBmap, table, datamap, column);
        while (rt.next()) {
            String DBparam = rt.getString("param");
            searchsql = rt.getString("search_sql");
            if (param == null || "".equals(param) || (DBparam == null)) {
            } else {
                param = param.trim();
                //将参数拆分
                String[] params = param.split("\\|");//页面输入的参数
                String[] DBparams = DBparam.split("\\|");//数据库中存储的参数

                //替换参数
                for (int i = 0; i < params.length; i++) {
                    String targe = "{" + DBparams[i] + "}";
                    searchsql = searchsql.replace(targe, params[i]);
                }

            }
        }
        
        sdatasql.setText(searchsql);
        sdatasql.setEditable(false);
        DataDao searchdata = new DataDao();
        ResultSet datart = null;
        
        System.out.println("查找前SQL"+searchsql);
        
        datart = searchdata.DataFind(DBmap, table, searchsql);
        //查询结果数,利用游标，先使其调到最后，然后在回到第一个；
        rt.last();
        int num = rt.getRow();
        rt.beforeFirst();
        if (num == 0) {
            AlarmDialog print = new AlarmDialog("没有查询到数据！");
            print.setVisible(true);
            return;
        }
        //将查询所得的数据展示出来
        ResultHelper show = new ResultHelper();
        show.ResultDataShow(datart, result);



    }

    public void Insert(Map DBmap, JComboBox busi_name_list) throws Exception {
        InsertDataMenu datamenu = new InsertDataMenu(DBmap, busi_name_list);
        datamenu.setVisible(true);

    }

    public void Update(Map DBmap, String businame, JComboBox busi_name_list) {
        if ("".equals(businame) || businame == null) {
            AlarmDialog print = new AlarmDialog("请先选择数据");
            print.setVisible(true);
            return;
        }
        UpdateDataMenu datamenu = new UpdateDataMenu(DBmap, businame, busi_name_list);
        datamenu.setVisible(true);

    }

    public void Delete(Map DBmap, String businame, JComboBox busi_name_list) {
        if ("".equals(businame) || businame == null) {
            AlarmDialog print = new AlarmDialog("请先选择数据！");
            print.setVisible(true);
            return;
        }
        String alarm = "你确定要删除" + businame + "?    ";
        String table = "base.source_data";
        String sql = "select distinct(busi_name) from base.source_data";
        DeleteAlarmDialog print = new DeleteAlarmDialog(alarm, DBmap, businame, busi_name_list, sql, table);
        print.setVisible(true);


    }

    private class InsertDataMenu extends JDialog implements ActionListener {

        JLabel redstar1 = new JLabel("*");
        JLabel redstar2 = new JLabel("*");
        JLabel busi_name = new JLabel("数据名称:");
        JTextField busi_name_input = new JTextField();
        JLabel busi_code = new JLabel("数据编码:");
        JTextField busi_code_input = new JTextField();
        JLabel remark = new JLabel("数据说明:");
        JTextField remark_input = new JTextField();
        JLabel creater = new JLabel("创建者:");
        JTextField creater_input = new JTextField();
        JLabel sql = new JLabel("查询SQL:");
        JTextArea sql_input = new JTextArea("##SQL中含有参数请用{PARAM}表示##");
        JScrollPane sqlscroll = new JScrollPane();
        JLabel param = new JLabel("参 数:");
        JTextField param_input = new JTextField();
        JLabel param_tip = new JLabel("多个参数用\"|\"分隔");
        JButton save = new JButton("保 存");
        Map DBmap = new HashMap();
        String table = "base.source_data";
        JComboBox busi_name_list;

        InsertDataMenu(Map DBmap, JComboBox busi_name_list) {
            this.DBmap = DBmap;
            this.busi_name_list = busi_name_list;

            this.setTitle("新增");
            this.setBounds(100, 100, 800, 500);
            this.setResizable(true);
            this.setLayout(null);

            this.add(redstar1);
            redstar1.setBounds(14, 25, 5, 10);
            redstar1.setForeground(Color.red);

            this.add(busi_name);
            busi_name.setBounds(20, 20, 60, 15);
            busi_name.setFont(new Font("微软雅黑", 0, 14));

            this.add(busi_name_input);
            busi_name_input.setBounds(85, 16, 302, 27);
            busi_name_input.setFont(new Font("微软雅黑", 0, 14));

            this.add(busi_code);
            busi_code.setBounds(389, 20, 60, 15);
            busi_code.setFont(new Font("微软雅黑", 0, 14));

            this.add(busi_code_input);
            busi_code_input.setBounds(451, 16, 317, 27);
            busi_code_input.setFont(new Font("微软雅黑", 0, 14));

            this.add(remark);
            remark.setBounds(20, 50, 60, 15);
            remark.setFont(new Font("微软雅黑", 0, 14));


            this.add(remark_input);
            remark_input.setBounds(85, 44, 683, 27);
            remark_input.setFont(new Font("微软雅黑", 0, 14));

            this.add(redstar2);
            redstar2.setBounds(14, 75, 5, 10);
            redstar2.setForeground(Color.red);

            this.add(sql);
            sql.setBounds(20, 75, 60, 15);
            sql.setFont(new Font("微软雅黑", 0, 14));


            this.add(sqlscroll);
            sqlscroll.setBounds(85, 70, 683, 300);
            sql_input.setBounds(85, 70, 683, 300);
            sql_input.setFont(new Font("微软雅黑", 0, 14));
            sqlscroll.setViewportView(sql_input);
            sqlscroll.setVisible(true);
            //设置自动滚动条


            this.add(param);
            param.setBounds(40, 375, 40, 15);
            param.setFont(new Font("微软雅黑", 0, 14));

            this.add(param_input);
            param_input.setBounds(85, 371, 400, 27);
            param_input.setFont(new Font("微软雅黑", 0, 14));

            this.add(param_tip);
            param_tip.setBounds(490, 378, 100, 15);

            this.add(creater);
            creater.setBounds(595, 375, 50, 15);
            creater.setFont(new Font("微软雅黑", 0, 14));


            this.add(creater_input);
            creater_input.setBounds(650, 371, 118, 27);
            creater_input.setFont(new Font("微软雅黑", 0, 14));

            this.add(save);
            save.setBounds(345, 405, 60, 30);
            save.setFont(new Font("微软雅黑", 0, 14));

            save.addActionListener(this);

        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == save) {
                Map datamap = new HashMap();
                datamap.put("busi_name", busi_name_input.getText().toString());
                datamap.put("describe", remark_input.getText().toString());
                //去掉注释
                String search_sql=sql_input.getText().toUpperCase().toString();
                int flag1=search_sql.indexOf("SELECT");
                search_sql=search_sql.substring(flag1);
                //去掉插入语句中的分号
                int flag2=search_sql.indexOf(';');
                 if(flag2>0){
                       search_sql=search_sql.substring(0, search_sql.length()-1);
                 }
                 //转义分号
                search_sql=search_sql.replace("\'","\'\'");
                datamap.put("search_sql", search_sql);
                datamap.put("param", param_input.getText().toUpperCase().toString());
                datamap.put("creater", creater_input.getText().toString());
                datamap.put("busi_code", busi_code_input.getText().toString());
                String sql = "select distinct(busi_name) from base.source_data";
                SourceDataDao insertsql = new SourceDataDao();
                insertsql.DataInsert(DBmap, table, datamap);
                //更新数据名称列表
                GetUIElement getlistelement = new GetUIElement();
                try {
                    getlistelement.GetElementList(DBmap, busi_name_list, sql);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    AlarmDialog print = new AlarmDialog(e1.getMessage());
                    print.setVisible(true);
                }
                
            }
            dispose();
//            AlarmDialog print = new AlarmDialog("新增成功！");
//            print.setVisible(true);
        }
    }

    private class UpdateDataMenu extends InsertDataMenu {

        String businame;

        UpdateDataMenu(Map DBmap, String businame, JComboBox busi_name_list) {

            super(DBmap, busi_name_list);
            this.setTitle("修改");
            this.businame = businame;
            Map selectmap = new HashMap();
            selectmap.put("busi_name", businame);
            Set column = new HashSet();
            column.add("*");
            SourceDataDao select = new SourceDataDao();
            ResultSet rt = select.DataFind(DBmap, table, selectmap, column);
            try {
                while (rt.next()) {
                    busi_name_input.setText(rt.getString("busi_name"));
                    remark_input.setText(rt.getString("describe"));
                    creater_input.setText(rt.getString("creater"));
                    sql_input.setText(rt.getString("search_sql"));
                    param_input.setText(rt.getString("param"));
                    busi_code_input.setText(rt.getString("busi_code"));
                }
                busi_name_input.setEditable(false);
                busi_name_input.setBackground(Color.gray);
            } catch (Exception e) {
                e.printStackTrace();
                AlarmDialog print = new AlarmDialog(e.getMessage());
                print.setVisible(true);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == save) {
                Map datamap = new HashMap();
                datamap.put("busi_name", busi_name_input.getText().toString());
                datamap.put("describe", remark_input.getText().toString());
                //转义分号
                String search_sql=sql_input.getText().toUpperCase().toString();
                search_sql=search_sql.replace("\'","\'\'");
                System.out.println("修改:"+search_sql);
                datamap.put("search_sql", search_sql);
                datamap.put("param", param_input.getText().toString());
                datamap.put("creater", creater_input.getText().toString());
                datamap.put("busi_code", busi_code_input.getText().toString());
                Map oldmap = new HashMap();
                oldmap.put("busi_name", businame);
                SourceDataDao updatesql = new SourceDataDao();
                updatesql.DataUpdate(DBmap, table, oldmap, datamap);
//                AlarmDialog print = new AlarmDialog("修改成功！");
//                print.setVisible(true);
            }
            dispose();
        }
    }
}
