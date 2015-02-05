package objects;

/**
 * @author backs.kristin-anna
 * 
 *         Klasse zur Darstellung von Login-Daten. Diese Klasse enthaelt alle
 *         noetigen Attribute sowie getter- und setter-Methoden um auf diese
 *         zuzugreifen.
 */

public class Login {

	private String ID;
	private String loginName;

	private String loginPasswort;
	private Berechtigung berechtigung;
	
	public Login(String loginName, String loginPasswort) {
		super();
		this.loginName = loginName;
		this.loginPasswort = loginPasswort;
	}
	
	public Login() {
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPasswort() {
		return loginPasswort;
	}

	public void setLoginPasswort(String loginPasswort) {
		this.loginPasswort = loginPasswort;
	}

	public Berechtigung getBerechtigung() {
		return berechtigung;
	}

	public void setBerechtigung(Berechtigung berechtigung) {
		this.berechtigung = berechtigung;
	}

}
