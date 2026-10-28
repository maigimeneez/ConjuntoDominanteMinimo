
package modelo;

import java.util.ArrayList;

public class Grafo {

    private ArrayList<ArrayList<Integer>> _adyacentes;

    public Grafo(int n) {
        if (n <= 0)
            throw new IllegalArgumentException(
                "La cantidad de vertices debe ser positiva"
            );

        _adyacentes = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < n; i++)
            _adyacentes.add(new ArrayList<Integer>());
    }

    public int cantidadVertices() {
        return _adyacentes.size();
    }

    public void agregarArista(int origen, int destino) {
        validarArista(origen, destino);

        if (!existeArista(origen, destino)) {
            _adyacentes.get(origen - 1).add(destino);
            _adyacentes.get(destino - 1).add(origen);
        }
    }

    public void eliminarArista(int origen, int destino) {
        validarArista(origen, destino);

        _adyacentes.get(origen - 1).remove(Integer.valueOf(destino));
        _adyacentes.get(destino - 1).remove(Integer.valueOf(origen));
    }

    public boolean existeArista(int origen, int destino) {
        validarVertice(origen);
        validarVertice(destino);

        boolean ret = false;

        ret = _adyacentes.get(origen - 1).contains(destino);

        return ret;
    }

    public ArrayList<Integer> obtenerVecinos(int vertice) {
        validarVertice(vertice);

        ArrayList<Integer> ret = new ArrayList<Integer>();

        ret.addAll(_adyacentes.get(vertice - 1));

        return ret;
    }

    private void validarArista(int origen, int destino) {
        validarVertice(origen);
        validarVertice(destino);

        if (origen == destino)
            throw new IllegalArgumentException(
                "No se permiten lazos"
            );
    }

    private void validarVertice(int vertice) {
        if (vertice <= 0 || vertice > cantidadVertices())
            throw new IllegalArgumentException(
                "Vertice fuera de rango: " + vertice
            );
    }
    public boolean esDominante(Solucion sol) {
        boolean ret = true;

        for (int v = 1; v <= cantidadVertices() && ret; v++) {

            if (!sol.contiene(v)) {

                ArrayList<Integer> vecinos = obtenerVecinos(v);

                boolean dominado = false;

                for (int i = 0; i < vecinos.size() && !dominado; i++) {
                    int vecino = vecinos.get(i);

                    if (sol.contiene(vecino))
                        dominado = true;
                }

                if (!dominado)
                    ret = false;
            }
        }

        return ret;
    }

    
}
