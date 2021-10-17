// Paquete al que pertenece la clase
package logic;

/**
 * Clase AbstractHash que declara los métodos a implementar
 * en las Tablas Hash, tanto abiertas como cerradas
 * @author Samuel Rodríguez Ares (UO271612)
 */
public abstract class AbstractHash<T> {
	
	/**
	 * Devuelve el número de elementos insertados hasta el momento en la Tabla Hash
	 * @return Número de elementos insertados en la tabla, de tipo int
	 */
	abstract public int getNumOfElems();
	
	/**
	 * Devuelve el tamaño total de la Tabla Hash
	 * @return Tamaño total de la Tabla Hash, de tipo int
	 */
	abstract public int getSize();
	
	/**
	 * Añade un elemento a la Tabla Hash, buscando una posición para el mismo
	 * @param elem elemento a añadir, de tipo genérico T
	 * @return 0 si insertó el elemento correctamente;
	 *        -1 si el elemento pasado como parámetro es null;
	 */
	abstract public int add(T elem);
	
	/**
	 * Encuentra un elemento en la Tabla Hash, calculando la posición
	 * en la que estaría y comprobando si hay coincidencia
	 * @param elem elemento a buscar o encontrar, de tipo genérico T
	 * @return null si no lo encuentra, el elemento a encontrar es null
	 *         o si la tabla es vacía; el nodo encontrado en otro caso
	 */
	abstract public T find(T elem);
	
	/**
	 * Borra un elemento de la Tabla Hash
	 * @param elem elemento a borrar, de tipo genérico T
	 * @return 0 si lo borra con éxito;
	 *        -1 si el elemento pasado como parámetro es null;
	 *        -2 si el elemento pasado como parámetro no existe en la Tabla Hash;
	 *        -3 si la tabla está vacía
	 */
	abstract public int remove(T elem);
	
	/**
	 * Devuelve una cadena de caracteres con información
	 * sobre los elementos insertados en la tabla, el número de elementos
	 * y el tamaño total de la Tabla Hash
	 * @return Cadena con la información de los elementos insertados,
	 *         el número de elementos y el tamaño total de la tabla 
	 */
	abstract public String toString();
	
	/**
	 * Calcula la posición de un elemento en la Tabla Hash a partir de su valor
	 * @param info valor del elemento cuya posición se quiere conocer
	 * @return Posición calculada directamente si es positiva; la posición
	 *         sumada al tamaño total de la tabla si el cálculo resulta negativo
	 */
	protected int fHash(T info) {
		int pos = info.hashCode() % getSize();
		
		// Caso para tratar posiciones negativas
		if (pos < 0) {
			pos += getSize();
		}
		
		return pos;
	}
	
	/**
	 * Devuelve si el número pasado como parámetro es positivo y primo
	 * @param numero número sobre el que se comprueba si es primo o no, tipo int
	 * @return true si numero es primo; false en otro caso
	 */
	protected boolean isPositivePrime(int numero) {
		boolean isPrimo = true;
		
		// Solo contempla números positivos
		if (numero >= 0) {
			// Se busca algún divisor
			for (int i = 2; i <= numero / 2; i++) {
				if (numero % i == 0) {
					isPrimo = false;
				}
			}
		}
		
		return isPrimo;
	}
	
	/**
	 * Devuelve el siguiente número primo al pasado como parámetro
	 * @param numero número a partir del cual se calcula el siguiente primo (int)
	 * @return Siguiente número primo al parámetro numero, de tipo int
	 */
	protected int nextPrimeNumber(int numero) {
		int num = numero + 1;	// siguiente
		
		// Si no es primo, incrementa y se comprueba de nuevo
		while (!isPositivePrime(num)) {
			num++;
		}
		
		return num;	// se devuelve el número cuando haya encontrado el primo
	}
	
	/**
	 * Devuelve el anterior número primo al pasado como parámetro
	 * @param numero número a partir del cual se calcula el anterior primo (int)
	 * @return Anterior número primo al parámetro numero, de tipo int
	 */
	protected int previousPrimeNumber(int numero) {
		if (numero <= 3) {
			return 3;	// mínimo número primo en nuestro dominio
		}
		
		int num = numero - 1;	// anterior
		
		// Si no es primo, decrementa y se comprueba de nuevo
		while (!isPositivePrime(num)) {
			num--;
		}
		
		return num; // se devuelve el número cuando haya encontrado el primo
	}
	
	/**
	 * Aumenta el tamaño de la tabla
	 */
	abstract protected void reDispersion();
	
	/**
	 * Disminuye el tamaño de la tabla
	 */
	abstract protected void inverseReDispersion();

}