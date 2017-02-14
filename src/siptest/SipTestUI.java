/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package siptest;

import siptest.DataSearch.SourceDataDao;
import siptest.SSH.StopServerThread;
import siptest.SSH.SSHConnection;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JTextArea;
import javax.swing.undo.UndoManager;
import siptest.DataSearch.AlarmDialog;
import siptest.DataSearch.SDataControl;

/**
 *
 * @author wuqm
 */
public class SipTestUI extends javax.swing.JFrame {
    private UndoManager undoManager = new UndoManager();

    /**
     * Creates new form SipTestUI
     */
    public SipTestUI() {

        initComponents();

        String[] nameList = ConfigManager.ReadDBName("EVN");
        GetUIElement.getElement(DB_Name, nameList);
        //获取当前选择的数据库
        this.DBname = DB_Name.getSelectedItem().toString();
        SData_Evn.setText(this.DBname);
        this.configmap = ConfigManager.ReadData(this.DBname);
        //获取并显示配置的渠道信息
        InterfaceInfos = ConfigManager.ReadInterface(configmap.get("Interface").toString());
        GetUIElement.getInterfaceElement(Interface_Name, InterfaceInfos);
        if(configmap.containsKey("URL")){
        this.URL=configmap.get("URL").toString();
        URLInput.setText(URL);
        }
        //获取并显示app服务器信息
        
        if(configmap.containsKey("AppServer")){
        this.AppServer=configmap.get("AppServer").toString();
        appServer.setText(AppServer);
        }else{
            this.AppServer=null;
            appServer.setText("");
        }
        
        if(configmap.containsKey("AppUser")){
        this.AppUser=configmap.get("AppUser").toString();
        appUserName.setText(AppUser);
        }else{
            this.AppUser=null;
            appUserName.setText("");
        }
        
        if(configmap.containsKey("AppPwd")){
        this.AppPwd=configmap.get("AppPwd").toString();
        appPwd.setText(AppPwd);
        }else{
            this.AppPwd=null;
            appPwd.setText("");
        }
        
        if(configmap.containsKey("AppCommand")){
        this.AppCommand=configmap.get("AppCommand").toString();
        appCommand.setText(AppCommand);
        }else{
            this.AppCommand=null;
            appCommand.setText("");
        }
        
        //获取并显示soa服务器信息
        if(configmap.containsKey("SoaServer")){
        this.SoaServer=configmap.get("SoaServer").toString();
        soaServer.setText(SoaServer);
        }else{
            this.SoaServer=null;
            soaCommand.setText("");
        }
        
        if(configmap.containsKey("SoaUser")){
        this.SoaUser=configmap.get("SoaUser").toString();
        soaUserName.setText(SoaUser);
        }else{
            this.SoaUser=null;
            soaUserName.setText("");
        }
        
        if(configmap.containsKey("SoaPwd")){
        this.SoaPwd=configmap.get("SoaPwd").toString();
        soaPwd.setText(SoaPwd);
        }else{
            this.SoaPwd=null;
            soaPwd.setText("");
        }
        
        if(configmap.containsKey("SoaCommand")){
        this.SoaCommand=configmap.get("SoaCommand").toString();
        soaCommand.setText(SoaCommand);
        }else{
            this.SoaCommand=null;
            soaCommand.setText("");
        }
             
        this.AppLog=appLog;
        
        this.setTitle("接口测试工具V1.51");
        //帮助文档快捷键
        Help.setMnemonic(KeyEvent.VK_F1);
        RequestInput.getDocument().addUndoableEditListener(undoManager);
        
             
       //添加关闭窗口事件 
       addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
            if(AppServer!=null && AppUser!=null && AppPwd!=null && AppCommand!=null){
                StopServerThread closeApp=new StopServerThread(AppServer,AppUser,AppPwd,AppCommand,null);
                closeApp.Killrun();
            }
          
            if(SoaServer!=null && SoaUser!=null && SoaPwd!=null && SoaCommand!=null){
                StopServerThread closeSoa=new StopServerThread(SoaServer,SoaUser,SoaPwd,SoaCommand,null);
                closeSoa.Killrun();
            }         
       }
       });
       
       try{
        GetUIElement.GetElementList(configmap, SBusi_Name_List,sql);
       }catch(Exception e){
           STip.setText("获取数据名称失败");
       }
       
        

    }
    String request;//输入报文
    String response;//输出报文
    String DBname;//版本
    public static Map configmap;//congig信息
    public static String[][] InterfaceInfos;
    public static String[][] UrlInfos;
    public static String AppServer;//App服务器
    public static String AppUser;//用户名
    public static String AppPwd;//密码
    public static String AppCommand;//命令
    public static String SoaServer;//soa服务器
    public static String SoaUser;//用户名
    public static String SoaPwd;//密码
    public static String SoaCommand;//命令
    public static String URL;//请求地址
    public static JTextArea AppLog;
    public static Thread appthread;//app日志线程
    public static Thread soathread;//soa日志线程
    String sql = "select distinct(busi_name) from base.source_data";//获取数据名称的SQL
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        DB_Name = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        Interface_Name = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        OInameInput = new javax.swing.JTextField();
        SummitRequest = new javax.swing.JButton();
        FindResquest = new javax.swing.JButton();
        ShareResult = new javax.swing.JButton();
        DeleteJson = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        URLInput = new javax.swing.JTextField();
        ClearLog = new javax.swing.JButton();
        Help = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Log = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        ClearRequest = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        ClearResponse = new javax.swing.JButton();
        SaveResult = new javax.swing.JButton();
        JScrollPanel = new javax.swing.JScrollPane();
        RequestInput = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        ResponseInput = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        appIP = new javax.swing.JLabel();
        appServer = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        appUserName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        appPwd = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        appCommand = new javax.swing.JTextField();
        appClearLog = new javax.swing.JButton();
        appSaveLog = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        appLog = new javax.swing.JTextArea();
        appSubmit = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        soaServer = new javax.swing.JTextField();
        soaUserName = new javax.swing.JTextField();
        soaPwd = new javax.swing.JTextField();
        soaCommand = new javax.swing.JTextField();
        soaClearLog = new javax.swing.JButton();
        soaSaveLog = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        soaLog = new javax.swing.JTextArea();
        soaSubmit = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        SData_Evn = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        SBusi_Name_List = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        SParam_Input = new javax.swing.JTextField();
        SSearch = new javax.swing.JButton();
        SInsert = new javax.swing.JButton();
        SUpdate = new javax.swing.JButton();
        SDelete = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        SData_SQL = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        SResult = new javax.swing.JTextArea();
        STip = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setFont(new java.awt.Font("宋体", 1, 16)); // NOI18N

        jLabel1.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jLabel1.setText("版本：");

        DB_Name.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        DB_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DB_NameActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jLabel2.setText("渠 道：");

        Interface_Name.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jLabel4.setText("接口名 :");

        OInameInput.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N

        SummitRequest.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        SummitRequest.setText("提交请求");
        SummitRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SummitRequestActionPerformed(evt);
            }
        });

        FindResquest.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        FindResquest.setText("查找报文");
        FindResquest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FindResquestActionPerformed(evt);
            }
        });

        ShareResult.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        ShareResult.setText("保存报文");
        ShareResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShareResultActionPerformed(evt);
            }
        });

        DeleteJson.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        DeleteJson.setText("删除保存报文");
        DeleteJson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteJsonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jLabel3.setText("URL :");

        URLInput.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        URLInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                URLInputActionPerformed(evt);
            }
        });

        ClearLog.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        ClearLog.setText("清空日志");
        ClearLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearLogActionPerformed(evt);
            }
        });

        Help.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        Help.setText("帮助文档");
        Help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HelpActionPerformed(evt);
            }
        });

        Log.setColumns(20);
        Log.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        Log.setRows(5);
        Log.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setViewportView(Log);

        jLabel5.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jLabel5.setText("输入报文");

        ClearRequest.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        ClearRequest.setText("清空报文");
        ClearRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearRequestActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jLabel6.setText("输出报文");

        ClearResponse.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        ClearResponse.setText("清空报文");
        ClearResponse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearResponseActionPerformed(evt);
            }
        });

        SaveResult.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        SaveResult.setText("本地保存");
        SaveResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveResultActionPerformed(evt);
            }
        });

        RequestInput.setColumns(20);
        RequestInput.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        RequestInput.setRows(5);
        JScrollPanel.setViewportView(RequestInput);

        ResponseInput.setEditable(false);
        ResponseInput.setColumns(20);
        ResponseInput.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        ResponseInput.setRows(5);
        jScrollPane2.setViewportView(ResponseInput);

        jButton1.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jButton1.setText("格式化");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jScrollPane1)
                    .addContainerGap())
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(DB_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Interface_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(OInameInput))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(URLInput, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(SummitRequest)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ClearLog)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Help))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(FindResquest)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ShareResult)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(DeleteJson)))
                    .addContainerGap(211, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ClearRequest)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JScrollPanel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ClearResponse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SaveResult)
                        .addGap(445, 445, 445))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(DB_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(Interface_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OInameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FindResquest)
                    .addComponent(ShareResult)
                    .addComponent(DeleteJson))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(URLInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClearLog)
                    .addComponent(Help)
                    .addComponent(SummitRequest))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ClearRequest)
                    .addComponent(ClearResponse)
                    .addComponent(SaveResult)
                    .addComponent(jButton1)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                    .addComponent(JScrollPanel)))
        );

        jTabbedPane1.addTab("接口测试", jPanel1);

        jTabbedPane2.setFont(new java.awt.Font("黑体", 0, 14)); // NOI18N

        appIP.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        appIP.setText("服务器IP：");

        appServer.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jLabel8.setText("用户名：");

        appUserName.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jLabel9.setText("密 码：");

        appPwd.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jLabel10.setText("执行命令：");

        appCommand.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N

        appClearLog.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        appClearLog.setText("清空日志");
        appClearLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appClearLogActionPerformed(evt);
            }
        });

        appSaveLog.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        appSaveLog.setText("保存日志");
        appSaveLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appSaveLogActionPerformed(evt);
            }
        });

        appLog.setColumns(20);
        appLog.setRows(5);
        jScrollPane3.setViewportView(appLog);

        appSubmit.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        appSubmit.setText("手工执行");
        appSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(appIP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(appServer, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(appUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(appPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(appCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(appSubmit))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(appClearLog)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(appSaveLog)))
                        .addContainerGap(156, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appIP)
                    .addComponent(appServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(appUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(appPwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(appCommand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appSubmit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appClearLog)
                    .addComponent(appSaveLog))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("APP日志", jPanel4);

        jLabel7.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jLabel7.setText("服务器IP：");

        jLabel11.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jLabel11.setText("用户名：");

        jLabel12.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jLabel12.setText("密 码：");

        jLabel13.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jLabel13.setText("执行命令：");

        soaServer.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        soaServer.setText(" ");

        soaUserName.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        soaUserName.setText(" ");

        soaPwd.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N

        soaCommand.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N

        soaClearLog.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        soaClearLog.setText("清空日志");
        soaClearLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soaClearLogActionPerformed(evt);
            }
        });

        soaSaveLog.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        soaSaveLog.setText("保存日志");
        soaSaveLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soaSaveLogActionPerformed(evt);
            }
        });

        soaLog.setColumns(20);
        soaLog.setRows(5);
        jScrollPane4.setViewportView(soaLog);

        soaSubmit.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        soaSubmit.setText("手工执行");
        soaSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soaSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(soaServer, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(soaUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(soaPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(soaCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(soaSubmit))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(soaClearLog)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(soaSaveLog)))
                        .addContainerGap(148, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(soaServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soaUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soaPwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soaCommand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soaSubmit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(soaClearLog)
                    .addComponent(soaSaveLog))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("SOA日志", jPanel6);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("日 志", jPanel2);

        jLabel14.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jLabel14.setText("数据库：");

        SData_Evn.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        SData_Evn.setText("***********");
        SData_Evn.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(0), null));

        jLabel16.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jLabel16.setText("数据名称：");

        SBusi_Name_List.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        SBusi_Name_List.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        SBusi_Name_List.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SBusi_Name_ListActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jLabel17.setText("参数：");

        SParam_Input.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N

        SSearch.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        SSearch.setText("查 询");
        SSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SSearchActionPerformed(evt);
            }
        });

        SInsert.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        SInsert.setText("新 增");
        SInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SInsertActionPerformed(evt);
            }
        });

        SUpdate.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        SUpdate.setText("修 改");
        SUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SUpdateActionPerformed(evt);
            }
        });

        SDelete.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        SDelete.setText("删 除");
        SDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SDeleteActionPerformed(evt);
            }
        });

        SData_SQL.setColumns(20);
        SData_SQL.setRows(5);
        jScrollPane5.setViewportView(SData_SQL);

        SResult.setColumns(20);
        SResult.setFont(new java.awt.Font("黑体", 0, 13)); // NOI18N
        SResult.setRows(5);
        jScrollPane6.setViewportView(SResult);

        STip.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        STip.setText("选择需要查询的数据，如果SQL中有参数，输入参数后点击【查询】；如果需要录入新增的数据查找SQL，点击【新增】，修改已有的数据SQL,点击【修改】，删除点击【删除】；所有查找的SQL都存储在base.source_data表中");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(STip)
                        .addGap(0, 160, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SData_Evn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SBusi_Name_List, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SParam_Input, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SInsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SDelete)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jScrollPane5)
            .addComponent(jScrollPane6)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel16)
                        .addComponent(SBusi_Name_List, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)
                        .addComponent(SParam_Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SSearch)
                        .addComponent(SInsert)
                        .addComponent(SUpdate)
                        .addComponent(SDelete))
                    .addComponent(SData_Evn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(STip, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("数据查找", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
     * @description 查找报文，如果报文存在于数据库base.soa_message中直接显示，如果不存在则查找参数拼接
     * @version V1.1
     */
    private void FindResquestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FindResquestActionPerformed
        // TODO add your handling code here:
        Log.setText("连接数据库......\n");
        if (configmap.isEmpty()) {
            Log.setText("数据库配置信息读取失败，请检查config.xml文件");
        } else {
            String OIname;//接口名
            String InterfaceName;//渠道名称

            OIname = OInameInput.getText();
            InterfaceName = Interface_Name.getSelectedItem().toString();

            if (OIname.equals("")) {
                Log.setText("接口名不可为空！");
            } else {

                //查找报文，数据库中存在报文则直接查找出来，没有就进行拼接
                String interfacetype = GetUIElement.getInterfaceType(InterfaceName, InterfaceInfos);
                Map mapjson = new HashMap(SingleTest.FindJson(OIname, configmap, interfacetype));
                if (mapjson.isEmpty()) {
                    request = SingleTest.Montage(OIname, configmap, InterfaceName);
                    RequestInput.setText(request);
                    Log.setText("拼接报文");
                    Log.setText(SingleTest.remark);
                } else {
                    String request = mapjson.get("request").toString();
                    RequestInput.setText(JsonFormatTool.formatJson(request, "     "));
                    Log.setText("查找报文成功！");
                }
            }
        }
    }//GEN-LAST:event_FindResquestActionPerformed

    /*
     * @description 提交post请求测试结果
     * @version V1.1
     */
    private void SummitRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SummitRequestActionPerformed
        String InterfaceName;//渠道名称
        InterfaceName = Interface_Name.getSelectedItem().toString();
        String interfacetype = GetUIElement.getInterfaceType(InterfaceName, InterfaceInfos);
        String URL;//接口地址
        URL = URLInput.getText();
        if (URL.equals("")) {
            Log.setText("接口地址不可为空！");
        } else {
            //V1.43获取请求报文中的接口名
            request = RequestInput.getText();
            request = request.replace(" ", "");//清除空格
            request = request.replace("\\s", "");//清除空格
            request = request.replace("\n", "");//清除回车
            request = request.replace("\\t", "");//清除制表符
            request = request.replace("\\r", "");//清除换行符
            int start = request.indexOf("\"BusiCode\":\"");
            int end = request.indexOf("\"", start+12);
            String name = request.substring(start+12, end);//获取报文中的接口名

            OInameInput.setText(name);//显示在接口名栏中

            request = JsonFormatTool.formatJson(RequestInput.getText().toString(), "  ");//格式化输入报文
            RequestInput.setText(request);


            response = SingleTest.SendPost(request, URL);//提交POST请求
            Log.setText("访问接口......\n");


            int result = 0;
            result = response.indexOf("\"Code\":\"0000\"");//如果接口第一次调用成功，自动插输入报文
            //格式化输出报文
            response = JsonFormatTool.formatJson(response, "  ");
            ResponseInput.setText(response);
            if (result > 0) {
                if (configmap.isEmpty()) {
                    Log.setText("数据库配置信息读取失败，请检查config.xml文件");
                    return;
                }
                Map mapjson = new HashMap(SingleTest.FindJson(name, configmap, interfacetype));//查找报文是否已经存在于数据库
                //如果不存在
                if (mapjson.isEmpty()) { 
                    if (RequestInput.getText().toString().equals("")) {
                        Log.setText("报文不能为空");
                        return;
                    }
                    String input_json = RequestInput.getText().toString();
                    ShareJson insert = new ShareJson();

                    int mark = insert.InsertJson(name, configmap, input_json, response, interfacetype);

                    if (mark > 0) {
                        Log.setText("报文插入数据库成功！");
                    } else {
                        Log.setText("报文插入数据库不成功！");
                    }
                }else{
                    
                }
            }

            Log.setText(SingleTest.remark);//返回操作提示
        }

    }//GEN-LAST:event_SummitRequestActionPerformed

    /*
     * @description 清空输出报文
     * @version V1.1
     */
    private void ClearResponseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearResponseActionPerformed
        // TODO add your handling code here:

        ResponseInput.setText("");
    }//GEN-LAST:event_ClearResponseActionPerformed
    /*
     * @description 清空输入报文
     * @version V1.1
     */
    private void ClearRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearRequestActionPerformed
        // TODO add your handling code here:
        RequestInput.setText("");
    }//GEN-LAST:event_ClearRequestActionPerformed

    /*
     * @description 保存测试结果，包括接口名，输入报文，输出报文
     * @version V1.2
     */
    private void SaveResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveResultActionPerformed
        // TODO add your handling code here:
        String path = FileChooserAction.FileSavePath(OInameInput.getText());
        StringBuilder content = new StringBuilder();
        content.append("接口名:").append("\r\n").append(OInameInput.getText()).append("\r\n").append("\r\n");
        content.append("输入报文:").append("\r\n").append(RequestInput.getText()).append("\r\n").append("\r\n");
        content.append("输出报文:").append("\r\n").append(ResponseInput.getText()).append("\r\n").append("\r\n");
        Log.setText(FileChooserAction.SaveFile(path, (content.toString())));
    }//GEN-LAST:event_SaveResultActionPerformed

    /*
     * @description 帮助文档
     * @version V1.2
     */
    private void HelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HelpActionPerformed
        // TODO add your handling code here:
        try {
            String[] arg = new String[3];
            arg[0] = "cmd";
            arg[1] = "/c";
            arg[2] = "Explorer.exe .\\README.txt";
            Runtime.getRuntime().exec(arg);
        } catch (Exception e) {
            e.printStackTrace();
            Log.setText("帮助文件打开失败");
        }
    }//GEN-LAST:event_HelpActionPerformed

    /*
     * @description 共享报文，即报文入库保存
     * @version V1.2
     */
    private void ShareResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShareResultActionPerformed
        // TODO add your handling code here:
        String name = OInameInput.getText().toString();
        if (name.equals("")) {
            Log.setText("接口名不能为空！");
            return;
        }
        String InterfaceName;//渠道名称
        InterfaceName = Interface_Name.getSelectedItem().toString();
        String interfacetype = GetUIElement.getInterfaceType(InterfaceName, InterfaceInfos);
        Map mapjson = new HashMap(SingleTest.FindJson(name, configmap, interfacetype));//查找报文是否已经存在于数据库
        if (mapjson.isEmpty()) {//不存在数据库中
            if (RequestInput.getText().toString().equals("")) {
                Log.setText("输入报文不能为空！");
                return;
            }
            String input_json = RequestInput.getText().toString();
            String output_json = ResponseInput.getText().toString();
            ShareJson insert = new ShareJson();
            //String description=SingleTest.remark;
            int mark = insert.InsertJson(name, configmap, input_json, output_json, interfacetype);
            if (mark > 0) {
                Log.setText("报文插入数据库成功！");
            } else {
                Log.setText("报文插入数据库不成功！");
            }
        } else {//原有接口报文存在于数据库中
            ShareJson confirm = new ShareJson();//询问是否覆盖原来的报文
            String input_json = RequestInput.getText().toString();
            String output_json = ResponseInput.getText().toString();
            try {
                confirm.Confirm(name, configmap, input_json, output_json, interfacetype);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.setText("操作成功！");
        }
    }//GEN-LAST:event_ShareResultActionPerformed

    private void DB_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DB_NameActionPerformed
        try{
            if(appthread.isAlive()){
                appthread.interrupt();//杀死工具监听日志的线程
                StopServerThread closeApp=new StopServerThread(AppServer,AppUser,AppPwd,AppCommand,null);
                closeApp.Killrun(); //杀死服务器上的线程
            }            
            
        }catch(Exception e){
            System.out.println("appthread is null");
            
        }
        
        try{
            if(soathread.isAlive()){
                soathread.interrupt();//杀死工具监听日志的线程
                StopServerThread closeSoa=new StopServerThread(SoaServer,SoaUser,SoaPwd,SoaCommand,null);
                closeSoa.Killrun();//杀死服务器上的线程
            }
            
        }catch(Exception e){
            System.out.println("soathread is null");
          
        }
        
      
        
        //实时监听版本
        this.DBname = DB_Name.getSelectedItem().toString();
        SData_Evn.setText(this.DBname);
        this.configmap = ConfigManager.ReadData(this.DBname);
                //获取并显示URL信息
        if(configmap.containsKey("URL")){
        this.URL=configmap.get("URL").toString();
        URLInput.setText(URL);
        }
        //获取并显示app服务器信息
        
        if(configmap.containsKey("AppServer")){
        this.AppServer=configmap.get("AppServer").toString();
        appServer.setText(AppServer);
        }else{
            this.AppServer=null;
            appServer.setText("");
        }
        
        if(configmap.containsKey("AppUser")){
        this.AppUser=configmap.get("AppUser").toString();
        appUserName.setText(AppUser);
        }else{
            this.AppUser=null;
            appUserName.setText("");
        }
        
        if(configmap.containsKey("AppPwd")){
        this.AppPwd=configmap.get("AppPwd").toString();
        appPwd.setText(AppPwd);
        }else{
            this.AppPwd=null;
            appPwd.setText("");
        }
        
        if(configmap.containsKey("AppCommand")){
        this.AppCommand=configmap.get("AppCommand").toString();
        appCommand.setText(AppCommand);
        }else{
            this.AppCommand=null;
            appCommand.setText("");
        }
        
        //获取并显示soa服务器信息
        if(configmap.containsKey("SoaServer")){
        this.SoaServer=configmap.get("SoaServer").toString();
        soaServer.setText(SoaServer);
        }else{
            this.SoaServer=null;
            soaServer.setText("");
        }
        
        if(configmap.containsKey("SoaUser")){
        this.SoaUser=configmap.get("SoaUser").toString();
        soaUserName.setText(SoaUser);
        }else{
            this.SoaUser=null;
            soaUserName.setText("");
        }
        
        if(configmap.containsKey("SoaPwd")){
        this.SoaPwd=configmap.get("SoaPwd").toString();
        soaPwd.setText(SoaPwd);
        }else{
            this.SoaPwd=null;
            soaPwd.setText("");
        }
        
        if(configmap.containsKey("SoaCommand")){
        this.SoaCommand=configmap.get("SoaCommand").toString();
        soaCommand.setText(SoaCommand);
        }else{
            this.SoaCommand=null;
            soaCommand.setText("");
        }
             
        if(AppServer!=null && AppUser!=null  && AppPwd!=null && AppCommand!=null){
            System.out.println("1062");
            appthread  =new SSHConnection(AppServer,AppUser,AppPwd,AppCommand,appLog);
            appthread.start();//重启线程获取日志
        }
        
       if(SoaServer!=null && SoaUser!=null && SoaPwd!=null && SoaCommand!=null){
           System.out.println("1068");
           soathread  =new SSHConnection(SoaServer,SoaUser,SoaPwd,SoaCommand,soaLog);
           soathread.start(); //重启线程获取日志
       }
       
      try{
        GetUIElement.GetElementList(configmap, SBusi_Name_List,sql);
       }catch(Exception e){
           STip.setText("获取数据名称失败");
       }
    

    }//GEN-LAST:event_DB_NameActionPerformed

    private void ClearLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearLogActionPerformed
        // TODO add your handling code here:
        Log.setText("");//清空日志
    }//GEN-LAST:event_ClearLogActionPerformed

    private void DeleteJsonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteJsonActionPerformed
        // 删除数据库中接口报文
        if (OInameInput == null) {
            Log.setText("接口名不能为空！");
        } else {
            String interfacetype = GetUIElement.getInterfaceType(Interface_Name.getSelectedItem().toString(), InterfaceInfos);
            ShareJson deleteJson = new ShareJson();
            int result = deleteJson.DeleteJson(OInameInput.getText(), configmap, interfacetype);
            if (result == 0) {
                Log.setText("报文删除失败！");
            } else {
                Log.setText("报文删除成功！");
                RequestInput.setText("");
                ResponseInput.setText("");
            }
        }
    }//GEN-LAST:event_DeleteJsonActionPerformed

    private void URLInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_URLInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_URLInputActionPerformed

    private void appClearLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appClearLogActionPerformed
        // TODO add your handling code here:
        appLog.setText("");
    }//GEN-LAST:event_appClearLogActionPerformed

    private void soaClearLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soaClearLogActionPerformed
        // TODO add your handling code here:
        soaLog.setText("");
    }//GEN-LAST:event_soaClearLogActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //格式化输入报文
         RequestInput.setText(JsonFormatTool.formatJson(RequestInput.getText().toString(), "  "));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void soaSaveLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soaSaveLogActionPerformed
        // TODO add your handling code here:
        String name = FileChooserAction.FileSavePath(OInameInput.getText()+"-soaLog");
        FileChooserAction.SaveFile(name, (soaLog.getText().toString()));
        
    }//GEN-LAST:event_soaSaveLogActionPerformed

    private void appSaveLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appSaveLogActionPerformed
        // TODO add your handling code here:
        String name = FileChooserAction.FileSavePath(OInameInput.getText()+"-appLog");
        FileChooserAction.SaveFile(name, (appLog.getText().toString()));
    }//GEN-LAST:event_appSaveLogActionPerformed

    private void appSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appSubmitActionPerformed
        // TODO add your handling code here:
        if (appthread.isAlive()){
            appthread.interrupt();//杀死线程
        }
        StopServerThread closeApp=new StopServerThread(AppServer,AppUser,AppPwd,AppCommand,null);
        closeApp.Killrun();//关闭后台命令
        
        appthread  =new SSHConnection(appServer.getText(),appUserName.getText(),appPwd.getText(),appCommand.getText(),appLog);
        appthread.start();
     
    }//GEN-LAST:event_appSubmitActionPerformed

    private void soaSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soaSubmitActionPerformed
        // TODO add your handling code here:
        if (soathread.isAlive()){
            soathread.interrupt();//杀死线程
        }
        StopServerThread closeSoa=new StopServerThread(SoaServer,SoaUser,SoaPwd,SoaCommand,null);
        closeSoa.Killrun();//关闭后台命令
        soathread  =new SSHConnection(soaServer.getText(),soaUserName.getText(),soaPwd.getText(),soaCommand.getText(),soaLog);
        soathread.start(); 
    }//GEN-LAST:event_soaSubmitActionPerformed

    private void SBusi_Name_ListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SBusi_Name_ListActionPerformed
        // TODO add your handling code here:
        if (SBusi_Name_List.getSelectedItem() != null) {
            String busi_name = SBusi_Name_List.getSelectedItem().toString();
            if (" ".equals(busi_name)) {
            } else {
                String table = "base.source_data";
                Map datamap = new HashMap();
                datamap.put("busi_name", busi_name);
                Set column = new HashSet();
                column.add("*");
                SourceDataDao select = new SourceDataDao();
                ResultSet rt = null;
                String searchsql = null;
                //查找source_data数据的SQL
                rt = select.DataFind(configmap, table, datamap, column);
                try {
                    while (rt.next()) {
                        //打印数据提示
                        STip.setText("数据说明："+rt.getString("describe")+"    "+"参数："+rt.getString("param"));
                        SData_SQL.setText(rt.getString("search_sql"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            SResult.setText("");
    }

    }//GEN-LAST:event_SBusi_Name_ListActionPerformed

    private void SSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SSearchActionPerformed
        // TODO add your handling code here:
            String busi_name = SBusi_Name_List.getSelectedItem().toString();
        if (" ".equals(busi_name)) {
            AlarmDialog print = new AlarmDialog("请先选择数据!");
            print.setVisible(true);
            return;
        }
        String param = SParam_Input.getText();
        SDataControl find = new SDataControl();
        SResult.setText("");
        SParam_Input.setText("");
        try {
            find.Find(configmap, busi_name, param, SResult, SData_SQL);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_SSearchActionPerformed

    private void SInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SInsertActionPerformed
        // TODO add your handling code here:
                SDataControl newdata = new SDataControl();
        try {
            newdata.Insert(configmap, SBusi_Name_List);
        } catch (Exception e) {
            e.printStackTrace();
            AlarmDialog print = new AlarmDialog(e.getMessage());
            print.setVisible(true);
        }
        SData_SQL.setText("");
        SResult.setText("");
        SParam_Input.setText("");
    }//GEN-LAST:event_SInsertActionPerformed

    private void SUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SUpdateActionPerformed
        // TODO add your handling code here:
        String busi_name = SBusi_Name_List.getSelectedItem().toString();
        SDataControl update = new SDataControl();
        update.Update(configmap, busi_name, SBusi_Name_List);
        SData_SQL.setText("");
        SResult.setText("");
        SParam_Input.setText("");
        
    }//GEN-LAST:event_SUpdateActionPerformed

    private void SDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SDeleteActionPerformed
        // TODO add your handling code here:
         String busi_name = SBusi_Name_List.getSelectedItem().toString();
        SDataControl delete = new SDataControl();
        delete.Delete(configmap, busi_name, SBusi_Name_List);
        SData_SQL.setText("");
        SResult.setText("");
        SParam_Input.setText("");
        STip.setText("");
    }//GEN-LAST:event_SDeleteActionPerformed
private void close(java.awt.event.ActionEvent evt){
    
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SipTestUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SipTestUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SipTestUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SipTestUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        
       

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SipTestUI().setVisible(true);
            }
            
        });
        
        

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClearLog;
    private javax.swing.JButton ClearRequest;
    private javax.swing.JButton ClearResponse;
    private javax.swing.JComboBox DB_Name;
    private javax.swing.JButton DeleteJson;
    private javax.swing.JButton FindResquest;
    private javax.swing.JButton Help;
    private javax.swing.JComboBox Interface_Name;
    private javax.swing.JScrollPane JScrollPanel;
    private javax.swing.JTextArea Log;
    private javax.swing.JTextField OInameInput;
    private javax.swing.JTextArea RequestInput;
    private javax.swing.JTextArea ResponseInput;
    private javax.swing.JComboBox SBusi_Name_List;
    private javax.swing.JLabel SData_Evn;
    private javax.swing.JTextArea SData_SQL;
    private javax.swing.JButton SDelete;
    private javax.swing.JButton SInsert;
    private javax.swing.JTextField SParam_Input;
    private javax.swing.JTextArea SResult;
    private javax.swing.JButton SSearch;
    private javax.swing.JLabel STip;
    private javax.swing.JButton SUpdate;
    private javax.swing.JButton SaveResult;
    private javax.swing.JButton ShareResult;
    private javax.swing.JButton SummitRequest;
    private javax.swing.JTextField URLInput;
    private javax.swing.JButton appClearLog;
    private javax.swing.JTextField appCommand;
    private javax.swing.JLabel appIP;
    private javax.swing.JTextArea appLog;
    private javax.swing.JTextField appPwd;
    private javax.swing.JButton appSaveLog;
    private javax.swing.JTextField appServer;
    private javax.swing.JButton appSubmit;
    private javax.swing.JTextField appUserName;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JButton soaClearLog;
    private javax.swing.JTextField soaCommand;
    private javax.swing.JTextArea soaLog;
    private javax.swing.JTextField soaPwd;
    private javax.swing.JButton soaSaveLog;
    private javax.swing.JTextField soaServer;
    private javax.swing.JButton soaSubmit;
    private javax.swing.JTextField soaUserName;
    // End of variables declaration//GEN-END:variables
}
