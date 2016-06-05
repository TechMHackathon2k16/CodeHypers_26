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

import com.parkit.pojo.Place;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class PlaceData {
	final private String placeDataFilePath = "placeData.xml";
	
	public String writePlacetoXML(Place place) throws FileNotFoundException{
		String status = "FAILURE";
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("Place", Place.class);
		xstream.alias("PlacesData", Places.class);
		Places allPlaces = this.readAllPlacesfromXML();
		if(allPlaces == null){
			allPlaces = new Places();
		}
		allPlaces.addPlace(place);
		String allPlacesXML = xstream.toXML(allPlaces);
		FileOutputStream fos = null;
		try {
		    fos = new FileOutputStream(placeDataFilePath);
		    fos.write("<?xml version=\"1.0\"?>".getBytes("UTF-8")); //write XML header, as XStream doesn't do that for us
		    byte[] bytes = allPlacesXML.getBytes("UTF-8");
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

	public Places readAllPlacesfromXML(){
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("Place", Place.class);
		xstream.alias("PlacesData", Places.class);
		InputStream in;
		Places places;
		try {
			in = new FileInputStream(placeDataFilePath);
			places = (Places) xstream.fromXML(in);
		} catch (FileNotFoundException e) {
			places = null;
		}
		
		return places;
	}
	
	public String getAvailableCountofPlace(Place place){
		String status = "FAILURE";
		Places places = this.readAllPlacesfromXML();
		if(places == null){
			status = "No Data";
		}else{
			for(Place p : places.getPlaces()){
				if(p.getName().equals(place.getName())
						&& p.getLat().equals(place.getLat()) 
						&& p.getLng().equals(place.getLng())){
					status = "" + p.getAvailableCount();
					break;
				}else{
					status = "No Data";
				}
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
