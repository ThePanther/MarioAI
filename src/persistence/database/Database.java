package persistence.database;

import persistence.importhandler.impl.DBConfig;

public interface Database {
		
	// Erstellt eine neue Datenbank, wenn die DB nicht existiert
	// und (erzeugt)/(ersetzt die alte) Tabelle
	public boolean createDatabase();
	
	// Gibt eine Liste von Rewards zurück
	public double[] select(int stateId);
	
	// Insert/Update 
	boolean update(int state, int action, double rewards); 	

	// Speichert neuen Wissenbasis in db
	public boolean saveAll();

}
