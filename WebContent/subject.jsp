<%@page import="DAL.SubjectDAL"%>
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
<meta charset="ISO-8859-1">
<title>Subjects</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="CSS/bootstrap-5.1.1-dist/js/bootstrap.min.js"></script>
<link href="CSS/bootstrap-5.1.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="./JS/constants.js"></script>
<script src="./JS/app.js"></script>
<script src="./JS/subjects.js"></script>
</head>
<body onload="fetchPosts();storeData();getPosts();">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="#">Classroom</a>
	 <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
	<div class="collapse navbar-collapse" id="navbarNavDropdown">
		<ul class="navbar-nav">
			<li class="navbar-item active"><a class="nav-link" href="index.jsp">Home page</a></li>
			<li class="navbar-item active"><a class="nav-link" href="chat.jsp">Chat</a></li>
			<li class="navbar-item active"><a class="nav-link" href="./files.jsp">Files</a></li>
			<li class="navbar-item active"><a class="nav-link" href="help.jsp">Help</a></li>
			<li class="navbar-item active"><a class="nav-link" href="ActionHandlers/LogoutHandler.jsp">Logout</a></li>
		</ul>
	</div>

</nav>

<div class="mx-auto w-50">

	<%
		
	if(SubjectDAL.isUserOwner(Integer.parseInt(request.getParameter("id")), (Integer)session.getAttribute("user")))
	{
	
	%>
	<h2>Add users to class</h2>
	
	<input id="addUserInput" type="text" class="form-control mw-20" placeholder="Email">
	
	<button class="btn btn-primary mt-3 mb-3" onclick="addUserToClass(event)">Add user</button>
	
	<form>
	<%} %>
	<h2>Posts</h2>
	<textarea class="mt-3 form-contro" id="new-post" rows="4" cols="100"></textarea>
	<br><button class="btn btn-primary mt-3 mb-3" onclick="addPost(event)">Add Post</button>
	</form>
	
	<div id="posts">
	
	</div>
</div>
</body>
</html>