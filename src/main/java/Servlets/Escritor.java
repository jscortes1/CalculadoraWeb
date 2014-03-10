package Servlets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Escritor {
	private File file;
	private FileOutputStream fis;
	private ObjectOutputStream ois;


	public  Escritor(String nombreA) throws IOException{
		
		file = new File (nombreA);
		fis=new FileOutputStream(file);
		ois=new ObjectOutputStream(fis);
	}

	public void leerLinea(Object obj) throws IOException{
		ois.writeObject(obj);
	}

	public void cerrarArchivo() throws IOException{
		ois.close();
	}
}
