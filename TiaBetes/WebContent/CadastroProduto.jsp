<%@page import="tiabetes.modelo.mysql.ProdutoMSQL"%>
<%@page import="tiabetes.modelo.dao.ProdutoDAO"%>
<%@page import="tiabetes.modelo.comum.entidade.Produto"%>
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
		String modo = (String) session.getAttribute("MODO_CADASTRO_PRODUTO");
		String mensagem = (String) session.getAttribute("MENSAGEM");
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		
		@SuppressWarnings("unchecked")
		List<Produto> produtos = (List<Produto>) session.getAttribute("PRODUTO_LISTA"); 
		
		Produto prdEdit = (Produto) session.getAttribute("PRODUTO_EDITAR");
		
		session.setAttribute("MENSAGEM", null);
		session.setAttribute("LISTAPRODUTO", null);
		session.setAttribute("PRODUTO_EDITAR", null);
		session.setAttribute("PRODUTO_LISTA", null);
		
		if (usuario == null) response.sendRedirect("./Login.jsp");
		
		if (mensagem == null) {
			mensagem = "";
		}
		
		if (modo == null) {
			modo = "Novo Produto";
		}
		
		if (produtos == null || produtos.size() <= 0){
			ProdutoDAO prdDAO = new ProdutoMSQL();
			produtos = prdDAO.buscarListaProduto();
		}
		
		String idEdit = "";
		String nomeEdit = "";
		
		if (prdEdit != null){
			idEdit = String.valueOf(prdEdit.getId());
			nomeEdit = prdEdit.getNome();
		}
		
	%>
	<div align="center">
		<h1>TiaBete's Doceria</h1>
		<h2>Cadastro de Produtos</h2>
		<h3><%=modo%></h3>
		<h4><%=mensagem%></h4>
		
		<br />
		<form action="./CadastroProdutoController" method="POST">
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
					<td><label for="txtNome">Descrição: </label></td>
					<td><input id="txtNome" type="text" name="txtNome" value='<%=nomeEdit%>' /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Novo" name="cmd" /></td>
					<td><input type="submit" value="Salvar" name="cmd" /></td>
					<td><input type="submit" value="Voltar" name="cmd" /></td>
				</tr>

			</table>

			<%
				if (produtos != null && produtos.size() > 0) {
			%>
			<div>
				<br />
				<h3>Produtos Cadastrados</h3>
			</div>
			<table border="2">
				<tr>
					<td>Id</td>
					<td>Nome</td>
					<td></td>
					<td></td>
				</tr>
				<%for (Produto u : produtos) { %>
					<tr>
						<td><%=u.getId()%></td>
						<td><%=u.getNome()%></td>
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