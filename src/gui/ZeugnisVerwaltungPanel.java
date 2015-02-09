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

import org.jdatepicker.impl.JDatePickerImpl;

import com.toedter.calendar.JYearChooser;

public class ZeugnisVerwaltungPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GUITools tools;
	private String[] tHeads = { "Azubi", "Note" };
	private ArrayList<Klasse> klasseList;
	private ArrayList<Fach> fachList;
	private ArrayList<Azubi> azubiList;

	public ZeugnisVerwaltungPanel(ArrayList<Klasse> klasseList, GUITools tools) {
		this.klasseList = klasseList;
		this.tools = tools;
		innerZeugnisPanel = new JPanel(new GridBagLayout());
		druckPanel = new JPanel(new GridLayout(0, 1, 0, 25));
		
		dcbmKlasse = new DefaultComboBoxModel(this.klasseList.toArray());
		cmbKlasse = new JComboBox();
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
	
	private JPanel innerZeugnisPanel,druckPanel;
	
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
	
	private void initialize(){
		this.setLayout(new BorderLayout());
		
		
		GridBagConstraints c = new GridBagConstraints();
		tools.setConstraintsDefault(c);
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
		innerZeugnisPanel.add(tools.createTiteledPanel("Jahr", jahrAusbildung), c);
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
		
		

	}
	
	private void fillTable(){
		/////////////////////////////////////
		azubiList = new ArrayList<Azubi>();
		////////////////////////////////////
		for(int i = 0; i < azubiList.size(); i++) {
			dtmNoten.addRow(new Object[]{azubiList.get(i),"Note"});
		}
	}

}
