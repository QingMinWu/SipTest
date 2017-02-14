
package siptest;

import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import java.io.Writer;
import java.io.FileWriter;
import java.io.Reader;

/**
 *
 * 
 */
public class FileChooserAction {

    
//    public static String FileChooser() {
//        JFileChooser file = new JFileChooser("C:\\Documents and Settings\\Administrator");
//        file.setAcceptAllFileFilterUsed(false);//去掉显示所有文件这个过滤器
//        file.addChoosableFileFilter(new ExcelFileFilter("xls"));
//        int result = file.showOpenDialog(null);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            //选择的文件绝对路径
//            String path = file.getSelectedFile().getAbsolutePath();
//            return path;
//        } else {
//
//            return "你未选择文件！";
//        }
//    }
//
//    private static class ExcelFileFilter extends FileFilter {
//
//        String ext;
//
//        ExcelFileFilter(String ext) {
//            this.ext = ext;
//        }
//
//        public boolean accept(File file) {
//            if (file.isDirectory()) {
//                return true;
//            }
//            String fileName = file.getName();
//            int index = fileName.lastIndexOf(".");
//            if (index > 0 && index < fileName.length() - 1) {
//                String extension = fileName.substring(index + 1).toLowerCase();
//                if (extension.equals(ext)) {
//                    return true;
//                }
//            }
//            return false;
//        }
//
//        public String getDescription() {
//            if (ext.equals("xls")) {
//                return "Microsoft Excel文件(*.xls)";
//            }
//
//            return "";
//        }
//    }

    /*
     * @description 文件保存的路径，保存文件名以接口名命名
     * @param OIname 文件名
     * @return String 保存的路径
     */
    public static String FileSavePath(String OIname) {
        JFileChooser file = new JFileChooser(".");
        file.setAcceptAllFileFilterUsed(false);//去掉显示所有文件这个过滤器
        file.addChoosableFileFilter(new TxtFileFilter("txt"));
        file.setSelectedFile(new File(OIname + ".txt"));
        //  file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//目录,使用这个选项则不能使用默认文件名
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            //获得选择的文件绝对路径
            String path = file.getSelectedFile().getAbsolutePath();
            return path;
        } else {
            return "";
        }

    }

    /*
     * txt文件筛选器
     */
    private static class TxtFileFilter extends FileFilter {

        String ext;

        TxtFileFilter(String ext) {
            this.ext = ext;
        }

        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true;
            }
            String fileName = file.getName();
            int index = fileName.lastIndexOf(".");
            if (index > 0 && index < fileName.length() - 1) {
                String extension = fileName.substring(index + 1).toLowerCase();
                if (extension.equals(ext)) {
                    return true;
                }
            }
            return false;
        }

        public String getDescription() {
            if (ext.equals("txt")) {
                return "(*.txt)";
            }

            return "";
        }
    }

    /*
     * @description 生成测试结果文件
     * @param path 文件生成路径
     * @param content 文件生成内容（接口名，输入报文，输出报文）
     * @return 文件生成情况
     * @version V1.1
     */
    public static String SaveFile(String path, String content) {
        if (path == "") {
            return "未选择生成测试结果文件！";
        }
        int Namecheck = 0;
        Namecheck = path.indexOf(".txt");
        if (Namecheck <= 0) {
            path += ".txt";
        }

        File file = new File(path);
        try {
            Writer output = new FileWriter(file);
            output.write(content);
            output.flush();
            output.close();
        } catch (Exception e) {

            return "文件生成失败";
        }
        return "文件生成成功,请查看" + path;
    }

    /*
     * @description 另存为日志文件
     * @param path文件保存路径
     * @return 生成结果
     *
     * @version V1.4
     */
    public static String SaveAs(String path) {
        if (path == "") {
            return "未选择生成测试结果文件！";
        }

        try {
            File f = new File(".\\Log.txt");
            Reader in = new FileReader(f);
            char[] c = new char[(int) f.length()];
            in.read(c);
            in.close();
            Writer output = new FileWriter(path);
            output.write(new String(c));
            output.flush();
            output.close();

        } catch (Exception e) {
            e.printStackTrace();
            return "文件生成失败";
        }
        return "日志文件生成成功,请查看" + path;
    }
}
