package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtBenutzername;
	private JTextField txtPasswort;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setTitle("Azubiverwaltung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblLogin.setForeground(Color.BLUE);
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.gridwidth = 3;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 5;
		gbc_lblLogin.gridy = 1;
		contentPane.add(lblLogin, gbc_lblLogin);
		
		JLabel lblBenutzername = new JLabel("Benutzername:");
		GridBagConstraints gbc_lblBenutzername = new GridBagConstraints();
		gbc_lblBenutzername.anchor = GridBagConstraints.WEST;
		gbc_lblBenutzername.gridwidth = 2;
		gbc_lblBenutzername.insets = new Insets(0, 0, 5, 5);
		gbc_lblBenutzername.gridx = 3;
		gbc_lblBenutzername.gridy = 4;
		contentPane.add(lblBenutzername, gbc_lblBenutzername);
		
		txtBenutzername = new JTextField();
		GridBagConstraints gbc_txtBenutzername = new GridBagConstraints();
		gbc_txtBenutzername.gridwidth = 3;
		gbc_txtBenutzername.insets = new Insets(0, 0, 5, 5);
		gbc_txtBenutzername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBenutzername.gridx = 6;
		gbc_txtBenutzername.gridy = 4;
		contentPane.add(txtBenutzername, gbc_txtBenutzername);
		txtBenutzername.setColumns(10);
		
		JLabel lblPasswort = new JLabel("Passwort:");
		GridBagConstraints gbc_lblPasswort = new GridBagConstraints();
		gbc_lblPasswort.anchor = GridBagConstraints.WEST;
		gbc_lblPasswort.gridwidth = 2;
		gbc_lblPasswort.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswort.gridx = 3;
		gbc_lblPasswort.gridy = 6;
		contentPane.add(lblPasswort, gbc_lblPasswort);
		
		txtPasswort = new JTextField();
		GridBagConstraints gbc_txtPasswort = new GridBagConstraints();
		gbc_txtPasswort.gridwidth = 3;
		gbc_txtPasswort.insets = new Insets(0, 0, 5, 5);
		gbc_txtPasswort.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPasswort.gridx = 6;
		gbc_txtPasswort.gridy = 6;
		contentPane.add(txtPasswort, gbc_txtPasswort);
		txtPasswort.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.ipadx = 15;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 4;
		gbc_btnLogin.gridy = 9;
		contentPane.add(btnLogin, gbc_btnLogin);
		
		JButton btnBeenden = new JButton("Beenden");
		GridBagConstraints gbc_btnBeenden = new GridBagConstraints();
		gbc_btnBeenden.insets = new Insets(0, 0, 5, 5);
		gbc_btnBeenden.gridx = 8;
		gbc_btnBeenden.gridy = 9;
		contentPane.add(btnBeenden, gbc_btnBeenden);
	}

}
