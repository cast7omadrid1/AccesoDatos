//============================================================================
// Name        : Ejer2.cpp
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

#include <map>

using namespace std;

struct mialumno{
	int clase;//numero de registro donde almacena
	char apellido[25];//apellidos del alumno
	float nota1;//nota primera eval
	float nota2;//idem segunda
	float nota3;//idem tercera
};


typedef float media;//en la primera parte del mapa se almacenan las medias
typedef int clase;//en la segunda parte del mapa se almacena el registro de los alumnos

//creamos el multimapa
//Es multimapa puesto que las medias se pueden repetir
typedef multimap<media,clase,greater<media> >mapa;

int main() {

	mialumno alumno;//variable de estructura
	mapa m;
	multimap<media,clase>::iterator t;
	//int elegir;//para menu
	//int numReg;//para buscar el numero de registro


	//creación del string de nombres
	//string nombres[5]={"juan","Miguel","María","Andrés","Julia"};
/*
	cout<<"\tMenu"<<endl;
		cout<<"-----------------------"<<endl;

		cout<<"2-BUSCAR"<<endl;
*/

		//creación del archivo "ejercicio1.txt"

	ifstream alumnos("c:\\datos\\ejercicio1.txt",ios::in|ios::binary);//entrada(lectura),abrir en binario

	alumnos.read(reinterpret_cast<char*>(&alumno),sizeof(mialumno));//leemos el primer registro



	  while(!alumnos.eof()){

	int med;
	med=(float)((alumno.nota1+alumno.nota2+alumno.nota3)/3);//creamos las notas medias que se almacenan en una variable

	//insertamos datos en el multimapa
	m.insert(pair<media,clase>(med,alumno.clase));

	//leer en el fichero
	alumnos.read(reinterpret_cast<char*>(&alumno),sizeof(mialumno));//lee el segundo,tercero hasta llegar al final
	  }
	  alumnos.close();//cerramos el archivo

	  //abrimos de nuevo el archivo pero la variable con distinto nombre
	 ifstream alumnos1("c:\\datos\\ejercicio1.txt",ios::in|ios::binary);

	 //recorremos el archivo gracias al iterador
	for(t=m.begin();t!=m.end();t++){

		//mostramos la nota media
		cout<<"Media: "<<(*t).first<<" -- ";
		//nos posicionamos en la segunda parte del archivo (registro)
		//desde el princiio del archivo(beg)
		alumnos1.seekg((*t).second*sizeof(mialumno),ios::beg);
		//volvemos a leer la información del archivo
		alumnos1.read(reinterpret_cast<char*>(&alumno),sizeof(mialumno));
		cout<< "ALUMNOS:" <<endl;

			 cout<< alumno.clase<<endl;
			 cout<< alumno.apellido<<endl;
			 cout<< alumno.nota1 <<endl;
			 cout<< alumno.nota2<<endl;
			 cout<< alumno.nota3 <<endl;
	}
			alumnos1.close();
			 system("pause");

	return 0;
}
