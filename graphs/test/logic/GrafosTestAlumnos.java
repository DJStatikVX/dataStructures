package logic;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GrafosTestAlumnos {

	@Test
	public void testAddNode() {
		// Creamos un vector de nodos con tamaño 2
		Grafo<Integer> graph = new Grafo<Integer>(2);
		
		// Caso 1 - Añadimos el nodo al vector
		
		Assert.assertEquals(graph.addNode(1), 0);
		Assert.assertEquals(graph.getNode(1), 0);
		Assert.assertTrue(graph.existNode(1));
		
		// Caso 3 - Añadimos un nodo que ya existe
		Assert.assertEquals(graph.addNode(1), -1);
		
		graph.addNode(2);
		// Caso 4 - Añadimos un nodo al vector que ya se encuentra lleno
		Assert.assertEquals(graph.addNode(3), -2);	
		System.out.print("Grafo completo-->"+graph.toString());
	}

	@Test
	public void testGetNode() {
		// Creamos un vector de nodos con tamaño 2
		Grafo<Integer> graph = new Grafo<Integer>(2);
		graph.addNode(1);
		graph.addNode(2);
		
		// Caso 1 - El nodo existe
		Assert.assertEquals(graph.getNode(2), 1);
		
		// Caso 2 - El nodo no existe
		Assert.assertEquals(graph.getNode(3), -1);
		
		// Caso 3 - El nodo es null
		Assert.assertEquals(graph.getNode(null), -1);
		
	}

	@Test
	public void testGetEdge() {
		// Creamos un vector de nodos con tamaño 2
		Grafo<Integer> graph = new Grafo<Integer>(2);
		graph.addNode(1);
		graph.addNode(2);
		
		// Caso 1 - No existe la arista
		Assert.assertEquals(graph.getEdge(1, 2), -4, 0.0);
		
		// Caso 2 - No existe ninguno de los dos nodos
		Assert.assertEquals(graph.getEdge(0, 3), -3, 0.0);
		
		// Caso 3 - No existe el nodo destino
		Assert.assertEquals(graph.getEdge(1, 3), -2, 0.0);
		
		// Caso 4 - No existe el nodo origen
		Assert.assertEquals(graph.getEdge(0, 2), -1, 0.0);
		
		
	}

	@Test
	public void testExistNode() {
		// Creamos un vector de nodos con tamaño 2
		Grafo<Integer> graph = new Grafo<Integer>(2);
		graph.addNode(1);
		graph.addNode(2);
		
		// Caso 1 - El nodo existe
		Assert.assertTrue(graph.existNode(2));
				
		// Caso 2 - El nodo no existe
		Assert.assertFalse(graph.existNode(5));
		
		// Caso 3 - El nodo es null
		Assert.assertFalse(graph.existNode(null));
	}

	
	@Test
	public void testAddEdge(){
		Grafo<Integer> graph = new Grafo<Integer>(5);
		
		// No hay nodos
		Assert.assertEquals(-3, graph.addEdge(1, 2, 1));
		
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addEdge(1, 2, 1);
		graph.addEdge(1, 5, 10);
		graph.addEdge(1, 4, 3);
		graph.addEdge(2, 3, 5);
		graph.addEdge(2, 2, 4);
		graph.addEdge(3, 5, 1);
		graph.addEdge(4, 3, 2);
		//Peso negativo
		Assert.assertEquals(-5, graph.addEdge(4, 3, -6));
		System.out.print("Grafo completo-->"+graph.toString());
		
		// Los nodos y el camino existe
		Assert.assertEquals(1.0, graph.getEdge(1, 2), 0.0);
		Assert.assertTrue(graph.existEdge(1,2));
		Assert.assertEquals(10.0, graph.getEdge(1, 5), 0.0);
		Assert.assertTrue(graph.existEdge(1,5));
		Assert.assertEquals(3.0, graph.getEdge(1, 4), 0.0);
		Assert.assertTrue(graph.existEdge(1,4));
		Assert.assertEquals(5.0, graph.getEdge(2, 3), 0.0);
		Assert.assertTrue(graph.existEdge(2,3));
		Assert.assertEquals(4.0, graph.getEdge(2, 2), 0.0);
		Assert.assertTrue(graph.existEdge(2,2));
		Assert.assertEquals(1.0, graph.getEdge(3, 5), 0.0);
		Assert.assertTrue(graph.existEdge(3,5));
		Assert.assertEquals(2.0, graph.getEdge(4, 3), 0.0);
		Assert.assertTrue(graph.existEdge(4,3));
		
		// No existe ni el nodo origen ni el destino
		Assert.assertEquals(-3, graph.addEdge(8, 9, 6));
		Assert.assertEquals(-3, graph.addEdge(8, null, 6));
		
		
		
	}
	
	@Test
	public void testExistEdge(){
		Grafo<Integer> graph = new Grafo<Integer>(5);
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addEdge(1, 2, 1);
		graph.addEdge(1, 5, 10);
		graph.addEdge(2, 3, 5);
		graph.addEdge(3, 5, 1);
		graph.addEdge(4, 3, 2);
		
		// Los caminos existen
		Assert.assertTrue(graph.existEdge(1,2));
		Assert.assertTrue(graph.existEdge(1,5));
		Assert.assertTrue(graph.existEdge(2,3));
		Assert.assertTrue(graph.existEdge(3,5));
		Assert.assertTrue(graph.existEdge(4,3));
		// Los caminos no existen
		Assert.assertFalse(graph.existEdge(1,1));
		Assert.assertFalse(graph.existEdge(1,3));
		Assert.assertFalse(graph.existEdge(1,4));
		Assert.assertFalse(graph.existEdge(2,1));
		Assert.assertFalse(graph.existEdge(2,2));
		
		// Los nodos no existen o son null
		Assert.assertFalse(graph.existEdge(6, 7));
		Assert.assertFalse(graph.existEdge(null, 7));
		
	}
	
	@Test
	public void testRemoveEdge(){
		Grafo<Integer> graph = new Grafo<Integer>(5);
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addEdge(1, 2, 1);
		graph.addEdge(1, 5, 10);
		graph.addEdge(2, 3, 5);
		graph.addEdge(3, 5, 1);
		graph.addEdge(4, 3, 2);
		
		// Caso 2 - la arista no existe
		Assert.assertEquals(-4, graph.removeEdge(1, 1));
		Assert.assertEquals(-4, graph.removeEdge(1, 3));
		Assert.assertEquals(-4, graph.removeEdge(1, 4));
		Assert.assertEquals(-4, graph.removeEdge(5, 4));
		Assert.assertEquals(-4, graph.removeEdge(5, 5));
		
		// Caso 1 - la arista existe
		Assert.assertEquals(0, graph.removeEdge(1, 2));
		Assert.assertEquals(false, graph.existEdge(1, 2));
		Assert.assertEquals(-4, graph.getEdge(1, 2), 0.0);
		
		
		// Caso 3 - Los nodos no existen
		Assert.assertEquals(-1,  graph.removeEdge(null, 2));
		Assert.assertEquals(-2,  graph.removeEdge(1, 10));
		Assert.assertEquals(-3,  graph.removeEdge(6, 7));
	}
	
	@Test
	public void testRemoveNode(){
		Grafo<Integer> graph = new Grafo<Integer>(5);
		
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addEdge(1, 2, 1);
		graph.addEdge(1, 5, 10);
		graph.addEdge(1, 4, 3);
		graph.addEdge(2, 3, 5);
		graph.addEdge(2, 2, 4);
		graph.addEdge(3, 5, 1);
		graph.addEdge(4, 3, 2);
		
		// Caso 1 - Eliminar cualquier nodo que no ocupe la última posición
		Assert.assertEquals(0, graph.removeNode(3));
		Assert.assertFalse(graph.existNode(3));
		//Caso 2- Elimino el último nodo
		Assert.assertEquals(0, graph.removeNode(5));
		Assert.assertFalse(graph.existNode(5));
		
		//Caso 3: No puedo eliminar el nodo
		Assert.assertEquals(-1, graph.removeNode(7));
		
		
		
		
		
	}


}
