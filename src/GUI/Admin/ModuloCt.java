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
import javax.swing.SwingConstants;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;


public class ModuloCt extends JPanel implements ActionListener{

	JLabel lblPreventivo;
	private JTable tblNoleggi;
	private JButton btnFiltra;
	private JButton btnAggiungi;
	private JButton btnCalcola;
	private JScrollPane scroll = new JScrollPane(tblNoleggi);
	private JTextField txtRilasciatada;
	private JTextField txtCliente;
	private JTextField txtVeicolo;
	private JTextField txtTipologia;
	private JTextField txtCosto;
	private JTextField txtPagato;
	private JTextField txtNome;
	private JTextField txtCognome;
	private JTextField txtPatente;
	private JFormattedTextField frmtdtxtfldinizio;
	private JFormattedTextField frmtdtxtfldFine;
	private JFormattedTextField frmtdtxtfldTipologia;
	private JFormattedTextField frmtdtxtfldRilasciatail2;
	private JFormattedTextField frmtdtxtfldValida2;
	private JFormattedTextField frmtdtxtfldInizio2;
	private JFormattedTextField frmtdtxtfldFine2;
	
	/* Costruttore ModuloCt */
	
	public ModuloCt(String str){
		set(str);
	}

	public void set(String str){
		if (str == "Elenca"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elenco Contratti"));
			
			tblNoleggi = new JTable();
			tblNoleggi.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Codice Noleggio", "Tipologia","Costo Totale","Pagato","Data Inizio","Data Fine","Nome","Cognome","Numero Patente","Rilasciata Da","Rilasciata il","Valida Fino a"
				}
			));
			tblNoleggi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setViewportView(tblNoleggi);
			
			btnFiltra = new JButton("Filtra Contratti");
			btnFiltra.addActionListener(this); 	/* Action Listener per il bottone Filtra.*/
			
			JLabel lblCliente = new JLabel("Cliente da Filtrare");
			lblCliente.setFont(new Font("Arial", Font.BOLD, 13));
			
			txtCliente = new JTextField();
			txtCliente.setColumns(10);
			
			JLabel lblVeicoloDaFiltrare = new JLabel("Veicolo da Filtrare");
			lblVeicoloDaFiltrare.setFont(new Font("Arial", Font.BOLD, 13));
			
			txtVeicolo = new JTextField();
			txtVeicolo.setColumns(10);
			
			/* Crea il Layout per l'elenco dei Contratti. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(51)
									.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
									.addGap(69))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(txtCliente)
										.addComponent(lblCliente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblVeicoloDaFiltrare, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(txtVeicolo, Alignment.TRAILING))
									.addGap(108)
									.addComponent(btnFiltra, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
							.addGap(18, 19, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCliente)
								.addComponent(lblVeicoloDaFiltrare, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnFiltra)
								.addComponent(txtCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtVeicolo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str == "Nuovo"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Nuovo Contratto di Noleggio"));
			
			btnAggiungi = new JButton("Aggiungi");
			btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAggiungi.addActionListener(this);
			
			frmtdtxtfldTipologia = new JFormattedTextField();
			frmtdtxtfldTipologia.setText("Tipologia*");
			frmtdtxtfldTipologia.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldTipologia.setForeground(Color.BLACK);
			frmtdtxtfldTipologia.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldTipologia.setEditable(false);
			frmtdtxtfldTipologia.setBorder(null);
		
			txtTipologia = new JTextField();
			txtTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JFormattedTextField frmtdtxtflddatainizio = new JFormattedTextField();
			frmtdtxtflddatainizio.setText("Data Inizio*");
			frmtdtxtflddatainizio.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtflddatainizio.setForeground(Color.BLACK);
			frmtdtxtflddatainizio.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtflddatainizio.setEditable(false);
			frmtdtxtflddatainizio.setBorder(null);
		
			JFormattedTextField frmtdtxtflddatafine = new JFormattedTextField();
			frmtdtxtflddatafine.setText("Data Fine*");
			frmtdtxtflddatafine.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtflddatafine.setForeground(Color.BLACK);
			frmtdtxtflddatafine.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtflddatafine.setEditable(false);
			frmtdtxtflddatafine.setBorder(null);
		
			JFormattedTextField frmtdtxtfldcosto = new JFormattedTextField();
			frmtdtxtfldcosto.setText("Costo Totale*");
			frmtdtxtfldcosto.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldcosto.setForeground(Color.BLACK);
			frmtdtxtfldcosto.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldcosto.setEditable(false);
			frmtdtxtfldcosto.setBorder(null);
		
			txtCosto = new JTextField();
			txtCosto.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JFormattedTextField frmtdtxtfldPagato = new JFormattedTextField();
			frmtdtxtfldPagato.setText("Gi\u00E0 Pagato");
			frmtdtxtfldPagato.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldPagato.setForeground(Color.BLACK);
			frmtdtxtfldPagato.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldPagato.setEditable(false);
			frmtdtxtfldPagato.setBorder(null);
		
			txtPagato = new JTextField();
			txtPagato.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JFormattedTextField frmtdtxtfldNome = new JFormattedTextField();
			frmtdtxtfldNome.setText("Nome*");
			frmtdtxtfldNome.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldNome.setForeground(Color.BLACK);
			frmtdtxtfldNome.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldNome.setEditable(false);
			frmtdtxtfldNome.setBorder(null);
		
			txtNome = new JTextField();
			txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JFormattedTextField frmtdtxtfldCognome = new JFormattedTextField();
			frmtdtxtfldCognome.setText("Cognome*");
			frmtdtxtfldCognome.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldCognome.setForeground(Color.BLACK);
			frmtdtxtfldCognome.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldCognome.setEditable(false);
			frmtdtxtfldCognome.setBorder(null);
			
			txtCognome = new JTextField();
			txtCognome.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JFormattedTextField frmtdtxtfldPatente = new JFormattedTextField();
			frmtdtxtfldPatente.setText("Numero Patente*");
			frmtdtxtfldPatente.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldPatente.setForeground(Color.BLACK);
			frmtdtxtfldPatente.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldPatente.setEditable(false);
			frmtdtxtfldPatente.setBorder(null);
		
			txtPatente = new JTextField();
			txtPatente.setFont(new Font("Arial", Font.PLAIN, 12));
						
			JFormattedTextField frmtdtxtfldMex = new JFormattedTextField();
			frmtdtxtfldMex.setText("Inserire tutti i campi con l'asterisco!");
			frmtdtxtfldMex.setHorizontalAlignment(SwingConstants.LEFT);
			frmtdtxtfldMex.setForeground(Color.RED);
			frmtdtxtfldMex.setFont(new Font("Arial", Font.PLAIN, 14));
			frmtdtxtfldMex.setEditable(false);
			frmtdtxtfldMex.setBorder(null);
			
			DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
			
			frmtdtxtfldinizio = new JFormattedTextField(dateformat);
			frmtdtxtfldinizio.setText("aaaa/mm/gg");
		
			frmtdtxtfldFine = new JFormattedTextField(dateformat);
			frmtdtxtfldFine.setText("aaaa/mm/gg");
		
			JFormattedTextField frmtdtxtfldRilasciatada = new JFormattedTextField();
			frmtdtxtfldRilasciatada.setText("Rilasciata da");
			frmtdtxtfldRilasciatada.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldRilasciatada.setForeground(Color.BLACK);
			frmtdtxtfldRilasciatada.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldRilasciatada.setEditable(false);
			frmtdtxtfldRilasciatada.setBorder(null);
		
			txtRilasciatada = new JTextField();
			txtRilasciatada.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JFormattedTextField frmtdtxtfldRilasciatail = new JFormattedTextField();
			frmtdtxtfldRilasciatail.setText("Rilasciata il");
			frmtdtxtfldRilasciatail.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldRilasciatail.setForeground(Color.BLACK);
			frmtdtxtfldRilasciatail.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldRilasciatail.setEditable(false);
			frmtdtxtfldRilasciatail.setBorder(null);
		
			JFormattedTextField frmtdtxtfldValida = new JFormattedTextField();
			frmtdtxtfldValida.setText("Valida Fino a");
			frmtdtxtfldValida.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldValida.setForeground(Color.BLACK);
			frmtdtxtfldValida.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldValida.setEditable(false);
			frmtdtxtfldValida.setBorder(null);
		
			frmtdtxtfldRilasciatail2 = new JFormattedTextField(dateformat);
			frmtdtxtfldRilasciatail2.setText("aaaa/mm/gg");
		
			frmtdtxtfldValida2 = new JFormattedTextField(dateformat);
			frmtdtxtfldValida2.setText("aaaa/mm/gg");
		
			JFormattedTextField frmtdtxtfldCliente = new JFormattedTextField();
			frmtdtxtfldCliente.setText("Cliente*");
			frmtdtxtfldCliente.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldCliente.setForeground(Color.BLACK);
			frmtdtxtfldCliente.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldCliente.setEditable(false);
			frmtdtxtfldCliente.setBorder(null);
		
			JFormattedTextField frmtdtxtfldVeicolo = new JFormattedTextField();
			frmtdtxtfldVeicolo.setText("Veicolo*");
			frmtdtxtfldVeicolo.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldVeicolo.setForeground(Color.BLACK);
			frmtdtxtfldVeicolo.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldVeicolo.setEditable(false);
			frmtdtxtfldVeicolo.setBorder(null);
		
			txtCliente = new JTextField();
			txtCliente.setFont(new Font("Arial", Font.PLAIN, 12));
		
			txtVeicolo = new JTextField();
			txtVeicolo.setFont(new Font("Arial", Font.PLAIN, 12));
			
			/* Crea il Layout per un nuovo Contratto. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(frmtdtxtfldTipologia, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtflddatainizio, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtfldNome, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtflddatafine, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtfldcosto, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtfldPagato, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtfldPatente, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtfldCognome, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(81)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(frmtdtxtfldinizio, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
												.addComponent(txtTipologia, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
												.addComponent(txtPagato, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
												.addComponent(txtCosto, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
												.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
												.addComponent(txtCognome, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
												.addComponent(txtPatente, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(frmtdtxtfldFine, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(frmtdtxtfldRilasciatada, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
									.addComponent(txtRilasciatada, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(frmtdtxtfldRilasciatail, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
									.addComponent(frmtdtxtfldRilasciatail2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(frmtdtxtfldValida, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
									.addComponent(frmtdtxtfldValida2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(frmtdtxtfldCliente, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
									.addComponent(txtCliente, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(frmtdtxtfldVeicolo, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
									.addComponent(txtVeicolo, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(frmtdtxtfldMex, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(190)
											.addComponent(btnAggiungi, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
											.addGap(15)))
									.addGap(175))))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldTipologia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTipologia, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtflddatainizio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldinizio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtflddatafine, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldFine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldcosto, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCosto, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldPagato, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPagato, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldNome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldCognome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldPatente, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPatente, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldRilasciatada, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtRilasciatada, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldRilasciatail, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldRilasciatail2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldValida, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldValida2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldCliente, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCliente, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldVeicolo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtVeicolo, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addComponent(frmtdtxtfldMex, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(28, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str == "Preventivo"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Calcola Preventivo Contratto di Noleggio"));
			
			btnCalcola = new JButton("Calcola Preventivo");
			btnCalcola.setFont(new Font("Arial", Font.PLAIN, 12));
			btnCalcola.addActionListener(this);	/* Action Listener per il bottone Calcola.*/
			
			txtVeicolo = new JTextField();
			txtVeicolo.setFont(new Font("Arial", Font.PLAIN, 12));
			txtVeicolo.setColumns(10);
			
			JFormattedTextField frmtdtxtfldVeicolo = new JFormattedTextField();
			frmtdtxtfldVeicolo.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldVeicolo.setBorder(null);
			frmtdtxtfldVeicolo.setForeground(new Color(0, 0, 0));
			frmtdtxtfldVeicolo.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldVeicolo.setEditable(false);
			frmtdtxtfldVeicolo.setText("Veicolo Da Noleggiare");
		
			JFormattedTextField frmtdtxtfldInizio = new JFormattedTextField();
			frmtdtxtfldInizio.setText("Data Inizio Noleggio");
			frmtdtxtfldInizio.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldInizio.setForeground(Color.BLACK);
			frmtdtxtfldInizio.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldInizio.setEditable(false);
			frmtdtxtfldInizio.setBorder(null);
		
			JFormattedTextField frmtdtxtfldFine = new JFormattedTextField();
			frmtdtxtfldFine.setText("Data Fine Noleggio");
			frmtdtxtfldFine.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldFine.setForeground(Color.BLACK);
			frmtdtxtfldFine.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtfldFine.setEditable(false);
			frmtdtxtfldFine.setBorder(null);
			
			DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
			
			frmtdtxtfldInizio2 = new JFormattedTextField(dateformat);
			frmtdtxtfldInizio2.setText("aaaa/mm/gg");
		
			frmtdtxtfldFine2 = new JFormattedTextField(dateformat);
			frmtdtxtfldFine2.setText("aaaa/mm/gg");
		
			lblPreventivo = new JLabel("");
			lblPreventivo.setFont(new Font("Arial", Font.BOLD, 14));
			lblPreventivo.setHorizontalAlignment(SwingConstants.CENTER);
		
			/* Crea il Layout per calcolare un Preventivo. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(frmtdtxtfldVeicolo, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldInizio, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldFine, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(frmtdtxtfldFine2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(frmtdtxtfldInizio2)
									.addComponent(txtVeicolo, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)))
							.addGap(62))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(163)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblPreventivo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnCalcola, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addContainerGap(194, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldVeicolo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtVeicolo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldInizio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldInizio2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldFine, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldFine2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(27)
							.addComponent(lblPreventivo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnCalcola, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(50, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}		
	}
	
	/* Definisce le azioni da eseguire in base al pulsante clickato.*/
	
	public void actionPerformed(ActionEvent e){
		if (btnFiltra == e.getSource()){
			try{
			/* Inserire cosa fa il pulsante Filtra*/
			String Cliente = txtCliente.getText();
			String Veicolo = txtVeicolo.getText();
			txtCliente.setText("");
		    txtVeicolo.setText("");
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Contratti non trovati!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(btnAggiungi == e.getSource()){
			try{
			/* Inserire cosa fa il pulsante Aggiungi*/
			String Tipologia = txtTipologia.getText();
			String Inizio = frmtdtxtfldinizio.getText();
			String Fine = frmtdtxtfldFine.getText();
			String Costo = txtCosto.getText();
			String Pagato = txtPagato.getText();
			String Nome = txtNome.getText();
			String Cognome = txtCognome.getText();
			String Patente = txtPatente.getText();
			String Rilasciatada = txtRilasciatada.getText();
			String Rilasciatail = frmtdtxtfldRilasciatail2.getText();
			String Valida = frmtdtxtfldValida2.getText();
			String Cliente = txtCliente.getText();
			String Veicolo = txtVeicolo.getText();
			JOptionPane.showMessageDialog(null , "Nuovo Contratto Aggiunto!");
			txtTipologia.setText("");
			frmtdtxtfldinizio.setText("aaaa/mm/gg");
			frmtdtxtfldFine.setText("aaaa/mm/gg");
			txtCosto.setText("");
			txtPagato.setText("");
			txtNome.setText("");
			txtCognome.setText("");
			txtPatente.setText("");
			txtRilasciatada.setText("");
			frmtdtxtfldRilasciatail2.setText("aaaa/mm/gg");
			frmtdtxtfldValida2.setText("aaaa/mm/gg");
			txtCliente.setText("");
			txtVeicolo.setText("");
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Contratto non Aggiunto!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(btnCalcola == e.getSource()){
			try{
			/* Inserire cosa fa il pulsante Calcola*/
			String Veicolo = txtVeicolo.getText();
			String Inizio = frmtdtxtfldInizio2.getText();
			String Fine = frmtdtxtfldFine2.getText();
			lblPreventivo.setText("0000");
			txtVeicolo.setText("");
			frmtdtxtfldInizio2.setText("aaaa/mm/gg");
			frmtdtxtfldFine2.setText("aaaa/mm/gg");
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Preventivo non Calcolato!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
