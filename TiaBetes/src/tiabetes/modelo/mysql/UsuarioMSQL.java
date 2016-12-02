package tiabetes.modelo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tiabetes.modelo.comum.entidade.Usuario;
import tiabetes.modelo.comum.enumerador.TipoPerfil;
import tiabetes.modelo.dao.UsuarioDAO;

public class UsuarioMSQL implements UsuarioDAO{
	
	private Connection conn;

	public UsuarioMSQL() { 
		conn = UtilMSQL.getInstance().getConn();
	}
	
	@Override
	public Usuario buscarPorLogin(String login) {
		
		Usuario usuario = new Usuario();
		
		String query = " SELECT `usuario`.`id_usuario`, "
				  + "        `usuario`.`ds_login`,      "
				  + "        `usuario`.`ds_senha`,      "
				  + "        `usuario`.`ds_nome`,       "
				  + "        `usuario`.`cd_perfil`      "
				  + "    FROM `tiabetes`.`usuario`      "
				  + "    WHERE ds_login = ?;            "

				;
		
		try { 
			
			PreparedStatement psmt = conn.prepareStatement(query);
			
			psmt.setString(1, login);
			
			ResultSet rs = psmt.executeQuery();
			
			if (rs.next()) {
				
				usuario.setId(rs.getLong("id_usuario"));
				usuario.setLogin(rs.getString("ds_login"));
				usuario.setSenha(rs.getString("ds_senha"));
				usuario.setNome(rs.getString("ds_nome"));
				usuario.setPerfil(TipoPerfil.values()[rs.getInt("cd_perfil")]);
				
			} else {
				return null;
			}
			
		} catch (SQLException ex) {
			
			throw new RuntimeException("Erro ao executar query: \r\n" 
					+ query + "\r\n\r\n"
					+ "Detalhes" + ex.getMessage());
			
		}
		
		return usuario;
		
	}

	@Override
	public void popularBaseDados() throws RuntimeException {

		String query = " INSERT INTO USUARIO (      "
				     + " 	 ds_login,              "
				     + "     ds_senha,              "
				     + "     ds_nome,               "
				     + "     cd_perfil              "
				     + " ) VALUES (                 "
				     + " 	 'admin',               "
				     + "     '12345',               "
				     + "     'ADMINISTRADOR',       "
				     + "     0                      "
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
	public List<Usuario> buscarListaUsuario() {
			
		List<Usuario> lstUsuario =  new ArrayList<Usuario>();
		
		String query = " SELECT `usuario`.`id_usuario`,    "
				     + "        `usuario`.`ds_login`,      "
				     + "        `usuario`.`ds_senha`,      "
				     + "        `usuario`.`ds_nome`,       "
				     + "        `usuario`.`cd_perfil`      "
				     + "    FROM `tiabetes`.`usuario`      "

				;
		
		try { 
			
			PreparedStatement psmt = conn.prepareStatement(query);
			
			ResultSet rs = psmt.executeQuery();
			
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getLong("id_usuario"));
				usuario.setLogin(rs.getString("ds_login"));
				usuario.setSenha(rs.getString("ds_senha"));
				usuario.setNome(rs.getString("ds_nome"));
				usuario.setPerfil(TipoPerfil.values()[rs.getInt("cd_perfil")]);
				lstUsuario.add(usuario);
			}
			
		} catch (SQLException ex) {
			
			throw new RuntimeException("Erro ao executar query: \r\n" 
					+ query + "\r\n\r\n"
					+ "Detalhes" + ex.getMessage());
			
		}
		
		return lstUsuario;
	}

	@Override
	public Usuario buscarPorId(long id) {
		
		Usuario usuario = new Usuario();
		
		String query = " SELECT `usuario`.`id_usuario`, "
				     + "        `usuario`.`ds_login`,      "
				     + "        `usuario`.`ds_senha`,      "
				     + "        `usuario`.`ds_nome`,       "
				     + "        `usuario`.`cd_perfil`      "
				     + "    FROM `tiabetes`.`usuario`      "
				     + "    WHERE id_usuario = ?;          "

				;
		
		try { 
			
			PreparedStatement psmt = conn.prepareStatement(query);
			
			psmt.setLong(1, id);
			
			ResultSet rs = psmt.executeQuery();
			
			if (rs.next()) {
				
				usuario.setId(rs.getLong("id_usuario"));
				usuario.setLogin(rs.getString("ds_login"));
				usuario.setSenha(rs.getString("ds_senha"));
				usuario.setNome(rs.getString("ds_nome"));
				usuario.setPerfil(TipoPerfil.values()[rs.getInt("cd_perfil")]);
				
			} else {
				return null;
			}
			
		} catch (SQLException ex) {
			
			throw new RuntimeException("Erro ao executar query: \r\n" 
					+ query + "\r\n\r\n"
					+ "Detalhes" + ex.getMessage());
			
		}
		
		return usuario;
	}

	@Override
	public void inserir(Usuario usuario) throws RuntimeException {
		
		String query = " INSERT INTO usuario ( "
				     + " 	 ds_login,         "
				     + "     ds_senha,         "
				     + "     ds_nome,          "
				     + "     cd_perfil         "
				     + " ) VALUES (            "
				     + " 	 ?,                "
				     + "     ?,                "
				     + "     ?,                "
				     + "     ?                 "
				     + " );                    "

            ;
		
		try {

			PreparedStatement psmt = conn.prepareStatement(query);
			
			psmt.setString(1, usuario.getLogin());
			psmt.setString(2, usuario.getSenha());
			psmt.setString(3, usuario.getNome());
			psmt.setInt(4, usuario.getPerfil().getValor());
			
			psmt.executeUpdate();

		} catch (SQLException ex){

			throw new RuntimeException("Erro ao executar query: \r\n" 
					+ query + "\r\n\r\n"
					+ "Detalhes" + ex.getMessage());

		}	
	}

	@Override
	public void atualizar(Usuario usuario) throws RuntimeException {
		
		String query = " UPDATE `tiabetes`.`usuario` "
			         + " SET                         "
			         + " `ds_login` = ?,             "
			         + " `ds_senha` = ?,             "
			         + " `ds_nome` = ?,              "
			         + " `cd_perfil` = ?             "
			         + " WHERE `id_usuario` = ?;     "
			      
        ;
	
		try {

			PreparedStatement psmt = conn.prepareStatement(query);

			psmt.setString(1, usuario.getLogin());
			psmt.setString(2, usuario.getSenha());
			psmt.setString(3, usuario.getNome());
			psmt.setInt(4, usuario.getPerfil().getValor());
			psmt.setLong(5, usuario.getId());

			psmt.executeUpdate();

		} catch (SQLException ex){

			throw new RuntimeException("Erro ao executar query: \r\n" 
					+ query + "\r\n\r\n"
					+ "Detalhes" + ex.getMessage());

		}	
		
	}

	@Override
	public void excluir(long id) throws RuntimeException {
		
		String query = " DELETE FROM `tiabetes`.`usuario` "
			         + " WHERE `id_usuario` = ?;          "
			      
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
