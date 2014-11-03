package persistence.database;

import static org.junit.Assert.*;

import org.junit.Test;

import context.ManagerFactory;
import persistence.database.impl.DBCommunication;
import persistence.importhandler.ImportHandler;
import persistence.importhandler.impl.DBConfig;

public class DatabaseCommunicationTest {

	
	@Test 
	public void dropDabaseTest(){
		
		ImportHandler ih = ManagerFactory.getManager(ImportHandler.class); 
		DBCommunication dbCommunication = new DBCommunication(ih.getDBConfig());
		dbCommunication.dropTables();
	}
}
