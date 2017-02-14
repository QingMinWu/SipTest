
package siptest;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @description 处理config.xml文件
 * @author wuqm
 */
public class ConfigManager {

    /*
     * @description 读取config.xml文件中的数据
     * @return 配置文件中的数据
     * @version V1.4
     */
    public static Map ReadData(String evn) {
        Map result = new HashMap();
        File f = new File("." + File.separator + "config.xml");
        Element element = null;
        // documentBuilder为抽象不能直接实例化(将XML文件转换为DOM文件)
        DocumentBuilder db = null;
        DocumentBuilderFactory dbf = null;
        try {
            // 返回documentBuilderFactory对象
            dbf = DocumentBuilderFactory.newInstance();
            // 返回db对象用documentBuilderFatory对象获得返回documentBuildr对象
            db = dbf.newDocumentBuilder();
            // 得到一个DOM并返回给document对象
            Document dt = db.parse(f);
            // 得到一个elment根元素
            element = dt.getDocumentElement();
            // 获得根节点

            // 获得根元素下的子节点
            NodeList childNodes = element.getChildNodes();
            // 遍历这些子节点
            for (int i = 0; i < childNodes.getLength(); i++) {
                // 获得每个对应位置i的结点
                Node node1 = childNodes.item(i);
                if("EVN".equals(node1.getNodeName())){
                if (evn.equals(node1.getAttributes().getNamedItem("name").getNodeValue())) {
                    NodeList nodeDetail = node1.getChildNodes();
                    for (int j = 0; j < nodeDetail.getLength(); j++) {
                        // 获得元素每一个节点
                        Node detail = nodeDetail.item(j);                      
                                if ("DBURL".equals(detail.getNodeName())) // 输出DBURL
                                {
                                    result.put("DBURL", detail.getTextContent());
                                } else if ("DBusername".equals(detail.getNodeName())) // 输出DBusername
                                {
//                            System.out.println("用户名: " + detail.getTextContent());
                                    result.put("DBusername", detail.getTextContent());
                                } else if ("DBpassword".equals(detail.getNodeName())) // 输出DBpassword
                                {
                                    result.put("DBpassword", detail.getTextContent());
//                                     System.out.println( detail.getTextContent());
                                } else if("URL".equals(detail.getNodeName())) {
                                    result.put("URL", detail.getTextContent()); 

                                }else if("AppServer".equals(detail.getNodeName())) {
                                    result.put("AppServer", detail.getTextContent());      

                                }else if("AppUser".equals(detail.getNodeName())) {
                                    result.put("AppUser", detail.getTextContent());     

                                }else if("AppPwd".equals(detail.getNodeName())) {
                                    result.put("AppPwd", detail.getTextContent());   

                                }else if("AppCommand".equals(detail.getNodeName())) {
                                    result.put("AppCommand", detail.getTextContent());     

                                }else if("SoaServer".equals(detail.getNodeName())) {
                                    result.put("SoaServer", detail.getTextContent());   

                                }else if("SoaUser".equals(detail.getNodeName())) {
                                    result.put("SoaUser", detail.getTextContent());  

                                }else if("SoaPwd".equals(detail.getNodeName())) {
                                    result.put("SoaPwd", detail.getTextContent());    

                                }else if("SoaCommand".equals(detail.getNodeName())) {
                                    result.put("SoaCommand", detail.getTextContent());       

                                }
                            }
                        
                } 
                }
                
                if ("Jsondata".equals(node1.getNodeName())) {
                    result.put("Jsondata", node1.getTextContent());
//                    System.out.println( node1.getTextContent());
                }

                if ("Interface".equals(node1.getNodeName())) {
                    result.put("Interface", node1.getTextContent());
                }
                if ("DBparamsql".equals(node1.getNodeName())) {
                                    result.put("DBparamsql", node1.getTextContent());

                 } 
                if ("DBinterfacesql".equals(node1.getNodeName())) {
                                    result.put("DBinterfacesql", node1.getTextContent());} 

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     * @description 处理config.xml中的版本信息，获取指定标签名称（如数据库，版本）下所有的name
     * @param TabName 标签名称
     */
    public static String[] ReadDBName(String TabName) {
        List names = new ArrayList();
        File f = new File("." + File.separator + "config.xml");
        Element element = null;
        // documentBuilder为抽象不能直接实例化(将XML文件转换为DOM文件)
        DocumentBuilder db = null;
        DocumentBuilderFactory dbf = null;
        try {
            // 返回documentBuilderFactory对象
            dbf = DocumentBuilderFactory.newInstance();
            // 返回db对象用documentBuilderFatory对象获得返回documentBuildr对象
            db = dbf.newDocumentBuilder();
            // 得到一个DOM并返回给document对象
            Document dt = db.parse(f);
            // 得到一个elment根元素
            element = dt.getDocumentElement();
            // 获得根元素下的子节点
            NodeList childNodes = element.getChildNodes();
            // 遍历这些子节点
//            System.out.print(childNodes.getLength());
            for (int i = 0; i < childNodes.getLength(); i++) {
                // 获得每个对应位置i的结点
                Node node1 = childNodes.item(i);
//                System.out.print(node1.getNodeValue());
                if (TabName.equals(node1.getNodeName())) {                    
                    if (!"#text".equals(node1.getNodeName())) {
                      names.add(node1.getAttributes().getNamedItem("name").getNodeValue());
//                      System.out.print(node1.getAttributes().getNamedItem("name").getNodeValue());
                    }
                                           
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] result = (String[]) names.toArray(new String[names.size()]);
        return result;
    }
   
    /*
     * @description 处理config.xml中的渠道信息，渠道信息|渠道ID|渠道TYPE 转化为一个二维数组
     * @param InterfacInfo config中原始的渠道信息格式
     * @String[][] 存储渠道信息的二维数组
     * @version V1.42
     */
    public static String[][] ReadInterface(String InterfaceInfo) {
        
        String[] temp = InterfaceInfo.split(",");
        String[][] Interface=new String[temp.length][3];
        for (int i = 0; i < temp.length; i++) {
            String[] Infos = temp[i].split("\\|");
            for (int j = 0; j < 3; j++) {
                Interface[i][j] = Infos[j];
              }
        }
        return Interface;
    }
    
    /*
     * @description 处理config.xml中的url信息，url名称|url地址 转化为一个二维数组
     * @param URL config中URL的原始格式
     * @return String[][] 存储URL信息的二维数组
     * @version V1.42
     * 
     */
    
        public static String[][]  ReadURL(String URL) {
        
        String[] temp = URL.split(",");
        String[][] url=new String[temp.length][2];
        for (int i = 0; i < temp.length; i++) {
            String[] Infos = temp[i].split("\\|");
            for (int j = 0; j < 2; j++) {
                url[i][j] = Infos[j];
              }
        }
        return url;
    }
    
        public static void main(String [] args){
            ConfigManager.ReadData("广州测试");
        }

}
