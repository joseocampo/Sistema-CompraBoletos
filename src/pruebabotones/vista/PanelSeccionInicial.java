/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebabotones.vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class PanelSeccionInicial extends JPanel {

    public PanelSeccionInicial() {
        this.setLayout(new BorderLayout());
        
        botonContinuar = new JButton("Elije tu lugar");
        
        this.add(BorderLayout.SOUTH,botonContinuar);
        
    }

    public JButton getBotonContinuar() {
        return botonContinuar;
    }
    
    private JButton botonContinuar;

}
