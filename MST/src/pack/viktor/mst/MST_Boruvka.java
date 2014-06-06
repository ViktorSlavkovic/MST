package pack.viktor.mst;

import pack.viktor.ds.DisjointSet_WQUPC;

/** 
* Klasa koja implementira interfejs MST Boruvkinim algoritmom
* @author Viktor Slavkovic 
*/
public class MST_Boruvka implements MST {
	
	private Graph G;
	
	/** 
	* Konstruktor koji vrsi standardne inicijalizacije;
	* @param	G	Graf cije se minimalno stablo razapinjanja trazi;
	*/
	public MST_Boruvka(Graph G) {
		this.G = G;
	}
	
	/** 
	* Glavni metod koji generise MST;
	* @return	Vraca MST kao Graph;
	*/
	@Override
	public Graph generate() {
		Graph R = new Graph(G.V());		
		DisjointSet_WQUPC S = new DisjointSet_WQUPC(G.V());
		
		while (true) {
			if (R.E()==R.V()-1) break;
			
			Edge[] compMin = new Edge[G.V()];
			for (int i=0; i<compMin.length; i++) compMin[i]=null;
			
			for(Edge e : G.edges()) {
				int v = e.getEither();
				int w = e.getOther(v);
				v=S.find(v);
				w=S.find(w);
				
				if (v==w) continue;
				
				if (compMin[v]==null || e.compareTo(compMin[v])<0) compMin[v]=e;
				if (compMin[w]==null || e.compareTo(compMin[w])<0) compMin[w]=e;
			}
			
			for (Edge e: compMin) if (e!=null){
				int v = e.getEither();
				int w = e.getOther(v);
				if (S.connected(v,w)) continue;
				S.union(v, w);
				R.addEdge(e);
			}
		}
		
		return R;
	}

}
