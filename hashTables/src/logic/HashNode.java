// Paquete al que pertenece la clase
package logic;

/**
 * Clase que implementa los elementos de una Tabla Hash
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class HashNode<T> {
	
	private T info;		// contenido de un elemento, de tipo genérico
	private int estado; // estado del elemento: borrado, lleno o vacío
	
	/**
	 * Constante para definir cuando el elemento está borrado
	 */
	public static final int BORRADO = -1;
	
	/**
	 * Constante para definir cuando el elemento está vacío
	 */
	public static final int VACIO = 0;
	
	/**
	 * Constante para definir cuando el elemento está lleno
	 */
	public static final int LLENO = 1;
	
	/**
	 * Constructor por defecto, que inicializa el elemento sin valor
	 * y con su estado establecido inicialmente como vacío
	 */
	public HashNode() {
		setInfo(null);
		this.estado = VACIO;
	}
	
	/**
	 * Devuelve el valor del elemento de la tabla
	 * @return Valor del elemento de la tabla, de tipo genérico T
	 */
	public T getInfo() {
		return this.info;
	}
	
	/**
	 * Modifica o establece el valor del elemento de la tabla, manteniéndolo lleno
	 * @param elemento valor a establecer para el elemento, de tipo genérico T
	 */
	public void setInfo(T elemento) {
		this.info = elemento;
		this.estado = LLENO;
	}
	
	/**
	 * Establece el elemento de la tabla como borrado
	 */
	public void remove() {
		this.estado = BORRADO;
	}
	
	/**
	 * Devuelve el estado del elemento de la tabla
	 * @return -1 si el elemento está borrado;
	 *          0 si el elemento está vacío;
	 *          1 si el elemento está lleno
	 */
	public int getStatus() {
		return this.estado;
	}
	
	/**
	 * Devuelve una cadena con la información del elemento (si es lleno)
	 * o especificando que es vacío o borrado en otro caso
	 * @return {info} si es lleno;
	 *          _E_   si es vacío;
	 *          _D_   si es borrado
	 */
	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder("{");	// caracter de apertura
		
		// Según el estado del elemento...
		switch (getStatus()) {
			case LLENO:
				// Si está lleno, muestra su valor
				cadena.append(getInfo().toString());
				break;
			case VACIO:
				// Si está vacío, muestra _E_
				cadena.append("_E_");
				break;
			case BORRADO:
				// Si está borrado, muestra _D_
				cadena.append("_D_");
		}
		
		cadena.append("}");			// caracter de cierre
		
		return cadena.toString();	// se devuelve la cadena compuesta
	}

}