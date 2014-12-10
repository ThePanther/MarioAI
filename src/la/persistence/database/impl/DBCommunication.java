package la.persistence.database.impl;

import java.util.*;
import java.sql.*;

import la.persistence.entities.Knowledge;
import la.common.Reward;
import la.common.RewardsGroup;
import la.common.State;
import la.common.Try;
import la.persistence.importhandler.impl.DBConfig;

public class DBCommunication {

	private Connection conn = null;
	private Statement stmt = null;
	private String dbname;
	private String user;
	private String password;
	private String driver;
	private String hostUrl;
	private String dbUrl;

	private static final int NUM_ACTIONS = 12;
	private static final String TABLE_KNOWLEDGE = "knowledge";
	private static final String TABLE_REWARDSGROUP = "rewardsGroup";
	private static final String TABLE_REWARD = "reward";
	private static final String TABLE_TRY = "try";

	private synchronized void openDB() throws SQLException {
		conn = DriverManager.getConnection(dbUrl, user, password);
		stmt = conn.createStatement();
	}

	private synchronized void closeDB() {
		try {
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DBCommunication(DBConfig dbConfig) {
		this.dbname = dbConfig.getDbname();
		this.user = dbConfig.getUser();
		this.password = dbConfig.getPassword();
		this.driver = dbConfig.getDriver();
		this.hostUrl = dbConfig.getHostUrl();
		this.dbUrl = dbConfig.getDbUrl();
		try {
			Class.forName(driver);
			System.out.println("Connecting to database...");
			System.out.println("at " + hostUrl);
			System.out.println("Checking if database " + dbname + " exists...");
			conn = DriverManager.getConnection(hostUrl, user, password);
			if (!databaseExists(dbname)) {
				// if not exists create a new DB
				System.out.println("Not Exists. Creating a new DB...");
				stmt = conn.createStatement();
				newDatabase(dbname);
			}
			closeDB();
			System.out
					.println("Updating URL and reconnecting to DB..." + dbUrl);

			openDB();
			System.out.println("Connection successful.");
			System.out.println("Attempt to create table");
			newTables();
			System.out.println("DB Configuration successful");
			// closeDB();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean databaseExists(String dbname) throws Exception {
		ResultSet dbnames = conn.getMetaData().getCatalogs();
		while (dbnames.next()) {
			String dbcandidate = dbnames.getString(1);
			if (dbcandidate.equals(dbname)) {
				return true;
			}
		}
		return false;
	}

	private void newDatabase(String dbname) throws Exception {
		String sql = "CREATE DATABASE " + dbname;
		stmt.executeUpdate(sql);
	}

	private void newTables() throws Exception {
		newTableRewardsGroup();
		newTableKnowledge();
		newTableReward();
		newTableTry();
	}

	public void dropTables() {
		// String sql = "DROP DATABASE IF EXISTS " + dbname;
		String sql1 = " DROP TABLE IF EXISTS " + TABLE_KNOWLEDGE;
		String sql2 = " DROP TABLE IF EXISTS " + TABLE_REWARD;
		String sql3 = " DROP TABLE IF EXISTS " + TABLE_TRY;
		String sql4 = " DROP TABLE IF EXISTS " + TABLE_REWARDSGROUP;

		try {
			stmt.executeUpdate(sql1);
			stmt.executeUpdate(sql2);
			stmt.executeUpdate(sql3);
			stmt.executeUpdate(sql4);
			
			
			
			
			
			
			
			
			
			
			
			
			try {
				Class.forName(driver);
				System.out.println("Connecting to database...");
				System.out.println("at " + hostUrl);
				System.out.println("Checking if database " + dbname + " exists...");
				conn = DriverManager.getConnection(hostUrl, user, password);
				if (!databaseExists(dbname)) {
					// if not exists create a new DB
					System.out.println("Not Exists. Creating a new DB...");
					stmt = conn.createStatement();
					newDatabase(dbname);
				}
				closeDB();
				System.out
						.println("Updating URL and reconnecting to DB..." + dbUrl);

				openDB();
				System.out.println("Connection successful.");
				System.out.println("Attempt to create table");
				newTables();
				System.out.println("DB Configuration successful");
				// closeDB();
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void newTableRewardsGroup() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_REWARDSGROUP
				+ "(rgid INT NOT NULL AUTO_INCREMENT, "
				+ "	lastTime TIMESTAMP, " + " PRIMARY KEY ( rgid )) ";
		System.out.println(sql);
		stmt.executeUpdate(sql);
	}

	private void newTableKnowledge() throws SQLException {
		String actions = "";
		for (int i = 1; i <= NUM_ACTIONS; i++) {
			actions += "a" + i + " DOUBLE, ";
		}
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_KNOWLEDGE
				+ "(state BIGINT(20) NOT NULL, " + " rgid INT NOT NULL,  "
				+ actions + " PRIMARY KEY ( state, rgid ), "
				+ " CONSTRAINT FOREIGN KEY (rgid) REFERENCES `"
				+ TABLE_REWARDSGROUP + "` (rgid) ) ";
		System.out.println(sql);
		stmt.executeUpdate(sql);
	}

	private void newTableTry() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_TRY
				+ "(tid INT NOT NULL AUTO_INCREMENT, " + " win INT, "
				+ " rewards DOUBLE, " + " steps INT, "
				+ " rgid INT NOT NULL,  "
				+ " reward_win_count INT DEFAULT 0," 
				+ " reward_death_count INT DEFAULT 0,"
		        + " reward_hurt_count INT DEFAULT 0,"
		        + " reward_kill_count INT DEFAULT 0,"
		        + " reward_elapsed_frame_count INT DEFAULT 0," 
		        + " reward_move_right_count INT DEFAULT 0,"
		        + " reward_move_left_count INT DEFAULT 0,"
		        + " reward_move_up_count INT DEFAULT 0,"
		        + " reward_move_down_count INT DEFAULT 0,"
				+ "PRIMARY KEY ( tid, rgid ), "
				+ " CONSTRAINT FOREIGN KEY (rgid) REFERENCES `"
				+ TABLE_REWARDSGROUP + "` (rgid) ) ";
		System.out.println(sql);
		stmt.executeUpdate(sql);

	}

	private void newTableReward() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_REWARD
				+ "(rid INT NOT NULL AUTO_INCREMENT, "
				+ " rgid INT NOT NULL,  " + " name VARCHAR(20), "
				+ " reward INT, " + " PRIMARY KEY ( rid, rgid ), "
				+ " CONSTRAINT FOREIGN KEY (rgid) REFERENCES `"
				+ TABLE_REWARDSGROUP + "` (rgid) ) ";
		System.out.println(sql);
		stmt.executeUpdate(sql);
	}

	public RewardsGroup getRewardsGroup(List<Reward> rewards)
			throws SQLException {
		// openDB();
		Set<Integer> result = new HashSet<Integer>();
		for (int i = 0; i < rewards.size(); i++) {
			if (i == 0){
				result.addAll(getRewardsGroups(rewards.get(i).getName(),rewards.get(i).getReward()));
			}
			result.retainAll(getRewardsGroups(rewards.get(i).getName(), rewards.get(i).getReward()));
		}
		int rewardsGroupId = 0;
		if (result.size() != 0) {
			for (Integer i : result)
				rewardsGroupId = i;
			RewardsGroup oldRewardsGroup = new RewardsGroup(rewardsGroupId,
					rewards);
			return oldRewardsGroup;
		}
		rewardsGroupId = insertRewardGroup();
		RewardsGroup newRewardsGroup = new RewardsGroup(rewardsGroupId, rewards);
		for (int i = 0; i < rewards.size(); i++) {
			insertReward(newRewardsGroup.getRewards().get(i), rewardsGroupId);
		}
		// closeDB();
		return newRewardsGroup;
	}

	private Set<Integer> getRewardsGroups(String rewardsName, int rewards)
			throws SQLException {
		Set<Integer> result = new HashSet<Integer>();
		String sql = " SELECT rgid FROM " + TABLE_REWARD + " WHERE name='"
				+ rewardsName + "' AND reward=" + rewards;
		// System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			result.add(rs.getInt(1));
		}
		return result;
	}

	public void inserKnowledge(long stateId, int rewardsGroupId,
			double[] rewardsList) {
		PreparedStatement pstmt;
		try {
			// openDB();
			// REPLACE INTO knowledge SET state=2, rgid=2 ,a1=1000
			String sql = "REPLACE INTO " + TABLE_KNOWLEDGE + " SET "
					+ " state= " + stateId + ", " + " rgid= " + rewardsGroupId
					+ ", " + " a1 = " + rewardsList[0] + ", " + " a2 = "
					+ rewardsList[1] + ", " + " a3 = " + rewardsList[2] + ", "
					+ " a4 = " + rewardsList[3] + ", " + " a5 = "
					+ rewardsList[4] + ", " + " a6 = " + rewardsList[5] + ", "
					+ " a7 = " + rewardsList[6] + ", " + " a8 = "
					+ rewardsList[7] + ", " + " a9 = " + rewardsList[8] + ", "
					+ " a10 = " + rewardsList[9] + ", " + " a11 = "
					+ rewardsList[10] + ", " + " a12 = " + rewardsList[11];
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException(
						"Creating user failed, no rows affected.");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateRewardsGroup(RewardsGroup rewardsGroup) {
		// TODO Auto-generated method stub
		// UPDATE `marioai`.`rewardsGroup` SET `lastTime` = now() WHERE
		// `rewardsGroup`.`rgid` = 1;
		try {
			String sql = "UPDATE " + TABLE_REWARDSGROUP
					+ " SET lastTime = now() WHERE rgid = "
					+ rewardsGroup.getId();
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);;
			int affectedRows;
			affectedRows = pstmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private synchronized int insertRewardGroup() {
		int rewardGroupID = 0;
		PreparedStatement pstmt;
		try {
			String sql = "INSERT INTO " + TABLE_REWARDSGROUP
					+ " (rgid) VALUES (NULL)";
			// System.out.println(sql);
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException(
						"Creating user failed, no rows affected.");
			}
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					rewardGroupID = generatedKeys.getInt(1);
				} else {
					throw new SQLException(
							"Creating user failed, no ID obtained.");
				}
			}
			RewardsGroup rw = new RewardsGroup(rewardGroupID, null); 
			updateRewardsGroup(rw); 
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rewardGroupID;
	}
	

	public synchronized void insertTry(List<Try> aTryList, RewardsGroup rewardsGroup) {
		PreparedStatement pstmt;
		try {
			//(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
			String values = "";
			for (Try aTry : aTryList){
				values += "( Null, " +aTry.getWin() + ", "
									 +aTry.getRewards() + ", " 
									 +aTry.getSteps() + ", "
									 +rewardsGroup.getId() + ", "
									 +aTry.getReward_win_count() + ", "
									 +aTry.getReward_death_count() + ", "
									 +aTry.getReward_hurt_count() + ", "
									 +aTry.getReward_kill_count() + ", "
									 +aTry.getReward_elapsed_frame_count() + ", "
									 +aTry.getReward_move_right_count() + ", "
									 +aTry.getReward_move_left_count() + ", "
									 +aTry.getReward_move_up_count() + ", "
									 + aTry.getReward_move_down_count() 
									 +"),"; 
			}
			String sql = "INSERT INTO "
					+ TABLE_TRY
					+ " (tid, win, rewards, steps, rgid, reward_win_count, reward_death_count, reward_hurt_count, "
					+ "reward_kill_count, reward_elapsed_frame_count, reward_move_right_count, reward_move_left_count, "
					+ "reward_move_up_count, reward_move_down_count) VALUES " + values.substring(0, values.length()-1);
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);								
			int affectedRows = pstmt.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException(
						"Creating user failed, no rows affected.");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	synchronized void insertTry(Try aTry, RewardsGroup rewardsGroup) {
		PreparedStatement pstmt;
		try {

			String sql = "INSERT INTO "
					+ TABLE_TRY
					+ " (tid, win, rewards, steps, rgid, reward_win_count, reward_death_count, reward_hurt_count, "
					+ "reward_kill_count, reward_elapsed_frame_count, reward_move_right_count, reward_move_left_count, "
					+ "reward_move_up_count, reward_move_down_count) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			// System.out.println(sql);
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, aTry.getWin());
			pstmt.setDouble(2, aTry.getRewards());
			pstmt.setInt(3, aTry.getSteps());
			pstmt.setInt(4, rewardsGroup.getId());
			pstmt.setInt(5, aTry.getReward_win_count());
			pstmt.setInt(6, aTry.getReward_death_count());
			pstmt.setInt(7, aTry.getReward_hurt_count());
			pstmt.setInt(8, aTry.getReward_kill_count());
			pstmt.setInt(9, aTry.getReward_elapsed_frame_count()); 
			pstmt.setInt(10, aTry.getReward_move_right_count()); 
			pstmt.setInt(11, aTry.getReward_move_left_count());
			pstmt.setInt(12, aTry.getReward_move_up_count());
			pstmt.setInt(13, aTry.getReward_move_down_count());
																					
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException(
						"Creating user failed, no rows affected.");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private synchronized void insertReward(Reward reward, int rewardsGroupId) {
		PreparedStatement pstmt;
		try {
			String sql = "INSERT INTO " + TABLE_REWARD
					+ " (rgid, name, reward) VALUES (?, ?, ?)";
			// System.out.println(sql);
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, rewardsGroupId);
			pstmt.setString(2, reward.getName());
			pstmt.setInt(3, reward.getReward());

			int affectedRows = pstmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException(
						"Creating user failed, no rows affected.");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Knowledge selectKnowledge(RewardsGroup rewardsGroup) {
		Knowledge knowledge = new Knowledge();
		try {
			// openDB();
			String sql = " SELECT * FROM " + TABLE_KNOWLEDGE + " WHERE rgid="
					+ rewardsGroup.getId();
			// System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				long stateId = rs.getLong(1);
				double[] actionValues = new double[NUM_ACTIONS];
				for (int i = 0; i < NUM_ACTIONS; i++) {
					actionValues[i] = rs.getDouble((i + 2));
				}
				State state = new State(stateId);
				knowledge.put(state.getStateId(), rewardsGroup.getId(),
						actionValues);

			}
			// closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return knowledge;
	}
	
	public List<Try> selectTries(RewardsGroup rewardsGroup){
		List<Try> tries = new ArrayList<Try>();
		try {
			// openDB();
			String sql = " SELECT * FROM " + TABLE_TRY + " WHERE rgid="+
					+ rewardsGroup.getId();
			// System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt(1);
				int win = rs.getInt(2);
				double rewards = rs.getDouble(3);
				int steps = rs.getInt(4);
				/*
				 * private int reward_win_count;
	private int reward_death_count;
	private int reward_hurt_count;
	private int reward_kill_count; 
	private int reward_elapsed_frame_count;
	private int reward_move_right_count;
	private int reward_move_left_count;
	private int reward_move_up_count;
	private int reward_move_down_count;
				 */
				//Try(int id, int win, double rewards, int steps)
				Try aTry = new Try(id, win, rewards, steps); 
				aTry.setReward_win_count(rs.getInt(6));
				aTry.setReward_death_count(rs.getInt(7));
				aTry.setReward_hurt_count(rs.getInt(8));
				aTry.setReward_kill_count(rs.getInt(9));
				aTry.setReward_elapsed_frame_count(rs.getInt(10));
				aTry.setReward_move_right_count(rs.getInt(11));
				aTry.setReward_move_left_count(rs.getInt(12));
				aTry.setReward_move_up_count(rs.getInt(13));
				aTry.setReward_move_down_count(rs.getInt(14));
				tries.add(aTry);

			}
			// closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tries;	
	}

	public RewardsGroup getLastRewardsGroup() throws SQLException {
		RewardsGroup result = null;
		List<Reward> rewardsList = new ArrayList<Reward>();
		// SELECT * FROM reward WHERE rgid =(
		// SELECT rgid
		// FROM rewardsGroup
		// WHERE lastTime=(
		// SELECT max(lastTime) FROM rewardsGroup
		// )
		// )
		String sql = " SELECT * FROM " + TABLE_REWARD
				+ " WHERE rgid =( SELECT rgid FROM " + TABLE_REWARDSGROUP
				+ " WHERE lastTime=( SELECT max(lastTime) FROM "
				+ TABLE_REWARDSGROUP + " ))";
//		System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);
		int rgid = 0;
		while (rs.next()) {
			rgid = rs.getInt(2);
			String name = rs.getString(3);
			int reward = rs.getInt(4);
			rewardsList.add(new Reward(name, reward));
		}
		result = new RewardsGroup(rgid, rewardsList);
		return result;
	}

}