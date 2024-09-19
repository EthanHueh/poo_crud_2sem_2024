package poo_crud_2sem_2024;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("Seja bem vindo!\n");

            System.out.println("Escolha a opcao desejada:");
            System.out.println("1 - Cadastrar construcao");
            System.out.println("2 - Consultar todos");
            System.out.println("3 - Consultar por ID");
            System.out.println("4 - Atualizar construcao");
            System.out.println("5 - Deletar consrucao\n");

            System.out.println("6 - Sair\n");

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
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    System.out.println("------- AVISO -------");
                    System.out.println("Deseja mesmo sair??");
                    System.out.print("Insira 's' caso queira sair: ");

                    char opcaoSair = sc.nextLine().charAt(0);
                        
                    if (Character.toLowerCase(opcaoSair) == 's'){
                        System.out.println("Até mais!!");               
                        sc.close();
                        return;
                    }

                    break;

            }
            
        }
        while(true);
    }
    
}