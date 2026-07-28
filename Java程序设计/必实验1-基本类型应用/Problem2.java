import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < 10; i++) { //循环10次
            System.out.print("输入本月用电量:");
            double charge = sc.nextDouble();//charge表示本月用电量
            double ans;//ans表示电费
            if(charge <= 50)
                ans = 0.55 * charge;
            else if (charge <= 220)
                ans = 0.55 * 50 + 0.58 * (charge - 50);
            else
                ans = 0.55 * 50 + 0.58 * (220 - 50) + 0.65 * (charge - 220);
            System.out.println("应缴纳的电费为:" + ans + "元");
        }
    }
}
