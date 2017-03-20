package GUI.Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;


public class ModuloOp extends JPanel implements ActionListener{

	private JTable tblOperatori;
	private JTextField txtPassword;
	private JTextField txtUsername;
	private JFormattedTextField frmtdtxtfldPassword;
	private JButton btnAggiungi;
	private JButton btnElimina;
	private JScrollPane scroll = new JScrollPane(tblOperatori);
	
	/* Costruttore ModuloOp */
	
	public ModuloOp(String str){
		set(str);
	}

	public void set(String str){
		if (str == "Elenca"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elenco Operatori"));

			tblOperatori = new JTable();
			tblOperatori.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Operatore", "Password"
				}
			));
			
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setViewportView(tblOperatori);
			
			/* Crea il Layout per l'elenco degli Operatori. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(64)
							.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(360, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(35)
							.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
							.addGap(50))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str == "Nuovo"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Nuovo Operatore"));
			
			btnAggiungi = new JButton("Aggiungi Operatore");
			btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAggiungi.addActionListener(this);	/* Action Listener per il bottone Accedi.*/
			
			txtPassword = new JPasswordField();
			
			txtUsername = new JTextField();
			txtUsername.setFont(new Font("Arial", Font.PLAIN, 12));
			
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
			GroupLayout gl_contentPane = new GroupLayout(this);
			
			/* Crea il Layout per un nuovo Operatore. */
			
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(frmtdtxtUsername, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldPassword, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtUsername, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
							.addGap(10))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(194, Short.MAX_VALUE)
							.addComponent(btnAggiungi)
							.addGap(177))
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
							.addGap(78)
							.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(133, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str == "Elimina"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elimina Operatore"));
			
			btnElimina = new JButton("Elimina Operatore");
			btnElimina.setFont(new Font("Arial", Font.PLAIN, 12));
			btnElimina.addActionListener(this);	/* Action Listener per il bottone Elimina.*/
			
			txtUsername = new JTextField();
			txtUsername.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JFormattedTextField frmtdtxtUsername = new JFormattedTextField();
			frmtdtxtUsername.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtUsername.setBorder(null);
			frmtdtxtUsername.setForeground(new Color(0, 0, 0));
			frmtdtxtUsername.setFont(new Font("Arial", Font.BOLD, 14));
			frmtdtxtUsername.setEditable(false);
			frmtdtxtUsername.setText("Username");
			
			/* Crea il Layout per eleminare un Operatore. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addComponent(frmtdtxtUsername, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
							.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
							.addGap(10))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(188, Short.MAX_VALUE)
							.addComponent(btnElimina)
							.addGap(169))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(118)
							.addComponent(btnElimina, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(167, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
	}
	
	/* Definisce le azioni da eseguire in base al pulsante clickato.*/
	
	public void actionPerformed(ActionEvent e){
		if (btnAggiungi == e.getSource()){
			try{
			/* Inserire cosa fa il pulsante Aggiungi*/
			String user = txtUsername.getText();
			String pass = txtPassword.getText();
			JOptionPane.showMessageDialog(null , "Nuovo Operatore Aggiunto!");
			txtUsername.setText("");
			txtPassword.setText("");
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Operatore non Aggiunto!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(btnElimina == e.getSource()){
			try{
			/* Inserire cosa fa il pulsante Elimina*/
			String user = txtUsername.getText();
			JOptionPane.showMessageDialog(null , "Operatore Eliminato!");
			txtUsername.setText("");
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Operatore non Eliminato!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
