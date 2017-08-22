package utils;

/**
 * La classe IsNumeric contiene metodi per controllare se una stringa � numerica.
 */
public class IsNumeric {
	
	/**
	 * Inizializza un nuovo oggetto IsNumeric.
	 */
	public IsNumeric() {
		
	}

	/**
	 * Verifica se la stringa passata come argomento � numerica.
	 * 
	 * @param string la stringa da analizzare.
	 * @return true se la stringa � numerica; false altrimenti.
	 */
	public static boolean isNumeric(String string) {
	    
		return string!= null && string.matches("\\d*\\.?\\d*");
	}
	
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * 
	 * @return una stringa rappresentante l'oggetto.
	 */
	public String toString() {
		return "IsNumeric [La classe IsNumeric contiene metodi per controllare se una stringa � numerica.]";
	}
}
