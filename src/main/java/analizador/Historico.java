/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

import java.io.File;
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
    public  ArrayList<String> listaH;
    public String resultado;
    public String historico;
    public String errores;
    String path = new File(".").getCanonicalPath();
    
    
    public Historico() throws IOException{
       listaH= new ArrayList<String>();
       i=0;
       escritor=new Escritor(path+"/src/main/java/Documentos/Historico.txt");
       lector=new Lector(path+"/src/main/java/Documentos/Historico.txt");
       sintaxisO="";
       resultado="";
       historico="";
    }
    
    public void capturarSintaxisO(String sintaxisO){
       this.sintaxisO=sintaxisO;
    }
    
    
public void cargarHistorico() throws IOException, ClassNotFoundException {

            Lector lector=new Lector(path+"/src/main/java/Documentos/Historico.txt");
            listaH=(ArrayList<String>) lector.leerLinea();

            lector.cerrarArchivo();


    }
	
    public void guardarHistorico() throws IOException {

            Escritor escritor=new Escritor(path+"/src/main/java/Documentos/Historico.txt");
            escritor.leerLinea(listaH);
            escritor.cerrarArchivo();


    }

    public void capturarResultado(String resultado) throws IOException {
        System.out.println(path+"/src/main/java/Documentos/Historico.txt");
        
        this.resultado=resultado;
        listaH.add(resultado);
        
        System.out.println(listaH.toString());
        guardarHistorico();
    }

    public void ordenarHistorico() {
        historico=this.sintaxisO+"   "+this.resultado;
        listaH.add(historico);
    }

    public void capturarValidacion(String errores,boolean validacion) {
       this.errores=errores;
    }

    
    
    
}
