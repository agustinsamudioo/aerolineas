package aerolineaBondiJet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.lang.model.element.NestingKind;

public class Vuelo {

	Aeropuerto aeropuerto_salida;
	Aeropuerto aeropuerto_llegada;
	String hora_salida;
	String hora_llegada;
	int tripulantes;
	String id_vuelo;
	String nombre;
	Aeropuerto origen;
	int capacidad;
	int cant_pasajeros;
	int cant_tripulantes;
	String fecha_salida_vuelo;
	Aeropuerto destino;
	double refrigerioValor;
	double[] precios;
	Seccion seccion;
	HashMap<Boolean, Integer>asientos_dispobibles;

	public Vuelo(String origen, String destino, String fecha, int tripulantes,double refrigerioValor, double[] precios) {
		this.aeropuerto_salida.nombre = origen;
		this.aeropuerto_salida.nombre = destino;
	}
	public Vuelo(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio, double[] precios,
			int[] cantAsientos) {
		this.origen.nombre = origen;
		this.destino.nombre = destino;
		this.fecha_salida_vuelo = fecha;
		this.tripulantes = tripulantes;
		this.precios = precios;
	}
	public Vuelo() {
		
		
		
	}
	
	
}
