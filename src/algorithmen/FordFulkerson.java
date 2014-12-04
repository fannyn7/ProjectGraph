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
	public int i = 0;

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
			for (int j=0; j<n; j++){
				flow[i][j] = 0;
			}	
		}

		//while there exists a path p from s to t in the residual Network Gf
		Graph residualGraph = new Graph(graph.getKnotenPosition(),graph.getCapacity());

		/*int[][] residualGraphCapacity = residualGraph.getCapacity();
		/// ne sert à rien a priori ?? (flow = 0 ici)
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				residualGraphCapacity[i][j] = graph.getCapacity()[i][j] - flow[i][j];
			}	
		}
		residualGraph.setCapacity(residualGraphCapacity);
		 */


		ArrayList<Integer> path = DFS(/*residualGraphCapacity*/ residualGraph.getCapacity(), start, target);
		while(path != null && !pathList.contains(path)){
			//drawGraph(new Graph(residualGraph.getKnotenPosition(),residualGraph.getCapacity()), path);
			for (int i = 0; i < n; i++){
				for (int j = 0; j < n; j++){
					flow[i][j] = 0;
				}	
			}

			int minCapacityPath = Integer.MAX_VALUE;
			int capacity = 0;

			for (int i = 0; i < path.size() - 1; i++){
				capacity = residualGraph.getCapacity()[path.get(i)][path.get(i+1)];
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
				flow[path.get(i)][path.get(i+1)] = /*flow[path.get(i)][path.get(i+1)] +*/ minCapacityPath;
				//} else {
				// else (v,u).f = (v,u).f - cf(p)			
				//System.out.println("flow  "+ path.get(i) + "," + path.get(i+1) + " : " + flow[path.get(i)][path.get(i+1)]);
				//flow[path.get(i+1)][path.get(i)] = -flow[path.get(i)][path.get(i+1)];					
				//}
				//System.out.println("flow de " + path.get(i) + "  : "+ flow[path.get(i)][path.get(i+1)]);
			}

			for (int i = 0; i < n; i++){
				for (int j = 0; j < n; j++){
					residualGraph.getCapacity()[i][j] -= flow[i][j];
					residualGraph.getCapacity()[j][i] += flow[i][j];
				}	
			}

			/*System.out.println("Graphe residual : " );
			for (int i=0; i < residualGraph.getCapacity().length; i++){
				System.out.println("{" + residualGraph.getCapacity()[i][0] + " , " + residualGraph.getCapacity()[i][1] + " , " + residualGraph.getCapacity()[i][2] + " , " + residualGraph.getCapacity()[i][3] + " , " + residualGraph.getCapacity()[i][4] + " , " + residualGraph.getCapacity()[i][5] + "}");
			}
*/

			pathList.add(path);
			//drawGraph(new Graph(residualGraph.getKnotenPosition(),residualGraph.getCapacity()), path);

			/*for (int i = 0; i < n; i++){
				for (int j = 0; j < n; j++){
					System.out.println("residualGraphCapacity["+i+"]["+j+"] = " + residualGraphCapacity[i][j]) ;
				}	
			}*/
			path = DFS(residualGraph.getCapacity(), start, target);
		}
		System.out.println("flow Max Ford-Fulkerson : " + floxMax);
	}


	public ArrayList<Integer> DFS(int[][] graph, int start, int target) {
		int n = graph.length;
		Stack<Integer> stack = new Stack<Integer>();
		int[] isSeen = new int[n];
		int[] nbAdj = new int[n];
		int current = start;
		int previous;

		stack.push(start);

		for (int i=0; i<n; i++){
			isSeen[i] = 0;
			nbAdj[i] = 0;
		}
		
		isSeen[start]=1;
		
		if (start != target){

			while (!stack.isEmpty()){
				previous = current;
				current = stack.peek();

				ArrayList<Integer> adjacents = getAdjacent(graph, current); 
				int i = nbAdj[current];
				int nextAdj;
				if (i==adjacents.size() ){
					isSeen[current]=2;
					stack.pop();
				} else {
					nextAdj = adjacents.get(i);
					if (isSeen[nextAdj]==0){
						isSeen[nextAdj] = 1;
						stack.push(nextAdj);
						if (nextAdj==target){
							//System.out.println("stack : " + stack);
							//System.out.println("list : " + new ArrayList<Integer>(stack));
							return  new ArrayList<Integer>(stack);
						}
					}
					nbAdj[current] ++;
				}
			}
		}
	return null;
}	


/*int n = graph.length;
		Stack<Integer> open = new Stack<Integer>();
		ArrayList<Integer> closed = new ArrayList<Integer>();
		int next = 0, previous;
		System.out.println("DFS");
		open.push(start);
		System.out.println("push : " + start);
		if (start != target){
			System.out.println("stack : " + open);

			while (!open.isEmpty()){
				next = open.pop();
				closed.add(next);
				System.out.println(next+ " added to close ");
				System.out.println("\npath : " + closed + "\nstack : " + open);
				int nbAdjacents = 0;
				ArrayList<Integer> adjacents = getAdjacent(graph, next); 
				if (adjacents!=null){
					for (int node : adjacents){ 
						if (!closed.contains(node)){
							if (node == target){
								closed.add(node);
								System.out.println(node+ " added to close ");
								System.out.println("\npath : " + closed + "\nstack : " + open);
								System.out.println("fin : " + closed);
								return closed;
							} else {
								open.push(node);
								System.out.println("\npath : " + closed + "\nstack : " + open);
							}
						} else {
							nbAdjacents++;						
							if (nbAdjacents==getAdjacent(graph, next).size()){
								closed.remove(new Integer(next));
								System.out.println(node +" déjà là");
								System.out.println(next+ " removed to close 1 ");
								System.out.println("\npath : " + closed + "\nstack : " + open);
							}	
						}
					}
				} else {
					// on est dans un puits
					previous = getParent(graph,next);
					if (previous >= 0) {
						graph[previous][next] = 0;						
					}
					System.out.println(next + " est un puits");
					System.out.println("remove " + previous + "->" + next);
					closed.remove(new Integer(next));
					System.out.println(next+ " removed to close 2 ");
					System.out.println("\npath : " + closed + "\nstack : " + open);
				}
			}
			return null;

		}
		return null;		*/

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

public int getParent(int[][] graph, int node){
	for (int i = graph.length - 1; i >= 0; i--){
		if (graph[i][node] > 0){
			return i;
		}			
	}
	return -1;
}

public void drawGraph(Graph residualGraph, ArrayList<Integer> path){
	JFrame frame = new JFrame("Graph Visualiesierung " + i++);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.getContentPane().add(new DrawGraph(residualGraph, path));
	frame.setSize(1000,600);
	frame.setVisible(true);
}

}
