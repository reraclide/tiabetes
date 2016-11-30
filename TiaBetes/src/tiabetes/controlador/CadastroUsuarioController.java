package tiabetes.controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tiabetes.modelo.comum.util.Retorno;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/CadastroUsuarioController")
public class CadastroUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
				
				response.sendRedirect("./CadastroUsuario.jsp");
				
			} else if ("Voltar".equals(cmd)){
				
				response.sendRedirect("./MenuCadastro.jsp");
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
