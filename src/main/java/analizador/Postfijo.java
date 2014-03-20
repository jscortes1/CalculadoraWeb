package analizador;

import java.util.Stack; //Clase para la simulacion de piimport logica.Sintaxis;


public class Postfijo {
    
    Sintaxis verificarSintaxis =new Sintaxis();                   // Instanciamos la clase sintaxis, que retorna una expresion legible y validada
  

    //-----------------------------------------------------------------------------------------------
    // Tabla de prioridades
    //-----------------------------------------------------------------------------------------------
    private static int prioridad(String simbolo) {
        
        int prioridad = 99; //Inicializamos en 99 para darle mas prioridad a futuros carcacteres mas especiales
        
        if (simbolo.equals("^")) prioridad = 5;
        if (simbolo.equals("*") || simbolo.equals("/")) prioridad = 4;
        if (simbolo.equals("+") || simbolo.equals("-")) prioridad = 3;
        
        return prioridad;
    }
    
  
  
    //-----------------------------------------------------------------------------------------------
    // Algoritmo para convertir una expresion corriente a postfijo
    //-----------------------------------------------------------------------------------------------
  
    public String convertir_a_Postfijo(String entradaUsuario){  

        String respuestaSintaxis=verificarSintaxis.anazalizar_Infijo(entradaUsuario);   // variable que guarda la expresion validada y con espacios aplicados, parentesis corregidos y numeros negativos corregisdos.
        String postfijoConvertido="";                                                   // variable que guarda la expresion final ya convertida
    
      
        // Si no hay error de sintaxis convierta a postfijo y retornela, de lo contrario retorne error
        if(!respuestaSintaxis.equals("error")){

            String[] listadoSintaxis = respuestaSintaxis.split(" ");                    // Separamos los respectivos caracteres y numeros en una lista
            String caracterActual;                                                      // Almacena el caracter actual que se esta procesando durante la conversion
        
            
            // Pila que guardara todos los operadores matematicos y parentesis
            Stack <String> pilaOperadores = new Stack <String>();                       

        
            //---------------------------------------------------------------------------------------------------
        
            pilaOperadores.push("(");                                                   // El primer elemnto que estará en la pila sera un parentesis abierto, para que el algoritmo realice el proceso de  conversion correctamente
        
            // recorremos la lista de caracteres
            for(int i=1; i<listadoSintaxis.length;i++){    
                
                caracterActual=listadoSintaxis[i];                                      // Para almacenar el caracter actual temporalmente


                //Si es un numero añadalo al postfijo, si no a la pila
                if(Character.isDigit(caracterActual.charAt(0))){

                    postfijoConvertido+=caracterActual;                                 //Agregamos el numero al postfijo
                    postfijoConvertido+=" ";                                            //Añadimos espacio para seperar los numeros de mas de 1 digito

                }else{
                    
                    //******************************************************************************
                    //*            
                    //*               Algoritmo de Infijo a Postfijo
                    //*            
                    //******************************************************************************

                    
                    //Si el carcaterActual es ")", elimine elemnetos de la pila mientras los añade al postfijo hasta que se encuentre con "("
                    if(caracterActual.equals(")")){
                        
                        //Hasta que se encuentre con "(", añadir de operadores desde la pila al postfijo
                        while(!pilaOperadores.peek().equals("(")){
                            postfijoConvertido+=pilaOperadores.pop();                   //Añadiendo a psotfijo el elemnto eliminado de la pila con pop()
                            postfijoConvertido+=" ";                                    //Añadimos espacio para seperar los numeros de mas de 1 digito
                        }
                        pilaOperadores.pop();
                    }


                    //Si el ultimo elemento de la pila es "(" o el actual de la sintaxis es "(", agregue cualquier elemento a la pila
                    else if(pilaOperadores.peek().equals("(") || caracterActual.equals("(")){
                        pilaOperadores.push(caracterActual);                            //Añadimos cualquier caracter entrante a la`pila
                    }


                    //Si el ultimo elemento de la pila es igual al carcter actual, añadir elemnto pila a postfijo y reemplzar elemento pila por actual
                    else if(prioridad(pilaOperadores.peek())==prioridad(caracterActual)){

                        postfijoConvertido+=pilaOperadores.pop();                   //Añadiendo a psotfijo el elemento eliminado de la pila con pop()
                        postfijoConvertido+=" ";                                    //Añadimos espacio para seperar los numeros de mas de 1 digito
                        pilaOperadores.push(caracterActual);                        //Agregamos a la pila el caracterActual

                    }

                    
                    // Si el elmento de la pila tiene mayor prioridad que el carcter actual, elimine de pila y agregue a postfijo hasta que necuentre "("
                    else if(prioridad(pilaOperadores.peek())>prioridad(caracterActual)){

                        //Hasta que se encuentre con "(", añadir a postfijo operadores eliminados desde la pila
                        while(!pilaOperadores.peek().equals("(")){                  
                            postfijoConvertido+=pilaOperadores.pop();                //Añadiendo a psotfijo el elemento eliminado de la pila con pop()
                            postfijoConvertido+=" ";                                 //Añadimos espacio para seperar los numeros de mas de 1 digito
                        }
                        pilaOperadores.push(caracterActual);                        //Despues de actualizar pila, añadimos el caracter actual a la pila

                    }

                    // Si el elmento de la pila tiene menor prioridad que el carcter actual, agregue normalmente el caracter actual
                    else if(prioridad(pilaOperadores.peek())<prioridad(caracterActual)){

                        pilaOperadores.push(caracterActual);                        // Agruegue a la pila el carcter actual

                    }

                }

            }
        

            //Si la pila todavia no esta vacia, terminamos de añadir los elementos restantes de la pila al postfijo
            if(!pilaOperadores.empty()){

                //Hasta que se encuentre con "(", añadir de operadores desde la pila al postfijo
                while(!pilaOperadores.peek().equals("(")){
                     postfijoConvertido+=pilaOperadores.pop();                          //Añadiendo a psotfijo el elemento eliminado de la pila con pop()
                     postfijoConvertido+=" ";                                           //Añadimos espacio para seperar los numeros de mas de 1 digito
                }
            }


            //Eliminamos del postfijo el ultimo espacio en blanco
            postfijoConvertido=postfijoConvertido.substring(0, postfijoConvertido.length()-1); 

            //Imprimimos el postfijo por cuestiones de DEBUG
            System.out.println("DEBUG - Postfijo: " + postfijoConvertido+"\n");


            return postfijoConvertido;      //Retornamos el resultado

        }else{

            return "error";                 //Retornamos el resultado

        }       
    
    }
  
}