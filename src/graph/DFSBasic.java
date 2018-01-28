/**
 * name:基本深度优先遍历算法
 * Author:杨寅
 * Date:2009/03/16
 */

package graph;

import java.util.ListIterator;



public class DFSBasic {
	int stime[];
	int etime[];
	int prenode[];
	int size = 0;
	int time = 0;
	Graph mygraph;
	public DFSBasic(Graph mygraph){
		this.mygraph = mygraph;
		this.size = mygraph.size();
		stime = new int[size + 1];
		etime = new int[size + 1];
		prenode = new int[size + 1];
	}
	public void runGFS()
	{
		init();
		for(int i = 1; i <= size; i++)
		{
			if(mygraph.getVertex(i).isWhite())
			{
				visitDFS(i);
			}
		}
	}
	void init()
	{
		//初始化
		for(int i = 1; i <= size; i ++)
		{
			mygraph.getVertex(i).setWhite();
			stime[i] = 0;
			etime[i] = 0;
			prenode[i] = -1;
		}
		time = 0;
	}
	void visitDFS(int curnode)
	{
		mygraph.getVertex(curnode).setGray();
		time = time + 1;
		stime[curnode] = time;
		ListIterator<Integer> iterator = mygraph.getAllNeighbours(curnode).listIterator();
		while(iterator.hasNext())
		{
			int exnode = iterator.next();
			if(mygraph.getVertex(exnode).isWhite())
			{
				prenode[exnode] = curnode;
				visitDFS(exnode);
			}
		}
		mygraph.getVertex(curnode).setBlack();
		etime[curnode] = ++time;
	}
	public int[] getEtime()
	{
		return etime;
	}
}
