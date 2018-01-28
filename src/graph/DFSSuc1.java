/**
 * name: DFS的变体，用来辅助计算强连通分量
 * author: 杨寅
 * Date:09/03/17
 */
package graph;

import java.util.LinkedList;

import java.util.ListIterator;



public class DFSSuc1 {
	int etime[];
	Graph mygraph;
	int size;
	boolean visited[];
	LinkedList<LinkedList<Integer>> res; //用来存储强连通分量
	public DFSSuc1(Graph mygraph, int etime[])
	{
		size = mygraph.size();
		visited = new boolean[size + 1];
		this.etime = etime;
		this.mygraph = mygraph;
	}
	//初始化相关变量
	void init()
	{
		res = new LinkedList<LinkedList<Integer>>();
		for(int i = 1; i <= size; i ++)
		{
			mygraph.getVertex(i).setWhite();
			visited[i] = false;
		}
	}
	//从首次深度优先搜索的完成时间中找最大值
	int getLargestEtime()
	{
		int largestpos = -5;
		int largestvalue = -5;
		for(int i = 1; i <= size; i++)
		{
			if(!visited[i] && etime[i] > largestvalue)
			{
				largestpos = i;
				largestvalue = etime[i];
			}
		}
		return largestpos;
	}
	//运行变体深度优先搜索算法
	public void runGFS()
	{
		init();
		int targetnode = 0;
		while((targetnode = getLargestEtime()) != -5)
		{	
			visited[targetnode] = true;
			LinkedList<Integer> templist = new LinkedList<Integer>();
			templist.add(targetnode);
			visitDFS(targetnode, templist);
			res.add(templist);
		}
	}
	public LinkedList<LinkedList<Integer>> getRes()
	{
		return res;
	}
	void visitDFS(int curnode, LinkedList<Integer> templist)
	{
		mygraph.getVertex(curnode).setGray();
		ListIterator<Integer> iterator = mygraph.getAllNeighbours(curnode).listIterator();
		while(iterator.hasNext())
		{
			int exnode = iterator.next();
			if(mygraph.getVertex(exnode).isWhite())
			{
				templist.add(exnode);
				visited[exnode] = true;
				visitDFS(exnode, templist);
			}
		}
		mygraph.getVertex(curnode).setBlack();
	}
}
