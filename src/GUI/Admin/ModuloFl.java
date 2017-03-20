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
	private JButton btnAggiungi;
	private JButton btnElimina;
	private JButton btnModifica;
	private JButton btnCerca;
	private JScrollPane scroll = new JScrollPane(tblOperatori);
	private JTextField txtTipologia;
	private JTextField txtNome;
	private JTextField txtDisp;
	private JTextField txtMarca;
	private JTextField txtAlimentazione;
	private JTextField txtKm;
	private JTextField txtTarga;
	private JTextField txtDimensioni;
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
			
			tblOperatori = new JTable();
			tblOperatori.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Targa", "Tipologia","Nome","Disponibilità","Marca","Alimentazione","Km Effettuati","Dimensioni","Data Immatricolazione","Data Scadenza Bollo","Data Scadenza Tagliando", "Data Scadenza Assicurazione","Data Scadenza Ormeggio","Data Scadenza Alaggio"
				}
			));
			tblOperatori.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
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
			btnAggiungi.addActionListener(this);	/* Action Listener per il bottone Aggiungi.*/
			
			txtTarga = new JTextField();
			txtTarga.setFont(new Font("Arial", Font.PLAIN, 12));
			
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
		
			txtNome = new JTextField();
			txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JFormattedTextField frmtdtxtfldNome = new JFormattedTextField();
			frmtdtxtfldNome.setText("Nome*");
			frmtdtxtfldNome.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldNome.setForeground(Color.BLACK);
			frmtdtxtfldNome.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldNome.setEditable(false);
			frmtdtxtfldNome.setBorder(null);
		
			JFormattedTextField frmtdtxtfldDisponibilita = new JFormattedTextField();
			frmtdtxtfldDisponibilita.setText("Disponibilit\u00E0(si/no)*");
			frmtdtxtfldDisponibilita.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldDisponibilita.setForeground(Color.BLACK);
			frmtdtxtfldDisponibilita.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldDisponibilita.setEditable(false);
			frmtdtxtfldDisponibilita.setBorder(null);
		
			txtDisp = new JTextField();
			txtDisp.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JFormattedTextField frmtdtxtfldMarca = new JFormattedTextField();
			frmtdtxtfldMarca.setText("Marca*");
			frmtdtxtfldMarca.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldMarca.setForeground(Color.BLACK);
			frmtdtxtfldMarca.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldMarca.setEditable(false);
			frmtdtxtfldMarca.setBorder(null);
		
			txtMarca = new JTextField();
			txtMarca.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JFormattedTextField frmtdtxtfldAlimentazione = new JFormattedTextField();
			frmtdtxtfldAlimentazione.setText("Alimentazione*");
			frmtdtxtfldAlimentazione.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldAlimentazione.setForeground(Color.BLACK);
			frmtdtxtfldAlimentazione.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldAlimentazione.setEditable(false);
			frmtdtxtfldAlimentazione.setBorder(null);
		
			txtAlimentazione = new JTextField();
			txtAlimentazione.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JFormattedTextField frmtdtxtfldKm = new JFormattedTextField();
			frmtdtxtfldKm.setText("Km Effettuati*");
			frmtdtxtfldKm.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldKm.setForeground(Color.BLACK);
			frmtdtxtfldKm.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldKm.setEditable(false);
			frmtdtxtfldKm.setBorder(null);
		
			txtKm = new JTextField();
			txtKm.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JFormattedTextField frmtdtxtfldDimnesioni = new JFormattedTextField();
			frmtdtxtfldDimnesioni.setText("Dimensionii*");
			frmtdtxtfldDimnesioni.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldDimnesioni.setForeground(Color.BLACK);
			frmtdtxtfldDimnesioni.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldDimnesioni.setEditable(false);
			frmtdtxtfldDimnesioni.setBorder(null);
			
			txtDimensioni = new JTextField();
			txtDimensioni.setFont(new Font("Arial", Font.PLAIN, 12));
			
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
			
			/* Crea il Layout per un nuovo Veicolo. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
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
												.addComponent(txtAlimentazione, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(frmtdtxtfldKm, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
											.addGap(91)
											.addComponent(txtKm, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
									.addGap(61))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(305)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
													.addComponent(frmtdtxtfldImma2)
													.addComponent(frmtdtxtfldBollo2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
													.addComponent(frmtdtxtfldTagliando2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
													.addComponent(frmtdtxtfldAssicurazione2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
													.addComponent(frmtdtxtfldOrmeggio2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
													.addComponent(frmtdtxtfldAlaggio2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(frmtdtxtfldImma, Alignment.LEADING)
												.addComponent(frmtdtxtfldBollo, Alignment.LEADING)
												.addComponent(frmtdtxtfldTagliando, Alignment.LEADING)
												.addComponent(frmtdtxtfldAlaggio, Alignment.LEADING)
												.addComponent(frmtdtxtfldOrmeggio, Alignment.LEADING)
												.addComponent(frmtdtxtfldAssicurazione, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addComponent(frmtdtxtfldMex, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(193)
												.addComponent(btnAggiungi)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(frmtdtxtfldDimnesioni, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
											.addGap(91)
											.addComponent(txtDimensioni, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
									.addContainerGap())))
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
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(2)
									.addComponent(frmtdtxtfldDimnesioni, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtDimensioni, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldImma, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldImma2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldBollo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldBollo2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldTagliando, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldTagliando2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldAssicurazione, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldAssicurazione2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldOrmeggio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldOrmeggio2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldAlaggio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldAlaggio2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(frmtdtxtfldMex, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(53)
							.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(50, Short.MAX_VALUE))
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
			
			JFormattedTextField frmtdtxtTarga = new JFormattedTextField();
			frmtdtxtTarga.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtTarga.setBorder(null);
			frmtdtxtTarga.setForeground(new Color(0, 0, 0));
			frmtdtxtTarga.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtTarga.setEditable(false);
			frmtdtxtTarga.setText("Targa Veicolo");
			
			/* Crea il Layout per un eliminare un Veicolo. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(42)
							.addComponent(frmtdtxtTarga, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
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
								.addComponent(frmtdtxtTarga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
			
			txtTarga = new JTextField();
			txtTarga.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JFormattedTextField frmtdtxtTarga = new JFormattedTextField();
			frmtdtxtTarga.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtTarga.setBorder(null);
			frmtdtxtTarga.setForeground(new Color(0, 0, 0));
			frmtdtxtTarga.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtTarga.setEditable(false);
			frmtdtxtTarga.setText("Targa Veicolo da Modificare");
			
			/* Crea il Layout per modificare un Veicolo. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(42)
							.addComponent(frmtdtxtTarga, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
							.addComponent(txtTarga, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
							.addGap(39))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(191)
							.addComponent(btnCerca)
							.addContainerGap(190, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtTarga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTarga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnCerca, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(267, Short.MAX_VALUE))
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
			txtTarga.setText("");
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Veicolo non Trovato!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}