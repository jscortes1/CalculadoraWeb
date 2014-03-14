package utils;

import logica.Calculadora;


public class Main {
    
    //       ^
    
    public static void main(String[] args) {

        // INSTANCIAS Y VARIABLES
        
        Calculadora calculadora =new Calculadora();     //Instanciamos motor de la calculadora
       
        
        String expresionOriginal;                       // Expresion original que escribira el usuario
        String resultado;                               // Resultado de la operacion aritmetica
                
        
        
        // INTERFAZ
        
        
        
        
        // REALIZAR OPERACION
       
        resultado = calculadora.calcular("1+2");    
        
        
        // Imprimimos resultado
        System.out.println("-----------------------------------\nResultado: " + resultado+"\n-----------------------------------\n\n");        
    
    }
}
