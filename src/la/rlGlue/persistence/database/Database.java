package la.rlGlue.persistence.database;

import java.util.List;

import la.rlGlue.common.Reward;
import la.rlGlue.common.RewardsGroup;
import la.rlGlue.common.State;
import la.rlGlue.common.Try;

public interface Database {
	
	public RewardsGroup getRewardsGroup(List<Reward> rewards); 
	
	public double[] select(State state, RewardsGroup rewardsGroup);
	
	boolean update(State state, RewardsGroup rewardsGroup, int action, double value);
	
	public boolean saveAll(Try aTry, RewardsGroup rewardsGroup);
	
	public List<Try> getTries(RewardsGroup rewardsGroup); 
	
	public RewardsGroup getLastRewardsGroup(); 
	
	public void reset(); 

}
