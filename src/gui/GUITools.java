package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

	/**
	 * Klasse GUITools
	 * stellt Methoden zur Erstellung
	 * bzw. Bedienung der GUI zur verfuegung
	 * @author Maksim Imaev
	 *
	 */
public class GUITools {

	
	private static final String PATTERN_ZAHL = "[0-9]*";
	private static final String PATTERN_NAMEN = "([A-Za-z ]|-)*";
	private static final String PATTERN_BST_ZAHL = "[A-Za-z0-9]*";
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%_]).{6,20})";
	private Font titleFont = new Font("SansSerif", Font.BOLD, 20);
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private Properties p;
	/**
	 * Constructor
	 * setzt die Properties fuer den JDatePicker
	 */
	public GUITools() {
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * methode validate
	 * @param emailStr String mit dem e-mail
	 * @return true, wenn der e-mail-String dem Pattern entspricht
	 */
	public boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
	}


	/**
	 * Methode checkVolljahr prueft, ob ein "Datum" volljaehrig ist
	 * @param tag 
	 * @param monat
	 * @param jahr
	 * @return true, wenn der geburtstag frueher 
	 * oder gleich heute - 18 Jahren ist
	 */
	public boolean checkVolljahr(int tag, int monat, int jahr) {
		Date gebTag = new Date(jahr - 1900, monat, tag);
		Date now = new Date();
		now.setYear(now.getYear() - 18);
		return gebTag.before(now);

	}

	/**
	 * Methode createTiteledPanel
	 * erstellt ein JPanel mit dem gewuenschtem Inhalt
	 * und dem Titeled Border mit dem bewuenschtem Titel 
	 * @param title String fuer den Titel des Boarder
	 * @param component inhalt des JPanels
	 * @return JPanel
	 */
	public JPanel createTiteledPanel(String title, Component component) {
		JPanel returnPanel = new JPanel();
		// returnPanel.setPreferredSize(new Dimension(400, 200));
		returnPanel.setBorder(BorderFactory.createTitledBorder(title));
		returnPanel.add(component);
		return returnPanel;
	}
	
	/**
	 * Methode createButton
	 * erstellt ein JButton mit eintsprechendem Text
	 * und von entsprechender groesse
	 * @param text
	 * @param wight
	 * @param height
	 * @return JButton
	 */
	public JButton createButton(String text, int wight, int height) {
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(wight, height));
		return button;
	}

	/**
	 * Methode setConstraintsDefault
	 * setzt uebergebene GridBagConstraints auf
	 * "Anfangszustand"
	 * @param c
	 */
	public void setConstraintsDefault(GridBagConstraints c) {
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
	}
	

	/**
	 * Methode addComponentNextLine
	 * fuegt dem JPanel ein Component 
	 * in der naechster Zeile
	 * (wird benutzt mit dem GridBagLayout fuer parent-JPanel)
	 * @param parent
	 * @param addetCom
	 * @param c
	 */
	public void addComponentNextLine(JPanel parent, Component addetCom, GridBagConstraints c) {
		c.gridy++;
		parent.add(addetCom, c);
	}

	/**
	 * Methode createNewDatePicker()
	 * erstellt ein neues JDatePicker
	 * @return JDatePickerImpl
	 */
	public JDatePickerImpl createNewDatePicker() {
		UtilDateModel model;
		JDatePanelImpl datePanel;
		DateLabelFormatter dlf;
		dlf = new DateLabelFormatter();
		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model, p);
		return new JDatePickerImpl(datePanel, dlf);
	}
	
	/**
	 * Methode createTitlePanel
	 * erstellt ein JPanel mit dem Title-Label
	 * mit entsprechendem Text und dem einheitlichem Font
	 * @param title
	 * @return
	 */
	public JPanel createTitlePanel(String title) {
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(titleFont);
		titlePanel.add(titleLabel);
		return titlePanel;
	}
	
	/**
	 * Method clearTextFields
	 * setzt die inhalte der uebergebener JTextFields auf leer
	 * @param textFields
	 */
	public void clearTextFields(JTextField... textFields) {
		for (int i = 0; i < textFields.length; i++) {
			textFields[i].setText("");
		}
	}

	/**
	 * Methode checkTFNamenLength
	 * uberprueft, ob die uebergebene JTextFields
	 * Strings von dem Namen-Pattern von eintsprechender laenge haben
	 * @param minLength
	 * @param maxLength
	 * @param textFields
	 * @return
	 */
	public boolean checkTFNamenLength(int minLength,int maxLength, JTextField... textFields) {
		boolean check = true;
		for (int i = 0; i < textFields.length; i++) {
			String toCheck = textFields[i].getText();
			if (!checkStringNamen(toCheck) || !(toCheck.length() <= maxLength) || !(toCheck.length() >= minLength))
				check = false;
		}
		return check;
	}

	/**
	 * Methode checkTFNamenLength
	 * uberprueft, ob die uebergebene JTextFields
	 * Strings von dem Zahlen-Pattern von eintsprechender laenge haben
	 * @param length
	 * @param textFields
	 * @return
	 */
	public boolean checkTFZahlen(int length, JTextField... textFields) {
		boolean check = true;
		for (int i = 0; i < textFields.length; i++) {
			String toCheck = textFields[i].getText();
			if (!checkStringZahlenLength(toCheck, length)) {
				check = false;
			}
			
		}
		return check;
	}


	/**
	 * Methode checkStringBuchZahlen
	 * prueft, ob uebergebener String dem Pattern entspricht
	 * @param string
	 * @return
	 */
	public boolean checkStringBuchZahlen(String string) {
		return Pattern.matches(PATTERN_BST_ZAHL, string);
	}

	/**
	 * Methode checkStringPassword
	 * prueft, ob uebergebener String dem Pattern entspricht
	 * @param string
	 * @return
	 */
	public boolean checkStringPassword(String string) {
		return Pattern
				.matches(
						PASSWORD_PATTERN,
						string);
	}

	/**
	 * Methode checkStringNamen
	 * prueft, ob uebergebener String dem Pattern entspricht
	 * @param string
	 * @return
	 */
	public boolean checkStringNamen(String string) {
		return Pattern.matches(PATTERN_NAMEN, string);
	}

	/**
	 * Methode checkStringZahlenLength
	 * prueft, ob uebergebener String dem Pattern entspricht
	 * und kuerzer oder gleich der laenge ist
	 * @param string
	 * @return
	 */
	public boolean checkStringZahlenLength(String string, int length) {
		if (string.length() <= length) {
			return Pattern.matches(PATTERN_ZAHL, string);
		} else
			return false;
	}
	
	/**
	 * Methode checkStringInArray
	 * prueft, ob ein String in einem Strin-Array vorhanden ist
	 * @param checkString
	 * @param stringArray
	 * @return
	 */
	public boolean checkStringInArray(String checkString, String[]stringArray){
		boolean found = false;
		for(int i = 0; i< stringArray.length-1; i++){
			if(checkString.equals(stringArray[i])){
				found = true;
			}
		}
		return found;
	}
	
}
