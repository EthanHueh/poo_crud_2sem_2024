package poo_crud_2sem_2024;

import java.sql.SQLException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class Main {
    
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args){
        
        testarConexao();

        do {
            System.out.println("\nSeja bem vindo!\n");
            l();
            System.out.println("Escolha a opcao desejada:\n");
            System.out.println("1 - Cadastrar construcao");
            System.out.println("2 - Consultar todos");
            System.out.println("3 - Consultar por ID");
            System.out.println("4 - Atualizar construcao");
            System.out.println("5 - Deletar consrucao\n");

            System.out.println("6 - Sair\n");
            l();
            
            System.out.print("Sua opcao: ");
            int opcao = 0;
            try {
                opcao = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Insira um numero!!");
            }

            switch(opcao){
                case 1:
                    cadastrar();
                    break;
                case 2:
                    consultarTodasConstrucoes();
                    break;

                case 3:
                    consultarByID();
                    break;

                case 4:
                    atualizar();
                    break;

                case 5:
                    deletarConstrucao();
                    break;

                case 6:
                    System.out.println("-------------------------- AVISO ----------------------------");
                    System.out.println("Deseja mesmo sair??");
                    System.out.print("Insira 's' caso queira sair: ");

                    char opcaoSair = in.nextLine().charAt(0);
                        
                    if (Character.toLowerCase(opcaoSair) == 's'){
                        System.out.println("Até mais!!");               
                        in.close();
                        return;
                    }

                    break;
                    
                default:
                    System.out.println("Insira uma opcao valida!!");

            }
            
        }
        while(true);
    }

    //método que imprime uma linha na tela
    public static void l(){
        System.out.println("-------------------------------------------------------------");
    }

    private static void testarConexao() {
        l();
        try {
            if (Construcao.hasConexao()){
                System.out.println("Conectado ao Banco de Dados PostgreSQL com sucesso!!");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        l();
    }
    
    public static void cadastrar(){
        Construcao c = new Construcao();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("------------------------- CADASTRAR -------------------------");

        System.out.println("Insira o nome da construcao: ");
        String nome = in.nextLine();
        c.setNome(nome);

        System.out.println("Insira o endereco da construcao: ");
        String endereco = in.nextLine();
        c.setEndereco(endereco);

        System.out.println("Insira o tipo da construcao: ");
        String tipo = in.nextLine();
        c.setTipo(tipo);  

        LocalDate dataInicio = null;
        boolean dataValida = false;

        while (!dataValida) {
            System.out.println("Insira a data de inicio da construcao (dd/MM/yyyy): ");
            String dataStr = in.nextLine();

            try {
                // Converte a string para LocalDate
                dataInicio = LocalDate.parse(dataStr, formatter);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida, tente novamente no formato dd/MM/yyyy.");
            }
        }
        c.setDataInicio(dataInicio);

        LocalDate dataTermino = null;
        dataValida = false;
        while (!dataValida) {
            System.out.println("Insira a data de previsao de termino da construcao (dd/MM/yyyy): ");
            String dataStr = in.nextLine();

            try {
                // Converte a string para LocalDate
                dataTermino = LocalDate.parse(dataStr, formatter);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida, tente novamente no formato dd/MM/yyyy.");
            }
        }
        c.setDataPrevisaoTermino(dataTermino);

        System.out.println("Insira a area em metros quadrados: ");
        int area = in.nextInt();
        c.setAreaTotal_m2(area); 

        System.out.println("Insira o orcamento total da contrucao: ");
        double orcamento = in.nextDouble();
        c.setOrcamentoTotal(orcamento); 

        System.out.println("Insira o nome do responsavel da construcao: ");
        String responsavel = in.nextLine();
        responsavel = in.nextLine();
        c.setResponsavel(responsavel);
        
        System.out.println("Insira o status atual da construcao: ");
        String status = in.nextLine();
        c.setStatus(status);
        
        try {
            Construcao.inserir(c);
        } catch (SQLException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static void consultarTodasConstrucoes() {
        l();
        
        try {
            for (Construcao c: Construcao.consultarTodos()){
                l();
                System.out.println(c.toString());
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private static void consultarByID(){
        int id = 0;
        try {
            l();
            System.out.print("Insira o id da construcao desejada: ");
            id = Integer.parseInt(in.nextLine());
            l();
        } catch (NumberFormatException e){
            System.out.println("Insira um numero!!");
            return;
        }

        try {
            System.out.println(Construcao.consultarByID(id).toString());
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void atualizar(){
        Construcao c = new Construcao();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("------------------------- ATUALIZAR -------------------------");

        //consistir para a sua construção
        boolean isSuaConstrucao = false;
        while(isSuaConstrucao){
            System.out.println("Insira o ID da construcao que quer atualizar: ");
            int id = in.nextInt();
            l();
            try {
                System.out.println(Construcao.consultarByID(id).toString());
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("\nEstá é a construcao que deseja atualizar?(s/n) ");
            char opcaoSair = in.nextLine().charAt(0);    
                if (Character.toLowerCase(opcaoSair) == 's'){
                    isSuaConstrucao = true;            
                    c.setId(id);
                }
            l();
        }

        System.out.println("Insira o novo nome da construcao: ");
        String nome = in.nextLine();
        c.setNome(nome);

        System.out.println("Insira o novo endereco da construcao: ");
        String endereco = in.nextLine();
        c.setEndereco(endereco);

        System.out.println("Insira o novo tipo da construcao: ");
        String tipo = in.nextLine();
        c.setTipo(tipo);  

        LocalDate dataInicio = null;
        boolean dataValida = false;

        while (!dataValida) {
            System.out.println("Insira a nova data de inicio da construcao (dd/MM/yyyy): ");
            String dataStr = in.nextLine();

            try {
                // Converte a string para LocalDate
                dataInicio = LocalDate.parse(dataStr, formatter);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida, tente novamente no formato dd/MM/yyyy.");
            }
        }
        c.setDataInicio(dataInicio);

        LocalDate dataTermino = null;
        dataValida = false;
        while (!dataValida) {
            System.out.println("Insira a nova data de previsao de termino da construcao (dd/MM/yyyy): ");
            String dataStr = in.nextLine();

            try {
                // Converte a string para LocalDate
                dataTermino = LocalDate.parse(dataStr, formatter);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida, tente novamente no formato dd/MM/yyyy.");
            }
        }
        c.setDataPrevisaoTermino(dataTermino);

        System.out.println("Insira a nova area em metros quadrados: ");
        int area = in.nextInt();
        c.setAreaTotal_m2(area); 

        System.out.println("Insira o novo orcamento total da contrucao: ");
        double orcamento = in.nextDouble();
        c.setOrcamentoTotal(orcamento); 

        System.out.println("Insira o novo nome do responsavel da construcao: ");
        String responsavel = in.nextLine();
        responsavel = in.nextLine();
        c.setResponsavel(responsavel);
        
        System.out.println("Insira o novo status atual da construcao: ");
        String status = in.nextLine();
        c.setStatus(status);

        try {
            Construcao.atualizar(c);
        } catch (SQLException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static void deletarConstrucao() {
        listarConstrucoes();
        l();

        int opcaoConstrucao = 0;
        try {
            System.out.print("Insira o nº da construcao que queira deletar: ");
            opcaoConstrucao = Integer.parseInt(in.nextLine());

            try {
                Construcao.deletar(Construcao.consultarTodos().get(opcaoConstrucao - 1));
            } catch (SQLException | ClassNotFoundException | IndexOutOfBoundsException ex) {
                System.out.println(ex.getMessage());
            }

        } catch (NumberFormatException e){
            System.out.println("Insira um numero!!");
        }
    }

    private static void listarConstrucoes() {
        l();
        try {
            int contador = 1;
            for (Construcao c: Construcao.consultarTodos()){
                l();
                System.out.println(contador+" - "+c.getNome());
                contador++;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}