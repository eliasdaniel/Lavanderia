package logica;

public class ClienteEjecutivo extends Cliente{
	private String prioridad ="Alta";

	
	public ClienteEjecutivo( String nombre, String apellido, String telefono, String direccion, String prioridad) {
		super( nombre, apellido, telefono, direccion);
		this.prioridad = prioridad;
	}






	public ClienteEjecutivo(int idCliente, String nombre, String apellido, String telefono, String direccion,String prioridad){
			super(idCliente, nombre, apellido, telefono, direccion);
	
		
	}






	public String getPrioridad() {
		return prioridad;
	}

	

}
