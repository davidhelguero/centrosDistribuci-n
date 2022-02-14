package negocio;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Localizacion implements Serializable{
	private String archivoClientes;
	private String archivoCentros;
	private int cantCentros;
	private ArrayList<Entidad> clientes;
	private ArrayList<Entidad> centros;
	private static final long serialVersionUID = 1L;
	
	public Localizacion(String archivoClientes, String archivoCentros, int cantCentros) {
		setArchivoClientes(archivoClientes);
		setArchivoCentros(archivoCentros);
		setCantCentros(cantCentros);
		clientes = new ArrayList<>();
		centros = new ArrayList<>();
	}
	
	private void setArchivoClientes(String archivoClientes) {
		if(!archivoClientes.isBlank())
			this.archivoClientes = archivoClientes;
		else
			throw new IllegalArgumentException("La ruta no puede estar vacía");
	}

	private void setArchivoCentros(String archivoCentros) {
		if(!archivoCentros.isBlank())
			this.archivoCentros = archivoCentros;
		else
			throw new IllegalArgumentException("La ruta no puede estar vacía"); 
	}

	private void setCantCentros(int cantCentros) {
		if(cantCentros > 0)
			this.cantCentros = cantCentros;
		else
			throw new IllegalArgumentException("La cantidad de centros no puede ser 0 o negativa");
	}

	public List<CostoPorCentro> obtenerCentros() {
		
		leerArchivoClientes();
		leerArchivoCentros();
		return centrosDeMenorCosto();
	}

	private void leerArchivoClientes(){
		try {
			FileInputStream fis = new FileInputStream(archivoClientes);
			ObjectInputStream in = new ObjectInputStream(fis);
			clientes = (ArrayList<Entidad>) in.readObject();
			in.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void leerArchivoCentros(){
		try {
			FileInputStream fis = new FileInputStream(archivoCentros);
			ObjectInputStream in = new ObjectInputStream(fis);
			centros = (ArrayList<Entidad>) in.readObject();
			in.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private List<CostoPorCentro> centrosDeMenorCosto() {
		
		if(cantCentros > centros.size())
			throw new IllegalArgumentException("La cantidad de centros que desea abrir es mayor a la que fue ingresada");
		
		double costoPorCentro = 0;
		ArrayList<CostoPorCentro> costosPorCentro = new ArrayList<>();
		
		for(Entidad centro: centros) {
			for(Entidad cliente: clientes)
				costoPorCentro += distancia(centro.getLatitud(), centro.getLongitud(), cliente.getLatitud(), cliente.getLongitud());
			
			costosPorCentro.add(new CostoPorCentro(centro.getNombre(), costoPorCentro));
			costoPorCentro = 0;
		}
		
		Collections.sort(costosPorCentro);
		return costosPorCentro.subList(0, cantCentros);
	}

	private int distancia(double lat1, double lon1, double lat2, double lon2) {
		
		double radioTierra = 6371;

		lat1 = Math.toRadians(lat1);
		lon1 = Math.toRadians(lon1);
		lat2 = Math.toRadians(lat2);
		lon2 = Math.toRadians(lon2);

		double dlon = (lon2 - lon1);
		double dlat = (lat2 - lat1);

		double sinlat = Math.sin(dlat / 2);
		double sinlon = Math.sin(dlon / 2);

		double a = (sinlat * sinlat) + Math.cos(lat1)*Math.cos(lat2)*(sinlon*sinlon);
		double c = 2 * Math.asin (Math.min(1.0, Math.sqrt(a)));

		double distancia = radioTierra * c;

		return (int)distancia;

	}

}
