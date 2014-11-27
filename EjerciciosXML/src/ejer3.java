import java.io.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import java.util.*;

public class ejer3 {

	/**
	 * @param args
	 * @throws IOException
	 *             , ParserConfigurationException
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 */
	public static void main(String[] args) throws IOException,
			ParserConfigurationException, TransformerFactoryConfigurationError,
			TransformerException {
		// TODO Auto-generated method stub

		Scanner teclado = new Scanner(System.in);

		int op;
		String nombre, apellidos, dni;
		int base, compl, irpf;

		// creamos el documento
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();

		doc.setXmlVersion("1.0");
		Element raiz = doc.createElement("empleados");
		doc.appendChild(raiz);

		do {
			System.out.println("1-Introducir datos");
			System.out.println("2-Salir y guardar");
			op = teclado.nextInt();
			teclado.nextLine();// salto de linea

			switch (op) {

			case 1:

				try {

					System.out.println("Introduce el dni");
					dni = teclado.nextLine();

					// creamos el nodo empleado y su atributo dni
					Element emple = doc.createElement("empleado");
					emple.setAttribute("dni", dni);

					System.out.println("Introduce el Nombre");
					nombre = teclado.nextLine();

					// Crear el nodo del nombre con su contenido y
					// posteriormente lo añadimos
					Element nom = doc.createElement("nombre");
					Text textnom = doc.createTextNode(nombre);
					emple.appendChild(nom);
					nom.appendChild(textnom);

					System.out.println("Introduce el Apellido");
					apellidos = teclado.nextLine();

					// Crear el nodo del nombre con su contenido y
					// posteriormente lo añadimos
					Element ape = doc.createElement("apellido");
					Text textape = doc.createTextNode(apellidos);
					emple.appendChild(ape);
					ape.appendChild(textape);

					// creamos el nodo sueldo

					// Crear el nodo del sueldo con su contenido y
					// posteriormente lo añadimos
					Element suel = doc.createElement("sueldo");
					emple.appendChild(suel);

					System.out.println("Introduce el sueldo base");
					base = teclado.nextInt();

					// Crear el nodo del nombre con su contenido y
					// posteriormente lo añadimos
					Element bas = doc.createElement("base");
					Text textbas = doc.createTextNode(Integer.toString(base));
					suel.appendChild(bas);
					bas.appendChild(textbas);

					System.out.println("Introduce los complementos");
					compl = teclado.nextInt();

					// Crear el nodo del nombre con su contenido y
					// posteriormente lo añadimos
					Element com = doc.createElement("complementos");
					Text textcom = doc.createTextNode(Integer.toString(compl));
					suel.appendChild(com);
					com.appendChild(textcom);

					System.out.println("Introduce el irpf");
					irpf = teclado.nextInt();

					// Crear el nodo del nombre con su contenido y
					// posteriormente lo añadimos
					Element ir = doc.createElement("irpf");
					Text textir = doc.createTextNode(Integer.toString(compl));
					suel.appendChild(ir);
					ir.appendChild(textir);

					// añadimosel nodo al documento
					doc.getDocumentElement().appendChild(emple);

				} catch (Exception e) {
					e.printStackTrace();
				}

				break;

			case 2:

				Source source = new DOMSource(doc);
				Result result = new StreamResult(new File(
						"c:\\datos\\empleados1.xml"));

				// Transformación del Document al fichero
				Transformer trans = TransformerFactory.newInstance()
						.newTransformer();
				trans.transform(source, result);

				break;

			}

		} while (op != 2);

	}

}
