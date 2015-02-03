package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainWindow {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JPanel shownPanel, loginPanel, menuePanel, azubiPanel,
			ausbilderPanel, registerPanel, betriebsPanel;
	private int zugangsStufe;

	public void initialize() {
		mainFrame = new JFrame("HHBK Azubiverwaltung");
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setSize(800, 600);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		ImageIcon icon = new ImageIcon("stuff/logo_hhbk_web_final.gif");
		JLabel jLogo = new JLabel(icon);
		logoPanel.add(jLogo);
		icon = new ImageIcon("stuff/DUNKEL_LOGO.gif");
		jLogo = new JLabel(icon);
		logoPanel.add(jLogo);
		// setShownLogin();
		// setShownMenue();
		 setShownAzubi();
//			setShownAusbilder();
//			setShownRegister();
//		setShowBetrieb();
		mainPanel.add(logoPanel, BorderLayout.NORTH);
		mainPanel.add(shownPanel, BorderLayout.CENTER);

		mainFrame.add(mainPanel);
		// mainFrame.pack();
		mainFrame.setVisible(true);
	}

	private void setShownLogin() {
		if (loginPanel == null) {
			createLoginPanel();
		}
		shownPanel = loginPanel;
	}

	private void setShownMenue() {
		if (menuePanel == null) {
			createMenuePanel();
		}
		shownPanel = menuePanel;
	}

	private void setShownAzubi() {
		if (azubiPanel == null) {
			createAzubiVerwaltung();
		}
		shownPanel = azubiPanel;
	}

	private void setShownAusbilder() {
		if (ausbilderPanel == null) {
			createAusbilderVerwaltung();
		}
		shownPanel = ausbilderPanel;
	}

	private void setShownRegister() {
		if (registerPanel == null) {
			createRegister();
		}
		shownPanel = registerPanel;
	}
	
	private void setShowBetrieb(){
		if(betriebsPanel == null){
			createBetriebVerw();
		}
		shownPanel = betriebsPanel;
	}
	
	private void createBetriebVerw(){
		betriebsPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		JLabel label = new JLabel("Betriebsverwaltung");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		betriebsPanel.add(label, c);
		label = new JLabel("Betriebe");
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 1;
		betriebsPanel.add(label, c);
		JList betriebsListList = new JList();
		betriebsListList.setPreferredSize(new Dimension(200, 150));
		c.gridy = 2;
		c.gridheight = 6;
		betriebsPanel.add(betriebsListList, c);
		
		label = new JLabel("Bezeichnung");
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		betriebsPanel.add(label,c);
		label = new JLabel("PLZ / Ort");
		c.gridy++;
		betriebsPanel.add(label, c);
		label = new JLabel("Straße / HausNr");
		c.gridy++;
		betriebsPanel.add(label, c);
		label = new JLabel("Ansprechpartner");
		c.gridy++;
		betriebsPanel.add(label,c);
		label = new JLabel("Telefonnummer");
		c.gridy++;
		betriebsPanel.add(label,c);
		label = new JLabel("E-Mail");
		c.gridy++;
		betriebsPanel.add(label,c);
		
		JTextField bezField = new JTextField(20);
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		betriebsPanel.add(bezField,c);
		JPanel ortPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		JTextField plzField = new JTextField(6);
		JTextField ortField = new JTextField(13);
		ortPanel.add(plzField);
		ortPanel.add(new JLabel(" / "));
		ortPanel.add(ortField);
		c.gridy++;
		betriebsPanel.add(ortPanel,c);
		JTextField adresseField = new JTextField(20);
		c.gridy++;
		betriebsPanel.add(adresseField,c);
		JTextField ansprechField = new JTextField(20);
		c.gridy++;
		betriebsPanel.add(ansprechField,c);
		JTextField teleField = new JTextField(20);
		c.gridy++;
		betriebsPanel.add(teleField,c);
		JTextField eMailField = new JTextField(20);
		c.gridy++;
		betriebsPanel.add(eMailField,c);
		
		JButton closeButton = createButton("Schließen", 150, 25);
		JButton	addButton = createButton("Erstellen", 150, 25);
		JButton editButton = createButton("Ändern", 150, 25);
		JButton	eraseButton = createButton("Löschen", 150, 25);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonPanel.add(closeButton);
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 3;
		betriebsPanel.add(buttonPanel,c);
	}

	private void createRegister() {
		registerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		JLabel label = new JLabel("Registrierung");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		registerPanel.add(label, c);
		label = new JLabel("Nutzer");
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 1;
		registerPanel.add(label, c);
		JList nutzerList = new JList();
		nutzerList.setPreferredSize(new Dimension(200, 250));
		c.gridy = 2;
		c.gridheight = 8;
		registerPanel.add(nutzerList, c);
		
		label = new JLabel("Vorname");
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		registerPanel.add(label,c);
		label = new JLabel("Name");
		c.gridy++;
		registerPanel.add(label,c);
		label = new JLabel("Username");
		c.gridy++;
		registerPanel.add(label,c);
		label = new JLabel("Passwort");
		c.gridy++;
		registerPanel.add(label,c);
		label = new JLabel("Telefonnummer");
		c.gridy++;
		registerPanel.add(label,c);
		label = new JLabel("E-Mail");
		c.gridy++;
		registerPanel.add(label,c);
		label = new JLabel("Berechtigung");
		c.gridy++;
		registerPanel.add(label,c);
		label = new JLabel("Klasse");
		c.gridy++;
		registerPanel.add(label,c);
		
		JTextField vorField = new JTextField(20);
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		registerPanel.add(vorField,c);
		JTextField nameField = new JTextField(20);
		c.gridy++;
		registerPanel.add(nameField,c);
		JTextField userField = new JTextField(20);
		c.gridy++;
		registerPanel.add(userField,c);
		JTextField passField = new JTextField(20);
		c.gridy++;
		registerPanel.add(passField,c);
		JTextField teleField = new JTextField(20);
		c.gridy++;
		registerPanel.add(teleField,c);
		JTextField eMailField = new JTextField(20);
		c.gridy++;
		registerPanel.add(eMailField,c);
		
		JPanel berPanel = new JPanel(new GridLayout(0,1));
		JCheckBox cbLehrer = new JCheckBox("Lehrer");
		JCheckBox cbLeitung = new JCheckBox("Bereichsleitung");
		berPanel.add(cbLehrer);
		berPanel.add(cbLeitung);
		c.gridy++;
		registerPanel.add(berPanel,c);
		
		JComboBox cmbKlasse = new JComboBox();
		cmbKlasse.setPreferredSize(new Dimension(200, 22));
		c.gridy++;
		registerPanel.add(cmbKlasse,c);
		
		JButton closeButton = createButton("Schließen", 150, 25);
		JButton	addButton = createButton("Erstellen", 150, 25);
		JButton editButton = createButton("Ändern", 150, 25);
		JButton	eraseButton = createButton("Löschen", 150, 25);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonPanel.add(closeButton);
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 3;
		registerPanel.add(buttonPanel,c);
	}

	private void createAusbilderVerwaltung() {
		ausbilderPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		JLabel label = new JLabel("Ausbilderverwaltung");
		c.gridx = 1;
		c.gridy = 0;
		ausbilderPanel.add(label, c);
		label = new JLabel("Betriebswahl");
		c.gridx = 0;
		c.gridy = 1;
		ausbilderPanel.add(label, c);
		JComboBox cmbBetrieb = new JComboBox();
		cmbBetrieb.setPreferredSize(new Dimension(200, 25));
		c.gridy = 2;
		ausbilderPanel.add(cmbBetrieb, c);
		label = new JLabel("Ausbilder");
		c.gridy = 3;
		ausbilderPanel.add(label, c);
		JList ausbilderList = new JList();
		ausbilderList.setPreferredSize(new Dimension(200, 150));
		c.gridy = 4;
		c.gridheight = 6;
		ausbilderPanel.add(ausbilderList, c);
		label = new JLabel("Vorname");
		c.gridy = 4;
		c.gridx = 1;
		c.gridheight = 1;
		ausbilderPanel.add(label, c);
		label = new JLabel("Name");
		c.gridy++;
		ausbilderPanel.add(label, c);
		label = new JLabel("Geburtsdatum");
		c.gridy++;
		ausbilderPanel.add(label, c);
		label = new JLabel("Geschlecht");
		c.gridy++;
		ausbilderPanel.add(label, c);
		label = new JLabel("Telefonnummer");
		c.gridy++;
		ausbilderPanel.add(label, c);
		label = new JLabel("E-Mail");
		c.gridy++;
		ausbilderPanel.add(label, c);

		JTextField vorField = new JTextField(20);
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 2;
		ausbilderPanel.add(vorField, c);
		JTextField nachField = new JTextField(20);
		c.gridy = 5;
		ausbilderPanel.add(nachField, c);
		label = new JLabel("Hier kommt der DatePicker rein");
		c.gridy = 6;
		ausbilderPanel.add(label, c);
		ButtonGroup btgr = new ButtonGroup();
		JRadioButton rbMann = new JRadioButton("Männlich");
		JRadioButton rbFrau = new JRadioButton("Weiblich");
		btgr.add(rbMann);
		btgr.add(rbFrau);
		JPanel rbPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		rbPanel.add(rbFrau);
		rbPanel.add(rbMann);
		c.gridy = 7;
		c.gridwidth = 2;
		ausbilderPanel.add(rbPanel, c);

		JTextField tNummerField = new JTextField(20);
		c.gridy = 8;
		ausbilderPanel.add(tNummerField, c);
		JTextField eMailField = new JTextField(20);
		c.gridy = 9;
		ausbilderPanel.add(eMailField, c);
		JButton closeButton = createButton("Schließen", 150, 25);
		JButton	addButton = createButton("Erstellen", 150, 25);
		JButton editButton = createButton("Ändern", 150, 25);
		JButton	eraseButton = createButton("Löschen", 150, 25);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonPanel.add(closeButton);
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 4;
		ausbilderPanel.add(buttonPanel, c);

	}

	private void createAzubiVerwaltung() {
		azubiPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		JLabel label = new JLabel("Azubiverwaltung");
		c.gridx = 1;
		c.gridy = 0;
		azubiPanel.add(label, c);
		label = new JLabel("Klassenwahl");
		c.gridx = 0;
		c.gridy = 1;
		azubiPanel.add(label, c);
		JComboBox cmbKlasse = new JComboBox();
		cmbKlasse.setPreferredSize(new Dimension(200, 25));
		c.gridy = 2;
		azubiPanel.add(cmbKlasse, c);
		label = new JLabel("Azubis");
		c.gridy = 3;
		azubiPanel.add(label, c);
		JList azubiList = new JList();
		azubiList.setPreferredSize(new Dimension(200, 200));
		c.gridy = 4;
		c.gridheight = 8;
		azubiPanel.add(azubiList, c);
		label = new JLabel("Vorname");
		c.gridy = 4;
		c.gridx = 1;
		c.gridheight = 1;
		azubiPanel.add(label, c);
		label = new JLabel("Name");
		c.gridy++;
		azubiPanel.add(label, c);
		label = new JLabel("Geburtsdatum");
		c.gridy++;
		azubiPanel.add(label, c);
		label = new JLabel("Geschlecht");
		c.gridy++;
		azubiPanel.add(label, c);
		label = new JLabel("PLZ / Ort");
		c.gridy++;
		azubiPanel.add(label, c);
		label = new JLabel("Straße / HausNr");
		c.gridy++;
		azubiPanel.add(label, c);
		label = new JLabel("Telefonnummer");
		c.gridy++;
		azubiPanel.add(label, c);
		label = new JLabel("E-Mail");
		c.gridy++;
		azubiPanel.add(label, c);

		JTextField vorField = new JTextField(20);
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 2;
		azubiPanel.add(vorField, c);
		JTextField nachField = new JTextField(20);
		c.gridy = 5;
		azubiPanel.add(nachField, c);
		label = new JLabel("Hier kommt der DatePicker rein");
		c.gridy = 6;
		azubiPanel.add(label, c);
		ButtonGroup btgr = new ButtonGroup();
		JRadioButton rbMann = new JRadioButton("Männlich");
		JRadioButton rbFrau = new JRadioButton("Weiblich");
		btgr.add(rbMann);
		btgr.add(rbFrau);
		JPanel rbPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		rbPanel.add(rbFrau);
		rbPanel.add(rbMann);
		c.gridy = 7;
		c.gridwidth = 2;
		azubiPanel.add(rbPanel, c);
		JPanel ortPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		JTextField plzField = new JTextField(6);
		JTextField ortField = new JTextField(13);
		ortPanel.add(plzField);
		ortPanel.add(new JLabel(" / "));
		ortPanel.add(ortField);
		c.gridx = 2;
		c.gridy = 8;
		azubiPanel.add(ortPanel, c);

		JTextField strField = new JTextField(20);
		c.gridx = 2;
		c.gridy = 9;
		c.gridwidth = 2;
		azubiPanel.add(strField, c);
		JTextField tNummerField = new JTextField(20);
		c.gridy = 10;
		azubiPanel.add(tNummerField, c);
		JTextField eMailField = new JTextField(20);
		c.gridy = 11;
		azubiPanel.add(eMailField, c);
		JButton closeButton = createButton("Schließen", 150, 25);
		JButton	addButton = createButton("Erstellen", 150, 25);
		JButton editButton = createButton("Ändern", 150, 25);
		JButton	eraseButton = createButton("Löschen", 150, 25);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonPanel.add(closeButton);
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 4;
		azubiPanel.add(buttonPanel, c);

	}

	private void createMenuePanel() {
		menuePanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20, 20, 20, 20);
		JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel jlabel = new JLabel("Benutzer:");
		userPanel.add(jlabel);
		JLabel benutzerLabel = new JLabel("Benutzername");
		userPanel.add(benutzerLabel);
		JButton logoutButton = createButton("Logout", 100, 25);
		userPanel.add(logoutButton);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		menuePanel.add(userPanel, c);
		JButton azubiButton = createButton("Azubiverwaltung", 175, 25);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		menuePanel.add(azubiButton, c);
		JButton ausbilderButton = createButton("Ausbilderverwaltung", 175, 25);
		c.gridx = 2;
		menuePanel.add(ausbilderButton, c);
		JButton regButton = createButton("Registrieren", 175, 25);
		c.gridx = 1;
		c.gridy = 2;
		menuePanel.add(regButton, c);
		JButton betriebButton = createButton("Betriebsverwaltung", 175, 25);
		c.gridx = 0;
		c.gridy = 3;
		menuePanel.add(betriebButton, c);
		JButton zeugnisButton = createButton("Zeugnisverwaltung", 175, 25);
		c.gridx = 2;
		c.gridy = 3;
		menuePanel.add(zeugnisButton, c);

	}

	private void createLoginPanel() {
		loginPanel = new JPanel();
		loginPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20, 20, 20, 20);
		JLabel jlabel = new JLabel("Login");
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		loginPanel.add(jlabel, c);
		jlabel = new JLabel("Benutzername");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		loginPanel.add(jlabel, c);
		jlabel = new JLabel("Passwort");
		c.gridx = 0;
		c.gridy = 2;
		loginPanel.add(jlabel, c);
		JTextField lognameFeld = new JTextField(15);
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		loginPanel.add(lognameFeld, c);
		JPasswordField passwordField = new JPasswordField(15);
		c.gridx = 3;
		c.gridy = 2;
		loginPanel.add(passwordField, c);
		JButton loginButton = createButton("Login", 175, 25);
		// **************************************************
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO getZugangsStufe
				zugangsStufe = 0;
				setShownMenue();
			}
		});
		// **************************************************
		c.gridx = 0;
		c.gridy = 3;
		loginPanel.add(loginButton, c);
		JButton abbruchButton = createButton("Abbrechen", 175, 25);
		c.gridx = 3;
		c.gridy = 3;
		loginPanel.add(abbruchButton, c);
	}

	private JButton createButton(String text, int wight, int height) {
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(wight, height));
		return button;
	}
}
