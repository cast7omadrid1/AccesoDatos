import java.io.*;
import java.util.Scanner;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.SAXException;

public class Ejer4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException,
			TransformerException {
		// TODO Auto-generated method stub

		Scanner teclado = new Scanner(System.in);

		// creamos el archivo
		File dep = new File("c:\\datos\\empleados2.dat");

		// declaramos el fichero de acceso aleatorio, escritura+lectura
		RandomAccessFile depAle = new RandomAccessFile(dep, "rw");

		// creamos un objeto de la clase
		empleados empleado = new empleados();

		// no se que coño es!!!!!!!
		InputStreamReader entrada = new InputStreamReader(System.in);
		BufferedReader teclado1 = new BufferedReader(entrada);

		try {

			// Fabricar el documento
			DocumentBuilderFactory xml = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = xml.newDocumentBuilder();
			Document doc = db.parse("c:\\datos\\empleados.xml");

			// Obtener el nodo raíz del fichero "empleados"
			Element raiz = doc.getDocumentElement();

			// Obtener la lista de nodos que tienen la etiqueta "empleado"
			NodeList listaEmpleados = raiz.getElementsByTagName("empleado");
			NodeList listaSueldo = raiz.getElementsByTagName("sueldo");// hacer
																		// lista
																		// para
																		// cada
																		// hijo

			// StringBuffer buffer = null; // buffer para almacenar empleado

			// Recorrer la lista de los nodos encontrados
			for (int i = 0; i < listaEmpleados.getLength(); i++) {

				// Obtener de la lista un cliente tras otro
				Node nodoEmpleado = listaEmpleados.item(i);
				Node sueldo = listaSueldo.item(i);// recorremos los items dentro
													// de sueldo
				System.out.println("\nEmpleado: " + i + 1);
				System.out.println("------------");

				Node atr = nodoEmpleado.getAttributes().getNamedItem("dni");
				System.out.println(atr.getNodeName() + ": "
						+ atr.getNodeValue());

				// obtenemos el valor del atributo dni
				empleado.setDni(atr.getNodeValue());

				// Obtener los elementos que compone el nodo empleado
				NodeList nodoElementos = nodoEmpleado.getChildNodes();

				// recorremos los elementos del nodoElementos
				for (int j = 0; j < nodoElementos.getLength(); j++) {

					// Obtener de la lista un empleado tras otro
					Node NombreApellidos = nodoElementos.item(j);

					if (NombreApellidos.getNodeType() == Node.ELEMENT_NODE) {
						// obtener el valor del contenido
						Node datoNom = NombreApellidos.getFirstChild();

						if (datoNom != null
								&& datoNom.getNodeType() == Node.TEXT_NODE) {
							System.out.println(NombreApellidos.getNodeName()
									+ ":" + datoNom.getNodeValue());
							// si el nodo encontrado es igual a nombre,
							// obtenemos el
							// valor
							if (NombreApellidos.getNodeName() == "nombre") {

								empleado.setNom(datoNom.getNodeValue());
								// si el nodo encontrado es igual a apellidos,
								// obtenemos
								// el valor
							} else if (NombreApellidos.getNodeName() == "apellidos") {
								empleado.setAp(datoNom.getNodeValue());
							}// cierre if_3
						}// if_2
					}// if_1
				}// for
					// listamos los nodos hijos de sueldo
				NodeList nodosueldos = sueldo.getChildNodes();

				// recorremos los elementos del nodoElementos
				for (int r = 0; r < nodosueldos.getLength(); r++) {

					// obtener de la lista un elemeno tras otro de sueldo
					Node datoSueldo = nodosueldos.item(r);

					if (datoSueldo.getNodeType() == Node.ELEMENT_NODE) {

						// Obtener el valor contenido en el elemento
						Node datoElemento = datoSueldo.getFirstChild();

						if (datoElemento != null
								&& datoElemento.getNodeType() == Node.TEXT_NODE) {

							System.out.println(datoSueldo.getNodeName() + ": "
									+ datoElemento.getNodeValue());

							if (datoSueldo.getNodeName() == "base")
								empleado.setBase(Float.parseFloat(datoElemento
										.getNodeValue()));
							// si el nodo encontrado es igual a apellidos,
							// obtenemos el valor
							else if (datoSueldo.getNodeName() == "complemento")
								empleado.setComp(Float
										.parseFloat(datoElemento.getNodeValue()));

							else if (datoSueldo.getNodeName() == "irpf")
								empleado.setIrpf(Float.parseFloat(datoElemento
										.getNodeValue()));
						}// if_2
					}// if_1

				}// segundo for

				// escribimos en el archivo
				empleado.write(depAle);

			}// primer for

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

		System.out.println("Iniciando lectura fichero Java...");
		int numeroRegistros = (int) (dep.length() / empleado.tamano());

		depAle.seek(0);

		for (int x = 0; x < numeroRegistros; x++) {
			empleado.Leer(depAle);

			System.out.println("Dni: " + empleado.getDni());
			System.out.println("Nombre : " + empleado.getNom());
			System.out.println("Apellidos: " + empleado.getAp());
			System.out.println("Base: " + empleado.getBase());
			System.out.println("Complementos: " + empleado.getComp());
			System.out.println("IRPF: " + empleado.getIrpf());
			System.out.println();

		}

	}

}

