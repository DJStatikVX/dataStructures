// Paquete al que pertenece la clase
package avl;

// Importaci�n de clases
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import avl.AVLTree;

/**
 * Clase de pruebas unitarias de la clase AVLTree
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class AVLTreeTest {
	
	private AVLTree<Integer> tree;		// �rbol de las diapositivas
	private AVLTree<Integer> empty;		// �rbol vac�o
	
	@Before
	public void initialize() {
		tree = new AVLTree<Integer>();
		empty = new AVLTree<Integer>();
		
		// Se a�aden los nodos
		tree.addNode(50);
		tree.addNode(25);
		tree.addNode(75);
		tree.addNode(10);
		tree.addNode(30);
		tree.addNode(90);
	}

	/**
	 * Pruebas unitarias del m�todo addNode()
	 */
	@Test
	public void testAddNode() {
		AVLTree<Integer> treeAdd = new AVLTree<Integer>();
		
		// Caso 1: Se intenta insertar un nodo null
		assertEquals(-2, treeAdd.addNode(null));
		
		// Caso 2: �rbol vac�o (ra�z null), se inserta un nodo
		assertEquals(0, treeAdd.addNode(5));
		assertNotNull(treeAdd.findNode(5));
		
		// Caso 3: Se intenta insertar un nodo ya existente
		assertEquals(-1, treeAdd.addNode(5));
		
		// Caso 4: La ra�z no es null y se inserta un nodo de clave menor en ella
		treeAdd.addNode(3);
		assertEquals("3:FB=0\t5:FB=-1", treeAdd.inOrder());
		
		// Caso 5: Se inserta un nodo de clave menor a la ra�z (con recursi�n)
		treeAdd.addNode(1);
		assertEquals("1:FB=0\t3:FB=0\t5:FB=0", treeAdd.inOrder());
		
		// Caso 6: La ra�z no es null y se inserta un nodo de clave mayor en ella
		treeAdd.addNode(10);
		assertEquals("1:FB=0\t3:FB=1\t5:FB=1\t10:FB=0", treeAdd.inOrder());
		
		// Caso 7: Se inserta un nodo de clave mayor a la ra�z (con recursi�n)
		treeAdd.addNode(4);
		assertEquals("1:FB=0\t3:FB=1\t4:FB=0\t5:FB=0\t10:FB=0", treeAdd.inOrder());
		
		// ------------------ Casos con rotaciones ----------------------------
		
		// Caso 8: Un �rbol se desequilibra y requiere Rotaci�n Simple Derecha
		assertEquals("50:FB=0\t25:FB=0\t10:FB=0\t30:FB=0\t75:FB=1\t90:FB=0", 
				tree.preOrder());
		
		tree.addNode(95);
		
		assertEquals("50:FB=0\t25:FB=0\t10:FB=0\t30:FB=0\t90:FB=0\t75:FB=0\t95:FB=0", 
				tree.preOrder());
		
		// Caso 9: Otro �rbol se desequilibra y requiere Rotaci�n Simple Izquierda
		AVLTree<Integer> treeRSI = new AVLTree<Integer>();
		
		treeRSI.addNode(50);
		treeRSI.addNode(25);
		treeRSI.addNode(75);
		treeRSI.addNode(10);
		
		assertEquals("50:FB=-1\t25:FB=-1\t10:FB=0\t75:FB=0", treeRSI.preOrder());
		
		// Se a�ade el nodo que provoca el desequilibrio
		treeRSI.addNode(5);
		
		assertEquals("50:FB=-1\t10:FB=0\t5:FB=0\t25:FB=0\t75:FB=0", 
				treeRSI.preOrder());
		
		// Caso 10: Otro �rbol se desequilibra y requiere Rotaci�n Doble Derecha
		AVLTree<Integer> treeRDD = new AVLTree<Integer>();
		
		treeRDD.addNode(50);
		treeRDD.addNode(25);
		treeRDD.addNode(75);
		treeRDD.addNode(30);
		
		assertEquals("50:FB=-1\t25:FB=1\t30:FB=0\t75:FB=0", treeRDD.preOrder());
		
		// Se a�ade el nodo que provoca el desequilibrio
		treeRDD.addNode(26);
		
		assertEquals("50:FB=-1\t26:FB=0\t25:FB=0\t30:FB=0\t75:FB=0", treeRDD.preOrder());
		
		// Caso 11: Otro �rbol se desequilibra y requiere Rotaci�n Doble Izquierda
		AVLTree<Integer> treeRDI = new AVLTree<Integer>();
		
		treeRDI.addNode(50);
		treeRDI.addNode(25);
		treeRDI.addNode(75);
		treeRDI.addNode(60);
		
		assertEquals("50:FB=1\t25:FB=0\t75:FB=-1\t60:FB=0", treeRDI.preOrder());
		
		// Se a�ade el nodo que provoca el desequilibrio
		treeRDI.addNode(65);
		
		assertEquals("50:FB=1\t25:FB=0\t65:FB=0\t60:FB=0\t75:FB=0", treeRDI.preOrder());
	}
	
	/**
	 * Pruebas unitarias del m�todo findNode()
	 */
	@Test
	public void testFindNode() {
		AVLTree<Integer> treeFind = new AVLTree<Integer>();
		
		treeFind.addNode(50);
		treeFind.addNode(25);
		treeFind.addNode(10);
		treeFind.addNode(40);
		treeFind.addNode(35);
		treeFind.addNode(45);
		treeFind.addNode(42);
		treeFind.addNode(75);
		treeFind.addNode(60);
		treeFind.addNode(70);
		treeFind.addNode(90);
		
		// Caso 1: Se intenta buscar un nodo null
		assertNull(treeFind.findNode(null));
		
		// Caso 2: El �rbol est� vac�o
		assertNull(empty.findNode(7));
		
		// Caso 3: Buscar un nodo que no est� dentro del �rbol
		assertNull(treeFind.findNode(100));
		
		// Caso 4: Buscar directamente la ra�z
		assertNotNull(treeFind.findNode(50));
		
		// Caso 5: Buscar un nodo de clave menor a la ra�z (sin recursi�n)
		assertNotNull(treeFind.findNode(25));
		
		// Caso 6: Buscar un nodo de clave mayor a la ra�z (sin recursi�n)
		assertNotNull(treeFind.findNode(75));
		
		// Caso 7: Buscar un nodo de clave menor a la ra�z (con recursi�n)
		assertNotNull(treeFind.findNode(42));
		
		// Caso 8: Buscar un nodo de clave mayor a la ra�z (con recursi�n)
		assertNotNull(treeFind.findNode(70));
	}
	
	/**
	 * Pruebas unitarias del m�todo removeNode()
	 */
	@Test
	public void testRemoveNode() {
		// Caso 1: Se intenta borrar de un �rbol vac�o
		assertEquals(-2, empty.removeNode(7));
		
		// Caso 2: Se intenta borrar un nodo null
		assertEquals(-2, tree.removeNode(null));
		
		// Caso 3: Se intenta borrar un nodo que no existe
		assertEquals(-1, tree.removeNode(100));
		
		// Caso 4: Se intenta borrar un nodo con un solo hijo por la derecha
		assertEquals(0, tree.removeNode(75));
		assertEquals("10:FB=0\t25:FB=0\t30:FB=0\t50:FB=-1\t90:FB=0", tree.inOrder());
		
		// Caso 5: Se intenta borrar un nodo hoja (sin hijos)
		assertEquals(0, tree.removeNode(90));	// RSI
		assertEquals("10:FB=0\t25:FB=1\t30:FB=0\t50:FB=-1", tree.inOrder());
		
		// Caso 6: Se intenta borrar un nodo con un solo hijo por la izquierda
		assertEquals(0, tree.removeNode(50));
		assertEquals("10:FB=0\t25:FB=0\t30:FB=0", tree.inOrder());
		
		// Caso 7: Se intenta borrar la ra�z del �rbol (que tiene dos hijos)
		assertEquals(0, tree.removeNode(25));	// mayor de los menores
		assertEquals("10:FB=1\t30:FB=0", tree.inOrder());
	}
	
	/**
	 * Pruebas unitarias del m�todo preOrder()
	 */
	@Test
	public void testPreOrder() {
		// Caso 1: �rbol vac�o
		assertEquals("", empty.preOrder());
		
		// Caso 2: �rbol no vac�o
		assertEquals("50:FB=0\t25:FB=0\t10:FB=0\t30:FB=0\t75:FB=1\t90:FB=0", 
				tree.preOrder());
	}
	
	/**
	 * Pruebas unitarias del m�todo inOrder()
	 */
	@Test
	public void testInOrder() {
		// Caso 1: �rbol vac�o
		assertEquals("", empty.inOrder());
		
		// Caso 2: �rbol no vac�o
		assertEquals("10:FB=0\t25:FB=0\t30:FB=0\t50:FB=0\t75:FB=1\t90:FB=0", 
				tree.inOrder());
	}
	
	/**
	 * Pruebas unitarias del m�todo postOrder()
	 */
	@Test
	public void testPostOrder() {
		// Caso 1: �rbol vac�o
		assertEquals("", empty.postOrder());
		
		// Caso 2: �rbol no vac�o
		assertEquals("10:FB=0\t30:FB=0\t25:FB=0\t90:FB=0\t75:FB=1\t50:FB=0", 
				tree.postOrder());
	}
	
	/**
	 * Pruebas unitarias del m�todo esAscendienteDirecto()
	 */
	@Test
	public void testEsAscendienteDirecto() {
		// Caso 1: El padre no existe en el �rbol
		assertFalse(tree.esAscendienteDirecto(100, 50));
		
		// Caso 2: El hijo no existe en el �rbol
		assertFalse(tree.esAscendienteDirecto(50, 100));
		
		// Caso 3: El padre es ascendiente directo (subcaso hijo izquierdo)
		assertTrue(tree.esAscendienteDirecto(25, 10));
		
		// Caso 4: El padre es ascendiente directo (subcaso hijo derecho)
		assertTrue(tree.esAscendienteDirecto(50, 75));
		
		// Caso 5: El padre no es ascendiente directo
		assertFalse(tree.esAscendienteDirecto(50, 30));
	}
	
	/**
	 * Pruebas unitarias del m�todo numAristas()
	 */
	@Test
	public void testNumAristas() {
		// Caso 1: No existe origen en el �rbol
		assertEquals(-1, tree.numAristas(72, 50));
		
		// Caso 2: No existe destino en el �rbol
		assertEquals(-1, tree.numAristas(50, 100));
		
		// Caso 3: No existe origen ni destino en el �rbol
		assertEquals(-1, tree.numAristas(0, 80));
		
		// Caso 4: Origen y destino son el mismo nodo
		assertEquals(0, tree.numAristas(50, 50));
				
		// Caso 6: Origen y destino se encuentran a distintas alturas (izda.)
		assertEquals(2, tree.numAristas(50, 10));
		
		// Caso 7: Origen y destino se encuentran a distintas alturas (dcha.)
		assertEquals(2, tree.numAristas(50, 30));
	}
	
	/**
	 * Pruebas unitarias del m�todo camino()
	 */
	@Test
	public void testCamino() {
		// Caso 1: No existe origen en el �rbol
		assertEquals("", tree.camino(80, 25));
		
		// Caso 2: No existe destino en el �rbol
		assertEquals("", tree.camino(10, 60));
		
		// Caso 3: No existe origen ni destino en el �rbol
		assertEquals("", tree.camino(95, 7));
		
		// Caso 4: Origen y destino son el mismo nodo
		assertEquals("50:FB=0", tree.camino(50, 50));
		
		// Caso 5: Origen y destino se encuentran a distintas alturas (izquierda)
		assertEquals("50:FB=0\t25:FB=0\t10:FB=0", tree.camino(50, 10));
		
		// Caso 6: Origen y destino se encuentran a distintas alturas (derecha)
		assertEquals("50:FB=0\t75:FB=1\t90:FB=0", tree.camino(50, 90));
	}
	
}