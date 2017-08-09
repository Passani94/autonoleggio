package entita;

import javax.swing.JOptionPane;

import db.DBConnect;
import java.sql.SQLException;

import gui.moduli.ModuloFlotta;

/* Classe per l'entità Veicolo */

public class Veicolo {
	
	private boolean test;
	
	private DBConnect veicolo;
	
	private String targa;
	private String targaCerca;
	private String tipologia;
	private String marca;
	private String nome;
	private String disponibilita;
	private String alimentazione;
	private String km;
	private String dimensioni;
	private String immatricolazione;
	private String bollo;
	private String tagliando;
	private String assicurazione;
	private String ormeggio;
	private String alaggio;
	private String breve;
	private String lungo;
	
	private static final String TGPATTERN1 = "[a-zA-Z]{2}\\d\\d\\d[a-zA-Z]{2}"; //Pattern Targa Autoveicolo
	private static final String TGPATTERN2 = "[a-zA-Z]{1}\\d\\d\\d[a-zA-Z]{2}"; //Pattern Targa Scooter
	private static final String TGPATTERN3 = "[a-zA-Z]{2}\\d\\d\\d\\d\\d"; //Pattern Targa Motocicletta e Quad-Bike
	private static final String TGPATTERN4 = "\\d[a-zA-Z]{2}\\d\\d\\d"; //Pattern Targa Mezzo Acquatico
	
	/* Costruttore Veicolo */
	
	public Veicolo(){
		breve="";
		lungo="";
		test=true;
		veicolo = new DBConnect();
	}
	
	/* Metodo. Aggiunge un nuovo veicolo al DB. */
	
	public void aggiungi(ModuloFlotta content) {
		if (check(content,"aggiungi")) {
			try{ /* Cerca nel DB un veicolo con la targa inserita. */
				veicolo.exequery("SELECT * FROM veicolo where Targa='"+targa+"'","select"); 
				/* Verifica se il veicolo che si vuole aggiungere è già presente nel DB. */
				if (veicolo.rs.next()) {	
					JOptionPane.showMessageDialog(null, "Errore! Esiste già un veicolo con tale targa!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtTarga.setText("");
					content.txtTarga.requestFocus();
				} /* Aggiunge il nuovo veicolo. Inoltre resetta i campi della form per un nuovo inserimento. */
				else {	
					disponibilita = "SI";
					if (dimensioni.equals("lun/lar/alt")) {
						dimensioni = "";						
					}
					String valori="('"+targa+"','"+tipologia+"','"+marca+"','"+nome+"','"+disponibilita+"','"+alimentazione+"',"+km+",'"+dimensioni+"',"
							+ ""+immatricolazione+","+bollo+","+tagliando+","+assicurazione+","+ormeggio+","+alaggio+",'"+breve+"','"+lungo+"')";
					veicolo.exequery("INSERT INTO veicolo VALUES "+valori,"insert");
					JOptionPane.showMessageDialog(null , "Nuovo Veicolo Aggiunto!");
					content.txtTarga.setText("");
					content.comboBoxTipologia.setSelectedIndex(0);
					content.txtMarca.setText("");
					content.txtNome.setText("");
					content.comboBoxAlimentazione.setSelectedIndex(0);
					content.txtKm.setText("");
					content.txtDimensioni.setText("lun/lar/alt");
					content.frmtdtxtfldImma.setText("Seleziona una data");
					content.frmtdtxtfldBollo.setText("Seleziona una data");
					content.frmtdtxtfldTagliando.setText("Seleziona una data");
					content.frmtdtxtfldAssicurazione.setText("Seleziona una data");
					content.frmtdtxtfldOrmeggio.setText("Seleziona una data");
					content.frmtdtxtfldAlaggio.setText("Seleziona una data");
					content.comboBoxBreveTermine.setSelectedIndex(0);
					content.comboBoxLungoTermine.setSelectedIndex(0);
					content.txtTarga.requestFocus();
				}
				veicolo.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Veicolo non aggiunto!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo. Elimina un veicolo dal DB. */
	
	public void elimina(ModuloFlotta content){
		if (checkelimina(content)){
			try{ /* Verifica se è presente un veicolo con tale targa. */
				veicolo.exequery("SELECT * FROM veicolo where Targa='"+targa+"'","select"); 
				if(!veicolo.rs.next()){
					JOptionPane.showMessageDialog(null, "Errore! Non è presente un veicolo con tale targa!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtTarga.requestFocus();
				} else{
					int scelta = JOptionPane.showConfirmDialog(
							null,
							"Si desidera eliminare il veicolo targato "+targa+" ?",
							"Conferma eliminazione",
							JOptionPane.YES_NO_OPTION);
					if (scelta == JOptionPane.YES_OPTION){
						veicolo.exequery("DELETE FROM veicolo WHERE Targa='"+targa+"'","delete");
						JOptionPane.showMessageDialog(null , "Veicolo eliminato!");
						content.txtTarga.setText("");
						content.txtTarga.requestFocus();
					}
					
					
				}
				veicolo.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Veicolo non eliminato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/* Metodo. Cerca un veicolo nel DB */
	
	public void cerca(ModuloFlotta content){
		
		String item;
		if (checkcerca(content)){
			try{
				veicolo.exequery("SELECT * FROM veicolo where Targa='"+targaCerca+"'","select");
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
					content.comboBoxTipologia.setEnabled(false);
					content.txtMarca.setEditable(false);
					content.txtNome.setEditable(false);
					content.txtKm.setEditable(true);
					content.txtDimensioni.setEditable(false);
					content.frmtdtxtfldImma.setEditable(false);
					content.frmtdtxtfldBollo.setEditable(true);
					content.frmtdtxtfldTagliando.setEditable(true);
					content.frmtdtxtfldAssicurazione.setEditable(true);
					if (veicolo.rs.getString(2).equals("Barca_Motore") || veicolo.rs.getString(2).equals("Catamarano") || veicolo.rs.getString(2).equals("Gommone")) {
						content.frmtdtxtfldOrmeggio.setEnabled(true);
						content.frmtdtxtfldAlaggio.setEnabled(true);
					}else {
						content.frmtdtxtfldOrmeggio.setEnabled(false);
						content.frmtdtxtfldAlaggio.setEnabled(false);	
					}
				}else{
					JOptionPane.showMessageDialog(null, "Errore! Non è presente un veicolo con tale targa!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtTargaCerca.setText("");
					content.txtTargaCerca.requestFocus();
				}
				
				veicolo.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Veicolo non trovato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo. Modifica un veicolo nel DB. */
	
	public void modifica(ModuloFlotta content){
		if (check(content,"modifica")){
			try{
				String valori = "Tipologia='"+tipologia+"',Marca='"+marca+"',Nome='"+nome+"',Disponibilita='"+disponibilita+"',Marca='"+marca+"',"
						+ "Alimentazione='"+alimentazione+"',Km_Effettuati="+km+",Dimensioni='"+dimensioni+"',Data_Immatricolazione="+immatricolazione+","
								+ "Data_Scadenza_Bollo="+bollo+",Data_Scadenza_Tagliando="+tagliando+",Data_Scadenza_Assicurazione="+assicurazione+","
										+ "Data_Scadenza_Ormeggio="+ormeggio+",Data_Scadenza_Costo_Alaggio="+alaggio;
				veicolo.exequery("UPDATE veicolo SET "+valori+" WHERE Targa='"+targa+"'","update");
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
				
				veicolo.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Veicolo non modificato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo. Verificare che i dati inseriti siano corretti. */
	
	private boolean check(ModuloFlotta content, String tipo){
		boolean check=true;
		/* Verifica se sono stati inseriti tutti i campi necessari. */
		if (tipo.equals("aggiungi") && (targa.equals("") || content.comboBoxTipologia.getSelectedIndex() == 0 || marca.equals("") || nome.equals("")
				|| content.comboBoxAlimentazione.getSelectedIndex() == 0 || km.equals("") 
				|| content.comboBoxBreveTermine.getSelectedIndex() == 0)) {
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci tutti i campi indicati da un asterisco!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		} /* Verifica se sono stati inseriti tutti i campi necessari durante la modifica di un veicolo. */
		else if (tipo.equals("modifica") && (targa.equals("") || content.comboBoxTipologia.getSelectedIndex() == 0 || marca.equals("") || nome.equals("")
				|| content.comboBoxAlimentazione.getSelectedIndex() == 0 || km.equals(""))){		
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci tutti i campi indicati da un asterisco!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}else if(!targa.matches(TGPATTERN1) && !targa.matches(TGPATTERN2) && !targa.matches(TGPATTERN3) && !targa.matches(TGPATTERN4)){
			content.txtTarga.setText("");
			content.txtTarga.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La Targa del veicolo deve essere composta da: \n - Autoveicolo: 4 caratteri e 3 cifre (es. TO175RP); "
					+ "\n - Scooter: 3 caratteri e 3 cifre (es. X269DL); \n - Motocicletta e Quad-Bike: 2 caratteri e 5 cifre (es. AA12345);"
					+ "\n - Mezzo Acquatico: 2 caratteri e 4 cifre (es. 8PC567).",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (marca.length()>15){
			content.txtMarca.setText("");
			content.txtMarca.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Marca deve avere meno di 15 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (nome.length()>20){
			content.txtNome.setText("");
			content.txtNome.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Nome deve avere meno di 20 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!isNumeric(km) || km.length()>20){
			content.txtKm.setText("");
			content.txtKm.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Kilometri Effettuati deve essere composto da meno di 20 cifre!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (dimensioni.length()>20){
			content.txtDimensioni.setText("");
			content.txtDimensioni.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il campo Dimensione deve essere composto da meno di 20 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (immatricolazione.equals("Seleziona una data")){
			content.frmtdtxtfldImma.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di immatricolazione inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (bollo.equals("Seleziona una data") ){
			content.frmtdtxtfldBollo.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di scadenza bollo inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (tagliando.equals("Seleziona una data")){
			content.frmtdtxtfldTagliando.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di scadenza tagliando inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (assicurazione.equals("Seleziona una data")){
			content.frmtdtxtfldAssicurazione.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di scadenza assicurazione inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (ormeggio.equals("Seleziona una data") && ((content.comboBoxTipologia.getSelectedIndex()== 7)|| 
				((content.comboBoxTipologia.getSelectedIndex()== 10) || (content.comboBoxTipologia.getSelectedIndex()== 13)))){
			content.frmtdtxtfldOrmeggio.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di scadenza ormeggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (alaggio.equals("Seleziona una data") && ((content.comboBoxTipologia.getSelectedIndex()== 7)|| 
				((content.comboBoxTipologia.getSelectedIndex()== 10) || (content.comboBoxTipologia.getSelectedIndex()== 13)))){
			content.frmtdtxtfldAlaggio.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di scadenza alaggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!lungo.equals(breve) && content.comboBoxLungoTermine.getSelectedIndex() != 0 && tipo.equals("aggiungi")){
			content.comboBoxLungoTermine.setSelectedIndex(0);
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Il codice di \"Costo Lungo Termine\" deve corrispondere a \"Costo Breve Termine\"!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		if (check==false) test=check; 
			else {
				if(immatricolazione.equals("Seleziona una data")) immatricolazione="DEFAULT"; else immatricolazione="'"+immatricolazione+"'";
				if(bollo.equals("Seleziona una data")) bollo="DEFAULT"; else bollo="'"+bollo+"'";;
				if(tagliando.equals("Seleziona una data")) tagliando="DEFAULT"; else tagliando="'"+tagliando+"'";
				if(assicurazione.equals("Seleziona una data")) assicurazione="DEFAULT"; else assicurazione="'"+assicurazione+"'";
				if(ormeggio.equals("") || ormeggio.equals("Seleziona una data")) ormeggio="DEFAULT"; else ormeggio="'"+ormeggio+"'";
				if(alaggio.equals("") || alaggio.equals("Seleziona una data")) alaggio="DEFAULT"; else alaggio="'"+alaggio+"'";
				if (!lungo.equals("")) lungo=lungo.substring(11);
				test=true;}
		return test;
	}
	
	/* Metodo. Verifica la correttezza della targa del veicolo da eliminare. */
	
	private boolean checkelimina(ModuloFlotta content){
		boolean check=true;
		if(!targa.matches(TGPATTERN1) && !targa.matches(TGPATTERN2) && !targa.matches(TGPATTERN3) && !targa.matches(TGPATTERN4)){
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
	
	private boolean checkcerca(ModuloFlotta content){
		boolean check=true;
		if(!targaCerca.matches(TGPATTERN1) && !targaCerca.matches(TGPATTERN2) && !targaCerca.matches(TGPATTERN3) && !targaCerca.matches(TGPATTERN4)){
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
	
	
/* METODI USATI DALLA GUI PER LA GESTIONE DEI VEICOLI (--> vedi classe ModuloFl <--) */

/* Metodo. Assegna i valori al veicolo. */

	public void setValori(ModuloFlotta content, String tipo){
		targa = content.txtTarga.getText().trim();
		tipologia = content.comboBoxTipologia.getSelectedItem().toString();
		marca = content.txtMarca.getText().trim();
		nome = content.txtNome.getText().trim();
		alimentazione = content.comboBoxAlimentazione.getSelectedItem().toString();
		km = content.txtKm.getText().trim();
		dimensioni = content.txtDimensioni.getText().trim();
		immatricolazione = content.frmtdtxtfldImma.getText().trim();
		bollo = content.frmtdtxtfldBollo.getText().trim();
		tagliando = content.frmtdtxtfldTagliando.getText().trim();
		assicurazione = content.frmtdtxtfldAssicurazione.getText().trim();
		ormeggio = content.frmtdtxtfldOrmeggio.getText().trim();
		alaggio = content.frmtdtxtfldAlaggio.getText().trim();
		if (tipo.equals("aggiungi")) {
			breve = content.comboBoxBreveTermine.getSelectedItem().toString();
				if (content.comboBoxLungoTermine.getSelectedIndex() != 0) lungo = content.comboBoxLungoTermine.getSelectedItem().toString();
					}else {
					disponibilita = content.comboBoxDisponibilita.getSelectedItem().toString();
					}
	}

/* Metodo. Assegna solo la chiave (Targa) al veicolo. */

	public void setTarga(ModuloFlotta content){
		targa = content.txtTarga.getText().trim();
	}

/* Metodo. Assegna solo la chiave (Targa) al veicolo da cercare. */

	public void setTargaCerca(ModuloFlotta content){
		targaCerca = content.txtTargaCerca.getText().trim();
	}

	@Override
	public String toString() {
		return "Veicolo [test=" + test + ", veicolo=" + veicolo + ", targa=" + targa + ", targaCerca=" + targaCerca
				+ ", tipologia=" + tipologia + ", marca=" + marca + ", nome=" + nome + ", disponibilita="
				+ disponibilita + ", alimentazione=" + alimentazione + ", km=" + km + ", dimensioni=" + dimensioni
				+ ", immatricolazione=" + immatricolazione + ", bollo=" + bollo + ", tagliando=" + tagliando
				+ ", assicurazione=" + assicurazione + ", ormeggio=" + ormeggio + ", alaggio=" + alaggio + ", breve="
				+ breve + ", lungo=" + lungo + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veicolo other = (Veicolo) obj;
		if (alaggio == null) {
			if (other.alaggio != null)
				return false;
		} else if (!alaggio.equals(other.alaggio))
			return false;
		if (alimentazione == null) {
			if (other.alimentazione != null)
				return false;
		} else if (!alimentazione.equals(other.alimentazione))
			return false;
		if (assicurazione == null) {
			if (other.assicurazione != null)
				return false;
		} else if (!assicurazione.equals(other.assicurazione))
			return false;
		if (bollo == null) {
			if (other.bollo != null)
				return false;
		} else if (!bollo.equals(other.bollo))
			return false;
		if (breve == null) {
			if (other.breve != null)
				return false;
		} else if (!breve.equals(other.breve))
			return false;
		if (dimensioni == null) {
			if (other.dimensioni != null)
				return false;
		} else if (!dimensioni.equals(other.dimensioni))
			return false;
		if (disponibilita == null) {
			if (other.disponibilita != null)
				return false;
		} else if (!disponibilita.equals(other.disponibilita))
			return false;
		if (immatricolazione == null) {
			if (other.immatricolazione != null)
				return false;
		} else if (!immatricolazione.equals(other.immatricolazione))
			return false;
		if (km == null) {
			if (other.km != null)
				return false;
		} else if (!km.equals(other.km))
			return false;
		if (lungo == null) {
			if (other.lungo != null)
				return false;
		} else if (!lungo.equals(other.lungo))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (ormeggio == null) {
			if (other.ormeggio != null)
				return false;
		} else if (!ormeggio.equals(other.ormeggio))
			return false;
		if (tagliando == null) {
			if (other.tagliando != null)
				return false;
		} else if (!tagliando.equals(other.tagliando))
			return false;
		if (targa == null) {
			if (other.targa != null)
				return false;
		} else if (!targa.equals(other.targa))
			return false;
		if (targaCerca == null) {
			if (other.targaCerca != null)
				return false;
		} else if (!targaCerca.equals(other.targaCerca))
			return false;
		if (test != other.test)
			return false;
		if (tipologia == null) {
			if (other.tipologia != null)
				return false;
		} else if (!tipologia.equals(other.tipologia))
			return false;
		if (veicolo == null) {
			if (other.veicolo != null)
				return false;
		} else if (!veicolo.equals(other.veicolo))
			return false;
		return true;
	}

}

