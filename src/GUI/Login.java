package GUI;

import db.DBConnect;
import java.sql.SQLException;

import GUI.Admin.Pannello;
import GUI.User.PannelloU;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame implements ActionListener,Runnable{

	private static final long serialVersionUID = 7526488295622776147L; 
	private DBConnect log;
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
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/External/car.png")));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Errore! Impossibile avviare il pannello di login!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
		}
	}
	 
	/* Definisce il frame Login.*/
	
	public Login() {
    	try {
    		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
    	        if ("Nimbus".equals(info.getName())) {
    	            UIManager.setLookAndFeel(info.getClassName());
    	            UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
    	            break;
    	        }
    	    }
		} catch (Exception e) {
		    e.printStackTrace();
		    try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	        catch (Exception ex) {JOptionPane.showMessageDialog(null, "Errore! Impossibile avviare l'interfaccia!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);}
		}
		setResizable(false);
		setTitle("Autonoleggio - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnAccedi.setFont(new Font("Arial", Font.PLAIN, 12));
		/* ActionListener per il bottone Accedi.*/
		btnAccedi.addActionListener(this);	
	    
		btnEsci.setFont(new Font("Arial", Font.PLAIN, 12));
		/* ActionListener per il bottone Esci.*/
		btnEsci.addActionListener(this);	
		
		/** Tra parentesi viene definita una Classe ANONIMA, cio� una classe "locale" senza 
		 * 	un nome assegnato. Si tratta di una classe definita un'unica volta attraverso una
		 *  singola espressione caratterizzata da una versione estesa della sintassi
		 *  dell'operatore new. 
		 *  In particolare viene implementata una classe ASTRATTA (KeyAdapter) e quindi devono
		 *  essere implementati tutti i suoi metodi (--> Infatti si pu� notare la presenza di 
		 *  due metodi "vuoti". 
		 **/
		txtPassword = new JPasswordField();
		/* KeyListener per il tasto Enter.*/
		txtPassword.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent evt) {
					if(evt.getKeyCode()==KeyEvent.VK_ENTER) {
						log = new DBConnect();
						check();
					}
				}
				public void keyTyped(KeyEvent evt) {
				}
				public void keyReleased(KeyEvent evt) {
				}
		});
		
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
	
	/* Metodo. Verifica che i dati inseriti per l'accesso siano validi.*/
	
	private void check(){
		int size=0;
			try {
			String user = txtUsername.getText().trim(); //Il metodo trim() prende una stringa e rimuove eventuali whitespaces in testa ed in coda
			char[] pass = txtPassword.getPassword();
			String pwd=String.copyValueOf(pass);
			/* Effettua una connessione al DB e cerca una corrispondenza con l'utente e la password inseriti.*/
			log.exequery("SELECT * FROM operatore WHERE (BINARY ID_Operatore='" + user + "' AND BINARY Password='" + pwd + "')","select"); 
			/* Se la ricerca ha esito positivo, aggiorna il valore size ad 1*/
			if (log.rs.next()) size=1;	
			/* Se non viene inserito l'username o la password, viene restituito un errore.*/
			if (user.equals("") || pwd.equals("")){	
				JOptionPane.showMessageDialog(null, "Errore! Inserisci l'username e/o la password!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
				txtUsername.requestFocus();
			} /* Se non esiste l'utente o se la password � errata, viene restituito un errore.*/
			else if (size==0){	
				log.rs.beforeFirst();
				txtUsername.setText("");
				txtPassword.setText("");
				user=null;
				pass=null;
				pwd=null;
				JOptionPane.showMessageDialog(null, "Errore! Utente non trovato o password errata!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
				txtUsername.requestFocus();
				}/* Se l'utente viene trovato ed � l'admin, viene avviato il pannello di controllo dell'admin */
				else if (user.equals("admin")){	
					this.dispose();
					new Pannello(user);
					}/* Se l'utente viene trovato ed � un operatore, viene avviato il pannello di controllo dell'operatore */
					else{	
						this.dispose();
						new PannelloU(user);
					}
			} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Errore! Impossibile connettersi al DB per effettuare l'accesso!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
			}
	}
	
	/* Definisce le azioni da eseguire in base al pulsante cliccato.*/
	
	public void actionPerformed(ActionEvent e){
		if (btnAccedi == e.getSource()){
			log = new DBConnect();
			check();
		}
		else if (btnEsci == e.getSource()){
			System.exit(0); 
		}
	}
}