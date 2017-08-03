package gui.moduli;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import db.DBConnect;
import utils.CostruisciTabella;
import utils.TableColumnAdjuster;

public class ModuloExtra extends JPanel implements ActionListener, FocusListener{

	private static final long serialVersionUID = 1L; 
	
	private JButton btnProfitto;
	private JButton btnProfittoA;
	private JFormattedTextField frmtdtxtfldMese;
	private JFormattedTextField frmtdtxtfldAnno;
	
	private JTable tblAlaggio;
	private JTable tblAssicurazione;
	private JTable tblBollo;
	private JTable tblOrmeggio;
	private JLabel lblProfitto;
	private JTable tblTagliando;
	private JTable tblVeicoli;
	private JScrollPane scroll = new JScrollPane(tblVeicoli);
	private JScrollPane scrollAlaggio = new JScrollPane(tblAlaggio);
	private JScrollPane scrollAssicurazione = new JScrollPane(tblAssicurazione);
	private JScrollPane scrollBollo = new JScrollPane(tblBollo);
	private JScrollPane scrollOrmeggio = new JScrollPane(tblOrmeggio);
	private JScrollPane scrollTagliando = new JScrollPane(tblTagliando);
	
	private static final String MONTHPATTERN = "^\\d{4}-\\d{2}$";
	private static final String YEARPATTERN = "^\\d{4}$";
	
	private DBConnect Extra = new DBConnect();
	private DBConnect Profitto = new DBConnect();
	
	private String dataQuery, dataQuery2;
	
	/* Costruttori ModuloEx */
	
	public ModuloExtra (String str) {
		set(str);	
	}

	public ModuloExtra() {
		this.setBorder(BorderFactory.createTitledBorder("Funzionalità Aggiuntive"));
		
		JLabel lblFunz = new JLabel("Seleziona la funzionalità desiderata");
		lblFunz.setHorizontalAlignment(SwingConstants.CENTER);
		lblFunz.setFont(new Font("Arial", Font.BOLD, 14));
		
		/* Crea il Layout iniziale per il pannello funzionalità aggiuntive. */
		
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
						.addContainerGap(50, Short.MAX_VALUE)
						.addComponent(lblFunz, GroupLayout.PREFERRED_SIZE,420, GroupLayout.PREFERRED_SIZE)
						.addGap(170))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(120)
						.addComponent(lblFunz, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(255, Short.MAX_VALUE))
			);
		this.setLayout(gl_contentPane);
	}
	
	public void set(String str) {
		if (str.equals("Statistica")) {
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elenco veicoli più noleggiati"));
			
			try {
				Extra.exequery("SELECT COUNT(*) as Numero_Noleggi, b.Targa, b.Tipologia, b.Marca, b.Nome, b.Disponibilita "
						+ "FROM noleggio a INNER JOIN veicolo b ON a.Veicolo = b.Targa GROUP BY b.Targa ORDER BY Numero_Noleggi DESC","select");
			}
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Errore! Impossibile caricare l'elenco dei veicoli più noleggiati!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
			}
			
			tblVeicoli = new JTable();
			tblVeicoli.setModel(new CostruisciTabella(Extra.rs).model);
			tblVeicoli.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			JTableHeader header= tblVeicoli.getTableHeader();
			TableColumnModel colMod = header.getColumnModel();
			TableColumn tabCol = colMod.getColumn(0);
			tabCol.setHeaderValue("Noleggi");
			
			TableColumnAdjuster tca = new TableColumnAdjuster(tblVeicoli);
			tca.adjustColumns();
			
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setViewportView(tblVeicoli);
			
			/* Crea il Layout per l'elenco dei veicoli più noleggiati. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(54, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
							.addContainerGap())
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		} 
		else if (str.equals("Mensile")) {
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Profitto Mensile"));
			
			JLabel lblMensile = new JLabel("Profitto Mensile");
			lblMensile.setHorizontalAlignment(SwingConstants.CENTER);
			lblMensile.setFont(new Font("Arial", Font.BOLD, 13));
			
			JLabel lblMese = new JLabel("Seleziona Anno-Mese");
			lblMese.setHorizontalAlignment(SwingConstants.CENTER);
			lblMese.setFont(new Font("Arial", Font.BOLD, 12));
			
			btnProfitto = new JButton("Calcola Profitto");
			btnProfitto.addActionListener(this);	/* Action Listener per il bottone Profitto.*/
			btnProfitto.addFocusListener(this);
			
			lblProfitto = new JLabel("");
			lblProfitto.setFont(new Font("Arial", Font.BOLD, 12));
			lblProfitto.setHorizontalAlignment(SwingConstants.CENTER);
			
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM");
			dateformat.setLenient(false);
			
			frmtdtxtfldMese = new JFormattedTextField(dateformat);
			frmtdtxtfldMese.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldMese.setColumns(7);
			frmtdtxtfldMese.setText("aaaa-mm");
			frmtdtxtfldMese.addFocusListener(this);
			
			/* Crea il Layout per il profitto mensile. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(114)
									.addComponent(lblMensile, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(120)
									.addComponent(lblProfitto, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(121, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(34)
							.addComponent(lblMese, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addGap(42)
							.addComponent(frmtdtxtfldMese, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
							.addComponent(btnProfitto)
							.addGap(46))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(26)
							.addComponent(lblMensile, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldMese, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMese, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnProfitto))
							.addGap(30)
							.addComponent(lblProfitto, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(162, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str.equals("Annuale")) {
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Profitto Annuale"));
			
			JLabel lblAnnuale = new JLabel("Profitto Annuale");
			lblAnnuale.setHorizontalAlignment(SwingConstants.CENTER);
			lblAnnuale.setFont(new Font("Arial", Font.BOLD, 13));
			
			JLabel lblAnno = new JLabel("Seleziona Anno");
			lblAnno.setHorizontalAlignment(SwingConstants.CENTER);
			lblAnno.setFont(new Font("Arial", Font.BOLD, 12));
			
			btnProfittoA = new JButton("Calcola Profitto");
			btnProfittoA.addActionListener(this);	/* Action Listener per il bottone Profitto Annuale.*/
			btnProfittoA.addFocusListener(this);
			
			lblProfitto = new JLabel("");
			lblProfitto.setFont(new Font("Arial", Font.BOLD, 12));
			lblProfitto.setHorizontalAlignment(SwingConstants.CENTER);
			
			DateFormat dateformat = new SimpleDateFormat("yyyy");
			dateformat.setLenient(false);
			
			frmtdtxtfldAnno = new JFormattedTextField(dateformat);
			frmtdtxtfldAnno.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldAnno.setColumns(4);
			frmtdtxtfldAnno.setText("aaaa");
			frmtdtxtfldAnno.addFocusListener(this);
			
			/* Crea il Layout per il profitto annuale. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(114)
									.addComponent(lblAnnuale, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(120)
									.addComponent(lblProfitto, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(121, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(34)
							.addComponent(lblAnno, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addGap(42)
							.addComponent(frmtdtxtfldAnno, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
							.addComponent(btnProfittoA)
							.addGap(46))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(26)
							.addComponent(lblAnnuale, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldAnno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAnno, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnProfittoA))
							.addGap(30)
							.addComponent(lblProfitto, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(162, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str.equals("Scadenze")){
			DateFormat fmt = new SimpleDateFormat("yyyy-MM");
			Calendar c = Calendar.getInstance();
			dataQuery = fmt.format(c.getTime());
			c.setTime(c.getTime());
			c.add(Calendar.MONTH,1);
			dataQuery2 = fmt.format(c.getTime());
			
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Prossime Scadenze"));
			
			JLabel lblBollo = new JLabel("Bollo");
			lblBollo.setHorizontalAlignment(SwingConstants.LEFT);
			lblBollo.setFont(new Font("Arial", Font.BOLD, 13));
			
			JLabel lblTagliando = new JLabel("Tagliando");
			lblTagliando.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTagliando.setFont(new Font("Arial", Font.BOLD, 13));
			
			JLabel lblAssicuarazione = new JLabel("Assicurazione");
			lblAssicuarazione.setHorizontalAlignment(SwingConstants.LEFT);
			lblAssicuarazione.setFont(new Font("Arial", Font.BOLD, 13));
			
			JLabel lblOrmeggio = new JLabel("Ormeggio");
			lblOrmeggio.setHorizontalAlignment(SwingConstants.RIGHT);
			lblOrmeggio.setFont(new Font("Arial", Font.BOLD, 13));
			
			JLabel lblAlaggio = new JLabel("Alaggio");
			lblAlaggio.setHorizontalAlignment(SwingConstants.LEFT);
			lblAlaggio.setFont(new Font("Arial", Font.BOLD, 13));
			
			try{
				Extra.exequery("SELECT Data_Scadenza_Bollo, Targa, Tipologia, Marca, Nome, Alimentazione, Km_Effettuati  "
					+ "FROM veicolo WHERE (Data_Scadenza_Bollo LIKE '"+dataQuery+"-%' OR Data_Scadenza_Bollo LIKE '"+dataQuery2+"-%') "
					+ "ORDER BY Data_Scadenza_Bollo ASC","select");
			}
			catch (SQLException e) {
							JOptionPane.showMessageDialog(null, "Errore! Impossibile caricare l'elenco dei veicoli con bollo in scadenza!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
			}
			
			tblBollo = new JTable();
			tblBollo.setModel(new CostruisciTabella(Extra.rs).model);
			tblBollo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			JTableHeader header= tblBollo.getTableHeader();
			TableColumnModel colMod = header.getColumnModel();
			TableColumn tabCol = colMod.getColumn(0);
			tabCol.setHeaderValue("Data_Scadenza");
			
			TableColumnAdjuster tca = new TableColumnAdjuster(tblBollo);
			tca.adjustColumns();
			
			scrollBollo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollBollo.setViewportView(tblBollo);
			
			try {
				Extra.exequery("SELECT Data_Scadenza_Tagliando, Targa, Tipologia, Marca, Nome, Alimentazione, Km_Effettuati "
					+ "FROM veicolo WHERE (Data_Scadenza_Tagliando LIKE '"+dataQuery+"-%' OR Data_Scadenza_Tagliando LIKE '"+dataQuery2+"-%') "
					+ "ORDER BY Data_Scadenza_Tagliando ASC","select");
			}
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Errore! Impossibile caricare l'elenco dei veicoli con tagliando in scadenza!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
			
			tblTagliando = new JTable();
			tblTagliando.setModel(new CostruisciTabella(Extra.rs).model);
			tblTagliando.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			header= tblTagliando.getTableHeader();
			colMod = header.getColumnModel();
			tabCol = colMod.getColumn(0);
			tabCol.setHeaderValue("Data_Scadenza");
			
			tca = new TableColumnAdjuster(tblTagliando);
			tca.adjustColumns();
			
			scrollTagliando.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollTagliando.setViewportView(tblTagliando);
			
			try {
				Extra.exequery("SELECT Data_Scadenza_Assicurazione, Targa, Tipologia, Marca, Nome, Alimentazione, Km_Effettuati "
						+ "FROM veicolo WHERE (Data_Scadenza_Assicurazione LIKE '"+dataQuery+"-%' OR Data_Scadenza_Assicurazione LIKE '"+dataQuery2+"-%') "
						+ "ORDER BY Data_Scadenza_Assicurazione ASC","select");
			}
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Errore! Impossibile caricare l'elenco dei veicoli con assicurazione in scadenza!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
			
			tblAssicurazione = new JTable();
			tblAssicurazione.setModel(new CostruisciTabella(Extra.rs).model);
			tblAssicurazione.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			header= tblAssicurazione.getTableHeader();
			colMod = header.getColumnModel();
			tabCol = colMod.getColumn(0);
			tabCol.setHeaderValue("Data_Scadenza");
			
			tca= new TableColumnAdjuster(tblAssicurazione);
			tca.adjustColumns();
			
			scrollAssicurazione.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollAssicurazione.setViewportView(tblAssicurazione);
			
			try {
				Extra.exequery("SELECT Data_Scadenza_Ormeggio, Targa, Tipologia, Marca, Nome, Alimentazione, Km_Effettuati "
						+ "FROM veicolo WHERE (Data_Scadenza_Ormeggio LIKE '"+dataQuery+"-%' OR Data_Scadenza_Ormeggio LIKE '"+dataQuery2+"-%') "
						+ "ORDER BY Data_Scadenza_Ormeggio ASC","select");
			}
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Errore! Impossibile caricare l'elenco dei veicoli con ormeggio in scadenza!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
			
			tblOrmeggio = new JTable();
			tblOrmeggio.setModel(new CostruisciTabella(Extra.rs).model);
			tblOrmeggio.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			header= tblOrmeggio.getTableHeader();
			colMod = header.getColumnModel();
			tabCol = colMod.getColumn(0);
			tabCol.setHeaderValue("Data_Scadenza");
			
			tca= new TableColumnAdjuster(tblOrmeggio);
			tca.adjustColumns();
			
			scrollOrmeggio.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollOrmeggio.setViewportView(tblOrmeggio);
			
			try {
				Extra.exequery("SELECT Data_Scadenza_Costo_Alaggio, Targa, Tipologia, Marca, Nome, Alimentazione, Km_Effettuati "
						+ "FROM veicolo WHERE (Data_Scadenza_Costo_Alaggio LIKE '"+dataQuery+"-%' OR Data_Scadenza_Costo_Alaggio LIKE '"+dataQuery2+"-%') "
						+ "ORDER BY Data_Scadenza_Costo_Alaggio ASC","select");
			}
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Errore! Impossibile caricare l'elenco dei veicoli con alaggio in scadenza!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
			
			tblAlaggio = new JTable();
			tblAlaggio.setModel(new CostruisciTabella(Extra.rs).model);
			tblAlaggio.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			header= tblAlaggio.getTableHeader();
			colMod = header.getColumnModel();
			tabCol = colMod.getColumn(0);
			tabCol.setHeaderValue("Data_Scadenza");
			
			tca=new TableColumnAdjuster(tblAlaggio);
			tca.adjustColumns();
			
			scrollAlaggio.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollAlaggio.setViewportView(tblAlaggio);
			
			/* Crea il Layout per la lista delle scadenze. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(94))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblBollo, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(scrollAlaggio, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
												.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
													.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(scrollAssicurazione, 0, 0, Short.MAX_VALUE)
														.addComponent(lblAssicuarazione, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
														.addComponent(scrollBollo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))))
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(lblTagliando)
														.addComponent(scrollTagliando, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblOrmeggio)))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(63)
													.addComponent(scrollOrmeggio, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))))
										.addComponent(lblAlaggio))))
							.addContainerGap())
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBollo, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTagliando, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollBollo, 0, 0, Short.MAX_VALUE)
								.addComponent(scrollTagliando, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAssicuarazione, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblOrmeggio, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollAssicurazione, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollOrmeggio, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
							.addGap(28)
							.addComponent(lblAlaggio, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(scrollAlaggio, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
	}
	
	/* Definisce le azioni da eseguire in base al pulsante cliccato.*/
	
	public void actionPerformed(ActionEvent e) {
		if (btnProfitto == e.getSource()) {
			try {
				lblProfitto.setText("");
				String mese = frmtdtxtfldMese.getText();
				if (mese.matches(MONTHPATTERN)) {
					Profitto.exequery("SELECT SUM(Costo_Totale) as Profitto_Totale FROM noleggio WHERE Data_Inizio LIKE '" +mese+"-%'","select");
					if (Profitto.rs.next()) {
						if (Profitto.rs.getString(1) == null) {
							lblProfitto.setText("Profitto del " + mese + ": 0 €");
						}else {
						lblProfitto.setText("Profitto del " + mese + ":  " + Profitto.rs.getString(1) + " €");
						}
					}else {
					frmtdtxtfldMese.requestFocus();
					frmtdtxtfldMese.setText("aaaa-mm");
					JOptionPane.showMessageDialog(null, "Errore! La data inserita non è valida!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
					}
				}
			}catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Profitto non calcolato!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}else if (btnProfittoA == e.getSource()) {
			try {
				lblProfitto.setText("");
				String anno = frmtdtxtfldAnno.getText().trim();
				if (anno.matches(YEARPATTERN)) {
					Profitto.exequery("SELECT SUM(Costo_Totale) as Profitto_Totale FROM noleggio WHERE Data_Inizio LIKE '" +anno+"-%-%'","select");
					if (Profitto.rs.next()) {
						if (Profitto.rs.getString(1) == null) {
							lblProfitto.setText("Profitto del " + anno + ": 0 €");
						}else {
						lblProfitto.setText("Profitto del " + anno + ":  " + Profitto.rs.getString(1) + " €");
						}
					}else {
						frmtdtxtfldAnno.requestFocus();
						frmtdtxtfldAnno.setText("aaaa");
						JOptionPane.showMessageDialog(null, "Errore! La data inserita non valida!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					}
				}
			}catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Profitto non calcolato!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void focusGained(FocusEvent e) {
		if (frmtdtxtfldMese == e.getSource() && frmtdtxtfldMese.getText().equals("aaaa-mm")) { 
			frmtdtxtfldMese.setText("");
		}
		else if (frmtdtxtfldAnno == e.getSource() && frmtdtxtfldAnno.getText().equals("aaaa")) {
			frmtdtxtfldAnno.setText("");
		}
		
		if (!(frmtdtxtfldMese == e.getSource()) && frmtdtxtfldMese.getText().equals("")) { 
			frmtdtxtfldMese.setText("aaaa-mm");
		}
		else if (!(frmtdtxtfldAnno == e.getSource()) && frmtdtxtfldAnno.getText().equals("")) {
			frmtdtxtfldAnno.setText("aaaa");
		}
	}

	public void focusLost(FocusEvent arg0) {
		
	}
}