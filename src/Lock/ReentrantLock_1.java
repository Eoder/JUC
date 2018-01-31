package Lock;

import java.util.concurrent.TimeUnit;

import Lock.AbstractQueuedSynchronizer.ConditionObject;

import java.util.Collection;

public class ReentrantLock_1 implements Lock, java.io.Serializable{
	
	private static final long serialVersionUID = 7373984872572414699L;
	
	/*Synchronizer providing all implementation mechanics*/
	private final Sync sync;
	
	abstract static class Sync extends AbstractQueuedSynchronizer{
		
		private static final long serialVersionUID = -5179523762034025860L;
		
		abstract void lock();
		
		final boolean nonfairTryAquire(int acquires){
			
		}
		
		protected final boolean tryRelease(int release){
			
		}
		
		protected final boolean isHeldExclusively(){
			
		}
		
	
		final ConditionObject newCondition() {
			
		}
		
		final Thread getOwner(){
			
		}
		
		final int getHoldCount(){
			
		}
		
		final boolean isLocked(){
			
		}
		
		private void readObject(java.io.ObjectInputStream s)
			throws java.io.IOException,ClassNotFoundException{
			s.defaultReadObject();
			setState(0);//reset to unlocked state
		}
	}
	
	/*
	 *Sync object for non-fair lock 
	 */
	
	static final class NonfairSync extends Sync{
		
		private static final long serialVersionUID = 7316153563782823691L;
		
		final void lock(){
			
		}
		
		protected final boolean tryAcquire(int acquires){
			
		}
	}
	
	/*
	 * Sync Object for fair lock
	 */
	static final class FairSync extends Sync{
		
		private static final long serialVersionUID = -3000897897090466540L;
		
		final void lock(){
			acquire(1);
		}
		
		/**
		 * Fair version of tryAcquire, Dont grant access unless 
		 * recursive call or no waiters or is first
		 */
		protected final boolean tryAcquire(int acquires){
			
		}
		
	}
	
	public ReentrantLock(){

		sync = new NonfairSync();
	}
	
	public ReentrantLock(boolean fair){
		sync =  fair?new FairSync():new NonfairSync();
	}
	
	public void lock(){
		sync.lock();
	}
	
	public void lockInterruptibly() throws InterraptedException(){
		
	}
	
	public boolean tryLock(){
		return sync.nonfairTryAquire(1);
	}
	
	public boolean tryLock(long timeout, TimeUnit unit)
		throws InterruptedException{
		
	}
	
	public void unlock(){
		sync.release(1);
	}
	
	public Condition newCondition(){
		return sync.newConition();
	}
	
	public int getHoldCount(){
		return sync.getHoldCount();
	}
	
	public boolean isLocked(){
		return sync.isLocked();
	}
	
	public final boolean isFair(){
		return sync instanceof FairSync;
	}
	
	protected Thread getOwner(){
		return sync.getOwner();
		
	}
	
	public final boolean hasQueuedThreads(){
		return sync.hasQueuedThreads();
	}
	
	public final boolean hasQueuedThread(Thread thread){
		return sync.isQueued(thread);
	}
	
	public final int getQueueLength(){
		return sync.getQueueLength();
	}
	
	protected Collection<Thread>getQueuedThreads(){
		return sync.getQueuedThreads();
	}
	
	public boolean hasWaiters(Condition condition){
		
	}
	
	public int getWaiterQueueLength(Condition condition){
		
		
		return sync.getWaitQueueLength((AbstractQueuedSynchronizer.ConditionObject) condition);
	}
	
	protected Collection<Thread>getWaitingThreads(Condition condition){
		
	}
	
	public String toString(){
		
	}	
	

}
