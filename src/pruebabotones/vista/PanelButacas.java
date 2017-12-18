package pruebabotones.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import pruebabotones.controlador.ControlAplicacion;

/**
 *
 * @author jose ocampo
 */
public class PanelButacas extends JPanel {

    public PanelButacas() throws HeadlessException {

        configurar();
    }

    private void configurar() {
//        ajustarComponentes();
    }
    
    

   

//    private  JButton[][] butacas = new JButton[MAXIMO_FILAS][MAXIMO_COLUMNAS];
    private JButton[][] butacas;
    private final Color COLOR_PASILLO = new Color(100, 20, 95);
    private final Color COLOR_TITULOS = new Color(0, 0, 255);

    private final Font FUENTE_TITULOS_BORDE = new Font("Eras Bold ITC", Font.ITALIC, 14);
    private final ImageIcon ICONO_BUTACA = new ImageIcon("butaca.png");
    private JButton botonComprar;
    private JButton botonRegresar;
    private JPanel panelButacas;
    private JPanel panelBase;
    private JPanel panelInfo;

}
