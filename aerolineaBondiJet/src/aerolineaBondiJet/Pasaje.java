package aerolineaBondiJet;

public class Pasaje {
	int id_pasaje;
	int seccion;
	int num_asiento;

	public Pasaje(int id_pasaje, int seccion, int num_asiento) {
		this.id_pasaje = id_pasaje;
		this.seccion = seccion;
		this.num_asiento = num_asiento;
	}

	public int getId_pasaje() {
		return id_pasaje;
	}

	public void setId_pasaje(int id_pasaje) {
		this.id_pasaje = id_pasaje;
	}

	public int getSeccion() {
		return seccion;
	}

	public void setSeccion(int seccion) {
		this.seccion = seccion;
	}

	public int getNum_asiento() {
		return num_asiento;
	}

	public void setNum_asiento(int num_asiento) {
		this.num_asiento = num_asiento;
	}
}
