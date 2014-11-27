import java.sql.*;

public class Ejer2A {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try{
			
			//Cargamos el driver, se le pasa este drievr JDBC para SQLite
			
			Class.forName("org.sqlite.JDBC").newInstance();
			
			//creamos la conexión con las bases de datos creadas anteriormente
			Connection conexion=DriverManager.getConnection("jdbc:sqlite:c:/Sqlite/empresas.db");
			Statement sentencia=conexion.createStatement();
			ResultSet resul=sentencia.executeQuery("SELECT * FROM  empleados");
			
			//mientras el resultado de la consilta exista
			while(resul.next()){
				
				System.out.println(resul.getInt(1)+" "+resul.getString(2)+" "+resul.getString(3)+" "+resul.getString(4)+" "
				+resul.getInt(5)+" "+resul.getInt(6)+" "+resul.getInt(7)+" ");
				
			}//fin while
			//cerramos la consulta
			resul.close();
			//cerramos la sentencia
			sentencia.close();
			//cerramos la conexión
			conexion.close();
		
		}catch (ClassNotFoundException cn) {cn.printStackTrace();}
			catch (InstantiationException ie) {ie.printStackTrace();}
			catch (IllegalAccessException ia){ia.printStackTrace();}
			catch (SQLException e){e.printStackTrace();}
		
	}
}

