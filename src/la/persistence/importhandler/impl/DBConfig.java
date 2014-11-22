package la.persistence.importhandler.impl;

public class DBConfig {

	private String dbhost;
	private String dbname;
	private String user; 
	private String password;
	private String driver; 
	private String hostUrl; 
	private String dbUrl; 
	
	public DBConfig(String dbhost, String dbname, String user, String password, String driver, String hostUrl) {
		super();
		this.dbhost = dbhost;
		this.dbname = dbname;
		this.user = user;
		this.password = password;
		this.driver = driver; 
		this.hostUrl = hostUrl; 
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
	
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	//jdbc:mysql://localhost/
	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}
	//jdbc:mysql://localhost/marioai2
	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	
	
}
