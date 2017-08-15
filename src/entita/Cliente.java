package entita;

import db.DBConnect;
import java.sql.SQLException;

import gui.moduli.ModuloCliente;

import javax.swing.JOptionPane;

/**
 * La classe Cliente rappresenta i clienti dell'autonoleggio.
 */
public class Cliente {
	
	/**
	 * La tipologia di cliente. Può essere di tre tipi: <br><br> - Associazione <br> - Azienda <br> - Privato
	 */
	public String tipologia;
	
	/**
	 * La ragione sociale del cliente. <br><br> 
	 * - La Denominazione nel caso di Associazione ed Azienda. <br>
	 * - La coppia Cognome Nome nel caso di Privato.
	 */
	public String rs;
	
	/**
	 * Il Codice di Avviamento Postale (CAP) della città in cui risiede il cliente.
	 */
	public String cap;
	
	/**
	 * La città in cui risiede il cliente.
	 */
	public String citta;
	
	/**
	 * La via associata alla residenza del cliente. 
	 */
	public String via;
	
	/**
	 * Il numero civico associato alla residenza del cliente.
	 */
	public String numero;
	
	/**
	 * Il code Codice Fiscale o la code Partita Iva del cliente.
	 */
	public String CF_PIVA;
	
	/**
	 * L'email del cliente.
	 */
	public String email;
	
	/**
	 * Il recapito telefonico del cliente.
	 */
	public String telefono;
	
	
	private boolean test;
	
	private DBConnect cliente;
	
	private String clienteCerca;
	
	private static final String EMAILPATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String CFPATTERN = "[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";
	
	/**
	 * Inizializza un nuovo oggetto Cliente e stabilisce una connessione con il database.
	 */
	public Cliente() {
		
		test = true;
		cliente = new DBConnect();
	}


	/**
	 * Aggiunge un nuovo cliente al database.
	 * 
	 * @param content il form {@code "Nuovo Cliente"} ed i relativi dati inseriti.
	 */
	public void aggiungi(ModuloCliente content) {
		
		if (check(content)) {
			try {
				/* Verifica se nel DB esiste gia' un cliente con il CF (o la Partita IVA) inseriti.*/
				cliente.exequery("SELECT * FROM cliente where CF_PIVA='"+CF_PIVA+"'","select");
				if (cliente.rs.next()) {
					JOptionPane.showMessageDialog(null, "Errore! Esiste gi\u00E0 un cliente con tale CF/Partita IVA!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtCF_PIVA.setText("");
					content.txtCF_PIVA.requestFocus();
				} else {
					/* Aggiunge il nuovo cliente. Inoltre resetta i campi della form per un nuovo inserimento. */
					String valori = "('"+CF_PIVA+"','"+tipologia+"','"+rs+"',"+cap+",'"+citta+"','"+via+"',"+numero+","+telefono+",'"+email+"')";
					cliente.exequery("INSERT INTO cliente VALUES "+valori,"insert");
					JOptionPane.showMessageDialog(null , "Nuovo cliente aggiunto!");
					content.txtCF_PIVA.setText("");
					content.txtCF_PIVA.requestFocus();
					content.comboBoxTipologia.setSelectedIndex(0);
					content.txtRS.setText("");
					content.txtCAP.setText("");
					content.txtCitta.setText("");
					content.txtVia.setText("");
					content.txtNumero.setText("");
					content.txtEmail.setText("");
					content.txtTelefono.setText("");
				}
				cliente.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Cliente non aggiunto!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Elimina un cliente dal database.
	 * 
	 * @param content il form {@code "Elimina Cliente"} ed i relativi dati inseriti.
	 */
	public void elimina(ModuloCliente content) {
		
		if (checkElimina(content)) {
			try {
				/* Verifica se nel DB esiste un cliente con il CF (o la Partita IVA) inseriti.*/
				cliente.exequery("SELECT * FROM cliente where CF_PIVA='"+CF_PIVA+"'","select");
				if (!cliente.rs.next()) {
					JOptionPane.showMessageDialog(null, "Errore! Non \u00E8 presente un cliente con tale CF/Partita IVA!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtCF_PIVA.requestFocus();
				} else {
					int scelta = JOptionPane.showConfirmDialog(
							null,
							"Si desidera eliminare l'utente "+CF_PIVA+" ?",
							"Conferma eliminazione",
							JOptionPane.YES_NO_OPTION);
					if (scelta == JOptionPane.YES_OPTION) {
						cliente.exequery("DELETE FROM cliente WHERE CF_PIVA='"+CF_PIVA+"'","delete");
					JOptionPane.showMessageDialog(null , "Cliente Eliminato!");
					content.txtCF_PIVA.setText("");
					content.txtCF_PIVA.requestFocus();
					}
				}
				cliente.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Cliente non eliminato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Cerca un cliente nel database.
	 * 
	 * @param content il form {@code "Modifica Cliente"} ed i relativi campi inseriti.
	 */
	public void cerca(ModuloCliente content) {
		
		String item;
		if (checkCerca(content)) {
			try {
				cliente.exequery("SELECT * FROM cliente where CF_PIVA='"+clienteCerca+"'","select");
				if (cliente.rs.next()) {
					content.txtCF_PIVA.setText(cliente.rs.getString(1));
					
					for (int i = 1; i<4; i++) {						 
			            item = content.comboBoxTipologia.getItemAt(i);			 
			            if (item.equals(cliente.rs.getString(2))){
			              content.comboBoxTipologia.setSelectedIndex(i);			 
			            }
			         }
					
					content.txtRS.setText(cliente.rs.getString(3));
					content.txtCAP.setText(cliente.rs.getString(4));
					content.txtCitta.setText(cliente.rs.getString(5));
					content.txtVia.setText(cliente.rs.getString(6));
					content.txtNumero.setText(cliente.rs.getString(7));
					content.txtTelefono.setText(cliente.rs.getString(8));
					content.txtEmail.setText(cliente.rs.getString(9));
					
					content.txtClienteCerca.setEditable(false);
					content.comboBoxTipologia.setEditable(false);
					content.txtRS.setEditable(true);
					content.txtCAP.setEditable(true);
					content.txtCitta.setEditable(true);
					content.txtVia.setEditable(true);
					content.txtNumero.setEditable(true);
					content.txtEmail.setEditable(true);
					content.txtTelefono.setEditable(true);		
				} else {
					JOptionPane.showMessageDialog(null, "Errore! Non \u00E8 presente un cliente con tale CF/Partita IVA!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtClienteCerca.setText("");
					content.txtClienteCerca.requestFocus();
				}
				cliente.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Cliente non trovato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Modifica un cliente presente nel database.
	 * 
	 * @param content il form {@code "Modifica Cliente"} ed i relativi dati inseriti.
	 */
	public void modifica(ModuloCliente content) {
	
		if (check(content)) {
			try {
				String valori = "Tipologia='"+tipologia+"',Ragione_Sociale='"+rs+"',CAP="+cap+",Citta='"+citta+"',Via='"+via+"',Numero="+numero+",Telefono="+telefono+",Email='"+email+"'";
				cliente.exequery("UPDATE cliente SET "+valori+" WHERE CF_PIVA='"+CF_PIVA+"'","update");
				JOptionPane.showMessageDialog(null , "Cliente Modificato!");
				content.txtClienteCerca.setText("");
				content.txtClienteCerca.requestFocus();
				content.txtClienteCerca.setEditable(true);
				content.txtCF_PIVA.setEditable(false);
				content.comboBoxTipologia.setEditable(false);
				content.txtRS.setEditable(false);
				content.txtCAP.setEditable(false);
				content.txtCitta.setEditable(false);
				content.txtVia.setEditable(false);
				content.txtNumero.setEditable(false);
				content.txtEmail.setEditable(false);
				content.txtTelefono.setEditable(false);
				
				cliente.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Cliente non modificato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Verifica che i dati del cliente da aggiungere/modificare siano corretti.
	 * 
	 * @param content il form {@code "Nuovo Cliente"/"Modifica Cliente"} ed i relativi dati inseriti.
	 * @return true se i dati inseriti sono validi; false altrimenti.
	 */
	private boolean check(ModuloCliente content) {
		
		boolean check = true;
		/* Verifica se sono stati inseriti tutti i campi necessari.*/
		if (CF_PIVA.equals("") || content.comboBoxTipologia.getSelectedIndex() == 0 || rs.equals("")) {	
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci tutti i campi indicati da un asterisco!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		} else if (CF_PIVA.length() == 16 && !CF_PIVA.matches(CFPATTERN)) {
			content.txtCF_PIVA.setText("");
			content.txtCF_PIVA.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale inserito non \u00E8 valido!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if((CF_PIVA.length() == 11 && !CF_PIVA.matches("\\d{11}"))) {
			content.txtCF_PIVA.setText("");
			content.txtCF_PIVA.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La Partita IVA inserita non \u00E8 valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if(CF_PIVA.length() < 11 || (CF_PIVA.length() > 11 && CF_PIVA.length() < 16) || CF_PIVA.length() > 16) {
			content.txtCF_PIVA.setText("");
			content.txtCF_PIVA.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale deve avere 16 caratteri e la Partita IVA 11 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if (rs.length() > 40) {
			content.txtRS.setText("");
			content.txtRS.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Ragione Sociale deve avere meno di 30 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if ((!isNumeric(cap) || cap.length() > 5 || cap.length() < 5) && !cap.equals("")) {
			content.txtCAP.setText("");
			content.txtCAP.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il CAP deve essere composto da 5 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if (citta.length() > 25) {
			content.txtCitta.setText("");
			content.txtCitta.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Citt\u00E0 deve avere meno di 20 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if (via.length() > 20) {
			content.txtVia.setText("");
			content.txtVia.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Via deve avere meno di 20 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if ((!isNumeric(numero) || numero.length() > 3) && !numero.equals("")) {
			content.txtNumero.setText("");
			content.txtNumero.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Numero deve essere composto al massimo da 3 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if ((email.length() > 30 || !email.matches(EMAILPATTERN)) && !email.equals("")) {
			content.txtEmail.setText("");
			content.txtEmail.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! L'Email inserita non \u00E8 valida o \u00E8 troppo lunga!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if ((telefono.length() > 10 || telefono.length() < 10 || !isNumeric(telefono)) && !telefono.equals("")) {
			content.txtTelefono.setText("");
			content.txtTelefono.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Telefono deve essere composto da 10 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		
		if (check==false) test=check; 
		else {
			if (cap.equals("")) cap="NULL";
			if (citta.equals("")) citta="";
			if (via.equals("")) via="";
			if (numero.equals("")) numero="NULL";
			if (telefono.equals("")) telefono="NULL";
			if (email.equals("")) email="";
			test=true;
		}
		return test;
	}
	
	/**
	 * Verifica la correttezza del {@code Codice Fiscale} (o della {@code Partita IVA}) del cliente da eliminare.
	 * 
	 * @param content il form {@code "Elimina Cliente"} ed i relativi dati inseriti.
	 * @return true se i dati inseriti sono validi; false altrimenti.
	 */
	private boolean checkElimina(ModuloCliente content) {
		
		boolean check=true;
		/* Verifica se sono stati inseriti tutti i campi necessari */
		if (CF_PIVA.equals("")) {		
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci il cliente da eliminare!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		} else if (CF_PIVA.length() == 16 && !CF_PIVA.matches(CFPATTERN)) {
			content.txtCF_PIVA.setText("");
			content.txtCF_PIVA.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale inserito non \u00E8 valido!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if((CF_PIVA.length() == 11 && !CF_PIVA.matches("\\d{11}"))) {
			content.txtCF_PIVA.setText("");
			content.txtCF_PIVA.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La Partita IVA inserita non \u00E8 valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if(CF_PIVA.length() < 11 || (CF_PIVA.length() > 11 && CF_PIVA.length() < 16) || CF_PIVA.length() > 16) {
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
	
	/**
	 *  Verifica la correttezza del {@code Codice Fiscale} (o della {@code Partita IVA}) del cliente da cercare.
	 *  
	 *  @param content il form {@code "Modifica Cliente"} ed i relativi dati inseriti.
	 */
	private boolean checkCerca(ModuloCliente content) {
		
		boolean check=true;
		if (clienteCerca.length() == 16 && !clienteCerca.matches(CFPATTERN)) {
			content.txtClienteCerca.setText("");
			content.txtClienteCerca.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale inserito non \u00E8 valido!",
				"Errore ",
		    	JOptionPane.ERROR_MESSAGE);
		} else if((clienteCerca.length() == 11 && !clienteCerca.matches("\\d{11}"))) {
			content.txtClienteCerca.setText("");
			content.txtClienteCerca.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La Partita IVA inserita non \u00E8 valida!",
				"Errore ",
		    	JOptionPane.ERROR_MESSAGE);
		} else if(clienteCerca.length() < 11 || (clienteCerca.length() > 11 && clienteCerca.length() < 16) || clienteCerca.length() > 16) {
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
	
	/**
	 * Verifica se la stringa passata come argomento è numerica.
	 * 
	 * @param string la stringa da controllare.
	 * @return true se la stringa è numerica; false altrimenti.
	 */
	private static boolean isNumeric(String string) {
		
	    try {
	        Long.parseLong(string);
	    } catch (Exception e) {
	    	return false;
	    }
	    return true;
	}

/* METODI USATI DALLA GUI PER LA GESTIONE DEI CLIENTI ( --> vedi classe ModuloCl <-- ) */

	/**
	 * Assegna i valori inseriti nella form alle variabili dell'oggetto {@code Cliente}.
	 * 
	 * @param content il form {@code "Nuovo Cliente"/"Modifica Cliente"} ed i relativi dati inseriti.
	 */
	public void setValori(ModuloCliente content) {
		
		CF_PIVA = content.txtCF_PIVA.getText().trim();
		tipologia = content.comboBoxTipologia.getSelectedItem().toString();
		rs = content.txtRS.getText().trim();
		cap = content.txtCAP.getText().trim();
		citta = content.txtCitta.getText().trim();
		via = content.txtVia.getText().trim();
		numero = content.txtNumero.getText().trim();
		email = content.txtEmail.getText().trim();
		telefono = content.txtTelefono.getText().trim();
	}
	
	/**
	 * Assegna il {@code Codice Fiscale} (o la {@code Partita IVA}) del cliente da eliminare.
	 * 
	 * @param content il form {@code "Elimina Cliente"} ed i relativi dati inseriti.
	 */
	public void setIDElimina(ModuloCliente content) {
		
		CF_PIVA = content.txtCF_PIVA.getText().trim();
	}

	/**
	 * Assegna il {@code Codice Fiscale} (o la {@code Partita IVA}) del cliente da cercare.
	 * 
	 * @param content il form {@code "Modifica Cliente"} ed i relativi dati inseriti.
	 */
	public void setIDCerca(ModuloCliente content) {
		
		clienteCerca = content.txtClienteCerca.getText().trim();
	}


/* OVERRIDING METODI toString() ED equals() */
	
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * 
	 * @return una stringa rappresentante l'oggetto.
	 */
	public String toString() {
		
		return "Cliente [cliente=" + cliente + ", test=" + test + ", tipologia=" + tipologia + ", rs=" + rs + ", cap="
				+ cap + ", citta=" + citta + ", via=" + via + ", numero=" + numero + ", CF_PIVA=" + CF_PIVA + ", email="
				+ email + ", telefono=" + telefono + ", clienteCerca=" + clienteCerca + "]";
	}

	/**
	 * Confronta questo oggetto con quello passato come argomento.
	 * 
	 * @param obj l'oggetto da confrontare.
	 * @return true se i due oggetti sono uguali; false altrimenti.
	 */
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (CF_PIVA == null) {
			if (other.CF_PIVA != null)
				return false;
		} else if (!CF_PIVA.equals(other.CF_PIVA))
			return false;
		if (cap == null) {
			if (other.cap != null)
				return false;
		} else if (!cap.equals(other.cap))
			return false;
		if (citta == null) {
			if (other.citta != null)
				return false;
		} else if (!citta.equals(other.citta))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (clienteCerca == null) {
			if (other.clienteCerca != null)
				return false;
		} else if (!clienteCerca.equals(other.clienteCerca))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (rs == null) {
			if (other.rs != null)
				return false;
		} else if (!rs.equals(other.rs))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (test != other.test)
			return false;
		if (tipologia == null) {
			if (other.tipologia != null)
				return false;
		} else if (!tipologia.equals(other.tipologia))
			return false;
		if (via == null) {
			if (other.via != null)
				return false;
		} else if (!via.equals(other.via))
			return false;
		return true;
	}
}