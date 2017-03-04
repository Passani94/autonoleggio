package GUI;

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
import java.awt.EventQueue;

import javax.swing.SwingConstants;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtPassword;
	private JTextField txtUsername;
	private JFormattedTextField frmtdtxtfldPassword;

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
		setTitle("Autonoleggio - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEsci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
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
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAccedi, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtdtxtUsername, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtdtxtfldPassword, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnEsci, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(txtUsername, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
							.addComponent(txtPassword, 152, 152, 152)))
					.addGap(10))
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
						.addComponent(btnAccedi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(63, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
