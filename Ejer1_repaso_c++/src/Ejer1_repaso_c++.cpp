//============================================================================
// Name        : Ejer1_repaso_c++.cpp
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

//definimos la estructura de datos
typedef struct ficha {
	char dni[10];
	char nombre[25];
	char apellidos[25];
} persona;

//definimos mapa
typedef string dni;
typedef int reg;

typedef map<dni, reg, less<dni> > mapa;

#include "ejer4.h"

int main() {
//definimos la variable para usar el mapa
	mapa m;

//definimos el vetor
	vector<int> v;

//variable para las diferentes opciones del menu
	int elegir;

	do {

		cout << "\nMenu" << endl;
		cout << "-------------" << endl;
		cout << "1-Altas" << endl;
		cout << "2-Bajas" << endl;
		cout << "3-Listar" << endl;
		cout << "0-Salir" << endl;
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
