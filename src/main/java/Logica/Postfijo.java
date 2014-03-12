package Logica;

import java.util.Scanner;
import java.util.Stack;

public class Postfijo {
    
  
    
  //-----------------------------------------------------------------------------------------------
  //-----------------------------------------------------------------------------------------------
     
    
  //Corregimos casos especiales de multiplicacion con parentesis añadiendo el signo "*"
  //Separamos con espacios cada operador de los operando para su optima conversion a postfijo
  private static String depurar(String s) {
    
    s = "(" + s + ")";//Añadimos parentesis al comienzo y final para que se tome toda la expresion
    
    String simbols = "^+-*/()";//Simbolos matematicos soportados
      
    
    String fixMultiplicaion = "";//Aqui almacenamos la nuva expresion con los parentesis corregidos
    String str = "";//String final a retornar con espacios y "*" ya aplicados
  
    
    //Algoritmo para añadir el signo "*" en los casos especiales
    for (int i = 0; i < s.length(); i++) {
        if(i!=s.length()-1 && i!=0){
            if(s.charAt(i)=="(".charAt(0) && Character.isDigit(s.charAt(i-1))){
                fixMultiplicaion += "*";
            }
            fixMultiplicaion += s.charAt(i);
            
            if(s.charAt(i)==")".charAt(0) && Character.isDigit(s.charAt(i+1))){
                fixMultiplicaion +="*";
            } 
            if(s.charAt(i)==")".charAt(0) && s.charAt(i+1)=="(".charAt(0)){
                fixMultiplicaion += "*";
            }
        }else{
            fixMultiplicaion += s.charAt(i);
        }
    }
    

    
    //Añadimos espacios entre operadores y parentesis
    for (int i = 0; i < fixMultiplicaion.length(); i++) {     
      if (simbols.contains("" + fixMultiplicaion.charAt(i))) {
        str += " " + fixMultiplicaion.charAt(i) + " ";
      }else str += fixMultiplicaion.charAt(i);
    }
    
    
    return str.replaceAll("\\s+", " ").trim();
  } 

  
  
  
  
  //-----------------------------------------------------------------------------------------------
  //-----------------------------------------------------------------------------------------------
  
  //Jerarquia de los operadores
    private static int prioridad(String op) {
        int prf = 99;
        if (op.equals("^")) prf = 5;
        if (op.equals("*") || op.equals("/")) prf = 4;
        if (op.equals("+") || op.equals("-")) prf = 3;
        if (op.equals(")")) prf = 2;
        if (op.equals("(")) prf = 1;
        return prf;
    }
    
  
  
  //-----------------------------------------------------------------------------------------------
  //-----------------------------------------------------------------------------------------------
  
  public String analizar(){
      
    Sintaxis sintaxis =new Sintaxis(); 
      
    String postfix="";  
    Scanner input = new Scanner(System.in);
    
    //Entrada de datos
    System.out.print("Expresión algebraica:  ");
    
    System.out.println("\n");   
    String entrada=input.next();//Capturamos la expresion algebraica
    System.out.println("");
    
    
    //Comprobamos la sintaxis
    if(sintaxis.balanceada(entrada)==false) return "error";
    
    
    //Depurar la expresion algebraica
    String expr = depurar(entrada);
    String[] arrayInfix = expr.split(" ");

    //Declaración de las pilas
    Stack < String > E = new Stack < String > (); //Pila entrada
    Stack < String > P = new Stack < String > (); //Pila temporal para operadores
    Stack < String > S = new Stack < String > (); //Pila salida

    //Añadir la array a la Pila de entrada (E)
    for (int i = arrayInfix.length - 1; i >= 0; i--) {
      E.push(arrayInfix[i]);
    }

    
//Algoritmo  a Postfijo
    try {     
      while (!E.isEmpty()) {
        switch (prioridad(E.peek())){
          case 1:
            P.push(E.pop());
            break;
          case 3:
          case 4:
            while(prioridad(P.peek()) >= prioridad(E.peek())) {
              S.push(P.pop());
            }
            P.push(E.pop());
            break; 
            case 5:
            while(prioridad(P.peek()) >= prioridad(E.peek())) {
              S.push(P.pop());
            }
            P.push(E.pop());
            break; 
          case 2:
            while(!P.peek().equals("(")) {
              S.push(P.pop());
            }
            P.pop();
            E.pop();
            break; 
          default:
            S.push(E.pop()); 
        } 
      } 

      
    //Eliminamos caracteres indeseados
      postfix = S.toString().replaceAll("[\\]\\[,]", "");


      System.out.println("Postfijo: " + postfix+"\n");
           
      
      
    }catch(Exception ex){ 
      System.err.println(ex);
    }
    
    return postfix;
  }
}