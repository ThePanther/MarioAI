package la.persistence.entities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Knowledge {
	
	// state -> {rewardsGroup -> {}}
	private HashMap<Long, Map<Integer, RewardsForAction>> knowledes;  
	
	public Knowledge() {
		knowledes = new HashMap<Long, Map<Integer,RewardsForAction>>();
	}
	
	public boolean isEmpty(){
		return knowledes.size() == 0; 
	}

	public RewardsForAction getRewardsList(Long state, int rewardsGroup) {
		return knowledes.get(state).get(rewardsGroup);
	}
	

	public boolean isExist(long state, int rewardsGroup) {
		if (knowledes.get(state) == null)
			return false; 
		return knowledes.get(state).get(rewardsGroup) != null;
	}
	
	public void put(long state, int rewardsGroup, double [] actionValues, boolean change){
		Map<Integer, RewardsForAction> rewardsGroupActionsMap = new HashMap<Integer, RewardsForAction>();
		rewardsGroupActionsMap.put(rewardsGroup, new RewardsForAction(actionValues, change));
		this.knowledes.put(state, rewardsGroupActionsMap); 
	}
	
//	@Override
//	public String toString() {
//		String resultStr = ""; 
//		Iterator it = knowledes.keySet().iterator();
//		while (it.hasNext()){
//			long stateID = (Long) it.next(); 
//		    resultStr += " STATE = " + stateID + " "; 
//		    Iterator it2 = knowledes.get(stateID).keySet().iterator(); 
//		    while (it2.hasNext()){
//		    	int rgID = (Integer) it2.next(); 
//			    resultStr += " Rewards Group  = " + rgID + " \n";
//			    resultStr += Arrays.toString(knowledes.get(stateID).get(rgID));
//		    }
//		}
//		return resultStr + "\n";
//	}

	public Set<Long> keySet() {
		return knowledes.keySet();
	}

	public Map<Integer, RewardsForAction> get(long stateID) {
		return knowledes.get(stateID);
	}

	public void setAllRewardsForAction(boolean b) {
		Iterator it = knowledes.keySet().iterator();
		while (it.hasNext()){
			long stateID = (Long) it.next(); 
		    Iterator it2 = knowledes.get(stateID).keySet().iterator(); 
		    while (it2.hasNext()){
		    	int rgID = (Integer) it2.next(); 
		    	knowledes.get(stateID).get(rgID).setChange(false);
		    }
		}
	}
}
