// Paquete al que pertenece la clase
package avl;

/**
 * Implementa un nodo de un árbol AVL
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class AVLNode<T extends Comparable<T>> {
	
	private T info;				// el contenido de un nodo de tipo genérico
	private AVLNode<T> left;	// nodo hijo izquierdo
	private AVLNode<T> right;	// nodo hijo derecho
	private int factorBalance;	// factor de balance del árbol
	private int altura;			// altura del árbol
	
	/**
	 * Constructor que incializa el nodo como una hoja (sin hijos, FB y A son 0)
	 * @param clave clave correspondiente al nuevo nodo, de tipo genérico T
	 */
	public AVLNode(T clave) {
		setInfo(clave);
		setLeft(null);
		setRight(null);
		setFB(0);
		setAltura(0);
	}
	
	/**
	 * Devuelve la clave del nodo
	 * @return Clave del nodo, de tipo genérico T
	 */
	public T getInfo() {
		return this.info;
	}
	
	/**
	 * Modifica o establece la clave del nodo
	 * @param clave clave a establecer para el nodo, de tipo genérico T
	 */
	public void setInfo(T clave) {
		this.info = clave;
	}
	
	/**
	 * Modifica o establece el hijo izquierdo
	 * @param node hijo izquierdo a establecer para el nodo, de tipo genérico T
	 */
	public void setLeft(AVLNode<T> node) {
		this.left = node;
	}
	
	/**
	 * Modifica o establece el hijo derecho
	 * @param node hijo derecho a establecer para el nodo, de tipo genérico T
	 */
	public void setRight(AVLNode<T> node) {
		this.right = node;
	}
	
	/**
	 * Devuelve el hijo izquierdo del nodo
	 * @return Hijo izquierdo del nodo, AVLNode de tipo genérico T
	 */
	public AVLNode<T> getLeft() {
		return this.left;
	}
	
	/**
	 * Devuelve el hijo derecho del nodo
	 * @return Hijo derecho del nodo, AVLNode de tipo genérico T
	 */
	public AVLNode<T> getRight() {
		return this.right;
	}
	
	/**
	 * Devuelve la altura del nodo
	 * @return Altura del nodo, de tipo int
	 */
	public int getAltura() {
		return this.altura;
	}
	
	/**
	 * Modifica o establece la altura del nodo
	 * @param a altura a establecer para el nodo, de tipo int
	 */
	public void setAltura(int a) {
		this.altura = a;
	}
	
	/**
	 * Devuelve el factor de balance del nodo
	 * @return Factor de balance del nodo, de tipo int
	 */
	public int getFB() {
		return this.factorBalance;
	}
	
	/**
	 * Modifica o establece el factor de balance del nodo
	 * @param bf factor de balance a establecer para el nodo, de tipo int
	 */
	protected void setFB(int bf) {
		this.factorBalance = bf;
	}
	
	/**
	 * Calcula el factor de balance y la altura para el nodo en cuestión.
	 * El factor de balance se establece como la diferencia entra la altura
	 * del hijo derecho y la altura del hijo izquierdo.
	 * La altura se establece como el máximo entre la altura de los subárboles
	 * (En caso de que no tenga hijo, el subárbol toma altura de valor -1)
	 */
	public void calcularFactorBalanceAltura() {
		// Caso de que sea un nodo hoja
		if (getLeft() == null && getRight() == null) {
			setFB(0);
			setAltura(0);
		
		// Caso de que no tenga hijo izquierdo
		} else if (getLeft() == null && getRight() != null) {
			setFB(getRight().getAltura() - (-1));
			setAltura(getRight().getAltura() + 1);
		
		// Caso de que no tenga hijo derecho
		} else if (getLeft() != null && getRight() == null) {
			setFB(-1 - getLeft().getAltura());
			setAltura(getLeft().getAltura() + 1);
			
		// Cualquier otro caso (tiene dos hijos)
		} else {
			setFB(getRight().getAltura() - getLeft().getAltura());
			setAltura(Math.max(getLeft().getAltura(), getRight().getAltura()) + 1);
		}
	}
	
	/**
	 * Devuelve una cadena con la clave del nodo y su factor de balance
	 * @return Cadena con el formato	info:FB=factorBalance
	 */
	@Override
	public String toString() {
		return info.toString() + ":FB=" + getFB();
	}	

}