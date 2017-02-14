/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package siptest.SSH;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.Writer;
import javax.swing.JTextArea;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import java.io.FileWriter;
import java.util.Date;


/**
 * @description 链接服务器获取操作日志,V1.4新增功能
 * @author wuqm
 * 
 */
public class SSHConnection extends Thread{
 
    public String Ip;
    public String User;
    public String Pwd;
    public String Comm;
    public JTextArea Log;
  /**
  * 
  * @param Ip 链接的主机
  * @param User 用户名
  * @param Pwd 密码
  * @param cmmd 执行的命令
  * @return 获取的日志
  * @version V1.4
  */
    public SSHConnection(String Ip,String User,String Pwd,String Comm,JTextArea Log){
        this.Ip=Ip;
        this.User=User;
        this.Pwd=Pwd;
        this.Comm=Comm;
        this.Log=Log;
     
 }
 
    @Override
    public void run(){
        try{

            Connection conn=new Connection(Ip);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(User, Pwd);
            if (isAuthenticated == false)
                throw new IOException("Authentication failed.");
            Session sess = conn.openSession();
            sess.execCommand(Comm);  
            System.out.println("执行命令："+Comm);
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout,"GBK"));
//            InitLog();//初始化日志文件

            while (true)
            {               
              String line=null;
              try{
               line = br.readLine();
              }catch(Exception e){
                  e.printStackTrace();
              }
               if (line == null)
                    break;
                //输出到页面
               Log.append(line);
               Log.append("\n");
//               //保存在本地
//               SaveLogToLocal(line);
//               SaveLogToLocal("\n");
            }
           
            sess.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    /*
     * @description 初始化临时日志文件 
     * @version V1.4
     * 
     */
// public void InitLog(){
//     File f=new File("."+File.separator+"Log.txt");
//     try{
//         Writer out=new FileWriter(f);
//         String s=(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
//         out.write(s);
//         out.flush();
//         out.close();
//     }catch(Exception e){
//         e.printStackTrace();
//     } 
//     
// }
 /*
  * @param str需要保存的日志信息
  * @description 保存服务器本次的日志到本地
  * @version V1.4
  */
// public void SaveLogToLocal(String str){
//     File f=new File("."+File.separator+"Log.txt");
//     try{
//         Writer out=new FileWriter(f,true);//追加日志信息
//         out.write(str);
//         out.flush();
//         out.close();
//     }catch(Exception e){
//         e.printStackTrace();
//     } 
// }
 
 
public static void main(String []args){
    String ip="10.3.3.188";
    String user="aiquery";
    String pwd="gzcrm%123";
    String comm="tailapp.sh |grep 96660027";
    JTextArea Log;
//    SSHConnection test=new SSHConnection(ip,user,pwd,comm,null);
//    test.start();
     try{
            System.out.println(ip+"     "+ comm);
            Connection conn=new Connection(ip);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(user, pwd);
            if (isAuthenticated == false)
                throw new IOException("Authentication failed.");
            Session sess = conn.openSession();
            sess.execCommand(comm);  
            System.out.println("执行命令："+comm);         
            sess.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
}
}
