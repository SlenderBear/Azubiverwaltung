package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	private JPanel shownPanel,loginPanel,menuePanel,azubiPanel;
	private int zugangsStufe;
	
	
	public void initialize(){
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
//		setShownLogin();
//		setShownMenue();
		setShownAzubi();
		mainPanel.add(logoPanel,BorderLayout.NORTH);
		mainPanel.add(shownPanel,BorderLayout.CENTER);
		
		
		mainFrame.add(mainPanel);
//		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	private void setShownLogin(){
		if(loginPanel == null){
			createLoginPanel();
		}
		shownPanel = loginPanel;
	}
	
	private void setShownMenue(){
		if(menuePanel == null){
			createMenuePanel();
		}
		shownPanel = menuePanel;
	}
	
	private void setShownAzubi(){
		if(azubiPanel == null){
			createAzubiVerwaltung();
		}
		shownPanel = azubiPanel;
	}
	
	private void createAzubiVerwaltung(){
		azubiPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		JLabel label = new JLabel("Azubiverwaltung");
		c.gridx = 1;
		c.gridy = 0;
		azubiPanel.add(label,c);
		label = new JLabel("Klassenwahl");
		c.gridx = 0;
		c.gridy = 1;
		azubiPanel.add(label,c);
		JComboBox cmbKlasse = new JComboBox();
		cmbKlasse.setPreferredSize(new Dimension(200, 25));
		c.gridy = 2;
		azubiPanel.add(cmbKlasse,c);
		label = new JLabel("Azubis");
		c.gridy = 3;
		azubiPanel.add(label,c);
		JList azubiList = new JList();
		azubiList.setPreferredSize(new Dimension(200, 200));
		c.gridy = 4;
		c.gridheight = 8;
		azubiPanel.add(azubiList,c);
		label = new JLabel("Vorname");
		c.gridy = 4;
		c.gridx = 1;
		c.gridheight = 1;
		azubiPanel.add(label,c);
		label = new JLabel("Name");
		c.gridy++;
		azubiPanel.add(label,c);
		label = new JLabel("Geburtsdatum");
		c.gridy++;
		azubiPanel.add(label,c);
		label = new JLabel("Geschlecht");
		c.gridy++;
		azubiPanel.add(label,c);
		label = new JLabel("PLZ / Ort");
		c.gridy++;
		azubiPanel.add(label,c);
		label = new JLabel("Straße / HausNr");
		c.gridy++;
		azubiPanel.add(label,c);
		label = new JLabel("Telefonnummer");
		c.gridy++;
		azubiPanel.add(label,c);
		label = new JLabel("E-Mail");
		c.gridy++;
		azubiPanel.add(label,c);
		
		JTextField vorField = new JTextField(20);
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 2;
		azubiPanel.add(vorField,c);
		JTextField nachField = new JTextField(20);
		c.gridy = 5;
		azubiPanel.add(nachField,c);
		label = new JLabel("Hier kommt der DatePicker rein");
		c.gridy = 6;
		azubiPanel.add(label,c);
		ButtonGroup btgr = new ButtonGroup();
		JRadioButton rbMann = new JRadioButton("Männlich");
		JRadioButton rbFrau = new JRadioButton("Weiblich");
		btgr.add(rbMann);
		btgr.add(rbFrau);
		JPanel rbPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
		rbPanel.add(rbFrau);
		rbPanel.add(rbMann);
		c.gridy = 7;
		c.gridwidth = 2;
		azubiPanel.add(rbPanel,c);
		JPanel ortPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
		JTextField plzField = new JTextField(6);
		JTextField ortField = new JTextField(13);
		ortPanel.add(plzField);
		ortPanel.add(new JLabel(" / "));
		ortPanel.add(ortField);
		c.gridx = 2;
		c.gridy = 8;
		azubiPanel.add(ortPanel,c);
		
		JTextField strField = new JTextField(20);
		c.gridx = 2;
		c.gridy = 9;
		c.gridwidth = 2;
		azubiPanel.add(strField,c);
		JTextField tNummerField = new JTextField(20);
		c.gridy = 10;
		azubiPanel.add(tNummerField,c);
		JTextField eMailField = new JTextField(20);
		c.gridy = 11;
		azubiPanel.add(eMailField,c);
		JButton editButton = createButton("Ändern", 100, 25);
		c.gridy = 12;
		c.gridx = 1;
		c.gridwidth = 1;
		azubiPanel.add(editButton,c);
		JButton delButton = createButton("Löschen", 100, 25);
		c.gridx = 2;
		azubiPanel.add(delButton,c);
		JButton neuButton = createButton("Neu", 100, 25);
		c.gridx = 3;
		azubiPanel.add(neuButton,c);
		JButton exitButton = createButton("Schließen", 100, 25);
		c.gridx = 0;
		azubiPanel.add(exitButton,c);
		
		
		
		
	}
	
	private void createMenuePanel(){
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
		menuePanel.add(userPanel,c);
		JButton azubiButton = createButton("Azubiverwaltung", 175, 25);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		menuePanel.add(azubiButton,c);
		JButton ausbilderButton = createButton("Ausbilderverwaltung", 175, 25);
		c.gridx = 2;
		menuePanel.add(ausbilderButton,c);
		JButton regButton = createButton("Registrieren", 175, 25);
		c.gridx = 1;
		c.gridy = 2;
		menuePanel.add(regButton,c);
		JButton betriebButton = createButton("Betriebsverwaltung", 175, 25);
		c.gridx = 0;
		c.gridy = 3;
		menuePanel.add(betriebButton,c);
		JButton zeugnisButton = createButton("Zeugnisverwaltung", 175, 25);
		c.gridx = 2;
		c.gridy = 3;
		menuePanel.add(zeugnisButton,c);

	}
	
	
	private void createLoginPanel(){
		loginPanel = new JPanel();
		loginPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20, 20, 20, 20);
		JLabel jlabel = new JLabel("Login");
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		loginPanel.add(jlabel,c);
		jlabel = new JLabel("Benutzername");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		loginPanel.add(jlabel,c);
		jlabel = new JLabel("Passwort");
		c.gridx = 0;
		c.gridy = 2;
		loginPanel.add(jlabel,c);
		JTextField lognameFeld = new JTextField(15);
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		loginPanel.add(lognameFeld,c);
		JPasswordField passwordField = new JPasswordField(15);
		c.gridx = 3;
		c.gridy = 2;
		loginPanel.add(passwordField,c);
		JButton loginButton = createButton("Login", 175, 25);
		//**************************************************
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO getZugangsStufe
				zugangsStufe = 0;
				setShownMenue();
			}
		});
		//**************************************************
		c.gridx = 0;
		c.gridy = 3;
		loginPanel.add(loginButton,c);
		JButton abbruchButton = createButton("Abbrechen", 175, 25);
		c.gridx = 3;
		c.gridy = 3;
		loginPanel.add(abbruchButton,c);
	}
	private JButton createButton(String text,int wight,int height){
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(wight, height));
		return button;
	}
}
