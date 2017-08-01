package gui.pannelli;

import javax.swing.JPanel;
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
import gui.finestre.FinestraAdmin;
import gui.moduli.ModuloOperatore;


public class PannelloOperatore extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 7526472295882776147L; 
	private JButton btnAggiorna = new JButton("Aggiorna Elenco");
	private JButton btnNuovo = new JButton("Nuovo Operatore");
	private JButton btnElimina = new JButton("Elimina Operatore");
	private ModuloOperatore pnlModulo = new ModuloOperatore("Elenca");
	private JButton btnEsci = new JButton("Esci");
	private JButton btnLogout = new JButton("Logout");
	private FinestraAdmin frame;
	
	/* Modifica il contentPane Operatore.*/
	
	public JPanel run(JPanel contentPane){
		
		btnAggiorna.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAggiorna.addActionListener(this); /* Action Listener per il bottone Aggiorna.*/
		
		btnNuovo.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNuovo.addActionListener(this); /* Action Listener per il bottone Nuovo.*/
		
		btnElimina.setFont(new Font("Arial", Font.PLAIN, 12));
		btnElimina.addActionListener(this); /* Action Listener per il bottone Elimina.*/
		
		btnEsci.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEsci.addActionListener(this); /* Action Listener per il bottone Esci.*/
		
		btnLogout.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLogout.addActionListener(this); /* Action Listener per il bottone Logout.*/
		
		JLabel lbllog = new JLabel("Loggato come");
		lbllog.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel user = new JLabel(frame.Username);
		user.setFont(new Font("Arial", Font.BOLD, 12));
		user.setForeground(Color.RED);
		
		/* Crea il Layout.*/
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnNuovo)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnElimina, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnAggiorna, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
								.addComponent(lbllog, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(user, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addComponent(pnlModulo, GroupLayout.PREFERRED_SIZE, 506, GroupLayout.PREFERRED_SIZE)
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnNuovo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnElimina, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnAggiorna, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(pnlModulo, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(user, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbllog, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
						.addGap(15)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
		contentPane.setLayout(gl_contentPane);
		return contentPane;
	}
	
	/* Costruttore contentPane Operatore .*/
	
	public PannelloOperatore(FinestraAdmin pn) {
		frame = pn;
		pn.setTitle("Autonoleggio - Operatore");
		pn.setContentPane(this.run(pn.contentPane));
	}
	
	/* Definisce le azioni da eseguire in base al pulsante clickato.*/
	
	public void actionPerformed(ActionEvent e){
		if (btnEsci == e.getSource()){ 
			int scelta = JOptionPane.showConfirmDialog(
				    null,
				    "Si desidera uscire dall'applicazione?",
				    "Conferma uscita",
				    JOptionPane.YES_NO_OPTION);
			if (scelta == JOptionPane.YES_OPTION){
				System.exit(0);
			}
		}
		else if(btnLogout == e.getSource()){
			int scelta = JOptionPane.showConfirmDialog(
					null,
					"Si desidera effettuare il logout?",
					"Conferma logout",
					JOptionPane.YES_NO_OPTION);
			if (scelta == JOptionPane.YES_OPTION){
				frame.dispose();
				Login log = new Login();
				log.run();
			}
		}
		else if(btnAggiorna == e.getSource()){
			btnAggiorna.setText("Aggiorna Elenco");
			pnlModulo.set("Elenca");
		}
		else if(btnNuovo == e.getSource()){
			btnAggiorna.setText("Elenco Operatori");
			pnlModulo.set("Nuovo");
		}
		else if(btnElimina == e.getSource()){
			btnAggiorna.setText("Elenco Operatori");
			pnlModulo.set("Elimina");
		}
	}
}