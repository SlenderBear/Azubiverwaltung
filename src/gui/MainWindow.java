package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import objects.Ausbilder;
import objects.Azubi;
import objects.Betrieb;
import objects.Klasse;
import objects.Lehrer;
import objects.Login;

import businesslogik.dataprovider.StandardDataProvider;

/**
 * HauptKlasse für die Benutzeroberflaeche. Erstellt die GUI.
 * @author Maksim Imaev
 *
 */
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

	/**
	 * Constructor der Klasse. Als Uebergabeparameter kriegt den StandartDataProvider.
	 * Erstellt eine Instanz von GUITools
	 * setzt den benutzer auf null
	 * ruft die Methoden setLists()
	 * und start() auf
	 * @param sdp
	 */
	public MainWindow(StandardDataProvider sdp) {
		c = new GridBagConstraints();
		tools = new GUITools();
		this.sdp = sdp;
		this.user = null;
		setLists();
		start();
	}

	/**
	 * Methode initialize
	 * Erstellt das "HauptFenster" der GUI (mainFrame)
	 */
	public void initialize() {
		mainFrame = new JFrame("HHBK Azubiverwaltung");
		mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		mainFrame.setSize(1050, 750);
		mainFrame.setResizable(false);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		ImageIcon icon = new ImageIcon("stuff/logo_hhbk_web_final.gif");
		JLabel jLogo = new JLabel(icon);
		logoPanel.add(jLogo);
		icon = new ImageIcon("stuff/DUNKEL_LOGO.gif");
		jLogo = new JLabel(icon);
		logoPanel.add(jLogo);
		setMiddleLogin();
		mainPanel.add(logoPanel, BorderLayout.NORTH);

		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
	}
	
	/**
	 * Methode setMiddleLogin()
	 * setzt den mittleren Panel beim mainFrame
	 * auf login-Panel
	 */

	private void setMiddleLogin() {
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
	
	/**
	 * Methode setMiddleMenue
	 * setzt den mittleren Panel beim mainFrame
	 * auf "menuePanel" welches alle anderen Panels beinhaltet
	 */
	private void setMiddleMenue() {
		if (menuePanel == null) {
			createMenuePanel();
		}
		mainPanel.remove(loginPanel);
		mainPanel.add(menuePanel);
		mainFrame.invalidate();
		mainFrame.validate();
	}

	/**
	 * Methode createZeugnisVerwaltung()
	 * setzt zeugnisPanel auf eine Instanz von ZeugnisVerwaltungPanel
	 */
	private void createZeugnisVerwaltung() {
		zeugnisPanel = new ZeugnisVerwaltungPanel(zugangsStufe, sdp,
				klasseList, tools);
	}

	/**
	 * Methode createKlassenVerwaltung()
	 * setzt klassenPanel auf eine Instanz von KlassenVerwaltungPanel
	 */
	private void createKlassenVerwaltung() {
		klassenPanel = new KlassenVerwaltungPanel(zugangsStufe, sdp,
				klasseList, lehrerList, tools);
	}

	/**
	 * Methode createBetriebVerwaltung() 
	 * setzt betriebsPanel auf eine Instanz von BetriebeVerwaltungPanel
	 */
	private void createBetriebVerwaltung() {
		betriebsPanel = new BetriebeVerwaltungPanel(zugangsStufe, sdp,
				betriebList, tools);
	}

	/**
	 * Methode createRegisterVerwaltung()
	 * setzt registerPanel auf eine Instanz von RegisterVerwaltungPanel
	 */
	private void createRegisterVerwaltung() {
		registerPanel = new RegisterVerwaltungPanel(zugangsStufe, sdp,
				lehrerList, tools);
	}

	/**
	 * Methode createAusbilderVerwaltung() 
	 * setzt ausbilderPanel auf eine Instanz von AusbilderVerwaltungPanel
	 */
	private void createAusbilderVerwaltung() {
		ausbilderPanel = new AusbilderVerwaltungPanel(zugangsStufe, sdp,
				betriebList, ausbilderList, tools);
	}

	/**
	 * Methode createAzubiVerwaltung()
	 * setzt azubiPanel auf eine Instanz von AzubiVerwaltungPanel
	 */
	private void createAzubiVerwaltung() {
		azubiPanel = new AzubiVerwaltungPanel(zugangsStufe, sdp, klasseList,
				ausbilderList, azubiList, tools);
	}
	/**
	 * Methode createMenuePanel()
	 * erstellt und fuellt das menuePanel
	 * entsprechend den Zugangrechten des Users
	 * (niemend, außer Admin, kann den Panel/Tab zur Verwaltung von Login-Daten von Lerern sehen)
	 * 
	 */
	private void createMenuePanel() {
		menuePanel = new JTabbedPane();
		if (zugangsStufe >= 0) {
			createAzubiVerwaltung();
			menuePanel.addTab("Azubiverwaltung", azubiPanel);
		}
		if (zugangsStufe >= 0) {
			createBetriebVerwaltung();
			menuePanel.addTab("Betriebsverwaltung", betriebsPanel);
		}
		if (zugangsStufe >= 0) {
			createAusbilderVerwaltung();
			menuePanel.addTab("Ausbilderverwaltung", ausbilderPanel);
		}
		if (zugangsStufe >= 0) {
			createKlassenVerwaltung();
			menuePanel.addTab("Klassenverwaltung", klassenPanel);
		}
		if (zugangsStufe == 0) {
			createRegisterVerwaltung();
			menuePanel.addTab("Registrierung", registerPanel);
		}
		if (zugangsStufe >= 0) {
			createZeugnisVerwaltung();
			menuePanel.addTab("Zeugnisverwaltung", zeugnisPanel);
		}

	}
	/**
	 * Methode addUserPanel()
	 * erstellt nach dem Login ein UserPanel fuer mainFrame
	 * mit der Anzeige für den Benutzer sowie Buttons zum Auslogen bzw. Verlassen des Programms
	 */
	private void addUserPanel() {
		JLabel label = new JLabel("Benutzer: ");
		JButton btLogout = tools.createButton("Logout", 150, 25);
		JButton closeButton = tools.createButton("Schliessen", 150, 25);
		userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

		userPanel.add(label);
		if (user != null) {
			label = new JLabel(user.getLoginName());
		} else {
			label = new JLabel("leeres Login");
		}
		userPanel.add(label);

		btLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setMiddleLogin();
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

	/**
	 * Methode removeUserPanel()
	 * entfernt den UserPanel von dem mainPanel der mainFrame
	 * ausserdem setzt userPanel und menuePanel auf null
	 */
	private void removeUserPanel() {
		mainPanel.remove(userPanel);
		mainFrame.invalidate();
		mainFrame.validate();
		userPanel = null;
		menuePanel = null;
	}

	/**
	 * Methode createLoginPanel()
	 * erstellt den loginPanel
	 */
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
				if (setUser(lognameFeld.getText(), passwordField.getText())) {
					addUserPanel();
					setMiddleMenue();
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

	/**
	 * Methode setLists()
	 * holt die listen aus der DB
	 * bzw erzeugt sie Leer
	 */
	private void setLists() {
		klasseList = sdp.gibAlleKlassen();
		betriebList = sdp.gibAlleBetriebe();
		ausbilderList = sdp.gibAlleAusbilder();
		azubiList = new ArrayList<Azubi>();
		lehrerList = sdp.gibAlleLehrer();
	}
	

	/**
	 * Methode start()
	 * erstellt das erste Fenster der Anwendung zur Auswahl der DB, 
	 * der bei der Anwendung benutzt wird
	 */
	private void start() {
		final JFrame firstFrame = new JFrame("Auswahl Datenbank");
		JPanel firstpanel = new JPanel(new GridBagLayout());

		firstFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		firstFrame.setSize(400, 400);
		firstFrame.setResizable(false);
		tools.setConstraintsDefault(c);
		c.gridwidth = 2;
		firstpanel.add(tools.createTitlePanel("Datenbank Wдhlen"));
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
				if (rbMYSQL.isSelected()) {
					sdp.changeDataProvider(StandardDataProvider.db_optionen.MYSQL
							.toString());
				} else {

					sdp.changeDataProvider(StandardDataProvider.db_optionen.SQLITE
							.toString());
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

	/**
	 * Methode setuser
	 * @param login String mit dem Loginnamen
	 * @param passwd String mit dem Passwort
	 * @return true, wenn erfolgreich
	 */
	private boolean setUser(String login, String passwd) {

		user = sdp.getLoginByLoginDaten(new Login(login, passwd));
		if (user != null) {

			zugangsStufe = user.getBerechtigung().getID();
			return true;
		} else {

			return false;
		}

	}

}
