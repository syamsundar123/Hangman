import java.sql.*;
import java.util.Random;
public class Database
{
	
	
	ResultSet rs;

	Database() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "system";
		String password = "syam123";
		String query = "SELECT * from words";
		
		try
		{
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url,username,password);
			Statement stmt = conn.createStatement();
			rs  = stmt.executeQuery(query);
			System.out.println("Query Executed Successfully");
			Random rand = new Random();
			int randomnumber = 1 + rand.nextInt(10);
			for(int i =0;i<randomnumber;i++)
			{
				rs.next();
			}
			System.out.println(rs.getString(1)+" "+ rs.getString(2)+" "+rs.getString(3) + " " + rs.getString(4));
			
				
		}
		
		catch(ClassNotFoundException c)
		{
			System.out.println("Failed to Connect  " + c);
		}
		
	
		
	}

}
