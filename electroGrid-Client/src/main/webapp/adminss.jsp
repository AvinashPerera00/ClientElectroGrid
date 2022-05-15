<%@page import="com.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/admins.js"></script>

<style type="text/css">
body {
	background-image: url("images/blubs.png");
	background-position: center;
	background-repeat: repeat;
	background-attachment: fixed;
}

.opa {
opacity: 0.8;
}

.buttonStyle{
	width: 100%;
}

.txtStyles{
	font-family:Comic Sans MS;
}
</style>
</head>
<body>

		<div class="container opa">
			<div class="row">
				
					<div class="card border-0 shadow rounded-3 my-5">
						<div class="card-body p-4 p-sm-5">
							<h2 class="card-title text-center mb-5 fw-light fs-12 text-warning txtStyles">Admin
								Managment</h2>




							<form id="formItem" name="formItem">
								Admin Name: <input id="name" name="name" type="text"
									class="form-control form-control-sm"> <br> Admin
								Email: <input id="email" name="email" type="text"
									class="form-control form-control-sm"> <br> Admin
								Phone: <input id="phone" name="phone" type="text"
									class="form-control form-control-sm"> <br> Admin
								Username: <input id="username" name="username" type="text"
									class="form-control form-control-sm"> <br> Admin
								Password: <input id="password" name="password" type="password"
									class="form-control form-control-sm"> <br> <input
									id="btnSave" name="btnSave" type="button" value="Save"
									class="btn btn-warning btn-rounded-pill rounded-pill btnTxt buttonStyle border border-info"
				data-mdb-ripple-color="dark"> <input type="hidden"
									id="hidItemIDSave" name="hidItemIDSave" value="">
							</form>
							<br>

							<div id="alertSuccess" class="alert alert-success"></div>
							<br>
							<div id="alertError" class="alert alert-danger"></div>

							<br>
							<div id="divItemsGrid">
								<%
								Admin adminObj = new Admin();
								out.print(adminObj.readItems());
								%>
							</div>
						</div>
					</div>

				</div>

			</div>
	
	

</body>
</html>