package negocio;

import static org.junit.Assert.*;

import org.junit.Test;

public class EntidadTest {

	@Test (expected = IllegalArgumentException.class)
	public void nombreVacio() 
	{
		Entidad entidad = new Entidad("",1000,2000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void longitudNegativa() 
	{
		Entidad entidad = new Entidad("Los Polvorines",1000,-2000);
	}

}
