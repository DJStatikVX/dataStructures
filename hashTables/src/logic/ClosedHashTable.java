// Paquete al que pertenece la clase
package logic;

// Importacion de clases
import java.lang.reflect.Array;

/**
 * Clase ClosedHashTable que implementa una Tabla Hash cerrada
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class ClosedHashTable<T> extends AbstractHash<T> {
	
	private int numElementos;		// número de elementos hasta el momento
	private HashNode<T> tabla[];    // Tabla Hash
	private int tipoExploracion;	// 1 - lineal, 2 - cuadrática, 3 - disp. doble
	private double minlf;			// factor de carga mínimo
	private double maxlf;			// factor de carga máximo
	
	/**
	 * Constante para definir el factor mínimo de carga
	 */
	public static final double MINIMUM_LF = 0.16;
	
	/**
	 * Constante para definir el factor máximo de carga
	 */
	public static final double MAXIMUM_LF = 0.5;
	
	/**
	 * Constante para definir el tipo de exploración lineal
	 */
	public static final int LINEAL = 1;
	
	/**
	 * Constante para definir el tipo de exploración cuadrática
	 */
	public static final int CUADRATICA = 2;
	
	/**
	 * Constante para definir el tipo de exploración por dispersión doble
	 */
	public static final int DOBLE = 3;
	
	/**
	 * Constructor que recibe como parámetros el tamaño y el tipo de exploración
	 * @param tam tamaño a asignar a la nueva Tabla Hash cerrada, de tipo int
	 * @param tipo tipo de exploración a utilizar en la nueva tabla, tipo int
	 */
	@SuppressWarnings("unchecked")
	public ClosedHashTable(int tam, int tipo) {
		this.numElementos = 0;
		this.tipoExploracion = tipo;
		this.minlf = MINIMUM_LF;
		this.maxlf = MAXIMUM_LF;
		
		// Si el tamaño pasado no es primo, se establece con el siguiente primo
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
	 * Constructor que recibe como parámetros el tamaño, el tipo de exploración,
	 * el factor de carga mínimo y el factor de carga máximo
	 * @param tam tamaño a asignar a la nueva Tabla Hash cerrada, de tipo int
	 * @param tipo tipo de exploración a utilizar en la nueva tabla, tipo int
	 * @param minlf factor de carga mínimo, de tipo double
	 * @param maxlf factor de carga máximo, de tipo double
	 */
	@SuppressWarnings("unchecked")
	public ClosedHashTable(int tam, int tipo, double minlf, double maxlf) {
		this.numElementos = 0;
		this.tipoExploracion = tipo;
		this.minlf = minlf;
		this.maxlf = maxlf;
		
		// Si el tamaño pasado no es primo, se establece con el siguiente primo
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
	 * Devuelve el número de elementos insertados hasta el momento en la Tabla Hash
	 * @return Número de elementos insertados en la tabla, de tipo int
	 */
	@Override
	public int getNumOfElems() {
		return this.numElementos;
	}

	/**
	 * Devuelve el tamaño total de la Tabla Hash
	 * @return Tamaño total de la Tabla Hash, de tipo int
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
	 * Añade un elemento a la Tabla Hash, buscando una posición para el mismo
	 * @param elem elemento a añadir, de tipo genérico T
	 * @return 0 si insertó el elemento correctamente;
	 *        -1 si el elemento pasado como parámetro es null;
	 */
	@Override
	public int add(T elem) {
		// Se comprueba si el elemento a añadir es null
		if (elem == null) {
			return -1;
		}
		
		// Posición del intento 0
		int pos = fHash(elem);
		
		// Si es posible, se inserta el elemento
		if (tabla[pos].getStatus() == HashNode.BORRADO 
				|| tabla[pos].getStatus() == HashNode.VACIO) {
			
			tabla[pos] = new HashNode<T>();
			tabla[pos].setInfo(elem);
			
			// Incrementa el número de elementos
			numElementos++;
			
			// Se modifica el tamaño de la tabla si es necesario
			reDispersion();
			
		// En caso contrario, recalculo la posición hasta encontrar una libre
		} else if (tabla[pos].getStatus() == HashNode.LLENO) {
			int i = 1;	// variable para el número de intentos
			
			// Se recalcula la posición hasta que encuentre una celda vacía o borrada
			while (tabla[exitCollision(i, elem)].getStatus() == HashNode.LLENO) {
				
				i++;	// incrementa el contador
				
				// Si el número de intentos excede el tamaño, no encontró posición
				if (i >= getSize()) {
					return -1;
				}
			}
			
			// Si encontró nueva posición válida, se inserta
			tabla[exitCollision(i, elem)] = new HashNode<T>();
			tabla[exitCollision(i, elem)].setInfo(elem);
			
			// Incrementa el número de elementos
			numElementos++;
			
			// Se modifica el tamaño de la tabla si es necesario
			reDispersion();
			
		}
		
		return 0;	// añadido con éxito
	}
	
	/**
	 * Método que sale de una colisión buscando una nueva posición,
	 * cuyo cálculo varía según el tipo de exploración utilizado
	 * @param i número de intento, de tipo int
	 * @param elem clave del elemento cuya posición se quiere recalcular, tipo T
	 * @return Nueva posición calculada para el elemento en cuestión
	 */
	private int exitCollision(int i, T elem) {
		// En caso de utilizar exploración lineal
		if (this.tipoExploracion == LINEAL) {
			return (fHash(elem) + i) % getSize();
			
		// En caso de utilizar exploración cuadrática
		} else if (this.tipoExploracion == CUADRATICA) {
			return (fHash(elem) + (i * i)) % getSize();
			
		// En caso de utilizar exploración por doble dispersión
		} else if (this.tipoExploracion == DOBLE) {
			int R = previousPrimeNumber(getSize());	// primo anterior al tamaño
			int B = getSize();						// tamaño de la tabla
			
			return (fHash(elem) + i * (R - fHash(elem) % R)) % B;
		}
		
		return 0;
	}

	/**
	 * Encuentra un elemento en la Tabla Hash, calculando la posición
	 * en la que estaría y comprobando si hay coincidencia
	 * @param elem elemento a buscar o encontrar, de tipo genérico T
	 * @return null si no lo encuentra, el elemento a encontrar es null
	 *         o si la tabla es vacía; el nodo encontrado en otro caso
	 */
	@Override
	public T find(T elem) {
		// Se comprueba si el elemento es null o/y si la tabla está vacía
		if (elem == null || getNumOfElems() == 0) {
			return null;
		}
		
		// Se calcula la posición en la que debería estar
		int pos = fHash(elem);
		
		// Si está vacío, no es posible encontrar el elemento
		if (tabla[pos].getStatus() == HashNode.VACIO) {
			return null;
			
		// En caso de que esté lleno o borrado
		} else if (tabla[pos].getStatus() == HashNode.LLENO 
				|| tabla[pos].getStatus() == HashNode.BORRADO) {
			
			// Si está lleno y coincide, devuelve el elemento
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
	 * Método que busca iterativamente en las siguiente posiciones
	 * hasta encontrar (si es posible) el elemento pasado como parámetro
	 * @param pos posición a partir de la que se busca, de tipo int
	 * @param elem elemento a buscar, de tipo genérico T
	 * @return Elemento encontrado si lo consigue, de tipo T; null en otro caso
	 */
	private T iterativeSearch(int pos, T elem) {
		int i = 1;	// variable para el número de intentos
		
		// Se recalcula la posición hasta que encuentre una celda llena que coincida
		while (tabla[exitCollision(i, elem)].getStatus() == HashNode.LLENO
				|| tabla[exitCollision(i, elem)].getStatus() == HashNode.BORRADO) {
			
			// Se comprueba si la nueva posición calculada es llena y coincide
			if (tabla[exitCollision(i, elem)].getStatus() == HashNode.LLENO
					&& tabla[exitCollision(i, elem)].getInfo().equals(elem)) {
				
				// Si es así, se devuelve el elemento
				return tabla[exitCollision(i, elem)].getInfo();
			}
			
			i++;	// incrementa el contador
			
			// Si el número de intentos excede el tamaño, no encontró posición
			if (i >= getSize()) {
				return null;
			}
		}
		
		return null;
	}

	/**
	 * Borra un elemento de la Tabla Hash
	 * @param elem elemento a borrar, de tipo genérico T
	 * @return 0 si lo borra con éxito;
	 *        -1 si el elemento pasado como parámetro es null;
	 *        -2 si el elemento pasado como parámetro no existe en la Tabla Hash;
	 *        -3 si la tabla está vacía
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
			
			// Se calcula la posición en la que debería estar
			int pos = fHash(elem);
				
			// Si está lleno y coincide, devuelve el elemento
			if (tabla[pos].getStatus() == HashNode.LLENO && tabla[pos].getInfo().equals(elem)) {
				
				// Se elimina el elemento
				tabla[pos].remove();
				numElementos--;

				// Se modifica el tamaño de la tabla si es necesario
				inverseReDispersion();

				return 0;

			// En caso contrario, es necesario buscar otras posiciones
			} else if (tabla[pos].getStatus() == HashNode.LLENO 
					|| tabla[pos].getStatus() == HashNode.BORRADO) {

				int i = 1; // variable para el número de intentos

				// Se recalcula la posición hasta que encuentre una celda llena que coincida
				while (tabla[exitCollision(i, elem)].getStatus() == HashNode.LLENO
						|| tabla[exitCollision(i, elem)].getStatus() == HashNode.BORRADO) {

					// Se comprueba si la nueva posición calculada es llena y coincide
					if (tabla[exitCollision(i, elem)].getStatus() == HashNode.LLENO
							&& tabla[exitCollision(i, elem)].getInfo().equals(elem)) {

						// Si es así, se devuelve el elemento
						tabla[exitCollision(i, elem)].remove();
						numElementos--;

						// Se modifica el tamaño de la tabla si es necesario
						inverseReDispersion();

						return 0;
					}

					i++; // incrementa el contador

					// Si el número de intentos excede el tamaño, no encontró posición
					if (i >= getSize()) {
						return -2;
					}
				}
			}
		}
				
		return 0;
	}

	/**
	 * Devuelve una cadena de caracteres con información
	 * sobre los elementos insertados en la tabla, el número de elementos
	 * y el tamaño total de la Tabla Hash
	 * @return Cadena con la información de los elementos insertados,
	 *         el número de elementos y el tamaño total de la tabla 
	 */
	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		
		// Se concatenan todos los elementos de la tabla
		for (int i = 0; i < getSize(); i++) {
			cadena.append(tabla[i]);
			cadena.append(";");
		}
		
		// Se concatena el tamaño
		cadena.append("[Size: ");
		cadena.append(getSize());
		
		// Se concatena el número de elementos
		cadena.append(" Num.Elems.: ");
		cadena.append(getNumOfElems());
		
		cadena.append("]");
		
		// Se devuelve la cadena formada
		return cadena.toString();
	}

	/**
	 * Aumenta el tamaño de la Tabla Hash si el factor de carga
	 * de la misma supera el máximo establecido
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void reDispersion() {
		// Se comprueba si el FC supera el máximo
		if (getFC() > maxlf) {
			HashNode<T>[] aux = tabla;			// almaceno la tabla actual 
			int newSize = nextPrimeNumber(getSize() * 2);	// nuevo tamaño
			
			// Ahora tabla apunta a una nueva tabla vacía
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
	 * Reduce el tamaño de la Tabla Hash si el factor de carga
	 * de la misma es inferior al mínimo establecido
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void inverseReDispersion() {
		// Se comprueba si el FC es inferior al máximo
		if (getFC() < minlf) {
			HashNode<T>[] aux = tabla;	// almaceno la tabla actual
			int newSize;				// nuevo tamaño
			
			// Si el tamaño / 2 no es primo
			if (!isPositivePrime(getSize() / 2)) {
				// El nuevo tamaño será el primo anterior a tamaño / 2
				newSize = previousPrimeNumber(getSize() / 2);
			} else {
				// En caso contrario, el tamaño pasará a ser la mitad
				newSize = (getSize() / 2);
			}
			
			// Ahora tabla apunta a una nueva tabla vacía
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