/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;


import analizador.Calculadora;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author F212
 */
public class CalculadoraTest {
 
    @Test
    public void debeSumarDosNumeros() throws IOException{
        String a="2";
        String b="2";
        float resultado=4;
        Calculadora c=new Calculadora();
        float x=c.evaluar("+", a, b);
        Assert.assertEquals(x,resultado);
    }
    
    
    
    
    
    @Test
    public void division() throws IOException{
        String a="5";
        String b="2";
        float resultado=2.5f;
        Calculadora c=new Calculadora();
        float x=c.evaluar("/", a, b); 
        Assert.assertEquals(x, resultado);
    }
    
    @Test(expectedExceptions = ArithmeticException.class)
    public void divisionpor0() throws IOException{
        String a="5";
        String b="0";
        float resultado=2.5f;
        Calculadora c=new Calculadora();
        float x=c.evaluar("/", a, b); 
    }
    
    @Test
    public void calcular() throws IOException{
        Calculadora c = new Calculadora();
        
        String x=c.calcular("(2)(2)");
        Assert.assertEquals(x, "4");
    }
    
    
    
    
}
