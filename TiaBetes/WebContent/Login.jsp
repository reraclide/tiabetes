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
	
		String mensagem = (String) session.getAttribute("MENSAGEM");
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		
		session.setAttribute("MENSAGEM", null);
		
		if (mensagem == null) {
			mensagem = "";
		}
		
		if (usuario != null) response.sendRedirect("./Menu.jsp");
	
	%>
	<div align="center">
		<h1>TiaBete's - Autenticação</h1>
		<h3><%=mensagem%></h3>
		
		<form action="./LoginController" method="POST">
			<table>
				<tr>
					<td><label for="txtLogin">Login: </label></td>
					<td><input id="txtLogin" type="text" name="txtLogin" /></td>
				</tr>
				<tr>
					<td><label for="txtSenha">Senha: </label></td>
					<td><input type="password" id="txtSenha" type="text" name="txtSenha" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Entrar" name="cmd" /></td>
				</tr>
				<tr>
					<td><br /><br /><br /><br /></td>
					<td><input type="submit" value="Criar Base de Dados" name="cmd" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>