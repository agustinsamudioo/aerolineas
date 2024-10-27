package aerolineaBondiJet;

import java.util.HashMap;

public class Aeropuerto {
	String nombre;
	String pais;
	String provincia;
	String direccion;
	HashMap<String, Vuelo>vuelos;
	
	public Aeropuerto(String nombre, String pais, String provincia, String direccion) {
	
		this.nombre=nombre;
		this.pais=pais;
		this.provincia=provincia;
		this.direccion=direccion;
		this.vuelos=new HashMap<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public HashMap<String, Vuelo> getVuelos() {
		return vuelos;
	}

	public void setVuelos(HashMap<String, Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

	
}
