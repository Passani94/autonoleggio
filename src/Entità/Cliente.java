package Entità;

import javax.swing.JOptionPane;

import db.DBConnect;
import GUI.Admin.ModuloCl;

/* Classe per l'entità Cliente */

public class Cliente{
	
	private DBConnect cliente;
	private boolean test;
	
	public String Tipologia;
	public String RS;
	public String CAP;
	public String Citta;
	public String Via;
	public String Numero;
	public String CF_PIVA;
	public String Email;
	public String Telefono;
	public String ClienteCerca;
	private static final String EMAILPATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String CFPATTERN = "[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";
	
	/* Costruttore Cliente */
	
	public Cliente(){
		test=true;
		cliente = new DBConnect();
	}
	
	/* Metodo. Aggiunge un nuovo cliente al DB*/
	
	public void aggiungi(ModuloCl content){
		if (check(content)){
			try{
				/* Cerca nel DB un cliente con il CF (o la Partita IVA) inseriti.*/
				cliente.exequery("SELECT * FROM cliente where CF_PIVA='"+CF_PIVA+"'","select");
				/*Verifica se il cliente che si vuole aggiungere è già presente nel DB.*/
				if (cliente.rs.next()){
					JOptionPane.showMessageDialog(null, "Errore! Esiste già un cliente con tale CF/Partita IVA!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtCF_PIVA.setText("");
					content.txtCF_PIVA.requestFocus();
				}/* Aggiunge il nuovo cliente. Inoltre resetta i campi della form per un nuovo inserimento. */
				else{	
					String valori="('"+CF_PIVA+"','"+Tipologia+"','"+RS+"',"+CAP+",'"+Citta+"','"+Via+"',"+Numero+","+Telefono+",'"+Email+"')";
					cliente.exequery("INSERT INTO cliente VALUES "+valori,"insert");
					JOptionPane.showMessageDialog(null , "Nuovo cliente aggiunto!");
					content.txtRS.setText("");
					content.txtCAP.setText("");
					content.txtCitta.setText("");
					content.txtVia.setText("");
					content.txtEmail.setText("");
					content.txtNumero.setText("");
					content.txtCF_PIVA.setText("");
					content.txtTelefono.setText("");
					content.txtCF_PIVA.requestFocus();
				}
			} catch (Exception ex){
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Cliente non aggiunto!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo. Elimina un cliente dal DB. */
	
	public void elimina(ModuloCl content){
		if (checkelimina(content)){
			try{
				cliente.exequery("SELECT * FROM cliente where CF_PIVA='"+CF_PIVA+"'","select");
				/*Verifica se è presente un cliente con tale CF/Partita IVA.*/
				if(cliente.rs.next()){
					cliente.exequery("DELETE FROM cliente WHERE CF_PIVA='"+CF_PIVA+"'","delete");
					JOptionPane.showMessageDialog(null , "Cliente Eliminato!");
					content.txtCF_PIVA.setText("");
					content.txtCF_PIVA.requestFocus();
				} else{
					JOptionPane.showMessageDialog(null, "Errore! Non è presente un cliente con tale CF/Partita IVA!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtCF_PIVA.setText("");
					content.txtCF_PIVA.requestFocus();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Cliente non eliminato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo. Cerca un cliente nel DB. */
	
	public void cerca(ModuloCl content){
		
		String item;
		if (checkcerca(content)){
			try{
				cliente.exequery("SELECT * FROM cliente where CF_PIVA='"+ClienteCerca+"'","select");
				if (cliente.rs.next()){
					content.txtClienteCerca.setEditable(false);
					content.txtRS.setText(cliente.rs.getString(3));
					content.txtCAP.setText(cliente.rs.getString(4));
					content.txtCitta.setText(cliente.rs.getString(5));
					content.txtVia.setText(cliente.rs.getString(6));
					content.txtEmail.setText(cliente.rs.getString(9));
					content.txtNumero.setText(cliente.rs.getString(7));
					content.txtCF_PIVA.setText(cliente.rs.getString(1));
					content.txtTelefono.setText(cliente.rs.getString(8));
					content.comboBoxTipologia.setEditable(true);
					content.txtRS.setEditable(true);
					content.txtCAP.setEditable(true);
					content.txtCitta.setEditable(true);
					content.txtVia.setEditable(true);
					content.txtNumero.setEditable(true);
					content.txtEmail.setEditable(true);
					content.txtTelefono.setEditable(true);
					
					for (int i=1; i<4; i++) {						 
			            item=content.comboBoxTipologia.getItemAt(i);			 
			            if (item.equals(cliente.rs.getString(2))) {
			              content.comboBoxTipologia.setSelectedIndex(i);			 
			            }
			          }	
				}else{
					JOptionPane.showMessageDialog(null, "Errore! Non è presente un cliente con tale CF/Partita IVA!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtClienteCerca.setText("");
					content.txtClienteCerca.requestFocus();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Cliente non trovato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo. Modifica un cliente nel DB. */
	
	public void modifica(ModuloCl content){
	
		if (check(content)){
			try{
				String valori = "Tipologia='"+Tipologia+"',Ragione_Sociale='"+RS+"',CAP="+CAP+",Citta='"+Citta+"',Via='"+Via+"',Numero="+Numero+",Telefono="+Telefono+",Email='"+Email+"'";
				cliente.exequery("UPDATE cliente SET "+valori+" WHERE CF_PIVA='"+CF_PIVA+"'","update");
				JOptionPane.showMessageDialog(null , "Cliente Modificato!");
				content.txtClienteCerca.setText("");
				content.txtClienteCerca.requestFocus();
				content.txtClienteCerca.setEditable(true);
				content.comboBoxTipologia.setEditable(false);
				content.txtRS.setEditable(false);
				content.txtCAP.setEditable(false);
				content.txtCitta.setEditable(false);
				content.txtVia.setEditable(false);
				content.txtNumero.setEditable(false);
				content.txtCF_PIVA.setEditable(false);
				content.txtEmail.setEditable(false);
				content.txtTelefono.setEditable(false);
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Cliente non modificato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo. Verifica che i dati inseriti siano corretti. */
	
	private boolean check(ModuloCl content){
		boolean check=true;
		/* Verifica se sono stati inseriti tutti i campi necessari.*/
		if (CF_PIVA.equals("") || Tipologia.equals("") || RS.equals("")){	
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci tutti i campi indicati da un asterisco!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}else if (CF_PIVA.length()==16 && !CF_PIVA.matches(CFPATTERN)){
			content.txtCF_PIVA.setText("");
			content.txtCF_PIVA.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale inserito non è valido!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if((CF_PIVA.length()==11 && !CF_PIVA.matches("\\d{11}"))){
			content.txtCF_PIVA.setText("");
			content.txtCF_PIVA.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La Partita IVA inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if(CF_PIVA.length()<11 || (CF_PIVA.length()>11 && CF_PIVA.length()<16) || CF_PIVA.length()>16){
			content.txtCF_PIVA.setText("");
			content.txtCF_PIVA.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale deve avere 16 caratteri e la Partita IVA 11 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Tipologia.length()>15){
			content.comboBoxTipologia.setSelectedIndex(-1);;
			content.comboBoxTipologia.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Tipologia deve avere meno di 15 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (RS.length()>30){
			content.txtRS.setText("");
			content.txtRS.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Ragione Sociale deve avere meno di 30 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if ((!isNumeric(CAP) || CAP.length()>5 || CAP.length()<5) && !CAP.equals("")){
			content.txtCAP.setText("");
			content.txtCAP.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il CAP deve essere composto da 5 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Citta.length()>20){
			content.txtCitta.setText("");
			content.txtCitta.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Città deve avere meno di 20 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Via.length()>20){
			content.txtVia.setText("");
			content.txtVia.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Via deve avere meno di 20 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if ((!isNumeric(Numero) || Numero.length()>3) && !Numero.equals("")){
			content.txtNumero.setText("");
			content.txtNumero.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Numero deve essere composto al massimo da 3 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if ((Email.length()>25 || !Email.matches(EMAILPATTERN)) && !Email.equals("")){
			content.txtEmail.setText("");
			content.txtEmail.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! L'Email inserita non è valida o è troppo lunga!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if ((Telefono.length()>10 || Telefono.length()<10 || !isNumeric(Telefono)) && !Telefono.equals("")){
			content.txtTelefono.setText("");
			content.txtTelefono.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Telefono deve essere composto da 10 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		if (check==false) test=check; 
			else {
				if(CAP.equals("")) CAP="NULL";
				if(Citta.equals("")) Citta="NULL";
				if(Via.equals("")) Via="NULL";
				if(Numero.equals("")) Numero="NULL";
				if(Telefono.equals("")) Telefono="NULL";
				if(Email.equals("")) Email="NULL";
				test=true;}
		return test;
	}
	
/* Metodo. Verifica la correttezza del CF (o della Partita IVA) del cliente da eliminare. */
	
	private boolean checkelimina(ModuloCl content){
		boolean check=true;
		/* Verifica se sono stati inseriti tutti i campi necessari */
		if (CF_PIVA.equals("")){		
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, Inserisci il cliente da eliminare!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}else if (CF_PIVA.length()==16 && !CF_PIVA.matches(CFPATTERN)){
			content.txtCF_PIVA.setText("");
			content.txtCF_PIVA.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale inserito non è valido!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if((CF_PIVA.length()==11 && !CF_PIVA.matches("\\d{11}"))){
			content.txtCF_PIVA.setText("");
			content.txtCF_PIVA.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La Partita IVA inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if(CF_PIVA.length()<11 || (CF_PIVA.length()>11 && CF_PIVA.length()<16) || CF_PIVA.length()>16){
			content.txtCF_PIVA.setText("");
			content.txtCF_PIVA.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale deve avere 16 caratteri e la Partita IVA 11 cifre!!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		if (check==false) test=check; 
		else test=true;
		return test;
	}
	
	/* Metodo. Verifica la correttezza del CF (o della Partita IVA) del cliente da cercare. */
	
	private boolean checkcerca(ModuloCl content){
		boolean check=true;
		if (ClienteCerca.length()==16 && !ClienteCerca.matches(CFPATTERN)){
			content.txtClienteCerca.setText("");
			content.txtClienteCerca.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale inserito non è valido!",
				"Errore ",
		    	JOptionPane.ERROR_MESSAGE);
		} else if((ClienteCerca.length()==11 && !ClienteCerca.matches("\\d{11}"))){
			content.txtClienteCerca.setText("");
			content.txtClienteCerca.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La Partita IVA inserita non è valida!",
				"Errore ",
		    	JOptionPane.ERROR_MESSAGE);
		} else if(ClienteCerca.length()<11 || (ClienteCerca.length()>11 && ClienteCerca.length()<16) || ClienteCerca.length()>16){
			content.txtClienteCerca.setText("");
			content.txtClienteCerca.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale deve avere 16 caratteri e la Partita IVA 11 cifre!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}
		if (check==false) test=check; 
		else test=true;
		return test;
	}
	
	/* Metodo. Verifica se una stringa è numerica. */
	
	private static boolean isNumeric(String string) {
	    try {
	        Long.parseLong(string);
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}

/***** METODI USATI DALLA GUI PER LA GESTIONE DEI CLIENTI ( --> vedi classe ModuloCl <-- ) *****/

/* Metodo. Assegna i valori al cliente. */

	public void setValori(ModuloCl content){
		CF_PIVA = content.txtCF_PIVA.getText().trim();
		Tipologia = content.comboBoxTipologia.getSelectedItem().toString();
		RS = content.txtRS.getText().trim();
		CAP = content.txtCAP.getText().trim();
		Citta = content.txtCitta.getText().trim();
		Via = content.txtVia.getText().trim();
		Numero = content.txtNumero.getText().trim();
		Email = content.txtEmail.getText().trim();
		Telefono = content.txtTelefono.getText().trim();
	}
/* Metodo. Assegna solo la chiave (CF/Partita_IVA) del cliente. */

	public void setID(ModuloCl content){
		CF_PIVA = content.txtCF_PIVA.getText().trim();
	}

/* Metodo. Assegna solo la chiave (CF/Partita_IVA) del cliente da cercare. */

	public void setIDcerca(ModuloCl content){
		ClienteCerca = content.txtClienteCerca.getText().trim();
	}
}