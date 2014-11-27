//============================================================================
// Name        : ejer3_c+.cpp
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

struct personas {
	char nombre[80]; //nombre
	char apell1[80]; //primer apellido
	char apell2[80]; //segundo apellido
	int telefono; //teléfono

};

int main() {
	//srand(time(NULL));
	int elegir;
	int telf;
	personas persona;
	string nom, ape1, ape2;

	do {

		cout << "\tMenu" << endl;
		cout << "1-Insertar persona en TXT" << endl;
		cout << "2-Insertar persona en BIN" << endl;
		cout << "3-Listar en TXT" << endl;
		cout << "4-Listar en BIN" << endl;
		cout << "0-Salir" << endl;

		cin >> elegir;

		if (elegir == 1) {

			ofstream insertar;
			insertar.open("c:\\datos\\ejercicio3.txt", ios::out | ios::app);

			cout << "Introduce Nombre" << endl;
			cin >> nom;

			strcpy(persona.nombre, nom.c_str());

			cout << "Introduce Primer Apellido" << endl;
			cin >> ape1;

			strcpy(persona.apell1, ape1.c_str());

			cout << "Introduce Segundo Apellido" << endl;
			cin >> ape2;

			strcpy(persona.apell2, ape2.c_str());

			cout << "Introduce Número de telefono" << endl;
			cin >> telf;

			persona.telefono = telf;

			//escribimos en el fcihero de texto
			insertar << persona.nombre << "" << " " << persona.apell1 << " "
					<< persona.apell2 << " " << persona.telefono << endl;

			//cerramos el fichero de texto
			insertar.close();

			//fin opcion1
		} else if (elegir == 2) {

			ofstream insertar1;
			insertar1.open("c:\\datos\\ejerccio3_bin.txt", ios::binary);

			cout << "Introduce Nombre" << endl;
			cin >> nom;

			strcpy(persona.nombre, nom.c_str());

			cout << "Introduce Primer Apellido" << endl;
			cin >> ape1;

			strcpy(persona.apell1, ape1.c_str());

			cout << "Introduce Segundo Apellido" << endl;
			cin >> ape2;

			strcpy(persona.apell2, ape2.c_str());

			cout << "Introduce Número de telefono" << endl;
			cin >> telf;

			persona.telefono = telf;

			//insertamos los datos en el archivo binario
			insertar1.write(reinterpret_cast<char*>(&persona),
					sizeof(personas));

			//cerramos el archivo
			insertar1.close();

		} else if (elegir == 3) {

			//abrimos el archivo deteto para listarlo
			ifstream listar;
			listar.open("c:\\datos\\ejercicio3.txt", ios::in);

			//leemos los datos del archivo

			listar >> persona.nombre;
			listar >> persona.apell1;
			listar >> persona.apell2;
			listar >> persona.telefono;

			//si no se llega al final del fichero
			while (!listar.eof()) {
				//mostramos la información
				cout << "Nombre: ";
				cout << persona.nombre << endl;
				cout << "1er apellido: ";
				cout << persona.apell1 << endl;
				cout << "2do apellido: ";
				cout << persona.apell2 << endl;
				cout << "Telefono: ";
				cout << persona.telefono << endl;

				//volvemos a leer
				listar >> persona.nombre;
				listar >> persona.apell1;
				listar >> persona.apell2;
				listar >> persona.telefono;

			}
			listar.close();

		} else if (elegir == 4) {

			ifstream listar1;
			listar1.open("c:\\datos\\ejerccio3_bin.txt", ios::in | ios::binary);

			listar1.read(reinterpret_cast<char*>(&persona), sizeof(personas));

			while (!listar1.eof()) {

				//mostramos la información
				cout << "Nombre: ";
				cout << persona.nombre << endl;
				cout << "1er apellido: ";
				cout << persona.apell1 << endl;
				cout << "2do apellido: ";
				cout << persona.apell2 << endl;
				cout << "Telefono: ";
				cout << persona.telefono << endl;

				//leemos el primer registro
				listar1.read(reinterpret_cast<char*>(&persona),
						sizeof(personas));

			}
			listar1.close();

		} else {
			cout << "El programa ha finalizado" << endl;
		}

	} while (elegir != 0);

	return 0;
}
