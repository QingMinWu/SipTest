package siptest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.*;
import java.net.*;
import static siptest.SipTestUI.InterfaceInfos;

/*
 * @description 拼接报文提交请求
 */
public class SingleTest {

    public static String remark;//拼接提示

 /*
  * @description 查找入参拼接JSON报文
  * @param name 接口名字
  * @param map 数据库信息
  * @param interfacename 渠道名称（V1.41）interfaceid和interfacetype参数化
  * @return 拼装好的报文
  */
    public static String Montage(String name, Map map,String interfacename) {
        //获取数据库配置
        String driver = "oracle.jdbc.OracleDriver";
        String url = map.get("DBURL").toString();
        String user = map.get("DBusername").toString();
        String password = map.get("DBpassword").toString();
        String paramsql = map.get("DBparamsql").toString();
        String interfacesql = map.get("DBinterfacesql").toString();
        
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        StringBuilder request = null;
        boolean flag = false;
        String result = "";
        int channel = 0;
        String sql = "";


        StringBuilder input = new StringBuilder();
        StringBuilder mark = new StringBuilder();
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            // 根据接口名查询数据库中接口参数
            System.out.println("connect..." + new Date());
            //查询参数SQL
            //替换接口名
            paramsql = paramsql.replace("$OIname$", name);
            //替换渠道ID
            paramsql=paramsql.replace("$interfaceid$",GetUIElement.getInterfaceId(interfacename, InterfaceInfos));
            pstm = con.prepareStatement(paramsql);
            rs = pstm.executeQuery();
            Print.print("数据库连接成功，根据接口名查找报文参数");
            System.out.print(new Date());

            Print.print("该接口共有以下入参");
            mark.append("该接口共有以下入参\n");
            ArrayList List = new ArrayList();
            ArrayList ListPoint = new ArrayList();
            while (rs.next()) {
                String busiparam = rs.getString(1);//入参
                String paramname = rs.getString(2);//入参名称
                int isnull = rs.getInt(3);
                channel = rs.getInt(4);

                if (isnull == 1) {
                    Print.print("【" + busiparam + "】" + paramname + "(此参数可为空)\n");
                    mark.append("【" + busiparam + "】" + paramname + "\n");
                } else {
                    Print.print("【" + busiparam + "】" + paramname + "(此参数不可为空)\n");
                    mark.append("【" + busiparam + "】" + paramname + "(此参数不可为空)\n");
                }
                int judge = busiparam.indexOf("[");//判断是否有[
                int pos = busiparam.indexOf(".");//判断是否有.

                if (judge > 0 && pos > 0) {
                    List.add(busiparam);//ComplexAssemble处理类型
                   Print.print("添加" + busiparam);
                } else {
                    if (pos > 0) {
                        ListPoint.add(busiparam);//PointFilter处理类型
                         Print.print("PointFilter:" + busiparam);
                    } else {
                        // 加上引号拼接
                        input.append("\"").append(busiparam).append("\"").append(":");
                        input.append("\"").append("\"").append(",");
                    }
                }
                flag = true;

            }
            if (!List.isEmpty()) {
                int n = List.size();
                String[] filter = (String[]) List.toArray(new String[n]);
                ComplexAssemble assemble=new ComplexAssemble();
                input.append(assemble.Assemble(filter));
            }
            //处理AgencyInfo.AgencyAddress这样类型的数据
            if (!ListPoint.isEmpty()) {
                int n = ListPoint.size();
                String[] filter = (String[]) ListPoint.toArray(new String[n]);
                input.append(PointFilter.PointFilter(filter));
            }

            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String inputs = "";
            // 去掉BUSICODE拼接部分最后一个逗号,如果没有入参则不用进行该步骤
            if (flag) {
                inputs = input.substring(0, input.length() - 1);
            }
            if (channel == 0) {
                remark = "该接口无数据";
                result = "";
                return result;
            } else {
                remark = mark.toString();//入参提示
            }

            name = "\"" + name + "\"";
            String requests = map.get("Jsondata").toString();
            //获取当前渠道名称的interfaceid和interfacetype
            requests=requests.replace("$interfaceid$", GetUIElement.getInterfaceId(interfacename, InterfaceInfos));
            requests=requests.replace("$interfacetype$",GetUIElement.getInterfaceType(interfacename, InterfaceInfos));
            
            requests = requests.replace("$busiparam$", inputs);
            requests = requests.replace("$busicode$", name);
            Print.print("接口输入报文拼接完成\n" + requests);// 显示打印完整的输入报文
            result = JsonFormatTool.formatJson(requests, "  ");//格式化报文


        } catch (Exception e) {
            e.printStackTrace();
        } 
        return result;
    }

/*
 * @description 提交post请求
 * @param path 接口地址
 * @param request 请求报文
 * @return 接口返回信息
 */
    public static String SendPost(String request, String path) {
        String response = "";
        String formatresponse = "";
        try {
            byte[] b = request.getBytes("UTF-8");
            URL url = new URL(path);// 访问的地址
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");// 设置访问方式，默认为GET
            conn.setConnectTimeout(10 * 1000);// 设置连接超时时间为10秒
            conn.setReadTimeout(120 * 1000);// 设置读取超时时间为120秒
            // 使用 URL 连接进行输出，则将 DoOutput标志设置为 true
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
            conn.setRequestProperty("Content-Length", String.valueOf(b.length));
            OutputStream outStream;
            try {
                outStream = conn.getOutputStream();// 返回写入到此连接的输出流
                outStream.write(b);// 发送报文
                outStream.close();

            } catch (Exception e) {
                remark = "可能存在网络不通！";
                e.printStackTrace();

            }

            if (conn.getResponseCode() == 200) {
                remark = "连接接口成功,读取数据";
                // HTTP服务端返回的编码是UTF-8,故必须设置为UTF-8,保持编码统一,否则会出现中文乱码
                BufferedReader input = new BufferedReader(new InputStreamReader(
                        (InputStream) conn.getInputStream(), "UTF-8"));
                response = input.readLine();
                input.close();
            }

            conn.disconnect();// 断开连接
            Print.print("接口返回报文\n" + response);
            //System.out.print(response);
        } catch (Exception e) {
            e.printStackTrace();
            remark = "可能存在网络不通";
        }
        return response;
    }

/*
 * @description 查找json报文，是否已存在于数据库中
 * @param name 接口BUSI_CODE
 * @param map 数据库信息
 * @param interfacetype 接口的interfacetype
 * @return 查找结果
 */
    public static Map FindJson(String name, Map map,String interfacetype) {
        String driver = "oracle.jdbc.OracleDriver";
        String url = map.get("DBURL").toString();
        String user = map.get("DBusername").toString();
        String password = map.get("DBpassword").toString();
        String sql = "select * from base.soa_message where busi_code='" + name + "' and  interface_type='" + interfacetype + "'";
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Map JsonMap = new HashMap();
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String request = rs.getString("input_json");
                //request = JsonFormatTool.formatJson(request.toString(), "  ");
                JsonMap.put("request", request);

            }
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonMap;
    }
}
