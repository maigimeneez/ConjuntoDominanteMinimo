package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.AlgoritmoGoloso;
import modelo.Grafo;
import modelo.Solucion;

class AlgoritmoGolosoTest {

    private AlgoritmoGoloso algoritmo;

    @BeforeEach
    void setUp() {
        algoritmo = new AlgoritmoGoloso();
    }

    private Grafo grafoCon(int n, int[][] aristas) {
        Grafo g = new Grafo(n);
        for (int[] a : aristas)
            g.agregarArista(a[0], a[1]);
        return g;
    }

    @Test
    void testUnSoloVertice() {
        Grafo g = new Grafo(1);

        Solucion s = algoritmo.conjuntoDominante(g);

        assertEquals(1, s.tamano());
        assertTrue(s.contiene(1));
    }

    @Test
    void testGrafoSinAristas() {
        Grafo g = new Grafo(3);

        Solucion s = algoritmo.conjuntoDominante(g);

        assertEquals(3, s.tamano());
        assertTrue(s.contiene(1));
        assertTrue(s.contiene(2));
        assertTrue(s.contiene(3));
        assertTrue(g.esDominante(s));
    }

    @Test
    void testGrafoLineal() {
        Grafo g = grafoCon(3, new int[][] { {1, 2}, {2, 3} });

        Solucion s = algoritmo.conjuntoDominante(g);

        assertEquals(1, s.tamano());
        assertTrue(s.contiene(2));
        assertTrue(g.esDominante(s));
    }

    @Test
    void testGrafoEstrella() {
        Grafo g = grafoCon(4, new int[][] { {1, 2}, {1, 3}, {1, 4} });

        Solucion s = algoritmo.conjuntoDominante(g);

        assertEquals(1, s.tamano());
        assertTrue(s.contiene(1));
        assertTrue(g.esDominante(s));
    }

    @Test
    void testGrafoCompleto() {
        Grafo g = grafoCon(4, new int[][] {
            {1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4}, {3, 4}
        });

        Solucion s = algoritmo.conjuntoDominante(g);

        assertEquals(1, s.tamano());
        assertTrue(g.esDominante(s));
    }

    @Test
    void testCaminoDeCuatroVertices() {
        Grafo g = grafoCon(4, new int[][] { {1, 2}, {2, 3}, {3, 4} });

        Solucion s = algoritmo.conjuntoDominante(g);

        assertEquals(2, s.tamano());
        assertTrue(g.esDominante(s));
    }

    @Test
    void testGrafoDesconectado() {
        Grafo g = grafoCon(4, new int[][] { {1, 2}, {3, 4} });

        Solucion s = algoritmo.conjuntoDominante(g);

        assertEquals(2, s.tamano());
        assertTrue(g.esDominante(s));
    }

    @Test
    void testGrafoDeLaFiguraDelEnunciado() {
        Grafo g = grafoCon(6, new int[][] {
            {6, 4}, {4, 5}, {4, 3}, {5, 1}, {5, 2}, {1, 2}, {2, 3}
        });

        Solucion s = algoritmo.conjuntoDominante(g);

        assertEquals(2, s.tamano());
        assertTrue(g.esDominante(s));
    }

    @Test
    void testResultadoDeterminista() {
        Grafo g = grafoCon(6, new int[][] {
            {6, 4}, {4, 5}, {4, 3}, {5, 1}, {5, 2}, {1, 2}, {2, 3}
        });

        Solucion s1 = algoritmo.conjuntoDominante(g);
        Solucion s2 = algoritmo.conjuntoDominante(g);

        assertEquals(s1.toString(), s2.toString());
    }

    @Test
    void testNoModificaElGrafo() {
        Grafo g = grafoCon(3, new int[][] { {1, 2}, {2, 3} });

        algoritmo.conjuntoDominante(g);

        assertTrue(g.existeArista(1, 2));
        assertTrue(g.existeArista(2, 3));
        assertFalse(g.existeArista(1, 3));
    }

    @Test
    void testSiempreDevuelveConjuntoDominante() {
        Random random = new Random(42);

        for (int intento = 0; intento < 200; intento++) {

            int n = 2 + random.nextInt(25);
            Grafo g = new Grafo(n);

            for (int i = 1; i <= n; i++)
                for (int j = i + 1; j <= n; j++)
                    if (random.nextDouble() < 0.3)
                        g.agregarArista(i, j);

            Solucion s = algoritmo.conjuntoDominante(g);

            assertTrue(g.esDominante(s),
                    "No es dominante: " + s + " con n=" + n);
            assertTrue(s.tamano() <= n);
        }
    }
}