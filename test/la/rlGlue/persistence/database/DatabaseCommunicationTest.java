package la.rlGlue.persistence.database;

import org.junit.Test;

import context.ManagerFactory;
import la.rlGlue.persistence.database.impl.DBCommunication;
import la.rlGlue.persistence.importhandler.ImportHandler;

public class DatabaseCommunicationTest {

	
	@Test 
	public void dropDabaseTest(){
		
		ImportHandler ih = ManagerFactory.getManager(ImportHandler.class); 
		DBCommunication dbCommunication = new DBCommunication(ih.getDBConfig());
		dbCommunication.dropTables();
	}
}
