package com.parkit.dao;

import java.util.ArrayList;
import java.util.List;

import com.parkit.pojo.Place;

public class Places {
	private List<Place> places = new ArrayList<Place>();

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}
	
	public void addPlace(Place place){
		this.places.add(place);
	}
	
	public void removePlace(Place place){
		for(Place p : this.places){
			if(p.getName().equals(place.getName()) 
					&& p.getLat().equals(place.getLat()) 
					&& p.getLng().equals(place.getLng()) 
					&& p.getTotalCount() == place.getTotalCount()){
				places.remove(p);
			}
		}
	}
	
	public void editPlace(Place oldPlace, int newCount){
		for(Place p : this.places){
			if(p.getName().equals(oldPlace.getName()) 
					&& p.getLat().equals(oldPlace.getLat()) 
					&& p.getLng().equals(oldPlace.getLng()) 
					&& p.getTotalCount() == oldPlace.getTotalCount()){
				p.setAvailableCount(newCount);
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Place place : places){
			sb.append("\nPlaces [");
			sb.append("\nPlace {");
			sb.append(" Name: " + place.getName());
			sb.append(" , Lat: " + place.getLat());
			sb.append(" , Lng: " + place.getLng());
			sb.append(" , AvailableCount: " + place.getAvailableCount());
			sb.append(" , TotalCount: " + place.getTotalCount());
			sb.append("}");
			sb.append("\n]");
		}
		return sb.toString();
	}
}
