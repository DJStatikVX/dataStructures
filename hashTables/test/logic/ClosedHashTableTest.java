// Paquete al que pertenece la clase
package logic;

// Importación de clases
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Clase de prueba para las Tablas Hash cerradas
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class ClosedHashTableTest {
	
	private ClosedHashTable<Integer> l;	    // Tabla Hash cerrada con exp. lineal
	private ClosedHashTable<Integer> c;	    // Tabla Hash cerrada con exp. cuadrática
	private ClosedHashTable<Integer> d;	    // Tabla Hash cerrada con exp. disp. doble
	
	private ClosedHashTable<Integer> empty; // Tabla Hash cerrada vacía
	
	@Before
	public void inicializar() {
		l = new ClosedHashTable<Integer>(4, 1, 0.16, 0.4);	// LINEAL
		c = new ClosedHashTable<Integer>(4, 2, 0.16, 0.4);	// CUADRÁTICA
		d = new ClosedHashTable<Integer>(4, 3, 0.16, 0.4);	// DOBLE
		
		empty = new ClosedHashTable<Integer>(4, 1, 0.16, 0.4);
	}
	
	// ---------------------- EXPLORACIÓN LINEAL -----------------------------

	/**
	 * Pruebas unitarias del método add()
	 */
	@Test
	public void testAddLineal() {
		// Caso 1: Se intenta añadir un elemento null
		assertEquals(-1, l.add(null));

		// Caso 2: Se añade un elemento que caería en una posición vacía
		assertEquals(0, l.add(5));			// posición 0
		System.out.println(l.toString());
		assertEquals("{5};{_E_};{_E_};{_E_};{_E_};[Size: 5 Num.Elems.: 1]", l.toString());

		// Caso 3: Se añade un elemento que caería en una posición llena (sinónimo)
		assertEquals(0, l.add(5));			// posición 0 (llena) -> posición 1
		System.out.println(l.toString());
		assertEquals("{5};{5};{_E_};{_E_};{_E_};[Size: 5 Num.Elems.: 2]", l.toString());

		// Caso 4: Se añade otro elemento no sinónimo que produce redispersión
		assertEquals(0, l.add(7));			// posición 7
		System.out.println(l.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{5};{7};{_E_};{_E_};{_E_};[Size: 11 Num.Elems.: 3]",
				l.toString());

		// Caso 5: Se inserta un elemento a caer en una posición con elemento borrado
		l.remove(5);						// posición 5 pasa a BORRADO
		System.out.println(l.toString());
		assertEquals(0, l.add(16));			// posición 5 (borrada)
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{16};{5};{7};{_E_};{_E_};{_E_};[Size: 11 Num.Elems.: 3]",
				l.toString());
	}
	
	/**
	 * Pruebas unitarias del método find()
	 */
	@Test
	public void testFindLineal() {		
		// Caso 1: Se intenta encontrar un elemento en una Tabla Hash vacía
		assertNull(empty.find(9));
		
		// Caso 2: Se intenta encontrar un elemento que es null
		assertNull(l.find(null));
		
		// Caso 3: La posición correspondiente al elemento a buscar está vacía
		assertNull(l.find(2));
		
		// Caso 4: La posición correspondiente al elemento está llena y coincide
		l.add(6);							// posición 1 (vacía)
		System.out.println(l.toString());
		assertEquals(Integer.toString(6), l.find(6).toString());
		
		// Caso 5: La posición correspondiente al elemento está llena y no coincide
		l.add(11);							// posición 1 (llena) -> posición 2
		System.out.println(l.toString());
		assertEquals(Integer.toString(11), l.find(11).toString());
		
		// Caso 6: La posición correspondiente al elemento está borrada
		l.remove(6);						// posición 1 pasa a BORRADO
		System.out.println(l.toString());
		assertEquals(Integer.toString(11), l.find(11).toString());
		
		// Caso 7: El elemento no está en la tabla (agota iteraciones)
		assertNull(l.find(40));
	}
	
	/**
	 * Pruebas unitarias del método remove()
	 */
	@Test
	public void testRemoveLineal() {
		// Caso 1: Se intenta borrar de una tabla vacía
		assertEquals(-3, empty.remove(8));
		
		// Caso 2: Se intenta eliminar un elemento que es null
		l.add(20);										// posición 0
		System.out.println(l.toString());
		assertEquals(-1, l.remove(null));
		
		// Caso 3: Se intenta eliminar un elemento que no existe en la tabla
		assertEquals(-2, l.remove(0));
		
		// Caso 4: La posición correspondiente al elemento está llena y coincide
		assertEquals(0, l.remove(20));					// posición 0 (llena)
		System.out.println(l.toString());
		
		// Se produce redispersión inversa
		assertEquals("{_E_};{_E_};[Size: 2 Num.Elems.: 0]", l.toString());
		
		// Caso 5: La posición correspondiente al elemento está llena y no coincide
		l.add(0);	// posición 0
		l.add(20);	// posición 0 (llena) -> posición 1
		
		// Se produce redispersión
		System.out.println(l.toString());
		assertEquals(0, l.remove(20));
		
		System.out.println(l.toString());
		assertEquals("{0};{_D_};{_E_};{_E_};{_E_};[Size: 5 Num.Elems.: 1]", l.toString());
		
		// Caso 6: La posición correspondiente al elemento está borrada
		l.remove(0);
		System.out.println(l.toString());
		l.add(1);					   		// posición 1
		System.out.println(l.toString());
		l.add(16);					   		// posición 1 (llena) -> posición 2
		System.out.println(l.toString());
		l.remove(1);
		System.out.println(l.toString());
		assertEquals(0, l.remove(16)); 		// caería en la posición 1 (borrada)
		assertEquals("{_E_};{_E_};[Size: 2 Num.Elems.: 0]", l.toString());
	}
	
	// ---------------------- EXPLORACIÓN CUADRÁTICA --------------------------
	
	/**
	 * Pruebas unitarias del método add()
	 */
	@Test
	public void testAddCuadratico() {
		// Caso 1: Se intenta añadir un elemento null
		assertEquals(-1, c.add(null));

		// Caso 2: Se añade un elemento que caería en una posición vacía
		assertEquals(0, c.add(5));			// posición 0
		System.out.println(c.toString());
		assertEquals("{5};{_E_};{_E_};{_E_};{_E_};[Size: 5 Num.Elems.: 1]", c.toString());

		// Caso 3: Se añade un elemento que caería en una posición llena (sinónimo)
		assertEquals(0, c.add(5));			// posición 0 (llena) -> posición 1
		System.out.println(c.toString());
		assertEquals("{5};{5};{_E_};{_E_};{_E_};[Size: 5 Num.Elems.: 2]", c.toString());

		// Caso 4: Se añade otro elemento no sinónimo que produce redispersión
		assertEquals(0, c.add(7));			// posición 7
		System.out.println(c.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{5};{7};{_E_};{_E_};{_E_};[Size: 11 Num.Elems.: 3]",
				c.toString());

		// Caso 5: Se inserta un elemento a caer en una posición con elemento borrado
		c.remove(5);						// posición 5 pasa a BORRADO
		System.out.println(c.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_D_};{5};{7};{_E_};{_E_};{_E_};[Size: 11 Num.Elems.: 2]",
				c.toString());
		assertEquals(0, c.add(16));			// posición 5 (borrada)
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{16};{5};{7};{_E_};{_E_};{_E_};[Size: 11 Num.Elems.: 3]",
				c.toString());
		
		// Caso 6: Se inserta un elemento de forma que requiere más de un intento
		assertEquals(0, c.add(16));			// posición 5 (llena) -> posición 9
		System.out.println(c.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{16};{5};{7};{_E_};{16};{_E_};[Size: 11 Num.Elems.: 4]",
				c.toString());
	}
	
	/**
	 * Pruebas unitarias del método find()
	 */
	@Test
	public void testFindCuadratico() {
		// Caso 1: Se intenta encontrar un elemento en una Tabla Hash vacía
		assertNull(empty.find(9));
		
		// Caso 2: Se intenta encontrar un elemento que es null
		assertNull(c.find(null));
		
		// Caso 3: La posición correspondiente al elemento a buscar está vacía
		assertNull(c.find(2));
		
		// Caso 4: La posición correspondiente al elemento está llena y coincide
		c.add(6);							// posición 1 (vacía)
		System.out.println(c.toString());
		assertEquals(Integer.toString(6), c.find(6).toString());
		
		// Caso 5: La posición correspondiente al elemento está llena y no coincide
		c.add(11);							// posición 1 (llena) -> posición 2
		System.out.println(c.toString());
		assertEquals(Integer.toString(11), c.find(11).toString());
		
		// Caso 6: La posición correspondiente al elemento está borrada
		c.remove(6);						// posición 1 pasa a BORRADO
		System.out.println(c.toString());
		assertEquals(Integer.toString(11), c.find(11).toString());
		
		// Caso 7: El elemento no está en la tabla (agota iteraciones)
		assertNull(c.find(40));
	}
	
	/**
	 * Pruebas unitarias del método remove()
	 */
	@Test
	public void testRemoveCuadratico() {
		// Caso 1: Se intenta borrar de una tabla vacía
		assertEquals(-3, empty.remove(8));
		
		// Caso 2: Se intenta eliminar un elemento que es null
		c.add(20);							// posición 0
		System.out.println(c.toString());
		assertEquals(-1, c.remove(null));
		
		// Caso 3: Se intenta eliminar un elemento que no existe en la tabla
		assertEquals(-2, c.remove(0));
		
		// Caso 4: La posición correspondiente al elemento está llena y coincide
		assertEquals(0, c.remove(20));		// posición 0 (coincide)
		System.out.println(c.toString());
		
		// Se produce redispersión inversa
		assertEquals("{_E_};{_E_};[Size: 2 Num.Elems.: 0]", c.toString());
		
		// Caso 5: La posición correspondiente al elemento está llena y no coincide
		c.add(0);							// posición 0
		c.add(20);							// posición 0 (llena) -> posición 1
		
		// Se produce redispersión
		System.out.println(c.toString());
		assertEquals(0, c.remove(20));
		
		System.out.println(c.toString());
		assertEquals("{0};{_D_};{_E_};{_E_};{_E_};[Size: 5 Num.Elems.: 1]", c.toString());
		
		// Caso 6: La posición correspondiente al elemento está borrada
		c.remove(0);
		System.out.println(c.toString());
		c.add(1);					   		// posición 1
		System.out.println(c.toString());
		c.add(16);					   		// posición 1 (llena) -> posición 2
		System.out.println(c.toString());
		c.remove(1);
		System.out.println(c.toString());
		assertEquals(0, c.remove(16)); 		// caería en la posición 1 (borrada)
		assertEquals("{_E_};{_E_};[Size: 2 Num.Elems.: 0]", c.toString());
	}
	
	// ---------------------- DISPERSIÓN DOBLE -------------------------------

	/**
	 * Pruebas unitarias del método add()
	 */
	@Test
	public void testAddDoble() {
		// Caso 1: Se intenta añadir un elemento null
		assertEquals(-1, d.add(null));

		// Caso 2: Se añade un elemento que caería en una posición vacía
		assertEquals(0, d.add(5));			// posición 0
		System.out.println(d.toString());
		assertEquals("{5};{_E_};{_E_};{_E_};{_E_};[Size: 5 Num.Elems.: 1]", d.toString());

		// Caso 3: Se añade un elemento que caería en una posición llena (sinónimo)
		assertEquals(0, d.add(5));			// posición 0 (llena) -> pos. 3
		System.out.println(d.toString());
		assertEquals("{5};{_E_};{_E_};{5};{_E_};[Size: 5 Num.Elems.: 2]", d.toString());

		// Caso 4: Se añade otro elemento no sinónimo que produce redispersión
		assertEquals(0, d.add(7));			// posición 7
		System.out.println(d.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{_E_};{7};{_E_};{5};{_E_};[Size: 11 Num.Elems.: 3]",
				d.toString());

		// Caso 5: Se inserta un elemento a caer en una posición con elemento borrado
		d.remove(5);
		System.out.println(d.toString());
		assertEquals(0, d.add(16));			// posición 5
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{16};{_E_};{7};{_E_};{5};{_E_};[Size: 11 Num.Elems.: 3]",
				d.toString());
	}
	
	/**
	 * Pruebas unitarias del método find()
	 */
	@Test
	public void testFindDoble() {
		// Caso 1: Se intenta encontrar un elemento en una Tabla Hash vacía
		assertNull(empty.find(9));

		// Caso 2: Se intenta encontrar un elemento que es null
		assertNull(d.find(null));

		// Caso 3: La posición correspondiente al elemento a buscar está vacía
		assertNull(d.find(2));

		// Caso 4: La posición correspondiente al elemento está llena y coincide
		d.add(6); 							// posición 1 (vacía)
		System.out.println(d.toString());
		assertEquals(Integer.toString(6), d.find(6).toString());

		// Caso 5: La posición correspondiente al elemento está llena y no coincide
		d.add(11); 							// posición 1 (llena) -> posición 2
		System.out.println(d.toString());
		assertEquals(Integer.toString(11), d.find(11).toString());

		// Caso 6: La posición correspondiente al elemento está borrada
		d.remove(6); 						// posición 1 pasa a BORRADO
		System.out.println(d.toString());
		assertEquals(Integer.toString(11), d.find(11).toString());

		// Caso 7: El elemento no está en la tabla (agota iteraciones)
		assertNull(d.find(40));
	}
	
	/**
	 * Pruebas unitarias del método remove()
	 */
	@Test
	public void testRemove() {
		// Caso 1: Se intenta borrar de una tabla vacía
		assertEquals(-3, empty.remove(8));

		// Caso 2: Se intenta eliminar un elemento que es null
		d.add(20); 							// posición 0
		System.out.println(d.toString());
		assertEquals(-1, d.remove(null));

		// Caso 3: Se intenta eliminar un elemento que no existe en la tabla
		assertEquals(-2, d.remove(0));

		// Caso 4: La posición correspondiente al elemento está llena y coincide
		assertEquals(0, d.remove(20)); 		// posición 0 (llena)
		System.out.println(d.toString());

		// Se produce redispersión inversa
		assertEquals("{_E_};{_E_};[Size: 2 Num.Elems.: 0]", d.toString());

		// Caso 5: La posición correspondiente al elemento está llena y no coincide
		d.add(0); 							// posición 0
		System.out.println(d.toString());
		// Se produce redispersión
		d.add(20); 							// posición 0 (llena) -> posición 3
		System.out.println(d.toString());
		assertEquals(0, d.remove(20));

		System.out.println(d.toString());
		assertEquals("{0};{_E_};{_E_};{_D_};{_E_};[Size: 5 Num.Elems.: 1]", d.toString());

		// Caso 6: La posición correspondiente al elemento está borrada
		d.remove(0);
		// Se produce redispersión inversa
		System.out.println(d.toString());
		d.add(1); 							// posición 1
		// Se produce redispersión
		System.out.println(d.toString());
		d.add(16); 							// posición 1 (llena) -> posición 3
		System.out.println(d.toString());
		d.remove(1);
		System.out.println(d.toString());
		assertEquals(0, d.remove(16)); 		// caería en la posición 1 (borrada)
		assertEquals("{_E_};{_E_};[Size: 2 Num.Elems.: 0]", d.toString());
	}
}