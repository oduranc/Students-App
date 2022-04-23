package clases;

import java.sql.*;

public class Conexion {

    //Conexión Local
    public static Connection conectar() {
        try {
            Connection cn = DriverManager.getConnection("jdbc:sqlite:C:/Users/oscar/OneDrive/Documentos/db/sistema.s3db");
            return cn;
        } catch (SQLException e) {
            System.out.println("Error en la conexión local. " + e);
        }
        return (null);
    }

}