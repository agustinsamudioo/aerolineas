package aerolineaBondiJet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.lang.model.element.NestingKind;

public class Vuelo {
	String id_vuelo;
	String origen;
	int capacidad;
//	int cant_pasajeros;
	int cant_tripulantes;
	String fechaSalida;
	String destino;
//	HashMap<Integer, Pasajero> pasajeros;
//	Refrigerio refrigerio;
//	int max_pasajeros;
//	int cant_seciones;
	int[] cantAsientos; //ver si corresponde a clase Seccion
	double[] precios; //ver si corresponde a clase Seccion
//	Seccion seccion;
//	HashMap<Boolean, Integer>asientos_dispobibles;

	public Vuelo(String origen, String destino, String fecha, int tripulantes, double[] precios,
			int[] cantAsientos) {
		this.origen = origen;
		this.destino = destino;
		this.fechaSalida = fecha;
		this.cant_tripulantes = tripulantes;
//		this.refrigerio.precio = valorRefrigerio;
		this.precios = precios;
		this.cantAsientos = cantAsientos;
	}
	public Vuelo() {
		
	}
	
	public static boolean fechaPosteriorActual(String fechaS) {
		// Definimos el formato del String que vamos a recibir
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		// Convertimos el String a LocalDate
		LocalDate fecha = LocalDate.parse(fechaS, formato);
		LocalDate fechaActual = LocalDate.now();
		// Preguntamos si fecha es mayor a fecha actual
		return fecha.isAfter(fechaActual);
	}
	public static boolean fechaActualSemana(String Fecha, String fechaVuelo) {
		// Definimos el formato del String que vamos a recibir
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		// Convertimos el String a LocalDate
		LocalDate fechaDate = LocalDate.parse(Fecha, formato);
		LocalDate fechavueloDate = LocalDate.parse(fechaVuelo, formato);
		LocalDate fechaSemanaDespuesDate = fechaDate.plusWeeks(1);

		// Preguntamos si la fecha del vuelo esta entre la fecha dada y hasta una semana
		// despues
		if (!fechavueloDate.isBefore(fechaDate) && !fechavueloDate.isAfter(fechaSemanaDespuesDate)) {
			return true;
		}
		return false;
	}
	
}
