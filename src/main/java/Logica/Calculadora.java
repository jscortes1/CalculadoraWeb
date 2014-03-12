package Logica;

import java.util.Stack;

public class Calculadora {
    
   public float evaluar(String op, String n2, String n1) {
    float num1 = Float.parseFloat(n1);
    float num2 = Float.parseFloat(n2);
    if (op.equals("+")) return (num1 + num2);
    if (op.equals("-")) return (num1 - num2);
    if (op.equals("*")) return (num1 * num2);
    if (op.equals("/")) return (num1 / num2);
    if (op.equals("%")) return (num1 % num2);
    if (op.equals("^")) return (float) (Math.pow(num1,num2));
    
    return 0;
  }
   
  public void calcular(String expr){
      //Entrada (Expresión en Postfija)

    String[] post = expr.split(" ");    
    
    //Declaración de las pilas
    Stack < String > E = new Stack < String > (); //Pila entrada
    Stack < String > P = new Stack < String > (); //Pila de operandos

    //Añadir post (array) a la Pila de entrada (E)
    for (int i = post.length - 1; i >= 0; i--) {
      E.push(post[i]);
    }

    //Algoritmo de Evaluación Postfija
    String operadores = "+-*/%^"; 
    while (!E.isEmpty()) {
      if (operadores.contains("" + E.peek())) {
        P.push(evaluar(E.pop(), P.pop(), P.pop()) + "");
      }else {
        P.push(E.pop());
      } 
    }

    //Mostrar resultados:
    System.out.println("-----------------------------------\nResultado: " + P.peek()+"\n-----------------------------------\n\n");
  }
    
}