package la.persistence.database;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import la.common.Reward;
import la.common.RewardsGroup;
import la.common.State;
import la.common.Try;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.annotations.BeforeSuite;

import context.ManagerFactory;

public class DatabaseTest {


	private static Database db;
	private static ArrayList<Reward> rList1; 
	private static ArrayList<Reward> rList2; 
	@BeforeClass
	public static void initialize(){
		db = ManagerFactory.getManager(Database.class);
		db.reset();
		
		rList1 = new ArrayList<Reward>();
		rList1.add(new Reward("Sieg", 1000));

		rList2 = new ArrayList<Reward>();
		rList2.add(new Reward("Sieg", 1000));
		rList2.add(new Reward("Verletzt", 50));

	}
	
	//public RewardsGroup getRewardsGroup(List<Reward> rewards); 
	@Test
	public void getRewardsGroupTest() {
		//RewardsList leer
		assertNull(db.getRewardsGroup(new ArrayList<Reward>()));
		
		//RewardsList null
		assertNull(db.getRewardsGroup(null));
		
		//RewardsList 1 Element 
		RewardsGroup rg1 = db.getRewardsGroup(rList1);
		RewardsGroup rg1_copy = db.getRewardsGroup(rList1);
		//rg1 = rg1_copy
		assertTrue(rg1.equals(rg1_copy));
		
		//RewardsList 2 Elemente => new RewardsGroup 
		RewardsGroup rg2 = db.getRewardsGroup(rList2);
		//rg1 != rg2
		assertFalse(rg1.equals(rg2));
	}
	
//	public double[] select(State state, RewardsGroup rewardsGroup);
	@Test
	public void selectTest() {	
		
	}
	
//	boolean update(State state, RewardsGroup rewardsGroup, int action, double value);
	@Test
	public void updateTest() {	
		
	}
//	public boolean saveAll(Try aTry, RewardsGroup rewardsGroup);
	@Test
	public void saveAllTest() {	
		//Try aTry = null;
		//RewardsGroup rewardsGroup = null;
		//db.saveAll(aTry, rewardsGroup); 
	}
//	public List<Try> getTries(RewardsGroup rewardsGroup); 
	@Test
	public void getTriesTest() {	
		
	}	
//	public RewardsGroup getLastRewardsGroup(); 
	@Test
	public void getLastRewardsGroupTest() {	
		
	}	
//	public void reset(); 
	@Test
	public void resetTest() {	
		db.reset();
	}
}
