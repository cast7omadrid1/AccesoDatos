import java.util.*;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class empleados implements Serializable {

	private String dni;
	private String nom;
	private String ap;
	// private float sueldo;
	private float base;
	private float complemento;
	private float irpf;

	public empleados(String dni, String nom, String ap, float base,
			float complemento, float irfp) {
		this.dni = dni;
		this.nom = nom;
		this.ap = ap;
		this.base = base;
		this.complemento = complemento;
		this.irpf = irpf;

	}

	public empleados() {

	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDni() {
		return dni;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setAp(String ap) {
		this.ap = ap;
	}

	public String getAp() {
		return ap;
	}

	public void setBase(float base) {
		this.base = base;
	}

	public float getBase() {
		return base;
	}

	public void setComp(float complemento) {
		this.complemento = complemento;
	}

	public float getComp() {
		return complemento;
	}

	public void setIrpf(float irpf) {
		this.irpf = irpf;
	}

	public float getIrpf() {
		return irpf;
	}

	public void Leer(RandomAccessFile depAle) throws IOException {

		// leemos dni

		// creamos buffer
		char[] temp = new char[10];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = depAle.readChar();
			dni = new String(temp);
		}
		// leemos nombre
		temp = new char[20];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = depAle.readChar();
			nom = new String(temp);
		}
		// leemos apellido
		temp = new char[20];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = depAle.readChar();
			ap = new String(temp);
		}

		// leemos datos de sueldo
		base = depAle.readFloat();
		complemento = depAle.readFloat();
		irpf = depAle.readFloat();

	}// fin leer

	public void write(RandomAccessFile depAle) throws IOException {

		// creamos el buffer
		StringBuffer sb;

		// escrbimos dni
		if (dni != null)
			sb = new StringBuffer(dni);
		else
			sb = new StringBuffer();
		sb.setLength(10);
		// escribimos y pasamos el buffer a string
		depAle.writeChars(sb.toString());

		// escrbimos nombre
		if (nom != null)
			sb = new StringBuffer(nom);
		else
			sb = new StringBuffer();
		sb.setLength(20);
		// escribimos y pasamos el buffer a string
		depAle.writeChars(sb.toString());
		
		// escrbimos apellido
		if (ap!= null)
			sb = new StringBuffer(ap);
		else
			sb = new StringBuffer();
		sb.setLength(20);
		// escribimos y pasamos el buffer a string
		depAle.writeChars(sb.toString());

		
	    //escribimos datos sueldo
	    depAle.writeFloat(base);
	    depAle.writeFloat(complemento);
	    depAle.writeFloat(irpf);


	}//fin write
	
	public void mostrar(){
		
		System.out.println("Informacion de los empleados");
		System.out.println("\nNombre: " + nom
				+ "\nApellido: " + ap +"\nBase: "+base+"\nComplemento: "+complemento+"\nIrpf: "+irpf+"\n");

		
	}//fin mostrar
	
	int tamano() {
		//lo del (3*4) es pq hay 3 float que ocupan 4 bytes cada uno
		//(10+20+20) tamaño que ocupan los string
	    return 2 * (10 + 20 + 20) + (3 * 4);
	}

	
	

}
