package la.persistence.entities;

import java.util.Map;

public class RewardsForAction {
	private double[] actionValues;
	private boolean change;

	public RewardsForAction(double[] actionValues, boolean change) {
		this.setActionValues(actionValues); 
		this.setChange(change); 
	}

	public double[] getActionValues() {
		return actionValues;
	}

	public void setActionValues(double[] actionValues) {
		this.actionValues = actionValues;
	}

	public boolean isChange() {
		return change;
	}

	public void setChange(boolean change) {
		this.change = change;
	}
	
	
}
