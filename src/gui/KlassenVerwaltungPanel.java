package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import businesslogik.dataprovider.StandardDataProvider;
import objects.Klasse;
import objects.Lehrer;
/**
 * 
 * @author Maksim Imaev
 * Klasse KlassenVerwaltungPanel erweitert JPanel
 * erstellt ein JPanel zur Verwaltung von Klassen
 *
 */
public class KlassenVerwaltungPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GUITools tools;
	private ArrayList<Klasse> klasseList;
	private ArrayList<Lehrer> userList;
	private StandardDataProvider sdp;
	private int zugangsStufe;
	
	public KlassenVerwaltungPanel(int zugangsStufe, StandardDataProvider sdp, ArrayList<Klasse> klasseList, ArrayList<Lehrer> userList, GUITools tools) {
		this.zugangsStufe = zugangsStufe;
		this.sdp = sdp;
		this.klasseList = klasseList;
		this.userList = userList;
		this.tools = tools;
		
		innerKlassenPanel = new JPanel(new GridBagLayout());
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		dlmKlasseList = new DefaultListModel();
		klassenJList = new JList(dlmKlasseList);
		klassenScrollPane = new JScrollPane(klassenJList);
		klassenScrollPane.setPreferredSize(new Dimension(200, 300));

		bezField = new JTextField(20);
//		this.userList = new ArrayList<Lehrer>();
		dcbmLehrer = new DefaultComboBoxModel(this.userList.toArray());
		cmbLehrer = new JComboBox(dcbmLehrer);
		cmbLehrer.setPreferredSize(new Dimension(200, 25));

		addButton = tools.createButton("Erstellen", 150, 25);
		editButton = tools.createButton("Aendern", 150, 25);
		eraseButton = tools.createButton("Loeschen", 150, 25);
		fillKlassenJList();
		initialize();
	}
	
	private JPanel innerKlassenPanel,buttonPanel;

	private DefaultListModel dlmKlasseList;
	private JList klassenJList;
	private JScrollPane klassenScrollPane;

	private JTextField bezField;

	private DefaultComboBoxModel dcbmLehrer;
	private JComboBox cmbLehrer;

	private JButton addButton,editButton,eraseButton;
	/**
	 * Methode initialize
	 * erstellt den JPanel
	 */
	private void initialize(){
		this.setLayout(new BorderLayout());
		
		
		GridBagConstraints c = new GridBagConstraints();
		tools.setConstraintsDefault(c);
		
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridheight = 3;
		innerKlassenPanel.add(tools.createTiteledPanel("Klassen", klassenScrollPane),
				c);

		// *********************************************//

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		innerKlassenPanel.add(tools.createTiteledPanel("Bezeichnung", bezField), c);
		tools.addComponentNextLine(innerKlassenPanel,
				tools.createTiteledPanel("Lehrer", cmbLehrer),c);

		// *********************************************//

		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseButton);
		
		if(zugangsStufe < 2)addButton.setEnabled(false);
		if(zugangsStufe < 2)eraseButton.setEnabled(false);

		this.add(tools.createTitlePanel("Klassenverwaltung"),
				BorderLayout.NORTH);
		this.add(innerKlassenPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Klasse newKlasse = new Klasse();
				newKlasse.setBezeichnung(bezField.getText());
				newKlasse.setLehrer((Lehrer)dcbmLehrer.getSelectedItem());
				dlmKlasseList.addElement(newKlasse);
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(klassenJList.getSelectedIndex()!= -1){
					Klasse selectedKlasse = (Klasse) dlmKlasseList.getElementAt(klassenJList.getSelectedIndex());
					selectedKlasse.setBezeichnung(bezField.getText());
					selectedKlasse.setLehrer((Lehrer)dcbmLehrer.getSelectedItem());
					klassenJList.setModel(dlmKlasseList);
				}
				
			}
		});
		
		eraseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(klassenJList.getSelectedIndex()!= -1){
					dlmKlasseList.remove(klassenJList.getSelectedIndex());
				}
				
			}
		});
		
		klassenJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(klassenJList.getSelectedIndex()!= -1){
					Klasse selectedKlasse = (Klasse) dlmKlasseList.getElementAt(klassenJList.getSelectedIndex());
					bezField.setText(selectedKlasse.getBezeichnung());
					dcbmLehrer.setSelectedItem(selectedKlasse.getLehrer());
				}
				
			}
		});
	}
	
	private void fillKlassenJList(){
		dlmKlasseList.removeAllElements();
		for(int i = 0; i < klasseList.size(); i++){
			dlmKlasseList.addElement(klasseList.get(i));
		}
	}
	
}
