package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * La classe ArrotondaNumero contiene metodi per gestire operazioni di arrotondamento di numeri.
 */
public class ArrotondaNumero {
	
	/**
	 *  Inizializza un nuovo oggetto ArrotondaNumero.
	 */
	public ArrotondaNumero() {
		
	}

	/**
	 * 	Arrotonda un numero decimale.
	 * 
	 * 	@param valore il numero che si desidera arrotondare.
	 * 	@param posizioni il numero desiderato di cifre dopo la virgola.
	 *	@return il valore dell'argomento arrotondato al numero di cifre indicato.
	 */
	public static double arrotonda(double valore, int posizioni) {

	    BigDecimal bd = new BigDecimal(valore);
	    bd = bd.setScale(posizioni, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	/**
	 * Restituisce una rappresentazione in stringa dell'oggetto.
	 */
	public String toString() {
		return "ArrotondaNumero [La classe ArrotondaNumero contiene metodi per gestire operazioni di arrotondamento di numeri.]";
	}
	
	
}
