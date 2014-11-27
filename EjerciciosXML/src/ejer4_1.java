import java.io.*;
import java.util.Scanner;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.SAXException;

public class ejer4_1 {

	/**
	 * @param args
	 * @throws TransformerException
	 */
	public static void main(String[] args) throws IOException,
			TransformerException {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);

		// creamos el archivo
		File dep = new File("c:\\datos\\empleados.dat");

		// declaramos el fichero de acceso aleatorio, escritura+lectura
		RandomAccessFile depAle = new RandomAccessFile(dep, "rw");

		// creamos un objeto de la clase
		empleados empleado = new empleados();

		try {
			// Fabricar el documento
			DocumentBuilderFactory xml = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = xml.newDocumentBuilder();
			Document doc = db.parse("c:\\datos\\empleados.xml");

			// Obtener el nodo raíz del fichero
			Element raiz = doc.getDocumentElement();

			// Obtener la lista de nodos que tienen la etiqueta "cliente"
			NodeList listaAlumnos = raiz.getElementsByTagName("alumno");

			StringBuffer buffer = null; // buffer para almacenar apellido

			// Recorrer la lista de los nodos encontrados
			for (int i = 0; i < listaAlumnos.getLength(); i++) {

				// Obtener de la lista un cliente tras otro
				Node Alumno = listaAlumnos.item(i);
				System.out.println("\nAlumno: " + i + 1);
				System.out.println("------------");

				// Obtener los elementos que compone el nodo cliente
				NodeList datosAlumno = Alumno.getChildNodes();

				for (int j = 0; j < datosAlumno.getLength(); j++) {

					// Obtener de la lista un cliente tras otro
					Node NombreApellidos = datosAlumno.item(j);

					if (NombreApellidos.getNodeType() == Node.ELEMENT_NODE) {
						// obtener el valor del contenido
						Node datoNom = NombreApellidos.getFirstChild();

						// System.out.println(NombreApellidos.getNodeName() +
						// ":"
						// + datoNom.getNodeValue());

						if (NombreApellidos.getNodeName() == "nombre")
							empleado.setNom(datoNom.getNodeValue());

						else if (NombreApellidos.getNodeName() == "apellidos")
							empleado.setAp(datoNom.getNodeValue());

					}

				}
				// escribimos la información en el archivo
				empleado.write(depAle);
				// mostramos la información del alumno
				System.out.println("\tNOMBRE: " + empleado.getNom());
				System.out.println("\tAPELLIDO: " + empleado.getAp());

			}// fin for

			// cerramos el archivo
			depAle.close();

		} catch (IOException ex) {
			System.out.println("ERROR: no se ha podido leer el fichero "
					+ ex.getMessage());
			ex.printStackTrace();
		} catch (ParserConfigurationException ex) {
			System.out
					.println("ERROR: No se ha podido crear el generador de documentos XML "
							+ ex.getMessage());
			ex.printStackTrace();
		} catch (SAXException ex) {
			System.out
					.println("ERROR: el formato XML del fichero no es correcto "
							+ ex.getMessage());
			ex.printStackTrace();
		}

	}// fin main
}// fin class

