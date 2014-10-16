package persistence;

import context.ManagerFactory;
import persistence.database.Database;
import persistence.importhandler.ImportHandler;

public class Main {
	public static void main(String[] args) {		
		Database db = ManagerFactory.getManager(Database.class); 
		db.createTable(); 
	}

}
