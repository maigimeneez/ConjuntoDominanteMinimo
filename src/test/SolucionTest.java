package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Solucion;

class SolucionTest {

    private Solucion solucion;

    @BeforeEach
    void setUp() {
        solucion = new Solucion();
    }

    @Test
    void testSolucionNuevaEstaVacia() {
        assertEquals(0, solucion.tamano());
        assertFalse(solucion.contiene(1));
    }

    @Test
    void testAgregarVertice() {
        solucion.agregarVertice(3);

        assertEquals(1, solucion.tamano());
        assertTrue(solucion.contiene(3));
    }

    @Test
    void testAgregarVariosVertices() {
        solucion.agregarVertice(2);
        solucion.agregarVertice(4);
        solucion.agregarVertice(5);

        assertEquals(3, solucion.tamano());
        assertTrue(solucion.contiene(2));
        assertTrue(solucion.contiene(4));
        assertTrue(solucion.contiene(5));
    }

    @Test
    void testNoAgregaDuplicados() {
        solucion.agregarVertice(2);
        solucion.agregarVertice(2);
        solucion.agregarVertice(2);

        assertEquals(1, solucion.tamano());
    }

    @Test
    void testContieneVerticeNoAgregado() {
        solucion.agregarVertice(1);

        assertFalse(solucion.contiene(2));
    }

    @Test
    void testToStringVacia() {
        assertEquals("{}", solucion.toString());
    }

    @Test
    void testToStringUnElemento() {
        solucion.agregarVertice(7);

        assertEquals("{7}", solucion.toString());
    }

    @Test
    void testToStringVariosElementos() {
        solucion.agregarVertice(2);
        solucion.agregarVertice(4);

        assertEquals("{2, 4}", solucion.toString());
    }
}