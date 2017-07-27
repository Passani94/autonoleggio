package Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ArrotondaNumero {

	/* Metodo. Arrotonda un numero decimale. */
	public static double arrotonda(double valore, int posizioni) {

	    BigDecimal bd = new BigDecimal(valore);
	    
	    /*Il paramentro 'posizioni' indica il numero desiderato di cifre dopo la virgola .*/
	    bd = bd.setScale(posizioni, RoundingMode.HALF_UP);
	    
	    return bd.doubleValue();
	}
}
