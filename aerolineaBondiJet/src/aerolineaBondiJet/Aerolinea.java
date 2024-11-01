package aerolineaBondiJet;

import java.lang.reflect.UndeclaredThrowableException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

public class Aerolinea {

	String nombre;
	String cuit;
	private HashMap<Integer, Cliente> clientes;
	private HashMap<String, Vuelo> vuelosPublicos;
	private HashMap<String, Vuelo> vuelosPrivados;
	private HashMap<Integer, Pasajero> pasajeros;
	private HashMap<String, Aeropuerto> aeropuertos;
	private HashMap<String, String> codigosVuelos;
	private HashMap<Integer, String> asientos;
	private HashMap<Integer, String> asientosDisponibles; // clave: numero de asiento, valor: seccion
	// private HashMap<Integer, String> asientosOcupados; // clave: numero de
	// asiento, valor: seccion
	private static int contadorCodigo = 0;
	private static int contadorCodigoPasaje = 0;

	public Aerolinea(String nombre, String cuit) {
		this.nombre = nombre;
		this.cuit = cuit;
		this.clientes = new HashMap<>();
		this.vuelosPublicos = new HashMap<>();
		this.vuelosPrivados = new HashMap<>();
		this.pasajeros = new HashMap<>();
		this.aeropuertos = new HashMap<>();

	}

	public void registrarCliente(int dni, String nombre, String telefono) {
		if (clientes.containsKey(dni)) {
			throw new RuntimeException("Cliente ya existe");
		}
		Cliente nuevoCliente = new Cliente(dni, nombre, telefono);
		clientes.put(dni, nuevoCliente);
	}

	public void registrarAeropuerto(String nombre, String pais, String provincia, String direccion) {
		if (aeropuertos.containsKey(nombre))
			throw new RuntimeException("Nombre de Aeropuerto ya existe");
		Aeropuerto nuevoAeropuerto = new Aeropuerto(nombre, pais, provincia, direccion);
		aeropuertos.put(nombre, nuevoAeropuerto);

	}

	public boolean aeropuertoNacional(String nombreAeropuerto) {
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

	public boolean fechaPosteriorActual(String fechaS) {
		// Definimos el formato del String que vamos a recibir
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		// Convertimos el String a LocalDate
		LocalDate fecha = LocalDate.parse(fechaS, formato);
		LocalDate fechaActual = LocalDate.now();
		// Preguntamos si fecha es mayor a fecha actual
		return fecha.isAfter(fechaActual);
	}

	public static synchronized String GeneradorId() {
		contadorCodigo++;
		return String.valueOf(contadorCodigo);
	}

	public static synchronized int GeneradorIdPasaje() {
		contadorCodigoPasaje++;
		return contadorCodigoPasaje;
	}

	public String registrarVueloPublicoNacional(String origen, String destino, String fecha, int tripulantes,
			double valorRefrigerio, double[] precios, int[] cantAsientos) {
		if (!fechaPosteriorActual(fecha))
			throw new RuntimeException("la fecha ingresada es anterior al presente");
		if (!aeropuertoNacional(origen))
			throw new RuntimeException("Aeropuerto ingresado no pertenece a Argentina");
		if (!aeropuertoNacional(destino))
			throw new RuntimeException("Aeropuerto ingresado no pertenece a Argentina");
		VueloNacional nuevoVuelo = new VueloNacional(origen, destino, fecha, tripulantes, valorRefrigerio, precios,
				cantAsientos);
		nuevoVuelo.id_vuelo = GeneradorId();

		if (vuelosPublicos.containsKey(nuevoVuelo.id_vuelo))
			throw new RuntimeException("Id de vuelo ya existe");
		vuelosPublicos.put(nuevoVuelo.id_vuelo, nuevoVuelo);
		String texto = "-PUB-NAC";
		String codigo = nuevoVuelo.id_vuelo + texto;
		codigosVuelos.put(nuevoVuelo.id_vuelo, texto);
		return codigo;
	}

	public String registrarVueloPublicoInternacional(String origen, String destino, String fecha, int tripulantes,
			double valorRefrigerio, int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas) {

		if (!fechaPosteriorActual(fecha))
			throw new RuntimeException("la fecha ingresada es anterior al presente");

		VueloInternacional nuevoVuelo = new VueloInternacional(origen, destino, fecha, tripulantes, valorRefrigerio,
				precios, cantAsientos);

		nuevoVuelo.id_vuelo = GeneradorId();
		if (vuelosPublicos.containsKey(nuevoVuelo.id_vuelo))
			throw new RuntimeException("Id de vuelo ya existe");
		vuelosPublicos.put(nuevoVuelo.id_vuelo, nuevoVuelo);
		String texto = "-PUB-INT";
		String codigo = nuevoVuelo.id_vuelo + texto;
		codigosVuelos.put(nuevoVuelo.id_vuelo, texto);
		return codigo;
	}

//	public Map<Integer, String> asientosDisponiblesOriginal(String codVuelo) { 
//		
//		if(!vuelosPublicos.containsKey(codVuelo)) throw new RuntimeException("Vuelo no existe");
//		Vuelo vuelo = vuelosPublicos.get(codVuelo);
//	    int[] cantAsientos = vuelo.cantAsientos;
//	    String[] secciones = {"Turista", "Ejecutivo", "Primera Clase"};
//	    int numeroAsiento = 1;
//		
//	    // Recorrer asientos en cada clase
//	    for (int i = 0; i < cantAsientos.length; i++) {
//	        String seccion = secciones[i];  // Asignar el nombre de la clase directamente
//
//	        for (int j = 0; j < cantAsientos[i]; j++) {
//	            if (estaAsientoDisponible(numeroAsiento, codVuelo)) {
//	                asientosDisponibles.put(numeroAsiento, seccion);
//	            }
//	            else {
//		            numeroAsiento++;
//		            if(!asientosOcupados.containsKey(numeroAsiento)) {
//		            	asientosOcupados.put(numeroAsiento,seccion);
//		            }
//	            }
//	        }
//	    }
//	    return asientosDisponibles;
//	}
//	
	public boolean estaAsientoDisponible(int numeroAsiento, String codVuelo) {
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

	public Map<Integer, String> generarAsientos(String codVuelo) {
		if (!vuelosPublicos.containsKey(codVuelo))
			throw new RuntimeException("Vuelo no existe");
		Vuelo vuelo = vuelosPublicos.get(codVuelo);
		int[] cantAsientos = vuelo.cantAsientos;
		String[] secciones = { "Turista", "Ejecutivo", "Primera Clase" };
		int numeroAsiento = 1;
		// Recorrer asientos en cada clase
		for (int i = 0; i < cantAsientos.length; i++) {
			String seccion = secciones[i]; // Asignar el nombre de la clase directamente
			for (int j = 0; j < cantAsientos[i]; j++) {
				asientos.put(numeroAsiento, seccion);
			}
		}
		return asientos;
	}

	public Map<Integer, String> asientosDisponibles(String codVuelo) {
		Iterator<Map.Entry<Integer, String>> iterador = asientos.entrySet().iterator();
		while (iterador.hasNext()) {
			Map.Entry<Integer, String> entrada = iterador.next();
			int numeroAsiento = entrada.getKey(); // Número de asiento
			if (estaAsientoDisponible(numeroAsiento, codVuelo)) {
				asientosDisponibles.put(numeroAsiento, codVuelo);
			}

		}
		return asientosDisponibles;
	}

	public int venderPasaje(int dni, String codVuelo, int nroAsiento, boolean aOcupar) {
		if (!clientes.containsKey(dni))
			throw new RuntimeException("Cliente no registrado, no se pudo vender el pasaje");
		int codPasaje = GeneradorIdPasaje();
		Pasajero nuevoPasajero = new Pasajero(nroAsiento, codVuelo, codPasaje, aOcupar);
		pasajeros.put(dni, nuevoPasajero);

		return codPasaje;
	}

	public String consultarSeccionAsiento(int nroAsiento) {
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

	void cancelarPasaje(int dni, String codVuelo, int nroAsiento) {
		String seccion = consultarSeccionAsiento(nroAsiento);
		pasajeros.remove(dni, codVuelo);
		asientosDisponibles.put(nroAsiento, seccion);

	}

}
