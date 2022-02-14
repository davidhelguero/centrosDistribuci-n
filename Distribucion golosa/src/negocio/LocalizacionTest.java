package negocio;

import static org.junit.Assert.*;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.junit.Test;

public class LocalizacionTest {

	@Test (expected = IllegalArgumentException.class)
	public void rutaArchivoClientesVacia() 
	{
		Localizacion localizacion = new Localizacion("","centros.txt", 10);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void rutaArchivoCentrosVacia() 
	{
		Localizacion localizacion = new Localizacion("clientes.txt","", 10);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void cantidadCentrosPorAbrirMenorAUno() 
	{
		Localizacion localizacion = new Localizacion("clientes.txt", "centros.txt", 0);
	}
	
	@Test (expected = Exception.class)
	public void rutasArchivosNoExistentes() 
	{
		Localizacion localizacion = new Localizacion("clientes.txt", "centros.txt", 10);
		localizacion.obtenerCentros();
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void obtenerCantidaDeCentrosMayorALosIngresados() 
	{
		Localizacion localizacion = prepararDatos1();
		localizacion.obtenerCentros();
	}
	
	@Test
	public void obtenerCentros() 
	{
		Localizacion localizacion = prepararDatos();
		assertEquals("José C. Paz", localizacion.obtenerCentros().get(0).getNombreCentro());
		assertEquals("San Miguel", localizacion.obtenerCentros().get(1).getNombreCentro());
	}
//	@Test
//	public void testVacio()
//	{
//		Grafo g = new Grafo(0);
//		assertTrue(BFS.esConexo(g));
//	}

	private Localizacion prepararDatos() {
		preparar();
		Localizacion localizacion = new Localizacion("clientes.txt", "centros.txt", 2);
		return localizacion;
	}
	
	private Localizacion prepararDatos1() {
		preparar();
		Localizacion localizacion = new Localizacion("clientes.txt", "centros.txt", 4);
		return localizacion;
	}
	
	private void preparar() {
		ArrayList<Entidad> centros = new ArrayList<>();
		ArrayList<Entidad> clientes = new ArrayList<>();
		centros.add(new Entidad("Los Polvorines",1,2));
		centros.add(new Entidad("San Miguel",3,4));
		centros.add(new Entidad("José C. Paz",9,10));
		clientes.add(new Entidad("David",5,6));
		clientes.add(new Entidad("Ezequiel",7,8));
		try
		{
			FileOutputStream fos = new FileOutputStream("centros.txt");
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(centros);
			out.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		try
		{
			FileOutputStream fos = new FileOutputStream("clientes.txt");
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(clientes);
			out.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}


}
