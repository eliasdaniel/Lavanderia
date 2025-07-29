package conexion_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logica.Cliente;
import logica.ClienteBasico;
import logica.ClienteEjecutivo;
import logica.Prenda;

public class Controlador_DB {
	
	 private static final String URL = "jdbc:mysql://127.0.0.1:3306/lavanderia_db";
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

	    public static int obtenerIdServicioPorNombre(String nombreServicio) {
	        int id = -1;
	        try (Connection conn = conectar()) {
	            String sql = "SELECT id_servicio FROM servicios WHERE nombre = ?";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, nombreServicio);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                id = rs.getInt("id_servicio");
	            } else {
	                System.out.println("Servicio no encontrado: " + nombreServicio);
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al obtener id_servicio: " + e.getMessage());
	        }
	        return id;
	    }


	    public static double obtenerPrecioServicio(int idServicio) {
	        double precio = 0.0;
	        try (Connection conn = conectar()) {
	            String sql = "SELECT precio FROM servicios WHERE id_servicio = ?";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setInt(1, idServicio);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                precio = rs.getDouble("precio");
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al obtener precio del servicio: " + e.getMessage());
	        }
	        return precio;
	    }

	    
	    public static boolean insertarRegistro(Cliente cliente, Prenda prenda) {
	        String sqlCliente = "INSERT INTO clientes (nombre, apellido, telefono, direccion, tipo_cliente, prioridad) "
	                          + "VALUES (?, ?, ?, ?, ?, ?)";

	        try (Connection conn = conectar()) {

	            // 1. Insertar cliente
	            PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS);
	            stmtCliente.setString(1, cliente.getNombre());
	            stmtCliente.setString(2, cliente.getApellido());
	            stmtCliente.setString(3, cliente.getTelefono());
	            stmtCliente.setString(4, cliente.getDireccion());

	            // Detectar tipo de cliente
	            if (cliente instanceof ClienteBasico) {
	                stmtCliente.setString(5, "Basico");
	                stmtCliente.setString(6, null);
	            } else if (cliente instanceof ClienteEjecutivo) {
	                stmtCliente.setString(5, "Ejecutivo");
	                stmtCliente.setString(6, ((ClienteEjecutivo) cliente).getPrioridad());
	            } else {
	                stmtCliente.setString(5, "Desconocido");
	                stmtCliente.setString(6, null);
	            }

	            // Ejecutar inserción
	            int filasCliente = stmtCliente.executeUpdate();
	            if (filasCliente == 0) {
	                System.out.println("No se insertó el cliente.");
	                return false;
	            }

	            // 2. Obtener id_cliente generado
	            ResultSet generatedKeys = stmtCliente.getGeneratedKeys();
	            int idCliente;
	            if (generatedKeys.next()) {
	                idCliente = generatedKeys.getInt(1);
	            } else {
	                System.out.println("No se pudo obtener el ID del cliente.");
	                return false;
	            }

	            // 3. Obtener id_servicio
	            int idServicio = obtenerIdServicioPorNombre(prenda.getServicio());
	            if (idServicio == -1) {
	                System.out.println("Servicio no encontrado.");
	                return false;
	            }

	         // 4. Generar ticket único
	            String ticket = "TCK-" + System.currentTimeMillis();

	            // 5. Obtener precio del servicio
	            double precio = obtenerPrecioServicio(idServicio);
	            double total = precio * prenda.getCantidad();


	            // 5. Insertar prenda
	            String sqlPrenda = "INSERT INTO prendas (id_cliente, id_servicio, cantidad, tipo_entrega, observaciones, ticket, total) "
	                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";


	            try (PreparedStatement stmtPrenda = conn.prepareStatement(sqlPrenda)) {
	            	stmtPrenda.setInt(1, idCliente);
	            	stmtPrenda.setInt(2, idServicio);
	            	stmtPrenda.setInt(3, prenda.getCantidad());
	            	stmtPrenda.setString(4, prenda.getEntrega());
	            	stmtPrenda.setString(5, prenda.getObservacion());
	            	stmtPrenda.setString(6, ticket);
	            	stmtPrenda.setDouble(7, total);

	                int filasPrenda = stmtPrenda.executeUpdate();
	                return filasPrenda > 0;

	            } catch (SQLException e) {
	                System.out.println("Error al insertar prenda: " + e.getMessage());
	                return false;
	            }

	        } catch (SQLException e) {
	            System.out.println("Error al insertar cliente: " + e.getMessage());
	            return false;
	        }
	    }


	        
	        


	    public static Object[][] obtenerRegistros() {
	        List<Object[]> lista = new ArrayList<>();

	        String sql = """
	            SELECT 
	                c.nombre,
	                c.apellido,
	                c.telefono,
	                c.direccion,
	                p.ticket,
	                s.nombre AS servicio,
	                p.cantidad,
	                p.tipo_entrega,
	                p.observaciones,
	                c.prioridad,
	                p.total,
	                p.estado
	            FROM clientes c
	            JOIN prendas p ON c.id_cliente = p.id_cliente
	            JOIN servicios s ON p.id_servicio = s.id_servicio
	        """;

	        try (Connection conn = conectar();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                Object[] fila = {
	                    rs.getString("nombre"),
	                    rs.getString("apellido"),
	                    rs.getString("telefono"),
	                    rs.getString("direccion"),
	                    rs.getString("ticket"),
	                    rs.getString("servicio"),
	                    rs.getInt("cantidad"),
	                    rs.getString("tipo_entrega"),
	                    rs.getString("observaciones"),
	                    rs.getString("prioridad"),
	                    rs.getDouble("total"),
	                    rs.getString("estado")
	                };
	                lista.add(fila);
	            }

	        } catch (SQLException e) {
	            System.out.println("Error al obtener registros: " + e.getMessage());
	        }

	        Object[][] datos = new Object[lista.size()][];
	        for (int i = 0; i < lista.size(); i++) {
	            datos[i] = lista.get(i);
	        }

	        return datos;
	    }

	    
	    public static boolean eliminarRegistro(int idCliente) {
	        String sql = "DELETE FROM clientes WHERE id_cliente = ?";

	        try (Connection conn = conectar();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, idCliente);
	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            System.out.println("Error al eliminar: " + e.getMessage());
	            return false;
	        }
	    }
	    
	    public static boolean actualizarRegistro(Cliente cliente, Prenda prenda) {
	        String sqlCliente = "UPDATE clientes SET nombre = ?, apellido = ?, telefono = ?, direccion = ?, prioridad = ? WHERE id_cliente = ?";
	        String sqlPrenda = "UPDATE prendas SET tipo_servicio = ?, cantidad = ?, tipo_entrega = ?, observaciones = ? WHERE cliente_id = ?";

	        try (Connection conn = conectar();
	             PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente);
	             PreparedStatement stmtPrenda = conn.prepareStatement(sqlPrenda)) {

	            // Datos del cliente
	            stmtCliente.setString(1, cliente.getNombre());
	            stmtCliente.setString(2, cliente.getApellido());
	            stmtCliente.setString(3, cliente.getTelefono());
	            stmtCliente.setString(4, cliente.getDireccion());
	            stmtCliente.setString(5, (cliente instanceof ClienteEjecutivo) ? ((ClienteEjecutivo) cliente).getPrioridad() : null);
	            stmtCliente.setInt(6, cliente.getId_cliente());

	            // Datos de la prenda
	            stmtPrenda.setString(1, prenda.getServicio());
	            stmtPrenda.setInt(2, prenda.getCantidad());
	            stmtPrenda.setString(3, prenda.getEntrega());
	            stmtPrenda.setString(4, prenda.getObservacion());
	            stmtPrenda.setInt(5, cliente.getId_cliente());

	            int filasCliente = stmtCliente.executeUpdate();
	            int filasPrenda = stmtPrenda.executeUpdate();

	            return filasCliente > 0 && filasPrenda > 0;

	        } catch (SQLException e) {
	            System.out.println("Error al actualizar: " + e.getMessage());
	            return false;
	        }
	    }



		}
	
	

