package Entità;

import javax.swing.JOptionPane;

import GUI.Admin.ModuloCl;
import db.DBConnect;

/* Classe per l'entità Cliente */

public class Cliente {
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
	private boolean test=true;
	private static final String EMAILPATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String CFPATTERN = "[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";
	private DBConnect cliente = new DBConnect();
	
	/* Costruttore Cliente */
	
	public Cliente(){
		
	}
	
	/* Metodo per Aggiungere il nuovo cliente al DB. */
	
	public void aggiungi(ModuloCl ct){
		if (check(ct)){
			try{
				cliente.exequery("SELECT * FROM cliente where CF_PIVA='"+CF_PIVA+"'","select"); /* Cerca se esiste già il cliente nel DB */
				if (cliente.rs.next()){	/* Verifica se esiste già il cliente nel DB */
					JOptionPane.showMessageDialog(null, "Errore, il Cliente con il/la CF/P_IVA inserita è già presente!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					ct.txtCF_PIVA.setText("");
					ct.txtCF_PIVA.requestFocus();
				}else if (CF_PIVA.equals("") || Tipologia.equals("") || RS.equals("")){		/* Verifica se sono stati inseriti tutti i campi necessari */
					JOptionPane.showMessageDialog(null, "Errore, Inserisci tutti i Campi con l'Asterisco!",
						"Errore ",
							JOptionPane.ERROR_MESSAGE);
				} else {	/* Aggiunge il cliente e resetta il form per poter inserire un nuovo cliente */
					String valori = new String("('"+CF_PIVA+"','"+Tipologia+"','"+RS+"','"+CAP+"','"+Citta+"','"+Via+"','"+Numero+"','"+Telefono+"','"+Email+"')");
					cliente.exequery("INSERT INTO cliente VALUES "+valori,"insert");
					JOptionPane.showMessageDialog(null , "Nuovo Cliente Aggiunto!");
					ct.txtTipologia.setText("");
					ct.txtRS.setText("");
					ct.txtCAP.setText("");
					ct.txtCitta.setText("");
					ct.txtVia.setText("");
					ct.txtEmail.setText("");
					ct.txtNumero.setText("");
					ct.txtCF_PIVA.setText("");
					ct.txtTelefono.setText("");
					ct.txtCF_PIVA.requestFocus();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Cliente non Aggiunto!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo usato per eliminare il cliente dal DB. */
	
	public void elimina(ModuloCl ct){
		if (check(ct)){
			try{
				cliente.exequery("SELECT * FROM cliente where CF_PIVA='"+CF_PIVA+"'","select"); /* Controlla se il cliente è presente e può essere eliminato. */
				if(cliente.rs.next()){
					cliente.exequery("DELETE FROM cliente WHERE CF_PIVA='"+CF_PIVA+"'","delete");
					JOptionPane.showMessageDialog(null , "Cliente Eliminato!");
					ct.txtCF_PIVA.setText("");
					ct.txtCF_PIVA.requestFocus();
				} else{
					JOptionPane.showMessageDialog(null, "Errore, Il Cliente con il/la CF/PIVA non è presente nel DB!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					ct.txtCF_PIVA.setText("");
					ct.txtCF_PIVA.requestFocus();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Cliente non Eliminato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo per cercare un cliente nel DB. */
	
	public void cerca(ModuloCl content){
		if (checkcerca(content)){
			try{
				cliente.exequery("SELECT * FROM cliente where CF_PIVA='"+ClienteCerca+"'","select");
				if (cliente.rs.next()){
					content.txtClienteCerca.setEditable(false);
					content.txtTipologia.setText(cliente.rs.getString(2));
					content.txtRS.setText(cliente.rs.getString(3));
					content.txtCAP.setText(cliente.rs.getString(4));
					content.txtCitta.setText(cliente.rs.getString(5));
					content.txtVia.setText(cliente.rs.getString(6));
					content.txtEmail.setText(cliente.rs.getString(9));
					content.txtNumero.setText(cliente.rs.getString(7));
					content.txtCF_PIVA.setText(cliente.rs.getString(1));
					content.txtTelefono.setText(cliente.rs.getString(8));
					content.txtCF_PIVA.requestFocus();
					content.txtTipologia.setEditable(true);
					content.txtRS.setEditable(true);
					content.txtCAP.setEditable(true);
					content.txtCitta.setEditable(true);
					content.txtVia.setEditable(true);
					content.txtNumero.setEditable(true);
					content.txtEmail.setEditable(true);
					content.txtTelefono.setEditable(true);
				}else{
					JOptionPane.showMessageDialog(null, "Errore, Il Cliente con il/la CF/PIVA non è presente nel DB!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtClienteCerca.setText("");
					content.txtClienteCerca.requestFocus();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Cliente non Trovato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo per modificare un cliente nel DB. */
	
	public void modifica(ModuloCl content){
		if (check(content)){
			try{
				String valori = "Tipologia='"+Tipologia+"',Ragione_Sociale='"+RS+"',CAP='"+CAP+"',Citta='"+Citta+"',Via='"+Via+"',Numero='"+Numero+"',Telefono='"+Telefono+"',Email='"+Email+"'";
				cliente.exequery("UPDATE cliente SET "+valori+" WHERE CF_PIVA='"+CF_PIVA+"'","update");
				JOptionPane.showMessageDialog(null , "Cliente Modificato!");
				content.txtClienteCerca.setText("");
				content.txtClienteCerca.requestFocus();
				content.txtClienteCerca.setEditable(true);
				content.txtTipologia.setEditable(false);
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
				JOptionPane.showMessageDialog(null, "Errore, Cliente non Modificato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo per assegnare i valori al Cliente. */
	
	public void setValori(ModuloCl content){
		Tipologia = content.txtTipologia.getText().trim();
		RS = content.txtRS.getText().trim();
		CAP = content.txtCAP.getText().trim();
		Citta = content.txtCitta.getText().trim();
		Via = content.txtVia.getText().trim();
		Numero = content.txtNumero.getText().trim();
		CF_PIVA = content.txtCF_PIVA.getText().trim();
		Email = content.txtEmail.getText().trim();
		Telefono = content.txtTelefono.getText().trim();
	}
	
	/* Metodo per assegnare solo la chiave al Cliente. */
	
	public void setID(ModuloCl content){
		CF_PIVA = content.txtCF_PIVA.getText().trim();
	}
	
	/* Metodo per assegnare solo la chiave del Cliente da cercare. */
	
	public void setIDcerca(ModuloCl content){
		ClienteCerca = content.txtClienteCerca.getText().trim();
	}
	
	/* Metodo per verificare la correttezza dei dati inseriti. */
	
	private boolean check(ModuloCl content){
		boolean check=true;
		if (CF_PIVA.length()==16 && !CF_PIVA.matches(CFPATTERN)){
			content.txtCF_PIVA.setText("");
			content.txtCF_PIVA.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il codice fiscare inserito non è valido!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if((CF_PIVA.length()==11 && !CF_PIVA.matches("\\d{11}"))){
			content.txtCF_PIVA.setText("");
			content.txtCF_PIVA.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la partita iva inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if(CF_PIVA.length()<11 || (CF_PIVA.length()>11 && CF_PIVA.length()<16) || CF_PIVA.length()>16){
			content.txtCF_PIVA.setText("");
			content.txtCF_PIVA.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il codice fiscare deve avere 16 caratteri e la partita IVA 11 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Tipologia.length()>25){
			content.txtTipologia.setText("");
			content.txtTipologia.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la Tipologia deve avere meno di 25 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (RS.length()>30){
			content.txtRS.setText("");
			content.txtRS.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la Ragione Sociale deve avere meno di 30 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if ((!isNumeric(CAP) || CAP.length()>5 || CAP.length()<5) && !CAP.equals("")){
			content.txtCAP.setText("");
			content.txtCAP.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il CAP deve essere un numero di 5 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Citta.length()>20){
			content.txtCitta.setText("");
			content.txtCitta.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la città deve avere meno di 20 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Via.length()>20){
			content.txtVia.setText("");
			content.txtVia.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la via deve avere meno di 20 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if ((!isNumeric(Numero) || Numero.length()>3) && !Numero.equals("")){
			content.txtNumero.setText("");
			content.txtNumero.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il numero non deve contenere caratteri e deve essere composto da massimo 3 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if ((Email.length()>25 || !Email.matches(EMAILPATTERN)) && !Email.equals("")){
			content.txtEmail.setText("");
			content.txtEmail.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, l'Email inserita non è valida o è troppo lunga!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if ((Telefono.length()>10 || Telefono.length()<10 || !isNumeric(Telefono)) && !Telefono.equals("")){
			content.txtTelefono.setText("");
			content.txtTelefono.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il telefono deve essere composto da 10 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		if (check==false) test=check; 
			else test=true;
		return test;
	}
	
	/* Metodo per verificare la correttezza del/la CF/PIVA del lciente da cercare. */
	
	private boolean checkcerca(ModuloCl content){
		boolean check=true;
		if (ClienteCerca.length()==16 && !ClienteCerca.matches(CFPATTERN)){
			content.txtClienteCerca.setText("");
			content.txtClienteCerca.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il codice fiscare inserito non è valido!",
				"Errore ",
		    	JOptionPane.ERROR_MESSAGE);
		} else if((ClienteCerca.length()==11 && !ClienteCerca.matches("\\d{11}"))){
			content.txtClienteCerca.setText("");
			content.txtClienteCerca.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la partita iva inserita non è valida!",
				"Errore ",
		    	JOptionPane.ERROR_MESSAGE);
		} else if(ClienteCerca.length()<11 || (ClienteCerca.length()>11 && ClienteCerca.length()<16) || ClienteCerca.length()>16){
			content.txtClienteCerca.setText("");
			content.txtClienteCerca.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il codice fiscare deve avere 16 caratteri e la partita IVA 11 cifre!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}
	if (check==false) test=check; 
	else test=true;
	return test;
	}
	
	/* Metodo per verificare se una stringa è numerica. */
	
	public static boolean isNumeric(String string) {
	    try {
	        Long.parseLong(string);
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}
}
