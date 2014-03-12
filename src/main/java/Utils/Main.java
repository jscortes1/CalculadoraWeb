package Utils;

import Logica.Calculadora;
import Logica.Postfijo;

public class Main {
    
    /////     ^
    
    public static void main(String[] args) {
    
        Postfijo post =new Postfijo();
        Calculadora calc =new Calculadora();
        

        String expresionPost=post.analizar();

        if(expresionPost!="error"){
            calc.calcular(expresionPost);
        }else{
            System.out.println("Error de sintaxis!\n\n");
        }
    }
}
