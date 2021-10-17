// Paquete al que pertenece la clase
package monticulos;

/**
 * Clase BinaryHeap para implementar un mont�culo binario de m�nimos
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class BinaryHeap<T extends Comparable<T>> implements PriorityQueue<T> {
	
	private T[] monticulo;		// lista de elementos del mont�culo
	private int numElementos;	// n�mero de elementos insertados hasta el momento
	
	/**
	 * Constructor por defecto que recibe un tama�o como par�metro,
	 * inicializa el vector de elementos y establece el n�mero de ellos a 0
	 * @param n tama�o del mont�culo binario, de tipo n
	 */
	@SuppressWarnings("unchecked")
	public BinaryHeap(int n) {
		monticulo = (T[]) new Comparable[n];
		numElementos = 0;
	}
	
	/**
	 * A�ade un elemento m�s a la cola de prioridad (a la �ltima posici�n)
	 * @param elemento elemento a a�adir, de tipo gen�rico T
	 * @return -3 si no cabe el elemento en la cola de prioridad;
	 *  	   -2 si el elemento es null; -1 si el elemento ya existe;
	 *  	    0 si lo a�ade correctamente
	 */
	@Override
	public int add(T elemento) {
		// Se comprueba si la cola de prioridad ya est� llena
		if (numElementos == monticulo.length) {
			return -3;
		}
		
		// Se comprueba si el elemento a a�adir es null
		if (elemento == null) {
			return -2;
		}
		
		// Se comprueba si el elemento ya existe en la cola de prioridad
		if (found(elemento)) {
			return -1;
		}
		
		// En cualquier otro caso, a�ade el elemento
		monticulo[numElementos] = elemento;
		numElementos++;
		
		// Se realiza un filtrado ascendente para recolocar el mont�culo
		filtradoAscendente(elemento);
		
		return 0;	// a�adido con �xito
	}
	
	/**
	 * Devuelve si se ha encontrado un elemento en el mont�culo o no
	 * @param elemento elemento a encontrar, de tipo gen�rico T
	 * @return true si se encontr�; false si elemento es null o no se encontr�
	 */
	private boolean found(T elemento) {
		if (elemento != null) {
			for (int i = 0; i < numElementos; i++) {
				if (elemento.compareTo(monticulo[i]) == 0) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Devuelve la posici�n del elemento pasado como par�metro en el mont�culo
	 * @param elemento elemento del mont�culo cuya posici�n se quiere conocer (T)
	 * @return Posici�n del elemento si se encontr�; -1 en otro caso
	 */
	private int getPos(T elemento) {
		for (int i = 0; i < numElementos; i++) {
			if (elemento.compareTo(monticulo[i]) == 0) {
				return i;
			}
		}
		
		return -1;	// no se encontr� el elemento
	}
	
	/**
	 * Realiza un filtrado ascendente del mont�culo de m�nimos a partir de la
	 * posici�n pasada como par�metro
	 * @param elemento elemento a partir de la cual se realiza el filtrado (T)
	 */
	protected void filtradoAscendente(T elemento) {
		// Mientras elemento no sea la ra�z y su padre sea mayor...
		while (getParent(elemento) != -1 && monticulo[getParent(elemento)].
				compareTo(elemento) > 0) {
			
			// Realiza el intercambio entre elemento y su padre
			switchAscendente(elemento);
		}
	}
	
	/**
	 * Intercambia el elemento de la posici�n pasada como par�metro
	 * por su respectivo padre o ascendiente
	 * @param elemento elemento a partir del cual se realiza el filtrado (T)
	 */
	private void switchAscendente(T elemento) {
		int pos = getParent(elemento);	// posici�n del padre
		T aux = elemento;
		
		// Realiza el intercambio entre elemento y su padre
		monticulo[getPos(elemento)] = monticulo[getParent(elemento)];
		monticulo[pos] = aux;
	}
	
	/**
	 * Realiza un filtrado descendente en un mont�culo de m�nimos a partir de la
	 * posici�n pasada como par�metro
	 * @param elemento elemento a partir del cual se realiza el filtrado, tipo T
	 */
	protected void filtradoDescendente(T elemento) {
		boolean finished = false;	// eval�a si ha terminado el filtrado
		
		// Se repite una serie de comprobaciones mientras no haya terminado
		while (!finished) {
			
			// Si elemento es un nodo hoja, deja de iterar
			if (getLeftChild(elemento) == -1 && getRightChild(elemento) == -1) {
				
				finished = true;	// ha terminado
			
			// Si es menor que sus hijos, deja de iterar
			} else if (getLeftChild(elemento) != -1 && getRightChild(elemento) != -1 
					&& elemento.compareTo(monticulo[getLeftChild(elemento)]) < 0 
						&& elemento.compareTo(monticulo[getRightChild(elemento)]) < 0) {
				
				finished = true;	// ha terminado
				
			} else {	// Se han comprobado los casos de parada
		
				// Si solo tiene un hijo por la izquierda
				if (getLeftChild(elemento) != -1 && getRightChild(elemento) == -1) {
					
					// Si es mayor que su hijo izquierdo, se intercambian
					if (elemento.compareTo(monticulo[getLeftChild(elemento)]) > 0) {
						
						// Realiza el intercambio entre elemento e hijo izquierdo
						switchLeftDescendente(elemento);
						
					// Si no, termina el filtrado
					} else {
						finished = true;
					}
					
				// En caso de que tenga dos hijos
				} else if (getLeftChild(elemento) != -1 && getRightChild(elemento) != -1) {
					
					// En caso de que solo sea mayor que el hijo izquierdo
					if (elemento.compareTo(monticulo[getLeftChild(elemento)]) > 0
							&& elemento.compareTo(monticulo[getRightChild(elemento)]) < 0) {
						
						// Realiza el intercambio entre elemento e hijo izquierdo
						switchLeftDescendente(elemento);
						
					// En caso de que solo sea mayor que el hijo derecho
					} else if (elemento.compareTo(monticulo[getLeftChild(elemento)]) < 0
							&& elemento.compareTo(monticulo[getRightChild(elemento)]) > 0) {
						
						// Realiza el intercambio entre elemento e hijo derecho
						switchRightDescendente(elemento);
						
					// En caso de que sea mayor que ambos hijos
					} else if (elemento.compareTo(monticulo[getLeftChild(elemento)]) > 0
							&& elemento.compareTo(monticulo[getRightChild(elemento)]) > 0) {
						
						// Realiza el intercambio entre elemento e hijo m�nimo
						switchMinDescendente(elemento);
					} 
				}	
			}
		}
	}
	
	/**
	 * Intercambia el elemento pasado como par�metro por su hijo izquierdo
	 * @param elemento elemento a intercambiar por su hijo izquierdo, de tipo T
	 */
	private void switchLeftDescendente(T elemento) {
		int pos = getLeftChild(elemento);	// posici�n del hijo
		T aux = elemento;
		
		// Realiza el intercambio entre padre e hijo izquierdo
		monticulo[getPos(elemento)] = monticulo[getLeftChild(elemento)];
		monticulo[pos] = aux;
	}
	
	/**
	 * Intercambia el elemento pasado como par�metro por su hijo derecho
	 * @param elemento elemento a intercambiar por su hijo derecho, de tipo T
	 */
	private void switchRightDescendente(T elemento) {
		int pos = getRightChild(elemento);	// posici�n del hijo
		T aux = elemento;
		
		// Realiza el intercambio entre padre e hijo izquierdo
		monticulo[getPos(elemento)] = monticulo[getRightChild(elemento)];
		monticulo[pos] = aux;
	}
	
	/**
	 * Intercambia el elemento pasado como par�metro por su hijo m�nimo
	 * @param elemento elemento a intercambiar por su hijo m�nimo, de tipo T
	 */
	private void switchMinDescendente(T elemento) {
		T min = null; // almacenar� el hijo m�nimo del elemento
		
		// Si el hijo izquierdo es menor que el derecho...
		if (monticulo[getLeftChild(elemento)].compareTo(
				monticulo[getRightChild(elemento)]) < 0) {
			
			// El hijo m�nimo ser�a el izquierdo
			min = monticulo[getLeftChild(elemento)];
			
		// En cambio, si el hijo derecho es menor que el izquierdo...
		} else if (monticulo[getLeftChild(elemento)].compareTo(
				monticulo[getRightChild(elemento)]) > 0) {
			
			// El hijo m�nimo ser�a el derecho
			min = monticulo[getRightChild(elemento)];
		}
		
		T aux = elemento;
		int posMinChild = getPos(min);	// posici�n del hijo m�nimo
		
		// Realiza el intercambio entre padre e hijo m�nimo
		monticulo[getPos(elemento)] = monticulo[posMinChild];
		monticulo[posMinChild] = aux;
	}
	
	/**
	 * Devuelve el padre del elemento pasado como par�metro
	 * @param element elemento cuyo padre se quiere conocer
	 * @return Posici�n del padre (si existe); -1 en otro caso
	 */
	private int getParent(T element) {
		// Si ya es la ra�z, no tiene ascendiente
		if (getPos(element) == 0) {
			return -1;
		}
		
		// En otro caso, se devuelve la posici�n del padre
		return (getPos(element) - 1) / 2;
	}
	
	/**
	 * Devuelve el hijo izquierdo del elemento pasado como par�metro
	 * @param element elemento cuyo hijo izquierdo se quiere conocer
	 * @return Posici�n del hijo izquierdo (si existe); -1 en otro caso
	 */
	private int getLeftChild(T element) {
		int posChild = (getPos(element) * 2) + 1;	// posici�n hijo izquierdo
		
		// Si al calcular la posici�n no sobrepasa el mont�culo, se devuelve
		if (posChild < numElementos) {
			return posChild;
		}
		
		return -1;	// si la posici�n calculada no es coherente (hijo no existe)
	}
	
	/**
	 * Devuelve el hijo derecho del elemento pasado como par�metro
	 * @param element elemento cuyo hijo derecho se quiere conocer
	 * @return Posici�n del hijo derecho (si existe); -1 en otro caso
	 */
	private int getRightChild(T element) {
		int posChild = (getPos(element) * 2) + 2;	// posici�n hijo izquierdo
		
		// Si al calcular la posici�n no sobrepasa el mont�culo, se devuelve
		if (posChild < numElementos) {
			return posChild;
		}
		
		return -1;	// si la posici�n calculada no es coherente (hijo no existe)
	}

	/**
	 * Devuelve la ra�z de la cola de prioridad (posici�n 0)
	 * @return null si la cola de prioridad est� vac�a; la ra�z en otro caso
	 */
	@Override
	public T getTop() {
		// Si es un �rbol vac�o, no hay ra�z
		if (isEmpty()) {
			return null;
		}
		
		T aux = monticulo[0];	// se almacena la ra�z anterior
		
		// Se coloca el �ltimo elemento en la ra�z
		monticulo[0] = monticulo[numElementos - 1];
		monticulo[numElementos - 1] = null;
		numElementos--;
		
		// Realiza un filtrado descendente para recolocar el mont�culo
		filtradoDescendente(monticulo[0]);
		
		return aux;			    // finalmente, devuelve la ra�z a sacar
	}

	/**
	 * Elimina un elemnto de la cola de prioridad
	 * @param elemento elemento a eliminar, de tipo gen�rico T
	 * @return -2 si el elemento es null o el mont�culo est� vac�o;
	 * 		   -1 si el elemento a borrar no existe;
	 *          0 si borra correctamente
	 */
	@Override
	public int remove(T elemento) {
		// Se comprueba si el elemento es null
		if (elemento == null) {
			return -2;
		}
		
		// Se comprueba si el mont�culo est� vac�o
		if (isEmpty()) {
			return -2;
		}
		
		// Se comprueba si ese elemento ya existe en el mont�culo
		if (!found(elemento)) {
			return -1;
		}
		
		int pos = getPos(elemento);	// se almacena la posici�n del elemento
		
		// Se reemplaza el elemento a borrar por el �ltimo del mont�culo
		monticulo[pos] = monticulo[numElementos - 1];
		monticulo[numElementos - 1] = null;
		numElementos--;
		
		// Realiza un filtrado descendente para recolocar el mont�culo
		filtradoDescendente(monticulo[pos]);
		
		return 0; // borrado con �xito
	}

	/**
	 * Devuelve si la cola de prioridad est� vac�a o no
	 * @return true si la cola de prioridad est� vac�a; false si no
	 */
	@Override
	public boolean isEmpty() {
		return numElementos == 0;
	}

	/**
	 * Borra todos los elementos de la cola de prioridad
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		monticulo = (T[]) new Comparable[monticulo.length];
		numElementos = 0;
	}

	/**
	 * Cambia la prioridad o el contenido 
	 * de una posici�n que se pasa como par�metro
	 * @param pos posici�n cuyo contenido se pasa, de tipo int
	 * @param elemento elemento a colocar en la posici�n, de tipo gen�rico T
	 * @return -2 si la posici�n es negativa o mayor que el n�mero de elementos;
	 *         -1 si el elemento pasado como par�metro ya exist�a;
	 *          0 si cambia la prioridad sin ning�n problema
	 */
	@Override
	public int cambiarPrioridad(int pos, T elemento) {
		// Se comprueba si la posici�n corresponde a alg�n elemento
		if (pos < 0 || pos >= numElementos) {
			return -2;
		}
		
		// Se comprueba que el elemento no sea null y exista en el mont�culo
		if (elemento == null || found(elemento)) {
			return -1;
		}
		
		// Se guarda el valor anterior y se cambia la prioridad
		T aux = monticulo[pos];
		monticulo[pos] = elemento;
		
		// Si ahora tiene un valor menor, se realiza un filtrado ascendente
		if (elemento.compareTo(aux) < 0) {
			filtradoAscendente(monticulo[pos]);
			
		// En cambio, si tiene un valor mayor, se realiza un filtrado descendente
		} else if (elemento.compareTo(aux) > 0) {
			filtradoDescendente(monticulo[pos]);
		}
		
		return 0;	// prioridad cambiada con �xito
	}
	
	/**
	 * Muestra los elementos del vector
	 * separados por tabuladores (excepto el �ltimo elemento)
	 * @return Cadena con los elementos del vector, de tipo String
	 */
	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();	// almacena la cadena
		
		// Se recorren los elementos del mont�culo para a�adirlos a cadena
		for (int i = 0; i < numElementos; i++) {
			// Para todos los elementos menos el �ltimo, a�ade un tabulador
			if (i != numElementos - 1) {
				cadena.append(monticulo[i].toString() + "\t");
			// En el caso del �ltimo, no a�ade el tabulador
			} else {
				cadena.append(monticulo[i].toString());
			}
		}
		
		// Finalmente, se devuelve la cadena compuesta
		return cadena.toString();
	}

}