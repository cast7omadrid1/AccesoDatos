//============================================================================
// Name        : Ejer4.cpp
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
#include <vector>
#include <map>

using namespace std;

//definimos mapa
typedef string dni;
typedef int reg;
typedef map<dni, reg, less<dni> > mapa;

//declaramos los archivos
/*
 FILE*pclave;
 FILE*datos;
 FILE*indice;
 FILE*lapila;
 */
//definimos la estructura
typedef struct ficha {
	char dni[10];
	char nombre[25];
	char apellidos[25];
} persona;

#include "ejer4.h"

int main() {

	//persona tmp;
	int elegir;

	mapa m;

	vector<int> v; //definimos el vector

	//variables
	///int a;
	//int b;

	crear_archivos();

	do {
		cout << "\tMenu" << endl;
		cout << "-----------------------" << endl;
		cout << "1-Altas" << endl;
		cout << "2-Bajas" << endl;
		cout << "3-Listado" << endl;
		cout << "0.Salir" << endl;
		cin >> elegir;
		if (elegir == 1) {

			altas(m, v);

		}
		if (elegir == 2) {

			bajas(m, v);
		}
		if (elegir == 3) {
			listar();

		}

	} while (elegir != 0);

	return 0;
}
