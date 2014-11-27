import java.io.*;
import java.util.*;

public class Ejer1Aleatorio {

	/**
	 * @param args
	 * @throws FileNotFoundException,IOException 
	 */
	public static void main(String[] args) throws FileNotFoundException,IOException {
		// TODO Auto-generated method stub

		departamento departamento = new departamento();
		//creamos el archivo
		File dep=new File("c:\\datos\\departamentos2.dat");
		
		//declaramos el fichero de acceso aleatorio, escritura+lectura
		RandomAccessFile depAle=new RandomAccessFile(dep,"rw");
		
		//declaramos la información que vamos a escribir en el archivo
		
		String nombres[] = { "Juan", "Ana", "Carlos", "Alicia", "Carmen",
		"Jesús" };
		
		int numDep[] = { 1, 2, 3, 4, 5, 6 };

		String localidad[] = { "Zaragoza", "Huesca", "Teruel", "Zaragoza",
		"Huesca", "Teruel"};
		
		for(int i=0;i<numDep.length;i++){
			// escribimos la nueva info en la clase
			//departamento.setnumDep(numDep);
			//departamento.setnom(nombres);
			//departamento.setlocalidad(localidad);
			
			depAle.seek(depAle.length());
			
			//depAle.seek(pos); // Colocar el puntero
			departamento.write(depAle);

		}
		
		//leemos el archivo
		int posicion=0;
		for(;;){
			
			depAle.seek(posicion);
			departamento.Leer(depAle);
			
			System.out.println("NumDep: " + numDep + "\nNombre: " + nombres
					+ "\nLocalidad " + localidad + "\n\n ");

		//nos colocamos en el siguiene registro sumando 40 bytes al puntero
			
			posicion = posicion + 40;
			// Controlamos el final del fichero
			if (depAle.getFilePointer() == depAle.length())
				break;
		}

		depAle.close();

		
		

	}

}
