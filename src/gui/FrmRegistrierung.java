package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class FrmRegistrierung extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtVorname;
	private JTextField txtName;
	private JTextField txtTelefonnummer;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistrierung frame = new FrmRegistrierung();
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
	public FrmRegistrierung() {
		setTitle("Registrierung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblRegistrierung = new JLabel("Registrierung");
		lblRegistrierung.setForeground(Color.BLUE);
		lblRegistrierung.setFont(new Font("Times New Roman", Font.BOLD, 20));
		GridBagConstraints gbc_lblRegistrierung = new GridBagConstraints();
		gbc_lblRegistrierung.gridwidth = 8;
		gbc_lblRegistrierung.insets = new Insets(0, 0, 5, 0);
		gbc_lblRegistrierung.gridx = 0;
		gbc_lblRegistrierung.gridy = 1;
		contentPane.add(lblRegistrierung, gbc_lblRegistrierung);
		
		JLabel lblVorname = new JLabel("Vorname:");
		GridBagConstraints gbc_lblVorname = new GridBagConstraints();
		gbc_lblVorname.anchor = GridBagConstraints.WEST;
		gbc_lblVorname.insets = new Insets(0, 0, 5, 5);
		gbc_lblVorname.gridx = 4;
		gbc_lblVorname.gridy = 3;
		contentPane.add(lblVorname, gbc_lblVorname);
		
		txtVorname = new JTextField();
		GridBagConstraints gbc_txtVorname = new GridBagConstraints();
		gbc_txtVorname.insets = new Insets(0, 0, 5, 5);
		gbc_txtVorname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtVorname.gridx = 5;
		gbc_txtVorname.gridy = 3;
		contentPane.add(txtVorname, gbc_txtVorname);
		txtVorname.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 4;
		gbc_lblName.gridy = 4;
		contentPane.add(lblName, gbc_lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 5;
		gbc_txtName.gridy = 4;
		contentPane.add(txtName, gbc_txtName);
		
		JLabel lblTelefonnummer = new JLabel("Telefonnummer:");
		GridBagConstraints gbc_lblTelefonnummer = new GridBagConstraints();
		gbc_lblTelefonnummer.anchor = GridBagConstraints.WEST;
		gbc_lblTelefonnummer.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefonnummer.gridx = 4;
		gbc_lblTelefonnummer.gridy = 5;
		contentPane.add(lblTelefonnummer, gbc_lblTelefonnummer);
		
		txtTelefonnummer = new JTextField();
		txtTelefonnummer.setColumns(10);
		GridBagConstraints gbc_txtTelefonnummer = new GridBagConstraints();
		gbc_txtTelefonnummer.insets = new Insets(0, 0, 5, 5);
		gbc_txtTelefonnummer.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTelefonnummer.gridx = 5;
		gbc_txtTelefonnummer.gridy = 5;
		contentPane.add(txtTelefonnummer, gbc_txtTelefonnummer);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 4;
		gbc_lblEmail.gridy = 6;
		contentPane.add(lblEmail, gbc_lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 5;
		gbc_txtEmail.gridy = 6;
		contentPane.add(txtEmail, gbc_txtEmail);
		
		JLabel lblBerechtigung = new JLabel("Berechtigung:");
		GridBagConstraints gbc_lblBerechtigung = new GridBagConstraints();
		gbc_lblBerechtigung.anchor = GridBagConstraints.WEST;
		gbc_lblBerechtigung.insets = new Insets(0, 0, 5, 5);
		gbc_lblBerechtigung.gridx = 4;
		gbc_lblBerechtigung.gridy = 7;
		contentPane.add(lblBerechtigung, gbc_lblBerechtigung);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Lehrer");
		rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 5;
		gbc_rdbtnNewRadioButton.gridy = 7;
		contentPane.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
		
		JRadioButton rdbtnBereichsleitung = new JRadioButton("Bereichsleitung");
		buttonGroup.add(rdbtnBereichsleitung);
		GridBagConstraints gbc_rdbtnBereichsleitung = new GridBagConstraints();
		gbc_rdbtnBereichsleitung.anchor = GridBagConstraints.WEST;
		gbc_rdbtnBereichsleitung.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnBereichsleitung.gridx = 5;
		gbc_rdbtnBereichsleitung.gridy = 8;
		contentPane.add(rdbtnBereichsleitung, gbc_rdbtnBereichsleitung);
		
		JLabel lblKlasse = new JLabel("Klasse:");
		GridBagConstraints gbc_lblKlasse = new GridBagConstraints();
		gbc_lblKlasse.anchor = GridBagConstraints.WEST;
		gbc_lblKlasse.insets = new Insets(0, 0, 0, 5);
		gbc_lblKlasse.gridx = 4;
		gbc_lblKlasse.gridy = 9;
		contentPane.add(lblKlasse, gbc_lblKlasse);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.gridx = 5;
		gbc_comboBox.gridy = 9;
		contentPane.add(comboBox, gbc_comboBox);
	}

}
