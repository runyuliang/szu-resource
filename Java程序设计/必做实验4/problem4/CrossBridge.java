import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Bridge implements Runnable {
    private final Lock lock = new ReentrantLock();//互斥锁
    int ei = 1;//ei代表东边第几个人
    int wi = 1;//wi代表西边第几个人

    @Override
    public void run() {
        lock.lock();//锁住
        // 若线程为偶数东边人过桥
        if (Integer.valueOf(Thread.currentThread().getName()) % 2 == 0) {
            if (ei <= 10) {
                System.out.println("Thread " + Thread.currentThread().getName() + " is running.");
                if (ei % 10 == 1)
                    System.out.println("The 1st person from the east is crossing the bridge.");
                else if (ei % 10 == 2)
                    System.out.println("The 2nd person from the east is crossing the bridge.");
                else if (ei % 10 == 3)
                    System.out.println("The 3rd person from the east is crossing the bridge.");
                else
                    System.out.println("The " + ei + "th person from the east is crossing the bridge.");
                System.out.println("Finished.\n");
                ei++;
            }

        } else {//若线程为偶数西边人过桥
            if (wi <= 10) {
                System.out.println("Thread " + Thread.currentThread().getName() + " is running.");
                if (wi % 10 == 1)
                    System.out.println("The 1st person from the west is crossing the bridge.");
                else if (wi % 10 == 2)
                    System.out.println("The 2nd person from the west is crossing the bridge.");
                else if (wi % 10 == 3)
                    System.out.println("The 3rd person from the west is crossing the bridge.");
                else
                    System.out.println("The " + wi + "th person from the west is crossing the bridge.");
                System.out.println("Finished.\n");
                wi++;
            }
        }
        lock.unlock();//开锁
    }
}

public class CrossBridge {
    public static void main(String[] args) {

        Bridge b = new Bridge();//建立桥对象
        for (int i = 0; i < 40; i++) {//创建40个线程,对应东边20人和西边20人
            Thread t = new Thread(b);
            t.setName(i + "");
            t.start();
        }
    }
}