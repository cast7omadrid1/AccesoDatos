import java.util.*;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
public class departamento implements Serializable{
	
	private int numDep;
	private String nom;
	private String localidad;
	
	
public departamento(){
		
	}
	public departamento (int numDep, String nom, String localidad){
		this.numDep=numDep;
		this.nom=nom;
		this.localidad=localidad;

	}

	public int getNumDep() {
		return numDep;
	}

	public void setNumDep(int numDep) {
		this.numDep = numDep;
	}

	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	public  void Leer(RandomAccessFile depAle)
			throws IOException {
			numDep = depAle.readInt();
			char[] temp = new char[8];
		    for (int i = 0; i < temp.length; i++)
		      temp[i] = depAle.readChar();
		    nom = new String(temp);
		    temp = new char[10];
		    for (int i = 0; i < temp.length; i++)
		      temp[i] = depAle.readChar();
		    localidad = new String(temp);

	}

public void write(RandomAccessFile depAle) throws IOException {
	    
		depAle.writeInt(numDep);
		StringBuffer sb;
	    if (nom != null)
	    	//escribimos en el buffer
	      sb = new StringBuffer(nom);
	    else
	      sb = new StringBuffer();
	    //rellenamos..
	    sb.setLength(8);
	    //escribimos en el archivo
	    depAle.writeChars(sb.toString());

	    if (localidad != null)
	      sb = new StringBuffer(localidad);
	    else
	      sb = new StringBuffer();
	    sb.setLength(10);
	    depAle.writeChars(sb.toString());

	  }

public void mostrar(){
	
	//mostramos la información por pantalla
	
	System.out.println("Información del departamento modificado: ");
	System.out.println("Número: " + numDep + "\nNombre: " + nom
			+ "\nlocalidad: " + localidad);
	

	
}


}
