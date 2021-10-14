<%@page import="java.io.Console"%>
<%@page import="DAL.UserDAL"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="Models.User" %>
<jsp:useBean id="new_user" class="Models.User"></jsp:useBean>
<jsp:setProperty property="*" name="new_user" ></jsp:setProperty> 
<%
	int res = UserDAL.register(new_user);
	if(res ==0)
	{
		
		response.sendRedirect("../index.jsp");
		return;
	
	}
	response.sendRedirect("../register.jsp?fail=true");
%>
<script>
console.log("uspesno");
</script>