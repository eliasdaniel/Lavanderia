package logica;



public class Prenda {
	
	private int num_cliente;
	private String servicio;
	private int cantidad;
	private String entrega;
	private String observacion;
	private ClienteEjecutivo clE;
	private ClienteBasico clB;
	
	public Prenda(int num_cliente, String servicio, int cantidad, String entrega, String observacion,
			ClienteEjecutivo clE) {
		this.num_cliente = num_cliente;
		this.servicio = servicio;
		this.cantidad = cantidad;
		this.entrega = entrega;
		this.observacion = observacion;
		this.clE = clE;
		
	}
	
	public Prenda(int num_cliente, String servicio, int cantidad, String entrega, String observacion,
			ClienteBasico clB) {
		this.num_cliente = num_cliente;
		this.servicio = servicio;
		this.cantidad = cantidad;
		this.entrega = entrega;
		this.observacion = observacion;
		this.clB = clB;
	}
	public int getNum_cliente() {
		return num_cliente;
	}
	public void setNum_cliente(int num_cliente) {
		this.num_cliente = num_cliente;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getEntrega() {
		return entrega;
	}
	public void setEntrega(String entrega) {
		this.entrega = entrega;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public ClienteEjecutivo getclE() {
		return clE;
	}
	public void setclE(ClienteEjecutivo clE) {
		this.clE = clE;
	}
	public ClienteBasico getclB() {
		return clB;
	}
	public void setclB(ClienteBasico clB) {
		this.clB = clB;
	}

	
	
}
