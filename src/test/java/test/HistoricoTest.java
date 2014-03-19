/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import analizador.Historico;
import java.io.IOException;
import org.testng.annotations.Test;

/**
 *
 * @author E205-1
 */
public class HistoricoTest {
    
    Historico historico;
    
    public HistoricoTest() throws IOException{
        historico=new Historico();
    }
    
    @Test
    public void guardaHistorico() throws IOException, ClassNotFoundException{
        
        historico.capturarResultado("prueba");
    }
    
}