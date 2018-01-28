/**
 * Name:有向图的强连通分支算法，基于邻接表,采用转置矩阵 + 二次深度优先搜索算法实现
 * Author:杨寅
 * Date:2009/03/17
 */
package graph;

import java.util.LinkedList;

import java.util.ListIterator;




public class StrongConnectivity {
	Graph mygraph;
	Graph myrevgraph;
	DFSBasic dfsbasic;
	DFSSuc1 dfssuc1;
	LinkedList<LinkedList<Integer>> res;
	public StrongConnectivity(Graph mygraph){
		this.mygraph = mygraph;
		myrevgraph = mygraph.getReverseGraph();
		dfsbasic = new DFSBasic(mygraph);
	}
	//计算强连通分量
	public void runStrongConnectivity()
	{
		dfsbasic.runGFS();
		dfssuc1 = new DFSSuc1(myrevgraph, dfsbasic.getEtime());
		dfssuc1.runGFS();
		res = dfssuc1.getRes();
		//test code below
		for(int i = 1; i <= mygraph.size(); i++)
		{
			System.out.println("etime[" + i + "] = " + dfsbasic.getEtime()[i]);
		}
		System.out.println("neighbour:" + myrevgraph.getAllNeighbours(1).toString());
	}
	//打印结果
	public void showRes()
	{
		System.out.println("/*** The connectivity algorithm ***/");
		System.out.println("/#The strong connective subgraphs are:/");
		ListIterator<LinkedList<Integer>> iterator = res.listIterator();
		int i = 0;
		while(iterator.hasNext())
		{
			System.out.println("Subgraph [" + ++i + "]: ");
			ListIterator<Integer> templist = iterator.next().listIterator();
			while(templist.hasNext())
			{
				System.out.print(templist.next() + " ");
			}
			System.out.println("");
		}
		
		
		System.out.println("*******************");
		System.out.println("");
	}
}
