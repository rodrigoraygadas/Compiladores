/*
Analizador sintactico que se basa en las siguientes reglas:
E::= T or E
E::= T
T::= F and T
T::= F
F::= not F
F::= true
F::= false
F::= (E)

Simplificando quedaría:
E= T || T + “or” + E + ”;”
T= F || F + ”and” + T
F= “not” + F || “true” || “false” || “(“ + E + ”)”

Se utilizo JFLex para definir las reglas de derivación y se generó 
automaticamente la clase "Léxico". Las reglas de derivación se escribieron
en un archivo .txt, se cargaron a JFlex y se genero el archivo .java que aparece
en el paquete del proyecto

Los datos que requieran ser evaluados deben estar en un archivo en 
formato .txt, en la misma carpeta que el Jar. El archivo debe tener 
por nombre "archivo.txt
*/


package fes.aragon.inicio;
//Se importan las clases necesarias
import fes.aragon.token.Lexico;
import fes.aragon.token.Sym;
import fes.aragon.token.Tokens;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Rodrigo Raygadas
 */
public class Inicio {               

    private boolean error = true;       //Bandera para errores
    private Tokens tokens = null;       //para manejo de lexemas 
    private Lexico analizador = null;   //Permite acceder al analizador lexico 

    public static void main(String[] args) throws IOException {
        Inicio ap = new Inicio();           
        BufferedReader buf;
        try {           //Lectura del archivo.txt
            buf = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/archivo.txt"));
            ap.analizador = new Lexico(buf);
            ap.siguienteToken();        //Se manda a llamar al metodo para leer el siguiente token
            ap.e();                     //metodo e()
        } catch (IOException ex) {
            ex.printStackTrace();       //En caso de existir un error, se imprime la excpeción y la ruta del erro
        }   
    }

    public void e() {       //regla de derivación para E
        while (tokens.getLexema() != Sym.EOF){  //Lo hará mientras no exista el fin de archivo End Of File
            t();    //Se empiezan a llamar los metodos de las reglas semanticas
            if (tokens.getLexema() == (Sym.OR)) {   //Si detecta un terminal, se llama nuevamente al metodo T
                siguienteToken();   
                t();
            }
            while (tokens.getLexema() != Sym.PUNTOCOMA) {   //Si no detecta un punto y coma mandara a llamar a error sintactico
                errorSintactico();
            }
            if (!this.error) {  //Si no exise error, error=true, se marca como error
                System.out.println("Invalida linea= " + (tokens.getLinea() + 1));
                this.error = true; //se cambia la bandera
            } else {
                System.out.println("Valida  linea= " + (tokens.getLinea() + 1));    //error =false, es una linea valida            
            }
            siguienteToken();
        } 
    }
        
    public void t() {   //regla de derivación para T
        f();    //Se manda a llamar al metodo F
        if (tokens.getLexema() == Sym.AND) {    //Comienzan a valorarse las derivaciones
            siguienteToken();
            t();
        }
    }

    public void f() {   //regla de derivación para F
        if (tokens.getLexema() == Sym.NOT) { //Comienzan a valorarse las derivaciones
            siguienteToken();
            f();    //Se vuelve a mandar a llamar a F, recursivo
        } else if (tokens.getLexema() == Sym.TRUE || tokens.getLexema() == Sym.FALSE) {
            siguienteToken();
        } else if (tokens.getLexema() == Sym.PARABERTURA) {
            siguienteToken();
            t();    //Se manda a llamar a las derivaciones de T porque así lo marca la regla
            if (tokens.getLexema() == Sym.PARCIERRE) {
                siguienteToken();
            } else {
                errorSintactico();  //Se marca como error porque no se detecta el cierre de parentesis
            }
        } else {
            errorSintactico();  //Si no se cumple nada de lo anterior, es un error sintactico
        }
    }

    private void errorSintactico() { //El metodo es llamado para los errores sintacticos
        this.error = false;
        //descartar todo hasta encontrar ;            
        do {
            System.out.println(tokens.toString());
            if (tokens.getLexema() != Sym.PUNTOCOMA) {
                siguienteToken();
            }
        } while (tokens.getLexema() != Sym.PUNTOCOMA && tokens.getLexema() != Sym.EOF); //Si el lexema que se está leyendo es 
    }                                                                                   //Diferente al pnto y coma y al fin de archivo se va a ejecutar

    private void siguienteToken() { //Lectura del siguiente token
        try {
            tokens = analizador.yylex(); //Se le asigna el valor generado por JFlex a la variable tokens
            if (tokens == null) {
                tokens = new Tokens("EOF", Sym.EOF, 0, 0);
                throw new IOException("fin archivo");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
