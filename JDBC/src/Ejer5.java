import java.awt.font.NumericShaper;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ejer5 {

	/**
	 * @param args
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, SQLException, ClassNotFoundException,
			IOException {

		Scanner teclado = new Scanner(System.in);
		int opcion, opcionTablas;

		int db;
		Connection conexion = null;

		do {

			System.out.println("Menu");
			System.out.println("-----------------");
			System.out.println("Selecciona BBDD: ");
			System.out.println("1-SQLite");
			System.out.println("2-MYSQL");
			System.out.println("3-Salir");
			opcion = teclado.nextInt();

			switch (opcion) {

			case 1:

				Class.forName("org.sqlite.JDBC").newInstance();
				conexion = DriverManager
						.getConnection("jdbc:sqlite:c:/sqlite/tienda.db");// Establecemos
																			// conexion
																			// BBDD
																			// SQLITE
				do {
					System.out.println("Seleccione tabla: ");
					System.out.println("1-.PRODUCTOS");
					System.out.println("2-.CLIENTES");
					System.out.println("3-.VENTAS");
					System.out.println("4-.LISTADO CLIENTES");
					System.out.println("5-.NINGUNA");
					System.out.print("Seleccione opcion: ");
					opcionTablas = teclado.nextInt();
					teclado.nextLine();

					switch (opcionTablas) {

					case 1:

						insertarProductos(conexion);

						break;

					case 2:

						insertarClientes(conexion);

						break;

					case 3:

						insertarVenta(conexion);

						break;

					}// fin switch

				} while (opcionTablas != 5);

				break;

			case 2:

				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conexion = DriverManager.getConnection(
						"jdbc:mysql://localhost/tienda", "root", "root");

				do {
					System.out.println("Seleccione tabla: ");
					System.out.println("1-.PRODUCTOS");
					System.out.println("2-.CLIENTES");
					System.out.println("3-.VENTAS");
					System.out.println("4-.LISTADO CLIENTES");
					System.out.println("5-.NINGUNA");
					System.out.print("Seleccione opcion: ");
					opcionTablas = teclado.nextInt();
					teclado.nextLine();

					switch (opcionTablas) {

					case 1:

						insertarProductos(conexion);

						break;

					case 2:

						insertarClientes(conexion);

						break;

					case 3:

						insertarVenta(conexion);

						break;

					}// fin switch

				} while (opcionTablas != 5);

				break;
			}// fin switch

		} while (opcion != 3);

	}// fin main

	private static void insertarProductos(Connection conexion)
			throws SQLException, IOException {

		Scanner teclado = new Scanner(System.in);
		int id, stockactual, stockminimo, pvp;
		String descripcion, nombre, direccion, telefono, nif, poblacion;

		String sql = "INSERT INTO productos VALUES (?,?, ?, ?, ?) ";
		String sql1 = "SELECT * FROM productos WHERE (id=?)";

		try {

			PreparedStatement sentencia = conexion.prepareStatement(sql);
			PreparedStatement sent1 = conexion.prepareStatement(sql1);

			id = ComprobarProducto(sent1);
			while (id != 0) {
				System.out
						.println("Introducion de datos en la tabla Productos SQLITE");
				System.out
						.println("-------------------------------------------");

				System.out.println("Introduce descripcion del producto");
				descripcion = teclado.nextLine();
				// si es nulo volvemos a pedir la descripcion
				while (descripcion.equals("")) {
					System.out
							.println("Introduce descripcion del producto,el campo no puede ser nulo");
					descripcion = teclado.nextLine();
				}

				// a continuación pedimos el resto de los datos
				// y realizamos la inserccion de los mismos en
				// la base de datos

				System.out.println("Introduce el stock actual del producto");
				stockactual = teclado.nextInt();

				System.out.println("Introduce el stock minimo del producto");
				stockminimo = teclado.nextInt();

				System.out.println("Introduce el precio del producto");
				pvp = teclado.nextInt();

				sentencia.setInt(1, id);
				sentencia.setString(2, descripcion);
				sentencia.setInt(3, stockactual);
				sentencia.setInt(4, stockminimo);
				sentencia.setInt(5, pvp);
				sentencia.executeUpdate();
				id = ComprobarProducto(sent1);
			}
			// Una vez realizada la introduccion de los
			// datos lo mostramos
			/*
			 * ResultSet resul; resul = sentencia
			 * .executeQuery("select * from productos where descripcion='"
			 * +descripcion+"';");
			 * 
			 * // mientras la consulta exista while (resul.next()) {
			 * 
			 * System.out.println(resul.getInt(1) + " " + resul.getString(2) +
			 * " " + resul.getInt(3) + " " + resul.getInt(4) + " " +
			 * resul.getInt(5)); }
			 */

			// al final de do while volver a pedir.
			// System.out.print("Introduzca otro id para continuar o 0 para abortar: ");
			// id = teclado.nextLine();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void insertarClientes(Connection conexion) {

		Scanner teclado = new Scanner(System.in);
		int id;
		String nombre, direccion, poblacion, telefono, nif;
		String sql = "INSERT INTO clientes VALUES (?, ?, ?, ?, ?,?) ";
		String sql1 = "SELECT * FROM clientes WHERE (id=?)";
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			PreparedStatement sent1 = conexion.prepareStatement(sql1);
			System.out.println("==================");
			System.out.println("INSERTAR CLIENTES");
			System.out.println("==================");

			id = ComprobarCliente(sent1);
			while (id != 0) {
				System.out.print("Nombre del cliente: ");
				nombre = teclado.nextLine();

				System.out.print("Dirección del cliente:");
				direccion = teclado.nextLine();

				System.out.print("Poblacion");
				poblacion = teclado.nextLine();

				System.out.print("Telefono");
				telefono = teclado.nextLine();

				System.out.print("nif");
				nif = teclado.nextLine();

				sentencia.setInt(1, id);
				sentencia.setString(2, nombre);
				sentencia.setString(3, direccion);
				sentencia.setString(4, poblacion);
				sentencia.setString(5, telefono);
				sentencia.setString(6, nif);
				sentencia.executeUpdate();
				id = ComprobarCliente(sent1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void insertarVenta(Connection conexion) {

		Scanner teclado = new Scanner(System.in);
		int id;
		int idCliente, idProducto, cant;
		String fecha;
		String sql = "INSERT INTO ventas VALUES (?, ?, ?, ?,?) ";
		String sql1 = "SELECT * FROM ventas WHERE (idVenta=?)";
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			PreparedStatement sent1 = conexion.prepareStatement(sql1);
			System.out.println("==================");
			System.out.println("INSERTAR VENTAS");
			System.out.println("==================");

			id = ComprobarVenta(sent1);
			while (id != 0) {
				System.out.print("Fecha: ");
				fecha = teclado.nextLine();

				System.out.print("Id cliente:");
				idCliente = ComprobarCliente2(sent1);

				System.out.print("Id producto:");
				idProducto = ComprobarProducto2(sent1);

				System.out.print("Cantidad:");
				cant = ComprobarStock(conexion, idProducto);

				sentencia.setInt(1, id);
				sentencia.setString(2, fecha);
				sentencia.setInt(3, idCliente);
				sentencia.setInt(4, idProducto);
				sentencia.setInt(5, cant);
				sentencia.executeUpdate();
				id = ComprobarVenta(sent1);

				// actualizamos el stock
				actualizarStock(conexion, idProducto, cant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static int ComprobarCliente(PreparedStatement sent1)
			throws IOException, SQLException {
		Scanner teclado = new Scanner(System.in);
		int id;
		ResultSet resulSent1 = null;
		System.out.print("Código del cliente: ");
		id = teclado.nextInt();

		sent1.setInt(1, id);
		resulSent1 = sent1.executeQuery();
		while (resulSent1.next()) {
			System.out.println("El cliente ya existe");
			System.out.print("Código del cliente: ");
			id = teclado.nextInt();
			sent1.setInt(1, id);
			resulSent1 = sent1.executeQuery();
		}
		resulSent1.close();
		return id;
	}

	private static int ComprobarCliente2(PreparedStatement sent1)
			throws IOException, SQLException {

		Scanner teclado = new Scanner(System.in);
		int id;
		ResultSet resulSent1 = null;
		System.out.print("Código del cliente: ");
		id = teclado.nextInt();

		sent1.setInt(1, id);
		resulSent1 = sent1.executeQuery();
		while (!resulSent1.next()) {
			System.out.println("El cliente ya existe");
			System.out.print("Código del cliente: ");
			id = teclado.nextInt();
			sent1.setInt(1, id);
			resulSent1 = sent1.executeQuery();
		}
		resulSent1.close();
		return id;
	}

	private static int ComprobarProducto(PreparedStatement sent1)
			throws IOException, SQLException {
		Scanner teclado = new Scanner(System.in);
		int id;
		ResultSet resulSent1 = null;
		System.out.print("Código del producto: ");
		id = teclado.nextInt();

		sent1.setInt(1, id);
		// ejecuta la sentencia de insertarProductos
		resulSent1 = sent1.executeQuery();
		while (resulSent1.next()) {
			System.out.println("El producto existe");
			System.out.print("Código del producto: ");
			id = teclado.nextInt();

			sent1.setInt(1, id);
			resulSent1 = sent1.executeQuery();
		}
		resulSent1.close();
		return id;
	}

	private static int ComprobarProducto2(PreparedStatement sent1)
			throws IOException, SQLException {
		Scanner teclado = new Scanner(System.in);
		int id;
		ResultSet resulSent1 = null;
		System.out.print("Código del producto: ");
		id = teclado.nextInt();

		sent1.setInt(1, id);
		// ejecuta la sentencia de insertarProductos
		resulSent1 = sent1.executeQuery();
		while (!resulSent1.next()) {
			System.out.println("El producto existe");
			System.out.print("Código del producto: ");
			id = teclado.nextInt();

			sent1.setInt(1, id);
			resulSent1 = sent1.executeQuery();
		}
		resulSent1.close();
		return id;
	}

	private static int ComprobarVenta(PreparedStatement sent1)
			throws IOException, SQLException {
		Scanner teclado = new Scanner(System.in);
		int id;
		ResultSet resulSent1 = null;
		System.out.print("Código de venta: ");
		id = teclado.nextInt();

		sent1.setInt(1, id);
		resulSent1 = sent1.executeQuery();
		while (resulSent1.next()) {
			System.out.println("La venta ya existe");
			System.out.print("Código del venta: ");
			id = teclado.nextInt();
			sent1.setInt(1, id);
			resulSent1 = sent1.executeQuery();
		}
		resulSent1.close();
		return id;
	}

	private static int ComprobarStock(Connection conexion, int idProducto)
			throws IOException, SQLException {

		Scanner teclado = new Scanner(System.in);

		Statement sentencia = conexion.createStatement();
		ResultSet resul;
		resul = sentencia
				.executeQuery("select stockActual from productos where id="
						+ idProducto + ";");
		int cantDisponible = 0;
		while (resul.next()) {
			cantDisponible = resul.getInt(1);

		}

		int cant;

		System.out.print("Cantidad ");
		cant = teclado.nextInt();

		// sent1.setInt(1, cant);

		while (cantDisponible < cant) {
			System.out.println("No hay suficiente stock en la tienda");
			System.out.print("Cantidad: ");
			cant = teclado.nextInt();

		}
		resul.close();
		return cant;
	}

	public static void actualizarStock(Connection conexion, int idProducto,
			int cant) throws SQLException {

		Statement sentencia = conexion.createStatement();

		ResultSet resul;
		resul = sentencia
				.executeQuery("select stockActual from productos where id="
						+ idProducto + ";");
		int cantDisponible = 0;
		while (resul.next()) {
			cantDisponible = resul.getInt(1);
		}

		int result = sentencia
				.executeUpdate("update productos set stockActual= "
						+ (cantDisponible - cant) + " where id= " + idProducto);

	}
}


