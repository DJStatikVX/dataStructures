// Paquete al que pertenece la clase
package util;

/**
 * Clase Algoritmos que contiene métodos iterativos y recursivos
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class Algoritmos {
	
	/**
	 * Constante para el tiempo de espera del método doNothing(), de tipo int
	 */
	static final int TIEMPO = 250;
	
	/**
	 * Calcula, de forma iterativa, la potencia en base 2 tomando
	 * como exponente el número pasado como parámetro y la devuelve
	 * @param n número a utilizar como exponente, de tipo long
	 * @return Potencia en base 2 del parámetro proporcionado, de tipo long
	 */
	public static long potenciaBase2It(long n) {
		long result = 1;	// caso básico y almacenamiento del resultado
		
		// Iteraciones
		for (long i = n; i > 0; i--) {
			result *= 2; 
		}
		
		return result;			// se devuelve el resultado
	}
	
	/**
	 * Calcula, de forma recursiva, la potencia en base 2 tomando
	 * como exponente el número pasado como parámetro y la devuelve
	 * @param n número a utilizar como exponente, de tipo long
	 * @return Potencia en base 2 del parámetro proporcionado, de tipo long
	 */
	public static long potenciaBase2RV1(long n) {
//		doNothing();
		
		long result = 1;		// caso básico y almacenamiento del resultado
		
		if (n > 0) {
			result = potenciaBase2RV1(n - 1) * 2;		// recursividad
		}
		
		return result;			// se devuelve el resultado
	}
	
	/**
	 * Versión 2 de la potencia en base 2, cuyo caso básico corresponde al
	 * exponente de valor 0 y el resto de casos trabajan con 2^(n-1) + 2^(n-1)
	 * @param n exponente con el que se calculará el valor, de tipo long
	 * @return 1 si n vale 0, 2 ^ (n - 1) + 2 ^ (n - 1) en otro caso
	 */
	public static long potenciaBase2RV2(long n) {
//		doNothing();
		
		long result = 1;		// caso básico y almacenamiento del resultado
	
		if (n > 0) {
			result = potenciaBase2RV2(n - 1) + potenciaBase2RV2(n - 1);
		}
		
		return result;			// se devuelve el resultado
	}

	/**
	 * Versión 3 de la potencia en base 2, cuyo caso básico corresponde al
	 * exponente de valor 0. En casos generales, si el exponente es par,
	 * trabajará con 2 ^ (n / 2) * 2 ^ (n / 2). Si no, se mult. lo anterior por 2.
	 * @param n exponente con el que se calculará el valor, de tipo long
	 * @return 1 si n vale 0, 2 ^ (n / 2) * 2 ^ (n / 2) en otro caso (si par)
	 * 		   y 2 ^ (n / 2) * 2 ^ (n / 2) * 2 si es impar
	 */
	public static long potenciaBase2RV3(long n) {
//		doNothing();
		
		long result = 1;		// caso básico y almacenamiento del resultado
		
		// Caso general
		if (n > 0) {
			// Exponente par
			if (n % 2 == 0) {
				result = potenciaBase2RV3(n / 2) * potenciaBase2RV3(n / 2);
			// Exponente impar
			} else {
				result = potenciaBase2RV3(n / 2) * potenciaBase2RV3(n / 2) * 2;
			}
		}
		
		return result;		    // se devuelve el resultado
	}
	
	/**
	 * Versión 4 de la potencia en base 2, cuyo caso básico corresponde al
	 * exponente de valor 0. En casos generales, se declara una variable
	 * auxiliar en memoria. Si el exponente es par, trabajará 
	 * con 2 ^ (n / 2) * 2 ^ (n / 2). Si no, se multiplica lo anterior por 2.
	 * @param n exponente con el que se calculará el valor, de tipo long
	 * @return 1 si n vale 0, 2 ^ (n / 2) * 2 ^ (n / 2) en otro caso (si par)
	 * 		   y 2 ^ (n / 2) * 2 ^ (n / 2) * 2 si es impar
	 */
	public static long potenciaBase2RV4(long n) {
//		doNothing();
		
		if (n == 0) {		    // caso básico
			return 1;
		}
		
		long result = potenciaBase2RV4(n / 2);
		
		// Caso general
		if (n % 2 == 0) {
			// Exponente par
			return (result * result);
		} else {
			// Exponente impar
			return (result * result * 2);
		}
	}
	
	/**
	 * Calcula, de forma iterativa, el factorial del 
	 * número pasado como parámetro y lo devuelve
	 * @param n número del que se calcula el factorial, de tipo long
	 * @return Factorial del parámetro proporcionado, de tipo long
	 */
	public static long factorialIt(long n) {
		long result = 1;		// caso básico y almacenamiento del resultado
		
		// Iteraciones para calcular el resultado
		for (long i = n; i > 0; i--) {
			result *= i;
		}
		
		return result;
	}
	
	/**
	 * Calcula, de forma recursiva, el factorial del 
	 * número pasado como parámetro y lo devuelve
	 * @param n número del que se calcula el factorial, de tipo long
	 * @return Factorial del parámetro proporcionado, de tipo long
	 */
	public static long factorialR(long n) {
		long result = 1;		// caso básico y almacenamiento del resultado
		
		if (n > 1) {
			result = factorialR(n - 1) * n;		// recursividad
		}
		
		return result;
	}

	/**
	 * Calcula, de forma iterativa, la sucesión de Fibonacci del
	 * número pasado como parámetro y devuelve el resultado
	 * @param n número del que se calcula la sucesión de Fibonacci, de tipo long
	 * @return Resultado de la sucesión de Fibonacci del parámetro proporcionado
	 */
	public static long fibonacciIt(long n) {
		long result = 0; 	// variable local para almacenar el resultado
		long primero = 0;	// almacenaría el valor (n - 2)
		long anterior = 1;	// almacenaría el valor (n - 1)
		
		if (n == 1) {
			result = 1;
		} else if (n > 1) {
			for (int i = 2; i <= n; i++) {
				result = primero + anterior; // se realiza la suma
				primero = anterior;			 // se igualan los términos
				anterior = result;			 // se incrementa el (n - 1)
			}
		}
		
		return result; // se devuelve el resultado, sea caso básico o calculado
	}

	/**
	 * Calcula, de forma recursiva, la sucesión de Fibonacci del
	 * número pasado como parámetro y devuelve el resultado
	 * @param n número del que se calcula la sucesión de Fibonacci, de tipo long
	 * @return Resultado de la sucesión de Fibonacci del parámetro proporcionado
	 */
	public static long fibonacciR(int n) {
		long result = 0;		// variable local para almacenar el resultado
		
		if (n == 1) {
			result = 1;		    // caso básico
		} else if (n > 1) {
			result = fibonacciR(n - 1) + fibonacciR(n - 2); // recursividad
		}
		
		return result; 		    // se devuelve el resultado calculado
	}
	
	/**
	 * Algoritmo que recorre un bucle for (complejidad O(n))
	 * @param n condición de parada, de tipo long
	 */
	public static void lineal(long n) {
		for (int i = 0; i < n; i++) {
			doNothing();
		}
	}
	
	/**
	 * Algoritmo que recorre dos bucles for anidados (complejidad O(n^2))
	 * @param n condición de parada, de tipo long
	 */
	public static void cuadratico(long n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				doNothing();
			}
		}
	}
	
	/**
	 * Algoritmo que recorre tres bucles for anidados (complejidad O(n^3))
	 * @param n condición de parada, de tipo long
	 */
	public static void cubico(long n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					doNothing();
				}
			}
		}
	}
	
	/**
	 * Algoritmo que recorre un bucle logarítmico (complejidad O(log2(n))
	 * @param n condición de parada, de tipo long
	 */
	public static void logaritmico(long n) {
		for (int i = 1; i < n; i *= 2) {
			doNothing();
		}
	}
	
	/**
	 * Método que espera una determinada cantidad de tiempo (indicada
	 * por la constante TIEMPO) para aportar mayor significación
	 * a la medida del tiempo de ejecución de los métodos
	 */
	public static void doNothing() {
		try {
			Thread.sleep(TIEMPO);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
}