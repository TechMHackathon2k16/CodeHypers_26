package com.parkit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parkit.dao.PlaceData;
import com.parkit.pojo.Place;

/**
 * Servlet implementation class GetAvailabilityofPlace
 */
public class GetAvailabilityofPlace extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAvailabilityofPlace() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("name");
		String lat = request.getParameter("lat");
		String lng = request.getParameter("lng");
		PlaceData placeData = new PlaceData();
		Place place = new Place();
		place.setName(name);
		place.setLat(lat);
		place.setLng(lng);
		String count = placeData.getAvailableCountofPlace(place);
		PrintWriter pw = response.getWriter();
		pw.print(count);
	}


}
