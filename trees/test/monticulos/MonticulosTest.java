// Paquete al que pertenece la clase
package monticulos;

// Importación de clases
import static org.junit.Assert.*;

import org.junit.Test;

import monticulos.BinaryHeap;
import monticulos.Persona;

/**
 * Pruebas unitarias de la clase BinaryHeap
 * @author mrsuarez
 */
public class MonticulosTest {

	/**
	 * Test para probar un montículo de enteros
	 */
	@Test
	public void test1() {
		System.out.println("Montículos de enteros");
		System.out.println("*********************");
		BinaryHeap<Integer> m = new BinaryHeap<Integer>(7);
				
		// Intenta borrar en un montículo vacío
		assertEquals(-2, m.remove(5));
		
		assertEquals(0, m.add(2));
		assertEquals(0, m.add(4));
		assertEquals(0, m.add(12));
		assertEquals(0, m.add(5));
		assertEquals(0, m.add(6));
		assertEquals("2\t4\t12\t5\t6", m.toString());
		System.out.println(m.toString());
		
		// Añade un elemento que ya existe
		assertEquals(-1, m.add(6));
		
		// Borra un elemento que no existe
		assertEquals(-1, m.remove(40));
		
		assertEquals(0, m.add(8));
		assertEquals("2\t4\t8\t5\t6\t12", m.toString());
		System.out.println(m.toString());
		assertEquals(0, m.remove(4));
		assertEquals("2\t5\t8\t12\t6", m.toString());
		System.out.println(m.toString());
		
		// Inserto elementos
		assertEquals(0, m.add(1));
		assertEquals("1\t5\t2\t12\t6\t8", m.toString());
		System.out.println(m.toString());
		
		// Inserto elementos
		assertEquals(0, m.add(10));
		assertEquals("1\t5\t2\t12\t6\t8\t10", m.toString());
		System.out.println(m.toString());
		
		// Inserto y no cabe
		assertEquals(-3, m.add(20));
		assertEquals("1\t5\t2\t12\t6\t8\t10", m.toString());
		System.out.println(m.toString());
		
		// Borra la raíz
		assertEquals(new Integer(1), m.getTop());
		assertEquals("2\t5\t8\t12\t6\t10", m.toString());
		System.out.println(m.toString());
		

		// Cambiar la prioridad de un elemento a una mayor que la que tiene
		assertEquals(0, m.cambiarPrioridad(2, new Integer(20)));
		assertEquals("2\t5\t10\t12\t6\t20", m.toString());
		System.out.println(m.toString());
		
		// Cambiar la prioridad de un elemento a una menor que la que tiene
		assertEquals(0, m.cambiarPrioridad(3, new Integer(1)));
		assertEquals("1\t2\t10\t5\t6\t20", m.toString());
		System.out.println(m.toString());
		System.out.println("\n");

	}
	
	/**
	 * Test para probar un monticulo de objetos persona
	 */
	@Test
	public void test2() {
		System.out.println("Montículos de Personas");
		System.out.println("*********************");
		BinaryHeap<Persona> m= new BinaryHeap<Persona>(7);

				
		// Intenta borrar en un montículo vacio
		assertEquals(-2, m.remove(new Persona(1, "Pedro")));
		
		assertEquals(0, m.add(new Persona(2, "Pedro")));
		assertEquals(0, m.add(new Persona(4, "Clara")));
		assertEquals(0, m.add(new Persona(12, "Julian")));
		assertEquals(0, m.add(new Persona(5, "Luisa")));
		assertEquals(0, m.add(new Persona(6, "María")));

		assertEquals("[2:Pedro]\t[4:Clara]\t[12:Julian]\t[5:Luisa]\t[6:María]", m.toString());
		System.out.println(m.toString());
		
		// Añade un elemento que ya existe
		assertEquals(-1, m.add(new Persona(12, "Julian")));
		
		// Borra un elemento que no existe
		assertEquals(-1, m.remove(new Persona(8, "Carlos")));
		
		assertEquals(0, m.add(new Persona(8, "Carmen")));
		assertEquals("[2:Pedro]\t[4:Clara]\t[8:Carmen]\t[5:Luisa]\t[6:María]\t[12:Julian]", m.toString());
		System.out.println(m.toString());
		assertEquals(0, m.remove(new Persona(8, "Carlos")));
		assertEquals("[2:Pedro]\t[4:Clara]\t[12:Julian]\t[5:Luisa]\t[6:María]", m.toString());
		System.out.println(m.toString());
		
		// Inserto elementos
		assertEquals(0, m.add(new Persona(1,"Rebeca")));
		assertEquals("[1:Rebeca]\t[4:Clara]\t[2:Pedro]\t[5:Luisa]\t[6:María]\t[12:Julian]", m.toString());
		System.out.println(m.toString());
		
		// Inserto elementos
		assertEquals(0, m.add(new Persona(10,"Amaya")));
		assertEquals("[1:Rebeca]\t[4:Clara]\t[2:Pedro]\t[5:Luisa]\t[6:María]\t[12:Julian]\t[10:Amaya]", m.toString());
		System.out.println(m.toString());
		
		// Inserto y no cabe
		assertEquals(-3, m.add(new Persona(40,"Jesús")));
		assertEquals("[1:Rebeca]\t[4:Clara]\t[2:Pedro]\t[5:Luisa]\t[6:María]\t[12:Julian]\t[10:Amaya]", m.toString());
		System.out.println(m.toString());
		
		// Borra la raíz
		Persona p = m.getTop();
		assertEquals(1, p.getPrioridad());
		assertEquals("Rebeca", p.getNombre());
		// assertEquals(new Persona(1,"Rebeca"),m.getTop());
		assertEquals("[2:Pedro]\t[4:Clara]\t[10:Amaya]\t[5:Luisa]\t[6:María]\t[12:Julian]", m.toString());
		System.out.println(m.toString());
		
		// Cambiar la prioridad de un elemento a una mayor que la que tiene
		assertEquals(0, m.cambiarPrioridad(2, new Persona(15, "Amaya")));
		assertEquals("[2:Pedro]\t[4:Clara]\t[12:Julian]\t[5:Luisa]\t[6:María]\t[15:Amaya]", m.toString());
		System.out.println(m.toString());
		
		// Cambiar la prioridad de un elemento a una menor que la que tiene
		assertEquals(0, m.cambiarPrioridad(1, new Persona(1,"Clara")));
		assertEquals("[1:Clara]\t[2:Pedro]\t[12:Julian]\t[5:Luisa]\t[6:María]\t[15:Amaya]", m.toString());
		System.out.println(m.toString());
	}

}