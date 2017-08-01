package Entità;

import javax.swing.JOptionPane;

import GUI.Admin.ModuloFl;
import db.DBConnect;

/* Classe per l'entità Veicolo */

public class Veicolo {
	
	private boolean test;
	
	private DBConnect veicolo;
	
	private String Targa;
	private String TargaCerca;
	private String Tipologia;
	private String Marca;
	private String Nome;
	private String Disp;
	private String Alimentazione;
	private String Km;
	private String Dimensioni;
	private String Imma;
	private String Bollo;
	private String Tagliando;
	private String Assicurazione;
	private String Ormeggio;
	private String Alaggio;
	private String Breve;
	private String Lungo;
	private static final String TGPATTERN1 = "[a-zA-Z]{2}\\d\\d\\d[a-zA-Z]{2}"; //Pattern Targa Autoveicolo
	private static final String TGPATTERN2 = "[a-zA-Z]{1}\\d\\d\\d[a-zA-Z]{2}"; //Pattern Targa Scooter
	private static final String TGPATTERN3 = "[a-zA-Z]{2}\\d\\d\\d\\d\\d"; //Pattern Targa Motocicletta e Quad-Bike
	private static final String TGPATTERN4 = "\\d[a-zA-Z]{2}\\d\\d\\d"; //Pattern Targa Mezzo Acquatico
	private static final String DATEPATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
	
	/* Costruttore Veicolo */
	
	public Veicolo(){
		Breve="";
		Lungo="";
		test=true;
		veicolo = new DBConnect();
	}
	
	/* Metodo. Aggiunge un nuovo veicolo al DB. */
	
	public void aggiungi(ModuloFl content) {
		if (check(content,"aggiungi")) {
			try{ /* Cerca nel DB un veicolo con la targa inserita. */
				veicolo.exequery("SELECT * FROM veicolo where Targa='"+Targa+"'","select"); 
				/* Verifica se il veicolo che si vuole aggiungere è già presente nel DB. */
				if (veicolo.rs.next()) {	
					JOptionPane.showMessageDialog(null, "Errore! Esiste già un veicolo con tale targa!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtTarga.setText("");
					content.txtTarga.requestFocus();
				} /* Aggiunge il nuovo veicolo. Inoltre resetta i campi della form per un nuovo inserimento. */
				else {	
					Disp = "SI";
					String valori="('"+Targa+"','"+Tipologia+"','"+Marca+"','"+Nome+"','"+Disp+"','"+Alimentazione+"',"+Km+",'"+Dimensioni+"',"
							+ ""+Imma+","+Bollo+","+Tagliando+","+Assicurazione+","+Ormeggio+","+Alaggio+",'"+Breve+"','"+Lungo+"')";
					veicolo.exequery("INSERT INTO veicolo VALUES "+valori,"insert");
					JOptionPane.showMessageDialog(null , "Nuovo Veicolo Aggiunto!");
					content.txtTarga.setText("");
					content.comboBoxTipologia.setSelectedIndex(0);
					content.txtMarca.setText("");
					content.txtNome.setText("");
					content.comboBoxAlimentazione.setSelectedIndex(0);
					content.txtKm.setText("");
					content.txtDimensioni.setText("");
					content.frmtdtxtfldImma.setText("aaaa-mm-gg");
					content.frmtdtxtfldBollo.setText("aaaa-mm-gg");
					content.frmtdtxtfldTagliando.setText("aaaa-mm-gg");
					content.frmtdtxtfldAssicurazione.setText("aaaa-mm-gg");
					content.frmtdtxtfldOrmeggio.setText("aaaa-mm-gg");
					content.frmtdtxtfldAlaggio.setText("aaaa-mm-gg");
					content.comboBoxBreveTermine.setSelectedIndex(0);
					content.comboBoxLungoTermine.setSelectedIndex(0);
					content.txtTarga.requestFocus();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Veicolo non aggiunto!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo. Elimina un veicolo dal DB. */
	
	public void elimina(ModuloFl content){
		if (checkelimina(content)){
			try{ /* Verifica se è presente un veicolo con tale targa. */
				veicolo.exequery("SELECT * FROM veicolo where Targa='"+Targa+"'","select"); 
				if(veicolo.rs.next()){
					veicolo.exequery("DELETE FROM veicolo WHERE Targa='"+Targa+"'","delete");
					JOptionPane.showMessageDialog(null , "Veicolo eliminato!");
					content.txtTarga.setText("");
					content.txtTarga.requestFocus();
				} else{
					JOptionPane.showMessageDialog(null, "Errore! Non è presente un veicolo con tale targa!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtTarga.setText("");
					content.txtTarga.requestFocus();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Veicolo non eliminato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/* Metodo. Cerca un veicolo nel DB */
	
	public void cerca(ModuloFl content){
		
		String item;
		if (checkcerca(content)){
			try{
				veicolo.exequery("SELECT * FROM veicolo where Targa='"+TargaCerca+"'","select");
				if (veicolo.rs.next()){					
					content.txtTarga.setText(veicolo.rs.getString(1));
					
					for (int i=1; i<21; i++) {
			            item=content.comboBoxTipologia.getItemAt(i);
			            if (item.equals(veicolo.rs.getString(2))) {			 
			              content.comboBoxTipologia.setSelectedIndex(i);			 
			            }
			          }
			 
					content.txtMarca.setText(veicolo.rs.getString(3));
					content.txtNome.setText(veicolo.rs.getString(4));
					
					for (int i=1; i<3; i++) {						 
			            item=content.comboBoxDisponibilita.getItemAt(i);			 
			            if (item.equals(veicolo.rs.getString(5))) {
			              content.comboBoxDisponibilita.setSelectedIndex(i);			 
			            }
			          }		          
			 
			        for (int i=1; i<5; i++) {
			            item=content.comboBoxAlimentazione.getItemAt(i);			 
			            if (item.equals(veicolo.rs.getString(6))) {			 
			              content.comboBoxAlimentazione.setSelectedIndex(i);
			            }			 
			          }
			 
					content.txtKm.setText(veicolo.rs.getString(7));
					content.txtDimensioni.setText(veicolo.rs.getString(8));
					content.frmtdtxtfldImma.setText(veicolo.rs.getString(9));
					content.frmtdtxtfldBollo.setText(veicolo.rs.getString(10));
					content.frmtdtxtfldTagliando.setText(veicolo.rs.getString(11));
					content.frmtdtxtfldAssicurazione.setText(veicolo.rs.getString(12));
					content.frmtdtxtfldOrmeggio.setText(veicolo.rs.getString(13));
					content.frmtdtxtfldAlaggio.setText(veicolo.rs.getString(14));
					content.txtMarca.setEditable(true);
					content.txtNome.setEditable(true);
					content.txtKm.setEditable(true);
					content.txtDimensioni.setEditable(true);
					content.frmtdtxtfldImma.setEditable(true);
					content.frmtdtxtfldBollo.setEditable(true);
					content.frmtdtxtfldTagliando.setEditable(true);
					content.frmtdtxtfldAssicurazione.setEditable(true);
					content.frmtdtxtfldOrmeggio.setEditable(true);
					content.frmtdtxtfldAlaggio.setEditable(true);
				}else{
					JOptionPane.showMessageDialog(null, "Errore! Non è presente un veicolo con tale targa!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtTargaCerca.setText("");
					content.txtTargaCerca.requestFocus();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Veicolo non trovato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo. Modifica un veicolo nel DB. */
	
	public void modifica(ModuloFl content){
		if (check(content,"modifica")){
			try{
				String valori = "Tipologia='"+Tipologia+"',Marca='"+Marca+"',Nome='"+Nome+"',Disponibilita='"+Disp+"',Marca='"+Marca+"',"
						+ "Alimentazione='"+Alimentazione+"',Km_Effettuati="+Km+",Dimensioni='"+Dimensioni+"',Data_Immatricolazione="+Imma+","
								+ "Data_Scadenza_Bollo="+Bollo+",Data_Scadenza_Tagliando="+Tagliando+",Data_Scadenza_Assicurazione="+Assicurazione+","
										+ "Data_Scadenza_Ormeggio="+Ormeggio+",Data_Scadenza_Costo_Alaggio="+Alaggio;
				veicolo.exequery("UPDATE veicolo SET "+valori+" WHERE Targa='"+Targa+"'","update");
				JOptionPane.showMessageDialog(null , "Veicolo modificato!");
				content.txtTargaCerca.setText("");
				content.txtTargaCerca.requestFocus();
				content.txtTargaCerca.setEditable(true);
				content.comboBoxTipologia.setEditable(false);
				content.txtMarca.setEditable(false);
				content.txtNome.setEditable(false);
				content.comboBoxDisponibilita.setEditable(false);
				content.comboBoxAlimentazione.setEditable(false);
				content.txtKm.setEditable(false);
				content.txtDimensioni.setEditable(false);
				content.frmtdtxtfldImma.setEditable(false);
				content.frmtdtxtfldBollo.setEditable(false);
				content.frmtdtxtfldTagliando.setEditable(false);
				content.frmtdtxtfldAssicurazione.setEditable(false);
				content.frmtdtxtfldOrmeggio.setEditable(false);
				content.frmtdtxtfldAlaggio.setEditable(false);
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Veicolo non modificato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo. Verificare che i dati inseriti siano corretti. */
	
	private boolean check(ModuloFl content, String tipo){
		boolean check=true;
		/* Verifica se sono stati inseriti tutti i campi necessari. */
		if ((Targa.equals("") || content.comboBoxTipologia.getSelectedIndex() == 0 || Marca.equals("") || Nome.equals("")
				|| content.comboBoxAlimentazione.getSelectedIndex() == 0 || Km.equals("") 
				|| content.comboBoxBreveTermine.getSelectedIndex() == 0) && tipo.equals("aggiungi")) {
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci tutti i campi indicati da un asterisco!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		} /* Verifica se sono stati inseriti tutti i campi necessari durante la modifica di un veicolo. */
		else if ((Targa.equals("") || content.comboBoxTipologia.getSelectedIndex() == 0 || Marca.equals("") || Nome.equals("")
				|| content.comboBoxAlimentazione.getSelectedIndex() == 0 || Km.equals("") 
				) && tipo.equals("modifica")){		
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci tutti i campi indicati da un asterisco!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}else if(!Targa.matches(TGPATTERN1) && !Targa.matches(TGPATTERN2) && !Targa.matches(TGPATTERN3) && !Targa.matches(TGPATTERN4)){
			content.txtTarga.setText("");
			content.txtTarga.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La Targa del veicolo deve essere composta da: \n - Autoveicolo: 4 caratteri e 3 cifre (es. TO175RP); "
					+ "\n - Scooter: 3 caratteri e 3 cifre (es. X269DL); \n - Motocicletta e Quad-Bike: 2 caratteri e 5 cifre (es. AA12345);"
					+ "\n - Mezzo Acquatico: 2 caratteri e 4 cifre (es. 8PC567).",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Marca.length()>15){
			content.txtMarca.setText("");
			content.txtMarca.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Marca deve avere meno di 15 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Nome.length()>20){
			content.txtNome.setText("");
			content.txtNome.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Nome deve avere meno di 20 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!isNumeric(Km) || Km.length()>20){
			content.txtKm.setText("");
			content.txtKm.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Kilometri Effettuati deve essere composto da meno di 20 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Dimensioni.length()>20){
			content.txtDimensioni.setText("");
			content.txtDimensioni.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Dimensione deve essere composto da meno di 20 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Imma.matches(DATEPATTERN) && !Imma.equals("") && !Imma.equals("aaaa-mm-gg")){
			content.frmtdtxtfldImma.setText("");
			content.frmtdtxtfldImma.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di immatricolazione inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Bollo.matches(DATEPATTERN) && !Bollo.equals("") && !Bollo.equals("aaaa-mm-gg")){
			content.frmtdtxtfldBollo.setText("");
			content.frmtdtxtfldBollo.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di scadenza bollo inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Tagliando.matches(DATEPATTERN) && !Tagliando.equals("") && !Tagliando.equals("aaaa-mm-gg")){
			content.frmtdtxtfldTagliando.setText("");
			content.frmtdtxtfldTagliando.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di scadenza tagliando inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Assicurazione.matches(DATEPATTERN) && !Assicurazione.equals("") && !Assicurazione.equals("aaaa-mm-gg")){
			content.frmtdtxtfldAssicurazione.setText("");
			content.frmtdtxtfldAssicurazione.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di scadenza assicurazione inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Ormeggio.matches(DATEPATTERN) && !Ormeggio.equals("") && !Ormeggio.equals("aaaa-mm-gg")){
			content.frmtdtxtfldOrmeggio.setText("");
			content.frmtdtxtfldOrmeggio.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di scadenza ormeggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Alaggio.matches(DATEPATTERN) && !Alaggio.equals("") && !Alaggio.equals("aaaa-mm-gg")){
			content.frmtdtxtfldAlaggio.setText("");
			content.frmtdtxtfldAlaggio.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di scadenza alaggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Lungo.equals(Breve) && content.comboBoxLungoTermine.getSelectedIndex() != 0 && tipo.equals("aggiungi")){
			content.comboBoxLungoTermine.setSelectedIndex(0);
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il codice di \"Costo Lungo Termine\" deve corrispondere a \"Costo Breve Termine\"!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		if (check==false) test=check; 
			else {
				if(Imma.equals("") || Imma.equals("aaaa-mm-gg")) Imma="DEFAULT"; else Imma="'"+Imma+"'";
				if(Bollo.equals("") || Bollo.equals("aaaa-mm-gg")) Bollo="DEFAULT"; else Bollo="'"+Bollo+"'";;
				if(Tagliando.equals("") || Tagliando.equals("aaaa-mm-gg")) Tagliando="DEFAULT"; else Tagliando="'"+Tagliando+"'";
				if(Assicurazione.equals("") || Assicurazione.equals("aaaa-mm-gg")) Assicurazione="DEFAULT"; else Assicurazione="'"+Assicurazione+"'";
				if(Ormeggio.equals("") || Ormeggio.equals("aaaa-mm-gg")) Ormeggio="DEFAULT"; else Ormeggio="'"+Ormeggio+"'";
				if(Alaggio.equals("") || Alaggio.equals("aaaa-mm-gg")) Alaggio="DEFAULT"; else Alaggio="'"+Alaggio+"'";
				if (!Lungo.equals("")) Lungo=Lungo.substring(11);
				test=true;}
		return test;
	}
	
	/* Metodo. Verifica la correttezza della targa del veicolo da eliminare. */
	
	private boolean checkelimina(ModuloFl content){
		boolean check=true;
		if(!Targa.matches(TGPATTERN1) && !Targa.matches(TGPATTERN2) && !Targa.matches(TGPATTERN3) && !Targa.matches(TGPATTERN4)){
			content.txtTarga.setText("");
			content.txtTarga.requestFocus();
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

	/* Metodo. Verifica la correttezza della targa del veicolo da cercare. */
	
	private boolean checkcerca(ModuloFl content){
		boolean check=true;
		if(!TargaCerca.matches(TGPATTERN1) && !TargaCerca.matches(TGPATTERN2) && !TargaCerca.matches(TGPATTERN3) && !TargaCerca.matches(TGPATTERN4)){
			content.txtTargaCerca.setText("");
			content.txtTargaCerca.requestFocus();
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
	
	
/***** METODI USATI DALLA GUI PER LA GESTIONE DEI VEICOLI (--> vedi classe ModuloFl <--) *****/

/* Metodo. Assegna i valori al veicolo. */

public void setValori(ModuloFl content, String tipo){
	Targa = content.txtTarga.getText().trim();
	Tipologia = content.comboBoxTipologia.getSelectedItem().toString();
	Marca = content.txtMarca.getText().trim();
	Nome = content.txtNome.getText().trim();
	Alimentazione = content.comboBoxAlimentazione.getSelectedItem().toString();
	Km = content.txtKm.getText().trim();
	Dimensioni = content.txtDimensioni.getText().trim();
	Imma = content.frmtdtxtfldImma.getText().trim();
	Bollo = content.frmtdtxtfldBollo.getText().trim();
	Tagliando = content.frmtdtxtfldTagliando.getText().trim();
	Assicurazione = content.frmtdtxtfldAssicurazione.getText().trim();
	Ormeggio = content.frmtdtxtfldOrmeggio.getText().trim();
	Alaggio = content.frmtdtxtfldAlaggio.getText().trim();
	if (tipo.equals("aggiungi")) {
		Breve = content.comboBoxBreveTermine.getSelectedItem().toString();
		if (content.comboBoxLungoTermine.getSelectedIndex() != 0) Lungo = content.comboBoxLungoTermine.getSelectedItem().toString();
	}else {
		Disp = content.comboBoxDisponibilita.getSelectedItem().toString();
	}
}

/* Metodo. Assegna solo la chiave (Targa) al veicolo. */

public void setTarga(ModuloFl content){
	Targa = content.txtTarga.getText().trim();
}

/* Metodo. Assegna solo la chiave (Targa) al veicolo da cercare. */

public void setTargaCerca(ModuloFl content){
	TargaCerca = content.txtTargaCerca.getText().trim();
}

}

