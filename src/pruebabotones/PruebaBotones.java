/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebabotones;

import pruebabotones.controlador.ControlAplicacion;
import pruebabotones.modelo.Modelo;
import pruebabotones.vista.VentanaAplicacion;

/**
 *
 * @author jose ocampo
 */
public class PruebaBotones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Modelo modelo = new Modelo();
        ControlAplicacion control = new ControlAplicacion(modelo);

        VentanaAplicacion v1 = new VentanaAplicacion(control,"V1");
//        VentanaAplicacion v2 = new VentanaAplicacion(control,"V2");

       
        v1.iniciar();
//        v2.iniciar();
    
        
        


    }

}
