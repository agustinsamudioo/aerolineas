package aerolineaBondiJet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.lang.model.element.NestingKind;

public class Vuelo {
	int id_vuelo;
	String nombre;
	Aeropuerto aeropuerto_salida;
	int capacidad;
	String hora_salida;
	String hora_llegada;
	int cant_pasajeros;
	int cant_tripulantes;
	String fecha_salida_vuelo;
	Aeropuerto aeropuerto_llegada;
	HashMap<Integer, Pasajero> pasajeros;
	Refrigerio refrigerio;
	int max_pasajeros;
	int cant_seciones;
	int[] cantAsientos;
	double[] precios;

	public Vuelo(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio, double[] precios,
			int[] cantAsientos) {
		this.aeropuerto_salida.nombre = origen;
		this.aeropuerto_salida.nombre = destino;
		this.fecha_salida_vuelo = fecha;
		this.cant_tripulantes = tripulantes;
		this.refrigerio.precio = valorRefrigerio;
		this.precios = precios;
		this.cantAsientos = cantAsientos;
	}
}
