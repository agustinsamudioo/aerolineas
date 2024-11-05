package aerolineaBondiJet;

import java.util.HashMap;

public class VueloPrivado extends Vuelo {
	public String id_vuelo;
	int dniComprador;
	HashMap<Integer, Pasajero> pasajeros;
	double costoTotal;
	String cantidadDeJets;

	public VueloPrivado(String origen, String destino, String fecha, int tripulantes,
			double[] precios, int[] cantAsientos) {
		super(origen, destino, fecha, tripulantes, precios, cantAsientos);
	}

	public VueloPrivado(String origen,String destino, String fecha,int dni_comprador, int tripulantes, double precio) {
		this.origen=origen;
		this.destino=destino;
		this.fechaSalida=fecha;
		this.cant_tripulantes=tripulantes;
		this.dniComprador= dni_comprador;
		id_vuelo = Aerolinea.GeneradorId();
		this.cantidadDeJets = jetsNecesarios(tripulantes);
		this.costoTotal = calcularCosto(precio,tripulantes);
		HashMap<Integer, Pasajero> pasajeros =  new HashMap<>();

	}

	public String detallesDelVuelo() {
		return "Id del vuelo:"+ id_vuelo;
	}
	
	
	public String jetsNecesarios(int tripulantes) {
		int capacidadJet=15;

		double cantidadDeJets =  tripulantes / capacidadJet ;
		cantidadDeJets = Math.ceil(cantidadDeJets); //redondea hacia el entero superior.
		String Jets = String.valueOf(cantidadDeJets); //convierte a String para poder usarse en funcion detalleDelVuelo
		return Jets;
	}
	
	
	public double calcularCosto(double precio, int tripulantes) {
		
		double jets= Double.parseDouble(jetsNecesarios(tripulantes));
		double valor = precio * jets;
		double impuesto = valor*0.3;
		double costo = valor + impuesto;
		return costo;

	}	

}
