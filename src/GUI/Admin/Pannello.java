package GUI.Admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

import GUI.Login;

public class Pannello extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnEsci = new JButton("Esci");
	private JButton btnLogout = new JButton("Logout");
	/* Crea il frame Login.*/
	
	public void run() {
		try {
			Pannello frame = new Pannello();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Definisce il frame Login.*/
	
	public Pannello() {
		setTitle("Autonoleggio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);/* Action Listener per il bottone Accedi.*/
	    
		btnEsci.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEsci.addActionListener(this);
		
		JFormattedTextField frmtdtxtlog = new JFormattedTextField();
		frmtdtxtlog.setText("Loggato come");
		frmtdtxtlog.setHorizontalAlignment(SwingConstants.CENTER);
		frmtdtxtlog.setBorder(null);
		frmtdtxtlog.setForeground(new Color(0, 0, 0));
		frmtdtxtlog.setFont(new Font("Arial", Font.PLAIN, 12));
		frmtdtxtlog.setEditable(false);
		
		
		btnLogout.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLogout.addActionListener(this);
		
		JFormattedTextField frmtdtxtuser = new JFormattedTextField();
		frmtdtxtuser.setHorizontalAlignment(SwingConstants.CENTER);
		frmtdtxtuser.setForeground(Color.BLACK);
		frmtdtxtuser.setFont(new Font("Arial", Font.PLAIN, 12));
		frmtdtxtuser.setEditable(false);
		frmtdtxtuser.setBorder(null);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(472, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(frmtdtxtlog, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(frmtdtxtuser, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(8))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(frmtdtxtlog, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtdtxtuser, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 483, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/* Definisce le azioni da eseguire in base al pulsante clickato.*/
	
	public void actionPerformed(ActionEvent e){
		if (btnEsci == e.getSource()){
			System.exit(0);}
		else if(btnLogout == e.getSource()){
			this.dispose();
			Login log = new Login();
			log.run();
		}
	}
}