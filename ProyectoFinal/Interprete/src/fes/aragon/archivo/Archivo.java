/*
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
import java.nio.Buffer;
import java.util.ArrayList;

/**
 *
 * @author Rodrigo Raygadas
 */

// Va a trabajar con el archivo
public class Archivo {
    
    private File archivo;
    // Va a guardar las instrucciones ya leidas
    private ArrayList<String> datos=new ArrayList<>();

    public Archivo() {
    }
    
    public Archivo(File archivo) {
        this.archivo = archivo;
    }
    
    // Leer la información del codigo intermedio
    public void cargar() throws FileNotFoundException, IOException{
        FileReader fr=new FileReader(archivo.getAbsolutePath());
        BufferedReader bf = new BufferedReader(fr);
        String cad="";
        while((cad=bf.readLine())!=null){
            datos.add(cad);
            //System.out.println(cad);
        }
        bf.close();
        //System.out.println(datos.size());
        //System.out.println("entra");
    }

    public ArrayList<String> getDatos() {
        return datos;
    }
    
    
    
}
