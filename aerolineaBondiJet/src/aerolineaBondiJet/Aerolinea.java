package aerolineaBondiJet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Aerolinea {
	
	String nombre;
	String cuit;
	private HashMap<Integer,Cliente> clientes;
	private HashMap<Integer,Vuelo> vuelosPublicosNacionales;
	private HashMap<Integer,Vuelo> vuelosPublicosInternacionales;
	private HashMap<Integer,Vuelo> vuelosPrivados;
	private HashMap<Integer,Pasaje> pasajes;
	private HashMap<Integer,Pasajero> pasajeros;
	private HashMap<String,Aeropuerto> aeropuertos;
	int[] cantAsientos;
	Double[]precios;
	private static int contadorCodigo=0;
	

	public Aerolinea(String nombre, String cuit) {
		this.nombre=nombre;
		this.cuit=cuit;
		this.clientes=new HashMap<>();
		this.vuelosPublicosNacionales=new HashMap<>();
		this.vuelosPublicosInternacionales=new HashMap<>();
		this.vuelosPrivados=new HashMap<>();
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
	public boolean aeropuertoNacional(String nombreAeropuerto) {
		// creamos un iterador sobrel el entrySet del Hashmap de aeropuertos 
		// esto es para poder recorrer todos los aeropuertos y poder controlarlos
		if(!aeropuertos.containsKey(nombreAeropuerto)) {
			throw new RuntimeException("Aeropuerto no existe");
		}
		Iterator<Map.Entry<String, Aeropuerto>> iterador= aeropuertos.entrySet().iterator();
		
		while(iterador.hasNext()) {
			Map.Entry<String, Aeropuerto> entrada= iterador.next();
			String clave= entrada.getKey(); // clave, o sea nombre del aeropuerto
			Aeropuerto valor= entrada.getValue(); // valor, que seria el objeto aeropuerto
			if(nombreAeropuerto==clave && valor.pais.equals("Argentina")) {
				return true;
			}
		}
		return false;
	}
	
	public static synchronized int GeneradorId(int contadorCodigo) {
		return contadorCodigo++;
	}
	
	public String registrarVueloPublicoNacional(String origen, String destino, String fecha, 
			int tripulantes, double valorRefrigerio, Double[] precios, int[] cantAsientos) {
	VueloNacional nuevoVuelo= new VueloNacional(origen, destino, fecha, tripulantes, valorRefrigerio , precios, cantAsientos);
	nuevoVuelo.id_vuelo=GeneradorId(contadorCodigo);
	if(vuelosPublicosNacionales.containsKey(nuevoVuelo.id_vuelo)) {
		throw new RuntimeException("Id de vuelo ya existe");
	}
	vuelosPublicosNacionales.put(nuevoVuelo.id_vuelo, nuevoVuelo);
	String codigo=nuevoVuelo.id_vuelo+"-PUB";
	return codigo;
	}
}
