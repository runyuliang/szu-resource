public class ThreadTest {//主类
    public static Object o = new Object();//创建一个将被两个线程同时访问的共享对象
    Integer[] num = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};//数字数组
    String[] str = new String[]{"OneJanuary", "TwoFebruary", "ThreeMarch",
            "FourApril", "FiveMay", "SixJune", "SevenJuly", "EightAugust", "NightSeptember",
            "TenOctober", "ElevenNovember", "TwelveDecember"};//月份数组


    class Thread1 implements Runnable {
        public void run() {
            synchronized (o) {
                for (int i = 0; i < 12; i++) {//循环遍历数字数组
                    System.out.print(num[i]);
                    o.notify();//唤醒其他线程并休眠
                    try {
                        o.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class Thread2 implements Runnable {
        public void run() {
            synchronized (o) {
                for (int i = 0; i < 12; i++) {//循环遍历月份数组
                    String s = str[i];
                    System.out.print(s);
                    o.notify();
                    try {
                        o.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public void start() {//一次运行两个线程
        new Thread(new Thread1()).start();
        new Thread(new Thread2()).start();
    }

    public static void main(String args[]) {//主函数
        ThreadTest t = new ThreadTest();
        t.start();
    }
}
