package analizador;

import java.util.Stack;

public class Sintaxis {
    
    Stack <String> pilaAgrupadores = new Stack <String>();    
    
    public boolean balanceada(String cadena) {

    	for (int i = 0 ; i < cadena.length() ; i++)
    	{
            
            // Si es difente del primer y ultimo carcter de la expresion
            if(i!=0 && i!=cadena.length()-1){
                
                //Si hay parentesis sin digito "()" retorne error 
                if(cadena.charAt(i) == '(' && cadena.charAt(i+1) == ')'){
                    return false;
                }
                
            }
            
            //Si encuentra "(" añadalo a la pila
    	    if (cadena.charAt(i) == '(') {
    	    	pilaAgrupadores.push("(");
    	    }
            
            //Si encuentra ")" elimine un elemento de la pila
            if (cadena.charAt(i)==')') {
    	    	
                //Si esta vacia la pila retorne error, de lo contrario, elimine un elemnto de la pila
                if(pilaAgrupadores.empty()){
                    return false;
                }else{
                    pilaAgrupadores.pop();
                }
   	    }               
        }
        
        //Si la pila esta vacia, todo salio correctamen, la cantidad de parentesis son la misma que las de salida, si no esta vacia, se retorna false para indicar error de sintaxis
    	if (pilaAgrupadores.empty()) {
    	    return true;
    	} else {
   	    return false;
    	}
    }	
 
    
    
    
    //-----------------------------------------------------------------------------------------------
    // Algoritmo para añadir "*" en los parentesis que multiplican ->  ")("  ->  ")#("
    //-----------------------------------------------------------------------------------------------
    
    public String corregirParentesis(String sintaxisOriginal){
        
        String parentesisCorregido = "";//Aqui almacenamos la nuva expresion con los parentesis corregidos

        //Algoritmo para añadir el signo "*" en los casos especiales
        for (int i = 0; i < sintaxisOriginal.length(); i++) {
            
            // Si no estamos ni al final ni al comienzo de la expresion entonces....
            if(i!=0 && i!=sintaxisOriginal.length()-1){
                  
                // Si hay un numero al lado de un parentesis " 5(...) ", es multiplicacion
                if(sintaxisOriginal.charAt(i)=="(".charAt(0) && Character.isDigit(sintaxisOriginal.charAt(i-1))){
                    parentesisCorregido += "*";                                 // añadir "*" a la expresion
                }
                parentesisCorregido += sintaxisOriginal.charAt(i);
                
                // Si hay un numero al lado de un parentesis " (...)5 ", es multiplicacion
                if(sintaxisOriginal.charAt(i)==")".charAt(0) && Character.isDigit(sintaxisOriginal.charAt(i+1))){
                    parentesisCorregido += "*";                                 // añadir "*" a la expresion
                } 

                // Si hay parentesis opuestos juntos "(...)(...)", es multiplicacion
                if(sintaxisOriginal.charAt(i)==")".charAt(0) && sintaxisOriginal.charAt(i+1)=="(".charAt(0)){
                    parentesisCorregido += "*";                                 // añadir "*" a la expresion
                }

            }else{
                
                parentesisCorregido += sintaxisOriginal.charAt(i);              //Añadimos normalmente el carcter actual a la expresion
                
            }
        }
    
        return parentesisCorregido;                                             // Retornamos sintaxis corregida
    }
    
    
    //-----------------------------------------------------------------------------------------------
    // Algoritmo para reconocer y validar un numero negativo
    //-----------------------------------------------------------------------------------------------
    
    public String corregirNegativos(String sintaxisOriginal){
        
        String negativoCorregido = "";                                          //Aqui almacenamos la nueva expresion con los negativos corregidos

        //Recorremos toda la sintaxis y reparamos negativos
        for (int i = 0; i < sintaxisOriginal.length(); i++) {

            //Si el signo "-" se encuentra despues de un parentesis "(", es un numero negativo y no un signo negativo comun.
            if(sintaxisOriginal.charAt(i)=="-".charAt(0) && sintaxisOriginal.charAt(i-1)=="(".charAt(0)){
                
                negativoCorregido += "(1-2)";                                   // El truco, la magia, reemplzar "-" por "(1-2)"....
                
            }else{
                negativoCorregido += sintaxisOriginal.charAt(i);                // Si no, añadir el negativo normalmente a la expresion
            }
        }

        return negativoCorregido;                                               // retornamos la nueva expresion
    }    
    
    
    //-----------------------------------------------------------------------------------------------
    //   Algoritmo para añadir espacios entre cada operador matematico y parentesis, de esta manera separamos numeros de mas de 1 digito
    //-----------------------------------------------------------------------------------------------
    
    public String corregirDigitos(String sintaxisOriginal){
        
        String simbolos = "^+-*/()";//Simbolos matematicos soportados
        String digitosCorregidos = "";//String final a retornar con espacios y "*" ya aplicados
        
        //Añadimos espacios entre operadores y parentesis
        for (int i = 0; i < sintaxisOriginal.length(); i++) {
            
            // Si los operadores se encuentra en la sintaxis añada espacio, de lo contrario no lo haga
            if (simbolos.contains("" + sintaxisOriginal.charAt(i))) {
                digitosCorregidos += " " + sintaxisOriginal.charAt(i) + " ";    // Añadiendo espacios
            }else{
                digitosCorregidos += sintaxisOriginal.charAt(i);                // Mo se añade espacio
            }

        }
    
    digitosCorregidos=digitosCorregidos.replaceAll("\\s+", " ").trim();         // Eliminamos espacio al comienzo y final de la expresion y algunos duplicado de manera erronea, dejando asi un solo espacio como separador.
    
    
    return digitosCorregidos;
    }
    
    
    //-----------------------------------------------------------------------------------------------
    //   Algoritmo para validar operadores duplicados y al comienzo y al final de la expresion, el " - " al comienzo sería la unica excepcion
    //-----------------------------------------------------------------------------------------------
    
    public Boolean validarSignos(String sintaxisOriginal){
        
        String[] listaSintaxis=sintaxisOriginal.split(" ");
        
        String simbolos = "^+-*/";//Simbolos matematicos soportados

        String caracterActual;
        String caracterAnterior;
        String caracterSuperior;
        
        //recorremos la sintaxis
        for (int i = 1; i < listaSintaxis.length-1; i++) {
            
            caracterActual=""+listaSintaxis[i].charAt(0);
            caracterAnterior=""+listaSintaxis[i-1].charAt(0);
            caracterSuperior=""+listaSintaxis[i+1].charAt(0);
            
            if (simbolos.contains(caracterActual)) {
                if (simbolos.contains("" + caracterAnterior) || simbolos.contains("" + caracterSuperior)) {
                    return false;
                }
                if (caracterAnterior.equals("(") && !caracterActual.equals("-") || caracterSuperior.equals(")")) {
                    return false;
                }
            }
        }
        return true;
    }
    
    
    public String anazalizar_Infijo(String sintaxisOriginal){
        
        String sintaxisCorregida = sintaxisOriginal;
          
        sintaxisCorregida = "(" + sintaxisCorregida + ")";          //Añadimos parentesis al comienzo y final para que se tome toda la expresión
        sintaxisCorregida =corregirNegativos(sintaxisCorregida);
        sintaxisCorregida =corregirParentesis(sintaxisCorregida);   //Añadimos parentesis al comienzo y final para que se tome toda la expresión 
        sintaxisCorregida =corregirDigitos(sintaxisCorregida);      //Añadimos parentesis al comienzo y final para que se tome toda la expresión
        
        
        if(balanceada(sintaxisCorregida)==false || validarSignos(sintaxisCorregida)==false){
            return "error";
        }else{
            return sintaxisCorregida;
        }
        
        
    }
}