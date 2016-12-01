package tiabetes.modelo.dao;

import java.util.List;

import tiabetes.modelo.comum.entidade.Usuario;

public interface UsuarioDAO {
	
	Usuario buscarPorLogin(String nome);
	Usuario buscarPorId(long id);
	List<Usuario> buscarListaUsuario();
    void popularBaseDados() throws RuntimeException;
    
}
