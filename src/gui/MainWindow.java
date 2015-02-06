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

import com.sun.media.sound.ModelAbstractChannelMixer;

import datenbank.provider.StandardDataProvider;

public class MainWindow {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JPanel loginPanel, azubiPanel, userPanel, ausbilderPanel,
			registerPanel, betriebsPanel, klassenPanel, zeugnisPanel;
	private JTabbedPane menuePanel;
	private int zugangsStufe;
	private String[] tHeads = { "Azubi", "Note" };
	private String[] fRichtungen = { "Fachinformatiker", "Systemintegrator" };
	private String[] lSFormStrings = { "Hauptschule", "Realschule",
			"Gymnasium", "Gesamtschule", "Berufskoleg", "Förderschule",
			"sonstige" };
	private String[] lAbsStrings = { "HS9", "H10A", "FOR", "FOR Q", "FHR",
			"AHR", "sonstiger" };
	private String[] konfessStrings = { "römisch katholisch",
			"evangelisch-reformierte", "griechisch-orthodox", "islamisch",
			"alevitisch", "jüdisch", "jüdisch", "ohne", "sonstige" };
	private Properties p;
	private GridBagConstraints c;
	private StandardDataProvider sdp;
	
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public MainWindow(StandardDataProvider sdp) {
		c = new GridBagConstraints();
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		this.sdp = sdp;
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
		zeugnisPanel = new JPanel(new BorderLayout());
		JPanel innerZeugnisPanel = new JPanel(new GridBagLayout());
		JPanel druckPanel = new JPanel(new GridLayout(0, 1, 0, 25));
		setConstraintsDefault();
		// //////////////////////////////////////////////////////////
		Calendar now = Calendar.getInstance();
		int jahr = now.get(Calendar.YEAR);
		Vector<Integer> years = new Vector<Integer>();
		for (int i = 0; i < 5; i++) {
			years.add(jahr - i);
		}

		JComboBox cmbKlasse = new JComboBox();
		cmbKlasse.setPreferredSize(new Dimension(200, 25));
		JComboBox cmbJahrBox = new JComboBox(years);
		cmbJahrBox.setPreferredSize(new Dimension(150, 25));

		JList azubiList = new JList();
		JScrollPane azubiScrollPane = new JScrollPane(azubiList);
		azubiScrollPane.setPreferredSize(new Dimension(200, 150));

		DefaultTableModel dtmNoten = new MyTable(8, 2);
		dtmNoten.setColumnIdentifiers(tHeads);

		JTable notenTable = new JTable(dtmNoten);

		JScrollPane tableScrollPane = new JScrollPane(notenTable);
		tableScrollPane.setPreferredSize(new Dimension(250, 148));

		JDatePickerImpl dpZeug = createNewDatePicker();

		JButton btZeugDruck = createButton("Schüler", 150, 25);
		JButton btZeugKlasseDruck = createButton("Gesamte Klasse", 150, 25);
		// //////////////////////////////////////////////////////////
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 1;
		innerZeugnisPanel.add(createTiteledPanel("Klassenwahl", cmbKlasse), c);
		c.gridheight = 3;
		addComponentNextLine(innerZeugnisPanel,
				createTiteledPanel("Faecher", azubiScrollPane));
		// ***************************************//
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		innerZeugnisPanel.add(createTiteledPanel("Jahr", cmbJahrBox), c);
		c.gridheight = 3;
		addComponentNextLine(innerZeugnisPanel,
				createTiteledPanel("Noten", tableScrollPane));
		// ***************************************//

		c.gridx = 2;
		c.gridheight = 1;
		c.gridy = 0;
		innerZeugnisPanel.add(
				createTiteledPanel("Zeugniskonferenz am:", dpZeug), c);

		JLabel label = new JLabel("Zeugnis drucken für:");

		c.gridy++;
		innerZeugnisPanel.add(label, c);

		druckPanel.add(btZeugDruck);
		druckPanel.add(btZeugKlasseDruck);

		c.gridy++;
		innerZeugnisPanel.add(druckPanel, c);

		zeugnisPanel.add(createTitlePanel("Zeugnisverwaltung"),
				BorderLayout.NORTH);
		zeugnisPanel.add(innerZeugnisPanel, BorderLayout.CENTER);

	}

	private void createKlassenVerwaltung() {
		klassenPanel = new JPanel(new BorderLayout());
		JPanel innerKlassenPanel = new JPanel(new GridBagLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setConstraintsDefault();
		// //////////////////////////////////////////////////////////
		JList klassenList = new JList();
		JScrollPane klassenScrollPane = new JScrollPane(klassenList);
		klassenScrollPane.setPreferredSize(new Dimension(200, 300));

		JTextField bezField = new JTextField(20);

		JComboBox cmbLehrer = new JComboBox();
		cmbLehrer.setPreferredSize(new Dimension(200, 25));

		JButton addButton = createButton("Erstellen", 150, 25);
		JButton editButton = createButton("Ändern", 150, 25);
		JButton eraseButton = createButton("Löschen", 150, 25);
		// //////////////////////////////////////////////////////////
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridheight = 3;
		innerKlassenPanel.add(createTiteledPanel("Klassen", klassenScrollPane),
				c);

		// *********************************************//

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		innerKlassenPanel.add(createTiteledPanel("Bezeichnung", bezField), c);
		addComponentNextLine(innerKlassenPanel,
				createTiteledPanel("Lehrer", cmbLehrer));

		// *********************************************//

		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);

		klassenPanel.add(createTitlePanel("Klassenverwaltung"),
				BorderLayout.NORTH);
		klassenPanel.add(innerKlassenPanel, BorderLayout.CENTER);
		klassenPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	private void createBetriebVerw() {
		betriebsPanel = new JPanel(new BorderLayout());
		JPanel innerBetriebsPanel = new JPanel(new GridBagLayout());
		JPanel ortPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setConstraintsDefault();
		// /////////////////////////////////////////////////////////////
		JTextField bezField = new JTextField(20);
		JTextField plzField = new JTextField(6);
		JTextField ortField = new JTextField(13);
		JTextField adresseField = new JTextField(20);
		JTextField teleField = new JTextField(20);
		JTextField eMailField = new JTextField(20);

		JList betriebsList = new JList();
		JScrollPane betriebsScrollPane = new JScrollPane(betriebsList);
		betriebsScrollPane.setPreferredSize(new Dimension(200, 300));

		JButton addButton = createButton("Erstellen", 150, 25);
		JButton editButton = createButton("Ändern", 150, 25);
		JButton eraseButton = createButton("Löschen", 150, 25);

		ortPanel.add(plzField);
		ortPanel.add(new JLabel(" / "));
		ortPanel.add(ortField);
		// //////////////////////////////////////////////////////////////
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridheight = 6;
		innerBetriebsPanel.add(
				createTiteledPanel("Betriebe", betriebsScrollPane), c);
		// ***********************************//
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		innerBetriebsPanel.add(createTiteledPanel("Bezeichnung", bezField), c);
		addComponentNextLine(innerBetriebsPanel,
				createTiteledPanel("PLZ / Ort", ortPanel));
		addComponentNextLine(innerBetriebsPanel,
				createTiteledPanel("Strasse / HausNr", adresseField));
		addComponentNextLine(innerBetriebsPanel,
				createTiteledPanel("Telefonnummer", teleField));
		addComponentNextLine(innerBetriebsPanel,
				createTiteledPanel("E-Mail", eMailField));
		// ***********************************//
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);
		betriebsPanel.add(createTitlePanel("Betriebeverwaltung"),
				BorderLayout.NORTH);
		betriebsPanel.add(innerBetriebsPanel, BorderLayout.CENTER);
		betriebsPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	private JPanel createTitlePanel(String title) {
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel titleLabel = new JLabel(title);
		titlePanel.add(titleLabel);
		return titlePanel;
	}

	private void createRegister() {
		registerPanel = new JPanel(new BorderLayout());
		JPanel innerRegisterPanel = new JPanel(new GridBagLayout());
		setConstraintsDefault();
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel berPanel = new JPanel(new GridLayout(0, 1));
		// *************************
		final DefaultListModel userListModel = new DefaultListModel();
		final JList userList = new JList(userListModel);
		JScrollPane userScrollPane = new JScrollPane(userList);
		userScrollPane.setPreferredSize(new Dimension(200, 350));

		final JTextField vorField = new JTextField(20);
		final JTextField nameField = new JTextField(20);
		final JTextField userField = new JTextField(20);
		final JTextField passField = new JTextField(20);
		final JTextField teleField = new JTextField(20);

		ButtonGroup btgr = new ButtonGroup();

		final JRadioButton rbLehrer = new JRadioButton("Klassenlehrer");
		final JRadioButton rbLeitung = new JRadioButton("Bereichsleitung");

		btgr.add(rbLeitung);
		btgr.add(rbLehrer);

		berPanel.add(rbLehrer);
		berPanel.add(rbLeitung);

		rbLehrer.setSelected(true);

		JButton addButton = createButton("Erstellen", 150, 25);
		JButton editButton = createButton("Ändern", 150, 25);
		JButton eraseButton = createButton("Löschen", 150, 25);
		// ***********************//
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridheight = 8;
		innerRegisterPanel.add(createTiteledPanel("Nutzer", userScrollPane), c);

		// ***********************//

		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		innerRegisterPanel.add(createTiteledPanel("Vorname", vorField), c);
		addComponentNextLine(innerRegisterPanel,
				createTiteledPanel("Name", nameField));
		addComponentNextLine(innerRegisterPanel,
				createTiteledPanel("Username", userField));
		addComponentNextLine(innerRegisterPanel,
				createTiteledPanel("Passwort", passField));
		addComponentNextLine(innerRegisterPanel,
				createTiteledPanel("Telefonnummer", teleField));
		addComponentNextLine(innerRegisterPanel,
				createTiteledPanel("Berechtigung", berPanel));
		//
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);
		registerPanel
				.add(createTitlePanel("Registrierung"), BorderLayout.NORTH);
		registerPanel.add(innerRegisterPanel, BorderLayout.CENTER);
		registerPanel.add(buttonPanel, BorderLayout.SOUTH);
		// ***************Listeners******************///

		userList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (userList.getSelectedIndex() != -1) {
					Lehrer selectedLehrer = (Lehrer) userListModel.get(userList
							.getSelectedIndex());
					Login selectedLogin = selectedLehrer.getLogin();
					Berechtigung selectedBer = selectedLogin.getBerechtigung();
					nameField.setText(selectedLehrer.getName());
					vorField.setText(selectedLehrer.getVorname());
					teleField.setText(selectedLehrer.getTelefon());
					userField.setText(selectedLogin.getLoginName());
					passField.setText(selectedLogin.getLoginPasswort());
					if (selectedBer.getID() == 1) {
						rbLehrer.setSelected(true);
					} else if (selectedBer.getID() == 2) {
						rbLeitung.setSelected(true);
					}

				}
			}
		});

		// **********************************************
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkTFNamenLength(50, nameField, vorField)
						&& checkStringPassword(passField.getText())) {
					Login login = new Login(userField.getText(), passField
							.getText());
					Berechtigung newBer = new Berechtigung();
					if (rbLehrer.isSelected())
						newBer.setID(1);
					else if (rbLeitung.isSelected())
						newBer.setID(2);
					Lehrer newLehrer = new Lehrer(nameField.getText(), vorField
							.getText(), teleField.getText(), login);
					newLehrer.getLogin().setBerechtigung(newBer);
					userListModel.addElement(newLehrer);

					clearTextFields(nameField, vorField, teleField, userField,
							passField);
				} else {
					JOptionPane.showMessageDialog(registerPanel,
							"Felder falsch gefüllt", "Fehler",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		// ****************************************************
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (userList.getSelectedIndex() != -1) {
					if (checkTFNamenLength(50, nameField, vorField)) {
						Lehrer selectedLehrer = (Lehrer) userListModel
								.get(userList.getSelectedIndex());
						selectedLehrer.setName(nameField.getText());
						selectedLehrer.setVorname(vorField.getText());
						selectedLehrer.setTelefon(teleField.getText());
						Login cLogin = selectedLehrer.getLogin();
						cLogin.setLoginName(userField.getText());
						cLogin.setLoginPasswort(passField.getText());
						Berechtigung cBer = cLogin.getBerechtigung();
						if (rbLehrer.isSelected())
							cBer.setID(1);
						else if (rbLeitung.isSelected())
							cBer.setID(2);
					} else {
						System.out.println("Fehler");
					}

				}

				clearTextFields(nameField, vorField, teleField, userField,
						passField);
				userList.setModel(userListModel);
			}
		});
		// *******************************************
		eraseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (userList.getSelectedIndex() != -1)
					userListModel.remove(userList.getSelectedIndex());
				clearTextFields(nameField, vorField, teleField, userField,
						passField);

			}
		});
		// /*******************************************////

	}

	private void clearTextFields(JTextField... textFields) {
		for (int i = 0; i < textFields.length; i++) {
			textFields[i].setText("");
		}
	}

	private boolean checkTFNamenLength(int length, JTextField... textFields) {
		boolean check = true;
		for (int i = 0; i < textFields.length; i++) {
			String toCheck = textFields[i].getText();
			if (!checkStringNamen(toCheck) || !(toCheck.length() <= length))
				check = false;
		}
		return check;
	}

	private boolean checkTFZahlen(int length, JTextField... textFields) {
		boolean check = true;
		for (int i = 0; i < textFields.length; i++) {
			String toCheck = textFields[i].getText();
			if (!checkStringZahlenLength(toCheck, length)) {
				check = false;
			}
			;
		}
		return check;
	}

	private boolean checkStringBuch(String string) {
		return Pattern.matches("[A-Za-z]+", string);
	}

	private boolean checkStringBuchZahlen(String string) {
		return Pattern.matches("[A-Za-z0-9]+", string);
	}

	private boolean checkStringPassword(String string) {
		return Pattern
				.matches(
						"((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%_]).{6,20})",
						string);
	}

	private boolean checkStringNamen(String string) {
		return Pattern.matches("([A-Za-z ]|-)+", string);
	}

	private boolean checkStringZahlenLength(String string, int length) {
		if (string.length() <= length) {
			return Pattern.matches("[0-9]*", string);
		} else
			return false;
	}

	private void createAusbilderVerwaltung() {
		ausbilderPanel = new JPanel(new BorderLayout());
		JPanel innerAusbilderPanel = new JPanel(new GridBagLayout());
		JPanel rbPGeschlechtPanel = new JPanel(new FlowLayout(
				FlowLayout.CENTER, 0, 0));
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setConstraintsDefault();
		// ************************
		JComboBox cmbBetrieb = new JComboBox();
		cmbBetrieb.setPreferredSize(new Dimension(200, 25));

		JList ausbilderList = new JList();
		JScrollPane ausbilderScrollPane = new JScrollPane(ausbilderList);
		ausbilderScrollPane.setPreferredSize(new Dimension(200, 300));

		JTextField vorField = new JTextField(20);
		JTextField nachField = new JTextField(20);
		JTextField tNummerField = new JTextField(20);
		JTextField eMailField = new JTextField(20);

		ButtonGroup btgrGeschlecht = new ButtonGroup();

		JRadioButton rbMann = new JRadioButton("Herr");
		JRadioButton rbFrau = new JRadioButton("Frau");

		btgrGeschlecht.add(rbMann);
		btgrGeschlecht.add(rbFrau);
		rbPGeschlechtPanel.add(rbFrau);
		rbPGeschlechtPanel.add(rbMann);

		JButton addButton = createButton("Erstellen", 150, 25);
		JButton editButton = createButton("Ändern", 150, 25);
		JButton eraseButton = createButton("Löschen", 150, 25);

		// ********************//
		c.gridx = 0;
		c.gridy = 0;

		innerAusbilderPanel.add(createTiteledPanel("Betriebwahl", cmbBetrieb),
				c);

		c.gridheight = 6;
		addComponentNextLine(innerAusbilderPanel,
				createTiteledPanel("Ausbilder", ausbilderScrollPane));
		// ********************//
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		innerAusbilderPanel.add(createTiteledPanel("Vorname", vorField), c);
		addComponentNextLine(innerAusbilderPanel,
				createTiteledPanel("Nachname", nachField));
		addComponentNextLine(innerAusbilderPanel,
				createTiteledPanel("Geschlecht", rbPGeschlechtPanel));
		addComponentNextLine(innerAusbilderPanel,
				createTiteledPanel("Telefon", tNummerField));
		addComponentNextLine(innerAusbilderPanel,
				createTiteledPanel("E-Mail", eMailField));
		// ********************//
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);
		ausbilderPanel.add(createTitlePanel("Ausbilderverwaltung"),
				BorderLayout.NORTH);
		ausbilderPanel.add(innerAusbilderPanel, BorderLayout.CENTER);
		ausbilderPanel.add(buttonPanel, BorderLayout.SOUTH);

	}

	private void createAzubiVerwaltung() {
		azubiPanel = new JPanel(new BorderLayout());
		JTabbedPane innerAzubiPanel = new JTabbedPane();
		JPanel innerAzubiPanelStamm = new JPanel(new GridBagLayout());
		JPanel innerAzubiPanelZusatz = new JPanel(new GridBagLayout());
		JPanel rbGeschlechtPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,
				0, 0));
		JPanel rbVolljahrPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,
				0, 0));
		JPanel rbInklusionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,
				0, 0));
		JPanel ortPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

		setConstraintsDefault();

		// ///////////////////////////////////////////////////////////
		final JTextField vorField = new JTextField(20);
		final JTextField nachField = new JTextField(20);
		final JTextField gebNameField = new JTextField(20);
		final JTextField plzField = new JTextField(6);
		final JTextField ortField = new JTextField(13);
		final JTextField gebOrtField = new JTextField(20);
		final JTextField gebLandField = new JTextField(20);
		final JTextField gebLandVaterField = new JTextField(20);
		final JTextField gebLandMutterField = new JTextField(20);
		final JTextField strField = new JTextField(20);
		final JTextField tNummerField = new JTextField(20);
		final JTextField hNummerField = new JTextField(20);
		final JTextField eMailField = new JTextField(20);
		final JTextField staatAng1Field = new JTextField(20);
		final JTextField staatAng2Field = new JTextField(20);
		final JTextField fehlTageField = new JTextField(20);
		final JTextField ausJahrField = new JTextField(20);

		final JTextField sonstReliField = new JTextField(20);
		sonstReliField.setEnabled(false);
		final JTextField sonstAbschlusField = new JTextField(20);
		sonstAbschlusField.setEnabled(false);
		final JTextField sonstSchulformField = new JTextField(20);
		sonstSchulformField.setEnabled(false);
		final JTextField anmerkField = new JTextField(40);

		final JDatePickerImpl dpGebTag = createNewDatePicker();
		final JDatePickerImpl dpAusBeg = createNewDatePicker();
		final JDatePickerImpl dpAusEnde = createNewDatePicker();
		final JDatePickerImpl dpZuzug = createNewDatePicker();

		ButtonGroup btgrGeschl = new ButtonGroup();

		final JRadioButton rbMann = new JRadioButton("Männlich");
		JRadioButton rbFrau = new JRadioButton("Weiblich");

		btgrGeschl.add(rbMann);
		btgrGeschl.add(rbFrau);

		rbGeschlechtPanel.add(rbFrau);
		rbGeschlechtPanel.add(rbMann);

		ButtonGroup btgrVolljahr = new ButtonGroup();

		final JRadioButton rbAdult = new JRadioButton("Ja");
		rbAdult.setEnabled(false);
		final JRadioButton rbTeen = new JRadioButton("Nein");
		rbTeen.setSelected(true);
		rbTeen.setEnabled(false);

		btgrVolljahr.add(rbAdult);
		btgrVolljahr.add(rbTeen);

		rbVolljahrPanel.add(rbAdult);
		rbVolljahrPanel.add(rbTeen);

		ButtonGroup btgrInklusion = new ButtonGroup();

		final JRadioButton rbBraucht = new JRadioButton("Ja");
		JRadioButton rbNBraucht = new JRadioButton("Nein");

		btgrInklusion.add(rbBraucht);
		btgrInklusion.add(rbNBraucht);

		rbInklusionPanel.add(rbBraucht);
		rbInklusionPanel.add(rbNBraucht);
		rbInklusionPanel.setPreferredSize(new Dimension(150, 20));

		ortPanel.add(plzField);
		ortPanel.add(new JLabel(" / "));
		ortPanel.add(ortField);

		ArrayList<Klasse> klasseList = new ArrayList<Klasse>();
		ArrayList<Betrieb> betriebList = new ArrayList<Betrieb>();
		ArrayList<Ausbilder> ausbilderList = new ArrayList<Ausbilder>();
		//
		final DefaultComboBoxModel dcbBetrieb = new DefaultComboBoxModel(betriebList.toArray());
		final DefaultComboBoxModel dcbAusbilder = new DefaultComboBoxModel(ausbilderList.toArray());
		final DefaultComboBoxModel dcbmKlasse = new DefaultComboBoxModel(
				klasseList.toArray());
		
		final JComboBox cmbBetrieb = new JComboBox(dcbBetrieb);
		cmbBetrieb.setPreferredSize(new Dimension(200, 20));
		//
		final JComboBox cmbAusbilder = new JComboBox(dcbAusbilder);
		cmbAusbilder.setPreferredSize(new Dimension(200, 20));
		//
		final JComboBox cmbFachrichtung = new JComboBox(fRichtungen);
		cmbFachrichtung.setPreferredSize(new Dimension(200, 20));
		//
		final JComboBox cmbletzteSchule = new JComboBox(lSFormStrings);
		cmbletzteSchule.setPreferredSize(new Dimension(200, 20));
		//
		final JComboBox cmbletzterAbschluss = new JComboBox(lAbsStrings);
		cmbletzterAbschluss.setPreferredSize(new Dimension(200, 20));
		//
		final JComboBox cmbKonfession = new JComboBox(konfessStrings);
		cmbKonfession.setPreferredSize(new Dimension(200, 20));
		//
		final JComboBox cmbKlasse = new JComboBox(dcbmKlasse);
		cmbKlasse.setPreferredSize(new Dimension(200, 20));
		//
		DefaultListModel azubiListModel = new DefaultListModel();
		JList azubiList = new JList(azubiListModel);
		JScrollPane azubiScrollPane = new JScrollPane(azubiList);
		azubiScrollPane.setPreferredSize(new Dimension(200, 250));
		//
		JButton addButton = createButton("Erstellen", 150, 25);
		JButton editButton = createButton("Ändern", 150, 25);
		JButton eraseButton = createButton("Löschen", 150, 25);
		// //////////////////////////
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);

		innerAzubiPanel.addTab("Stammdaten", innerAzubiPanelStamm);
		innerAzubiPanel.addTab("Ergänzung", innerAzubiPanelZusatz);
		azubiPanel.add(createTitlePanel("Ausbilderverwaltung"),
				BorderLayout.NORTH);
		azubiPanel.add(innerAzubiPanel, BorderLayout.CENTER);
		azubiPanel.add(buttonPanel, BorderLayout.SOUTH);
		// ////************/////////

		c.gridx = 0;
		c.gridy = 0;
		innerAzubiPanelStamm.add(
				createTiteledPanel("Fachrichtung", cmbFachrichtung), c);
		// innerAzubiPanel.add(createTiteledPanel("Klassenwahl", cmbKlasse), c);
		addComponentNextLine(innerAzubiPanelStamm,
				createTiteledPanel("Klassenwahl", cmbKlasse));
		c.gridheight = 5;
		addComponentNextLine(innerAzubiPanelStamm,
				createTiteledPanel("Azubis", azubiScrollPane));
		// ////************/////////
		// ////***StammDatenfüllen**/////////
		// ////************/////////
		c.gridy = 0;
		c.gridx = 1;
		c.gridheight = 1;
		innerAzubiPanelStamm.add(createTiteledPanel("Name", nachField), c);
		addComponentNextLine(innerAzubiPanelStamm,
				createTiteledPanel("Telefon", tNummerField));
		addComponentNextLine(innerAzubiPanelStamm,
				createTiteledPanel("Geburtsdatum", dpGebTag));
		addComponentNextLine(innerAzubiPanelStamm,
				createTiteledPanel("Straße / HausNr", strField));
		addComponentNextLine(innerAzubiPanelStamm,
				createTiteledPanel("Volljährig", rbVolljahrPanel));

		// ////************/////////
		c.gridy = 0;
		c.gridx = 2;
		innerAzubiPanelStamm.add(createTiteledPanel("Vorname", vorField), c);
		addComponentNextLine(innerAzubiPanelStamm,
				createTiteledPanel("Mobil", hNummerField));
		addComponentNextLine(innerAzubiPanelStamm,
				createTiteledPanel("Geburtsort", gebOrtField));
		addComponentNextLine(innerAzubiPanelStamm,
				createTiteledPanel("PLZ / Ort", ortPanel));
		addComponentNextLine(innerAzubiPanelStamm,
				createTiteledPanel("Geschlecht", rbGeschlechtPanel));

		// ////************/////////
		c.gridy = 0;
		c.gridx = 3;
		innerAzubiPanelStamm.add(
				createTiteledPanel("Geburtsname", gebNameField), c);
		addComponentNextLine(innerAzubiPanelStamm,
				createTiteledPanel("E-Mail", eMailField));
		addComponentNextLine(innerAzubiPanelStamm,
				createTiteledPanel("Geburtsland", gebLandField));
		addComponentNextLine(innerAzubiPanelStamm,
				createTiteledPanel("AusbildungsJahr", ausJahrField));
		addComponentNextLine(innerAzubiPanelStamm,
				createTiteledPanel("Inklusionsberatung", rbInklusionPanel));

		// ////************/////////
		// c.gridy = 0;
		// c.gridx = 4;

		// ////************/////////
		// ////****ZusatzDatenFüllen*****/////////
		setConstraintsDefault();
		c.gridx = 0;
		c.gridy = -1;
		addComponentNextLine(innerAzubiPanelZusatz,
				createTiteledPanel("1.Staatsangehörigkeit", staatAng1Field));
		addComponentNextLine(innerAzubiPanelZusatz,
				createTiteledPanel("2.Staatsangehörigkeit", staatAng2Field));
		addComponentNextLine(innerAzubiPanelZusatz,
				createTiteledPanel("Geburtsland Vater", gebLandVaterField));
		addComponentNextLine(innerAzubiPanelZusatz,
				createTiteledPanel("Geburtsland Mutter", gebLandMutterField));
		addComponentNextLine(innerAzubiPanelZusatz,
				createTiteledPanel("Zuzugsdatum", dpZuzug));
		addComponentNextLine(innerAzubiPanelZusatz,
				createTiteledPanel("Konfession", cmbKonfession));
		c.gridx = 1;
		c.gridy = -1;
		addComponentNextLine(innerAzubiPanelZusatz,
				createTiteledPanel("Letzte Schulform", cmbletzteSchule));
		addComponentNextLine(innerAzubiPanelZusatz,
				createTiteledPanel("sonstige Schulform:", sonstSchulformField));
		c.gridwidth = 2;
		addComponentNextLine(innerAzubiPanelZusatz,
				createTiteledPanel("Anmerkungen Ausbildung", anmerkField));
		c.gridwidth = 1;
		addComponentNextLine(innerAzubiPanelZusatz,
				createTiteledPanel("Ausbildungsbegin", dpAusBeg));
		addComponentNextLine(innerAzubiPanelZusatz,
				createTiteledPanel("Betrieb", cmbBetrieb));
		addComponentNextLine(innerAzubiPanelZusatz,
				createTiteledPanel("sonstige Religion:", sonstReliField));
		c.gridx = 2;
		c.gridy = -1;

		addComponentNextLine(innerAzubiPanelZusatz,
				createTiteledPanel("Schulabschluss", cmbletzterAbschluss));
		addComponentNextLine(
				innerAzubiPanelZusatz,
				createTiteledPanel("sonstiger Schulabschluß",
						sonstAbschlusField));
		c.gridy++;
		addComponentNextLine(innerAzubiPanelZusatz,
				createTiteledPanel("Ausbildungsende", dpAusEnde));
		addComponentNextLine(innerAzubiPanelZusatz,
				createTiteledPanel("Ausbilder", cmbAusbilder));
		addComponentNextLine(innerAzubiPanelZusatz,
				createTiteledPanel("FehlTage", fehlTageField));

		// ////************/////////
		// ////************/////////
		//

		cmbKonfession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbKonfession.getSelectedIndex() == konfessStrings.length - 1) {
					sonstReliField.setEnabled(true);
				} else {
					sonstReliField.setEnabled(false);
				}

			}
		});
		//
		cmbletzterAbschluss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbletzterAbschluss.getSelectedIndex() == lAbsStrings.length - 1) {
					sonstAbschlusField.setEnabled(true);
				} else {
					sonstAbschlusField.setEnabled(false);
				}

			}
		});
		//
		cmbletzteSchule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbletzteSchule.getSelectedIndex() == lSFormStrings.length - 1) {
					sonstSchulformField.setEnabled(true);
				} else {
					sonstSchulformField.setEnabled(false);
				}

			}
		});
		//
		dpGebTag.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DateModel<?> gebModel = dpGebTag.getModel();
				if (checkVolljahr(gebModel.getDay(), gebModel.getMonth(),
						gebModel.getYear())) {
					rbAdult.setSelected(true);
				} else {
					rbTeen.setSelected(true);
				}
				;
			}
		});
		//
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cmbKlasse.getSelectedIndex() != -1) {
					Azubi newAzubi = new Azubi();
					if (checkTFNamenLength(50, vorField, nachField)&&checkTFNamenLength(30, ortField, gebLandField, gebLandMutterField,
									gebLandVaterField,gebOrtField,
									staatAng1Field,
									staatAng2Field )&& validate(eMailField.getText() )) {
						if (checkTFZahlen(15, tNummerField, hNummerField)) {
							newAzubi.setVorname(vorField.getText());
							newAzubi.setName(nachField.getText());
							newAzubi.setOrt(ortField.getText());
							newAzubi.setGeburtsort(gebOrtField.getText());
							newAzubi.setGeburtsland(gebLandField.getText());
							newAzubi.setStrasse(strField.getText());
							newAzubi.setPlz(plzField.getText());
							newAzubi.setLehrjahr(Integer.parseInt(ausJahrField.getText()));
							if(dpAusEnde.getModel().getValue() != null){
								DateModel<?> ausEndeModel = dpAusEnde.getModel();
								newAzubi.setAusbildungsende(ausEndeModel.getDay()+"-"+ausEndeModel.getMonth()+"-"+ausEndeModel.getYear());
							}
							{
								DateModel<?> ausAnfModel = dpAusBeg.getModel();
								newAzubi.setAusbildungsbeginn(ausAnfModel.getDay()+"-"+ausAnfModel.getMonth()+"-"+ausAnfModel.getYear());
							}
							if(!gebNameField.getText().isEmpty()){
								newAzubi.setGeburtsname(gebNameField.getText());
							}
							if(!gebLandMutterField.getText().isEmpty()){
								newAzubi.setGeburtsland_Mutter(gebLandMutterField
									.getText());
							}
							if(!gebLandVaterField.getText().isEmpty()){
								newAzubi.setGeburtsland_Vater(gebLandVaterField
										.getText());
							}
							newAzubi.setStaatsangehoerigkeit_1(staatAng1Field
									.getText());
							if(!staatAng2Field.getText().isEmpty()){
								newAzubi.setStaatsangehoerigkeit_2(staatAng2Field
									.getText());
							}
							
							newAzubi.setTelefon(tNummerField.getText());
							newAzubi.setMobiltelefon(hNummerField.getText());
							if(!anmerkField.getText().isEmpty()){
								newAzubi.setAnmerkung_Schulabschluss(anmerkField
									.getText());
							}
							newAzubi.setEmail(eMailField.getText());
							newAzubi.setFehltage(Integer.parseInt(fehlTageField
									.getText()));
							if (!sonstReliField.isEnabled()) {
								newAzubi.setKonfession((String) cmbKonfession
										.getSelectedItem());
							} else {
								newAzubi.setKonfession(sonstReliField.getText());
							}
							if (!sonstAbschlusField.isEnabled()) {
								newAzubi.setSchulabschluss((String) cmbletzterAbschluss
										.getSelectedItem());
							} else {
								newAzubi.setSchulabschluss(sonstAbschlusField
										.getText());
							}
							if (!sonstSchulformField.isEnabled()) {
								newAzubi.setLetzte_Schulform((String) cmbletzteSchule
										.getSelectedItem());
							} else {
								newAzubi.setLetzte_Schulform(sonstSchulformField
										.getText());
							}
							if (dpZuzug.getModel().getValue() != null) {
								DateModel<?> zuzModel = dpZuzug.getModel();
								newAzubi.setZuzugsjahr(zuzModel.getYear());
							}
							if (cmbFachrichtung.getSelectedIndex() == 0) {
								newAzubi.setFachrichtung('f');
							} else {
								newAzubi.setFachrichtung('s');
							}
							if (rbAdult.isSelected()) {
								newAzubi.setVolljaehrigkeit('j');
							} else {
								newAzubi.setVolljaehrigkeit('n');
							}
							if (rbBraucht.isSelected()) {
								newAzubi.setInklusionsberatung('j');
							} else {
								newAzubi.setInklusionsberatung('n');
							}
							if (rbMann.isSelected()) {
								newAzubi.setGeschlecht('m');
							} else {
								newAzubi.setGeschlecht('f');

							}
							if(cmbBetrieb.getSelectedIndex() > -1){
								newAzubi.setBetrieb((Betrieb)dcbBetrieb.getElementAt(cmbBetrieb.getSelectedIndex()));
							}
							if(cmbAusbilder.getSelectedIndex() > -1){
								newAzubi.setAusbilder((Ausbilder)dcbAusbilder.getElementAt(cmbAusbilder.getSelectedIndex()));
							}
							if(cmbKlasse.getSelectedIndex() > -1){
								newAzubi.setKlasse((Klasse)dcbmKlasse.getElementAt(cmbKlasse.getSelectedIndex()));
							}

						}
					} else {

					}
					;
				} else {

				}

			}
		});
		//
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				if (!sonstReliField.isEnabled()) {
//					System.out.println((String) cmbKonfession.getSelectedItem());
//				} else {
//					System.out.println(sonstReliField.getText());
//				}
				System.out.println(gebLandVaterField.getText());
 			}
		});

	}
	
	private boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
	}


	private boolean checkVolljahr(int tag, int monat, int jahr) {
		Date gebTag = new Date(jahr - 1900, monat, tag);
		Date now = new Date();
		now.setYear(now.getYear() - 18);
		return gebTag.before(now);

	}

	private JPanel createTiteledPanel(String title, Component component) {
		JPanel returnPanel = new JPanel();
		// returnPanel.setPreferredSize(new Dimension(400, 200));
		returnPanel.setBorder(BorderFactory.createTitledBorder(title));
		returnPanel.add(component);
		return returnPanel;
	}

	private void createMenuePanel() {
		// addUserPanel();
		menuePanel = new JTabbedPane();
		if (zugangsStufe == 0) {
			createAzubiVerwaltung();
			menuePanel.addTab("Azubiverwaltung", azubiPanel);
		}
		if (zugangsStufe == 0) {
			createBetriebVerw();
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
			createRegister();
			menuePanel.addTab("Registrierung", registerPanel);
		}
		if (zugangsStufe == 0) {
			createZeugnisVerwaltung();
			menuePanel.addTab("Zeugnisverwaltung", zeugnisPanel);
		}

	}

	private void addUserPanel() {
		JLabel label = new JLabel("Benutzer: ");
		JButton btLogout = createButton("Logout", 150, 25);
		JButton closeButton = createButton("Schließen", 150, 25);
		userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

		userPanel.add(label);
		label = new JLabel("Hier Kommt benutzername");
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
		setConstraintsDefault();
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

	private void setConstraintsDefault() {
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
	}

	private void addComponentNextLine(JPanel parent, Component addetCom) {
		c.gridy++;
		parent.add(addetCom, c);
	}

	private JDatePickerImpl createNewDatePicker() {
		UtilDateModel model;
		JDatePanelImpl datePanel;
		DateLabelFormatter dlf;
		dlf = new DateLabelFormatter();
		model = new UtilDateModel();
		// model.setSelected(true);
		datePanel = new JDatePanelImpl(model, p);
		return new JDatePickerImpl(datePanel, dlf);
	}
}
