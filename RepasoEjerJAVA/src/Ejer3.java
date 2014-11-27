import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejer3{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Scanner teclado = new Scanner(System.in);
		//departament =new departamento;
		departamento departament = new departamento();
		// creamos el archivo
		File dep = new File("c:\\datos\\departamentos1.dat");

		// declaramos el fichero de acceso aleatorio, escritura+lectura
		RandomAccessFile depAle = new RandomAccessFile(dep, "rw");

		boolean error = true;
		int dBorrar;
		int posicion=0;
		int pos = 0;
		int numDep;
		String localE="";
		String nomNuevo="";
		// pedimos dep a borrar
		System.out.println("Indica el id del departamento a borrar: ");
		dBorrar = teclado.nextInt();

		for (int i=0;i<depAle.length();i++) { // recorro el fichero aleatorio
		
			depAle.seek(posicion); // nos posicionamos en el inicio del fichero
			
			departament.Leer(depAle);
			
			//comprobamos que el deaprtamento a borrar existe
			if (departament.getNumDep() == dBorrar) {
				pos = posicion;
				
				//numDep = departament.getNumDep();
				//nomE = departament.getNom();
				//localE = departament.getLocalidad();
				error = false;
				break;
				
			}
			// Nos colocamos en el siguiente registro, sumando 36 bytes al
			// puntero
			posicion = posicion + 40;
			// Controlamos el final del fichero
			if (depAle.getFilePointer() == depAle.length())
				break;
			}//for
		
	// si no existe el departamento salta un error
			if (error != false){
				System.out.println("ERROR: Ese departamento no existe");
			}else {
			
			numDep=0;
			localE="";
			nomNuevo="";
			
			departament.setNom(nomNuevo);
			departament.setLocalidad(localE);
			departament.setNumDep(numDep);
			StringBuffer buffer = null;

			depAle.seek(pos); // Colocar el puntero
			departament.write(depAle);
			

			// mostramos
			departament.mostrar();
			
			}//if

		
	}

}
