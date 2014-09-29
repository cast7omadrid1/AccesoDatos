//============================================================================
// Name        : Trat_fich1.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <fstream>//Biblioteca para el manejo de ficheros
#include <cstdlib>//Biblioteca estándar
#include <string.h>
#include <stdio.h>      /* printf, scanf, puts, NULL */
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */


using namespace std;


struct mialumno{
	int clase;//numero de registro donde almacena
	char apellido[25];//apellidos del alumno
	float nota1;//nota primera eval
	float nota2;//idem segunda
	float nota3;//idem tercera
};

int main() {

	mialumno alumno;//variable de estructura
	int elegir;//para menu
	int numReg;//para buscar el numero de registro





	//creación del string de nombres
	string nombres[5]={"juan","Miguel","María","Andrés","Julia"};

	cout<<"\tMenu"<<endl;
		cout<<"-----------------------"<<endl;

		cout<<"2-BUSCAR"<<endl;


		//creación del archivo "ejercicio1.dat"
	ofstream alumnos;//salida
	alumnos.open("c:\\datos\\ejercicio1.txt",ios::out|ios::binary);//salida,abrir en binario

	if(alumnos.bad()){
		cout<<"Problemas al crear el fichero"<<endl;
	}else{
		for(int i=0;i<10;i++){
		alumno.clase=i;
		strcpy(alumno.apellido,nombres[rand()%4].c_str());
		alumno.nota1=rand()%10;
		alumno.nota2=rand()%10;
		alumno.nota3=rand()%10;

	    cout<< alumno.clase<<endl;
	    cout<< alumno.apellido<<endl;
	    cout<< alumno.nota1 <<endl;
		cout<< alumno.nota2<<endl;
		cout<< alumno.nota3 <<endl;

		//escribirlo en el fichero
		alumnos.write(reinterpret_cast<char*>(&alumno),sizeof(mialumno));

	}
		alumnos.close();
	}
	cin>>elegir;

	if(elegir == 2){

			cout<<"Indique el número de registro del alumno que desee buscar: ";
			cin>>numReg;


	//Abrir un stream de entrada para leer el fichero
	ifstream alumnos;
	alumnos.open("c:\\datos\\ejercicio1.txt",ios::out|ios::binary);//entrada

	alumnos.seekg((numReg)*sizeof(mialumno),ios::beg);
	//de forma secuencial no hace falta posicionarse

	//leer en el fichero
	alumnos.read(reinterpret_cast<char*>(&alumno),sizeof(mialumno));


	 	 	 	 cout<< "ALUMNOS:" <<endl;

				 cout<< alumno.clase<<endl;
				 cout<< alumno.apellido<<endl;
				 cout<< alumno.nota1 <<endl;
				 cout<< alumno.nota2<<endl;
				 cout<< alumno.nota3 <<endl;
			}
			alumnos.close();
			 system("pause");

	return 0;
}
