/**
 * Name:测试代码


 * Author:杨寅
 * Date:2009/03/16
 * Content:
 * 	Step1:利用AutoBuildGraph类自动构建一个基于邻接表的图，或者利用其静态函数tips()来手工构建一个基于邻接表的图
 * 	Step2:基于Step1生成的图来生成一个Gdijkstra类
 * 	Step3:调用Step2中生成类的userInterface方法来交互式地运行Dijkstra算法并显示结果
 * 	Step4:利用AutoBuildGraph类自动构建一个基于邻接表的图，或者利用其静态函数tips()来手工构建一个基于邻接表的图
 * 	Step5:基于Step4生成的图来生成一个StrongConnectivity类
 * 	Step6:调用Step5中生成类的运行Dijkstra算法并显示结果
 */
package graph;

import java.util.LinkedList;


public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*//测试Dijkstra算法
		Graph dijkgraph = AutoBuildGraph.buildTestGraphForDijk();  //此行代码为测试代码，自动构建图，不用手工输入
		//Graph mygraph = Graph.tips();    //此行代码将提示用户手动输入图的结点数和边的权值
		Gdijkstra dijkalgo = new Gdijkstra(dijkgraph); 
		dijkalgo.userInterface();
		*/
		//测设强连通分量算法
		//Graph conngraph = AutoBuildGraph.buildTestGraphForConn(); 
		Graph conngraph = Graph.tips();  //此行代码将提示用户手动输入图的结点数和边的权值
		StrongConnectivity sconnalgo = new StrongConnectivity(conngraph);
		sconnalgo.runStrongConnectivity();
		sconnalgo.showRes();
	}

}
