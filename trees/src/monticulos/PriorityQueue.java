// Paquete al que pertenece la interfaz
package monticulos;

/**
 * Interfaz para albergar los m�todos de las colas de prioridad
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public interface PriorityQueue<T extends Comparable<T>> {

	/**
	 * A�ade un elemento m�s a la cola de prioridad (a la �ltima posici�n)
	 * @param elemento elemento a a�adir, de tipo gen�rico T
	 * @return -3 si no cabe el elemento en la cola de prioridad;
	 *  	   -2 si el elemento es null; -1 si el elemento ya existe;
	 *  	    0 si lo a�ade correctamente
	 */
	public int add(T elemento);
	
	/**
	 * Devuelve la ra�z de la cola de prioridad (posici�n 0)
	 * @return null si la cola de prioridad est� vac�a; la ra�z en otro caso
	 */
	public T getTop();
	
	/**
	 * Elimina un elemnto de la cola de prioridad
	 * @param elemento elemento a eliminar, de tipo gen�rico T
	 * @return -2 si el elemento es null o el mont�culo est� vac�o;
	 * 		   -1 si el elemento a borrar no existe;
	 *          0 si borra correctamente
	 */
	public int remove(T elemento);
	
	/**
	 * Devuelve si la cola de prioridad est� vac�a o no
	 * @return true si la cola de prioridad est� vac�a; false si no
	 */
	public boolean isEmpty();
	
	/**
	 * Borra todos los elementos de la cola de prioridad
	 */
	public void clear();
	
	/**
	 * Cambia la prioridad o el contenido 
	 * de una posici�n que se pasa como par�metro
	 * @param pos posici�n cuyo contenido se pasa, de tipo int
	 * @param elemento elemento a colocar en la posici�n, de tipo gen�rico T
	 * @return -2 si la posici�n es negativa o mayor que el n�mero de elementos;
	 *         -1 si el elemento pasado como par�metro ya exist�a;
	 *          0 si cambia la prioridad sin ning�n problema
	 */
	public int cambiarPrioridad(int pos, T elemento);
	
	/**
	 * Muestra los elementos del vector
	 * separados por tabuladores (excepto el �ltimo elemento)
	 * @return Cadena con los elementos del vector, de tipo String
	 */
	public String toString();
	
}