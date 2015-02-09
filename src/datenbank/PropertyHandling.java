package datenbank;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Diese Klasse stellt die Methoden um Properties zu ändern oder aus zu lesen bereit.
 * 
 * @author dunkel.gregor
 *
 */
public class PropertyHandling {
	
//	private final String filenameProp = "src\\verwaltung.properties";
	private final String filenameProp_Home = "src/verwaltung.properties";
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
			stream = new BufferedInputStream(new FileInputStream(filenameProp_Home));
			properties.load(stream);
			stream.close();
		} catch (Exception e) {
			return "EMPTY";
		}
		return properties.getProperty(prop);
	}

	/**
	 * Schreibt den Property Wert.
	 * @param prop {@link String}
	 * @param value {@link String}
	 * @return true wenn erfolgreich.
	 * @throws IOException 
	 */
	public boolean schreibeProp(String prop, String value) {
		BufferedInputStream stream = null;
		Properties props = new Properties();
		try {
			stream = new BufferedInputStream(new FileInputStream(filenameProp_Home));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			props.load(stream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		props.setProperty(prop, value);
		OutputStream out;
		try {
			out = new FileOutputStream(new File(filenameProp_Home));
			props.store(out, "");
		} catch (Exception e) {
			return false;
		}
		return true;
	}


}
