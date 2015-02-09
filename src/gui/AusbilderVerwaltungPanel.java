package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import objects.Ausbilder;
import objects.Betrieb;

public class AusbilderVerwaltungPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private ArrayList<Betrieb> betriebList;
	private ArrayList<Ausbilder> ausbilderList;
	private GUITools tools;
	
	public AusbilderVerwaltungPanel(ArrayList<Betrieb> betriebList,ArrayList<Ausbilder> ausbilderList, GUITools tools) {
		this.betriebList = betriebList;
		this.ausbilderList = ausbilderList;
		this.tools=tools;
		initialize();
	}
	
	public void initialize(){
		this.setLayout(new BorderLayout());
		JPanel innerAusbilderPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JPanel rbPGeschlechtPanel = new JPanel(new FlowLayout(
				FlowLayout.CENTER, 0, 0));
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		tools.setConstraintsDefault(c);
		// ************************
		DefaultComboBoxModel dcbmBetrieb = new DefaultComboBoxModel(betriebList.toArray());
		JComboBox cmbBetrieb = new JComboBox();
		cmbBetrieb.setPreferredSize(new Dimension(200, 25));

		DefaultListModel ausbilderDLM = new DefaultListModel();
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

		JButton addButton = tools.createButton("Erstellen", 150, 25);
		JButton editButton = tools.createButton("Ändern", 150, 25);
		JButton eraseButton = tools.createButton("Löschen", 150, 25);

		// ********************//
		c.gridx = 0;
		c.gridy = 0;

		innerAusbilderPanel.add(tools.createTiteledPanel("Betriebwahl", cmbBetrieb),
				c);

		c.gridheight = 6;
		tools.addComponentNextLine(innerAusbilderPanel,
				tools.createTiteledPanel("Ausbilder", ausbilderScrollPane),c);
		// ********************//
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		innerAusbilderPanel.add(tools.createTiteledPanel("Vorname", vorField), c);
		tools.addComponentNextLine(innerAusbilderPanel,
				tools.createTiteledPanel("Nachname", nachField),c);
		tools.addComponentNextLine(innerAusbilderPanel,
				tools.createTiteledPanel("Geschlecht", rbPGeschlechtPanel),c);
		tools.addComponentNextLine(innerAusbilderPanel,
				tools.createTiteledPanel("Telefon", tNummerField),c);
		tools.addComponentNextLine(innerAusbilderPanel,
				tools.createTiteledPanel("E-Mail", eMailField),c);
		// ********************//
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);
		this.add(tools.createTitlePanel("Ausbilderverwaltung"),
				BorderLayout.NORTH);
		this.add(innerAusbilderPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	

}
