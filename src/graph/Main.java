/**
 * Name:���Դ���


 * Author:����
 * Date:2009/03/16
 * Content:
 * 	Step1:����AutoBuildGraph���Զ�����һ�������ڽӱ��ͼ�����������侲̬����tips()���ֹ�����һ�������ڽӱ��ͼ
 * 	Step2:����Step1���ɵ�ͼ������һ��Gdijkstra��
 * 	Step3:����Step2���������userInterface����������ʽ������Dijkstra�㷨����ʾ���
 * 	Step4:����AutoBuildGraph���Զ�����һ�������ڽӱ��ͼ�����������侲̬����tips()���ֹ�����һ�������ڽӱ��ͼ
 * 	Step5:����Step4���ɵ�ͼ������һ��StrongConnectivity��
 * 	Step6:����Step5�������������Dijkstra�㷨����ʾ���
 */
package graph;

import java.util.LinkedList;


public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*//����Dijkstra�㷨
		Graph dijkgraph = AutoBuildGraph.buildTestGraphForDijk();  //���д���Ϊ���Դ��룬�Զ�����ͼ�������ֹ�����
		//Graph mygraph = Graph.tips();    //���д��뽫��ʾ�û��ֶ�����ͼ�Ľ�����ͱߵ�Ȩֵ
		Gdijkstra dijkalgo = new Gdijkstra(dijkgraph); 
		dijkalgo.userInterface();
		*/
		//����ǿ��ͨ�����㷨
		//Graph conngraph = AutoBuildGraph.buildTestGraphForConn(); 
		Graph conngraph = Graph.tips();  //���д��뽫��ʾ�û��ֶ�����ͼ�Ľ�����ͱߵ�Ȩֵ
		StrongConnectivity sconnalgo = new StrongConnectivity(conngraph);
		sconnalgo.runStrongConnectivity();
		sconnalgo.showRes();
	}

}
