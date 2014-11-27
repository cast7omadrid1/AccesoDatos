import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class Ejer1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		try {

			/*
			 * Crear una instancia de DocumentBuilderFactory para construir el
			 * parser. Se debe encerrarse entre try-catch porque genera la
			 * excepción ParserConfigurationException
			 */

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			// creamos la versión y el elemento raiz
			doc.setXmlVersion("1.0");
			Element raiz = doc.createElement("departamentos");
			doc.appendChild(raiz);
			
			//creamos el objeto
			departamento1 departament = new departamento1();
			
			// creamos el fichero que tenemos que leer
			File dep = new File("c:\\datos\\departamentos.dat");
			
			//declaramos el fichero como a leatorio para poder leero (solo será de lectura)
			
			RandomAccessFile depAle=new RandomAccessFile(dep, "r");
			
			//recorremos el archivo para poder leerlo
			int posicion=0;
			for(int i=0;i<depAle.length();i++){
				
				//nos posicionamos en el principio del fichero
				depAle.seek(posicion);
				//leemos los datos del archivo
				departament.Leer(depAle);
				
				//creamos el siguiente nodo 
				Element nodo=doc.createElement("empleado");
				//le añadimos un atributo  (id) a dicho nodo 
				//ese id corresponde al numero de depatamento
				nodo.setAttribute("id", Integer.toString(departament.getNumDep()));
				
				
				//creamos el elemento nombe y le añadimos el nombre que consegumos del fichero creado
				Element nombre=doc.createElement("Nombre");
				Text textnom=doc.createTextNode(departament.getNom());
				nodo.appendChild(nombre);
				nombre.appendChild(textnom);
				
				//creamos el elemento nombe y le añadimos el nombre que consegumos del fichero creado
				Element localidad=doc.createElement("Localidad");
				Text textlocal=doc.createTextNode(departament.getLocalidad());
				nodo.appendChild(nombre);
				nombre.appendChild(textlocal);
				
				//añadimos los nodos al documento
				doc.getDocumentElement().appendChild(nodo);
				
			}//cierre for
			
			/*
			 * El Documento no tiene formato y está en memoria. En necesario darle un formato
			 * y guardarlo en un fichero de texto, del tipo XML.
			 */
			Source source =new DOMSource(doc);
			Result result=new StreamResult(new File ("c:\\datos\\empleados.xml"));

			//Transformamos el documento al fichero
			Transformer trans=TransformerFactory.newInstance().newTransformer();
			trans.transform(source, result);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
