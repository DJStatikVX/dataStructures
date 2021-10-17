// Paquete al que pertenece la clase
package util;

/**
 * Clase Main con un método principal para ejecutar métodos estáticos
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class Main {

	/**
	 * Método principal
	 * @param args parámetros (no utilizados)
	 */
	public static void main(String[] args) {
		
		// Pruebas por consola de los métodos tratados durante la primera sesión
		
		System.out.println("Potencias Base 2");
		
		for (int i = 0; i < 5; i++) {
			System.out.println(Algoritmos.potenciaBase2It(i));
			System.out.println(Algoritmos.potenciaBase2RV1(i));
		}
		
		System.out.println();
		
		System.out.println("Factoriales");
		
		for (int i = 0; i < 5; i++) {
			System.out.println(Algoritmos.factorialIt(i));
			System.out.println(Algoritmos.factorialR(i));
		}
		
		System.out.println();
		
		System.out.println("Fibonacci");
		
		for (int i = 0; i < 5; i++) {
			System.out.println(Algoritmos.fibonacciIt(i));
			System.out.println(Algoritmos.fibonacciR(i));
		}
		
		System.out.println();
		
		// Ejecución de pruebas para la entrega del proyecto de Algoritmos
		
		TestBench.test("lineal.csv", 5, 0, 9, "util.Algoritmos", "lineal");
		TestBench.test("cuadratico.csv", 5, 0, 9, "util.Algoritmos", "cuadratico");
		TestBench.test("cubico.csv", 5, 0, 9, "util.Algoritmos", "cubico");
		TestBench.test("logaritmico.csv", 5, 0, 9, "util.Algoritmos", "logaritmico");
		TestBench.test("pow1.csv", 5, 0, 9, "util.Algoritmos", "potenciaBase2RV1");
		TestBench.test("pow2.csv", 5, 0, 9, "util.Algoritmos", "potenciaBase2RV2");
		TestBench.test("pow3.csv", 5, 0, 9, "util.Algoritmos", "potenciaBase2RV3");
		TestBench.test("pow4.csv", 5, 0, 9, "util.Algoritmos", "potenciaBase2RV4");

	}

}