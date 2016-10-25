package com.example.webmvc.model;

public class User{
	String name;
	String pass;

	public String toString(){
		return "name:"+name+";pass:"+pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}