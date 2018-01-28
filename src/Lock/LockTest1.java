package Lock;

import java.util.concurrent.locks.Lock;  
import java.util.concurrent.locks.ReentrantLock;  
  
/**
 * �Ƚ�ReentrantLock��synchronized���÷�
1��ReentrantLockӵ��synchronized��ͬ�Ĳ����Ժ��ڴ����壬���⻹������ͶƱ����ʱ���Ⱥ���жϵȺ�
�߳�A��B��Ҫ��ȡ����O������������A��ȡ�˶���O����B���ȴ�A�ͷŶ�O��������
ʹ��synchronizedʱ�����A���ͷţ�B��һֱ�ȴ���ȥ���޷��жϡ�
ʹ��ReentrantLockʱ�����A���ͷţ�B�����ڵȴ��㹻��ʱ���ֹͣ�ȴ�������ִ����������
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