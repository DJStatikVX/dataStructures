// Paquete al que pertenece la clase
package bst;

/**
 * Clase BSTree que implementa un árbol binario
 * @author Samuel Rodríguez Ares (UO271612)
 * @param <T> tipo de objetos para los nodos del árbol, de tipo genérico T
 */
public class BSTree<T extends Comparable<T>> {
	
	private BSTNode<T> raiz;	// nodo raíz del árbol
	private String recorrido;	// cadena global para los recorridos
	
	/**
	 * Constructor por defecto
	 */
	public BSTree() {
		this.raiz = null;
	}
	
	/**
	 * Añade un nuevo nodo al árbol.
	 * Si ya existe la clave devuelve -1 (ya existe)
	 * En cualquier otro caso (si vale null) devuelve -2 
	 * Si el árbol es vacío crea un nuevo nodo y devuelve 0
	 * Si la raíz no es null llama a addNodeR con el nodo a partir del cual
	 * vamos a insertar y el valor del nodo a insertar
	 * @param clave clave del nuevo nodo a añadir, de tipo genérico T
	 * @return 0 si el nodo se inserta correctamente; -1 si el nodo a insertar
	 * 		   ya existe; -2 si el nodo a insertar es null
	 */
	public int addNode(T clave) {
		// Se comprueba si la clave es null
		if (clave == null) {
			return -2;
		}
		
		// Se comprueba si el nodo ya existe en el árbol
		if (findNode(clave) != null) {
			return -1;
		}
		
		// Si el árbol es vacío, lo incializa y establece como raíz
		if (this.raiz == null) {
			
			BSTNode<T> node = new BSTNode<T>(clave);
			this.raiz = node;
		
		// En caso contrario, busca dónde tiene que insertarlo
		} else {
			addNodeR(this.raiz, clave);
		}
		
		return 0; // se insertó correctamente
	}
	
	/**
	 * Método recursivo de inserción de nodos.
	 * Si la clave es menor que el valor del nodo a partir del cual
	 * queremos insertar, inserta en la izquierda siempre y cuando no tenga
	 * hijos por la izquierda y devolver 0.
	 * Si la clave es mayor que el valor del nodo a partir del cual 
	 * queremos insertar, inserta a la derecha siempre y cuando 
	 * no tenga hijos por la derecha y devuelve 0.
	 * @param node nodo a partir del cual se quiere insertar el nodo, BSTNode de T
	 * @param clave clave del nuevo nodo a añadir, de tipo genérico T
	 * @return 0 (siempre se va a poder añadir al recurrir a este método)
	 */
	public int addNodeR(BSTNode<T> node, T clave) {
		// Si la clave es menor, busca por la izquierda del nodo
		if (clave.compareTo(node.getInfo()) < 0) {
			// Si no hay hijo izquierdo, se inserta el nodo
			if (node.getLeft() == null) {
				node.setLeft(new BSTNode<T>(clave));
			
			// En caso contrario, vuelve a comparar con el hijo izquierdo
			} else {
				addNodeR(node.getLeft(), clave);
			}
			
		// Si la clave es mayor, busca por la derecha del nodo
		} else if (clave.compareTo(node.getInfo()) > 0) {
			// Si no hay hijo derecho, se inserta el nodo
			if (node.getRight() == null) {
				node.setRight(new BSTNode<T>(clave));
				
			// En caso contrario, vuelve a comparar con el hijo derecho
			} else {
				addNodeR(node.getRight(), clave);
			}
		}
		
		return 0; // cuando se haya insertado correctamente
	}
	
	/**
	 * Si la raíz no es null, llama a findNodeR() con el nodo a partir del cual
	 * queremos insertar y el valor a buscar
	 * @param clave clave del nodo que se quiere buscar, tipo genérico T
	 * @return Nodo cuya clave se busca si lo encuentra (BSTNode); null en otro caso
	 */
	public BSTNode<T> findNode(T clave) {
		// Se comprueba si la clave es null
		if (clave == null) {
			return null;
		}
		
		// Se comprueba si el árbol está vacío
		if (this.raiz == null) {
			return null;
			
		// En caso contrario, comienza a buscar el nodo
		} else {
			return findNodeR(this.raiz, clave);
		}
	}
	
	/**
	 * Si la clave es igual al valor del nodo a partir del cual
	 * queremos insertar devuelve el nodo.
	 * Si el valor es menor que el valor del nodo a partir del cual
	 * queremos insertar busca el valor con la raíz el hijo izquierdo.
	 * Si el valor es mayor que el valor del nodo a partir del cual
	 * queremos insertar busca el valor con la raíz el hijo derecho
	 * @param node nodo a partir del cual se quiere buscar el nodo, BSTNode de T
	 * @param clave clave del nodo que se quiere buscar, tipo genérico T
	 * @return Nodo a buscar, si lo encuentra, de tipo BSTNode de T
	 */
	private BSTNode<T> findNodeR(BSTNode<T> node, T clave) {
		// Si las claves coinciden, lo ha encontrado y lo devuelve
		if (clave.compareTo(node.getInfo()) == 0) {
			return node;
			
		// Si la clave es menor, sigue buscando por la izquierda del nodo
		} else if (clave.compareTo(node.getInfo()) < 0
				&& node.getLeft() != null) {
			return findNodeR(node.getLeft(), clave);
			
		// Si la clave es mayor, sigue buscando por la derecha del nodo
		} else if (clave.compareTo(node.getInfo()) > 0
				&& node.getRight() != null) {
			return findNodeR(node.getRight(), clave);
		}
		
		return null; // si no encontró el nodo con la clave especificada
	}
	
	/**
	 * Devuelve una cadena con el recorrido preorden, separando los elementos
	 * por tabuladores, eliminando el último tabulador si lo hubiese.
	 * Pasa por la raíz, luego recorre el subárbol izquierdo y por último
	 * recorre el subárbol derecho
	 * @return Recorrido preorden del árbol en forma de cadena, tipo String
	 */
	public String preOrder() {
		recorrido = "";
		return preOrderR(this.raiz);
	}
	
	/**
	 * Método recursivo que devuelve en forma de cadena el recorrido preorden 
	 * a partir del nodo especificado
	 * @param raiz nodo a partir del cual se realiza el recorrido preorden, BSTNode
	 * @return Recorrido preorden en forma de String del nodo en cuestión
	 */
	private String preOrderR(BSTNode<T> raiz) {
		// Se comprueba si la raíz del árbol/subárbol es null
		if (raiz == null) {
			return recorrido;
			
		// En caso contrario, concatena la raíz
		} else {
			recorrido = raiz.toString();
		}
		
		// Si hay subárbol izquierdo, realiza el recorrido preorden del mismo
		if (raiz.getLeft() != null) {
			recorrido += ("\t" + preOrderR(raiz.getLeft())); 
		}
		
		// Si hay subárbol derecho, realiza el recorrido preorden del mismo
		if (raiz.getRight() != null) {
			recorrido += ("\t" + preOrderR(raiz.getRight()));
		}
		
		return recorrido; // devuelve el recorrido calculado
	}
	
	/** 
	 * Devuelve una cadena con el recorrido inorden, separando los elementos
	 * por tabuladores, eliminando el último tabulador si lo hubiese.
	 * Recorre el subárbol izquierdo, luego pasa por la raíz
	 * y por último recorre el subárbol derecho
	 * @return Recorrido inorden del árbol en forma de cadena, tipo String
	 */
	public String inOrder() {
		recorrido = "";
		
		// Si el árbol está vacío, devuelve cadena vacía
		if (this.raiz == null) {
			return recorrido;
		}
		
		// En otro caso, llama al método recursivo y elimina el tabulador final
		return inOrderR(this.raiz).substring(0, recorrido.length() - 1);
	}
	
	/**
	 * Método recursivo que devuelve en forma de cadena
	 * el recorrido inorden a partir del nodo especificado
	 * @param raiz nodo a partir del cual se realiza el recorrido inorden
	 * @return Recorrido inorden en forma de String del nodo en cuestión
	 */
	private String inOrderR(BSTNode<T> node) {
		// Se comprueba si la raíz del árbol/subárbol es null
		if (node == null) {
			return recorrido;
		}
		
		// Si hay subárbol izquierdo, realiza el recorrido inorden del mismo
		if (node.getLeft() != null) {
			recorrido = inOrderR(node.getLeft());
		}
		
		// Se concatena la raíz, que no es null
		recorrido += (node.toString() + "\t");
		
		// Si hay subárbol derecho, realiza el recorrido inorden del mismo
		if (node.getRight() != null) {
			recorrido = inOrderR(node.getRight());
		}
		
		return recorrido; // devuelve el recorrido calculado
	}
	
	/** 
	 * Devuelve una cadena con el recorrido postorden, separando los elementos
	 * por tabuladores, eliminando el último tabulador si lo hubiese
	 * Recorre el subárbol izquierdo, luego recorre el subárbol
	 * derecho y por último pasa por la raíz
	 * @return Recorrido postorden del árbol en forma de cadena, tipo String
	 */
	public String postOrder() {
		recorrido = "";
		
		// Si el árbol está vacío, devuelve cadena vacía
		if (this.raiz == null) {
			return recorrido;
		}
		
		// En otro caso, llama al método recursivo y elimina el tabulador final
		return postOrderR(this.raiz).substring(0, recorrido.length() - 1);
	}
	
	/**
	 * Método recursivo que devuelve en forma de cadena el recorrido postorden 
	 * a partir del nodo especificado
	 * @param raiz nodo a partir del cual se realiza el recorrido postorden
	 * @return Recorrido postorden en forma de String del nodo en cuestión
	 */
	private String postOrderR(BSTNode<T> node) {
		// Se comprueba si la raíz del árbol/subárbol es null
		if (node == null) {
			return recorrido;
		}
		
		// Si hay subárbol izquierdo, realiza el recorrido postorden del mismo
		if (node.getLeft() != null) {
			recorrido = postOrderR(node.getLeft()); 
		}
		
		// Si hay subárbol derecho, realiza el recorrido postorden del mismo
		if (node.getRight() != null) {
			recorrido = (postOrderR(node.getRight()));
		}
		
		// Se concatena la raíz, que no es null
		recorrido += (node.toString() + "\t");
		
		return recorrido; // devuelve el recorrido calculado
	}
	
	/**
	 * Elimina el nodo, cuya clave se pasa como parámetro, del árbol
	 * @param clave clave del nodo a eliminar, de tipo genérico T
	 * @return 0 si elimina el nodo con éxito, -1 si la clave no existe
	 * 		   y -2 si la clave o el árbol son null/vacíos
	 */
	public int removeNode(T clave) {
		// Se comprueba si la clave es null o si el árbol es vacío
		if (clave == null || this.raiz == null) {
			return -2;
			
		// Se comprueba si el nodo está en el árbol
		} else if (findNode(clave) == null) {
			return -1;
		}
		
		// Se asigna el árbol actualizado a la raíz
		this.raiz = removeNodeR(this.raiz, clave);
		
		return 0; // si pudo eliminarse con éxito
	}
	
	/**
	 * Busco entre los nodos hasta encontrar la clave
	 * ¿Tiene hijo izquierdo? ¿Tiene hijo derecho? Se devuelve si solo hay uno
	 * Si hay dos, buscar el mayor del subárbol izquierdo del nodo a borrar.
	 * Asignar el borrado del subárbol izquierdo por la izquierda del nodo.
	 * Se puede hacer un método auxiliar para buscar ese hijo
	 * @param node nodo de referencia para el borrado, BSTNode de T
	 * @param clave clave del nodo a borrar, de tipo genérico T
	 * @return El nodo en cuestión si hay hijos; null si node no tiene hijos
	 */
	private BSTNode<T> removeNodeR(BSTNode<T> node, T clave) {
		
		// Si la clave es menor que la del nodo, busca por su izquierda
		if (clave.compareTo(node.getInfo()) < 0) {
			// Actualizará el subárbol izquierdo cuando encuentre el nodo
			node.setLeft(removeNodeR(node.getLeft(), clave));
			return node;
		
		// Si la clave es mayor que la del nodo, busca por su derecha	
		} else if (clave.compareTo(node.getInfo()) > 0) {
			// Actualizará el subárbol derecho cuando encuentre el nodo
			node.setRight(removeNodeR(node.getRight(), clave));
			return node;
		}
		
		// Una vez encuentre el nodo a borrar... Casos posibles:
		
		// El nodo no tiene hijos; devuelve null
		if (node.getLeft() == null && node.getRight() == null) {
			return null;
			
		// El nodo solo tiene un hijo por la izquierda
		} else if (node.getLeft() != null && node.getRight() == null) {
			return node.getLeft();
			
		// El nodo solo tiene un hijo por la derecha
		} else if (node.getRight() != null && node.getLeft() == null) {
			return node.getRight();
		}
		
		// El nodo tiene dos hijos, uno a la izquierda y otro a la derecha
		if (node.getLeft() != null && node.getRight() != null) {
			// Se busca el mayor de los menores (subárbol izquierdo)
			BSTNode<T> max = getHighestChild(node.getLeft());
			
			// Se establece por su izquierda el subárbol del borrado sin ese máximo
			max.setLeft(removeNodeR(node.getLeft(), max.getInfo()));
			
			// Al reemplazarse, tendrá que tener el subárbol derecho del borrado
			max.setRight(node.getRight());
			
			return max;	// árbol actualizado
		}
		
		return null; // cualquier otro caso
	}
	
	/**
	 * Devuelve el hijo mayor del nodo pasado como parámetro. 
	 * Si va encontrando nodos mayores, realiza llamadas recursivas
	 * @param node nodo del que se busca el hijo mayor, de tipo BSTNode de T
	 * @return Hijo con mayor clave del nodo pasado como parámetro, tipo BSTNode
	 */
	private BSTNode<T> getHighestChild(BSTNode<T> node) {
		if (node.getRight() == null) {
			return node;
		} else {
			return getHighestChild(node.getRight());
		}
	}

}