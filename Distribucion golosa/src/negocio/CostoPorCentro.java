package negocio;

public class CostoPorCentro implements Comparable<CostoPorCentro>{
	private String nombreCentro;
	private double costoTotal;
	
	public CostoPorCentro(String nombreCentro, double costoTotal) {
		setNombreCentro(nombreCentro);
		setCostoTotal(costoTotal);
	}
	
	private void setNombreCentro(String nombreCentro) {
		if(!nombreCentro.isBlank())
			this.nombreCentro = nombreCentro;
		else
			throw new IllegalArgumentException("El nombre no puede estar vacío");
	}

	private void setCostoTotal(double costoTotal) {
		if(costoTotal > 0)
			this.costoTotal = costoTotal;
		else
			throw new IllegalArgumentException("El costo no puede ser 0 o negativo");
	}

	public String getNombreCentro() {
		return nombreCentro;
	}

	public double getCostoTotal() {
		return costoTotal;
	}

	@Override
	public int compareTo(CostoPorCentro o) {
		if(this.getCostoTotal() < o.getCostoTotal())
			return -1;
		if(this.getCostoTotal() == o.getCostoTotal())
			return 0;
		return 1;
	}
	
}
