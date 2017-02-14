package siptest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import javax.swing.JComboBox;

/**
 * @description 获取界面显示的数据（主要是Jcombobox(下拉框)控件的数据）
 * 
 */
public class GetUIElement {

 /*
  * @description 普通数组显示
  * @param list显示的控件
  * @param names需要显示的数据
  * @version V1.4
  */
    public static void getElement(JComboBox list, String[] names) {
        for (int i = 0; i < names.length; i++) {
            list.addItem(names[i]);
//            System.out.println(names[i]);

        }

    }

    /*
     * @description 显示渠道名称
     * @param list显示的控件
     * @param interfaceinfos渠道信息二维数组
     * @version V1.41
     */
    public static void getInterfaceElement(JComboBox list, String[][] interfaceinfos) {
        for (int i = 0; i < interfaceinfos.length; i++) {
            list.addItem(interfaceinfos[i][0]);
        }
    }

  /*
     * @description 显示URL名称
     * @param list显示的控件
     * @param URL信息二维数组
     * @version V1.42
     */
//    public static void getURLElement(JComboBox list, String[][] urlinfos) {
//        for (int i = 0; i < urlinfos.length; i++) {
//            list.addItem(urlinfos[i][0]);
//        }
//    }

    /*
     * @description 根据渠道名称获取InterfaceId
     * @param interfacename 渠道名称
     * @param interfaceinfos 渠道信息
     * @return 渠道ID
     * version V1.41
     */
    public static String getInterfaceId(String interfacename, String[][] interfaceinfos) {
        String InterfaceId = null;
        for (int i = 0; i < interfaceinfos.length; i++) {
            if (interfacename == null ? interfaceinfos[i][0] == null : interfacename.equals(interfaceinfos[i][0])) {
                InterfaceId = interfaceinfos[i][1];//渠道信息存储格式渠道名称|interfaceid|interfacetype
            }
        }
        return InterfaceId;
    }

    /*
     * @description 根据渠道名称获取InterfaceType
     * @param interfacename 渠道名称
     * @param interfaceinfos 渠道信息
     * @return 渠道TYPE
     * version V1.41
     */
    public static String getInterfaceType(String interfacename, String[][] interfaceinfos) {
        String InterfaceType = null;
        for (int i = 0; i < interfaceinfos.length; i++) {
            if (interfacename == null ? interfaceinfos[i][0] == null : interfacename.equals(interfaceinfos[i][0])) {
                InterfaceType = interfaceinfos[i][2];//渠道信息存储格式渠道名称|interfaceid|interfacetype
            }
        }
        return InterfaceType;
    }
    
       /*
     * @description 根据URL名称获取对应的URL地址
     * @param urlname url名称
     * @param urlinfos url信息
     * @return url地址
     * version V1.42
     */
    public static String getURL(String urlname, String[][] urlinfos) {
        String url = null;
        for (int i = 0; i < urlinfos.length; i++) {
            if (urlname == null ? urlinfos[i][0] == null : urlname.equals(urlinfos[i][0])) {
                url = urlinfos[i][1];//URL信息存储格式urlname|urlinfos
            }
        }
        return url;
    }
    
    /*
     * @description 从数据库中获取LIST的item
     * @version V1.51
     */
     public static void GetElementList(Map map, JComboBox list,String sql) throws Exception {
        String driver = "oracle.jdbc.OracleDriver";
        String url = map.get("DBURL").toString();
        String user = map.get("DBusername").toString();
        String password = map.get("DBpassword").toString();

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
       // String sql = "select distinct(busi_name) from base.source_data";

        Class.forName(driver);
        con = DriverManager.getConnection(url, user, password);
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        ClearElementList(list);
        list.addItem("");
        while (rs.next()) {
            list.addItem(rs.getString("BUSI_NAME"));            
        }
        
        rs.close();
        pstm.close();
        con.close();
        
    }
   
    public static void ClearElementList(JComboBox list) throws Exception{
        list.removeAllItems();
    }
}
