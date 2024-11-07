package aerolineaBondiJet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Aerolinea implements IAerolinea {

	String nombre;
	String cuit;
	private HashMap<Integer, Cliente> clientes;
	private HashMap<String, Vuelo> vuelos; // id vuelo, objeto vuelo
	private HashMap<Integer, Pasajero> pasajeros;
	private HashMap<String, Aeropuerto> aeropuertos;
	private HashMap<Integer, String> asientos;
	private HashMap<Integer, String> asientosDisponibles; // clave: numero de asiento, valor: seccion

	private static int contadorCodigo = 0;
	private static int contadorCodigoPasaje = 0;

	
	public Aerolinea(String nombre, String cuit) {
		this.nombre = nombre;
		this.cuit = cuit;
		this.clientes = new HashMap<>();
		this.vuelos = new HashMap<>();
		this.pasajeros = new HashMap<>();
		this.aeropuertos = new HashMap<>();

		this.asientos = new HashMap<>();
		this.asientosDisponibles = new HashMap<>();

	}

	public void registrarCliente(int dni, String nombre, String telefono) {
		if (clientes.containsKey(dni)) {
			throw new RuntimeException("Cliente ya existe");
		}
		Cliente nuevoCliente = new Cliente(dni, nombre, telefono);
		clientes.put(dni, nuevoCliente);
	}

	public void registrarAeropuerto(String nombre, String pais,String provincia, String direccion) {
		if (aeropuertos.containsKey(nombre))
			throw new RuntimeException("Nombre de Aeropuerto ya existe");
		Aeropuerto nuevoAeropuerto = new Aeropuerto(nombre,pais,provincia,direccion);
		aeropuertos.put(nombre, nuevoAeropuerto);

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
		if (!Vuelo.fechaPosteriorActual(fecha))
			throw new RuntimeException("la fecha ingresada es anterior al presente");
		if (!Aeropuerto.aeropuertoNacional(origen, aeropuertos))
			throw new RuntimeException("Aeropuerto ingresado no pertenece a Argentina");
		if (!Aeropuerto.aeropuertoNacional(destino, aeropuertos))
			throw new RuntimeException("Aeropuerto ingresado no pertenece a Argentina");
		VueloNacional nuevoVuelo = new VueloNacional(origen, destino, fecha, tripulantes, valorRefrigerio, precios,
				cantAsientos);
		nuevoVuelo.id_vuelo = GeneradorId();

		
		String texto = "-PUB";
		String codigo = nuevoVuelo.id_vuelo + texto;
		if (vuelos.containsKey(codigo))
			throw new RuntimeException("Id de vuelo ya existe");
		vuelos.put(nuevoVuelo.id_vuelo + texto, nuevoVuelo);

		

		return codigo;
	}

	public String registrarVueloPublicoInternacional(String origen, String destino, String fecha, int tripulantes,
			double valorRefrigerio, int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas) {

		if (!Vuelo.fechaPosteriorActual(fecha))
			throw new RuntimeException("la fecha ingresada es anterior al presente");

		VueloInternacional nuevoVuelo = new VueloInternacional(origen, destino, fecha, tripulantes, valorRefrigerio,
				precios, cantAsientos);

		nuevoVuelo.id_vuelo = GeneradorId();
		
		String texto = "-PUB";
		String codigo = nuevoVuelo.id_vuelo + texto;
		if (vuelos.containsKey(codigo))
			throw new RuntimeException("Id de vuelo ya existe");
		vuelos.put(nuevoVuelo.id_vuelo + texto, nuevoVuelo);
		

		return codigo;
	}

	public String VenderVueloPrivado (String origen, String destino, String fecha, int tripulantes,
			double precio,  int dniComprador, int[] acompaniantes) {
		//Se verifica que los aeropuertos  de origen y llegada sean nacionales y 
		//que la fecha ingresada posterior a la actual 
		if (!Aeropuerto.aeropuertoNacional(origen, aeropuertos))
			throw new RuntimeException("Aeropuerto ingresado de origen no pertenece a Argentina");
		if (!Aeropuerto.aeropuertoNacional(destino, aeropuertos))			
			throw new RuntimeException("Aeropuerto ingresado de destino no pertenece a Argentina");
		if (!Vuelo.fechaPosteriorActual(fecha))
			throw new RuntimeException("la fecha ingresada es anterior a la fecha actual");
		// Se verifica que el comprador sea cliente de la compañia
		if (!clientes.containsKey(dniComprador)) {
			throw new RuntimeException("El comprador del vuelo no esta registrado como cliente de la Aerolinea");
		}
		// Se verifica que los acompaniantes sean clientes de la compañia
		for (int i=0;i<acompaniantes.length;i++) {	
			if (!clientes.containsKey(acompaniantes[i])) 
				throw new RuntimeException("El acompañante no esta registrado como cliente de la Aerolinea");
		}
		//Se crea el vuelo privado
		VueloPrivado vueloNuevo = new VueloPrivado(origen, destino, fecha, dniComprador, tripulantes, precio);

		//Se agrega el vuelo creado a la lista de vuelos
		String codigo = vueloNuevo.id_vuelo + "-PRI";
		if (vuelos.containsKey(codigo))
			throw new RuntimeException("Id de vuelo ya existe");
		vuelos.put(codigo, vueloNuevo);
		return codigo;

	}

	public Map<Integer, String> generarAsientos(String codVuelo) {
		if (!vuelos.containsKey(codVuelo))
			throw new RuntimeException("Vuelo no existe");
		VueloPublico vuelo = (VueloPublico) vuelos.get(codVuelo);
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
			if (Seccion.estaAsientoDisponible(numeroAsiento, codVuelo, pasajeros)) {
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



	public void cancelarPasaje(int dni, String codVuelo, int nroAsiento) {
		String seccion = Seccion.consultarSeccionAsiento(nroAsiento, asientos);
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



	public List<String> consultarVuelosSimilares(String origen, String destino, String Fecha) {
		Iterator<Map.Entry<String, Vuelo>> iterador = vuelos.entrySet().iterator();
		List<String> consultarVuelosSimilares = new ArrayList<String>();
		while (iterador.hasNext()) {
			Map.Entry<String, Vuelo> entrada = iterador.next();
			Vuelo valor = entrada.getValue(); // Objeto vuelo
			String clave = entrada.getKey(); // codigo de vuelo
			if (valor.origen.equals(origen) && valor.destino.equalsIgnoreCase(destino)) {
				if (Vuelo.fechaActualSemana(Fecha, valor.fechaSalida)) {
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
//	public String detalleDeVuelo(String codVuelo) {
//		Iterator<Map.Entry<String, Vuelo>> iterador = vuelos.entrySet().iterator();
//		Vuelo vuelo = vuelos.get(codVuelo);
//		String tipo="";
//		if (vuelo instanceof VueloInternacional)
//			tipo = "INTERNACIONAL";
//		if (vuelo instanceof VueloNacional)
//			tipo = "NACIONAL";
//		if (vuelo instanceof VueloPrivado)
//			tipo = "PRIVADO"+"("+((VueloPrivado) vuelo).cantidadDeJets+")";
//
//		while (iterador.hasNext()) {
//			Map.Entry<String, Vuelo> entrada = iterador.next();
//			Vuelo valor = entrada.getValue(); // objeto vuelo
//			String clave = entrada.getKey(); // codigo de vuelo
//			if (clave.equalsIgnoreCase(codVuelo)) {
//				String detalleVuelo = clave + "-" + valor.origen + "-" + valor.destino + "-"
//						+ valor.fechaSalida + "-" + tipo;
//				return detalleVuelo;
//
//			}
//		}
//		return "Codigo de vuelo no existe";
//	}

	public String detalleDeVuelo(String codVuelo) {
	    Vuelo vuelo = vuelos.get(codVuelo);
	 
	    if (vuelo == null) {
	        return "Codigo de vuelo no existe";
	    }
	    
	    String tipo = "";
	    if (vuelo instanceof VueloInternacional) {
	        tipo = "INTERNACIONAL";
	    } else if (vuelo instanceof VueloNacional) {
	        tipo = "NACIONAL";
	    } else if (vuelo instanceof VueloPrivado) {
	        tipo = "PRIVADO (" + ((VueloPrivado) vuelo).cantidadDeJets + ")";
	    }
	    
	    return codVuelo + " - " + vuelo.origen + " - " + vuelo.destino + " - " +
	       vuelo.fechaSalida + " - " + tipo;

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
	public List<String> cancelarVuelo(String codVuelo) {
		List<String> vuelosCancelados = new ArrayList<String>();
		String formato = "";
		Iterator<Map.Entry<Integer, Pasajero>> iterador = pasajeros.entrySet().iterator();

		while (iterador.hasNext()) {
			Map.Entry<Integer, Pasajero> entrada = iterador.next();
			Pasajero valor = entrada.getValue(); // objeto pasajero
			int clave = entrada.getKey(); // dni
			formato += clave + " - " + valor.nombre + " - " + valor.telefono + " - ";
			vuelosCancelados.add(formato);
		}
		return vuelosCancelados;
	}

	@Override
	public void Aerolinea(String nombre, String CUIT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelarPasaje(int dni, int codPasaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double totalRecaudado(String destino) {
		
		
		return 0;
	}
}
