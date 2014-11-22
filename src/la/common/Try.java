package la.common;

public class Try {
	private int id; 
	private int win; 
	private double rewards; 
	private int steps;
	public Try(int id, int win, double rewards, int steps) {
		super();
		this.id = id;
		this.win = win;
		this.rewards = rewards;
		this.steps = steps;
	}
	public Try(int win, double rewards, int steps) {
		super();
		this.id = -1;
		this.win = win;
		this.rewards = rewards;
		this.steps = steps;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public double getRewards() {
		return rewards;
	}
	public void setRewards(double rewards) {
		this.rewards = rewards;
	}
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	
	
	
	
	
}
