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
    public  ArrayList<String> listaH;
    public String resultado;
    public String historico;
    
    public Historico() throws IOException{
       listaH= new ArrayList<String>();
       i=0;
       escritor=new Escritor("Documentos/Historico.txt");
       lector=new Lector("Documentos/Historico.txt");
       sintaxisO="";
       resultado="";
       historico="";
    }
    
    public void capturarSintaxisO(String sintaxisO){
       this.sintaxisO=sintaxisO;
    }
    
    
    public void cargarHistorico() throws IOException, ClassNotFoundException {
		Lector lector=new Lector("Documentos/Historico.txt");
		listaH=(ArrayList<String>) lector.leerLinea();

		lector.cerrarArchivo();


	}
	
	public void guardarHistorico() throws IOException {

		Escritor escritor=new Escritor("Documentos/Historico.txt");
		escritor.leerLinea(listaH);
		escritor.cerrarArchivo();


	}

        void capturarResultado(String resultado) {
            this.resultado=resultado;
        }

    void ordenarHistorico() {
        historico=this.sintaxisO+"   "+this.resultado;
    }

    
    
    
}
