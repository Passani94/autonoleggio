package gui.moduli;

import java.sql.SQLException;

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

public class ModuloMezziDisponibili extends JPanel{
	
	private static final long serialVersionUID = 7526460295622776147L; 
	private JTable tblDisponibili;
	private JScrollPane scroll = new JScrollPane(tblDisponibili);
	private DBConnect Disponibili = new DBConnect();
	
	public void set(){
		this.setBorder(BorderFactory.createTitledBorder("Mezzi Disponibili per il Noleggio"));
		
		try{Disponibili.exequery("SELECT * FROM veicolo WHERE disponibilita='si'","select");}
		catch (SQLException e) {  
			JOptionPane.showMessageDialog(null, "Errore, impossibile caricare l'elenco dei veicoli disponibili!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);}
		
		tblDisponibili = new JTable();
		tblDisponibili.setModel(new CostruisciTabella(Disponibili.rs).model);
		tblDisponibili.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(tblDisponibili);
		tca.adjustColumns();
		
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setViewportView(tblDisponibili);
		
		/* Crea il Layout per l'elenco dei mezzi Disponibili. */
		
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
	
	/* Costruttore ModuloHmU */
	
	public ModuloMezziDisponibili() {
		set();
	}
}	