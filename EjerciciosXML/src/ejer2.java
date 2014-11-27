import java.io.*;
import java.util.Scanner;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.SAXException;
public class ejer2 {

	/**
	 * @param args
	 * @throws TransformerFactoryConfigurationError 
	 * @throws TransformerException 
	 */
	public static void main(String[] args) throws IOException, TransformerFactoryConfigurationError, TransformerException {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);
		try {
			
			//creamos el documento que vamos a leer 
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse("c:\\datos\\alumnos.xml");

			//Obtener el nodo raíz del fichero, en este caso alumnos
			Element raiz=doc.getDocumentElement();
/*
			//Obtener la lista de nodos que tienen la etiqueta "alumno"
			NodeList listaAlumnos=raiz.getElementsByTagName("alumno");

			//recorremos la lista de nodos encontrados
			//getLength()-->conseguir la longuitud
			for(int i=0;i<listaAlumnos.getLength();i++){
				
				//obtenemos de la lista un cliente tras otro
				Node alumno=listaAlumnos.item(i);
				//mostramos los datos del alumno
				System.out.println("\nAlumno: "+i);
				System.out.println("------------");

				//obtenemos los elementos que comonen en nodo cliente
				NodeList datosAlumnos=alumno.getChildNodes();
				
				for(int j=0;j<datosAlumnos.getLength();j++){
					//sacamos la lista de un elemento tras otro
					Node alumno1=datosAlumnos.item(i);
					
					//comprobar que el elemento alumno1 es del tipo elemento
					if (alumno1.getNodeType()==Node.ELEMENT_NODE){
						
						//obtenemos el valor del contenido
						
						Node datoNom=alumno1.getFirstChild();
						
						
						System.out.println(alumno1.getNodeName() + ":"
								+ datoNom.getNodeValue());

					}//cierre if
					
				}//cierre for 2
				
				
			}//cierre for 1
			*/
			//pedimos la informacion por teclado
			String nombre;
			System.out.println("\nIntroduce Nombre");
			nombre=teclado.nextLine();
			
			String apellido;
			System.out.println("Introduce apellido");
			apellido=teclado.nextLine();
			
			//creamos el nodo
			Element alu=doc.createElement("alumno");
			
			//Creamos el nodo del nuevo nombre con su contenido
			Element nombre1 = doc.createElement("nombre");
			Text textnom = doc.createTextNode(nombre);
			alu.appendChild(nombre1);
			nombre1.appendChild(textnom);

			// Crear el nodo del apellido con su contenido y posteriormente lo
			// añadimos
			Element apellido1 = doc.createElement("apellidos");
			Text textape = doc.createTextNode(apellido);
			alu.appendChild(apellido1);
			apellido1.appendChild(textape);


			// añadir el nodo al Document
			doc.getDocumentElement().appendChild(alu);

			Source source = new DOMSource(doc);
			Result result = new StreamResult(new File("c:\\datos\\alumnos.xml"));

			// Transformación del Document al fichero
			Transformer trans = TransformerFactory.newInstance()
					.newTransformer();
			trans.transform(source, result);
			
		}catch (IOException ex){
			System.out.println("ERROR: no se ha podido leer el fichero "+ex.getMessage());
			ex.printStackTrace();
		}catch(ParserConfigurationException ex){
			System.out.println("ERROR: No se ha podido crear el generador de documentos XML "+ex.getMessage());
			ex.printStackTrace();
		}catch (SAXException ex){
			System.out.println("ERROR: el formato XML del fichero no es correcto "+ex.getMessage());
			ex.printStackTrace();
		}

		
	}

}
