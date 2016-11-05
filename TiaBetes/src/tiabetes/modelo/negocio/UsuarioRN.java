package tiabetes.modelo.negocio;

import tiabetes.modelo.comum.entidade.Usuario;
import tiabetes.modelo.comum.util.Retorno;

public interface UsuarioRN {
	
	Retorno validarLogin(Usuario usr);
	
}
