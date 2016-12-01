package tiabetes.controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tiabetes.modelo.comum.entidade.Usuario;
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
				
				// TODO: fazer o método para inserir
				
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
				
			} else {
				session.setAttribute("MENSAGEM", "Comando inválido");
				response.sendRedirect(PostBack);
			}
			

		} catch (Exception ex) {
			session.setAttribute("MENSAGEM", ex.getMessage());
			response.sendRedirect("./Erro.jsp");
		}
	}

	@SuppressWarnings("unused")
	private void exibirPaginaErro(Retorno retorno, HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		HttpSession session = request.getSession();
		session.setAttribute("MENSAGEM", retorno.getMensagem());
		
		response.sendRedirect("./Erro.jsp");
		
	}

}
