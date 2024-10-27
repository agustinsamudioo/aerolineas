package aerolineaBondiJet;

import java.sql.Date;
import java.util.HashMap;

public class Vuelo {
int id_vuelo;
String nombre;
Aeropuerto aeropuerto_salida;
int capacidad;
Date hora_salida;
Date hora_llegada;
int cant_pasajeros;
Date fecha_vuelo;
Aeropuerto aeropuerto_llegada;
HashMap<Integer, Pasajero>pasajeros;

public Vuelo() {
	
}
}
