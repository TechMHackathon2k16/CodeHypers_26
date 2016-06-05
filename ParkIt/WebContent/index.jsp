<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyPark</title>
</head>
<body>
<%
Cookie cookie = null;
Cookie[] cookies = null;
// Get an array of Cookies associated with this domain
cookies = request.getCookies();
int gotCookie = 0;
if( cookies != null ){
   //out.println("<h2> Found Cookies Name and Value</h2>");
   for (int i = 0; i < cookies.length; i++){
      cookie = cookies[i];
      //out.print("Name : " + cookie.getName( ) + ",  ");
      //out.print("Value: " + cookie.getValue( )+" <br/>");
      if(cookie.getName().equals("mobile")){
    	  /* cookie.setMaxAge(0);
          response.addCookie(cookie); */
    	  gotCookie = 1;
    	  //out.println("Found mobile Number : " + cookie.getValue());
      }if(cookie.getName().equals("name")){
    	  /* cookie.setMaxAge(0);
          response.addCookie(cookie); */
    	  gotCookie = 1;
    	  //out.println("Found mobile Number : " + cookie.getValue());
      }else{
    	  gotCookie = 0;
      }
   }
}else{
   //out.println("<h2>No cookies founds</h2>");
    gotCookie = 0;
}

if(gotCookie == 0){
	   %>
	   <jsp:include page="signup.jsp"></jsp:include> 
	 <%
}else if(gotCookie == 1){
	   %>
	   <jsp:include page="dashboard.jsp"></jsp:include> 
	 <%
}
%>
</body>
</html>