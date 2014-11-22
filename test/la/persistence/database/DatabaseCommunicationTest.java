package la.persistence.database;

import org.junit.Test;

import context.ManagerFactory;
import la.persistence.database.impl.DBCommunication;
import la.persistence.importhandler.ImportHandler;

public class DatabaseCommunicationTest {

	
	@Test 
	public void dropDabaseTest(){
		
		ImportHandler ih = ManagerFactory.getManager(ImportHandler.class); 
		DBCommunication dbCommunication = new DBCommunication(ih.getDBConfig());
		dbCommunication.dropTables();
	}
}
