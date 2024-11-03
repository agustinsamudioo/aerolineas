package aerolineaBondiJet;

import java.util.HashMap;

public class VueloPrivado extends Vuelo{
	int idVuelo;
	String origen;
	String destino;
	String fecha;
	Cliente clienteComprador; //de aca se necesita el dni del comprador solamente.
	int tripulantes;
	int dni_comprador;
	double valorFinal;


	//	public class VueloPrivado {
	//		Double precioJet;
	//		int max_asientos;
	//		HashMap<Integer, Pasajero> pasajerosPriv;
	//		int cant_jets;
	//		Double precioVueloCompleto;



	public VueloPrivado(String origen,String destino, String fecha,int dni_comprador, int tripulantes, double precio) {
		this.origen=origen;
		this.destino=destino;
		this.fecha=fecha;
		this.tripulantes=tripulantes;
		this.dni_comprador= dni_comprador;
		valorFinal= valor(precio,tripulantes);

	}
	public VueloPrivado(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio,
			double[] precios, int[] cantAsientos) {
		super(origen, destino, fecha, tripulantes, valorRefrigerio, precios, cantAsientos);

		
	}
	//	public double valor(double precio,int tripulantes) {

	//		double valor = precio * JetsNecesarios(tripulantes);
	//		double impuesto = valor*0.3;
	//		double valorFinal= valor + impuesto;
	//		return valorFinal;


	//	}
	public String toString() {
		return "Id del vuelo:"+ id_vuelo ;
	}
	public double JetsNecesarios(int tripulantes) {
		int capacidadJet=15;

		double cantidadDeJets =  tripulantes / capacidadJet ;
		return cantidadDeJets = Math.ceil(cantidadDeJets);

	}
	public double valor(double precio, int tripulantes) {
		double valor = precio * JetsNecesarios(tripulantes);
		double impuesto = valor*0.3;
		return valorFinal= valor + impuesto;

	}



}

