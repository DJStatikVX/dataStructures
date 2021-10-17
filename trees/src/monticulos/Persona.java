// Paquete al que pertenece la clase
package monticulos;

/**
 * Clase que crea objetos de tipo persona
 * @author mrsuarez
 */
class Persona implements Comparable<Persona> {
	
	private int prioridad;	// clave del objeto
	private String nombre;	// nombre en cadena

	/**
	 * Constructor de la clase Persona
	 * @param clave prioridad de la persona, de tipo int
	 * @param n nombre de la persona, de tipo String
	 */
	public Persona(int clave, String n) {
		prioridad = clave;
		nombre = n;
	}

	/**
	 * Devuelve la prioridad de la persona
	 * @return Prioridad de la persona, de tipo int
	 */
	public int getPrioridad() {
		return prioridad;
	}

	/**
	 * Devuelve el nombre de la persona
	 * @return Nombre de la persona, de tipo String
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Compara la prioridad de la persona con la de otra
	 * @param p persona con la que se compara la prioridad
	 * @return -1 si tiene menos prioridad que p;
	 * 		    1 si tiene más prioridad que p;
	 * 			0 si tiene la misma prioridad que p
	 */
	@Override
	public int compareTo(Persona p) {
		if (prioridad < (p.prioridad)) {
			return -1;
		} else if (prioridad > (p.prioridad)) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * Muestra un objeto con la prioridad y el nombre entre corchetes
	 * @return Cadena con el formato [prioridad:nombre], de tipo String
	 */
	@Override
	public String toString() {
		return "[" + prioridad + ":" + nombre + "]";
	}
}