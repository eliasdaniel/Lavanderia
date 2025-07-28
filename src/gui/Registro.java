package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Registro extends JFrame{

	

/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro window = new Registro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	
	
	public Registro() {
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
		
		JLabel lblNewLabel = new JLabel("Lavanderia Burbujas & Sonrisas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(73, 53, 770, 60);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 122, 397, 419);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        RegistrarDatos pantalla = new RegistrarDatos();
		        pantalla.setVisible(true); 
		        pantalla.setLocationRelativeTo(null);
		    }
		});
		btnNewButton.setBounds(110, 29, 247, 71);
		panel_1.add(btnNewButton);


		
		JButton btnNewButton_1 = new JButton("Ver Registros");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBounds(110, 123, 247, 71);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cerrar");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_2.setBounds(110, 220, 247, 71);
		panel_1.add(btnNewButton_2);
		
		
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(479, 122, 364, 439);

		// Cargar y escalar la imagen para ajustarla al tamaño del JLabel
		ImageIcon originalIcon = new ImageIcon("C:\\Users\\elias\\Downloads\\laundry_icon_651x7202.png");
		Image scaledImage = originalIcon.getImage().getScaledInstance(
		        lblNewLabel_1.getWidth(),   // o usa directamente 364
		        lblNewLabel_1.getHeight(),  // o usa directamente 439
		        Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(scaledImage));

		panel.add(lblNewLabel_1);

		
		

	}
}
