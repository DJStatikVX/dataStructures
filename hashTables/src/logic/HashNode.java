// Paquete al que pertenece la clase
package logic;

/**
 * Clase que implementa los elementos de una Tabla Hash
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class HashNode<T> {
	
	private T info;		// contenido de un elemento, de tipo gen�rico
	private int estado; // estado del elemento: borrado, lleno o vac�o
	
	/**
	 * Constante para definir cuando el elemento est� borrado
	 */
	public static final int BORRADO = -1;
	
	/**
	 * Constante para definir cuando el elemento est� vac�o
	 */
	public static final int VACIO = 0;
	
	/**
	 * Constante para definir cuando el elemento est� lleno
	 */
	public static final int LLENO = 1;
	
	/**
	 * Constructor por defecto, que inicializa el elemento sin valor
	 * y con su estado establecido inicialmente como vac�o
	 */
	public HashNode() {
		setInfo(null);
		this.estado = VACIO;
	}
	
	/**
	 * Devuelve el valor del elemento de la tabla
	 * @return Valor del elemento de la tabla, de tipo gen�rico T
	 */
	public T getInfo() {
		return this.info;
	}
	
	/**
	 * Modifica o establece el valor del elemento de la tabla, manteni�ndolo lleno
	 * @param elemento valor a establecer para el elemento, de tipo gen�rico T
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
	 * @return -1 si el elemento est� borrado;
	 *          0 si el elemento est� vac�o;
	 *          1 si el elemento est� lleno
	 */
	public int getStatus() {
		return this.estado;
	}
	
	/**
	 * Devuelve una cadena con la informaci�n del elemento (si es lleno)
	 * o especificando que es vac�o o borrado en otro caso
	 * @return {info} si es lleno;
	 *          _E_   si es vac�o;
	 *          _D_   si es borrado
	 */
	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder("{");	// caracter de apertura
		
		// Seg�n el estado del elemento...
		switch (getStatus()) {
			case LLENO:
				// Si est� lleno, muestra su valor
				cadena.append(getInfo().toString());
				break;
			case VACIO:
				// Si est� vac�o, muestra _E_
				cadena.append("_E_");
				break;
			case BORRADO:
				// Si est� borrado, muestra _D_
				cadena.append("_D_");
		}
		
		cadena.append("}");			// caracter de cierre
		
		return cadena.toString();	// se devuelve la cadena compuesta
	}

}