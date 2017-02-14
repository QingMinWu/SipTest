/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package siptest.SSH;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JTextArea;

/**
 *
 * @author wuqm
 */
public class StopServerThread{
     public String Ip;
    public String User;
    public String Pwd;
    public String Comm;
    public JTextArea Log;
    public StopServerThread(String Ip,String User,String Pwd,String Comm,JTextArea Log){
        this.Ip=Ip;
        System.out.println(Ip);
        this.User=User;
        System.out.println(User);
        this.Pwd=Pwd;
        System.out.println(Pwd);
        this.Comm=Comm;
        System.out.println(Comm);
        this.Log=Log;
    }
    public void Killrun(){
     int flag;
     String condition=null;
//     System.out.println(Comm);
     if(Comm.contains("|")){
         flag=Comm.indexOf("|");
         condition=Comm.substring(flag);
//     System.out.println(condition);
     }
     if(!(condition == null)){
         Comm="ps -ef " +condition+"| grep \"csh -c\" |awk -F \" \" '{print $2}'";    
       try{
            Connection conn=new Connection(Ip);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(User, Pwd);
            if (isAuthenticated == false)
                throw new IOException("Authentication failed.");
            Session sess1 = conn.openSession();
            Session sess2=conn.openSession();
          
            sess1.execCommand(Comm); 
            System.out.println("执行命令："+Comm);
            InputStream stdout = new StreamGobbler(sess1.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout,"GBK"));
              String line=null;
              try{
               line = br.readLine();
                if (line == null){
                    
                }else{                    
               sess2.execCommand("kill "+line);
               System.out.print("执行命令："+"kill "+line);
                }
              }catch(Exception e){
                  e.printStackTrace();
              }
              
            sess1.close();
            sess2.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
     }
 }
    
}
