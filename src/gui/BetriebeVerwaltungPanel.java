package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import businesslogik.dataprovider.StandardDataProvider;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

import objects.Betrieb;

/**
 * 
 * @author Maksim Imaev
 * Klasse BetriebeVerwaltungPanel erweiter JPanel
 * erstellt den JPanel zur Verwaltung von Betrieben
 *
 */
public class BetriebeVerwaltungPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GUITools tools;
	private ArrayList<Betrieb> betriebList;
	private StandardDataProvider sdp;
	private int zugangsStufe;
	public BetriebeVerwaltungPanel(int zugangsStufe, StandardDataProvider sdp, ArrayList<Betrieb> betriebList,GUITools tools) {
		this.zugangsStufe = zugangsStufe;
		this.sdp = sdp;
		this.tools = tools;
		this.betriebList = betriebList;
		innerBetriebsPanel = new JPanel(new GridBagLayout());
		ortPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// /////////////////////////////////////////////////////////////
		bezField = new JTextField(20);
		plzField = new JTextField(6);
		ortField = new JTextField(13);
		adresseField = new JTextField(20);
		teleField = new JTextField(20);
		faxField = new JTextField(20);
		eMailField = new JTextField(20);

		dlmBetriebsList = new DefaultListModel();
		betriebJList = new JList(dlmBetriebsList);
		betriebsScrollPane = new JScrollPane(betriebJList);
		betriebsScrollPane.setPreferredSize(new Dimension(200, 300));

		addButton = tools.createButton("Erstellen", 150, 25);
		editButton = tools.createButton("Aendern", 150, 25);
		eraseButton = tools.createButton("Loeschen", 150, 25);
		fillBetriebeJList();
		initialize();
	}
	
	private JPanel innerBetriebsPanel,ortPanel,buttonPanel;
	
	private JTextField bezField,plzField,ortField,adresseField,teleField,eMailField,faxField;

	private DefaultListModel dlmBetriebsList;
	private JList betriebJList;
	private JScrollPane betriebsScrollPane;

	private JButton addButton,editButton,eraseButton;
	/**
	 * Methode initialize
	 * erstellt den JPanel
	 */
	private void initialize(){
		this.setLayout(new BorderLayout());
		
		
		GridBagConstraints c = new GridBagConstraints();
		tools.setConstraintsDefault(c);
		
		ortPanel.add(plzField);
		ortPanel.add(new JLabel(" / "));
		ortPanel.add(ortField);
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
				tools.createTiteledPanel("Faxnummer", faxField),c);
		tools.addComponentNextLine(innerBetriebsPanel,
				tools.createTiteledPanel("E-Mail", eMailField),c);
		// ***********************************//
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);
		if(zugangsStufe < 2)addButton.setEnabled(false);
		if(zugangsStufe < 2)eraseButton.setEnabled(false);
		this.add(tools.createTitlePanel("Betriebeverwaltung"),
				BorderLayout.NORTH);
		this.add(innerBetriebsPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Betrieb newBetrieb = new Betrieb();
				newBetrieb.setFirmenbezeichnung(bezField.getText());
				newBetrieb.setPlz(plzField.getText());
				newBetrieb.setStrasse(adresseField.getText());
				newBetrieb.setOrt(ortField.getText());
				newBetrieb.seteMail(eMailField.getText());
				newBetrieb.setTelefon(teleField.getText());
				newBetrieb.setFax(faxField.getText());
				dlmBetriebsList.addElement(newBetrieb);
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(betriebJList.getSelectedIndex() != -1){
					Betrieb selectedBetrieb = (Betrieb) dlmBetriebsList.getElementAt(betriebJList.getSelectedIndex());
					selectedBetrieb.setFirmenbezeichnung(bezField.getText());
					selectedBetrieb.setPlz(plzField.getText());
					selectedBetrieb.setStrasse(adresseField.getText());
					selectedBetrieb.setOrt(ortField.getText());
					selectedBetrieb.seteMail(eMailField.getText());
					selectedBetrieb.setTelefon(teleField.getText());
					selectedBetrieb.setFax(faxField.getText());
					betriebJList.setModel(dlmBetriebsList);
				}
				
			}
		});
		
		eraseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(betriebJList.getSelectedIndex() != -1){
					dlmBetriebsList.remove(betriebJList.getSelectedIndex());
				}
				
			}
		});
		
		betriebJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(betriebJList.getSelectedIndex() != -1){
					Betrieb selectedBetrieb = (Betrieb) dlmBetriebsList.getElementAt(betriebJList.getSelectedIndex());
					bezField.setText(selectedBetrieb.getFirmenbezeichnung());
					plzField.setText(selectedBetrieb.getPlz());
					ortField.setText(selectedBetrieb.getOrt());
					adresseField.setText(selectedBetrieb.getStrasse());
					eMailField.setText(selectedBetrieb.geteMail());
					teleField.setText(selectedBetrieb.getTelefon());
					faxField.setText(selectedBetrieb.getFax());
				}
				
			}
		});
	}
	/**
	 * Methode fillBetriebeJList()
	 * fuellt die JList mit betrieben
	 */
	private void fillBetriebeJList(){
		dlmBetriebsList.removeAllElements();
		for(int i = 0; i < betriebList.size(); i++){
			dlmBetriebsList.addElement(betriebList.get(i));
		}
	}
}
