package logica;

public class ClienteBasico extends Cliente{

	public ClienteBasico(String nombre, String apellido, String telefono, String direccion) {
		super(nombre, apellido, telefono);
		
	}

	public ClienteBasico(int idCliente, String nombre, String apellido, String telefono, String direccion) {
		super(idCliente, nombre, apellido, telefono, direccion);
	}

	


}
