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
 * La classe ModuloElencoClienti genera l'elenco dei clienti contenuti nel database.
 */
public class ModuloElencoClienti extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JTable tblClienti;
	private JScrollPane scroll = new JScrollPane(tblClienti);
	private DBConnect clienti = new DBConnect();

	/**
	 * Inizializza un nuovo oggetto ModuloElencoClienti e richiama il metodo {@code set}.
	 */
	public ModuloElencoClienti() {
		set();
	}
	
	/**
	 * Costruisce l'elenco dei clienti contenuti nel database.
	 */
	public void set() {
		
		this.setBorder(BorderFactory.createTitledBorder("Elenco Clienti"));
		
		try {
			clienti.exequery("SELECT * FROM cliente ORDER BY Tipologia, Ragione_Sociale","select");
		} catch (SQLException e) {  
		JOptionPane.showMessageDialog(null, "Errore, impossibile generare l'elenco dei clienti!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);}
		
		tblClienti = new JTable();
		tblClienti.setModel(new CostruisciTabella(clienti.rs).model);
		tblClienti.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(tblClienti);
		tca.adjustColumns();		
		
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setViewportView(tblClienti);
		
		/* Crea il layout per l'elenco dei clienti contenuti nel database. */
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
		return "ModuloElencoClienti [La classe ModuloElencoClienti genera l'elenco dei clienti contenuti nel database.]";
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
		ModuloElencoClienti other = (ModuloElencoClienti) obj;
		if (clienti == null) {
			if (other.clienti != null)
				return false;
		} else if (!clienti.equals(other.clienti))
			return false;
		if (scroll == null) {
			if (other.scroll != null)
				return false;
		} else if (!scroll.equals(other.scroll))
			return false;
		if (tblClienti == null) {
			if (other.tblClienti != null)
				return false;
		} else if (!tblClienti.equals(other.tblClienti))
			return false;
		return true;
	}


}
