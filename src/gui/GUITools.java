package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

public class GUITools {

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private Properties p;
	public GUITools() {
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
	}


	public boolean checkVolljahr(int tag, int monat, int jahr) {
		Date gebTag = new Date(jahr - 1900, monat, tag);
		Date now = new Date();
		now.setYear(now.getYear() - 18);
		return gebTag.before(now);

	}

	public JPanel createTiteledPanel(String title, Component component) {
		JPanel returnPanel = new JPanel();
		// returnPanel.setPreferredSize(new Dimension(400, 200));
		returnPanel.setBorder(BorderFactory.createTitledBorder(title));
		returnPanel.add(component);
		return returnPanel;
	}
	
	public JButton createButton(String text, int wight, int height) {
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(wight, height));
		return button;
	}

	public void setConstraintsDefault(GridBagConstraints c) {
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
	}

	public void addComponentNextLine(JPanel parent, Component addetCom, GridBagConstraints c) {
		c.gridy++;
		parent.add(addetCom, c);
	}

	public JDatePickerImpl createNewDatePicker() {
		UtilDateModel model;
		JDatePanelImpl datePanel;
		DateLabelFormatter dlf;
		dlf = new DateLabelFormatter();
		model = new UtilDateModel();
		// model.setSelected(true);
		datePanel = new JDatePanelImpl(model, p);
		return new JDatePickerImpl(datePanel, dlf);
	}
	
	public JPanel createTitlePanel(String title) {
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel titleLabel = new JLabel(title);
		titlePanel.add(titleLabel);
		return titlePanel;
	}
	
	public void clearTextFields(JTextField... textFields) {
		for (int i = 0; i < textFields.length; i++) {
			textFields[i].setText("");
		}
	}

	public boolean checkTFNamenLength(int minLength,int maxLength, JTextField... textFields) {
		boolean check = true;
		for (int i = 0; i < textFields.length; i++) {
			String toCheck = textFields[i].getText();
			if (!checkStringNamen(toCheck) || !(toCheck.length() <= maxLength) || !(toCheck.length() >= minLength))
				check = false;
		}
		return check;
	}

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

//	private boolean checkStringBuch(String string) {
////		return Pattern.matches("[^ÖöÄäßÜü']*", string);
//	}

	public boolean checkStringBuchZahlen(String string) {
		return Pattern.matches("[A-Za-z0-9]*", string);
	}

	public boolean checkStringPassword(String string) {
		return Pattern
				.matches(
						"((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%_]).{6,20})",
						string);
	}

	public boolean checkStringNamen(String string) {
		return Pattern.matches("([A-Za-z ]|-)*", string);
	}

	public boolean checkStringZahlenLength(String string, int length) {
		if (string.length() <= length) {
			return Pattern.matches("[0-9]*", string);
		} else
			return false;
	}
	
}
