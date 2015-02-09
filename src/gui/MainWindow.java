package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import objects.Ausbilder;
import objects.Azubi;
import objects.Berechtigung;
import objects.Betrieb;
import objects.Klasse;
import objects.Lehrer;
import objects.Login;

import org.jdatepicker.DateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import businesslogik.dataprovider.StandardDataProvider;


//import datenbank.provider.StandardDataProvider;

public class MainWindow {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JPanel loginPanel, azubiPanel, userPanel, ausbilderPanel,
			registerPanel, betriebsPanel, klassenPanel, zeugnisPanel;
	private JTabbedPane menuePanel;
	private int zugangsStufe;
	private GUITools tools;
	
	private Login user;
	
	
	private GridBagConstraints c;
	
	private ArrayList<Klasse> klasseList;
	private ArrayList<Betrieb> betriebList;
	private ArrayList<Ausbilder> ausbilderList;
	private ArrayList<Azubi> azubiList;
	private ArrayList<Lehrer> lehrerList;
	private StandardDataProvider sdp;
	
	

	public MainWindow(StandardDataProvider sdp) {
		c = new GridBagConstraints();
		tools = new GUITools();
		this.sdp = sdp;
		this.user = null;
		setLists();
		start();
	}

	public void initialize() {
		mainFrame = new JFrame("HHBK Azubiverwaltung");
		mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		mainFrame.setSize(1050, 750);
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
		mainPanel.add(logoPanel, BorderLayout.NORTH);

		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
	}

	private void setShownLogin() {
		if (loginPanel == null) {
			createLoginPanel();
			mainPanel.add(loginPanel, BorderLayout.CENTER);
		} else {
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

	private void createZeugnisVerwaltung() {
		zeugnisPanel = new ZeugnisVerwaltungPanel(sdp,klasseList,tools);
	}

	private void createKlassenVerwaltung() {
		klassenPanel = new KlassenVerwaltungPanel(sdp,klasseList,lehrerList,tools);
	}

	private void createBetriebVerwaltung() {
		betriebsPanel = new BetriebeVerwaltungPanel(sdp,betriebList,tools);
	}

	private void createRegisterVerwaltung() {
		registerPanel = new RegisterVerwaltungPanel(sdp,lehrerList, tools);
	}

	private void createAusbilderVerwaltung() {
		ausbilderPanel = new AusbilderVerwaltungPanel(sdp,betriebList, ausbilderList, tools);
	}

	private void createAzubiVerwaltung() {
		azubiPanel = new AzubiVerwaltungPanel(sdp,klasseList, ausbilderList,azubiList, tools);
	}
	
	

	private void createMenuePanel() {
		// addUserPanel();
		menuePanel = new JTabbedPane();
		if (zugangsStufe == 0) {
			createAzubiVerwaltung();
			menuePanel.addTab("Azubiverwaltung", azubiPanel);
		}
		if (zugangsStufe == 0) {
			createBetriebVerwaltung();
			menuePanel.addTab("Betriebsverwaltung", betriebsPanel);
		}
		if (zugangsStufe == 0) {
			createAusbilderVerwaltung();
			menuePanel.addTab("Ausbilderverwaltung", ausbilderPanel);
		}
		if (zugangsStufe == 0) {
			createKlassenVerwaltung();
			menuePanel.addTab("Klassenverwaltung", klassenPanel);
		}
		if (zugangsStufe == 0) {
			createRegisterVerwaltung();
			menuePanel.addTab("Registrierung", registerPanel);
		}
		if (zugangsStufe == 0) {
			createZeugnisVerwaltung();
			menuePanel.addTab("Zeugnisverwaltung", zeugnisPanel);
		}

	}

	private void addUserPanel() {
		JLabel label = new JLabel("Benutzer: ");
		JButton btLogout = tools.createButton("Logout", 150, 25);
		JButton closeButton = tools.createButton("Schliessen", 150, 25);
		userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

		userPanel.add(label);
		if(user != null){
		label = new JLabel(user.getLoginName());
		}
		else{
			label = new JLabel("leeres Login");
		}
		userPanel.add(label);

		btLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setShownLogin();
				removeUserPanel();
			}
		});
		userPanel.add(btLogout);
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
			}
		});
		userPanel.add(closeButton);
		mainPanel.add(userPanel, BorderLayout.SOUTH);
	}

	private void removeUserPanel() {
		mainPanel.remove(userPanel);
		mainFrame.invalidate();
		mainFrame.validate();
		userPanel = null;
		menuePanel = null;
	}

	private void createLoginPanel() {
		loginPanel = new JPanel();
		loginPanel.setLayout(new GridBagLayout());
		tools.setConstraintsDefault(c);
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		loginPanel.add(tools.createTitlePanel("Login"), c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		loginPanel.add(new JLabel("Benutzername"), c);
		c.gridx = 0;
		c.gridy = 2;
		loginPanel.add(new JLabel("Passwort"), c);
		final JTextField lognameFeld = new JTextField(15);
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		loginPanel.add(lognameFeld, c);
		final JPasswordField passwordField = new JPasswordField(15);
		c.gridx = 3;
		c.gridy = 2;
		loginPanel.add(passwordField, c);
		JButton loginButton = tools.createButton("Login", 175, 25);
		// **************************************************
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(setUser(lognameFeld.getText(), passwordField.getText())){
					addUserPanel();
					setShownMenue();
				}
				
			}
		});
		c.gridx = 0;
		c.gridy = 3;
		loginPanel.add(loginButton, c);
		// **************************************************
		// **************************************************
		JButton abbruchButton = tools.createButton("Abbrechen", 175, 25);
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
	
	private void setLists(){
//		klasseList = new ArrayList<Klasse>();
//		betriebList = new ArrayList<Betrieb>();
//		ausbilderList = new ArrayList<Ausbilder>();
//		azubiList = new ArrayList<Azubi>();
//		lehrerList = new ArrayList<Lehrer>();
		klasseList = sdp.gibAlleKlassen();
		betriebList = sdp.gibAlleBetriebe();
		ausbilderList = sdp.gibAlleAusbilder();
		azubiList = new ArrayList<Azubi>();
		lehrerList = sdp.gibAlleLehrer();
	}
	
	private void start(){
		final JFrame firstFrame = new JFrame("Auswahl Datenbank");
		JPanel firstpanel = new JPanel(new GridBagLayout());
		
		firstFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		firstFrame.setSize(400, 400);
		tools.setConstraintsDefault(c);
		c.gridwidth = 2;
		firstpanel.add(tools.createTitlePanel("Datenbank Wählen"));
		c.gridwidth = 1;
		
		final JRadioButton rbMYSQL = new JRadioButton("MySQL");
		rbMYSQL.setSelected(true);
		JRadioButton rbSQLite = new JRadioButton("SQLite");
		
		ButtonGroup dbButtonGroup = new ButtonGroup();
		dbButtonGroup.add(rbMYSQL);
		dbButtonGroup.add(rbSQLite);
		
		
		JButton okButton = tools.createButton("OK", 150, 25);
		JButton closeButton = tools.createButton("Abbruch", 150, 25);
		
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rbMYSQL.isSelected()){
					sdp.changeDataProvider(StandardDataProvider.db_optionen.MYSQL.toString());
				}else{
					
					sdp.changeDataProvider(StandardDataProvider.db_optionen.SQLITE.toString());
				}
				initialize();
				firstFrame.dispose();
			}
		});
		
		closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				firstFrame.dispose();
				
			}
		});
		
		tools.addComponentNextLine(firstpanel, rbMYSQL, c);
		tools.addComponentNextLine(firstpanel, rbSQLite, c);
		tools.addComponentNextLine(firstpanel, okButton, c);
		tools.addComponentNextLine(firstpanel, closeButton, c);
		
		firstFrame.add(firstpanel);
		firstFrame.setVisible(true);
	}
	
	private boolean setUser(String login, String passwd){
		
			Login logDat = sdp.getLoginByLoginDaten(new Login(login, passwd));
			if(logDat != null){
				
				zugangsStufe = logDat.getBerechtigung().getID();
				return true;
			}else{
				
				return false;
			}
		
	}

	
}
