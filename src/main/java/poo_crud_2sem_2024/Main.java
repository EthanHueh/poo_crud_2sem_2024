package poo_crud_2sem_2024;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {

        l();
        testarConexao();
        
        Scanner sc = new Scanner(System.in);
        
        do {
            l();
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
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Insira um numero!!");
            }

            switch(opcao){
                case 1:        
                    break;

                case 2:
                    consultarTodasConstrucoes();
                    break;

                case 3:
                    int id = 0;
                    try {
                        l();
                        System.out.print("Insira o id da construcao desejada: ");
                        id = Integer.parseInt(sc.nextLine());
                        l();
                    } catch (NumberFormatException e){
                        System.out.println("Insira um numero!!");
                        break;
                    }
                    
                    try {
                        System.out.println(Construcao.consultarByID(id).toString());
                    } catch (SQLException | ClassNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }

                    break;

                case 4:
                    break;

                case 5:
                    listarConstrucoes();
                    l();
                    
                    int opcaoConstrucao = 0;
                    try {
                        System.out.print("Insira o nº da construcao que queira deletar: ");
                        opcaoConstrucao = Integer.parseInt(sc.nextLine());
                        
                        try {
                            Construcao.deletar(Construcao.consultarTodos().get(opcaoConstrucao - 1));
                        } catch (SQLException | ClassNotFoundException | IndexOutOfBoundsException ex) {
                            System.out.println(ex.getMessage());
                        }
                        
                    } catch (NumberFormatException e){
                        System.out.println("Insira um numero!!");
                        break;
                    }
                    
                    break;

                case 6:
                    System.out.println("-------------------------- AVISO ----------------------------");
                    System.out.println("Deseja mesmo sair??");
                    System.out.print("Insira 's' caso queira sair: ");

                    char opcaoSair = sc.nextLine().charAt(0);
                        
                    if (Character.toLowerCase(opcaoSair) == 's'){
                        System.out.println("Até mais!!");               
                        sc.close();
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
        try {
            if (Construcao.hasConexao()){
                System.out.println("Conectado ao Banco de Dados PostgreSQL com sucesso!!");
            }
        } catch (SQLException | ClassNotFoundException ex) {
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