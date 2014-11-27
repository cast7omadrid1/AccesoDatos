
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public  class Ejer3 {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {

		try {
			// Cargar el driver, selepasaeste driver JDBC para MySQL
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/empresas", "root", "root");

			DatabaseMetaData dbmd = conexion.getMetaData();

			//
			System.out.println("INFORMACIÓN DE LA BASE DE DATOS:");
			System.out.println("Nombre:" + dbmd.getDatabaseProductName());
			System.out.println("Driver de conexión:" + dbmd.getDriverName());
			System.out.println("URL:" + dbmd.getURL());
			System.out.println("Nombre de usuario:" + dbmd.getUserName());
			System.out.println("-------------------");

			ResultSet tablas = dbmd.getTables(null, "empresas", null, null);
			// System.out.println("Nombredelastablas:"
			// + dbmd.getTables(null, null, null, null));

			// paraadatabla
			while (tablas.next()) {
				System.out.println("\n\t"+tablas.getString("TABLE_NAME"));
				System.out.println("--------");

				// tabla
				String tabla = tablas.getString("TABLE_NAME");

				// Mostrarlainformaciónsobrelascolumnasdeunatabla o
				// tablas.
				// columnasdelatabla
				ResultSet columnas = dbmd.getColumns(null, "empresas", tabla,
						null);

				// mostramosloscamposdelatabla
				while (columnas.next()) {
					System.out.println("Campo:"
							+ columnas.getString("COLUMN_NAME") + " Tipo: "
							+ columnas.getString("TYPE_NAME") + " Tamaño: "
							+ columnas.getString("COLUMN_SIZE"));
				}

				// Mostrarlaclaveprimariadeunatabla
				ResultSet primaryKey = dbmd.getPrimaryKeys(null, "empresas",
						tabla);
				String pkDepartamento = " ";
				// String separador= " ";

				while (primaryKey.next()) {
					pkDepartamento = pkDepartamento + "  "
							+ primaryKey.getString("COLUMN_NAME");
				}
				System.out.println("Clave primaria: " + pkDepartamento);

				ResultSet ForeignKey = dbmd.getExportedKeys(null, "empresas",
						tabla);
				System.out.println("Clave ajena: " + ForeignKey);
			}
			// //

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
