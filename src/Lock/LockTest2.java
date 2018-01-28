
/*
 * ReentrantLock��ȡ���������ַ�ʽ��
lock()�������ȡ�����������أ��������̳߳���������ǰ�߳���һֱ��������״̬��ֱ����ȡ����
tryLock()�������ȡ������������true���������߳��³��У���������false;
tryLock(long timeout, TimeUnit unit)�������ȡ��������������true���������߳��������������ȴ�����������ʱ�䣬��
�ȴ��Ĺ����У������ȡ������������true������ȴ���ʱ������false������lock()�����൱trylock���ݸ����޴��ʱ�����;
lockInteruptibly�������ȡ�������������أ���֮����ǰ�̴߳������ߣ�ֱ����ȡ�������ߵ�ǰ�߳��̱߳������߳��жϡ�

2��synchronized����JVM����ʵ�֣���������ͨ��һЩ��ع��߼�������������ڴ���ִ�г����쳣��JVM�Զ��ͷ�����;
Lock��ͨ������ʵ�֣�Ϊ�˱�֤����һ���ᱻ�ͷţ�һ��ὫunLock()�ŵ�flyinal{}�С�

3������Դ���������ҵ�����£�synchronized������Ҫ����ReentrantLock��
������Դ�����ܼ��ҵ�����£�synchronized�����ܻ��½���ʮ����jvm��Ҫ��������cpu���ڴ�������ͬ������
����ReentrantLock��������ά�ֳ�̬��
 */

package Lock;
//һ�����̹߳�ͬ�����Ĵ���
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
  
	//synchronized�ؼ��֡�Lock���Կ��Ƴ���Ƭ�ε�ͬ����ԭ����ֻ�ܱ�֤����������ͬ����
    //�߳̾���������ʱ��ԭ�������ܱ�synchronized�Եͣ�����������ʱ��Ҳ��ά�ֳ�̬��
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
