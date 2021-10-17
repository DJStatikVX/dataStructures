// Paquete al que pertenece la clase
package util;

// Importaci�n de clases
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * Clase TestBench con m�todos para medir la eficacia y rendimiento
 * de los m�todos est�ticos en la clase Algoritmos
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class TestBench {
	
	// INTROSPECCI�N: n m�todos, 1 test
	
	/**
	 * Busca un m�todo de una clase y lo ejecuta con un par�metro determinado
	 * @param className nombre de la clase, de tipo String
	 * @param methodName nombre del m�todo, de tipo String
	 * @param n par�metro que pasamos al m�todo, de tipo int
	 * @return Invocaci�n del m�todo seleccionado con su par�metro, o null
	 */
	public static Object testAlgorithm(String className, 
			String methodName, int n) {
		
		try {
			
			// Se busca la clase y se declara en un objeto
			Object obj = Class.forName(className).newInstance();
			
			/* 
			 * Una vez hallada la clase, se invoca al m�todo en cuesti�n
			 * y se almacena en una variable de tipo Method
			 */
			Method method = obj.getClass().getMethod(methodName, long.class);
		
			return method.invoke(obj, n);	// se invoca el m�todo con par. n
			
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
		
		return null; // se devuelve null si no se encontr� la clase o m�todo
	}
	
	/**
	 * Calcula el tiempo promedio de ejecuci�n de un m�todo llevado a cabo
	 * un determinado n�mero de veces y con una carga determinada
	 * @param times n�mero de veces a ejecutar el m�todo, de tipo int
	 * @param inicioCarga carga inicial, de tipo int
	 * @param finalCarga carga final, de tipo int
	 * @param className nombre de la clase que alberga el m�todo, de tipo String
	 * @param method nombre del m�todo a ejecutar, de tipo String
	 */
	public static void test(int times, int inicioCarga, 
			int finalCarga, String className, String method) {
	
		// Se repite la medici�n para cada carga
		for (int carga = inicioCarga; carga < finalCarga; carga++) {
			
			// Se empieza a medir el tiempo
			double tiempoInicial = System.currentTimeMillis();
			
			// Ejecuci�n del algoritmo el n�mero de veces correspondiente
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
	 * Calcula el tiempo promedio de ejecuci�n de un m�todo llevado a cabo
	 * un determinado n�mero de veces y con una carga determinada; adem�s,
	 * lo imprime en un fichero de nombre output
	 * @param output nombre del fichero de salida, de tipo String
	 * @param times n�mero de veces a ejecutar el m�todo, de tipo int
	 * @param inicioCarga carga inicial, de tipo int
	 * @param finalCarga carga final, de tipo int
	 * @param className nombre de la clase que alberga el m�todo, de tipo String
	 * @param method nombre del m�todo a ejecutar, de tipo String
	 */
	public static void test(String output, int times, int inicioCarga,
			int finalCarga, String className, String method) {
		
		try {

			// Se inicializa el objeto de escritura al fichero
			PrintWriter pw = new PrintWriter(new FileWriter(output));
			
			try {

				// Se repite la medici�n para cada carga
				for (int carga = inicioCarga; carga < finalCarga; carga++) {

					// Se empieza a medir el tiempo
					double tiempoInicial = System.currentTimeMillis();

					// Ejecuci�n del algoritmo el n�mero de veces correspondiente
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