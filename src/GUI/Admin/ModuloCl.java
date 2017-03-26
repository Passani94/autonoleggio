package GUI.Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import Entità.Cliente;


public class ModuloCl extends JPanel implements ActionListener{

	private JTable tblClienti;
	private JButton btnAggiungi;
	private JButton btnElimina;
	private JButton btnModificaC;
	private JButton btnCerca;
	private JScrollPane scroll = new JScrollPane(tblClienti);
	public JTextField txtTipologia;
	public JTextField txtRS;
	public JTextField txtCAP;
	public JTextField txtCitta;
	public JTextField txtVia;
	public JTextField txtNumero;
	public JTextField txtCF_PIVA;
	public JTextField txtEmail;
	public JTextField txtTelefono;
	private JTextField txtClienteCerca;
	Cliente CL = new Cliente();
	
	/* Costruttore ModuloCl */
	
	public ModuloCl(String str){
		set(str);
	}

	public void set(String str){
		if (str == "Elenca"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elenco Clienti"));
			
			tblClienti = new JTable();
			tblClienti.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"CF_PIVA", "Tipologia","Ragione Sociale","CAP","Città","Via","Numero","Email","Telefono"
				}
			));
			tblClienti.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setViewportView(tblClienti);
			
			/* Crea il Layout per l'elenco dei Clienti. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(64)
							.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(360, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(35)
							.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
							.addGap(50))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str == "Nuovo"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Nuovo Cliente"));
			
			btnAggiungi = new JButton("Aggiungi");
			btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAggiungi.addActionListener(this);	/* Action Listener per il bottone Aggiungi.*/
			
			txtCF_PIVA = new JTextField();
			txtCF_PIVA.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblid = new JLabel("CF o PIVA*");
			lblid.setFont(new Font("Arial", Font.BOLD, 14));
			
			JLabel lblTipologia = new JLabel("Tipologia*");
			lblTipologia.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtTipologia = new JTextField();
			txtTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
		
			txtRS = new JTextField();
			txtRS.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblRS = new JLabel("Ragione Sociale*");
			lblRS.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblCAP = new JLabel("CAP");
			lblCAP.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtCAP = new JTextField();
			txtCAP.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblcitta = new JLabel("Città");
			lblcitta.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtCitta = new JTextField();
			txtCitta.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblVia = new JLabel("Via");
			lblVia.setFont(new Font("Arial", Font.BOLD, 14));

		
			txtVia = new JTextField();
			txtVia.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblNumero = new JLabel("N. Civico");
			lblNumero.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtNumero = new JTextField();
			txtNumero.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblEmail = new JLabel("Email");
			lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtEmail = new JTextField();
			txtEmail.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblTelefono = new JLabel("Telefono");
			lblTelefono.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtTelefono = new JTextField();
			txtTelefono.setFont(new Font("Arial", Font.PLAIN, 12));
						
			JLabel lblMex = new JLabel("Inserire tutti i campi con l'asterisco!");
			lblMex.setForeground(Color.RED);
			lblMex.setFont(new Font("Arial", Font.PLAIN, 14));
			
			/* Crea il Layout per un nuovo Cliente. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblid, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRS, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNumero, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCAP, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblcitta, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblVia, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
									.addGap(81)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(txtRS, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
											.addComponent(txtTipologia, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
											.addComponent(txtCAP, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
											.addComponent(txtCF_PIVA, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
											.addComponent(txtVia, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
											.addComponent(txtCitta, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
											.addComponent(txtNumero, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
											.addComponent(txtEmail))
										.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblMex, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(200)
							.addComponent(btnAggiungi, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
							.addGap(190))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCF_PIVA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTipologia, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRS, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtRS, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCAP, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCAP, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblcitta, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCitta, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtVia, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNumero, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(lblMex, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(20, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str == "Elimina"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elimina Cliente"));
			
			btnElimina = new JButton("Elimina Cliente");
			btnElimina.setFont(new Font("Arial", Font.PLAIN, 12));
			btnElimina.addActionListener(this);	/* Action Listener per il bottone Elimina.*/
			
			txtCF_PIVA = new JTextField();
			txtCF_PIVA.setFont(new Font("Arial", Font.PLAIN, 12));
			txtCF_PIVA.setColumns(10);
			
			JLabel lblid = new JLabel("CF o P.IVA");
			lblid.setFont(new Font("Arial", Font.BOLD, 14));
			
			/* Crea il Layout per un eliminare un Cliente. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(42)
							.addComponent(lblid, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
							.addComponent(txtCF_PIVA, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
							.addGap(62))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(179)
							.addComponent(btnElimina)
							.addContainerGap(194, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCF_PIVA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(116)
							.addComponent(btnElimina, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(169, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str == "Modifica"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Modifica Veicolo"));
			
			btnCerca = new JButton("Cerca Veicolo");
			btnCerca.setFont(new Font("Arial", Font.PLAIN, 12));
			btnCerca.addActionListener(this);	/* Action Listener per il bottone Cerca.*/
			
			txtClienteCerca = new JTextField();
			txtClienteCerca.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblClienteCerca = new JLabel("CF o P.IVA Cliente da Modificare");
			lblClienteCerca.setFont(new Font("Arial", Font.BOLD, 14));
		
			btnModificaC = new JButton("Modifica Cliente");
			btnModificaC.setFont(new Font("Arial", Font.PLAIN, 12));
			btnModificaC.addActionListener(this);	/* Action Listener per il bottone Modifica.*/
			
			txtCF_PIVA = new JTextField();
			txtCF_PIVA.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblid = new JLabel("CF o PIVA");
			lblid.setFont(new Font("Arial", Font.BOLD, 14));
			
			JLabel lblTipologia = new JLabel("Tipologia");
			lblTipologia.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtTipologia = new JTextField();
			txtTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
		
			txtRS = new JTextField();
			txtRS.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblRS = new JLabel("Ragione Sociale");
			lblRS.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblCAP = new JLabel("CAP");
			lblCAP.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtCAP = new JTextField();
			txtCAP.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblcitta = new JLabel("Città");
			lblcitta.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtCitta = new JTextField();
			txtCitta.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblVia = new JLabel("Via");
			lblVia.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtVia = new JTextField();
			txtVia.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblNumero = new JLabel("N. Civico");
			lblNumero.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtNumero = new JTextField();
			txtNumero.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblEmail = new JLabel("Email");
			lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtEmail = new JTextField();
			txtEmail.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblTelefono = new JLabel("Telefono");
			lblTelefono.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtTelefono = new JTextField();
			txtTelefono.setFont(new Font("Arial", Font.PLAIN, 12));
			
			txtTipologia.setEditable(false);
			txtRS.setEditable(false);
			txtCAP.setEditable(false);
			txtCitta.setEditable(false);
			txtVia.setEditable(false);
			txtNumero.setEditable(false);
			txtCF_PIVA.setEditable(false);
			txtEmail.setEditable(false);
			txtTelefono.setEditable(false);
			
			/* Crea il Layout per modificare un Cliente. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblTelefono, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
									.addComponent(lblEmail))
								.addComponent(lblClienteCerca, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRS, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
								.addComponent(lblTipologia, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
								.addComponent(lblid, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
								.addComponent(lblCAP, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblcitta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
								.addComponent(lblVia, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
								.addComponent(lblNumero, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
							.addGap(91)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtTelefono, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(txtNumero, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(txtVia, 150, 150, 150)
								.addComponent(txtCitta, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(txtCAP, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(txtRS, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(txtTipologia, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(txtCF_PIVA, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(txtClienteCerca, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
							.addGap(418))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(177)
							.addComponent(btnCerca)
							.addContainerGap(587, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(174)
							.addComponent(btnModificaC)
							.addContainerGap(195, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(39)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblClienteCerca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtClienteCerca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnCerca, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCF_PIVA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTipologia, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRS, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtRS, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCAP, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCAP, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblcitta, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCitta, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtVia, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNumero, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addComponent(btnModificaC, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(50, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
	}
	
	/* Definisce le azioni da eseguire in base al pulsante clickato.*/
	
	public void actionPerformed(ActionEvent e){
		if (btnAggiungi == e.getSource()){
			CL.aggiungi(this);
		}
		else if(btnElimina == e.getSource()){
			try{
			/* Inserire cosa fa il pulsante Elimina*/
			String CF_PIVA = txtCF_PIVA.getText();
			JOptionPane.showMessageDialog(null , "Veicolo Eliminato!");
			txtCF_PIVA.setText("");
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Cliente non Eliminato!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(btnCerca == e.getSource()){
			try{
			/* Inserire cosa fa il pulsante Cerca*/
			String targa = txtClienteCerca.getText();
			txtClienteCerca.setEditable(false);
			/*if veicolo trovato riempi i textfields*/
			txtTipologia.setEditable(true);
			txtRS.setEditable(true);
			txtCAP.setEditable(true);
			txtCitta.setEditable(true);
			txtVia.setEditable(true);
			txtNumero.setEditable(true);
			txtCF_PIVA.setEditable(true);
			txtEmail.setEditable(true);
			txtTelefono.setEditable(true);
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Cliente non Trovato!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(btnModificaC == e.getSource()){
			try{
			/* Inserire cosa fa il pulsante Modifica*/
				String Tipologia = txtTipologia.getText();
				String RS = txtRS.getText();
				String CAP = txtCAP.getText();
				String Citta = txtCitta.getText();
				String Via = txtVia.getText();
				String Numero = txtNumero.getText();
				String CF_PIVA = txtCF_PIVA.getText();
				String Email = txtEmail.getText();
				String Telefono = txtTelefono.getText();
				JOptionPane.showMessageDialog(null , "Cliente Modificato!");
				txtClienteCerca.setText("");
				txtClienteCerca.setEditable(true);
				txtTipologia.setEditable(false);
				txtRS.setEditable(false);
				txtCAP.setEditable(false);
				txtCitta.setEditable(false);
				txtVia.setEditable(false);
				txtNumero.setEditable(false);
				txtCF_PIVA.setEditable(false);
				txtEmail.setEditable(false);
				txtTelefono.setEditable(false);
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Cliente non Modificato!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}