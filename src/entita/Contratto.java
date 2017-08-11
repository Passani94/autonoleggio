package entita;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import db.DBConnect;
import java.sql.SQLException;


import gui.moduli.ModuloContratto;
import utils.CostruisciTabella;
import utils.TableColumnAdjuster;

/**
 * La classe Contratto rappresenta i contratti dell'autonoleggio.
 */
public class Contratto {
	
	/**
	 * La tipologia di noleggio. Può essere di due tipi: <br><br> - Breve <br> - Lungo
	 */
	public String tipologia;
	
	/**
	 * La targa del veicolo noleggiato.
	 */
	public String veicolo;
	
	/**
	 * Il Codice Fiscale (o la Partita IVA) del cliente.
	 */
	public String cliente;
	
	/**
	 * La data di inizio noleggio.
	 */
	public String inizio;
	
	/**
	 * La data di fine noleggio.
	 */
	public String fine;
	
	/**
	 * Il costo del noleggio.
	 */
	public String costo;
	
	/**
	 * L'acconto lasciato dal cliente.
	 */
	public String acconto;
	
	/**
	 * Il cognome riportato sulla patente del conducente.
	 */
	public String cognome;
	
	/**
	 * Il nome riportato sulla patente del conducente.
	 */
	public String nome;
	
	/**
	 * Il numero di patente.
	 */
	public String patente;
	
	/**
	 * La data di fine validità della patente.
	 */
	public String valida;
	
	/**
	 * L'ente che ha rilasciato la patente.
	 */
	public String rilasciataDa;
	
	/**
	 * La data in cui è stata rilasciata la patente.
	 */
	public String rilasciataIl;
	
	
	private boolean test;
	
	private DBConnect noleggio;
	
	private Integer codice,codiceCerca,codiceModifica;
	
	private static final String CFPATTERN = "[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";
	private static final String PTPATTERN = "^[a-zA-Z]{2}\\d{7}[a-zA-Z]{1}";	//Pattern Patente
	private static final String TGPATTERN1 = "[a-zA-Z]{2}\\d\\d\\d[a-zA-Z]{2}"; //Pattern Targa Autoveicolo
	private static final String TGPATTERN2 = "[a-zA-Z]{1}\\d\\d\\d[a-zA-Z]{2}"; //Pattern Targa Scooter
	private static final String TGPATTERN3 = "[a-zA-Z]{2}\\d\\d\\d\\d\\d"; 		//Pattern Targa Motocicletta e Quad-Bike
	private static final String TGPATTERN4 = "\\d[a-zA-Z]{2}\\d\\d\\d"; 		//Pattern Targa Mezzo Acquatico
	
	
	/**
	 * Inizializza un nuovo oggetto Contratto e stabilisce una connessione con il database.
	 */
	public Contratto() {
		
		test=true;
		noleggio = new DBConnect();
	}
	
	/**
	 * Aggiunge un nuovo contratto al database.
	 * 
	 * @param content il form {@code "Nuovo Contratto"} ed i relativi dati inseriti.
	 */
	public void aggiungi(ModuloContratto content) {
		
		if (check(content)) {
			try { 
				/* Cerca nel DB un cliente con il CF (o la Partita IVA) inseriti. */
				noleggio.exequery("SELECT * FROM cliente where CF_PIVA='"+cliente+"'","select"); 
				/* Verifica se il cliente inserito è presente nel DB. */
				if (!noleggio.rs.next()) {	
					JOptionPane.showMessageDialog(null, "Errore! Il cliente inserito non è presente!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtCliente.setText("");
					content.txtCliente.requestFocus();
					test=false;
				} 
				/* Cerca nel DB un veicolo con la Targa inserita. */
				noleggio.exequery("SELECT * FROM veicolo where Targa='"+veicolo+"'","select"); 
				/* Verifica se il veicolo inserito è presente nel DB. */
				if (!noleggio.rs.next()) {	
					JOptionPane.showMessageDialog(null, "Errore! Il veicolo inserito non è presente!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtVeicolo.setText("");
					content.txtVeicolo.requestFocus();
					test=false;
				} 
				/* Verifica se il veicolo desiderato è disponibile. */
				if (test==true && noleggio.rs.getString("Disponibilita").equals("SI")) {	
					String valori="(DEFAULT,'"+tipologia+"','"+veicolo+"','"+cliente+"',"+inizio+","+fine+","+costo+","+acconto+",'"+cognome+"',"
							+ "'"+nome+"','"+patente+"',"+valida+",'"+rilasciataDa+"',"+rilasciataIl+")";
					/* Aggiunge il contratto di noleggio. Inoltre resetta i campi della form per un nuovo inserimento. */
					noleggio.exequery("INSERT INTO noleggio VALUES "+valori,"insert");
					JOptionPane.showMessageDialog(null , "Nuovo contratto di noleggio inserito!");
					content.comboBoxTipologia.setSelectedIndex(0);
					content.txtVeicolo.setText("");
					content.txtCliente.setText("");
					content.frmtdtxtfldInizio.setText("Seleziona una data");
					content.frmtdtxtfldFine.setText("Seleziona una data");
					content.txtCosto.setText("");
					content.txtAcconto.setText("");
					content.txtCognome.setText("");
					content.txtNome.setText("");
					content.txtPatente.setText("");
					content.frmtdtxtfldValida.setText("Seleziona una data");
					content.txtRilasciatada.setText("");
					content.frmtdtxtfldRilasciatail.setText("Seleziona una data");
				} else {
					JOptionPane.showMessageDialog(null, "Errore! Il veicolo inserito non è disponibile per il noleggio!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
					content.txtVeicolo.setText("");
					content.txtVeicolo.requestFocus();
				}
				noleggio.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Contratto di noleggio non inserito!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Cerca un contratto nel database.
	 * 
	 * @param content il form {@code "Modifica Contratto"} ed i relativi campi inseriti.
	 */
	public void cerca(ModuloContratto content){
		
		String item;
			if (checkcerca(content)) {
				try {
				noleggio.exequery("SELECT * FROM noleggio where Cod_Noleggio="+codiceCerca+"","select");
				if (noleggio.rs.next()) {
					content.txtContrattoCerca.setEditable(false);
					
					for (int i=1; i<3; i++) {						 
			            item=content.comboBoxTipologia.getItemAt(i);			 
			            if (item.equals(noleggio.rs.getString(2))) {
			              content.comboBoxTipologia.setSelectedIndex(i);			 
			            }
			          }					
				
					content.txtVeicolo.setText(noleggio.rs.getString(3));
					content.txtCliente.setText(noleggio.rs.getString(4));
					content.frmtdtxtfldInizio.setText(noleggio.rs.getString(5));
					content.frmtdtxtfldFine.setText(noleggio.rs.getString(6));
					content.txtCosto.setText(noleggio.rs.getString(7));
					content.txtAcconto.setText(noleggio.rs.getString(8));
					content.txtCognome.setText(noleggio.rs.getString(9));
					content.txtNome.setText(noleggio.rs.getString(10));
					content.txtPatente.setText(noleggio.rs.getString(11));
					content.frmtdtxtfldValida.setText(noleggio.rs.getString(12));
					if (!noleggio.rs.getString(13).equals("NULL")) {
						content.txtRilasciatada.setText(noleggio.rs.getString(13));
					} else {
						content.txtRilasciatada.setText("");
					}
					content.frmtdtxtfldRilasciatail.setText(noleggio.rs.getString(14));
					
					content.comboBoxTipologia.setEnabled(false);
					content.txtVeicolo.setEditable(false);
					content.frmtdtxtfldInizio.setEditable(false);
					content.frmtdtxtfldFine.setEditable(false);
					content.txtCosto.setEditable(false);
					content.txtAcconto.setEditable(true);
					content.txtCognome.setEditable(true);
					content.txtNome.setEditable(true);
					content.txtPatente.setEditable(true);
					content.frmtdtxtfldValida.setEditable(true);
					content.txtRilasciatada.setEditable(true);
					content.frmtdtxtfldRilasciatail.setEditable(true);
					
				} else {
					JOptionPane.showMessageDialog(null, "Errore! Non è presente un contratto con tale codice!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtContrattoCerca.setText("");
					content.txtContrattoCerca.requestFocus();
				}
				noleggio.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Contratto non trovato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Elimina un contratto dal database.
	 * 
	 * @param content il form {@code "Elimina Contratto"} ed i relativi dati inseriti.
	 */
	public void elimina(ModuloContratto content) {
		
		if (checkelimina(content)) {
			try {
				noleggio.exequery("SELECT * FROM noleggio where Cod_Noleggio="+codice+"","select");
				/*Verifica se è presente un contratto con tale codice*/
				if (!noleggio.rs.next()) {
					JOptionPane.showMessageDialog(null, "Errore! Non è presente un contratto con tale codice!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtCodiceElimina.requestFocus();
				} else {
					int scelta = JOptionPane.showConfirmDialog(
							null,
							"Si desidera eliminare il contratto con codice "+codice+" ?",
							"Conferma eliminazione",
							JOptionPane.YES_NO_OPTION);
					if (scelta == JOptionPane.YES_OPTION) {
						noleggio.exequery("DELETE FROM noleggio WHERE Cod_Noleggio="+codice+"","delete");
					JOptionPane.showMessageDialog(null , "Contratto Eliminato!");
					content.txtCodiceElimina.setText("");
					content.txtCodiceElimina.requestFocus();
					}
				}
				noleggio.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Contratto non eliminato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Modifica un contratto presente nel database.
	 * 
	 * @param content il form {@code "Modifica Contratto"} ed i relativi dati inseriti.
	 */
	public void modifica(ModuloContratto content){
	
		if (check(content)) {
			try {
				String valori = "Cod_Noleggio="+codiceModifica+",Tipologia='"+tipologia+"',Veicolo='"+veicolo+"',Cliente='"+cliente+"',"
						+ "Data_Inizio="+inizio+",Data_Fine="+fine+",Costo_Totale="+costo+",Acconto="+
						acconto+",Cognome='"+cognome+"',Nome='"+nome+"',Num_Patente='"+patente+"',"
								+ "Valida_Fino_a="+valida+",Rilasciata_Da='"+rilasciataDa+"',"
										+ "Rilasciata_Il="+rilasciataIl+"";
				noleggio.exequery("UPDATE noleggio SET "+valori+" WHERE Cod_Noleggio="+codiceModifica+"","update");
				JOptionPane.showMessageDialog(null , "Contratto Modificato!");
				content.txtContrattoCerca.requestFocus();
				content.txtContrattoCerca.setEditable(true);
				content.comboBoxTipologia.setEnabled(false);
				content.txtVeicolo.setEditable(false);
				content.txtCliente.setEditable(false);
				content.frmtdtxtfldInizio.setEditable(false);
				content.frmtdtxtfldFine.setEditable(false);
				content.txtCosto.setEditable(false);
				content.txtAcconto.setEditable(false);
				content.txtCognome.setEditable(false);
				content.txtNome.setEditable(false);
				content.txtPatente.setEditable(false);
				content.frmtdtxtfldValida.setEditable(false);
				content.txtRilasciatada.setEditable(false);
				content.frmtdtxtfldRilasciatail.setEditable(false);
				
				noleggio.con.close();				
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Contratto non modificato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	/* Metodo. Filtra la tabella dei contratti. */
	/**
	 * Filtra un contratto presente nell'elenco contratti.
	 * 
	 * @param content il form {@code "Elenco Contratti"} ed i relativi dati inseriti.
	 */
	public void filtra(ModuloContratto content) {
		
		if (checkfiltra(content)) {
			try {
				if (cliente.equals("")) {
					noleggio.exequery("SELECT * FROM noleggio WHERE Veicolo='"+veicolo+"'","select");
				} else if (veicolo.equals("")) {
					noleggio.exequery("SELECT * FROM noleggio WHERE Cliente='"+cliente+"'","select");
				} else {
					noleggio.exequery("SELECT * FROM noleggio WHERE Veicolo='"+veicolo+"' AND Cliente='"+cliente+"'","select");
				}
				content.tblNoleggi.setModel(new CostruisciTabella(noleggio.rs).model);
				content.tblNoleggi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				TableColumnAdjuster tca = new TableColumnAdjuster(content.tblNoleggi);
				tca.adjustColumns();
				content.revalidate();
				
				noleggio.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Impossibile filtrare i contratti di noleggio!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Verifica che i dati del contratto da aggiungere/modificare siano corretti.
	 * 
	 * @param content il form {@code "Nuovo Contratto"/"Modifica Contratto"} ed i relativi dati inseriti.
	 * @return true se i dati inseriti sono validi; false altrimenti.
	 */
	private boolean check(ModuloContratto content){
		
		boolean check=true;
		/* Verifica se sono stati inseriti tutt i campi necessari. */
		if (content.comboBoxTipologia.getSelectedIndex()==0 || veicolo.equals("") || cliente.equals("") || inizio.equals("Seleziona una data") 
				|| fine.equals("Seleziona una data") || costo.equals("") || cognome.equals("") || nome.equals("") || patente.equals("")) {		
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci tutti i campi indicati da un asterisco!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		} else if (!veicolo.matches(TGPATTERN1) && !veicolo.matches(TGPATTERN2) && !veicolo.matches(TGPATTERN3) && !veicolo.matches(TGPATTERN4)) {
			content.txtVeicolo.setText("");
			content.txtVeicolo.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La Targa del veicolo deve essere composta da: \n - Autoveicolo: 4 caratteri e 3 cifre (es. TO175RP); "
					+ "\n - Scooter: 3 caratteri e 3 cifre (es. X269DL); \n - Motocicletta e Quad-Bike: 2 caratteri e 5 cifre (es. AA12345);"
					+ "\n - Mezzo Acquatico: 2 caratteri e 4 cifre (es. 8PC567).",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if (cliente.length()==16 && !cliente.matches(CFPATTERN)) {
			content.txtCliente.setText(""); 
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! il Codice Fiscale inserito non è valido!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if ((cliente.length()==11 && !cliente.matches("\\d{11}"))) {
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La Partita IVA inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if (cliente.length()<11 || (cliente.length()>11 && cliente.length()<16) || cliente.length()>16) {
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale deve avere 16 caratteri e la Partita IVA 11 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if (!isNumeric(costo) || costo.length()>10) {
			content.txtCosto.setText("");
			content.txtCosto.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Costo Totale deve essere composto da meno di 10 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if ((!isNumeric(costo) || (acconto.length()>10)) && !acconto.equals("")) {
			content.txtAcconto.setText("");
			content.txtAcconto.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Acconto deve essere composto da meno di 10 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if (cognome.length()>15) {
			content.txtCognome.setText("");
			content.txtCognome.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Cognome deve avere meno di 15 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if (nome.length()>15) {
			content.txtNome.setText("");
			content.txtNome.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Nome deve avere meno di 15 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if (patente.length()>10 ||patente.length()<10 || !patente.matches(PTPATTERN)) {
			content.txtPatente.setText("");
			content.txtPatente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Patente deve essere composta da 3 caratteri e 7 cifre (Es:TO1234567X)!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if (valida.equals("Seleziona una data")) {
			content.frmtdtxtfldValida.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci tutti i campi indicati da un asterisco!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if (rilasciataDa.length()>20) {
			content.txtRilasciatada.setText("");
			content.txtRilasciatada.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo 'Rilasciata Da' deve avere meno di 20 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if (rilasciataIl.equals("Seleziona una data")) {
			content.frmtdtxtfldRilasciatail.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci tutti i campi indicati da un asterisco!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		if (check==false) {
			test=check; 
		} else {
			if (inizio.equals("Seleziona una data")) { 
				inizio="DEFAULT";
			} else {
				inizio="'"+inizio+"'";
			}
			if (fine.equals("Seleziona una data")) { 
				fine="DEFAULT";
			} else { 
				fine="'"+fine+"'";
			}
			if (acconto.equals("")) {
				acconto="NULL";
			}
			if (valida.equals("Seleziona una data")) {
				valida="DEFAULT";
			} else { 
				valida="'"+valida+"'";
			}
			if (rilasciataDa.equals("")) {
				rilasciataDa="";
			}
			if (rilasciataIl.equals("Seleziona una data")) {
				rilasciataIl="DEFAULT";
			} else {
				rilasciataIl="'"+rilasciataIl+"'";
			}
			test=true;
		}
		return test;
	}
	
	/**
	 * Verifica che i dati (cliente e/o veicolo) del contratto  da filtrare sianno corretti.
	 * 
	 * @param content il form {@code "Elenco Contratti"} ed i relativi dati inseriti.
	 * @return true se i dati sono validi; false altrimenti.
	 */
	private boolean checkfiltra(ModuloContratto content) {
		
		boolean check=true;
		/* Verifica se è stato inserito almeno un campo. */
		if (cliente.equals("") && veicolo.equals("")) {		
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci un cliente e/o un veicolo da filtrare!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		} else if (cliente.length()==16 && !cliente.matches(CFPATTERN)) {
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale del cliente da filtrare non è valido!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if ((cliente.length()==11 && !cliente.matches("\\d{11}"))) {
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! la Partita IVA del cliente da filtrare non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if (((cliente.length()<11 || (cliente.length()>11 && cliente.length()<16) || cliente.length()>16)) && !cliente.equals("")) {
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale deve avere 16 caratteri e la Partita IVA 11 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if ((!veicolo.matches(TGPATTERN1) && !veicolo.matches(TGPATTERN2) && !veicolo.matches(TGPATTERN3) && !veicolo.matches(TGPATTERN4)) && !veicolo.equals("")) {
			content.txtVeicolo.setText("");
			content.txtVeicolo.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La Targa del veicolo deve essere composta da: \n - Autoveicolo: 4 caratteri e 3 cifre (es. TO175RP); "
					+ "\n - Scooter: 3 caratteri e 3 cifre (es. X269DL); \n - Motocicletta e Quad-Bike: 2 caratteri e 5 cifre (es. AA12345);"
					+ "\n - Mezzo Acquatico: 2 caratteri e 4 cifre (es. 8PC567).",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		
		if (check==false) test=check; 
		else test=true;
		
		return test;
	}
	
	/**
	 * Verifica che il codice del contratto da eliminare sia corretto.
	 * 
	 * @param content il form {@code "Elimina Contratto"} ed i relativi dati inseriti.
	 * @return true se i dati sono validi; false altrimenti.
	 */
	private boolean checkelimina(ModuloContratto content) {
		
		boolean check=true;
		
		if (codice  <=0) {
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il codice deve essere maggiore o uguale ad 1!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
		}
		
		if (check==false) test=check; 
		else test=true;
		
		return test;
	}
	
	/**
	 * Verifica che il codice del contratto da modificare sia corretto.
	 * 
	 * @param content il form {@code "Modifica Contratto"} ed i relativi dati inseriti.
	 * @return true se i dati sono validi; false altrimenti.
	 */
	private boolean checkcerca (ModuloContratto content){
		boolean check=true;
		
		if (codiceCerca <= 0) {
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il codice deve essere maggiore o uguale ad 1!",
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
	    
		return string!= null && string.matches("[-+]?\\d*\\.?\\d*");
	}
	
	
	/* METODI USATI DALLA GUI PER LA GESTIONE DEI CONTRATTI (--> vedi classe ModuloCt <--) */
	
	/**
	 * Assegna i valori inseriti nella form alle variabili dell'oggetto {@code Contratto}.
	 * 
	 * @param content il form {@code "Nuovo Contratto"/"Modifica Contratto"} ed i relativi dati inseriti.
	 */
	public void setValori(ModuloContratto content) {
		
		tipologia = content.comboBoxTipologia.getSelectedItem().toString();
		veicolo = content.txtVeicolo.getText().trim();
		cliente = content.txtCliente.getText().trim();
		inizio = content.frmtdtxtfldInizio.getText().trim();
		fine = content.frmtdtxtfldFine.getText().trim();
		costo = content.txtCosto.getText().trim();
		acconto = content.txtAcconto.getText().trim();
		cognome = content.txtCognome.getText().trim();
		nome = content.txtNome.getText().trim();
		patente = content.txtPatente.getText().trim();
		valida = content.frmtdtxtfldValida.getText().trim();
		rilasciataDa = content.txtRilasciatada.getText().trim();
		rilasciataIl = content.frmtdtxtfldRilasciatail.getText().trim();
	}
	
	/**
	 * Assegna il codice del contratto da eliminare.
	 * 
	 * @param content il form {@code "Elimina Cliente"} ed i relativi dati inseriti.
	 * @return true l'operazione di set è andata a buon fine; false altrimenti.
	 */
	public boolean setCodiceElimina (ModuloContratto content) {
		
		boolean esito = false;
		
		if (content.txtCodiceElimina.getText().trim().toString().equals("")) {		
			JOptionPane.showMessageDialog(null, "Errore! Inserisci il codice di un contratto!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		} else if (!content.txtCodiceElimina.getText().trim().toString().matches("\\d*")) {
			JOptionPane.showMessageDialog(null, "Errore! Il codice deve essere composto da sole cifre!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
			content.txtCodiceElimina.setText("");
		} else {
			codice=Integer.decode((content.txtCodiceElimina.getText().trim()));
			esito = true;
		}
		
		return esito;
	}
	
	/**
	 * Assegna il codice del contratto da cercare.
	 * 
	 * @param content il form {@code "Modifica Cliente"} ed i relativi dati inseriti.
	 * @return true se l'operazione di set è andata a buon fine; false altrimenti.
	 */
	public boolean setCodiceCerca(ModuloContratto content) {
		
		boolean esito = false;
		
		if (content.txtContrattoCerca.getText().trim().toString().equals("")) {		
			JOptionPane.showMessageDialog(null, "Errore! Inserisci il codice di un contratto!",
				"Errore",
				JOptionPane.ERROR_MESSAGE);
		} else if (!content.txtContrattoCerca.getText().trim().toString().matches("\\d*")) {
			JOptionPane.showMessageDialog(null, "Errore! Il codice deve essere composto da sole cifre!",
				"Errore",
				JOptionPane.ERROR_MESSAGE);
			content.txtContrattoCerca.setText("");
		} else {
			codiceCerca = Integer.decode(content.txtContrattoCerca.getText().trim());
			esito = true;
		}
		
		return esito;
	}
	
	/**
	 * Assegna il codice del contratto da modificare.
	 * 
	 * @param content il form {@code "Modifica Cliente"} ed i relativi dati inseriti.
	 */
	public void setCodiceModifica (ModuloContratto content) {
		
			codiceModifica = Integer.decode(content.txtContrattoCerca.getText().trim());
	}
	
	/**
	 * Assegna i valori inseriti nella form alle variabili dell'oggetto {@code Contratto}.
	 * 
	 * @param content il form {@code "Elenco Contratti"} ed i relativi dati inseriti.
	 */
	public void setValoriFiltra(ModuloContratto content){
		cliente = content.txtCliente.getText().trim();
		veicolo = content.txtVeicolo.getText().trim();
	}

/* OVERRIDING METODI toString() ED equals() */
	
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * 
	 * @return una stringa rappresentante l'oggetto.
	 */
	public String toString() {
		return "Contratto [La classe Contratto rappresenta i contratti dell'autonoleggio.]";
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
		Contratto other = (Contratto) obj;
		if (acconto == null) {
			if (other.acconto != null)
				return false;
		} else if (!acconto.equals(other.acconto))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		if (codiceCerca == null) {
			if (other.codiceCerca != null)
				return false;
		} else if (!codiceCerca.equals(other.codiceCerca))
			return false;
		if (codiceModifica == null) {
			if (other.codiceModifica != null)
				return false;
		} else if (!codiceModifica.equals(other.codiceModifica))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (costo == null) {
			if (other.costo != null)
				return false;
		} else if (!costo.equals(other.costo))
			return false;
		if (fine == null) {
			if (other.fine != null)
				return false;
		} else if (!fine.equals(other.fine))
			return false;
		if (inizio == null) {
			if (other.inizio != null)
				return false;
		} else if (!inizio.equals(other.inizio))
			return false;
		if (noleggio == null) {
			if (other.noleggio != null)
				return false;
		} else if (!noleggio.equals(other.noleggio))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (patente == null) {
			if (other.patente != null)
				return false;
		} else if (!patente.equals(other.patente))
			return false;
		if (rilasciataDa == null) {
			if (other.rilasciataDa != null)
				return false;
		} else if (!rilasciataDa.equals(other.rilasciataDa))
			return false;
		if (rilasciataIl == null) {
			if (other.rilasciataIl != null)
				return false;
		} else if (!rilasciataIl.equals(other.rilasciataIl))
			return false;
		if (test != other.test)
			return false;
		if (tipologia == null) {
			if (other.tipologia != null)
				return false;
		} else if (!tipologia.equals(other.tipologia))
			return false;
		if (valida == null) {
			if (other.valida != null)
				return false;
		} else if (!valida.equals(other.valida))
			return false;
		if (veicolo == null) {
			if (other.veicolo != null)
				return false;
		} else if (!veicolo.equals(other.veicolo))
			return false;
		return true;
	}
	
}
