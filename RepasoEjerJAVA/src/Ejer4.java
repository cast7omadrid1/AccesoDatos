import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class Ejer4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Scanner teclado = new Scanner(System.in);
		// departament =new departamento;
		departamento departament = new departamento();
		// departamento departament;
		int idDep;
		String nombre, localidad;
		int posicion = 0;
		boolean encontrado = false;

		// informaci�n a a�adir
		System.out.println("Indica el id del departamento a a�adir: ");
		idDep = teclado.nextInt();
		teclado.nextLine();
		System.out.println("Nombre: ");
		nombre = teclado.nextLine();
		System.out.println("Localidad: ");
		localidad = teclado.nextLine();

		File dep = new File("c:\\datos\\departamento2.dat");

		RandomAccessFile depAle = new RandomAccessFile(dep, "rw");

		for (int i = 0; i < depAle.length(); i++) {

			depAle.seek(posicion); // nos posicionamos en el inicio del fichero

			departament.Leer(depAle);

			if (departament.getNumDep() == 0) {

				// escribimos la nueva info en la clase
				departament.setNumDep(idDep);
				departament.setNom(nombre);
				departament.setLocalidad(localidad);
				depAle.seek(posicion); 
				// escribimos la info en el archivo
				departament.write(depAle);
				encontrado = true;
				// salimos
				break;
			}

			// Nos colocamos en el siguiente registro, sumando 36 bytes al
			// puntero
			posicion = posicion + 40;
			// Controlamos el final del fichero
			if (depAle.getFilePointer() == depAle.length())
				break;

		}

		
		if (encontrado == false) {
			// escribimos la nueva info en la clase
			departament.setNumDep(idDep);
			departament.setNom(nombre);
			departament.setLocalidad(localidad);

			// a�adimos el registro al final del archivo
			depAle.seek(depAle.length());
			departament.write(depAle);

		}

		posicion = 0;
		// mostramos todo el fichero
		for (int i = 0; i < depAle.length(); i++) {
			
			depAle.seek(posicion); // nos posicionamos en el inicio del fichero
			departament.Leer(depAle);
			departament.mostrar();
			posicion = posicion + 40;
			// Controlamos el final del fichero
			if (depAle.getFilePointer() == depAle.length())
				break;
		}

		// teclado.close();
		depAle.close();

	}

}

