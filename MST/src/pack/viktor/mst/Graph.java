package pack.viktor.mst;

import java.util.LinkedList;

import pack.princeton.stdio.StdIn;

/** 
* Klasa koja predstavlja tip tezinskog grafa implementiran nizom lista povezanosti.
* Liste sadrze reference na konkretne objekte tipa Edge.
* @author Viktor Slavkovic 
*/
public class Graph {
	private int _numV;
	private int _numE;
	private LinkedList<Edge>[] _adj;
	
	/** 
	* Konstruktor koji vrsi standardne inicijalizacije;
	* @param	V	Broj cvorova grafa;
	* @throws 	IllegalArgumentException	Kada je parametar broja cvororva negativan.
	*/
	@SuppressWarnings("unchecked")
	public Graph(int V) {
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this._numV = V;
		this._numE = 0;
		_adj = (LinkedList<Edge>[]) new LinkedList[V];
		for (int v = 0; v < V; v++) {
			_adj[v] = new LinkedList<Edge>();
		}
    }
	
	/** 
	* Metod za pristup broju cvorova;
	* @return	Vraca broj cvorova.
	*/
	public int V() {
        return _numV;
    }
	
	/** 
	* Metod za pristup brojacu grana;
	* @return	Vraca broj grana.
	*/
	public int E() {
        return _numE;
    }
	
	/** 
	* Metod za dodavanje grane u graf;
	* @param	e		Objekat tipa Edge koji se dodaje;
	* @throws	IndexOutOfBoundsException		Ako indeksi krajeva grane nisu validni;
	*/
	public void addEdge(Edge e) {
		int v = e.getEither();
		int w = e.getOther(v);
		if (v < 0 || v >= V())
			throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V() - 1));
		if (w < 0 || w >= V())
			throw new IndexOutOfBoundsException("vertex " + w + " is not between 0 and " + (V() - 1));
		_adj[v].add(e);
		_adj[w].add(e);
		_numE++;
	}
	
	/** 
	* Metod za dobijanje iterabilnog skupa grana koje izlaze iz odredjenog cvora;
	* @param	v		Indeks cvora;
	* @throws	IndexOutOfBoundsException		Ako indeks cvora nije validan;
	* @return	Vraca Iterable
	*/
	public Iterable<Edge> adj(int v) {
		if (v < 0 || v >= V())
			throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V() - 1));
		return _adj[v];
	}
	
	/** 
	* Metod za dobijanje iterabilnog skupa svih grana grafa;
	* @return	Vraca Iterable
	*/
	public Iterable<Edge> edges() {
		LinkedList<Edge> list = new LinkedList<Edge>();
		for (int v = 0; v < V(); v++) {
		    int selfLoops = 0;
		    for (Edge e : adj(v)) {
		        if (e.getOther(v) > v) {
		            list.add(e);
		        }
		        // only add one copy of each self loop (self loops will be consecutive)
	            else if (e.getOther(v) == v) {
	                if (selfLoops % 2 == 0) list.add(e);
	                selfLoops++;
	            }
	        }
	    }
	    return list;
	}
	
	/** 
	* Metod za ispis grafa u string;
	* @return	Vraca trazeni String
	*/
	public String toString() {
		String NEWLINE = System.getProperty("line.separator");
	    StringBuilder s = new StringBuilder();
	    s.append(V() + " " + E() + NEWLINE);
	    for (int v = 0; v < V(); v++) {
	    	s.append(v + ": ");
	        for (Edge e : _adj[v]) {
	        	s.append(e.getOther(v) + "  ");
	        }
	        s.append(NEWLINE);
	    }
	    return s.toString();
	 }
	
	/** 
	* Metod za dodavanje grana ucitanih sa standardnog ulaza u formatu "br.grana grana1(cvor1 cvor2 tezina) grana2(cvor1 cvor2 tezina) ...";
	* Upotreblejn je omotac za standardni ulaz i izlas preuzet od prof. Robert Sedgewick-a sa Princeton univerziteta.
	*/
	public void addEdgesFromStdIn() {
		int n = StdIn.readInt();
		for (int i=0; i<n; i++) {
			Edge e = new Edge();
			e.readFromStdIn();
			addEdge(e);
		}
	}
	 
}
