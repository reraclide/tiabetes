package tiabetes.modelo.dao;

import java.util.List;

import tiabetes.modelo.comum.entidade.Usuario;

public interface UsuarioDAO {
	
	Usuario buscarPorLogin(String nome);
	
	Usuario buscarPorId(long id);
	
	List<Usuario> buscarListaUsuario();
	
	void inserir(Usuario usuario) throws RuntimeException;
	
	void atualizar(Usuario usuario) throws RuntimeException;
	
	void excluir(long id) throws RuntimeException;
	
    void popularBaseDados() throws RuntimeException;
    
}
