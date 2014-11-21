package graph;

import java.util.ArrayList;

public class GraphMatrix {

	private int[][] matrix;
	
	public GraphMatrix(int nbKnoten){
		int n = nbKnoten;
		this.matrix = new int[n][n];
		for (int i = 0; i<n; i++){
			for (int j = 0; j<n; j++){
				this.matrix[i][j] = 0;
			}
		}
	}
	
	public GraphMatrix(int nbKnoten, int[][] matrixGraph){
		int n = nbKnoten;
		this.matrix = matrixGraph;
	}	
	
	public void setFlow(int flow, int head, int tail){
		this.matrix[head][tail] = flow;
	}
	
}
