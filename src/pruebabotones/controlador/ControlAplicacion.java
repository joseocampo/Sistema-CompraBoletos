package pruebabotones.controlador;

import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;
import pruebabotones.modelo.Modelo;

/**
 *
 * @author jose ocampo
 */
public class ControlAplicacion {

    public ControlAplicacion(Modelo modelo) {
        this.modelo = modelo;
    }

    public void agregarObservador(Observer o) {
        modelo.agregarObservador(o);
    }

    public JButton[][] getButacas() {
        return modelo.getButacas();
    }

    public void dibujar(JPanel p) {
        modelo.dibujar(p);
    }

    public void pintarButacasCompradas() {
        modelo.pintarButacasCompradas();
    }

    private Modelo modelo;
}
