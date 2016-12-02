<%@page import="tiabetes.modelo.mysql.UsuarioMSQL"%>
<%@page import="tiabetes.modelo.dao.UsuarioDAO"%>
<%@page import="java.util.List"%>
<%@page import="tiabetes.modelo.comum.entidade.Usuario"%>
<%@page import="tiabetes.modelo.comum.enumerador.TipoPerfil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TiaBete's</title>
</head>
<body>
	<%
		String modo = (String) session.getAttribute("MODO_CADASTRO_USUARIO");
		String mensagem = (String) session.getAttribute("MENSAGEM");
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		
		@SuppressWarnings("unchecked")
		List<Usuario> usuarios = (List<Usuario>) session.getAttribute("USUARIO_LISTA"); 
		
		Usuario usrEdit = (Usuario) session.getAttribute("USUARIO_EDITAR");
		
		session.setAttribute("MENSAGEM", null);
		session.setAttribute("LISTAUSUARIO", null);
		session.setAttribute("USUARIO_EDITAR", null);
		session.setAttribute("USUARIO_LISTA", null);
		
		if (usuario == null) response.sendRedirect("./Login.jsp");
		
		if (mensagem == null) {
			mensagem = "";
		}
		
		if (modo == null) {
			modo = "Novo Usuário";
		}
		
		if (usuarios == null || usuarios.size() <= 0){
			UsuarioDAO usrDAO = new UsuarioMSQL();
			usuarios = usrDAO.buscarListaUsuario();
		}
		
		String idEdit = "";
		String nomeEdit = "";
		String loginEdit = "";
		String senhaEdit = "";
		String perfilEdit0 = "";
		String perfilEdit1 = "";
		String perfilEdit2 = "";
		
		if (usrEdit != null){
			idEdit = String.valueOf(usrEdit.getId());
			nomeEdit = usrEdit.getNome();
			loginEdit = usrEdit.getLogin();
			senhaEdit = usrEdit.getSenha();
			perfilEdit0 = (usrEdit.getPerfil() == TipoPerfil.ADMINSTRADOR ? "selected" : "");
			perfilEdit1 = (usrEdit.getPerfil() == TipoPerfil.FUNCIONARIO ? "selected" : "");
			perfilEdit2 = (usrEdit.getPerfil() == TipoPerfil.CLIENTE ? "selected" : "");
		}
		
	%>
	<div align="center">
		<h1>TiaBete's Doceria</h1>
		<h2>Cadastro de Usuários</h2>
		<h3><%=modo%></h3>
		<h4><%=mensagem%></h4>
		
		<br />
		<form action="./CadastroUsuarioController" method="POST">
			<table>
				<tr style="display:none;">
					<td><label for="txtModo">Modo: </label></td>
					<td><input id="txtModo" type="text" name="txtModo" value='<%=modo%>' /></td>
				</tr>
				<tr style="display:none;">
					<td><label for="txtId">Id: </label></td>
					<td><input id="txtId" type="text" name="txtId" value='<%=idEdit%>' /></td>
				</tr>
				<tr>
					<td><label for="txtNome">Nome: </label></td>
					<td><input id="txtNome" type="text" name="txtNome" value='<%=nomeEdit%>' /></td>
				</tr>
				<tr>
					<td><label for="txtLogin">Login: </label></td>
					<td><input id="txtLogin" type="text" name="txtLogin" value='<%=loginEdit%>' /></td>
				</tr>
				<tr>
					<td><label for="txtSenha">Senha: </label></td>
					<td><input id="txtSenha" type="text" name="txtSenha" value='<%=senhaEdit%>' /></td>
				</tr>
				<tr>
					<td><label for="txtPerfil">Perfil: </label></td>
					<td>
						<select id="txtPerfil" name="txtPerfil" >
							<option <%=perfilEdit0%> value="0">Administrador</option>
							<option <%=perfilEdit1%> value="1">Funcionário</option>
							<option <%=perfilEdit2%> value="2">Cliente</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="Novo" name="cmd" /></td>
					<td><input type="submit" value="Salvar" name="cmd" /></td>
					<td><input type="submit" value="Voltar" name="cmd" /></td>
				</tr>

			</table>

			<%
				if (usuarios != null && usuarios.size() > 0) {
			%>
			<div>
				<br />
				<h3>Usuários Cadastrados</h3>
			</div>
			<table border="2">
				<tr>
					<td>Id</td>
					<td>Nome</td>
					<td>Login</td>
					<td>Perfil</td>
					<td></td>
					<td></td>
				</tr>
				<%for (Usuario u : usuarios) { %>
					<tr>
						<td><%=u.getId()%></td>
						<td><%=u.getNome()%></td>
						<td><%=u.getLogin()%></td>
						<td><%=u.getPerfil()%></td>
						<td><button type="submit" value="Editar<%=u.getId()%>" name="cmd">Editar</button></td>
						<td><button type="submit" value="Excluir<%=u.getId()%>" name="cmd">Excluir</button></td>
					</tr>
				<%}%>
			</table>
			<%
				}
			%>

		</form>
	</div>
</body>
</html>