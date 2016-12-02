package tiabetes.modelo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tiabetes.modelo.comum.entidade.Produto;
import tiabetes.modelo.dao.ProdutoDAO;

public class ProdutoMSQL implements ProdutoDAO{
	
	private Connection conn;

	public ProdutoMSQL() { 
		conn = UtilMSQL.getInstance().getConn();
	}

	@Override
	public void popularBaseDados() throws RuntimeException {

		String query = " INSERT INTO PRODUTO (      "
				     + "     ds_nome                "
				     + " ) VALUES (                 "
				     + " 	 'Chocolate'            "
				     + " );                         "

	             ;

		try {

			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.executeUpdate();

		} catch (SQLException ex){

			throw new RuntimeException("Erro ao executar query: \r\n" 
					+ query + "\r\n\r\n"
					+ "Detalhes" + ex.getMessage());

		}	

	}

	@Override
	public List<Produto> buscarListaProduto() {
			
		List<Produto> lstProduto =  new ArrayList<Produto>();
		
		String query = " SELECT `produto`.`id_produto`,    "
				     + "        `produto`.`ds_nome`        "
				     + "    FROM `tiabetes`.`produto`      "

				;
		
		try { 
			
			PreparedStatement psmt = conn.prepareStatement(query);
			
			ResultSet rs = psmt.executeQuery();
			
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getLong("id_produto"));
				produto.setNome(rs.getString("ds_nome"));
				lstProduto.add(produto);
			}
			
		} catch (SQLException ex) {
			
			throw new RuntimeException("Erro ao executar query: \r\n" 
					+ query + "\r\n\r\n"
					+ "Detalhes" + ex.getMessage());
			
		}
		
		return lstProduto;
	}

	@Override
	public Produto buscarPorId(long id) {
		
		Produto produto = new Produto();
		
		String query = " SELECT `produto`.`id_produto`,    "
				     + "        `produto`.`ds_nome`        "
				     + "    FROM `tiabetes`.`produto`      "
				     + "    WHERE id_produto = ?;          "

				;
		
		try { 
			
			PreparedStatement psmt = conn.prepareStatement(query);
			
			psmt.setLong(1, id);
			
			ResultSet rs = psmt.executeQuery();
			
			if (rs.next()) {
				
				produto.setId(rs.getLong("id_produto"));
				produto.setNome(rs.getString("ds_nome"));
				
			} else {
				return null;
			}
			
		} catch (SQLException ex) {
			
			throw new RuntimeException("Erro ao executar query: \r\n" 
					+ query + "\r\n\r\n"
					+ "Detalhes" + ex.getMessage());
			
		}
		
		return produto;
	}

	@Override
	public void inserir(Produto produto) throws RuntimeException {
		
		String query = " INSERT INTO produto ( "
				     + "     ds_nome           "
				     + " ) VALUES (            "
				     + "     ?                 "
				     + " );                    "

            ;
		
		try {

			PreparedStatement psmt = conn.prepareStatement(query);
			
			psmt.setString(1, produto.getNome());
			
			psmt.executeUpdate();

		} catch (SQLException ex){

			throw new RuntimeException("Erro ao executar query: \r\n" 
					+ query + "\r\n\r\n"
					+ "Detalhes" + ex.getMessage());

		}	
	}

	@Override
	public void atualizar(Produto produto) throws RuntimeException {
		
		String query = " UPDATE `tiabetes`.`produto` "
			         + " SET                         "
			         + " `ds_nome` = ?               "
			         + " WHERE `id_produto` = ?;     "
			      
        ;
	
		try {

			PreparedStatement psmt = conn.prepareStatement(query);

			psmt.setString(1, produto.getNome());
			psmt.setLong(2, produto.getId());

			psmt.executeUpdate();

		} catch (SQLException ex){

			throw new RuntimeException("Erro ao executar query: \r\n" 
					+ query + "\r\n\r\n"
					+ "Detalhes" + ex.getMessage());

		}	
		
	}

	@Override
	public void excluir(long id) throws RuntimeException {
		
		String query = " DELETE FROM `tiabetes`.`produto` "
			         + " WHERE `id_produto` = ?;          "
			      
        ;
	
		try {

			PreparedStatement psmt = conn.prepareStatement(query);

			psmt.setLong(1, id);

			psmt.executeUpdate();

		} catch (SQLException ex){

			throw new RuntimeException("Erro ao executar query: \r\n" 
					+ query + "\r\n\r\n"
					+ "Detalhes" + ex.getMessage());

		}	
		
		
	}

}
