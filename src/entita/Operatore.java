package entita;

import javax.swing.JOptionPane;

import db.DBConnect;
import java.sql.SQLException;

import gui.moduli.ModuloOperatore;

/* Classe per l'entità Operatore */

public class Operatore {
	private String user;
	private String pass;
	private boolean test;
	private DBConnect operatore;
	
	/* Costruttore Operatore */
	
	public Operatore(){
		test=true;
		operatore = new DBConnect();
	}
	
	/* Metodo. Aggiungi un nuovo operatore al DB. */
	
	public void aggiungi(ModuloOperatore content){
		if (check(content)){
			try{ /* Cerca nel DB un operatore con l'username inserito. */
				operatore.exequery("SELECT * FROM operatore where ID_Operatore='"+user+"'","select"); 
				/* Verifica se l'operatore che si vuole aggiungere è già presente nel DB. */
				if (operatore.rs.next()){	
					JOptionPane.showMessageDialog(null, "Errore! L'operatore con l'Username inserito è già presente!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtUsername.setText("");
					content.txtUsername.requestFocus();
				} /* Aggiunge il nuovo operatore. Inoltre resetta i campi della form per un nuovo inserimento. */
				else {	
					String valori = new String("('"+user+"','"+pass+"')");
					operatore.exequery("INSERT INTO operatore VALUES "+valori,"insert");
					JOptionPane.showMessageDialog(null , "Nuovo operatore aggiunto!");
					content.txtUsername.setText("");
					content.txtPassword.setText("");
					content.txtUsername.requestFocus();
				}
				operatore.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Operatore non aggiunto!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo. Elimina un operatore dal DB. */
	
	public void elimina(ModuloOperatore content){
		if (!user.equals("admin")){
			pass="elimina";
			if (check(content)){
				try{ 
					operatore.exequery("SELECT * FROM operatore where ID_Operatore='"+user+"'","select"); 
					/* Verifica se è presente un operatore con tale username. */
					if(!operatore.rs.next()){
						JOptionPane.showMessageDialog(null, "Errore! Non è presente un operatore con tale Username!",
								"Errore ",
								JOptionPane.ERROR_MESSAGE);
						content.txtUsername.requestFocus();
					}else {
						int scelta = JOptionPane.showConfirmDialog(
								null,
								"Si desidera eliminare l'operatore "+user+" ?",
								"Conferma eliminazione",
								JOptionPane.YES_NO_OPTION);
						if (scelta == JOptionPane.YES_OPTION){
							operatore.exequery("DELETE FROM operatore WHERE ID_Operatore='"+user+"'","delete");
							JOptionPane.showMessageDialog(null , "Operatore eliminato!");
							content.txtUsername.setText("");
							content.txtUsername.requestFocus();
						}
					}
					operatore.con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Errore! Operatore non eliminato!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
				}
		}}else{
			content.txtUsername.setText("");
			content.txtUsername.requestFocus();
			JOptionPane.showMessageDialog(null, "Errore! Impossibile eliminare l'Amministratore!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	/* Metodo. Verifica la correttezza dei dati inseriti. */
	
	private boolean check(ModuloOperatore content){
		boolean check=true;
		/* Verifica se sono stati inseriti tutti i campi necessari. */
		if (user.equals("") || pass.equals("")){		
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Non lasciare campi vuoti!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
		}else if (user.length()>25){
			content.txtUsername.setText("");
			content.txtUsername.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! L'Username deve avere meno di 25 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if(pass.length()>25 || pass.length()<6){
			content.txtPassword.setText("");
			content.txtPassword.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La password deve avere tra 6 e 25 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		if (check==false) test=check; 
			else test=true;
		return test;
	}
	
	/* METODI USATI DALLA GUI PER LA GESTIONE DEGLI OPERATORI (--> vedi classe ModuloOp <--) */
	
	/* Metodo. Assegna i valori all'operatore. */
	
	public void setValori(ModuloOperatore content){
		user = content.txtUsername.getText().trim();
		pass = content.txtPassword.getText().trim();
	}
	
	/* Metodo . Assegna l'username all'operatore da eliminare. */
	
	public void setUsername(ModuloOperatore content){
		user = content.txtUsername.getText().trim();
	}
}
