package com.parkit.pojo;

public class User {
	private String name;
	private String mobile;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nUser [");
		sb.append(" Name: " + name);
		sb.append(" , Mobile: " + mobile);
		sb.append("]");
		return sb.toString();
	}
	
}
