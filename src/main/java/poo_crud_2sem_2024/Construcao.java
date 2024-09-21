package poo_crud_2sem_2024;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Construcao {

    private int         id;
    private String      nome;
    private String      endereco;
    private String      tipo;
    private LocalDate   dataInicio;
    private LocalDate   dataPrevisaoTermino;
    private int         areaTotal_m2;
    private double      orcamentoTotal;
    private String      responsavel;
    private String      status;

    public static Construcao inserir(Construcao c) throws ClassNotFoundException, SQLException{
        Connection conn = getConexao();
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
        return c;
    }
    
    public static List<Construcao> consultarTodos() throws SQLException, ClassNotFoundException {
        List<Construcao> construcoes = new ArrayList<>();
        
        Connection conn = getConexao();
        String SQL = "SELECT * FROM construcoes";
        PreparedStatement stmt = conn.prepareStatement(SQL);
        ResultSet rs = stmt.executeQuery();

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
        
        conn.close();

        return construcoes;
    }
    
    public static Construcao consultarByID(int id) throws SQLException, ClassNotFoundException{
        Connection conn = getConexao();
        String SQL = "SELECT * FROM construcoes WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(SQL);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        
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
        
        return cst;
    }
    
    public static void atualizar(Construcao c){
        
    }
    
    public static void deletar(Construcao c) throws SQLException, ClassNotFoundException {
        Connection conn = getConexao();
        String SQL = "DELETE FROM construcoes WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(SQL);
        stmt.setInt(1, c.getId());
        
        stmt.execute();
        
    }
    
    public static boolean hasConexao() throws SQLException, ClassNotFoundException {       
        return getConexao() != null;
    }
       
    //método para conectar ao banco de dados
    private static Connection getConexao () throws SQLException, ClassNotFoundException {
        String DRIVER = "org.postgresql.Driver";
        String URL = "jdbc:postgresql://localhost:5432/banco_poo_prj1";
        String USER = "postgres";
        String PASSWORD = "admin123";
        
        Class.forName(DRIVER);

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    @Override
    public String toString(){        
        return new StringBuilder()
            .append("ID: ").append(id).append("\n")
            .append("Nome: ").append(nome).append("\n")
            .append("Endereco: ").append(endereco).append("\n")
            .append("Tipo de Construcao: ").append(tipo).append("\n")
            .append("Data de Inicio: ").append(dataInicio).append("\n")
            .append("Data de previsao de termino: ").append(dataPrevisaoTermino).append("\n")
            .append("Area total (em m2): ").append(areaTotal_m2).append("\n")
            .append("Orcamento total: ").append(orcamentoTotal).append("\n")
            .append("Responsavel: ").append(responsavel).append("\n")
            .append("Status: ").append(status).append("\n")
            .toString();
    }
    
    //Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataPrevisaoTermino() {
        return dataPrevisaoTermino;
    }

    public void setDataPrevisaoTermino(LocalDate dataPrevisaoTermino) {
        this.dataPrevisaoTermino = dataPrevisaoTermino;
    }

    public int getAreaTotal_m2() {
        return areaTotal_m2;
    }

    public void setAreaTotal_m2(int areaTotal_m2) {
        this.areaTotal_m2 = areaTotal_m2;
    }

    public double getOrcamentoTotal() {
        return orcamentoTotal;
    }

    public void setOrcamentoTotal(double orcamentoTotal) {
        this.orcamentoTotal = orcamentoTotal;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}