package Lock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

import chapter05.FairAndUnfairTest.Job;

public class FairAndUnfairTest {
	
	private static Lock fairLock = new ReentantLock2(true);
	private static Lock unfairLock = new ReentantLock2(false);
	private static CountDownLatch start;
	
	@Test
	public void fair(){
		
		testLock(fairLock);
	}
	
	@Test
	public void unfair(){
		testLock(unfairLock);
	}
	
	public void testLock(Lock lock){
		//启动5个Lock
		 start = new CountDownLatch(1);
	     for (int i = 0; i < 5; i++) {
	            Thread thread = new Job(lock);
	            thread.setName("" + i);
	            thread.start();
	     }
	     start.countDown();
	}	
	
	private static class Job extends Thread{
		private Lock lock;
		public Job(Lock lock){
			this.lock=lock;
		}
		
		public void run(){
			//连续2次打印当前的Thread 和等待队列中的 Thread
		}
		
		
	}


	private  static class ReentantLock2 extends ReentrantLock{
		
		public ReentantLock2(boolean fair)
		{
			super(fair);
		}
		public Collection<Thread> getQueuedThreads(){
			List<Thread> arrayList = new ArrayList<Thread>(super.getWaitQueueLength(null));
			Collections.reverse(arrayList);
			return arrayList;
			
		}
		
	}
	

}

