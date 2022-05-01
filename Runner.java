package hausuebung_4;

import java.sql.*;
import java.time.LocalDate;


public class Runner {
	public static Connection createConnection() {

		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	}


	public static void main(String args[]) {
		try {
			Connection c = createConnection();	
			
			SchuelerZKlasse.dropSchuelerZKlasse(c);
			Klasse.dropKlasse(c);
			Schueler.dropSchueler(c);
			
			Klasse.createKlasse(c);
			Schueler.createSchueler(c);
			SchuelerZKlasse.createSchuelerZKlasse(c);
			
			Klasse.insertKlasse(c, 1, 22, "1AHWII");
			Klasse.insertKlasse(c, 2, 36, "1BHWII");
			
			
			Schueler.insertSchueler(c, 1, "Johann", "Peter");
			Schueler.insertSchueler(c, 2, "Hans", "Maier");
			
			SchuelerZKlasse.insertSchuelerZKlasse(c, "1", "1", LocalDate.of(2018, 06, 13));
			SchuelerZKlasse.insertSchuelerZKlasse(c, "2", "2", LocalDate.of(2018, 06, 13));
			
			
			
			Klasse.WriteKlasse(c, "/Users/philip/Desktop/HTL/SWP-Greinöcker/ECLIPSE/Infi/src/hausuebung_4/klasse.csv");
			
			Schueler.writeSchueler(c, "/Users/philip/Desktop/HTL/SWP-Greinöcker/ECLIPSE/Infi/src/hausuebung_4/schueler.csv");
			
			SchuelerZKlasse.writeSchuelerZKlasse(c, "/Users/philip/Desktop/HTL/SWP-Greinöcker/ECLIPSE/Infi/src/hausuebung_4/schuelerzklasse.csv");
			

			c.close();
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
