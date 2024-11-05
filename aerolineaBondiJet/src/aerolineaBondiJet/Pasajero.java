package aerolineaBondiJet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Pasajero extends Cliente {
	int num_asiento;
	String codVuelo;
	int codPasaje;
	boolean aOcupar;

	public Pasajero(int num_asiento,String codVuelo, int codPasaje, boolean aOcupar) {
		super(codPasaje, codVuelo, codVuelo);

	}

}
