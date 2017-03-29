package autonoleggio;

import javax.swing.JOptionPane;


import GUI.Login;

public class Autonoleggio {

	public static void main(String[] args) throws Exception {
		try{
			(new Thread(new Login())).start();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Errore, Impossibile Avviare L'Applicazione!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
		}	
	}		
}