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
		<h2>Cadastro de Usuários</h2>
		<h3><%=mensagem%></h3>
		<br />
		<form action="./CadastroUsuarioController" method="POST">
			<table>
				<tr>
					<td><label for="txtNome">Nome: </label></td>
					<td><input id="txtNome" type="text" name="txtNome" /></td>
				</tr>
				<tr>
					<td><label for="txtLogin">Login: </label></td>
					<td><input id="txtLogin" type="text" name="txtLogin" /></td>
				</tr>
				<tr>
					<td><label for="txtSenha">Senha: </label></td>
					<td><input id="txtSenha" type="text" name="txtSenha" /></td>
				</tr>
				<tr>
					<td><label for="txtPerfil">Perfil: </label></td>
					<td><input id="txtPerfil" type="text" name="txtPerfil" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Salvar" name="cmd" /></td>
				</tr>
				<tr><td><input type="submit" value="Voltar" name="cmd" /></td></tr>

			</table>
		</form>
	</div>
</body>
</html>