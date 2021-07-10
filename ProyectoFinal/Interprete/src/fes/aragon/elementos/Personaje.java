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
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.ArrayList;

/**
 *
 * @author Rodrigo Raygadas
 */
public class Personaje {

    private ArrayList<Image> persDer = new ArrayList<Image>();
    private ArrayList<Image> persIzq = new ArrayList<Image>();
    private ArrayList<Image> persArri = new ArrayList<Image>();
    private ArrayList<Image> persAba = new ArrayList<Image>();
    private ArrayList<Image> pintada = new ArrayList<Image>();
    private int x;
    private int y;
    private int contador;
    private boolean fueraRangoX = false;
    private boolean fueraRangoY = false;
    private boolean mueve = true;
    private MediaTracker tracker;
    private boolean derecha = true;
    private boolean izquierda = true;
    private boolean arriba = true;
    private boolean abajo = true;
    private double multiplicador = 1;

    public Personaje(String arri,String abaj,String derech,String izquierd, int numeroFrames, Component componente) {
        tracker = new MediaTracker(componente);
        Toolkit herram = Toolkit.getDefaultToolkit();

        //Ash Derecha
        for (int i = 1; i < 4; i++) {
            Image imagen = herram.getImage(getClass().getResource(
                    "\\fes\\aragon\\recursos" + derech + "_" + i
                    + ".png"));
            persDer.add(imagen);
            tracker.addImage(imagen, i);
        }
        //Ash Izquierda
        for (int i = 5; i < 8; i++) {
            Image imagen = herram.getImage(getClass().getResource(
                    "\\fes\\aragon\\recursos" + izquierd + "_" + i
                    + ".png"));
            persIzq.add(imagen);
            tracker.addImage(imagen, i);
        }

        //Ash Arriba
        for (int i = 8; i < 11; i++) {
            Image imagen = herram.getImage(getClass().getResource(
                    "\\fes\\aragon\\recursos" + arri + "_" + i
                    + ".png"));
            persArri.add(imagen);
            tracker.addImage(imagen, i);
        }

        //Ash Abajo
        for (int i = 11; i < 15; i++) {
            Image imagen = herram.getImage(getClass().getResource(
                    "\\fes\\aragon\\recursos" + abaj + "_" + i
                    + ".png"));
            persAba.add(imagen);
            tracker.addImage(imagen, i);
        }

        try {
            tracker.waitForAll();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }

    public boolean dentroRango() {
        if (x >= 542 || x <= 0) {
            fueraRangoX = true;
        }
        if (y >= 200 || y <= 0) {
            fueraRangoY = true;
        }
        //**********revisar***********
        return mueve;
    }

    public void mueveDerecha() {
        //Se añade al arreglo que se va "a pintar" el pato 0, 1 y 2 pues son de la animacion recta
        ArrayList<Image> aPintar = new ArrayList<Image>();
        aPintar.add(persDer.get(0));
        aPintar.add(persDer.get(1));
        aPintar.add(persDer.get(2));
        x += (int) (8 * multiplicador);
        pintada = aPintar;
    }

    public void mueveIzquierda() {
        //Se añade al arreglo que se va "a pintar" el pato 0, 1 y 2 pues son de la animacion recta
        ArrayList<Image> aPintar = new ArrayList<Image>();
        aPintar.add(persIzq.get(0));
        aPintar.add(persIzq.get(1));
        aPintar.add(persIzq.get(2));
        x -= (int) (8 * multiplicador);
        pintada = aPintar;
    }
    
    public void mueveArriba() {
        //Se añade al arreglo que se va "a pintar" el pato 0, 1 y 2 pues son de la animacion recta
        ArrayList<Image> aPintar = new ArrayList<Image>();
        aPintar.add(persIzq.get(0));
        aPintar.add(persIzq.get(1));
        aPintar.add(persIzq.get(2));
        y -= (int) (3 * multiplicador);
        pintada = aPintar;
    }
    
    public void mueveAbajo() {
        //Se añade al arreglo que se va "a pintar" el pato 0, 1 y 2 pues son de la animacion recta
        ArrayList<Image> aPintar = new ArrayList<Image>();
        aPintar.add(persIzq.get(0));
        aPintar.add(persIzq.get(1));
        aPintar.add(persIzq.get(2));
        y += (int) (3 * multiplicador);
        pintada = aPintar;
    }
    
    
    
    
}
