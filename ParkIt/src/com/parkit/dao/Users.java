package com.parkit.dao;

import java.util.ArrayList;
import java.util.List;

import com.parkit.pojo.User;

public class Users {
	private List<User> users = new ArrayList<User>();

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
		this.users.add(user);
	}
	
	public void removeUser(User user){
		for(User u : users){
			if(u.getMobile().equals(user.getMobile())){
				users.remove(u);
			}
		}
	}
	
	public void editUser(User old_user, User new_user){
		for(User u : users){
			if(u.getMobile().equals(old_user.getMobile())){
				u.setMobile(new_user.getMobile());
				u.setName(new_user.getName());
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(User user : users){
			sb.append("\nUsers [");
			sb.append("\nUser {");
			sb.append(" Name: " + user.getName());
			sb.append(" , Mobile: " + user.getMobile());
			sb.append("}");
			sb.append("\n]");
		}
		return sb.toString();
	}
	
}
