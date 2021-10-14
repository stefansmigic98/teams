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
<title>Files</title>

<script type="text/javascript" src="CSS/bootstrap-5.1.1-dist/js/bootstrap.min.js"></script>
<link href="CSS/bootstrap-5.1.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="./JS/constants.js"></script>
<script src="./JS/files.js"></script>
</head>
<body onload="getFilesForSubject()">
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
<div class="mx-auto w-50 h-100">


<form class="my-3">
	<label>File to stash:</label>
	<input class="form-control-file" id="selectFiles" type="file" name="file" required />
	<button class="btn btn-primary" onclick="uploadFile(event)">Upload</button>
</form>

<form>
	<input type="text" id="folder-name" class="form-control" placeholder="New folder name"/>
	<button class="btn btn-primary my-3" onclick="createNewFolder(event)" >Create new folder</button>
</form>
<button class="btn btn-danger" onclick="goBack()">Back</button>

<table class="table" >
	<thead>
		<tr>
			<th>Name</th>
			<th>Download</th>
		</tr>
	</thead>
	<tbody id="filesContainer">
		
	</tbody>
</table>
</div>
</body>
</html>