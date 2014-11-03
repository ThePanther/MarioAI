package la.rlGlue.persistence.database;

import la.rlGlue.common.Reward;
import la.rlGlue.common.RewardsGroup;
import la.rlGlue.common.State;
import la.rlGlue.common.Try;

public interface Database {
	
	public RewardsGroup getRewardsGroup(Reward[] rewards); 
	
	public double[] select(State state, RewardsGroup rewardsGroup);
	
	boolean update(State state, RewardsGroup rewardsGroup, int action, double value);
	
	public boolean saveAll();
	
	public Try[] getTries(RewardsGroup rewardsGroup); 
	
	public void reset(); 

}
