// Paquete al que pertenece la clase
package logic;

// Importación de clases
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test de la clase Grafo
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class GrafoTest {

	/**
	 * Pruebas unitarias del método addNode()
	 */
	@Test
	public void testAddNode() {
		Grafo<Integer> grafo = new Grafo<Integer>(5);
		
		// Caso 1: Se añade un nodo correctamente
		assertEquals(0, grafo.addNode(0));
		
		// Caso 2: Se intenta añadir un nodo ya existente en el grafo
		assertEquals(-1, grafo.addNode(0));
		
		// Caso 3: Se llena el grafo y se intenta añadir uno más
		grafo.addNode(1);
		grafo.addNode(2);
		grafo.addNode(3);
		grafo.addNode(4);
		
		assertEquals(-2, grafo.addNode(5));
		
		// Caso 4: Se intenta añadir un nodo null
		assertEquals(-3, grafo.addNode(null));
	}
	
	/**
	 * Pruebas unitarias del método getNode()
	 */
	@Test
	public void testGetNode() {
		Grafo<Integer> grafo = new Grafo<Integer>(5);
		
		// Caso 1: El parámetro es null
		assertEquals(-1, grafo.getNode(null));
		
		// Caso 2: El nodo no existe en el grafo
		assertEquals(-1, grafo.getNode(0));
		
		// Caso 3: El nodo existe en el grafo
		grafo.addNode(1);
		assertEquals(0, grafo.getNode(1));
	}
	
	/**
	 * Pruebas unitarias del método removeNode()
	 */
	@Test
	public void testRemoveNode() {
		Grafo<Integer> grafo = new Grafo<Integer>(3);
		
		// Caso 1: Se intenta eliminar un nodo no existente
		assertEquals(-1, grafo.removeNode(0));
		
		// Caso 2: Se elimina un nodo tras añadirlo
		grafo.addNode(1);
		assertEquals(0, grafo.removeNode(1));
		
		// Casp 3: Se intenta eliminar un nodo null
		assertEquals(-1, grafo.removeNode(null));
		
		// Caso 4: Se elimina un nodo que no es el último
		grafo.addNode(2);
		grafo.addNode(3);
		
		assertEquals(0, grafo.removeNode(2));
	}
	
	/**
	 * Pruebas unitarias del método existNode()
	 */
	@Test
	public void testExistNode() {
		Grafo<Integer> grafo = new Grafo<Integer>(2);
		
		// Caso 1: Se comprueba un nodo añadido al grafo
		grafo.addNode(0);
		assertTrue(grafo.existNode(0));
		
		// Caso 2: Se comprueba un nodo que no está en el grafo
		assertFalse(grafo.existNode(7));
		
		// Caso 3: Se comprueba un nodo que vale null
		assertFalse(grafo.existNode(null));
	}
	
	/**
	 * Pruebas unitarias del método getEdge()
	 */
	@Test
	public void testGetEdge() {
		Grafo<Integer> grafo = new Grafo<Integer>(2);
		
		// Caso 1: Existe fuente y existe destino (sin arista)
		grafo.addNode(0);
		grafo.addNode(1);
		
		assertEquals(-4, grafo.getEdge(0, 1), 0.1);
		
		// Caso 2: No existe fuente y existe destino
		assertEquals(-1, grafo.getEdge(4, 0), 0.1);
		
		// Caso 3: Existe fuente y no existe destino
		assertEquals(-2, grafo.getEdge(1, 5), 0.1);
		
		// Caso 4: No existe fuente y no existe destino
		assertEquals(-3, grafo.getEdge(4, 5), 0.1);
		
		// Caso 5: Existe fuente y existe destino (con arista)
		grafo.addEdge(0, 1, 3);
		
		assertEquals(3, grafo.getEdge(0, 1), 0.1);
	}
	
	/**
	 * Pruebas unitarias del método existEdge()
	 */
	@Test
	public void testExistEdge() {
		Grafo<Integer> grafo = new Grafo<Integer>(3);
		
		grafo.addNode(0);
		grafo.addNode(1);
		grafo.addNode(2);
		
		// Caso 1: Existen fuente y destino (y hay arista)
		grafo.addEdge(0, 1, 1);
		
		assertTrue(grafo.existEdge(0, 1));
		
		// Caso 2: Existen fuente y destino (y no hay arista)
		assertFalse(grafo.existEdge(0, 2));
		
		// Caso 3: No existe fuente
		assertFalse(grafo.existEdge(3, 0));
		
		// Caso 4: No existe destino
		assertFalse(grafo.existEdge(4, 1));
		
		// Caso 5: No existen fuente ni destino
		assertFalse(grafo.existEdge(5, 6));
	}
	
	/**
	 * Pruebas unitarias del método addEdge()
	 */
	@Test
	public void testAddEdge() {
		Grafo<Integer> grafo = new Grafo<Integer>(3);
		
		grafo.addNode(0);
		grafo.addNode(1);
		grafo.addNode(2);
		
		// Caso 1: No existe nodo origen
		assertEquals(-1, grafo.addEdge(7, 0, 4));
		
		// Caso 2: No existe nodo destino
		assertEquals(-2, grafo.addEdge(2, 5, 1));
		
		// Caso 3: No existe origen ni destino
		assertEquals(-3, grafo.addEdge(3, 6, 2));
		
		// Caso 4: El peso pasado como parámetro es negativo
		assertEquals(-5, grafo.addEdge(0, 1, -3));
		
		// Caso 5: La arista ya existe
		grafo.addEdge(0, 1, 5);
		
		assertEquals(-4, grafo.addEdge(0, 1, 0));
	}
	
	/**
	 * Pruebas unitarias del método removeEdge()
	 */
	@Test
	public void testRemoveEdge() {
		Grafo<Integer> grafo = new Grafo<Integer>(3);
		
		grafo.addNode(1);
		
		// Caso 1: No existe origen
		assertEquals(-1, grafo.removeEdge(0, 1));
		
		// Caso 2: No existe destino
		assertEquals(-2, grafo.removeEdge(1, 2));
		
		// Caso 3: No existe destino ni origen
		assertEquals(-3, grafo.removeEdge(3, 4));
		
		// Caso 4: No existe la arista entre nodos existentes
		grafo.addNode(0);
		
		assertEquals(-4, grafo.removeEdge(0, 1));
		
		// Caso 5: Sí existe la arista entre nodos existentes
		grafo.addEdge(0, 1, 3);
		
		assertEquals(0, grafo.removeEdge(0, 1));
	}
	
	/**
	 * Pruebas unitarias del método dijkstra()
	 */
	@Test
	public void testDijkstra() {
		Grafo<Integer> grafo1 = new Grafo<Integer>(6);
		
		// Se añaden los nodos
		grafo1.addNode(1);
		grafo1.addNode(2);
		grafo1.addNode(3);
		grafo1.addNode(4);
		grafo1.addNode(5);
		grafo1.addNode(6);
		
		// Caso 1: Se intenta hacer Dijkstra sin aristas
		assertArrayEquals(new double[] {0.0, Double.POSITIVE_INFINITY, 
				Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 
				Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY}, 
				grafo1.dijkstra(1), 0.0);
		
		// Caso 2: Se añaden las aristas con un ejemplos de las diapositivas
		grafo1.addEdge(1, 1, 1); // ciclo
		grafo1.addEdge(1, 2, 1);
		grafo1.addEdge(1, 5, 10);
		grafo1.addEdge(2, 3, 5);
		grafo1.addEdge(3, 5, 1);
		grafo1.addEdge(4, 3, 2);
		grafo1.addEdge(1, 4, 3);
		grafo1.addEdge(4, 5, 6);
		
		// Se prueban los costes mínimos partiendo de V1
		assertArrayEquals(new double[] {0.0, 1, 5, 3, 6, Double.POSITIVE_INFINITY}, 
				grafo1.dijkstra(1), 0.0);
		
		// Otro caso (grafo 2 de las diapositivas de Dijkstra)
		Grafo<Integer> grafo2 = new Grafo<Integer>(6);
		
		// Se añaden los nodos
		grafo2.addNode(1);
		grafo2.addNode(2);
		grafo2.addNode(3);
		grafo2.addNode(4);
		grafo2.addNode(5);
		grafo2.addNode(6);
		
		// Se añaden las aristas
		grafo2.addEdge(1, 2, 3);
		grafo2.addEdge(1, 3, 4);
		grafo2.addEdge(1, 5, 8);
		grafo2.addEdge(2, 5, 5);
		grafo2.addEdge(3, 5, 3);
		grafo2.addEdge(5, 4, 7);
		grafo2.addEdge(5, 6, 3);
		grafo2.addEdge(6, 4, 2);
		
		// Se comprueba el resultado de Dijkstra partiendo del nodo B
		assertArrayEquals(new double[] {0.0, 3, 4, 12, 7, 10}, 
				grafo2.dijkstra(1), 0.0);
		
		// Otro caso (de las diapositivas de Seminario)
		Grafo<Character> grafo3 = new Grafo<Character>(8);
		
		// Se añaden los nodos
		grafo3.addNode('A');
		grafo3.addNode('B');
		grafo3.addNode('C');
		grafo3.addNode('D');
		grafo3.addNode('E');
		grafo3.addNode('F');
		grafo3.addNode('G');
		grafo3.addNode('H');
		
		// Se añaden las aristas
		grafo3.addEdge('A', 'B', 1);
		grafo3.addEdge('B', 'A', 9);
		grafo3.addEdge('A', 'E', 7);
		grafo3.addEdge('B', 'C', 3);
		grafo3.addEdge('C', 'G', 3);
		grafo3.addEdge('B', 'F', 9);
		grafo3.addEdge('D', 'F', 4);
		grafo3.addEdge('D', 'H', 9);
		grafo3.addEdge('H', 'F', 8);
		grafo3.addEdge('E', 'G', 6);
		grafo3.addEdge('G', 'E', 4);
		grafo3.addEdge('G', 'H', 8);
		grafo3.addEdge('E', 'H', 9);
		
		// Se comprueba el resultado de Dijkstra partiendo del nodo B
		assertArrayEquals(new double[] {9, 0.0, 3, Double.POSITIVE_INFINITY, 
				10, 9, 6, 14}, grafo3.dijkstra('B'), 0.1);
	}
	
	/**
	 * Pruebas unitarias del método floyd()
	 */
	@Test
	public void testFloyd() {
		Grafo<Integer> grafo1 = new Grafo<Integer>(5);
		
		// Caso 1 (negativo): No hay nodos añadidos (no se puede aplicar Floyd)
		assertEquals(0, grafo1.getTam());
		assertEquals(-1, grafo1.floyd());
		
		// Caso 2 (positivo): Sí hay nodos añadidos (se puede aplicar Floyd)
		
		// Se añaden los nodos
		grafo1.addNode(1);
		grafo1.addNode(2);
		grafo1.addNode(3);
		grafo1.addNode(4);
		grafo1.addNode(5);
		
		// Se añaden las aristas
		grafo1.addEdge(1, 2, 1);
		grafo1.addEdge(1, 5, 10);
		grafo1.addEdge(2, 3, 5);
		grafo1.addEdge(3, 5, 1);
		grafo1.addEdge(4, 3, 2);
		grafo1.addEdge(1, 4, 3);
		grafo1.addEdge(4, 5, 6);
		
		// Se ejecuta el algoritmo de Floyd
		grafo1.floyd();
		
		// Se compara el resultado con la matriz A
		assertArrayEquals(new double[][] 
			{{0, 1, 5, 3, 6},
			{Double.POSITIVE_INFINITY, 0, 5, Double.POSITIVE_INFINITY, 6},
			{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0, Double.POSITIVE_INFINITY, 1},
			{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 2, 0, 3},
			{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0}}, 
			grafo1.getAFloyd());
		
	}
	
	/**
	 * Pruebas unitarias del método getAFloyd()
	 */
	@Test
	public void testGetAFloyd() {
		Grafo<Integer> grafo = new Grafo<Integer>(3);
		
		grafo.addNode(1);
		grafo.addNode(2);
		grafo.addNode(3);
		
		// Caso 1: No se ha ejecutado Floyd
		assertNull(grafo.getAFloyd());
		
		// Caso 2: Tras ejecutar Floyd
		grafo.floyd();
		assertArrayEquals(new double[][] {
			{0, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
			{Double.POSITIVE_INFINITY, 0, Double.POSITIVE_INFINITY},
			{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0}}, grafo.getAFloyd());
	}
	
	/**
	 * Pruebas unitarias del método getPFloyd()
	 */
	@Test
	public void testGetPFloyd() {
		Grafo<Integer> grafo = new Grafo<Integer>(3);
		
		grafo.addNode(1);
		grafo.addNode(2);
		grafo.addNode(3);
		
		// Caso 1: No se ha ejecutado Floyd
		assertNull(grafo.getPFloyd());
		
		// Caso 2: Tras ejecutar Floyd
		grafo.floyd();
		assertNotNull(grafo.getPFloyd());
		assertArrayEquals(new Object[][] {
			{null, null, null},
			{null, null, null},
			{null, null, null}}, grafo.getPFloyd());
	}
	
	/**
	 * Pruebas unitarias del método minCostPath()
	 */
	@Test
	public void testMinCostPath() {
		Grafo<Integer> grafo1 = new Grafo<Integer>(5);
		
		// Se añaden los nodos
		grafo1.addNode(1);
		grafo1.addNode(2);
		grafo1.addNode(3);
		grafo1.addNode(4);
		grafo1.addNode(5);
		
		// Se añaden las aristas
		grafo1.addEdge(1, 2, 1);
		grafo1.addEdge(1, 5, 10);
		grafo1.addEdge(2, 3, 5);
		grafo1.addEdge(3, 5, 1);
		grafo1.addEdge(4, 3, 2);
		grafo1.addEdge(1, 4, 3);
		grafo1.addEdge(4, 5, 6);
		
		// Caso 1: No existe origen
		assertEquals(-1, grafo1.minCostPath(6, 1), 0.1);
		
		// Caso 2: No existe destino
		assertEquals(-1, grafo1.minCostPath(2, 7), 0.1);
		
		// Caso 3: Existe origen y destino con arista, pero no se ejecutó Floyd
		assertNull(grafo1.getAFloyd());
		assertNull(grafo1.getPFloyd());
		
		assertEquals(grafo1.getEdge(1, 5),
				grafo1.minCostPath(1, 5), 0.1);
		
		// Se comprueba que se hayan inicializado A y P
		assertNotNull(grafo1.getAFloyd());
		assertNotNull(grafo1.getPFloyd());
		
		// Caso 4: Existe origen y destino, pero no la arista
		assertFalse(grafo1.existEdge(3, 6));
		assertEquals(-1, grafo1.minCostPath(3, 6), 0.1);
		
		// Caso 5: Existe origen y destino con arista, y se ejecuta Floyd
		grafo1.floyd();
		assertEquals(grafo1.getAFloyd()[grafo1.getNode(1)][grafo1.getNode(5)], 
				grafo1.minCostPath(1, 5), 0.1);
		
	}
	
	/**
	 * Pruebas unitarias del método path()
	 */
	@Test
	public void testPath() {
		
		// Grafo del Ejercicio 2 de las diapositivas
		
		Grafo<Integer> grafo1 = new Grafo<Integer>(5);
		
		// Se añaden los nodos
		grafo1.addNode(1);
		grafo1.addNode(2);
		grafo1.addNode(3);
		grafo1.addNode(4);
		grafo1.addNode(5);
		
		// Se añaden las aristas
		grafo1.addEdge(1, 2, 1);
		grafo1.addEdge(1, 5, 10);
		grafo1.addEdge(2, 3, 5);
		grafo1.addEdge(3, 5, 1);
		grafo1.addEdge(4, 3, 2);
		grafo1.addEdge(1, 4, 3);
		grafo1.addEdge(4, 5, 6);
		
		// Se ejecuta el algoritmo de Floyd
		grafo1.floyd();
		
		// Caso 1: No existe origen
		assertEquals("", grafo1.path(6, 1));
		
		// Caso 2: No existe destino
		assertEquals("", grafo1.path(3, 8));
		
		// Caso 3: No existe origen ni destino
		assertEquals("", grafo1.path(10, 14));
		
		// Caso 4: Origen y destino coinciden
		assertEquals("2", grafo1.path(2, 2));
		
		// Caso 5: No hay camino entre origen y destino
		assertEquals("5\tInfinity\t1", grafo1.path(5, 1));
		
		// Caso 6: Hay camino entre origen y destino
		assertEquals("1\t3.0\t4\t2.0\t3\t1.0\t5", grafo1.path(1, 5));
		
		// Prueba particular del Caso 6 con otro grafo (Ejercicio 1)
		
		Grafo<Integer> grafo2 = new Grafo<Integer>(6);
		
		// Se añaden los nodos
		grafo2.addNode(1);
		grafo2.addNode(2);
		grafo2.addNode(3);
		grafo2.addNode(4);
		grafo2.addNode(5);
		grafo2.addNode(6);
		
		// Se añaden las aristas
		grafo2.addEdge(1, 2, 3);
		grafo2.addEdge(1, 3, 4);
		grafo2.addEdge(1, 5, 8);
		grafo2.addEdge(2, 5, 5);
		grafo2.addEdge(3, 5, 3);
		grafo2.addEdge(5, 4, 7);
		grafo2.addEdge(5, 6, 3);
		grafo2.addEdge(6, 4, 2);
		
		// Se ejecuta el algoritmo de Floyd
		grafo2.floyd();
		
		// Se prueba el camino de V5 a V4 (debería pasar por V6)
		assertEquals("5\t3.0\t6\t2.0\t4", grafo2.path(5, 4));
	}
	
	/**
	 * Pruebas unitarias del método recorridoProfundidad()
	 */
	@Test
	public void testRecorridoProfundidad() {
		Grafo<Integer> grafo = new Grafo<Integer>(4);
		
		// Se añaden los nodos
		grafo.addNode(1);
		grafo.addNode(2);
		grafo.addNode(3);
		grafo.addNode(4);
		
		// Se añaden las aristas
		grafo.addEdge(1, 2, 2);
		grafo.addEdge(1, 3, 1);
		grafo.addEdge(2, 4, 3);
		grafo.addEdge(3, 2, 4);
		grafo.addEdge(4, 3, 6);
		grafo.addEdge(4, 4, 5);
		
		// Caso 1: No existe el nodo origen
		assertEquals("", grafo.recorridoProfundidad(5));
		
		// Caso 2: Existe el nodo origen (y se produce recursividad)
		assertEquals("1\t2\t4\t3", grafo.recorridoProfundidad(1));
		
		// Añadimos otro grafo más complejo para contemplar otros casos
		
		Grafo<Integer> grafo2 = new Grafo<Integer>(6);
		
		// Se añaden los nodos
		grafo2.addNode(1);
		grafo2.addNode(2);
		grafo2.addNode(3);
		grafo2.addNode(4);
		grafo2.addNode(5);
		grafo2.addNode(6);
		
		// Se añaden las aristas
		grafo2.addEdge(1, 2, 3);
		grafo2.addEdge(1, 3, 4);
		grafo2.addEdge(1, 5, 8);
		grafo2.addEdge(2, 5, 5);
		grafo2.addEdge(3, 5, 3);
		grafo2.addEdge(5, 4, 7);
		grafo2.addEdge(5, 6, 3);
		grafo2.addEdge(6, 4, 2);
		
		// Caso 3: El nodo origen es un sumidero (no tiene "hijos")
		assertEquals("4", grafo2.recorridoProfundidad(4));
		
		// Caso 4: Desde el nodo origen se recorre todo el grafo
		assertEquals("1\t2\t5\t4\t6\t3", grafo2.recorridoProfundidad(1));
	}
}