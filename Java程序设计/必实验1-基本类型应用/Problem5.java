public class Problem5 {
    public static void main(String[] args) {
        double numerator = 1, denominator = 1;
        double ans = 0;
        for (int i = 0; i < 20; i++) {
            ans += numerator / denominator;
            numerator++;
            denominator *= 5;
        }
        System.out.println("数列前20项和为"+ans);
    }
}
