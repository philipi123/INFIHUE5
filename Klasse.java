package hausuebung_4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Klasse extends Runner{
	public static void dropKlasse(Connection c) {
		try { 
			Statement stmt = c.createStatement();
			String sql = "drop table if exists Klasse;";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	public static void createKlasse(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists Klasse (Klnr int primary key, anzschueler int, abteilung varchar(30));";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertKlasse(Connection c, int Klnr, int anzschueler, String abteilung) {

		try {
			String s = "insert into Klasse (Klnr, anzschueler, abteilung) values (?, ?, ?);";
			PreparedStatement stmt = c.prepareStatement(s);

			stmt.setInt(1, Klnr);
			stmt.setInt(2, anzschueler);
			stmt.setString(3, abteilung);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void WriteKlasse(Connection c, String klasse) {
		try { 
			File f = new File(klasse);
			FileWriter fw = new FileWriter(f);

			Statement stmt = c.createStatement();
			String sql = "select Klnr, anzschueler, abteilung from klasse;";
			ResultSet rs = stmt.executeQuery(sql);
			/*auto_increment*/ int id = 1;	

			while (rs.next()) {
				int kln = rs.getInt("Klnr");
				int s = rs.getInt("anzschueler");
				String abt = rs.getString("abteilung");

				String csv = id + ", " + kln + ", " + ", " + s + ", " + abt;
				fw.write(csv);
				id++;
			}

			rs.close();
			stmt.close();
			fw.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}   catch (IOException e) {
			e.printStackTrace();
		}
	}
}
