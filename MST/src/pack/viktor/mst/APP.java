package pack.viktor.mst;

import pack.princeton.stdio.StdIn;
import pack.princeton.stdio.StdOut;

/** 
* Klasa koja implementira test aplikaciju za sva 3 MST algoritma
* @author Viktor Slavkovic 
*/
public class APP {
	/** 
	* Main metod
	* Ucitava graf sa standardnog ulaza u formatu
	* 		br.cvorova
	* 		br.grana
	* 		grana_1.cvor1 grana_1.cvor2 grana_1.tezina
	* 		...
	* 		grana_n.cvor1 grana_n.cvor2 grana_n.tezina
	* Stampa 3 dobijena MST (Boruvka, Kruskal, Prim)
	* Upotreblejn je omotac za standardni ulaz i izlas preuzet od prof. Robert Sedgewick-a sa Princeton univerziteta. 
        * @param args
	*/
	public static void main(String args[]) {
		
		int n = StdIn.readInt();
		
		Graph G = new Graph(n);
		G.addEdgesFromStdIn();
		StdOut.println("GRAF:");
		StdOut.print(G.toString());
		
		StdOut.println("MST Boruvka:");
		MST M1=new MST_Boruvka(G);
		StdOut.println(M1.generate().toString());
		
		StdOut.println("MST Kruskal:");
		MST M2=new MST_Kruskal(G);
		StdOut.println(M2.generate().toString());
		
		StdOut.println("MST Prim:");
		MST M3=new MST_Prim(G);
		StdOut.println(M3.generate().toString());
	}
}
