// Paquete al que pertenece la clase
package monticulos;

// Importación de clases
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import monticulos.BinaryHeap;

/**
 * Pruebas unitarias de la clase BinaryHeap
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class BinaryHeapTest {
	
	private BinaryHeap<Integer> heap;	// montículo de prueba unitaria
	
	/**
	 * Inicializa un montículo de mínimos para las pruebas unitarias,
	 * y supone dos casos de pruebas unitarias para el método isEmpty()
	 */
	@Before
	public void setUp() {
		heap = new BinaryHeap<Integer>(10);
		
		// Se comprueba que el montículo se inicializa vacío
		assertTrue(heap.isEmpty());
		assertNull(heap.getTop());

		Integer[] nodos = new Integer[] { 12, 14, 15, 20, 16, 17, 19, 24 };
		
		// Inserción de elementos
		for (Integer i : nodos) {
			heap.add(i);
		}
		
		// Se comprueba el orden de inserción de elementos
		assertFalse(heap.isEmpty());
		assertEquals("12\t14\t15\t20\t16\t17\t19\t24", heap.toString());
	}

	/**
	 * Pruebas unitarias del método add()
	 */
	@Test
	public void testAdd() {	
		// Caso 1: Se añade un elemento null
		assertEquals(-2, heap.add(null));
		
		// Caso 2: Se intenta añadir un elemento ya existente
		assertEquals(-1, heap.add(15));
		
		// Caso 3: Se inserta correctamente y se produce un solo intercambio
		assertEquals(0, heap.add(18));
		assertEquals("12\t14\t15\t18\t16\t17\t19\t24\t20", heap.toString());
		
		// Caso 4: Se inserta correctamente y se produce más de un intercambio
		assertEquals(0, heap.add(1));
		assertEquals("1\t12\t15\t18\t14\t17\t19\t24\t20\t16", heap.toString());
		
		// Caso 5: Se inserta un elemento y el montículo ya está lleno
		assertEquals(-3, heap.add(10));
	}
	
	/**
	 * Pruebas unitarias del método getTop()
	 */
	@Test
	public void testGetTop() {
		assertEquals(0, heap.add(30));
		
		// Caso 1: Se saca la raíz en un montículo no vacío
		assertEquals("12", heap.getTop().toString());
		assertEquals("14\t16\t15\t20\t30\t17\t19\t24", heap.toString());
		
		// Caso 2: Se saca la raíz en un montículo vacío
		heap.clear();
		assertNull(heap.getTop());
	}
	
	/**
	 * Pruebas unitarias del método remove()
	 */
	@Test
	public void testRemove() {
		heap.add(30);
		
		// Caso 1: Se elimina un elemento que es null
		assertEquals(-2, heap.remove(null));
		
		// Caso 2: Se intenta eliminar de un montículo vacío
		BinaryHeap<Integer> empty = new BinaryHeap<Integer>(1);
		assertEquals(-2, empty.remove(5));
		
		// Caso 3: Se intenta eliminar un elemento que no existe
		assertEquals(-1, heap.remove(25));
		
		// Caso 4: Se elimina un elemento con éxito (produce un solo intercambio)
		assertEquals(0, heap.remove(15));
		assertEquals("12\t14\t17\t20\t16\t30\t19\t24", heap.toString());
		
		// Caso 5: Se elimina un elemento con éxito (produce más de un intercambio)
		assertEquals(0, heap.remove(12));
		assertEquals("14\t16\t17\t20\t24\t30\t19", heap.toString());
	}

	/**
	 * Pruebas unitarias del método cambiarPrioridad()
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
		
		// Caso 3: Se pasa como parámetro la misma prioridad
		heap.cambiarPrioridad(2, 15);
		assertEquals("5\t14\t15\t24\t16\t17\t19\t40\t30", heap.toString());
		
		// Caso 4: Se pasa como parámetro un elemento null
		assertEquals(-1, heap.cambiarPrioridad(2, null));
		
		// Caso 5: Se pasa como parámetro un elemento ya existente
		assertEquals(-1, heap.cambiarPrioridad(3, 14));
		
		// Caso 6: Se pasa como parámetro una posición negativa
		assertEquals(-2, heap.cambiarPrioridad(-7, 20));
		
		// Caso 7: Se pasa como parámetro una posición fuera de rango
		assertEquals(-2, heap.cambiarPrioridad(9, 50));
	}
	
	/**
	 * Pruebas unitarias del método clear()
	 */
	@Test
	public void testClear() {
		// Caso 1: El montículo no está vacío
		assertFalse(heap.isEmpty());
		heap.clear();
		assertTrue(heap.isEmpty());
		
		// Caso 2: El montículo ya estaba vacío
		heap.clear();
		assertTrue(heap.isEmpty());
	}

}