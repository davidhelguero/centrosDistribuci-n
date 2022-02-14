import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import negocio.CostoPorCentro;

public class prueba2Test {

	@Test (expected = IllegalArgumentException.class)
	public void setNull() 
	{
		prueba2.menorElemento(null);
	}
	
//	@Test (expected = IllegalArgumentException.class)
//	public void setInstanciadoPeroVacio() 
//	{
//		Set<Integer> set = new HashSet<>();
//		prueba2.menorElemento(set);
//	}
	
	@Test
	public void unSoloElemento() 
	{
		Set<Integer> set = new HashSet<>();
		set.add(10);
		assertEquals(10, prueba2.menorElemento(set));
	}
	
	@Test
	public void devolverElMenor() 
	{
		Set<Integer> set = prepararDatos1();
		assertEquals(2147483644 , prueba2.menorElemento(set));
	}
	
	@Test
	public void valoresNegativos() 
	{
		Set<Integer> set = prepararDatos2();
		assertEquals(-3 , prueba2.menorElemento(set));
	}

	private Set<Integer> prepararDatos1() {
		Set<Integer> set = new HashSet<>();
		set.add(2147483646);
		set.add(2147483645);
		set.add(2147483644);
		return set;
	}
	
	private Set<Integer> prepararDatos2() {
		Set<Integer> set = new HashSet<>();
		set.add(-1);
		set.add(-3);
		set.add(2);
		return set;
	}
}
