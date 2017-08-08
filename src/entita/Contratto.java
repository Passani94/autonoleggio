package entita;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import db.DBConnect;
import java.sql.SQLException;


import gui.moduli.ModuloContratto;
import utils.CostruisciTabella;
import utils.TableColumnAdjuster;

/* Classe per l'entità Contratto */

public class Contratto {
	
	public String tipologia;
	public String veicolo;
	public String cliente;
	public String inizio;
	public String fine;
	public String costo;
	public String acconto;
	public String cognome;
	public String nome;
	public String patente;
	public String valida;
	public String rilasciataDa;
	public String rilasciataIl;
	
	public Integer codice,codiceCerca,codiceModifica;
	
	private boolean test;
	
	private DBConnect noleggio;
	
	private static final String CFPATTERN = "[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";
	private static final String PTPATTERN = "^[a-zA-Z]{2}\\d{7}[a-zA-Z]{1}";
	private static final String TGPATTERN1 = "[a-zA-Z]{2}\\d\\d\\d[a-zA-Z]{2}"; //Pattern Targa Autoveicolo
	private static final String TGPATTERN2 = "[a-zA-Z]{1}\\d\\d\\d[a-zA-Z]{2}"; //Pattern Targa Scooter
	private static final String TGPATTERN3 = "[a-zA-Z]{2}\\d\\d\\d\\d\\d"; //Pattern Targa Motocicletta e Quad-Bike
	private static final String TGPATTERN4 = "\\d[a-zA-Z]{2}\\d\\d\\d"; //Pattern Targa Mezzo Acquatico
	
	
	/* Costruttore Contratto */
	
	public Contratto() {
		test=true;
		noleggio = new DBConnect();
	}
	
	
	/* Metodo. Aggiunge un nuovo contratto al DB. */
	
	public void aggiungi(ModuloContratto content) {
		if (check(content)){
			try { 
				/* Cerca nel DB un cliente con il CF (o la Partita IVA) inseriti. */
				noleggio.exequery("SELECT * FROM cliente where CF_PIVA='"+cliente+"'","select"); 
				/* Verifica se il cliente inserito è presente nel DB. */
				if (!noleggio.rs.next()){	
					JOptionPane.showMessageDialog(null, "Errore! Il cliente inserito non è presente!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtCliente.setText("");
					content.txtCliente.requestFocus();
					test=false;
				} /* Cerca nel DB un veicolo con la Targa inserita. */
				noleggio.exequery("SELECT * FROM veicolo where Targa='"+veicolo+"'","select"); 
				/* Verifica se il veicolo inserito è presente nel DB. */
				if (!noleggio.rs.next()){	
					JOptionPane.showMessageDialog(null, "Errore! Il veicolo inserito non è presente!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtVeicolo.setText("");
					content.txtVeicolo.requestFocus();
					test=false;
				} /* Verifica se il veicolo desiderato è disponibile. */
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
	
	public void cerca(ModuloContratto content){
		String item;
			if (checkcerca(content)){
				try{
				noleggio.exequery("SELECT * FROM noleggio where Cod_Noleggio="+codiceCerca+"","select");
				if (noleggio.rs.next()){
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
					if(!noleggio.rs.getString(13).equals("NULL")) {
						content.txtRilasciatada.setText(noleggio.rs.getString(13));
					}else {
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
					
				}else{
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
	
	/* Metodo. Elimina un contratto. */
	
	public void elimina(ModuloContratto content) {
		if (checkelimina(content)){
			try{
				noleggio.exequery("SELECT * FROM noleggio where Cod_Noleggio="+codice+"","select");
				/*Verifica se è presente un contratto con tale codice*/
				if(!noleggio.rs.next()){
					JOptionPane.showMessageDialog(null, "Errore! Non è presente un contratto con tale codice!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtCodice.requestFocus();
				}else {
					int scelta = JOptionPane.showConfirmDialog(
							null,
							"Si desidera eliminare il contratto con codice "+codice+" ?",
							"Conferma eliminazione",
							JOptionPane.YES_NO_OPTION);
					if (scelta == JOptionPane.YES_OPTION){
						noleggio.exequery("DELETE FROM noleggio WHERE Cod_Noleggio="+codice+"","delete");
					JOptionPane.showMessageDialog(null , "Contratto Eliminato!");
					content.txtCodice.setText("");
					content.txtCodice.requestFocus();
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
	
/* Metodo. Modifica un contratto nel DB. */
	
	public void modifica(ModuloContratto content){
	
		if (check(content)){
			try{
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
	
	public void filtra(ModuloContratto content){
		if (checkfiltra(content)){
			try{
				if (cliente.equals("")){
					noleggio.exequery("SELECT * FROM noleggio WHERE Veicolo='"+veicolo+"'","select");
				}
				else if(veicolo.equals("")){
					noleggio.exequery("SELECT * FROM noleggio WHERE Cliente='"+cliente+"'","select");
				}
				else{
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
	
	/* Metodo. Verifica che i dati inseriti siano corretti. */
	
	private boolean check(ModuloContratto content){
		boolean check=true;
		/* Verifica se sono stati inseriti tutt i campi necessari. */
		if (content.comboBoxTipologia.getSelectedIndex()==0 || veicolo.equals("") || cliente.equals("") || inizio.equals("Seleziona una data") 
				|| fine.equals("Seleziona una data") || costo.equals("") || cognome.equals("") || nome.equals("") || patente.equals("")){		
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci tutti i campi indicati da un asterisco!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}else if (!veicolo.matches(TGPATTERN1) && !veicolo.matches(TGPATTERN2) && !veicolo.matches(TGPATTERN3) && !veicolo.matches(TGPATTERN4)){
			content.txtVeicolo.setText("");
			content.txtVeicolo.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La Targa del veicolo deve essere composta da: \n - Autoveicolo: 4 caratteri e 3 cifre (es. TO175RP); "
					+ "\n - Scooter: 3 caratteri e 3 cifre (es. X269DL); \n - Motocicletta e Quad-Bike: 2 caratteri e 5 cifre (es. AA12345);"
					+ "\n - Mezzo Acquatico: 2 caratteri e 4 cifre (es. 8PC567).",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (cliente.length()==16 && !cliente.matches(CFPATTERN)){
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! il Codice Fiscale inserito non è valido!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if((cliente.length()==11 && !cliente.matches("\\d{11}"))){
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La Partita IVA inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if(cliente.length()<11 || (cliente.length()>11 && cliente.length()<16) || cliente.length()>16){
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale deve avere 16 caratteri e la Partita IVA 11 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!isNumeric(costo) || costo.length()>10){
			content.txtCosto.setText("");
			content.txtCosto.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Costo Totale deve essere composto da meno di 10 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if ((!isNumeric(costo) || (acconto.length()>10)) && !acconto.equals("")){
			content.txtAcconto.setText("");
			content.txtAcconto.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Acconto deve essere composto da meno di 10 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (cognome.length()>15){
			content.txtCognome.setText("");
			content.txtCognome.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Cognome deve avere meno di 15 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (nome.length()>15){
			content.txtNome.setText("");
			content.txtNome.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Nome deve avere meno di 15 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (patente.length()>10 ||patente.length()<10 || !patente.matches(PTPATTERN)){
			content.txtPatente.setText("");
			content.txtPatente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Patente deve essere composta da 3 caratteri e 7 cifre (Es:TO1234567X)!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (valida.equals("Seleziona una data")){
			content.frmtdtxtfldValida.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci tutti i campi indicati da un asterisco!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (rilasciataDa.length()>20){
			content.txtRilasciatada.setText("");
			content.txtRilasciatada.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo 'Rilasciata Da' deve avere meno di 20 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (rilasciataIl.equals("Seleziona una data")){
			content.frmtdtxtfldRilasciatail.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci tutti i campi indicati da un asterisco!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		if (check==false){
			test=check; 
		}
		else {
			if(inizio.equals("Seleziona una data")){ 
				inizio="DEFAULT";
			}else{
				inizio="'"+inizio+"'";
			}
			if(fine.equals("Seleziona una data")){ 
				fine="DEFAULT";
			}else{ 
				fine="'"+fine+"'";
			}
			if(acconto.equals("")){
				acconto="NULL";
			}
			if(valida.equals("Seleziona una data")){
				valida="DEFAULT";
			}else{ valida="'"+valida+"'";
			}
			if(rilasciataDa.equals("")){
				rilasciataDa="";
			}
			if(rilasciataIl.equals("Seleziona una data")){
				rilasciataIl="DEFAULT";
			}else{
				rilasciataIl="'"+rilasciataIl+"'";
			}
			test=true;
		}
		return test;
	}
	
	/* Metodo. Verifica che il Cliente e/o il Veicolo da filtrare siano corretti. */
	
	private boolean checkfiltra(ModuloContratto content){
		boolean check=true;
		/* Verifica se è stato inserito almeno un campo. */
		if (cliente.equals("") && veicolo.equals("")){		
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci un cliente e/o un veicolo da filtrare!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}else if (cliente.length()==16 && !cliente.matches(CFPATTERN)){
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale del cliente da filtrare non è valido!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if((cliente.length()==11 && !cliente.matches("\\d{11}"))){
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! la Partita IVA del cliente da filtrare non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if(((cliente.length()<11 || (cliente.length()>11 && cliente.length()<16) || cliente.length()>16)) && !cliente.equals("")){
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale deve avere 16 caratteri e la Partita IVA 11 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if((!veicolo.matches(TGPATTERN1) && !veicolo.matches(TGPATTERN2) && !veicolo.matches(TGPATTERN3) && !veicolo.matches(TGPATTERN4)) && !veicolo.equals("")){
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
	
	/* Verifica la correttezza del codice contratto nel modulo "Elimina Contratto". */
	private boolean checkelimina(ModuloContratto content){
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
	
	/* Verifica la correttezza del codice contratto nel modulo "ModificaContratto" */
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
	/* Metodo. Verifica se una stringa è numerica. */
	
	private static boolean isNumeric(String string) {
	    
		return string!= null && string.matches("[-+]?\\d*\\.?\\d*");
	}
	
	
	/* METODI USATI DALLA GUI PER LA GESTIONE DEI CONTRATTI (--> vedi classe ModuloCt <--) */
	
	/* Metodo. Assegna i valori al contratto. */
	
	public void setValori(ModuloContratto content){
		
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
	
	public boolean setCodice (ModuloContratto content) {
		
		boolean esito = false;
		
		if (content.txtCodice.getText().trim().toString().equals("")){		
			JOptionPane.showMessageDialog(null, "Errore! Inserisci il codice di un contratto!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}else if (!content.txtCodice.getText().trim().toString().matches("\\d*")){
			JOptionPane.showMessageDialog(null, "Errore! Il codice deve essere composto da sole cifre!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
			content.txtCodice.setText("");
		}else {
			codice=Integer.decode((content.txtCodice.getText().trim()));
			esito = true;
		}
		
		return esito;
	}
	
	public boolean setCodiceCerca(ModuloContratto content){
		
		boolean esito = false;
		
		if (content.txtContrattoCerca.getText().trim().toString().equals("")){		
			JOptionPane.showMessageDialog(null, "Errore! Inserisci il codice di un contratto!",
				"Errore",
				JOptionPane.ERROR_MESSAGE);
		}else if (!content.txtContrattoCerca.getText().trim().toString().matches("\\d*")){
			JOptionPane.showMessageDialog(null, "Errore! Il codice deve essere composto da sole cifre!",
				"Errore",
				JOptionPane.ERROR_MESSAGE);
			content.txtContrattoCerca.setText("");
		}else {
			codiceCerca = Integer.decode(content.txtContrattoCerca.getText().trim());
			esito = true;
		}
		
		return esito;
	}
	
	public void setCodiceModifica (ModuloContratto content){
		
			codiceModifica = Integer.decode(content.txtContrattoCerca.getText().trim());
	}
	
	/* Metodo. Assegna i valori al contratto da filtrare. */
	
	public void setValoriFiltra(ModuloContratto content){
		cliente = content.txtCliente.getText().trim();
		veicolo = content.txtVeicolo.getText().trim();
	}
	
}
