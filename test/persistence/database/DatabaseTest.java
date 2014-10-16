package persistence.database;

import static org.junit.Assert.*;

import org.junit.Test;

import persistence.importhandler.ImportHandler;
import context.ManagerFactory;

public class DatabaseTest {

	@Test
	public void testCreateTable() {
		Database db = ManagerFactory.getManager(Database.class);
		ImportHandler ih = ManagerFactory.getManager(ImportHandler.class);
		assertTrue(db.createTable(ih.getDBConfig()));
	}

}
