package tiabetes.controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/MenuController")
public class MenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuController() {
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
			
			if ("Cadastros".equals(cmd)){
				
				response.sendRedirect("./MenuCadastro.jsp");
				
			} else if ("Logout".equals(cmd)) {

				logout(request, response);

			} 
			
		} catch (Exception ex) {
			session.setAttribute("MENSAGEM", ex.getMessage());
			response.sendRedirect("./Erro.jsp");
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
				
		HttpSession session = request.getSession();
		
		session.setAttribute("USUARIO", null);
		
		response.sendRedirect("./Login.jsp");
		
	}

}
