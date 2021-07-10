/*
Programa que busca y contabiliza los caracteres repetidos dentro de un .txt
Integrantes:
    Ramírez Leon Miguel Angel
    Raygadas Baez Rodrigo
Versión de IDE:
    Netbeans IDE 8.2
 */
public class Palabras {
    String pal;
    int cant;

    public Palabras(String pal, int cant) {
        this.pal = pal;
        this.cant = cant;
    }

    public String getPal() {
        return pal;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    @Override
    public String toString() {
        
        return "\""+pal+"\" se repite: "+cant+"\n"; 
    }
    
    
}
