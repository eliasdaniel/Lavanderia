package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import conexion_db.Controlador_DB;
import logica.Cliente;
import logica.ClienteBasico;
import logica.ClienteEjecutivo;
import logica.Prenda;

public class RegistrarDatos extends JFrame {

    private JTextField txtNombre, txtApellido, txtTelefono, txtDireccion,txtPrioridad;
    private JLabel lbPrioridad;
    

    public RegistrarDatos() {
        initialize();
    }

    private void initialize() {
        setBounds(100, 100, 900, 664);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel(null);
        setContentPane(contentPane);

        // Agregar contenido normal
        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 886, 627);
        contentPane.add(panel);

        JLabel lblRegistroDeDatos = new JLabel("Registro de Datos", SwingConstants.CENTER);
        lblRegistroDeDatos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
        lblRegistroDeDatos.setBounds(53, 11, 770, 60);
        panel.add(lblRegistroDeDatos);

        // Panel Cliente
        JPanel panelCliente = new JPanel(null);
        panelCliente.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));
        panelCliente.setBounds(10, 95, 461, 167);
        panel.add(panelCliente);

        JLabel lbNombre = new JLabel("Nombre:");
        lbNombre.setBounds(10, 25, 71, 14);
        panelCliente.add(lbNombre);

        JLabel lbApellido = new JLabel("Apellido:");
        lbApellido.setBounds(10, 58, 71, 14);
        panelCliente.add(lbApellido);

        JLabel lbTelefono = new JLabel("Telefono:");
        lbTelefono.setBounds(10, 95, 59, 14);
        panelCliente.add(lbTelefono);

        JLabel lbDireccion = new JLabel("Direccion:");
        lbDireccion.setBounds(10, 127, 71, 14);
        panelCliente.add(lbDireccion);

        txtNombre = new JTextField();
        txtNombre.setBounds(91, 19, 343, 20);
        panelCliente.add(txtNombre);

        txtApellido = new JTextField();
        txtApellido.setBounds(91, 52, 343, 20);
        panelCliente.add(txtApellido);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(91, 89, 343, 20);
        panelCliente.add(txtTelefono);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(91, 121, 343, 20);
        panelCliente.add(txtDireccion);

        // Imagen
        JLabel lbImagen = new JLabel();
        lbImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lbImagen.setBounds(479, 122, 364, 439);
        ImageIcon icon = new ImageIcon("C:\\Users\\elias\\Downloads\\laundry_icon_651x7202.png");
        Image scaled = icon.getImage().getScaledInstance(364, 439, Image.SCALE_SMOOTH);
        lbImagen.setIcon(new ImageIcon(scaled));
        panel.add(lbImagen);

        // Panel Prenda
        JPanel panelPrenda = new JPanel(null);
        panelPrenda.setBorder(BorderFactory.createTitledBorder("Detalles de la Prenda"));
        panelPrenda.setBounds(10, 297, 461, 222);
        panel.add(panelPrenda);

        JComboBox<String> cmbServicio = new JComboBox<>(new String[]{"Lavado y Secado", "Lavado en seco", "Planchado", "Limpieza de cortinas", "Lavado de alfombras"});
        cmbServicio.setBounds(111, 38, 138, 22);
        panelPrenda.add(cmbServicio);

        JSpinner spnCantidad = new JSpinner();
        spnCantidad.setBounds(111, 74, 50, 20);
        panelPrenda.add(spnCantidad);

        JComboBox<String> cmbEntrega = new JComboBox<>(new String[]{"Retiro", "A Domicilio", "Servicio exprés"});
        cmbEntrega.setBounds(111, 111, 138, 22);
        panelPrenda.add(cmbEntrega);

        JTextArea txtObservacion = new JTextArea();
        txtObservacion.setBounds(111, 140, 340, 71);
        panelPrenda.add(txtObservacion);

        JLabel lbServicio = new JLabel("Tipo de Servicio:");
        lbServicio.setBounds(10, 38, 102, 14);
        panelPrenda.add(lbServicio);

        JLabel lbCantidad = new JLabel("Cantidad:");
        lbCantidad.setBounds(10, 73, 60, 14);
        panelPrenda.add(lbCantidad);

        JLabel lbEntrega = new JLabel("Tipo de Entrega:");
        lbEntrega.setBounds(10, 111, 102, 14);
        panelPrenda.add(lbEntrega);

        JLabel lbObservacion = new JLabel("Observacion:");
        lbObservacion.setBounds(10, 145, 91, 14);
        panelPrenda.add(lbObservacion);
        
        lbPrioridad = new JLabel("Prioridad");
        lbPrioridad.setBounds(370, 11, 81, 22);
        lbPrioridad.setOpaque(false); // 🔹 Hace el JLabel transparente
        panelPrenda.add(lbPrioridad);

        txtPrioridad = new JTextField();
        txtPrioridad.setBounds(380, 35, 60, 20);
        txtPrioridad.setOpaque(false); // 🔹 Hace el JTextField transparente
        txtPrioridad.setBackground(new Color(0, 0, 0, 0)); // 🔹 Fondo completamente transparente
        txtPrioridad.setBorder(null);
        panelPrenda.add(txtPrioridad);


        JRadioButton rdbtnEjecutivo = new JRadioButton("Ejecutivo");
        rdbtnEjecutivo.setBounds(121, 77, 111, 23);
        panel.add(rdbtnEjecutivo);

        JRadioButton rdbtnBasico = new JRadioButton("Basico");
        rdbtnBasico.setBounds(246, 77, 111, 23);
        panel.add(rdbtnBasico);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rdbtnEjecutivo);
        grupo.add(rdbtnBasico);
        
        rdbtnEjecutivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtPrioridad.setText("Alta");
                txtPrioridad.setEditable(false); // Opcional
            }
        });

        rdbtnBasico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtPrioridad.setText("");
                txtPrioridad.setEditable(false); // Mantener desactivado también para básico
            }
        });


        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(53, 545, 170, 34);
        btnLimpiar.addActionListener(e -> {
            txtNombre.setText(""); txtApellido.setText(""); txtTelefono.setText(""); txtDireccion.setText("");
            txtObservacion.setText(""); spnCantidad.setValue(0); cmbEntrega.setSelectedIndex(0); cmbServicio.setSelectedIndex(0);
 
            

 
        });
        panel.add(btnLimpiar);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(233, 545, 170, 34);
        panel.add(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String telefono = txtTelefono.getText();
                String direccion = txtDireccion.getText();
               String prioridad = txtPrioridad.getText();

                if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor completa todos los campos obligatorios.");
                    return;
                }

                Cliente cliente = rdbtnBasico.isSelected() ?
                    new ClienteBasico(nombre, apellido, telefono, direccion) :
                    rdbtnEjecutivo.isSelected() ? new ClienteEjecutivo(nombre, apellido, telefono, direccion, prioridad) : null;

                if (cliente == null) {
                    JOptionPane.showMessageDialog(null, "Seleccione el tipo de cliente.");
                    return;
                }

                Prenda prenda = new Prenda(
                    cmbServicio.getSelectedItem().toString(),
                    (int) spnCantidad.getValue(),
                    cmbEntrega.getSelectedItem().toString(),
                    txtObservacion.getText()
                );

                boolean insertado = Controlador_DB.insertarRegistro(cliente, prenda);

                if (insertado) {
                    JOptionPane.showMessageDialog(null, "Registro guardado correctamente.");
                    Burbujas panelBurbujas = new Burbujas();
                    panelBurbujas.setOpaque(false);
                    panelBurbujas.setBounds(0, 0, getWidth(), getHeight());
                    getLayeredPane().add(panelBurbujas, JLayeredPane.POPUP_LAYER);
                    new Timer(5000, t -> {
                        getLayeredPane().remove(panelBurbujas);
                        getLayeredPane().repaint();
                    }).start();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar el registro.");
                }
            }
        });
    }
}
