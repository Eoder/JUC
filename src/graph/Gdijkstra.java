/**
 * Name:Dijstra算法，基于邻接表
 * Author:杨寅
 * Date:2009/03/16
 */
package graph;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;



public class Gdijkstra {
	
	int dvalue[];
	int shortest[];
	int source = 0;
	
	private Graph mygraph;
	
	public Gdijkstra(Graph mygraph){
		this.mygraph = mygraph;
		dvalue = new int[mygraph.size() + 1];
		shortest = new int[mygraph.size() + 1];
	}
	//用户交互，提示用户输入源点
	public void userInterface()
	{
		System.out.println("/*** The Dijkstra Algorithm ***/");
		System.out.println("Input your source node:");
		System.out.print("$");
		BufferedReader in =	new BufferedReader(new InputStreamReader( System.in));
		try{
			this.source = Integer.parseInt(in.readLine().trim());
		}catch(Exception e)
		{
			
		}
		runDijkstra();
		showResults();
	}
	private void initializeSingleSource()
	{
		for(int i = 1; i <= mygraph.size(); i++)
		{
			dvalue[i] = -1; // -1 is the maximum value
			shortest[i] = 0;
		}
		dvalue[source] = 0;
	}
	//松弛算法
	private void relax(int u, int v, int w)
	{
		int edgevalue = mygraph.getEdgeValue(u, v);
		if(dvalue[v] == -1 || dvalue[v] > dvalue[u] + edgevalue)
		{
			dvalue[v] = dvalue[u] + edgevalue;
			shortest[v] = u;
		}
	}
	//运行Dijkstra算法
	public void runDijkstra()
	{
		//初始化松弛参数
		this.source = source;
		initializeSingleSource();
		//初始化结点集合S和Q
		LinkedList<Integer> S = new LinkedList<Integer>();
		LinkedList<Integer> Q = new LinkedList<Integer>();
		for(int i = 1; i <= mygraph.size(); i++)
			Q.add(i);
		while(Q.size() != 0)
		{
			ListIterator<Integer> iterator1 = Q.listIterator();
			ListIterator<Integer> iterator2 = null;
			int minvalue = -1;
			int minpos = -1;
			while(iterator1.hasNext())
			{
				int curpos = iterator1.next();
				if((minvalue == -1 || dvalue[curpos] < minvalue) && dvalue[curpos] != -1)
				{
					minvalue = dvalue[curpos];
					minpos = curpos;
				}
			}
			Q.remove(Q.indexOf(new Integer(minpos)));
			S.add(minpos);
			
			iterator2 = mygraph.getAllNeighbours(minpos).listIterator();
			while(iterator2.hasNext())
			{
				int pos = iterator2.next();
				relax(minpos, pos, mygraph.getEdgeValue(minpos, pos));
			}
		}
	}
	//递归法打印最短路径
	private void printfPath(int source, int dest)
	{
		if(dest == source)
			System.out.print(source);
		else
		{
			printfPath(source, shortest[dest]);
			System.out.print("->" + dest);
		}
	}
	//打印结果
	public void showResults()
	{
		System.out.println("/*** The Dijkstra Results, source point is " + source+ " ***/");
		for(int i = 1; i <= mygraph.size(); i++)
		{
			if(dvalue[i] != -1 && i != source)
			{
				System.out.println("*******************");
				System.out.println("To node [" + i + "]");
				System.out.println("Minimum path length: " + dvalue[i]);
				printfPath(source, i);
				System.out.println("");
				
			}
		}
		System.out.println("*******************");
		System.out.println("");
	}
}
