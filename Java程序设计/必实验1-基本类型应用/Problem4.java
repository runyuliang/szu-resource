public class Problem4 {
    public static boolean is_prime(int x){
        if(x == 0 || x == 1)//0和1不是素数
            return false;
        if(x == 2)//2是素数
            return true;
        if(x % 2 ==0)//除了2之外，2的倍数不是素数
            return false;
        for(int i = 3; i <= (int)Math.sqrt(x); i += 2)//依次判断i是否为x的因数
            if(x % i == 0)
                return false;
        return true;
    }
    public static void main(String[] args) {
        int cnt = 0;//计数器
        for(int i = 1; i <= 500; i++)
            if(is_prime(i)) {
                cnt++;//素数个数++
                System.out.print(i + " ");
                if(cnt % 10 == 0)//每输出10个素数换一次行，美观
                    System.out.println();
            }
        System.out.println();//最后一行也换一次行
        System.out.println("1-500内有" + cnt + "个素数");
    }
}
