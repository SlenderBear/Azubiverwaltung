package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePickerImpl;

public class ZeugnisVerwaltungPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GUITools tools;
	private String[] tHeads = { "Azubi", "Note" };

	public ZeugnisVerwaltungPanel(GUITools tools) {
		this.tools = tools;
		initialize();
	}
	
	public void initialize(){
		this.setLayout(new BorderLayout());
		JPanel innerZeugnisPanel = new JPanel(new GridBagLayout());
		JPanel druckPanel = new JPanel(new GridLayout(0, 1, 0, 25));
		GridBagConstraints c = new GridBagConstraints();
		tools.setConstraintsDefault(c);
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

		JDatePickerImpl dpZeug = tools.createNewDatePicker();

		JButton btZeugDruck = tools.createButton("Schüler", 150, 25);
		JButton btZeugKlasseDruck = tools.createButton("Gesamte Klasse", 150, 25);
		// //////////////////////////////////////////////////////////
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 1;
		innerZeugnisPanel.add(tools.createTiteledPanel("Klassenwahl", cmbKlasse), c);
		c.gridheight = 3;
		tools.addComponentNextLine(innerZeugnisPanel,
				tools.createTiteledPanel("Faecher", azubiScrollPane), c);
		// ***************************************//
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		innerZeugnisPanel.add(tools.createTiteledPanel("Jahr", cmbJahrBox), c);
		c.gridheight = 3;
		tools.addComponentNextLine(innerZeugnisPanel,
				tools.createTiteledPanel("Noten", tableScrollPane),c);
		// ***************************************//

		c.gridx = 2;
		c.gridheight = 1;
		c.gridy = 0;
		innerZeugnisPanel.add(
				tools.createTiteledPanel("Zeugniskonferenz am:", dpZeug), c);

		JLabel label = new JLabel("Zeugnis drucken für:");

		c.gridy++;
		innerZeugnisPanel.add(label, c);

		druckPanel.add(btZeugDruck);
		druckPanel.add(btZeugKlasseDruck);

		c.gridy++;
		innerZeugnisPanel.add(druckPanel, c);

		this.add(tools.createTitlePanel("Zeugnisverwaltung"),
				BorderLayout.NORTH);
		this.add(innerZeugnisPanel, BorderLayout.CENTER);

	}

}
