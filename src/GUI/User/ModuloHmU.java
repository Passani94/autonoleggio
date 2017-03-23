package GUI.User;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class ModuloHmU extends JPanel{
	
	private JTable tblDisponibili;
	private JScrollPane scroll = new JScrollPane(tblDisponibili);

	public void set(){
		this.setBorder(BorderFactory.createTitledBorder("Mezzi Disponibili per il Noleggio"));

		tblDisponibili = new JTable();
		tblDisponibili.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Targa", "Tipologia","Nome"
				}
		));
		
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
	
	public ModuloHmU() {
		set();
	}
}	