package aerolineaBondiJet;

public class VueloNacional extends Vuelo {
	int cant_secciones=2;
	int max_pasajeros=170;

	public VueloNacional(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio,
			double[] precios, int[] cantAsientos) {
		super(origen, destino, fecha, tripulantes, valorRefrigerio, precios, cantAsientos);

	}

}
