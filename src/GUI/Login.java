package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.Admin.Pannello;

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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Login extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JPasswordField txtPassword;
	private JTextField txtUsername;
	private JFormattedTextField frmtdtxtfldPassword;
	private JButton btnAccedi = new JButton("Accedi");
	private JButton btnEsci = new JButton("Esci");
	
	/* Crea il frame Login.*/
	
	public void run() {
		try {
			Login frame = new Login();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Definisce il frame Login.*/
	
	public Login() {
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (UnsupportedLookAndFeelException e) {}
         catch (ClassNotFoundException e) {}
         catch (InstantiationException e) {}
         catch (IllegalAccessException e) {}
        
		setResizable(false);
		setTitle("Autonoleggio - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		btnAccedi.setFont(new Font("Arial", Font.PLAIN, 12));
	    btnAccedi.addActionListener(this);	/* Action Listener per il bottone Accedi.*/
	    
		btnEsci.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEsci.addActionListener(this);	/* Action Listener per il bottone Esci.*/
		
		txtPassword = new JPasswordField();
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Arial", Font.PLAIN, 12));
		txtUsername.setColumns(10);
		
		JFormattedTextField frmtdtxtUsername = new JFormattedTextField();
		frmtdtxtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		frmtdtxtUsername.setBorder(null);
		frmtdtxtUsername.setForeground(new Color(0, 0, 0));
		frmtdtxtUsername.setFont(new Font("Arial", Font.BOLD, 14));
		frmtdtxtUsername.setEditable(false);
		frmtdtxtUsername.setText("Username");
		
		frmtdtxtfldPassword = new JFormattedTextField();
		frmtdtxtfldPassword.setText("Password");
		frmtdtxtfldPassword.setHorizontalAlignment(SwingConstants.CENTER);
		frmtdtxtfldPassword.setForeground(Color.BLACK);
		frmtdtxtfldPassword.setFont(new Font("Arial", Font.BOLD, 14));
		frmtdtxtfldPassword.setEditable(false);
		frmtdtxtfldPassword.setBorder(null);
		
		/*Crea il Layout per il Login.*/
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(frmtdtxtUsername, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(frmtdtxtfldPassword, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE))))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap(40, Short.MAX_VALUE)
							.addComponent(btnAccedi, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtUsername, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
							.addGap(10))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addGap(36))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtdtxtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(54)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtdtxtfldPassword, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(75)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAccedi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/* Definisce le azioni da eseguire in base al pulsante clickato.*/
	
	public void actionPerformed(ActionEvent e){
		if (btnAccedi == e.getSource()){
			/* Inserire cosa fa il pulsante Accedi*/
			String user = txtUsername.getText();
			this.dispose();
			Pannello op = new Pannello(user);
			}
		else if (btnEsci == e.getSource()){
			System.exit(0); 
		}
	}
}