<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="tiabetes.modelo.comum.entidade.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TiaBete's</title>
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
		<h1>TiaBete's Doceria</h1>
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
					<td>
						<p>
							<b>Para utilizar o sistema siga os seguintes passos:</b> <br/>
							<b>1</b> - Criar instância do MySQL na porta 3306<br/>
							<b>2</b> - Criar usuário para acesso ao banco de dados: <br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Usuário: <b>root</b> <br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Senha: <b>aluno</b> <br/>
							<b>3</b> - Criar base de dados com o nome "tiabetes" <br/>
							<b>4</b> - Clicar no botão a seguir: <input type="submit" value="Criar Estrutura Base de Dados" name="cmd" /><br/>
							<b>5</b> - Efetuar login no sistema TiaBete's com os dados: <br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Usuário: <b>admin</b> <br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Senha: <b>12345</b> <br/><br/>
							<b>Ps.</b> Utilizar MySQL 5.7 e Tomcat 8
						</p>
					</td>
					
				</tr>
				<tr align="center">
					<td align="center"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>