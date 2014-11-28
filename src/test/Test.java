package test;

import javax.swing.JFrame;

import algorithmen.FordFulkerson;
import zufallsgenerator.Zufallsgenerator;
import graph.Graph;

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
			//ArrayList<Position> pos = new ArrayList<Position>();
			//pos.add(new Position(50,300));
			//pos.add(new Position(150,200));
			//pos.add(new Position(250,400));
			//pos.add(new Position(350,200));
			//pos.add(new Position(450,400));
			//pos.add(new Position(650,300));
			//int[][] capacity = {{0,10,10,0,0,0},{0,0,2,4,8,0},{0,0,0,0,9,0},{0,0,0,0,0,10},{0,0,0,6,0,10},{0,0,0,0,0,0}};
			//Graphbis graph = new Graphbis(pos, capacity);
						
			
			Graph graph = Zufallsgenerator.createZG(10, 20);
			
			JFrame frame = new JFrame("Graph Visualiesierung");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.getContentPane().add(graph);
			frame.setSize(1000,600);
			frame.setVisible(true);
			
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
			
			
			FordFulkerson ff = new FordFulkerson();
			ff.fordFulkerson(graph, 0, 5);
		}
}
