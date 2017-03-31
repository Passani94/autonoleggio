package Entità;

import java.util.Calendar;

import javax.swing.JOptionPane;

import GUI.Admin.ModuloCt;
import db.DBConnect;

/* Classe per l'entità Preventivo */

public class Preventivo {
	private String Veicolo;
	private String Inizio;
	private String Fine;
	private String Tipologia;
	private Calendar calendar_Inizio;
	private boolean test;
	private DBConnect preventivo;
	private static final String TGPATTERN = "[a-zA-Z]{2}\\d\\d\\d[a-zA-Z]{2}";
	private static final String DATEPATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
	
	/* Costruttore Preventivo */
	
	public Preventivo(){
		test=true;
		preventivo = new DBConnect();
		calendar_Inizio = Calendar.getInstance();
	}

	/* Metodo usato per calcolare il preventivo. */
	
	public void calcola(ModuloCt content){
		calendar_Inizio.set(Calendar.YEAR, Integer.parseInt(Inizio.substring(0,3)));
		calendar_Inizio.set(Calendar.MONTH, Integer.parseInt(Inizio.substring(5,7)));
		calendar_Inizio.set(Calendar.DATE, Integer.parseInt(Inizio.substring(8,10)));
		if (check(content)){
			try{
				preventivo.exequery("SELECT * FROM veicolo where Targa='"+Veicolo+"'","select"); /* Controlla se il veicolo è presente. */
				if(preventivo.rs.next()){
					Tipologia = preventivo.rs.getString("Costobt");
				} else{
					JOptionPane.showMessageDialog(null, "Errore, Il Veicolo con la Targa "+Veicolo+" non è presente nel DB!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtVeicolo.setText("");
					content.txtVeicolo.requestFocus();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Preventivo non Calcolato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo per assegnare i valori al Preventivo. */
	
	public void setValori(ModuloCt content){
		Veicolo = content.txtVeicolo.getText().trim();
		Inizio = content.frmtdtxtfldInizio.getText().trim();
		Fine = content.frmtdtxtfldFine.getText().trim();
	}
	
	/* Metodo per verificare la correttezza dei dati inseriti. */
	
	private boolean check(ModuloCt content){
		boolean check=true;
		if(Inizio.equals("") || Inizio.equals("aaaa-mm-gg") || Fine.equals("") || Fine.equals("aaaa-mm-gg")){
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, Inserisci tutti i Campi!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}else if(Veicolo.length()<7 || Veicolo.length()>7 || !Veicolo.matches(TGPATTERN)){
			content.txtVeicolo.setText("");
			content.txtVeicolo.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, La Targa del veicolo deve essere composta da 4 caratteri e 3 cifre(Es: TO175RP)!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Inizio.matches(DATEPATTERN) && !Inizio.equals("") && !Inizio.equals("aaaa-mm-gg")){
			content.frmtdtxtfldInizio.setText("");
			content.frmtdtxtfldInizio.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la data di inizio noleggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if(!Fine.matches(DATEPATTERN) && !Fine.equals("") && !Fine.matches("aaaa-mm-gg")){
			content.frmtdtxtfldFine.setText("");
			content.frmtdtxtfldFine.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la data di fine noleggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		if (check==false) test=check; 
			else {test=true;}
		return test;
	}
}
