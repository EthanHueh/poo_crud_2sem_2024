package poo_crud_2sem_2024;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
   
    //método para conectar ao banco de dados
    public static Connection getConexao () throws SQLException, ClassNotFoundException {
        String DRIVER = "org.postgresql.Driver";
        String URL = "jdbc:postgresql://localhost:5432/banco_poo_prj1";
        String USER = "postgres";
        String PASSWORD = "admin123";
        
        Class.forName(DRIVER);

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}

