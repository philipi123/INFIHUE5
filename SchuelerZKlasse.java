package hausuebung_4;


	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.time.LocalDate;
	import java.sql.Date;
	import java.sql.PreparedStatement;


	public class SchuelerZKlasse extends Runner{

		public static void dropSchuelerZKlasse(Connection c) {
			try {
				Statement stmt = c.createStatement();
				String sql = "drop table if exists SchuelerZKlasse;";
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static void createSchuelerZKlasse(Connection c) {
			try {
				Statement stmt = c.createStatement();
				String sql = "create table if not exists SchuelerZKlasse (schID int not null, klID int not null, datum date not null, primary key(schID, klID, datum), foreign key (schID) references Schueler(ktNR), foreign key (klID) references Klasse(KlNR));"; 
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				 
			}
		}
		
		public static void insertSchuelerZKlasse(Connection c, String schID, String klID, String eintragsdatum) {					
			try {
				LocalDate today = LocalDate.now();
				java.sql.Date sqld = java.sql.Date.valueOf(today);
				
				String sql = "insert into SchuelerZuKlasse (schID, klID, eintragsdatum) values (" + schID + ", " + klID + ", \"" + sqld + "\");";
						
				PreparedStatement stmt = c.prepareStatement(sql);
				
				stmt.setString(1, schID);
				stmt.setString(2, klID);
				stmt.setString(3, eintragsdatum);
				stmt.executeUpdate(sql);
				stmt.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
		public static void writeSchuelerZKlasse(Connection c, String schuelerZuKlasse) {
			try { 
				File f = new File(schuelerZuKlasse);
				FileWriter fw = new FileWriter(f);

				Statement stmt = c.createStatement();
				String sql = "select schID, klID, eintragsdatum from schuelerZuKlasse;";
				ResultSet rs = stmt.executeQuery(sql);
				int id = 1;	

				while (rs.next()) {
					int sid = rs.getInt("schID");
					int klid = rs.getInt("klID");
					Date d = rs.getDate("eintragsdatum");
					
					String csv = id + ", " + sid + ", " + klid + ", " + d;
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
