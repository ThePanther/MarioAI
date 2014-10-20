package persistence.database;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

import persistence.importhandler.ImportHandler;
import persistence.importhandler.impl.DBConfig;
import context.ManagerFactory;

public class DatabaseTest {

	@Test
	public void testCreateDatabase() {
		Database db = ManagerFactory.getManager(Database.class);
		assertTrue(db.createDatabase());
		assertTrue(db.createDatabase());
		assertTrue(db.createDatabase());
		assertTrue(db.createDatabase());	
	}
	
	@Test
	public void testSelect(){
		Database db = ManagerFactory.getManager(Database.class);
		db.createDatabase();  
		double [] rewardsList = db.select(0);
		
		ImportHandler ih = ManagerFactory.getManager(ImportHandler.class);
		DBConfig dbConfig = ih.getDBConfig(); 
		
		assertTrue(rewardsList.length == dbConfig.getNumberOfActions());
		
		for (int i = 0; i < dbConfig.getNumberOfActions(); i++) {
			assertTrue(rewardsList[i] == 0.0);
		}		
		
	}
	
	@Test 
	public void testUpdate(){

		ImportHandler ih = ManagerFactory.getManager(ImportHandler.class);
		DBConfig dbConfig = ih.getDBConfig(); 
		
		
		Database db = ManagerFactory.getManager(Database.class);
		db.createDatabase(); 

		long state = 0;
		db.select(state); 
		
		int action = 1;
		int rewards = 10;
		boolean retValue = db.update(state, action, rewards); 
		
		double[] rewardsList = db.select(0);
		assertTrue(rewardsList[action] == rewards);
	}

	
	
	@Test
	public void testSaveAll() {
		Database db = ManagerFactory.getManager(Database.class);
		db.createDatabase(); 
		db.select(0); 
		db.select(1); 
		db.select(2); 
		db.select(3); 
		db.select(4); 
		db.select(0); 
		db.select(1); 
		db.select(2); 
		db.select(3); 
		db.select(4); 
		assertTrue(db.saveAll());
	}
	
	
	

}
