
package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import modelo.Grafo;
import modelo.Solucion;
import presenter.Presenter;

public class VentanaPrincipal extends JFrame {

    private Presenter _presenter;

    private JTextField _txtCantidadVertices;
    private JTextField _txtOrigen;
    private JTextField _txtDestino;

    private JButton _btnCrearGrafo;
    private JButton _btnAgregarArista;
    private JButton _btnEliminarArista;
    private JButton _btnCalcular;
    private JButton _btnCargarArchivo;

    private JLabel _lblMensaje;
    private JLabel _lblResultado;

    private PanelGrafo _panelGrafo;

    public VentanaPrincipal(Presenter presenter) {

        _presenter = presenter;

        setTitle("Conjunto Dominante");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        inicializarComponentes();
        configurarEventos();
        actualizarEstado();
    }

    private void inicializarComponentes() {

        JPanel panelControles = new JPanel();

        panelControles.setLayout(new GridLayout(0, 1, 5, 5));

        panelControles.setBorder(
                BorderFactory.createTitledBorder("Carga del grafo")
        );

        _txtCantidadVertices = new JTextField(8);
        _txtOrigen = new JTextField(8);
        _txtDestino = new JTextField(8);

        _btnCrearGrafo = new JButton("Crear grafo");
        _btnAgregarArista = new JButton("Agregar arista");
        _btnEliminarArista = new JButton("Eliminar arista");
        _btnCalcular = new JButton("Calcular conjunto");
        _btnCargarArchivo = new JButton("Cargar archivo");

        panelControles.add(new JLabel("Cantidad de vertices:"));
        panelControles.add(_txtCantidadVertices);
        panelControles.add(_btnCrearGrafo);

        panelControles.add(new JLabel("Origen de la arista:"));
        panelControles.add(_txtOrigen);

        panelControles.add(new JLabel("Destino de la arista:"));
        panelControles.add(_txtDestino);

        panelControles.add(_btnAgregarArista);
        panelControles.add(_btnEliminarArista);

        panelControles.add(_btnCargarArchivo);
        panelControles.add(_btnCalcular);

        _lblMensaje = new JLabel("Cree un grafo para comenzar");

        _lblResultado = new JLabel(
                "Conjunto dominante: -",
                SwingConstants.CENTER
        );

        JPanel panelInferior = new JPanel(new GridLayout(2, 1));

        panelInferior.setBorder(
                BorderFactory.createTitledBorder("Resultado")
        );

        panelInferior.add(_lblResultado);
        panelInferior.add(_lblMensaje);

        _panelGrafo = new PanelGrafo();

        add(panelControles, BorderLayout.WEST);
        add(_panelGrafo, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void configurarEventos() {

        _btnCrearGrafo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                crearGrafo();
            }
        });

        _btnAgregarArista.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                agregarArista();
            }
        });

        _btnEliminarArista.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                eliminarArista();
            }
        });

        _btnCalcular.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                calcularConjuntoDominante();
            }
        });

        _btnCargarArchivo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                cargarArchivo();
            }
        });
    }

    private void crearGrafo() {

        try {

            int cantidadVertices =
                    Integer.parseInt(_txtCantidadVertices.getText().trim());

            _presenter.crearGrafo(cantidadVertices);

            actualizarVistaGrafo();

            _lblMensaje.setText("Grafo creado correctamente");

            _lblResultado.setText("Conjunto dominante: -");

        } catch (NumberFormatException ex) {

            mostrarError("Ingrese una cantidad de vertices valida");

        } catch (IllegalArgumentException ex) {

            mostrarError(ex.getMessage());
        }
    }

    private void agregarArista() {

        try {

            int origen = Integer.parseInt(_txtOrigen.getText().trim());
            int destino = Integer.parseInt(_txtDestino.getText().trim());

            _presenter.agregarArista(origen, destino);

            actualizarVistaGrafo();

            _lblMensaje.setText("Arista agregada correctamente");

            _lblResultado.setText("Conjunto dominante: -");

        } catch (NumberFormatException ex) {

            mostrarError("Ingrese vertices validos");

        } catch (IllegalStateException ex) {

            mostrarError(ex.getMessage());

        } catch (IllegalArgumentException ex) {

            mostrarError(ex.getMessage());
        }
    }

    private void eliminarArista() {

        try {

            int origen = Integer.parseInt(_txtOrigen.getText().trim());
            int destino = Integer.parseInt(_txtDestino.getText().trim());

            _presenter.eliminarArista(origen, destino);

            actualizarVistaGrafo();

            _lblMensaje.setText("Arista eliminada correctamente");

            _lblResultado.setText("Conjunto dominante: -");

        } catch (NumberFormatException ex) {

            mostrarError("Ingrese vertices validos");

        } catch (IllegalStateException ex) {

            mostrarError(ex.getMessage());

        } catch (IllegalArgumentException ex) {

            mostrarError(ex.getMessage());
        }
    }

    private void calcularConjuntoDominante() {

        try {

            Solucion solucion =
                    _presenter.calcularConjuntoDominante();

            actualizarVistaGrafo();

            _lblResultado.setText(
                    "Conjunto dominante: " + solucion
                    + " | Tamano: " + solucion.tamano()
            );

            if (_presenter.esDominante(solucion)) {

                _lblMensaje.setText("La solucion es dominante");

            } else {

                _lblMensaje.setText("La solucion no es dominante");
            }

        } catch (IllegalStateException ex) {

            mostrarError(ex.getMessage());
        }
    }

    private void cargarArchivo() {

        JFileChooser selector = new JFileChooser();

        int resultado = selector.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {

            File archivo = selector.getSelectedFile();

            try {

                _presenter.cargarGrafo(archivo);

                actualizarVistaGrafo();

                _lblMensaje.setText("Grafo cargado correctamente");

                _lblResultado.setText("Conjunto dominante: -");

            } catch (Exception ex) {

                mostrarError("No se pudo cargar el archivo: "
                        + ex.getMessage());
            }
        }
    }

    private void actualizarVistaGrafo() {

        Grafo grafo = _presenter.obtenerGrafo();

        Solucion solucion = _presenter.obtenerSolucion();

        _panelGrafo.actualizarGrafo(grafo, solucion);

        actualizarEstado();
    }

    private void actualizarEstado() {

        boolean hayGrafo = _presenter.hayGrafo();

        _btnAgregarArista.setEnabled(hayGrafo);
        _btnEliminarArista.setEnabled(hayGrafo);
        _btnCalcular.setEnabled(hayGrafo);
    }

    private void mostrarError(String mensaje) {

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
