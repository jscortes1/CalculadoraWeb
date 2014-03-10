package Servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Lector {

	
	private File file;
	private FileInputStream fis;
	private ObjectInputStream ois;

	public  Lector(String nombreA) throws IOException{
		file=new File(nombreA);
		fis=new FileInputStream(this.file);
		ois=new ObjectInputStream(fis);

	
	}
	
	public Object leerLinea() throws IOException, ClassNotFoundException{
			return ois.readObject();
		
	}
	
	public void cerrarArchivo() throws IOException{
		ois.close();
	}


	


}
