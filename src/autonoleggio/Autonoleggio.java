package autonoleggio;

import javax.swing.JOptionPane;

/**
 * La classe Autonoleggio manda in esecuzione l'applicazione.
 */
public class Autonoleggio {

	/**
	 * Avvia l'applicazione.
	 * 
	 * @param args un vettore di stringhe ricevute da righe di comando
	 */
	public static void main(String[] args) {
		try {
			(new Thread(new Login())).start();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Errore! Impossibile avviare l'applicazione!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
		}	
	}
	
/* OVERRIDING METODI toString() */
	
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * 
	 * @return una stringa rappresentante l'oggetto.
	 */
	public String toString() {
		return "Autonoleggio [La classe Autonoleggio manda in esecuzione l'applicazione.]";
	}	
		
	
}