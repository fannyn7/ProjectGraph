package test;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import zufallsgenerator.Zufallsgenerator;
import graph.Graph;
import graph.Kanten;
import graph.Knoten;

public class Test extends JPanel {
		public void	paintComponent(Graphics	g){
			super.paintComponent(g);
			Graph graph = Zufallsgenerator.createZG(10, 10);
			System.out.println("nb knoten" + graph.getKnoten().size());
			int i=0;
			for (Knoten knoten : graph.getKnoten()){
				g.fillOval(knoten.getPosX()-5, knoten.getPosY()-5, 10, 10);
			}
			for (Kanten kanten: graph.getKanten()){
				Knoten head = kanten.getHead();
				int headX = head.getPosX();
				int headY = head.getPosY();
				Knoten tail = kanten.getTail();
				int tailX = tail.getPosX();
				int tailY = tail.getPosY();
				int flow = kanten.getFlow();
				g.drawLine(headX, headY, tailX, tailY);
				// g.drawString(Integer.toString(flow), Math.min(headX, tailX)+Math.abs(headX-tailX/2), Math.min(headY, tailY)+Math.abs(headY-tailY/2));
			}
	}

		public static void main(String[] args){
			Test test = new	Test();
			JFrame frame = new JFrame("Graph Visualiesierung");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.getContentPane().add(test);
			frame.setSize(500,500);
			frame.setVisible(true);
		}
}
