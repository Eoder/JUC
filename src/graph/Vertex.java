package graph;
/*
 * 实际节点，用来存储实际的用户数据
 */
public class Vertex{
	private Object value;
	private boolean isVisited;
	private Color color;
	public Link link;
	
	Vertex(Object value){
		link = new Link();
		this.value = value;
		isVisited = false;
		color = Color.WHITE;
	}
	//结点的颜色将在多个算法中被用到，如在计算强连通分支的时候
	public void setWhite()
	{
		this.color = Color.WHITE;
	}
	public void setGray()
	{
		this.color = Color.GRAY;
	}
	public void setBlack()
	{
		this.color = Color.BALCK;
	}
	public boolean isWhite()
	{
		return color == Color.WHITE;
	}
	public boolean isGray()
	{
		return color == Color.GRAY;
	}
	public boolean isBlack()
	{
		return color == Color.BALCK;
	}
	void visit(){
		isVisited = true;
	}
	boolean isVisited(){
		return isVisited;
	}
}