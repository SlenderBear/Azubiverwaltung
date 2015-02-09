package pdf;


import java.util.ArrayList;

import businesslogik.dataprovider.StandardDataProvider;
import objects.Azubi;
import objects.Zeugnis;
import objects.Zeugnisposition;

/**
 * @author backs.kristin-anna
 * Diese Klasse dient dem Handling von  Noten und allem was
 * damit zu tun hat in Bezug auf das Schreiben der Zeugnisse als PDF-Datei.
 */

public class NotenFuerPdf {
	
	ArrayList<Zeugnisposition> noten = null;
	
	
	public ArrayList<Zeugnisposition> getNoten() {
		return noten;
	}

	public void setNoten(ArrayList<Zeugnisposition> noten) {
		this.noten = noten;
	}
	/**
	 * Die Methode getDurchschnitt erstellt den Durchschnitt aller Noten eines Azubis
	 * @param azubi
	 * @return
	 */
	public String[] getDurchschnitt(Azubi azubi) {
		String[] durchschnitt = new String[2];
		double summe = 0;

		for (int i = 0; i < 8; i++) {
			if (noten.get(i).getFach().getBezeichnung().startsWith("D")) {
				summe = summe
						+ Double.parseDouble(noten.get(i).getNote().getNoteID());
			} else if (noten.get(i).getFach().getBezeichnung().startsWith("P")) {
				summe = summe
						+ Double.parseDouble(noten.get(i).getNote().getNoteID());
			} else if (noten.get(i).getFach().getBezeichnung().startsWith("R")) {
				summe = summe
						+ Double.parseDouble(noten.get(i).getNote().getNoteID());
			} else if (noten.get(i).getFach().getBezeichnung().startsWith("S")) {
				summe = summe
						+ Double.parseDouble(noten.get(i).getNote().getNoteID());
			} else if (noten.get(i).getFach().getBezeichnung().startsWith("I")) {
				if (azubi.getFachrichtung() == 's') {
					summe = summe
							+ 2
							* Double.parseDouble(noten.get(i).getNote()
									.getNoteID());
				} else {
					summe = summe
							+ Double.parseDouble(noten.get(i).getNote()
									.getNoteID());
				}
			} else if (noten.get(i).getFach().getBezeichnung().startsWith("A")) {
				if (azubi.getFachrichtung() == 'a') {
					summe = summe
							+ 2
							* Double.parseDouble(noten.get(i).getNote()
									.getNoteID());
				} else {
					summe = summe
							+ Double.parseDouble(noten.get(i).getNote()
									.getNoteID());
				}
			} else if (noten.get(i).getFach().getBezeichnung().startsWith("W")) {
				summe = summe
						+ Double.parseDouble(noten.get(i).getNote().getNoteID());
			} else if (noten.get(i).getFach().getBezeichnung().startsWith("E")) {
				summe = summe
						+ Double.parseDouble(noten.get(i).getNote().getNoteID());
			}
		}

		summe = summe / 9;

		durchschnitt[0] = mappeDurchschnitt(summe);
		durchschnitt[1] = String.valueOf(summe).substring(0, 3);

		return durchschnitt;
	}
	/**
	 * Die Methode mappeDurchschnitt ergänzt den Notentext zur Durchschnittsnote
	 * @param durchschnitt
	 * @return
	 */
	private String mappeDurchschnitt(Double durchschnitt){
		if (durchschnitt <= 1.5) {
			return "sehr gut";
		} else if (durchschnitt > 1.5 && durchschnitt <= 2.5) {
			return "gut";
		} else if (durchschnitt > 2.5 && durchschnitt <= 3.5) {
			return "befriedigend";
		} else if (durchschnitt > 3.5 && durchschnitt <= 4.5) {
			return "ausreichend";
		} else if (durchschnitt > 4.5 && durchschnitt <= 5.5) {
			return "mangelhaft";
		} else {
			return "ungenügend";
		}
	}
	/**
	 * Die Methode gibFachNotenStrings gibt die Strings der Fächer und Noten zurück
	 * @param format
	 * @return
	 */
	public String[][] gibFachNotenStrings(Short format) {
		String[][] strings = new String[2][8];
		if (format == 3) {
			strings[0][0] = "Deutsch / Kommunikation\u00B2\u207E";
			strings[0][1] = "Politik / Gesellschaftslehre\u00B2\u207E";
			strings[0][3] = "Sport / Gesundheitsförderung\u00B9\u207E";
			strings[0][4] = "Informations- und Telekommunikationssysteme\u00B2\u207E";
			strings[0][5] = "Anwendungsentwicklung\u00B2\u207E";
			strings[0][6] = "Wirtschafts- und Geschäftsprozesse\u00B2\u207E";
			strings[0][7] = "Englisch\u00B2\u207E";
		} else {
			strings[0][0] = "Deutsch / Kommunikation";
			strings[0][1] = "Politik / Gesellschaftslehre";
			strings[0][3] = "Sport / Gesundheitsförderung";
			strings[0][4] = "Informations- und Telekommunikationssysteme";
			strings[0][5] = "Anwendungsentwicklung";
			strings[0][6] = "Wirtschafts- und Geschäftsprozesse";
			strings[0][7] = "Englisch";
		}
		strings[0][2] = "Religionslehre";

		for (int i = 0; i < 8; i++) {
			if (noten.get(i).getFach().getBezeichnung().startsWith("D")) {
				strings[1][0] = "- " + noten.get(i).getNote().getBeschreibung()
						+ " -";
			} else if (noten.get(i).getFach().getBezeichnung().startsWith("P")) {
				strings[1][1] = "- " + noten.get(i).getNote().getBeschreibung()
						+ " -";
			} else if (noten.get(i).getFach().getBezeichnung().startsWith("R")) {
				strings[1][2] = "- " + noten.get(i).getNote().getBeschreibung()
						+ " -";
			} else if (noten.get(i).getFach().getBezeichnung().startsWith("S")) {
				strings[1][3] = "- " + noten.get(i).getNote().getBeschreibung()
						+ " -";
			} else if (noten.get(i).getFach().getBezeichnung().startsWith("I")) {
				strings[1][4] = "- " + noten.get(i).getNote().getBeschreibung()
						+ " -";
			} else if (noten.get(i).getFach().getBezeichnung().startsWith("A")) {
				strings[1][5] = "- " + noten.get(i).getNote().getBeschreibung()
						+ " -";
			} else if (noten.get(i).getFach().getBezeichnung().startsWith("W")) {
				strings[1][6] = "- " + noten.get(i).getNote().getBeschreibung()
						+ " -";
			} else if (noten.get(i).getFach().getBezeichnung().startsWith("E")) {
				strings[1][7] = "- " + noten.get(i).getNote().getBeschreibung()
						+ " -";
			}
		}

		return strings;
	}
	/**
	 * Die Methode holeZeugnisNote gibt zu einem Zeugnis alles Zeugnispositionen zurück
	 * @param zeugnis Zeugnis-Objekt
	 * @throws Exception
	 */
	public void holeZeugnisnote(Zeugnis zeugnis) throws Exception {
		StandardDataProvider dataGetter = StandardDataProvider.getInstance();
		noten = dataGetter.gibPositionenZuZeugnis(zeugnis);
		if (noten.isEmpty()) {
			throw new Exception("Keine Noten vorhanden.");
		}

	} 
	


}
