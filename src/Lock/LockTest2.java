
/*
 * ReentrantLock获取锁定的三种方式：
lock()，如果获取了锁立即返回，如果别的线程持有锁，当前线程则一直处于休眠状态，直至获取锁。
tryLock()，如果获取了锁立即返回true，如果别的线程下持有，立即返回false;
tryLock(long timeout, TimeUnit unit)，如果获取了锁定立即返回true，如果别的线程正持有锁，将等待参数给定的时间，在
等待的过程中，如果获取了锁定，返回true，如果等待超时，返回false，所以lock()方法相当trylock传递个无限大的时间参数;
lockInteruptibly，如果获取了锁定立即返回，反之，当前线程处理休眠，直至获取锁，或者当前线程线程被其他线程中断。

2、synchronized是在JVM层面实现，不但可以通过一些监控工具监控锁定，而且在代码执行出现异常，JVM自动释放锁定;
Lock是通过代码实现，为了保证锁定一定会被释放，一般会将unLock()放到flyinal{}中。

3、在资源竞争不激烈的情况下，synchronized的性能要优于ReentrantLock，
但在资源竞争很激烈的情况下，synchronized的性能会下降几十倍（jvm需要分配额外的cpu和内存来管理同步），
但是ReentrantLock的性能能维持常态。
 */

package Lock;
//一个多线程共同计数的代码
//public class LockTest2 extends Thread {  
//	  
//    private static int race = 0;  
//    private int threadNo;  
//  
//    public LockTest2(int threadNo) {  
//        this.threadNo = threadNo;  
//    }  
//  
//    public static void main(String[] args) throws InterruptedException {  
//        for (int i = 0; i < 10; i++) {  
//            new LockTest2(i).start();  
//        }  
//  
//        while (Thread.activeCount() > 1) {  
//            Thread.yield();  
//        }  
//        System.out.println(race);  
//    }  
//  
//    @Override  
//    public void run() {  
//        for (int i = 0; i < 1000; i++) {  
//            race++;  
//        }  
//    }  
//}  

import java.util.concurrent.atomic.AtomicInteger;  

public class LockTest2 extends Thread {  
  
	//synchronized关键字、Lock可以控制程序片段的同步，原子类只能保证单个变量的同步。
    //线程竞争不激烈时，原子类性能比synchronized略低，当竞争激烈时，也能维持常态。
    private static AtomicInteger race = new AtomicInteger();  
    private int threadNo;  
  
    
    
    public LockTest2(int threadNo) {  
        this.threadNo = threadNo;  
    }  
  
    public static void main(String[] args) throws InterruptedException {  
        for (int i = 0; i < 5; i++) {  
            new LockTest2(i).start();  
        }  
  
        while (Thread.activeCount() > 1) {  
            Thread.yield();  
        }  
        System.out.println(race);  
    }  
  
    @Override  
    public void run() {  
        for (int i = 0; i < 1000; i++) {  
            race.addAndGet(1);  
            
        }  
    }  
}  
