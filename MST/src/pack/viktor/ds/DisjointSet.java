package pack.viktor.ds;

/** 
* Interfejs klasa koje implementiraju strukturu disjunktnih skupova (Disjoint-Set)
* @author Viktor Slavkovic 
*/
public interface DisjointSet {
	
	/** 
	* Metod koji implementira operaciju unije;
	* Kao sto je receno u radu, ova operacija stvara vezu izmedju dva elementa skupa.
	* @param	idx1	Indeks prvog clana koji se povezuje;
	* @param	idx2	Indeks drugog clana koji se povezuje;
	*/
	public void union(int idx1, int idx2);
	
	/** 
	* Metod koji implementira operaciju nalazenja indeksa komponente povezanosti;
	* @param	idx		Indeks clana cija se komponenta povezanosti trazi;
	* @return	Vraca indeks komponente povezanosti.
	*/
	public int find(int idx);
	
	/** 
	* Metod koji implementira operaciju proveravanja povezanosti dva elementa skupa;
	* Interno proverava da li je find(idx1) isto sto i find(idx2).
	* @param	idx1	Indeks prvog clana;
	* @param	idx2	Indeks drugog clana;
	* @return	Vraca ocekivanu logicku vrednost.
	*/
	public boolean connected(int idx1, int idx2);
	
	/** 
	* Metod za pristup brojacu komponenata povezanosti;
	* @return	Vraca broj komponenata povezanosti.
	*/
	public int numComponents();
}
