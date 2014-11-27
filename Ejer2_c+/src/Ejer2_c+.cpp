//============================================================================
// Name        : Ejer2_c+.cpp
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

struct mialumno {
	int clase; //numero de registro donde almacena
	char apellido[25]; //apellidos del alumno
	float nota1; //nota primera eval
	float nota2; //idem segunda
	float nota3; //idem tercera
};

typedef float media; //en la primera parte del mapa se almacenan las medias
typedef int clase; //en la segunda parte del mapa se almacena el registro de los alumnos

//creamos el multimapa
//Es multimapa puesto que las medias se pueden repetir
typedef multimap<media, clase, greater<media> > mapa;

int main() {
	mialumno alumno; //variable de estructura
	mapa m;
	multimap<media, clase>::iterator t;
	srand(time(NULL));

	//entrada(lectura),abrir en binario
	ifstream alumnos("c:\\datos\\ejercicio1.txt", ios::in | ios::binary);

	alumnos.read(reinterpret_cast<char*>(&alumno), sizeof(mialumno));

	//si no se llega al final del fichero
	while (!alumnos.eof()) {

		float med;

		//creamos la media de notas, que se almacenan en la variable media
		med = (alumno.nota1 + alumno.nota2 + alumno.nota3) / 3;

		m.insert(pair<media, clase>(med, alumno.clase));

		//leemos en el fichero
		alumnos.read(reinterpret_cast<char*>(&alumno), sizeof(mialumno));

		//de esta forma no hace falta leer la primera vez
		/*
		for(int i=0;i<10;i++){
				float med=0;

				alumnos.seekg((i)*sizeof(mialumno),ios::beg);
				//leemos en el fichero
				alumnos.read(reinterpret_cast<char*>(&alumno), sizeof(mialumno));
				//creamos la media de notas, que se almacenan en la variable media
				med = (alumno.nota1 + alumno.nota2 + alumno.nota3) / 3;

				m.insert(pair<media, clase>(med, i));
		*/



	} //fin while
	  //cerramos el archivo
	alumnos.close();

	//para mostrar la información recorremos el mapa de principio a fin gracias al iterador
	ifstream alumnos1("c:\\datos\\ejercicio1.txt", ios::in | ios::binary);

	for (t = m.begin(); t != m.end(); t++) {

		//mostramos la media de los alumnos
		cout << "MEDIA: " << (*t).first << "--"<<endl;;


		//para mostrar la segunda parte debemos de posicionarnos
		//nos posicionamos desde el principio del fichero(beg)

		alumnos1.seekg((*t).second * sizeof(mialumno), ios::beg);

		//ahora leemos la información del archivo

		alumnos1.read(reinterpret_cast<char*>(&alumno), sizeof(mialumno));

		cout << "ALUMNOS:" << endl;

		cout << alumno.clase << endl;
		cout << alumno.apellido << endl;
		cout << alumno.nota1 << endl;
		cout << alumno.nota2 << endl;
		cout << alumno.nota3 << endl;

	}		//fin for;

	alumnos1.close();
	system("pause");

	return 0;
}
