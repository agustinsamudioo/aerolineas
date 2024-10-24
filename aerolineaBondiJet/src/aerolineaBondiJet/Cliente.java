package aerolineaBondiJet;

public class Cliente {
	
	String nombreCliente;
	Integer dni;
	int telefono;
	
	
	public Cliente(String nombre, int dni, int telefono) {
		
		this.nombreCliente=nombre;
		this.dni=dni;
		this.telefono=telefono;
		
	}
	
	// Getters
    public String getNombre() {
        return nombreCliente;
    }

    public Integer getDni() {
        return dni;
    }

    public Integer getTelefono() {
        return telefono;
    }

    // Sobrescribir el método toString para mostrar los datos del cliente
    @Override
    public String toString() {
        return "Nombre: " + nombreCliente + ", DNI: " + dni + ", Teléfono: " + telefono;
    }
	
	

}
