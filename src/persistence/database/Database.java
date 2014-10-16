package persistence.database;

import persistence.importhandler.impl.DBConfig;

public interface Database {
		
	// Erstellt eine neue Tabelle oder ersetzt die alte. 
	public boolean createTable(DBConfig dbConfig);
	
	// Gibt eine Liste von Rewards für ein bestimmten Zustand
	public double[] select(int stateId);
	
	// Fügt oder aktualisiert die Daten 
	public boolean insert(int state, int action, double rewards);
	
	// Speichert neuen Wissenbasis in db
	public boolean saveAll(); 
	

}
