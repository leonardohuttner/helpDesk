package br.com.myproject3.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
/**
 *
 * @author leonardohuttner
 */
public class connection {

    public static Connection getConexao(){
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ifsul","root","root");
            System.err.println("Conectado com Sucesso");
            return connection;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao BD! \n Erro:" + e);
        }
        return null;
    }
}