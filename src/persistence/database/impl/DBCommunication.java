package persistence.database.impl;

import java.util.*;
import java.sql.*;

import persistence.entities.Knowledge;
import persistence.entities.Reward;
import persistence.entities.RewardsGroup;
import persistence.entities.State;
import persistence.importhandler.impl.DBConfig;

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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void newTableRewardsGroup() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_REWARDSGROUP
				+ "(rgid INT NOT NULL AUTO_INCREMENT, "
				+ " PRIMARY KEY ( rgid )) ";
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

	public RewardsGroup getRewardsGroup(Reward[] rewards) throws SQLException {
		// openDB();
		Set<Integer> result = new HashSet<Integer>();
		for (int i = 0; i < rewards.length; i++) {
			if (i == 0)
				result.addAll(getRewardsGroups(rewards[i].getName(),
						rewards[i].getReward()));
			result.retainAll(getRewardsGroups(rewards[i].getName(),
					rewards[i].getReward()));
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
		for (int i = 0; i < rewards.length; i++) {
			insertReward(newRewardsGroup.getRewards()[i], rewardsGroupId);
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

	/*
	 * INSERT INTO `marioai`.`knowledge` (`state`, `rgid`, `a1`, `a2`, `a3`,
	 * `a4`, `a5`, `a6`, `a7`, `a8`, `a9`, `a10`, `a11`, `a12`) VALUES
	 * ('100000100002', '2', '12', '12', '1212', '12', '12', '12', '12', '12',
	 * '121', '21', '12', '21');
	 */
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
			// pstmt.setLong(1, stateId);
			// pstmt.setInt(2, rewardsGroupId);
			// String rest = "";
			// for (int i = 0; i < rewardsList.length; i++) {
			// // pstmt.setDouble(i + 3, rewardsList[i]);
			// if (i+1 == rewardsList.length){
			// rest += "a"+(i+1)+"= VALUES(a"+(i+1)+") ;";
			// }else{
			// rest += "a"+(i+1)+"= VALUES(a"+(i+1)+"), ";
			// }
			// }
			// sql += " ON DUPLICATE KEY UPDATE " + rest;
			// System.out.println("*****************" + sql);
			// INSERT INTO `marioai2`.`reward` (`rid`, `rgid`, `name`, `reward`)
			// VALUES ('1', '1', 'rechts', '1')
			// ON DUPLICATE KEY UPDATE name='jooo'
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
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rewardGroupID;
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

}