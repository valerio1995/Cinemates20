package mvp;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionClass {

public Connection connection;

    public Connection getConnection(){
        
        String dbServer = "mysql-42866-0.cloudclusters.net"; // change it to your database server name
        int dbPort = 19484; // change it to your database server port
        String dbName = "Cinemates20"; //database name
        String userName = "admin";
        String password = "oKIGQpjw";
        String url = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s&allowPublicKeyRetrieval=true&maxReconnects=6&autoReconnect=true&useSSL=false", 
                                    dbServer, dbPort, dbName, userName, password);
            


        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection=DriverManager.getConnection(url);
            System.out.println("Connected!");

        }catch (Exception e){
            e.printStackTrace();
        }

        return connection;
    }

}
