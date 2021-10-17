// Paquete al que pertenece la clase
package avl;

/**
 * Implementa un �rbol AVL
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class AVLTree<T extends Comparable<T>> {
	
	private AVLNode<T> raiz;	// nodo ra�z del �rbol
	private String recorrido;	// cadena para los recorridos
	private String camino;		// camino entre dos nodos
	
	public AVLTree() {
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
			
			AVLNode<T> node = new AVLNode<T>(clave);
			this.raiz = node;
		
		// En caso contrario, busca d�nde tiene que insertarlo
		} else {
			this.raiz = addNodeR(this.raiz, clave);
		}
		
		return 0;	// se insert� correctamente
	}
	
	/**
	 * M�todo recursivo de inserci�n de nodos.
	 * Si la clave es menor que el valor del nodo a partir del cual
	 * queremos insertar, inserta en la izquierda siempre y cuando no tenga
	 * hijos por la izquierda y devolver 0
	 * Si la clave es mayor que el valor
	 * del nodo a partir del cual queremos insertar, inserta a la derecha
	 * siempre y cuando no tenga hijos por la derecha y devolver 0
	 * @param node nodo a partir del cual se quiere insertar el nodo, tipo gen�rico
	 * @param clave clave del nuevo nodo a a�adir, de tipo gen�rico T
	 * @return 0 (siempre se va a poder a�adir al recurrir a este m�todo)
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
	 * Si la ra�z es null o la clave es null devuelve null
	 * Si la ra�z no es null llama a findNodeR con el nodo a partir del cual
	 * queremos insertar y el valor a buscar
	 * @param clave clave del nodo que se quiere buscar
	 * @return Nodo cuya clave se busca si lo encuentra; null en otro caso
	 */
	public AVLNode<T> findNode(T clave) {
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
	 * queremos insertar devuelve el nodo
	 * Si el valor es menor que el valor del nodo a partir del cual
	 * queremos insertar busca el valor con la ra�z el hijo izquierdo
	 * Si el valor es mayor que el valor del nodo a partir del cual
	 * queremos insertar busca el valor con la ra�z el hijo derecho
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
		
		return null;	// si no encontr� el nodo con la clave especificada
	}
	
	/**
	 * Elimina el nodo, cuya clave se pasa como par�metro, del �rbol.
	 * Devuelve 0 si lo hace bien; -1 si la clave no existe; 
	 * -2 si la clave o el �rbol en cuesti�n son null/vac�os.
	 * @param info clave del nodo a eliminar, de tipo gen�rico T
	 * @return 0 si elimina el nodo con �xito, -1 si la clave no existe
	 * 		   y -2 si la clave o el �rbol son null/vac�os
	 */
	public int removeNode(T info) {
		// Se comprueba si la clave es null y si el �rbol est� vac�o
		if (info == null || this.raiz == null) {
			return -2;
		}
		
		// Se comprueba que el nodo exista en el �rbol
		if (findNode(info) == null) {
			return -1;
		}
		
		// Se asigna el �rbol actualizado a la ra�z
		this.raiz = removeNodeR(this.raiz, info);
		
		return 0;	// si pudo eliminarse con �xito
	}
	
	/**
	 * Busco entre los nodos hasta encontrar la clave
	 * �Tiene hijo izquierdo? �Tiene hijo derecho? Se devuelve si solo hay uno.
	 * Si hay dos, buscar el mayor del sub�rbol izquierdo del nodo a borrar.
	 * Se asigna el borrado del sub�rbol izquierdo por la izquierda del nodo.
	 * @param node nodo de referencia para el borrado, AVLNode de T
	 * @param info clave del nodo a borrar, de tipo gen�rico T
	 * @return El nodo en cuesti�n si hay hijos; null si node no tiene hijos
	 */
	private AVLNode<T> removeNodeR(AVLNode<T> node, T info) {
		
		// Si la clave es menor que la del nodo, busca por su izquierda
		if (info.compareTo(node.getInfo()) < 0) {
			// Actualizar� el sub�rbol izquierdo cuando encuentre el nodo
			node.setLeft(removeNodeR(node.getLeft(), info));
			return updateTree(node);
		
		// Si la clave es mayor que la del nodo, busca por su derecha	
		} else if (info.compareTo(node.getInfo()) > 0) {
			// Actualizar� el sub�rbol derecho cuando encuentre el nodo
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
			// Se busca el mayor de los menores (sub�rbol izquierdo)
			AVLNode<T> max = getHighestChild(node.getLeft());
			
			// Se establece por su izquierda el sub�rbol del borrado sin ese m�ximo
			max.setLeft(removeNodeR(node.getLeft(), max.getInfo()));
			
			// Al reemplazarse, tendr� que tener el sub�rbol derecho del borrado
			max.setRight(node.getRight());
			
			return updateTree(max);	// �rbol actualizado
		}
		
		return null;	// cualquier otro caso
	}
	
	/**
	 * Devuelve el hijo mayor del nodo pasado como par�metro. Si va encontrando
	 * nodos mayores, realiza llamadas recursivas
	 * @param node nodo del que se busca el hijo mayor, AVLNode de T
	 * @return Hijo con mayor clave del nodo pasado como par�metro, tipo AVLNode
	 */
	private AVLNode<T> getHighestChild(AVLNode<T> node) {
		if (node.getRight() == null) {
			return node;
		} else {
			return getHighestChild(node.getRight());
		}
	}
	
	/**
	 * Calcula el factor de balance y la altura del nodo pasado como par�metro.
	 * Adem�s, realiza las rotaciones adecuadas si corresponde y lo devuelve
	 * @param node nodo que se quiere actualizar con sus sub�rboles, tipo AVLNode
	 * @return Nodo actualizado con las rotaciones oportunas, si fueron realizadas
	 */
	private AVLNode<T> updateTree(AVLNode<T> node) {
		// Primero, se actualiza el FB del nodo
		node.calcularFactorBalanceAltura();
		
		// Se comprueba si |FB| > 1. Si es as�, eval�a FB de sus hijos
		if (node.getFB() == -2) {
			if (node.getLeft().getFB() == 1) {
				// Rotaci�n Doble Izquierda
				node = doubleLeftRotation(node);
			} else {
				// Rotaci�n Simple Izquierda
				node = singleLeftRotation(node);
			}
			
		} else if (node.getFB() == 2) {
			if (node.getRight().getFB() == -1) {
				// Rotaci�n Doble Derecha
				node = doubleRightRotation(node);
			} else {
				// Rotaci�n Simple Derecha
				node = singleRightRotation(node);
			}
		}
		
		return node;	// se devuelve el nodo/sub�rbol actualizado
	}
	
	/**
	 * Hace la rotaci�n simple a la izquierda del nodo y devuelve el nodo rotado
	 * @param node nodo que hay que rotar a la izquierda, de tipo AVLNode de T
	 * @return Nodo tras aplicar la rotaci�n, de tipo AVLNode de T
	 */
	private AVLNode<T> singleLeftRotation(AVLNode<T> node) {
		AVLNode<T> aux = node.getLeft();
		node.setLeft(aux.getRight());
		node = updateTree(node);
		aux.setRight(node);
		
		return updateTree(aux);
	}
	
	/**
	 * Hace la rotaci�n simple a la derecha del nodo y devuelve el nodo rotado
	 * @param node nodo que hay que rotar a la derecha, de tipo AVLNode de T
	 * @return Nodo tras aplicar la rotaci�n, de tipo AVLNode de T
	 */
	private AVLNode<T> singleRightRotation(AVLNode<T> node) {
		AVLNode<T> aux = node.getRight();
		node.setRight(aux.getLeft());
		node = updateTree(node);
		aux.setLeft(node);
		
		return updateTree(aux);
	}
	
	/**
	 * Hace la rotaci�n doble a la izquierda del nodo y devuelve el nodo rotado
	 * @param node nodo que hay que rotar a la izquierda, de tipo AVLNode de T
	 * @return Nodo tras aplicar la rotaci�n, de tipo AVLNode de T
	 */
	private AVLNode<T> doubleLeftRotation(AVLNode<T> node) {
		node.setLeft(singleRightRotation(node.getLeft()));
		node = singleLeftRotation(node);
		
		return updateTree(node);
	}
	
	/**
	 * Hace la rotaci�n doble a la derecha del nodo y devuelve el nodo rotado
	 * @param node nodo que hay que rotar a la derecha, de tipo AVLNode de T
	 * @return Nodo tras aplicar la rotaci�n, de tipo AVLNode de T
	 */
	private AVLNode<T> doubleRightRotation(AVLNode<T> node) {
		node.setRight(singleLeftRotation(node.getRight()));
		node = singleRightRotation(node);
		
		return updateTree(node);
	}
	
	/**
	 * Devuelve si un nodo es ascendiente directo de otro
	 * @param padre nodo padre, de tipo gen�rico T
	 * @param hijo nodo hijo, de tipo gen�rico T
	 * @return Si el nodo padre es ascendiente directo del nodo hijo (boolean)
	 */
	public boolean esAscendienteDirecto(T padre, T hijo) {
		AVLNode<T> nodoPadre = findNode(padre);
		AVLNode<T> nodoHijo = findNode(hijo);
		
		// Siempre que ambos nodos existan...
		if (nodoPadre != null && nodoHijo != null) {
			// Se comprueba si el hijo est� a la izda. o a la dcha. del padre
			if (nodoPadre.getLeft().equals(nodoHijo) 
					|| nodoPadre.getRight().equals(nodoHijo)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Devuelve el n�mero de aristas entre un nodo y otro
	 * @param a clave del nodo A, de tipo T
	 * @param b clave del nodo B, de tipo T
	 * @return N�mero de aristas entre los nodos A y B (tipo int);
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
		
		// Llamada al m�todo recursivo
		numAristasR(nodoA, nodoB, c, cadena);
		
		// Si hay alguna arista, se escoge el �ltimo contador de la cadena
		if (cadena.length() > 0) {
			return Integer.parseInt(cadena.substring(cadena.length() - 1, 
					cadena.length()));
		}
		
		return c;	// si no encontr� ninguna arista (valdr� 0)
	}
	
	/**
	 * M�todo recursivo para contar el n�mero de aristas entre nodo y nodo
	 * @param a nodo origen, de tipo AVLNode de T
	 * @param b nodo destino, de tipo AVLNode de T
	 * @param c contador de aristas, de tipo int
	 * @param cad cadena de contadores, de tipo StringBuilder
	 * @return N�mero de aristas, cuyo valor se almacena en el par�metro c
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
		
		// Llamada al m�todo recursivo
		caminoR(nodoA, nodoB);
		
		// Si hay camino, se elimina el tabulador final y se devuelve
		if (camino.length() > 0) {
			return camino.substring(0, camino.length() - 1);
		}
		
		return camino;	// si no hay camino (cadena vac�a)
	}
	
	/**
	 * M�todo recursivo para el camino entre dos nodos de �rbol AVL
	 * @param nodoA nodo A origen, de tipo AVLNode
	 * @param nodoB nodo B destino, de tipo AVLNode
	 * @return Cadena con el camino de nodos entre A y B, de tipo String
	 */
	private String caminoR(AVLNode<T> nodoA, AVLNode<T> nodoB) {
		// Se comprueba si alguno de los nodos es null
		if (nodoA == null || nodoB == null) {
			return camino;
		}
		
		// Si B est� a la izquierda de A, se a�ade al camino y repite con su left
		if (nodoB.getInfo().compareTo(nodoA.getInfo()) < 0) {
			camino += (nodoA.toString() + "\t"); 
			caminoR(nodoA.getLeft(), nodoB);
			
		// Si B est� a la derecha de A, se a�ade al camino y repite con su right
		} else if (nodoB.getInfo().compareTo(nodoA.getInfo()) > 0) {
			camino += (nodoA.toString() + "\t");
			caminoR(nodoA.getRight(), nodoB);
		
		// Si son el mismo nodo, �nicamente se a�ade al camino
		} else {
			camino += (nodoB.toString() + "\t");
		}
		
		return camino;	// se devuelve el camino generado en la cadena
	}
	
	/**
	 * Devuelve una cadena con el recorrido preorden, separando los elementos
	 * por tabuladores, eliminando el �ltimo tabulador si lo hubiese.
	 * Pasa por la ra�z, luego recorre el sub�rbol izquierdo, y por �ltimo
	 * recorre el sub�rbol derecho
	 * @return Recorrido preorden del �rbol en forma de cadena, tipo String
	 */
	public String preOrder() {
		recorrido = "";
		return preOrderR(this.raiz);
	}
	
	/**
	 * M�todo recursivo que devuelve en forma de cadena
	 * el recorrido preorden a partir del nodo especificado
	 * @param raiz nodo a partir del cual se realiza el recorrido preorden
	 * @return Recorrido preorden en forma de String del nodo en cuesti�n
	 */
	private String preOrderR(AVLNode<T> raiz) {
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
		
		return recorrido;	// devuelve el recorrido calculado
	}
	
	/** 
	 * Devuelve una cadena con el recorrido inorden, separando los elementos
	 * por tabuladores, eliminando el �ltimo tabulador si lo hubiese.
	 * Recorre el sub�rbol izquierdo, luego pasa por la ra�z,
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
	private String inOrderR(AVLNode<T> node) {
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
		
		return recorrido;	// devuelve el recorrido calculado
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
	 * M�todo recursivo que devuelve en forma de cadena
	 * el recorrido postorden a partir del nodo especificado
	 * @param raiz nodo a partir del cual se realiza el recorrido postorden
	 * @return Recorrido postorden en forma de String del nodo en cuesti�n
	 */
	private String postOrderR(AVLNode<T> node) {
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
		
		return recorrido;	// devuelve el recorrido calculado
	}

}