package pack.viktor.mst;

import pack.princeton.stdio.StdIn;

/** 
* Klasa koja predstavlja tip grane tezinskog grafa.
* @author Viktor Slavkovic 
*/
public class Edge implements Comparable<Edge>{
	private int _from;
	private int _to;
	private double _weight;
	
	/** 
	* Konstruktor koji inicijalizuje granu kao sopstvenu petlju duzine 1 na cvoru 0;
	*/
	public Edge() {
        _from = 0;
        _to = 0;
        _weight = 1;
    }
	
	/** 
	* Konstruktor koji vrsi standardne inicijalizacije;
	* @param	from	Indeks cvora na jednom kraju grane;
	* @param	to		Indeks cvora na drgom kraju grane;
	* @param	weight	Tezina grane;
	* @throws 	IllegalArgumentException	Kada tezina nema razumnu vrednost;
	* @throws 	IndexOutOfBoundsException	Kada su indeksi cvorova negativni;
	*/
	public Edge(int from, int to, double weight) {
		if (from < 0)
			throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
		if (to < 0)
			throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
		if (Double.isNaN(weight))
			throw new IllegalArgumentException("Weight is NaN");
		_from = from;
		_to = to;
		_weight = weight;
	}
	
	/** 
	* Metod za pristup tezini grane;
	* @return	Vraca double tezinu.
	*/
	public double getWeight() {
        return _weight;
    }
	
	/** 
	* Metod za pristup cvoru na jendom kraju grane;
	* @return	Vraca ineks cvora.
	*/
	public int getEither() {
		return _from;		
	}
	
	/** 
	* Metod za pristup cvoru na drugom kraju grane;
	* @param	either	Ne taj, nego onaj drugi kraj (cvor);
	* @throws 	IllegalArgumentException	Kada je arguemnt nepostojeci indeks;
	* @return	Vraca ineks cvora.
	*/
	public int getOther(int either) {
		if (either == _from)
			return _to;
		else if (either == _to)
			return _from;
		else
			throw new IllegalArgumentException("Illegal endpoint");
	}
	
	/** 
	* Metod za poredjenje po tezini nasledjen iz Comparable;
	* @param	that	Grana sa kojom se poredi;
	* @return	negativno - grana koja se poredi je manje tezine; poyitivno - Grana koja se poredi je vece tezine; 0 - Grane su iste tezine sa tacnostu 0.001.
	*/
	@Override
	public int compareTo(Edge that) {
		if (Math.abs(this.getWeight() - that.getWeight()) < 0.001)
			return 0;
		if (this.getWeight() < that.getWeight())
			return -1;
		else
			return +1;
	}
	
	/** 
	* Metod za ucitavanje sa standardnog ulaza o formatu "kraj1 kraj2 tezina";
	* Upotreblejn je omotac za standardni ulaz i izlas preuzet od prof. Robert Sedgewick-a sa Princeton univerziteta.
	*/
	public void readFromStdIn() {
		_from = StdIn.readInt();
		_to = StdIn.readInt();
		_weight = StdIn.readDouble();
	}
}
