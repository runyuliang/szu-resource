import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account {//账户类
    private int amount = 1000;

    public synchronized void save() {//存钱
        amount += 100;
        System.out.println("账号余额增加100元");
        System.out.println("当前余额:" + this.amount + "元");
        System.out.println();
    }

    public synchronized void withdraw() {//取钱
        amount -= 100;
        System.out.println("账号余额减少100元");
        System.out.println("当前余额:" + this.amount + "元");
        System.out.println();
    }
}

class Producer implements Runnable {//存钱类
    private Account account;
    private Lock lock = new ReentrantLock();//锁

    public Producer(Account a) {
        this.account = a;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            lock.lock();
            account.save();
            lock.unlock();
        }
    }
}

class Customer implements Runnable {//取钱类
    private Account account;
    private Lock lock = new ReentrantLock();//锁

    public Customer(Account a) {
        this.account = a;
    }

    @Override
    public void run() {

        for (int i = 0; i <= 5; i++) {
            lock.lock();//关锁
            account.withdraw();
            lock.unlock();//开锁
        }
    }
}

public class BankAccount {
    public static void main(String[] args) {
        Account account = new Account();
        Producer p = new Producer(account);
        Customer c = new Customer(account);
        //创建2个取钱线程，2个存钱线程
        Thread t1 = new Thread(p);
        Thread t2 = new Thread(c);
        Thread t3 = new Thread(p);
        Thread t4 = new Thread(c);
        //运行线程
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
