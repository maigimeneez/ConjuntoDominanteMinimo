package vista;

import modelo.AlgoritmoGoloso;
import modelo.Grafo;
import modelo.Solucion;

public class Principal {

    public static void main(String[] args) {

        Grafo g = new Grafo(6);

        g.agregarArista(6, 4);
        g.agregarArista(4, 5);
        g.agregarArista(5, 1);
        g.agregarArista(5, 2);
        g.agregarArista(1, 2);
        g.agregarArista(2, 3);
        g.agregarArista(3, 4);

        AlgoritmoGoloso algoritmo = new AlgoritmoGoloso();

        Solucion ret = algoritmo.conjuntoDominante(g);

        System.out.println("Conjunto dominante: " + ret);
        System.out.println("Es dominante: " + g.esDominante(ret));
    }
}