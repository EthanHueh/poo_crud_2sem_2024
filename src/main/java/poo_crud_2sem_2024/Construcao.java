package poo_crud_2sem_2024;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    
    @Override
    public String toString(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return new StringBuilder()
            .append("ID: \t\t\t\t").append(id).append("\n")
            .append("Nome: \t\t\t\t").append(nome).append("\n")
            .append("Endereco: \t\t\t").append(endereco).append("\n")
            .append("Tipo de Construcao: \t\t").append(tipo).append("\n")
            .append("Data de Inicio: \t\t").append(formatter.format(dataInicio)).append("\n")
            .append("Data de previsao de termino: \t").append(formatter.format(dataPrevisaoTermino)).append("\n")
            .append("Area total (em m2): \t\t").append(areaTotal_m2).append("\n")
            .append("Orcamento total: \t\t").append(orcamentoTotal).append("\n")
            .append("Responsavel: \t\t\t").append(responsavel).append("\n")
            .append("Status: \t\t\t").append(status).append("\n")
            .toString();
    }

    public String toLinhaTabela() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
        return new StringBuilder()
            .append(String.format("\t%-5d\t|\t", id))                            // ID com largura de 5
            .append(String.format("%-20s\t|\t", nome.length() > 20 ? nome.substring(0, 17) + "..." : nome))   // Nome com limite de 20 caracteres
            .append(String.format("%-25s\t|\t", endereco.length() > 25 ? endereco.substring(0, 22) + "..." : endereco)) // Endereço com limite de 25 caracteres
            .append(String.format("%-10s\t|\t", tipo))                            // Tipo com largura de 10
            .append(String.format("%-12s\t|\t", formatter.format(dataInicio)))    // Data de início com largura de 12
            .append(String.format("%-15s\t|\t", formatter.format(dataPrevisaoTermino))) // Data de previsão com largura de 15
            .append(String.format("%-7d m2\t|\t", areaTotal_m2))                  // Área total
            .append(String.format("R$ %-10.2f\t|\t", orcamentoTotal))             // Orçamento com 2 casas decimais
            .append(String.format("%-15s\t|\t", responsavel.length() > 15 ? responsavel.substring(0, 12) + "..." : responsavel)) // Responsável com limite de 15 caracteres
            .append(String.format("%-10s", status))                               // Status com largura de 10
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