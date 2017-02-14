
package siptest;

import java.util.Arrays;

/**
 * @description 处理复合的参数
 * 
 */
public class ComplexAssemble {

/*
 * @description 组装复合参数（类似XX[n].yy）
 * @param s 复合参数
 * @return 组装好的json格式
 * @version V1.4(之前版本采用算法类似StringFilter)
 */
    public String Assemble(String[] s) {
        Arrays.sort(s);
        StringBuilder Result = new StringBuilder();//临时拼装容器
        String result;//拼装的最后结果
        int in = 0;//记录拼装的[{        
        int out = 0;//记录拼装的}] in和out的数量最后需要一致

        for (int i = 0; i < s.length; i++) {
            String[] current;
            String[] former;
            if (i == 0) {
                current = s[i].split("\\[n\\]\\.");
                int currentlen = current.length;//当前切割的参数的个数
                for (int num = 0; num < currentlen - 1; num++) {
                    Result.append("\"").append(current[num]).append("\"").append(":[{");
                    in++;
                }
                Result.append("\"").append(current[currentlen - 1]).append("\":\"\",");
            } else {
                current = s[i].split("\\[n\\]\\.");
                int currentlen = current.length;//当前切割的参数的个数
                former = s[i - 1].split("\\[n\\]\\.");
                int formerlen = former.length;//前一切割的参数的个数               
                int flag = 0;//记录当前参数与前一参数差异的标识

                //记录复合数量
                if (currentlen > formerlen) {
                    for (int length = 0; length < formerlen - 1; length++) {
                        if (current[length].equals(former[length])) {
                            flag++;//比较当前的参数与前一参数
                        }
                    }
                }
                if (currentlen < formerlen) {
                    for (int length = 0; length < currentlen - 1; length++) {
                        if (current[length].equals(former[length])) {
                            flag++;//比较当前的参数与前一参数
                        }
                    }
                }
                if (currentlen == formerlen) {
                    for (int length = 0; length < currentlen - 1; length++) {
                        if (current[length].equals(former[length])) {
                            flag++;//比较当前的参数与前一参数
                        }
                    }
                }

                if (flag != 0) {
                    if (((flag + 1) == currentlen) && (currentlen == formerlen)) {
                        //如果和前一参数复合相同                   
                        Result.append("\"").append(current[currentlen - 1]).append("\":\"\",");
                    } else {
                        if (currentlen < formerlen) {
                            //当前参数复合度少于前一参数
                            Result.setLength(Result.length() - 1);//去掉前一参数最后一个逗号
                            for (int diff = 0; diff < (formerlen - (flag + 1)); diff++) {
                                Result.append("}]");//补差
                                out++;
                            }
                            Result.append(",");
                            while ((flag + 1) != currentlen) {//复合数量相同但是复合内容不相同
                                for (int flagtmp = flag; flagtmp < (currentlen - 1); flagtmp++) {
                                    Result.append("\"").append(current[flag]).append("\"").append(":[{");
                                    in++;
                                }
                            }
                            Result.append("\"").append(current[currentlen - 1]).append("\":\"\",");
                        } else {
                            //当前参数复合度多于前一参数
                            if ((flag + 1) != formerlen) {//复合数量相同但是复合内容不相同
                                for (int flagtmp = flag; flagtmp < (currentlen - 1); flagtmp++) {
                                    Result.append("\"").append(current[flagtmp]).append("\"").append(":[{");
                                    in++;
                                }

                            } else {//复合内容相同
                                for (int flagtmp = formerlen - 1; flagtmp < (currentlen - 1); flagtmp++) {
                                    Result.append("\"").append(current[flagtmp]).append("\"").append(":[{");
                                    in++;
                                }
                            }
                            Result.append("\"").append(current[currentlen - 1]).append("\":\"\",");
                        }
                    }
                } else {
                    //没有相同的复合
                    while (in != out) {//处理上一复合参数剩下的问题
                        Result.setLength(Result.length() - 1);//去掉前一参数最后一个逗号
                        if (in > out) {
                            for (int gap = 0; gap < (in - out); gap++) {
                                Result.append("}]");
                            }
                            Result.append(",");
                        }
                        //归零重新开始             
                        in = 0;
                        out = 0;
                    }
                    for (int num = 0; num < (currentlen - 1); num++) {
                        Result.append("\"").append(current[num]).append("\"").append(":[{");
                        in++;
                    }
                    Result.append("\"").append(current[currentlen - 1]).append("\":\"\",");
                }
            }
        }
        
        if (in != out) {//处理上一复合参数剩下的问题
            Result.setLength(Result.length() - 1);//去掉前一参数最后一个逗号
            if (in > out) {
                for (int gap = 0; gap < (in - out); gap++) {
                    Result.append("}]");
                }
                Result.append(",");
            }
        }
        result = Result.toString();
//        System.out.println(result);
        return result;
    }

}
