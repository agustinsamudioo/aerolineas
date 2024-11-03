package aerolineaBondiJet;

import java.lang.reflect.UndeclaredThrowableException;
import java.nio.channels.NonReadableChannelException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

public class Aerolinea {

	String nombre;
	String cuit;
	private HashMap<Integer, Cliente> clientes;
	private HashMap<String, Vuelo> vuelosPublicos;
	private HashMap<Integer, Vuelo> vuelosPrivados;
	private HashMap<String, Vuelo> vuelos; // id vuelo, objeto vuelo
	private HashMap<Integer, Pasajero> pasajeros;
	private HashMap<String, Aeropuerto> aeropuertos;
	private HashMap<String, String> codigosVuelos; // id vuelo , texto PUB o PRIV
	private HashMap<Integer, String> asientos;
	private HashMap<Integer, String> asientosDisponibles; // clave: numero de asiento, valor: seccion
	// private HashMap<Integer, String> asientosOcupados; // clave: numero de
	// asiento, valor: seccion
	private static int CodigoId = 0;
	private static int contadorCodigoPasaje = 0;

	public Aerolinea(String nombre, String cuit) {
		this.nombre = nombre;
		this.cuit = cuit;
		this.clientes = new HashMap<>();
		this.vuelos = new HashMap<>();
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

	public static synchronized int GeneradorId() {
		CodigoId++;
		return CodigoId++;
	}

	public static synchronized int GeneradorIdPasaje() {
		contadorCodigoPasaje++;
		return contadorCodigoPasaje;
	}

	public String registrarVueloPublicoNacional(String origen, String destino, String fecha, int tripulantes,
			double valorRefrigerio, double[] precios, int[] cantAsientos) {
		if (!fechaPosteriorActual(fecha))
			throw new RuntimeException("la fecha ingresada es anterior a la fecha presente");
		if (!aeropuertoNacional(origen))
			throw new RuntimeException("Aeropuerto ingresado no pertenece a Argentina");
		if (!aeropuertoNacional(destino))
			throw new RuntimeException("Aeropuerto ingresado no pertenece a Argentina");
		VueloNacional nuevoVuelo = new VueloNacional(origen, destino, fecha, tripulantes, valorRefrigerio, precios,
				cantAsientos);
		nuevoVuelo.id_vuelo = GeneradorId();

		if (vuelos.containsKey(nuevoVuelo.id_vuelo))
			throw new RuntimeException("Id de vuelo ya existe");
		vuelosPublicosNacionales.put(nuevoVuelo.id_vuelo, nuevoVuelo);
		return nuevoVuelo.id_vuelo + "-PUB";

		vuelosPublicos.put(nuevoVuelo.id_vuelo, nuevoVuelo);

		vuelos.put(nuevoVuelo.id_vuelo, nuevoVuelo);
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
		if (vuelos.containsKey(nuevoVuelo.id_vuelo))
			throw new RuntimeException("Id de vuelo ya existe");

		vuelosPublicosInternacionales.put(nuevoVuelo.id_vuelo, nuevoVuelo);
		return nuevoVuelo.id_vuelo + "-PUB";

	}


	String VenderVueloPrivado(String origen, String destino, String fecha, int tripulantes,
			double precio,  int dniComprador, int[] acompaniantes) {
		
		if (!aeropuertoNacional(origen))
			throw new RuntimeException("Aeropuerto ingresado de origen no pertenece a Argentina");
		
		if (!aeropuertoNacional(destino))			
			throw new RuntimeException("Aeropuerto ingresado de destino no pertenece a Argentina");
		
		if (!fechaPosteriorActual(fecha))
			throw new RuntimeException("la fecha ingresada es anterior a la fecha actual");

		VueloPrivado VueloNuevo = new VueloPrivado(origen, destino, fecha, dniComprador, tripulantes, precio);
		 
		
		 VueloNuevo.idVuelo = GeneradorId();
			if (vuelosPrivados.containsKey(VueloNuevo.idVuelo))
				throw new RuntimeException("Id de vuelo ya existe");
			vuelosPrivados.put(VueloNuevo.idVuelo, VueloNuevo);
			return VueloNuevo.idVuelo + "-PRI";
		
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
		if (!vuelos.containsKey(codVuelo))
			throw new RuntimeException("Vuelo no existe");
		Vuelo vuelo = vuelos.get(codVuelo);
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
		Iterator<Map.Entry<Integer, Pasajero>> iterador = pasajeros.entrySet().iterator();
		while (iterador.hasNext()) {
			Map.Entry<Integer, Pasajero> entrada = iterador.next();
			Pasajero valor = entrada.getValue(); // Pasajero
			int clave = entrada.getKey(); // dni
			if (clave == dni && valor.codVuelo.equalsIgnoreCase(codVuelo)) {
				pasajeros.remove(clave);
				asientosDisponibles.put(nroAsiento, seccion);
			}
		}
		throw new RuntimeException("No se pudo cancelar el pasaje");
	}

	/**
	 * - 11. devuelve una lista de códigos de vuelos. que estén entre fecha dada y
	 * hasta una semana despues. La lista estará vacía si no se encuentran vuelos
	 * similares. La Fecha es la fecha de salida.
	 */
	// origen es un aeropuerto, destino es otro aeropuerto

	public boolean fecha_actual_semana(String Fecha, String fechaVuelo) {
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

	public List<String> consultarVuelosSimilares(String origen, String destino, String Fecha) {
		Iterator<Map.Entry<String, Vuelo>> iterador = vuelos.entrySet().iterator();
		List<String> consultarVuelosSimilares = new ArrayList<String>();
		while (iterador.hasNext()) {
			Map.Entry<String, Vuelo> entrada = iterador.next();
			Vuelo valor = entrada.getValue(); // Objeto vuelo
			String clave = entrada.getKey(); // codigo de vuelo
			if (valor.origen.nombre.equals(origen) && valor.destino.nombre.equalsIgnoreCase(destino)) {
				if (fecha_actual_semana(Fecha, valor.fecha_salida_vuelo)) {
					consultarVuelosSimilares.add(clave);
				}
			}

		}
		return consultarVuelosSimilares;
	}

	/**
	 * - 15 Detalle de un vuelo devuelve un texto con el detalle un vuelo en
	 * particular. Formato del String: CodigoVuelo - Nombre Aeropuerto de salida -
	 * Nombre Aeropuerto de llegada - fecha de salida - [NACIONAL /INTERNACIONAL /
	 * PRIVADO + cantidad de jets necesarios]. --> Ejemplo: . 545-PUB - Bariloche -
	 * Jujuy - 10/11/2024 - NACIONAL . 103-PUB - Ezeiza - Madrid - 15/11/2024 -
	 * INTERNACIONAL . 222-PRI - Ezeiza - Tierra del Fuego - 3/12/2024 - PRIVADO (3)
	 */
	public String detalleDeVuelo(String codVuelo) {
		Iterator<Map.Entry<String, Vuelo>> iterador = vuelos.entrySet().iterator();
		Vuelo vuelo = vuelos.get(codVuelo);
		String tipo="";
		if (vuelo instanceof VueloInternacional)
			tipo = "NACIONAL";
		if (vuelo instanceof VueloNacional)
			tipo = "INTERNACIONAL";
		if (vuelo instanceof VueloPrivado)
			tipo = "PRIVADO"+"("+((VueloPrivado) vuelo).cant_jets+")";

		while (iterador.hasNext()) {
			Map.Entry<String, Vuelo> entrada = iterador.next();
			Vuelo valor = entrada.getValue(); // objeto vuelo
			String clave = entrada.getKey(); // codigo de vuelo
			if (clave.equalsIgnoreCase(codVuelo)) {
				String detalleVuelo = clave + "-" + valor.origen.nombre + "-" + valor.destino.nombre + "-"
						+ valor.fecha_salida_vuelo + "-" + tipo;
				return detalleVuelo;

			}
		}
		return "Codigo de vuelo no existe";
	}

	/**
	 * - 13 Cancela un vuelo completo conociendo su codigo. Los pasajes se
	 * reprograman a vuelos con igual destino, no importa el numero del asiento pero
	 * si a igual seccion o a una mejor, y no importan las escalas. Devuelve los
	 * codigos de los pasajes que no se pudieron reprogramar. Los pasajes no
	 * reprogramados se eliminan. Y se devuelven los datos de la cancelación,
	 * indicando los pasajeros que se reprogramaron y a qué vuelo, y los que se
	 * cancelaron por no tener lugar. Devuelve una lista de Strings con este formato
	 * : “dni - nombre - telefono - [Codigo nuevo vuelo|CANCELADO]” --> Ejemplo: .
	 * 11111111 - Juan - 33333333 - CANCELADO . 11234126 - Jonathan - 33333311 -
	 * 545-PUB
	 */
	List<String> cancelarVuelo(String codVuelo) {
		List<String> vuelosCancelados = new ArrayList<String>();
		String formato = "";
		Iterator<Map.Entry<Integer, Pasajero>> iterador = pasajeros.entrySet().iterator();

		while (iterador.hasNext()) {
			Map.Entry<Integer, Pasajero> entrada = iterador.next();
			Pasajero valor = entrada.getValue(); // objeto pasajero
			int clave = entrada.getKey(); // dni
			formato += clave + " - " + valor.nombreCliente + " - " + valor.telefono + " - ";
			vuelosCancelados.add(formato);
		}
		return vuelosCancelados;
	}
}
