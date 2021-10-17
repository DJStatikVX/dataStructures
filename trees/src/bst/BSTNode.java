// Paquete al que pertenece la clase
package bst;

/**
 * Clase BSTNode que implementa un nodo de un �rbol
 * @author Samuel Rodr�guez Ares (UO271612)
 * @param <T> tipo de objeto a almacenar en el nodo, de tipo gen�rico T
 */
public class BSTNode<T extends Comparable<T>> {
	
	private T info;				// contenido de un nodo de tipo gen�rico
	private BSTNode<T> left; 	// hijo izquierdo (nodo)
	private BSTNode<T> right; 	// hijo derecho (nodo)
	
	/**
	 * Instancia el nodo estableciendo su clave al valor pasado como par�metro.
	 * Adem�s, establece sus hijos izquierdo y derecho a null
	 * @param clave clave a almacenar en el nodo, de tipo gen�rico T
	 */
	public BSTNode(T clave) {
		this.setInfo(clave);
		this.setLeft(null);
		this.setRight(null);
	}
	
	/**
	 * Modifica o establece la clave del nodo
	 * @param clave nueva clave a establecer, de tipo gen�rico T
	 */
	public void setInfo(T clave) {
		this.info = clave;
	}
	
	/**
	 * Devuelve la clave del nodo
	 * @return Clave del nodo en cuesti�n, de tipo gen�rico T
	 */
	public T getInfo() {
		return this.info;
	}
	
	/**
	 * Modifica o establece el hijo izquierdo del nodo
	 * @param node hijo izquierdo a establecer, de tipo BSTNode de T
	 */
	public void setLeft(BSTNode<T> node) {
		this.left = node;
	}
	
	/**
	 * Modifica o establece el hijo derecho del nodo
	 * @param node hijo derecho a establecer, de tipo BSTNode de T
	 */
	public void setRight(BSTNode<T> node) {
		this.right = node;
	}
	
	/**
	 * Devuelve el hijo izquierdo del nodo
	 * @return Hijo izquierdo del nodo, de tipo BSTNode de T
	 */
	public BSTNode<T> getLeft() {
		return this.left;
	}
	
	/**
	 * Devuelve el hijo derecho del nodo
	 * @return Hijo derecho del nodo, de tipo BSTNode de T
	 */
	public BSTNode<T> getRight() {
		return this.right;
	}
	
	/**
	 * Devuelve una cadena con la informaci�n de la clave del nodo
	 * @return Cadena con la informaci�n de la clave del nodo, de tipo String
	 */
	@Override
	public String toString() {
		return getInfo().toString();
	}
	
}