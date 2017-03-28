package Entità;

import javax.swing.JOptionPane;

import GUI.Admin.ModuloOp;
import db.DBConnect;

/* Classe per l'entità Operatore */

public class Operatore {
	private String user;
	private String pass;
	private boolean test=true;
	private DBConnect operatore = new DBConnect();
	
	/* Costruttore Operatore */
	
	public Operatore(){
		
	}
	
	/* Metodo per Aggiungere il nuovo Operatore al DB. */
	
	public void aggiungi(ModuloOp content){
		if (check(content)){
			try{
				operatore.exequery("SELECT * FROM operatore where ID_Operatore='"+user+"'","select"); /* Cerca se esiste già l'operatore nel DB */
				if (operatore.rs.next()){	/* Verifica se esiste già l'operatore nel DB */
					JOptionPane.showMessageDialog(null, "Errore, l'Operatore con l'Username inserito è già presente!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtUsername.setText("");
					content.txtUsername.requestFocus();
				}else {	/* Aggiunge l'operatore e resetta il form per poter inserire un nuovo operatore */
					String valori = new String("('"+user+"','"+pass+"')");
					operatore.exequery("INSERT INTO operatore VALUES "+valori,"insert");
					JOptionPane.showMessageDialog(null , "Nuovo Operatore Aggiunto!");
					content.txtUsername.setText("");
					content.txtPassword.setText("");
					content.txtUsername.requestFocus();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Operatore non Aggiunto!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo usato per eliminare l'operatore dal DB. */
	
	public void elimina(ModuloOp content){
		pass="elimina";
		if (check(content)){
			try{
				operatore.exequery("SELECT * FROM operatore where ID_Operatore='"+user+"'","select"); /* Controlla se l'operatore è presente e può essere eliminato. */
				if(operatore.rs.next()){
					operatore.exequery("DELETE FROM operatore WHERE ID_Operatore='"+user+"'","delete");
					JOptionPane.showMessageDialog(null , "Operatore Eliminato!");
					content.txtUsername.setText("");
					content.txtUsername.requestFocus();
				} else{
					JOptionPane.showMessageDialog(null, "Errore, l'Operatore con l'Username inserito non è presente nel DB!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtUsername.setText("");
					content.txtUsername.requestFocus();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Operatore non Eliminato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo per assegnare i valori all'Operatore. */
	
	public void setValori(ModuloOp content){
		user = content.txtUsername.getText().trim();
		pass = content.txtPassword.getText().trim();
	}
	
	/* Metodo per verificare la correttezza dei dati inseriti. */
	
	private boolean check(ModuloOp content){
		boolean check=true;
		if (user.equals("") || pass.equals("")){		/* Verifica se sono stati inseriti tutti i campi necessari */
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, Non lasciare vuoti i campi!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
		}else if (user.length()>25){
			content.txtUsername.setText("");
			content.txtUsername.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, l'Username deve avere meno di 25 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if(pass.length()>25 || pass.length()<6){
			content.txtPassword.setText("");
			content.txtPassword.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la password deve avere tra 6 e 25 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		if (check==false) test=check; 
			else test=true;
		return test;
	}
}
