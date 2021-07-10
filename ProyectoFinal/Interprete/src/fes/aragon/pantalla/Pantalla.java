/*
 Integrantes:
    Ramírez Leon Miguel Angel
    Raygadas Baez Rodrigo
Versión de IDE:
    Netbeans IDE 8.2
 */ 
package fes.aragon.pantalla;

import fes.aragon.interfaz.Pintar;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Rodrigo Raygadas
 */
public class Pantalla extends JPanel{ // Recibe los componentes que va a pintar
    
    private ArrayList<Pintar> componentes = new ArrayList<Pintar>();

    public ArrayList<Pintar> getComponentes() {
        return componentes;
    }
    
    
    
    // Cada que pinte constantemente 
    // Con paintcomponent
    // Hace el refrescado
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        // Debe convertirse primero a grephics"D
        Graphics2D gg=(Graphics2D)g;
        // Hace que cada componente pinte lo suyo 
        //Tomar en cuenta la jerarquia
        for (Pintar componente : componentes) {
            componente.pintar(gg);
            componente.calcular();
        }
    }
    
    
    
    
}
