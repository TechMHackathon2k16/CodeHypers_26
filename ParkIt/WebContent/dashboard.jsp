<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
Cookie[] cookies = null;
String userName = "";
cookies = request.getCookies();
if(cookies == null){
	out.print("no cookies");
	request.getRequestDispatcher("index.jsp").forward(request, response);
}else{
	for(Cookie ck : cookies){
		if(ck.getName().equals("name")){
			userName = ck.getValue();
		}
	}
	if(userName.equals("")){
		out.print("no name");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
%>
<!DOCTYPE html>
<html dir="ltr" lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<title>Dashboard</title>
<base href=".">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link href="css/common.css" type="text/css" rel="stylesheet"> 
<link href="css/font-awesome.min.css" type="text/css" rel="stylesheet">
<link href="css/summernote.css" rel="stylesheet">
<script type="text/javascript" src="js/summernote.js"></script>
<script src="js/moment.min.js" type="text/javascript"></script>
<script src="js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<link href="css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" media="screen">
<link type="text/css" href="css/stylesheet.css" rel="stylesheet" media="screen">
<script src="js/common.js" type="text/javascript"></script>
	<style>
      html, body {
        height: 100%;
		width: 480px;
        margin: 0 auto;
        padding: 0;
      }
      #map {
        height: 100%;
      }
      .controls {
        margin-top: 10px;
        border: 1px solid transparent;
        border-radius: 2px 0 0 2px;
        box-sizing: border-box;
        -moz-box-sizing: border-box;
        height: 32px;
        outline: none;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
      }

      #pac-input {
        background-color: #fff;
        font-family: Roboto;
        font-size: 15px;
        font-weight: 300;
        margin-left: 12px;
        padding: 0 11px 0 13px;
        text-overflow: ellipsis;
        width: 200px;
      }

      #pac-input:focus {
        border-color: #4d90fe;
      }

      .pac-container {
        font-family: Roboto;
      }

      #type-selector {
        color: #fff;
        background-color: #4d90fe;
        padding: 5px 11px 0px 11px;
      }

      #type-selector label {
        font-family: Roboto;
        font-size: 13px;
        font-weight: 300;
      }
      #target {
        width: 345px;
      }
    </style>
</head>
<body>

	<header id="header" class="navbar navbar-static-top">
		<div class="navbar-header">
		<a type="button" id="button-menu" class="pull-left"><i class="fa fa-indent fa-lg"></i></a>
		<a href="dashboard.jsp" class="navbar-brand">MyPark</a></div>
		<ul class="nav pull-right">
			<li><a href="LogOut"><span class="hidden-xs hidden-sm hidden-md">Logout</span> <i class="fa fa-sign-out fa-lg"></i></a></li>
		</ul>
	</header><input id="pac-input" class="controls" type="text" placeholder="Search Box">
    <div id="map"></div>
    <script>
      // This example adds a search box to a map, using the Google Place Autocomplete
      // feature. People can enter geographical searches. The search box will return a
      // pick list containing a mix of places and predicted search terms.

      // This example requires the Places library. Include the libraries=places
      // parameter when you first load the API. For example:
      // <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">
	var searchLoc;
	var searchPlace;
	var map;
	var markers = [];
	var infoWindows = [];
	var gblname='';
	var gblcount='';
	function checkPaymentOpt(){
	var hreff="https://www.instamojo.com/presidentialwheels/pay-presidential-wheels/?embed=form&data_Field_43346=073548-112&data_amount=50.00&data_email=anshuman.bhg%40gmail.com&data_name=Anshuman+Bhagwat&data_phone=9555684321&intent=buy";
	
	if($('#debit').is(':checked')){
	window.location.href = hreff;
	}
	else if($('#credit').is(':checked')){
	window.location.href = hreff;
	}
	else if($('#netBanking').is(':checked')){
	window.location.href = hreff;
	}
	else if($('#wallet').is(':checked')){
	
	}
	}
      function initAutocomplete() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: 28.6139, lng: 77.2090},
          zoom: 13,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        });

        // Create the search box and link it to the UI element.
        var input = document.getElementById('pac-input');
        var searchBox = new google.maps.places.SearchBox(input);
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

        // Bias the SearchBox results towards current map's viewport.
        map.addListener('bounds_changed', function() {
          searchBox.setBounds(map.getBounds());
        });

        
        // Listen for the event fired when the user selects a prediction and retrieve
        // more details for that place.
        searchBox.addListener('places_changed', function() {
          var places = searchBox.getPlaces();

          if (places.length == 0) {
            return;
          }

          // Clear out the old markers.
          markers.forEach(function(marker) {
            marker.setMap(null);
          });
          markers = [];
          infoWindows.forEach(function(infownd) {
              infownd.setMap(null);
            });
          infoWindows = [];

          // For each place, get the icon, name and location.
          var bounds = new google.maps.LatLngBounds();
          for(var i=0;i<1;i++) {
              var place = places[0];
            var icon = {
              url: place.icon,
              size: new google.maps.Size(71, 71),
              origin: new google.maps.Point(0, 0),
              anchor: new google.maps.Point(17, 34),
              scaledSize: new google.maps.Size(25, 25)
            };
			
			searchLoc = place.geometry.location;
			searchPlace = place;
			
            // Create a marker for each place.
            

            if (place.geometry.viewport) {
              // Only geocodes have viewport.
              bounds.union(place.geometry.viewport);
            } else {
              bounds.extend(place.geometry.location);
            }
          }
          map.fitBounds(bounds);
          google.maps.event.addListener(map, 'click', function() {
   		   //infowindow.setContent(place.name);
   		   infoWindows.forEach(function(infownd){
   			infownd.close();
   		   });
   		 });
		  positionSuccess();
        });
		
      }
	  
	  function positionSuccess(position) {
        // Centre the map on the new location
		var coords;
		var latLng;
		if(position == null || position == undefined || position == ""){
			//latLng = new google.maps.LatLng(28.5677,77.3259);
			console.log(new google.maps.LatLng(28.5677,77.3259));
			console.log(searchLoc.toString());
			latLng = searchLoc;//new google.maps.LatLng(28.5677,77.3259);
		}else{
			coords = position.coords || position.coordinate || position;
			latLng = new google.maps.LatLng(coords.latitude, coords.longitude);
		}
        map.setCenter(latLng);
        map.setZoom(16);

		//show marker for our main search Place
		ajaxGetCount(latLng.lat(),latLng.lng(),searchPlace.name);
        
        var request = {
            location: latLng,
            radius: 1000,
            types: ['parking']
        };
        var service = new google.maps.places.PlacesService(map);
        service.nearbySearch(request, callback);
        function callback(results, status) {
          if (status == google.maps.places.PlacesServiceStatus.OK) {
            for (var i = 0; i < results.length; i++) {
               //createMarker(results[i]);
               var p = results[i];
               var lt = p.geometry.location.lat();
               var ln = p.geometry.location.lng();
               var n = p.name;
               //console.log(lt +", "+ln+" , "+n);
               //console.log(p.geometry.location);
               ajaxGetCount(lt,ln,n);
           }
         }
       }
}		

       function createMarker(lat,lng,name,count) {
           console.log(lat+":"+lng+":"+name+":"+count);
	   //console.log(place);
			var placeLoc = new google.maps.LatLng(lat,lng);
			//console.log(placeLoc);
			gblname=name;
			gblcount=count;
			var contentString = '<div id="content">'+
					'<div id="bodyContent">'+name+
					'<br>Available: '+ count +' &nbsp;&nbsp;';
			if(!isNaN(count)){
				contentString += '<button type="button" id="bookTrigger" class="btn btn-info btn-sm" data-toggle="modal" data-target="#myModal">Book Now</button>';
			}
			contentString += 	
					'</div>'+
					'</div>';
			var infowindow = new google.maps.InfoWindow({
				content: contentString
			});
			//var parkingicon = 'https://maps.google.com/mapfiles/kml/shapes/parking_lot_maps.png';
			var parkingicon = 'park.png';
			var marker = new google.maps.Marker({
			map: map,
			position: placeLoc,
			title: name,
			icon: parkingicon,
			//label: 'P'
			});
			markers.push(marker);
			infoWindows.push(infowindow);
			

		 google.maps.event.addListener(marker, 'click', function() {
		   //infowindow.setContent(place.name);
		   infoWindows.forEach(function(infownd){
			infownd.close();
		   });
		   var index = markers.indexOf(marker);
		   infoWindows[index].open(map, this);
		 });

		 infoWindows[0].open(map, markers[0]);
}

function ajaxGetCount(lat,lng,name) {
	var count;
    $.ajax({
        type: "GET",
        url: "getCount",
        data: "name="+name+"&lat="+lat+"&lng="+lng,
        success: function(response) {
            if(response == null || response == "" || response == undefined)
            {
            	count = "Error";
            }else{
				count = response;
            }
            createMarker(lat,lng,name,count);
        },
        error: function() {
			count = "Error";
			createMarker(lat,lng,name,count);
        }
    });
}

function createSearchMarker(lat,lng,name,count){
	var marker = new google.maps.Marker({
        map: map,
        position: new google.maps.LatLng(lat,lng),
        title: 'there you are!'
    });
	gblname=name;
	gblcount=count;
	var contentString = '<div id="content">'+
	'<div id="bodyContent">'+name+
	'<br>Available: ' + count + ' &nbsp;&nbsp;<button type="button" id="bookTrigger" class="btn btn-info btn-sm" data-toggle="modal" data-target="#myModal">Book Now</button>'+
	'</div>'+
	'</div>';
	var infowindow = new google.maps.InfoWindow({
		content: contentString
	});
	infowindow.open(map, marker);
	google.maps.event.addListener(marker, 'click', function() {
	    infowindow.open(map, marker);
	});
	
}
/* $(document).ready(function(){
	$("#bookTrigger").click(function(){
		$("#placeName").html(gblname+'('+gblcount+')');
	});
}); */

    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA_0SVSVBQd29j_2YfCxUxrLiADYdVWTeE&libraries=places&&v=3.exp&callback=initAutocomplete"
         async defer></script>
	<nav id="column-left" class="">
		<div id="profile">
			
			<div><a class="dropdown-toggle" data-toggle="dropdown"><img src="images/ic_launcher.png" alt="demo demo" title="demo" class="img-circle"></a></div><h4 class="pull-right">
			<%= userName %>
			</h4>
		</div>
		<ul id="menu">
		  <li id="account" class="active"><a href="dashboard.jsp"><i class="fa fa-user fa-fw"></i> <span>My Account</span></a></li>
		  <li id="history" class="active"><a href="history.html"><i class="fa fa-history fa-fw"></i> <span>My Bookings</span></a></li>
		  <li id="services" class="active"><a href="service.html"><i class="fa fa-car fa-fw"></i> <span>Car Services</span></a></li>
		  <li id="invitation" class="active"><a href="#"><i class="fa fa-envelope-o fa-fw"></i> <span>Invite</span></a></li>
		  <li id="payment" class="active"><a href="#"><i class="fa fa-money fa-fw"></i> <span>Payment</span></a></li>
		  <li id="feedback" class="active"><a href="#"><i class="fa fa-comment-o fa-fw"></i> <span>FeedBack</span></a></li>
		  <li id="setings" class="active"><a href="#"><i class="fa fa-cogs fa-fw"></i> <span>Settings</span></a></li>
		  <li id="contact" class="active"><a href="contactus.html"><i class="fa fa-phone fa-fw"></i> <span>Contact Us</span></a></li>
		</ul>
	</nav>
		
	</div>
	
	 <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" id="placeName">GIP Parking (39 Available)</h4>
        </div>
        <div class="modal-body">
          <div class="list-group">
                                <a class="list-group-item">
                                    <i class="fa fa-clock-o fa-fw"></i>Time Slot
                                    <span class="pull-right text-muted small"><em>1 hour</em>
                                    </span>
                                </a>
                                <a  class="list-group-item">
                                    <i class="fa fa-rupee fa-fw"></i>Rate
                                    <span class="pull-right text-muted small"><em>50/- per hour</em>
                                    </span>
                                </a>
                                <a class="list-group-item">
                                    <i class="fa fa-bolt fa-fw"></i>Promo Code
                                    <span class="pull-right text-muted small" style="margin-top:-8px;"><input type="text" class="form-control ">
                                    </span>
                                    </span>
                                </a>
                                
                            </div>
        </div>
        <div class="modal-footer" style="text-align:center">
          <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#payModal">Book Now</button>
		  
        </div>
      </div>
    </div>
  </div>
  <div class="modal fade" id="payModal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">GIP Parking (39 Available)</h4>
        </div>
        <div class="modal-body">
          <div class="list-group">
                                <a  class="list-group-item">
                                    <i class="fa fa-inr fa-fw"></i> Pay by Wallet
                                    <span class="pull-right text-muted small"><input type="radio" name="optionsRadios" id="wallet" value="option1" checked="checked"/>
                                    </span>
                                </a>
                                <a  class="list-group-item">
                                    <i class="fa fa-credit-card fa-fw"></i> Pay by Debit Card
                                    <span class="pull-right text-muted small"><input type="radio" name="optionsRadios" id="debit" value="option1" />
                                    </span>
                                </a>
                                <a  class="list-group-item">
                                    <i class="fa fa-cc-mastercard fa-fw"></i> Pay by Credit card
                                    <span class="pull-right text-muted small"><input type="radio" name="optionsRadios" id="credit" value="option1" />
                                    </span>
                                   
                                </a>
								     <a class="list-group-item">
                                    <i class="fa fa-money fa-fw"></i> Pay by Net Banking
                                    <span class="pull-right text-muted small"><input type="radio" name="optionsRadios" id="netBanking" value="option1" />
                                    </span>
                                </a>
                                
                            </div>
        </div>
        <div class="modal-footer" style="text-align:center">
          <a class="btn btn-primary" onclick="checkPaymentOpt();" data-toggle="modal" data-target="#qrModal">Pay Now</a>
		  <div class="modal fade" id="qrModal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">GIP Parking (39 Available)</h4>
        </div>
        <div class="modal-body">
          <div class="list-group">
                            <label style="color:green">Payment through wallet Successful !</label> <br>
								<img src="images/qa.png" style="width:21%" ><br><br>
                                     <p>Date/Time of Booking : 5th June 2016 09:00pm
									 <label style="color:red">Show above QA Code at the time of Entry</label>
									</p>
          </div>
        </div>
        <div class="modal-footer" style="text-align:center">
          <button type="button" class="btn btn-primary" onclick="location.reload()" >OK</button>
		  
        </div>
      </div>
    </div>
  </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>