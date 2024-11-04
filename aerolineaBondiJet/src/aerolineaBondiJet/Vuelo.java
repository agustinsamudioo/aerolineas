package aerolineaBondiJet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.lang.model.element.NestingKind;

public class Vuelo {
	String id_vuelo;
	String nombre;
	Aeropuerto origen;
	int capacidad;
	int cant_pasajeros;
	int cant_tripulantes;
	String fecha_salida_vuelo;
	Aeropuerto destino;
	HashMap<Integer, Pasajero> pasajeros;
	Refrigerio refrigerio;
	int max_pasajeros;
	int cant_seciones;
	int[] cantAsientos;
	double[] precios;
	Seccion seccion;
	HashMap<Boolean, Integer>asientos_dispobibles;

	public Vuelo(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio, double[] precios,
			int[] cantAsientos) {
		this.origen.nombre = origen;
		this.destino.nombre = destino;
		this.fecha_salida_vuelo = fecha;
		this.cant_tripulantes = tripulantes;
		this.refrigerio.precio = valorRefrigerio;
		this.precios = precios;
		this.cantAsientos = cantAsientos;
	}
	public Vuelo() {
		
	}
	
}
