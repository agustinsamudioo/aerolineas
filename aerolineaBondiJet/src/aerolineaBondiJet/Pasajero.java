package aerolineaBondiJet;

public class Pasajero extends Cliente {
	int num_asiento;
	String codVuelo;
	int codPasaje;
	boolean aOcupar;
	//int seccion;

	public Pasajero(int num_asiento,String codVuelo, int codPasaje, boolean aOcupar) {
		super(codPasaje, codVuelo, codVuelo);

	}
}
