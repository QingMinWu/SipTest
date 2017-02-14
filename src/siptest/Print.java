package siptest;

public class Print {
	public static void print(String str){
		// 打印信息
		System.out.println(str);
		// 记录日志
//		try{
//			File f = new File("./log.txt");// 在当前目录下生成
//			// 追加方式
//			Writer out = new FileWriter(f,true);// 如果文件不存在则自动创建
//			String s="\r\n\n";
//			out.write(s);// 换行
//                       s=(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
//			//s=new Date().toString();
//			out.write(s);// 记录日志时间
//			s="------------";
//			out.write(s);
//			s=str;
//			out.write(s);
//			out.close();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		
	}
}
