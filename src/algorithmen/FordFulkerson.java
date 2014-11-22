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

	//private Stack<Integer> path;
	
	public FordFulkerson(){
		//path = new Stack<Integer>();
	}
	public void fordFulkerson(int[][] graph, int start, int target){
		
		int n = graph.length;
		int[][] flow = new int[n][n];
		for (int i=0; i<n; i++){
			for (int j=0; i<n; i++){
				flow[i][j] = 0;
			}	
		}
		
		//while there exists a path p from s to t in the residual Network Gf
		int[][] residualGraph = graph;
		System.out.println("graphe résiduel");
		for (int i = 0; i < graph.length; i++){
			for (int j = 0; i < graph.length; i++){
				residualGraph[i][j] = graph[i][j] - flow[i][j];
			}	
		}

		System.out.println("start DFS");
		ArrayList<Integer> path = DFS(residualGraph, start, target);
		System.out.println("end DFS : " + path);
		while(path.contains(target)){
			int minCapacityPath = 0;
			int capacity = 0;
			
			for (int i = 0; i < path.size() - 1; i++){
				capacity = residualGraph[path.get(i)][path.get(i+1)];
				if (capacity < minCapacityPath){
					minCapacityPath = capacity;
				}
			}
			
			// for each edge (u,v) in p 
			for (int i = 0; i < path.size() - 1; i++){
				// 	if (u,v) appartient à E
				if (graph[i][i+1] != 0){
					// (u,v).f = (v,u).f + cf(p)
					flow[i][i+1] = flow[i+1][i] + minCapacityPath;
				} else {
					// else (v,u).f = (v,u).f - cf(p)						
					flow[i+1][i] = flow[i+1][i] - minCapacityPath;					
				}
				System.out.println("flow de " + i + "  : "+ flow[i][i+1]);
			}
			System.out.println("start DFS");
			path = DFS(flow, start, target);
			System.out.println("end DFS");
		}
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
			if (graph[node][i] != 0){
				result.add(i);
			}			
		}
		System.out.println("adjacent à " + node + " : " + result);
		return result;
	}
}
