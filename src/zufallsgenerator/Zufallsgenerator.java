package zufallsgenerator;

import graph.Graph;
import graph.GraphMatrix;
import graph.Kanten;
import graph.Knoten;

import java.util.ArrayList;
import java.util.Random;

public class Zufallsgenerator {

	public static ArrayList<Knoten> createZKnoten(int knotenzahl){
		ArrayList<Knoten> knoten = new ArrayList<Knoten>(knotenzahl);
		Random r = new Random();
		int randomX;
		int randomY;
		for (int i=0; i<knotenzahl; i++){
			randomX = r.nextInt(400);
			randomY = r.nextInt(400);
			knoten.add(new Knoten(randomX, randomY));
		}
		return knoten;
	}
	

	private static ArrayList<Kanten> createZKanten(ArrayList<Knoten> knoten,
			int maxKapazitat) {
		ArrayList<Kanten> kanten = new ArrayList<Kanten>();
		Kanten newKanten;
		Random r = new Random();
		int randomKapazitat = r.nextInt(maxKapazitat);
		kanten.add(new Kanten(knoten.get(0), knoten.get(1), randomKapazitat));
		kanten.add(new Kanten(knoten.get(1), knoten.get(0), randomKapazitat));
		for (int i=0; i<knoten.size()-1; i++){
			for (int j=2; j<knoten.size(); j++){
				System.out.println("Segment : [" + i + " , " + j + "]");
				randomKapazitat = r.nextInt(maxKapazitat);
				newKanten = new Kanten(knoten.get(j), knoten.get(i), randomKapazitat);
				System.out.println("result : " +newKanten.intersectOne(kanten) );
				if (!newKanten.intersectOne(kanten) && i!=j){
					System.out.println("add Segment");
					kanten.add(newKanten);
					kanten.add(new Kanten(knoten.get(i), knoten.get(j), randomKapazitat));
				}
			}
		}
		return kanten;
	}
	
	public static Graph createZG(int knotenzahl, int maxKapazitat){
		ArrayList<Knoten> knoten = createZKnoten(knotenzahl);
		ArrayList<Kanten> kanten = createZKanten(knoten, maxKapazitat);
		return new Graph(knoten, kanten);
	}
	
	public static GraphMatrix createZGMatrix(int knotenzahl, int maxKapazitat){

		int n = knotenzahl;
		int randomKapazitat;
		Random r = new Random();
		GraphMatrix graph = new GraphMatrix(n);
		for (int i = 0; i<n; i++){
			for (int j = 0; j<n; j++){
				randomKapazitat = r.nextInt(maxKapazitat);
				graph.setFlow(randomKapazitat, i, j);
				randomKapazitat = r.nextInt(maxKapazitat);
				graph.setFlow(randomKapazitat, j, i);
			}
		}
		return graph;
	}
}
