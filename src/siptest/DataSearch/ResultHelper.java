/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package siptest.DataSearch;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextArea;

/**
 *
 * @author wuqm
 */
public class ResultHelper {
    public void ResultDataShow(ResultSet rt, JTextArea result) throws Exception {
        DataDao rtdata = new DataDao();
        Map rtmap = new HashMap(rtdata.DataCollect(rt));

        String[] column = (String[]) rtmap.get("column");
        String[][] data = (String[][]) rtmap.get("data");
        int row = Integer.parseInt(rtmap.get("row").toString());
        System.out.println("结果行数"+row);
        int col = Integer.parseInt(rtmap.get("col").toString());

        ResultHelper resulthelper = new ResultHelper();
        int[] columnlength = resulthelper.LengthCount(column, data, row, col);
        String resultdata = resulthelper.PrintResult(column, data, columnlength, col, row);
        result.append(resultdata);
    }

    public int[] LengthCount(String[] column, String[][] data, int row, int col) throws Exception {

        int[] columnlength = new int[col];
        for (int i = 0; i < col; i++) {
            int max = 0;
            //跟数据长度对比
            for (int n = 0; n < row; n++) {
                if (data[n][i] != null && !("".equals(data[n][i]))) {
                    while (data[n][i].getBytes().length > max) {
                        max = data[n][i].getBytes().length;
                    }
                }
            }
            //跟列名对比
            if ((column[i].getBytes().length) > max) {
                max = column[i].getBytes().length;
            }
            columnlength[i] = max;
        }
        return columnlength;
    }

    public String PrintResult(String[] column, String[][] data, int[] columnlength, int col, int row) throws Exception {
        StringBuilder resulttmp = new StringBuilder();
        String result = "";
        //组装列名
        for (int i = 0; i < col; i++) {
            result += column[i];
            for (int n = 0; n < ((columnlength[i] + 4) - (column[i].length())); n++) {
                result += " ";
            }

        }
        result += "\n";

        //组装数据,跟最长的长度对比，不足的用空格补全，保持格式一致,每列间距为4个空格
        for (int i = 0; i < row; i++) {
            for (int n = 0; n < col; n++) {

                if (data[i][n] != null && !("".equals(data[i][n]))) {
                    resulttmp.append(data[i][n]);
                    for (int m = 0; m < ((columnlength[n] + 4) - (data[i][n].length())); m++) {
                        resulttmp.append(" ");
                    }
                } else {
                    for (int m = 0; m < (columnlength[n] + 4); m++) {
                        resulttmp.append(" ");
                    }
                }
            }
            resulttmp.append("\n");
        }
        result += resulttmp.toString();
//        System.out.println(result);
        return result;
    }
}
