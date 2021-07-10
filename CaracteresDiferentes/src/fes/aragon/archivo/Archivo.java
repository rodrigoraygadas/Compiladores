/*
Programa que busca y contabiliza los caracteres repetidos dentro de un .txt
Integrantes:
    Ramírez Leon Miguel Angel
    Raygadas Baez Rodrigo
Versión de IDE:
    Netbeans IDE 8.2
 */
package fes.aragon.archivo;
//Se importan las librerías necesarias 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Rodrigo Raygadas
 */
public class Archivo {
    ArrayList<String> palabras = new ArrayList<>();
//Busca los espacios para identificarlos como palabras
    public ArrayList<String> abrir() {
        JFileChooser abrir = new JFileChooser();        //Selector de archivos

        int dato = abrir.showOpenDialog(null);

        if (JFileChooser.APPROVE_OPTION == dato) {
            FileReader fr = null;

            try {
                File ruta = abrir.getSelectedFile();
                fr = new FileReader(ruta);
                BufferedReader uf = new BufferedReader(fr);
                String cad = "";                    //Variable para cadenas

                while ((cad = uf.readLine()) != null) {
                    String[] tokens = cad.split("");       //Se separa por espacios
                    for (int i = 0; i < tokens.length; i++) {   //Se lee todo el txt
                        palabras.add(tokens[i]);
                    }

                }
            } catch (FileNotFoundException ex) {

            } catch (IOException ex) {

            } finally {
                try {
                    fr.close();

                } catch (IOException ex) {

                }
            }
        }
        return palabras;
    }
}
