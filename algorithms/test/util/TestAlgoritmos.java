// Paquete al que pertenece la clase
package util;

// Importación de clases
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Clase de prueba para Algoritmos
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class TestAlgoritmos {

	// ------------------- Pruebas de potencia en base 2 ----------------------
	//                             (Versión 1)
	
	/**
	 * Caso 1 (iterativo): Caso básico (n = 0)
	 */
	@Test
	public void testPotenciaBase2ItBasic() {
		assertEquals(1, Algoritmos.potenciaBase2It(0));
	}
	
	/**
	 * Caso 2 (iterativo): Caso con un valor intermedio mínimo
	 */
	public void testPotenciaBase2ItMin() {
		assertEquals(2, Algoritmos.potenciaBase2It(1));
	}
	
	/**
	 * Caso 3 (iterativo): Caso normal intermedio
	 */
	@Test
	public void testPotenciaBase2ItMedium() {
		assertEquals(32, Algoritmos.potenciaBase2It(5));
	}
	
	/**
	 * Caso 4 (iterativo): Valor muy grande
	 */
	@Test
	public void testPotenciaBase2ItHuge() {
		assertEquals(281474976710656L, Algoritmos.potenciaBase2It(48));
	}
	
	/**
	 * Caso 1 (recursivo): Caso básico (n = 0)
	 */
	@Test
	public void testPotenciaBase2RV1Basic() {
		assertEquals(1, Algoritmos.potenciaBase2RV1(0));
	}
	
	/**
	 * Caso 2 (recursivo): Caso con un valor intermedio mínimo 
	 */
	@Test
	public void testPotenciaBase2RV1Min() {
		assertEquals(2, Algoritmos.potenciaBase2RV1(1));
	}
	
	/**
	 * Caso 3 (recursivo): Caso normal intermedio
	 */
	@Test
	public void testPotenciaBase2RV1Medium() {
		assertEquals(64, Algoritmos.potenciaBase2RV1(6));
	}
	
	/**
	 * Caso 4 (recursivo): Valor muy grande
	 */
	@Test
	public void testPotenciaBase2RV1Huge() {
		assertEquals(4611686018427387904L, Algoritmos.potenciaBase2RV1(62));
	}
	
	// ------------------- Pruebas de la Versión 2 ----------------------------
	
	/**
	 * Caso 1: Caso básico (n = 0)
	 */
	@Test
	public void testPotenciaBase2RV2Basic() {
		assertEquals(1, Algoritmos.potenciaBase2RV2(0));
	}
	
	/**
	 * Caso 2: Caso con un valor intermedio mínimo 
	 */
	@Test
	public void testPotenciaBase2RV2Min() {
		assertEquals(2, Algoritmos.potenciaBase2RV2(1));
	}
	
	/**
	 * Caso 3: Caso normal intermedio
	 */
	@Test
	public void testPotenciaBase2RV2Medium() {
		assertEquals(16, Algoritmos.potenciaBase2RV2(4));
	}
	
	/**
	 * Caso 4: Valor muy grande
	 */
	@Test
	public void testPotenciaBase2RV2Huge() {
		assertEquals(268435456, Algoritmos.potenciaBase2RV2(28));
	}
	
	// ------------------- Pruebas de la Versión 3 ----------------------------
	
	/**
	 * Caso 1: Caso básico (n = 0)
	 */
	@Test
	public void testPotenciaBase2RVBasic() {
		assertEquals(1, Algoritmos.potenciaBase2RV3(0));
	}
	
	/**
	 * Caso 2: Caso con un valor intermedio mínimo 
	 */
	@Test
	public void testPotenciaBase2RV3Min() {
		assertEquals(2, Algoritmos.potenciaBase2RV3(1));
	}
	
	/**
	 * Caso 3: Caso normal intermedio par
	 */
	@Test
	public void testPotenciaBase2RV3Par() {
		assertEquals(64, Algoritmos.potenciaBase2RV3(6));
	}
	
	/**
	 * Caso 4: Caso normal intermedio impar
	 */
	@Test
	public void testPotenciaBase2RV3Impar() {
		assertEquals(32, Algoritmos.potenciaBase2RV3(5));
	}
	
	/**
	 * Caso 5: Valor muy grande
	 */
	@Test
	public void testPotenciaBase2RV3Huge() {
		assertEquals(2199023255552L, Algoritmos.potenciaBase2RV3(41));
	}
	
	// ------------------- Pruebas de la Versión 4 ----------------------------
	
	/**
	 * Caso 1: Caso básico (n = 0)
	 */
	@Test
	public void testPotenciaBase2RV4Basic() {
		assertEquals(1, Algoritmos.potenciaBase2RV4(0));
	}
	
	/**
	 * Caso 2: Caso con un valor intermedio mínimo 
	 */
	@Test
	public void testPotenciaBase2RV4Min() {
		assertEquals(2, Algoritmos.potenciaBase2RV4(1));
	}
	
	/**
	 * Caso 3: Caso normal intermedio par
	 */
	@Test
	public void testPotenciaBase2RV4Par() {
		assertEquals(16, Algoritmos.potenciaBase2RV4(4));
	}
	
	/**
	 * Caso 4: Caso normal intermedio impar
	 */
	@Test
	public void testPotenciaBase2RV4Impar() {
		assertEquals(128, Algoritmos.potenciaBase2RV4(7));
	}
	
	/**
	 * Caso 5: Valor muy grande
	 */
	@Test
	public void testPotenciaBase2RV4Huge() {
		assertEquals(281474976710656L, Algoritmos.potenciaBase2RV4(48));
	}
	
	// --------------------- Pruebas del factorial ----------------------------
	
	/**
	 * Caso 1 (iterativo): Casos básicos
	 */
	@Test
	public void testFactorialItBasic() {
		assertEquals(1, Algoritmos.factorialIt(0));
		assertEquals(1, Algoritmos.factorialIt(1));
	}
	
	/**
	 * Caso 2 (iterativo): Valores normales intermedios
	 */
	@Test
	public void testFactorialItMedium() {
		assertEquals(120, Algoritmos.factorialIt(5));
		assertEquals(40320, Algoritmos.factorialIt(8));
	}
	
	/**
	 * Caso 3 (iterativo): Valor muy grande
	 */
	@Test
	public void testFactorialItHuge() {
		assertEquals(2432902008176640000L, Algoritmos.factorialIt(20));
	}
	
	/**
	 * Caso 1 (recursivo): Casos básicos
	 */
	@Test
	public void testFactorialRBasic() {
		assertEquals(1, Algoritmos.factorialR(0));
		assertEquals(1, Algoritmos.factorialR(1));
	}
	
	/**
	 * Caso 2 (recursivo): Valores normales intermedios
	 */
	@Test
	public void testFactorialRMedium() {
		assertEquals(24, Algoritmos.factorialR(4));
		assertEquals(720, Algoritmos.factorialR(6));
	}
	
	/**
	 * Caso 3 (recursivo): Valor muy grande
	 */
	@Test
	public void testFactorialRHuge() {
		assertEquals(2432902008176640000L, Algoritmos.factorialR(20));
	}
	
	// -------------------- Pruebas de Fibonacci ------------------------------
	
	/**
	 * Caso 1 (iterativo): Casos básicos (n = 0 y n = 1)
	 */
	@Test
	public void testFibonacciItBasic() {
		assertEquals(0, Algoritmos.fibonacciIt(0));
		assertEquals(1, Algoritmos.fibonacciIt(1));
	}
	
	/**
	 * Caso 2 (iterativo): Valor negativo
	 */
	@Test
	public void testFibonacciItNegative() {
		assertEquals(0, Algoritmos.fibonacciIt(-7));
	}
	
	/**
	 * Caso 3 (iterativo): Valor normal intermedio
	 */
	@Test
	public void testFibonacciItMedium() {
		assertEquals(34, Algoritmos.fibonacciIt(9));
	}
	
	/**
	 * Caso 4 (iterativo): Valor muy grande
	 */
	@Test
	public void testFibonacciItHuge() {
		assertEquals(23416728348467685.0, Algoritmos.fibonacciIt(80), 1);
	}
	
	/**
	 * Caso 1 (recursivo): Casos básicos (n = 0 y n = 1)
	 */
	@Test
	public void testFibonacciRBasic() {
		assertEquals(0, Algoritmos.fibonacciR(0));
		assertEquals(1, Algoritmos.fibonacciR(1));
	}
	
	/**
	 * Caso 2 (recursivo): Valor negativo
	 */
	@Test
	public void testFibonacciRNegative() {
		assertEquals(0, Algoritmos.fibonacciIt(-3));
	}
	
	/**
	 * Caso 3 (recursivo): Valor normal intermedio
	 */
	@Test
	public void testFibonacciRMedium() {
		assertEquals(3, Algoritmos.fibonacciR(4));
	}
	
	/**
	 * Caso 4 (recursivo): Valor muy grande
	 */
	@Test
	public void testFibonacciRHuge() {
		assertEquals(9227465, Algoritmos.fibonacciR(35));
	}
	
	// -------------------- Pruebas en clase ---------------------------------
	
	/**
	 * Pruebas de factorial solicitadas por la profesora en clase
	 */
	@Test
	public void testFactorialClase() {
		assertEquals(1, Algoritmos.factorialIt(0));
		assertEquals(1, Algoritmos.factorialR(0));
		assertEquals(1, Algoritmos.factorialIt(1));
		assertEquals(1, Algoritmos.factorialR(1));
		assertEquals(2, Algoritmos.factorialIt(2));
		assertEquals(2, Algoritmos.factorialR(2));
		assertEquals(6, Algoritmos.factorialIt(3));
		assertEquals(6, Algoritmos.factorialR(3));
		assertEquals(24, Algoritmos.factorialIt(4));
		assertEquals(24, Algoritmos.factorialR(4));
		assertEquals(120, Algoritmos.factorialIt(5));
		assertEquals(120, Algoritmos.factorialR(5));
		assertEquals(720, Algoritmos.factorialIt(6));
		assertEquals(720, Algoritmos.factorialR(6));
		assertEquals(1307674368000L, Algoritmos.factorialIt(15));
		assertEquals(1307674368000L, Algoritmos.factorialR(15));
	}
	
}