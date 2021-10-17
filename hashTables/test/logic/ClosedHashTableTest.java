// Paquete al que pertenece la clase
package logic;

// Importaci�n de clases
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Clase de prueba para las Tablas Hash cerradas
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class ClosedHashTableTest {
	
	private ClosedHashTable<Integer> l;	    // Tabla Hash cerrada con exp. lineal
	private ClosedHashTable<Integer> c;	    // Tabla Hash cerrada con exp. cuadr�tica
	private ClosedHashTable<Integer> d;	    // Tabla Hash cerrada con exp. disp. doble
	
	private ClosedHashTable<Integer> empty; // Tabla Hash cerrada vac�a
	
	@Before
	public void inicializar() {
		l = new ClosedHashTable<Integer>(4, 1, 0.16, 0.4);	// LINEAL
		c = new ClosedHashTable<Integer>(4, 2, 0.16, 0.4);	// CUADR�TICA
		d = new ClosedHashTable<Integer>(4, 3, 0.16, 0.4);	// DOBLE
		
		empty = new ClosedHashTable<Integer>(4, 1, 0.16, 0.4);
	}
	
	// ---------------------- EXPLORACI�N LINEAL -----------------------------

	/**
	 * Pruebas unitarias del m�todo add()
	 */
	@Test
	public void testAddLineal() {
		// Caso 1: Se intenta a�adir un elemento null
		assertEquals(-1, l.add(null));

		// Caso 2: Se a�ade un elemento que caer�a en una posici�n vac�a
		assertEquals(0, l.add(5));			// posici�n 0
		System.out.println(l.toString());
		assertEquals("{5};{_E_};{_E_};{_E_};{_E_};[Size: 5 Num.Elems.: 1]", l.toString());

		// Caso 3: Se a�ade un elemento que caer�a en una posici�n llena (sin�nimo)
		assertEquals(0, l.add(5));			// posici�n 0 (llena) -> posici�n 1
		System.out.println(l.toString());
		assertEquals("{5};{5};{_E_};{_E_};{_E_};[Size: 5 Num.Elems.: 2]", l.toString());

		// Caso 4: Se a�ade otro elemento no sin�nimo que produce redispersi�n
		assertEquals(0, l.add(7));			// posici�n 7
		System.out.println(l.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{5};{7};{_E_};{_E_};{_E_};[Size: 11 Num.Elems.: 3]",
				l.toString());

		// Caso 5: Se inserta un elemento a caer en una posici�n con elemento borrado
		l.remove(5);						// posici�n 5 pasa a BORRADO
		System.out.println(l.toString());
		assertEquals(0, l.add(16));			// posici�n 5 (borrada)
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{16};{5};{7};{_E_};{_E_};{_E_};[Size: 11 Num.Elems.: 3]",
				l.toString());
	}
	
	/**
	 * Pruebas unitarias del m�todo find()
	 */
	@Test
	public void testFindLineal() {		
		// Caso 1: Se intenta encontrar un elemento en una Tabla Hash vac�a
		assertNull(empty.find(9));
		
		// Caso 2: Se intenta encontrar un elemento que es null
		assertNull(l.find(null));
		
		// Caso 3: La posici�n correspondiente al elemento a buscar est� vac�a
		assertNull(l.find(2));
		
		// Caso 4: La posici�n correspondiente al elemento est� llena y coincide
		l.add(6);							// posici�n 1 (vac�a)
		System.out.println(l.toString());
		assertEquals(Integer.toString(6), l.find(6).toString());
		
		// Caso 5: La posici�n correspondiente al elemento est� llena y no coincide
		l.add(11);							// posici�n 1 (llena) -> posici�n 2
		System.out.println(l.toString());
		assertEquals(Integer.toString(11), l.find(11).toString());
		
		// Caso 6: La posici�n correspondiente al elemento est� borrada
		l.remove(6);						// posici�n 1 pasa a BORRADO
		System.out.println(l.toString());
		assertEquals(Integer.toString(11), l.find(11).toString());
		
		// Caso 7: El elemento no est� en la tabla (agota iteraciones)
		assertNull(l.find(40));
	}
	
	/**
	 * Pruebas unitarias del m�todo remove()
	 */
	@Test
	public void testRemoveLineal() {
		// Caso 1: Se intenta borrar de una tabla vac�a
		assertEquals(-3, empty.remove(8));
		
		// Caso 2: Se intenta eliminar un elemento que es null
		l.add(20);										// posici�n 0
		System.out.println(l.toString());
		assertEquals(-1, l.remove(null));
		
		// Caso 3: Se intenta eliminar un elemento que no existe en la tabla
		assertEquals(-2, l.remove(0));
		
		// Caso 4: La posici�n correspondiente al elemento est� llena y coincide
		assertEquals(0, l.remove(20));					// posici�n 0 (llena)
		System.out.println(l.toString());
		
		// Se produce redispersi�n inversa
		assertEquals("{_E_};{_E_};[Size: 2 Num.Elems.: 0]", l.toString());
		
		// Caso 5: La posici�n correspondiente al elemento est� llena y no coincide
		l.add(0);	// posici�n 0
		l.add(20);	// posici�n 0 (llena) -> posici�n 1
		
		// Se produce redispersi�n
		System.out.println(l.toString());
		assertEquals(0, l.remove(20));
		
		System.out.println(l.toString());
		assertEquals("{0};{_D_};{_E_};{_E_};{_E_};[Size: 5 Num.Elems.: 1]", l.toString());
		
		// Caso 6: La posici�n correspondiente al elemento est� borrada
		l.remove(0);
		System.out.println(l.toString());
		l.add(1);					   		// posici�n 1
		System.out.println(l.toString());
		l.add(16);					   		// posici�n 1 (llena) -> posici�n 2
		System.out.println(l.toString());
		l.remove(1);
		System.out.println(l.toString());
		assertEquals(0, l.remove(16)); 		// caer�a en la posici�n 1 (borrada)
		assertEquals("{_E_};{_E_};[Size: 2 Num.Elems.: 0]", l.toString());
	}
	
	// ---------------------- EXPLORACI�N CUADR�TICA --------------------------
	
	/**
	 * Pruebas unitarias del m�todo add()
	 */
	@Test
	public void testAddCuadratico() {
		// Caso 1: Se intenta a�adir un elemento null
		assertEquals(-1, c.add(null));

		// Caso 2: Se a�ade un elemento que caer�a en una posici�n vac�a
		assertEquals(0, c.add(5));			// posici�n 0
		System.out.println(c.toString());
		assertEquals("{5};{_E_};{_E_};{_E_};{_E_};[Size: 5 Num.Elems.: 1]", c.toString());

		// Caso 3: Se a�ade un elemento que caer�a en una posici�n llena (sin�nimo)
		assertEquals(0, c.add(5));			// posici�n 0 (llena) -> posici�n 1
		System.out.println(c.toString());
		assertEquals("{5};{5};{_E_};{_E_};{_E_};[Size: 5 Num.Elems.: 2]", c.toString());

		// Caso 4: Se a�ade otro elemento no sin�nimo que produce redispersi�n
		assertEquals(0, c.add(7));			// posici�n 7
		System.out.println(c.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{5};{7};{_E_};{_E_};{_E_};[Size: 11 Num.Elems.: 3]",
				c.toString());

		// Caso 5: Se inserta un elemento a caer en una posici�n con elemento borrado
		c.remove(5);						// posici�n 5 pasa a BORRADO
		System.out.println(c.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_D_};{5};{7};{_E_};{_E_};{_E_};[Size: 11 Num.Elems.: 2]",
				c.toString());
		assertEquals(0, c.add(16));			// posici�n 5 (borrada)
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{16};{5};{7};{_E_};{_E_};{_E_};[Size: 11 Num.Elems.: 3]",
				c.toString());
		
		// Caso 6: Se inserta un elemento de forma que requiere m�s de un intento
		assertEquals(0, c.add(16));			// posici�n 5 (llena) -> posici�n 9
		System.out.println(c.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{16};{5};{7};{_E_};{16};{_E_};[Size: 11 Num.Elems.: 4]",
				c.toString());
	}
	
	/**
	 * Pruebas unitarias del m�todo find()
	 */
	@Test
	public void testFindCuadratico() {
		// Caso 1: Se intenta encontrar un elemento en una Tabla Hash vac�a
		assertNull(empty.find(9));
		
		// Caso 2: Se intenta encontrar un elemento que es null
		assertNull(c.find(null));
		
		// Caso 3: La posici�n correspondiente al elemento a buscar est� vac�a
		assertNull(c.find(2));
		
		// Caso 4: La posici�n correspondiente al elemento est� llena y coincide
		c.add(6);							// posici�n 1 (vac�a)
		System.out.println(c.toString());
		assertEquals(Integer.toString(6), c.find(6).toString());
		
		// Caso 5: La posici�n correspondiente al elemento est� llena y no coincide
		c.add(11);							// posici�n 1 (llena) -> posici�n 2
		System.out.println(c.toString());
		assertEquals(Integer.toString(11), c.find(11).toString());
		
		// Caso 6: La posici�n correspondiente al elemento est� borrada
		c.remove(6);						// posici�n 1 pasa a BORRADO
		System.out.println(c.toString());
		assertEquals(Integer.toString(11), c.find(11).toString());
		
		// Caso 7: El elemento no est� en la tabla (agota iteraciones)
		assertNull(c.find(40));
	}
	
	/**
	 * Pruebas unitarias del m�todo remove()
	 */
	@Test
	public void testRemoveCuadratico() {
		// Caso 1: Se intenta borrar de una tabla vac�a
		assertEquals(-3, empty.remove(8));
		
		// Caso 2: Se intenta eliminar un elemento que es null
		c.add(20);							// posici�n 0
		System.out.println(c.toString());
		assertEquals(-1, c.remove(null));
		
		// Caso 3: Se intenta eliminar un elemento que no existe en la tabla
		assertEquals(-2, c.remove(0));
		
		// Caso 4: La posici�n correspondiente al elemento est� llena y coincide
		assertEquals(0, c.remove(20));		// posici�n 0 (coincide)
		System.out.println(c.toString());
		
		// Se produce redispersi�n inversa
		assertEquals("{_E_};{_E_};[Size: 2 Num.Elems.: 0]", c.toString());
		
		// Caso 5: La posici�n correspondiente al elemento est� llena y no coincide
		c.add(0);							// posici�n 0
		c.add(20);							// posici�n 0 (llena) -> posici�n 1
		
		// Se produce redispersi�n
		System.out.println(c.toString());
		assertEquals(0, c.remove(20));
		
		System.out.println(c.toString());
		assertEquals("{0};{_D_};{_E_};{_E_};{_E_};[Size: 5 Num.Elems.: 1]", c.toString());
		
		// Caso 6: La posici�n correspondiente al elemento est� borrada
		c.remove(0);
		System.out.println(c.toString());
		c.add(1);					   		// posici�n 1
		System.out.println(c.toString());
		c.add(16);					   		// posici�n 1 (llena) -> posici�n 2
		System.out.println(c.toString());
		c.remove(1);
		System.out.println(c.toString());
		assertEquals(0, c.remove(16)); 		// caer�a en la posici�n 1 (borrada)
		assertEquals("{_E_};{_E_};[Size: 2 Num.Elems.: 0]", c.toString());
	}
	
	// ---------------------- DISPERSI�N DOBLE -------------------------------

	/**
	 * Pruebas unitarias del m�todo add()
	 */
	@Test
	public void testAddDoble() {
		// Caso 1: Se intenta a�adir un elemento null
		assertEquals(-1, d.add(null));

		// Caso 2: Se a�ade un elemento que caer�a en una posici�n vac�a
		assertEquals(0, d.add(5));			// posici�n 0
		System.out.println(d.toString());
		assertEquals("{5};{_E_};{_E_};{_E_};{_E_};[Size: 5 Num.Elems.: 1]", d.toString());

		// Caso 3: Se a�ade un elemento que caer�a en una posici�n llena (sin�nimo)
		assertEquals(0, d.add(5));			// posici�n 0 (llena) -> pos. 3
		System.out.println(d.toString());
		assertEquals("{5};{_E_};{_E_};{5};{_E_};[Size: 5 Num.Elems.: 2]", d.toString());

		// Caso 4: Se a�ade otro elemento no sin�nimo que produce redispersi�n
		assertEquals(0, d.add(7));			// posici�n 7
		System.out.println(d.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{_E_};{7};{_E_};{5};{_E_};[Size: 11 Num.Elems.: 3]",
				d.toString());

		// Caso 5: Se inserta un elemento a caer en una posici�n con elemento borrado
		d.remove(5);
		System.out.println(d.toString());
		assertEquals(0, d.add(16));			// posici�n 5
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{16};{_E_};{7};{_E_};{5};{_E_};[Size: 11 Num.Elems.: 3]",
				d.toString());
	}
	
	/**
	 * Pruebas unitarias del m�todo find()
	 */
	@Test
	public void testFindDoble() {
		// Caso 1: Se intenta encontrar un elemento en una Tabla Hash vac�a
		assertNull(empty.find(9));

		// Caso 2: Se intenta encontrar un elemento que es null
		assertNull(d.find(null));

		// Caso 3: La posici�n correspondiente al elemento a buscar est� vac�a
		assertNull(d.find(2));

		// Caso 4: La posici�n correspondiente al elemento est� llena y coincide
		d.add(6); 							// posici�n 1 (vac�a)
		System.out.println(d.toString());
		assertEquals(Integer.toString(6), d.find(6).toString());

		// Caso 5: La posici�n correspondiente al elemento est� llena y no coincide
		d.add(11); 							// posici�n 1 (llena) -> posici�n 2
		System.out.println(d.toString());
		assertEquals(Integer.toString(11), d.find(11).toString());

		// Caso 6: La posici�n correspondiente al elemento est� borrada
		d.remove(6); 						// posici�n 1 pasa a BORRADO
		System.out.println(d.toString());
		assertEquals(Integer.toString(11), d.find(11).toString());

		// Caso 7: El elemento no est� en la tabla (agota iteraciones)
		assertNull(d.find(40));
	}
	
	/**
	 * Pruebas unitarias del m�todo remove()
	 */
	@Test
	public void testRemove() {
		// Caso 1: Se intenta borrar de una tabla vac�a
		assertEquals(-3, empty.remove(8));

		// Caso 2: Se intenta eliminar un elemento que es null
		d.add(20); 							// posici�n 0
		System.out.println(d.toString());
		assertEquals(-1, d.remove(null));

		// Caso 3: Se intenta eliminar un elemento que no existe en la tabla
		assertEquals(-2, d.remove(0));

		// Caso 4: La posici�n correspondiente al elemento est� llena y coincide
		assertEquals(0, d.remove(20)); 		// posici�n 0 (llena)
		System.out.println(d.toString());

		// Se produce redispersi�n inversa
		assertEquals("{_E_};{_E_};[Size: 2 Num.Elems.: 0]", d.toString());

		// Caso 5: La posici�n correspondiente al elemento est� llena y no coincide
		d.add(0); 							// posici�n 0
		System.out.println(d.toString());
		// Se produce redispersi�n
		d.add(20); 							// posici�n 0 (llena) -> posici�n 3
		System.out.println(d.toString());
		assertEquals(0, d.remove(20));

		System.out.println(d.toString());
		assertEquals("{0};{_E_};{_E_};{_D_};{_E_};[Size: 5 Num.Elems.: 1]", d.toString());

		// Caso 6: La posici�n correspondiente al elemento est� borrada
		d.remove(0);
		// Se produce redispersi�n inversa
		System.out.println(d.toString());
		d.add(1); 							// posici�n 1
		// Se produce redispersi�n
		System.out.println(d.toString());
		d.add(16); 							// posici�n 1 (llena) -> posici�n 3
		System.out.println(d.toString());
		d.remove(1);
		System.out.println(d.toString());
		assertEquals(0, d.remove(16)); 		// caer�a en la posici�n 1 (borrada)
		assertEquals("{_E_};{_E_};[Size: 2 Num.Elems.: 0]", d.toString());
	}
}