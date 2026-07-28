import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class PrintChar implements Runnable{
    private char charToPrint;//被打印的字符
    private int times;//输出次数

    public PrintChar(char c, int t){//构造方法
        charToPrint = c;
        times = t;
    }

    @Override
    public void run(){//使用了接口Runnable,重写run方法
        for(int i =  0; i < times; i++){
            System.out.print(charToPrint);
        }
    }
}

class PrintNum implements Runnable{
    private int lastNum;//输出的最后一个数字
    //从1开始，依次输出，到lastNum为止
    public PrintNum(int n){//构造方法
        lastNum = n;
    }

    @Override
    public void run(){//重写run方法
        for(int i = 1; i <= lastNum; i++) {
            System.out.print(" " + i);
        }
    }
}

public class ExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        //建立1个容量为3的线程池
        //执行线程
        executor.execute(new PrintChar('a',100));
        executor.execute(new PrintChar('b',100));
        executor.execute(new PrintNum(100));

        executor.shutdown();//执行完了，关闭池子
    }
}
