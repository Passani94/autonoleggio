package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalcolaGiorni {
	
	/* Metodo. Calcola i giorni di noleggio. */
	
	public static int calcolaGiorni(String dataInizio, String dataFine) throws ParseException{
	
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			fmt.setLenient(false);
			
			/* Esegue il parsing delle due stringhe passate come argomenti. */
			Date d1 = fmt.parse(dataInizio);
			Date d2 = fmt.parse(dataFine);
			
			/* Calcola la differenza in millisecondi. */
			long millisDiff = d2.getTime() - d1.getTime();
			
			/* Calcola la differenza in giorni. */
			int giorni = (int) (millisDiff / 86400000);
			
			/* Aggiusta la differenza per soddisfare il reale funzionamento dell'autonoleggio. 
			 * 
			 * es. d1 = 2017-07-31	d2 = 2017-07-31 
			 * -> giorni = 0  ma nella realtà ne viene contato 1.
			 * 
			 * es. d1 = 2017-07-31	d2 = 2018-07-31
			 * -> giorni = 365 ma nella realtà ne vengono contati 366.
			 * 
			 * */
			
			if(giorni >= 0 && giorni < 730) {
				giorni = giorni + 1;
			} else if(giorni >= 730 && giorni < 1096) {
				giorni = giorni +2;
			} else if(giorni >= 1096) {
				giorni = giorni + 3;
			}
			
			return giorni;
	}

}
