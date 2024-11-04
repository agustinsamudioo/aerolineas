package aerolineaBondiJet;

import java.util.HashMap;

public class VueloPrivado extends Vuelo {
	public String id_vuelo;
	int comprador;
	HashMap<Integer, Pasajero> pasajerosPriv;
	double costoTotal;
	String cantidadDeJets;

	public VueloPrivado(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio,
			double[] precios, int[] cantAsientos) {
		super(origen, destino, fecha, tripulantes, valorRefrigerio, precios, cantAsientos);
	}

	public VueloPrivado(String origen,String destino, String fecha,int dni_comprador, int tripulantes, double precio) {
		this.origen.nombre=origen;
		this.destino.nombre=destino;
		this.fecha_salida_vuelo=fecha;
		this.cant_tripulantes=tripulantes;
		this.comprador= dni_comprador;
		id_vuelo = Aerolinea.GeneradorId();
		this.cantidadDeJets = JetsNecesarios(tripulantes);
		this.costoTotal = calcularCosto(precio,tripulantes);

	}

	public String detallesDelVuelo() {
		return "Id del vuelo:"+ id_vuelo;
	}
	
	
	public String JetsNecesarios(int tripulantes) {
		int capacidadJet=15;

		double cantidadDeJets =  tripulantes / capacidadJet ;
		cantidadDeJets = Math.ceil(cantidadDeJets); //redondea hacia el entero superior.
		String Jets = String.valueOf(cantidadDeJets); //convierte a String para poder usarse en funcion detalleDelVuelo
		return Jets;
	}
	
	
	public double calcularCosto(double precio, int tripulantes) {
		
		double jets= Double.parseDouble(JetsNecesarios(tripulantes));
		double valor = precio * jets;
		double impuesto = valor*0.3;
		double costo = valor + impuesto;
		return costo;

	}	

}
