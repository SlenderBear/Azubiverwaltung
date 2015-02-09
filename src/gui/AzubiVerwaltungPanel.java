package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdatepicker.DateModel;
import org.jdatepicker.impl.JDatePickerImpl;

import businesslogik.dataprovider.StandardDataProvider;

import com.toedter.calendar.JYearChooser;

import objects.Ausbilder;
import objects.Azubi;
import objects.Klasse;
/**
 * Klasse AzubiVerwaltungPanel
 * erweiter JPanel
 * Erstellt den JPanel zur Verwaltung von Azubi-Daten
 * @author Maksim Imaev
 *
 */
public class AzubiVerwaltungPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] fRichtungen = { "Anwendungsentwickler", "Systemintegrator" };
	private String[] lSFormStrings = { "Hauptschule", "Realschule",
			"Gymnasium", "Gesamtschule", "Berufskoleg", "Foerderschule",
			"sonstige" };
	private String[] lAbsStrings = { "HS9", "H10A", "FOR", "FOR Q", "FHR",
			"AHR", "sonstiger" };
	private String[] konfessStrings = { "roemisch katholisch",
			"evangelisch-reformierte", "griechisch-orthodox", "islamisch",
			"alevitisch", "juedisch", "ohne", "sonstige" };
	private GUITools tools;
	private ArrayList<Klasse> klasseList;
	private ArrayList<Ausbilder> ausbilderList;
	private ArrayList<Azubi> azubiList;
	private StandardDataProvider sdp;
	private int zugangsStufe;
	public AzubiVerwaltungPanel(int zugangsStufe, StandardDataProvider sdp, ArrayList<Klasse> klasseList,
			ArrayList<Ausbilder> ausbilderList,ArrayList<Azubi> azubiList, GUITools tools) {
		this.zugangsStufe = zugangsStufe;
		this.sdp = sdp;
		this.klasseList = klasseList;
		this.ausbilderList = ausbilderList;
		this.tools = tools;
		this.azubiList = azubiList;
		
		innerAzubiPanel = new JTabbedPane();
		innerAzubiPanelStamm = new JPanel(new GridBagLayout());
		innerAzubiPanelZusatz = new JPanel(new GridBagLayout());
		rbGeschlechtPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,
				0, 0));
		rbVolljahrPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,
				0, 0));
		rbInklusionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,
				0, 0));
		ortPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		zuzugPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		vorField = new JTextField(20);
		nachField = new JTextField(20);
		gebNameField = new JTextField(20);
		plzField = new JTextField(6);
		ortField = new JTextField(13);
		gebOrtField = new JTextField(20);
		gebLandField = new JTextField(20);
		gebLandVaterField = new JTextField(20);
		gebLandMutterField = new JTextField(20);
		strField = new JTextField(20);
		tNummerField = new JTextField(20);
		hNummerField = new JTextField(20);
		eMailField = new JTextField(20);
		staatAng1Field = new JTextField(20);
		staatAng2Field = new JTextField(20);
		fehlTageField = new JTextField(20);

		sonstReliField = new JTextField(20);
		sonstAbschlusField = new JTextField(20);

		sonstSchulformField = new JTextField(20);
		anmerkField = new JTextField(40);

		dpGebTag = tools.createNewDatePicker();
		dpAusBeg = tools.createNewDatePicker();
		dpAusEnde = tools.createNewDatePicker();
		rbAdult = new JRadioButton("Ja");
		rbTeen = new JRadioButton("Nein");
		rbMann = new JRadioButton("Maennlich");
		rbFrau = new JRadioButton("Weiblich");
		rbBraucht = new JRadioButton("Ja");
		rbNBraucht = new JRadioButton("Nein");

		zuzugYC = new JYearChooser();
		ausbYC = new JYearChooser();
		dcbAusbilder = new DefaultComboBoxModel(ausbilderList.toArray());
		dcbmKlasse = new DefaultComboBoxModel(klasseList.toArray());
		cmbAusbilder = new JComboBox(dcbAusbilder);
		cmbFachrichtung = new JComboBox(fRichtungen);
		cmbletzteSchule = new JComboBox(lSFormStrings);
		cmbletzterAbschluss = new JComboBox(lAbsStrings);
		cmbKonfession = new JComboBox(konfessStrings);
		cmbKlasse = new JComboBox(dcbmKlasse);
		azubiListModel = new DefaultListModel();
		azubiJList = new JList(azubiListModel);
		azubiScrollPane = new JScrollPane(azubiJList);
		cbZuzug = new JCheckBox("Zugezogen?");
		selectedAzubi = null;
		initialize();

	}
	
	private JTabbedPane innerAzubiPanel;
	private JPanel innerAzubiPanelStamm,innerAzubiPanelZusatz,rbGeschlechtPanel,rbVolljahrPanel,rbInklusionPanel,ortPanel,buttonPanel,zuzugPanel;

	private JTextField vorField, nachField, gebNameField, plzField, ortField,
			gebOrtField, gebLandField, gebLandVaterField, gebLandMutterField,
			strField, tNummerField, hNummerField, eMailField, staatAng1Field,
			staatAng2Field, fehlTageField, sonstReliField,
			sonstAbschlusField, sonstSchulformField, anmerkField;

	private JDatePickerImpl dpGebTag,dpAusBeg,dpAusEnde;
	
	private JRadioButton rbAdult,rbTeen,rbMann,rbFrau,rbBraucht,rbNBraucht;

	private JYearChooser zuzugYC,ausbYC;
	
	private DefaultComboBoxModel dcbAusbilder,dcbmKlasse;
	
	private JComboBox cmbAusbilder,cmbFachrichtung,cmbletzteSchule,cmbletzterAbschluss,cmbKonfession,cmbKlasse;
	
	private DefaultListModel azubiListModel;
	
	private JList azubiJList;
	
	private JScrollPane azubiScrollPane;
	
	private JCheckBox cbZuzug;
	
	private Azubi selectedAzubi;

	/**
	 * Methode initialize
	 * erstellt den JPanel
	 */
	private void initialize() {
		this.setLayout(new BorderLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		setDefaultLand();

		sonstReliField.setEnabled(false);
		sonstAbschlusField.setEnabled(false);
		sonstSchulformField.setEnabled(false);

		ButtonGroup btgrGeschl = new ButtonGroup();

		btgrGeschl.add(rbMann);
		btgrGeschl.add(rbFrau);

		rbGeschlechtPanel.add(rbFrau);
		rbGeschlechtPanel.add(rbMann);

		ButtonGroup btgrVolljahr = new ButtonGroup();
		
		zuzugYC.setEnabled(false);
		ausbYC.setYear(0);
		ausbYC.setPreferredSize(new Dimension(150, 25));

		rbAdult.setEnabled(false);
		rbTeen.setSelected(true);
		rbTeen.setEnabled(false);

		btgrVolljahr.add(rbAdult);
		btgrVolljahr.add(rbTeen);

		rbVolljahrPanel.add(rbAdult);
		rbVolljahrPanel.add(rbTeen);

		ButtonGroup btgrInklusion = new ButtonGroup();

		btgrInklusion.add(rbBraucht);
		btgrInklusion.add(rbNBraucht);

		rbInklusionPanel.add(rbBraucht);
		rbInklusionPanel.add(rbNBraucht);
		rbInklusionPanel.setPreferredSize(new Dimension(150, 20));

		ortPanel.add(plzField);
		ortPanel.add(new JLabel(" / "));
		ortPanel.add(ortField);
		
		zuzugPanel.add(cbZuzug);
		zuzugPanel.add(zuzugYC);
		//
		cmbAusbilder.setPreferredSize(new Dimension(200, 20));
		//
		cmbFachrichtung.setPreferredSize(new Dimension(200, 20));
		//
		cmbletzteSchule.setPreferredSize(new Dimension(200, 20));
		//
		cmbletzterAbschluss.setPreferredSize(new Dimension(200, 20));
		//
		cmbKonfession.setPreferredSize(new Dimension(200, 20));
		//
		cmbKlasse.setPreferredSize(new Dimension(200, 20));
		//
		azubiScrollPane.setPreferredSize(new Dimension(200, 250));
		//
		JButton addButton = tools.createButton("Erstellen", 150, 25);
		JButton editButton = tools.createButton("Aendern", 150, 25);
		JButton eraseButton = tools.createButton("Loeschen", 150, 25);
		if(zugangsStufe < 2)addButton.setEnabled(false);
		if(zugangsStufe < 2)eraseButton.setEnabled(false);
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);
		
		if(zugangsStufe < 2)addButton.setEnabled(false);
		if(zugangsStufe < 2)eraseButton.setEnabled(false);

		innerAzubiPanel.addTab("Stammdaten", innerAzubiPanelStamm);
		innerAzubiPanel.addTab("Ergaenzung", innerAzubiPanelZusatz);
		this.add(tools.createTitlePanel("Ausbilderverwaltung"),
				BorderLayout.NORTH);
		this.add(innerAzubiPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		// ////************/////////

		c.gridx = 0;
		c.gridy = 0;
		innerAzubiPanelStamm.add(
				tools.createTiteledPanel("Fachrichtung", cmbFachrichtung), c);
		tools.addComponentNextLine(innerAzubiPanelStamm,
				tools.createTiteledPanel("Klassenwahl", cmbKlasse), c);
		c.gridheight = 5;
		tools.addComponentNextLine(innerAzubiPanelStamm,
				tools.createTiteledPanel("Azubis", azubiScrollPane), c);
		// ////************/////////
		// ////************/////////
		c.gridy = 0;
		c.gridx = 1;
		c.gridheight = 1;
		innerAzubiPanelStamm
				.add(tools.createTiteledPanel("Name", nachField), c);
		tools.addComponentNextLine(innerAzubiPanelStamm,
				tools.createTiteledPanel("Telefon", tNummerField), c);
		tools.addComponentNextLine(innerAzubiPanelStamm,
				tools.createTiteledPanel("Geburtsdatum", dpGebTag), c);
		tools.addComponentNextLine(innerAzubiPanelStamm,
				tools.createTiteledPanel("Strasse / HausNr", strField), c);
		tools.addComponentNextLine(innerAzubiPanelStamm,
				tools.createTiteledPanel("Volljaehrig", rbVolljahrPanel), c);

		// ////************/////////
		c.gridy = 0;
		c.gridx = 2;
		innerAzubiPanelStamm.add(tools.createTiteledPanel("Vorname", vorField),
				c);
		tools.addComponentNextLine(innerAzubiPanelStamm,
				tools.createTiteledPanel("Mobil", hNummerField), c);
		tools.addComponentNextLine(innerAzubiPanelStamm,
				tools.createTiteledPanel("Geburtsort", gebOrtField), c);
		tools.addComponentNextLine(innerAzubiPanelStamm,
				tools.createTiteledPanel("PLZ / Ort", ortPanel), c);
		tools.addComponentNextLine(innerAzubiPanelStamm,
				tools.createTiteledPanel("Geschlecht", rbGeschlechtPanel), c);

		// ////************/////////
		c.gridy = 0;
		c.gridx = 3;
		innerAzubiPanelStamm.add(
				tools.createTiteledPanel("Geburtsname", gebNameField), c);
		tools.addComponentNextLine(innerAzubiPanelStamm,
				tools.createTiteledPanel("E-Mail", eMailField), c);
		tools.addComponentNextLine(innerAzubiPanelStamm,
				tools.createTiteledPanel("Geburtsland", gebLandField), c);
		tools.addComponentNextLine(innerAzubiPanelStamm,
				tools.createTiteledPanel("AusbildungsJahr", ausbYC), c);
		tools.addComponentNextLine(innerAzubiPanelStamm, tools
				.createTiteledPanel("Inklusionsberatung", rbInklusionPanel), c);
		// ////************/////////
		tools.setConstraintsDefault(c);
		c.gridx = 0;
		c.gridy = -1;
		tools.addComponentNextLine(innerAzubiPanelZusatz, tools
				.createTiteledPanel("1.Staatsangehoerigkeit", staatAng1Field), c);
		tools.addComponentNextLine(innerAzubiPanelZusatz, tools
				.createTiteledPanel("2.Staatsangehoerigkeit", staatAng2Field), c);
		tools.addComponentNextLine(innerAzubiPanelZusatz, tools
				.createTiteledPanel("Geburtsland Vater", gebLandVaterField), c);
		tools.addComponentNextLine(innerAzubiPanelZusatz, tools
				.createTiteledPanel("Geburtsland Mutter", gebLandMutterField),
				c);
		tools.addComponentNextLine(innerAzubiPanelZusatz,
				tools.createTiteledPanel("Zuzugsjahr", zuzugPanel), c);
		tools.addComponentNextLine(innerAzubiPanelZusatz,
				tools.createTiteledPanel("Konfession", cmbKonfession), c);
		c.gridx = 1;
		c.gridy = -1;
		tools.addComponentNextLine(innerAzubiPanelZusatz,
				tools.createTiteledPanel("Letzte Schulform", cmbletzteSchule),
				c);
		tools.addComponentNextLine(innerAzubiPanelZusatz,
				tools.createTiteledPanel("sonstige Schulform:",
						sonstSchulformField), c);
		c.gridwidth = 2;
		tools.addComponentNextLine(
				innerAzubiPanelZusatz,
				tools.createTiteledPanel("Anmerkungen Ausbildung", anmerkField),
				c);
		c.gridwidth = 1;
		tools.addComponentNextLine(innerAzubiPanelZusatz,
				tools.createTiteledPanel("Ausbildungsbegin", dpAusBeg), c);
		c.gridy++;
		tools.addComponentNextLine(innerAzubiPanelZusatz,
				tools.createTiteledPanel("sonstige Religion:", sonstReliField),
				c);
		c.gridx = 2;
		c.gridy = -1;

		tools.addComponentNextLine(
				innerAzubiPanelZusatz,
				tools.createTiteledPanel("Schulabschluss", cmbletzterAbschluss),
				c);
		tools.addComponentNextLine(innerAzubiPanelZusatz, tools
				.createTiteledPanel("sonstiger Schulabschluss",
						sonstAbschlusField), c);
		c.gridy++;
		tools.addComponentNextLine(innerAzubiPanelZusatz,
				tools.createTiteledPanel("Ausbildungsende", dpAusEnde), c);
		tools.addComponentNextLine(innerAzubiPanelZusatz,
				tools.createTiteledPanel("Ausbilder", cmbAusbilder), c);
		tools.addComponentNextLine(innerAzubiPanelZusatz,
				tools.createTiteledPanel("FehlTage", fehlTageField), c);
		// ////************/////////
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
				if (tools.checkVolljahr(gebModel.getDay(), gebModel.getMonth(),
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

					if (tools.checkTFNamenLength(1, 50, vorField, nachField,
							gebNameField)
							&& tools.checkTFNamenLength(1, 30, ortField,
									gebLandField, gebLandMutterField,
									gebLandVaterField, gebOrtField,
									staatAng1Field, staatAng2Field)
							&& tools.validate(eMailField.getText())) {
						if (tools.checkTFZahlen(15, tNummerField, hNummerField)) {
							Azubi newAzubi = new Azubi();
							changeAzubi(newAzubi);
							azubiListModel.addElement(newAzubi);

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
				if(azubiJList.getSelectedIndex() != -1 && selectedAzubi != null){
				
					if (tools.checkTFNamenLength(0, 50, vorField, nachField,
							gebNameField)
							&& tools.checkTFNamenLength(0, 30, ortField,
									gebLandField, gebLandMutterField,
									gebLandVaterField, gebOrtField,
									staatAng1Field, staatAng2Field)
							&& tools.validate(eMailField.getText())) {
						if (tools.checkTFZahlen(15, tNummerField, hNummerField)) {
							changeAzubi(selectedAzubi);
							azubiJList.setModel(azubiListModel);

						}
						else{
							System.out.println("Somethings wrong with Numbers");
						}
					} else {
						System.out.println("Somethings wrong with Words");
					}

				

			}else {
					System.out.println("Somethings wrong with Select");
				}
			}
		});
		//
		eraseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(azubiJList.getSelectedIndex() != -1 && selectedAzubi != null){
				selectedAzubi = null;
				azubiListModel.remove(azubiJList.getSelectedIndex());
				}
			}
		});
		//
		cbZuzug.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				zuzugYC.setEnabled(cbZuzug.isSelected());
				
			}
		});
		//
		azubiJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(azubiJList.getSelectedIndex() != -1){
					selectedAzubi = (Azubi) azubiListModel.getElementAt(azubiJList.getSelectedIndex());
					if(selectedAzubi != null){
						setFieldsOfAzubi();
					}
					
				}
			}

			
		});
		//
		cmbKlasse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fillAzubi((Klasse)dcbmKlasse.getElementAt(cmbKlasse.getSelectedIndex()));
				
			}
		});
	}
	
	/**
	 * Methode setFieldsOfAzubi()
	 * fuellt die Felder mit den daten von dem Ausgewaehltem Azubi
	 */
	private void setFieldsOfAzubi() {
		vorField.setText(selectedAzubi.getVorname());
		nachField.setText(selectedAzubi.getName());
		gebNameField.setText(selectedAzubi.getGeburtsname());
		if(selectedAzubi.getFachrichtung() == 'A'){
			cmbFachrichtung.setSelectedItem(fRichtungen[0]);
			
		}else{
			cmbFachrichtung.setSelectedItem(fRichtungen[1]);
		}
		tNummerField.setText(selectedAzubi.getTelefon());
		hNummerField.setText(selectedAzubi.getMobiltelefon());
		eMailField.setText(selectedAzubi.getEmail());
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
		Date date;
		try {
			date = format.parse(selectedAzubi.getGeburtsdatum());
			dpGebTag.getModel().setDay(date.getDay());
			dpGebTag.getModel().setMonth(date.getMonth());
			dpGebTag.getModel().setYear(date.getYear()+1900);
			dpGebTag.getModel().setSelected(true);
			date = format.parse(selectedAzubi.getAusbildungsbeginn());
			dpAusBeg.getModel().setDay(date.getDay());
			dpAusBeg.getModel().setMonth(date.getMonth());
			dpAusBeg.getModel().setYear(date.getYear()+1900);
			dpAusBeg.getModel().setSelected(true);
			if(selectedAzubi.getAusbildungsende() != null){
				date = format.parse(selectedAzubi.getAusbildungsende());
				dpAusEnde.getModel().setDay(date.getDay());
				dpAusEnde.getModel().setMonth(date.getMonth());
				dpAusEnde.getModel().setYear(date.getYear()+1900);
				dpAusEnde.getModel().setSelected(true);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gebOrtField.setText(selectedAzubi.getGeburtsort());
		gebLandField.setText(selectedAzubi.getGeburtsland());
		strField.setText(selectedAzubi.getStrasse());
		plzField.setText(selectedAzubi.getPlz());
		ortField.setText(selectedAzubi.getOrt());
		ausbYC.setYear(selectedAzubi.getLehrjahr());
		if(selectedAzubi.getVolljaehrigkeit() == 'j'){
			rbAdult.setSelected(true);
		}
		else{
			rbTeen.setSelected(true);
		}
		if(selectedAzubi.getGeschlecht() == 'm'){
			rbMann.setSelected(true);
		}else{
			rbFrau.setSelected(true);
		}
		if(selectedAzubi.getInklusionsberatung() == 'j'){
			rbBraucht.setSelected(true);
		}else{
			rbNBraucht.setSelected(true);
		}
		staatAng1Field.setText(selectedAzubi.getStaatsangehoerigkeit_1());
		staatAng2Field.setText(selectedAzubi.getStaatsangehoerigkeit_2());
		gebLandVaterField.setText(selectedAzubi.getGeburtsland_Vater());
		gebLandMutterField.setText(selectedAzubi.getGeburtsland_Mutter());

		if(selectedAzubi.getZuzugsjahr() != 0){
			cbZuzug.setSelected(true);
			zuzugYC.setEnabled(true);
			zuzugYC.setYear(selectedAzubi.getZuzugsjahr());
		}
		
		if(tools.checkStringInArray(selectedAzubi.getKonfession(), konfessStrings)){
			cmbKonfession.setSelectedItem(selectedAzubi.getKonfession());
		}
		else{
			cmbKonfession.setSelectedIndex(konfessStrings.length-1);
			sonstReliField.setText(selectedAzubi.getKonfession());
		}
		
		if(tools.checkStringInArray(selectedAzubi.getLetzte_Schulform(), lSFormStrings)){
			cmbletzteSchule.setSelectedItem(selectedAzubi.getLetzte_Schulform());
		}
		else{
			cmbletzteSchule.setSelectedIndex(lSFormStrings.length-1);
			sonstSchulformField.setText(selectedAzubi.getLetzte_Schulform());
		}
		
		if(tools.checkStringInArray(selectedAzubi.getSchulabschluss(), lAbsStrings)){
			cmbletzterAbschluss.setSelectedItem(selectedAzubi.getSchulabschluss());
		}
		else{
			cmbletzterAbschluss.setSelectedIndex(lAbsStrings.length-1);
			sonstAbschlusField.setText(selectedAzubi.getSchulabschluss());
		}
		
		anmerkField.setText(selectedAzubi.getAnmerkung_Schulabschluss());
		fehlTageField.setText(""+selectedAzubi.getFehltage());
		cmbAusbilder.setSelectedItem(selectedAzubi.getAusbilder());
		
	}
	
	/**
	 * Methode changeAzubi
	 * andert den uebergebenen Azubi
	 * entsprechend der Feldbelegung
	 * @param selectedAzubi
	 */
	private void changeAzubi(Azubi selectedAzubi) {
		selectedAzubi.setVorname(vorField.getText());
		selectedAzubi.setName(nachField.getText());
		selectedAzubi.setOrt(ortField.getText());
		selectedAzubi.setGeburtsort(gebOrtField.getText());
		selectedAzubi.setGeburtsland(gebLandField.getText());
		selectedAzubi.setStrasse(strField.getText());
		selectedAzubi.setPlz(plzField.getText());
		selectedAzubi.setLehrjahr(ausbYC.getYear());
		if (dpAusEnde.getModel().getValue() != null) {
			DateModel<?> ausEndeModel = dpAusEnde.getModel();
			selectedAzubi.setAusbildungsende(ausEndeModel.getDay() + "."
					+ ausEndeModel.getMonth() + "." + ausEndeModel.getYear());
		}
		{
			DateModel<?> ausAnfModel = dpAusBeg.getModel();
			selectedAzubi.setAusbildungsbeginn(ausAnfModel.getDay() + "."
					+ ausAnfModel.getMonth() + "." + ausAnfModel.getYear());
		}
		if (!gebNameField.getText().isEmpty()) {
			selectedAzubi.setGeburtsname(gebNameField.getText());
		}
		if (!gebLandMutterField.getText().isEmpty()) {
			selectedAzubi.setGeburtsland_Mutter(gebLandMutterField.getText());
		}
		if (!gebLandVaterField.getText().isEmpty()) {
			selectedAzubi.setGeburtsland_Vater(gebLandVaterField.getText());
		}
		selectedAzubi.setStaatsangehoerigkeit_1(staatAng1Field.getText());
		if (!staatAng2Field.getText().isEmpty()) {
			selectedAzubi.setStaatsangehoerigkeit_2(staatAng2Field.getText());
		}

		selectedAzubi.setTelefon(tNummerField.getText());
		selectedAzubi.setMobiltelefon(hNummerField.getText());
		if (!anmerkField.getText().isEmpty()) {
			selectedAzubi.setAnmerkung_Schulabschluss(anmerkField.getText());
		}
		selectedAzubi.setEmail(eMailField.getText());
		selectedAzubi.setFehltage(Integer.parseInt(fehlTageField.getText()));
		if (!sonstReliField.isEnabled()) {
			selectedAzubi.setKonfession((String) cmbKonfession
					.getSelectedItem());
		} else {
			selectedAzubi.setKonfession(sonstReliField.getText());
		}
		if (!sonstAbschlusField.isEnabled()) {
			selectedAzubi.setSchulabschluss((String) cmbletzterAbschluss
					.getSelectedItem());
		} else {
			selectedAzubi.setSchulabschluss(sonstAbschlusField.getText());
		}
		if (!sonstSchulformField.isEnabled()) {
			selectedAzubi.setLetzte_Schulform((String) cmbletzteSchule
					.getSelectedItem());
		} else {
			selectedAzubi.setLetzte_Schulform(sonstSchulformField.getText());
		}
		if (cbZuzug.isSelected()) {

			selectedAzubi.setZuzugsjahr(zuzugYC.getValue());
		}
		if (cmbFachrichtung.getSelectedIndex() == 0) {
			selectedAzubi.setFachrichtung('a');
		} else {
			selectedAzubi.setFachrichtung('s');
		}
		if (rbAdult.isSelected()) {
			selectedAzubi.setVolljaehrigkeit('j');
		} else {
			selectedAzubi.setVolljaehrigkeit('n');
		}
		if (rbBraucht.isSelected()) {
			selectedAzubi.setInklusionsberatung('j');
		} else {
			selectedAzubi.setInklusionsberatung('n');
		}
		if (rbMann.isSelected()) {
			selectedAzubi.setGeschlecht('m');
		} else {
			selectedAzubi.setGeschlecht('f');

		}
		if (cmbAusbilder.getSelectedIndex() > -1) {
			selectedAzubi.setAusbilder((Ausbilder) dcbAusbilder
					.getElementAt(cmbAusbilder.getSelectedIndex()));
		}
		if (cmbKlasse.getSelectedIndex() > -1) {
			selectedAzubi.setKlasse((Klasse) dcbmKlasse.getElementAt(cmbKlasse
					.getSelectedIndex()));
		}

	}
	
	/**
	 * Methode setDefaultLand()
	 * setzt JTextFields auf "default"
	 */
	private void setDefaultLand(){
		gebLandField.setText("Deutschland");
		staatAng1Field.setText("deutsch");
	}
	
	
	/**
	 * Methode fillAzubi
	 * fuellt das DefaultListModel mit den Azubis aus der Klasse
	 * @param klasse
	 */
	private void fillAzubi(Klasse klasse){
		azubiJList.setSelectedIndex(-1);
		azubiListModel.removeAllElements();
		azubiList = sdp.gibAzubiVon(klasse);
		for(int i = 0; i < azubiList.size(); i++){
			azubiListModel.addElement(azubiList.get(i));
		}
	}

}
