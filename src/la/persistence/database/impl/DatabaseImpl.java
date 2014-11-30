package la.persistence.database.impl;

import context.ManagerFactory;
import la.persistence.database.Database;
import la.persistence.entities.Knowledge;
import la.common.Reward;
import la.common.RewardsGroup;
import la.common.State;
import la.common.Try;
import la.persistence.importhandler.ImportHandler;
import la.persistence.importhandler.impl.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DatabaseImpl implements Database {

	private static final int NUM_ACTIONS = 12;
	private Knowledge knowledge;
	private DBCommunication dbCommunication;

	public DatabaseImpl() {
		DBConfig dbConfig = ((ImportHandler) ManagerFactory
				.getManager(ImportHandler.class)).getDBConfig();
		this.dbCommunication = new DBCommunication(dbConfig);
		this.knowledge = new Knowledge();
	}

	@Override
	public RewardsGroup getRewardsGroup(List<Reward> rewards) {
			if (rewards == null)
				return null; 
			if (rewards.size() == 0)
				return null; 
			
			RewardsGroup result = null;
			try {
				result = dbCommunication.getRewardsGroup(rewards);
				//result.setTries(dbCom);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
	}

	@Override
	public double[] select(State state, RewardsGroup rewardsGroup) {
		if (state == null)
			return null; 
		if (rewardsGroup == null)
			return null;
		
		if (knowledge.isEmpty()) {
			//System.out.println("Knowledge is empty");
			knowledge = dbCommunication.selectKnowledge(rewardsGroup);
		}
	
		if (!knowledge.isExist(state.getStateId(), rewardsGroup.getId())) {
			double[] rewardsList = new double[NUM_ACTIONS];
			for (int i = 0; i < NUM_ACTIONS; i++) {
				rewardsList[i] = 0;
			}
			knowledge.put(state.getStateId(), rewardsGroup.getId(), rewardsList);
		}
		return knowledge.getRewardsList(state.getStateId(), rewardsGroup.getId());
	}

	@Override
	public boolean update(State state, RewardsGroup rewardsGroup, int action,
			double value) {
		double[] rewardsList = knowledge.getRewardsList(state.getStateId(), rewardsGroup.getId());
		rewardsList[action] = value;
		knowledge.put(state.getStateId(), rewardsGroup.getId(), rewardsList);
		return true;
	}

	@Override
	public boolean saveAll(Try aTry, RewardsGroup rewardsGroup) {
		
		dbCommunication.insertTry(aTry, rewardsGroup); 
		dbCommunication.updateRewardsGroup(rewardsGroup);
		Iterator it = knowledge.keySet().iterator();
		while (it.hasNext()){
			long stateID = (Long) it.next(); 
		    Iterator it2 = knowledge.get(stateID).keySet().iterator(); 
		    while (it2.hasNext()){
		    	int rgID = (Integer) it2.next(); 
			    double[] rewardsList = knowledge.getRewardsList(stateID, rgID);
				dbCommunication.inserKnowledge(stateID, rgID, rewardsList);		
		    }
		}
		
		
		return true;
	}

	@Override
	public void reset() {
		dbCommunication.dropTables();
	}

	@Override
	public List<Try> getTries(RewardsGroup rewardsGroup) {
		return dbCommunication.selectTries(rewardsGroup);
	}

	@Override
	public RewardsGroup getLastRewardsGroup() {
		RewardsGroup result = null;
		try {
			result = dbCommunication.getLastRewardsGroup();
			//result.setTries(dbCom);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


}
