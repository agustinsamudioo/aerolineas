package aerolineaBondiJet;

public class Cliente {
	int dni;
	String nombreCliente;	
	String telefono;
	
	public Cliente(int dni, String nombre, String telefono) {
		
		this.nombreCliente=nombre;
		this.dni=dni;
		this.telefono=telefono;
		
	}


	public String getNombreCliente() {
		return nombreCliente;
	}


	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}


	public int getDni() {
		return dni;
	}


	public void setDni(int dni) {
		this.dni = dni;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	

}
