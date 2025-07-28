package conexion_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Controlador_DB {
	
	 private static final String URL = "jdbc:mysql://127.0.0.1:3306/lavanderia";
	    private static final String USUARIO = "root";
	    private static final String CLAVE = "39062";       

	    public static Connection conectar() {
	        try {
	            Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
	            System.out.println(" Conexion exitosa.");
	            return conexion;
	        } catch (SQLException e) {
	            System.out.println(" Error al conectar: " + e.getMessage());
	            return null;
	        }
	    }
	
}
