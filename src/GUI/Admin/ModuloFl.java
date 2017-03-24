package GUI.Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
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


public class ModuloFl extends JPanel implements ActionListener{

	private JTable tblVeicoli;
	private JButton btnAggiungi;
	private JButton btnElimina;
	private JButton btnModificaV;
	private JButton btnCerca;
	private JScrollPane scroll = new JScrollPane(tblVeicoli);
	private JTextField txtTipologia;
	private JTextField txtNome;
	private JTextField txtDisp;
	private JTextField txtMarca;
	private JTextField txtAlimentazione;
	private JTextField txtKm;
	private JTextField txtTarga;
	private JTextField txtDimensioni;
	private JTextField txtTargaCerca;
	private JTextField txtBreve;
	private JTextField txtLungo;
	private JFormattedTextField frmtdtxtfldTipologia;
	private JFormattedTextField frmtdtxtfldImma2;
	private JFormattedTextField frmtdtxtfldBollo2;
	private JFormattedTextField frmtdtxtfldTagliando2;
	private JFormattedTextField frmtdtxtfldAssicurazione2;
	private JFormattedTextField frmtdtxtfldOrmeggio2;
	private JFormattedTextField frmtdtxtfldAlaggio2;
	
	/* Costruttore ModuloFl */
	
	public ModuloFl(String str){
		set(str);
	}

	public void set(String str){
		if (str == "Elenca"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elenco Veicoli"));
			
			tblVeicoli = new JTable();
			tblVeicoli.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Targa", "Tipologia","Nome","Disponibilità","Marca","Alimentazione","Km Effettuati","Dimensioni","Immatricolazione","Scadenza Bollo","Scadenza Tagliando", "Scadenza Assicurazione","Scadenza Ormeggio","Scadenza Alaggio"
				}
			));
			tblVeicoli.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setViewportView(tblVeicoli);
			
			/* Crea il Layout per l'elenco dei Veicoli. */
			
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
			this.setBorder(BorderFactory.createTitledBorder("Nuovo Veicolo"));
			
			btnAggiungi = new JButton("Aggiungi");
			btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAggiungi.addActionListener(this);	/* Action Listener per il bottone Aggiungi.*/
			
			txtTarga = new JTextField();
			txtTarga.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblTarga = new JLabel("Targa*");
			lblTarga.setFont(new Font("Arial", Font.BOLD, 14));
			
			JLabel lblTipologia = new JLabel("Tipologia*");
			lblTipologia.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtTipologia = new JTextField();
			txtTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
		
			txtNome = new JTextField();
			txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblNome = new JLabel("Nome*");
			lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblDisponibilita = new JLabel("Disponibilit\u00E0(si/no)*");
			lblDisponibilita.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtDisp = new JTextField();
			txtDisp.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblMarca = new JLabel("Marca*");
			lblMarca.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtMarca = new JTextField();
			txtMarca.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblAlimentazione = new JLabel("Alimentazione*");
			lblAlimentazione.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtAlimentazione = new JTextField();
			txtAlimentazione.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblKm = new JLabel("Km Effettuati*");
			lblKm.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtKm = new JTextField();
			txtKm.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblDimensioni = new JLabel("Dimensionii*");
			lblDimensioni.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtDimensioni = new JTextField();
			txtDimensioni.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblImma = new JLabel("Data Immatricolazione");
			lblImma.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblBollo = new JLabel("Data Scadenza Bollo");
			lblBollo.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblTagliando = new JLabel("Data Scadenza Tagliando");
			lblTagliando.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblAssicurazione = new JLabel("Data Scadenza Assicurazione");
			lblAssicurazione.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblOrmeggio = new JLabel("Data Scadenza Ormeggio");
			lblOrmeggio.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblAlaggio = new JLabel("Data Scadenza Alaggio");
			lblAlaggio.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblMex = new JLabel("Inserire tutti i campi con l'asterisco!");
			lblMex.setForeground(Color.RED);
			lblMex.setFont(new Font("Arial", Font.PLAIN, 14));
			
			DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
			
			frmtdtxtfldImma2 = new JFormattedTextField(dateformat);
			frmtdtxtfldImma2.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldImma2.setColumns(10);
			frmtdtxtfldImma2.setText("aaaa/mm/gg");
			
			frmtdtxtfldBollo2 = new JFormattedTextField(dateformat);
			frmtdtxtfldBollo2.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldBollo2.setColumns(10);
			frmtdtxtfldBollo2.setText("aaaa/mm/gg");
			
			frmtdtxtfldTagliando2 = new JFormattedTextField(dateformat);
			frmtdtxtfldTagliando2.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldTagliando2.setColumns(10);
			frmtdtxtfldTagliando2.setText("aaaa/mm/gg");
			
			frmtdtxtfldAssicurazione2 = new JFormattedTextField(dateformat);
			frmtdtxtfldAssicurazione2.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldAssicurazione2.setColumns(10);
			frmtdtxtfldAssicurazione2.setText("aaaa/mm/gg");
			
			frmtdtxtfldOrmeggio2 = new JFormattedTextField(dateformat);
			frmtdtxtfldOrmeggio2.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldOrmeggio2.setColumns(10);
			frmtdtxtfldOrmeggio2.setText("aaaa/mm/gg");
			
			frmtdtxtfldAlaggio2 = new JFormattedTextField(dateformat);
			frmtdtxtfldAlaggio2.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldAlaggio2.setColumns(10);
			frmtdtxtfldAlaggio2.setText("aaaa/mm/gg");
			
			JLabel lblBreve = new JLabel("Costo Breve Termine*");
			lblBreve.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtBreve = new JTextField();
			txtBreve.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblLungo = new JLabel("Costo Lungo Termine");
			lblLungo.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtLungo = new JTextField();
			txtLungo.setFont(new Font("Arial", Font.PLAIN, 12));
			
			/* Crea il Layout per un nuovo Veicolo. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblBreve, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAlaggio)
										.addComponent(lblOrmeggio)
										.addComponent(lblAssicurazione)
										.addComponent(lblBollo)
										.addComponent(lblImma)
										.addComponent(lblDimensioni, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblKm, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAlimentazione, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblMarca)
										.addComponent(lblDisponibilita)
										.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTarga, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTagliando)
										.addComponent(lblLungo, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))
									.addGap(107)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtLungo, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtBreve, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtfldAlaggio2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtfldOrmeggio2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtfldAssicurazione2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtfldTagliando2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtfldBollo2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
										.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(txtTarga, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtTipologia, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtDisp, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtAlimentazione, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtKm, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtDimensioni, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
											.addComponent(frmtdtxtfldImma2))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblMex, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(205)
									.addComponent(btnAggiungi)))
							.addContainerGap())
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtTarga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTarga))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtTipologia, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtDisp, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDisponibilita, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtAlimentazione, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAlimentazione, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtKm, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblKm, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtDimensioni, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDimensioni, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldImma2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblImma, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldBollo2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBollo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldTagliando2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTagliando, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldAssicurazione2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAssicurazione, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldOrmeggio2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblOrmeggio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldAlaggio2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAlaggio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtBreve, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBreve, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtLungo, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLungo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(lblMex, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(50))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str == "Elimina"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elimina Veicolo"));
			
			btnElimina = new JButton("Elimina Veicolo");
			btnElimina.setFont(new Font("Arial", Font.PLAIN, 12));
			btnElimina.addActionListener(this);	/* Action Listener per il bottone Elimina.*/
			
			txtTarga = new JTextField();
			txtTarga.setFont(new Font("Arial", Font.PLAIN, 12));
			txtTarga.setColumns(10);
			
			JLabel lblTarga = new JLabel("Targa Veicolo");
			lblTarga.setFont(new Font("Arial", Font.BOLD, 14));
			
			/* Crea il Layout per un eliminare un Veicolo. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(42)
							.addComponent(lblTarga, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
							.addComponent(txtTarga, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
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
								.addComponent(lblTarga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTarga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
			
			txtTargaCerca = new JTextField();
			txtTargaCerca.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblTargaCerca = new JLabel("Targa Veicolo da Modificare");
			lblTargaCerca.setFont(new Font("Arial", Font.BOLD, 14));
		
			btnModificaV = new JButton("Modifica Veicolo");
			btnModificaV.setFont(new Font("Arial", Font.PLAIN, 12));
			btnModificaV.addActionListener(this);	/* Action Listener per il bottone Modifica.*/
			
			txtTarga = new JTextField();
			txtTarga.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblTarga = new JLabel("Targa*");
			lblTarga.setFont(new Font("Arial", Font.BOLD, 14));
			
			JLabel lblTipologia = new JLabel("Tipologia*");
			lblTipologia.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtTipologia = new JTextField();
			txtTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
		
			txtNome = new JTextField();
			txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblNome = new JLabel("Nome*");
			lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblDisponibilita = new JLabel("Disponibilit\u00E0(si/no)*");
			lblDisponibilita.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtDisp = new JTextField();
			txtDisp.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblMarca = new JLabel("Marca*");
			lblMarca.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtMarca = new JTextField();
			txtMarca.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblAlimentazione = new JLabel("Alimentazione*");
			lblAlimentazione.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtAlimentazione = new JTextField();
			txtAlimentazione.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblKm = new JLabel("Km Effettuati*");
			lblKm.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtKm = new JTextField();
			txtKm.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblDimensioni = new JLabel("Dimensioni*");
			lblDimensioni.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtDimensioni = new JTextField();
			txtDimensioni.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblImma = new JLabel("Data Immatricolazione");
			lblImma.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblBollo = new JLabel("Data Scadenza Bollo");
			lblBollo.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblTagliando = new JLabel("Data Scadenza Tagliando");
			lblTagliando.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblAssicurazione = new JLabel("Data Scadenza Assicurazione");
			lblAssicurazione.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblOrmeggio = new JLabel("Data Scadenza Ormeggio");
			lblOrmeggio.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblAlaggio = new JLabel("Data Scadenza Alaggio");
			lblAlaggio.setFont(new Font("Arial", Font.BOLD, 14));
			
			DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
			
			frmtdtxtfldImma2 = new JFormattedTextField(dateformat);
			frmtdtxtfldImma2.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldImma2.setColumns(10);
			frmtdtxtfldImma2.setText("aaaa/mm/gg");
			
			frmtdtxtfldBollo2 = new JFormattedTextField(dateformat);
			frmtdtxtfldBollo2.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldBollo2.setColumns(10);
			frmtdtxtfldBollo2.setText("aaaa/mm/gg");
			
			frmtdtxtfldTagliando2 = new JFormattedTextField(dateformat);
			frmtdtxtfldTagliando2.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldTagliando2.setColumns(10);
			frmtdtxtfldTagliando2.setText("aaaa/mm/gg");
			
			frmtdtxtfldAssicurazione2 = new JFormattedTextField(dateformat);
			frmtdtxtfldAssicurazione2.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldAssicurazione2.setColumns(10);
			frmtdtxtfldAssicurazione2.setText("aaaa/mm/gg");
			
			frmtdtxtfldOrmeggio2 = new JFormattedTextField(dateformat);
			frmtdtxtfldOrmeggio2.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldOrmeggio2.setColumns(10);
			frmtdtxtfldOrmeggio2.setText("aaaa/mm/gg");
			
			frmtdtxtfldAlaggio2 = new JFormattedTextField(dateformat);
			frmtdtxtfldAlaggio2.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldAlaggio2.setColumns(10);
			frmtdtxtfldAlaggio2.setText("aaaa/mm/gg");
			
			txtTipologia.setEditable(false);
			txtNome.setEditable(false);
			txtDisp.setEditable(false);
			txtMarca.setEditable(false);
			txtAlimentazione.setEditable(false);
			txtKm.setEditable(false);
			txtTarga.setEditable(false);
			txtDimensioni.setEditable(false);
			frmtdtxtfldImma2.setEditable(false);
			frmtdtxtfldBollo2.setEditable(false);
			frmtdtxtfldTagliando2.setEditable(false);
			frmtdtxtfldAssicurazione2.setEditable(false);
			frmtdtxtfldOrmeggio2.setEditable(false);
			frmtdtxtfldAlaggio2.setEditable(false);
			
			/* Crea il Layout per modificare un Veicolo. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblTagliando, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
									.addComponent(lblBollo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
									.addComponent(lblImma, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
									.addComponent(lblAssicurazione)
									.addComponent(lblOrmeggio)
									.addComponent(lblAlaggio)
									.addComponent(lblDimensioni))
								.addComponent(lblTargaCerca, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNome, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
								.addComponent(lblTipologia, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
								.addComponent(lblTarga, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
								.addComponent(lblDisponibilita, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMarca, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
								.addComponent(lblAlimentazione, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
								.addComponent(lblKm, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
							.addGap(91)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(frmtdtxtfldImma2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(frmtdtxtfldAlaggio2, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(frmtdtxtfldAssicurazione2, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(frmtdtxtfldBollo2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
									.addComponent(frmtdtxtfldTagliando2))
								.addComponent(frmtdtxtfldOrmeggio2, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(txtKm, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(txtAlimentazione, 150, 150, 150)
								.addComponent(txtMarca, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(txtDisp, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(txtNome, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(txtTipologia, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(txtTarga, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(txtTargaCerca, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(txtDimensioni, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
							.addGap(418))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(173)
							.addComponent(btnModificaV)
							.addContainerGap(196, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(177)
							.addComponent(btnCerca)
							.addContainerGap(204, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(39)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTargaCerca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTargaCerca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnCerca, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTarga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTarga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTipologia, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDisponibilita, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDisp, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAlimentazione, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtAlimentazione, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblKm, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtKm, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDimensioni, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDimensioni, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblImma, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldImma2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBollo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldBollo2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTagliando, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldTagliando2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAssicurazione, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldAssicurazione2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldOrmeggio2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblOrmeggio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAlaggio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldAlaggio2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(32)
							.addComponent(btnModificaV, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(95, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
	}
	
	/* Definisce le azioni da eseguire in base al pulsante clickato.*/

	public void actionPerformed(ActionEvent e){
		if (btnAggiungi == e.getSource()){
			try{
			/* Inserire cosa fa il pulsante Aggiungi*/
			String Tipologia = txtTipologia.getText();
			String Nome = txtNome.getText();
			String Disp = txtDisp.getText();
			String Marca = txtMarca.getText();
			String Alimentazione = txtAlimentazione.getText();
			String Km = txtKm.getText();
			String Targa = txtTarga.getText();
			String Dimensioni = txtDimensioni.getText();
			String Imma = frmtdtxtfldImma2.getText();
			String Bollo = frmtdtxtfldBollo2.getText();
			String Tagliando = frmtdtxtfldTagliando2.getText();
			String Assicurazione = frmtdtxtfldAssicurazione2.getText();
			String Ormeggio = frmtdtxtfldOrmeggio2.getText();
			String Alaggio = frmtdtxtfldAlaggio2.getText();
			String Breve = txtBreve.getText();
			String Lungo = txtLungo.getText();
			JOptionPane.showMessageDialog(null , "Nuovo Veicolo Aggiunto!");
			txtTipologia.setText("");
			txtNome.setText("");
			txtDisp.setText("");
			txtMarca.setText("");
			txtAlimentazione.setText("");
			txtKm.setText("");
			txtTarga.setText("");
			txtDimensioni.setText("");
			frmtdtxtfldImma2.setText("aaaa/mm/gg");
			frmtdtxtfldBollo2.setText("aaaa/mm/gg");
			frmtdtxtfldTagliando2.setText("aaaa/mm/gg");
			frmtdtxtfldAssicurazione2.setText("aaaa/mm/gg");
			frmtdtxtfldOrmeggio2.setText("aaaa/mm/gg");
			frmtdtxtfldAlaggio2.setText("aaaa/mm/gg");
			txtBreve.setText("");
			txtLungo.setText("");
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
			String targa = txtTarga.getText();
			JOptionPane.showMessageDialog(null , "Veicolo Eliminato!");
			txtTarga.setText("");
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Veicolo non Eliminato!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(btnCerca == e.getSource()){
			try{
			/* Inserire cosa fa il pulsante Cerca*/
			String targa = txtTarga.getText();
			txtTargaCerca.setEditable(false);
			/*if veicolo trovato riempi i textfields*/
			txtTipologia.setEditable(true);
			txtNome.setEditable(true);
			txtDisp.setEditable(true);
			txtMarca.setEditable(true);
			txtAlimentazione.setEditable(true);
			txtKm.setEditable(true);
			txtTarga.setEditable(true);
			txtDimensioni.setEditable(true);
			frmtdtxtfldImma2.setEditable(true);
			frmtdtxtfldBollo2.setEditable(true);
			frmtdtxtfldTagliando2.setEditable(true);
			frmtdtxtfldAssicurazione2.setEditable(true);
			frmtdtxtfldOrmeggio2.setEditable(true);
			frmtdtxtfldAlaggio2.setEditable(true);
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Veicolo non Trovato!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(btnModificaV == e.getSource()){
			try{
			/* Inserire cosa fa il pulsante Modifica*/
				String Tipologia = txtTipologia.getText();
				String Nome = txtNome.getText();
				String Disp = txtDisp.getText();
				String Marca = txtMarca.getText();
				String Alimentazione = txtAlimentazione.getText();
				String Km = txtKm.getText();
				String Targa = txtTarga.getText();
				String Dimensioni = txtDimensioni.getText();
				String Imma = frmtdtxtfldImma2.getText();
				String Bollo = frmtdtxtfldBollo2.getText();
				String Tagliando = frmtdtxtfldTagliando2.getText();
				String Assicurazione = frmtdtxtfldAssicurazione2.getText();
				String Ormeggio = frmtdtxtfldOrmeggio2.getText();
				String Alaggio = frmtdtxtfldAlaggio2.getText();
				JOptionPane.showMessageDialog(null , "Veicolo Modificato!");
				txtTargaCerca.setText("");
				txtTargaCerca.setEditable(true);
				txtTipologia.setEditable(false);
				txtNome.setEditable(false);
				txtDisp.setEditable(false);
				txtMarca.setEditable(false);
				txtAlimentazione.setEditable(false);
				txtKm.setEditable(false);
				txtTarga.setEditable(false);
				txtDimensioni.setEditable(false);
				frmtdtxtfldImma2.setEditable(false);
				frmtdtxtfldBollo2.setEditable(false);
				frmtdtxtfldTagliando2.setEditable(false);
				frmtdtxtfldAssicurazione2.setEditable(false);
				frmtdtxtfldOrmeggio2.setEditable(false);
				frmtdtxtfldAlaggio2.setEditable(false);
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Veicolo non Modificato!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}