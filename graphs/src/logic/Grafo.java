// Paquete al que pertenece la clase
package logic;

// Importación de clases
import java.text.DecimalFormat;

/**
 * Clase genérica para modelar un grafo y sus propiedades
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class Grafo<T> {
	
	private T[] nodes;					// array de nodos
	private double[][] pesos;			// matriz de pesos
	private boolean[][] aristas;		// matriz de aristas
	int tam;							// número de elementos
	
	double[][] A;						// matriz A de Floyd
	T[][] P;						    // matriz P de Floyd
	
	String path;					    // camino de Floyd
	String df;						    // Recorrido en Profundidad
	

	/**
	 * Constructor de la clase, que recibe como parámetro la dimensión del grafo.
	 * Inicializa el array de nodos, las matrices, y establece el tamaño a 0
	 * @param dimension dimensión del grafo, de tipo int
	 */
	@SuppressWarnings("unchecked")
	public Grafo(int dimension) {
		setNodes((T[]) new Object[dimension]);
		setPesos(new double[dimension][dimension]);
		setAristas(new boolean[dimension][dimension]);
		setTam(0);
	}
	
	/**
	* Método que devuelve la posición de un nodo en el vector nodes
	* @param node el nodo a buscar
	* @return int la posición del nodo a buscar; devuelve -1 en caso de que 
	* no se encuentre o el nodo que se le pase sea null
	*/
	public int getNode(T node) {
		if (node != null) {
			
			// Se busca aquel nodo que sea igual al parámetro
			for (int i = 0; i < getTam(); i++) {
				if (getNodes()[i].equals(node)) {
					return i;
				}
			}
		}
		
		return -1; // si el parámetro es null o no se encontró
	}
	
	/**
	* Método que añade un nuevo nodo al grafo.
	* Falla si el nodo ya existe en el grafo o no cabe
	* @param node el nodo que se desea añadir
	* @return int 0 si el nodo es añadido correctamente; 
	* -1 si existe el nodo; -2 si no hay sitio; -3 si node es null
	*/
	public int addNode(T node) {	
		// Se comprueba si es null
		if (node == null) {
			return -3;
		}
		
		// Se comprueba si hay sitio en el grafo
		if (getTam() >= getNodes().length) {
			return -2;
			
		// Se comprueba que el nodo no esté ya añadido
		} else if (getNode(node) == -1) {
			
			// Si todo está correcto, lo coloca en la posición donde apunta tam
			this.nodes[getTam()] = node;
		
			// A continuación, se establecen sus pesos y aristas
			for (int i = 0; i < getTam(); i++) {
				this.aristas[getTam()][i] = false;
				this.aristas[i][getTam()] = false;
				this.pesos[getTam()][i] = 0;
				this.pesos[i][getTam()] = 0;
			}
			
			++this.tam;	// se incrementa el número de elementos
			
			return 0;
		}
		
		return -1; // si el nodo ya estaba en el grafo
	}
	
	/**
	* Método que elimina un nodo del grafo
	* @param node el nodo a eliminar
	* @return int 0 si ha sido posible eliminarlo; -1 en caso contrario
	*/
	public int removeNode(T node) {
		int i = getNode(node); // se recoge el valor de retorno para evalur
		
		// Se comprueba si el nodo existe
		if (i >= 0) {
			
			--this.tam; // decrementa el número de elementos
			
			// Si no es el último nodo se reemplaza por el último
			if (i != getTam() + 1) {
				this.nodes[i] = this.nodes[getTam()];
				
				// Se reemplazan los elementos en las matrices de aristas y pesos
				for (int j = 0; j <= getTam(); j++) {
					this.aristas[j][i] = this.aristas[j][getTam()];
					this.aristas[i][j] = this.aristas[getTam()][j];
					this.pesos[j][i] = this.pesos[j][getTam()];
					this.pesos[i][j] = this.pesos[getTam()][j];
				}
				
				// Caso del codo, que no es movido al intercambiar filas y columnas
				this.aristas[i][i] = this.aristas[getTam()][getTam()];
				this.pesos[i][i] = this.pesos[getTam()][getTam()];
				
				return 0;
			}
		}
		
		return -1; // en caso de que no se encontrase el nodo
	}
	
	/**
	* Método que comprueba si el nodo pasado como parámetro existe en grafo
	* @param node el nodo a comprobar
	* @return boolean true si existe el nodo; false en otro caso
	*/
	public boolean existNode(T node) {
		for (int i = 0; i < getTam(); i++) {
			
			// Busca si existe el nodo en el grafo
			if (getNodes()[i].equals(node)) {
				return true;
			}
		}
		
		return false; // si no lo encontró
	}
	
	/**
	* Método que devuelve el peso de la arista entre dos nodos.
	* 
	* @param source el nodo fuente
	* @param target el nodo destino
	* @return double el peso de la arista; -1 si no exite source; -2 si
	* no existe target; -3 si no existen ni source ni target; -4 si
	* no exite la arista entre source y target
	*/
	public double getEdge(T source, T target) {
		// Se comprueba la existencia de los nodos implicados
		if (!existNode(source) && !existNode(target)) {
			return -3;
		} else if (!existNode(source)) {
			return -1;
		} else if (!existNode(target)) {
			return -2;
		}
		
		// Si existen ambos, se comprueba si hay arista y se devuelve su peso
		if (getAristas()[getNode(source)][getNode(target)]) {
			return getPesos()[getNode(source)][getNode(target)];
		}
		
		return -4; // si no hay arista entre source y target
	}
	
	/**
	* Método que comprueba si existe arista entre dos nodos
	*
	* @param source el nodo origen
	* @param target el nodo destino
	* @return boolean true si existe arista
	*/
	public boolean existEdge(T source, T target) {
		// Se comprueba que el peso entre source y target sea 0 o mayor
		if (getEdge(source, target) >= 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	* Método que añade una arista entre dos nodos
	*
	* @param source el nodo origen
	* @param target el nodo destino
	* @param weight el peso de la arista que une al nodo origen y al nodo destino
	* @return int 0 si se ha añadido la arista; -1 no existe nodo origen, 
	* -2 no existe nodo destino, -3 no existen ninguno, 
	* -4 ya existe la arista, -5 peso negativo
	*/
	public int addEdge(T source, T target, double weight) {
		// Se comprueba si existen los nodos
		if (!existNode(source) && !existNode(target)) {
			return -3;
		} else if (!existNode(source)) {
			return -1;
		} else if (!existNode(target)) {
			return -2;
		}
		
		// Se comprueba que el peso no sea negativo
		if (weight < 0) {
			return -5;
		
		// Se comprueba si ya existe la arista
		} else if (existEdge(source, target)) {
			return -4;
			
		// Si no existe la arista, se modifican las matrices de aristas y pesos
		} else {
			aristas[getNode(source)][getNode(target)] = true;
			pesos[getNode(source)][getNode(target)] = weight;
			
			return 0; // operación completada con éxito
		}
	}
	
	/**
	* Método que elimina una arista del grafo en caso de que esta exista
	*
	* @param source el nodo origen
	* @param target el nodo destino
	* @return int 0 si ha sido posible borrar la arista; -1 no existe nodo origen, 
	* -2 no existe nodo destino, -3 no existen ninguno, -4 no existe la arista
	*/
	public int removeEdge(T source, T target) {
		// Se comprueba la existencia de los nodos
		if (!existNode(source) && !existNode(target)) {
			return -3;
		} else if (!existNode(source)) {
			return -1;
		} else if (!existNode(target)) {
			return -2;
		}
		
		// Se comprueba si la arista existe o no
		if (!existEdge(source, target)) {
			return -4;
			
		// En caso de que exista, se elimina su valor en las matrices del grafo
		} else {
			aristas[getNode(source)][getNode(target)] = false;
			pesos[getNode(source)][getNode(target)] = 0;
			
			return 0; // la operación se completó exitosamente
		}
	}
	
	/**
	 * Método que implementa el Algoritmo de Dijkstra
	 * @param source nodo origen para realizar Dijkstra
	 * @return Vector con los costes mínimos para llegar al resto de nodos
	 */
	public double[] dijkstra(T source) {
		double[] D = inicializarD(source);
		T[] P = inicializarP(source);
		
		// Inicializamos el vector S con el primer pivote a true
		boolean[] S = new boolean[getTam()];
		S[getNode(source)] = true;
			
		// Mientras queden nodos por pivotar...
		while (minCost(D, S) != -1) {
			
			// Rotación de pivote
			int w = minCost(D, S);
			S[w] = true;				// se marca el nodo como visitado

			// Para el pivote en cuestión, buscamos los costes mínimos
			for (int m = 0; m < getTam(); m++) {
				
				// Para cada nodo del vector V-S...
				if (!S[m] && getAristas()[w][m]) {
					
					// Si el coste a través del pivote es menor, se reemplaza
					if (D[w] + getPesos()[w][m] < D[m]) {
						D[m] = D[w] + getPesos()[w][m];
						P[m] = getNodes()[w];
					}
				}
			}
		}
		
		return D; // finalmente, se devuelve el vector de costes optimizado 
	}
	
	/**
	 * Inicializa el vector D de pesos "mínimos" de Dijkstra
	 * @param source nodo origen
	 * @return Vector D inicializado
	 */
	private double[] inicializarD(T source) {
		double D[] = new double[getTam()];
		int sourcePos = getNode(source);
		
		// Se recorre la lista de nodos
		for (int i = 0; i < getTam(); i++) {
			
			// Se establece a 0 para sí mismo
			if (sourcePos == i) {
				D[i] = 0;
			
			// Si hay arista, establece su peso
			} else if (existEdge(source, getNodes()[i])) {
				D[i] = getEdge(source, getNodes()[i]);
			
			// Si no, lo establece a infinito
			} else {
				D[i] = Double.POSITIVE_INFINITY;
			}
		}
		
		return D;
	}
	
	/**
	 * Inicializa el vector P de caminos de Dijkstra
	 * @param source nodo origen
	 * @return Vector P inicializado
	 */
	private T[] inicializarP(T source) {
		@SuppressWarnings("unchecked")
		T[] P = (T[]) new Object[getTam()];
		
		// Se recorre la lista de nodos
		for (int i = 0; i < getTam(); i++) {
			
			// Se establece a null para sí mismo
			if (source.equals(getNodes()[i])) {
				P[i] = null;
				
			// También si no hay arista
			} else if (!existEdge(source, getNodes()[i])) {
				P[i] = null;
			
			// Si hay arista, se establece a sí mismo
			} else {
				P[i] = source;
			}
		}
		
		return P;
	}
	
	/**
	 * Devuelve el coste mínimo que haya en el vector D para nodos no visitados
	 * @param D vector de costes mínimos, de tipo double[]
	 * @param S vector de caminos, de tipo boolean[]
	 * @return Posición del coste mínimo, de tipo int; -1 si visitó todos
	 */
	private int minCost(double[] D, boolean[] S) {
		double min = Double.POSITIVE_INFINITY;	// mayor mínimo posible
		int pos = -1;
		
		// Se van buscando los costes mínimos en los nodos no visitados
		for (int i = 0; i < D.length; i++) {
			if (D[i] < min && !S[i]) {
				min = D[i];
				pos = i;
			}
		}
		
		return pos; // del nodo no visitado con coste mínimo (-1 si visitó todos)
	}
	
	/**
	* Aplica el algoritmo de Floyd al grafo y devuelve
	* 0 si lo aplica y genera matrices A y P; y –1 si no lo hace
	* @return 0 si pudo reducir costes; -1 en otro caso
	*/
	public int floyd() {
		// Primero, se comprueba si es posible aplicar Floyd
		if (getTam() <= 0) {
			return -1;
		}
		
		A = initializeFloydA();		// inicializa A
		P = initializeFloydP();		// inicializa P
				
		// Nodo pivote
		for (int k = 0; k < getTam(); k++) {
			// Filas
			for (int i = 0; i < getTam(); i++) {
				// Columnas
				for (int j = 0; j < getTam(); j++) {
					// ¿A pasando por B hasta C es más barato que A hasta C?
					if (A[i][k] + A[k][j] < A[i][j]) {
						A[i][j] = A[i][k] + A[k][j];	// anota el coste
						P[i][j] = getNodes()[k];		// anota nodo intermedio
					}
				}
			}
		}
		
		return 0;	// se pudo aplicar Floyd
	}
	
	/**
	* Método que inicializa la matriz de pesos A para el Algoritmo de Floyd
	*
	* @return double[][] la matriz de pesos
	*/
	private double[][] initializeFloydA() {
		double[][] aLocal = new double[getTam()][getTam()];
		
		// Se recorre la matriz de costes
		for (int i = 0; i < getPesos().length; i++) {
			for (int j = 0; j < getPesos()[i].length; j++) {
				
				// Diagonal
				if (i == j) {
					aLocal[i][j] = 0;
					
				// Si hay arista, se parte del coste
				} else if (getAristas()[i][j]) {
					aLocal[i][j] = getPesos()[i][j];
					
				// Para cualquier otro caso, se anota Infinito
				} else {
					aLocal[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
		
		return aLocal;	// se devuelve la matriz A local inicializada
	}
	
	/**
	* Método que inicializa la matriz de caminos P para el Algoritmo de Floyd
	*
	* @return T[][] la matriz de caminos / return int[][] la matriz de caminos
	*/
	@SuppressWarnings("unchecked")
	private T[][] initializeFloydP() {
		T[][] pLocal = (T[][]) new Object[getTam()][getTam()];
		
		// Se inicializa con todos los valores a null
		for (int i = 0; i < getAristas().length; i++) {
			for (int j = 0; j < getAristas()[i].length; j++) {
				pLocal[i][j] = null;
			}
		}
		
		return pLocal;	// se devuelve la matriz P local inicializada
	}
	
	/**
	* Devuelve la matriz A de Floyd
	* Si no se ha invocado a Floyd debe devolver null
	* @return Matriz A de costes para Floyd; null si no está inicializada
	*/
	public double[][] getAFloyd() {
		return this.A;
	}

	/**
	* Devuelve la matriz P de Floyd,
	* Si no se ha invocado a Floyd debe devolver null
	* @return Matriz P de caminos para Floyd; null si no está inicializada
	*/ 
	public T[][] getPFloyd() {
		return this.P;
	}
	
	/**
	 * Imprime por pantalla la información del grafo; tanto las características
	 * de sus nodos, así como las aristas en formato T/F y los pesos de cada una
	 * @return Cadena compuesta por la información de los nodos y aristas del grafo
	 */
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");
		String cadena = "";
		
		double[][] aFloyd = getAFloyd();
		T[][] pFloyd = getPFloyd();
		
		cadena += "VECTOR NODOS\n";
		
		// Se imprime el toString de cada nodo del grafo
		for (int i = 0; i < getTam(); i++) {
			cadena += nodes[i].toString() + "\t";
		}
		
		cadena += "\n\nMATRIZ ARISTAS\n";
		
		// Se recorre la matriz de aristas
		for (int i = 0; i < getTam(); i++) {
			for (int j = 0; j < getTam(); j++) {
				
				// Si hay arista, muestra T. Si no, muestra F
				if (aristas[i][j]) {
					cadena += "T\t";
				} else {
					cadena += "F\t";
				}
			}
			
			cadena += "\n";
		}
		
		cadena += "\nMATRIZ PESOS\n";
		
		// Se recorre la matriz de aristas y se devuelve el peso de las existentes
		for (int i = 0; i < getTam(); i++) {
			for (int j = 0; j < getTam(); j++) {
				cadena += (aristas[i][j] ? df.format(pesos[i][j]) : "-") + "\t";
			}
			
			cadena += "\n";
		}
		
		// Muestra los costes de la matriz A de Floyd en formato decimal
		if (aFloyd != null ) {
            cadena += "\nAFloyd\n";
            
            for (int i = 0; i < getTam(); i++) {
                for (int j = 0; j < getTam(); j++) {
                    cadena += df.format(getAFloyd()[i][j]) + "\t";
                }
                
                cadena += "\n";
            }
        }
		
		// Muestra los nodos de la matriz P de Floyd
        if (pFloyd != null ) {
            cadena += "\nPFloyd\n";
            
            for (int i = 0; i < getTam(); i++) {
                for (int j = 0; j < getTam(); j++) {
                    if (pFloyd[i][j] != null) {
                    	// Añade además un guión para alinear la salida
                        cadena += getPFloyd()[i][j].toString() + "-" + "\t";
                    } else {
                        cadena += " -" + "\t";
                    }
                }
                
                cadena += "\n";
            }
        }
		
		return cadena;	// finalmente, se devuelve la cadena compuesta
	}
	
	/**
	* Devuelve el coste del camino de coste mínimo
	* entre origen y destino según Floyd.
	* Si no están generadas las matrices de Floyd, las genera,
	* y si no puede obtener el valor por alguna razón, devuelve -1
	* @param origen nodo origen, de tipo genérico T
	* @param destino nodo destino, de tipo genérico T
	* @return Coste mínimo anotado en la matriz A de Floyd entre origen y destino;
	* 		  -1 si no es posible obtener el valor (alguno de los nodos no existe)
	*/
	public double minCostPath(T origen, T destino) {
		// Se comprueba la existencia de los nodos implicados
		if (!existNode(origen) || !existNode(destino)) {
			return -1;
		}
		
		// Si no están generadas las matrices de Floyd, se inicializan
		if (!isAInitialized() && !isPInitialized()) {
			A = initializeFloydA();
			P = initializeFloydP();
		}
		
		// Si hay coste mínimo anotado, se devuelve. Si no, retorna -1
		if (getAFloyd()[getNode(origen)][getNode(destino)] != Double.POSITIVE_INFINITY) {
			return getAFloyd()[getNode(origen)][getNode(destino)];
		} else {
			return -1;
		}
	}
	
	/**
	 * Comprueba si la matriz A está inicializada
	 * @return true si A está inicializada; false si no
	 */
	private boolean isAInitialized() {
		if (getAFloyd() == null) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Comprueba si la matriz P está inicializada
	 * @return true si P está inicializada; false si no
	 */
	private boolean isPInitialized() {	
		if (getPFloyd() == null) {
			return false;
		}
		
		return true;
	}
	
	/**
	* Indica el camino entre los nodos que se le pasan como parámetros
	* con el siguiente formato:
	*
	* Origen<tab>(coste0)<tab>Intermedio1<tab>(coste1)….IntermedioN<tab>(costeN) Destino
	* Si no hay camino: Origen<tab>(Infinity)<tab>Destino
	* Si Origen y Destino coinciden: Origen
	* Si no existen los 2 nodos, devuelve una cadena vacía
	*
	* @param origen nodo origen, de tipo genérico T
	* @param destino nodo destino, de tipo genérico T
	* @return El String con el formato indicado anteriormente
	*/
	public String path(T origen, T destino) {
		this.path = "";	// valor inicial de la cadena
		
		// Se comprueba la existencia de los nodos implicados
		if (!existNode(origen) || !existNode(destino)) {
			return path;
		}
		
		// Se comprueba si origen y destino coinciden
		if (origen.equals(destino)) {
			path += origen;
			return path;
		}
		
		// Se comprueba si no hay camino
		if (getPFloyd()[getNode(origen)][getNode(destino)] == null) {
			path += (origen + "\tInfinity\t" + destino);
			return path;
			
		// En cualquier otro caso (existen, son diferentes y hay camino)
		} else {
			path += origen;
			pathFloydIntermedios(origen, destino);
			path += destino;
			
			return path;
		}
	}
	
	/**
	* Método auxiliar que muestra el camino mínimo entre 2 nodos 
	* usando las matrices de Floyd, y devuelve el camino en la variable path
	* para así mostrar el camino de Floyd
	* @param origen nodo origen, de tipo genérico T
	* @param destino nodo destino, de tipo genérico T
	*/
	private void pathFloydIntermedios(T origen, T destino) {
		T k = P[getNode(origen)][getNode(destino)];	// supuesto nodo intermedio
		
		if (k != null) {	// si hay nodo intermedio...
			
			// 1. Busca más intermedios entre origen y el intermedio hallado
			pathFloydIntermedios(origen, k);
			
			// 2. Muestra el nodo intermedio hallado en el camino
			path += k;
			
			// 3. Busca nodos intermedios entre el intermedio hallado y destino
			pathFloydIntermedios(k, destino);
			
			} else {	// si no encontró nodo intermedio...
			
			// Añade el coste entre origen y destino
			path += ("\t" + getAFloyd()[getNode(origen)][getNode(destino)] + "\t");
		}
	}
	
	/**
	 * Lanza el Recorrido en Profundidad de un grafo desde un nodo determinado
	 * (no necesariamente recorre todos los nodos), y al recorrer cada nodo
	 * añade el toString() del nodo y un tabulador.
	 * @param nodo nodo desde el que comienza el recorrido, de tipo genérico T
	 * @return Recorrido en Profundidad desde el nodo; cadena vacía si no existe
	 */
	public String recorridoProfundidad(T nodo) {
		df = "";
		
		// Se comprueba la existencia del nodo implicado
		if (!existNode(nodo)) {
			return df;
		}
		
		// Se inicializa el vector de nodos visitados
		boolean[] visited = new boolean[getTam()];
		
		// Marca el nodo origen como visitado y lo añade a la cadena
		visited[getNode(nodo)] = true;
		df += nodo.toString();
	
		// Inicia el recorrido en profundidad
		recursivoProfundidad(nodo, visited);
		
		return df;	// devuelve la cadena con el recorrido completo
	}
	
	/**
	* Método auxiliar que implementa el Recorrido en Profundidad:
	* Desde un nodo pasado como parámetro, actualiza el vector de nodos visitados 
	* con dicho nodo y comprueba si existe camino para el resto de nodos 
	* que no se encuentren visitados.
	* En caso de que exista camino, repite el mismo proceso para el nuevo nodo.
	*
	* Además, imprime el recorrido seguido en cadenaDFS
	*
	* @param source nodo origen, de tipo genérico T
	* @param visited vector de nodos visitados, de tipo boolean[]
	*/
	private void recursivoProfundidad(T source, boolean[] visited) {
		visited[getNode(source)] = true;
				
		// Para cada nodo no visitado y accesible desde source...
		for (int i = 0; i < getAristas()[getNode(source)].length; i++) {
			if (!visited[i] && existEdge(source, getNodes()[i])) {
				// Si hay arista, realiza llamada recursiva para recorrerlo
				df += ("\t" + getNodes()[i]);
				recursivoProfundidad(getNodes()[i], visited);
			}
		}
	}
	
	/**
	 * Establece o modifica el array de nodos del grafo
	 * @param nodes array de nodos a establecer, array de T
	 */
	private void setNodes(T[] nodes) {
		this.nodes = nodes;
	}
	
	/**
	 * Devuelve el array de nodos del grafo
	 * @return Array de nodos del grafo, de tipo array de T
	 */
	public T[] getNodes() {
		return this.nodes;
	}
	
	/**
	 * Establece o modifica la matriz de pesos del grafo
	 * @param pesos matriz de pesos a establecer, matriz bidimensional de double
	 */
	private void setPesos(double[][] pesos) {
		this.pesos = pesos;
	}
	
	/**
	 * Devuelve la matriz de pesos del grafo
	 * @return Matriz de pesos del grafo, matriz bidimensional de double
	 */
	public double[][] getPesos() {
		return this.pesos;
	}
	
	/**
	 * Modifica o establece la matriz de aristas del grafo
	 * @param aristas matriz de aristas a establecer, bidimensional de boolean
	 */
	private void setAristas(boolean[][] aristas) {
		this.aristas = aristas;
	}
	
	/**
	 * Devuelve la matriz de aristas del grafo
	 * @return Matriz de aristas del grafo, bidimensional de boolean
	 */
	public boolean[][] getAristas() {
		return this.aristas;
	}
	
	/**
	 * Establece o modifica el número de elementos del grafo
	 * @param tam número de elementos a establecer, de tipo int
	 * @throws IllegalStateException si tam es negativo
	 */
	private void setTam(int tam) {
		assertTam(tam);
		this.tam = tam;
	}
	
	/**
	 * Comprueba si el tamaño del grafo a establecer es válido.
	 * En caso de que no lo sea, lanza una excepción no comprobada
	 * @param tam número de elementos a comprobar, de tipo int
	 * @throws IllegalStateException si tam es negativo
	 */
	private void assertTam(int tam) {
		if (tam < 0) {
			throw new IllegalStateException("No es posible inicializar un grafo"
					+ " con número negativo de elementos.");
		}
	}
	
	/**
	 * Devuelve el número de elementos del grafo
	 * @return Número de elementos del grafo, de tipo int
	 */
	public int getTam() {
		return this.tam;
	}
	
}