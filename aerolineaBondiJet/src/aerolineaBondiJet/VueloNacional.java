package aerolineaBondiJet;

public class VueloNacional extends VueloPublico {
	
	
	public VueloNacional(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio,
			double[] precios, int[] cantAsientos) {
		super(origen, destino, fecha, tripulantes, precios, cantAsientos);
		this.refrigerio.precio=valorRefrigerio;
		this.cantSecciones=2;
												
	}

}
