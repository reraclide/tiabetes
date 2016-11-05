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
import tiabetes.modelo.dao.BaseDAO;
import tiabetes.modelo.dao.UsuarioDAO;
import tiabetes.modelo.mysql.BaseMSQL;
import tiabetes.modelo.mysql.UsuarioMSQL;
import tiabetes.modelo.negocio.UsuarioRN;
import tiabetes.modelo.servico.UsuarioSVC;

/**
 * EMERSON ESTEVE AQUI RS
 * Servlet implementation class UsuarioController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
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

			if ("Entrar".equals(cmd)) {

				entrar(request, response);

			} else if ("Criar Base de Dados".equals(cmd)){
				
				criarBaseDados(request, response);
				session.setAttribute("MENSAGEM", "Base criada com sucesso!");
				
			}

			response.sendRedirect("./Login.jsp");
			
		} catch (Exception ex) {
			session.setAttribute("MENSAGEM", ex.getMessage());
			response.sendRedirect("./Erro.jsp");
		}
	}

	private void entrar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		Retorno retorno;
		
		HttpSession session = request.getSession();

		String txtLogin = request.getParameter("txtLogin");
		String txtSenha = request.getParameter("txtSenha");

		UsuarioRN usuarioRN = new UsuarioSVC();
		UsuarioDAO usuarioDAO = new UsuarioMSQL();

		Usuario usuario = new Usuario();
		usuario.setLogin(txtLogin);
		usuario.setSenha(txtSenha);

		retorno = usuarioRN.validarLogin(usuario);
		if (!retorno.sucesso() && !retorno.arquitetura()){
			session.setAttribute("MENSAGEM", retorno.getMensagem());
		} else if (retorno.arquitetura()){
			exibirPaginaErro(retorno, request, response);
		} else {
			usuario = usuarioDAO.buscarPorLogin(txtLogin);
			session.setAttribute("USUARIO", usuario);
		}
		
	}
	
	private void criarBaseDados(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		BaseDAO baseDAO = new BaseMSQL();
		
		baseDAO.criarEstruturaBaseDados();
		baseDAO.popularBaseDados();
		
	}
	
	private void exibirPaginaErro(Retorno retorno, HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		HttpSession session = request.getSession();
		session.setAttribute("MENSAGEM", retorno.getMensagem());
		
		response.sendRedirect("./Erro.jsp");
		
	}

}
