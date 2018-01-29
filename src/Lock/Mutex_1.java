package Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;

public class Mutex_1 implements Lock{
	
	//静态内部类，自定义同步器
	private static class Sync extends AbstractQueuedSynchronizer{
		
		private static final long serialVersionUID =  -45644333322L;
		
		//是否处于占用状态
		protected boolean isHeldExclusively(){
			return getState()==1; //状态1表示？
		}
		
		//当状态为0时获取锁
		public boolean tryAcquire(int acquires){
			
			assert acquires ==1; //Otherwise unused
			
			if(compareAndSetState(0,1)){
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}
		
		//释放锁，将状态设置为0
		protected boolean tryRelease(int releases){
			
			assert releases ==1; //Otherwise unused
			
			
			if(getState()==0){
				throw new IllegalMonitorStateException();
			}
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
			
			
		}
		
		//返回一个Condition, 每个condition都包含一个condition 队列
		Condition newCondition(){
			
			return new ConditionObject();
			
		}
	
		
	}
	
	//仅需将操作代理到Sync上即可（接口与抽象类的关系）
	private final Sync sync = new Sync();

	@Override
	public void lock() {
		// TODO Auto-generated method stub
		sync.acquire(1);
		
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		sync.acquireInterruptibly(1);
		
		
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return sync.tryAcquireNanos(1, unit.toNanos(timeout));
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		sync.release(1);
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return sync.newCondition();
	}
	
	public boolean isLocked(){
		return sync.isHeldExclusively();
	}
	
	public boolean hasQueuedThreads(){
		return sync.hasQueuedThreads();
	}
	
	



}
