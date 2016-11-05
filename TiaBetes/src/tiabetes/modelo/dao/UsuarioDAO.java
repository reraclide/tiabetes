package tiabetes.modelo.dao;

import tiabetes.modelo.comum.entidade.Usuario;

public interface UsuarioDAO {
	
	Usuario buscarPorLogin(String nome);
    void popularBaseDados() throws RuntimeException;
    
}
