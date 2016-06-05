package com.parkit.main;

import java.io.FileNotFoundException;

import com.parkit.dao.PlaceData;
import com.parkit.dao.UserData;
import com.parkit.dao.Users;
import com.parkit.pojo.Place;
import com.parkit.pojo.User;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		User user = new User();
		user.setName("ad");
		user.setMobile("123");
		System.out.println(user.toString());
		Users users = new Users();
		users.addUser(user);
		System.out.println(users.toString());
		System.out.println(new UserData().writeUsertoXML(user));
		//////////////////////////////////////
		Place place = new Place();
		PlaceData placesData = new PlaceData();
		place.setName("The Great India Place");
		place.setLat("28.5677473");
		place.setLng("77.32590260000006");
		place.setTotalCount(50);
		place.setAvailableCount(39);
		placesData.writePlacetoXML(place);
	}

}
