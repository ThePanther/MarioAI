package persistence.database;

import java.util.HashMap;

import persistence.entities.Knowledge;
import persistence.entities.Reward;
import persistence.entities.RewardsGroup;
import persistence.entities.State;
import persistence.entities.Try;
import persistence.importhandler.impl.DBConfig;

public interface Database {
	
	public RewardsGroup getRewardsGroup(Reward[] rewards); 
	
	public double[] select(State state, RewardsGroup rewardsGroup);
	
	boolean update(State state, RewardsGroup rewardsGroup, int action, double value);
	
	public boolean saveAll();
	
	public Try[] getTries(RewardsGroup rewardsGroup); 

}
