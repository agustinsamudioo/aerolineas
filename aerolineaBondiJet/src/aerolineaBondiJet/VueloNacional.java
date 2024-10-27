package aerolineaBondiJet;

public class VueloNacional extends Vuelo {
	int cant_secciones;
	int max_pasajeros;

	public VueloNacional(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio,
			double[] precios, int[] cantAsientos) {
		super(origen, destino, fecha, tripulantes, valorRefrigerio, precios, cantAsientos);

	}

}
