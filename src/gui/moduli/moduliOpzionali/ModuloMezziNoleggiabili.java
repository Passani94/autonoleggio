package gui.moduli.moduliOpzionali;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import db.DBConnect;
import utils.CostruisciTabella;
import utils.Noleggiabilita;
import utils.TableColumnAdjuster;

/**
 * La classe ModuloMezziNoleggiabili genera l'elenco dei mezzi noleggiabili.
 */
public class ModuloMezziNoleggiabili extends JPanel{
	
	private static final long serialVersionUID = 1L; 
	private JTable tblNoleggiabili;
	private JScrollPane scroll = new JScrollPane(tblNoleggiabili);
	private DBConnect noleggiabili = new DBConnect();
	private String dataOggi;
	private Date day;
	private Vector <String> targhe,disp;
	
	/**
	 * Inizializza un nuovo oggetto ModuloMezziNoleggiabili e richiama il metodo {@code set}.
	 */
	public ModuloMezziNoleggiabili() {
		set();
	}
	
	/**
	 * Costruisce l'elenco dei mezzi noleggiabili.
	 */
	public void set() {
		
		int i,numero=0;
		this.setBorder(BorderFactory.createTitledBorder("Mezzi Noleggiabili in Giornata"));
		
		TimeZone zone = TimeZone.getTimeZone("GMT+1");
		
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		fmt.setTimeZone(zone);
		fmt.setLenient(false);
		
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 1);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		
		day = today.getTime();
		dataOggi=fmt.format(day);		
		
		try{
			noleggiabili.exequery("CREATE TABLE mezzinoleggiabili AS SELECT Targa, Tipologia, Marca, Nome,"
					+ "Disponibilita, Alimentazione, Km_Effettuati, Dimensioni FROM veicolo","update");
			noleggiabili.exequery("SELECT * FROM mezzinoleggiabili", "select");
						
			targhe= new Vector <>();
			disp= new Vector <>();
			
			while(noleggiabili.rs.next()) {
				targhe.addElement(noleggiabili.rs.getString(1));
				disp.addElement(noleggiabili.rs.getString(5));
			}
			
			numero=targhe.size();
		} catch (SQLException e) {  
			JOptionPane.showMessageDialog(null, "Errore, impossibile caricare l'elenco dei veicoli!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
		}
		
		for (i=0;i<numero;i++) {
				try {
					if ((!Noleggiabilita.controlla(targhe.get(i),dataOggi,dataOggi)) || (disp.get(i).equals("NO"))) {
						noleggiabili.exequery("DELETE FROM mezzinoleggiabili WHERE Targa='"+targhe.get(i)+"'", "delete");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
				
		try {
			noleggiabili.exequery("SELECT Targa, Tipologia, Marca, Nome, Alimentazione , "
					+ "Dimensioni FROM mezzinoleggiabili", "select");
			
			tblNoleggiabili = new JTable();
			tblNoleggiabili.setModel(new CostruisciTabella(noleggiabili.rs).model);
			TableColumnAdjuster tca = new TableColumnAdjuster(tblNoleggiabili);
			tca.adjustColumns();
			
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setViewportView(tblNoleggiabili);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			noleggiabili.exequery("DROP TABLE mezzinoleggiabili", "delete");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/* Crea il layout per l'elenco dei mezzi noleggiabili. */
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
						.addContainerGap())
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
						.addContainerGap())
			);
		this.setLayout(gl_contentPane);
		}

	
/* OVERRIDING METODI toString() ED equals() */
	
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * 
	 * @return una stringa rappresentante l'oggetto.
	 */
	public String toString() {
		return "ModuloMezziNoleggiabili [La classe ModuloMezziNoleggiabili genera l'elenco dei mezzi noleggiabili.]";
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
		ModuloMezziNoleggiabili other = (ModuloMezziNoleggiabili) obj;
		if (dataOggi == null) {
			if (other.dataOggi != null)
				return false;
		} else if (!dataOggi.equals(other.dataOggi))
			return false;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (disp == null) {
			if (other.disp != null)
				return false;
		} else if (!disp.equals(other.disp))
			return false;
		if (noleggiabili == null) {
			if (other.noleggiabili != null)
				return false;
		} else if (!noleggiabili.equals(other.noleggiabili))
			return false;
		if (scroll == null) {
			if (other.scroll != null)
				return false;
		} else if (!scroll.equals(other.scroll))
			return false;
		if (targhe == null) {
			if (other.targhe != null)
				return false;
		} else if (!targhe.equals(other.targhe))
			return false;
		if (tblNoleggiabili == null) {
			if (other.tblNoleggiabili != null)
				return false;
		} else if (!tblNoleggiabili.equals(other.tblNoleggiabili))
			return false;
		return true;
	}
	
	
}	