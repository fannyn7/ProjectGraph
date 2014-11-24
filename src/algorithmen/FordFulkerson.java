package algorithmen;

import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import graph.Graph;
import graph.GraphMatrix;
import graph.Kanten;
import graph.Knoten;

public class FordFulkerson {

	private ArrayList<ArrayList<Integer>> pathList;
	
	public FordFulkerson(){
		pathList = new ArrayList<ArrayList<Integer>>();
	}
	public void fordFulkerson(int[][] graph, int start, int target){
		
		int n = graph.length;
		int[][] flow = new int[n][n];
		int floxMax = 0;
		for (int i=0; i<n; i++){
			for (int j=0; i<n; i++){
				flow[i][j] = 0;
			}	
		}
		
		//while there exists a path p from s to t in the residual Network Gf
		int[][] residualGraph = graph;
		for (int i = 0; i < graph.length; i++){
			for (int j = 0; j < graph.length; j++){
				residualGraph[i][j] = graph[i][j] - flow[i][j];
			}	
		}
/*		System.out.println("Graphe residual : " );
		for (int i=0; i < residualGraph.length; i++){
			System.out.println("{" + residualGraph[i][0] + " , " + residualGraph[i][1] + " , " + residualGraph[i][2] + " , " + residualGraph[i][3] + " , " + residualGraph[i][4] + " , " + residualGraph[i][5] + "}");
		}*/

		
		ArrayList<Integer> path = DFS(residualGraph, start, target);
		while(path != null && !pathList.contains(path)){
			int minCapacityPath = Integer.MAX_VALUE;
			int capacity = 0;
			
			for (int i = 0; i < path.size() - 1; i++){
				capacity = residualGraph[path.get(i)][path.get(i+1)];
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
					flow[path.get(i+1)][path.get(i)] = flow[path.get(i+1)][path.get(i)] - minCapacityPath;					
				//}
				//System.out.println("flow de " + path.get(i) + "  : "+ flow[path.get(i)][path.get(i+1)]);
			}
			
			for (int i = 0; i < graph.length; i++){
				for (int j = 0; j < graph.length; j++){
					residualGraph[i][j] = residualGraph[i][j] - flow[i][j];
				}	
			}
			
			/*System.out.println("Graphe residual : " );
			for (int i=0; i < residualGraph.length; i++){
				System.out.println("{" + residualGraph[i][0] + " , " + residualGraph[i][1] + " , " + residualGraph[i][2] + " , " + residualGraph[i][3] + " , " + residualGraph[i][4] + " , " + residualGraph[i][5] + "}");
			}*/
			pathList.add(path);
			path = DFS(residualGraph, start, target);
		}
		System.out.println("flow Max : " + floxMax);
	}

	public ArrayList<Integer> DFS(int[][] graph, int start, int target) {
		int n = graph.length;
		Stack<Integer> open = new Stack<Integer>();
		ArrayList<Integer> closed = new ArrayList<Integer>();
		int next;
		boolean[] isSeen = new boolean[n];
		for (int i = 0; i < n ; i++){
			isSeen[i] = false;
		}	
		isSeen[start] = true;
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
		// System.out.println("adjacent a " + node + " : " + result);
		return result;
	}
}
