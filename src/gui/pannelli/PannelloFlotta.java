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
import gui.moduli.ModuloFlotta;
import gui.moduli.moduliOpzionali.ModuloElencoVeicoli;

/**
 * La classe PannelloFlotta implementa un pannello contenuto all'interno del frame Finestra.
 */
public class PannelloFlotta extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L; 
	
	private JButton btnAggiorna = new JButton("Elenco Veicoli");
	private JButton btnNuovo = new JButton("Nuovo Veicolo");
	private JButton btnElimina = new JButton("Elimina Veicolo");
	private JButton btnModifica = new JButton("Modifica Veicolo");
	private JButton btnEsci = new JButton("Esci");
	private JButton btnLogout = new JButton("Logout");
	
	private ModuloFlotta pnlModulo = new ModuloFlotta("Principale");
	private ModuloFlotta pnlModulo2 = new ModuloFlotta("Opzionale");
	private ModuloElencoVeicoli pnlModulo3 = new ModuloElencoVeicoli("Completo");
	
	private JScrollPane scrollPane = new JScrollPane(pnlModulo);
	private JScrollPane scrollPane2 = new JScrollPane(pnlModulo2);
	
	private Finestra frame;
	private JLabel user;
	
	/**
	 * Inizializza un nuovo oggetto PannelloFlotta.
	 * 
	 * @param window un frame Finestra.
	 */
	public PannelloFlotta(Finestra window) {
		frame = window;
		window.setTitle("Autonoleggio - Flotta");
		window.setContentPane(this.run(window.contentPane));
	}
	
	/**
	 * Modifica il pannello PannelloFlotta.
	 * 
	 * @param contentPane un pannello "vuoto".
	 * @return il pannello modificato.
	 */
	public JPanel run(JPanel contentPane) {
		
		scrollPane.setViewportView(pnlModulo);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		scrollPane2.setViewportView(pnlModulo2);
		scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        
		btnAggiorna.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAggiorna.addActionListener(this); // Action Listener per il bottone Aggiorna.
		
		btnNuovo.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNuovo.addActionListener(this); // Action Listener per il bottone Nuovo.
		
		btnElimina.setFont(new Font("Arial", Font.PLAIN, 12));
		btnElimina.addActionListener(this); // Action Listener per il bottone Elimina.
		
		btnModifica.setFont(new Font("Arial", Font.PLAIN, 12));
		btnModifica.addActionListener(this); // Action Listener per il bottone Modifica.
		
		btnEsci.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEsci.addActionListener(this); // Action Listener per il bottone Esci.
		
		btnLogout.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLogout.addActionListener(this); // Action Listener per il bottone Logout.
		
		JLabel lbllog = new JLabel("Loggato come");
		lbllog.setFont(new Font("Arial", Font.PLAIN, 12));
		
		user = new JLabel(frame.username);
		user.setFont(new Font("Arial", Font.BOLD, 12));
		user.setForeground(Color.RED);
		
		/* Crea il layout del PannelloFlotta.*/
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnNuovo)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnModifica, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnElimina, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnAggiorna, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 492, Short.MAX_VALUE)
								.addComponent(lbllog, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(user, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNuovo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnElimina, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnModifica, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAggiorna, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(user, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbllog, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
							.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE))
						.addGap(15)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
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
			if (scelta == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		
		} else if (btnLogout == e.getSource()) {
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
		
		} else if (btnNuovo == e.getSource()) {
			btnAggiorna.setText("Elenco Veicoli");
			pnlModulo.set("Nuovo");
			scrollPane2.setViewportView(pnlModulo2);
		
		} else if (btnModifica == e.getSource()) {
			btnAggiorna.setText("Elenco Veicoli");
			pnlModulo.set("Modifica");
			scrollPane2.setViewportView(pnlModulo3);
			
		} else if(btnElimina == e.getSource()) {
			btnAggiorna.setText("Elenco Veicoli");
			pnlModulo.set("Elimina");
			scrollPane2.setViewportView(pnlModulo3);
		
		} else if(btnAggiorna == e.getSource()) {
			btnAggiorna.setText("Aggiorna Elenco");
			pnlModulo.set("Elenca");
			scrollPane2.setViewportView(pnlModulo2);
		
		}
	}

	/* OVERRIDING METODI toString() ED equals() */
	
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * 
	 * @return una stringa rappresentante l'oggetto.
	 */
	public String toString() {
		return "PannelloFlotta [La classe PannelloFlotta implementa un pannello contenuto all'interno del frame Finestra.]";
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
		PannelloFlotta other = (PannelloFlotta) obj;
		if (btnAggiorna == null) {
			if (other.btnAggiorna != null)
				return false;
		} else if (!btnAggiorna.equals(other.btnAggiorna))
			return false;
		if (btnElimina == null) {
			if (other.btnElimina != null)
				return false;
		} else if (!btnElimina.equals(other.btnElimina))
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
		if (btnModifica == null) {
			if (other.btnModifica != null)
				return false;
		} else if (!btnModifica.equals(other.btnModifica))
			return false;
		if (btnNuovo == null) {
			if (other.btnNuovo != null)
				return false;
		} else if (!btnNuovo.equals(other.btnNuovo))
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