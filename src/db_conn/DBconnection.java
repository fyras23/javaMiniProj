package db_conn;

import java.sql.Connection;
import java.sql.DriverManager;



public class DBconnection {
	public static Connection  getCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root",
					"");
//			Statement transwmission = (Statement) con.createStatement();
//			ResultSet result = transwmission.executeQuery("select * from utilisateur");
//
//			while (result.next()) {
//				System.out.println("noms = " + result.getString(2) + result.getString(3));
//			}
			return con ; 

		} catch (Exception e) {
			System.out.print("impossible de se connecter & la base de données");
		}
		return null ;
	}
}