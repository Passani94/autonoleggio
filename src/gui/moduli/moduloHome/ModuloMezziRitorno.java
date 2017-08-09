package gui.moduli.moduloHome;

import java.sql.SQLException;
import java.util.Calendar;

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
import utils.TableColumnAdjuster;

public class ModuloMezziRitorno extends JPanel{
	
	private static final long serialVersionUID = 1L; 
	private JTable tblRitorno;
	private JScrollPane scroll = new JScrollPane(tblRitorno);
	private DBConnect InRitorno = new DBConnect();
	
	public void set(){
		this.setBorder(BorderFactory.createTitledBorder("Mezzi in Ritorno Oggi"));
		
		java.sql.Date DataOggi = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		try{InRitorno.exequery("SELECT n.Cod_Noleggio, n.Tipologia, n.Veicolo, v.Marca, v.Nome, n.Cliente, n.Costo_Totale FROM noleggio n,veicolo v WHERE n.Data_Fine='"+DataOggi+"' AND n.Veicolo=v.Targa","select");} 
		catch (SQLException e) {  
		JOptionPane.showMessageDialog(null, "Errore, impossibile caricare l'elenco dei veicoli in rientro oggi!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);}
		
		tblRitorno = new JTable();
		tblRitorno.setModel(new CostruisciTabella(InRitorno.rs).model);
		tblRitorno.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(tblRitorno);
		tca.adjustColumns();		
		
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setViewportView(tblRitorno);
		
		/* Crea il Layout per l'elenco dei mezzi in ritorno. */
		
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
	
	/* Costruttore ModuloHm */
	
	public ModuloMezziRitorno() {
		set();
	}

	@Override
	public String toString() {
		return "ModuloMezziRitorno [Questa classe crea il modulo dei mezzi in ritorno.]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModuloMezziRitorno other = (ModuloMezziRitorno) obj;
		if (InRitorno == null) {
			if (other.InRitorno != null)
				return false;
		} else if (!InRitorno.equals(other.InRitorno))
			return false;
		if (scroll == null) {
			if (other.scroll != null)
				return false;
		} else if (!scroll.equals(other.scroll))
			return false;
		if (tblRitorno == null) {
			if (other.tblRitorno != null)
				return false;
		} else if (!tblRitorno.equals(other.tblRitorno))
			return false;
		return true;
	}
	
	
}	