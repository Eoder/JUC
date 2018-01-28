package graph;
/*
 * ʵ�ʽڵ㣬�����洢ʵ�ʵ��û�����
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
	//������ɫ���ڶ���㷨�б��õ������ڼ���ǿ��ͨ��֧��ʱ��
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