package com.parkit.pojo;

public class Place {
	private String name;
	private String lat;
	private String lng;
	private int availableCount;
	private int totalCount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public int getAvailableCount() {
		return availableCount;
	}
	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nPlace [");
		sb.append(" Name: " + name);
		sb.append(" , Lat: " + lat);
		sb.append(" , Lng: " + lng);
		sb.append(" , AvailableCount: " + availableCount);
		sb.append(" , TotalCount: " + totalCount);
		sb.append("]");
		return sb.toString();
	}
}
