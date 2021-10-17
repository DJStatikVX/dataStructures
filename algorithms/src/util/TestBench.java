// Paquete al que pertenece la clase
package util;

// Importación de clases
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * Clase TestBench con métodos para medir la eficacia y rendimiento
 * de los métodos estáticos en la clase Algoritmos
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class TestBench {
	
	// INTROSPECCIÓN: n métodos, 1 test
	
	/**
	 * Busca un método de una clase y lo ejecuta con un parámetro determinado
	 * @param className nombre de la clase, de tipo String
	 * @param methodName nombre del método, de tipo String
	 * @param n parámetro que pasamos al método, de tipo int
	 * @return Invocación del método seleccionado con su parámetro, o null
	 */
	public static Object testAlgorithm(String className, 
			String methodName, int n) {
		
		try {
			
			// Se busca la clase y se declara en un objeto
			Object obj = Class.forName(className).newInstance();
			
			/* 
			 * Una vez hallada la clase, se invoca al método en cuestión
			 * y se almacena en una variable de tipo Method
			 */
			Method method = obj.getClass().getMethod(methodName, long.class);
		
			return method.invoke(obj, n);	// se invoca el método con par. n
			
		} catch (ClassNotFoundException cnfe) {
			System.out.println("ERROR: Class not found!");
			cnfe.printStackTrace();
		} catch (NoSuchMethodException nsme) {
			System.out.println("ERROR: No such method!");
			nsme.printStackTrace();
		} catch (IllegalAccessException iae) {
			System.out.println("ERROR: Forbidden access!");
			iae.printStackTrace();
		} catch (IllegalArgumentException iarge) {
			System.out.println("ERROR: Illegal argument!");
			iarge.printStackTrace();
		} catch (Exception e) {
			System.out.println("ERROR: Unknown error!");
		}
		
		return null; // se devuelve null si no se encontró la clase o método
	}
	
	/**
	 * Calcula el tiempo promedio de ejecución de un método llevado a cabo
	 * un determinado número de veces y con una carga determinada
	 * @param times número de veces a ejecutar el método, de tipo int
	 * @param inicioCarga carga inicial, de tipo int
	 * @param finalCarga carga final, de tipo int
	 * @param className nombre de la clase que alberga el método, de tipo String
	 * @param method nombre del método a ejecutar, de tipo String
	 */
	public static void test(int times, int inicioCarga, 
			int finalCarga, String className, String method) {
	
		// Se repite la medición para cada carga
		for (int carga = inicioCarga; carga < finalCarga; carga++) {
			
			// Se empieza a medir el tiempo
			double tiempoInicial = System.currentTimeMillis();
			
			// Ejecución del algoritmo el número de veces correspondiente
			for (int j = 0; j < times; j++) {
				testAlgorithm(className, method, carga);
			}
			
			// Se mide el tiempo final
			double tiempoFinal = System.currentTimeMillis();
			
			// Se calcula el tiempo promedio
			double tiempo = (tiempoFinal - tiempoInicial) / times;
			
			// Se muestra el tiempo empleado para la carga iterada
			System.out.println(carga + ", " + tiempo);
		}
	}
	
	/**
	 * Calcula el tiempo promedio de ejecución de un método llevado a cabo
	 * un determinado número de veces y con una carga determinada; además,
	 * lo imprime en un fichero de nombre output
	 * @param output nombre del fichero de salida, de tipo String
	 * @param times número de veces a ejecutar el método, de tipo int
	 * @param inicioCarga carga inicial, de tipo int
	 * @param finalCarga carga final, de tipo int
	 * @param className nombre de la clase que alberga el método, de tipo String
	 * @param method nombre del método a ejecutar, de tipo String
	 */
	public static void test(String output, int times, int inicioCarga,
			int finalCarga, String className, String method) {
		
		try {

			// Se inicializa el objeto de escritura al fichero
			PrintWriter pw = new PrintWriter(new FileWriter(output));
			
			try {

				// Se repite la medición para cada carga
				for (int carga = inicioCarga; carga < finalCarga; carga++) {

					// Se empieza a medir el tiempo
					double tiempoInicial = System.currentTimeMillis();

					// Ejecución del algoritmo el número de veces correspondiente
					for (int j = 0; j < times; j++) {
						testAlgorithm(className, method, carga);
					}

					// Se mide el tiempo final
					double tiempoFinal = System.currentTimeMillis();

					// Se calcula el tiempo promedio
					double tiempo = (tiempoFinal - tiempoInicial) / times;

					// Se graba el tiempo empleado para la carga en el fichero
					pw.println(carga + ";" + tiempo);
				}

			} finally {
				pw.close(); // obligatoriamente, se cierra el flujo tras grabar
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}