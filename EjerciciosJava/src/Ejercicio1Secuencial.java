

import java.io.*;
import java.util.*;

public class Ejercicio1Secuencial {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws  IOException,ClassNotFoundException{
		// TODO Auto-generated method stub

		//defino la clase departamento
		departamentos departamento;
		
		//declaramos el fichero
		File dep=new File("c:\\datos\\Departamentos.dat");
		
		//creamos los flujos de salida y de entrada
		
		FileOutputStream depOut= new FileOutputStream(dep);
		FileInputStream depIn=new FileInputStream(dep);
		
		//Conectamos el flujo de bytes al de salida y al de  entrada
		ObjectOutputStream datOut = new ObjectOutputStream(depOut);
		ObjectInputStream datIn = new ObjectInputStream(depIn);

		
		
		String nombres[]={"pepe","juan","Sevillana","Alicia","Carmen","perica"};
		
		int numDep[]={1,2,3,4,5,6};
		
		String localidad[] = { "Zaragoza", "Huesca", "Teruel", "Zaragoza",
				"Huesca", "Teruel"};

		//escribimos 
		for (int i=0;i<numDep.length;i++){
			//creamos el objeto departamento
			
			departamento=new departamentos(numDep[i],nombres[i],localidad[i]);
			
			//escribimos en el fichero
			datOut.writeObject(departamento);
		}
		
		depOut.close();//cerramos el stream de salida
		
		//leemos el contenido del fichero
		
		try{
			while(true){
				//leemos departamento
				
				
				departamento=(departamentos)datIn.readObject();
			
				System.out.println("Nº departamento: " + departamento.getnumDep()
						+ "\n Nombre: " + departamento.getnom() + "\n Localidad: "
						+ departamento.getlocalidad()+ "\n\n");

			}
			
			
		} catch (EOFException eo) {
		}

		datIn.close();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}//main

}//class
