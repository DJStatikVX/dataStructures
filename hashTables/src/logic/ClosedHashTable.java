// Paquete al que pertenece la clase
package logic;

// Importacion de clases
import java.lang.reflect.Array;

/**
 * Clase ClosedHashTable que implementa una Tabla Hash cerrada
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class ClosedHashTable<T> extends AbstractHash<T> {
	
	private int numElementos;		// n�mero de elementos hasta el momento
	private HashNode<T> tabla[];    // Tabla Hash
	private int tipoExploracion;	// 1 - lineal, 2 - cuadr�tica, 3 - disp. doble
	private double minlf;			// factor de carga m�nimo
	private double maxlf;			// factor de carga m�ximo
	
	/**
	 * Constante para definir el factor m�nimo de carga
	 */
	public static final double MINIMUM_LF = 0.16;
	
	/**
	 * Constante para definir el factor m�ximo de carga
	 */
	public static final double MAXIMUM_LF = 0.5;
	
	/**
	 * Constante para definir el tipo de exploraci�n lineal
	 */
	public static final int LINEAL = 1;
	
	/**
	 * Constante para definir el tipo de exploraci�n cuadr�tica
	 */
	public static final int CUADRATICA = 2;
	
	/**
	 * Constante para definir el tipo de exploraci�n por dispersi�n doble
	 */
	public static final int DOBLE = 3;
	
	/**
	 * Constructor que recibe como par�metros el tama�o y el tipo de exploraci�n
	 * @param tam tama�o a asignar a la nueva Tabla Hash cerrada, de tipo int
	 * @param tipo tipo de exploraci�n a utilizar en la nueva tabla, tipo int
	 */
	@SuppressWarnings("unchecked")
	public ClosedHashTable(int tam, int tipo) {
		this.numElementos = 0;
		this.tipoExploracion = tipo;
		this.minlf = MINIMUM_LF;
		this.maxlf = MAXIMUM_LF;
		
		// Si el tama�o pasado no es primo, se establece con el siguiente primo
		if (!isPositivePrime(tam)) {
			tam = nextPrimeNumber(tam);
		}
		
		// Crea la tabla de elementos
		this.tabla = (HashNode<T>[]) Array.newInstance(HashNode.class, tam);
		
		// Llena la tabla con huecos para elementos
		for (int i = 0; i < tam; i++) {
			tabla[i] = new HashNode<T>();
		}
	}
	
	/**
	 * Constructor que recibe como par�metros el tama�o, el tipo de exploraci�n,
	 * el factor de carga m�nimo y el factor de carga m�ximo
	 * @param tam tama�o a asignar a la nueva Tabla Hash cerrada, de tipo int
	 * @param tipo tipo de exploraci�n a utilizar en la nueva tabla, tipo int
	 * @param minlf factor de carga m�nimo, de tipo double
	 * @param maxlf factor de carga m�ximo, de tipo double
	 */
	@SuppressWarnings("unchecked")
	public ClosedHashTable(int tam, int tipo, double minlf, double maxlf) {
		this.numElementos = 0;
		this.tipoExploracion = tipo;
		this.minlf = minlf;
		this.maxlf = maxlf;
		
		// Si el tama�o pasado no es primo, se establece con el siguiente primo
		if (!isPositivePrime(tam)) {
			tam = nextPrimeNumber(tam);
		}
		
		// Crea la tabla de elementos
		this.tabla = (HashNode<T>[]) Array.newInstance(HashNode.class, tam);
		
		// Llena la tabla con huecos para elementos
		for (int i = 0; i < tam; i++) {
			tabla[i] = new HashNode<T>();
		}
	}

	/**
	 * Devuelve el n�mero de elementos insertados hasta el momento en la Tabla Hash
	 * @return N�mero de elementos insertados en la tabla, de tipo int
	 */
	@Override
	public int getNumOfElems() {
		return this.numElementos;
	}

	/**
	 * Devuelve el tama�o total de la Tabla Hash
	 * @return Tama�o total de la Tabla Hash, de tipo int
	 */
	@Override
	public int getSize() {
		return this.tabla.length;
	}
	
	/**
	 * Calcula y devuelve el factor de carga actual de la tabla
	 * @return Factor de carga actual en la tabla, de tipo double
	 */
	private double getFC() {
		return (double) (numElementos * 1.0 / getSize() * 1.0);
	}

	/**
	 * A�ade un elemento a la Tabla Hash, buscando una posici�n para el mismo
	 * @param elem elemento a a�adir, de tipo gen�rico T
	 * @return 0 si insert� el elemento correctamente;
	 *        -1 si el elemento pasado como par�metro es null;
	 */
	@Override
	public int add(T elem) {
		// Se comprueba si el elemento a a�adir es null
		if (elem == null) {
			return -1;
		}
		
		// Posici�n del intento 0
		int pos = fHash(elem);
		
		// Si es posible, se inserta el elemento
		if (tabla[pos].getStatus() == HashNode.BORRADO 
				|| tabla[pos].getStatus() == HashNode.VACIO) {
			
			tabla[pos] = new HashNode<T>();
			tabla[pos].setInfo(elem);
			
			// Incrementa el n�mero de elementos
			numElementos++;
			
			// Se modifica el tama�o de la tabla si es necesario
			reDispersion();
			
		// En caso contrario, recalculo la posici�n hasta encontrar una libre
		} else if (tabla[pos].getStatus() == HashNode.LLENO) {
			int i = 1;	// variable para el n�mero de intentos
			
			// Se recalcula la posici�n hasta que encuentre una celda vac�a o borrada
			while (tabla[exitCollision(i, elem)].getStatus() == HashNode.LLENO) {
				
				i++;	// incrementa el contador
				
				// Si el n�mero de intentos excede el tama�o, no encontr� posici�n
				if (i >= getSize()) {
					return -1;
				}
			}
			
			// Si encontr� nueva posici�n v�lida, se inserta
			tabla[exitCollision(i, elem)] = new HashNode<T>();
			tabla[exitCollision(i, elem)].setInfo(elem);
			
			// Incrementa el n�mero de elementos
			numElementos++;
			
			// Se modifica el tama�o de la tabla si es necesario
			reDispersion();
			
		}
		
		return 0;	// a�adido con �xito
	}
	
	/**
	 * M�todo que sale de una colisi�n buscando una nueva posici�n,
	 * cuyo c�lculo var�a seg�n el tipo de exploraci�n utilizado
	 * @param i n�mero de intento, de tipo int
	 * @param elem clave del elemento cuya posici�n se quiere recalcular, tipo T
	 * @return Nueva posici�n calculada para el elemento en cuesti�n
	 */
	private int exitCollision(int i, T elem) {
		// En caso de utilizar exploraci�n lineal
		if (this.tipoExploracion == LINEAL) {
			return (fHash(elem) + i) % getSize();
			
		// En caso de utilizar exploraci�n cuadr�tica
		} else if (this.tipoExploracion == CUADRATICA) {
			return (fHash(elem) + (i * i)) % getSize();
			
		// En caso de utilizar exploraci�n por doble dispersi�n
		} else if (this.tipoExploracion == DOBLE) {
			int R = previousPrimeNumber(getSize());	// primo anterior al tama�o
			int B = getSize();						// tama�o de la tabla
			
			return (fHash(elem) + i * (R - fHash(elem) % R)) % B;
		}
		
		return 0;
	}

	/**
	 * Encuentra un elemento en la Tabla Hash, calculando la posici�n
	 * en la que estar�a y comprobando si hay coincidencia
	 * @param elem elemento a buscar o encontrar, de tipo gen�rico T
	 * @return null si no lo encuentra, el elemento a encontrar es null
	 *         o si la tabla es vac�a; el nodo encontrado en otro caso
	 */
	@Override
	public T find(T elem) {
		// Se comprueba si el elemento es null o/y si la tabla est� vac�a
		if (elem == null || getNumOfElems() == 0) {
			return null;
		}
		
		// Se calcula la posici�n en la que deber�a estar
		int pos = fHash(elem);
		
		// Si est� vac�o, no es posible encontrar el elemento
		if (tabla[pos].getStatus() == HashNode.VACIO) {
			return null;
			
		// En caso de que est� lleno o borrado
		} else if (tabla[pos].getStatus() == HashNode.LLENO 
				|| tabla[pos].getStatus() == HashNode.BORRADO) {
			
			// Si est� lleno y coincide, devuelve el elemento
			if (tabla[pos].getStatus() == HashNode.LLENO && tabla[pos].getInfo().equals(elem)) {
				return tabla[pos].getInfo();
				
			// En caso contrario, es necesario buscar otras posiciones
			} else if (tabla[pos].getStatus() == HashNode.LLENO 
					|| tabla[pos].getStatus() == HashNode.BORRADO) {
				
				// Busca iterativamente en otras posiciones
				return iterativeSearch(pos, elem);
			}
		}
		
		return null;
	}
	
	/**
	 * M�todo que busca iterativamente en las siguiente posiciones
	 * hasta encontrar (si es posible) el elemento pasado como par�metro
	 * @param pos posici�n a partir de la que se busca, de tipo int
	 * @param elem elemento a buscar, de tipo gen�rico T
	 * @return Elemento encontrado si lo consigue, de tipo T; null en otro caso
	 */
	private T iterativeSearch(int pos, T elem) {
		int i = 1;	// variable para el n�mero de intentos
		
		// Se recalcula la posici�n hasta que encuentre una celda llena que coincida
		while (tabla[exitCollision(i, elem)].getStatus() == HashNode.LLENO
				|| tabla[exitCollision(i, elem)].getStatus() == HashNode.BORRADO) {
			
			// Se comprueba si la nueva posici�n calculada es llena y coincide
			if (tabla[exitCollision(i, elem)].getStatus() == HashNode.LLENO
					&& tabla[exitCollision(i, elem)].getInfo().equals(elem)) {
				
				// Si es as�, se devuelve el elemento
				return tabla[exitCollision(i, elem)].getInfo();
			}
			
			i++;	// incrementa el contador
			
			// Si el n�mero de intentos excede el tama�o, no encontr� posici�n
			if (i >= getSize()) {
				return null;
			}
		}
		
		return null;
	}

	/**
	 * Borra un elemento de la Tabla Hash
	 * @param elem elemento a borrar, de tipo gen�rico T
	 * @return 0 si lo borra con �xito;
	 *        -1 si el elemento pasado como par�metro es null;
	 *        -2 si el elemento pasado como par�metro no existe en la Tabla Hash;
	 *        -3 si la tabla est� vac�a
	 */
	@Override
	public int remove(T elem) {
		// Se comprueba si no hay elementos insertados
		if (getNumOfElems() == 0) {
			return -3;
		}
		
		// Se comprueba si el elemento es null
		if (elem == null) {
			return -1;
		}
		
		// Se comprueba si el elemento no existe en la tabla
		if (find(elem) == null) {
			return -2;
			
		} else {
			
			// Se calcula la posici�n en la que deber�a estar
			int pos = fHash(elem);
				
			// Si est� lleno y coincide, devuelve el elemento
			if (tabla[pos].getStatus() == HashNode.LLENO && tabla[pos].getInfo().equals(elem)) {
				
				// Se elimina el elemento
				tabla[pos].remove();
				numElementos--;

				// Se modifica el tama�o de la tabla si es necesario
				inverseReDispersion();

				return 0;

			// En caso contrario, es necesario buscar otras posiciones
			} else if (tabla[pos].getStatus() == HashNode.LLENO 
					|| tabla[pos].getStatus() == HashNode.BORRADO) {

				int i = 1; // variable para el n�mero de intentos

				// Se recalcula la posici�n hasta que encuentre una celda llena que coincida
				while (tabla[exitCollision(i, elem)].getStatus() == HashNode.LLENO
						|| tabla[exitCollision(i, elem)].getStatus() == HashNode.BORRADO) {

					// Se comprueba si la nueva posici�n calculada es llena y coincide
					if (tabla[exitCollision(i, elem)].getStatus() == HashNode.LLENO
							&& tabla[exitCollision(i, elem)].getInfo().equals(elem)) {

						// Si es as�, se devuelve el elemento
						tabla[exitCollision(i, elem)].remove();
						numElementos--;

						// Se modifica el tama�o de la tabla si es necesario
						inverseReDispersion();

						return 0;
					}

					i++; // incrementa el contador

					// Si el n�mero de intentos excede el tama�o, no encontr� posici�n
					if (i >= getSize()) {
						return -2;
					}
				}
			}
		}
				
		return 0;
	}

	/**
	 * Devuelve una cadena de caracteres con informaci�n
	 * sobre los elementos insertados en la tabla, el n�mero de elementos
	 * y el tama�o total de la Tabla Hash
	 * @return Cadena con la informaci�n de los elementos insertados,
	 *         el n�mero de elementos y el tama�o total de la tabla 
	 */
	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		
		// Se concatenan todos los elementos de la tabla
		for (int i = 0; i < getSize(); i++) {
			cadena.append(tabla[i]);
			cadena.append(";");
		}
		
		// Se concatena el tama�o
		cadena.append("[Size: ");
		cadena.append(getSize());
		
		// Se concatena el n�mero de elementos
		cadena.append(" Num.Elems.: ");
		cadena.append(getNumOfElems());
		
		cadena.append("]");
		
		// Se devuelve la cadena formada
		return cadena.toString();
	}

	/**
	 * Aumenta el tama�o de la Tabla Hash si el factor de carga
	 * de la misma supera el m�ximo establecido
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void reDispersion() {
		// Se comprueba si el FC supera el m�ximo
		if (getFC() > maxlf) {
			HashNode<T>[] aux = tabla;			// almaceno la tabla actual 
			int newSize = nextPrimeNumber(getSize() * 2);	// nuevo tama�o
			
			// Ahora tabla apunta a una nueva tabla vac�a
			this.tabla = (HashNode<T>[]) Array.newInstance(HashNode.class, newSize);
			numElementos = 0;
			
			// Lleno la nueva tabla con huecos para elementos
			for (int i = 0; i < newSize; i++) {
				tabla[i] = new HashNode<T>();
			}
			
			// Copio los elementos llenos de la tabla anterior a la nueva
			for (int i = 0; i < aux.length; i++) {
				if (aux[i].getStatus() == HashNode.LLENO) {
					add(aux[i].getInfo());
				}
			}
		}	
	}
	
	/**
	 * Reduce el tama�o de la Tabla Hash si el factor de carga
	 * de la misma es inferior al m�nimo establecido
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void inverseReDispersion() {
		// Se comprueba si el FC es inferior al m�ximo
		if (getFC() < minlf) {
			HashNode<T>[] aux = tabla;	// almaceno la tabla actual
			int newSize;				// nuevo tama�o
			
			// Si el tama�o / 2 no es primo
			if (!isPositivePrime(getSize() / 2)) {
				// El nuevo tama�o ser� el primo anterior a tama�o / 2
				newSize = previousPrimeNumber(getSize() / 2);
			} else {
				// En caso contrario, el tama�o pasar� a ser la mitad
				newSize = (getSize() / 2);
			}
			
			// Ahora tabla apunta a una nueva tabla vac�a
			this.tabla = (HashNode<T>[]) Array.newInstance(HashNode.class, newSize);
			numElementos = 0;
			
			// Lleno la nueva tabla con huecos para elementos
			for (int i = 0; i < newSize; i++) {
				tabla[i] = new HashNode<T>();
			}
			
			// Copio los elementos llenos de la tabla anterior a la nueva
			for (int i = 0; i < aux.length; i++) {
				if (aux[i].getStatus() == HashNode.LLENO) {
					add(aux[i].getInfo());
				}
			}
		}		
	}
}