package aerolineaBondiJet;

public class VueloNacional extends VueloPublico {
	double valorRefrigerio;
	
	
	public VueloNacional(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio,
			double[] precios, int[] cantAsientos) {
		super(origen, destino, fecha, tripulantes, precios, cantAsientos);
		this.valorRefrigerio=valorRefrigerio;
		this.cantSecciones=2;
												
	}

}
