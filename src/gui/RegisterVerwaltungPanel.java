package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import objects.Berechtigung;
import objects.Lehrer;
import objects.Login;

public class RegisterVerwaltungPanel extends JPanel{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GUITools tools;
	private ArrayList<Lehrer> userList;

	public RegisterVerwaltungPanel(ArrayList<Lehrer> userList,GUITools tools) {
		this.userList = userList;
		this.tools = tools;
		innerRegisterPanel = new JPanel(new GridBagLayout());
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		berPanel = new JPanel(new GridLayout(0, 1));
		
		userListModel = new DefaultListModel();
		userJList = new JList(userListModel);
		userScrollPane = new JScrollPane(userJList);
		userScrollPane.setPreferredSize(new Dimension(200, 350));

		vorField = new JTextField(20);
		nameField = new JTextField(20);
		userField = new JTextField(20);
		passField = new JTextField(20);
		teleField = new JTextField(20);

		btgr = new ButtonGroup();

		rbLehrer = new JRadioButton("Klassenlehrer");
		rbLeitung = new JRadioButton("Bereichsleitung");



		addButton = tools.createButton("Erstellen", 150, 25);
		editButton = tools.createButton("Ändern", 150, 25);
		eraseButton = tools.createButton("Löschen", 150, 25);
		initialize();
	}
	
	private JPanel innerRegisterPanel,buttonPanel,berPanel;
	// *************************
	private DefaultListModel userListModel;
	private JList userJList;
	private JScrollPane userScrollPane;

	private JTextField vorField ,nameField,userField,passField,teleField;

	private ButtonGroup btgr;

	private JRadioButton rbLehrer,rbLeitung;

	private JButton addButton,editButton,eraseButton;
	
	private void initialize(){
		 this.setLayout(new BorderLayout());
			
			GridBagConstraints c = new GridBagConstraints();
			tools.setConstraintsDefault(c);
			btgr.add(rbLeitung);
			btgr.add(rbLehrer);
			
			berPanel.add(rbLehrer);
			berPanel.add(rbLeitung);
			rbLehrer.setSelected(true);
			// ***********************//
			c.gridy = 0;
			c.gridx = 0;
			c.gridwidth = 1;
			c.gridheight = 8;
			innerRegisterPanel.add(tools.createTiteledPanel("Nutzer", userScrollPane), c);

			// ***********************//

			c.gridx = 1;
			c.gridy = 0;
			c.gridheight = 1;
			innerRegisterPanel.add(tools.createTiteledPanel("Vorname", vorField), c);
			tools.addComponentNextLine(innerRegisterPanel,
					tools.createTiteledPanel("Name", nameField),c);
			tools.addComponentNextLine(innerRegisterPanel,
					tools.createTiteledPanel("Username", userField),c);
			tools.addComponentNextLine(innerRegisterPanel,
					tools.createTiteledPanel("Passwort", passField),c);
			tools.addComponentNextLine(innerRegisterPanel,
					tools.createTiteledPanel("Telefonnummer", teleField),c);
			tools.addComponentNextLine(innerRegisterPanel,
					tools.createTiteledPanel("Berechtigung", berPanel),c);
			//
			buttonPanel.add(addButton);
			buttonPanel.add(editButton);
			buttonPanel.add(eraseButton);
			this
					.add(tools.createTitlePanel("Registrierung"), BorderLayout.NORTH);
			this.add(innerRegisterPanel, BorderLayout.CENTER);
			this.add(buttonPanel, BorderLayout.SOUTH);
			// ***************Listeners******************///

			userJList.addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (userJList.getSelectedIndex() != -1) {
						Lehrer selectedLehrer = (Lehrer) userListModel.get(userJList
								.getSelectedIndex());
						Login selectedLogin = selectedLehrer.getLogin();
						Berechtigung selectedBer = selectedLogin.getBerechtigung();
						nameField.setText(selectedLehrer.getName());
						vorField.setText(selectedLehrer.getVorname());
						teleField.setText(selectedLehrer.getTelefon());
						userField.setText(selectedLogin.getLoginName());
						passField.setText(selectedLogin.getLoginPasswort());
						if (selectedBer.getID() == 1) {
							rbLehrer.setSelected(true);
						} else if (selectedBer.getID() == 2) {
							rbLeitung.setSelected(true);
						}

					}
				}
			});

			// **********************************************
			addButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (tools.checkTFNamenLength(1,50, nameField, vorField)
							&& tools.checkStringPassword(passField.getText())) {
						Login login = new Login(userField.getText(), passField
								.getText());
						Berechtigung newBer = new Berechtigung();
						if (rbLehrer.isSelected())
							newBer.setID(1);
						else if (rbLeitung.isSelected())
							newBer.setID(2);
						Lehrer newLehrer = new Lehrer(nameField.getText(), vorField
								.getText(), teleField.getText(), login);
						newLehrer.getLogin().setBerechtigung(newBer);
						userListModel.addElement(newLehrer);

						tools.clearTextFields(nameField, vorField, teleField, userField,
								passField);
					} else {
						JOptionPane.showMessageDialog(addButton.getParent(),
								"Felder falsch gefüllt", "Fehler",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			});
			// ****************************************************
			editButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (userJList.getSelectedIndex() != -1) {
						if (tools.checkTFNamenLength(1,50, nameField, vorField)) {
							Lehrer selectedLehrer = (Lehrer) userListModel
									.get(userJList.getSelectedIndex());
							selectedLehrer.setName(nameField.getText());
							selectedLehrer.setVorname(vorField.getText());
							selectedLehrer.setTelefon(teleField.getText());
							Login cLogin = selectedLehrer.getLogin();
							cLogin.setLoginName(userField.getText());
							cLogin.setLoginPasswort(passField.getText());
							Berechtigung cBer = cLogin.getBerechtigung();
							if (rbLehrer.isSelected())
								cBer.setID(1);
							else if (rbLeitung.isSelected())
								cBer.setID(2);
						} else {
							System.out.println("Fehler");
						}

					}

					tools.clearTextFields(nameField, vorField, teleField, userField,
							passField);
					userJList.setModel(userListModel);
				}
			});
			// *******************************************
			eraseButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (userJList.getSelectedIndex() != -1)
						userListModel.remove(userJList.getSelectedIndex());
					tools.clearTextFields(nameField, vorField, teleField, userField,
							passField);

				}
			});
			// /*******************************************////
	}
}
