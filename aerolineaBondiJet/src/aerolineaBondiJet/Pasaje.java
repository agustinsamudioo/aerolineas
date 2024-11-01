package aerolineaBondiJet;

public class Pasaje {
	int id_pasaje;
	Seccion seccion;

	public Pasaje(int id_pasaje, Seccion seccion) {
		this.id_pasaje = id_pasaje;
		this.seccion = seccion;
	}

	public int getId_pasaje() {
		return id_pasaje;
	}

	public void setId_pasaje(int id_pasaje) {
		this.id_pasaje = id_pasaje;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

	

	
}
