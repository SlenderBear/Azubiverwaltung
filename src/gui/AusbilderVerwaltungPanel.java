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

import objects.Ausbilder;
import objects.Betrieb;

/**
 * 
 * @author Maksim Imaev
 * AusbilderVerwaltungPanel
 * erweiter JPanel
 * erstellt den JPanel zur Verwaltung
 * von Ausbildern
 */
public class AusbilderVerwaltungPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private ArrayList<Betrieb> betriebList;
	private ArrayList<Ausbilder> ausbilderList;
	private GUITools tools;
	private StandardDataProvider sdp;
	private int zugangsStufe;
	public AusbilderVerwaltungPanel(int zugangsStufe, StandardDataProvider sdp, ArrayList<Betrieb> betriebList,ArrayList<Ausbilder> ausbilderList, GUITools tools) {
		this.zugangsStufe = zugangsStufe;
		this.sdp = sdp;
		this.betriebList = betriebList;
		this.ausbilderList = ausbilderList;
		this.tools=tools;
		
		innerAusbilderPanel = new JPanel(new GridBagLayout());
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		dcbmBetrieb = new DefaultComboBoxModel(this.betriebList.toArray());
		cmbBetrieb = new JComboBox(dcbmBetrieb);
		cmbBetrieb.setPreferredSize(new Dimension(200, 25));

		ausbilderDLM = new DefaultListModel();
		ausbilderJList = new JList(ausbilderDLM);
		ausbilderScrollPane = new JScrollPane(ausbilderJList);
		ausbilderScrollPane.setPreferredSize(new Dimension(200, 300));

		vorField = new JTextField(20);
		nachField = new JTextField(20);
		tNummerField = new JTextField(20);
		eMailField = new JTextField(20);



		addButton = tools.createButton("Erstellen", 150, 25);
		editButton = tools.createButton("Aendern", 150, 25);
		eraseButton = tools.createButton("Loeschen", 150, 25);
		
		if(zugangsStufe < 2)addButton.setEnabled(false);
		if(zugangsStufe < 2)eraseButton.setEnabled(false);
		initialize();
	}
	
	private JPanel innerAusbilderPanel,buttonPanel;
	
	private DefaultComboBoxModel dcbmBetrieb;
	
	private JComboBox cmbBetrieb;

	private DefaultListModel ausbilderDLM;
	
	private JList ausbilderJList;
	
	private JScrollPane ausbilderScrollPane;

	private JTextField vorField;
	private JTextField nachField;
	private JTextField tNummerField;
	private JTextField eMailField;



	private JButton addButton;
	private JButton editButton;
	private JButton eraseButton;
	
	/**
	 * Methode initialize
	 * erstellt den JPanel
	 */
	private void initialize(){
		this.setLayout(new BorderLayout());
		GridBagConstraints c = new GridBagConstraints();
		tools.setConstraintsDefault(c);
		// ************************
		

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
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Ausbilder newAusbilder = new Ausbilder();
				newAusbilder.setVorname(vorField.getText());
				newAusbilder.setName(nachField.getText());
				newAusbilder.setEmail(eMailField.getText());
				newAusbilder.setTelefon(tNummerField.getText());
				newAusbilder.setBetrieb((Betrieb)dcbmBetrieb.getSelectedItem());
				ausbilderDLM.addElement(newAusbilder);
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ausbilderJList.getSelectedIndex() != -1)
				{
					Ausbilder selectedAusbilder = (Ausbilder) ausbilderDLM.getElementAt(ausbilderJList.getSelectedIndex());
					selectedAusbilder.setVorname(vorField.getText());
					selectedAusbilder.setName(nachField.getText());
					selectedAusbilder.setEmail(eMailField.getText());
					selectedAusbilder.setTelefon(tNummerField.getText());
					selectedAusbilder.setBetrieb((Betrieb)dcbmBetrieb.getSelectedItem());
					ausbilderJList.setModel(ausbilderDLM);
				}
			}
		});
		
		eraseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ausbilderJList.getSelectedIndex() != -1)
				{
					ausbilderDLM.remove(ausbilderJList.getSelectedIndex());
				}
				
			}
		});
		
		ausbilderJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(ausbilderJList.getSelectedIndex() != -1)
				{
					Ausbilder selectedAusbilder = (Ausbilder) ausbilderDLM.getElementAt(ausbilderJList.getSelectedIndex());
					vorField.setText(selectedAusbilder.getVorname());
					nachField.setText(selectedAusbilder.getName());
					eMailField.setText(selectedAusbilder.getEmail());
					tNummerField.setText(selectedAusbilder.getTelefon());
				}
				
			}
		});
		
		cmbBetrieb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fillAusbilderJList((Betrieb)dcbmBetrieb.getSelectedItem());
				
			}
		});
		
	}
	/**
	 * Methode fillAusbilderJList
	 * fuellt die JList mit Ausbildern von einem Betrieb
	 */
		private void fillAusbilderJList(Betrieb betrieb){
			ausbilderDLM.removeAllElements();
			for(int i = 0; i < ausbilderList.size(); i++){
				if(ausbilderList.get(i).getBetrieb().getFirmenbezeichnung().equals(betrieb.getFirmenbezeichnung())){
					ausbilderDLM.addElement(ausbilderList.get(i));
				}
			}
		}
	

}
