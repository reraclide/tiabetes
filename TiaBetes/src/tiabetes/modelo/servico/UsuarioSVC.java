package tiabetes.modelo.servico;

import tiabetes.modelo.comum.entidade.Usuario;
import tiabetes.modelo.comum.util.Retorno;
import tiabetes.modelo.dao.UsuarioDAO;
import tiabetes.modelo.mysql.UsuarioMSQL;
import tiabetes.modelo.negocio.UsuarioRN;

public class UsuarioSVC implements UsuarioRN {

	@Override
	public Retorno validarLogin(Usuario usr) {
		try {
			
			UsuarioDAO usuarioDAO = new UsuarioMSQL();
			Usuario usrBD = usuarioDAO.buscarPorLogin(usr.getLogin());
			
			if (usrBD == null) {
				return Retorno.getFuncional("Usuário não econtrado!");
			}
			
			if (!usr.senha.equals(usrBD.getSenha())) {
				return Retorno.getFuncional("Senha inválida!");
			}
			
			return Retorno.getSucesso("teste");
			
		} catch (Exception ex) {
			return Retorno.getArquitetura("Erro ao validar acesso: " + ex.getMessage());
		}
	}

}
