

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CreateAccount extends javax.swing.JFrame {

    String[] defFields = {"Name", "Username", "Password", "Confirm Password"};
    DB_CONNECTION DB = new DB_CONNECTION();

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CreateAccount.class.getName());

    public CreateAccount() {
        //setUndecorated(true);
        initComponents();
        setResizable(false);
        setTitle("Create new Account");
        defaultFields();
        autoIncrementID();
    }

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    void autoIncrementID() {
        String url = "jdbc:mysql://localhost:3306/Jurial_Obo_system"
                + "?useSSL=false&serverTimezone=Asia/Manila";

        String sql = "SELECT COALESCE(MAX(user_id), 0) + 1 AS next_id "
                + "FROM users";

        try (Connection con = DriverManager.getConnection(url, "root", "root"); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                accountIDNumber.setText(
                        String.valueOf(rs.getInt("next_id"))
                );
            }

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error retrieving next user ID", ex);
            accountIDNumber.setText("N/A");
        }
    }

    void defaultFields() {
        //fullname
        studentName.setText(defFields[0]);
        studentName.setFont(new Font("SansSerif", Font.ITALIC, 12));
        studentName.setForeground(new Color(204, 204, 204));
        //username
        studentUsername.setText(defFields[1]);
        studentUsername.setFont(new Font("SansSerif", Font.ITALIC, 12));
        studentUsername.setForeground(new Color(204, 204, 204));
        //password
        studentPassword.setText(defFields[2]);
        studentPassword.setFont(new Font("SansSerif", Font.ITALIC, 12));
        studentPassword.setForeground(new Color(204, 204, 204));
        studentPassword.setEchoChar((char) 0);
        //confirm password
        studentConfirmPassword.setText(defFields[3]);
        studentConfirmPassword.setFont(new Font("SansSerif", Font.ITALIC, 12));
        studentConfirmPassword.setForeground(new Color(204, 204, 204));
        studentConfirmPassword.setEchoChar((char) 0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        studentName = new javax.swing.JTextField();
        studentUsername = new javax.swing.JTextField();
        studentPassword = new javax.swing.JPasswordField();
        studentConfirmPassword = new javax.swing.JPasswordField();
        createAccountButton = new javax.swing.JPanel();
        LoginLabel = new javax.swing.JLabel();
        backToMainPage = new javax.swing.JLabel();
        CreateAccountLabel1 = new javax.swing.JLabel();
        accountIDNumber = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(115, 138, 110));

        studentName.setBackground(new java.awt.Color(102, 102, 102));
        studentName.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        studentName.setForeground(new java.awt.Color(51, 51, 51));
        studentName.setText("Fullname");
        studentName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        studentName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                studentNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                studentNameFocusLost(evt);
            }
        });
        studentName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                studentNameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                studentNameKeyTyped(evt);
            }
        });

        studentUsername.setBackground(new java.awt.Color(102, 102, 102));
        studentUsername.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        studentUsername.setForeground(new java.awt.Color(51, 51, 51));
        studentUsername.setText("Username");
        studentUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        studentUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                studentUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                studentUsernameFocusLost(evt);
            }
        });
        studentUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                studentUsernameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                studentUsernameKeyTyped(evt);
            }
        });

        studentPassword.setBackground(new java.awt.Color(102, 102, 102));
        studentPassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        studentPassword.setForeground(new java.awt.Color(51, 51, 51));
        studentPassword.setText("jPasswordF");
        studentPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        studentPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                studentPasswordFocusLost(evt);
            }
        });
        studentPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                studentPasswordKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                studentPasswordKeyTyped(evt);
            }
        });

        studentConfirmPassword.setBackground(new java.awt.Color(102, 102, 102));
        studentConfirmPassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        studentConfirmPassword.setForeground(new java.awt.Color(51, 51, 51));
        studentConfirmPassword.setText("jPasswordF");
        studentConfirmPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        studentConfirmPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                studentConfirmPasswordFocusLost(evt);
            }
        });
        studentConfirmPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                studentConfirmPasswordKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                studentConfirmPasswordKeyTyped(evt);
            }
        });

        createAccountButton.setBackground(java.awt.Color.orange);
        createAccountButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createAccountButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                createAccountButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                createAccountButtonMouseExited(evt);
            }
        });

        LoginLabel.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        LoginLabel.setForeground(new java.awt.Color(51, 51, 51));
        LoginLabel.setText("Create Account");

        javax.swing.GroupLayout createAccountButtonLayout = new javax.swing.GroupLayout(createAccountButton);
        createAccountButton.setLayout(createAccountButtonLayout);
        createAccountButtonLayout.setHorizontalGroup(
            createAccountButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createAccountButtonLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(LoginLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        createAccountButtonLayout.setVerticalGroup(
            createAccountButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createAccountButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LoginLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        backToMainPage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        backToMainPage.setForeground(new java.awt.Color(51, 51, 51));
        backToMainPage.setText("<- Back");
        backToMainPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backToMainPageMouseClicked(evt);
            }
        });

        CreateAccountLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CreateAccountLabel1.setForeground(new java.awt.Color(0, 0, 0));
        CreateAccountLabel1.setText("Acc ID:");

        accountIDNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        accountIDNumber.setForeground(new java.awt.Color(0, 0, 0));
        accountIDNumber.setText("00000     ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(backToMainPage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CreateAccountLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(accountIDNumber))
                    .addComponent(studentUsername, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(studentName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addComponent(studentPassword)
                    .addComponent(studentConfirmPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(createAccountButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backToMainPage)
                    .addComponent(CreateAccountLabel1)
                    .addComponent(accountIDNumber))
                .addGap(18, 18, 18)
                .addComponent(studentName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(studentUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(studentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(studentConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(createAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void studentNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_studentNameFocusGained

    }//GEN-LAST:event_studentNameFocusGained

    private void studentNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_studentNameFocusLost
        if (studentName.getText().trim().isEmpty()) {
            studentName.setText(defFields[0]);
            studentName.setFont(new Font("SansSerif", Font.ITALIC, 12));
            studentName.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_studentNameFocusLost

    private void studentNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentNameKeyPressed
        if (studentName.getText().equals(defFields[0])) {
            studentName.setText(null);
            studentName.setFont(new Font("SansSerif", Font.PLAIN, 15));
        }
    }//GEN-LAST:event_studentNameKeyPressed

    private void studentNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentNameKeyTyped
        studentName.setFont(new Font("SansSerif", Font.BOLD, 15));
        studentName.setForeground(Color.WHITE);
    }//GEN-LAST:event_studentNameKeyTyped

    private void studentUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_studentUsernameFocusGained

    }//GEN-LAST:event_studentUsernameFocusGained

    private void studentUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_studentUsernameFocusLost
        if (studentUsername.getText().trim().isEmpty()) {
            studentUsername.setText(defFields[1]);
            studentUsername.setFont(new Font("SansSerif", Font.ITALIC, 12));
            studentUsername.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_studentUsernameFocusLost

    private void studentUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentUsernameKeyPressed
        if (studentUsername.getText().equals(defFields[1])) {
            studentUsername.setText(null);
            studentUsername.setFont(new Font("SansSerif", Font.PLAIN, 15));
        }
    }//GEN-LAST:event_studentUsernameKeyPressed

    private void studentUsernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentUsernameKeyTyped
        studentUsername.setFont(new Font("SansSerif", Font.BOLD, 15));
        studentUsername.setForeground(Color.WHITE);
    }//GEN-LAST:event_studentUsernameKeyTyped

    private void studentPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_studentPasswordFocusLost
        if (studentPassword.getText().trim().isEmpty()) {
            studentPassword.setText(defFields[2]);
            studentPassword.setEchoChar((char) 0);
            studentPassword.setFont(new Font("SansSerif", Font.ITALIC, 12));
            studentPassword.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_studentPasswordFocusLost

    private void studentPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentPasswordKeyPressed
        if (studentPassword.getText().equals(defFields[2])) {
            studentPassword.setText(null);
            studentPassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
        }
    }//GEN-LAST:event_studentPasswordKeyPressed

    private void studentPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentPasswordKeyTyped
        studentPassword.setFont(new Font("SansSerif", Font.BOLD, 15));
        studentPassword.setForeground(Color.WHITE);
        studentPassword.setEchoChar('*');
    }//GEN-LAST:event_studentPasswordKeyTyped

    private void studentConfirmPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_studentConfirmPasswordFocusLost
        if (studentConfirmPassword.getText().trim().isEmpty()) {
            studentConfirmPassword.setText(defFields[3]);
            studentConfirmPassword.setEchoChar((char) 0);
            studentConfirmPassword.setFont(new Font("SansSerif", Font.ITALIC, 12));
            studentConfirmPassword.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_studentConfirmPasswordFocusLost

    private void studentConfirmPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentConfirmPasswordKeyPressed
        if (studentConfirmPassword.getText().equals(defFields[3])) {
            studentConfirmPassword.setText(null);
            studentConfirmPassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
        }
    }//GEN-LAST:event_studentConfirmPasswordKeyPressed

    private void studentConfirmPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentConfirmPasswordKeyTyped
        studentConfirmPassword.setFont(new Font("SansSerif", Font.BOLD, 15));
        studentConfirmPassword.setForeground(Color.WHITE);
        studentConfirmPassword.setEchoChar('*');
    }//GEN-LAST:event_studentConfirmPasswordKeyTyped

    private void createAccountButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createAccountButtonMouseClicked
        String fullName = studentName.getText().trim();
        String username = studentUsername.getText().trim();
        String password = new String(studentPassword.getPassword());
        String confirmPassword = new String(studentConfirmPassword.getPassword());
        if (fullName.equals(defFields[0]) || username.equals(defFields[1])
                || password.equals(defFields[2]) || confirmPassword.equals(defFields[3])
                || fullName.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields!");
            return;
        }
        if (confirmPassword.equals(password)) {
            Login register = new Login(null);
            register.createAccount(fullName, username, password);
            defaultFields();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Password doesn't match. Try again!");
            studentConfirmPassword.setText(defFields[3]);
            studentConfirmPassword.setFont(new Font("SansSerif", Font.ITALIC, 12));
            studentConfirmPassword.setForeground(new Color(204, 204, 204));
            studentConfirmPassword.setEchoChar((char) 0);
        }
    }//GEN-LAST:event_createAccountButtonMouseClicked

    private void createAccountButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createAccountButtonMouseEntered

    }//GEN-LAST:event_createAccountButtonMouseEntered

    private void createAccountButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createAccountButtonMouseExited

    }//GEN-LAST:event_createAccountButtonMouseExited

    private void backToMainPageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backToMainPageMouseClicked
        this.dispose();
    }//GEN-LAST:event_backToMainPageMouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new CreateAccount().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CreateAccountLabel1;
    private javax.swing.JLabel LoginLabel;
    private javax.swing.JLabel accountIDNumber;
    private javax.swing.JLabel backToMainPage;
    private javax.swing.JPanel createAccountButton;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField studentConfirmPassword;
    private javax.swing.JTextField studentName;
    private javax.swing.JPasswordField studentPassword;
    private javax.swing.JTextField studentUsername;
    // End of variables declaration//GEN-END:variables
}
