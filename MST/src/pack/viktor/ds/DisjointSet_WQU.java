package pack.viktor.ds;

/** 
* Klasa koja implementira interfejs DisjointSet algoritmom Weighted Quick-Union
* @author Viktor Slavkovic 
*/
public class DisjointSet_WQU implements DisjointSet {

	private int id[];
	private int size[];
	private int num;
	
	/** 
	* Konstruktor koji vrsi standardne inicijalizacije;
	* @param	n	Broj elemenata skupa;
	* @throws 	IllegalArgumentException	Kada je parametar broja elemenata neggativan.
	*/
	public DisjointSet_WQU(int n) {
		if (n < 0)
			throw new IllegalArgumentException("Number of elements must be nonnegative.");
		num = n;
		id = new int[n];
		size = new int[n];
		for (int i = 0; i < n; i++) {
			id[i] = i;
			size[i] = 1;
		}
	}
	
	/** 
	* Metod za pristup brojacu komponenata povezanosti;
	* @return	Vraca broj komponenata povezanosti.
	*/
	@Override
	public int numComponents() {
		return num;
	}
	
	/** 
	* Metod koji implementira operaciju unije;
	* Kao sto je receno u radu, ova operacija stvara vezu izmedju dva elementa skupa.
	* @param	idx1	Indeks prvog clana koji se povezuje;
	* @param	idx2	Indeks drugog clana koji se povezuje;
	* @throws	IndexOutOfBoundsException		Ako prosledjeni indeksi nisu validni;
	*/
	@Override
	public void union(int idx1, int idx2) {
		if (idx1 < 0 || idx1 >= id.length)
			throw new IndexOutOfBoundsException();
		if (idx2 < 0 || idx2 >= id.length)
			throw new IndexOutOfBoundsException();
		
		int r1 = find(idx1);
		int r2 = find(idx2);
		if (r1 != r2) {
			if (size[r1] < size[r2]) {
				id[r1] = r2;
				size[r2] += size[r1];
			} else {
				id[r2] = r1;
				size[r1] += size[r2];
			}
			num--;
		}
	}
	
	/** 
	* Metod koji implementira operaciju nalazenja indeksa komponente povezanosti;
	* @param	idx		Indeks clana cija se komponenta povezanosti trazi;
	* @return	Vraca indeks komponente povezanosti.
	* @throws	IndexOutOfBoundsException		Ako prosledjeni indeks nije validan;
	*/	
	@Override
	public int find(int idx) {
		if (idx < 0 || idx >= id.length)
			throw new IndexOutOfBoundsException();
		while (idx != id[idx])
			idx = id[idx];
		return idx;
	}
	
	/** 
	* Metod koji implementira operaciju proveravanja povezanosti dva elementa skupa;
	* Interno proverava da li je find(idx1) isto sto i find(idx2).
	* @param	idx1	Indeks prvog clana;
	* @param	idx2	Indeks drugog clana;
	* @return	Vraca ocekivanu logicku vrednost.
	* @throws	IndexOutOfBoundsException		Ako prosledjeni indeksi nisu validni;
	*/
	@Override
	public boolean connected(int idx1, int idx2) {
		return find(idx1) == find(idx2);
	}

}