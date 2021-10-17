// Paquete al que pertenece la clase
package avl;

/**
 * Implementa un árbol AVL
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class AVLTree<T extends Comparable<T>> {
	
	private AVLNode<T> raiz;	// nodo raíz del árbol
	private String recorrido;	// cadena para los recorridos
	private String camino;		// camino entre dos nodos
	
	public AVLTree() {
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
			
			AVLNode<T> node = new AVLNode<T>(clave);
			this.raiz = node;
		
		// En caso contrario, busca dónde tiene que insertarlo
		} else {
			this.raiz = addNodeR(this.raiz, clave);
		}
		
		return 0;	// se insertó correctamente
	}
	
	/**
	 * Método recursivo de inserción de nodos.
	 * Si la clave es menor que el valor del nodo a partir del cual
	 * queremos insertar, inserta en la izquierda siempre y cuando no tenga
	 * hijos por la izquierda y devolver 0
	 * Si la clave es mayor que el valor
	 * del nodo a partir del cual queremos insertar, inserta a la derecha
	 * siempre y cuando no tenga hijos por la derecha y devolver 0
	 * @param node nodo a partir del cual se quiere insertar el nodo, tipo genérico
	 * @param clave clave del nuevo nodo a añadir, de tipo genérico T
	 * @return 0 (siempre se va a poder añadir al recurrir a este método)
	 */
	public AVLNode<T> addNodeR(AVLNode<T> node, T clave) {
		// Si la clave es menor, busca por la izquierda del nodo
		if (clave.compareTo(node.getInfo()) < 0) {
			// Si no hay hijo izquierdo, se inserta el nodo
			if (node.getLeft() == null) {
				node.setLeft(new AVLNode<T>(clave));
			
			// En caso contrario, vuelve a comparar con el hijo izquierdo
			} else {
				node.setLeft(addNodeR(node.getLeft(), clave));
			}
			
		// Si la clave es mayor, busca por la derecha del nodo
		} else if (clave.compareTo(node.getInfo()) > 0) {
			// Si no hay hijo derecho, se inserta el nodo
			if (node.getRight() == null) {
				node.setRight(new AVLNode<T>(clave));
				
			// En caso contrario, vuelve a comparar con el hijo derecho
			} else {
				node.setRight(addNodeR(node.getRight(), clave));
			}
		}
		
		return updateTree(node);	// se actualiza el nodo y se devuelve
	}
	
	/**
	 * Si la raíz es null o la clave es null devuelve null
	 * Si la raíz no es null llama a findNodeR con el nodo a partir del cual
	 * queremos insertar y el valor a buscar
	 * @param clave clave del nodo que se quiere buscar
	 * @return Nodo cuya clave se busca si lo encuentra; null en otro caso
	 */
	public AVLNode<T> findNode(T clave) {
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
	 * queremos insertar devuelve el nodo
	 * Si el valor es menor que el valor del nodo a partir del cual
	 * queremos insertar busca el valor con la raíz el hijo izquierdo
	 * Si el valor es mayor que el valor del nodo a partir del cual
	 * queremos insertar busca el valor con la raíz el hijo derecho
	 * @param node nodo a partir del cual se quiere buscar el nodo
	 * @param clave clave del nodo que se quiere buscar
	 * @return Nodo a buscar, si lo encuentra, de tipo AVLNode de T
	 */
	private AVLNode<T> findNodeR(AVLNode<T> node, T clave) {
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
		
		return null;	// si no encontró el nodo con la clave especificada
	}
	
	/**
	 * Elimina el nodo, cuya clave se pasa como parámetro, del árbol.
	 * Devuelve 0 si lo hace bien; -1 si la clave no existe; 
	 * -2 si la clave o el árbol en cuestión son null/vacíos.
	 * @param info clave del nodo a eliminar, de tipo genérico T
	 * @return 0 si elimina el nodo con éxito, -1 si la clave no existe
	 * 		   y -2 si la clave o el árbol son null/vacíos
	 */
	public int removeNode(T info) {
		// Se comprueba si la clave es null y si el árbol está vacío
		if (info == null || this.raiz == null) {
			return -2;
		}
		
		// Se comprueba que el nodo exista en el árbol
		if (findNode(info) == null) {
			return -1;
		}
		
		// Se asigna el árbol actualizado a la raíz
		this.raiz = removeNodeR(this.raiz, info);
		
		return 0;	// si pudo eliminarse con éxito
	}
	
	/**
	 * Busco entre los nodos hasta encontrar la clave
	 * ¿Tiene hijo izquierdo? ¿Tiene hijo derecho? Se devuelve si solo hay uno.
	 * Si hay dos, buscar el mayor del subárbol izquierdo del nodo a borrar.
	 * Se asigna el borrado del subárbol izquierdo por la izquierda del nodo.
	 * @param node nodo de referencia para el borrado, AVLNode de T
	 * @param info clave del nodo a borrar, de tipo genérico T
	 * @return El nodo en cuestión si hay hijos; null si node no tiene hijos
	 */
	private AVLNode<T> removeNodeR(AVLNode<T> node, T info) {
		
		// Si la clave es menor que la del nodo, busca por su izquierda
		if (info.compareTo(node.getInfo()) < 0) {
			// Actualizará el subárbol izquierdo cuando encuentre el nodo
			node.setLeft(removeNodeR(node.getLeft(), info));
			return updateTree(node);
		
		// Si la clave es mayor que la del nodo, busca por su derecha	
		} else if (info.compareTo(node.getInfo()) > 0) {
			// Actualizará el subárbol derecho cuando encuentre el nodo
			node.setRight(removeNodeR(node.getRight(), info));
			return updateTree(node);
		}
		
		// Una vez encuentre el nodo a borrar... Casos posibles:
		
		// El nodo no tiene hijos; devuelve null
		if (node.getLeft() == null && node.getRight() == null) {
			return null;
			
		// El nodo solo tiene un hijo por la izquierda
		} else if (node.getLeft() != null && node.getRight() == null) {
			return updateTree(node.getLeft());
			
		// El nodo solo tiene un hijo por la derecha
		} else if (node.getRight() != null && node.getLeft() == null) {
			return updateTree(node.getRight());
		}
		
		// El nodo tiene dos hijos, uno a la izquierda y otro a la derecha
		if (node.getLeft() != null && node.getRight() != null) {
			// Se busca el mayor de los menores (subárbol izquierdo)
			AVLNode<T> max = getHighestChild(node.getLeft());
			
			// Se establece por su izquierda el subárbol del borrado sin ese máximo
			max.setLeft(removeNodeR(node.getLeft(), max.getInfo()));
			
			// Al reemplazarse, tendrá que tener el subárbol derecho del borrado
			max.setRight(node.getRight());
			
			return updateTree(max);	// árbol actualizado
		}
		
		return null;	// cualquier otro caso
	}
	
	/**
	 * Devuelve el hijo mayor del nodo pasado como parámetro. Si va encontrando
	 * nodos mayores, realiza llamadas recursivas
	 * @param node nodo del que se busca el hijo mayor, AVLNode de T
	 * @return Hijo con mayor clave del nodo pasado como parámetro, tipo AVLNode
	 */
	private AVLNode<T> getHighestChild(AVLNode<T> node) {
		if (node.getRight() == null) {
			return node;
		} else {
			return getHighestChild(node.getRight());
		}
	}
	
	/**
	 * Calcula el factor de balance y la altura del nodo pasado como parámetro.
	 * Además, realiza las rotaciones adecuadas si corresponde y lo devuelve
	 * @param node nodo que se quiere actualizar con sus subárboles, tipo AVLNode
	 * @return Nodo actualizado con las rotaciones oportunas, si fueron realizadas
	 */
	private AVLNode<T> updateTree(AVLNode<T> node) {
		// Primero, se actualiza el FB del nodo
		node.calcularFactorBalanceAltura();
		
		// Se comprueba si |FB| > 1. Si es así, evalúa FB de sus hijos
		if (node.getFB() == -2) {
			if (node.getLeft().getFB() == 1) {
				// Rotación Doble Izquierda
				node = doubleLeftRotation(node);
			} else {
				// Rotación Simple Izquierda
				node = singleLeftRotation(node);
			}
			
		} else if (node.getFB() == 2) {
			if (node.getRight().getFB() == -1) {
				// Rotación Doble Derecha
				node = doubleRightRotation(node);
			} else {
				// Rotación Simple Derecha
				node = singleRightRotation(node);
			}
		}
		
		return node;	// se devuelve el nodo/subárbol actualizado
	}
	
	/**
	 * Hace la rotación simple a la izquierda del nodo y devuelve el nodo rotado
	 * @param node nodo que hay que rotar a la izquierda, de tipo AVLNode de T
	 * @return Nodo tras aplicar la rotación, de tipo AVLNode de T
	 */
	private AVLNode<T> singleLeftRotation(AVLNode<T> node) {
		AVLNode<T> aux = node.getLeft();
		node.setLeft(aux.getRight());
		node = updateTree(node);
		aux.setRight(node);
		
		return updateTree(aux);
	}
	
	/**
	 * Hace la rotación simple a la derecha del nodo y devuelve el nodo rotado
	 * @param node nodo que hay que rotar a la derecha, de tipo AVLNode de T
	 * @return Nodo tras aplicar la rotación, de tipo AVLNode de T
	 */
	private AVLNode<T> singleRightRotation(AVLNode<T> node) {
		AVLNode<T> aux = node.getRight();
		node.setRight(aux.getLeft());
		node = updateTree(node);
		aux.setLeft(node);
		
		return updateTree(aux);
	}
	
	/**
	 * Hace la rotación doble a la izquierda del nodo y devuelve el nodo rotado
	 * @param node nodo que hay que rotar a la izquierda, de tipo AVLNode de T
	 * @return Nodo tras aplicar la rotación, de tipo AVLNode de T
	 */
	private AVLNode<T> doubleLeftRotation(AVLNode<T> node) {
		node.setLeft(singleRightRotation(node.getLeft()));
		node = singleLeftRotation(node);
		
		return updateTree(node);
	}
	
	/**
	 * Hace la rotación doble a la derecha del nodo y devuelve el nodo rotado
	 * @param node nodo que hay que rotar a la derecha, de tipo AVLNode de T
	 * @return Nodo tras aplicar la rotación, de tipo AVLNode de T
	 */
	private AVLNode<T> doubleRightRotation(AVLNode<T> node) {
		node.setRight(singleLeftRotation(node.getRight()));
		node = singleRightRotation(node);
		
		return updateTree(node);
	}
	
	/**
	 * Devuelve si un nodo es ascendiente directo de otro
	 * @param padre nodo padre, de tipo genérico T
	 * @param hijo nodo hijo, de tipo genérico T
	 * @return Si el nodo padre es ascendiente directo del nodo hijo (boolean)
	 */
	public boolean esAscendienteDirecto(T padre, T hijo) {
		AVLNode<T> nodoPadre = findNode(padre);
		AVLNode<T> nodoHijo = findNode(hijo);
		
		// Siempre que ambos nodos existan...
		if (nodoPadre != null && nodoHijo != null) {
			// Se comprueba si el hijo está a la izda. o a la dcha. del padre
			if (nodoPadre.getLeft().equals(nodoHijo) 
					|| nodoPadre.getRight().equals(nodoHijo)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Devuelve el número de aristas entre un nodo y otro
	 * @param a clave del nodo A, de tipo T
	 * @param b clave del nodo B, de tipo T
	 * @return Número de aristas entre los nodos A y B (tipo int);
	 * 		   -1 si alguno de los nodos no existe
	 */
	public int numAristas(T a, T b) {
		AVLNode<T> nodoA = findNode(a);
		AVLNode<T> nodoB = findNode(b);
	
		// Se comprueba si alguno de los nodos no existe
		if (nodoA == null || nodoB == null) {
			return -1;
		}
		
		int c = 0;									 // contador de aristas
		StringBuilder cadena = new StringBuilder();	 // cadena de contadores
		
		// Llamada al método recursivo
		numAristasR(nodoA, nodoB, c, cadena);
		
		// Si hay alguna arista, se escoge el último contador de la cadena
		if (cadena.length() > 0) {
			return Integer.parseInt(cadena.substring(cadena.length() - 1, 
					cadena.length()));
		}
		
		return c;	// si no encontró ninguna arista (valdrá 0)
	}
	
	/**
	 * Método recursivo para contar el número de aristas entre nodo y nodo
	 * @param a nodo origen, de tipo AVLNode de T
	 * @param b nodo destino, de tipo AVLNode de T
	 * @param c contador de aristas, de tipo int
	 * @param cad cadena de contadores, de tipo StringBuilder
	 * @return Número de aristas, cuyo valor se almacena en el parámetro c
	 */
	private int numAristasR(AVLNode<T> a, AVLNode<T> b, int c, StringBuilder cad) {
		// Si las claves coinciden, lo ha encontrado y lo devuelve
		if (a.getInfo().compareTo(b.getInfo()) == 0) {
			return c;

		// Si la clave es menor, incrementa y sigue buscando por la izquierda del nodo
		} else if (a.getLeft() != null && b.getInfo().compareTo(a.getInfo()) < 0 ) {
			c++;
			cad.append(c);
			numAristasR(a.getLeft(), b, c, cad);

		// Si la clave es mayor, incrementa y sigue buscando por la derecha del nodo
		} else if (a.getRight() != null && b.getInfo().compareTo(a.getInfo()) > 0) {
			c++;
			cad.append(c);
			numAristasR(a.getRight(), b, c, cad);
		}
		
		return c;
	}
	
	/**
	 * Devuelve el camino de nodos entre un nodo y otro
	 * @param a nodo A, de tipo AVLNode
	 * @param b nodo B, de tipo AVLNode
	 * @return Cadena con el camino de nodos entre A y B, de tipo String
	 */
	public String camino(T a, T b) {
		AVLNode<T> nodoA = findNode(a);
		AVLNode<T> nodoB = findNode(b);
		
		camino = "";	// reseteo de la variable global
		
		// Llamada al método recursivo
		caminoR(nodoA, nodoB);
		
		// Si hay camino, se elimina el tabulador final y se devuelve
		if (camino.length() > 0) {
			return camino.substring(0, camino.length() - 1);
		}
		
		return camino;	// si no hay camino (cadena vacía)
	}
	
	/**
	 * Método recursivo para el camino entre dos nodos de árbol AVL
	 * @param nodoA nodo A origen, de tipo AVLNode
	 * @param nodoB nodo B destino, de tipo AVLNode
	 * @return Cadena con el camino de nodos entre A y B, de tipo String
	 */
	private String caminoR(AVLNode<T> nodoA, AVLNode<T> nodoB) {
		// Se comprueba si alguno de los nodos es null
		if (nodoA == null || nodoB == null) {
			return camino;
		}
		
		// Si B está a la izquierda de A, se añade al camino y repite con su left
		if (nodoB.getInfo().compareTo(nodoA.getInfo()) < 0) {
			camino += (nodoA.toString() + "\t"); 
			caminoR(nodoA.getLeft(), nodoB);
			
		// Si B está a la derecha de A, se añade al camino y repite con su right
		} else if (nodoB.getInfo().compareTo(nodoA.getInfo()) > 0) {
			camino += (nodoA.toString() + "\t");
			caminoR(nodoA.getRight(), nodoB);
		
		// Si son el mismo nodo, únicamente se añade al camino
		} else {
			camino += (nodoB.toString() + "\t");
		}
		
		return camino;	// se devuelve el camino generado en la cadena
	}
	
	/**
	 * Devuelve una cadena con el recorrido preorden, separando los elementos
	 * por tabuladores, eliminando el último tabulador si lo hubiese.
	 * Pasa por la raíz, luego recorre el subárbol izquierdo, y por último
	 * recorre el subárbol derecho
	 * @return Recorrido preorden del árbol en forma de cadena, tipo String
	 */
	public String preOrder() {
		recorrido = "";
		return preOrderR(this.raiz);
	}
	
	/**
	 * Método recursivo que devuelve en forma de cadena
	 * el recorrido preorden a partir del nodo especificado
	 * @param raiz nodo a partir del cual se realiza el recorrido preorden
	 * @return Recorrido preorden en forma de String del nodo en cuestión
	 */
	private String preOrderR(AVLNode<T> raiz) {
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
		
		return recorrido;	// devuelve el recorrido calculado
	}
	
	/** 
	 * Devuelve una cadena con el recorrido inorden, separando los elementos
	 * por tabuladores, eliminando el último tabulador si lo hubiese.
	 * Recorre el subárbol izquierdo, luego pasa por la raíz,
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
	private String inOrderR(AVLNode<T> node) {
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
		
		return recorrido;	// devuelve el recorrido calculado
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
	 * Método recursivo que devuelve en forma de cadena
	 * el recorrido postorden a partir del nodo especificado
	 * @param raiz nodo a partir del cual se realiza el recorrido postorden
	 * @return Recorrido postorden en forma de String del nodo en cuestión
	 */
	private String postOrderR(AVLNode<T> node) {
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
		
		return recorrido;	// devuelve el recorrido calculado
	}

}