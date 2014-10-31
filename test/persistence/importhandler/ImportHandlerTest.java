package persistence.importhandler;

import static org.junit.Assert.*;

import org.junit.Test;

import context.ManagerFactory;

public class ImportHandlerTest {

	@Test
	public void testGetDBConfig() {
		ImportHandler ih = ManagerFactory.getManager(ImportHandler.class);
		assertNotNull(ih.getDBConfig());
		assertNotNull(ih.getDBConfig().getDbhost());
		assertNotNull(ih.getDBConfig().getDbname());
		assertNotNull(ih.getDBConfig().getDriver());
		assertNotNull(ih.getDBConfig().getPassword());
		assertNotNull(ih.getDBConfig().getUser());
	}

}
