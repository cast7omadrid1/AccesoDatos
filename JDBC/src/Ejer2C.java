import java.sql.*;
import java.util.Scanner;

public class Ejer2C {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner teclado = new Scanner(System.in);

		int numEmp;
		String apellido, oficio, fecha_alt;
		int salario, comision, depNo;

		try {
			// Cargar el driver, se le pasa este driver JDBC para SQLite
			Class.forName("org.sqlite.JDBC").newInstance();
			Connection conexion = DriverManager
					.getConnection("jdbc:sqlite:c:/sqlite/empresas.db");
			Statement sentencia = conexion.createStatement();

			ResultSet resul;
			do {
				// pedimos el numero de empleado por teclado
				System.out.println("Introduce el numero de empleado");
				numEmp = teclado.nextInt();
				// teclado.nextLine();

				// buscamos si el numero de empleado ya está en la base de datos
				resul = sentencia
						.executeQuery("SELECT emp_no FROM empleados where emp_no = "
								+ numEmp);

				// si hay resultados muestra un mensaje
				if (resul.next()) {
					System.out.println("Este empleado ya existe");
				} else {
					break;
				}

			} while (!resul.next());

			ResultSet resultado;
			do {
				System.out
						.println("Introduce el número de departamento del empleado");
				depNo = teclado.nextInt();
				teclado.nextLine();
				// ////comprobar que el numero de departamentos existe

				// buscamos si el numero de empleado ya está en la base de datos
				resultado = sentencia
						.executeQuery("SELECT dept_no FROM departamentos where dept_no = "
								+ depNo);

				if (!resultado.next()) {
					System.out.println("no existe el departamento");
				}

			} while (resultado.next());
			// si el departamento existe

			// pedimos la demás información por teclado
			System.out.println("Introduce el apellido del empleado");
			apellido = teclado.nextLine();
			System.out.println("Introduce el oficio del empleado");
			oficio = teclado.nextLine();

			System.out.println("Introduce la fecha de alta del empleado");
			fecha_alt = teclado.nextLine();

			System.out.println("Introduce el salario del empleado");
			salario = teclado.nextInt();
			System.out.println("Introduce la comision del empleado");
			comision = teclado.nextInt();

			// creamos la sentencia para insertar los datos
			int result = sentencia
					.executeUpdate("Insert into empleados (emp_no, apellido, oficio, fecha_alt, salario, comision, dept_no) values ('"
							+ numEmp
							+ "','"
							+ apellido
							+ "','"
							+ oficio
							+ "','"
							+ fecha_alt
							+ "','"
							+ salario
							+ "','"
							+ comision + "','" + depNo + "');");

			System.out.println("Se ha introducido el empleado");

			// el departamento no existe

			resul.close();
			// resultado.close();
			sentencia.close();
			conexion.close();

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (InstantiationException ie) {
			ie.printStackTrace();
		} catch (IllegalAccessException ia) {
			ia.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
