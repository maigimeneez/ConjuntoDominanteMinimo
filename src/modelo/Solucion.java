
package modelo;

import java.util.ArrayList;

public class Solucion {

    private ArrayList<Integer> _vertices;

    public Solucion() {
        _vertices = new ArrayList<Integer>();
    }

    public void agregarVertice(int vertice) {
        if (!contiene(vertice))
            _vertices.add(vertice);
    }

    public boolean contiene(int vertice) {
        boolean ret = false;

        ret = _vertices.contains(vertice);

        return ret;
    }

    public int tamano() {
        return _vertices.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        for (int i = 0; i < _vertices.size(); i++) {
            sb.append(_vertices.get(i));

            if (i < _vertices.size() - 1)
                sb.append(", ");
        }

        return sb.append("}").toString();
    }
}
