package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {

	//salvar dados
	public void save(Contato contato) {

		String sql = "INSERT INTO contatos(nome, telefone, datacadastro) VALUES (?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Criar uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();

			// PreparedStatement para executar uma Query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			// adicionar os valores que sao adicionados
			pstm.setString(1, contato.getNome());
			pstm.setString(2, contato.getTelefone());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

			// executar a query
			pstm.execute();
			System.out.println("Contato salvo com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// fechar as conexões
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//atualizar dados
	public void update(Contato contato) {

		String sql = "UPDATE contatos SET nome = ?, telefone = ?, dataCadastro = ? " + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			//criar conexão com o banco
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//criar classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			//adicionar os valores para atualizar
			pstm.setString(1, contato.getNome());
			pstm.setString(2, contato.getTelefone());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

			//qual o ID para atualizar?
			pstm.setInt(4, contato.getId());

			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	//deletar dados
	public void deleteByID(int id) {
		
		String sql = "DELETE FROM contatos WHERE id = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null; 
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, id);		
			
			pstm.execute();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstm!=null) {
						pstm.close();
					}
					
					if(conn!=null) {
						conn.close();
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	
	//lista de dados
	public List<Contato> getContatos() {

		String sql = "SELECT * FROM contatos";

		List<Contato> contatos = new ArrayList<Contato>();

		Connection conn = null;
		PreparedStatement pstm = null;

		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Contato contato = new Contato();

				contato.setId(rset.getInt("id"));
				contato.setNome(rset.getString("nome"));
				contato.setTelefone(rset.getString("telefone"));
				contato.setDataCadastro(rset.getDate("dataCadastro"));

				contatos.add(contato);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rset != null)
					;
				{
					rset.close();
				}

				if (pstm != null)
					;
				{
					pstm.close();
				}
				if (conn != null)
					;
				{
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

				return contatos;
			}
		}
		return contatos;
	}
}
