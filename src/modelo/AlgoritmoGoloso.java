package modelo;

import java.util.ArrayList;

public class AlgoritmoGoloso {

    public Solucion conjuntoDominante(Grafo g) {

        Solucion ret = new Solucion();
        ArrayList<Integer> dominados = new ArrayList<Integer>();

        while (dominados.size() < g.cantidadVertices()) {

            int mejorVertice = 1;
            int maxNuevos = -1;

            for (int v = 1; v <= g.cantidadVertices(); v++) {

                int nuevos = cantidadNuevos(g, v, dominados);

                if (nuevos > maxNuevos) {
                    maxNuevos = nuevos;
                    mejorVertice = v;
                }
            }

            ret.agregarVertice(mejorVertice);
            dominar(g, mejorVertice, dominados);
        }

        return ret;
    }

    private int cantidadNuevos(Grafo g, int vertice, ArrayList<Integer> dominados) {

        int ret = 0;

        if (!dominados.contains(vertice)) {
            ret++;
        }

        ArrayList<Integer> vecinos = g.obtenerVecinos(vertice);

        for (int i = 0; i < vecinos.size(); i++) {

            int vecino = vecinos.get(i);

            if (!dominados.contains(vecino)) {
                ret++;
            }
        }

        return ret;
    }

    private void dominar(Grafo g, int vertice, ArrayList<Integer> dominados) {

        if (!dominados.contains(vertice)) {
            dominados.add(vertice);
        }

        ArrayList<Integer> vecinos = g.obtenerVecinos(vertice);

        for (int i = 0; i < vecinos.size(); i++) {

            int vecino = vecinos.get(i);

            if (!dominados.contains(vecino)) {
                dominados.add(vecino);
            }
        }
    }
}