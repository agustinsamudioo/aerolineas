package aerolineaBondiJet;

public class Pasajero extends Cliente {
<<<<<<< HEAD
	int id_vuelo;
	Pasaje pasaje;
=======
	int num_asiento;
	String codVuelo;
	int codPasaje;
	boolean aOcupar;
>>>>>>> branch 'main' of https://github.com/agustinsamudioo/aerolineas.git

<<<<<<< HEAD
	public Pasajero(int dni,String nombre, String telefono, int id_vuelo, Pasaje pasaje) {
		super(dni, nombre, telefono);
		this.id_vuelo=id_vuelo;
		this.pasaje=pasaje;
=======
	public Pasajero(int num_asiento,String codVuelo, int codPasaje, boolean aOcupar) {
		super(codPasaje, codVuelo, codVuelo);

>>>>>>> branch 'main' of https://github.com/agustinsamudioo/aerolineas.git
	}
}
