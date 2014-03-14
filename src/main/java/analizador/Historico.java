/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

import java.io.IOException;
import java.util.ArrayList;
import logica.Escritor;
import logica.Lector;

/**
 *
 * @author F209
 */
public class Historico {
    
    public Escritor escritor;
    public int i=0;
    public Lector lector;
    public String sintaxisO;
    public Historico() throws IOException{
       ArrayList<String> listaH= new ArrayList<String>();
       i=0;
       escritor=new Escritor("Documentos/Historico.txt");
       lector=new Lector("Documentos/Historico.txt");
       sintaxisO="";
    }
    
    public void capturarSintaxisO(String sintaxisO){
       this.sintaxisO=sintaxisO;
    }
    
}
