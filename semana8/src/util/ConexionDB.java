package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/speedfast_db";
    private static final String USER = "root"; // usuario local
    private static final String PASSWORD = ""; // contrasenia

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Metodo para consultar si existe la base de datos
    public static boolean existeDB(){
        try (Connection conexion = conectar()){
            return conexion != null;
        }catch (SQLException e){
            if (e.getErrorCode() == 1049){
                return false;
            }
            System.out.println("Error de conexion: " + e.getMessage());
            return false;
        }
    }
} // listo
