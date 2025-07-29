package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import conexion_db.Controlador_DB;
import logica.Cliente;
import logica.ClienteBasico;
import logica.ClienteEjecutivo;
import logica.Prenda;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VerRegistros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerRegistros frame = new VerRegistros();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public void windowOpened(WindowEvent e) {
	    cargarTabla();
	}

	private void cargarTabla() {
	    DefaultTableModel tabla = new DefaultTableModel() {
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };

	    // Títulos de las columnas
	    String[] titulos = {
	        "Nombre", "Apellido", "Teléfono", "Dirección", "Ticket",
	        "Servicio", "Cantidad", "Entrega", "Observaciones",
	        "Prioridad", "Total", "Estado"
	    };
	    tabla.setColumnIdentifiers(titulos);

	    // Cargar los datos desde la base de datos
	    Object[][] datos = Controlador_DB.obtenerRegistros();
	    for (Object[] fila : datos) {
	        tabla.addRow(fila);
	    }

	    table.setModel(tabla);
	}


	
	public VerRegistros() {
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowOpened(WindowEvent e) {
		        cargarTabla();
		    }
		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1020, 752);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1006, 715);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblVisualizacionDeDatos = new JLabel("Visualizacion de Datos");
		lblVisualizacionDeDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualizacionDeDatos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblVisualizacionDeDatos.setBounds(118, 44, 770, 60);
		panel.add(lblVisualizacionDeDatos);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 135, 973, 569);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 841, 495);
		panel_1.add(scrollPane);
		

		table = new JTable();
		scrollPane.setViewportView(table); 

		
		
		JLabel lblNewLabel = new JLabel("Datos de las Prendas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 27, 204, 25);
		panel_1.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.setForeground(new Color(128, 128, 128));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(861, 61, 102, 37);
		panel_1.add(btnNewButton);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setForeground(new Color(128, 128, 128));
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEditar.setBounds(861, 109, 102, 37);
		panel_1.add(btnEditar);
	
	
	btnNewButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        int filaSeleccionada = table.getSelectedRow();
	        if (filaSeleccionada == -1) {
	            JOptionPane.showMessageDialog(null, "Selecciona un registro para eliminar.");
	            return;
	        }

	        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar este registro?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
	        if (confirmacion == JOptionPane.YES_OPTION) {
	            int idCliente = Integer.parseInt(table.getValueAt(filaSeleccionada, 4).toString()); // No.Ticket = id_cliente

	            if (Controlador_DB.eliminarRegistro(idCliente)) {
	                JOptionPane.showMessageDialog(null, "Registro eliminado correctamente.");
	                cargarTabla(); // Refresca la tabla
	            } else {
	                JOptionPane.showMessageDialog(null, "Error al eliminar el registro.");
	            }
	        }
	    }
	});
	
	btnEditar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        int fila = table.getSelectedRow();
	        if (fila == -1) {
	            JOptionPane.showMessageDialog(null, "Selecciona un registro para editar.");
	            return;
	        }

	        int idCliente = Integer.parseInt(table.getValueAt(fila, 4).toString()); // No.Ticket
	        String nombre = table.getValueAt(fila, 0).toString();
	        String apellido = table.getValueAt(fila, 1).toString();
	        String telefono = table.getValueAt(fila, 2).toString();
	        String direccion = table.getValueAt(fila, 3).toString();
	        String tipoServicio = table.getValueAt(fila, 5).toString();
	        int cantidad = Integer.parseInt(table.getValueAt(fila, 6).toString());
	        String tipoEntrega = table.getValueAt(fila, 7).toString();
	        String observaciones = table.getValueAt(fila, 8).toString();
	        String prioridad = table.getValueAt(fila, 9) != null ? table.getValueAt(fila, 9).toString() : null;

	        // Crear objetos
	        Cliente cliente = (prioridad != null && !prioridad.isEmpty())
	                ? new ClienteEjecutivo(idCliente, nombre, apellido, telefono, direccion, prioridad)
	                : new ClienteBasico(idCliente, nombre, apellido, telefono, direccion);

	        Prenda prenda = new Prenda(tipoServicio, cantidad, tipoEntrega, observaciones);

	        // Confirmar y actualizar
	        int confirm = JOptionPane.showConfirmDialog(null, "¿Deseas guardar los cambios?", "Editar", JOptionPane.YES_NO_OPTION);
	        if (confirm == JOptionPane.YES_OPTION) {
	            boolean actualizado = Controlador_DB.actualizarRegistro(cliente, prenda);
	            if (actualizado) {
	                JOptionPane.showMessageDialog(null, "Registro actualizado correctamente.");
	                cargarTabla();
	            } else {
	                JOptionPane.showMessageDialog(null, "Error al actualizar el registro.");
	            }
	        }
	    }
	});

	
	}
}
