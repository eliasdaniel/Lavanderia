package gui;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import conexion_db.Controlador_DB;
import logica.*;

import java.awt.*;
import java.awt.event.*;

public class VerRegistros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				VerRegistros frame = new VerRegistros();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void cargarTabla() {
	    DefaultTableModel tabla = new DefaultTableModel() {
	        public boolean isCellEditable(int row, int column) {
	            return true; // ahora se puede editar directamente
	        }
	    };

	    String[] titulos = {
	        "ID", "Nombre", "Apellido", "Teléfono", "Dirección", "Ticket",
	        "Servicio", "Cantidad", "Entrega", "Observaciones",
	        "Prioridad", "Total", "Estado"
	    };
	    tabla.setColumnIdentifiers(titulos);

	    Object[][] datos = Controlador_DB.obtenerRegistros();
	    for (Object[] fila : datos) {
	        tabla.addRow(fila);
	    }

	    table.setModel(tabla);

	    // Ocultar columna ID visualmente
	    table.getColumnModel().getColumn(0).setMinWidth(0);
	    table.getColumnModel().getColumn(0).setMaxWidth(0);
	    table.getColumnModel().getColumn(0).setPreferredWidth(0);
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

		JLabel lblVisualizacionDeDatos = new JLabel("Visualización de Datos");
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

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.GRAY);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEliminar.setBounds(861, 61, 102, 37);
		panel_1.add(btnEliminar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setForeground(Color.GRAY);
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEditar.setBounds(861, 109, 102, 37);
		panel_1.add(btnEditar);

		// Acción para Eliminar
		btnEliminar.addActionListener(e -> {
		    int filaSeleccionada = table.getSelectedRow();
		    if (filaSeleccionada == -1) {
		        JOptionPane.showMessageDialog(null, "Selecciona un registro para eliminar.");
		        return;
		    }

		    int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar este registro?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
		    if (confirmacion == JOptionPane.YES_OPTION) {
		        int idCliente = Integer.parseInt(table.getValueAt(filaSeleccionada, 0).toString());
		        if (Controlador_DB.eliminarRegistro(idCliente)) {
		            JOptionPane.showMessageDialog(null, "Registro eliminado correctamente.");
		            cargarTabla();
		        } else {
		            JOptionPane.showMessageDialog(null, "Error al eliminar el registro.");
		        }
		    }
		});

		// Acción para Editar
		btnEditar.addActionListener(e -> {
		    int fila = table.getSelectedRow();
		    if (fila == -1) {
		        JOptionPane.showMessageDialog(null, "Selecciona un registro para editar.");
		        return;
		    }

		    try {
		        int idCliente = Integer.parseInt(table.getValueAt(fila, 0).toString());
		        String nombre = table.getValueAt(fila, 1).toString();
		        String apellido = table.getValueAt(fila, 2).toString();
		        String telefono = table.getValueAt(fila, 3).toString();
		        String direccion = table.getValueAt(fila, 4).toString();
		        String tipoServicio = table.getValueAt(fila, 6).toString();
		        int cantidad = Integer.parseInt(table.getValueAt(fila, 7).toString());
		        String tipoEntrega = table.getValueAt(fila, 8).toString();
		        String observaciones = table.getValueAt(fila, 9).toString();
		        String prioridad = table.getValueAt(fila, 10) != null ? table.getValueAt(fila, 10).toString() : null;
		        String estado = table.getValueAt(fila, 12).toString();

		        Cliente cliente = (prioridad != null && !prioridad.isEmpty())
		                ? new ClienteEjecutivo(idCliente, nombre, apellido, telefono, direccion, prioridad)
		                : new ClienteBasico(idCliente, nombre, apellido, telefono, direccion);

		        Prenda prenda = new Prenda(tipoServicio, cantidad, tipoEntrega, observaciones);
		        prenda.setEstado(estado); // incluye estado

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

		    } catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(null, "Error en los datos: " + ex.getMessage());
		    }
		});
	}
}
