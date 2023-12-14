package esprit.tn.greenshopjavafx.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static DataSource data;
    private Connection con;
    private String url = "jdbc:mysql://localhost:3306/esprit";
    private String user = "root";
    private String pwd = "";
    private DataSource(){
        try {
            con = DriverManager.getConnection(url, user, pwd);
            System.out.println("connexion établie");
        }catch (SQLException e)
        {
            System.out.println(e);
        }
    }
    public Connection getCon()
    {
        return con;
    }
    public static DataSource getInstance(){
        if(data==null)
        {
            data=new DataSource();
        }
        return data;
    }

}
