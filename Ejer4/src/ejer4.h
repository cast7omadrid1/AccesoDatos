/*
 * ejer4.h
 *
 *  Created on: 01/10/2014
 *      Author: Aaron
 */

#ifndef EJER4_H_
#define EJER4_H_

//si no existen los archivos los creamos


 void crear_archivos() {

//DATOS
  ifstream datos1;
  datos1.open("c:\\datos\\datos.txt",
 ios::in );
 if (!datos1){
 cout << "El archivo  datos no existe" << endl;

 datos1.close();

 ofstream datos1;

  datos1.open("c:\\datos\\datos.txt",ios::out);
  //indice1<<0;
 // indice1<<" ";
 // indice1<<0;
  datos1.close();
  datos1.open("c:\\datos\\indice.txt",ios::in);
  //INDICE
 }
 /////////////////////////////////////////////////////
   ifstream indice1;
 indice1.open("c:\\datos\\indice.txt",ios::in);
 if (!indice1){

	 cout << "El archivo   indice  no existe" << endl;


	  // cout << "El archivo indice existe" << endl;

 //int cero = 0;
 //indice << cero << " " << cero << " " << endl;

 indice1.close();

 ofstream indice1;

 indice1.open("c:\\datos\\indice.txt",ios::out);
 indice1<<0;
 indice1<<" ";
 indice1<<0;
 indice1.close();
 indice1.open("c:\\datos\\indice.txt",ios::in);
}



 //PCLAVE

 ////////////////////////////////////////////////////////
  ifstream pclave1;
  pclave1.open("c:\\datos\\pclave.txt", ios::in);
  if (!pclave1){

	  cout << "El archivo   pclave  no existe" << endl;


  pclave1.close();
  ofstream pclave1;
  pclave1.open("c:\\datos\\pclave.txt",ios::out);
  pclave1<<0;
  pclave1<<" ";
  pclave1<<0;
  pclave1.close();
  pclave1.open("c:\\datos\\indice.txt",ios::in);
  }



   //LAPILA

 ////////////////////////////////////////////////////////////
   ifstream lapila1;
    lapila1.open("c:\\datos\\lapila.txt" ,ios::in );
 if (!lapila1){

	 cout << "El archivo   lapila no existe" << endl;


 lapila1.close();

 lapila1.close();
   ofstream pclave1;
   lapila1.open("c:\\datos\\lapila.txt",ios::out);
  //lapila1 << 0;
   lapila1.close();
   lapila1.open("c:\\datos\\indice.txt",ios::in);

 }
 /////////////////////////////////////////////////////

 }

void construye(map<dni, reg, less<dni> >&m, int a, int b) {

	dni id;
	reg registro;

	//abrimos el archivo pclave dnd se almacenara
	//la clave y la direcci�n del mapa
	//estaba en ifstream
	fstream pclave("c:\\datos\\pclave.txt", ios::in);

	//recorremos todas las altas y las bajas
	for (int t = 0; t < a - b; t++) {

		//leemos la clave y la direccci�n del mapa
		pclave >> id;
		pclave >> registro;

		//insertamos en el mapa la clave y la direcccion
		m.insert(pair<dni, reg>(id, registro));

	}
	pclave.close();
}	//fin construye

void buscar(map<dni, reg, less<dni> > m, dni alfa, reg &registro) {

	//devuelve 0 si no existe y != si existe la clave alfa
	//es decir buscamos si existe o no el dni introducido

	//iterador para recorrer el mapa
	map<dni, reg, less<dni> >::iterator i;

	//buscamos si existe la clave
	i = m.find(alfa);

	//si la clave existe se muetra su direccion y si no "0"
	if (i != m.end()) {
		registro = (*i).second;

	} else {
		registro = 0;
	}
}	//fin de busca

void guarda(map<dni, reg, less<dni> > m) {

	//iterador para recorrer el mapa
	map<dni, reg, less<dni> >::iterator inicio, final;

	inicio = m.begin();
	final = m.end();

	ofstream pclave("c:\\datos\\pclave.txt", ios::out);

	for (inicio = m.begin(); inicio != m.end(); inicio++) {

		//guardamos en el mapa la pareja clave y direccion
		pclave << (*inicio).first;
		pclave << " ";
		pclave << (*inicio).second << endl;

	}
//pclave.close();
}	//fin guarda

void altas(map<dni, reg, less<dni> >&m, vector<int> v) {

	int altas, bajas;
	int hueco;
	dni dniPers;
	reg registro;
	reg numreg;
	string nom, ap;
	persona tmp;

	fstream datos("c:\\datos\\datos.txt",
			ios::out | ios::in | ios::binary);

	fstream indice("c:\\datos\\indice.txt", ios::out | ios::in);

	//leemos las altas y las bajas
	indice >> altas;
	indice >> bajas;
	//mostramos altas y bajas
	cout << "Altas: " << altas << "/ bajas: " << bajas << endl;

	indice.close();

	if (bajas != 0) {

		//se almacenan las bajas realizadas
		fstream lapila("c:\\datos\\lapila.txt", ios::out | ios::in );

		for (int x = 1; x <= bajas; x++) {

			lapila >> hueco; //leemos lo que hay en la pila

			v.push_back(hueco);
			//cout << "pos actual; " << v[x] << endl;
		}

		lapila.close();
	} //if

	construye(m, altas, bajas);

	cout << "Introduce tu n�mero de dni: " << endl;
	cin >> dniPers;

	while (dniPers != "0") {

		buscar(m, dniPers, registro);

		if (registro != 0) {

			cout << "ya esta incluido" << endl;

		} else {
			strcpy(tmp.dni, dniPers.c_str());
			cout << "introduce nombre" << endl;
			cin >> nom;

			strcpy(tmp.nombre, nom.c_str());

			cout << "introduce Apellido" << endl;
			cin >> ap;

			strcpy(tmp.apellidos, ap.c_str());

			//si hay una baja la aprovechara
			if (v.size() != 0) {

				bajas--;
				numreg = v.back();
				cout << "reg reutilizdo: " << numreg << endl;
				v.pop_back();

			} else {
				//si no hay bajas se inserta un nuevo registro
				altas++;
				numreg = altas;
				cout << "reg nuevo: " << numreg << endl;
			} //else

			//insertamos en el mapa el dni introducido
			// y el n�mero de registro asignado
			m.insert(pair<dni, reg>(dniPers, numreg));
			cout << "reg utilizado: " << numreg << endl;
			//mostramos la esturcuta
			//cout << "Alumno n�: " << i << endl;
			cout << "\tDNI: " << tmp.dni << endl;
			cout << "\tNombre: " << tmp.nombre << endl;
			cout << "\tApellido: " << tmp.apellidos << endl;
			cout << "-----------------------" << endl << endl;

			//nos posicionamos en el numero de registro que le toque
			datos.seekg((numreg - 1) * sizeof(persona), ios::beg);

			//escribimos el par clave direcccion en el mapa
			datos.write(reinterpret_cast<char*>(&tmp), sizeof(persona));

			//para comprobar el numero de registro dnd lo ha introducido
			cout << "registrado en :" << numreg << endl;
			dniPers = "0";

		} //primer else

	} //while, repetimos el proceso para m�s claves

	guarda(m); //llamamos a la funci�n guarda

	//se guardan los punteros de llenado
	ofstream indice1("c:\\datos\\indice.txt", ios::out);

	indice1 << altas << " " << bajas << " " << endl;
	//indice1 << " ";
	//indice1 << bajas;
	//indice1 << " ";

	indice1.close();				//cerramos indice

	ofstream lapila1("c:\\datos\\lapila.txt", ios::out );
	for (int z = 1; z <= bajas; z++) {

		lapila1 << v.back();
		v.pop_back();
	}
	lapila1.close();

	datos.close();

}
/*
void construyeBajas(map<dni, reg, less<dni> >&m) {

	dni id;
	reg registro;

	fstream pclave("c::\\datos\\pclave.txt", ios::in | ios::out);

	while (!pclave.eof()) {
		pclave >> id;
		pclave >> registro;

		m.insert(pair<dni, reg>(id, registro));
	}

	pclave.close();
}
*/
void bajas(map<dni, reg, less<dni> >&m, vector<int> v) {

	int hueco;
	int altas, bajas;
	dni dniPers;
	reg registro;
	//reg numreg;
	string nom, ap;
	persona tmp;

	//abrimos el archivo "datos" y el archivo "indice"
	fstream datos("c:\\datos\\datos.txt",
			ios::out | ios::in  | ios::binary);

	fstream indice("c:\\datos\\indice.txt", ios::out | ios::in);

	//leemos las altas y las bajas
	indice >> altas;
	indice >> bajas;
	//mostramos altas y bajas
	cout << "Altas: " << altas << "/ bajas: " << bajas << endl;

	indice.close();

	//comprobamos si existen bajas en el archivo "lapila"
	if (bajas != 0) {

		//se almacenan las bajas realizadas
		//creo qeu no tiene que ir lo de ios::app
		fstream lapila("c:\\datos\\lapila.txt", ios::out | ios::in);

		for (int x = 1; x <= bajas; x++) {

			lapila >> hueco; //leemos lo que hay en la pila

			v.push_back(hueco);
			//cout << "pos actual; " << v[x] << endl;
		}

		lapila.close();
	} //if

	map<dni, reg, less<dni> >::iterator pos;

	//construimos la pareja clave,direccion
	construye(m, altas, bajas);

	//pedimos por teclado el dni  a borrar
	cout << "dame el dni a borrar" << endl;
	cin >> dniPers;

	if (dniPers != "0") {

		//en el metodo buscar la informaci�n de si existe o no el dni se almacena en "registro"
		buscar(m, dniPers, registro);

		if (registro == 0) {

			cout << "no esta" << endl;

		} else {

			//nos posicionamos en el registro
			datos.seekg((registro - 1) * sizeof(persona), ios::beg);

			datos.read(reinterpret_cast<char*>(&tmp), sizeof(persona));

			cout << "=====================================" << endl;
			cout << "DNI: " << tmp.dni << endl;
			cout << "Nombre: " << tmp.nombre << endl;
			cout << "Apellido: " << tmp.apellidos << endl;
			cout << "=====================================" << endl;

			//devuelve un iterador al elemento encontrado
			pos = m.find(dniPers);
			//borra el elemento al que apunta el iterador
			m.erase(pos);

			cout << "DNI :" << dniPers << " con registro : " << registro
					<< " borrado con exito " << endl;

			//aumentamos el numero de bajas en una unidad
			bajas++;

			//a�adimos un elemento al final del vector
			v.push_back(registro);

		} //fin else

	} //fin if
	guarda(m);

	ofstream indice1("c:\\datos\\indice.txt", ios::out);

	indice1 << altas << " " << bajas << " " << endl;
	//indice1 << " ";
	//indice1 << bajas;
	//indice1 << " ";

	indice1.close();

	ofstream lapila1("c:\\datos\\lapila.txt", ios::out );
	for (int z = 1; z <= bajas; z++) {

		lapila1 << v.back();
		v.pop_back();
	}
	lapila1.close();

	datos.close();
}
void listar() {

	int altas, bajas;
	fstream indice("c:\\datos\\indice.txt", ios::out | ios::in);

	//leemos las altas y las bajas
	indice >> altas;
	indice >> bajas;
	//mostramos altas y bajas
	cout << "Altas: " << altas << "/ bajas: " << bajas << endl;

	indice.close();

	//iterador para recorrer el mapa
	map<dni, reg, less<dni> >::iterator pos;

	//si hay datos que leer
	if (altas != 0) {
		persona tmp;
		//abrimos el archivo para leerlo
		ifstream datos("c:\\datos\\datos.txt", ios::out | ios::binary);

		//-bajas
		for (int i = 1; i <= altas-bajas ; i++) {

			datos.seekg((i - 1) * sizeof(persona), ios::beg);

			//escribimos el par clave direcccion en el mapa
			datos.read(reinterpret_cast<char*>(&tmp), sizeof(persona));

			cout << "Alumno n�: " << i << endl;
			cout << "\tDNI: " << tmp.dni << endl;
			cout << "\tNombre: " << tmp.nombre << endl;
			cout << "\tApellido: " << tmp.apellidos << endl;
			cout << "-----------------------" << endl << endl;

		}
		datos.close();
	} //if
	else
		cout << "No hay informaci�n almacenada" << endl;
//construye();

}
#endif /* EJER4_H_ */
