/*
    Programa que contabiliza las palabras de un archivo .txt solicitando el separador de línea
Integrantes:
    Ramírez Leon Miguel Angel
    Raygadas Baez Rodrigo
Versión de IDE:
    Netbeans IDE 8.2
 */
package fes.aragon.archivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import fes.aragon.archivo.Ventana;

/**
 *
 * @author Rodrigo Raygadas
 */
public class Archivo {

    static ArrayList<String> palabras = new ArrayList<>();

//Se encarga de leer los espacios del archivo .txt    
    public static ArrayList<String> abrir() throws IOException {
        String ruta = null;
        try {
            JFileChooser abrir = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Solo txt", "txt", "TXT");
            abrir.setFileFilter(filtro);
            int valor = abrir.showOpenDialog(null);
            if (valor == JFileChooser.APPROVE_OPTION) {
                ruta = abrir.getSelectedFile().getPath();      //Se toma la ruta que seleccionó el usuario
                File file = new File(ruta);
                try{
                FileReader fr = new FileReader(file);           // Creamos un nuevo objeto para lectura de archivos
                BufferedReader buffer = new BufferedReader(fr);
                String linea;
                while ((linea = buffer.readLine()) != null) {       //empieza la lectura del archivo                   
                    String[] pal = linea.split(Ventana.separador); //Cada linea la separa por el caracter que ingresó el usuario.
                    for (String a : pal) {
                        palabras.add(a);                            //Cada palabra que encuentre se añade al arreglo de palabras    
                    }
                }
                fr.close();                                         //cerramos la lectura del archivo
                buffer.close();                                     //cerramos el buffer 
                }catch (FileNotFoundException ex) {                 //La excepción es obligatoria cuando se manejan archivos
                ex.printStackTrace();                               //En caso de caer en la excepcion, imprime la ruta que produce el error 
            } catch (IOException ex) {
                ex.printStackTrace();                               //En caso de caer en la excepcion, imprime la ruta que produce el error 
            }
            } else {
                throw new IOException("Se necesita un archivo");    //Si no encuentra un archivo, mostrara un mensaje
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return palabras;
    }
    
    

}

