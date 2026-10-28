package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import modelo.AlgoritmoGoloso;
import modelo.Grafo;
import java.util.ArrayList;

class AlgoritmoGolosoTest {

    private AlgoritmoGoloso algoritmo;

    @BeforeEach
    void setUp() {
        algoritmo = new AlgoritmoGoloso();
    }

//    @Test
//    void testGrafoLineal() {
//        // Grafo de 3 vértices en línea: 1 - 2 - 3
//        Grafo grafo = new Grafo(3);
//        grafo.agregarArista(1, 2);
//        grafo.agregarArista(2, 3);
//
//        ArrayList<Integer> resultado = algoritmo.conjuntoDominante(grafo);
//
//        // El vértice 2 domina a 1, 2 y 3, por lo que el conjunto dominante óptimo y goloso es {2}
//        assertNotNull(resultado);
//        assertTrue(resultado.contains(2));
//        
//        // Verificamos que cubra todos los vértices del grafo
//        assertEquals(1, resultado.size());
//    }

//    @Test
//    void testGrafoEstrella() {
//        // Grafo estrella: el vértice 1 está conectado con 2, 3 y 4
//        Grafo grafo = new Grafo(4);
//        grafo.agregarArista(1, 2);
//        grafo.agregarArista(1, 3);
//        grafo.agregarArista(1, 4);
//
//        ArrayList<Integer> resultado = algoritmo.conjuntoDominante(grafo);
//
//        // El vértice 1 es el centro y domina a todos por sí mismo
//        assertEquals(1, resultado.size());
//        assertEquals(1, resultado.get(0));
//    }
//
//    @Test
//    void testGrafoDesconectado() {
//        // Grafo sin aristas (vértices 1, 2, 3 aislados)
//        Grafo grafo = new Grafo(3);
//
//        ArrayList<Integer> resultado = algoritmo.conjuntoDominante(grafo);
//
//        // Como no hay aristas, cada vértice debe dominarse a sí mismo
//        assertEquals(3, resultado.size());
//        assertTrue(resultado.contains(1));
//        assertTrue(resultado.contains(2));
//        assertTrue(resultado.contains(3));
//    }
//
//    @Test
//    void testGrafoCompleto() {
//        // Grafo completo de 3 vértices (todos conectados entre sí)
//        Grafo grafo = new Grafo(3);
//        grafo.agregarArista(1, 2);
//        grafo.agregarArista(1, 3);
//        grafo.agregarArista(2, 3);
//
//        ArrayList<Integer> resultado = algoritmo.conjuntoDominante(grafo);
//
//        // Con elegir cualquiera de los vértices ya se dominan los 3
//        assertEquals(1, resultado.size());
//    }
}