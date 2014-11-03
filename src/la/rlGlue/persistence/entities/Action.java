package la.rlGlue.persistence.entities;

public class Action {

	private int aid; 
	private String name;
	
	public Action(int aid, String name) {
		super();
		this.aid = aid;
		this.name = name;
	}
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
}
