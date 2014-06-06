package pack.viktor.mst;

import java.util.PriorityQueue;

import pack.viktor.ds.DisjointSet;
import pack.viktor.ds.DisjointSet_WQUPC;

/** 
* Klasa koja implementira interfejs MST Kruskalovim algoritmom
* @author Viktor Slavkovic 
*/
public class MST_Kruskal implements MST {
	
	private Graph G;
	
	/** 
	* Konstruktor koji vrsi standardne inicijalizacije;
	* @param	G	Graf cije se minimalno stablo razapinjanja trazi;
	*/
	MST_Kruskal(Graph G) {
		this.G = G;
	}
	
	/** 
	* Glavni metod koji generise MST;
	* @return	Vraca MST kao Graph;
	*/
	@Override
	public Graph generate() {
		Graph R = new Graph(G.V());		
		DisjointSet S = new DisjointSet_WQUPC(G.V());
				
		PriorityQueue<Edge> Q = new PriorityQueue<Edge>(2*G.V());
		for (Edge e : G.edges()) Q.add(e);
		
		while (true) {
			if (R.E()==R.V()-1) break;
			Edge e=Q.poll();
			int v=e.getEither();
			int w=e.getOther(v);
			if (S.connected(v, w)) continue;
			S.union(v, w);
			R.addEdge(e);
		}
		
		return R;
	}

	
}
