package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainWindow {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JPanel shownPanel,loginPanel,menuePanel;
	private int zugangsStufe;
	
	public void initialize(){
		mainFrame = new JFrame("HHBK Azubiverwaltung");
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setSize(500, 500);
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
		setShownMenue();
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
	
	private void createMenuePanel(){
		menuePanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20, 20, 20, 20);
		
		JLabel jlabel = new JLabel("Benutzer");
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		menuePanel.add(jlabel,c);
		JLabel benutzerLabel = new JLabel("Benutzername");
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		menuePanel.add(benutzerLabel,c);
		JButton azubiButton = new JButton("Azubiverwaltung");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		menuePanel.add(azubiButton,c);
		JButton ausbilderButton = new JButton("Ausbilderverwaltung");
		c.gridx = 2;
		menuePanel.add(ausbilderButton,c);
		JButton regButton = new JButton("Registrieren");
		c.gridx = 1;
		c.gridy = 2;
		menuePanel.add(regButton,c);
		JButton betriebButton = new JButton("Betriebeverwaltung");
		c.gridx = 0;
		c.gridy = 3;
		menuePanel.add(betriebButton,c);
		JButton zeugnisButton = new JButton("Zeugnisverwaltung");
		c.gridx = 2;
		c.gridy = 3;
		menuePanel.add(zeugnisButton,c);

	}
	
	private void createLoginPanel(){
		loginPanel = new JPanel();
		loginPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel jlabel = new JLabel("Login");
		c.insets = new Insets(20, 20, 20, 20);
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
		JButton loginButton = new JButton("Login");
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
		loginButton.setPreferredSize(new Dimension(150, 25));
		c.gridx = 0;
		c.gridy = 3;
		loginPanel.add(loginButton,c);
		JButton abbruchButton = new JButton("Abbrechen");
		abbruchButton.setPreferredSize(new Dimension(150, 25));
		c.gridx = 3;
		c.gridy = 3;
		loginPanel.add(abbruchButton,c);
	}
}
