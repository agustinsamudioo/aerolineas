package aerolineaBondiJet;

public class VueloNacional extends Vuelo{
int cant_secciones;
int max_pasajeros;
public VueloNacional(String origen, String destino, String fecha, int tripulantes, Double valorRefrigerio,
		Double[] precios, int[] cantAsientos) {
	super(origen, destino, fecha, tripulantes, valorRefrigerio, precios, cantAsientos);

}

}
