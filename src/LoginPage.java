

import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LoginPage extends javax.swing.JFrame {

    String[] defUser = {"Username", "Admin Username"};
    String[] defPass = {"Password", "Admin Password"};
    DB_CONNECTION DB = new DB_CONNECTION();
    Color beige = new Color(229, 221, 209);
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoginPage.class.getName());

    public LoginPage() {
        //setUndecorated(true);
        initComponents();
        setResizable(false);
        setSize(1200, 775);
        setTitle("Login & Register");
        getContentPane().setBackground(beige);
        resetField();
    }

    void resetField() {
        //student
        studentUsername.setText(defUser[0]);
        studentPassword.setText(defPass[0]);
        studentUsername.setFont(new Font("SansSerif", Font.ITALIC, 12));
        studentUsername.setForeground(new Color(204, 204, 204));
        studentPassword.setFont(new Font("SansSerif", Font.ITALIC, 12));
        studentPassword.setForeground(new Color(204, 204, 204));
        studentPassword.setEchoChar((char) 0);

        //staff
        staffUsername.setText(defUser[0]);
        staffPassword.setText(defPass[0]);
        staffUsername.setFont(new Font("SansSerif", Font.ITALIC, 12));
        staffUsername.setForeground(new Color(204, 204, 204));
        staffPassword.setFont(new Font("SansSerif", Font.ITALIC, 12));
        staffPassword.setForeground(new Color(204, 204, 204));
        staffPassword.setEchoChar((char) 0);

        //admin
        adminUsername.setText(defUser[1]);
        adminPassword.setText(defPass[1]);
        adminUsername.setFont(new Font("SansSerif", Font.ITALIC, 12));
        adminUsername.setForeground(new Color(204, 204, 204));
        adminPassword.setFont(new Font("SansSerif", Font.ITALIC, 12));
        adminPassword.setForeground(new Color(204, 204, 204));
        adminPassword.setEchoChar((char) 0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        PassengerorAdminPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        staffLabelBG = new javax.swing.JPanel();
        staffLabel = new javax.swing.JLabel();
        adminLabelBG = new javax.swing.JPanel();
        adminLabel = new javax.swing.JLabel();
        studentLabelBG = new javax.swing.JPanel();
        studentLabel = new javax.swing.JLabel();
        ParentPanel = new javax.swing.JPanel();
        StudentPanel = new javax.swing.JPanel();
        studentUsername = new javax.swing.JTextField();
        studentPassword = new javax.swing.JPasswordField();
        studentLoginButton = new javax.swing.JPanel();
        LoginLabel = new javax.swing.JLabel();
        createAccountButton1 = new javax.swing.JLabel();
        AdminPanel = new javax.swing.JPanel();
        adminUsername = new javax.swing.JTextField();
        adminPassword = new javax.swing.JPasswordField();
        adminLoginButton = new javax.swing.JPanel();
        LoginLabel1 = new javax.swing.JLabel();
        StaffPanel = new javax.swing.JPanel();
        staffUsername = new javax.swing.JTextField();
        staffPassword = new javax.swing.JPasswordField();
        staffLoginButton = new javax.swing.JPanel();
        LoginLabel2 = new javax.swing.JLabel();
        createAccountButton2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(191, 207, 187));

        jPanel1.setBackground(new java.awt.Color(52, 76, 61));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 775));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 775, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(115, 138, 110));

        PassengerorAdminPanel.setBackground(new java.awt.Color(115, 138, 110));

        jPanel3.setBackground(new java.awt.Color(115, 138, 110));
        jPanel3.setToolTipText("");

        staffLabelBG.setBackground(new java.awt.Color(142, 165, 140));
        staffLabelBG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staffLabelBGMouseClicked(evt);
            }
        });

        staffLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        staffLabel.setForeground(new java.awt.Color(51, 51, 51));
        staffLabel.setText("STAFF");
        staffLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staffLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout staffLabelBGLayout = new javax.swing.GroupLayout(staffLabelBG);
        staffLabelBG.setLayout(staffLabelBGLayout);
        staffLabelBGLayout.setHorizontalGroup(
            staffLabelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, staffLabelBGLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(staffLabel)
                .addGap(28, 28, 28))
        );
        staffLabelBGLayout.setVerticalGroup(
            staffLabelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, staffLabelBGLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(staffLabel)
                .addContainerGap())
        );

        adminLabelBG.setBackground(new java.awt.Color(142, 165, 140));
        adminLabelBG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminLabelBGMouseClicked(evt);
            }
        });

        adminLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        adminLabel.setForeground(new java.awt.Color(51, 51, 51));
        adminLabel.setText("ADMIN");
        adminLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout adminLabelBGLayout = new javax.swing.GroupLayout(adminLabelBG);
        adminLabelBG.setLayout(adminLabelBGLayout);
        adminLabelBGLayout.setHorizontalGroup(
            adminLabelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminLabelBGLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(adminLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        adminLabelBGLayout.setVerticalGroup(
            adminLabelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminLabelBGLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(adminLabel)
                .addContainerGap())
        );

        studentLabelBG.setBackground(new java.awt.Color(52, 76, 61));
        studentLabelBG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentLabelBGMouseClicked(evt);
            }
        });

        studentLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        studentLabel.setForeground(new java.awt.Color(255, 255, 255));
        studentLabel.setText("STUDENT");
        studentLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout studentLabelBGLayout = new javax.swing.GroupLayout(studentLabelBG);
        studentLabelBG.setLayout(studentLabelBGLayout);
        studentLabelBGLayout.setHorizontalGroup(
            studentLabelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentLabelBGLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(studentLabel)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        studentLabelBGLayout.setVerticalGroup(
            studentLabelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, studentLabelBGLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(studentLabel)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(studentLabelBG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(staffLabelBG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminLabelBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(staffLabelBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(studentLabelBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(adminLabelBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PassengerorAdminPanelLayout = new javax.swing.GroupLayout(PassengerorAdminPanel);
        PassengerorAdminPanel.setLayout(PassengerorAdminPanelLayout);
        PassengerorAdminPanelLayout.setHorizontalGroup(
            PassengerorAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PassengerorAdminPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );
        PassengerorAdminPanelLayout.setVerticalGroup(
            PassengerorAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PassengerorAdminPanelLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ParentPanel.setBackground(new java.awt.Color(115, 138, 110));
        ParentPanel.setLayout(new java.awt.CardLayout());

        StudentPanel.setBackground(new java.awt.Color(115, 138, 110));

        studentUsername.setBackground(new java.awt.Color(102, 102, 102));
        studentUsername.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        studentUsername.setForeground(new java.awt.Color(0, 0, 0));
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
        studentPassword.setForeground(new java.awt.Color(0, 0, 0));
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

        studentLoginButton.setBackground(new java.awt.Color(255, 200, 0));
        studentLoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentLoginButtonMouseClicked(evt);
            }
        });

        LoginLabel.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        LoginLabel.setForeground(new java.awt.Color(51, 51, 51));
        LoginLabel.setText("Log In");

        javax.swing.GroupLayout studentLoginButtonLayout = new javax.swing.GroupLayout(studentLoginButton);
        studentLoginButton.setLayout(studentLoginButtonLayout);
        studentLoginButtonLayout.setHorizontalGroup(
            studentLoginButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentLoginButtonLayout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(LoginLabel)
                .addContainerGap(131, Short.MAX_VALUE))
        );
        studentLoginButtonLayout.setVerticalGroup(
            studentLoginButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentLoginButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LoginLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        createAccountButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        createAccountButton1.setForeground(new java.awt.Color(0, 0, 0));
        createAccountButton1.setText("Forgot Password");

        javax.swing.GroupLayout StudentPanelLayout = new javax.swing.GroupLayout(StudentPanel);
        StudentPanel.setLayout(StudentPanelLayout);
        StudentPanelLayout.setHorizontalGroup(
            StudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StudentPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(StudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(studentPassword)
                    .addComponent(studentUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addComponent(studentLoginButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
            .addGroup(StudentPanelLayout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(createAccountButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        StudentPanelLayout.setVerticalGroup(
            StudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StudentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(studentUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(studentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(studentLoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createAccountButton1)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        ParentPanel.add(StudentPanel, "card2");

        AdminPanel.setBackground(new java.awt.Color(115, 138, 110));

        adminUsername.setBackground(new java.awt.Color(102, 102, 102));
        adminUsername.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        adminUsername.setForeground(new java.awt.Color(0, 0, 0));
        adminUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        adminUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                adminUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                adminUsernameFocusLost(evt);
            }
        });
        adminUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                adminUsernameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                adminUsernameKeyTyped(evt);
            }
        });

        adminPassword.setBackground(new java.awt.Color(102, 102, 102));
        adminPassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        adminPassword.setForeground(new java.awt.Color(0, 0, 0));
        adminPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        adminPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                adminPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                adminPasswordFocusLost(evt);
            }
        });
        adminPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                adminPasswordKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                adminPasswordKeyTyped(evt);
            }
        });

        adminLoginButton.setBackground(new java.awt.Color(255, 200, 0));
        adminLoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminLoginButtonMouseClicked(evt);
            }
        });

        LoginLabel1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        LoginLabel1.setForeground(new java.awt.Color(51, 51, 51));
        LoginLabel1.setText("Log In");

        javax.swing.GroupLayout adminLoginButtonLayout = new javax.swing.GroupLayout(adminLoginButton);
        adminLoginButton.setLayout(adminLoginButtonLayout);
        adminLoginButtonLayout.setHorizontalGroup(
            adminLoginButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminLoginButtonLayout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(LoginLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        adminLoginButtonLayout.setVerticalGroup(
            adminLoginButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminLoginButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LoginLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout AdminPanelLayout = new javax.swing.GroupLayout(AdminPanel);
        AdminPanel.setLayout(AdminPanelLayout);
        AdminPanelLayout.setHorizontalGroup(
            AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(adminPassword)
                    .addComponent(adminUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addComponent(adminLoginButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );
        AdminPanelLayout.setVerticalGroup(
            AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(adminUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(adminPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(adminLoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        ParentPanel.add(AdminPanel, "card3");

        StaffPanel.setBackground(new java.awt.Color(115, 138, 110));

        staffUsername.setBackground(new java.awt.Color(102, 102, 102));
        staffUsername.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        staffUsername.setForeground(new java.awt.Color(0, 0, 0));
        staffUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        staffUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                staffUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                staffUsernameFocusLost(evt);
            }
        });
        staffUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                staffUsernameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                staffUsernameKeyTyped(evt);
            }
        });

        staffPassword.setBackground(new java.awt.Color(102, 102, 102));
        staffPassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        staffPassword.setForeground(new java.awt.Color(0, 0, 0));
        staffPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        staffPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                staffPasswordFocusLost(evt);
            }
        });
        staffPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffPasswordActionPerformed(evt);
            }
        });
        staffPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                staffPasswordKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                staffPasswordKeyTyped(evt);
            }
        });

        staffLoginButton.setBackground(new java.awt.Color(255, 200, 0));
        staffLoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staffLoginButtonMouseClicked(evt);
            }
        });

        LoginLabel2.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        LoginLabel2.setForeground(new java.awt.Color(51, 51, 51));
        LoginLabel2.setText("Log In");

        javax.swing.GroupLayout staffLoginButtonLayout = new javax.swing.GroupLayout(staffLoginButton);
        staffLoginButton.setLayout(staffLoginButtonLayout);
        staffLoginButtonLayout.setHorizontalGroup(
            staffLoginButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staffLoginButtonLayout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(LoginLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        staffLoginButtonLayout.setVerticalGroup(
            staffLoginButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staffLoginButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LoginLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        createAccountButton2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        createAccountButton2.setForeground(new java.awt.Color(0, 0, 0));
        createAccountButton2.setText("Forgot Password");

        javax.swing.GroupLayout StaffPanelLayout = new javax.swing.GroupLayout(StaffPanel);
        StaffPanel.setLayout(StaffPanelLayout);
        StaffPanelLayout.setHorizontalGroup(
            StaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StaffPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(StaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(staffPassword)
                    .addComponent(staffUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addComponent(staffLoginButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StaffPanelLayout.createSequentialGroup()
                .addContainerGap(154, Short.MAX_VALUE)
                .addComponent(createAccountButton2)
                .addGap(151, 151, 151))
        );
        StaffPanelLayout.setVerticalGroup(
            StaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StaffPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(staffUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(staffPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createAccountButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(staffLoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        ParentPanel.add(StaffPanel, "card4");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PassengerorAdminPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(PassengerorAdminPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void studentUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_studentUsernameFocusGained

    }//GEN-LAST:event_studentUsernameFocusGained

    private void studentUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_studentUsernameFocusLost
        if (studentUsername.getText().trim().isEmpty()) {
            studentUsername.setText(defUser[0]);
            studentUsername.setFont(new Font("SansSerif", Font.ITALIC, 12));
            studentUsername.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_studentUsernameFocusLost

    private void studentUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentUsernameKeyPressed
        if (studentUsername.getText().equals(defUser[0])) {
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
            studentPassword.setText(defPass[0]);
            studentPassword.setEchoChar((char) 0);
            studentPassword.setFont(new Font("SansSerif", Font.ITALIC, 12));
            studentPassword.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_studentPasswordFocusLost

    private void studentPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentPasswordKeyPressed
        if (studentPassword.getText().equals(defPass[0])) {
            studentPassword.setText(null);
            studentPassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
        }
    }//GEN-LAST:event_studentPasswordKeyPressed

    private void studentPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentPasswordKeyTyped
        studentPassword.setFont(new Font("SansSerif", Font.BOLD, 15));
        studentPassword.setForeground(Color.WHITE);
        studentPassword.setEchoChar('*');
    }//GEN-LAST:event_studentPasswordKeyTyped

    private void studentLoginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentLoginButtonMouseClicked
        Login login = new Login(this);
        login.studentLogin(studentUsername.getText().trim(), studentPassword.getText().trim());
    }//GEN-LAST:event_studentLoginButtonMouseClicked

    private void CreateAccountPaneltoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CreateAccountPaneltoButtonMouseClicked
        CreateAccount CA = new CreateAccount();
        CA.setVisible(true);
    }//GEN-LAST:event_CreateAccountPaneltoButtonMouseClicked

    private void CreateAccountPaneltoButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CreateAccountPaneltoButtonMouseEntered
    }//GEN-LAST:event_CreateAccountPaneltoButtonMouseEntered

    private void CreateAccountPaneltoButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CreateAccountPaneltoButtonMouseExited
    }//GEN-LAST:event_CreateAccountPaneltoButtonMouseExited

    private void adminUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adminUsernameFocusGained

    }//GEN-LAST:event_adminUsernameFocusGained

    private void adminUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adminUsernameFocusLost
        if (adminUsername.getText().trim().isEmpty()) {
            adminUsername.setText(defUser[1]);
            adminUsername.setFont(new Font("SansSerif", Font.ITALIC, 12));
            adminUsername.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_adminUsernameFocusLost

    private void adminUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adminUsernameKeyPressed
        if (adminUsername.getText().equals(defUser[1])) {
            adminUsername.setText("");
            adminUsername.setFont(new Font("SansSerif", Font.PLAIN, 15));
        }
    }//GEN-LAST:event_adminUsernameKeyPressed

    private void adminUsernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adminUsernameKeyTyped
        adminUsername.setFont(new Font("SansSerif", Font.BOLD, 15));
        adminUsername.setForeground(Color.WHITE);
    }//GEN-LAST:event_adminUsernameKeyTyped

    private void adminPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adminPasswordFocusLost
        if (adminPassword.getText().trim().isEmpty()) {
            adminPassword.setText(defPass[1]);
            adminPassword.setEchoChar((char) 0);
            adminPassword.setFont(new Font("SansSerif", Font.ITALIC, 12));
            adminPassword.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_adminPasswordFocusLost

    private void adminPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adminPasswordKeyPressed
        if (adminPassword.getText().equals(defPass[1])) {
            adminPassword.setText(null);
            adminPassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
        }
    }//GEN-LAST:event_adminPasswordKeyPressed

    private void adminPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adminPasswordKeyTyped
        adminPassword.setFont(new Font("SansSerif", Font.BOLD, 15));
        adminPassword.setForeground(Color.WHITE);
        adminPassword.setEchoChar('*');
    }//GEN-LAST:event_adminPasswordKeyTyped

    private void adminLoginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminLoginButtonMouseClicked
        Login login = new Login(this);
        login.adminLogin(adminUsername.getText().trim(), adminPassword.getText().trim());
    }//GEN-LAST:event_adminLoginButtonMouseClicked

    private void staffUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_staffUsernameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_staffUsernameFocusGained

    private void staffUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_staffUsernameFocusLost
        if (staffUsername.getText().trim().isEmpty()) {
            staffUsername.setText(defUser[0]);
            staffUsername.setFont(new Font("SansSerif", Font.ITALIC, 12));
            staffUsername.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_staffUsernameFocusLost

    private void staffUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staffUsernameKeyPressed
        if (staffUsername.getText().equals(defUser[0])) {
            staffUsername.setText(null);
            staffUsername.setFont(new Font("SansSerif", Font.PLAIN, 15));
        }
    }//GEN-LAST:event_staffUsernameKeyPressed

    private void staffUsernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staffUsernameKeyTyped
        staffUsername.setFont(new Font("SansSerif", Font.BOLD, 15));
        staffUsername.setForeground(Color.WHITE);
    }//GEN-LAST:event_staffUsernameKeyTyped

    private void staffPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_staffPasswordFocusLost
        if (staffPassword.getText().trim().isEmpty()) {
            staffPassword.setText(defPass[0]);
            staffPassword.setEchoChar((char) 0);
            staffPassword.setFont(new Font("SansSerif", Font.ITALIC, 12));
            staffPassword.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_staffPasswordFocusLost

    private void staffPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staffPasswordKeyPressed
        if (staffPassword.getText().equals(defPass[0])) {
            staffPassword.setText(null);
            staffPassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
        }
    }//GEN-LAST:event_staffPasswordKeyPressed

    private void staffPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staffPasswordKeyTyped
        staffPassword.setFont(new Font("SansSerif", Font.BOLD, 15));
        staffPassword.setForeground(Color.WHITE);
        staffPassword.setEchoChar('*');
    }//GEN-LAST:event_staffPasswordKeyTyped

    private void staffLoginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffLoginButtonMouseClicked
        Login login = new Login(this);
        login.staffLogin(staffUsername.getText().trim(), staffPassword.getText().trim());
    }//GEN-LAST:event_staffLoginButtonMouseClicked

    private void adminLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminLabelMouseClicked
        java.awt.CardLayout cl = (java.awt.CardLayout) ParentPanel.getLayout();
        cl.show(ParentPanel, "card3");
        setTitle("Admin Login");
        adminLabelBG.setBackground(new Color(52, 76, 61));
        adminLabel.setForeground(new Color(255, 255, 255));
        //staff
        staffLabelBG.setBackground(new Color(142, 165, 140));
        staffLabel.setForeground(new Color(51, 51, 51));
        //student
        studentLabelBG.setBackground(new Color(142, 165, 140));
        studentLabel.setForeground(new Color(51, 51, 51));
        resetField();
    }//GEN-LAST:event_adminLabelMouseClicked

    private void studentLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentLabelMouseClicked
        java.awt.CardLayout cl = (java.awt.CardLayout) ParentPanel.getLayout();
        cl.show(ParentPanel, "card2");
        setTitle("Student Login");
        studentLabelBG.setBackground(new Color(52, 76, 61));
        studentLabel.setForeground(new Color(255, 255, 255));
        //staff
        staffLabelBG.setBackground(new Color(142, 165, 140));
        staffLabel.setForeground(new Color(51, 51, 51));
        //admin
        adminLabelBG.setBackground(new Color(142, 165, 140));
        adminLabel.setForeground(new Color(51, 51, 51));
        resetField();

    }//GEN-LAST:event_studentLabelMouseClicked

    private void staffLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffLabelMouseClicked
        java.awt.CardLayout cl = (java.awt.CardLayout) ParentPanel.getLayout();
        cl.show(ParentPanel, "card4");
        setTitle("Staff Login");
        staffLabelBG.setBackground(new Color(52, 76, 61));
        staffLabel.setForeground(new Color(255, 255, 255));
        //student
        studentLabelBG.setBackground(new Color(142, 165, 140));
        studentLabel.setForeground(new Color(51, 51, 51));
        //admin
        adminLabelBG.setBackground(new Color(142, 165, 140));
        adminLabel.setForeground(new Color(51, 51, 51));
        resetField();

    }//GEN-LAST:event_staffLabelMouseClicked

    private void staffPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_staffPasswordActionPerformed

    private void studentLabelBGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentLabelBGMouseClicked
        java.awt.CardLayout cl = (java.awt.CardLayout) ParentPanel.getLayout();
        cl.show(ParentPanel, "card2");
        setTitle("Student Login");
        studentLabelBG.setBackground(new Color(52, 76, 61));
        studentLabel.setForeground(new Color(255, 255, 255));
        //staff
        staffLabelBG.setBackground(new Color(142, 165, 140));
        staffLabel.setForeground(new Color(51, 51, 51));
        //admin
        adminLabelBG.setBackground(new Color(142, 165, 140));
        adminLabel.setForeground(new Color(51, 51, 51));
        resetField();

    }//GEN-LAST:event_studentLabelBGMouseClicked

    private void staffLabelBGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffLabelBGMouseClicked
        java.awt.CardLayout cl = (java.awt.CardLayout) ParentPanel.getLayout();
        cl.show(ParentPanel, "card4");
        setTitle("Staff Login");
        staffLabelBG.setBackground(new Color(52, 76, 61));
        staffLabel.setForeground(new Color(255, 255, 255));
        //student
        studentLabelBG.setBackground(new Color(142, 165, 140));
        studentLabel.setForeground(new Color(51, 51, 51));
        //admin
        adminLabelBG.setBackground(new Color(142, 165, 140));
        adminLabel.setForeground(new Color(51, 51, 51));
        resetField();

    }//GEN-LAST:event_staffLabelBGMouseClicked

    private void adminLabelBGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminLabelBGMouseClicked
        java.awt.CardLayout cl = (java.awt.CardLayout) ParentPanel.getLayout();
        cl.show(ParentPanel, "card3");
        setTitle("Admin Login");
        adminLabelBG.setBackground(new Color(52, 76, 61));
        adminLabel.setForeground(new Color(255, 255, 255));
        //staff
        staffLabelBG.setBackground(new Color(142, 165, 140));
        staffLabel.setForeground(new Color(51, 51, 51));
        //student
        studentLabelBG.setBackground(new Color(142, 165, 140));
        studentLabel.setForeground(new Color(51, 51, 51));
        resetField();

    }//GEN-LAST:event_adminLabelBGMouseClicked

    private void adminPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adminPasswordFocusGained

    }//GEN-LAST:event_adminPasswordFocusGained

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new LoginPage().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdminPanel;
    private javax.swing.JLabel LoginLabel;
    private javax.swing.JLabel LoginLabel1;
    private javax.swing.JLabel LoginLabel2;
    private javax.swing.JPanel ParentPanel;
    private javax.swing.JPanel PassengerorAdminPanel;
    private javax.swing.JPanel StaffPanel;
    private javax.swing.JPanel StudentPanel;
    private javax.swing.JLabel adminLabel;
    private javax.swing.JPanel adminLabelBG;
    private javax.swing.JPanel adminLoginButton;
    private javax.swing.JPasswordField adminPassword;
    private javax.swing.JTextField adminUsername;
    private javax.swing.JLabel createAccountButton1;
    private javax.swing.JLabel createAccountButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel staffLabel;
    private javax.swing.JPanel staffLabelBG;
    private javax.swing.JPanel staffLoginButton;
    private javax.swing.JPasswordField staffPassword;
    private javax.swing.JTextField staffUsername;
    private javax.swing.JLabel studentLabel;
    private javax.swing.JPanel studentLabelBG;
    private javax.swing.JPanel studentLoginButton;
    private javax.swing.JPasswordField studentPassword;
    private javax.swing.JTextField studentUsername;
    // End of variables declaration//GEN-END:variables
}
