// Paquete al que pertenece la clase
package monticulos;

// Importaci�n de clases
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import monticulos.BinaryHeap;

/**
 * Pruebas unitarias de la clase BinaryHeap
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class BinaryHeapTest {
	
	private BinaryHeap<Integer> heap;	// mont�culo de prueba unitaria
	
	/**
	 * Inicializa un mont�culo de m�nimos para las pruebas unitarias,
	 * y supone dos casos de pruebas unitarias para el m�todo isEmpty()
	 */
	@Before
	public void setUp() {
		heap = new BinaryHeap<Integer>(10);
		
		// Se comprueba que el mont�culo se inicializa vac�o
		assertTrue(heap.isEmpty());
		assertNull(heap.getTop());

		Integer[] nodos = new Integer[] { 12, 14, 15, 20, 16, 17, 19, 24 };
		
		// Inserci�n de elementos
		for (Integer i : nodos) {
			heap.add(i);
		}
		
		// Se comprueba el orden de inserci�n de elementos
		assertFalse(heap.isEmpty());
		assertEquals("12\t14\t15\t20\t16\t17\t19\t24", heap.toString());
	}

	/**
	 * Pruebas unitarias del m�todo add()
	 */
	@Test
	public void testAdd() {	
		// Caso 1: Se a�ade un elemento null
		assertEquals(-2, heap.add(null));
		
		// Caso 2: Se intenta a�adir un elemento ya existente
		assertEquals(-1, heap.add(15));
		
		// Caso 3: Se inserta correctamente y se produce un solo intercambio
		assertEquals(0, heap.add(18));
		assertEquals("12\t14\t15\t18\t16\t17\t19\t24\t20", heap.toString());
		
		// Caso 4: Se inserta correctamente y se produce m�s de un intercambio
		assertEquals(0, heap.add(1));
		assertEquals("1\t12\t15\t18\t14\t17\t19\t24\t20\t16", heap.toString());
		
		// Caso 5: Se inserta un elemento y el mont�culo ya est� lleno
		assertEquals(-3, heap.add(10));
	}
	
	/**
	 * Pruebas unitarias del m�todo getTop()
	 */
	@Test
	public void testGetTop() {
		assertEquals(0, heap.add(30));
		
		// Caso 1: Se saca la ra�z en un mont�culo no vac�o
		assertEquals("12", heap.getTop().toString());
		assertEquals("14\t16\t15\t20\t30\t17\t19\t24", heap.toString());
		
		// Caso 2: Se saca la ra�z en un mont�culo vac�o
		heap.clear();
		assertNull(heap.getTop());
	}
	
	/**
	 * Pruebas unitarias del m�todo remove()
	 */
	@Test
	public void testRemove() {
		heap.add(30);
		
		// Caso 1: Se elimina un elemento que es null
		assertEquals(-2, heap.remove(null));
		
		// Caso 2: Se intenta eliminar de un mont�culo vac�o
		BinaryHeap<Integer> empty = new BinaryHeap<Integer>(1);
		assertEquals(-2, empty.remove(5));
		
		// Caso 3: Se intenta eliminar un elemento que no existe
		assertEquals(-1, heap.remove(25));
		
		// Caso 4: Se elimina un elemento con �xito (produce un solo intercambio)
		assertEquals(0, heap.remove(15));
		assertEquals("12\t14\t17\t20\t16\t30\t19\t24", heap.toString());
		
		// Caso 5: Se elimina un elemento con �xito (produce m�s de un intercambio)
		assertEquals(0, heap.remove(12));
		assertEquals("14\t16\t17\t20\t24\t30\t19", heap.toString());
	}

	/**
	 * Pruebas unitarias del m�todo cambiarPrioridad()
	 */
	@Test
	public void testCambiarPrioridad() {
		heap.add(30);
		
		// Caso 1: cambiar prioridad de 20 a 5 (pasa a tener menor valor)
		heap.cambiarPrioridad(3, 5);
		assertEquals("5\t12\t15\t14\t16\t17\t19\t24\t30", heap.toString());
		
		// Caso 2: cambiar prioridad de 12 a 40 (pasa a tener mayor valor)
		heap.cambiarPrioridad(1, 40);
		assertEquals("5\t14\t15\t24\t16\t17\t19\t40\t30", heap.toString());
		
		// Caso 3: Se pasa como par�metro la misma prioridad
		heap.cambiarPrioridad(2, 15);
		assertEquals("5\t14\t15\t24\t16\t17\t19\t40\t30", heap.toString());
		
		// Caso 4: Se pasa como par�metro un elemento null
		assertEquals(-1, heap.cambiarPrioridad(2, null));
		
		// Caso 5: Se pasa como par�metro un elemento ya existente
		assertEquals(-1, heap.cambiarPrioridad(3, 14));
		
		// Caso 6: Se pasa como par�metro una posici�n negativa
		assertEquals(-2, heap.cambiarPrioridad(-7, 20));
		
		// Caso 7: Se pasa como par�metro una posici�n fuera de rango
		assertEquals(-2, heap.cambiarPrioridad(9, 50));
	}
	
	/**
	 * Pruebas unitarias del m�todo clear()
	 */
	@Test
	public void testClear() {
		// Caso 1: El mont�culo no est� vac�o
		assertFalse(heap.isEmpty());
		heap.clear();
		assertTrue(heap.isEmpty());
		
		// Caso 2: El mont�culo ya estaba vac�o
		heap.clear();
		assertTrue(heap.isEmpty());
	}

}