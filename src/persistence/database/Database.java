package persistence.database;

public interface Database {
	
	// Gibt eine Liste von Rewards f�r ein bestimmten Zustand
	public double[] select(int stateId);
	
	// F�gt oder aktualisiert die Daten 
	public boolean insert(int state, int action, double rewards);
	
	// Loescht alle Datenschaetze aus db 
	public boolean removeAll(); 
	
	// Speichert neuen Wissenbasis in db
	public boolean saveAll(); 
	
}
