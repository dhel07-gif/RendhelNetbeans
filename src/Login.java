


import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import javax.swing.JOptionPane;

import javax.swing.JOptionPane;

import javax.swing.JOptionPane;

public class Login {

    String[] defUser = {"Username", "Admin Username"};
    String[] defPass = {"Password", "Admin Password"};
    DB_CONNECTION DB = new DB_CONNECTION();

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    private final LoginPage loginPage;

    public Login(LoginPage loginPage) {
        this.loginPage = loginPage;
    }
    
    void createAccount(String studentFullname, String studentUsername, String studentPassword) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Jurial_Obo_system?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            ps = con.prepareStatement("INSERT INTO users (full_name, username, password, role) VALUES (?,?,?, 'Student')");

            ps.setString(1, studentFullname);
            ps.setString(2, studentUsername);
            ps.setString(3, studentPassword);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Account Created Successfully!");

        } catch (ClassNotFoundException ex) {
            System.getLogger(CreateAccount.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (SQLException ex) {
            System.getLogger(CreateAccount.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    void studentLogin(String username, String password) {
        if (username.isEmpty() && password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Enter Username and Password!");
            return;
        } else if (username.equals(defUser[0]) && password.equals(defPass[0])) {
            JOptionPane.showMessageDialog(null, "Please Enter Username and Password!");
            return;
        }
        // Student accounts
        try {
            DB.ps = DB.con.prepareStatement("SELECT full_name, username, password, role FROM users WHERE username = ?");
            DB.ps.setString(1, username);

            DB.rs = DB.ps.executeQuery();

            if (DB.rs.next()) {
                String fn = DB.rs.getString("full_name");
                String un = DB.rs.getString("username");
                String pw = DB.rs.getString("password");
                String rl = DB.rs.getString("role");
                if (un.equals(username) && pw.equals(password)) {
                    //check if the role is == Student
                    if (rl.equalsIgnoreCase("Student")) {
                        JOptionPane.showMessageDialog(null, "Logged in Successfully!");
                        loginPage.dispose();
                        CanteenOrderDashboard COD = new CanteenOrderDashboard();
                        COD.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Access Denied! This login is restricted to students only.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } else {
                JOptionPane.showMessageDialog(null, "User not found!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
        }

    }

    void staffLogin(String username, String password) {
        if (username.isEmpty() && password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Enter Username and Password!");
            return;
        } else if (username.equals(defUser[0]) && password.equals(defPass[0])) {
            JOptionPane.showMessageDialog(null, "Please Enter Username and Password!");
            return;
        }

        // Staff accounts
        try {
            DB.ps = DB.con.prepareStatement("SELECT full_name, username, password, role FROM users WHERE username = ?");
            DB.ps.setString(1, username);

            DB.rs = DB.ps.executeQuery();

            if (DB.rs.next()) {
                String fn = DB.rs.getString("full_name");
                String un = DB.rs.getString("username");
                String pw = DB.rs.getString("password");
                String rl = DB.rs.getString("role");
                if (un.equals(username) && pw.equals(password)) {
                    //check if the role is == Staff
                    if (rl.equalsIgnoreCase("Staff")) {
                        JOptionPane.showMessageDialog(null, "Logged in Successfully!");
                        loginPage.dispose();
                        //studentInterface studInteface = new studentInterface();
                        //studInteface.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Access Denied! This login is restricted to staff only.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } else {
                JOptionPane.showMessageDialog(null, "User not found!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
        }
    }

    void adminLogin(String adminUsername, String adminPassword) {
        if (adminUsername.isEmpty() && adminPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Enter Username and Password!");
            return;
        } else if (adminUsername.equals(defUser[0]) && adminPassword.equals(defPass[0])) {
            JOptionPane.showMessageDialog(null, "Please Enter Username and Password!");
            return;
        }

        // Staff accounts
        try {
            DB.ps = DB.con.prepareStatement("SELECT full_name, username, password, role FROM users WHERE username = ?");
            DB.ps.setString(1, adminUsername);

            DB.rs = DB.ps.executeQuery();

            if (DB.rs.next()) {
                String fn = DB.rs.getString("full_name");
                String un = DB.rs.getString("username");
                String pw = DB.rs.getString("password");
                String rl = DB.rs.getString("role");
                if (un.equals(adminUsername) && pw.equals(adminPassword)) {
                    //check if the role is == Admin
                    if (rl.equalsIgnoreCase("Admin")) {
                        JOptionPane.showMessageDialog(null, "Logged in Successfully!");
                        loginPage.dispose();
                        //sample home = new sample();
                        //home.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Access Denied! This login is restricted to Admin only.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } else {
                JOptionPane.showMessageDialog(null, "User not found!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
        }
    }
}