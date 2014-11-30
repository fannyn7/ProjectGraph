package test;

import java.util.ArrayList;

import javax.swing.JFrame;

import algorithmen.EdmondsKarp;
import algorithmen.FordFulkerson;
import zufallsgenerator.Zufallsgenerator;
import graph.Graph;
import graph.Position;

public class Test {
/*		public void	paintComponent(Graphics	g, Graphbis graph){
			super.paintComponent(g);
			Graph graph = Zufallsgenerator.createZG(10, 10);
			System.out.println("nb knoten" + graph.getKnoten().size());
			int i=0;
			ArrayList<Position> positions = graph.getKnotenPosition();
			for (Position p : positions){
				g.fillOval(p.getX()-5, p.getY()-5, 10, 10);
			}
			int[][] capacities = graph.getCapacity();
			int flow;
			int n = capacities.length;
			for (int i = 1; i < n ; i++){
				for (int j = 1; j < n ; j++){
					flow = capacities[i][j];
					if (flow != 0){
						g.drawLine(positions.get(i).getX(), positions.get(i).getY(), positions.get(j).getX(), positions.get(j).getY());
					}
				}	
			}
			
			for (Kanten kanten: graph.getKanten()){
				Knoten head = kanten.getHead();
				int headX = head.getPosX();
				int headY = head.getPosY();
				Knoten tail = kanten.getTail();
				int tailX = tail.getPosX();
				int tailY = tail.getPosY();
				int flow = kanten.getCapacity();
				g.drawLine(headX, headY, tailX, tailY);
				// g.drawString(Integer.toString(flow), Math.min(headX, tailX)+Math.abs(headX-tailX/2), Math.min(headY, tailY)+Math.abs(headY-tailY/2));
			}
	}
*/
		public static void main(String[] args){
			ArrayList<Position> pos = new ArrayList<Position>();
			pos.add(new Position(50,300));
			pos.add(new Position(150,200));
			pos.add(new Position(250,400));
			pos.add(new Position(350,200));
			pos.add(new Position(450,400));
			pos.add(new Position(650,300));
			//int[][] capacity = {{0,9,9,0,0,0},{0,0,10,8,0,0},{0,0,0,1,3,0},{0,0,0,0,0,10},{0,0,0,8,0,7},{0,0,0,0,0,0}};
			int[][] capacity = {{0,10,10,0,0,0},{0,0,2,4,8,0},{0,0,0,0,9,0},{0,0,0,0,0,10},{0,0,0,6,0,10},{0,0,0,0,0,0}};
			Graph graph = new Graph(pos, capacity);
						
			
			//Graph graph = Zufallsgenerator.createZG(5, 20);
			
/*			int n = graph.getCapacity().length;
			int flow;
			for (int i = 0; i < n ; i++){
				for (int j = 0; j < n ; j++){
					flow =  graph.getCapacity()[i][j];
					if (flow != 0){
						System.out.println("flow[" + i + "][" + j + "] : " + flow);						
					}
				}
			}*/
			
			FordFulkerson ff = new FordFulkerson(graph);
			ff.fordFulkerson(0, 5);
			
			//EdmondsKarp ek = new EdmondsKarp(graph);
			//ek.edmondsKarp(0, 5);
			
		}
}
