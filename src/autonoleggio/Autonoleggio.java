package autonoleggio;

import javax.swing.JOptionPane;

public class Autonoleggio {

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
}