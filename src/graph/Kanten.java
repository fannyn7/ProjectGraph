package graph;

import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Kanten {

	private Knoten tail;
	private Knoten head;
	private int capacity;
	private int flow;
	
	public Kanten(Knoten tailKnoten, Knoten headKnoten, int flowEdge){
		tail = tailKnoten;
		head = headKnoten;
		capacity = flowEdge;
		flow = 0;
	}

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}

	public Knoten getTail() {
		return tail;
	}

	public void setTail(Knoten tail) {
		this.tail = tail;
	}

	public Knoten getHead() {
		return head;
	}

	public void setHead(Knoten head) {
		this.head = head;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean intersectOne(ArrayList<Kanten> kanten) {
		boolean result = false;
		int d, x3, x4, y3, y4, xi, yi;
		int x1 = this.head.getPosX();
		int x2 = this.tail.getPosX();
		int y1 = this.head.getPosY();
		int y2 = this.tail.getPosY();
		
		for (Kanten k : kanten){
			
			x3 = k.head.getPosX();
			x4 = k.tail.getPosX();
			y3 = k.head.getPosY();
			y4 = k.tail.getPosY();
			d = (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4);

			if (d!=0 ){
				xi = ((x3-x4)*(x1*y2-y1*x2)-(x1-x2)*(x3*y4-y3*x4))/d;
				yi = ((y3-y4)*(x1*y2-y1*x2)-(y1-y2)*(x3*y4-y3*x4))/d;
				if (!(xi <= Math.min(x1,x2) || xi >= Math.max(x1,x2))){
					if (!(xi <= Math.min(x3,x4) || xi >= Math.max(x3,x4))){
						if (!(yi <= Math.min(y1,y2) || yi >= Math.max(y1,y2))){
							if (!(yi <= Math.min(y3,y4) || yi >= Math.max(y3,y4))){
								result = true;
							}
						}
					}
				}
			}
		}
		return result;
	}
	
}
