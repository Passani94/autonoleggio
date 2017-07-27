package GUI.Admin;

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

import GUI.*;
import GUI.User.PannelloU;


public class PannelloContratto extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 7526472295622701147L; 
	private JButton btnAggiorna = new JButton("Aggiorna Elenco");
	private JButton btnNuovo = new JButton("Nuovo Contratto");
	private JButton btnPreventivo = new JButton("Calcola Preventivo");
	private ModuloCt pnlModulo = new ModuloCt("Elenca");
	private JButton btnEsci = new JButton("Esci");
	private JButton btnLogout = new JButton("Logout");
	private Pannello frame;
	private PannelloU frameU;
	private String tipo;
	private JLabel user;
	private JScrollPane scrollPane = new JScrollPane(pnlModulo);
	
	/* Modifica il contentPane Contratto.*/
	
	public JPanel run(JPanel contentPane){
		
		scrollPane.setViewportView(pnlModulo);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        
		btnAggiorna.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAggiorna.addActionListener(this); /* Action Listener per il bottone Aggiorna.*/
		
		btnNuovo.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNuovo.addActionListener(this); /* Action Listener per il bottone Nuovo.*/
		
		btnPreventivo.setFont(new Font("Arial", Font.PLAIN, 12));
		btnPreventivo.addActionListener(this);/* Action Listener per il bottone Modifica.*/
		
		btnEsci.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEsci.addActionListener(this); /* Action Listener per il bottone Esci.*/
		
		btnLogout.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLogout.addActionListener(this); /* Action Listener per il bottone Logout.*/
		
		JLabel lbllog = new JLabel("Loggato come");
		lbllog.setFont(new Font("Arial", Font.PLAIN, 12));
		
		if (tipo=="Pn"){
			user = new JLabel(frame.Username);}
			else{
			user = new JLabel(frameU.Username);
		}
		user.setFont(new Font("Arial", Font.BOLD, 12));
		user.setForeground(Color.RED);
		
		/* Crea il Layout.*/
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(btnNuovo)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnPreventivo, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnAggiorna, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
								.addComponent(lbllog, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(user, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 506, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNuovo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPreventivo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAggiorna, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(user, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbllog, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
						.addGap(15)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
		contentPane.setLayout(gl_contentPane);
		return contentPane;
	}
	
	/* Costruttore contentPane Contratto .*/
	
	public PannelloContratto(Pannello pn) {
		frame = pn;
		tipo="Pn";
		pn.setTitle("Autonoleggio - Contratto");
		pn.setContentPane(this.run(pn.contentPane));
	}
	
	/* Costruttore contentPane Contratto per l'Utente.*/
	
	public PannelloContratto(PannelloU pn) {
		frameU = pn;
		tipo="PnU";
		pn.setTitle("Autonoleggio - Contratto");
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
				if(tipo == "Pn"){
					frame.dispose();
				}
				else {
					frameU.dispose();
				}
				Login log = new Login();
				log.run();
			}
		}
		else if(btnAggiorna == e.getSource()){
			btnAggiorna.setText("Aggiorna Elenco");
			pnlModulo.set("Elenca");
		}
		else if(btnNuovo == e.getSource()){
			btnAggiorna.setText("Elenco Contratti");
			pnlModulo.set("Nuovo");
		}
		else if(btnPreventivo == e.getSource()){
			btnAggiorna.setText("Elenco Contratti");
			pnlModulo.set("Preventivo");
		}
	}
}