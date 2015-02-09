package testklassen;

import businesslogik.dataprovider.StandardDataProvider;
import objects.Ausbilder;
import objects.Betrieb;

public class TestJustin {

	public static void main(String[] args) {
		StandardDataProvider.changeDataProvider(StandardDataProvider.db_optionen.SQLITE.toString());
		StandardDataProvider provider = StandardDataProvider.getInstance();
		 
		provider.gibAlleAusbilder();
		
		Betrieb b = new Betrieb();
		b.setFirmenbezeichnung("LAchsnackenFirm");
		b.seteMail("adj@aol.de");
		b.setFax("dfjskdfj");
		b.setOrt("haltern");
		b.setPlz("42357");
		b.setStrasse("lembecker Straöe 43");
		b.setTelefon("02360642");
		provider.insert(b);
//		
//		Ausbilder a = new Ausbilder();
//		a.setEmail("shjdfh@aol.de");
//		a.setName("hford");
//		a.setVorname("henry");
//		a.setTelefon("02360643");
//		a.setBetrieb(b);
//		provider.insert(new String(""));
//		
//		provider.delete(a);
//		
//		b.seteMail("Beispielemail");
//		provider.update(b);
	}

}
