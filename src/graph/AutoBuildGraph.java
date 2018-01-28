/**
 * name:辅助测试类，用于自动生成Dijkstra和强连通算法需要的图
 * Author:杨寅
 * Date:09/03/16
 * Modified1: 
 * 	date: 09/03/17
 * 	content: 添加了public static Graph buildTestGraphForConn()方法
 */
package graph;

public class AutoBuildGraph {
	public AutoBuildGraph()
	{
		
	}
	public static Graph buildTestGraphForDijk()
	{
		System.out.println("This is the test case for graph algorithm...");
		Graph mygraph = new Graph(5);
		
		mygraph.connect(1, 2, 10);
		
		mygraph.connect(1, 3, 5);
		
		mygraph.connect(2, 3, 2);
		
		mygraph.connect(3, 2, 3);
		
		mygraph.connect(2, 4, 1);
		
		mygraph.connect(3, 5, 2);
		
		mygraph.connect(3, 4, 9);
		
		mygraph.connect(4, 5, 4);
		
		mygraph.connect(5, 4, 6);
		
		mygraph.connect(5, 1, 7);
		
		return mygraph;		
	}
	public static Graph buildTestGraphForConn()
	{
		System.out.println("This is the test case for graph algorithm...");
		Graph mygraph = new Graph(8);
		
		mygraph.connect(1, 2, 1);
		
		mygraph.connect(2, 5, 1);
		
		mygraph.connect(2, 3, 1);
		
		mygraph.connect(2, 6, 1);
		
		mygraph.connect(3, 4, 1);
		
		mygraph.connect(3, 7, 1);
		
		mygraph.connect(4, 3, 1);
		
		mygraph.connect(4, 8, 1);
		
		mygraph.connect(5, 1, 1);
		
		mygraph.connect(5, 6, 1);
		
		mygraph.connect(6, 7, 1);
		
		mygraph.connect(7, 6, 1);
		
		mygraph.connect(7, 8, 1);
		
		mygraph.connect(8, 8, 1);
		
		return mygraph;		
	}
}
