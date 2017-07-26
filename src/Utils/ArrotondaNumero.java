package Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
public class ArrotondaNumero {

	public static double arrotonda(double valore, int posizioni) {

	    BigDecimal bd = new BigDecimal(valore);
	    bd = bd.setScale(posizioni, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
