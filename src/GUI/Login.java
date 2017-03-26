package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.Admin.Pannello;
import GUI.User.PannelloU;
import db.DBConnect;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Login extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JPasswordField txtPassword;
	private JTextField txtUsername;
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
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
		
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
									.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(54)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
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
		int size=0;
		if (btnAccedi == e.getSource()){
			try {
			String user = txtUsername.getText().trim();
			char[] pass = txtPassword.getPassword();
			String pwd=String.copyValueOf(pass);
			DBConnect log = new DBConnect("SELECT * FROM operatore WHERE ID_Operatore='" + user + "' AND Password='" + pwd + "'","select"); /* Si connette al DB e cerca se l'utente inserito è presente*/
			if (log.rs.next()) size=1;	/* Se l'utente è presente il valore size va ad 1*/
			if (user.equals("") || pwd.equals("")){	/* Se non si inserisce l'username o la password viene notificato con un errore. */
				JOptionPane.showMessageDialog(null, "Errore, Inserisci l'Username e/o la Password!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
				txtUsername.requestFocus();
			}else if (size==0){	/* Se non esiste l'utente con cui si prova ad accedere viene notificato con un errore */
				log.rs.beforeFirst();
				txtUsername.setText("");
				txtPassword.setText("");
				user=null;
				pass=null;
				pwd=null;
				JOptionPane.showMessageDialog(null, "Errore, Utente non Trovato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
				txtUsername.requestFocus();
				}
				else if (user.equals("admin")){	/* Se l'utente esiste ed è l'admin viene avviato il pannello di controllo dell'admin */
					this.dispose();
					Pannello op = new Pannello(user);
					}
					else{	/* Se l'utente esiste ed è un operatore viene avviato il pannello di controllo dell'operatore */
						this.dispose();
						PannelloU op = new PannelloU(user);
					}
			} catch (SQLException e1) {
			e1.printStackTrace();
			}
		}
		else if (btnEsci == e.getSource()){
			System.exit(0); 
		}
	}
}