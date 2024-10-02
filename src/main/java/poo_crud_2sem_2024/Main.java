package poo_crud_2sem_2024;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    
    private static final Scanner in = new Scanner(System.in);
    private static final Terminal term = new Terminal();
    private static final ConstrucaoDAO conDAO = new ConstrucaoDAO();

    public static void main(String[] args){
        term.limparTerminal();
        term.setTextColor(15);
        term.l();
        testarConexao();
        term.l();
        System.out.println("\nPersistenca com DAO");
        System.out.println("POO - 2o semestre/2024\n");
        System.out.println("Feito por:");
        System.out.println("- Fabio Casagrande");
        System.out.println("- Nattan Silva de Souza\n");
        term.l();

        term.pausarPrograma();
        
        do {
            term.limparTerminal();
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
                    break;
                case 2:
                    term.limparTerminal();
                    term.setTextColor(13);
                    consultarTodasConstrucoes();
                    break;

                case 3:
                    term.limparTerminal();
                    term.setTextColor(14);
                    consultarByID();
                    break;

                case 4:
                    term.limparTerminal();
                    term.setTextColor(11);
                    atualizar();
                    break;

                case 5:
                    term.limparTerminal();
                    term.setTextColor(4);
                    deletarConstrucao();
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
                    break;
                    
                default:
                    System.out.println("Insira uma opcao valida!!");

            }

            term.setTextColor(15);
            term.pausarPrograma();
            term.limparTerminal();
            
        }
        while(true);
    }

    private static void testarConexao() {
        try {
            if (ConexaoFactory.getConexao() != null){
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
            conDAO.inserir(c);
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

        System.out.println("Escolha a opcao desejada:\n");
        System.out.println("1 - Exibicao simples");
        System.out.println("2 - Exibicao completa\n");
        term.l();
        System.out.print("Sua opcao: ");
        int opcao = term.consistirInteiro();

        try {
            term.limparTerminal();

            List<Construcao> construcoes = conDAO.consultarTodos();

            term.l();
            System.out.println("Numero de registros retornados: "+conDAO.consultarTodos().size());
            term.l();
            

            switch (opcao) {
                case 1:
                    System.out.println("\tID\t|\tNOME");
                    System.out.println("----------------|--------------------------------------------");
                    for (Construcao c: construcoes){
                        System.out.println("\t"+c.getId()+"\t|\t"+c.getNome());
                    }
                    term.l();
                    break;
                
                case 2:
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("\tID  \t|\tNOME                \t|\tENDERECO                    \t|\tTIPO       \t|\tDATA INICIO\t|\tPREV. TERMINO \t|\tAREA   \t\t|\tORCAMENTO  \t|\tRESPONSAVEL \t|\t    STATUS");     
                    System.out.println("----------------|-------------------------------|---------------------------------------|-----------------------|-----------------------|-----------------------|-----------------------|-----------------------|-----------------------|----------------------------");

                    for (Construcao c : construcoes) {
                        System.out.println(c.toLinhaTabela());
                    }
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    break;
            
                default:
                    break;
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
        Construcao c = new Construcao();
        c.setId(id);

        term.l();
        try {
            System.out.println("\n"+conDAO.consultarByID(c).toString());
            term.l();
        } catch (SQLException | ClassNotFoundException ex) {
            term.limparTerminal();
            term.l();
            System.out.println("Falha ao consultar o cadastro.");
            System.out.println("Tenha certeza que o cadastro com esse ID exista!");
            term.l();
        }
    }
    
    public static void atualizar() {
        Construcao c = new Construcao();
    
        // consistir para a sua construção
        do {
            System.out.println("------------------------- ATUALIZAR -------------------------");
            System.out.print("Insira o ID da construcao que quer atualizar: ");
    
            int id = term.consistirInteiro();
            c.setId(id);
    
            term.l();
            try {
                Construcao construcaoExistente = conDAO.consultarByID(c);
                System.out.println("\n"+construcaoExistente.toString());
                c = construcaoExistente; // recupera os dados da construção existente
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println("Falha ao consultar o cadastro.");
                System.out.println("Tenha certeza que o cadastro com esse ID exista!");
                term.l();
                return;
            }
            term.l();
            System.out.print("\nEsta é a construcao que deseja atualizar?(s/n) ");
            char opcaoSair = in.nextLine().charAt(0);
            if (Character.toLowerCase(opcaoSair) == 's') {
                c.setId(id);
                break;
            }
            term.l();
            term.limparTerminal();
        } while (true);
    
        boolean continuar = true;
        while (continuar) {
            term.limparTerminal();
            System.out.println("------------- CONSTRUCAO QUE ESTAMOS ATUALIZANDO -------------");
            System.out.println("\n" + c.toString());
            term.l();
            System.out.println("           Selecione o campo que deseja atualizar:");
            term.l();
            System.out.println("  1 - Nome");
            System.out.println("  2 - Endereco");
            System.out.println("  3 - Tipo");
            System.out.println("  4 - Data de Inicio");
            System.out.println("  5 - Data de Previsao de Termino");
            System.out.println("  6 - Area Total (m2)");
            System.out.println("  7 - Orcamento Total");
            System.out.println("  8 - Responsavel");
            System.out.println("  9 - Status");
            System.out.println("  0 - Finalizar Atualizacao");
            System.out.println(" -1 - Descartar alteracoes");
            
            term.l();
            System.out.print("Opcao: ");
            int opcao = term.consistirInteiro();
            term.l();
    
            switch (opcao) {
                case 1:
                    System.out.print("Insira o novo nome da construcao: ");
                    String nome = in.nextLine();
                    c.setNome(nome);
                    break;
                case 2:
                    System.out.print("Insira o novo endereco da construcao: ");
                    String endereco = in.nextLine();
                    c.setEndereco(endereco);
                    break;
                case 3:
                    System.out.print("Insira o novo tipo da construcao: ");
                    String tipo = in.nextLine();
                    c.setTipo(tipo);
                    break;
                case 4:
                    System.out.print("Insira a nova data de inicio da construcao (dd/MM/yyyy): ");
                    LocalDate dataInicio = term.consistirData();
                    c.setDataInicio(dataInicio);
                    break;
                case 5:
                    System.out.print("Insira a nova data de previsao de termino da construcao (dd/MM/yyyy): ");
                    LocalDate dataTermino = term.consistirData();
                    c.setDataPrevisaoTermino(dataTermino);
                    break;
                case 6:
                    System.out.print("Insira a nova area em metros quadrados: ");
                    int area = term.consistirInteiro();
                    c.setAreaTotal_m2(area);
                    break;
                case 7:
                    System.out.print("Insira o novo orcamento total da construcao: ");
                    double orcamento = term.consistirDouble();
                    c.setOrcamentoTotal(orcamento);
                    break;
                case 8:
                    System.out.print("Insira o novo nome do responsavel da construcao: ");
                    String responsavel = in.nextLine();
                    c.setResponsavel(responsavel);
                    break;
                case 9:
                    System.out.print("Insira o novo status atual da construcao: ");
                    String status = in.nextLine();
                    c.setStatus(status);
                    break;
                case 0:
                    continuar = false;
                    break;
                case -1:
                    term.limparTerminal();
                    term.l();
                    System.out.println("Atualizacao cancelada.");
                    term.l();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    term.pausarPrograma();
                    break;
            }

            term.limparTerminal();
        }
    
        try {
            term.l();
            conDAO.atualizar(c);
            term.l();
        } catch (SQLException | ClassNotFoundException ex) {
            term.limparTerminal();
            term.l();
            System.out.println("Falha ao atualizar os dados da construcao.");
            term.l();
        }
    }
    
    private static void deletarConstrucao() {

        Construcao c = new Construcao();
        do {
            System.out.println("-------------------------- DELETAR --------------------------");
            System.out.print("Insira o ID da construcao que queira deletar: ");
    
            int id = term.consistirInteiro();
            c.setId(id);
    
            term.l();
            try {
                Construcao construcaoExistente = conDAO.consultarByID(c);
                System.out.println("\n"+construcaoExistente.toString());
                c = construcaoExistente; // recupera os dados da construção existente
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println("Falha ao consultar o cadastro.");
                System.out.println("Tenha certeza que o cadastro com esse ID exista!");
                term.l();
                return;
            }
            term.l();
            System.out.print("\nEsta é a construcao que deseja deletar?(s/n) ");
            char opcaoSair = in.nextLine().charAt(0);
            if (Character.toLowerCase(opcaoSair) == 's') {
                c.setId(id);
                break;
            }
            term.l();
            term.limparTerminal();

        } while (true);
 
        try {
            conDAO.deletar(c);
            term.l();
            System.out.println("Cadastro da construcao foi deletado com sucesso!");
            term.l();
        } catch (SQLException | ClassNotFoundException ex) {
            term.limparTerminal();
            term.l();
            System.out.println("Falha ao deletar o cadastro.");
            System.out.println("Lembre-se de escolher um dos itens da lista que lhe eh mostrada.");
            term.l();
        }
        
    }
    
}