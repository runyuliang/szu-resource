import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);//使用Scanner类
        for(int i = 0; i < 10; i++) {
            System.out.print("Enter the number:");
            String s = sc.next();//以字符串形式读入数字
            String st = new StringBuffer(s).reverse().toString();//把s反转后赋值给st
            System.out.println(st);//输出反转后的字符串
        }
    }
}
