package Models;

import java.sql.Date;

public class Subject {

	private int id;
	private Date date_created;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Subject(int id,  String name,Date date_created) {
		super();
		this.id = id;
		this.date_created = date_created;
		this.name = name;
	}
	
	
	
}
