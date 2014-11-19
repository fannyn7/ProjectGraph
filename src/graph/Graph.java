package graph;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Graph {

	private ArrayList<Knoten> knoten = new ArrayList<Knoten>();
	private ArrayList<Kanten> kanten = new ArrayList<Kanten>();
	
	public Graph(ArrayList<Knoten> knotenList, ArrayList<Kanten> kantenList){
		knoten = knotenList;
		kanten = kantenList;
	}

	public ArrayList<Knoten> getKnoten() {
		return knoten;
	}

	public void setKnoten(ArrayList<Knoten> knoten) {
		this.knoten = knoten;
	}

	public ArrayList<Kanten> getKanten() {
		return kanten;
	}

	public void setKanten(ArrayList<Kanten> kanten) {
		this.kanten = kanten;
	}	
}
