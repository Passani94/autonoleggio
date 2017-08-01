package Entità;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import GUI.Admin.ModuloCt;
import Utils.CostruisciTabella;
import Utils.TableColumnAdjuster;
import db.DBConnect;

/* Classe per l'entità Contratto */

public class Contratto {
	public String Tipologia;
	public String Veicolo;
	public String Cliente;
	public String Inizio;
	public String Fine;
	public String Costo;
	public String Acconto;
	public String Cognome;
	public String Nome;
	public String Patente;
	public String Valida;
	public String Rilasciatada;
	public String Rilasciatail;
	
	private boolean test;
	private static final String CFPATTERN = "[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";
	private static final String PTPATTERN = "^[a-zA-Z]{2}\\d{7}[a-zA-Z]{1}";
	private static final String TGPATTERN1 = "[a-zA-Z]{2}\\d\\d\\d[a-zA-Z]{2}"; //Pattern Targa Autoveicolo
	private static final String TGPATTERN2 = "[a-zA-Z]{1}\\d\\d\\d[a-zA-Z]{2}"; //Pattern Targa Scooter
	private static final String TGPATTERN3 = "[a-zA-Z]{2}\\d\\d\\d\\d\\d"; //Pattern Targa Motocicletta e Quad-Bike
	private static final String TGPATTERN4 = "\\d[a-zA-Z]{2}\\d\\d\\d"; //Pattern Targa Mezzo Acquatico
	private static final String DATEPATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
	private DBConnect noleggio;
	
	/* Costruttore Contratto */
	
	public Contratto(){
		test=true;
		noleggio = new DBConnect();
	}
	
	
	/* Metodo. Aggiunge un nuovo contratto al DB. */
	
	public void aggiungi(ModuloCt content){
		if (check(content)){
			try{ /* Cerca nel DB un cliente con il CF (o la Partita IVA) inseriti. */
				noleggio.exequery("SELECT * FROM cliente where CF_PIVA='"+Cliente+"'","select"); 
				/* Verifica se il cliente inserito è presente nel DB. */
				if (!noleggio.rs.next()){	
					JOptionPane.showMessageDialog(null, "Errore! Il cliente inserito non è presente!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtCliente.setText("");
					content.txtCliente.requestFocus();
					test=false;
				} /* Cerca nel DB un veicolo con la Targa inserita. */
				noleggio.exequery("SELECT * FROM veicolo where Targa='"+Veicolo+"'","select"); 
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
					String valori="(DEFAULT,'"+Tipologia+"','"+Veicolo+"','"+Cliente+"',"+Inizio+","+Fine+","+Costo+","+Acconto+",'"+Cognome+"',"
							+ "'"+Nome+"','"+Patente+"',"+Valida+",'"+Rilasciatada+"',"+Rilasciatail+")";
					/* Aggiunge il contratto di noleggio. Inoltre resetta i campi della form per un nuovo inserimento. */
					noleggio.exequery("INSERT INTO noleggio VALUES "+valori,"insert");
					/* Rende il veicolo noleggiato non disponibile. */
					noleggio.exequery("UPDATE veicolo SET Disponibilita='NO' WHERE Targa='"+Veicolo+"'","update"); 
					JOptionPane.showMessageDialog(null , "Nuovo contratto di noleggio inserito!");
					content.comboBoxTipologia.setSelectedIndex(0);
					content.txtVeicolo.setText("");
					content.txtCliente.setText("");
					content.frmtdtxtfldInizio.setText("aaaa-mm-gg");
					content.frmtdtxtfldFine.setText("aaaa-mm-gg");
					content.txtCosto.setText("");
					content.txtAcconto.setText("");
					content.txtCognome.setText("");
					content.txtNome.setText("");
					content.txtPatente.setText("");
					content.frmtdtxtfldValida.setText("aaaa-mm-gg");
					content.txtRilasciatada.setText("");
					content.frmtdtxtfldRilasciatail.setText("aaaa-mm-gg");
				} else{
					JOptionPane.showMessageDialog(null, "Errore! Il veicolo inserito non è disponibile per il noleggio!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
					content.txtVeicolo.setText("");
					content.txtVeicolo.requestFocus();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Contratto di noleggio non inserito!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo. Filtra la tabella dei contratti. */
	
	public void filtra(ModuloCt content){
		if (checkfiltra(content)){
			try{
				if (Cliente.equals("")){
					noleggio.exequery("SELECT * FROM noleggio WHERE Veicolo='"+Veicolo+"'","select");
				}
				else if(Veicolo.equals("")){
					noleggio.exequery("SELECT * FROM noleggio WHERE Cliente='"+Cliente+"'","select");
				}
				else{
					noleggio.exequery("SELECT * FROM noleggio WHERE Veicolo='"+Veicolo+"' AND Cliente='"+Cliente+"'","select");
				}
				content.tblNoleggi.setModel(new CostruisciTabella(noleggio.rs).model);
				content.tblNoleggi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				TableColumnAdjuster tca = new TableColumnAdjuster(content.tblNoleggi);
				tca.adjustColumns();
				content.revalidate();
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Impossibile filtrare i contratti di noleggio!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo. Verifica che i dati inseriti siano corretti. */
	
	private boolean check(ModuloCt content){
		boolean check=true;
		/* Verifica se sono stati inseriti tutt i campi necessari. */
		if (content.comboBoxTipologia.getSelectedIndex()==0 || Veicolo.equals("") || Cliente.equals("") || Inizio.equals("") || Inizio.equals("aaaa-mm-gg") 
				|| Fine.equals("") || Fine.equals("aaaa-mm-gg") || Costo.equals("") || Cognome.equals("") || Nome.equals("") || Patente.equals("")){		
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci tutti i campi indicati da un asterisco!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}else if (!Veicolo.matches(TGPATTERN1) && !Veicolo.matches(TGPATTERN2) && !Veicolo.matches(TGPATTERN3) && !Veicolo.matches(TGPATTERN4)){
			content.txtVeicolo.setText("");
			content.txtVeicolo.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La Targa del veicolo deve essere composta da: \n - Autoveicolo: 4 caratteri e 3 cifre (es. TO175RP); "
					+ "\n - Scooter: 3 caratteri e 3 cifre (es. X269DL); \n - Motocicletta e Quad-Bike: 2 caratteri e 5 cifre (es. AA12345);"
					+ "\n - Mezzo Acquatico: 2 caratteri e 4 cifre (es. 8PC567).",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Cliente.length()==16 && !Cliente.matches(CFPATTERN)){
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! il Codice Fiscale inserito non è valido!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if((Cliente.length()==11 && !Cliente.matches("\\d{11}"))){
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La Partita IVA inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if(Cliente.length()<11 || (Cliente.length()>11 && Cliente.length()<16) || Cliente.length()>16){
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale deve avere 16 caratteri e la Partita IVA 11 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if(!Inizio.matches(DATEPATTERN)){
			content.frmtdtxtfldInizio.setText("");
			content.frmtdtxtfldInizio.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di inizio noleggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if(!Fine.matches(DATEPATTERN)){
			content.frmtdtxtfldFine.setText("");
			content.frmtdtxtfldFine.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di fine noleggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!isNumeric(Costo) || Costo.length()>10){
			content.txtCosto.setText("");
			content.txtCosto.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Costo Totale deve essere composto da meno di 10 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if ((!isNumeric(Acconto) || Acconto.length()>10) && !Acconto.equals("")){
			content.txtAcconto.setText("");
			content.txtAcconto.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Acconto deve essere composto da meno di 10 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Cognome.length()>15){
			content.txtCognome.setText("");
			content.txtCognome.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Cognome deve avere meno di 15 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Nome.length()>15){
			content.txtNome.setText("");
			content.txtNome.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Nome deve avere meno di 15 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Patente.length()>10 ||Patente.length()<10 || !Patente.matches(PTPATTERN)){
			content.txtPatente.setText("");
			content.txtPatente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Patente deve essere composta da 3 caratteri e 7 cifre (Es:TO1234567X)!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Valida.matches(DATEPATTERN) && !Valida.equals("") && !Valida.equals("aaaa-mm-gg")){
			content.frmtdtxtfldValida.setText("");
			content.frmtdtxtfldValida.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di fine validità inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Rilasciatada.length()>20){
			content.txtRilasciatada.setText("");
			content.txtRilasciatada.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo 'Rilasciata Da' deve avere meno di 20 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Rilasciatail.matches(DATEPATTERN) && !Rilasciatail.equals("") && !Rilasciatail.equals("aaaa-mm-gg")){
			content.frmtdtxtfldRilasciatail.setText("");
			content.frmtdtxtfldRilasciatail.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di rilascio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		if (check==false){
			test=check; 
		}
		else {
			if(Inizio.equals("") || Inizio.equals("aaaa-mm-gg")){ 
				Inizio="DEFAULT";
			}else{
				Inizio="'"+Inizio+"'";
			}
			if(Fine.equals("") || Fine.equals("aaaa-mm-gg")){ 
				Fine="DEFAULT";
			}else{ 
				Fine="'"+Fine+"'";
			}
			if(Acconto.equals("")){
				Acconto="NULL";
			}
			if(Valida.equals("") || Valida.equals("aaaa-mm-gg")){
				Valida="DEFAULT";
			}else{ Valida="'"+Valida+"'";
			}
			if(Rilasciatada.equals("")){
				Rilasciatada="NULL";
			}
			if(Rilasciatail.equals("") || Rilasciatail.equals("aaaa-mm-gg")){
				Rilasciatail="DEFAULT";
			}else{
				Rilasciatail="'"+Rilasciatail+"'";
			}
			test=true;
		}
		return test;
	}
	
	/* Metodo. Verifica che il Cliente e/o il Veicolo da filtrare siano corretti. */
	
	private boolean checkfiltra(ModuloCt content){
		boolean check=true;
		/* Verifica se è stato inserito almeno un campo. */
		if (Cliente.equals("") && Veicolo.equals("")){		
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci un cliente e/o un veicolo da filtrare!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}else if (Cliente.length()==16 && !Cliente.matches(CFPATTERN)){
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale del cliente da filtrare non è valido!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if((Cliente.length()==11 && !Cliente.matches("\\d{11}"))){
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! la Partita IVA del cliente da filtrare non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if(((Cliente.length()<11 || (Cliente.length()>11 && Cliente.length()<16) || Cliente.length()>16)) && !Cliente.equals("")){
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il Codice Fiscale deve avere 16 caratteri e la Partita IVA 11 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if((!Veicolo.matches(TGPATTERN1) && !Veicolo.matches(TGPATTERN2) && !Veicolo.matches(TGPATTERN3) && !Veicolo.matches(TGPATTERN4)) && !Veicolo.equals("")){
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
	
	/* Metodo. Verifica se una stringa è numerica. */
	
	private static boolean isNumeric(String string) {
	    try {
	        Long.parseLong(string);
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}
	
	
	/***** METODI USATI DALLA GUI PER LA GESTIONE DEI CONTRATTI (--> vedi classe ModuloCt <--) *****/
	
	/* Metodo. Assegna i valori al contratto. */
	
	public void setValori(ModuloCt content){
		Tipologia = content.comboBoxTipologia.getSelectedItem().toString();
		Veicolo = content.txtVeicolo.getText().trim();
		Cliente = content.txtCliente.getText().trim();
		Inizio = content.frmtdtxtfldInizio.getText().trim();
		Fine = content.frmtdtxtfldFine.getText().trim();
		Costo = content.txtCosto.getText().trim();
		Acconto = content.txtAcconto.getText().trim();
		Cognome = content.txtCognome.getText().trim();
		Nome = content.txtNome.getText().trim();
		Patente = content.txtPatente.getText().trim();
		Valida = content.frmtdtxtfldValida.getText().trim();
		Rilasciatada = content.txtRilasciatada.getText().trim();
		Rilasciatail = content.frmtdtxtfldRilasciatail.getText().trim();
	}
	
	/* Metodo. Assegna i valori al contratto da filtrare. */
	
	public void setValoriFiltra(ModuloCt content){
		Cliente = content.txtCliente.getText().trim();
		Veicolo = content.txtVeicolo.getText().trim();
	}
	
}
