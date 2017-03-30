package GUI.Admin;

import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import Utils.CostruisciTabella;
import Utils.TableColumnAdjuster;
import db.DBConnect;

public class ModuloHm extends JPanel{
	
	private JTable tblRitorno;
	private JScrollPane scroll = new JScrollPane(tblRitorno);
	private DBConnect InRitorno = new DBConnect();
	
	public void set(){
		this.setBorder(BorderFactory.createTitledBorder("Mezzi in Ritorno Oggi"));
		
		java.sql.Date DataOggi = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		InRitorno.exequery("SELECT * FROM noleggio WHERE Data_Fine='"+DataOggi+"'","select");/*scrivere query mezzi in ritorno*/
		
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
	
	public ModuloHm() {
		set();
	}
}	