<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="tiabetes.modelo.comum.entidade.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TiaBete's - Login</title>
</head>
<body>

	<%
		if (session.getAttribute("MENSAGEM") == null)
			response.sendRedirect("./Login.jsp");

		String mensagem = (String) session.getAttribute("MENSAGEM");

		session.setAttribute("MENSAGEM", null);
	%>
	
	<div align="center">
		<h2>Ocorreu um erro inesperado :(</h2>
		<br />
		<h4>O sistema retornou um erro:</h4>
		<br />
		<h5><%=mensagem%></h5>
	</div>

</body>
</html>