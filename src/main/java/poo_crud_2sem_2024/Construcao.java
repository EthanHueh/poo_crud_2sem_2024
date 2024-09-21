package poo_crud_2sem_2024;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
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
    
    public static void cadastrar(Construcao c){
        
    }
    
    public static List<Construcao> consultarTodos(){
        return null;
    }
    
    public static Construcao consultarByID(int id){
        return null;
    }
    
    public static void atualizar(Construcao c){
        
    }
    
    public static void deletar(Construcao c){
        
    }
    
    public static void testarConexao() {
        conexao();
    }
       
    //método para conectar ao banco de dados
    private static Connection conexao(){
        String URL = "jdbc:postgresql://localhost:5432/banco_poo_prj1";
        String USER = "postgres";
        String PASSWORD = "admin123";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
            if (conn != null){
                System.out.println("Conectado ao Banco de Dados PostgreSQL com sucesso!!");
                return conn;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
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