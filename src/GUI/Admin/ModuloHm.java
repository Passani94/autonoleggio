package GUI.Admin;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class ModuloHm extends JPanel{
	
	private JTable tblRitorno;
	private JScrollPane scroll = new JScrollPane(tblRitorno);

	public void set(){
		this.setBorder(BorderFactory.createTitledBorder("Mezzi in Ritorno Oggi"));

		tblRitorno = new JTable();
		tblRitorno.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Cliente", "Mezzo Noleggiato","Data Contratto"
				}
		));
		
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