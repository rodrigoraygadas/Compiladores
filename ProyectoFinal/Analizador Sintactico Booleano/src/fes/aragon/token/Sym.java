package fes.aragon.token;

/**
 *Metadatos para simbolos terminales
 * @author Rodrigo Raygadas
 */
public class Sym {
    public static final int TRUE=0;
    public static final int FALSE=1;
    public static final int AND=2;
    public static final int OR=3;
    public static final int NOT=4;
    public static final int PARABERTURA=5;
    public static final int PARCIERRE=6;
    public static final int PUNTOCOMA=7;
    public static final int EOF=8;
    public static final String[] terminales=new String[]{
        "TRUE",
        "FALSE",
        "AND",
        "OR",
        "NOT",
        "PARABERTURA",
        "PARCIERRE",
        "PUNTOCOMA",
        "EOF"
    };
    
}
