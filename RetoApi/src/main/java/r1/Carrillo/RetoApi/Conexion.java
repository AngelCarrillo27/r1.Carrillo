package r1.Carrillo.RetoApi;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private Connection connection=null;
    public Connection conectar()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cuentas",
                    "test", "Alaska22");

        }
        catch(Exception ex)
        {
            System.out.print(ex);
            return null;
        }
        return connection;
    }
}
