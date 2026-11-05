package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import modelo.Grafo;
import modelo.Solucion;
import java.util.ArrayList;

class GrafoTest {

    private Grafo grafo;

    @BeforeEach
    void setUp() {
        grafo = new Grafo(5);
    }

    private Solucion solucionCon(int... vertices) {
        Solucion s = new Solucion();
        for (int v : vertices)
            s.agregarVertice(v);
        return s;
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

        assertTrue(grafo.existeArista(1, 2));
        assertTrue(grafo.existeArista(2, 1));
    }

    @Test
    void testNoExisteAristaNoAgregada() {
        grafo.agregarArista(1, 2);

        assertFalse(grafo.existeArista(1, 3));
    }

    @Test
    void testAgregarAristaRepetidaNoDuplica() {
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(2, 1);

        assertEquals(1, grafo.obtenerVecinos(1).size());
        assertEquals(1, grafo.obtenerVecinos(2).size());
    }

    @Test
    void testAgregarAristaLazoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            grafo.agregarArista(3, 3);
        });
    }

    @Test
    void testAgregarAristaFueraDeRango() {
        assertThrows(IllegalArgumentException.class, () -> {
            grafo.agregarArista(0, 2);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            grafo.agregarArista(1, 6);
        });
    }

    @Test
    void testExisteAristaFueraDeRango() {
        assertThrows(IllegalArgumentException.class, () -> {
            grafo.existeArista(0, 1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            grafo.existeArista(1, 6);
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
    void testEliminarAristaInexistenteNoFalla() {
        grafo.eliminarArista(1, 2);

        assertFalse(grafo.existeArista(1, 2));
    }

    @Test
    void testEliminarAristaFueraDeRango() {
        assertThrows(IllegalArgumentException.class, () -> {
            grafo.eliminarArista(1, 9);
        });
    }

    @Test
    void testEliminarAristaLazo() {
        assertThrows(IllegalArgumentException.class, () -> {
            grafo.eliminarArista(2, 2);
        });
    }


    @Test
    void testObtenerVecinos() {
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);

        ArrayList<Integer> vecinos = grafo.obtenerVecinos(1);
        assertEquals(2, vecinos.size());
        assertTrue(vecinos.contains(2));
        assertTrue(vecinos.contains(3));

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

    @Test
    void testObtenerVecinosDevuelveCopia() {
        grafo.agregarArista(1, 2);

        ArrayList<Integer> vecinos = grafo.obtenerVecinos(1);
        vecinos.clear();

        assertTrue(grafo.existeArista(1, 2));
        assertEquals(1, grafo.obtenerVecinos(1).size());
    }


    @Test
    void testEsDominanteConTodosLosVertices() {
        Solucion s = solucionCon(1, 2, 3, 4, 5);

        assertTrue(grafo.esDominante(s));
    }

    @Test
    void testSolucionVaciaNoEsDominante() {
        grafo.agregarArista(1, 2);

        assertFalse(grafo.esDominante(new Solucion()));
    }

    @Test
    void testEsDominanteCentroDeEstrella() {
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(1, 4);
        grafo.agregarArista(1, 5);

        assertTrue(grafo.esDominante(solucionCon(1)));
    }

    @Test
    void testExtremoDeEstrellaNoEsDominante() {
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(1, 4);
        grafo.agregarArista(1, 5);

        assertFalse(grafo.esDominante(solucionCon(2)));
    }

    @Test
    void testVerticeAisladoDebeEstarEnLaSolucion() {
        grafo.agregarArista(1, 2);
        grafo.agregarArista(2, 3);

        assertFalse(grafo.esDominante(solucionCon(2)));
        assertFalse(grafo.esDominante(solucionCon(2, 4)));
        assertTrue(grafo.esDominante(solucionCon(2, 4, 5)));
    }

    @Test
    void testEsDominanteFiguraDelEnunciado() {
        Grafo g = new Grafo(6);
        g.agregarArista(6, 4);
        g.agregarArista(4, 5);
        g.agregarArista(4, 3);
        g.agregarArista(5, 1);
        g.agregarArista(5, 2);
        g.agregarArista(1, 2);
        g.agregarArista(2, 3);

        assertTrue(g.esDominante(solucionCon(2, 4)));
        assertFalse(g.esDominante(solucionCon(2)));
        assertFalse(g.esDominante(solucionCon(4)));
    }

    @Test
    void testEsDominanteDependeDeLasAristas() {
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(1, 4);
        grafo.agregarArista(1, 5);
        Solucion s = solucionCon(1);
        assertTrue(grafo.esDominante(s));

        grafo.eliminarArista(1, 5);
        assertFalse(grafo.esDominante(s));
    }
}