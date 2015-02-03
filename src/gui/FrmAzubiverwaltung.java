package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JCheckBox;

public class FrmAzubiverwaltung extends JFrame {

	private JPanel contentPane;
	private JTextField txtVorname;
	private JTextField txtName;
	private JTextField txtGebDatum;
	private JTextField txtTelNummer;
	private JTextField txtEMail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAzubiverwaltung frame = new FrmAzubiverwaltung();
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
	public FrmAzubiverwaltung() {
		setTitle("Azubiverwaltung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblKlassenauswahl = new JLabel("Klassenauswahl:");
		GridBagConstraints gbc_lblKlassenauswahl = new GridBagConstraints();
		gbc_lblKlassenauswahl.gridwidth = 7;
		gbc_lblKlassenauswahl.insets = new Insets(0, 0, 5, 5);
		gbc_lblKlassenauswahl.gridx = 3;
		gbc_lblKlassenauswahl.gridy = 1;
		contentPane.add(lblKlassenauswahl, gbc_lblKlassenauswahl);
		
		JComboBox cmbKlassenauswahl = new JComboBox();
		GridBagConstraints gbc_cmbKlassenauswahl = new GridBagConstraints();
		gbc_cmbKlassenauswahl.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbKlassenauswahl.gridwidth = 6;
		gbc_cmbKlassenauswahl.insets = new Insets(0, 0, 5, 5);
		gbc_cmbKlassenauswahl.gridx = 3;
		gbc_cmbKlassenauswahl.gridy = 2;
		contentPane.add(cmbKlassenauswahl, gbc_cmbKlassenauswahl);
		
		JLabel lblAzubiverwaltung = new JLabel("Azubiverwaltung");
		lblAzubiverwaltung.setForeground(Color.BLUE);
		lblAzubiverwaltung.setFont(new Font("Times New Roman", Font.BOLD, 20));
		GridBagConstraints gbc_lblAzubiverwaltung = new GridBagConstraints();
		gbc_lblAzubiverwaltung.gridwidth = 3;
		gbc_lblAzubiverwaltung.insets = new Insets(0, 0, 5, 5);
		gbc_lblAzubiverwaltung.gridx = 10;
		gbc_lblAzubiverwaltung.gridy = 2;
		contentPane.add(lblAzubiverwaltung, gbc_lblAzubiverwaltung);
		
		JLabel lblAzubis = new JLabel("Azubis:");
		GridBagConstraints gbc_lblAzubis = new GridBagConstraints();
		gbc_lblAzubis.gridwidth = 3;
		gbc_lblAzubis.insets = new Insets(0, 0, 5, 5);
		gbc_lblAzubis.gridx = 3;
		gbc_lblAzubis.gridy = 3;
		contentPane.add(lblAzubis, gbc_lblAzubis);
		
		JList jlistSchueler = new JList();
		GridBagConstraints gbc_jlistSchueler = new GridBagConstraints();
		gbc_jlistSchueler.gridheight = 6;
		gbc_jlistSchueler.gridwidth = 7;
		gbc_jlistSchueler.insets = new Insets(0, 0, 5, 5);
		gbc_jlistSchueler.fill = GridBagConstraints.BOTH;
		gbc_jlistSchueler.gridx = 3;
		gbc_jlistSchueler.gridy = 4;
		contentPane.add(jlistSchueler, gbc_jlistSchueler);
		
		JLabel lblVorname = new JLabel("Vorname");
		GridBagConstraints gbc_lblVorname = new GridBagConstraints();
		gbc_lblVorname.insets = new Insets(0, 0, 5, 5);
		gbc_lblVorname.gridx = 11;
		gbc_lblVorname.gridy = 4;
		contentPane.add(lblVorname, gbc_lblVorname);
		
		txtVorname = new JTextField();
		GridBagConstraints gbc_txtVorname = new GridBagConstraints();
		gbc_txtVorname.gridwidth = 5;
		gbc_txtVorname.insets = new Insets(0, 0, 5, 5);
		gbc_txtVorname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtVorname.gridx = 12;
		gbc_txtVorname.gridy = 4;
		contentPane.add(txtVorname, gbc_txtVorname);
		txtVorname.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 11;
		gbc_lblName.gridy = 5;
		contentPane.add(lblName, gbc_lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.gridwidth = 5;
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 12;
		gbc_txtName.gridy = 5;
		contentPane.add(txtName, gbc_txtName);
		
		JLabel lblGebDatum = new JLabel("Geburtsdatum");
		GridBagConstraints gbc_lblGebDatum = new GridBagConstraints();
		gbc_lblGebDatum.insets = new Insets(0, 0, 5, 5);
		gbc_lblGebDatum.gridx = 11;
		gbc_lblGebDatum.gridy = 6;
		contentPane.add(lblGebDatum, gbc_lblGebDatum);
		
		txtGebDatum = new JTextField();
		txtGebDatum.setColumns(10);
		GridBagConstraints gbc_txtGebDatum = new GridBagConstraints();
		gbc_txtGebDatum.gridwidth = 5;
		gbc_txtGebDatum.insets = new Insets(0, 0, 5, 5);
		gbc_txtGebDatum.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtGebDatum.gridx = 12;
		gbc_txtGebDatum.gridy = 6;
		contentPane.add(txtGebDatum, gbc_txtGebDatum);
		
		JLabel lblGeschlecht = new JLabel("Geschlecht");
		GridBagConstraints gbc_lblGeschlecht = new GridBagConstraints();
		gbc_lblGeschlecht.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeschlecht.gridx = 11;
		gbc_lblGeschlecht.gridy = 7;
		contentPane.add(lblGeschlecht, gbc_lblGeschlecht);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("M\u00E4nnlich");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 12;
		gbc_chckbxNewCheckBox.gridy = 7;
		contentPane.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Weiblich");
		GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_1.gridwidth = 4;
		gbc_chckbxNewCheckBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_1.gridx = 13;
		gbc_chckbxNewCheckBox_1.gridy = 7;
		contentPane.add(chckbxNewCheckBox_1, gbc_chckbxNewCheckBox_1);
		
		JLabel lblTelefonnummer = new JLabel("Telefonnummer");
		GridBagConstraints gbc_lblTelefonnummer = new GridBagConstraints();
		gbc_lblTelefonnummer.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefonnummer.gridx = 11;
		gbc_lblTelefonnummer.gridy = 8;
		contentPane.add(lblTelefonnummer, gbc_lblTelefonnummer);
		
		txtTelNummer = new JTextField();
		txtTelNummer.setColumns(10);
		GridBagConstraints gbc_txtTelNummer = new GridBagConstraints();
		gbc_txtTelNummer.gridwidth = 5;
		gbc_txtTelNummer.insets = new Insets(0, 0, 5, 5);
		gbc_txtTelNummer.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTelNummer.gridx = 12;
		gbc_txtTelNummer.gridy = 8;
		contentPane.add(txtTelNummer, gbc_txtTelNummer);
		
		JLabel lblGeburtsdatum = new JLabel("E-Mail");
		GridBagConstraints gbc_lblGeburtsdatum = new GridBagConstraints();
		gbc_lblGeburtsdatum.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeburtsdatum.gridx = 11;
		gbc_lblGeburtsdatum.gridy = 9;
		contentPane.add(lblGeburtsdatum, gbc_lblGeburtsdatum);
		
		txtEMail = new JTextField();
		txtEMail.setColumns(10);
		GridBagConstraints gbc_txtEMail = new GridBagConstraints();
		gbc_txtEMail.gridwidth = 5;
		gbc_txtEMail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEMail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEMail.gridx = 12;
		gbc_txtEMail.gridy = 9;
		contentPane.add(txtEMail, gbc_txtEMail);
		
		JButton btnAendern = new JButton("\u00C4ndern");
		GridBagConstraints gbc_btnAendern = new GridBagConstraints();
		gbc_btnAendern.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAendern.insets = new Insets(0, 0, 5, 5);
		gbc_btnAendern.gridx = 11;
		gbc_btnAendern.gridy = 10;
		contentPane.add(btnAendern, gbc_btnAendern);
	}

}
