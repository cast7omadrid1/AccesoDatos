import java.io.*;
import java.util.*;

public class Ejer2 {

	/**
	 * Aaron Castro Beatriz Domínguez
	 */
	public static void main(String[] args) throws IOException {

		Scanner teclado = new Scanner(System.in);
		//departament =new departamento;
		departamento departament = new departamento();
		//departamento departament;
		int dBorrar, pos = 0, numDep = 0;
		String nomE = "", localE = "";
		boolean error = true;
		
		
		// pedimos dep a borrar
		System.out.println("Indica el id del departamento a modificar: ");
		dBorrar = teclado.nextInt();

		// creamos el archivo
		File dep = new File("c:\\datos\\departamentos.dat");

		// declaramos el fichero de acceso aleatorio, escritura+lectura
		RandomAccessFile depAle = new RandomAccessFile(dep, "rw");

		// leemos archivo
		// Recorrer el fichero para visualizar los registros guardados
		// variables temporales
		int posicion = 0;

		for (int i=0;i<depAle.length();i++) { // recorro el fichero aleatorio
						
			depAle.seek(posicion); // nos posicionamos en el inicio del fichero
			
			departament.Leer(depAle);
			
		
			if (departament.getNumDep() == dBorrar) {
				pos = posicion;
				
				error = false;
				break;
				
			}

			// Nos colocamos en el siguiente registro, sumando 36 bytes al
			// puntero
			posicion = posicion + 40;
			// Controlamos el final del fichero
			if (depAle.getFilePointer() == depAle.length())
				break;
		}
		// ver si existe

		// si no existe el departamento salta un error
		if (error != false)
			System.out.println("ERROR: Ese departamento no existe");
		else {

			// pedimos nueva info
			teclado.nextLine();
			String nomNuevo="";
			System.out.println("Indique el nuevo nombre del departamento: ");
			nomNuevo = teclado.nextLine();

			
			// escribimos en archivo
			
			//definimos la clase
			departament.setNom(nomNuevo);
			
			
			StringBuffer buffer = null;
//
			depAle.seek(pos); // Colocar el puntero
			departament.write(depAle);
			

			// mostramos
			departament.mostrar();
	
		}
		teclado.close();
		depAle.close();
	}

}

