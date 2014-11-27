
import java.io.*;
import java.util.*;

public class Ejer1Binario {

	/**
	 * Aaron Castro Beatiz Domínguez
	 */

	public static void main(String[] args) throws IOException,
			ClassNotFoundException {

		departamento depart;
		

		File dep = new File("c:\\datos\\departamento.dat");

		// flujos de entrada y salida
		//escribir
		FileOutputStream depOut = new FileOutputStream(dep);
		//Leer
FileInputStream depIn = new FileInputStream(dep);

		// Stream de caracteres  de salida y entrada
		ObjectOutputStream datOut = new ObjectOutputStream(depOut);
		ObjectInputStream datIn = new ObjectInputStream(depIn);

		

		String nombres[] = { "Juan", "Ana", "Carlos", "Alicia", "Carmen",
				"Jesús" };
		int numDep[] = { 1, 2, 3, 4, 5, 6 };

		String localidad[] = { "Zaragoza", "Huesca", "Teruel", "Zaragoza",
				"Huesca", "Teruel"};
		// escribir
		for (int i = 0; i < numDep.length; i++) { // recorro los arrays
			// creo el objeto persona
			depart = new departamento(numDep[i], nombres[i], localidad[i]);
			// escribo la persona en el fichero
			datOut.writeObject(depart);
		}

		depOut.close();

		// Leer el contenido de los objetos en el fichero binario ----------------
		try {
			while (true) {
				// leer una pesona
				depart = (departamento) datIn.readObject();
				System.out.println("Nº departamento: " + depart.getNumDep()
						+ "\n Nombre: " + depart.getNom() + "\n Localidad: "
						+ depart.getLocalidad()+ "\n\n");
			}
		} catch (EOFException eo) {
		}

		depIn.close();

	}

}
