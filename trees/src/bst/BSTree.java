// Paquete al que pertenece la clase
package bst;

/**
 * Clase BSTree que implementa un �rbol binario
 * @author Samuel Rodr�guez Ares (UO271612)
 * @param <T> tipo de objetos para los nodos del �rbol, de tipo gen�rico T
 */
public class BSTree<T extends Comparable<T>> {
	
	private BSTNode<T> raiz;	// nodo ra�z del �rbol
	private String recorrido;	// cadena global para los recorridos
	
	/**
	 * Constructor por defecto
	 */
	public BSTree() {
		this.raiz = null;
	}
	
	/**
	 * A�ade un nuevo nodo al �rbol.
	 * Si ya existe la clave devuelve -1 (ya existe)
	 * En cualquier otro caso (si vale null) devuelve -2 
	 * Si el �rbol es vac�o crea un nuevo nodo y devuelve 0
	 * Si la ra�z no es null llama a addNodeR con el nodo a partir del cual
	 * vamos a insertar y el valor del nodo a insertar
	 * @param clave clave del nuevo nodo a a�adir, de tipo gen�rico T
	 * @return 0 si el nodo se inserta correctamente; -1 si el nodo a insertar
	 * 		   ya existe; -2 si el nodo a insertar es null
	 */
	public int addNode(T clave) {
		// Se comprueba si la clave es null
		if (clave == null) {
			return -2;
		}
		
		// Se comprueba si el nodo ya existe en el �rbol
		if (findNode(clave) != null) {
			return -1;
		}
		
		// Si el �rbol es vac�o, lo incializa y establece como ra�z
		if (this.raiz == null) {
			
			BSTNode<T> node = new BSTNode<T>(clave);
			this.raiz = node;
		
		// En caso contrario, busca d�nde tiene que insertarlo
		} else {
			addNodeR(this.raiz, clave);
		}
		
		return 0; // se insert� correctamente
	}
	
	/**
	 * M�todo recursivo de inserci�n de nodos.
	 * Si la clave es menor que el valor del nodo a partir del cual
	 * queremos insertar, inserta en la izquierda siempre y cuando no tenga
	 * hijos por la izquierda y devolver 0.
	 * Si la clave es mayor que el valor del nodo a partir del cual 
	 * queremos insertar, inserta a la derecha siempre y cuando 
	 * no tenga hijos por la derecha y devuelve 0.
	 * @param node nodo a partir del cual se quiere insertar el nodo, BSTNode de T
	 * @param clave clave del nuevo nodo a a�adir, de tipo gen�rico T
	 * @return 0 (siempre se va a poder a�adir al recurrir a este m�todo)
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
	 * Si la ra�z no es null, llama a findNodeR() con el nodo a partir del cual
	 * queremos insertar y el valor a buscar
	 * @param clave clave del nodo que se quiere buscar, tipo gen�rico T
	 * @return Nodo cuya clave se busca si lo encuentra (BSTNode); null en otro caso
	 */
	public BSTNode<T> findNode(T clave) {
		// Se comprueba si la clave es null
		if (clave == null) {
			return null;
		}
		
		// Se comprueba si el �rbol est� vac�o
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
	 * queremos insertar busca el valor con la ra�z el hijo izquierdo.
	 * Si el valor es mayor que el valor del nodo a partir del cual
	 * queremos insertar busca el valor con la ra�z el hijo derecho
	 * @param node nodo a partir del cual se quiere buscar el nodo, BSTNode de T
	 * @param clave clave del nodo que se quiere buscar, tipo gen�rico T
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
		
		return null; // si no encontr� el nodo con la clave especificada
	}
	
	/**
	 * Devuelve una cadena con el recorrido preorden, separando los elementos
	 * por tabuladores, eliminando el �ltimo tabulador si lo hubiese.
	 * Pasa por la ra�z, luego recorre el sub�rbol izquierdo y por �ltimo
	 * recorre el sub�rbol derecho
	 * @return Recorrido preorden del �rbol en forma de cadena, tipo String
	 */
	public String preOrder() {
		recorrido = "";
		return preOrderR(this.raiz);
	}
	
	/**
	 * M�todo recursivo que devuelve en forma de cadena el recorrido preorden 
	 * a partir del nodo especificado
	 * @param raiz nodo a partir del cual se realiza el recorrido preorden, BSTNode
	 * @return Recorrido preorden en forma de String del nodo en cuesti�n
	 */
	private String preOrderR(BSTNode<T> raiz) {
		// Se comprueba si la ra�z del �rbol/sub�rbol es null
		if (raiz == null) {
			return recorrido;
			
		// En caso contrario, concatena la ra�z
		} else {
			recorrido = raiz.toString();
		}
		
		// Si hay sub�rbol izquierdo, realiza el recorrido preorden del mismo
		if (raiz.getLeft() != null) {
			recorrido += ("\t" + preOrderR(raiz.getLeft())); 
		}
		
		// Si hay sub�rbol derecho, realiza el recorrido preorden del mismo
		if (raiz.getRight() != null) {
			recorrido += ("\t" + preOrderR(raiz.getRight()));
		}
		
		return recorrido; // devuelve el recorrido calculado
	}
	
	/** 
	 * Devuelve una cadena con el recorrido inorden, separando los elementos
	 * por tabuladores, eliminando el �ltimo tabulador si lo hubiese.
	 * Recorre el sub�rbol izquierdo, luego pasa por la ra�z
	 * y por �ltimo recorre el sub�rbol derecho
	 * @return Recorrido inorden del �rbol en forma de cadena, tipo String
	 */
	public String inOrder() {
		recorrido = "";
		
		// Si el �rbol est� vac�o, devuelve cadena vac�a
		if (this.raiz == null) {
			return recorrido;
		}
		
		// En otro caso, llama al m�todo recursivo y elimina el tabulador final
		return inOrderR(this.raiz).substring(0, recorrido.length() - 1);
	}
	
	/**
	 * M�todo recursivo que devuelve en forma de cadena
	 * el recorrido inorden a partir del nodo especificado
	 * @param raiz nodo a partir del cual se realiza el recorrido inorden
	 * @return Recorrido inorden en forma de String del nodo en cuesti�n
	 */
	private String inOrderR(BSTNode<T> node) {
		// Se comprueba si la ra�z del �rbol/sub�rbol es null
		if (node == null) {
			return recorrido;
		}
		
		// Si hay sub�rbol izquierdo, realiza el recorrido inorden del mismo
		if (node.getLeft() != null) {
			recorrido = inOrderR(node.getLeft());
		}
		
		// Se concatena la ra�z, que no es null
		recorrido += (node.toString() + "\t");
		
		// Si hay sub�rbol derecho, realiza el recorrido inorden del mismo
		if (node.getRight() != null) {
			recorrido = inOrderR(node.getRight());
		}
		
		return recorrido; // devuelve el recorrido calculado
	}
	
	/** 
	 * Devuelve una cadena con el recorrido postorden, separando los elementos
	 * por tabuladores, eliminando el �ltimo tabulador si lo hubiese
	 * Recorre el sub�rbol izquierdo, luego recorre el sub�rbol
	 * derecho y por �ltimo pasa por la ra�z
	 * @return Recorrido postorden del �rbol en forma de cadena, tipo String
	 */
	public String postOrder() {
		recorrido = "";
		
		// Si el �rbol est� vac�o, devuelve cadena vac�a
		if (this.raiz == null) {
			return recorrido;
		}
		
		// En otro caso, llama al m�todo recursivo y elimina el tabulador final
		return postOrderR(this.raiz).substring(0, recorrido.length() - 1);
	}
	
	/**
	 * M�todo recursivo que devuelve en forma de cadena el recorrido postorden 
	 * a partir del nodo especificado
	 * @param raiz nodo a partir del cual se realiza el recorrido postorden
	 * @return Recorrido postorden en forma de String del nodo en cuesti�n
	 */
	private String postOrderR(BSTNode<T> node) {
		// Se comprueba si la ra�z del �rbol/sub�rbol es null
		if (node == null) {
			return recorrido;
		}
		
		// Si hay sub�rbol izquierdo, realiza el recorrido postorden del mismo
		if (node.getLeft() != null) {
			recorrido = postOrderR(node.getLeft()); 
		}
		
		// Si hay sub�rbol derecho, realiza el recorrido postorden del mismo
		if (node.getRight() != null) {
			recorrido = (postOrderR(node.getRight()));
		}
		
		// Se concatena la ra�z, que no es null
		recorrido += (node.toString() + "\t");
		
		return recorrido; // devuelve el recorrido calculado
	}
	
	/**
	 * Elimina el nodo, cuya clave se pasa como par�metro, del �rbol
	 * @param clave clave del nodo a eliminar, de tipo gen�rico T
	 * @return 0 si elimina el nodo con �xito, -1 si la clave no existe
	 * 		   y -2 si la clave o el �rbol son null/vac�os
	 */
	public int removeNode(T clave) {
		// Se comprueba si la clave es null o si el �rbol es vac�o
		if (clave == null || this.raiz == null) {
			return -2;
			
		// Se comprueba si el nodo est� en el �rbol
		} else if (findNode(clave) == null) {
			return -1;
		}
		
		// Se asigna el �rbol actualizado a la ra�z
		this.raiz = removeNodeR(this.raiz, clave);
		
		return 0; // si pudo eliminarse con �xito
	}
	
	/**
	 * Busco entre los nodos hasta encontrar la clave
	 * �Tiene hijo izquierdo? �Tiene hijo derecho? Se devuelve si solo hay uno
	 * Si hay dos, buscar el mayor del sub�rbol izquierdo del nodo a borrar.
	 * Asignar el borrado del sub�rbol izquierdo por la izquierda del nodo.
	 * Se puede hacer un m�todo auxiliar para buscar ese hijo
	 * @param node nodo de referencia para el borrado, BSTNode de T
	 * @param clave clave del nodo a borrar, de tipo gen�rico T
	 * @return El nodo en cuesti�n si hay hijos; null si node no tiene hijos
	 */
	private BSTNode<T> removeNodeR(BSTNode<T> node, T clave) {
		
		// Si la clave es menor que la del nodo, busca por su izquierda
		if (clave.compareTo(node.getInfo()) < 0) {
			// Actualizar� el sub�rbol izquierdo cuando encuentre el nodo
			node.setLeft(removeNodeR(node.getLeft(), clave));
			return node;
		
		// Si la clave es mayor que la del nodo, busca por su derecha	
		} else if (clave.compareTo(node.getInfo()) > 0) {
			// Actualizar� el sub�rbol derecho cuando encuentre el nodo
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
			// Se busca el mayor de los menores (sub�rbol izquierdo)
			BSTNode<T> max = getHighestChild(node.getLeft());
			
			// Se establece por su izquierda el sub�rbol del borrado sin ese m�ximo
			max.setLeft(removeNodeR(node.getLeft(), max.getInfo()));
			
			// Al reemplazarse, tendr� que tener el sub�rbol derecho del borrado
			max.setRight(node.getRight());
			
			return max;	// �rbol actualizado
		}
		
		return null; // cualquier otro caso
	}
	
	/**
	 * Devuelve el hijo mayor del nodo pasado como par�metro. 
	 * Si va encontrando nodos mayores, realiza llamadas recursivas
	 * @param node nodo del que se busca el hijo mayor, de tipo BSTNode de T
	 * @return Hijo con mayor clave del nodo pasado como par�metro, tipo BSTNode
	 */
	private BSTNode<T> getHighestChild(BSTNode<T> node) {
		if (node.getRight() == null) {
			return node;
		} else {
			return getHighestChild(node.getRight());
		}
	}

}