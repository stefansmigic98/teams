<%@page import="java.io.Console"%>
<%@page import="DAL.UserDAL"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="Models.User" %>
<jsp:useBean id="login_user" class="Models.User"></jsp:useBean>
<jsp:setProperty property="*" name="login_user" ></jsp:setProperty> 
<%
	int id = UserDAL.login(login_user.getEmail(), login_user.getPassword());
	if(id >0)
	{
		session.setAttribute("user", id);
		response.sendRedirect("../index.jsp?id="+id);
		return;
	
	}
	response.sendRedirect("../login.jsp?fail=true");
%>
<script>
console.log("uspesno");
</script>