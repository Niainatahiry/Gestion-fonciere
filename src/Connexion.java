import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
    Connection cn;

    public Connexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost/foncière_db", "root", "");
            System.out.println("Connexion établie!");
        } catch (Exception e) {
            System.out.println("Non connectée!");
        }
    }

    public Connection laConnection() {
        return cn;
    }
}
