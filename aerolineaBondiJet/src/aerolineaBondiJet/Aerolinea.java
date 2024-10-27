package aerolineaBondiJet;

import java.util.HashMap;

public class Aerolinea {
	
	String nombre;
	String cuit;
	private HashMap<Integer,Cliente> clientes;
	private HashMap<Integer,Vuelo> vuelos;
	private HashMap<Integer,Pasaje> pasajes;
	private HashMap<Integer,Pasajero> pasajeros;
	private HashMap<String,Aeropuerto> aeropuertos;
	

	public Aerolinea(String nombre, String cuit) {
		this.nombre=nombre;
		this.cuit=cuit;
		this.clientes=new HashMap<>();
		this.vuelos=new HashMap<>();
		this.pasajes=new HashMap<>();
		this.pasajeros=new HashMap<>();
		this.aeropuertos=new HashMap<>();
		
	}
	public void registrarCliente(int dni, String nombre, String telefono) {
	    if (clientes.containsKey(dni)) {
	        throw new RuntimeException("Cliente ya existe");    
	    }
	    Cliente nuevoCliente = new Cliente(dni, nombre, telefono);
	    clientes.put(dni, nuevoCliente);
	}
	
	public void registrarAeropuerto(String nombre, String pais, String provincia, String direccion) {
		if(aeropuertos.containsKey(nombre)) {
			throw new RuntimeException("Nombre de Aeropuerto ya existe");
		}
		Aeropuerto nuevoAeropuerto= new Aeropuerto(nombre, pais, provincia, direccion);
		aeropuertos.put(nombre, nuevoAeropuerto);
	
	}
}
