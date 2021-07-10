package fes.aragon.token;

/**
 *  Metodos getters y setters para los lexenmas, tokens, lineas y
 *  columnas que se utilizaran dentro del txt
 * @author Rodrigo Raygadas
 */
public class Tokens {
    private String token;
    private int lexema;
    private int linea;
    private int columna;

    public Tokens(String token, int lexema, int linea, int columna) {
        this.token = token;
        this.lexema = lexema;
        this.linea = linea;
        this.columna = columna;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getLexema() {
        return lexema;
    }

    public void setLexema(int lexema) {
        this.lexema = lexema;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
 
    @Override
    public String toString() {
        return "Tokens{" + "token=" + token + ", lexema=" + lexema + ", linea=" + linea + ", columna=" + columna + '}';
    }
   
    
}
