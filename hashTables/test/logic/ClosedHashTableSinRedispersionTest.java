// Paquete al que pertenece la clase
package logic;

// Importación de clases
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Clase de pruebas unitarias para Tablas Hash sin redispersión (no funcionan)
 * @author mrsuarez
 */
public class ClosedHashTableSinRedispersionTest {
	
	/**
	 * Ahorra hacer el resto de pruebas, ya que está implementado con redispersión
	 */
	@Before
	public void startUp() {
		fail("La implementación del proyecto hace uso de dispersión.");
	}

	/**
	 * Pruebas con números enteros (Integer)
	 */
	@Test
	public void test1() {
		System.out.println("Pruebas con enteros");
		// Crea una tabla del tamaño 4 (número no primo)
		ClosedHashTable<Integer> tabla = new ClosedHashTable<Integer>(11, 2, 0.16, 0.5);
		// Muestra la tabla. Deberá estar vacía y ser de tamaño 5
		System.out.println(tabla.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 11 Num.Elems.: 0]", tabla.toString());
		
		// Inserta un null
		assertEquals(-1, tabla.add(null));

		// Inserta elementos
		assertEquals(0, tabla.add(8));
		assertEquals(0, tabla.add(10));
		System.out.println(tabla.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{8};{_E_};{10};[Size: 11 Num.Elems.: 2]", tabla.toString());
		
		assertEquals(0, tabla.add(66));
		System.out.println(tabla.toString());		
		assertEquals("{66};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{8};{_E_};{10};[Size: 11 Num.Elems.: 3]", tabla.toString());
		
		// Inserta elementos con colisión
		assertEquals(0, tabla.add(77));
		System.out.println(tabla.toString());	
		assertEquals("{66};{77};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{8};{_E_};{10};[Size: 11 Num.Elems.: 4]", tabla.toString());

		assertEquals(0, tabla.add(88));
		System.out.println(tabla.toString());	
		assertEquals("{66};{77};{_E_};{_E_};{88};{_E_};{_E_};{_E_};{8};{_E_};{10};[Size: 11 Num.Elems.: 5]", tabla.toString());

		assertEquals(0, tabla.add(89));
		System.out.println(tabla.toString());
		assertEquals("{66};{77};{89};{_E_};{88};{_E_};{_E_};{_E_};{8};{_E_};{10};[Size: 11 Num.Elems.: 6]", tabla.toString());
		
		
		// Sigue insertando elementos
		assertEquals(0, tabla.add(3));
		assertEquals(0, tabla.add(6));
		assertEquals(0, tabla.add(7));
		assertEquals(0, tabla.add(20));
		assertEquals(0, tabla.add(16));
		System.out.println(tabla.toString());
		assertEquals("{66};{77};{89};{3};{88};{16};{6};{7};{8};{20};{10};[Size: 11 Num.Elems.: 11]", tabla.toString());
		
	
		//Borra un elemento que existe
		assertEquals(0, tabla.remove(7));
		System.out.println(tabla.toString());
		assertEquals("{66};{77};{89};{3};{88};{16};{6};{_D_};{8};{20};{10};[Size: 11 Num.Elems.: 10]", tabla.toString());
		
		// Borra un null
		assertEquals(-1, tabla.remove(null));
		
		// Borra un elemento que no existe
		assertEquals(-2, tabla.remove(2));
		

		// Borrar elementos
		assertEquals(0, tabla.remove(77));
		System.out.println(tabla.toString());
		assertEquals("{66};{_D_};{89};{3};{88};{16};{6};{_D_};{8};{20};{10};[Size: 11 Num.Elems.: 9]", tabla.toString());

		assertEquals(0, tabla.remove(89));
		System.out.println(tabla.toString());
		assertEquals("{66};{_D_};{_D_};{3};{88};{16};{6};{_D_};{8};{20};{10};[Size: 11 Num.Elems.: 8]", tabla.toString());

		assertEquals(0, tabla.remove(88));
		System.out.println(tabla.toString());
		assertEquals("{66};{_D_};{_D_};{3};{_D_};{16};{6};{_D_};{8};{20};{10};[Size: 11 Num.Elems.: 7]", tabla.toString());

		assertEquals(0, tabla.remove(20));
		System.out.println(tabla.toString());	
		assertEquals("{66};{_D_};{_D_};{3};{_D_};{16};{6};{_D_};{8};{_D_};{10};[Size: 11 Num.Elems.: 6]", tabla.toString());
	
		// Inserta un elemento que ocuparía una posisión ya borrada
		assertEquals(0, tabla.add(44));
		System.out.println(tabla.toString());	
		assertEquals("{66};{44};{_D_};{3};{_D_};{16};{6};{_D_};{8};{_D_};{10};[Size: 11 Num.Elems.: 7]", tabla.toString());

	}
	
}