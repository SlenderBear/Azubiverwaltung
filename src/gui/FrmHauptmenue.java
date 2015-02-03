package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

public class FrmHauptmenue extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmHauptmenue frame = new FrmHauptmenue();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmHauptmenue() {
		setTitle("Hauptmen\u00FC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblBenutzer = new JLabel("Bentuzer: ");
		GridBagConstraints gbc_lblBenutzer = new GridBagConstraints();
		gbc_lblBenutzer.insets = new Insets(0, 0, 5, 5);
		gbc_lblBenutzer.gridx = 9;
		gbc_lblBenutzer.gridy = 0;
		contentPane.add(lblBenutzer, gbc_lblBenutzer);
		
		JLabel lblBenutzername = new JLabel("DefaultBenutzer");
		GridBagConstraints gbc_lblBenutzername = new GridBagConstraints();
		gbc_lblBenutzername.gridwidth = 2;
		gbc_lblBenutzername.insets = new Insets(0, 0, 5, 5);
		gbc_lblBenutzername.gridx = 10;
		gbc_lblBenutzername.gridy = 0;
		contentPane.add(lblBenutzername, gbc_lblBenutzername);
		
		JLabel lblHauptmenue = new JLabel("Hauptmen\u00FC");
		lblHauptmenue.setForeground(Color.BLUE);
		lblHauptmenue.setFont(new Font("Times New Roman", Font.BOLD, 20));
		GridBagConstraints gbc_lblHauptmenue = new GridBagConstraints();
		gbc_lblHauptmenue.gridwidth = 2;
		gbc_lblHauptmenue.insets = new Insets(0, 0, 5, 5);
		gbc_lblHauptmenue.gridx = 8;
		gbc_lblHauptmenue.gridy = 1;
		contentPane.add(lblHauptmenue, gbc_lblHauptmenue);
		
		JButton btnAzubiverwaltung = new JButton("Azubiverwaltung");
		GridBagConstraints gbc_btnAzubiverwaltung = new GridBagConstraints();
		gbc_btnAzubiverwaltung.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAzubiverwaltung.gridwidth = 3;
		gbc_btnAzubiverwaltung.insets = new Insets(0, 0, 5, 5);
		gbc_btnAzubiverwaltung.gridx = 5;
		gbc_btnAzubiverwaltung.gridy = 3;
		contentPane.add(btnAzubiverwaltung, gbc_btnAzubiverwaltung);
		
		JButton btnAusbilderverwaltung = new JButton("Ausbilderverwaltung");
		GridBagConstraints gbc_btnAusbilderverwaltung = new GridBagConstraints();
		gbc_btnAusbilderverwaltung.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAusbilderverwaltung.insets = new Insets(0, 0, 5, 5);
		gbc_btnAusbilderverwaltung.gridx = 10;
		gbc_btnAusbilderverwaltung.gridy = 3;
		contentPane.add(btnAusbilderverwaltung, gbc_btnAusbilderverwaltung);
		
		JButton btnRegistrierung = new JButton("Registrieren");
		GridBagConstraints gbc_btnRegistrierung = new GridBagConstraints();
		gbc_btnRegistrierung.gridheight = 2;
		gbc_btnRegistrierung.gridwidth = 2;
		gbc_btnRegistrierung.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistrierung.gridx = 8;
		gbc_btnRegistrierung.gridy = 4;
		contentPane.add(btnRegistrierung, gbc_btnRegistrierung);
		
		JButton btnBetriebsverwaltung = new JButton("Betriebsverwaltung");
		GridBagConstraints gbc_btnBetriebsverwaltung = new GridBagConstraints();
		gbc_btnBetriebsverwaltung.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBetriebsverwaltung.gridwidth = 3;
		gbc_btnBetriebsverwaltung.insets = new Insets(0, 0, 0, 5);
		gbc_btnBetriebsverwaltung.gridx = 5;
		gbc_btnBetriebsverwaltung.gridy = 6;
		contentPane.add(btnBetriebsverwaltung, gbc_btnBetriebsverwaltung);
		
		JButton btnZeugnisverwaltung = new JButton("Zeugnisverwaltung");
		GridBagConstraints gbc_btnZeugnisverwaltung = new GridBagConstraints();
		gbc_btnZeugnisverwaltung.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnZeugnisverwaltung.insets = new Insets(0, 0, 0, 5);
		gbc_btnZeugnisverwaltung.gridx = 10;
		gbc_btnZeugnisverwaltung.gridy = 6;
		contentPane.add(btnZeugnisverwaltung, gbc_btnZeugnisverwaltung);
	}

}
