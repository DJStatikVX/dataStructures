// Paquete al que pertenece la clase
package bst;

// Importaci�n de clases
import static org.junit.Assert.*;

import org.junit.Test;

import bst.BSTree;

/**
 * Pruebas unitarias de la clase BSTree
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class BSTreeTest {
	
	/**
	 * Pruebas unitarias del m�todo addNode()
	 */
	@Test
	public void testAddNode() {
		BSTree<Integer> tree = new BSTree<Integer>();
		
		// Caso 1: Se intenta insertar un nodo null
		assertEquals(-2, tree.addNode(null));
		
		// Caso 2: �rbol vac�o (ra�z null), se inserta un nodo
		assertEquals(0, tree.addNode(5));
		assertNotNull(tree.findNode(5));
		
		// Caso 3: Se intenta insertar un nodo ya existente
		assertEquals(-1, tree.addNode(5));
		
		// Caso 4: La ra�z no es null y se inserta un nodo de clave menor en ella
		tree.addNode(3);
		assertEquals("3\t5", tree.inOrder());
		
		// Caso 5: Se inserta un nodo de clave menor a la ra�z (con recursi�n)
		tree.addNode(1);
		assertEquals("1\t3\t5", tree.inOrder());
		
		// Caso 6: La ra�z no es null y se inserta un nodo de clave mayor en ella
		tree.addNode(10);
		assertEquals("1\t3\t5\t10", tree.inOrder());
		
		// Caso 7: Se inserta un nodo de clave mayor a la ra�z (con recursi�n)
		tree.addNode(20);
		assertEquals("1\t3\t5\t10\t20", tree.inOrder());
	}

	/**
	 * Pruebas unitarias del findNode()
	 */
	@Test
	public void testFindNode() {
		BSTree<Integer> tree = new BSTree<Integer>();
		BSTree<Integer> empty = new BSTree<Integer>();
		
		tree.addNode(50);
		tree.addNode(25);
		tree.addNode(10);
		tree.addNode(40);
		tree.addNode(35);
		tree.addNode(45);
		tree.addNode(42);
		tree.addNode(75);
		tree.addNode(60);
		tree.addNode(70);
		tree.addNode(90);
		
		// Caso 1: Se intenta buscar un nodo null
		assertNull(tree.findNode(null));
		
		// Caso 2: El �rbol est� vac�o
		assertNull(empty.findNode(7));
		
		// Caso 3: Buscar un nodo que no est� dentro del �rbol
		assertNull(tree.findNode(100));
		
		// Caso 4: Buscar directamente la ra�z
		assertNotNull(tree.findNode(50));
		
		// Caso 5: Buscar un nodo de clave menor a la ra�z (sin recursi�n)
		assertNotNull(tree.findNode(25));
		
		// Caso 6: Buscar un nodo de clave mayor a la ra�z (sin recursi�n)
		assertNotNull(tree.findNode(75));
		
		// Caso 7: Buscar un nodo de clave menor a la ra�z (con recursi�n)
		assertNotNull(tree.findNode(42));
		
		// Caso 8: Buscar un nodo de clave mayor a la ra�z (con recursi�n)
		assertNotNull(tree.findNode(70));
	}

	/**
	 * Pruebas unitarias del m�todo preOrder()
	 */
	@Test
	public void testPreOrder() {
		
		// Caso 1: �rbol vac�o
		BSTree<Integer> empty = new BSTree<Integer>();
		assertEquals("", empty.preOrder());
		
		// Caso 2: �rbol no vac�o
		BSTree<Integer> tree = new BSTree<Integer>();
		
		tree.addNode(50);
		tree.addNode(25);
		tree.addNode(75);
		tree.addNode(10);
		tree.addNode(40);
		tree.addNode(60);
		tree.addNode(90);
		tree.addNode(35);
		tree.addNode(45);
		tree.addNode(70);
		tree.addNode(42);
		
		assertEquals("50\t25\t10\t40\t35\t45\t42\t75\t60\t70\t90", 
				tree.preOrder());
	}
	
	/**
	 * Pruebas unitarias del m�todo inOrder()
	 */
	@Test
	public void testInOrder() {
		
		// Caso 1: �rbol vac�o
		BSTree<Integer> empty = new BSTree<Integer>();
		assertEquals("", empty.inOrder());
		
		// Caso 2: �rbol no vac�o
		BSTree<Integer> tree = new BSTree<Integer>();
		
		tree.addNode(50);
		tree.addNode(25);
		tree.addNode(75);
		tree.addNode(10);
		tree.addNode(40);
		tree.addNode(60);
		tree.addNode(90);
		tree.addNode(35);
		tree.addNode(45);
		tree.addNode(70);
		tree.addNode(42);
		
		assertEquals("10\t25\t35\t40\t42\t45\t50\t60\t70\t75\t90", 
				tree.inOrder());
	}
	
	/**
	 * Pruebas unitarias del m�todo postOrder()
	 */
	@Test
	public void testPostOrder() {
		
		// Caso 1: �rbol vac�o
		BSTree<Integer> empty = new BSTree<Integer>();
		assertEquals("", empty.postOrder());
		
		// Caso 2: �rbol no vac�o
		BSTree<Integer> tree = new BSTree<Integer>();
		
		tree.addNode(50);
		tree.addNode(25);
		tree.addNode(75);
		tree.addNode(10);
		tree.addNode(40);
		tree.addNode(60);
		tree.addNode(90);
		tree.addNode(35);
		tree.addNode(45);
		tree.addNode(70);
		tree.addNode(42);
		
		assertEquals("10\t35\t42\t45\t40\t25\t70\t60\t90\t75\t50", 
				tree.postOrder());
	}
	
	/**
	 * Pruebas unitarias del m�todo removeNode()
	 */
	@Test
	public void testRemoveNode() {
		BSTree<Integer> tree = new BSTree<Integer>();
		
		// Ejemplo de las diapositivas
		
		tree.addNode(6);
		tree.addNode(2);
		tree.addNode(8);
		tree.addNode(1);
		tree.addNode(4);
		tree.addNode(7);
		tree.addNode(9);
		tree.addNode(3);
		tree.addNode(5);
		
		// Caso 1: Borrar un nodo que no tiene hijos
		tree.removeNode(5);
		assertEquals("1\t2\t3\t4\t6\t7\t8\t9", tree.inOrder());
		
		// Caso 2: Borrar un nodo con un solo hijo
		tree.removeNode(3);
		tree.removeNode(9);
		tree.addNode(5);		// se restaura para este caso
		
		tree.removeNode(4);
		
		assertEquals("1\t2\t5\t6\t7\t8", tree.inOrder());

		// Caso 3: Borrar un nodo con dos hijos
		tree.addNode(4);
		tree.addNode(3);		// se restauran para este caso
		tree.addNode(9);
		
		tree.removeNode(6);
		
		assertEquals("1\t2\t3\t4\t5\t7\t8\t9", tree.inOrder());
	}

}