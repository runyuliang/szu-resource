import java.util.Scanner;

public class Problem6_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            StringBuilder up = new StringBuilder();//存储大写字母
            StringBuilder low = new StringBuilder();//存储小写字母
            StringBuilder num = new StringBuilder();//存储数字
            String s = sc.next();
            for (int j = 0; j < s.length(); j++) {
                //找到符合条件的值，就添加到对应的StringBuilder类的末尾
                if (Character.isUpperCase(s.charAt(j)))
                    up.append(s.charAt(j));
                else if (Character.isLowerCase(s.charAt(j)))
                    low.append(s.charAt(j));
                else
                    num.append(s.charAt(j));
            }
            //加入提示语
            System.out.println("UpperCase:" + up);
            System.out.println("LowerCase:" + low);
            System.out.println("Number:" + num);
        }
    }
}
