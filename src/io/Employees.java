package io;

public class Employees {
	private int id;
	private String login_pass;
	
	public Employees(int id, String login_pass) {
		this.id = id;
		this.login_pass = login_pass;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin_pass() {
		return login_pass;
	}
	public void setLogin_pass(String login_pass) {
		this.login_pass = login_pass;
	}
	
}
