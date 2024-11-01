package aerolineaBondiJet;

import java.util.HashMap;

public class VueloPrivado extends Vuelo{
	int idVuelo;
	String origen;
	String destino;
	String fecha;
	Cliente clienteComprador; //de aca se necesita el dni del comprador solamente.
	int tripulantes;
	
	
	

	public VueloPrivado(String origen,String destino, String fecha,int dni_comprador, int tripulantes) {
		this.origen=origen;
		this.destino=destino;
		this.fecha=fecha;
		this.tripulantes=tripulantes;
	}
	public double Valor(double precio,int[] acompaniantes) {
		
		double valor = precio * cantidadDeJetsNecesarios(acompaniantes);
		double impuesto = valor*0.3;
		double valorFinal= valor + impuesto;
		return valorFinal;
		
		
	}
	public String toString() {
		return id_vuelo
	}
	public int cantidadDeJetsNecesarios(int[] acompaniantes) {
		
		
	}
}
