package Entità;

import javax.swing.JOptionPane;

import GUI.Admin.ModuloFl;
import db.DBConnect;

/* Classe per l'entità Veicolo */

public class Veicolo {
	private String Targa;
	private String TargaCerca;
	private String Tipologia;
	private String Nome;
	private String Disp;
	private String Marca;
	private String Alimentazione;
	private String Km;
	private String Dimensioni;
	private String Imma;
	private String Bollo;
	private String Tagliando;
	private String Assicurazione;
	private String Ormeggio;
	private String Alaggio;
	private String Breve="";
	private String Lungo="";
	private boolean test=true;
	private DBConnect veicolo = new DBConnect();
	private static final String TGPATTERN = "[a-zA-Z]{2}\\d\\d\\d[a-zA-Z]{2}";
	private static final String DATEPATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
	
	/* Costruttore Veicolo */
	
	public Veicolo(){
		
	}
	
	/* Metodo per Aggiungere il nuovo veicolo al DB. */
	
	public void aggiungi(ModuloFl content){
		if (check(content,"aggiungi")){
			try{
				veicolo.exequery("SELECT * FROM veicolo where Targa='"+Targa+"'","select"); /* Cerca se esiste già il veicolo nel DB */
				if (veicolo.rs.next()){	/* Verifica se esiste già il veicolo nel DB */
					JOptionPane.showMessageDialog(null, "Errore, il Veicolo con la Targa inserita è già presente!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtTarga.setText("");
					content.txtTarga.requestFocus();
				}else {	/* Aggiunge il veicolo e resetta il form per poter inserire un nuovo veicolo */
					String valori="('"+Targa+"','"+Tipologia+"','"+Nome+"','"+Disp+"','"+Marca+"','"+Alimentazione+"',"+Km+",'"+Dimensioni+"',"+Imma+","+Bollo+","+Tagliando+","+Assicurazione+","+Ormeggio+","+Alaggio+",'"+Breve+"','"+Lungo+"')";
					veicolo.exequery("INSERT INTO veicolo VALUES "+valori,"insert");
					JOptionPane.showMessageDialog(null , "Nuovo Veicolo Aggiunto!");
					content.txtTarga.setText("");
					content.txtTipologia.setText("");
					content.txtNome.setText("");
					content.txtDisp.setText("");
					content.txtMarca.setText("");
					content.txtAlimentazione.setText("");
					content.txtKm.setText("");
					content.txtDimensioni.setText("");
					content.frmtdtxtfldImma.setText("aaaa-mm-gg");
					content.frmtdtxtfldBollo.setText("aaaa-mm-gg");
					content.frmtdtxtfldTagliando.setText("aaaa-mm-gg");
					content.frmtdtxtfldAssicurazione.setText("aaaa-mm-gg");
					content.frmtdtxtfldOrmeggio.setText("aaaa-mm-gg");
					content.frmtdtxtfldAlaggio.setText("aaaa-mm-gg");
					content.lstBreve.clearSelection();
					content.lstLungo.clearSelection();
					content.txtTarga.requestFocus();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Veicolo non Aggiunto!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo usato per eliminare il veicolo dal DB. */
	
	public void elimina(ModuloFl content){
		if (checkelimina(content)){
			try{
				veicolo.exequery("SELECT * FROM veicolo where Targa='"+Targa+"'","select"); /* Controlla se il veicolo è presente e può essere eliminato. */
				if(veicolo.rs.next()){
					veicolo.exequery("DELETE FROM veicolo WHERE Targa='"+Targa+"'","delete");
					JOptionPane.showMessageDialog(null , "Veicolo Eliminato!");
					content.txtTarga.setText("");
					content.txtTarga.requestFocus();
				} else{
					JOptionPane.showMessageDialog(null, "Errore, Il Veicolo con la Targa non è presente nel DB!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtTarga.setText("");
					content.txtTarga.requestFocus();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Targa non Eliminato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/* Metodo per cercare un veicolo nel DB. */
	
	public void cerca(ModuloFl content){
		if (checkcerca(content)){
			try{
				veicolo.exequery("SELECT * FROM veicolo where Targa='"+TargaCerca+"'","select");
				if (veicolo.rs.next()){
					content.txtTargaCerca.setEditable(false);
					content.txtTipologia.requestFocus();
					content.txtTarga.setText(veicolo.rs.getString(1));
					content.txtTipologia.setText(veicolo.rs.getString(2));
					content.txtNome.setText(veicolo.rs.getString(3));
					content.txtDisp.setText(veicolo.rs.getString(4));
					content.txtMarca.setText(veicolo.rs.getString(5));
					content.txtAlimentazione.setText(veicolo.rs.getString(6));
					content.txtKm.setText(veicolo.rs.getString(7));
					content.txtDimensioni.setText(veicolo.rs.getString(8));
					content.frmtdtxtfldImma.setText(veicolo.rs.getString(9));
					content.frmtdtxtfldBollo.setText(veicolo.rs.getString(10));
					content.frmtdtxtfldTagliando.setText(veicolo.rs.getString(11));
					content.frmtdtxtfldAssicurazione.setText(veicolo.rs.getString(12));
					content.frmtdtxtfldOrmeggio.setText(veicolo.rs.getString(13));
					content.frmtdtxtfldAlaggio.setText(veicolo.rs.getString(14));
					content.txtTipologia.setEditable(true);
					content.txtNome.setEditable(true);
					content.txtDisp.setEditable(true);
					content.txtMarca.setEditable(true);
					content.txtAlimentazione.setEditable(true);
					content.txtKm.setEditable(true);
					content.txtDimensioni.setEditable(true);
					content.frmtdtxtfldImma.setEditable(true);
					content.frmtdtxtfldBollo.setEditable(true);
					content.frmtdtxtfldTagliando.setEditable(true);
					content.frmtdtxtfldAssicurazione.setEditable(true);
					content.frmtdtxtfldOrmeggio.setEditable(true);
					content.frmtdtxtfldAlaggio.setEditable(true);
				}else{
					JOptionPane.showMessageDialog(null, "Errore, Il veicolo con la Targa cercata non è presente nel DB!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtTargaCerca.setText("");
					content.txtTargaCerca.requestFocus();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Veicolo non Trovato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo per modificare un veicolo nel DB. */
	
	public void modifica(ModuloFl content){
		if (check(content,"modifica")){
			try{
				String valori = "Tipologia='"+Tipologia+"',Nome='"+Nome+"',Disponibilita='"+Disp+"',Marca='"+Marca+"',Alimentazione='"+Alimentazione+"',Km_Effettuati="+Km+",Dimensioni='"+Dimensioni+"',Data_Immatricolazione="+Imma+",Data_Scadenza_Bollo="+Bollo+",Data_Scadenza_Tagliando="+Tagliando+",Data_Scadenza_Assicurazione="+Assicurazione+",Data_Scadenza_Ormeggio="+Ormeggio+",Data_Scadenza_Costo_Alaggio="+Alaggio;
				veicolo.exequery("UPDATE veicolo SET "+valori+" WHERE Targa='"+Targa+"'","update");
				JOptionPane.showMessageDialog(null , "Veicolo Modificato!");
				content.txtTargaCerca.setText("");
				content.txtTargaCerca.requestFocus();
				content.txtTargaCerca.setEditable(true);
				content.txtTipologia.setEditable(false);
				content.txtNome.setEditable(false);
				content.txtDisp.setEditable(false);
				content.txtMarca.setEditable(false);
				content.txtAlimentazione.setEditable(false);
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
				JOptionPane.showMessageDialog(null, "Errore, Veicolo non Modificato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo per assegnare i valori al Veicolo. */
	
	public void setValori(ModuloFl content, String tipo){
		Targa = content.txtTarga.getText().trim();
		Tipologia = content.txtTipologia.getText().trim();
		Nome = content.txtNome.getText().trim();
		Disp = content.txtDisp.getText().trim();
		Marca = content.txtMarca.getText().trim();
		Alimentazione = content.txtAlimentazione.getText().trim();
		Km = content.txtKm.getText().trim();
		Dimensioni = content.txtDimensioni.getText().trim();
		Imma = content.frmtdtxtfldImma.getText().trim();
		Bollo = content.frmtdtxtfldBollo.getText().trim();
		Tagliando = content.frmtdtxtfldTagliando.getText().trim();
		Assicurazione = content.frmtdtxtfldAssicurazione.getText().trim();
		Ormeggio = content.frmtdtxtfldOrmeggio.getText().trim();
		Alaggio = content.frmtdtxtfldAlaggio.getText().trim();
		if (tipo.equals("aggiungi")){
			Breve = content.lstBreve.getSelectedValue().toString();
			if (!content.lstLungo.isSelectionEmpty()) Lungo = content.lstLungo.getSelectedValue().toString();}
	}
	
	/* Metodo per assegnare solo la Targa al Veicolo. */
	
	public void setTarga(ModuloFl content){
		Targa = content.txtTarga.getText().trim();
	}
	
	/* Metodo per assegnare solo la Targa al Veicolo da cercare. */
	
	public void setTargaCerca(ModuloFl content){
		TargaCerca = content.txtTargaCerca.getText().trim();
	}
	
	/* Metodo per verificare la correttezza dei dati inseriti. */
	
	private boolean check(ModuloFl content, String tipo){
		boolean check=true;
		if ((Targa.equals("") || Tipologia.equals("") || Nome.equals("") || Disp.equals("") || Marca.equals("") || Alimentazione.equals("") || Km.equals("") || Dimensioni.equals("") || Breve.equals("")) && tipo.equals("aggiungi")){		/* Verifica se sono stati inseriti tutti i campi necessari */
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, Inserisci tutti i Campi con l'Asterisco!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}else if ((Targa.equals("") || Tipologia.equals("") || Nome.equals("") || Disp.equals("") || Marca.equals("") || Alimentazione.equals("") || Km.equals("") || Dimensioni.equals("")) && tipo.equals("modifica")){		/* Verifica se sono stati inseriti tutti i campi necessari durante la modifica*/
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, Inserisci tutti i Campi con l'Asterisco!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}else if(Targa.length()<7 || Targa.length()>7 || !Targa.matches(TGPATTERN)){
			content.txtTarga.setText("");
			content.txtTarga.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, La Targa deve essere composta da 4 caratteri e 3 cifre(Es: TO175RP)!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Tipologia.length()>20){
			content.txtTipologia.setText("");
			content.txtTipologia.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la Tipologia deve avere meno di 20 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Nome.length()>20){
			content.txtNome.setText("");
			content.txtNome.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il Nome deve avere meno di 20 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if ((Disp.length()>2 || Disp.length()<2) && (!Disp.equals("si") || !Disp.equals("no"))){
			content.txtDisp.setText("");
			content.txtDisp.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, indicare la Disponibilita del veicolo scrivendo si/no!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Marca.length()>15){
			content.txtMarca.setText("");
			content.txtMarca.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la Marca deve avere meno di 15 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Alimentazione.length()>15){
			content.txtAlimentazione.setText("");
			content.txtAlimentazione.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il tipo di Alimentazione deve avere meno di 15 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!isNumeric(Km) || Km.length()>20){
			content.txtKm.setText("");
			content.txtKm.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il numero di Kilometri Effettuati deve essere composto da meno di 20 cifre numeriche!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (Dimensioni.length()>20){
			content.txtDimensioni.setText("");
			content.txtDimensioni.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la Dimensione deve essere specificata in meno di 20 caratteri!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Imma.matches(DATEPATTERN) && !Imma.equals("") && !Imma.equals("aaaa-mm-gg")){
			content.frmtdtxtfldImma.setText("");
			content.frmtdtxtfldImma.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la data di Immatricolazione inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Bollo.matches(DATEPATTERN) && !Bollo.equals("") && !Bollo.equals("aaaa-mm-gg")){
			content.frmtdtxtfldBollo.setText("");
			content.frmtdtxtfldBollo.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la data di scadenza Bollo inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Tagliando.matches(DATEPATTERN) && !Tagliando.equals("") && !Tagliando.equals("aaaa-mm-gg")){
			content.frmtdtxtfldTagliando.setText("");
			content.frmtdtxtfldTagliando.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la data di scadenza Tagliando inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Assicurazione.matches(DATEPATTERN) && !Assicurazione.equals("") && !Assicurazione.equals("aaaa-mm-gg")){
			content.frmtdtxtfldAssicurazione.setText("");
			content.frmtdtxtfldAssicurazione.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la data di scadenza Assicurazione inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Ormeggio.matches(DATEPATTERN) && !Ormeggio.equals("") && !Ormeggio.equals("aaaa-mm-gg")){
			content.frmtdtxtfldOrmeggio.setText("");
			content.frmtdtxtfldOrmeggio.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la data di scadenza Ormeggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Alaggio.matches(DATEPATTERN) && !Alaggio.equals("") && !Alaggio.equals("aaaa-mm-gg")){
			content.frmtdtxtfldAlaggio.setText("");
			content.frmtdtxtfldAlaggio.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la data di scadenza Alaggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}if (!Lungo.equals(Breve) && !content.lstLungo.isSelectionEmpty() && tipo.equals("aggiungi")){
			content.lstLungo.clearSelection();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, il codice di Costo a Lungo Termine deve corrispondere a quello di Breve Termine!",
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
	
	/* Metodo per verificare la correttezza della Targa del veicolo da eliminare. */
	
	private boolean checkelimina(ModuloFl content){
		boolean check=true;
		if(Targa.length()<7 || Targa.length()>7 || !Targa.matches(TGPATTERN)){
			content.txtTarga.setText("");
			content.txtTarga.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, La Targa deve essere composta da 4 caratteri e 3 cifre(Es: TO175RP)!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		if (check==false) test=check; 
		else test=true;
		return test;
	}

	/* Metodo per verificare la correttezza della targa del veicolo da cercare. */
	
	private boolean checkcerca(ModuloFl content){
		boolean check=true;
		if(TargaCerca.length()<7 || TargaCerca.length()>7 || !TargaCerca.matches(TGPATTERN)){
			content.txtTargaCerca.setText("");
			content.txtTargaCerca.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, La Targa deve essere composta da 4 caratteri e 3 cifre(Es: TO175RP)!",
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
