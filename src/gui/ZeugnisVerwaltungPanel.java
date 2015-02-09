package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import objects.Azubi;
import objects.Fach;
import objects.Klasse;
import objects.Note;
import objects.Zeugnis;
import objects.Zeugnisposition;

import org.jdatepicker.impl.JDatePickerImpl;

import businesslogik.dataprovider.StandardDataProvider;

import com.itextpdf.text.pdf.internal.ZugferdChecker;
import com.toedter.calendar.JYearChooser;

public class ZeugnisVerwaltungPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GUITools tools;
	private String[] tHeads = { "Azubi", "Note" };
	private ArrayList<Klasse> klasseList;
	private ArrayList<Fach> fachList;
	private ArrayList<Azubi> azubiList;
	private ArrayList<Zeugnis> zeugnisList;
	private StandardDataProvider sdp;
	private int zugangsStufe;

	public ZeugnisVerwaltungPanel(int zugangsStufe, StandardDataProvider sdp, ArrayList<Klasse> klasseList, GUITools tools) {
		this.zugangsStufe = zugangsStufe;
		this.sdp = sdp;
		this.klasseList = klasseList;
		this.tools = tools;
		innerZeugnisPanel = new JPanel(new GridBagLayout());
		druckPanel = new JPanel(new GridLayout(0, 1, 0, 25));

		dcbmKlasse = new DefaultComboBoxModel(this.klasseList.toArray());
		cmbKlasse = new JComboBox(dcbmKlasse);
		cmbKlasse.setPreferredSize(new Dimension(200, 25));

		jahrAusbildung = new JYearChooser();

		dlmFach = new DefaultListModel();
		faecherList = new JList(dlmFach);
		azubiScrollPane = new JScrollPane(faecherList);
		azubiScrollPane.setPreferredSize(new Dimension(200, 150));

		dtmNoten = new MyTable(8, 2);
		dtmNoten.setColumnIdentifiers(tHeads);

		notenTable = new JTable(dtmNoten);

		tableScrollPane = new JScrollPane(notenTable);
		tableScrollPane.setPreferredSize(new Dimension(250, 148));

		dpZeug = tools.createNewDatePicker();

		btZeugDruck = tools.createButton("Schüler", 150, 25);
		btZeugKlasseDruck = tools.createButton("Gesamte Klasse", 150, 25);
		initialize();
	}

	private JPanel innerZeugnisPanel, druckPanel;

	private DefaultComboBoxModel dcbmKlasse;
	private JComboBox cmbKlasse;

	private JYearChooser jahrAusbildung;

	private DefaultListModel dlmFach;
	private JList faecherList;
	private JScrollPane azubiScrollPane;

	private DefaultTableModel dtmNoten;

	private JTable notenTable;

	private JScrollPane tableScrollPane;

	private JDatePickerImpl dpZeug;

	private JButton btZeugDruck;
	private JButton btZeugKlasseDruck;

	private void initialize() {
		this.setLayout(new BorderLayout());

		GridBagConstraints c = new GridBagConstraints();
		tools.setConstraintsDefault(c);
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 1;
		innerZeugnisPanel.add(
				tools.createTiteledPanel("Klassenwahl", cmbKlasse), c);
		c.gridheight = 3;
		tools.addComponentNextLine(innerZeugnisPanel,
				tools.createTiteledPanel("Faecher", azubiScrollPane), c);
		// ***************************************//
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		innerZeugnisPanel.add(tools.createTiteledPanel("Jahr", jahrAusbildung),
				c);
		c.gridheight = 3;
		tools.addComponentNextLine(innerZeugnisPanel,
				tools.createTiteledPanel("Noten", tableScrollPane), c);
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

		btZeugDruck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		btZeugKlasseDruck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		cmbKlasse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void fillFachList() {

	}

	private void fillAzubiList(Klasse klasse) {
		azubiList = new ArrayList<Azubi>();
	}

	private void fillTable(Fach fach) {
		// ///////////////////////////////////

		// //////////////////////////////////
		for (int i = 0; i < azubiList.size(); i++) {
			dtmNoten.addRow(new Object[] {
					azubiList.get(i),
					getNote((Fach) dlmFach.getElementAt(faecherList
							.getSelectedIndex()),
							findZeugnis(azubiList.get(i),
									jahrAusbildung.getYear())) });
		}
	}

	private Zeugnis findZeugnis(Azubi azubi, int jahr) {
		for (int i = 0; i < zeugnisList.size(); i++) {
			if (zeugnisList.get(i).getAzubi() == azubi
					&& zeugnisList.get(i).getJahr() == jahr) {
				return zeugnisList.get(i);
			}
		}
		return null;
	}

	private Note getNote(Fach fach, Zeugnis zeugnis) {
		ArrayList<Zeugnisposition> zpList = zeugnis.getPositionen();
		for (int i = 0; i < zpList.size(); i++) {
			if (zpList.get(i).getFach() == fach) {
				return zpList.get(i).getNote();
			}
		}
		return null;
	}
	
	private void getFachList(){
		dlmFach.removeAllElements();
		fachList = new ArrayList<Fach>();
		for(int i = 0; i < fachList.size(); i++){
			dlmFach.addElement(fachList.get(i));
		}
	}

}
