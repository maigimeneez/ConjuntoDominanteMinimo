package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import modelo.Grafo;
import modelo.LectorGrafo;

class LectorGrafoTest {

    @TempDir
    Path carpeta;

    private File archivoCon(String contenido) throws IOException {
        Path ruta = carpeta.resolve("grafo.txt");
        Files.writeString(ruta, contenido);
        return ruta.toFile();
    }

    @Test
    void testArchivoValido() throws IOException {
        File archivo = archivoCon("4\n1 2\n2 3\n3 4\n");

        Grafo g = LectorGrafo.cargarGrafo(archivo);

        assertEquals(4, g.cantidadVertices());
        assertTrue(g.existeArista(1, 2));
        assertTrue(g.existeArista(2, 3));
        assertTrue(g.existeArista(3, 4));
        assertFalse(g.existeArista(1, 4));
    }

    @Test
    void testArchivoSoloConCantidadDeVertices() throws IOException {
        File archivo = archivoCon("3\n");

        Grafo g = LectorGrafo.cargarGrafo(archivo);

        assertEquals(3, g.cantidadVertices());
        assertTrue(g.obtenerVecinos(1).isEmpty());
    }

    @Test
    void testIgnoraLineasEnBlanco() throws IOException {
        File archivo = archivoCon("3\n\n1 2\n\n\n2 3\n");

        Grafo g = LectorGrafo.cargarGrafo(archivo);

        assertTrue(g.existeArista(1, 2));
        assertTrue(g.existeArista(2, 3));
    }

    @Test
    void testAceptaEspaciosMultiplesYBordes() throws IOException {
        File archivo = archivoCon("  3  \n  1     2  \n2\t3\n");

        Grafo g = LectorGrafo.cargarGrafo(archivo);

        assertEquals(3, g.cantidadVertices());
        assertTrue(g.existeArista(1, 2));
        assertTrue(g.existeArista(2, 3));
    }

    @Test
    void testAristaRepetidaNoSeDuplica() throws IOException {
        File archivo = archivoCon("2\n1 2\n2 1\n1 2\n");

        Grafo g = LectorGrafo.cargarGrafo(archivo);

        assertEquals(1, g.obtenerVecinos(1).size());
        assertEquals(1, g.obtenerVecinos(2).size());
    }

    @Test
    void testArchivoVacio() throws IOException {
        File archivo = archivoCon("");

        assertThrows(IllegalArgumentException.class,
                () -> LectorGrafo.cargarGrafo(archivo));
    }

    @Test
    void testCantidadDeVerticesNoNumerica() throws IOException {
        File archivo = archivoCon("abc\n1 2\n");

        assertThrows(NumberFormatException.class,
                () -> LectorGrafo.cargarGrafo(archivo));
    }

    @Test
    void testCantidadDeVerticesCero() throws IOException {
        File archivo = archivoCon("0\n");

        assertThrows(IllegalArgumentException.class,
                () -> LectorGrafo.cargarGrafo(archivo));
    }

    @Test
    void testAristaConUnSoloExtremo() throws IOException {
        File archivo = archivoCon("3\n1\n");

        assertThrows(IllegalArgumentException.class,
                () -> LectorGrafo.cargarGrafo(archivo));
    }

    @Test
    void testAristaConTresExtremos() throws IOException {
        File archivo = archivoCon("3\n1 2 3\n");

        assertThrows(IllegalArgumentException.class,
                () -> LectorGrafo.cargarGrafo(archivo));
    }

    @Test
    void testAristaNoNumerica() throws IOException {
        File archivo = archivoCon("3\n1 x\n");

        assertThrows(NumberFormatException.class,
                () -> LectorGrafo.cargarGrafo(archivo));
    }

    @Test
    void testAristaConVerticeFueraDeRango() throws IOException {
        File archivo = archivoCon("3\n1 5\n");

        assertThrows(IllegalArgumentException.class,
                () -> LectorGrafo.cargarGrafo(archivo));
    }

    @Test
    void testAristaConLazo() throws IOException {
        File archivo = archivoCon("3\n2 2\n");

        assertThrows(IllegalArgumentException.class,
                () -> LectorGrafo.cargarGrafo(archivo));
    }

    @Test
    void testArchivoInexistente() {
        File inexistente = carpeta.resolve("no_existe.txt").toFile();

        assertThrows(IOException.class,
                () -> LectorGrafo.cargarGrafo(inexistente));
    }
}