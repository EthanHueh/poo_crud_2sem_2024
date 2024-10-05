package poo_crud_2sem_2024;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConstrucaoDAO {

    public void inserir(Construcao c) throws ClassNotFoundException, SQLException{
        Connection conn = ConexaoFactory.getConexao();
        String SQL = "INSERT INTO construcoes" +
                     "(nome, endereco, tipo," +
                     "data_inicio, data_previsao_termino," +
                     "area_total_m2, orcamento_total, nome_responsavel, status)" +
                     "VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(SQL);

        pstmt.setString(1, c.getNome());
        pstmt.setString(2, c.getEndereco());
        pstmt.setString(3, c.getTipo());
        pstmt.setDate(4, java.sql.Date.valueOf(c.getDataInicio()));
        pstmt.setDate(5, java.sql.Date.valueOf(c.getDataPrevisaoTermino()));
        pstmt.setDouble(6, c.getAreaTotal_m2());
        pstmt.setDouble(7, c.getOrcamentoTotal());
        pstmt.setString(8, c.getResponsavel());
        pstmt.setString(9, c.getStatus());

        // Executar a inserção
        int rowsInserted = pstmt.executeUpdate();

        // Verificar se a inserção foi bem-sucedida
        if (rowsInserted > 0) {
            System.out.println("Inserção bem-sucedida!");
        }

        // Fechar o PreparedStatement e a conexão
        pstmt.close();
        conn.close();
    }
    
    public List<Construcao> consultarTodos() throws SQLException, ClassNotFoundException {
        List<Construcao> construcoes = new ArrayList<>();
        
        Connection conn = ConexaoFactory.getConexao();
        String SQL = "SELECT * FROM construcoes";
        PreparedStatement pstmt = conn.prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()){
            Construcao cst = new Construcao();
                        
            cst.setId(rs.getInt("id"));
            cst.setNome(rs.getString("nome"));
            cst.setEndereco(rs.getString("endereco"));
            cst.setTipo(rs.getString("tipo"));
            cst.setDataInicio(rs.getDate("data_inicio").toLocalDate());
            cst.setDataPrevisaoTermino(rs.getDate("data_previsao_termino").toLocalDate());
            cst.setAreaTotal_m2(rs.getInt("area_total_m2"));
            cst.setOrcamentoTotal(rs.getDouble("orcamento_total"));
            cst.setResponsavel(rs.getString("nome_responsavel"));
            cst.setStatus(rs.getString("status"));
      
            construcoes.add(cst);
        }
        
        pstmt.close();
        conn.close();

        return construcoes;
    }
    
    public Construcao consultarByID(Construcao c) throws SQLException, ClassNotFoundException{
        Connection conn = ConexaoFactory.getConexao();
        String SQL = "SELECT * FROM construcoes WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(SQL);
        pstmt.setInt(1, c.getId());
        ResultSet rs = pstmt.executeQuery();
        rs.next();
                        
        c.setNome(rs.getString("nome"));
        c.setEndereco(rs.getString("endereco"));
        c.setTipo(rs.getString("tipo"));
        c.setDataInicio(rs.getDate("data_inicio").toLocalDate());
        c.setDataPrevisaoTermino(rs.getDate("data_previsao_termino").toLocalDate());
        c.setAreaTotal_m2(rs.getInt("area_total_m2"));
        c.setOrcamentoTotal(rs.getDouble("orcamento_total"));
        c.setResponsavel(rs.getString("nome_responsavel"));
        c.setStatus(rs.getString("status"));
        
        pstmt.close();
        conn.close();

        return c;
    }
    
    public void atualizar(Construcao c) throws ClassNotFoundException, SQLException {
        Connection conn = ConexaoFactory.getConexao();
        String SQL = "UPDATE construcoes SET " +
                     "nome = ?, endereco = ?, tipo = ?, " +
                     "data_inicio = ?, data_previsao_termino = ?, " +
                     "area_total_m2 = ?, orcamento_total = ?, nome_responsavel = ?, status = ? " +
                     "WHERE id = ?";
    
        PreparedStatement pstmt = conn.prepareStatement(SQL);
    
        // Configurar os valores para o comando SQL
        pstmt.setString(1, c.getNome());
        pstmt.setString(2, c.getEndereco());
        pstmt.setString(3, c.getTipo());
        pstmt.setDate(4, java.sql.Date.valueOf(c.getDataInicio()));
        pstmt.setDate(5, java.sql.Date.valueOf(c.getDataPrevisaoTermino()));
        pstmt.setDouble(6, c.getAreaTotal_m2());
        pstmt.setDouble(7, c.getOrcamentoTotal());
        pstmt.setString(8, c.getResponsavel());
        pstmt.setString(9, c.getStatus());
    
        // Define o ID para identificar qual registro atualizar
        pstmt.setInt(10, c.getId());
    
        // Executar a atualização
        int rowsUpdated = pstmt.executeUpdate();
    
        // Verificar se a atualização foi bem-sucedida
        if (rowsUpdated > 0) {
            System.out.println("Atualização bem-sucedida!");
        }
    
        // Fechar o PreparedStatement e a conexão
        pstmt.close();
        conn.close();
    }
     
    public void deletar(Construcao c) throws SQLException, ClassNotFoundException {
        Connection conn = ConexaoFactory.getConexao();
        String SQL = "DELETE FROM construcoes WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(SQL);
        pstmt.setInt(1, c.getId());
        
        pstmt.execute();

        pstmt.close();
        conn.close();
        
    }
    
}