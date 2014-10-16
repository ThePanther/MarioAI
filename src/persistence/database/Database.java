package persistence.database;

import persistence.importhandler.impl.DBConfig;

public interface Database {
		
	// Erstellt eine neue Tabelle oder ersetzt die alte. 
	public boolean createTable();
	
	// Gibt eine Liste von Rewards f�r ein bestimmten Zustand
	public double[] select(int stateId);
	
	// Fuegt oder aktualisiert die Daten 
	boolean update(int state, int action, double rewards); 	

	// Speichert neuen Wissenbasis in db
	public boolean saveAll();

	

}
