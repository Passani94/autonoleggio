package GUI;

import javax.swing.*; /*Trovare un modo per importare una volta sola*/

public class Login{
	Frame log = new Frame(); 
	
	public void login(){			/*Metodo per creare un frame per il login, richiamato all'avvio del programma e dopo un logout*/
		JFrame frame = log.newframe("Autonoleggio - Login");
		log.dimensiona(frame);
	}
}
