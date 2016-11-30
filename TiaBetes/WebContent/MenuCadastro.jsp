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
	
		String mensagem = (String) session.getAttribute("MENSAGEM");
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		
		session.setAttribute("MENSAGEM", null);
		
		if (mensagem == null) {
			mensagem = "";
		}
		
		if (usuario == null) response.sendRedirect("./Login.jsp");
	
	%>
	<div align="center">
		<h1>TiaBete's Doceria</h1>
		<h2>Menu Cadastros</h2>
		<h3><%=mensagem%></h3>
		<br />
		<form action="./MenuCadastroController" method="POST">
			<table>
			
				<tr><td><input type="submit" value="Usuários" name="cmd" /></td></tr>
				<tr><td><br /></td></tr>
				<tr><td><input type="submit" value="Produtos" name="cmd" /></td></tr>
				<tr><td><br /></td></tr>
				<tr><td><input type="submit" value="Voltar" name="cmd" /></td></tr>

			</table>
		</form>
	</div>
</body>
</html>