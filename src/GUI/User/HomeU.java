package GUI.User;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;

import GUI.*;
import javax.swing.JLabel;

public class HomeU extends JPanel implements ActionListener{
	
	private JButton btnEsci = new JButton("Esci");
	private JButton btnLogout = new JButton("Logout");
	private JPanel pnlCalendar = new JPanel(null);
	private ModuloHmU pnlModulo = new ModuloHmU();
	private PannelloU frame;
	
	/* Modifica il contentPane Home.*/
	
	public JPanel run(JPanel contentPane) {
		
		btnEsci.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEsci.addActionListener(this); /* Action Listener per il bottone Esci.*/
		
		btnLogout.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLogout.addActionListener(this); /* Action Listener per il bottone Logout.*/
		
		JLabel lbllog = new JLabel("Loggato come");
		lbllog.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel user = new JLabel(frame.Username);
		user.setFont(new Font("Arial", Font.PLAIN, 12));
		user.setForeground(Color.RED);
		
		Calendario cal = new Calendario(contentPane,pnlCalendar);
		
		/* Crea il Layout.*/
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(pnlModulo, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
									.addGap(86)
									.addComponent(pnlCalendar, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(lbllog, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
									.addComponent(user, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addContainerGap()))
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
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE, false)
							.addComponent(lbllog, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(user, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGap(80)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(pnlModulo, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
							.addComponent(pnlCalendar, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE, false)
							.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
		cal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.setLayout(gl_contentPane);
		return contentPane;
	}
	
	/* Costruttore contentPane HomeU .*/
	
	public HomeU(PannelloU pn) {
		frame = pn;
		pn.setTitle("Autonoleggio - Home");
		pn.setContentPane(this.run(pn.contentPane));
	}
	
	/* Definisce le azioni da eseguire in base al pulsante clickato.*/
	
	public void actionPerformed(ActionEvent e){
		if (btnEsci == e.getSource()){
			System.exit(0);}
		else if(btnLogout == e.getSource()){
			frame.dispose();
			Login log = new Login();
			log.run();
			}
	}
}