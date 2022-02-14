package negocio;

import static org.junit.Assert.*;

import org.junit.Test;

public class CostoPorCentroTest {

	@Test (expected = IllegalArgumentException.class)
	public void nombreVacio() 
	{
		CostoPorCentro costoPorCentro = new CostoPorCentro("", 1000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void costoNegativo() 
	{
		CostoPorCentro costoPorCentro = new CostoPorCentro("Los Polvorines", -1000);
	}
	
	@Test
	public void compararCostoConUnoMayor() 
	{
		CostoPorCentro costoPorCentro1 = new CostoPorCentro("Los Polvorines", 2000);
		CostoPorCentro costoPorCentro2 = new CostoPorCentro("San Miguel", 1000);
		assertEquals(1, costoPorCentro1.compareTo(costoPorCentro2));
	}
	
	@Test
	public void compararCostoConUnoMenor() 
	{
		CostoPorCentro costoPorCentro1 = new CostoPorCentro("Los Polvorines", 1000);
		CostoPorCentro costoPorCentro2 = new CostoPorCentro("San Miguel", 2000);
		assertEquals(-1, costoPorCentro1.compareTo(costoPorCentro2));
	}
	
	@Test
	public void compararCostoConUnoIgual() 
	{
		CostoPorCentro costoPorCentro1 = new CostoPorCentro("Los Polvorines", 1000);
		CostoPorCentro costoPorCentro2 = new CostoPorCentro("San Miguel", 1000);
		assertEquals(0, costoPorCentro1.compareTo(costoPorCentro2));
	}
}
