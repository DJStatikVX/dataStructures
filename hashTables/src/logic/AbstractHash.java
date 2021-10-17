// Paquete al que pertenece la clase
package logic;

/**
 * Clase AbstractHash que declara los m�todos a implementar
 * en las Tablas Hash, tanto abiertas como cerradas
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public abstract class AbstractHash<T> {
	
	/**
	 * Devuelve el n�mero de elementos insertados hasta el momento en la Tabla Hash
	 * @return N�mero de elementos insertados en la tabla, de tipo int
	 */
	abstract public int getNumOfElems();
	
	/**
	 * Devuelve el tama�o total de la Tabla Hash
	 * @return Tama�o total de la Tabla Hash, de tipo int
	 */
	abstract public int getSize();
	
	/**
	 * A�ade un elemento a la Tabla Hash, buscando una posici�n para el mismo
	 * @param elem elemento a a�adir, de tipo gen�rico T
	 * @return 0 si insert� el elemento correctamente;
	 *        -1 si el elemento pasado como par�metro es null;
	 */
	abstract public int add(T elem);
	
	/**
	 * Encuentra un elemento en la Tabla Hash, calculando la posici�n
	 * en la que estar�a y comprobando si hay coincidencia
	 * @param elem elemento a buscar o encontrar, de tipo gen�rico T
	 * @return null si no lo encuentra, el elemento a encontrar es null
	 *         o si la tabla es vac�a; el nodo encontrado en otro caso
	 */
	abstract public T find(T elem);
	
	/**
	 * Borra un elemento de la Tabla Hash
	 * @param elem elemento a borrar, de tipo gen�rico T
	 * @return 0 si lo borra con �xito;
	 *        -1 si el elemento pasado como par�metro es null;
	 *        -2 si el elemento pasado como par�metro no existe en la Tabla Hash;
	 *        -3 si la tabla est� vac�a
	 */
	abstract public int remove(T elem);
	
	/**
	 * Devuelve una cadena de caracteres con informaci�n
	 * sobre los elementos insertados en la tabla, el n�mero de elementos
	 * y el tama�o total de la Tabla Hash
	 * @return Cadena con la informaci�n de los elementos insertados,
	 *         el n�mero de elementos y el tama�o total de la tabla 
	 */
	abstract public String toString();
	
	/**
	 * Calcula la posici�n de un elemento en la Tabla Hash a partir de su valor
	 * @param info valor del elemento cuya posici�n se quiere conocer
	 * @return Posici�n calculada directamente si es positiva; la posici�n
	 *         sumada al tama�o total de la tabla si el c�lculo resulta negativo
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
	 * Devuelve si el n�mero pasado como par�metro es positivo y primo
	 * @param numero n�mero sobre el que se comprueba si es primo o no, tipo int
	 * @return true si numero es primo; false en otro caso
	 */
	protected boolean isPositivePrime(int numero) {
		boolean isPrimo = true;
		
		// Solo contempla n�meros positivos
		if (numero >= 0) {
			// Se busca alg�n divisor
			for (int i = 2; i <= numero / 2; i++) {
				if (numero % i == 0) {
					isPrimo = false;
				}
			}
		}
		
		return isPrimo;
	}
	
	/**
	 * Devuelve el siguiente n�mero primo al pasado como par�metro
	 * @param numero n�mero a partir del cual se calcula el siguiente primo (int)
	 * @return Siguiente n�mero primo al par�metro numero, de tipo int
	 */
	protected int nextPrimeNumber(int numero) {
		int num = numero + 1;	// siguiente
		
		// Si no es primo, incrementa y se comprueba de nuevo
		while (!isPositivePrime(num)) {
			num++;
		}
		
		return num;	// se devuelve el n�mero cuando haya encontrado el primo
	}
	
	/**
	 * Devuelve el anterior n�mero primo al pasado como par�metro
	 * @param numero n�mero a partir del cual se calcula el anterior primo (int)
	 * @return Anterior n�mero primo al par�metro numero, de tipo int
	 */
	protected int previousPrimeNumber(int numero) {
		if (numero <= 3) {
			return 3;	// m�nimo n�mero primo en nuestro dominio
		}
		
		int num = numero - 1;	// anterior
		
		// Si no es primo, decrementa y se comprueba de nuevo
		while (!isPositivePrime(num)) {
			num--;
		}
		
		return num; // se devuelve el n�mero cuando haya encontrado el primo
	}
	
	/**
	 * Aumenta el tama�o de la tabla
	 */
	abstract protected void reDispersion();
	
	/**
	 * Disminuye el tama�o de la tabla
	 */
	abstract protected void inverseReDispersion();

}