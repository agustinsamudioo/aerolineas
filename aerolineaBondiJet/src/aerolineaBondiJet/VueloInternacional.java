package aerolineaBondiJet;

public class VueloInternacional extends VueloPublico {
	
	String escala;
	
	public VueloInternacional(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio,
			double[] precios, int[] cantAsientos) {
		super(origen, destino, fecha, tripulantes, precios, cantAsientos);
		this.valorRefrigerio= valorRefrigerio;
		
	}

}
