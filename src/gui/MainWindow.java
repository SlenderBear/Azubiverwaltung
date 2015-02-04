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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class MainWindow {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JPanel loginPanel, azubiPanel,userPanel,
			ausbilderPanel, registerPanel, betriebsPanel, klassenPanel, zeugnisPanel;
	private JTabbedPane menuePanel;
	private int zugangsStufe;
	private String[] tHeads = {"Azubi","Note"};
	private Properties p;
	private UtilDateModel model;
    private JDatePanelImpl datePanel;
    private DateLabelFormatter dlf;
    
    public MainWindow() {
    	dlf = new DateLabelFormatter();
    	p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		model=new UtilDateModel();
	    datePanel = new JDatePanelImpl(model,p);
	}

	public void initialize() {
		mainFrame = new JFrame("HHBK Azubiverwaltung");
		mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
		 setShownLogin();
//		 setShownMenue();
//		 setShownAzubi();
//			setShownAusbilder();
//			setShownRegister();
//		setShowBetrieb();
//		 setShownKlassen();
//		setShownZeugnis();
		mainPanel.add(logoPanel, BorderLayout.NORTH);

		mainFrame.add(mainPanel);
		// mainFrame.pack();
		mainFrame.setVisible(true);
	}

	private void setShownLogin() {
		if (loginPanel == null) {
			createLoginPanel();
			mainPanel.add(loginPanel,BorderLayout.CENTER);
		}
		else
		{
			mainPanel.remove(menuePanel);
			mainPanel.add(loginPanel);
			mainFrame.invalidate();
			mainFrame.validate();
		}
	}

	private void setShownMenue() {
		if (menuePanel == null) {
			createMenuePanel();
		}
		mainPanel.remove(loginPanel);
		mainPanel.add(menuePanel);
		mainFrame.invalidate();
		mainFrame.validate();
	}
//
//	private void setShownAzubi() {
//		if (azubiPanel == null) {
//			createAzubiVerwaltung();
//		}
//		shownPanel = azubiPanel;
//	}
//
//	private void setShownAusbilder() {
//		if (ausbilderPanel == null) {
//			createAusbilderVerwaltung();
//		}
//		shownPanel = ausbilderPanel;
//	}
//
//	private void setShownRegister() {
//		if (registerPanel == null) {
//			createRegister();
//		}
//		shownPanel = registerPanel;
//	}
//	
//	private void setShowBetrieb(){
//		if(betriebsPanel == null){
//			createBetriebVerw();
//		}
//		shownPanel = betriebsPanel;
//	}
//	
//	private void setShownKlassen(){
//		if(klassenPanel == null){
//			createKlassenVerwaltung();
//		}
//		shownPanel = klassenPanel;
//	}
//	
//	private void setShownZeugnis(){
//		if(zeugnisPanel == null){
//			createZeugnisVerwaltung();
//		}
//		shownPanel = zeugnisPanel;
//	}
	
	private void createZeugnisVerwaltung(){
		zeugnisPanel = new JPanel(new BorderLayout());
		JPanel innerZeugnisPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		JLabel label = new JLabel("Klassenwahl");
		c.gridy=0;
		c.gridwidth = 1;
		innerZeugnisPanel.add(label, c);
		JComboBox cmbKlasse = new JComboBox();
		cmbKlasse.setPreferredSize(new Dimension(200, 25));
		c.gridy++;
		innerZeugnisPanel.add(cmbKlasse, c);
		label = new JLabel("Azubis");
		c.gridy++;
		innerZeugnisPanel.add(label, c);
		JList azubiList = new JList();
		azubiList.setPreferredSize(new Dimension(200, 150));
		c.gridy++;
		c.gridheight = 4;
		innerZeugnisPanel.add(azubiList, c);
		//
		label = new JLabel("Jahr");
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		innerZeugnisPanel.add(label, c);
		
		Calendar now = Calendar.getInstance();
		int jahr = now.get(Calendar.YEAR);
		Vector years = new Vector();
		for(int i = 0; i < 5; i++){
			years.add(jahr-i);
		}
		
		JComboBox cmbJahrBox = new JComboBox(years);
		cmbJahrBox.setPreferredSize(new Dimension(150, 25));
		c.gridy++;
		innerZeugnisPanel.add(cmbJahrBox, c);
		//
		//
		DefaultTableModel dtmNoten = new MyTable(8, 2);
		dtmNoten.setColumnIdentifiers(tHeads);
		JTable notenTable = new JTable(dtmNoten);
		JScrollPane tableScrollPane = new JScrollPane(notenTable);
		tableScrollPane.setPreferredSize(new Dimension(250, 148));
		c.gridy = 4;
		c.gridheight = 4;
		innerZeugnisPanel.add(tableScrollPane, c);
		//
		
		//
		label = new JLabel("Zeugniskonferenz am:");
		c.gridx = 2;
		c.gridheight = 1;
		c.gridy = 1;
		innerZeugnisPanel.add(label, c);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,dlf);
		c.gridy++;
		innerZeugnisPanel.add(datePicker, c);
		
		//
		label = new JLabel("Zeugnis drucken für:");
		c.gridy++;
		innerZeugnisPanel.add(label, c);
		JButton btZeugDruck = createButton("Schüler", 150, 25);
		JButton btZeugKlasseDruck = createButton("Gesamte Klasse", 150, 25);
		JPanel druckPanel = new JPanel(new GridLayout(0, 1, 0, 25));
//		druckPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		druckPanel.add(btZeugDruck);
		druckPanel.add(btZeugKlasseDruck);

		zeugnisPanel.add(createTitlePanel("Zeugnisverwaltung"),BorderLayout.NORTH);
		zeugnisPanel.add(innerZeugnisPanel,BorderLayout.CENTER);
		zeugnisPanel.add(druckPanel,BorderLayout.SOUTH);
		
	}
	
	private void createKlassenVerwaltung(){
		klassenPanel = new JPanel(new BorderLayout());
		JPanel innerKlassenPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		JLabel label = new JLabel("Klassen");
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 1;
		innerKlassenPanel.add(label, c);
		JList klassenList = new JList();
		klassenList.setPreferredSize(new Dimension(200, 150));
		c.gridy = 2;
		c.gridheight = 6;
		innerKlassenPanel.add(klassenList, c);
		
		label = new JLabel("Bezeichnung");
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		innerKlassenPanel.add(label,c);
		
		label = new JLabel("Lehrer");
		c.gridy++;
		innerKlassenPanel.add(label,c);
		
		JTextField bezField = new JTextField(20);
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		innerKlassenPanel.add(bezField,c);
		
		JComboBox cmbLehrer = new JComboBox();
		cmbLehrer.setPreferredSize(new Dimension(200, 25));
		c.gridy++;
		innerKlassenPanel.add(cmbLehrer,c);
		
		
		JButton	addButton = createButton("Erstellen", 150, 25);
		JButton editButton = createButton("Ändern", 150, 25);
		JButton	eraseButton = createButton("Löschen", 150, 25);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);
		c.gridy = 8;
		c.gridx = 0;
		c.gridwidth = 3;
		
		klassenPanel.add(createTitlePanel("Klassenverwaltung"),BorderLayout.NORTH);
		klassenPanel.add(innerKlassenPanel,BorderLayout.CENTER);
		klassenPanel.add(buttonPanel,BorderLayout.SOUTH);
	}
	
	private void createBetriebVerw(){
		betriebsPanel = new JPanel(new BorderLayout());
		JPanel innerBetriebsPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		JLabel label = new JLabel("Betriebe");
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 1;
		innerBetriebsPanel.add(label, c);
		JList betriebsList = new JList();
		betriebsList.setPreferredSize(new Dimension(200, 150));
		c.gridy = 2;
		c.gridheight = 6;
		innerBetriebsPanel.add(betriebsList, c);
		
		label = new JLabel("Bezeichnung");
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		innerBetriebsPanel.add(label,c);
		label = new JLabel("PLZ / Ort");
		c.gridy++;
		innerBetriebsPanel.add(label, c);
		label = new JLabel("Straße / HausNr");
		c.gridy++;
		innerBetriebsPanel.add(label, c);
		label = new JLabel("Ansprechpartner");
		c.gridy++;
		innerBetriebsPanel.add(label,c);
		label = new JLabel("Telefonnummer");
		c.gridy++;
		innerBetriebsPanel.add(label,c);
		label = new JLabel("E-Mail");
		c.gridy++;
		innerBetriebsPanel.add(label,c);
		
		JTextField bezField = new JTextField(20);
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		innerBetriebsPanel.add(bezField,c);
		JPanel ortPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		JTextField plzField = new JTextField(6);
		JTextField ortField = new JTextField(13);
		ortPanel.add(plzField);
		ortPanel.add(new JLabel(" / "));
		ortPanel.add(ortField);
		c.gridy++;
		innerBetriebsPanel.add(ortPanel,c);
		JTextField adresseField = new JTextField(20);
		c.gridy++;
		innerBetriebsPanel.add(adresseField,c);
		JTextField ansprechField = new JTextField(20);
		c.gridy++;
		innerBetriebsPanel.add(ansprechField,c);
		JTextField teleField = new JTextField(20);
		c.gridy++;
		innerBetriebsPanel.add(teleField,c);
		JTextField eMailField = new JTextField(20);
		c.gridy++;
		innerBetriebsPanel.add(eMailField,c);
		
		JButton	addButton = createButton("Erstellen", 150, 25);
		JButton editButton = createButton("Ändern", 150, 25);
		JButton	eraseButton = createButton("Löschen", 150, 25);
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);
		
		betriebsPanel.add(createTitlePanel("Betriebeverwaltung"),BorderLayout.NORTH);
		betriebsPanel.add(innerBetriebsPanel,BorderLayout.CENTER);
		betriebsPanel.add(buttonPanel,BorderLayout.SOUTH);
	}
	
	private JPanel createTitlePanel(String title){
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel titleLabel = new JLabel(title);
		titlePanel.add(titleLabel);
		return titlePanel;
	}

	private void createRegister() {
		registerPanel = new JPanel(new BorderLayout());
		JPanel innerRegisterPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		JLabel label = new JLabel("Nutzer");
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 1;
		innerRegisterPanel.add(label, c);
		JList nutzerList = new JList();
		nutzerList.setPreferredSize(new Dimension(200, 250));
		c.gridy = 2;
		c.gridheight = 8;
		innerRegisterPanel.add(nutzerList, c);
		
		label = new JLabel("Vorname");
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		innerRegisterPanel.add(label,c);
		label = new JLabel("Name");
		c.gridy++;
		innerRegisterPanel.add(label,c);
		label = new JLabel("Username");
		c.gridy++;
		innerRegisterPanel.add(label,c);
		label = new JLabel("Passwort");
		c.gridy++;
		innerRegisterPanel.add(label,c);
		label = new JLabel("Telefonnummer");
		c.gridy++;
		innerRegisterPanel.add(label,c);
		label = new JLabel("E-Mail");
		c.gridy++;
		innerRegisterPanel.add(label,c);
		label = new JLabel("Berechtigung");
		c.gridy++;
		innerRegisterPanel.add(label,c);
		label = new JLabel("Klasse");
		c.gridy++;
		innerRegisterPanel.add(label,c);
		
		JTextField vorField = new JTextField(20);
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		innerRegisterPanel.add(vorField,c);
		JTextField nameField = new JTextField(20);
		c.gridy++;
		innerRegisterPanel.add(nameField,c);
		JTextField userField = new JTextField(20);
		c.gridy++;
		innerRegisterPanel.add(userField,c);
		JTextField passField = new JTextField(20);
		c.gridy++;
		innerRegisterPanel.add(passField,c);
		JTextField teleField = new JTextField(20);
		c.gridy++;
		innerRegisterPanel.add(teleField,c);
		JTextField eMailField = new JTextField(20);
		c.gridy++;
		innerRegisterPanel.add(eMailField,c);
		
		JPanel berPanel = new JPanel(new GridLayout(0,1));
		JCheckBox cbLehrer = new JCheckBox("Lehrer");
		JCheckBox cbLeitung = new JCheckBox("Bereichsleitung");
		berPanel.add(cbLehrer);
		berPanel.add(cbLeitung);
		c.gridy++;
		innerRegisterPanel.add(berPanel,c);
		
		JComboBox cmbKlasse = new JComboBox();
		cmbKlasse.setPreferredSize(new Dimension(200, 22));
		c.gridy++;
		innerRegisterPanel.add(cmbKlasse,c);
		
		JButton	addButton = createButton("Erstellen", 150, 25);
		JButton editButton = createButton("Ändern", 150, 25);
		JButton	eraseButton = createButton("Löschen", 150, 25);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);
		registerPanel.add(createTitlePanel("Registrierung"),BorderLayout.NORTH);
		registerPanel.add(innerRegisterPanel,BorderLayout.CENTER);
		registerPanel.add(buttonPanel,BorderLayout.SOUTH);
	}

	private void createAusbilderVerwaltung() {
		ausbilderPanel = new JPanel(new BorderLayout());
		JPanel innerAusbilderPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		JLabel label = new JLabel("Betriebswahl");
		c.gridx = 0;
		c.gridy = 1;
		innerAusbilderPanel.add(label, c);
		JComboBox cmbBetrieb = new JComboBox();
		cmbBetrieb.setPreferredSize(new Dimension(200, 25));
		c.gridy = 2;
		innerAusbilderPanel.add(cmbBetrieb, c);
		label = new JLabel("Ausbilder");
		c.gridy = 3;
		innerAusbilderPanel.add(label, c);
		JList ausbilderList = new JList();
		ausbilderList.setPreferredSize(new Dimension(200, 150));
		c.gridy = 4;
		c.gridheight = 5;
		innerAusbilderPanel.add(ausbilderList, c);
		label = new JLabel("Vorname");
		c.gridy = 4;
		c.gridx = 1;
		c.gridheight = 1;
		innerAusbilderPanel.add(label, c);
		label = new JLabel("Name");
		c.gridy++;
		innerAusbilderPanel.add(label, c);
		label = new JLabel("Geschlecht");
		c.gridy++;
		innerAusbilderPanel.add(label, c);
		label = new JLabel("Telefonnummer");
		c.gridy++;
		innerAusbilderPanel.add(label, c);
		label = new JLabel("E-Mail");
		c.gridy++;
		innerAusbilderPanel.add(label, c);

		JTextField vorField = new JTextField(20);
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 2;
		innerAusbilderPanel.add(vorField, c);
		JTextField nachField = new JTextField(20);
		c.gridy++;
		innerAusbilderPanel.add(nachField, c);
		ButtonGroup btgr = new ButtonGroup();
		JRadioButton rbMann = new JRadioButton("Herr");
		JRadioButton rbFrau = new JRadioButton("Frau");
		btgr.add(rbMann);
		btgr.add(rbFrau);
		JPanel rbPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		rbPanel.add(rbFrau);
		rbPanel.add(rbMann);
		c.gridy++;
		c.gridwidth = 2;
		innerAusbilderPanel.add(rbPanel, c);

		JTextField tNummerField = new JTextField(20);
		c.gridy++;
		innerAusbilderPanel.add(tNummerField, c);
		JTextField eMailField = new JTextField(20);
		c.gridy++;
		innerAusbilderPanel.add(eMailField, c);
		JButton	addButton = createButton("Erstellen", 150, 25);
		JButton editButton = createButton("Ändern", 150, 25);
		JButton	eraseButton = createButton("Löschen", 150, 25);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);
		ausbilderPanel.add(createTitlePanel("Ausbilderverwaltung"),BorderLayout.NORTH);
		ausbilderPanel.add(innerAusbilderPanel,BorderLayout.CENTER);
		ausbilderPanel.add(buttonPanel, BorderLayout.SOUTH);

	}

	private void createAzubiVerwaltung() {
		azubiPanel = new JPanel(new BorderLayout());
		
		JPanel innerAzubiPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		JLabel label = new JLabel("Klassenwahl");
		c.gridx = 0;
		c.gridy = 1;
		innerAzubiPanel.add(label, c);
		JComboBox cmbKlasse = new JComboBox();
		cmbKlasse.setPreferredSize(new Dimension(200, 25));
		c.gridy = 2;
		innerAzubiPanel.add(cmbKlasse, c);
		label = new JLabel("Azubis");
		c.gridy = 3;
		innerAzubiPanel.add(label, c);
		JList azubiList = new JList();
		azubiList.setPreferredSize(new Dimension(200, 200));
		c.gridy = 4;
		c.gridheight = 8;
		innerAzubiPanel.add(azubiList, c);
		label = new JLabel("Vorname");
		c.gridy = 4;
		c.gridx = 1;
		c.gridheight = 1;
		innerAzubiPanel.add(label, c);
		label = new JLabel("Name");
		c.gridy++;
		innerAzubiPanel.add(label, c);
		label = new JLabel("Geburtsdatum");
		c.gridy++;
		innerAzubiPanel.add(label, c);
		label = new JLabel("Geschlecht");
		c.gridy++;
		innerAzubiPanel.add(label, c);
		label = new JLabel("PLZ / Ort");
		c.gridy++;
		innerAzubiPanel.add(label, c);
		label = new JLabel("Straße / HausNr");
		c.gridy++;
		innerAzubiPanel.add(label, c);
		label = new JLabel("Telefonnummer");
		c.gridy++;
		innerAzubiPanel.add(label, c);
		label = new JLabel("E-Mail");
		c.gridy++;
		innerAzubiPanel.add(label, c);

		JTextField vorField = new JTextField(20);
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 2;
		innerAzubiPanel.add(vorField, c);
		JTextField nachField = new JTextField(20);
		c.gridy = 5;
		innerAzubiPanel.add(nachField, c);
		//
	    JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,dlf);
		c.gridy = 6;
		innerAzubiPanel.add(datePicker, c);
		//
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
		innerAzubiPanel.add(rbPanel, c);
		JPanel ortPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		JTextField plzField = new JTextField(6);
		JTextField ortField = new JTextField(13);
		ortPanel.add(plzField);
		ortPanel.add(new JLabel(" / "));
		ortPanel.add(ortField);
		c.gridx = 2;
		c.gridy = 8;
		innerAzubiPanel.add(ortPanel, c);

		JTextField strField = new JTextField(20);
		c.gridx = 2;
		c.gridy = 9;
		c.gridwidth = 2;
		innerAzubiPanel.add(strField, c);
		JTextField tNummerField = new JTextField(20);
		c.gridy = 10;
		innerAzubiPanel.add(tNummerField, c);
		JTextField eMailField = new JTextField(20);
		c.gridy = 11;
		innerAzubiPanel.add(eMailField, c);
		JButton	addButton = createButton("Erstellen", 150, 25);
		JButton editButton = createButton("Ändern", 150, 25);
		JButton	eraseButton = createButton("Löschen", 150, 25);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);
//		c.gridy++;
//		c.gridx = 0;
//		c.gridwidth = 4;
		azubiPanel.add(createTitlePanel("Ausbilderverwaltung"), BorderLayout.NORTH);
		azubiPanel.add(innerAzubiPanel,BorderLayout.CENTER);
		azubiPanel.add(buttonPanel, BorderLayout.SOUTH);

	}

	private void createMenuePanel() {
//		addUserPanel();
		menuePanel = new JTabbedPane();
		if(zugangsStufe==0){
			createAzubiVerwaltung();
			menuePanel.addTab("Azubiverwaltung", azubiPanel);
		}
		if(zugangsStufe==0){
			createBetriebVerw();
			menuePanel.addTab("Betriebsverwaltung",	betriebsPanel);
		}
		if(zugangsStufe==0){
			createAusbilderVerwaltung();
			menuePanel.addTab("Ausbilderverwaltung", ausbilderPanel);
		}
		if(zugangsStufe==0){
			createKlassenVerwaltung();
			menuePanel.addTab("Klassenverwaltung", klassenPanel);
		}
		if(zugangsStufe==0){
			createRegister();
			menuePanel.addTab("Registrierung", registerPanel);
		}
		if(zugangsStufe==0){
			createZeugnisVerwaltung();
			menuePanel.addTab("Zeugnisverwaltung", zeugnisPanel);
		}
		
		
		
//		menuePanel = new JPanel(new GridBagLayout());
//		GridBagConstraints c = new GridBagConstraints();
//		c.insets = new Insets(20, 20, 20, 20);
//		JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
//		JLabel jlabel = new JLabel("Benutzer:");
//		userPanel.add(jlabel);
//		JLabel benutzerLabel = new JLabel("Benutzername");
//		userPanel.add(benutzerLabel);
//		JButton logoutButton = createButton("Logout", 100, 25);
//		userPanel.add(logoutButton);
//		c.gridx = 0;
//		c.gridy = 0;
//		c.gridwidth = 1;
//		menuePanel.add(userPanel, c);
//		JButton azubiButton = createButton("Azubiverwaltung", 175, 25);
//		c.gridx = 0;
//		c.gridy = 1;
//		c.gridwidth = 1;
//		menuePanel.add(azubiButton, c);
//		JButton ausbilderButton = createButton("Ausbilderverwaltung", 175, 25);
//		c.gridx = 2;
//		menuePanel.add(ausbilderButton, c);
//		JButton regButton = createButton("Registrieren", 175, 25);
//		c.gridx = 1;
//		c.gridy = 2;
//		menuePanel.add(regButton, c);
//		JButton betriebButton = createButton("Betriebsverwaltung", 175, 25);
//		c.gridx = 0;
//		c.gridy = 3;
//		menuePanel.add(betriebButton, c);
//		JButton zeugnisButton = createButton("Zeugnisverwaltung", 175, 25);
//		c.gridx = 2;
//		c.gridy = 3;
//		menuePanel.add(zeugnisButton, c);

	}
	
	private void addUserPanel(){
		userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel label = new JLabel("Benutzer: ");
		userPanel.add(label);
		label = new JLabel("Hier Kommt benutzername");
		userPanel.add(label);
		JButton btLogout= createButton("Logout", 150, 25);
		btLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setShownLogin();
				removeUserPanel();
			}
		});
		userPanel.add(btLogout);
		JButton closeButton = createButton("Schließen", 150, 25);
		closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
			}
		});
		userPanel.add(closeButton);
		mainPanel.add(userPanel,BorderLayout.SOUTH);
	}
	
	private void removeUserPanel(){
		mainPanel.remove(userPanel);
		mainFrame.invalidate();
		mainFrame.validate();
		userPanel = null;
		menuePanel = null;
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
				addUserPanel();
				setShownMenue();
			}
		});
		c.gridx = 0;
		c.gridy = 3;
		loginPanel.add(loginButton, c);
		// **************************************************
		// **************************************************
		JButton abbruchButton = createButton("Abbrechen", 175, 25);
		abbruchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
			}
		});
		c.gridx = 3;
		c.gridy = 3;
		loginPanel.add(abbruchButton, c);
		// **************************************************
	}

	private JButton createButton(String text, int wight, int height) {
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(wight, height));
		return button;
	}
}
