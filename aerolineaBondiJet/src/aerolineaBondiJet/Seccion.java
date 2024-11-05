package aerolineaBondiJet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Seccion {
	String tipo;
	Double precio;
	int capacidad;

	public Seccion(Double precio, int capacidad) {
		this.precio = precio;
		this.capacidad= capacidad;
	}
	public static String consultarSeccionAsiento(int nroAsiento, HashMap<Integer, String> asientos) {
		Iterator<Map.Entry<Integer, String>> iterador = asientos.entrySet().iterator();
		while (iterador.hasNext()) {
			Map.Entry<Integer, String> entrada = iterador.next();
			String valor = entrada.getValue(); // seccion
			int clave = entrada.getKey(); // asiento
			if (clave == nroAsiento) {
				return valor;
			}
		}
		return "Asiento ingresado invalido";
	}
	
	public static boolean estaAsientoDisponible(int numeroAsiento, String codVuelo, HashMap<Integer, Pasajero> pasajeros) {
		Iterator<Map.Entry<Integer, Pasajero>> iterador = pasajeros.entrySet().iterator();
		while (iterador.hasNext()) {
			Map.Entry<Integer, Pasajero> entrada = iterador.next();
			int clave = entrada.getKey(); // numero de asiento
			Pasajero valor = entrada.getValue(); // pasajero
			if (!valor.aOcupar && clave == numeroAsiento && valor.codVuelo.equals(codVuelo)) {
				return true;
			}
		}
		return false;
	}


}
