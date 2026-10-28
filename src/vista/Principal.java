package vista;

import presenter.Presenter;

public class Principal {

    public static void main(String[] args) {

        Presenter presenter = new Presenter();

        VentanaPrincipal ventana = new VentanaPrincipal(presenter);

        ventana.setVisible(true);
    }
}