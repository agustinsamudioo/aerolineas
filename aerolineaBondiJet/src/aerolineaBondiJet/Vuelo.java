package aerolineaBondiJet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.lang.model.element.NestingKind;

public class Vuelo {
	int id_vuelo;
	Aeropuerto aeropuerto_salida;
	Aeropuerto aeropuerto_llegada;
	String hora_salida;
	String hora_llegada;
	int tripulantes;
	String fecha_salida_vuelo;
	HashMap<Integer, Pasajero> pasajeros;
	double refrigerioValor;
	double[] precios;
	Seccion seccion;
	HashMap<Boolean, Integer>asientos_dispobibles;

	public Vuelo(String origen, String destino, String fecha, int tripulantes,double refrigerioValor, double[] precios) {
		this.aeropuerto_salida.nombre = origen;
		this.aeropuerto_salida.nombre = destino;
		this.fecha_salida_vuelo = fecha;
		this.tripulantes = tripulantes;
		this.precios = precios;
	}
	public Vuelo() {
		
		
		
	}
	
	
}
