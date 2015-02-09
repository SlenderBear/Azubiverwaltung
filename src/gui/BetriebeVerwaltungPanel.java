package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import objects.Betrieb;

public class BetriebeVerwaltungPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GUITools tools;
	private ArrayList<Betrieb> betriebList;
	public BetriebeVerwaltungPanel(ArrayList<Betrieb> betriebList,GUITools tools) {
		this.tools = tools;
		this.betriebList = betriebList;
		initialize();
	}
	
	public void initialize(){
		this.setLayout(new BorderLayout());
		JPanel innerBetriebsPanel = new JPanel(new GridBagLayout());
		JPanel ortPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		GridBagConstraints c = new GridBagConstraints();
		tools.setConstraintsDefault(c);
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

		JButton addButton = tools.createButton("Erstellen", 150, 25);
		JButton editButton = tools.createButton("Ändern", 150, 25);
		JButton eraseButton = tools.createButton("Löschen", 150, 25);

		ortPanel.add(plzField);
		ortPanel.add(new JLabel(" / "));
		ortPanel.add(ortField);
		// //////////////////////////////////////////////////////////////
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridheight = 6;
		innerBetriebsPanel.add(
				tools.createTiteledPanel("Betriebe", betriebsScrollPane), c);
		// ***********************************//
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		innerBetriebsPanel.add(tools.createTiteledPanel("Bezeichnung", bezField), c);
		tools.addComponentNextLine(innerBetriebsPanel,
				tools.createTiteledPanel("PLZ / Ort", ortPanel),c);
		tools.addComponentNextLine(innerBetriebsPanel,
				tools.createTiteledPanel("Strasse / HausNr", adresseField),c);
		tools.addComponentNextLine(innerBetriebsPanel,
				tools.createTiteledPanel("Telefonnummer", teleField),c);
		tools.addComponentNextLine(innerBetriebsPanel,
				tools.createTiteledPanel("E-Mail", eMailField),c);
		// ***********************************//
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);
		this.add(tools.createTitlePanel("Betriebeverwaltung"),
				BorderLayout.NORTH);
		this.add(innerBetriebsPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
}
