/*
 Integrantes:
    Ramírez Leon Miguel Angel
    Raygadas Baez Rodrigo
Versión de IDE:
    Netbeans IDE 8.2
 */ 
package fes.aragon.elementos;

import fes.aragon.interfaz.Pintar;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 *
 * @author Rodrigo Raygadas
 */
public class Cuadriculado implements Pintar{
    
    private Dimension xy = null; // Guarda las coordenadas
    private Component componente; //dicta lala dimensión del laminado
    
    private int numRenglones=13;
    private int numColumnas=13;

    public Cuadriculado(Component componente) {
        this.xy=componente.getSize();
    }
    
    

    @Override
    public void pintar(Graphics2D g) {
        // Pinta el cuadriculado
        int ancho = (int) xy.getWidth() / numColumnas;
        int largo = (int) xy.getHeight() /numRenglones;
        int xx=ancho;
        int yy=largo;
        for (int i = 0; i < numColumnas; i++) {
            g.drawLine(0, yy, (int) xy.getWidth(), yy);
            yy+=largo;
        }
        for (int i = 0; i < numRenglones; i++) {
            g.drawLine(xx, 0, xx, (int) xy.getHeight());
            xx+=ancho;
        }
        
    }

    @Override
    public void calcular() {
        
    }
    
}
