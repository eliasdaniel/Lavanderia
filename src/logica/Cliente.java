package logica;

public abstract class Cliente {
	
	private int id_cliente = 1;
	private String nombre;
	private String apellido;
	private String telefono;
	private String direccion;
	
	public Cliente(int id_cliente, String nombre, 
			String apellido, 
			String telefono) {
		this.id_cliente++;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
	}
	public Cliente(int id_cliente, 
			String nombre, 
			String apellido, 
			String telefono, 
			String direccion) {
		this.id_cliente ++;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	public int getId_cliente() {
		return id_cliente;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
