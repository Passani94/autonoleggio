package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * La classe GestioneGiorni contiene metodi per gestire date.
 */
public class GestioneGiorni {
	
	/**
	 *  Inizializza un nuovo oggetto GestioneGiorni.
	 */
	public GestioneGiorni() {
		
	}
	
	/**
	 * Calcola i giorni di noleggio.
	 * 
	 * @param dataInizio la data di inizio del noleggio.
	 * @param dataFine la data di fine del noleggio.
	 * @return il numero di giorni del noleggio.
	 * 
	 * @throws ParseException se avviene un errore durante il parsing delle stringhe passate come argomenti.
	 */
	public static long calcolaGiorni(String dataInizio, String dataFine) throws ParseException{
	
			TimeZone zone = TimeZone.getTimeZone("GMT+1");
		
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			fmt.setTimeZone(zone);
			fmt.setLenient(false);
			
			/* Esegue il parsing delle due stringhe passate come argomenti. */
			Date d1 = fmt.parse(dataInizio);
			Date d2 = fmt.parse(dataFine);
			
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(d1);
			c2.setTime(d2);
			
			long giorni = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
			
			return giorni+1;
	}
	
	/**
	 * Calcola due possibili date di fine noleggio per un periodo corrispondente a 30, 60, 90, 120, 150, 180, 365, 730, 1095 giorni. <br><br>
	 * 
	 * es. dataFine("2017-08-10", 65); <br><br>
	 * 		
	 * 	   data1 = "2017-10-09" corrispondente ad un periodo di noleggio di 60 giorni <br>
	 * 	   data2 = "2017-11-08" corrispondente ad un periodo di noleggio di 90 giorni 
	 * 	
	 * @param dataInizio la data di inizio del noleggio.
	 * @param giorniNoleggio il numero di giorni del noleggio.
	 * @return due possibili date di fine noleggio in relazione al valore del parametro giorniNoleggio.
	 * 
	 * @throws ParseException se avviene un errore durante il parsing delle stringhe passate come argomenti.
	 */
	public static String[] dataFine(String dataInizio, long giorniNoleggio) throws ParseException {
		
		Date d1, d2, d3;
		String dataFine1, dataFine2;
		String[] date = new String[2];
		
		TimeZone zone = TimeZone.getTimeZone("GMT+1");
		
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		fmt.setTimeZone(zone);
		fmt.setLenient(false);
		
		/* Esegue il parsing della stringa passata come argomento 
		 * e converte la data in millisecondi. */
		d1 = fmt.parse(dataInizio);
		
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d1);
		
		if (giorniNoleggio>31 && giorniNoleggio<59) {
			c1.add(Calendar.DATE,29);
			c2.add(Calendar.DATE,59);
		}else if (giorniNoleggio>61 && giorniNoleggio<89) {
			c1.add(Calendar.DATE,59);
			c2.add(Calendar.DATE,89);
		}else if (giorniNoleggio>91 && giorniNoleggio<119) {
			c1.add(Calendar.DATE,89);
			c2.add(Calendar.DATE,119);
		}else if (giorniNoleggio>119 && giorniNoleggio<149) {
			c1.add(Calendar.DATE,119);
			c2.add(Calendar.DATE,149);
		}else if (giorniNoleggio>151 && giorniNoleggio<179) {
			c1.add(Calendar.DATE,149);
			c2.add(Calendar.DATE,179);
		}else if (giorniNoleggio>181 && giorniNoleggio<366) {
			c1.add(Calendar.DATE,179);
			c2.add(Calendar.DATE,365);
		}else if (giorniNoleggio>367 && giorniNoleggio<731) {
			c1.add(Calendar.DATE,365);
			c2.add(Calendar.DATE,730);
		}else if (giorniNoleggio>732 && giorniNoleggio<1096) {
			c1.add(Calendar.DATE,730);
			c2.add(Calendar.DATE,1095);
		}else if (giorniNoleggio>1097) {
			c1.add(Calendar.DATE,1095);
		}
		
		/* Converte da Calendar a Date. */
		d2 = c1.getTime();
		d3 = c2.getTime();
		
		/* Converte le date in stringhe. */
		dataFine1 = fmt.format(d2);
		dataFine2 = fmt.format(d3);
		
		/* Mette le stringhe in un array. */
		date[0] = dataFine1;
		date[1] = dataFine2;
		
		return date;	
	}
	
	/**
	 * Verifica se la data di inizio noleggio viene dopo la data di fine noleggio.
	 * 
	 * @param dataInizio la data di inizio noleggio.
	 * @param dataFine la data di fine noleggio.
	 * @return true se dataInizio viene dopo dataFine; false altrimenti.
	 * 
	 * @throws ParseException se avviene un errore durante il parsing delle stringhe passate come argomenti.
	 */
	public static boolean isMaggiore(String dataInizio, String dataFine) throws ParseException {
		
		boolean maggiore = false;
		Date d1, d2;
		
		TimeZone zone = TimeZone.getTimeZone("GMT+1");
		
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		fmt.setTimeZone(zone);
		fmt.setLenient(false);
		
		/* Esegue il parsing delle stringhe passate come argomenti. */
		d1 = fmt.parse(dataInizio);
		d2 = fmt.parse(dataFine);
		
		maggiore = d1.after(d2);
		
		return maggiore;
	}
	
	/**
	 * Verifica se la data di inizio noleggio viene prima della data di fine noleggio.
	 * 
	 * @param dataInizio la data di inizio noleggio.
	 * @param dataFine la data di fine noleggio.
	 * @return true se dataInizio viene prima di dataFine; false altrimenti.
	 * 
	 * @throws ParseException se avviene un errore durante il parsing delle stringhe passate come argomenti.
	 */
	public static boolean isMinore(String dataInizio, String dataFine) throws ParseException {
		
		boolean minore = false;
		Date d1, d2;
		
		TimeZone zone = TimeZone.getTimeZone("GMT+1");
		
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		fmt.setTimeZone(zone);
		fmt.setLenient(false);
		
		/* Esegue il parsing delle stringhe passate come argomenti. */
		d1 = fmt.parse(dataInizio);
		d2 = fmt.parse(dataFine);
		
		minore = d1.before(d2);
		
		return minore;
	}
	
	/**
	 * Verifica se la data di inizio noleggio è antecedente alla data odierna.
	 * 
	 * @param dataInizio la data di inizio noleggio.
	 * @return true se dataInizio è antecedente alla data odierna; false altrimenti.
	 * 
	 * @throws ParseException se avviene un errore durante il parsing delle stringhe passate come argomenti.
	 */
	public static boolean nonConsentito(String dataInizio) throws ParseException {
		
		boolean nonConsentito = false;
		Date d1, d2;
		
		TimeZone zone = TimeZone.getTimeZone("GMT+1");
		
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		fmt.setTimeZone(zone);
		fmt.setLenient(false);
		
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		
		d1 = fmt.parse(dataInizio);
		d2 = today.getTime();
		
		nonConsentito = d1.before(d2);
		
		return nonConsentito;
	
	}

	/**
	 * Restituisce una rappresentazione in stringa dell'oggetto.
	 */
	public String toString() {
		return "GestioneGiorni [La classe GestioneGiorni contiene metodi per gestire date.]";
	}
	
	
}
