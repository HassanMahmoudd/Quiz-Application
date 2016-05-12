package application;

import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;
import java.sql.Statement ;
import java.sql.ResultSet ;

public class JDBCConnector {
	private Connection connection ;

    public static Connection connect()
    {
    	try 
    	{
    		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "DesigGraphi");
    		//System.out.println("Connection Successful");
    		return myConn;

    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return null;
    	}
    }

}
