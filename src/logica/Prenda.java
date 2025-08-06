package logica;



public class Prenda {
	
	private int num_ticket;
	private String servicio;
	private int cantidad;
	private String entrega;
	private String observacion;
	private ClienteEjecutivo clE;
	private ClienteBasico clB;
	private String estado;
	
	public Prenda( String servicio, int cantidad, String entrega, String observacion) {
		
		this.servicio = servicio;
		this.cantidad = cantidad;
		this.entrega = entrega;
		this.observacion = observacion;
		
		
	}
	
public Prenda( String servicio, int cantidad, String entrega, String observacion, String estado) {
		
		this.servicio = servicio;
		this.cantidad = cantidad;
		this.entrega = entrega;
		this.observacion = observacion;
		this.estado = estado;
		
	}

	public int getNum_ticket() {
		return num_ticket;
	}
	public void setNum_ticket(int num_cliente) {
		this.num_ticket = num_cliente;
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
}
