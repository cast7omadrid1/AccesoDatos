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
		departamentos departament = new departamentos();
		// departamento departament;
		int idDep;
		String nombre, localidad;
		int posicion = 0;
		boolean encontrado = false;

		// información a añadir
		System.out.println("Indica el id del departamento a añadir: ");
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

			departament.leer(depAle);

			if (departament.getnumDep() == 0) {

				// escribimos la nueva info en la clase
				departament.setnumDep(idDep);
				departament.setnom(nombre);
				departament.setlocalidad(localidad);
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
			departament.setnumDep(idDep);
			departament.setnom(nombre);
			departament.setlocalidad(localidad);

			// añadimos el registro al final del archivo
			depAle.seek(depAle.length());
			departament.write(depAle);

		}

		posicion = 0;
		// mostramos todo el fichero
		for (int i = 0; i < depAle.length(); i++) {
			
			depAle.seek(posicion); // nos posicionamos en el inicio del fichero
			departament.leer(depAle);
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

