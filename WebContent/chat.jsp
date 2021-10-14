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
<script type="text/javascript" src="CSS/bootstrap-5.1.1-dist/js/bootstrap.min.js"></script>
<link href="CSS/bootstrap-5.1.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="CSS/app.css" rel="stylesheet" type="text/css" />

<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="./JS/constants.js"></script>
<script src="./JS/chat.js"></script>
</head>
<body onload="connectToScoket();fetchInbox()">
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

 
 <main class="mx-auto w-50 h-100" >
    <div >

			<div class="row g-0">
				<div id="inbox-list" class="col-12 col-lg-5 col-xl-3 border-right">

					<div class="px-4 d-none d-md-block">
						<div class="d-flex align-items-center">
							<div class="flex">
								<input type="text" id="new-user" class="form-control my-3" placeholder="Enter username">
								<button class="btn btn-primary mb-3" onclick="newChat()">New message</button>
								
							</div>
						</div>
					</div>

				
					
					<hr class="d-block d-lg-none mt-1 mb-0">
				</div>
				<div class="col-12 col-lg-7 col-xl-9">
					<div class="py-2 px-4 border-bottom d-none d-lg-block">
						<div class="d-flex align-items-center py-1">
							
						<label class="text-primary ml-3"  id="sender-name"></label>
							
						</div>
					</div>

					<div class="position-relative">
						<div id="chat-area" class="chat chat-messages p-4">

						</div>
					</div>

					<div class="flex-grow-0 py-3 px-4 border-top">
						<div class="input-group">
							<input type="text" id="new-message" class="form-control" placeholder="Type your message">
							<button onclick="sendMessage(event)" class="btn btn-primary">Send</button>
						</div>
					</div>

				
			</div>
		</div>
	</div>
</main>
</body>
</html>