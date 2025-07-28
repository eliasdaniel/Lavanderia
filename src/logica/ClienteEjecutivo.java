package logica;

public class ClienteEjecutivo extends Cliente{
	private String prioridad ="Alta";


	public ClienteEjecutivo(int id_cliente, String nombre, String apellido, String telefono, String direccion, String prioridad) {
		super(id_cliente, nombre, apellido, telefono, direccion);
		this.prioridad = prioridad;
	}


	public String getPrioridad() {
		return prioridad;
	}

	

}
