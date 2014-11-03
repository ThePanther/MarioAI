package persistence.database.impl;

import context.ManagerFactory;
import persistence.database.Database;
import persistence.entities.Knowledge;
import persistence.entities.Reward;
import persistence.entities.RewardsGroup;
import persistence.entities.Action;
import persistence.entities.State;
import persistence.entities.Try;
import persistence.importhandler.ImportHandler;
import persistence.importhandler.impl.DBConfig;

import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
	public RewardsGroup getRewardsGroup(Reward[] rewards) {
		RewardsGroup result = null;
		try {
			result = dbCommunication.getRewardsGroup(rewards);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public double[] select(State state, RewardsGroup rewardsGroup) {
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
	public boolean saveAll() {
		
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
	public Try[] getTries(RewardsGroup rewardsGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		dbCommunication.dropTables();
	}

}
