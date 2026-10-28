
package presenter;

import modelo.Grafo;
import modelo.AlgoritmoGoloso;
import modelo.Solucion;
import modelo.LectorGrafo;

import java.io.File;
import java.io.IOException;

public class Presenter {

    private Grafo _grafo;
    private AlgoritmoGoloso _algoritmo;
    private Solucion _solucion;

    public Presenter() {

        _grafo = null;
        _algoritmo = new AlgoritmoGoloso();
        _solucion = null;
    }

    public void crearGrafo(int cantidadVertices) {

        _grafo = new Grafo(cantidadVertices);
        _solucion = null;
    }

    public void agregarArista(int origen, int destino) {

        validarGrafoCreado();

        _grafo.agregarArista(origen, destino);
        _solucion = null;
    }

    public void eliminarArista(int origen, int destino) {

        validarGrafoCreado();

        _grafo.eliminarArista(origen, destino);
        _solucion = null;
    }

    public Solucion calcularConjuntoDominante() {

        validarGrafoCreado();

        _solucion = _algoritmo.conjuntoDominante(_grafo);

        return _solucion;
    }

    public void cargarGrafo(File archivo) throws IOException {

        _grafo = LectorGrafo.cargarGrafo(archivo);
        _solucion = null;
    }

    public Grafo obtenerGrafo() {

        return _grafo;
    }

    public Solucion obtenerSolucion() {

        return _solucion;
    }

    public boolean hayGrafo() {

        return _grafo != null;
    }

    public boolean haySolucion() {

        return _solucion != null;
    }

    public boolean esDominante(Solucion solucion) {

        validarGrafoCreado();

        return _grafo.esDominante(solucion);
    }

    private void validarGrafoCreado() {

        if (_grafo == null) {
            throw new IllegalStateException("Primero debe crear un grafo");
        }
    }
}
