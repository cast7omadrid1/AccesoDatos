import java.sql.*;
import java.util.Scanner;

public class Ejer2D {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);

		// accedemos a la base
		try {

			// Cargar el driver, se le pasa este driver JDBC para SQLite
			Class.forName("org.sqlite.JDBC").newInstance();
			Connection conexion = DriverManager
					.getConnection("jdbc:sqlite:c:/sqlite/empresas.db");
			Statement sentencia = conexion.createStatement();

			// pedimos empleado

			int numEmp;
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
				if (!resul.next()) {
					System.out.println("Este empleado no existe");
				}

			} while (resul.next());

			// System.out.println(resul);

			// mostramos empleado
			// while (resul.next()) {
			ResultSet result;
			result = sentencia
					.executeQuery("SELECT * FROM empleados where emp_no = "
							+ numEmp);
			System.out.println(result.getInt(1) + " " + result.getString(2)
					+ " " + result.getString(3) + " " + result.getString(4)
				+ " " + result.getInt(5) + " " + result.getInt(6) + " "
					+ result.getInt(7));
			// }

			// pedimos comprobación

			int eliminar;
			System.out.println("¿Desea eliminar el empleado?  (1-Si 2-No)");
			eliminar = teclado.nextInt();

		if (eliminar == 1) {
				// ResultSet resultado;
				sentencia.executeUpdate("Delete from empleados where emp_no="
					+ numEmp + ";");
				System.out.println("El empleado ha sido eliminado");
			} else {

				System.out.println("La operación ha sido cancelada");
			}

			// borramo entrada

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
