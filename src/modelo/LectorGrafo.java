
package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LectorGrafo {

    public static Grafo cargarGrafo(File archivo) throws IOException {

        BufferedReader lector = new BufferedReader(new FileReader(archivo));

        Grafo ret = null;

        try {

            String primeraLinea = lector.readLine();

            if (primeraLinea == null) {
                throw new IllegalArgumentException("El archivo esta vacio");
            }

            int cantidadVertices = Integer.parseInt(primeraLinea.trim());

            ret = new Grafo(cantidadVertices);

            String linea;

            while ((linea = lector.readLine()) != null) {

                linea = linea.trim();

                if (!linea.equals("")) {

                    String[] extremos = linea.split("\\s+");

                    if (extremos.length != 2) {
                        throw new IllegalArgumentException(
                                "Cada arista debe tener dos vertices"
                        );
                    }

                    int origen = Integer.parseInt(extremos[0]);
                    int destino = Integer.parseInt(extremos[1]);

                    ret.agregarArista(origen, destino);
                }
            }

        } finally {

            lector.close();
        }

        return ret;
    }
}
