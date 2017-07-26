package GUI.Admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
//import javax.swing.table.DefaultTableModel;

import Utils.CostruisciTabella;
import Utils.TableColumnAdjuster;
import db.DBConnect;


public class ModuloEx extends JPanel implements ActionListener{

	private static final long serialVersionUID = 7526612295622776147L; 
	private JLabel lblProfitto;
	private JTable tblVeicoli;
	private JButton btnProfittoa;
	private JButton btnProfitto;
	private JScrollPane scroll = new JScrollPane(tblVeicoli);
	private JFormattedTextField frmtdtxtfldMese;
	private JFormattedTextField frmtdtxtfldanno;
	private JTable tblBollo;
	private JTable tblTagliando;
	private JTable tblAssicurazione;
	private JTable tblOrmeggio;
	private JTable tblAlaggio;
	private JScrollPane scrollbollo = new JScrollPane(tblBollo);
	private JScrollPane scrolltagliando = new JScrollPane(tblTagliando);
	private JScrollPane scrollAssicurazione = new JScrollPane(tblAssicurazione);
	private JScrollPane scrollOrmeggio = new JScrollPane(tblOrmeggio);
	private JScrollPane scrollAlaggio = new JScrollPane(tblAlaggio);
	private DBConnect Extra = new DBConnect();
	private DBConnect Profitto = new DBConnect();
	private double mese,anno;
	
	/* Costruttori ModuloEx */
	
	public ModuloEx(String str){
		set(str);
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		mese = cal.get(Calendar.MONTH);
		anno = cal.get(Calendar.YEAR);
	}

	public ModuloEx(){
		this.setBorder(BorderFactory.createTitledBorder("Funzionalità Aggiuntive"));
		
		JLabel lblFunz = new JLabel("Pannello Funzionalit\u00E0 Aggiuntive");
		lblFunz.setHorizontalAlignment(SwingConstants.CENTER);
		lblFunz.setFont(new Font("Arial", Font.BOLD, 14));
		
		/* Crea il Layout iniziale per il pannello funzionalità aggiuntive. */
		
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap(124, Short.MAX_VALUE)
						.addComponent(lblFunz, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
						.addGap(121))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(49)
						.addComponent(lblFunz, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(255, Short.MAX_VALUE))
			);
		this.setLayout(gl_contentPane);
	}
	
	public void set(String str){
		if (str == "Statistica"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elenco Veicoli più Noleggiati"));
			
			try{Extra.exequery("SELECT COUNT(*) as Numero_Noleggi, b.Targa, b.Tipologia, b.Nome, b.Disponibilita FROM noleggio a INNER JOIN veicolo b ON a.veicolo = b.Targa GROUP BY b.Targa ORDER BY Numero_Noleggi DESC","select");}
			catch(SQLException e){
				JOptionPane.showMessageDialog(null, "Errore, impossibile caricare l'elenco veicoli più noleggiati!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);}
			
			tblVeicoli = new JTable();
			tblVeicoli.setModel(new CostruisciTabella(Extra.rs).model);
			tblVeicoli.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
		else if (str == "Mensile"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Profitto Mensile"));
			
			JLabel lblMensile = new JLabel("Profitto Mensile Derivato dai Noleggi");
			lblMensile.setHorizontalAlignment(SwingConstants.CENTER);
			lblMensile.setFont(new Font("Arial", Font.BOLD, 13));
			
			JLabel lblMese = new JLabel("Seleziona Anno/Mese");
			lblMese.setHorizontalAlignment(SwingConstants.CENTER);
			lblMese.setFont(new Font("Arial", Font.BOLD, 12));
			
			btnProfitto = new JButton("Calcola Profitto");
			btnProfitto.addActionListener(this);	/* Action Listener per il bottone Profitto.*/
			
			lblProfitto = new JLabel("");
			lblProfitto.setFont(new Font("Arial", Font.BOLD, 12));
			lblProfitto.setHorizontalAlignment(SwingConstants.CENTER);
			
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM");
			
			frmtdtxtfldMese = new JFormattedTextField(dateformat);
			frmtdtxtfldMese.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldMese.setColumns(7);
			frmtdtxtfldMese.setText("aaaa-mm");
			
			/* Crea il Layout per Profitto Mensile. */
			
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
		else if (str == "Annuale"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Profitto Annuale"));
			
			JLabel lblAnnuale = new JLabel("Profitto Annuale Derivato dai Noleggi");
			lblAnnuale.setHorizontalAlignment(SwingConstants.CENTER);
			lblAnnuale.setFont(new Font("Arial", Font.BOLD, 13));
			
			JLabel lblAnno = new JLabel("Seleziona Anno");
			lblAnno.setHorizontalAlignment(SwingConstants.CENTER);
			lblAnno.setFont(new Font("Arial", Font.BOLD, 12));
			
			btnProfittoa = new JButton("Calcola Profitto");
			btnProfittoa.addActionListener(this);	/* Action Listener per il bottone Profitto Annuale.*/
			
			lblProfitto = new JLabel("");
			lblProfitto.setFont(new Font("Arial", Font.BOLD, 12));
			lblProfitto.setHorizontalAlignment(SwingConstants.CENTER);
			
			DateFormat dateformat = new SimpleDateFormat("yyyy");
			
			frmtdtxtfldanno = new JFormattedTextField(dateformat);
			frmtdtxtfldanno.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldanno.setColumns(4);
			frmtdtxtfldanno.setText("aaaa");
			
			/* Crea il Layout per Profitto Annuale. */
			
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
							.addComponent(frmtdtxtfldanno, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
							.addComponent(btnProfittoa)
							.addGap(46))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(26)
							.addComponent(lblAnnuale, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldanno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAnno, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnProfittoa))
							.addGap(30)
							.addComponent(lblProfitto, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(162, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str == "Scadenze"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Prossime Scadenze"));
			
			JLabel lblScadenze = new JLabel("Lista delle Scadenze nel Prossimo Mese");
			lblScadenze.setHorizontalAlignment(SwingConstants.CENTER);
			lblScadenze.setFont(new Font("Arial", Font.BOLD, 13));
			
			JLabel lblBollo = new JLabel("Veicoli con Bollo in Scadenza");
			lblBollo.setHorizontalAlignment(SwingConstants.LEFT);
			lblBollo.setFont(new Font("Arial", Font.BOLD, 12));
			
			JLabel lblTagliando = new JLabel("Veicoli con Tagliando in Scadenza");
			lblTagliando.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTagliando.setFont(new Font("Arial", Font.BOLD, 12));
			
			try{Extra.exequery("SELECT * FROM veicolo WHERE Data_Scadenza_Bollo LIKE '"+anno+"-"+mese+"-%'","select");}
			catch(SQLException e){
							JOptionPane.showMessageDialog(null, "Errore, impossibile caricare l'elenco veicoli con bollo in scadenza!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);}
			
			tblBollo = new JTable();
			tblBollo.setModel(new CostruisciTabella(Extra.rs).model);
			tblBollo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumnAdjuster tca = new TableColumnAdjuster(tblBollo);
			tca.adjustColumns();
			
			scrollbollo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollbollo.setViewportView(tblBollo);
			
			try{Extra.exequery("SELECT * FROM veicolo WHERE Data_Scadenza_Tagliando LIKE '"+anno+"-"+mese+"-%'","select");}
			catch(SQLException e){
				JOptionPane.showMessageDialog(null, "Errore, impossibile caricare l'elenco veicoli con tagliando in scadenza!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);}
			
			tblTagliando = new JTable();
			tblTagliando.setModel(new CostruisciTabella(Extra.rs).model);
			tblTagliando.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			new TableColumnAdjuster(tblTagliando);
			tca.adjustColumns();
			
			scrolltagliando.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrolltagliando.setViewportView(tblTagliando);
			
			
			JLabel lblAssicuarazione = new JLabel("Veicoli con Assicurazione in Scadenza");
			lblAssicuarazione.setHorizontalAlignment(SwingConstants.LEFT);
			lblAssicuarazione.setFont(new Font("Arial", Font.BOLD, 12));
			
			JLabel lblOrmeggio = new JLabel("Veicoli con Ormeggio in Scadenza");
			lblOrmeggio.setHorizontalAlignment(SwingConstants.RIGHT);
			lblOrmeggio.setFont(new Font("Arial", Font.BOLD, 12));
			
			try{Extra.exequery("SELECT * FROM veicolo WHERE Data_Scadenza_Assicurazione LIKE '"+anno+"-"+mese+"-%'","select");}
			catch(SQLException e){
				JOptionPane.showMessageDialog(null, "Errore, impossibile caricare l'elenco veicoli con assicurazione in scadenza!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);}
			
			tblAssicurazione = new JTable();
			tblAssicurazione.setModel(new CostruisciTabella(Extra.rs).model);
			tblAssicurazione.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			new TableColumnAdjuster(tblAssicurazione);
			tca.adjustColumns();
			
			scrollAssicurazione.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollAssicurazione.setViewportView(tblAssicurazione);
			
			
			try{Extra.exequery("SELECT * FROM veicolo WHERE Data_Scadenza_Ormeggio LIKE '"+anno+"-"+mese+"-%'","select");}
			catch(SQLException e){
				JOptionPane.showMessageDialog(null, "Errore, impossibile caricare l'elenco veicoli con ormeggio in scadenza!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);}
			
			tblOrmeggio = new JTable();
			tblOrmeggio.setModel(new CostruisciTabella(Extra.rs).model);
			tblOrmeggio.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			new TableColumnAdjuster(tblOrmeggio);
			tca.adjustColumns();
			
			scrollOrmeggio.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollOrmeggio.setViewportView(tblOrmeggio);
			
			JLabel lblAlaggio = new JLabel("Veicoli con Alaggio in Scadenza");
			lblAlaggio.setHorizontalAlignment(SwingConstants.LEFT);
			lblAlaggio.setFont(new Font("Arial", Font.BOLD, 12));
			
			try{Extra.exequery("SELECT * FROM veicolo WHERE Data_Scadenza_Costo_Alaggio LIKE '"+anno+"-"+mese+"-%'","select");}
			catch(SQLException e){
				JOptionPane.showMessageDialog(null, "Errore, impossibile caricare l'elenco veicoli con alaggio in scadenza!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);}
			
			tblAlaggio = new JTable();
			tblAlaggio.setModel(new CostruisciTabella(Extra.rs).model);
			tblAlaggio.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			new TableColumnAdjuster(tblAlaggio);
			tca.adjustColumns();
			
			scrollAlaggio.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollAlaggio.setViewportView(tblAlaggio);
			
			/* Crea il Layout per la lista delle Scadenze. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(94)
									.addComponent(lblScadenze, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblBollo, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
									.addComponent(lblTagliando, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollbollo, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAssicuarazione, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(scrolltagliando, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblOrmeggio, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(scrollAssicurazione, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
									.addComponent(scrollOrmeggio, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblAlaggio, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(scrollAlaggio, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
							.addContainerGap())
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblScadenze, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBollo)
								.addComponent(lblTagliando, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollbollo, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrolltagliando, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblOrmeggio, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAssicuarazione, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollAssicurazione, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollOrmeggio, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(lblAlaggio, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollAlaggio, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
	}
	
	/* Definisce le azioni da eseguire in base al pulsante clickato.*/
	
	public void actionPerformed(ActionEvent e){
		if (btnProfitto == e.getSource()){
			try{
				String mese = frmtdtxtfldMese.getText();
				if (mese.matches("^\\d{4}-\\d{2}$")){
					Profitto.exequery("SELECT SUM(Costo_Totale) as Profitto_Totale FROM noleggio WHERE Data_Inizio LIKE '" +mese+"-%'","select");
					if (Profitto.rs.next()){
						lblProfitto.setText("Profitto nel mese " + mese + ":  " + Profitto.rs.getString(1) + " €");
						frmtdtxtfldMese.setText("aaaa-mm");
					} else {
						lblProfitto.setText("Profitto nel mese " + mese + " 0");
						frmtdtxtfldMese.setText("aaaa-mm");
					}
				}else{
					frmtdtxtfldMese.requestFocus();
					frmtdtxtfldMese.setText("aaaa-mm");
					JOptionPane.showMessageDialog(null, "Errore, mese inserito non valido!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Profitto non Calcolato!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(btnProfittoa == e.getSource()){
			try{
				String anno = frmtdtxtfldanno.getText();
				if (anno.matches("^\\d{4}$")){
					Profitto.exequery("SELECT SUM(Costo_Totale) as Profitto_Totale FROM noleggio WHERE Data_Inizio LIKE '" +anno+"-%-%'","select");
					if (Profitto.rs.next()){
						lblProfitto.setText("Profitto nell'anno " + anno + ":  " + Profitto.rs.getString(1) + " €");
						frmtdtxtfldanno.setText("aaaa");
					}else {
						lblProfitto.setText("Profitto nell'anno " + anno + " 0");
						frmtdtxtfldanno.setText("aaaa");
					}
					}else{
						frmtdtxtfldanno.requestFocus();
						frmtdtxtfldanno.setText("aaaa");
						JOptionPane.showMessageDialog(null, "Errore, anno inserito non valido!",
								"Errore ",
								JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Profitto non Calcolato!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}