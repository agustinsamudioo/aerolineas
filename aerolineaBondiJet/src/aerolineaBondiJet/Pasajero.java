package aerolineaBondiJet;

public class Pasajero extends Cliente {
	int id_vuelo;
	Pasaje pasaje;

	public Pasajero(int dni,String nombre, String telefono, int id_vuelo, Pasaje pasaje) {
		super(dni, nombre, telefono);
		this.id_vuelo=id_vuelo;
		this.pasaje=pasaje;
	}
}
