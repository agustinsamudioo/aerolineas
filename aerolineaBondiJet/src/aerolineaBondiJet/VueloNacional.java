package aerolineaBondiJet;

public class VueloNacional extends Vuelo {

	Seccion secciones;
	
	
	public VueloNacional(String origen, String destino, String fecha, int tripulantes,Refrigerio valorRefrigerio,
			double[] precios) {
		super(origen, destino, fecha, tripulantes, valorRefrigerio, precios);
		
	}
	

}
