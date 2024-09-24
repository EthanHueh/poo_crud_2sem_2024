package poo_crud_2sem_2024;

import java.sql.SQLException;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    
    private static final Scanner in = new Scanner(System.in);
    private static final Terminal term = new Terminal();

    public static void main(String[] args){
        term.limparTerminal();
        term.setTextColor(15);
        term.l();
        testarConexao();
        term.l();
        
        System.out.println("\nPersistenca sem DAO");
        System.out.println("POO - 2o semestre/2024\n");
        System.out.println("Feito por:");
        System.out.println("- Fabio Casagrande");
        System.out.println("- Nattan Silva de Souza\n");
        

        do {
            term.l();
            System.out.println("\nSeja bem vindo!\n");
            term.l();
            System.out.println("Escolha a opcao desejada:\n");
            term.setTextColor(2);
            System.out.println("1 - Cadastrar construcao");
            term.setTextColor(13);
            System.out.println("2 - Consultar todos");
            term.setTextColor(14);
            System.out.println("3 - Consultar por ID");
            term.setTextColor(11);
            System.out.println("4 - Atualizar construcao");
            term.setTextColor(4);
            System.out.println("5 - Deletar construcao\n");
            term.setTextColor(8);
            System.out.println("6 - Sair\n");
            term.setTextColor(15);
            term.l();
            
            System.out.print("Sua opcao: ");
            int opcao = term.consistirInteiro();

            switch(opcao){
                case 1:
                    term.limparTerminal();
                    term.setTextColor(2);
                    cadastrar();
                    term.setTextColor(15);
                    term.pausarPrograma();
                    term.limparTerminal();
                    break;
                case 2:
                    term.limparTerminal();
                    term.setTextColor(13);
                    consultarTodasConstrucoes();
                    term.setTextColor(15);
                    term.pausarPrograma();
                    term.limparTerminal();
                    break;

                case 3:
                    term.limparTerminal();
                    term.setTextColor(14);
                    consultarByID();
                    term.setTextColor(15);
                    term.pausarPrograma();
                    term.limparTerminal();
                    break;

                case 4:
                    term.limparTerminal();
                    term.setTextColor(11);
                    atualizar();
                    term.setTextColor(15);
                    term.pausarPrograma();
                    term.limparTerminal();
                    break;

                case 5:
                    term.limparTerminal();
                    term.setTextColor(4);
                    deletarConstrucao();
                    term.setTextColor(15);
                    term.pausarPrograma();
                    term.limparTerminal();
                    break;

                case 6:
                    term.limparTerminal();
                    System.out.println("-------------------------- AVISO ----------------------------");
                    System.out.println("Deseja mesmo sair??");
                    System.out.print("Insira 's' caso queira sair: ");

                    char opcaoSair = in.nextLine().charAt(0);
                        
                    if (Character.toLowerCase(opcaoSair) == 's'){
                        term.l();
                        System.out.println("Até mais!!");
                        term.pausarPrograma();
                        term.limparTerminal();               
                        in.close();
                        return;
                    }
                    term.limparTerminal();
                    break;
                    
                default:
                    System.out.println("Insira uma opcao valida!!");

            }
            
        }
        while(true);
    }

    private static void testarConexao() {
        try {
            if (Construcao.hasConexao()){
                System.out.println("Conectado ao Banco de Dados PostgreSQL com sucesso!!");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Nao foi possivel se conectar ao Banco de Dados!!!");
        }
    }
        
    public static void cadastrar(){
        Construcao c = new Construcao();
        
        System.out.println("------------------------- CADASTRAR -------------------------");

        System.out.print("Insira o nome da nova construcao: ");
        String nome = in.nextLine();
        c.setNome(nome);

        System.out.print("Insira o endereco da nova construcao: ");
        String endereco = in.nextLine();
        c.setEndereco(endereco);

        System.out.print("Insira o tipo da nova construcao: ");
        String tipo = in.nextLine();
        c.setTipo(tipo);  

        System.out.print("Insira a data de inicio da nova construcao (dd/MM/yyyy): ");
        LocalDate dataInicio = term.consistirData();
        c.setDataInicio(dataInicio);

        System.out.print("Insira a data de previsao de termino da nova construcao (dd/MM/yyyy): ");
        LocalDate dataTermino = term.consistirData();
        c.setDataPrevisaoTermino(dataTermino);

        System.out.print("Insira a area em metros quadrados: ");
        int area = term.consistirInteiro();
        c.setAreaTotal_m2(area); 

        System.out.print("Insira o orcamento total da nova contrucao: ");
        double orcamento = term.consistirDouble();
        c.setOrcamentoTotal(orcamento); 

        System.out.print("Insira o nome do responsavel da  nova construcao: ");
        String responsavel = in.nextLine();
        c.setResponsavel(responsavel);
        
        System.out.print("Insira o status atual da construcao: ");
        String status = in.nextLine();
        c.setStatus(status);
        
        try {
            term.l();
            Construcao.inserir(c);
            term.l();
        } catch (SQLException | ClassNotFoundException ex){
            term.limparTerminal();
            term.l();
            System.out.println("Falha ao inserir os dados no cadastro.");
            term.l();
        }
    }

    private static void consultarTodasConstrucoes() {
        System.out.println("---------------------- CONSULTAR TODOS ----------------------");
        
        try {
            for (Construcao c: Construcao.consultarTodos()){
                System.out.println("\n"+c.toString());
                term.l();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            term.limparTerminal();
            term.l();
            System.out.println("Falha ao consultar as construcoes.");
            term.l();
        }
    }
    
    private static void consultarByID(){
        System.out.println("---------------------- CONSULTA POR ID ----------------------");
        System.out.print("Digite o ID da construcao que quer consultar: ");
        int id = term.consistirInteiro();
        term.l();
        try {
            System.out.println("\n"+Construcao.consultarByID(id).toString());
            term.l();
        } catch (SQLException | ClassNotFoundException ex) {
            term.limparTerminal();
            term.l();
            System.out.println("Falha ao consultar o cadastro.");
            System.out.println("Tenha certeza que o cadastro com esse ID exista!");
            term.l();
        }
    }
    
    public static void atualizar(){
        Construcao c = new Construcao();

        //consistir para a sua construção
        do {
            System.out.println("------------------------- ATUALIZAR -------------------------");
            System.out.println("Insira o ID da construcao que quer atualizar: ");
            int id = term.consistirInteiro();
            term.l();
            try {
                System.out.println(Construcao.consultarByID(id).toString());
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println("Falha ao consultar o cadastro.");
                System.out.println("Tenha certeza que o cadastro com esse ID exista!");
                term.l();
                return;
            }
            term.l();
            System.out.print("\nEsta é a construcao que deseja atualizar?(s/n) ");
            char opcaoSair = in.nextLine().charAt(0);    
                if (Character.toLowerCase(opcaoSair) == 's'){         
                    c.setId(id);
                    break;
                }
            term.l();
            term.limparTerminal();
            
        }
        while(true);

        System.out.print("Insira o novo nome da construcao: ");
        String nome = in.nextLine();
        c.setNome(nome);

        System.out.print("Insira o novo endereco da construcao: ");
        String endereco = in.nextLine();
        c.setEndereco(endereco);

        System.out.print("Insira o novo tipo da construcao: ");
        String tipo = in.nextLine();
        c.setTipo(tipo);  

        System.out.print("Insira a nova data de inicio da construcao (dd/MM/yyyy): ");
        LocalDate dataInicio = term.consistirData();
        c.setDataInicio(dataInicio);

        System.out.print("Insira a nova data de previsao de termino da construcao (dd/MM/yyyy): ");
        LocalDate dataTermino = term.consistirData();
        c.setDataPrevisaoTermino(dataTermino);

        System.out.print("Insira a nova area em metros quadrados: ");
        int area = term.consistirInteiro();
        c.setAreaTotal_m2(area); 

        System.out.print("Insira o novo orcamento total da contrucao: ");
        double orcamento = term.consistirDouble();
        c.setOrcamentoTotal(orcamento); 

        System.out.print("Insira o novo nome do responsavel da construcao: ");
        String responsavel = in.nextLine();
        c.setResponsavel(responsavel);
        
        System.out.print("Insira o novo status atual da construcao: ");
        String status = in.nextLine();
        c.setStatus(status);

        try {
            term.l();
            Construcao.atualizar(c);
            term.l();
        } catch (SQLException | ClassNotFoundException ex){
            term.limparTerminal();
            term.l();
            System.out.println("Falha ao atualizar os dados da construcao.");
            term.l();
        }
    }

    private static void deletarConstrucao() {
        System.out.println("-------------------------- DELETAR --------------------------");
        System.out.println("Construcoes cadastradas:");
        term.l();
        listarConstrucoes();
        term.l();
        System.out.print("Insira o nº da construcao que queira deletar: ");
        int opcaoConstrucao = term.consistirInteiro();
        
        try {
            Construcao.deletar(Construcao.consultarTodos().get(opcaoConstrucao - 1));
            term.l();
            System.out.println("Cadastro da construcao foi deletado com sucesso!");
            term.l();
        } catch (SQLException | ClassNotFoundException | IndexOutOfBoundsException ex) {
            term.limparTerminal();
            term.l();
            System.out.println("Falha ao deletar o cadastro.");
            System.out.println("Lembre-se de escolher um dos itens da lista que lhe eh mostrada.");
            term.l();
        }
        
    }

    private static void listarConstrucoes() {
        try {
            int contador = 1;
            for (Construcao c: Construcao.consultarTodos()){
                System.out.println("   "+contador+" - "+c.getNome());
                contador++;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            term.l();
            System.out.println("Falha ao consultar os cadastros.");
            term.l();
        }
    }
    
}