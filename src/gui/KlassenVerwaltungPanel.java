package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class KlassenVerwaltungPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GUITools tools;
	public KlassenVerwaltungPanel(GUITools tools) {
		this.tools = tools;
		initialize();
	}
	
	public void initialize(){
		this.setLayout(new BorderLayout());
		JPanel innerKlassenPanel = new JPanel(new GridBagLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		GridBagConstraints c = new GridBagConstraints();
		tools.setConstraintsDefault(c);
		// //////////////////////////////////////////////////////////
		JList klassenList = new JList();
		JScrollPane klassenScrollPane = new JScrollPane(klassenList);
		klassenScrollPane.setPreferredSize(new Dimension(200, 300));

		JTextField bezField = new JTextField(20);

		JComboBox cmbLehrer = new JComboBox();
		cmbLehrer.setPreferredSize(new Dimension(200, 25));

		JButton addButton = tools.createButton("Erstellen", 150, 25);
		JButton editButton = tools.createButton("Ändern", 150, 25);
		JButton eraseButton = tools.createButton("Löschen", 150, 25);
		// //////////////////////////////////////////////////////////
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

		this.add(tools.createTitlePanel("Klassenverwaltung"),
				BorderLayout.NORTH);
		this.add(innerKlassenPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
}
