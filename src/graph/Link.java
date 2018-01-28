package graph;
/*
 * Link, 用来和VNode一同构成邻接链表
 */
public class Link{
	private VNode first;
	private VNode current;
		
	Link(){
		first = null;
		current = null;
	}
		
	//增加一个邻接点，放在链表开始处
	void add(int to, int linkvalue){
		VNode tempnode = new VNode(to, linkvalue);
		if(first == null)
			first = tempnode;
		else
		{
			tempnode.next(first);
			first = tempnode;
		}
	}
	
	boolean hasNext(){
		return current == null ? first != null : current.next() != null; 
	}
	
	VNode next(){
		if(current == null)
			current = first;
		else
			current = current.next();
		return current;
	}
	
	void reset(){
		current = null;
	}
	//删除一个索引号为index的结点
	void remove(int index){
		VNode pre = null;
		VNode cur = first;
		
		while(cur != null)
		{
			if(cur.index() == index)
			{
				if(pre == null)
				{
					first = cur.next();
				}
				else
				{
					pre.next(cur.next());
				}
				return;
			}
			pre = cur;
			cur = cur.next();
		}
		
	}
}
