package graph;
/*
 *VNode�ڵ㣬������Linkһͬ�����ڽ����� 
 */
public class VNode{
	private int linkvalue;
	private int index;
	private VNode next;
	
	VNode(int index, int linkvalue){
		this.linkvalue = linkvalue;
		this.index = index;
		this.next = null;
	}
	void next(VNode next){
		this.next = next;
	}
	VNode next(){
		return next;	
	}
	int linkvalue(){
		return linkvalue;
	}
	int index(){
		return index;
	}
}