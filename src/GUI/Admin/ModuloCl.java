package GUI.Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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

import db.DBConnect;
import Entità.Cliente;
import Utils.CostruisciTabella;
import Utils.TableColumnAdjuster;


public class ModuloCl extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 7526472295622776188L; 	
	private Cliente CL = new Cliente();
	private DBConnect Clienti = new DBConnect();
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
	public JTextField txtClienteCerca;
	
	
	/* Costruttore ModuloCl */
	
	public ModuloCl(String str){
		set(str);
	}

	public void set(String str){
		if (str == "Elenca"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elenco Clienti"));
			
			try{Clienti.exequery("SELECT * FROM cliente","select");}
			catch(SQLException e){
				JOptionPane.showMessageDialog(null, "Errore, impossibile caricare l'elenco clienti!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);}
			
			tblClienti = new JTable();
			tblClienti.setModel(new CostruisciTabella(Clienti.rs).model);
			tblClienti.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumnAdjuster tca = new TableColumnAdjuster(tblClienti);
			tca.adjustColumns();
			
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setViewportView(tblClienti);
			
			/* Crea il Layout per l'elenco dei Clienti. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.CENTER)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(360, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
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
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(200)
							.addComponent(btnAggiungi, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
							.addGap(190))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblMex, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(249, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRS, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblid, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCAP, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblcitta, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblVia, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNumero, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtTelefono)
								.addComponent(txtTipologia, Alignment.LEADING)
								.addComponent(txtCF_PIVA, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
								.addComponent(txtRS, Alignment.LEADING)
								.addComponent(txtCAP)
								.addComponent(txtCitta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
								.addComponent(txtVia, Alignment.LEADING)
								.addComponent(txtNumero, Alignment.LEADING)
								.addComponent(txtEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
							.addGap(15))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblid)
								.addComponent(txtCF_PIVA, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTipologia, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRS, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtRS, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCAP, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCAP, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblcitta, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCitta, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtVia, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNumero, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
							.addComponent(lblMex, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(41))
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
			
			JLabel lblid = new JLabel("CF o P.IVA*");
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
			this.setBorder(BorderFactory.createTitledBorder("Modifica Cliente"));
			
			btnCerca = new JButton("Cerca Cliente");
			btnCerca.setFont(new Font("Arial", Font.PLAIN, 12));
			btnCerca.addActionListener(this);	/* Action Listener per il bottone Cerca.*/
			
			txtClienteCerca = new JTextField();
			txtClienteCerca.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblClienteCerca = new JLabel("CF o PIVA Cliente da Modificare ");
			lblClienteCerca.setFont(new Font("Arial", Font.BOLD, 14));
		
			btnModificaC = new JButton("Modifica Cliente");
			btnModificaC.setFont(new Font("Arial", Font.PLAIN, 12));
			btnModificaC.addActionListener(this);	/* Action Listener per il bottone Modifica.*/
			
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
							.addGap(0, 0, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblRS, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCAP, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblcitta, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblVia, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNumero, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblClienteCerca)
										.addComponent(lblid, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtCF_PIVA, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtClienteCerca, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtTipologia, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtRS, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtCAP, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtCitta, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtVia, 150, 150, 150)
										.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
									.addGap(12))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(177)
									.addComponent(btnCerca))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(174)
									.addComponent(btnModificaC)))
							.addGap(12))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(39)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblClienteCerca)
								.addComponent(txtClienteCerca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnCerca, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblid)
								.addComponent(txtCF_PIVA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTipologia, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRS, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtRS, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCAP, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCAP, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblcitta, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCitta, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtVia, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNumero, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addComponent(btnModificaC, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
	}
	
	/* Definisce le azioni da eseguire in base al pulsante clickato.*/
	
	public void actionPerformed(ActionEvent e){
		if (btnAggiungi == e.getSource()){
			CL.setValori(this);
			CL.aggiungi(this);
		}
		else if(btnElimina == e.getSource()){
			CL.setID(this);
			CL.elimina(this);
		}
		else if(btnCerca == e.getSource()){
			CL.setIDcerca(this);
			CL.cerca(this);
			}
		else if(btnModificaC == e.getSource()){
			CL.setValori(this);
			CL.modifica(this);
		}
	}
}