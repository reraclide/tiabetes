package tiabetes.controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tiabetes.modelo.comum.entidade.Usuario;
import tiabetes.modelo.comum.enumerador.TipoPerfil;
import tiabetes.modelo.comum.util.Retorno;
import tiabetes.modelo.dao.UsuarioDAO;
import tiabetes.modelo.mysql.UsuarioMSQL;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/CadastroUsuarioController")
public class CadastroUsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String PostBack = "./CadastroUsuario.jsp";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CadastroUsuarioController() {
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
				
				if (modo == null || "Novo Usuário".equals(modo)) {
					
					retorno = inserir(request);
					
				} else {
					
					retorno = editar(request);
					
				}
				
				if (retorno.sucesso()){
					session.setAttribute("MODO_CADASTRO_USUARIO", null);
					session.setAttribute("USUARIO_EDITAR", null);
				}
				
				session.setAttribute("MENSAGEM", retorno.getMensagem());
				
				UsuarioDAO usrDAO = new UsuarioMSQL();
				session.setAttribute("USUARIO_LISTA", usrDAO.buscarListaUsuario());
				
				response.sendRedirect(PostBack);
				
				
			} else if ("Novo".equals(cmd)){
				
				session.setAttribute("MODO_CADASTRO_USUARIO", null);
				session.setAttribute("USUARIO_EDITAR", null);
				response.sendRedirect(PostBack);
				
			} else if ("Voltar".equals(cmd)){
				
				response.sendRedirect("./MenuCadastro.jsp");
				
			} else if ("Editar".equals(cmd.substring(0, 6))){
				
				long id = Long.parseLong(cmd.substring(6, cmd.length()));
				UsuarioDAO usrDAO = new UsuarioMSQL();
				Usuario usuarioEdit = usrDAO.buscarPorId(id);
				session.setAttribute("USUARIO_EDITAR", usuarioEdit);
				session.setAttribute("MODO_CADASTRO_USUARIO", "Editando usuário: " + usuarioEdit.getNome());
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
			String txtLogin = request.getParameter("txtLogin");
			String txtSenha = request.getParameter("txtSenha");
			String txtPerfil = request.getParameter("txtPerfil");
			
			Usuario usrNovo = new Usuario();
			
			usrNovo.setNome(txtNome);
			usrNovo.setLogin(txtLogin);
			usrNovo.setSenha(txtSenha);
			usrNovo.setPerfil(TipoPerfil.getPerfil(Integer.parseInt(txtPerfil)));
			
			UsuarioDAO usrDAO = new UsuarioMSQL();
			usrDAO.inserir(usrNovo);
			
			return Retorno.getSucesso("Cadastrado com sucesso!");
			
		} catch (Exception ex) {
			return Retorno.getArquitetura("Erro ao inserir: " + ex.getMessage());
		}
	}
	
	private Retorno editar(HttpServletRequest request){
		try{
			
			String txtId = request.getParameter("txtId");
			String txtNome = request.getParameter("txtNome");
			String txtLogin = request.getParameter("txtLogin");
			String txtSenha = request.getParameter("txtSenha");
			String txtPerfil = request.getParameter("txtPerfil");
			
			Usuario usrEdit = new Usuario();
			
			usrEdit.setId(Long.parseLong(txtId));
			usrEdit.setNome(txtNome);
			usrEdit.setLogin(txtLogin);
			usrEdit.setSenha(txtSenha);
			usrEdit.setPerfil(TipoPerfil.getPerfil(Integer.parseInt(txtPerfil)));
			
			UsuarioDAO usrDAO = new UsuarioMSQL();
			usrDAO.atualizar(usrEdit);
			
			return Retorno.getSucesso("Atualizado com sucesso!");
			
		} catch (Exception ex) {
			return Retorno.getArquitetura("Erro ao editar cadastro: " + ex.getMessage());
		}
	}
	
	private Retorno excluir(HttpServletRequest request, long id){
		try{
			
			UsuarioDAO usrDAO = new UsuarioMSQL();
			usrDAO.excluir(id);
			
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
