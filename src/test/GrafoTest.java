package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import modelo.Grafo;
import java.util.ArrayList;

class GrafoTest {

    private Grafo grafo;

    @BeforeEach
    void setUp() {
        // Inicializamos un grafo de 5 vértices (del 1 al 5) antes de cada test
        grafo = new Grafo(5);
    }

    @Test
    void testConstructorValido() {
        assertEquals(5, grafo.cantidadVertices());
    }

    @Test
    void testConstructorInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Grafo(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Grafo(-2);
        });
    }

    @Test
    void testAgregarYExisteArista() {
        grafo.agregarArista(1, 2);
        
        // Como es un grafo no dirigido, debe existir en ambos sentidos
        assertTrue(grafo.existeArista(1, 2));
        assertTrue(grafo.existeArista(2, 1));
    }

    @Test
    void testAgregarAristaLazoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            grafo.agregarArista(3, 3);
        });
    }

    @Test
    void testAgregarAristaFueraDeRango() {
        // Vértice origen menor a 1
        assertThrows(IllegalArgumentException.class, () -> {
            grafo.agregarArista(0, 2);
        });
        
        // Vértice destino mayor a la cantidad de vértices (5)
        assertThrows(IllegalArgumentException.class, () -> {
            grafo.agregarArista(1, 6);
        });
    }

    @Test
    void testEliminarArista() {
        grafo.agregarArista(1, 2);
        assertTrue(grafo.existeArista(1, 2));

        grafo.eliminarArista(1, 2);
        assertFalse(grafo.existeArista(1, 2));
        assertFalse(grafo.existeArista(2, 1));
    }

    @Test
    void testObtenerVecinos() {
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);

        ArrayList<Integer> vecinos = grafo.obtenerVecinos(1);
        assertEquals(2, vecinos.size());
        assertTrue(vecinos.contains(2));
        assertTrue(vecinos.contains(3));

        // Un vértice sin vecinos debe retornar una lista vacía
        ArrayList<Integer> vecinosSinConexion = grafo.obtenerVecinos(5);
        assertTrue(vecinosSinConexion.isEmpty());
    }

    @Test
    void testObtenerVecinosVerticeInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            grafo.obtenerVecinos(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            grafo.obtenerVecinos(10);
        });
    }
}