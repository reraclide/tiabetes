package tiabetes.controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tiabetes.modelo.comum.entidade.Produto;
import tiabetes.modelo.comum.util.Retorno;
import tiabetes.modelo.dao.ProdutoDAO;
import tiabetes.modelo.mysql.ProdutoMSQL;

/**
 * Servlet implementation class CadastroProdutoController
 */
@WebServlet("/CadastroProdutoController")
public class CadastroProdutoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String PostBack = "./CadastroProduto.jsp";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CadastroProdutoController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			
			String cmd = request.getParameter("cmd");

			if ("Salvar".equals(cmd)) {
				
				String modo = request.getParameter("txtModo");
				Retorno retorno;
				
				if (modo == null || "Novo Produto".equals(modo)) {
					
					retorno = inserir(request);
					
				} else {
					
					retorno = editar(request);
					
				}
				
				if (retorno.sucesso()){
					session.setAttribute("MODO_CADASTRO_PRODUTO", null);
					session.setAttribute("PRODUTO_EDITAR", null);
				}
				
				session.setAttribute("MENSAGEM", retorno.getMensagem());
				
				ProdutoDAO prdDAO = new ProdutoMSQL();
				session.setAttribute("PRODUTO_LISTA", prdDAO.buscarListaProduto());
				
				response.sendRedirect(PostBack);
				
				
			} else if ("Novo".equals(cmd)){
				
				session.setAttribute("MODO_CADASTRO_PRODUTO", null);
				session.setAttribute("PRODUTO_EDITAR", null);
				response.sendRedirect(PostBack);
				
			} else if ("Voltar".equals(cmd)){
				
				response.sendRedirect("./MenuCadastro.jsp");
				
			} else if ("Editar".equals(cmd.substring(0, 6))){
				
				long id = Long.parseLong(cmd.substring(6, cmd.length()));
				ProdutoDAO prdDAO = new ProdutoMSQL();
				Produto produtoEdit = prdDAO.buscarPorId(id);
				session.setAttribute("PRODUTO_EDITAR", produtoEdit);
				session.setAttribute("MODO_CADASTRO_PRODUTO", "Editando usuário: " + produtoEdit.getNome());
				response.sendRedirect(PostBack);
				
			} else if ("Excluir".equals(cmd.substring(0, 7))){
				long id = Long.parseLong(cmd.substring(7, cmd.length()));
				Retorno retorno = excluir(request, id);
				
				session.setAttribute("MENSAGEM", retorno.getMensagem());
				
				response.sendRedirect(PostBack);
				
			} else {
				session.setAttribute("MENSAGEM", "Comando inválido");
				response.sendRedirect(PostBack);
			}
			

		} catch (Exception ex) {
			session.setAttribute("MENSAGEM", ex.getMessage());
			response.sendRedirect("./Erro.jsp");
		}
	}
	
	private Retorno inserir(HttpServletRequest request){
		try{
			
			String txtNome = request.getParameter("txtNome");
			
			Produto prdNovo = new Produto();
			
			prdNovo.setNome(txtNome);
			
			ProdutoDAO prdDAO = new ProdutoMSQL();
			prdDAO.inserir(prdNovo);
			
			return Retorno.getSucesso("Cadastrado com sucesso!");
			
		} catch (Exception ex) {
			return Retorno.getArquitetura("Erro ao inserir: " + ex.getMessage());
		}
	}
	
	private Retorno editar(HttpServletRequest request){
		try{
			
			String txtId = request.getParameter("txtId");
			String txtNome = request.getParameter("txtNome");
			
			Produto prdEdit = new Produto();
			
			prdEdit.setId(Long.parseLong(txtId));
			prdEdit.setNome(txtNome);
			
			ProdutoDAO prdDAO = new ProdutoMSQL();
			prdDAO.atualizar(prdEdit);
			
			return Retorno.getSucesso("Atualizado com sucesso!");
			
		} catch (Exception ex) {
			return Retorno.getArquitetura("Erro ao editar cadastro: " + ex.getMessage());
		}
	}
	
	private Retorno excluir(HttpServletRequest request, long id){
		try{
			
			ProdutoDAO prdDAO = new ProdutoMSQL();
			prdDAO.excluir(id);
			
			return Retorno.getSucesso("Excluído com sucesso!");
			
		} catch (Exception ex) {
			return Retorno.getArquitetura("Erro ao excluir cadastro: " + ex.getMessage());
		}
	}
	
	@SuppressWarnings("unused")
	private void exibirPaginaErro(Retorno retorno, HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		HttpSession session = request.getSession();
		session.setAttribute("MENSAGEM", retorno.getMensagem());
		
		response.sendRedirect("./Erro.jsp");
		
	}

}
