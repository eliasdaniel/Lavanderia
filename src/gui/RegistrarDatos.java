package gui;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class RegistrarDatos extends JFrame{

	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtDireccion;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarDatos window = new RegistrarDatos();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/

	public RegistrarDatos() {
		initialize();
	}

	
	private void initialize() {
		
		this.setBounds(100, 100, 900, 664);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 886, 627);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblRegistroDeDatos = new JLabel("Registro de Datos");
		lblRegistroDeDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeDatos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblRegistroDeDatos.setBounds(53, 11, 770, 60);
		panel.add(lblRegistroDeDatos);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));
		
		panel_1.setBounds(10, 95, 461, 167);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lbNombre = new JLabel("Nombre:");
		lbNombre.setBounds(10, 25, 71, 14);
		panel_1.add(lbNombre);
		
		JLabel lbApellido = new JLabel("Apellido:");
		lbApellido.setBounds(10, 58, 71, 14);
		panel_1.add(lbApellido);
		
		JLabel lbTelefono = new JLabel("Telefono:");
		lbTelefono.setBounds(10, 95, 59, 14);
		panel_1.add(lbTelefono);
		
		JLabel lbDireccion = new JLabel("Direccion:");
		lbDireccion.setBounds(10, 127, 71, 14);
		panel_1.add(lbDireccion);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(91, 19, 343, 20);
		panel_1.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(91, 52, 343, 20);
		panel_1.add(txtApellido);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(91, 89, 343, 20);
		panel_1.add(txtTelefono);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(91, 121, 343, 20);
		panel_1.add(txtDireccion);
		
		
		JLabel lbImagen = new JLabel();
		lbImagen.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lbImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lbImagen.setBounds(479, 122, 364, 439);

		
		ImageIcon originalIcon = new ImageIcon("C:\\Users\\elias\\Downloads\\laundry_icon_651x7202.png");
		Image scaledImage = originalIcon.getImage().getScaledInstance(
				lbImagen.getWidth(),  
				lbImagen.getHeight(),  
		        Image.SCALE_SMOOTH);
		lbImagen.setIcon(new ImageIcon(scaledImage));

		panel.add(lbImagen);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(BorderFactory.createTitledBorder("Detalles de la Prenda"));
		panel_2.setBounds(10, 297, 461, 222);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lbServicio = new JLabel("Tipo de Servicio:");
		lbServicio.setBounds(10, 38, 102, 14);
		panel_2.add(lbServicio);
		
		JLabel lbCantidad = new JLabel("Cantidad:");
		lbCantidad.setBounds(10, 73, 60, 14);
		panel_2.add(lbCantidad);
		
		JLabel lbEntrega = new JLabel("Tipo de Entrega:");
		lbEntrega.setBounds(10, 111, 102, 14);
		panel_2.add(lbEntrega);
		
		JLabel lbObservacion = new JLabel("Observacion:");
		lbObservacion.setBounds(10, 145, 91, 14);
		panel_2.add(lbObservacion);
		
		JTextArea txtObservacion = new JTextArea();
		txtObservacion.setBounds(111, 140, 340, 71);
		panel_2.add(txtObservacion);
		
		JComboBox cmbServicio = new JComboBox();
		cmbServicio.setModel(new DefaultComboBoxModel(new String[] {"Lavado y Secado", "Lavado en seco", "Planchado", "Limpieza de cortinas", "Lavado de alfombras"}));
		cmbServicio.setBounds(111, 38, 138, 22);
		panel_2.add(cmbServicio);
		
		JSpinner spnCantidad = new JSpinner();
		spnCantidad.setBounds(111, 74, 50, 20);
		panel_2.add(spnCantidad);
		
		JComboBox cmbEntrega = new JComboBox();
		cmbEntrega.setModel(new DefaultComboBoxModel(new String[] {"Retiro", "A Domicilio", "Servicio exprés"}));
		cmbEntrega.setBounds(111, 111, 138, 22);
		panel_2.add(cmbEntrega);
		
		JLabel lbPrioridad = new JLabel("Prioridad");
		lbPrioridad.setHorizontalAlignment(SwingConstants.CENTER);
		lbPrioridad.setBounds(370, 11, 81, 22);
		panel_2.add(lbPrioridad);
		
		JLabel lbPrioridad_1 = new JLabel("Prioridad");
		lbPrioridad_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbPrioridad_1.setBounds(370, 38, 81, 22);
		panel_2.add(lbPrioridad_1);
		
		JButton btnLimpiar = new JButton("  Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNombre.setText("");
		        txtApellido.setText("");
		        txtTelefono.setText("");
		        txtDireccion.setText("");
		        txtObservacion.setText("");
		        spnCantidad.setValue(0);
		        cmbEntrega.setSelectedIndex(0);
		        cmbServicio.setSelectedIndex(0);
		        
			}
		});
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLimpiar.setBounds(53, 545, 170, 34);

		ImageIcon iconoOriginal = new ImageIcon("C:\\Users\\elias\\Downloads\\borrar.png");
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
		        24, 24, Image.SCALE_SMOOTH);
		btnLimpiar.setIcon(new ImageIcon(imagenEscalada));
		panel.add(btnLimpiar);

		
		JButton btnGuardar = new JButton("  Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnGuardar.setBounds(233, 545, 170, 34);
		
		ImageIcon iconoOriginal_2 = new ImageIcon("C:\\Users\\elias\\Downloads\\disquete.png");
		Image imagenEscalada_2 = iconoOriginal_2.getImage().getScaledInstance(
		        24, 24, Image.SCALE_SMOOTH);
		btnGuardar.setIcon(new ImageIcon(imagenEscalada_2));
		panel.add(btnGuardar);
		
		JRadioButton rdbtnEjecutivo = new JRadioButton("Ejecutivo");
		rdbtnEjecutivo.setBounds(121, 77, 111, 23);
		panel.add(rdbtnEjecutivo);
		
		JRadioButton rdbtnBasico = new JRadioButton("Basico");
		rdbtnBasico.setBounds(246, 77, 111, 23);
		panel.add(rdbtnBasico);
		ButtonGroup  grupo = new ButtonGroup ();
		grupo.add(rdbtnEjecutivo);
		grupo.add(rdbtnBasico);

	}
}
