package la.common;

import java.util.HashMap;

public class Try {
	private int id; 
	private int win; 
	private double rewards; 
	private int steps;
	private int reward_win_count;
	private int reward_death_count;
	private int reward_hurt_count;
	private int reward_kill_count; 
	private int reward_elapsed_frame_count;
	private int reward_move_right_count;
	private int reward_move_left_count;
	private int reward_move_up_count;
	private int reward_move_down_count;
	
	public Try(int id, int win, double rewards, int steps) {
		super();
		this.id = id;
		this.win = win;
		this.rewards = rewards;
		this.steps = steps;
        setReward_win_count(0);
        setReward_death_count(0);
        setReward_hurt_count(0);
        setReward_kill_count(0);
        setReward_elapsed_frame_count(0);
        setReward_move_right_count(0);
        setReward_move_left_count(0);
        setReward_move_up_count(0);
        setReward_move_down_count(0);
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
	public int getReward_win_count() {
		return reward_win_count;
	}
	public void setReward_win_count(int reward_win_count) {
		this.reward_win_count = reward_win_count;
	}
	public int getReward_death_count() {
		return reward_death_count;
	}
	public void setReward_death_count(int reward_death_count) {
		this.reward_death_count = reward_death_count;
	}
	public int getReward_hurt_count() {
		return reward_hurt_count;
	}
	public void setReward_hurt_count(int reward_hurt_count) {
		this.reward_hurt_count = reward_hurt_count;
	}
	public int getReward_kill_count() {
		return reward_kill_count;
	}
	public void setReward_kill_count(int reward_kill_count) {
		this.reward_kill_count = reward_kill_count;
	}
	public int getReward_elapsed_frame_count() {
		return reward_elapsed_frame_count;
	}
	public void setReward_elapsed_frame_count(int reward_elapsed_frame_count) {
		this.reward_elapsed_frame_count = reward_elapsed_frame_count;
	}
	public int getReward_move_right_count() {
		return reward_move_right_count;
	}
	public void setReward_move_right_count(int reward_move_right_count) {
		this.reward_move_right_count = reward_move_right_count;
	}
	public int getReward_move_left_count() {
		return reward_move_left_count;
	}
	public void setReward_move_left_count(int reward_move_left_count) {
		this.reward_move_left_count = reward_move_left_count;
	}
	public int getReward_move_up_count() {
		return reward_move_up_count;
	}
	public void setReward_move_up_count(int reward_move_up_count) {
		this.reward_move_up_count = reward_move_up_count;
	}
	public int getReward_move_down_count() {
		return reward_move_down_count;
	}
	public void setReward_move_down_count(int reward_move_down_count) {
		this.reward_move_down_count = reward_move_down_count;
	}

	
}
