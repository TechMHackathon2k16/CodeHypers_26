package com.parkit.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parkit.dao.PlaceData;
import com.parkit.pojo.Place;

/**
 * Servlet implementation class LoadPlaces
 */
public class LoadPlaces extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadPlaces() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PlaceData placesData = new PlaceData();
		Place place = new Place();
		place.setName("The Great India Place");
		place.setLat("28.5677473");
		place.setLng("77.32590260000006");
		place.setTotalCount(50);
		place.setAvailableCount(39);
		placesData.writePlacetoXML(place);
		place.setName("GIP Mall Parking");
		place.setLat("28.5665989");
		place.setLng("77.32464200000004");
		place.setTotalCount(31);
		place.setAvailableCount(55);
		placesData.writePlacetoXML(place);
		place.setName("Parking");
		place.setLat("28.5735601");
		place.setLng("77.33282250000002");
		place.setTotalCount(23);
		place.setAvailableCount(40);
		placesData.writePlacetoXML(place);
		place.setName("201301");
		place.setLat("28.5673186");
		place.setLng("77.32348239999999");
		place.setTotalCount(27);
		place.setAvailableCount(35);
		placesData.writePlacetoXML(place);
		place.setName("Open Car and Bike Parking");
		place.setLat("28.5691477");
		place.setLng("77.3230916");
		place.setTotalCount(25);
		place.setAvailableCount(36);
		placesData.writePlacetoXML(place);
		place.setName("Noida Sector 28 Parking");
		place.setLat("28.5684766");
		place.setLng("77.32988869999997");
		place.setTotalCount(34);
		place.setAvailableCount(45);
		placesData.writePlacetoXML(place);
		place.setName("SAB Mall Parking");
		place.setLat("28.5736225");
		place.setLng("77.32402879999995");
		place.setTotalCount(16);
		place.setAvailableCount(70);
		placesData.writePlacetoXML(place);
	}

}
