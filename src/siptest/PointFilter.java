
package siptest;

import java.util.Arrays;

/**
 * @description处理复合xx.xx类型的参数 
 * @author wuqm
 */
public class PointFilter {

    /*
     * @param s 复合参数数组（只包含.的复合参数）
     * @return 拼装完成后的参数
     * @version V1.1
     */
    public static String PointFilter(String[] s) {
        Arrays.sort(s);
        StringBuilder Result = new StringBuilder();
        String tail = "";//尾部
        String tailtemp;
        String[] heads = new String[s.length];//头部
        String temp = "";
        String result;
        int tailpos;//获取尾部位置    
        for (int i = 0; i < s.length; i++) {
            int flag1 = s[i].indexOf(".");//获取头的位置
            heads[i] = s[i].substring(0, flag1);
            if (i == 0) {
                temp = heads[i];
                Result.append("\"").append(temp).append("\"").append(":{");
            }
            if (temp.equals(heads[i])) {
            } else {
                //去掉尾部最后的逗号
                String tmp = Result.toString();
                tmp = tmp.substring(0, tmp.length() - 1);
                Result = new StringBuilder(tmp);
                //组装好前一个组合
                Result.append("},");
                //开始新的一个
                temp = heads[i];
                Result.append("\"").append(temp).append("\"").append(":{");
            }
            //组装尾部
            tailpos = s[i].indexOf(".");
            tailtemp = s[i].substring(((tailpos) + 1), (s[i].length()));
            tail = "\"" + tailtemp + "\"" + ":\"\",";
            Result.append(tail);
        }
        result = Result.toString();
        //去掉最后一个逗号
        result = result.substring(0, (result.length() - 1));
        result += ("},");
        return result;
    }

}
