package Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;

public class Mutex_1 implements Lock{
	
	//��̬�ڲ��࣬�Զ���ͬ����
	private static class Sync extends AbstractQueuedSynchronizer{
		
		private static final long serialVersionUID =  -45644333322L;
		
		//�Ƿ���ռ��״̬
		protected boolean isHeldExclusively(){
			return getState()==1; //״̬1��ʾ��
		}
		
		//��״̬Ϊ0ʱ��ȡ��
		public boolean tryAcquire(int acquires){
			
			assert acquires ==1; //Otherwise unused
			
			if(compareAndSetState(0,1)){
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}
		
		//�ͷ�������״̬����Ϊ0
		protected boolean tryRelease(int releases){
			
			assert releases ==1; //Otherwise unused
			
			
			if(getState()==0){
				throw new IllegalMonitorStateException();
			}
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
			
			
		}
		
		//����һ��Condition, ÿ��condition������һ��condition ����
		Condition newCondition(){
			
			return new ConditionObject();
			
		}
	
		
	}
	
	//���轫��������Sync�ϼ��ɣ��ӿ��������Ĺ�ϵ��
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
