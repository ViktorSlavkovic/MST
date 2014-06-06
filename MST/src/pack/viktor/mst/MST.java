package pack.viktor.mst;

/** 
* Interfejs klasa koje implementiraju algoritme za nalazenje minimalnog stabla razapinjanja (MST)
* @author Viktor Slavkovic 
*/
public interface MST {
	/** 
	* Apstraktni metod koji generise trazeno stablo;
	* @return	Vraca MST kao Graph;
	*/
	public abstract Graph generate();
}
