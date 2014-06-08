package pack.viktor.mst;

import pack.viktor.pq.MinIdxPriorityQueue;
import pack.viktor.pq.MinIdxPriorityQueue_BH;

/** 
* Klasa koja implementira interfejs MST Primovim algoritmom
* @author Viktor Slavkovic 
*/
public class MST_Prim implements MST {
	
	private Graph R;
	private final Graph G;
	private MinIdxPriorityQueue<Edge> Q;
	boolean used[];
	
	private int start;
	
	/** 
	* Konstruktor koji vrsi standardne inicijalizacije;
	* @param	G	Graf cije se minimalno stablo razapinjanja trazi;
	*/
	MST_Prim(Graph G) {
		this.G = G;
		start=0;
	}
	
	/** 
	* Metod za read pristup startnom indeksu;
	* @return	Vraca broj komponenata povezanosti.
	*/
	public int getStart() {
		return start;
	}
	
	/** 
	* Metod za write pristup startnom indeksu;
	* @param	val		Startni indeks;	
	* @throws	IndexOutOfBoundsException	Ako prosledjeni indeks nije validan;
	*/
	public void setStart(int val) {
		if (val < 0 || val >= G.V())
			throw new IndexOutOfBoundsException();
		start = val;
	}
	
	/** 
	* Glavni metod koji generise MST;
	* @return	Vraca MST kao Graph;
	*/
	@Override
	public Graph generate() {
		
            R = new Graph(G.V());		
            used = new boolean[G.V()];
            for (int i=0; i<used.length; i++) used[i]=false;
	    Q = new MinIdxPriorityQueue_BH<>(G.V(),2*G.V());
	    
	    used[start]=true;
	    collect(start);	    
	    
	    while (true) {
			if (R.E()==R.V()-1) break;
			int cIdx=Q.getMinIdx();
			Edge cEdge=Q.getMin();
			Q.deleteMin();
			if (used[cIdx]) continue;
			R.addEdge(cEdge);
			used[cIdx]=true;
			collect(cIdx);			
		}
		return R;
	}
	
	private void collect(int src) {
		for (Edge e: G.adj(src)) {
			int to=e.getOther(src);
			if (!used[to]) {
				if (Q.exists(to)) {
					double cVal=Q.getVal(to).getWeight();
					if (e.getWeight()<cVal) Q.decreaseKey(to, e);
				}
				else Q.insert(to, e);
			}
		}
	}
	
}
