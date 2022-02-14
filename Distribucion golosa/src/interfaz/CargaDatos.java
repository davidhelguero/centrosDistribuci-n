package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.CostoPorCentro;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CargaDatos extends JDialog implements Serializable{

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_nombreCliente;
	private JTextField textField_latitudCliente;
	private JTextField textField_longitudCliente;
	private JTextField textField_nombreCentro;
	private JTextField textField_latitudCentro;
	private JTextField textField_longitudCentro;
	private ArrayList<negocio.Entidad> clientes;
	private ArrayList<negocio.Entidad> centros;
	private static final long serialVersionUID = 1L;
	private negocio.Localizacion localizacion;
	private JTextField textField_cantCentros;
	private int cantCentros;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CargaDatos dialog = new CargaDatos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CargaDatos() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		clientes  = new ArrayList<>();
		centros  = new ArrayList<>();
		
		
		//BOTON INGRESAR CLIENTE
		JButton btn_ingresarCliente = new JButton("Ingresar");
		btn_ingresarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(hayDatosDelClienteVacios())
					JOptionPane.showMessageDialog(getContentPane(), "No puede ingresar espacios en blanco");
				
				try {
					guardarDatosDelCliente();
					vaciarTextFieldsDelCliente();
				}
				
				catch(IllegalArgumentException exception) {
					JOptionPane.showMessageDialog(getContentPane(), "La longitud y latitud debe ser un número");
				}
			}
		});
		btn_ingresarCliente.setBounds(76, 123, 89, 23);
		contentPanel.add(btn_ingresarCliente);
		
		
		//BOTON INGRESAR CENTRO DE DISTRIBUCION
		JButton btn_ingresarCentro = new JButton("Ingresar");
		btn_ingresarCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(hayDatosDelCentroVacios())
					JOptionPane.showMessageDialog(getContentPane(), "No puede ingresar espacios en blanco");
				
				try {
					guardarDatosDelCentro();
					vaciarTextFieldsDelCentro();
				}
				catch(IllegalArgumentException exception) {
						JOptionPane.showMessageDialog(getContentPane(), "La longitud y latitud deben ser un número");
				}
			}
		});
		btn_ingresarCentro.setBounds(254, 123, 89, 23);
		contentPanel.add(btn_ingresarCentro);
		
		
		//LABELS Y TEXTFIELDS
		JLabel lbl_nombreCliente = new JLabel("Nombre:");
		lbl_nombreCliente.setBounds(20, 45, 57, 14);
		contentPanel.add(lbl_nombreCliente);
		
		JLabel lbl_latitudCliente = new JLabel("Latitud:");
		lbl_latitudCliente.setBounds(20, 70, 57, 14);
		contentPanel.add(lbl_latitudCliente);
		
		JLabel lbl_longitudCliente = new JLabel("Longitud:");
		lbl_longitudCliente.setBounds(20, 95, 57, 14);
		contentPanel.add(lbl_longitudCliente);
		
		JLabel lbl_nombreCentro = new JLabel("Nombre:");
		lbl_nombreCentro.setBounds(198, 45, 57, 14);
		contentPanel.add(lbl_nombreCentro);
		
		JLabel lbl_latitudCentro = new JLabel("Latitud:");
		lbl_latitudCentro.setBounds(198, 70, 57, 14);
		contentPanel.add(lbl_latitudCentro);
		
		JLabel lbl_longitudCentro = new JLabel("Longitud:");
		lbl_longitudCentro.setBounds(198, 95, 57, 14);
		contentPanel.add(lbl_longitudCentro);
		
		textField_nombreCliente = new JTextField();
		textField_nombreCliente.setBounds(76, 42, 86, 20);
		contentPanel.add(textField_nombreCliente);
		textField_nombreCliente.setColumns(10);
		
		textField_latitudCliente = new JTextField();
		textField_latitudCliente.setBounds(76, 67, 86, 20);
		contentPanel.add(textField_latitudCliente);
		textField_latitudCliente.setColumns(10);
		
		textField_longitudCliente = new JTextField();
		textField_longitudCliente.setBounds(76, 92, 86, 20);
		contentPanel.add(textField_longitudCliente);
		textField_longitudCliente.setColumns(10);
		
		textField_nombreCentro = new JTextField();
		textField_nombreCentro.setBounds(254, 42, 86, 20);
		contentPanel.add(textField_nombreCentro);
		textField_nombreCentro.setColumns(10);
		
		textField_latitudCentro = new JTextField();
		textField_latitudCentro.setBounds(254, 67, 86, 20);
		contentPanel.add(textField_latitudCentro);
		textField_latitudCentro.setColumns(10);
		
		textField_longitudCentro = new JTextField();
		textField_longitudCentro.setBounds(254, 92, 86, 20);
		contentPanel.add(textField_longitudCentro);
		textField_longitudCentro.setColumns(10);
		
		JLabel lbl_ingresarClientes = new JLabel("Ingresar clientes");
		lbl_ingresarClientes.setBounds(20, 20, 145, 14);
		contentPanel.add(lbl_ingresarClientes);
		
		JLabel lbl_ingresarCentros = new JLabel("Ingresar centros de distribuci\u00F3n");
		lbl_ingresarCentros.setBounds(198, 20, 226, 14);
		contentPanel.add(lbl_ingresarCentros);
		
		JLabel lbl_cantCentros = new JLabel("Ingrese cantidad de centros que desea abrir");
		lbl_cantCentros.setBounds(32, 177, 268, 14);
		contentPanel.add(lbl_cantCentros);
		
		textField_cantCentros = new JTextField();
		textField_cantCentros.setBounds(299, 174, 44, 20);
		contentPanel.add(textField_cantCentros);
		textField_cantCentros.setColumns(10);
		
		
		//BOTON OBTENER CENTROS
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Obtener centros");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//VERIFICA SI HAY CLIENTES Y CENTROS INGRESADOS
						boolean cantCentrosEsUnNumero = false;
						if(noHayClientesYCentros()) {
							JOptionPane.showMessageDialog(getContentPane(), "Debe ingresar al menos un cliente y al menos un centro");
						}
						else {
							
							//VERIFICA SI SE INGRESO UN NÚMERO Y SI ESTÁ DENTRO DEL RANGO
							cantCentrosEsUnNumero = verificarTipoIngresado();
							if(cantCentrosEsUnNumero && cantidadCentrosRangoValido())
								JOptionPane.showMessageDialog(getContentPane(), "La cantidad de centros que desea abrir no puede ser negativa o mayor a la cantidad de centros que ingresó");
							
							else if(cantCentrosEsUnNumero){
								
								guardarClientesEnArchivo();
								guardarCentrosEnArchivo();
								enviarDatos();
								List<negocio.CostoPorCentro> prueba = recibirResultados();
								mostrarResultados(prueba);
							}
						}
					}

					
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	//MÉTODOS AUXILIARES
	
	private void guardarDatosDelCliente() {
		double latitud = Double.parseDouble(textField_latitudCliente.getText());
		double longitud = Double.parseDouble(textField_longitudCliente.getText());
		if(longitud >= 0)
			clientes.add(new negocio.Entidad(textField_nombreCliente.getText(), latitud, longitud));
		else
			JOptionPane.showMessageDialog(getContentPane(), "La longitud no puede ser negativa. Ingrese de nuevo");
	}
	
	private void vaciarTextFieldsDelCliente() {
		textField_nombreCliente.setText("");
		textField_latitudCliente.setText("");
		textField_longitudCliente.setText("");
	}

	private boolean hayDatosDelClienteVacios() {
		return textField_nombreCliente.getText().isBlank() ||
		   textField_latitudCliente.getText().isBlank() ||
		   textField_longitudCliente.getText().isBlank();
	}
	
	private void guardarDatosDelCentro() {
		double latitud = Double.parseDouble(textField_latitudCentro.getText());
		double longitud = Double.parseDouble(textField_longitudCentro.getText());
		if(longitud >= 0)
			centros.add(new negocio.Entidad(textField_nombreCentro.getText(), latitud, longitud));
		else
			JOptionPane.showMessageDialog(getContentPane(), "La longitud no puede ser negativa. Ingrese de nuevo");
	}

	private void vaciarTextFieldsDelCentro() {
		textField_nombreCentro.setText("");
		textField_latitudCentro.setText("");
		textField_longitudCentro.setText("");
	}

	private boolean hayDatosDelCentroVacios() {
		return textField_nombreCentro.getText().isBlank() ||
		   textField_latitudCentro.getText().isBlank() ||
		   textField_longitudCentro.getText().isBlank();
	}
	
	private boolean noHayClientesYCentros() {
		return clientes.isEmpty() || centros.isEmpty();
	}

	private boolean cantidadCentrosRangoValido() {
		return cantCentros < 1 || cantCentros > centros.size();
	}

	private boolean verificarTipoIngresado() {
		boolean esNumero = false;
		try {
			cantCentros = Integer.parseInt(textField_cantCentros.getText());
			esNumero = true;
		}
		catch(IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(getContentPane(), "La cantidad de centros que desea abrir no puede ser vacío y debe ser un número");
		}
		return esNumero;
	}

	private void mostrarResultados(List<negocio.CostoPorCentro> prueba) {
		String nombresCentros = "Centros a abrir: ";
		double suma=0;
		for(CostoPorCentro costo: prueba) {
			nombresCentros += costo.getNombreCentro()+", ";
			suma+=costo.getCostoTotal();
		}
		nombresCentros = nombresCentros.substring(0, nombresCentros.length() - 2);
		nombresCentros += "\nCosto total: $"+suma;
		JOptionPane.showMessageDialog(getContentPane(), nombresCentros);
	}

	private List<negocio.CostoPorCentro> recibirResultados() {
		List<negocio.CostoPorCentro> resultados = localizacion.obtenerCentros();
		return resultados;
	}

	private void enviarDatos() {
		localizacion = new negocio.Localizacion("clientes.txt", "centros.txt", cantCentros);
	}
	
	//DECISIÓN DE IMPLEMENTACIÓN (NO SE SI ES LA MÁS CORRECTA): DESDE ESTA CLASE DE INTERFAZ, EN VEZ DE PASARLE LOS ARRAYLIST
	//CENTROS Y CLIENTES A LA CLASE LOCALIZACION, LE PASO YA LOS ARCHIVOS Y ESA CLASE SE ENCARGA DE LEERLOS.
	private void guardarCentrosEnArchivo() {
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
	}

	private void guardarClientesEnArchivo() {
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
