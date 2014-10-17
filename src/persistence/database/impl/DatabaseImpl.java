package persistence.database.impl;

import context.ManagerFactory;
import persistence.database.Database;
import persistence.importhandler.ImportHandler;
import persistence.importhandler.impl.DBConfig;

import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DatabaseImpl implements Database {
	private DBConfig dbConfig;
	private HashMap<Integer, double[]> knowledges;
	private String driver;

	public DatabaseImpl() {
		this.dbConfig = ((ImportHandler) ManagerFactory.getManager(ImportHandler.class)).getDBConfig();
		this.knowledges = new HashMap<Integer, double[]>();
	}

	public boolean createDatabase() {
		System.out.println("*********************************createDatabase \"" + dbConfig.getDbname()
				+ "\" start*********************************");
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(dbConfig.getDriver());
			System.out.println("Connecting to database: " + dbConfig.getHostUrl() + "...");
			conn = DriverManager.getConnection(dbConfig.getHostUrl(), dbConfig.getUser(), dbConfig.getPassword());
			stmt = conn.createStatement();
			DatabaseMetaData databaseMetaData = conn.getMetaData();
			ResultSet rs = databaseMetaData.getCatalogs();
			boolean flag = false;
			while (rs.next()) {
				if (rs.getString("TABLE_CAT").equals(dbConfig.getDbname()))
					flag = true;
			}

			if (!flag) {
				System.out.println("Creating database...");
				String sql = "CREATE DATABASE " + dbConfig.getDbname();
				System.out.println(sql);
				stmt.executeUpdate(sql);
				System.out.print("Database created successfully");
			} else {
				System.out.print("Database ist vorhanden");
			}
			System.out.println(": " + dbConfig.getDbname() + "...");
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		System.out.println("*********************************createDatabase \"" + dbConfig.getDbname()
				+ "\" ende*********************************");

		return createTable();
	}

	@Override
	public double[] select(int stateId) {
		if (knowledges.size() == 0) {
			// die daten aus db holen
			selectAll();
		}
		// wenn State nocht nicht vorhanden ist
		if (knowledges.get(stateId) == null) {
			double[] rewardsList = new double[dbConfig.getNumberOfActions()];
			for (int i = 0; i < dbConfig.getNumberOfActions(); i++) {
				rewardsList[i] = 0;
			}
			knowledges.put(stateId, rewardsList);
		}
		return knowledges.get(stateId);
	}

	// holt alle daten aus db
	private boolean selectAll() {
		System.out.println("*********************************selectAll \"" + dbConfig.getTablename()
				+ "\" start*********************************");
		Database db = ManagerFactory.getManager(Database.class);
		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName(dbConfig.getDriver());
			conn = DriverManager.getConnection(dbConfig.getDbUrl(), dbConfig.getUser(),
					dbConfig.getPassword());
			String query = "SELECT * FROM " + dbConfig.getTablename();
			System.out.println(query);
			// create the java statement
			stmt = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				int id = rs.getInt(dbConfig.getStateName());

				double[] rewardsList = new double[dbConfig.getNumberOfActions()];
				for (int i = 0; i < dbConfig.getNumberOfActions(); i++) {
					rewardsList[i] = rs.getDouble("A" + (i + 1));
				}
				knowledges.put(id, rewardsList);
			}
			// print the results
			printMap(knowledges);
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}
		System.out.println("*********************************selectAll \"" + dbConfig.getTablename()
				+ "\" ende*********************************");
		return true;
	}

	// print map
	private static void printMap(Map mp) {
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			System.out.println(pairs.getKey() + " = " + Arrays.toString((double[]) pairs.getValue()));
			it.remove(); // avoids a ConcurrentModificationException
		}
	}

	@Override
	public boolean update(int state, int action, double rewards) {
		double[] rewardsList = knowledges.get(state);
		rewardsList[action] = rewards;
		knowledges.put(state, rewardsList);
		return true;
	}

	public boolean insert(int state) {
		try {
			Class.forName(dbConfig.getDriver());
			Connection conn = DriverManager.getConnection(dbConfig.getDbUrl(), dbConfig.getUser(),
					dbConfig.getPassword());

			// the mysql insert statement
			String query = "INSERT INTO " + dbConfig.getTablename() + " (" + dbConfig.getStateName() + ", ";
			String values = " VALUES("+state + ", ";
			double [] rewardsList = knowledges.get(state); 
			for (int i = 0; i < dbConfig.getNumberOfActions() - 1; i++) {
				query += "A" + (i + 1) + ", ";
				values += rewardsList[i] + ",";
			}
			query += "A" + (dbConfig.getNumberOfActions()) + ")";
			values += rewardsList[dbConfig.getNumberOfActions()-1] + ")";
			query += values + " ON DUPLICATE KEY UPDATE A1 = 0.0, A2=0.0, A3=0.0";

			System.out.println(query);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query); 
			
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		return true;
	}

	@Override
	public boolean saveAll() {
		for (Integer key : knowledges.keySet()) {
			insert(key); 
		    //System.out.println("Key = " + key + " - " + hm.get(key));
		}
		return true;
	}

	public boolean createTable() {
		System.out.println("*********************************createTable \"" + dbConfig.getTablename()
				+ "\" start*********************************");
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(dbConfig.getDriver());
			// STEP 2: Open a connection to a datebase
			System.out.println("Connecting to a selected database: " + dbConfig.getDbname() + "...");
			conn = DriverManager.getConnection(dbConfig.getDbUrl(), dbConfig.getUser(), dbConfig.getPassword());
			DatabaseMetaData md = conn.getMetaData();
			System.out.println("Connected database successfully...");

			// STEP 3: Check the table. Delete Table, if exist
			ResultSet rs = md.getTables(null, null, "%", null);
			stmt = conn.createStatement();
			while (rs.next()) {
				// wenn die tabelle schon existiert
				// dann wird die geloescht
				if (rs.getString(3).equals(dbConfig.getTablename())) {
					String sql = "DROP TABLE " + dbConfig.getTablename();
					System.out.println(sql);
					stmt.executeUpdate(sql);
					System.out.println("Tabelle: " + dbConfig.getTablename() + " wurde geloescht...");
				}
			}

			System.out.println("Creating table in given database: " + dbConfig.getTablename() + "...");
			String sql = "CREATE TABLE " + dbConfig.getTablename() + "(" + dbConfig.getStateName()
					+ "  INTEGER not NULL, ";

			for (int i = 0; i < dbConfig.getNumberOfActions(); i++) {
				sql += " A" + (i + 1) + " DOUBLE, ";
			}
			sql += " PRIMARY KEY ( " + dbConfig.getStateName() + " ))";
			System.out.println(sql);
			stmt.executeUpdate(sql);

			System.out.println("Created table in given database: " + dbConfig.getTablename() + "...");
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		System.out.println("*********************************createTable \"" + dbConfig.getTablename()
				+ "\" ende*********************************");
		return true;
	}

}
