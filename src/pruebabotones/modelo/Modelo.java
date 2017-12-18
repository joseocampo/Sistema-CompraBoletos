package pruebabotones.modelo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author jose ocampo
 */
public class Modelo extends Observable {

    public Modelo() {
        observadores = new ArrayList<>();
        butacas = new JButton[MAXIMO_FILAS][MAXIMO_COLUMNAS];
        cola1 = new LinkedList();
        cola2 = new LinkedList();
    }

    public void agregarObservador(Observer o) {
        observadores.add(o);
        notificar();
    }

    public void setButacas(JButton[][] butacas) {
        this.butacas = butacas;
    }

    public JButton[][] getButacas() {
        return butacas;
    }

    public void notificar() {
        for (Observer o : observadores) {
            o.update(this, o);
        }
    }

    public void dibujar(JPanel panelBase) {
//        this.setLayout(new BorderLayout());

//        panelBase = new JPanel();
        panelBase.setLayout(new BorderLayout());
        panelBase.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                        "COMPRA Y RESERVA TUS BOLETOS",
                        TitledBorder.LEFT, TitledBorder.LEFT, FUENTE_TITULOS_BORDE, COLOR_TITULOS),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

//CREAMOS UN PANEL INFO, PARA AGREGAR INFORMACION EXTRA AL USUARIO 
        panelInfo = new JPanel();
        panelInfo.setLayout(new GridBagLayout());
        panelInfo.setBackground(Color.LIGHT_GRAY);
        panelInfo.setForeground(Color.BLUE);
// AGREGAMOS UN BORDER AL PANEL INFO 
        panelInfo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                        "ESCOGE TU LUGAR",
                        TitledBorder.CENTER, TitledBorder.CENTER, FUENTE_TITULOS_BORDE, COLOR_TITULOS),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        GridBagConstraints pantallagbc = new GridBagConstraints();
        pantallagbc.insets = new Insets(4, 8, 4, 8);

// CREAMOS Y AGREGAMOS BOTONES AL PANEL INFO 
        JButton botonVerde, botonAzul, botonRojo, botonAmarillo;
        JLabel labelVerde, labelAzul, labelRojo, labelAmarillo;

        pantallagbc.gridx = 0;
        pantallagbc.gridy = 0;
        panelInfo.add(botonVerde = new JButton(ICONO_BUTACA), pantallagbc);
        botonVerde.setBackground(Color.GREEN);
        botonVerde.setEnabled(false);
        pantallagbc.gridx = 1;
        pantallagbc.gridy = 0;
        panelInfo.add(labelVerde = new JLabel("  Disponible  "), pantallagbc);
        pantallagbc.gridx = 2;
        pantallagbc.gridy = 0;
        panelInfo.add(botonAzul = new JButton(ICONO_BUTACA), pantallagbc);
        botonAzul.setBackground(Color.BLUE);
        botonAzul.setEnabled(false);
        pantallagbc.gridx = 3;
        pantallagbc.gridy = 0;
        panelInfo.add(labelAzul = new JLabel("  Persona con discapacidad  "), pantallagbc);
        pantallagbc.gridx = 4;
        pantallagbc.gridy = 0;
        panelInfo.add(botonAmarillo = new JButton(ICONO_BUTACA), pantallagbc);
        botonAmarillo.setBackground(Color.YELLOW);
        botonAmarillo.setEnabled(false);
        pantallagbc.gridx = 5;
        pantallagbc.gridy = 0;
        panelInfo.add(labelAmarillo = new JLabel("  Su asiento  "), pantallagbc);
        pantallagbc.gridx = 6;
        pantallagbc.gridy = 0;
        panelInfo.add(botonRojo = new JButton(ICONO_BUTACA), pantallagbc);
        botonRojo.setBackground(Color.RED);
        botonRojo.setEnabled(false);
        pantallagbc.gridx = 7;
        pantallagbc.gridy = 0;
        panelInfo.add(labelRojo = new JLabel("  Vendido  "), pantallagbc);

        // CREAMOS UN PANEL PARA AGREGAR BUTACAS  
        panelButacas = new JPanel();
// LE AGREGAMOS UN BORDE AL PAEL BUTACAS 
        panelButacas.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                        "SELECCIONA TUS ASIENTOS", TitledBorder.CENTER,
                        TitledBorder.CENTER, FUENTE_TITULOS_BORDE, COLOR_TITULOS),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        panelButacas.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

//l  Llenamos lado matriz de izquierda  
        for (indice_i = 0; indice_i < MAXIMO_FILAS; indice_i++) {
            for (indice_j = 0; indice_j < ((MAXIMO_COLUMNAS) / 2); indice_j++) {

                butacas[indice_i][indice_j] = new JButton("", ICONO_BUTACA);
                colocarEtiquetaButacasIzquierda(indice_i, indice_j);
                butacas[indice_i][indice_j].setBackground(COLOR_BUTACA_DISPONIBLE);
                agregarEventosButacas(indice_i, indice_j);
                gbc.gridx = indice_j;
                gbc.gridy = indice_i;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                panelButacas.add(butacas[indice_i][indice_j], gbc);
            }
        }

//    llenamos matriz de derecha  
        for (indice_i = 0; indice_i < MAXIMO_FILAS; indice_i++) {
            for (indice_j = 6; indice_j < MAXIMO_COLUMNAS; indice_j++) {

                butacas[indice_i][indice_j] = new JButton("", ICONO_BUTACA);
                colocarEtiquetaButacasDerecha(indice_i, indice_j);
                butacas[indice_i][indice_j].setBackground(COLOR_BUTACA_DISPONIBLE);
                agregarEventosButacas(indice_i, indice_j);
                gbc.gridx = indice_j + 1;  //le sumamos 1 para que no agregue nada a la columna donde esta el pasillo  
                gbc.gridy = indice_i;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                panelButacas.add(butacas[indice_i][indice_j], gbc);
            }
        }

        //agregamos un pasillo entre las butacas  
        gbc.insets = new Insets(0, 8, 0, 8);
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.CENTER;
        for (int i = 0; i < MAXIMO_FILAS; i++) {
            butacas[i][5] = new JButton("");
            butacas[i][5].setBackground(COLOR_PASILLO);
            butacas[i][5].setEnabled(false);
            gbc.gridx = 5;
            gbc.gridy = i;
            panelButacas.add(butacas[i][5], gbc);
        }

        JPanel panelCompra = new JPanel();
        FlowLayout layout = new FlowLayout();
        panelCompra.setLayout(layout);
        botonComprar = new JButton("Haz tu pago >");
        botonRegresar = new JButton("< Regresar");
        botonComprar.setBackground(Color.ORANGE);
        botonComprar.setFont(FUENTE_TITULOS_BORDE);
        botonRegresar.setFont(FUENTE_TITULOS_BORDE);
        botonRegresar.setBackground(Color.LIGHT_GRAY);
        panelCompra.add(botonRegresar);
        panelCompra.add(botonComprar);
        layout.setAlignment(FlowLayout.CENTER);

        //AGREGAMOS LOS PANELES  
        panelBase.add(BorderLayout.NORTH, panelInfo);
        panelBase.add(BorderLayout.CENTER, panelButacas);
        panelBase.add(BorderLayout.SOUTH, panelCompra);

//        
//        this.add(BorderLayout.CENTER, panelBase);
        agregarEventos();

        
        
        
        
    }

    private void agregarEventosButacas(int i, int j) {
        butacas[i][j].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (butacas[i][j].getBackground().equals(COLOR_BUTACA_DISPONIBLE)
                        && butacas[i][j].isEnabled()) {
//                        && ((JButton) e.getSource()).isEnabled()) {
                    System.out.println(".mouseClicked() at [" + i + "," + (j) + " ]"); // mostramos para hacer pruebas 
                    //agregamos los indeces clickeados a cada cola para luego eliminarlo si el usuario asi lo desea 
                    cola1.add(i);
                    cola2.add(j);
                    System.out.println("cola1  i =  " + cola1.toString());
                    System.out.println("cola2 j=  " + cola2.toString());

                }

                cambiarColorButaca(e, i, j);
                resetearButacasSeleccionadas();

            }
        });

    }

    public void resetearButacasSeleccionadas() {

        int x = 3;
        int cont = 0;
        for (indice_i = 0; indice_i < MAXIMO_FILAS; indice_i++) {
            for (indice_j = 0; indice_j < MAXIMO_COLUMNAS; indice_j++) {
                if (butacas[indice_i][indice_j].getBackground().equals(COLOR_BUTACA_SELECCIONADA)) {
                    cont++;  //  contador incrementa cada vez que se encuentra una butaca seleccionada 
                    if (cont > x) {   // si el contador es mayor o igual que tres se empieza a quitar el color amarillo
                        // de la ultima butaca seleccionada.
                        if (!cola1.isEmpty() && !cola2.isEmpty()) {
                            int i = (int) cola1.poll();
                            int j = (int) cola2.poll();
                            //validamos que no se le cambie color a una celda que pertenece al pasillo 
                            butacas[i][j].setBackground(COLOR_BUTACA_DISPONIBLE);   //coloremos en verde
                            // la ultima butaca que el usuario selecciono 

                        }

                    }

                }
            }
        }

    }

    public void agregarEventos() {
        botonComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pintarButacasCompradas();
                verMisBoletos();
//                notificar();   //  linea nueva  
            }
        });

    }

    public JButton getBotonRegresar() {
        return botonRegresar;
    }

    public void pintarButacasCompradas() {
        for (indice_i = 0; indice_i < MAXIMO_FILAS; indice_i++) {
            for (indice_j = 0; indice_j < MAXIMO_COLUMNAS; indice_j++) {
                if (butacas[indice_i][indice_j].getBackground().equals(COLOR_BUTACA_SELECCIONADA)) {
                    butacas[indice_i][indice_j].setBackground(COLOR_BUTACA_VENDIDA);
                    notificar();
                }
            }
        }

        //  LUEGO DE QUE EL USUARIO COMPRE SUS ASIENTOS SELECCIONADOS 
        // LIMPIAMOS AMBAS COLAS PARA QUE NO SE CAMBIEN DE COLOR 
        // LOS ASIENTOS COMPRADOS...
        cola1.clear();
        cola2.clear();

    }

    public String verMisBoletos() {
        StringBuilder r = new StringBuilder();
        r.append("SUS BOLETOS SON : ");
        for (indice_i = 0; indice_i < MAXIMO_FILAS; indice_i++) {
            for (indice_j = 0; indice_j < MAXIMO_COLUMNAS; indice_j++) {
                if (butacas[indice_i][indice_j].getBackground().equals(COLOR_BUTACA_VENDIDA)) {

                    r.append(" ").append(butacas[indice_i][indice_j].getText()).append("    -   ");
//                    notificar(); //   linea nueva 
                }
            }
        }
//        JOptionPane.showMessageDialog(null, r.toString() );
        return r.toString();

    }

    private void cambiarColorButaca(MouseEvent e, int i, int j) {
        if (e.getClickCount() == 1
                && ((JButton) e.getSource()).getBackground().equals(COLOR_BUTACA_DISPONIBLE)) {
            ((JButton) e.getSource()).setBackground(COLOR_BUTACA_SELECCIONADA);
        } else if (e.getClickCount() == 1
                && ((JButton) e.getSource()).getBackground().equals(COLOR_BUTACA_SELECCIONADA)) {
            ((JButton) e.getSource()).setBackground(COLOR_BUTACA_DISPONIBLE);

            if (!cola1.isEmpty() && !cola2.isEmpty()) {
                //esto es para que no siga almacenado indices que ya no estan

                cola1.removeFirstOccurrence(i);  //esto es para que no siga almacenado indices que ya no estan
                cola2.removeFirstOccurrence(j);  //esto es para que no siga almacenado indices que ya no estan
                System.err.println("VALORES DE COLAS LUEGO DE QUIETAR AMARILLAS");
                System.out.println("cola1  i =  " + cola1.toString());   // mostramos para hacer pruebas 
                System.out.println("cola2 j=  " + cola2.toString());
            }
        }
    }

    private void colocarEtiquetaButacasIzquierda(int i, int j) {
        switch (i) {
            case 0: {
                butacas[i][j].setText((j + 1) + "A");
            }
            break;
            case 1: {
                butacas[i][j].setText((j + 1) + "B");
            }
            break;
            case 2: {
                butacas[i][j].setText((j + 1) + "C");
            }
            break;
            case 3: {
                butacas[i][j].setText((j + 1) + "D");
            }
            break;
            case 4: {
                butacas[i][j].setText((j + 1) + "E");
            }
            break;
            case 5: {
                butacas[i][j].setText((j + 1) + "F");
            }
            break;
            case 6: {
                butacas[i][j].setText((j + 1) + "G");
            }
            break;
            case 7: {
                butacas[i][j].setText((j + 1) + "H");
            }
            break;
            case 8: {
                butacas[i][j].setText((j + 1) + "I");
            }
            break;
            case 9: {
                butacas[i][j].setText((j + 1) + "J");
            }
            break;
            case 10: {
                butacas[i][j].setText((j + 1) + "K");
            }
            break;
            case 11: {
                butacas[i][j].setText((j + 1) + "L");
            }
            break;
            case 12: {
                butacas[i][j].setText((j + 1) + "M");
            }
            break;
            case 13: {
                butacas[i][j].setText((j + 1) + "N");
            }
            break;
            case 14: {
                butacas[i][j].setText((j + 1) + "Ñ");
            }
            break;
            default: {
            }
        }
    }

    private void colocarEtiquetaButacasDerecha(int i, int j) {
        switch (i) {
            case 0: {
                butacas[i][j].setText((j) + "A");
            }
            break;
            case 1: {
                butacas[i][j].setText((j) + "B");
            }
            break;
            case 2: {
                butacas[i][j].setText((j) + "C");
            }
            break;
            case 3: {
                butacas[i][j].setText((j) + "D");
            }
            break;
            case 4: {
                butacas[i][j].setText((j + 1) + "E");
            }
            break;
            case 5: {
                butacas[i][j].setText((j) + "F");
            }
            break;
            case 6: {
                butacas[i][j].setText((j + 1) + "G");
            }
            break;
            case 7: {
                butacas[i][j].setText((j) + "H");
            }
            break;
            case 8: {
                butacas[i][j].setText((j) + "I");
            }
            break;
            case 9: {
                butacas[i][j].setText((j) + "J");
            }
            break;
            case 10: {
                butacas[i][j].setText((j) + "K");
            }
            break;
            case 11: {
                butacas[i][j].setText((j) + "L");
            }
            break;
            case 12: {
                butacas[i][j].setText((j) + "M");
            }
            break;
            case 13: {
                butacas[i][j].setText((j) + "N");
            }
            break;
            case 14: {
                butacas[i][j].setText((j) + "Ñ");
            }
            break;
            default: {
            }
        }
    }

    private ArrayList<Observer> observadores;
    private JButton[][] butacas;

    private int indice_i = 0;
    private int indice_j = 0;
    private final Color COLOR_BUTACA_DISPONIBLE = new Color(87, 233, 45);
    private final Color COLOR_BUTACA_VENDIDA = new Color(248, 000, 000);
    private final Color COLOR_BUTACA_SELECCIONADA = new Color(237, 255, 033);
    private final Color COLOR_PASILLO = new Color(100, 20, 95);
    private final Color COLOR_TITULOS = new Color(0, 0, 255);

    private final Font FUENTE_TITULOS_BORDE = new Font("Eras Bold ITC", Font.ITALIC, 14);
    private final ImageIcon ICONO_BUTACA = new ImageIcon("butaca.png");
    private LinkedList cola1;   // se guardan los  valores que va tomando el indice_i   
    private LinkedList cola2; // se guardan los  valores que va tomando el indice_j  
    private static final int MAXIMO_FILAS = 15;
    private static final int MAXIMO_COLUMNAS = 11;

    private JButton botonComprar;
    private JButton botonRegresar;
    private JPanel panelButacas;
//    private JPanel panelBase;
    private JPanel panelInfo;

}
