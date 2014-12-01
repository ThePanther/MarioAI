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

	public Reward getReward(String name) {
		Reward returnReward = null;

		for(Reward reward : rewards) {
			if(reward.getName().equals(name)) {
				returnReward = reward;
			}
		}

		return returnReward;
	}

	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	} 
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((rewards == null) ? 0 : rewards.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RewardsGroup other = (RewardsGroup) obj;
		if (id != other.id)
			return false;
		if (rewards == null) {
			if (other.rewards != null)
				return false;
		} else if (!rewards.equals(other.rewards))
			return false;
		return true;
	}
}
