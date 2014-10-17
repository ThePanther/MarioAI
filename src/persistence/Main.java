package persistence;

import java.util.HashMap;

import context.ManagerFactory;
import persistence.database.Database;
import persistence.importhandler.ImportHandler;

public class Main {
	public static void main(String[] args) {		
		Database db = ManagerFactory.getManager(Database.class); 
		db.createDatabase(); 
		//db.selectAll();
//		
//		HashMap<Integer, double []> test = new HashMap<Integer, double[]>(); 
//		double[] arr = new double[3]; 
//		arr[0] = 1.0; 
//		test.put(1, arr);
//		System.out.println(test.get(1)[0]);
//		
	}

}
