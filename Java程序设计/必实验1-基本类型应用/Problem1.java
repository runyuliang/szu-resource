import java.util.Arrays;

public class Problem1 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();//记录程序开始时间
        int[][] a = new int[100][100];//二维数组a
        int [] b = new int[10000];//二维数组b
        int k = 0;//i,j,k都是计数器
        for(int i = 0; i < 100; i++)
            for(int j = 0; j < 100; j++, k++) {
                a[i][j] = (int) (Math.random() * 100);
                //Math.random()随机返回0-1内的一个浮点数
                //将返回值乘以100再转换为整型
                b[k] = a[i][j];//同步拷贝
            }
        Arrays.sort(b);//升序排序
        for(int i = 9999; i > 9994; i--)//由于是升序排序，所以倒着输出
            System.out.println(b[i]);
        long endTime = System.currentTimeMillis();//记录程序结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }
}
