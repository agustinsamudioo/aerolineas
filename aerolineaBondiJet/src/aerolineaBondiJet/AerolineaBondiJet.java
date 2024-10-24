package aerolineaBondiJet;

import java.util.HashMap;

public class AerolineaBondiJet {
	
	String nombre;
	int cuit;
	private HashMap<Integer,Cliente> clientes;
	//Map<Vuelo,Vuelo> vuelos;
	//Map<Pasajero,Pasaje> pasajes;
	//Map<Aeropuerto,Aeropuerto> aeropuertos;
	
	
	
	public AerolineaBondiJet() {
		
		this.nombre="BondiJet";
		this.cuit=1234;
		
	}
	public boolean cargarCliente(Cliente cliente) {
	    // Verificar si el cliente con el mismo DNI ya está en el HashMap
	    if (!clientes.containsKey(cliente.getDni())) {
	        // Agregar el cliente al HashMap usando su DNI como clave
	        clientes.put(Integer.parseInt(cliente.getDni()), cliente);
	        return true; // Indica que se agregó el cliente
	    }
	    return false; // Indica que el cliente ya existía y no se agregó
	}


}
