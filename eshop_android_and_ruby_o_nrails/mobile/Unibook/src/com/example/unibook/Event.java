package com.example.unibook;

import java.sql.Date;

import android.text.format.DateFormat;

public class Event {
	private int userId;
	private int id;
	private String name;
	//private DateFormat date ;
	private String location;
	private String description;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//public DateFormat getDate() {
		//return date;
	//}
	//public void setDate(DateFormat date) {
		//this.date = date;
	//}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
