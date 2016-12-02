package tiabetes.modelo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import tiabetes.modelo.dao.BaseDAO;
import tiabetes.modelo.dao.ProdutoDAO;
import tiabetes.modelo.dao.UsuarioDAO;

public class BaseMSQL implements BaseDAO {
	
	private Connection conn;

	public BaseMSQL() { 
		conn = UtilMSQL.getInstance().getConn();
	}
	
	@Override
	public void criarEstruturaBaseDados() throws RuntimeException {
		
		String query = " CREATE TABLE IF NOT EXISTS USUARIO (       "
					 + " id_usuario INT(6) NOT NULL AUTO_INCREMENT, "
					 + " ds_login VARCHAR(30) NOT NULL,             "
					 + " ds_senha VARCHAR(30) NOT NULL,             "
					 + " ds_nome  VARCHAR(200) NOT NULL,            "
					 + " cd_perfil TINYINT NOT NULL,                "
					 + " PRIMARY KEY (id_usuario) );                "
					
					 ;
		
		try {
			
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.executeUpdate();
			
		} catch (SQLException ex){
			
			throw new RuntimeException("Erro ao executar query: \r\n" 
					+ query + "\r\n\r\n"
					+ "Detalhes" + ex.getMessage());
			
		}
		

		query = " CREATE TABLE IF NOT EXISTS PRODUTO (       "
		      + " id_produto INT(6) NOT NULL AUTO_INCREMENT, "
		      + " ds_nome  VARCHAR(200) NOT NULL,            "
		      + " PRIMARY KEY (id_produto) );                "
			  
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
	public void popularBaseDados() {
		
		UsuarioDAO usuarioDAO = new UsuarioMSQL();
		ProdutoDAO produtoDAO = new ProdutoMSQL();
		
		usuarioDAO.popularBaseDados();
		produtoDAO.popularBaseDados();

	}

}
