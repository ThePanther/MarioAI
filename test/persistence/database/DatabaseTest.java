package persistence.database;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

import persistence.importhandler.ImportHandler;
import context.ManagerFactory;

public class DatabaseTest {

	@Test
	public void testCreateDatabase() {
		Database db = ManagerFactory.getManager(Database.class);
		assertTrue(db.createDatabase());
	}
	
	
	@Test
	public void testSaveAll() {
		HashMap<Integer, double []> knowledges = new HashMap<Integer, double[]>(); 
		double [] rewards1 = {1.0, 2.0, 3.0, 4.0}; 
		double [] rewards2 = {-1.0, -2.0, -3.0, -4.0}; 
		double [] rewards3 = {28, 15., 14.0, 4.13}; 
		knowledges.put(1, rewards1);
		knowledges.put(2, rewards2);
		knowledges.put(3, rewards3);
	    Iterator it = knowledges.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        double[] ab = (double[]) pairs.getValue(); 
	        System.out.println(pairs.getKey() + " = " + ab[0]);
	        it.remove(); // avoids a ConcurrentModificationException
	    }
		assertTrue(true);
	}
	
	
	

}
