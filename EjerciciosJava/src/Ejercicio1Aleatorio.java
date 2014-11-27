import java.io.*;
import java.util.*;

public class Ejercicio1Aleatorio {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException,ClassNotFoundException{
		// TODO Auto-generated method stub

		
		//departamentos departament = new departamento();
		//creamos el archivo
		File dep =new File("c:\\datos\\Aleatorio.dat");
		
		//int idDep = 0;
		//String nombre = null,Localidad = null;
		//int posicion = 0;
		
		//Declaramos el fichero de acceso aleatorio para lectura y escritura
		
		RandomAccessFile depAle=new RandomAccessFile(dep,"rw");
		
		
		String nombres[]={"pepe","juan","Sevillana","Alicia","Carmen","perica"};
		
		int numDep[]={1,2,3,4,5,6};
		
		String localidad[] = { "Zaragoza", "Huesca", "Teruel", "Zaragoza",
				"Huesca", "Teruel"};

		
		for(int i=0;i<localidad.length;i++){
			
			//departamento.write(depAle);
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
