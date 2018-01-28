/**
 * Name:����ͼ��ǿ��ͨ��֧�㷨�������ڽӱ�,����ת�þ��� + ����������������㷨ʵ��
 * Author:����
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
	//����ǿ��ͨ����
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
	//��ӡ���
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
