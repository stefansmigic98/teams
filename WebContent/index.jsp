<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
if (session.getAttribute("user") == null)
{
	response.sendRedirect("./login.jsp");  
}
	
	
%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="CSS/bootstrap-5.1.1-dist/js/bootstrap.min.js"></script>
<link href="CSS/bootstrap-5.1.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />


<link href="CSS/app.css" rel="stylesheet" type="text/css" />

<meta charset="ISO-8859-1">
<title>Classroom</title>
<script src="./JS/constants.js"></script>
<script src="./JS/app.js"></script>
<script src="./JS/chat.js"></script>

</head>

<body onload="fetchSubjects();storeUserId();connectToScoket()">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="#">Classroom</a>
	 <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
	<div class="collapse navbar-collapse" id="navbarNavDropdown">
		<ul class="navbar-nav">
			<li class="navbar-item active"><a class="nav-link" href="index.jsp">Home page</a></li>
			<li class="navbar-item active"><a class="nav-link" href="chat.jsp">Chat</a></li>
			<li class="navbar-item active"><a class="nav-link" href="help.jsp">Help</a></li>
			<li class="navbar-item active"><a class="nav-link" href="ActionHandlers/LogoutHandler.jsp">Logout</a></li>
		</ul>
	</div>

</nav>
<div class="mx-auto w-50">
	<h2>Create subject</h2>
	
	<div class="form-group">
	       		<label for="password" class="text-info">New subject:</label><br>
	        	
	        	<input id="newSubjectName" type="text" class="form-control input-text"  placeholder="Subject name">
	</div>
	
	<br>
	<button class="btn btn-info" onclick="createSubject(event)">Create new subject</button>
	<div id="subjectsContainer" ></div>
</div>
</body>
</html>