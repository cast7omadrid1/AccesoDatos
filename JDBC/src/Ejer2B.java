import java.sql.*;
public class Ejer2B {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		try{
			//cargamos el driver, se le pasa este driver JDBC para SQLite
			
			Class.forName("org.sqlite.JDBC").newInstance();
			Connection conexion=DriverManager.getConnection("jdbc:sqlite:c:/Sqlite/empresas.db");
			Statement sentencia=conexion.createStatement();
			
			ResultSet resul=sentencia.executeQuery("SELECT * FROM empleados where salario >= 1000");
			
			//si la cconsulta existe sacamos los datos
			while(resul.next()){
				System.out.println(resul.getInt(1)+" "+resul.getString(2)+" "+resul.getString(3)+" "+resul.getString(4)+" "
						+resul.getInt(5)+" "+resul.getInt(6)+" "+resul.getInt(7)+" ");
			}
			//cerramos la conexion la sentencia y el resultado
			resul.close();
			sentencia.close();
			conexion.close();
	
			
		}catch (ClassNotFoundException cn) {cn.printStackTrace();}
		catch (InstantiationException ie) {ie.printStackTrace();}
		catch (IllegalAccessException ia){ia.printStackTrace();}
		catch (SQLException e){e.printStackTrace();}

		
	}
	

}
