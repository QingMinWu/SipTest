/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package siptest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import javax.swing.JTextArea;

/**
 *
 * @author wuqm
 */
public class UpdateOsState {
    
      public static void testOs(String request,String URL,JTextArea Response) throws Exception{
	 
      File file = new File("G:/offerId.txt");
      try{
    	  BufferedReader br = new BufferedReader(new FileReader(file));   
    	 
          String bill_id=null;
    	  System.out.println("-----begin-------");
    	  while((bill_id=br.readLine())!=null){
    		  String temp=request.replace("$bill_id$", bill_id);
                  String response=SingleTest.SendPost(temp, URL);
                  record(bill_id);
                  record(JsonFormatTool.formatJson(response," "));  
                  Response.append(bill_id+"已处理"+"\n");
                  
                          		  
    		 
             
//              System.out.println(offerId);
              
    		 
    		  
    	  }
    	  
      }catch(Exception e){
    	  e.printStackTrace();
    	  
      }
	  
  }
      private static void record(String response){
		try{
		File f = new File("G:/log.txt");// 在当前目录下生成
		// 追加方式
		Writer out = new FileWriter(f,true);// 如果文件不存在则自动创建
		String s="\r\n";
		out.write(s);// 换行                
		out.write(response);// 记录订单号
		out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
}
      
      public static void main(String [] args){
          String test="####select ^dddidkdidkdid dkwueiksdji";
          test=test.toUpperCase();
          int flag =test.indexOf("SELECT");
          System.out.println(test.substring(flag));
          
      }
}
    

