
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ejer4 {

	/**
	 * @param args
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException {

		Scanner teclado = new Scanner(System.in);

		try {
			// Cargar el driver, selepasaeste driver JDBC para SQLite
			Class.forName("org.sqlite.JDBC").newInstance();
			Connection conexion = DriverManager
					.getConnection("jdbc:sqlite:c:/sqlite/empresas.db");
			Statement sentencia = conexion.createStatement();

			// instnaciamoslaconsulta
			ResultSet resul;
			int departamento;

			do {

				// pedimosporteclado el númerodedepartamento a mostrar
				System.out.println("Introduce el número de departamento");
				departamento = teclado.nextInt();

				// realizamoslaconsulta
				resul = sentencia
						.executeQuery("select apellido,salario, oficio, nombre from empleados, departamentos where empleados.dept_no = departamentos.deptNo and departamentos.deptNo="
								+ departamento + ";");

				// comprobamosqueexista el departametno (resultengadatos)
				if (resul.next()) {
					// mostramoslainformación (resul) deldepartamento
					System.out.println(resul.getString(1) + " "
							+ resul.getInt(2) + " " + resul.getString(3) + " "
							+ resul.getString(4));

					// salariomedio y númerodeempleados
					ResultSet resulEmp;
					resulEmp = sentencia
							.executeQuery("select sum(salario)/count(*),count(*) from empleados where dept_no="
									+ departamento + " group by dept_no;");

					//mostramoslaconsulta
					System.out.println("\nSalario medio/ número de empleados");
					System.out.println(resulEmp.getInt(1) + " "
							+ resul.getInt(2));

					// /
				}
				// mostramosunmensajede error
				else if (departamento != 0)
					System.out.println("El departamento no existe");

			} while (!resul.next() && departamento != 0);

			// finalizamoslas variables
			resul.close();
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




