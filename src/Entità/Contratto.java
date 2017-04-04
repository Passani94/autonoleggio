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
	public String Inizio;
	public String Fine;
	public String Costo;
	public String Pagato;
	public String Nome;
	public String Cognome;
	public String Patente;
	public String Rilasciatada;
	public String Rilasciatail;
	public String Valida;
	public String Cliente;
	public String Veicolo;
	private boolean test;
	private static final String CFPATTERN = "[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";
	private static final String TGPATTERN = "[a-zA-Z]{2}\\d\\d\\d[a-zA-Z]{2}";
	private static final String DATEPATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
	private static final String PTPATTERN = "^[a-zA-Z]{2}\\d{7}[a-zA-Z]{1}";
	private DBConnect noleggio;
	
	/* Costruttore Contratto */
	
	public Contratto(){
		test=true;
		noleggio = new DBConnect();
	}
	
	/* Metodo per assegnare i valori al Veicolo. */
	
	public void setValori(ModuloCt content){
		Tipologia = content.txtTipologia.getText().trim().toLowerCase();
		Inizio = content.frmtdtxtfldInizio.getText().trim();
		Fine = content.frmtdtxtfldFine.getText().trim();
		Costo = content.txtCosto.getText().trim();
		Pagato = content.txtPagato.getText().trim();
		Nome = content.txtNome.getText().trim();
		Cognome = content.txtCognome.getText().trim();
		Patente = content.txtPatente.getText().trim();
		Rilasciatada = content.txtRilasciatada.getText().trim();
		Rilasciatail = content.frmtdtxtfldRilasciatail.getText().trim();
		Valida = content.frmtdtxtfldValida.getText().trim();
		Cliente = content.txtCliente.getText().trim();
		Veicolo = content.txtVeicolo.getText().trim();
	}
	
	/* Metodo per assegnare i valori da filtrare. */
	
	public void setValoriFiltra(ModuloCt content){
		Cliente = content.txtCliente.getText().trim();
		Veicolo = content.txtVeicolo.getText().trim();
	}
	
	/* Metodo per Aggiungere il nuovo contratto al DB. */
	
	public void aggiungi(ModuloCt content){
		if (check(content)){
			try{
				noleggio.exequery("SELECT * FROM cliente where CF_PIVA='"+Cliente+"'","select"); /* Cerca se esiste il cliente associato al noleggio nel DB */
				if (!noleggio.rs.next()){	/* Verifica se esiste il cliente nel DB */
					JOptionPane.showMessageDialog(null, "Errore, il Cliente inserito non è presente!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtCliente.setText("");
					content.txtCliente.requestFocus();
					test=false;}
				noleggio.exequery("SELECT * FROM veicolo where Targa='"+Veicolo+"'","select"); /* Cerca se esiste il veicolo associato al noleggio nel DB */
				if (!noleggio.rs.next()){	/* Verifica se esiste il cliente nel DB */
					JOptionPane.showMessageDialog(null, "Errore, il Veicolo inserito non è presente!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtVeicolo.setText("");
					content.txtVeicolo.requestFocus();
					test=false;}
				if (test==true && noleggio.rs.getString("Disponibilita").equals("si")) {	/* Aggiunge il veicolo e resetta il form per poter inserirne uno nuovo. */
					String valori="(DEFAULT,'"+Tipologia+"',"+Inizio+","+Fine+","+Costo+","+Pagato+",'"+Nome+"','"+Cognome+"','"+Patente+"','"+Rilasciatada+"',"+Rilasciatail+","+Valida+",'"+Cliente+"','"+Veicolo+"')";
					noleggio.exequery("INSERT INTO noleggio VALUES "+valori,"insert");
					noleggio.exequery("UPDATE veicolo SET Disponibilita='no' WHERE Targa='"+Veicolo+"'","update"); /* Rende il veicolo non disponibile dopo il noleggio. */
					JOptionPane.showMessageDialog(null , "Nuovo Contratto di Noleggio Inserito!");
					content.txtTipologia.setText("");
					content.frmtdtxtfldInizio.setText("aaaa-mm-gg");
					content.frmtdtxtfldFine.setText("aaaa-mm-gg");
					content.txtCosto.setText("");
					content.txtPagato.setText("");
					content.txtNome.setText("");
					content.txtCognome.setText("");
					content.txtPatente.setText("");
					content.txtRilasciatada.setText("");
					content.frmtdtxtfldRilasciatail.setText("aaaa-mm-gg");
					content.frmtdtxtfldValida.setText("aaaa-mm-gg");
					content.txtCliente.setText("");
					content.txtVeicolo.setText("");
					content.txtTipologia.requestFocus();
				} else{
					JOptionPane.showMessageDialog(null, "Errore, Il veicolo inserito non è Disponibile per il noleggio!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
					content.txtVeicolo.setText("");
					content.txtVeicolo.requestFocus();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Contratto di Noleggio non Inserito!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo per filtrare la tabella. */
	
	public void filtra(ModuloCt content){
		if (checkfiltra(content)){
			try{
				if (Cliente.equals("")){noleggio.exequery("SELECT * FROM noleggio WHERE veicolo='"+Veicolo+"'","select");}
				else if(Veicolo.equals("")){noleggio.exequery("SELECT * FROM noleggio WHERE cliente='"+Cliente+"'","select");}
				else{noleggio.exequery("SELECT * FROM noleggio WHERE veicolo='"+Veicolo+"' AND cliente='"+Cliente+"'","select");}
				content.tblNoleggi.setModel(new CostruisciTabella(noleggio.rs).model);
				content.tblNoleggi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				TableColumnAdjuster tca = new TableColumnAdjuster(content.tblNoleggi);
				tca.adjustColumns();
				content.revalidate();
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Impossibile Filtrare i Contratti di Noleggio!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo per verificare la correttezza dei dati inseriti. */
	
	private boolean check(ModuloCt content){
		boolean check=true;
		if (Tipologia.equals("") || Inizio.equals("") || Inizio.equals("aaaa-mm-gg") || Fine.equals("") || Fine.equals("aaaa-mm-gg") || Costo.equals("") || Nome.equals("") || Cognome.equals("") || Patente.equals("") || Cliente.equals("") || Veicolo.equals("")){		/* Verifica se sono stati inseriti tutti i campi necessari */
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, Inserisci tutti i Campi con l'Asterisco!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}else if ((Tipologia.length()<5 || Tipologia.length()>5) && (!Tipologia.equals("breve") || !Tipologia.equals("lungo"))){
			content.txtTipologia.setText("");
			content.txtTipologia.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la tipologia di noleggio può essere Breve o Lungo!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if(!Inizio.matches(DATEPATTERN)){
			content.frmtdtxtfldInizio.setText("");
			content.frmtdtxtfldInizio.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la data di inizio noleggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if(!Fine.matches(DATEPATTERN)){
			content.frmtdtxtfldFine.setText("");
			content.frmtdtxtfldFine.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la data di fine noleggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!isNumeric(Costo) || Costo.length()>10){
			content.txtCosto.setText("");
			content.txtCosto.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il costo totale deve essere composto da meno di 10 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if ((!isNumeric(Pagato) || Pagato.length()>10) && !Pagato.equals("")){
			content.txtPagato.setText("");
			content.txtPagato.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la cifra già pagata deve essere composto da meno di 10 cifrei!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Nome.length()>15){
			content.txtNome.setText("");
			content.txtNome.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il nome deve avere meno di 15 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Cognome.length()>15){
			content.txtCognome.setText("");
			content.txtCognome.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il cognome deve avere meno di 15 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Patente.length()>10 ||Patente.length()<10 || !Patente.matches(PTPATTERN)){
			content.txtPatente.setText("");
			content.txtPatente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la patente deve essere composta da 3 caratteri e 7 cifre (Es:TO1234567X)!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Rilasciatada.length()>20){
			content.txtRilasciatada.setText("");
			content.txtRilasciatada.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il campo 'Rilasciata Da' deve avere meno di 20 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Rilasciatail.matches(DATEPATTERN) && !Rilasciatail.equals("") && !Rilasciatail.equals("aaaa-mm-gg")){
			content.frmtdtxtfldRilasciatail.setText("");
			content.frmtdtxtfldRilasciatail.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la data di rilascio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Valida.matches(DATEPATTERN) && !Valida.equals("") && !Valida.equals("aaaa-mm-gg")){
			content.frmtdtxtfldValida.setText("");
			content.frmtdtxtfldValida.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la data di fine validità inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Cliente.length()==16 && !Cliente.matches(CFPATTERN)){
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il codice fiscare inserito non è valido!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if((Cliente.length()==11 && !Cliente.matches("\\d{11}"))){
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la partita iva inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if(Cliente.length()<11 || (Cliente.length()>11 && Cliente.length()<16) || Cliente.length()>16){
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il codice fiscare deve avere 16 caratteri e la partita IVA 11 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Veicolo.length()<7 || Veicolo.length()>7 || !Veicolo.matches(TGPATTERN)){
			content.txtVeicolo.setText("");
			content.txtVeicolo.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la targa deve essere composta da 4 caratteri e 3 cifre(Es: TO175RP)!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		if (check==false) test=check; 
			else {
				if(Inizio.equals("") || Inizio.equals("aaaa-mm-gg")) Inizio="DEFAULT"; else Inizio="'"+Inizio+"'";
				if(Fine.equals("") || Fine.equals("aaaa-mm-gg")) Fine="DEFAULT"; else Fine="'"+Fine+"'";;
				if(Rilasciatail.equals("") || Rilasciatail.equals("aaaa-mm-gg")) Rilasciatail="DEFAULT"; else Rilasciatail="'"+Rilasciatail+"'";
				if(Valida.equals("") || Valida.equals("aaaa-mm-gg")) Valida="DEFAULT"; else Valida="'"+Valida+"'";
				if(Pagato.equals("")) Pagato="NULL";
				if(Rilasciatada.equals("")) Rilasciatada="NULL";
				test=true;}
		return test;
	}
	
	/* Metodo per verificare la correttezza del cliente e del veicolo da filtrare. */
	
	private boolean checkfiltra(ModuloCt content){
		boolean check=true;
		if (Cliente.equals("") && Veicolo.equals("")){		/* Verifica se è stato inserito almeno un campo */
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, Inserisci un Cliente e/o un Veicolo da filtrare!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}else if (Cliente.length()==16 && !Cliente.matches(CFPATTERN)){
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il codice fiscare del Cliente da filtrare non è valido!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if((Cliente.length()==11 && !Cliente.matches("\\d{11}"))){
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la partita iva del Cliente da filtrare non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if(((Cliente.length()<11 || (Cliente.length()>11 && Cliente.length()<16) || Cliente.length()>16)) && !Cliente.equals("")){
			content.txtCliente.setText("");
			content.txtCliente.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il codice fiscare deve avere 16 caratteri e la partita IVA 11 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if((Veicolo.length()<7 || Veicolo.length()>7 || !Veicolo.matches(TGPATTERN)) && !Veicolo.equals("")){
			content.txtVeicolo.setText("");
			content.txtVeicolo.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, La Targa del Veicolo deve essere composta da 4 caratteri e 3 cifre(Es: TO175RP)!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		if (check==false) test=check; 
		else test=true;
		return test;
	}
	
	/* Metodo per verificare se una stringa è numerica. */
	
	private static boolean isNumeric(String string) {
	    try {
	        Long.parseLong(string);
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}
}
