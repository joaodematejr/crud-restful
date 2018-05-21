package br.com.joao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.joao.config.BDConfig;
import br.com.joao.entity.Nota;

public class NotaDAO {

	// SALVAR
	public void addNota(Nota nota) throws Exception {
		int idGerado = 0;
		Connection conexao = BDConfig.getConnection();

		String sql = "INSERT INTO TB_NOTA(TITULO, DESCRICAO) VALUES(?, ?)";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, nota.getTitulo());
		statement.setString(2, nota.getDescricao());
		statement.execute();
	}

	// LISTAR
	public List<Nota> listarNotas() throws Exception {
		List<Nota> lista = new ArrayList<>();
		Connection conexao = BDConfig.getConnection();
		String sql = "SELECT * FROM TB_NOTA";
		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			Nota nota = new Nota();
			nota.setId(rs.getInt("ID"));
			nota.setTitulo(rs.getString("TITULO"));
			nota.setDescricao(rs.getString("DESCRICAO"));
			lista.add(nota);
		}
		return lista;
	}

	// LISTAR POR ID
	public Nota buscarNotaPorId(int id) throws Exception {
		Nota nota = null;
		Connection conexao = BDConfig.getConnection();
		String sql = "SELECT * FROM TB_NOTA WHERE ID = ?";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			nota = new Nota();
			nota.setId(rs.getInt("ID"));
			nota.setTitulo(rs.getString("TITULO"));
			nota.setDescricao(rs.getString("DESCRICAO"));
		}
		return nota;
	}

	// EDITAR
	public void editarNota(Nota nota, int id) throws Exception {
		Connection conexao = BDConfig.getConnection();
		String sql = "UPDATE TB_NOTA SET TITULO = ?, DESCRICAO = ? WHERE ID = ?";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, nota.getTitulo());
		statement.setString(2, nota.getDescricao());
		statement.setInt(3, id);
		statement.execute();
	}

	// DELETAR
	public void removerNota(int id) throws Exception {
		Connection conexao = BDConfig.getConnection();
		String sql = "DELETE FROM TB_NOTA WHERE ID = ?";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, id);
		statement.execute();
	}

}
