package la.common;

import java.util.List;

public class RewardsGroup {
	private int id; 
	private List<Reward> rewards;
	public RewardsGroup(int id, List<Reward> rewards) {
		super();
		this.id = id;
		this.rewards = rewards;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Reward> getRewards() {
		return rewards;
	}
	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	} 
	
	
}
