/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebabotones.vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import pruebabotones.controlador.ControlAplicacion;
import pruebabotones.modelo.Modelo;

/**
 *
 * @author jose ocampo
 */
public class VentanaAplicacion extends JFrame implements Observer {

    public VentanaAplicacion(ControlAplicacion gestorPrincipal, String titulo) throws HeadlessException {
        super(titulo);
        this.gestorPrincipal = gestorPrincipal;
        configurar();
    }

    private void configurar() {
        ajustarComponentes(getContentPane());
        setSize(new Dimension(1000, 680));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void ajustarComponentes(Container c) {
        pestañas = new JTabbedPane();

        panelInicio = new PanelSeccionInicial();
        panelCompra = new JPanel();
        gestorPrincipal.dibujar(panelCompra);

        agregarEventos();

        pestañas.add("SELECCIONA HORARIO", panelInicio);
        pestañas.add("ESCOGE TU LUGAR", panelCompra);
        pestañas.setEnabledAt(1, false);

        c.add(pestañas);
    }

    private void agregarEventos() {
        ((PanelSeccionInicial) panelInicio).getBotonContinuar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCompra.setEnabled(true);
                pestañas.setEnabledAt(1, true);
                pestañas.setSelectedComponent(panelCompra);
                pestañas.setEnabledAt(0, false);
            }
        });

//        ((PanelButacas) panelCompra).getBotonRegresar().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                pestañas.setEnabledAt(0, true);
//                pestañas.setEnabledAt(1, false);
//                pestañas.setSelectedComponent(panelInicio);
//
//            }
//        });
    }

    @Override
    public void update(Observable o, Object arg) {
//        PanelButacas p = (PanelButacas) panelCompra;
        Modelo m = (Modelo) o;

    }

    public void registrar() {
        gestorPrincipal.agregarObservador(this);
    }

    public JButton[][] getButacas() {
        return gestorPrincipal.getButacas();
    }

    public void iniciar() {
        registrar();
        setVisible(true);
    }

    private JTabbedPane pestañas;
    private JPanel panelCompra;
    private JPanel panelInicio;

    private ControlAplicacion gestorPrincipal;

}
