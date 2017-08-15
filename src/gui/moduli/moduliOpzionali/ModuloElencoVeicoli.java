package gui.moduli.moduliOpzionali;

import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;

import db.DBConnect;
import utils.CostruisciTabella;
import utils.TableColumnAdjuster;

/**
 * La classe ModuloElencoVeicoli si comporta in maniera differente a seconda dell'oggetto String che viene passato al costruttore.
 */	
public class ModuloElencoVeicoli extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTable tblVeicoli;
	private JScrollPane scroll = new JScrollPane(tblVeicoli);
	private DBConnect veicoli = new DBConnect();
	
	/**
	 * Inizializza un nuovo oggetto ModuloElencoVeicoli e richiama il metodo {@code set} passando come argomento l'oggetto String {@code str}.
	 * 
	 * @param str una stringa che determina il diverso comportamento del metodo {@code set}.
	 */
	public ModuloElencoVeicoli(String str) {
		set(str);
	}
	
	/**
	 * Si comporta in maniera differente a seconda dell'oggetto String che viene passato come argomento. <br><br>
	 *
	 * - Se viene passato "Completo", viene generato l'elenco dei veicoli contenuti nel database.<br>
	 * - Se viene passato "Disponibili", viene generato l'elenco dei veicoli disponibili.
	 * 
	 * @param str una stringa che determina cosa verrà mostrato a schermo.
	 */
	public void set(String str) {
		
		if (str.equals("Completo")) {
			
			this.setBorder(BorderFactory.createTitledBorder("Elenco Veicoli"));
			
			try {
				veicoli.exequery("SELECT Targa, Tipologia, Marca, Nome, Disponibilita, Alimentazione, Km_Effettuati, Dimensioni FROM veicolo","select");
			} catch (SQLException e) {  
				JOptionPane.showMessageDialog(null, "Errore, impossibile generare l'elenco dei veicoli!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);}
		
			tblVeicoli = new JTable();
			tblVeicoli.setModel(new CostruisciTabella(veicoli.rs).model);
			tblVeicoli.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumnAdjuster tca = new TableColumnAdjuster(tblVeicoli);
			tca.adjustColumns();		
		
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setViewportView(tblVeicoli);
		
			/* Crea il layout per l'elenco dei veicoli contenuti nel database. */
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
			
		} else if (str.equals("Disponibili")) {
					
			this.setBorder(BorderFactory.createTitledBorder("Elenco Veicoli Disponibili"));
		
			try {
				veicoli.exequery("SELECT Targa, Tipologia, Marca, Nome, Alimentazione, Km_Effettuati, Dimensioni FROM veicolo WHERE Disponibilita = 'SI'","select");
			} catch (SQLException e) {  
				JOptionPane.showMessageDialog(null, "Errore, impossibile generare l'elenco dei veicoli disponibili!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);}
		
			tblVeicoli = new JTable();
			tblVeicoli.setModel(new CostruisciTabella(veicoli.rs).model);
			tblVeicoli.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumnAdjuster tca = new TableColumnAdjuster(tblVeicoli);
			tca.adjustColumns();		
		
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setViewportView(tblVeicoli);
		
			/* Crea il layout per l'elenco dei veicoli disponibili. */
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
	}
	
/* OVERRIDING METODI toString() ED equals() */
	
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * 
	 * @return una stringa rappresentante l'oggetto.
	 */
	public String toString() {
		return "ModuloElencoVeicoli [La classe ModuloElencoVeicoli si comporta in maniera differente a seconda dell'oggetto String che viene passato al costruttore.]";
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
		ModuloElencoVeicoli other = (ModuloElencoVeicoli) obj;
		if (scroll == null) {
			if (other.scroll != null)
				return false;
		} else if (!scroll.equals(other.scroll))
			return false;
		if (tblVeicoli == null) {
			if (other.tblVeicoli != null)
				return false;
		} else if (!tblVeicoli.equals(other.tblVeicoli))
			return false;
		if (veicoli == null) {
			if (other.veicoli != null)
				return false;
		} else if (!veicoli.equals(other.veicoli))
			return false;
		return true;
	}
	
	


	
	
}
