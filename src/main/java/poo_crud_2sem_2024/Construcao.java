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
            .append("ID: ").append(id).append("\n")
            .append("Nome: ").append(nome).append("\n")
            .append("Endereco: ").append(endereco).append("\n")
            .append("Tipo de Construcao: ").append(tipo).append("\n")
            .append("Data de Inicio: ").append(formatter.format(dataInicio)).append("\n")
            .append("Data de previsao de termino: ").append(formatter.format(dataPrevisaoTermino)).append("\n")
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