<%@page import="tiabetes.modelo.comum.entidade.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TiaBete's - Menu Principal</title>
</head>
<body>
	<%
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		if (usuario == null) response.sendRedirect("./Login.jsp");
	%>

	<div align="center">
		<h1>Menu Principal</h1>
		<br />
		<h3>Item 1</h3>
		<h3>Item 2</h3>
		<h3>Item 3</h3>
		<h3>Item 4</h3>
		<h3>Item 5</h3>
	</div>
</body>
</html>