package aerolineaBondiJet;

import java.util.HashMap;

public class VueloPrivado extends Vuelo {
	Double precioJet;
	int max_asientos;
	HashMap<Integer, Pasajero> pasajerosPriv;
	int cant_jets;
	Double precioVueloCompleto;

	public VueloPrivado(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio,
			double[] precios, int[] cantAsientos) {
		super(origen, destino, fecha, tripulantes, valorRefrigerio, precios, cantAsientos);
		
		// TODO Auto-generated constructor stub
	}

}
