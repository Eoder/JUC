/**
 * Name:图的邻接表实现
 * Author:杨寅
 * Date:2009/03/16
 */

package graph;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/*
 * Graph用来表示整个图
 */
public class Graph{
	private Vertex[] vertexs;
	private int pos = -1;
	private int size = 0;
	
	//size表示结点个数，link的最大数目为等于size
	Graph(int size){
		this.size = size;
		vertexs = new Vertex[size + 1];
		for(int i = 1; i <= size; i++)
			vertexs[i] = new Vertex(new Integer(i));
	}
	public int size(){
		return size;
	}
	public Vertex getVertex(int num){
		return vertexs[num];
	}
	//增加一个实际节点,目前暂不使用此功能
	void add(Object obj){
		assert pos <= size;
		vertexs[++pos] = new Vertex(obj); 
	}
	void connect(int from, int to, int linkvalue)
	{
		//单向表
		vertexs[from].link.add(to, linkvalue);
		//双向表
		//vertexs[from].link.add(to);
		//vertexs[to].link.add(from);
	}
	void disconnect(int from, int to)
	{
		//单向表
		vertexs[from].link.remove(to);
		//双向表
		//vertexs[from].link.remove(to);
		//vertexs[to].link.remove(from);
	}
	//查询两点之间边的权值,若不存在边，则返回-1表示无穷大
	public int getEdgeValue(int from, int to)
	{
		int value = -1;
		VNode temp = null;
		vertexs[from].link.reset();
		while((temp = vertexs[from].link.next()) != null)
		{
			if(temp.index() == to){
				value = temp.linkvalue();
			}
		}
		return value;
	}
	//返回一个结点的所有邻接点
	public LinkedList<Integer> getAllNeighbours(int index)
	{
		LinkedList<Integer> neighbours  = new LinkedList<Integer>();
		VNode temp = null;
		vertexs[index].link.reset();
		while((temp = vertexs[index].link.next()) != null)
		{
			neighbours.add(temp.index());
		}
		return neighbours;
	}
	//构造图
	public static Graph tips()
	{
		BufferedReader in =	new BufferedReader(new InputStreamReader( System.in));
		String aline;
		int size = 0;
		int from = 0;
		int to = 0;
		int linkvalue = 0; 
		
		Graph mygraph = null;
		
		
		
		System.out.println("/** Graph Algorithm");
		System.out.println(" *  Author: YangYin, reviesed by yuli");
		System.out.println(" *  Date:2009/03/16");
		System.out.println("*/");
		
		System.out.println("<--input your graph size:");
		System.out.print("$");
		try{
			size = Integer.parseInt(in.readLine().trim());
		}catch(Exception e)
		{
		}
		mygraph = new Graph(size);
		while(1 == 1)
		{
			System.out.println("<--add edge like \"from value edgevalue\", using 0 0 0 to end:");
			System.out.print("$");
			try{
				aline = in.readLine().trim();
				int pos1 = aline.indexOf(" ");
				int pos2 = aline.indexOf(" ", pos1 + 1);
				from = Integer.parseInt(aline.substring(0, pos1));
				to = Integer.parseInt(aline.substring(pos1 + 1, pos2));
				linkvalue = Integer.parseInt(aline.substring(pos2 + 1, aline.length() ));
				if(from == 0 && to == 0)
					break;
				//System.out.println("from:" + from + " to:" + to + " linkvalue:" + linkvalue);
				mygraph.connect(from, to, linkvalue);
			}catch(Exception e)
			{
			}
		}
		return mygraph;
	}
	//计算并返回转置图
	public Graph getReverseGraph()
	{
		Graph revgraph = new Graph(size);
		for(int i = 1; i <= size; i++)
		{
			while(vertexs[i].link.hasNext())
			{
				VNode tempnode = vertexs[i].link.next();
				revgraph.connect(tempnode.index(), i, tempnode.linkvalue());
			}
		}
		return revgraph;
	}
}
	

