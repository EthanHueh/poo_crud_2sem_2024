package poo_crud_2sem_2024;

import java.sql.SQLException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class Main {
    
    private static final Scanner in = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args){
        limparTerminal();
        setTextColor(15);
        l();
        testarConexao();

        do {
            l();
            System.out.println("\nSeja bem vindo!\n");
            l();
            System.out.println("Escolha a opcao desejada:\n");
            setTextColor(2);
            System.out.println("1 - Cadastrar construcao");
            setTextColor(13);
            System.out.println("2 - Consultar todos");
            setTextColor(14);
            System.out.println("3 - Consultar por ID");
            setTextColor(11);
            System.out.println("4 - Atualizar construcao");
            setTextColor(4);
            System.out.println("5 - Deletar construcao\n");
            setTextColor(8);
            System.out.println("6 - Sair\n");
            setTextColor(15);
            l();
            
            System.out.print("Sua opcao: ");
            int opcao = consistirInteiro();

            switch(opcao){
                case 1:
                    limparTerminal();
                    setTextColor(2);
                    cadastrar();
                    setTextColor(15);
                    pausarPrograma();
                    limparTerminal();
                    break;
                case 2:
                    limparTerminal();
                    setTextColor(13);
                    consultarTodasConstrucoes();
                    setTextColor(15);
                    pausarPrograma();
                    limparTerminal();
                    break;

                case 3:
                    limparTerminal();
                    setTextColor(14);
                    consultarByID();
                    setTextColor(15);
                    pausarPrograma();
                    limparTerminal();
                    break;

                case 4:
                    limparTerminal();
                    setTextColor(11);
                    atualizar();
                    setTextColor(15);
                    pausarPrograma();
                    limparTerminal();
                    break;

                case 5:
                    limparTerminal();
                    setTextColor(4);
                    deletarConstrucao();
                    setTextColor(15);
                    pausarPrograma();
                    limparTerminal();
                    break;

                case 6:
                    limparTerminal();
                    System.out.println("-------------------------- AVISO ----------------------------");
                    System.out.println("Deseja mesmo sair??");
                    System.out.print("Insira 's' caso queira sair: ");

                    char opcaoSair = in.nextLine().charAt(0);
                        
                    if (Character.toLowerCase(opcaoSair) == 's'){
                        System.out.println("Até mais!!");
                        limparTerminal();               
                        in.close();
                        return;
                    }
                    limparTerminal();
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
    
    public static void limparTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Nao foi possivel limpar o terminal :(");
        }
    }

    public static void pausarPrograma() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "pause").inheritIO().start().waitFor();
            } else {
                System.out.println("Pressione Enter para continuar...");
                in.nextLine();  // Pausa para sistemas Unix/Linux
            }
        } catch (Exception e) {
            System.out.println("Nao foi possivel pausar o programa :(");
        }
    }

    // Função para trocar a cor do texto
    public static void setTextColor(int color) {
        boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
        if (isWindows) {
            setTextColorWindows(color);
        } else {
            setTextColorUnix(color);
        }
    }
    
    // Método para mudar a cor do texto no Windows
    private static void setTextColorWindows(int color) {
        String cmd = String.format("cmd /c color %X", color);
        try {
            new ProcessBuilder("cmd", "/c", cmd).inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Nao foi possivel trocar a cor do terminal :(");
        }
    }

    // Método para trocar a cor do texto em Unix/Linux (usando ANSI codes)
    private static void setTextColorUnix(int color) {
        switch (color) {
            case 0: color = 30; break;  // Preto
            case 1: color = 34; break;  // Azul
            case 2: color = 32; break;  // Verde
            case 3: color = 36; break;  // Ciano
            case 4: color = 31; break;  // Vermelho
            case 5: color = 35; break;  // Roxo
            case 6: color = 33; break;  // Amarelo
            case 7: color = 37; break;  // Branco
            case 8: color = 90; break;  // Cinza Escuro
            case 9: color = 94; break;  // Azul Claro
            case 10: color = 92; break; // Verde Claro
            case 11: color = 96; break; // Ciano Claro
            case 12: color = 91; break; // Vermelho Claro
            case 13: color = 95; break; // Roxo Claro
            case 14: color = 93; break; // Amarelo Claro
            case 15: color = 97; break; // Branco Brilhante
            default: color = 37; break; // Padrão branco
        }
        System.out.printf("\033[%dm", color);
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
    
    private static int consistirInteiro(){
        do {
            try {
                return Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Insira um numero!!");
            }
        }
        while (true);
    }
    
    private static double consistirDouble() {
        do {
            try {
                return Double.parseDouble(in.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Insira um numero!!");
            }
        }
        while (true);
    }
    
    private static LocalDate consistirData() {
        do {
            try {
                // Converte a string para LocalDate
                return LocalDate.parse(in.nextLine(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida, tente novamente no formato dd/MM/yyyy.");
            }
        } while (true);
        
    }
    
    public static void cadastrar(){
        Construcao c = new Construcao();
        
        System.out.println("------------------------- CADASTRAR -------------------------");

        System.out.println("Insira o nome da nova construcao: ");
        String nome = in.nextLine();
        c.setNome(nome);

        System.out.println("Insira o endereco da nova construcao: ");
        String endereco = in.nextLine();
        c.setEndereco(endereco);

        System.out.println("Insira o tipo da nova construcao: ");
        String tipo = in.nextLine();
        c.setTipo(tipo);  

        System.out.println("Insira a data de inicio da nova construcao (dd/MM/yyyy): ");
        LocalDate dataInicio = consistirData();
        c.setDataInicio(dataInicio);

        System.out.println("Insira a data de previsao de termino da nova construcao (dd/MM/yyyy): ");
        LocalDate dataTermino = consistirData();
        c.setDataPrevisaoTermino(dataTermino);

        System.out.println("Insira a area em metros quadrados: ");
        int area = consistirInteiro();
        c.setAreaTotal_m2(area); 

        System.out.println("Insira o orcamento total da nova contrucao: ");
        double orcamento = consistirDouble();
        c.setOrcamentoTotal(orcamento); 

        System.out.println("Insira o nome do responsavel da  nova construcao: ");
        String responsavel = in.nextLine();
        c.setResponsavel(responsavel);
        
        System.out.println("Insira o status atual da construcao: ");
        String status = in.nextLine();
        c.setStatus(status);
        
        try {
            l();
            Construcao.inserir(c);
            l();
        } catch (SQLException | ClassNotFoundException ex){
            limparTerminal();
            l();
            System.out.println("Falha ao inserir os dados no cadastro.");
            l();
        }
    }

    private static void consultarTodasConstrucoes() {
        System.out.println("---------------------- CONSULTAR TODOS ----------------------");
        
        try {
            for (Construcao c: Construcao.consultarTodos()){
                System.out.println("\n"+c.toString());
                l();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            limparTerminal();
            l();
            System.out.println("Falha ao consultar as construcoes.");
            l();
        }
    }
    
    private static void consultarByID(){
        System.out.println("---------------------- CONSULTA POR ID ----------------------");
        System.out.println("Digite o ID da construcao que quer consultar");
        int id = consistirInteiro();
        l();
        try {
            System.out.println("\n"+Construcao.consultarByID(id).toString());
            l();
        } catch (SQLException | ClassNotFoundException ex) {
            limparTerminal();
            l();
            System.out.println("Falha ao consultar o cadastro.");
            System.out.println("tenha certeza que o cadastro com esse ID exista!");
            l();
        }
    }
    
    public static void atualizar(){
        Construcao c = new Construcao();

        //consistir para a sua construção
        do {
            System.out.println("------------------------- ATUALIZAR -------------------------");
            System.out.println("Insira o ID da construcao que quer atualizar: ");
            int id = consistirInteiro();
            l();
            try {
                System.out.println(Construcao.consultarByID(id).toString());
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println("Falha ao consultar o cadastro.");
                System.out.println("tenha certeza que o cadastro com esse ID exista!");
                l();
                return;
            }
            l();
            System.out.println("\nEsta é a construcao que deseja atualizar?(s/n) ");
            char opcaoSair = in.nextLine().charAt(0);    
                if (Character.toLowerCase(opcaoSair) == 's'){         
                    c.setId(id);
                    break;
                }
            l();
            limparTerminal();
            
        }
        while(true);

        System.out.println("Insira o novo nome da construcao: ");
        String nome = in.nextLine();
        c.setNome(nome);

        System.out.println("Insira o novo endereco da construcao: ");
        String endereco = in.nextLine();
        c.setEndereco(endereco);

        System.out.println("Insira o novo tipo da construcao: ");
        String tipo = in.nextLine();
        c.setTipo(tipo);  

        System.out.println("Insira a nova data de inicio da construcao (dd/MM/yyyy): ");
        LocalDate dataInicio = consistirData();
        c.setDataInicio(dataInicio);

        System.out.println("Insira a nova data de previsao de termino da construcao (dd/MM/yyyy): ");
        LocalDate dataTermino = consistirData();
        c.setDataPrevisaoTermino(dataTermino);

        System.out.println("Insira a nova area em metros quadrados: ");
        int area = consistirInteiro();
        c.setAreaTotal_m2(area); 

        System.out.println("Insira o novo orcamento total da contrucao: ");
        double orcamento = consistirDouble();
        c.setOrcamentoTotal(orcamento); 

        System.out.println("Insira o novo nome do responsavel da construcao: ");
        String responsavel = in.nextLine();
        c.setResponsavel(responsavel);
        
        System.out.println("Insira o novo status atual da construcao: ");
        String status = in.nextLine();
        c.setStatus(status);

        try {
            l();
            Construcao.atualizar(c);
            l();
        } catch (SQLException | ClassNotFoundException ex){
            limparTerminal();
            l();
            System.out.println("Falha ao atualizar os dados da construcao.");
            l();
        }
    }

    private static void deletarConstrucao() {
        System.out.println("-------------------------- DELETAR --------------------------");
        System.out.println("Construcoes cadastradas:");
        l();
        listarConstrucoes();
        l();
        System.out.print("Insira o nº da construcao que queira deletar: ");
        int opcaoConstrucao = consistirInteiro();
        
        try {
            Construcao.deletar(Construcao.consultarTodos().get(opcaoConstrucao - 1));
            l();
            System.out.println("Cadastro da construcao foi deletado com sucesso!");
            l();
        } catch (SQLException | ClassNotFoundException | IndexOutOfBoundsException ex) {
            limparTerminal();
            l();
            System.out.println("Falha ao deletar o cadastro.");
            System.out.println("Lembre-se de escolher um dos itens da lista que lhe eh mostrada");
            l();
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
            l();
            System.out.println("Falha ao consultar os cadastros");
            l();
        }
    }
    
}