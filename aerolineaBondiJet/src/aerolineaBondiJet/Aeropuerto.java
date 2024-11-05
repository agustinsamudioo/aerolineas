package aerolineaBondiJet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Aeropuerto {
	String nombre;
	String pais;
	String provincia;
	String direccion;
//	HashMap<String, Vuelo>vuelos;
	
	public Aeropuerto(String nombre, String pais, String direccion) {
	
		this.nombre=nombre;
		this.pais=pais;
//		this.provincia=provincia;
//		this.direccion=direccion;
//		this.vuelos=new HashMap<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

//	public String getProvincia() {
//		return provincia;
//	}
//
//	public void setProvincia(String provincia) {
//		this.provincia = provincia;
//	}
//
//	public String getDireccion() {
//		return direccion;
//	}
//
//	public void setDireccion(String direccion) {
//		this.direccion = direccion;
//	}

//	public HashMap<String, Vuelo> getVuelos() {
//		return vuelos;
//	}

//	public void setVuelos(HashMap<String, Vuelo> vuelos) {
//		this.vuelos = vuelos;
//	}

	public static boolean aeropuertoNacional(String nombreAeropuerto, HashMap<String, Aeropuerto> aeropuertos) {
		// creamos un iterador sobrel el entrySet del Hashmap de aeropuertos
		// esto es para poder recorrer todos los aeropuertos y poder controlarlos
		if (!aeropuertos.containsKey(nombreAeropuerto))
			throw new RuntimeException("Aeropuerto no existe");
		Iterator<Map.Entry<String, Aeropuerto>> iterador = aeropuertos.entrySet().iterator();

		while (iterador.hasNext()) {
			Map.Entry<String, Aeropuerto> entrada = iterador.next();
			String clave = entrada.getKey(); // clave, o sea nombre del aeropuerto
			Aeropuerto valor = entrada.getValue(); // valor, que seria el objeto aeropuerto
			if (nombreAeropuerto.equalsIgnoreCase(clave) && valor.pais.equalsIgnoreCase("Argentina")) {
				return true;
			}
		}
		return false;
	}
}
