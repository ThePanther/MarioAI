package persistence.database.impl;

import context.ManagerFactory;
import persistence.database.Database;
import persistence.importhandler.ImportHandler;
import persistence.importhandler.impl.DBConfig;

import java.sql.*;

public class DatabaseImpl implements Database {
	private DBConfig dbConfig; 

	@Override
	public double[] select(int stateId) {
		DBConfig dbConfig = ((ImportHandler) ManagerFactory.getManager(ImportHandler.class)).getDBConfig();

		return null;
	}

	@Override
	public boolean update(int state, int action, double rewards) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveAll() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * 
	 * Erstellt eine neue Tabelle 
	 * wenn die Tabelle vorhanden ist, wird die gel�scht und dann neu erstellt.
	 * 
	 */
	public boolean createTable() {
		dbConfig = ((ImportHandler) ManagerFactory.getManager(ImportHandler.class)).getDBConfig();
		System.out.println("*********************************createTable start*********************************");
		
		//STEP 1: Create Database, if not exist
		createDatabase(dbConfig);

		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(dbConfig.getDriver());
			// STEP 2: Open a connection to a datebase
			System.out.println("Connecting to a selected database: "+ dbConfig.getDbname() +"...");
			conn = DriverManager.getConnection("jdbc:mysql://" + dbConfig.getDbhost() +"/"+ dbConfig.getDbname(), dbConfig.getUser(), dbConfig.getPassword());
			DatabaseMetaData md = conn.getMetaData();
			System.out.println("Connected database successfully...");
			
			// STEP 3: Check the table. Delete Table, if exist
			ResultSet rs = md.getTables(null, null, "%", null);
			stmt = conn.createStatement();
			while (rs.next()) {
				//wenn die tabelle schon existiert
				//dann wird die geloescht
				if (rs.getString(3).equals(dbConfig.getTablename())){
					String sql = "DROP TABLE " + dbConfig.getTablename(); 
					System.out.println(sql);
					stmt.executeUpdate(sql);
					System.out.println("Tabelle: "+ dbConfig.getTablename() +" wurde geloescht...");
				}
			}
			
			System.out.println("Creating table in given database: "+ dbConfig.getTablename() +"...");
			String sql = "CREATE TABLE " + dbConfig.getTablename() + "(" + dbConfig.getStateName() + "  INTEGER not NULL, ";

			for (int i = 0; i < dbConfig.getNumberOfActions(); i++) {
				sql += " A" + (i + 1) + " DOUBLE, ";
			}
			sql += " PRIMARY KEY ( " + dbConfig.getStateName() + " ))";
			System.out.println(sql);
			stmt.executeUpdate(sql);

			System.out.println("Created table in given database: "+ dbConfig.getTablename() +"...");
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
		System.out.println("*********************************createTable ende*********************************");
		return true; 
	}

	private void createDatabase(DBConfig dbConfig){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(dbConfig.getDriver());
			System.out.println("Connecting to database: "+ dbConfig.getUrl() +"...");
			conn = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUser(), dbConfig.getPassword());
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
			}else{
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
	}
	


}
