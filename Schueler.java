package hausuebung_4;


	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;

	

	public class Schueler extends Runner{

		public static void dropSchueler(Connection c) {
			try {
				Statement stmt = c.createStatement();
				String sql = "drop table if exists Schueler;";
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


		public static void createSchueler(Connection c) {
			try {
				Statement stmt = c.createStatement();
				String sql = "create table if not exists Schueler (ktNR int primary key, vorname varchar(20), nachname varchar(20));"; 
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}


		public static void insertSchueler(Connection c, int ktNR, String vorname, String nachname) {

			try {
				String s = "insert into Schueler (ktNR, vorname, nachname) values (?, ?, ?);";
				PreparedStatement stmt = c.prepareStatement(s);

				stmt.setInt(1, ktNR);
				stmt.setString(2, vorname);
				stmt.setString(3, nachname);
				stmt.executeUpdate(s);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public static void writeSchueler(Connection c, String schueler) {
			try { 
				File f = new File(schueler);
				FileWriter fw = new FileWriter(f);

				Statement stmt = c.createStatement();
				String sql = "select ktNR, vorname, nachname from schueler;";
				ResultSet rs = stmt.executeQuery(sql);
				int id = 1;	

				while (rs.next()) {
					int ktnr = rs.getInt("ktNR");
					String vn = rs.getString("vorname");
					String nn = rs.getString("nachname");

					String csv = id + ", " + ktnr + ", " + vn + ", " + nn + ", ";
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
