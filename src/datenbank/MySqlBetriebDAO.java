package datenbank;

import java.util.ArrayList;

import objects.Betrieb;

public class MySqlBetriebDAO implements StandardMySqlDAO<Betrieb> {
	private MySQLConnector connector = new MySQLConnector();

	@Override
	public Betrieb insert(Betrieb b) {
		String guid = connector.getNewGUID();
		String sql = "INSERT INTO betrieb values(" 
				+ guid 
				+ ",'" + b.getFirmenbezeichnung() 
				+ ",'" + b.getStrasse()
				+ ",'" + b.getPlz() 
				+ ",'" + b.getOrt() 
				+ ")";
		connector.statementExecute(sql);
		b.setID(guid);
		return b;
	}

	@Override
	public boolean update(Betrieb t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Betrieb t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Betrieb> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
