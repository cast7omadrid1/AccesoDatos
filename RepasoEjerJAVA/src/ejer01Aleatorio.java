import java.io.*;
import java.util.*;

public class ejer01Aleatorio {
	/**
	 * Aaron Castro Beatriz Domínguez
	 */
	public static void main(String[] args) throws IOException {

		// creamos el archivo
		File dep = new File("c:\\datos\\departamentos1.dat");

		// declaramos el fichero de acceso aleatorio, escritura+lectura
		RandomAccessFile depAle = new RandomAccessFile(dep, "rw");

		String nombres[] = { "Juan", "Ana", "Carlos", "Alicia", "Carmen",
				"Jesús" };
		int numDep[] = { 1, 2, 3, 4, 5, 6 };

		String localidad[] = { "Zaragoza", "Huesca", "Teruel", "Zaragoza",
				"Huesca", "Teruel" };

		StringBuffer buffer = null; // buffer para almacenar apellido

		// escribimos 6 registros en el archivo
		for (int i = 0; i < localidad.length; i++) {
			depAle.writeInt(numDep[i]); // numero del departamento
			buffer = new StringBuffer(nombres[i]);
			buffer.setLength(8); // asigno 8 caracteres fijo al apellido
			depAle.writeChars(buffer.toString()); // inserto el apellido
			buffer = new StringBuffer(localidad[i]);
			buffer.setLength(10); // asigno 10 caracteres fijo al apellido
			depAle.writeChars(buffer.toString()); // inserto el apellido
		}

		// leemos del archivo

		// Recorrer el fichero para visualizar los registros guardados
		int depNum, posicion = 0;
		String nom, local;
		char nomA[] = new char[8];
		char locA[] = new char[10];

		for (;;) { // recorro el fichero aleatorio
			depAle.seek(posicion); // nos posicionamos en el inicio del fichero
			depNum = depAle.readInt(); // obtenemos el id del empleado
			nom = Leer(depAle, nomA);
			local = Leer(depAle, locA);

			System.out.println("NumDep: " + depNum + "\nNombre: " + nom
					+ "\nLocalidad " + local + "\n\n ");
			// Nos colocamos en el siguiente registro, sumando 36 bytes al
			// puntero
			posicion = posicion + 40;
			// Controlamos el final del fichero
			if (depAle.getFilePointer() == depAle.length())
				break;
		}

		depAle.close();

	}

	private static String Leer(RandomAccessFile ficale, char[] str)
			throws IOException {
		char aux;
		for (int i = 0; i < str.length; i++) {
			aux = ficale.readChar(); // recorrer uno a uno los caracteres del
										// apellido
			str[i] = aux; // guardo el caracter leído en el array
		}
		String apeString = new String(str); // convierto en String el array
		return apeString;
	}

}

