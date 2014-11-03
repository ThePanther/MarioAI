package la.rlGlue.common;

public class Reward {
	private int rid; 
	private String name; 
	private int reward; 
	public Reward(String name, int reward) {
		this.name = name; 
		this.reward = reward; 
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getReward() {
		return reward;
	}
	public void setReward(int reward) {
		this.reward = reward;
	}
	
	
	
}
