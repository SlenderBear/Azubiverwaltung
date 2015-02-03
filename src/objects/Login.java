package objects;

public class Login {

	private String ID;
	private String loginName;
	private String loginPasswort;
	private short IDRechte;

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

	public short getIDRechte() {
		return IDRechte;
	}

	public void setIDRechte(short iDRechte) {
		IDRechte = iDRechte;
	}

}
