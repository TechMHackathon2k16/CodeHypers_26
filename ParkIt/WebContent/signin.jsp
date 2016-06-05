<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html dir="ltr" lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyPark-SignIn</title>
<base href=".">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link href="css/common.css" type="text/css" rel="stylesheet">
<script src="js/less-1.7.4.min.js"></script>
<link href="css/font-awesome.min.css" type="text/css" rel="stylesheet">
<link href="css/summernote.css" rel="stylesheet">
<script type="text/javascript" src="js/summernote.js"></script>
<script src="js/moment.min.js" type="text/javascript"></script>
<script src="js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<link href="css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" media="screen">
<link type="text/css" href="css/stylesheet.css" rel="stylesheet" media="screen">
<script src="js/common.js" type="text/javascript"></script>
</head>
<body>
<div id="container">
<header id="header" class="navbar navbar-static-top">
<div class="navbar-header">
<a href="" class="navbar-brand">MyPark</a></div>
</header>
<div id="content">
	<div class="container-fluid"><br>
		<br>
		<div class="row">
			<div class="col-sm-offset-4 col-sm-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title"><i class="fa fa-lock"></i> Please enter below details.</h1>
					</div>
					
					<div class="panel-body">
						<form action="doSignIn" method="post">
					
						<div class="form-group" style="text-align: center;color: #C00;">
							<label for="input-name">
								<% 
									String status = (String) request.getAttribute("status");
									if(status == null){
										status = "";
									}
								%>
								<%= status %>
							</label>
						</div>
						<h2>Sign In</h2>
						<div class="form-group">
							<label for="input-mobile">Mobile No.</label>
							<div class="input-group"><span class="input-group-addon"><i class=" fa fa-phone"></i></span>
								<input type="text" name="mobile" value="" placeholder="Mobile No." id="input-mobile" class="form-control">
							</div>
						</div>
						<div class="text-left" style="float:left">
							<a class="btn btn-primary" href="signup.jsp"><i class="fa fa-key"></i> Go to SignUp</a>
						</div>
						<div class="text-right">
							<button type="submit" name="submit" class="btn btn-primary"><i class="fa fa-key"></i> SignIn</button>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</body>
</html>