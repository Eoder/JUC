package Lock;

import java.util.concurrent.locks.Lock;  
import java.util.concurrent.locks.ReentrantLock;  
  
/**
 * 比较ReentrantLock与synchronized的用法
1、ReentrantLock拥有synchronized相同的并发性和内存语义，此外还多了锁投票，定时锁等候和中断等候。
线程A和B都要获取对象O的锁定，假设A获取了对象O锁，B将等待A释放对O的锁定。
使用synchronized时，如果A不释放，B将一直等待下去，无法中断。
使用ReentrantLock时，如果A不释放，B可以在等待足够长时间后，停止等待，继续执行其他事务。
 * @author yl
 *
 */
public class LockTest1 extends Thread {  
  
    private int threadNo;  
    private static Lock lock = new ReentrantLock();  
  
    public LockTest1(int threadNo) {  
        this.threadNo = threadNo;  
    }  
  
    public static void main(String[] args) throws InterruptedException {  
        for (int i = 0; i < 10; i++) {  
            new LockTest1(i).start();  
        }  
    }  
  
    @Override  
    public void run() {  
        lock.lock();  
        try {  
            for (int i = 0; i < 100; i++) {  
                System.out.println("No." + (threadNo + 1) + ": " + (i + 1));  
            }  
        } finally {  
            lock.unlock();  
        }  
    }  
}  