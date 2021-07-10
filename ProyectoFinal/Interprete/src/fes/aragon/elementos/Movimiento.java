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
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo Raygadas
 */
public class Movimiento implements Pintar {

    ArrayList<Pintar> componente = new ArrayList<Pintar>();
    private Component comp;
    // Para traer las imagenes
    private ArrayList<Image> imagen = new ArrayList<Image>();
    private Dimension xy = null;
    private MediaTracker tracker;
    private int x = 0;
    private int y = 0;
    int orientacion = 0;
    int ancho = 60;
    int alto = 30;
    private ArrayList<String> datos = new ArrayList<String>(); //Objeto del archivo
    private boolean fueraRangoX = false;
    private boolean fueraRangoY = false;
    private int distancia = 30;

    // Movimientos 
    private int coordenadas;
    private boolean arriba;
    private boolean abajo;
    private boolean derecha;
    private boolean izquierda;
    private boolean mover = true;

    private ArrayList<Image> persDer = new ArrayList<Image>();
    private ArrayList<Image> persIzq = new ArrayList<Image>();
    private ArrayList<Image> persArri = new ArrayList<Image>();
    private ArrayList<Image> persAba = new ArrayList<Image>();

    private Personaje personaje;
    private double multiplicador = 1;

    private int numUno = 0;
    private int numDos = 1;
    private int nu;
    private String n;

    //Para traer las imagenes con la orientación acorde al lado que se indique
    public Movimiento(Component componente, ArrayList<String> datos) {
        this.comp = componente;
        this.datos = datos;
        tracker = new MediaTracker(componente);
        xy = componente.getSize();
        Toolkit herramE = Toolkit.getDefaultToolkit();

        // Imagen Derecha
        for (int i = 1; i < 4; i++) {
            Image imagen = herramE.getImage(getClass().getResource(
                    "/fes/aragon/recursos/ashDer_" + i
                    + ".png"));
            persDer.add(imagen);
            tracker.addImage(imagen, i);
        }
        // Imagen Izquierda
        for (int i = 1, j = 4; i < 4; i++, j++) {
            Image imagen = herramE.getImage(getClass().getResource(
                    "/fes/aragon/recursos/ashIzq_" + i
                    + ".png"));
            persIzq.add(imagen);
            tracker.addImage(imagen, j);
        }
        //Imagen Arriba
        for (int i = 1, j = 8; i < 4; i++, j++) {
            Image imagen = herramE.getImage(getClass().getResource(
                    "/fes/aragon/recursos/ashArri_" + i
                    + ".png"));
            persArri.add(imagen);
            tracker.addImage(imagen, j);
        }
        //Imagen Abajo
        for (int i = 1, j = 12; i < 4; i++, j++) {
            Image imagen = herramE.getImage(getClass().getResource(
                    "/fes/aragon/recursos/ashAba_" + i
                    + ".png"));
            persAba.add(imagen);
            tracker.addImage(imagen, j);
        }

        Image ashDer = herramE.getImage(getClass().getResource("/fes/aragon/recursos/ashDer_1.png"));
        imagen.add(ashDer);
        Image ashIzq = herramE.getImage(getClass().getResource("/fes/aragon/recursos/ashIzq_1.png"));
        //imagen.add(ashIzq);
        Image ashArri = herramE.getImage(getClass().getResource("/fes/aragon/recursos/ashArri_1.png"));
        //imagen.add(ashArri);
        Image ashAba = herramE.getImage(getClass().getResource("/fes/aragon/recursos/ashAba_1.png"));
        //magen.add(ashAba);

        //Terminando de agregar las imagenes tracker
        tracker.addImage(ashDer, 1);
        tracker.addImage(ashIzq, 2);
        tracker.addImage(ashArri, 3);
        tracker.addImage(ashAba, 4);

        // Inicio de las imagenes
        arriba = false;
        abajo = false;
        izquierda = false;
        derecha = true;

        try {
            // Esperar a que se carguen todas las imagenes
            tracker.waitForAll();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }

//    public ArrayList<Pintar> getComponente() {
//        return componente;
//    }
    // Verifica si está dentro del rango y llama simulación de movimiento
    public void desplazar() {
        // Agregar simulación de desplazamiento
        Personaje pers;
        if (derecha) {  //Se desplaza hacia la derecha
            if (dentroRango()) {
                //pers=new Personaje("ashDer", 3, comp); // Verificar****
                this.x = x + 10;
            }
        } else if (izquierda) { //Se desplaza hacia la izquierda
            if (dentroRango()) {
                this.x = x - 10;
            }
        } else if (arriba) {    //Se desplaza hacia arriba
            if (mover) {
                this.y = y - 10;
            }
        } else if (abajo) {     //Se desplaza hacia abajo
            if (mover) {
                this.y = y + 10;
            }
        }
        mover = true;
    }

    public void moverDerecha() {    //Se mueve la imagen hacia la derecha dando el efecto de movimiento
        ArrayList<Image> aPintar = new ArrayList<Image>();
        if (derecha) {
            aPintar.add(persDer.get(1));
            aPintar.add(persDer.get(2));
            aPintar.add(persDer.get(3));
        }

        imagen = aPintar;
        x += (int) x + (5 * multiplicador);
    }

    public boolean dentroRango() {      //Evalua si está dentro del rango de la cuadrícula
        if (x > 636 || x < 0) {
            mover = false;
            System.out.println("Fuera de Rango X");
        }
        if (y > 415 || y < 0) {
            mover = false;
            System.out.println("Fuera de Rango Y");
        }
        return mover;
    }

    public void corrigeCoordenadas() {      //Si se sale del rango de coordenadas, deja a la imagen
        if (x < 0) {                        //Parada en el último lugar "visible" para estar posicionada
            //this.x = x + distancia + 10;
            this.x = 1;
            imagen.add(persDer.get(1));
        } else if (x > 636) {
            //this.x = x - distancia - 10;
            this.x = 635;
            imagen.add(persIzq.get(1));
        }
        if (y < 0) {
            this.y = 1;
            imagen.add(persAba.get(1));
        } else if (y > 415) {
            this.y = 414;
            imagen.add(persArri.get(1));
        }
        mover = true;
    }

    private int cont = 0;

    // Pinta todos los componentes
    @Override
    public void pintar(Graphics2D g) {
        if (cont < (imagen.size() - 1)) {
            g.drawImage(imagen.get(cont), x, y, 40, 40, null);
            cont++;
        }
        g.drawImage(imagen.get(cont), x, y, 40, 40, null);
    }

    // --------------------------- REVISAR--------------------
    @Override
    public void calcular() {
        //ArrayList<Image> paint = new ArrayList<Image>();
        //Ya teniendo los archivos
        if (!datos.isEmpty()) {
            if (dentroRango() == true) {
                //System.out.println("entra");
                String[] ins = datos.get(0).split(" ");
                System.out.println(ins[0]);
                datos.remove(0);
                if ((ins[0].equals("i")) || (ins[0].equals("inicio")) || (ins[0].equals("INICIO"))) {
                    //System.out.println("si es i");
                    imagen.add(persDer.get(1));
                    this.x = Integer.valueOf(ins[1]);
                    this.y = Integer.valueOf(ins[2]);
                }
                // Sigue identificar las siguientes opciones de movimiento
                if (ins[0].equals("iz") || (ins[0].equals("izquierda")) || (ins[0].equals("IZQUIERDA"))) {
                    //System.out.println("iz");
                    imagen.add(persIzq.get(1));
                    arriba = false;
                    abajo = false;
                    derecha = false;
                    izquierda = true;
                }
                if (ins[0].equals("der") || (ins[0].equals("derecha")) || (ins[0].equals("DERECHA"))) {
                    //System.out.println("der");
                    arriba = false;
                    abajo = false;
                    derecha = true;
                    izquierda = false;
                    imagen.add(persDer.get(1));
                }
                if (ins[0].equals("arr") || (ins[0].equals("arriba")) || (ins[0].equals("ARRIBA"))) {
                    imagen.add(persArri.get(1));
                    arriba = true;
                    abajo = false;
                    derecha = false;
                    izquierda = false;
                }
                if (ins[0].equals("aba") || (ins[0].equals("abajo")) || (ins[0].equals("ABAJO"))) {
                    imagen.add(persAba.get(1));
                    // Agregar algo para simular desplazamiento (metodo Personaje.moverAbajo)
                    arriba = false;
                    abajo = true;
                    derecha = false;
                    izquierda = false;
                }
                if ((ins[0].equals("mv")) || (ins[0].equals("mover")) || (ins[0].equals("MOVER"))) {
                    int c = ins.length;
                    if (c==1) {
                        //System.out.println("mv");
                        if (derecha) {
                            if (mover) {
                                this.x = x + distancia;
                                imagen.add(persDer.get(1));
                            }
                        } else if (izquierda) {
                            if (mover) {
                                imagen.add(persIzq.get(1));
                                this.x = x - distancia;
                            }
                        } else if (arriba) {
                            if (mover) {
                                imagen.add(persArri.get(1)); // revisar secuencia 
                                this.y = y - distancia;
                            }
                        } else if (abajo) {
                            if (mover) {
                                imagen.add(persAba.get(1)); // revisar secuencia 
                                this.y = y + distancia;
                            }
                        }
                    } else {
                        this.nu = Integer.valueOf(ins[1]);
                        boolean ban = validaNumero(ins[1]);
                        if (ban == true) {
                            for (int i = 0; i < nu; i++) {
                                if (derecha) {
                                    if (mover) {
                                        this.x = x + distancia;
                                        imagen.add(persDer.get(1));
                                    }
                                } else if (izquierda) {
                                    if (mover) {
                                        imagen.add(persIzq.get(1));
                                        this.x = x - distancia;
                                    }
                                } else if (arriba) {
                                    if (mover) {
                                        imagen.add(persArri.get(1)); // revisar secuencia 
                                        this.y = y - distancia;
                                    }
                                } else if (abajo) {
                                    if (mover) {
                                        imagen.add(persAba.get(1)); // revisar secuencia 
                                        this.y = y + distancia;
                                    }
                                }
                            }
                        }
                        System.out.println(ins[1]);
                    }

                    if ((ins[0].equals("mientras"))) {
                        this.numUno = Integer.valueOf(ins[1]);
                        this.numDos = Integer.valueOf(ins[3]);
                        boolean banU = validaNumero(ins[1]);
                        boolean banD = validaNumero(ins[3]);
                        if (banU == true && banD == true) {
                            while (numUno < numDos) {
                                if (derecha) {
                                    if (mover) {
                                        this.x = x + distancia;
                                        imagen.add(persDer.get(1));
                                    }
                                } else if (izquierda) {
                                    if (mover) {
                                        imagen.add(persIzq.get(1));
                                        this.x = x - distancia;
                                    }
                                } else if (arriba) {
                                    if (mover) {
                                        imagen.add(persArri.get(1)); // revisar secuencia 
                                        this.y = y - distancia;
                                    }
                                } else if (abajo) {
                                    if (mover) {
                                        imagen.add(persAba.get(1)); // revisar secuencia 
                                        this.y = y + distancia;
                                    }
                                }
                                numUno = numUno + 1;
                            }
                        }
                    }
                }
            } else {
                //System.out.println("Cambia coordenadas");
                corrigeCoordenadas();
            }
        } else {

            // Si queda fuera del rango para que no se embucle
            if (dentroRango() == false) {
                //System.out.println("Quedó fuera del rango");
                corrigeCoordenadasFin();
            }
            //System.out.println("fin ultima expresion");

        }

    }

    public void corrigeCoordenadasFin() {
        if (x < 0) {
            this.x = 1;
        } else if (x > 636) {
            this.x = 635;
        }
        if (y < 0) {
            this.y = 1;
        } else if (y > 415) {
            this.y = 414;
        }
        mover = false;
    }

    private int ascii;
    private boolean bandera;

    public boolean validaNumero(String num) {

        for (int i = 0; i < num.length(); i++) {
            ascii = num.charAt(i);
            if (ascii >= 48 && ascii <= 57) {
                bandera = true;
            } else {
                bandera = false;
            }
        }
        return bandera;
    }

}
