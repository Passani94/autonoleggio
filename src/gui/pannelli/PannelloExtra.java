package gui.pannelli;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

import autonoleggio.Login;
import gui.finestre.Finestra;
import gui.moduli.ModuloExtra;

/**
 * La classe PannelloExtra implementa un pannello contenuto all'interno del frame Finestra.
 */
public class PannelloExtra extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L; 
	
	private JButton btnScadenze = new JButton("Scadenze Varie");
	private JButton btnStatistica = new JButton("Veicoli Pi\u00F9 Noleggiati");
	private JButton btnMensile = new JButton("Calcola Profitto Mensile");
	private JButton btnAnnuale = new JButton("Calcola Profitto Annuale");
	private JButton btnEsci = new JButton("Esci");
	private JButton btnLogout = new JButton("Logout");
	
	private ModuloExtra pnlModulo = new ModuloExtra();
	private JScrollPane scrollPane = new JScrollPane(pnlModulo);
	
	private Finestra frame;
	private JLabel user;
	
	/**
	 * Inizializza un nuovo oggetto PannelloExtra.
	 * 
	 * @param window un frame Finestra.
	 */
	public PannelloExtra(Finestra window) {
		frame = window;
		window.setTitle("Autonoleggio - Altre Funzionalità");
		window.setContentPane(this.run(window.contentPane));
	}
	
	/**
	 * Modifica il pannello PannelloExtra.
	 * 
	 * @param contentPane un pannello "vuoto".
	 * @return il pannello modificato.
	 */
	public JPanel run(JPanel contentPane) {
	       
		scrollPane.setViewportView(pnlModulo);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		btnScadenze.setFont(new Font("Arial", Font.PLAIN, 12));
		btnScadenze.addActionListener(this); // Action Listener per il bottone Aggiorna.
		
		btnStatistica.setFont(new Font("Arial", Font.PLAIN, 12));
		btnStatistica.addActionListener(this); // Action Listener per il bottone Nuovo.
		
		btnMensile.setFont(new Font("Arial", Font.PLAIN, 12));
		btnMensile.addActionListener(this); // Action Listener per il bottone Elimina.
		
		btnAnnuale.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAnnuale.addActionListener(this); // Action Listener per il bottone Modifica.
		
		btnEsci.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEsci.addActionListener(this); // Action Listener per il bottone Esci.
		
		btnLogout.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLogout.addActionListener(this); // Action Listener per il bottone Logout.
		
		JLabel lbllog = new JLabel("Loggato come");
		lbllog.setFont(new Font("Arial", Font.PLAIN, 12));
		
		user = new JLabel(frame.username);
		user.setFont(new Font("Arial", Font.BOLD, 12));
		user.setForeground(Color.RED);
		
		/* Crea il layout del PannelloExtra.*/
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(lbllog, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(user, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btnMensile, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
									.addComponent(btnAnnuale, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
									.addComponent(btnScadenze, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
									.addComponent(btnStatistica, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(user, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
									.addComponent(lbllog, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
								.addGap(15)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
								.addContainerGap())
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addGap(147)
								.addComponent(btnStatistica, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addGap(27)
								.addComponent(btnMensile, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addGap(26)
								.addComponent(btnAnnuale, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addGap(27)
								.addComponent(btnScadenze, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(175, Short.MAX_VALUE))))
			);
		contentPane.setLayout(gl_contentPane);
		return contentPane;
	}
	
	
	
	/**
	 * Definisce le azioni da eseguire a seconda del bottone cliccato.
	 */
	public void actionPerformed(ActionEvent e) {
		if (btnEsci == e.getSource()) { 
			int scelta = JOptionPane.showConfirmDialog(
				    null,
				    "Si desidera uscire dall'applicazione?",
				    "Conferma uscita",
				    JOptionPane.YES_NO_OPTION);
			if (scelta == JOptionPane.YES_OPTION){
				System.exit(0);
			}
		
		} else if(btnLogout == e.getSource()) {
			int scelta = JOptionPane.showConfirmDialog(
					null,
					"Si desidera effettuare il logout?",
					"Conferma logout",
					JOptionPane.YES_NO_OPTION);
			if (scelta == JOptionPane.YES_OPTION) {
				frame.dispose();
				Login log = new Login();
				log.run();
			}
		
		} else if(btnStatistica == e.getSource()) {
			pnlModulo.set("Statistica");
		
		} else if(btnMensile == e.getSource()) {
			pnlModulo.set("Mensile");
		
		} else if(btnAnnuale == e.getSource()) {
			pnlModulo.set("Annuale");
		
		} else if(btnScadenze == e.getSource()) {
			pnlModulo.set("Scadenze");
		}
	}

/* OVERRIDING METODI toString() ED equals() */
	
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * 
	 * @return una stringa rappresentante l'oggetto.
	 */
	public String toString() {
		return "PannelloExtra [La classe PannelloExtra implementa un pannello contenuto all'interno del frame Finestra.]";
	}

	/**
	 * Confronta questo oggetto con quello passato come argomento.
	 * 
	 * @param obj l'oggetto da confrontare.
	 * @return true se i due oggetti sono uguali; false altrimenti.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PannelloExtra other = (PannelloExtra) obj;
		if (btnAnnuale == null) {
			if (other.btnAnnuale != null)
				return false;
		} else if (!btnAnnuale.equals(other.btnAnnuale))
			return false;
		if (btnEsci == null) {
			if (other.btnEsci != null)
				return false;
		} else if (!btnEsci.equals(other.btnEsci))
			return false;
		if (btnLogout == null) {
			if (other.btnLogout != null)
				return false;
		} else if (!btnLogout.equals(other.btnLogout))
			return false;
		if (btnMensile == null) {
			if (other.btnMensile != null)
				return false;
		} else if (!btnMensile.equals(other.btnMensile))
			return false;
		if (btnScadenze == null) {
			if (other.btnScadenze != null)
				return false;
		} else if (!btnScadenze.equals(other.btnScadenze))
			return false;
		if (btnStatistica == null) {
			if (other.btnStatistica != null)
				return false;
		} else if (!btnStatistica.equals(other.btnStatistica))
			return false;
		if (frame == null) {
			if (other.frame != null)
				return false;
		} else if (!frame.equals(other.frame))
			return false;
		if (pnlModulo == null) {
			if (other.pnlModulo != null)
				return false;
		} else if (!pnlModulo.equals(other.pnlModulo))
			return false;
		if (scrollPane == null) {
			if (other.scrollPane != null)
				return false;
		} else if (!scrollPane.equals(other.scrollPane))
			return false;
		return true;
	}
	
	
}