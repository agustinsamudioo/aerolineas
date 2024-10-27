package aerolineaBondiJet;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import javax.lang.model.element.NestingKind;

public class Vuelo {
int id_vuelo;
String nombre;
Aeropuerto aeropuerto_salida;
int capacidad;
Date hora_salida;
Date hora_llegada;
int cant_pasajeros;
int cant_tripulantes;
String fecha_salida_vuelo;
Aeropuerto aeropuerto_llegada;
HashMap<Integer, Pasajero>pasajeros;
Refrigerio refrigerio;
int[] cantAsientos;
Double[]precios;


public Vuelo(String origen, String destino, String fecha, int tripulantes, Double valorRefrigerio,
		Double []precios, int[]cantAsientos) {
	this.aeropuerto_salida.nombre=origen;
	this.aeropuerto_salida.nombre=destino;
	this.fecha_salida_vuelo=fecha;
	this.cant_tripulantes=tripulantes;
	this.refrigerio.precio=valorRefrigerio;
	this.precios=precios;
	this.cantAsientos=cantAsientos;
}
}
