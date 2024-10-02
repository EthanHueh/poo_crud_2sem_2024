package poo_crud_2sem_2024;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Terminal {
    
    private final Scanner in = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final String systemName = System.getProperty("os.name");
    
    public int consistirInteiro(){
        do {
            try {
                return Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Insira um numero!!");
            }
        }
        while (true);
    }
    
    public double consistirDouble() {
        do {
            try {
                return Double.parseDouble(in.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Insira um numero!!");
            }
        }
        while (true);
    }
    
    public LocalDate consistirData() {
        do {
            try {
                // Converte a string para LocalDate
                return LocalDate.parse(in.nextLine(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida, tente novamente no formato dd/MM/yyyy.");
            }
        } while (true);
        
    }
    
    //método que imprime uma linha na tela
    public void l(){
        System.out.println("-------------------------------------------------------------");
    }
    
    public void limparTerminal() {
        try {
            if (systemName.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Nao foi possivel limpar o terminal :(");
        }
    }

    public void pausarPrograma() {
        try {
            if (systemName.contains("Windows")) {
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
    public void setTextColor(int color) {
        boolean isWindows = systemName.toLowerCase().contains("windows");
        
        if (isWindows) {
            setTextColorWindows(color);
        } else {
            setTextColorUnix(color);
        }
    }
    
    // Método para mudar a cor do texto no Windows
    public void setTextColorWindows(int textColor) {
        String cmd = String.format("color %X%X", 0, textColor);
        try {
            new ProcessBuilder("cmd", "/c", cmd).inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Nao foi possivel trocar a cor do terminal :(");
        }
    }

    // Método para trocar a cor do texto em Unix/Linux (usando ANSI codes)
    public void setTextColorUnix(int color) {
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
}
