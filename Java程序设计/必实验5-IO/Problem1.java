import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem1 {
    public static ArrayList<Map<String, String>> readInfo(String filePath) {
        File file = new File(filePath);//打开文件
        Scanner sc;
        ArrayList<Map<String, String>> info = new ArrayList<>();
        // 正则表达式 拆分字符串
        String regex = "(\\w+)/(\\w+):\\s(.+)";
        Pattern p = Pattern.compile(regex);
        try {
            sc = new Scanner(file);
            Map<String, String> temp = new HashMap<>();
            while (sc.hasNext()) {
                try {
                    String str = sc.nextLine();//一次读取一行的信息
                    if (str.equals("")) {
                        info.add(new HashMap<>(temp));//加入数据
                        temp.clear();
                        continue;
                    }
                    Matcher m = p.matcher(str);
                    // 插入键值对
                    if (m.matches()) {
                        temp.put(m.group(2), m.group(3));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return info;
    }

    public static void createReviewFile(ArrayList<Map<String, String>> info, String filePath) {
        File file = new File(filePath);
        try {
            FileWriter output = new FileWriter(file);
            for (Map<String, String> item : info)
                output.write(item.get("userId") + ';' + item.get("productId") + ';' + item.get("summary") + '\n');
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "D:\\Users\\梁润宇\\Desktop\\大二上学期课程\\Java程序设计\\必实验5-IO\\";
        // 获取所有信息键值对
        ArrayList<Map<String, String>> info = readInfo(filePath + "Watches.txt");
        createReviewFile(info, filePath + "review.txt");
    }
}
