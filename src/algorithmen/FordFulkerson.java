package algorithmen;

import graph.Graph;
import graph.Kanten;
import graph.Knoten;

public class FordFulkerson {

	public void fordFulkerson(Graph graph, Knoten start, Knoten target){
		
		int n = graph.getKnoten().size();
		for (Kanten k : graph.getKanten()){
			k.setFlow(0);
		}
		
		//while there exists a path p from s to t in the residual Network Gf
		while(true){
			int minFlowResidualKanten = 0;
			int flowResidual = 0;
			// cf(p) = min{cf(u,v) : (u,v) is in p} !!!!!!!!!!!!!!! IN P !!!!!!!!!!!!!!!!!!
			for (Kanten  k : graph.getKanten()){
				// cf = c - f
				flowResidual = k.getCapacity() - k.getFlow();
				if (flowResidual < minFlowResidualKanten){
					minFlowResidualKanten = flowResidual;
				}
			}
				
			// for each edge (u,v) in p 
			for (Kanten k : graph.getKanten()){
				// 	if (u,v) appartient à E
				//if (k appartient à E){
					// (u,v).f = (v,u).f + cf(p)
					k.setFlow(k.getFlow()+flowResidual);
				//} else {
					// else (v,u).f = (v,u).f - cf(p)						
					k.setFlow(k.getFlow()-flowResidual);					
				//}
			}
		}
	}	
}
