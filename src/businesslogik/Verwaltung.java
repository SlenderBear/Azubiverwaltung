package businesslogik;

import gui.GUIController;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Verwaltung {

	public enum DBOptions {
		EMPTY, MYSQL, XML
	};

	DBOptions dbWahl;

	public Verwaltung() {
		Properties properties = new Properties();
		try {
			BufferedInputStream stream = new BufferedInputStream(
					new FileInputStream("verwaltung.properties"));
			properties.load(stream);
			stream.close();
			if ((dbWahl = DBOptions.valueOf(properties.getProperty("lang")))
					.equals("empty")) {
				JOptionPane
						.showMessageDialog(
								new JFrame(),
								"Diese Anwendung wurde nicht ordnungsgem‰ﬂ konfiguriert.n.\nBitte sprechen Sie mit dem zust‰ndigen Administrator.");
			}
		} catch (IOException e) {
			JOptionPane
					.showMessageDialog(
							new JFrame(),
							"Das gewuenschte Datenhaltungssytem konnte nicht gestartet werden.\nBitte sprechen Sie mit dem zust‰ndigen Administrator.");
		}

		GUIController GuiCon = new GUIController(this);
	}

}
