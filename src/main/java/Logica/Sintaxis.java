package Logica;

public class Sintaxis {
	
    class Nodo {
        char simbolo;
        Nodo sig;
    }
	
    private Nodo raiz;
    
    Sintaxis () {
        raiz=null;
    }
    
    public void insertar(char x) {
    	Nodo nuevo;
        nuevo = new Nodo();
        nuevo.simbolo = x;
        if (raiz==null)
        {
            nuevo.sig = null;
            raiz = nuevo;
        }
        else
        {
            nuevo.sig = raiz;
            raiz = nuevo;
        }
    }
    
    public char extraer ()
    {
        if (raiz!=null)
        {
            char informacion = raiz.simbolo;
            raiz = raiz.sig;
            return informacion;
        }
        else
        {
            return Character.MAX_VALUE;
        }
    }  
    
    public boolean vacia() {
        if (raiz==null) {
            return true;
        } else {
            return false;
        }
    }
    
    
    public boolean balanceada(String cadena) {

    	for (int f = 0 ; f < cadena.length() ; f++)
    	{
            
            if(f!=cadena.length()-1 && f!=0){
                if(cadena.charAt(f) == '(' && cadena.charAt(f+1) == ')'){
                    return false;
                }
            }
            
    	    if (cadena.charAt(f) == '(' || cadena.charAt(f) == '[' || cadena.charAt(f) == '{') {
    	    	insertar(cadena.charAt(f));
    	    } else {
    	    	if (cadena.charAt(f)==')') {
    	    	    if (extraer()!='(') {
    	    	        return false;
    	    	    }
    	     	} else {
    	    	    if (cadena.charAt(f)==']') {
    	    	        if (extraer()!='[') {
    	    	            return false;
    	    	        }
    	    	    } else {
    	    	        if (cadena.charAt(f)=='}') {
    	    	            if (extraer()!='{') {
    	    	                return false;
    	    	            }
    	    	        }
    	    	    }
    	        }
   	    }   
        }
    	if (vacia()) {
    	    return true;
    	} else {
   	    return false;
    	}
    }	
    
}