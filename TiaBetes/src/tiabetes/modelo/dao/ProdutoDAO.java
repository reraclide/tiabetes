package tiabetes.modelo.dao;

import java.util.List;

import tiabetes.modelo.comum.entidade.Produto;

public interface ProdutoDAO {
	
	Produto buscarPorId(long id);
	
	List<Produto> buscarListaProduto();
	
	void inserir(Produto produto) throws RuntimeException;
	
	void atualizar(Produto produto) throws RuntimeException;
	
	void excluir(long id) throws RuntimeException;
	
    void popularBaseDados() throws RuntimeException;
    
}
