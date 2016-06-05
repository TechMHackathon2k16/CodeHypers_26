package com.parkit.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;

import com.parkit.pojo.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class UserData {
	
	final private String userDataFilePath = "userData.xml";
	
	public String writeUsertoXML(User user) throws FileNotFoundException{
		String status = "FAILURE";
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("User", User.class);
		xstream.alias("UsersData", Users.class);
		Users allUsers = this.readAllUsersfromXML();
		if(allUsers == null){
			allUsers = new Users();
		}
		allUsers.addUser(user);
		String allUsersXML = xstream.toXML(allUsers);
		FileOutputStream fos = null;
		try {
		    fos = new FileOutputStream(userDataFilePath);
		    fos.write("<?xml version=\"1.0\"?>".getBytes("UTF-8")); //write XML header, as XStream doesn't do that for us
		    byte[] bytes = allUsersXML.getBytes("UTF-8");
		    fos.write(bytes);
		    status = "SUCCESS";
		} catch(Exception e) {
		    e.printStackTrace(); // this obviously needs to be refined.
		} finally {
		    if(fos!=null) {
		        try{ 
		            fos.close();
		        } catch (IOException e) {
		            e.printStackTrace(); // this obviously needs to be refined.
		        }
		    }
		}
		return status;
	}

	public Users readAllUsersfromXML(){
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("User", User.class);
		xstream.alias("UsersData", Users.class);
		InputStream in;
		Users users;
		try {
			in = new FileInputStream(userDataFilePath);
			users = (Users) xstream.fromXML(in);
		} catch (FileNotFoundException e) {
			users = null;
		}
		
		return users;
	}
	
	public User getUserByMobileNumber(String mobile){
		User user = null;
		Users users = this.readAllUsersfromXML();
		for(User u : users.getUsers()){
			if(u.getMobile().equals(mobile)){
				user = u;
				break;
			}
		}
		return user;
	}
	
	public String validateUser(User user){
		String status = "FAILURE";
		Users users = this.readAllUsersfromXML();
		if(users == null){
			status = "SUCCESS";
		}else{
			int count = 0;
			for(User u : users.getUsers()){
				if(u.getMobile().equals(user.getMobile())){
					status = "FAILURE";
					count++;
					break;
				}
			}
			if(count==0){
				status = "SUCCESS";
			}
		}
		return status;
	}
	
	public String validateLogin(String mobile){
		String status = "FAILURE";
		Users users = this.readAllUsersfromXML();
		if(users == null){
			status = "FAILURE";
		}else{
			int count = 0;
			for(User u : users.getUsers()){
				if(u.getMobile().equals(mobile)){
					count++;
					break;
				}
			}
			if(count>0){
				status = "SUCCESS";
			}
		}
		return status;
	}
	
	public String formatXml(String xml){
		   
	      try{
	         Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();
	         
	         serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	         serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	         
	         Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
	         StreamResult res =  new StreamResult(new ByteArrayOutputStream());            
	         
	         serializer.transform(xmlSource, res);
	         
	         return new String(((ByteArrayOutputStream)res.getOutputStream()).toByteArray());
	         
	      }catch(Exception e){
	         return xml;
	      }
	   }
}
