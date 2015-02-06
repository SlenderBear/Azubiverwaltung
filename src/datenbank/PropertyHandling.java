package datenbank;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Diese Klasse stellt die Methoden um Properties zu ändern oder aus zu lesen bereit.
 * 
 * @author dunkel.gregor
 *
 */
public class PropertyHandling {
	
	private final String filenameProp = "W:\\git\\Azubiverwaltung\\src\\verwaltung.properties";
	public static final String PROP_EMPTY = "empty";
	
	/**
	 * Liefert den Wert des Properties.
	 * @param prop String
	 * @return Wert
	 */
	public String liesPropAus(String prop) {
		Properties properties = new Properties();
		try {
			BufferedInputStream stream;
			stream = new BufferedInputStream(new FileInputStream(filenameProp));
			properties.load(stream);
			stream.close();
		} catch (Exception e) {
			return "EMPTY";
		}
		return properties.getProperty(prop);
	}

	/**
	 * Schrteibt den Property Wert.
	 * @param prop {@link String}
	 * @param value {@link String}
	 * @return true wenn erfolgreich.
	 */
	public boolean schreibeProp(String prop, String value) {
		Properties props = new Properties();
		props.setProperty("db", value);
		OutputStream out;
		try {
			out = new FileOutputStream(new File(filenameProp));
			props.store(out, "");
		} catch (Exception e) {
			return false;
		}
		return true;
	}


}
