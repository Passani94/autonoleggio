package GUI.Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;


public class ModuloFl extends JPanel implements ActionListener{

	private JTable tblOperatori;
	private JTextField txtPassword;
	private JTextField txtUsername;
	private JButton btnAggiungi;
	private JButton btnElimina;
	private JButton btnModifica;
	private JScrollPane scroll = new JScrollPane(tblOperatori);
	private JTextField txtTipologia;
	private JTextField txtNome;
	private JTextField txtDisp;
	private JTextField txtMarca;
	private JTextField txtAlimentazione;
	private JTextField txtKm;
	private JTextField txtTarga;
	private JFormattedTextField frmtdtxtfldTipologia;
	
	public ModuloFl(String str){
		set(str);
	}

	public void set(String str){
		if (str == "Elenca"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elenco Veicoli"));
			
			tblOperatori = new JTable();
			tblOperatori.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Operatore", "Password"
				}
			));
			
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setViewportView(tblOperatori);
			
			/* Crea il Layout per l'elenco dei Veicoli. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(64)
							.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(66, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(35)
							.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(0, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str == "Nuovo"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Nuovo Veicolo"));
			
			btnAggiungi = new JButton("Aggiungi");
			btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 12));
			
			txtTarga = new JTextField();
			txtTarga.setFont(new Font("Arial", Font.PLAIN, 12));
			txtTarga.setColumns(10);
			
			JFormattedTextField frmtdtxtTarga = new JFormattedTextField();
			frmtdtxtTarga.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtTarga.setBorder(null);
			frmtdtxtTarga.setForeground(new Color(0, 0, 0));
			frmtdtxtTarga.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtTarga.setEditable(false);
			frmtdtxtTarga.setText("Targa*");
			
			frmtdtxtfldTipologia = new JFormattedTextField();
			frmtdtxtfldTipologia.setText("Tipologia*");
			frmtdtxtfldTipologia.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldTipologia.setForeground(Color.BLACK);
			frmtdtxtfldTipologia.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldTipologia.setEditable(false);
			frmtdtxtfldTipologia.setBorder(null);
		
			txtTipologia = new JTextField();
			txtTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
			txtTipologia.setColumns(10);
		
			txtNome = new JTextField();
			txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
			txtNome.setColumns(10);
		
			JFormattedTextField frmtdtxtfldNome = new JFormattedTextField();
			frmtdtxtfldNome.setText("Nome*");
			frmtdtxtfldNome.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldNome.setForeground(Color.BLACK);
			frmtdtxtfldNome.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldNome.setEditable(false);
			frmtdtxtfldNome.setBorder(null);
		
			JFormattedTextField frmtdtxtfldDisponibilita = new JFormattedTextField();
			frmtdtxtfldDisponibilita.setText("Disponibilit\u00E0*");
			frmtdtxtfldDisponibilita.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldDisponibilita.setForeground(Color.BLACK);
			frmtdtxtfldDisponibilita.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldDisponibilita.setEditable(false);
			frmtdtxtfldDisponibilita.setBorder(null);
		
			txtDisp = new JTextField();
			txtDisp.setFont(new Font("Arial", Font.PLAIN, 12));
			txtDisp.setColumns(10);
		
			JFormattedTextField frmtdtxtfldMarca = new JFormattedTextField();
			frmtdtxtfldMarca.setText("Marca*");
			frmtdtxtfldMarca.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldMarca.setForeground(Color.BLACK);
			frmtdtxtfldMarca.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldMarca.setEditable(false);
			frmtdtxtfldMarca.setBorder(null);
		
			txtMarca = new JTextField();
			txtMarca.setFont(new Font("Arial", Font.PLAIN, 12));
			txtMarca.setColumns(10);
		
			JFormattedTextField frmtdtxtfldAlimentazione = new JFormattedTextField();
			frmtdtxtfldAlimentazione.setText("Alimentazione*");
			frmtdtxtfldAlimentazione.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldAlimentazione.setForeground(Color.BLACK);
			frmtdtxtfldAlimentazione.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldAlimentazione.setEditable(false);
			frmtdtxtfldAlimentazione.setBorder(null);
		
			txtAlimentazione = new JTextField();
			txtAlimentazione.setFont(new Font("Arial", Font.PLAIN, 12));
			txtAlimentazione.setColumns(10);
		
			JFormattedTextField frmtdtxtfldKm = new JFormattedTextField();
			frmtdtxtfldKm.setText("Km Effettuati*");
			frmtdtxtfldKm.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldKm.setForeground(Color.BLACK);
			frmtdtxtfldKm.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldKm.setEditable(false);
			frmtdtxtfldKm.setBorder(null);
		
			txtKm = new JTextField();
			txtKm.setFont(new Font("Arial", Font.PLAIN, 12));
			txtKm.setColumns(10);
			
			JFormattedTextField frmtdtxtfldImma = new JFormattedTextField();
			frmtdtxtfldImma.setText("Data Immatricolazione");
			frmtdtxtfldImma.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldImma.setForeground(Color.BLACK);
			frmtdtxtfldImma.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldImma.setEditable(false);
			frmtdtxtfldImma.setBorder(null);
		
			JFormattedTextField frmtdtxtfldBollo = new JFormattedTextField();
			frmtdtxtfldBollo.setText("Data Scadenza Bollo");
			frmtdtxtfldBollo.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldBollo.setForeground(Color.BLACK);
			frmtdtxtfldBollo.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldBollo.setEditable(false);
			frmtdtxtfldBollo.setBorder(null);
		
			JFormattedTextField frmtdtxtfldTagliando = new JFormattedTextField();
			frmtdtxtfldTagliando.setText("Data Scadenza Tagliando");
			frmtdtxtfldTagliando.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldTagliando.setForeground(Color.BLACK);
			frmtdtxtfldTagliando.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldTagliando.setEditable(false);
			frmtdtxtfldTagliando.setBorder(null);
		
			JFormattedTextField frmtdtxtfldAssicurazione = new JFormattedTextField();
			frmtdtxtfldAssicurazione.setText("Data Scadenza Assicurazione");
			frmtdtxtfldAssicurazione.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldAssicurazione.setForeground(Color.BLACK);
			frmtdtxtfldAssicurazione.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldAssicurazione.setEditable(false);
			frmtdtxtfldAssicurazione.setBorder(null);
		
			JFormattedTextField frmtdtxtfldOrmeggio = new JFormattedTextField();
			frmtdtxtfldOrmeggio.setText("Data Scadenza Ormeggio");
			frmtdtxtfldOrmeggio.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldOrmeggio.setForeground(Color.BLACK);
			frmtdtxtfldOrmeggio.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldOrmeggio.setEditable(false);
			frmtdtxtfldOrmeggio.setBorder(null);
		
			JFormattedTextField frmtdtxtfldAlaggio = new JFormattedTextField();
			frmtdtxtfldAlaggio.setText("Data Scadenza Alaggio");
			frmtdtxtfldAlaggio.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldAlaggio.setForeground(Color.BLACK);
			frmtdtxtfldAlaggio.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldAlaggio.setEditable(false);
			frmtdtxtfldAlaggio.setBorder(null);
		
			JFormattedTextField frmtdtxtfldMex = new JFormattedTextField();
			frmtdtxtfldMex.setText("Inserire tutti i campi con l'asterisco!");
			frmtdtxtfldMex.setHorizontalAlignment(SwingConstants.LEFT);
			frmtdtxtfldMex.setForeground(Color.RED);
			frmtdtxtfldMex.setFont(new Font("Arial", Font.PLAIN, 14));
			frmtdtxtfldMex.setEditable(false);
			frmtdtxtfldMex.setBorder(null);
			
			/* Crea il Layout per un nuovo Veicolo. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(frmtdtxtfldKm, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
											.addGap(91)
											.addComponent(txtKm, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
													.addComponent(frmtdtxtTarga, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
													.addComponent(frmtdtxtfldTipologia, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
													.addComponent(frmtdtxtfldNome, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
													.addComponent(frmtdtxtfldMarca)
													.addComponent(frmtdtxtfldDisponibilita, Alignment.LEADING)
													.addComponent(frmtdtxtfldAlimentazione, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)))
											.addGap(101)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(txtTipologia, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtTarga, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtDisp, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtAlimentazione, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))))
									.addGap(61))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(frmtdtxtfldImma, Alignment.LEADING)
										.addComponent(frmtdtxtfldBollo, Alignment.LEADING)
										.addComponent(frmtdtxtfldTagliando, Alignment.LEADING)
										.addComponent(frmtdtxtfldAlaggio, Alignment.LEADING)
										.addComponent(frmtdtxtfldOrmeggio, Alignment.LEADING)
										.addComponent(frmtdtxtfldAssicurazione, Alignment.LEADING))
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(frmtdtxtfldMex, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(220)
							.addComponent(btnAggiungi)
							.addContainerGap(241, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtTarga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtTarga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldTipologia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTipologia, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldNome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldDisponibilita, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDisp, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldMarca, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldAlimentazione, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtAlimentazione, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldKm, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtKm, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(frmtdtxtfldImma, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(frmtdtxtfldBollo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(frmtdtxtfldTagliando, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(frmtdtxtfldAssicurazione, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(frmtdtxtfldOrmeggio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(frmtdtxtfldAlaggio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(frmtdtxtfldMex, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(53)
							.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(56, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str == "Elimina"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elimina Veicolo"));
			
			btnElimina = new JButton("Elimina");
			btnElimina.setFont(new Font("Arial", Font.PLAIN, 12));
			btnElimina.addActionListener(this);	/* Action Listener per il bottone Elimina.*/
			
			txtUsername = new JTextField();
			txtUsername.setFont(new Font("Arial", Font.PLAIN, 12));
			txtUsername.setColumns(10);
			
			JFormattedTextField frmtdtxtUsername = new JFormattedTextField();
			frmtdtxtUsername.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtUsername.setBorder(null);
			frmtdtxtUsername.setForeground(new Color(0, 0, 0));
			frmtdtxtUsername.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtUsername.setEditable(false);
			frmtdtxtUsername.setText("Username");
			
			/* Crea il Layout per un eliminare un Veicolo. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addComponent(frmtdtxtUsername, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
							.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
							.addGap(10))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(188, Short.MAX_VALUE)
							.addComponent(btnElimina)
							.addGap(169))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(118)
							.addComponent(btnElimina, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(167, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str == "Modifica"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Modifica Veicolo"));
			
			btnModifica = new JButton("Modifica");
			btnModifica.setFont(new Font("Arial", Font.PLAIN, 12));
			btnModifica.addActionListener(this);	/* Action Listener per il bottone Modifica.*/
			
			txtUsername = new JTextField();
			txtUsername.setFont(new Font("Arial", Font.PLAIN, 12));
			txtUsername.setColumns(10);
			
			JFormattedTextField frmtdtxtUsername = new JFormattedTextField();
			frmtdtxtUsername.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtUsername.setBorder(null);
			frmtdtxtUsername.setForeground(new Color(0, 0, 0));
			frmtdtxtUsername.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtUsername.setEditable(false);
			frmtdtxtUsername.setText("Username");
			
			/* Crea il Layout per modificare un Veicolo. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addComponent(frmtdtxtUsername, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
							.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
							.addGap(10))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(188, Short.MAX_VALUE)
							.addComponent(btnModifica)
							.addGap(169))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(118)
							.addComponent(btnModifica, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(167, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
	}
	
	public void actionPerformed(ActionEvent e){
		if (btnAggiungi == e.getSource()){
			try{
			/* Inserire cosa fa il pulsante Aggiungi*/
			String user = txtUsername.getText();
			String pass = txtPassword.getText();
			JOptionPane.showMessageDialog(null , "Nuovo Veicolo Aggiunto!");
			txtUsername.setText("");
			txtPassword.setText("");
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Veicolo non Aggiunto!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(btnElimina == e.getSource()){
			try{
			/* Inserire cosa fa il pulsante Elimina*/
			String user = txtUsername.getText();
			JOptionPane.showMessageDialog(null , "Veicolo Eliminato!");
			txtUsername.setText("");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}