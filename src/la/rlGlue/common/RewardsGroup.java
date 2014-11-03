package la.rlGlue.common;

import java.util.Arrays;

public class RewardsGroup {
	private int id; 
	private Reward [] rewards; 
	private Try [] tries; 
	public RewardsGroup(int id, Reward[] rewards) {
		this.id = id; 
		this.rewards = rewards; 
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Reward[] getRewards() {
		return rewards;
	}
	public void setRewards(Reward[] rewards) {
		this.rewards = rewards;
	}
	public Try[] getTries() {
		return tries;
	}
	public void setTries(Try[] tries) {
		this.tries = tries;
	}
	@Override
	public String toString() {
		return " id = " + id + " " + Arrays.toString(rewards);
	}
}
