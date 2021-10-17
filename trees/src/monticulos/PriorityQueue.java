// Paquete al que pertenece la interfaz
package monticulos;

/**
 * Interfaz para albergar los métodos de las colas de prioridad
 * @author Samuel Rodríguez Ares (UO271612)
 */
public interface PriorityQueue<T extends Comparable<T>> {

	/**
	 * Añade un elemento más a la cola de prioridad (a la última posición)
	 * @param elemento elemento a añadir, de tipo genérico T
	 * @return -3 si no cabe el elemento en la cola de prioridad;
	 *  	   -2 si el elemento es null; -1 si el elemento ya existe;
	 *  	    0 si lo añade correctamente
	 */
	public int add(T elemento);
	
	/**
	 * Devuelve la raíz de la cola de prioridad (posición 0)
	 * @return null si la cola de prioridad está vacía; la raíz en otro caso
	 */
	public T getTop();
	
	/**
	 * Elimina un elemnto de la cola de prioridad
	 * @param elemento elemento a eliminar, de tipo genérico T
	 * @return -2 si el elemento es null o el montículo está vacío;
	 * 		   -1 si el elemento a borrar no existe;
	 *          0 si borra correctamente
	 */
	public int remove(T elemento);
	
	/**
	 * Devuelve si la cola de prioridad está vacía o no
	 * @return true si la cola de prioridad está vacía; false si no
	 */
	public boolean isEmpty();
	
	/**
	 * Borra todos los elementos de la cola de prioridad
	 */
	public void clear();
	
	/**
	 * Cambia la prioridad o el contenido 
	 * de una posición que se pasa como parámetro
	 * @param pos posición cuyo contenido se pasa, de tipo int
	 * @param elemento elemento a colocar en la posición, de tipo genérico T
	 * @return -2 si la posición es negativa o mayor que el número de elementos;
	 *         -1 si el elemento pasado como parámetro ya existía;
	 *          0 si cambia la prioridad sin ningún problema
	 */
	public int cambiarPrioridad(int pos, T elemento);
	
	/**
	 * Muestra los elementos del vector
	 * separados por tabuladores (excepto el último elemento)
	 * @return Cadena con los elementos del vector, de tipo String
	 */
	public String toString();
	
}