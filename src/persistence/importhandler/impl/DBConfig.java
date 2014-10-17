package persistence.importhandler.impl;

public class DBConfig {

	private String dbhost;
	private String dbname;
	private String user; 
	private String password;
	private String tablename;
	private String stateName;
	private int numberOfActions;
	private String driver; 
	private String hostUrl; 
	private String dbUrl; 
	
	public DBConfig(String dbhost, String dbname, String user, String password, String tablename, String stateName, int numberOfActions, String driver, String hostUrl) {
		super();
		this.dbhost = dbhost;
		this.dbname = dbname;
		this.user = user;
		this.password = password;
		this.tablename = tablename; 
		this.stateName = stateName; 
		this.numberOfActions = numberOfActions; 
		this.driver = driver; 
		this.hostUrl =hostUrl; 
		this.setDbUrl(hostUrl + dbname); 
		
	}

	public String getDbhost() {
		return dbhost;
	}

	public void setDbhost(String dbhost) {
		this.dbhost = dbhost;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public int getNumberOfActions() {
		return numberOfActions;
	}

	public void setNumberOfActions(int numberOfActions) {
		this.numberOfActions = numberOfActions;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	
	
}
