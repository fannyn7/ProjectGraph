package algorithmen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.JFrame;

import graph.DrawGraph;
import graph.Graph;

public class FordFulkerson {

	private Graph graph;
	private ArrayList<ArrayList<Integer>> pathList;
	
	public FordFulkerson(Graph g){
		graph = g;
		pathList = new ArrayList<ArrayList<Integer>>();
	}

	public FordFulkerson(Graph g, ArrayList<ArrayList<Integer>> list){
		graph = g;
		pathList = list;
	}
	
	public void fordFulkerson(int start, int target){
		
		int n = graph.getKnotenPosition().size();
		int[][] flow = new int[n][n];
		int floxMax = 0;
		for (int i=0; i<n; i++){
			for (int j=0; i<n; i++){
				flow[i][j] = 0;
			}	
		}
		
		//while there exists a path p from s to t in the residual Network Gf
		Graph residualGraph = graph;
		int[][] residualGraphCapacity = residualGraph.getCapacity();
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				residualGraphCapacity[i][j] = graph.getCapacity()[i][j] - flow[i][j];
			}	
		}
/*		System.out.println("Graphe residual début : " );
		for (int i=0; i < residualGraphCapacity.length; i++){
			System.out.println("{" + residualGraphCapacity[i][0] + " , " + residualGraphCapacity[i][1] + " , " + residualGraphCapacity[i][2] + " , " + residualGraphCapacity[i][3] + " , " + residualGraphCapacity[i][4] + " , " + residualGraphCapacity[i][5] + "}");
		}*/

		residualGraph.setCapacity(residualGraphCapacity);
		//drawGraph(residualGraph, new ArrayList<Integer>());		
		ArrayList<Integer> path = DFS(residualGraphCapacity, start, target);
		
		while(path != null && !pathList.contains(path)){
			int minCapacityPath = Integer.MAX_VALUE;
			int capacity = 0;
			
			for (int i = 0; i < path.size() - 1; i++){
				capacity = residualGraphCapacity[path.get(i)][path.get(i+1)];
				if (capacity < minCapacityPath){
					minCapacityPath = capacity;
				}
			}
			
			floxMax += minCapacityPath;
			
			// for each edge (u,v) in p 
			for (int i = 0; i < path.size() - 1; i++){
				// 	if (u,v) appartient a E
				//if (graph[path.get(i)][path.get(i+1)] != 0){
					// (u,v).f = (v,u).f + cf(p)
					flow[path.get(i)][path.get(i+1)] = flow[path.get(i)][path.get(i+1)] + minCapacityPath;
				//} else {
					// else (v,u).f = (v,u).f - cf(p)			
					System.out.println("flow  "+ path.get(i) + "," + path.get(i+1) + " : " + flow[path.get(i)][path.get(i+1)]);
					flow[path.get(i+1)][path.get(i)] = -flow[path.get(i)][path.get(i+1)];					
				//}
				//System.out.println("flow de " + path.get(i) + "  : "+ flow[path.get(i)][path.get(i+1)]);
			}
			
			for (int i = 0; i < n; i++){
				for (int j = 0; j < n; j++){
					residualGraphCapacity[i][j] = residualGraphCapacity[i][j] - flow[i][j];
				}	
			}
			
			System.out.println("Graphe residual : " );
			for (int i=0; i < residualGraphCapacity.length; i++){
				System.out.println("{" + residualGraphCapacity[i][0] + " , " + residualGraphCapacity[i][1] + " , " + residualGraphCapacity[i][2] + " , " + residualGraphCapacity[i][3] + " , " + residualGraphCapacity[i][4] + " , " + residualGraphCapacity[i][5] + "}");
			}
			
			
			pathList.add(path);
			//drawGraph(residualGraph, path);
			path = DFS(residualGraphCapacity, start, target);
		}
		System.out.println("flow Max : " + floxMax);
	}


	public ArrayList<Integer> DFS(int[][] graph, int start, int target) {
		int n = graph.length;
		Stack<Integer> open = new Stack<Integer>();
		ArrayList<Integer> closed = new ArrayList<Integer>();
		int next;
		
		open.push(start);
		if (start != target){
			while (!open.isEmpty()){
				next = open.pop();
				closed.add(next);
				for (int node : getAdjacent(graph, next)){ 
					if (!closed.contains(node)){
						if (node == target){
							closed.add(node);
							System.out.println("fin : " + closed);
							return closed;
						} else {
								open.push(node);															
						}
					} else {
						closed.remove(new Integer(next));
					}
				}
			}
			return null;
			
/*			for (int node : getAdjacent(graph, start)){
				if (!isSeen[node]){
						isSeen[node] = true;
						System.out.println("node pushed : " + node);
						path.push(node);
						DFS(graph, node, target);
						System.out.println("node poped : " + path.peek());
						path.pop();
					} 
				}
				System.out.println("node poped : " + path.peek());
				path.pop();*/
		}
		return null;		
	}	

	public ArrayList<Integer> getAdjacent(int[][] graph, int node){
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < graph.length; i++){
			if (graph[node][i] > 0){
				result.add(i);
			}			
		}
		//System.out.println("adjacent a " + node + " : " + result);
		return result;
	}
	
	public void drawGraph(Graph residualGraph, ArrayList<Integer> path){
		JFrame frame = new JFrame("Graph Visualiesierung");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(new DrawGraph(residualGraph, path));
		frame.setSize(1000,600);
		frame.setVisible(true);
	}
	
}
