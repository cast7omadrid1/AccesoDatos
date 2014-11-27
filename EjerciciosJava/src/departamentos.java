

import java.util.*;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class departamentos implements Serializable{

	private int numDep;
	private String nom;
	private String localidad;
	

	public departamentos (int numDep,String nom, String localidad){
		
		this.numDep=numDep;
		this.nom=nom;
		this.localidad=localidad;
	}//fin this
	
	public departamentos() {
		
	}

	//davos valor
	public void setnumDep(int NumDep){
		numDep=NumDep;	
	}
	
	public int getnumDep(){
		return numDep;
	}
	
	public void setnom(String Nom){
		nom=Nom;	
	}
	
	public String getnom(){
		return nom;
	}
	
	public void setlocalidad(String Localidad){
		localidad=Localidad;	
	}
	
	public String getlocalidad(){
		return localidad;
	}
	
	public void write(RandomAccessFile depAle) throws IOException{
		
		depAle.writeInt(numDep);
		//cfreamos el buffer
		StringBuffer sb;
		
		if(nom!=null){
			//escribimos en el buffer
			sb=new StringBuffer(nom);
		}else{
			
		sb=new StringBuffer();

		}//fin if
		
		//asignamos 8 caracteres al nombre
		sb.setLength(8);
		
		//escribimos en el archivo
		depAle.writeChars(sb.toString());
		
		
		if (localidad != null)
		      sb = new StringBuffer(localidad);
		    else
		      sb = new StringBuffer();
		    sb.setLength(10);
		    depAle.writeChars(sb.toString());

	}//fin write
	
	public void  leer(RandomAccessFile depAle)throws IOException {
		
		//leemos el numero de departamento
		numDep=depAle.readInt();
		
		char[]temp=new char[8];
		//leemos los nombres
		for(int i=0;i<temp.length;i++){
			temp[i]=depAle.readChar();
			nom=new String(temp);
		}
		
		temp=new char[10];
		//leemos las localidades
	    for (int i = 0; i < temp.length; i++){
		      temp[i] = depAle.readChar();
		    localidad = new String(temp);
	    }
	}

	public void mostrar(){
		
		//mostramos la información por pantalla
		
		System.out.println("Información de los departamentos: ");
		System.out.println("Número: " + numDep + "\nNombre: " + nom
				+ "\nlocalidad: " + localidad);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
}//fin class
