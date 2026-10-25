
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class DB_CONNECTION {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement stat;

    public void sysConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Jurial_Obo_system?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            stat = (Statement) con.createStatement();
        } catch (ClassNotFoundException ex) {
            System.getLogger(DB_CONNECTION.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (SQLException ex) {
            System.getLogger(DB_CONNECTION.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public DB_CONNECTION() {
        sysConnection();
    }
}
