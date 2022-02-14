# centrosDistribucion
Este programa tiene como funcionalidad mostrarle al usuario los centros de distribución que debe abrir generando el menor costo, teniendo en cuenta los centros y clientes que ingresa anteriormente con sus latitudes y longitudes.

Está compuesto por una clase de interfaz llamada CargaDeDatos y tres clases de negocio: Localizacion, CostoPorCentro y Entidad (con sus respectivos tests) que serán comentadas a continuación.

Carga de datos

Esta clase tiene como atributos JLabels que informan al usuario lo que tiene que hacer, JTextFields donde el usuario debe ingresar lo que se le pide y un botón que genera el cuadro donde se mostrará los centros de distribución a abrir y el costo total de los mismos. También tiene dos arraylist donde se guardan los centros y los clientes; una variable del tipo Localizacion donde mandarán los datos ingresados y una variable que guarda la cantidad de centros que se quiere abrir.
Además cuenta con un botón para ingresar los datos del cliente que cuando se presiona, evalúa que los datos ingresados sean correctos y se guardan en los arraylist.
El botón Generar Centros cuando es presionado, verifica que los datos ingresados sean correctos y si es así, procede a guardar los datos en los archivos, enviárselos al objeto Localizacion que se instanció anteriormente, a recibir los resultados y por último, mostrarlos en un JDialog.

Localizacion

Esta clase tiene como atributos dos strings donde se guardan la ruta de archivo de los clientes y los centros, la cantidad de centros por abrir, dos arraylist de los clientes y los centros.
Cuenta con sets donde se controla que los datos ingresados no estén vacíos o fuera del rango establecido.
El método principal es obtenerCentros que llama a los métodos leerArchivoClientes y leerArchivoCentros que lo que hace es como dice el nombre, leer los archivos y guardarlos en los arraylist. Una vez hecho esto, llama al método centrosDeMenorCosto que lo que hace es sumar la distancia de todos los clientes con cada centro e ir guardándolos en un nuevo arraylist. Luego, lo que hace ordenarlo según el costo de menor a mayor y retorna una parte de ese arraylist (desde la posición 0 hasta la cantidad de centros que se quiere abrir).
El método distancia básicamente hace lo que informa en el hipervínculo del trabajo práctico.

Entidad

Esta clase fue hecha para los clientes y los centros que tienen los mismos atributos. 
Esta clase tiene de atributos un string nombre, un double latitud y un double longitud. Con los sets se controla que el nombre no esté vacío y la longitud no sea negativa.
Todas las clases involucradas implementan la interfaz Serializable para poder usar el manejo de archivos.

Costo por centro

Esta clase implementa la interfaz Comparable.
Tiene como atributos el nombre del centro y el costo (la sumatoria de los costos de la distancia entre los clientes y los costos).
Con los sets se controla que el nombre no esté vacío y el costo no sea negativo.
También cada atributo tiene su get público para poder acceder desde las otras clases.
Y por último tiene el método compareTo que compara dos costos según el atributo costo.

Las clases CargaDeDatos, Localizacion y Entidad implementan la interfaz Serializable para poder trabajar con el manejo de archivos.
