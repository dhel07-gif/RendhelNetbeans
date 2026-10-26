
import java.awt.Color;
import javax.swing.JOptionPane;

public class PaymentGUI extends javax.swing.JFrame {

    Color beige = new Color(229, 221, 209);
    Color evergreen = new Color(47, 74, 60);
    Color white = new Color(255, 255, 255);
    DB_CONNECTION DB = new DB_CONNECTION();
    private CanteenOrderDashboard dashboardRef;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PaymentGUI.class.getName());

    public PaymentGUI() {
        initComponents();
        setResizable(false);
        setTitle("Payment");
    }

    public PaymentGUI(String totalValue, CanteenOrderDashboard dashboard) {
        initComponents();
        setResizable(false);
        setTitle("Payment");
        lblTotalValue.setText(totalValue);
        this.dashboardRef = dashboard;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ParentPanel = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        lblTotalValue = new javax.swing.JLabel();
        lblMyOrder = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        lblDate = new javax.swing.JLabel();
        confirmPayment = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ParentPanel.setBackground(new java.awt.Color(255, 255, 255));

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(47, 74, 60));
        lblTotal.setText("Total Amount");

        lblTotalValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalValue.setForeground(new java.awt.Color(35, 140, 90));
        lblTotalValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalValue.setText("₱0.00");

        lblMyOrder.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblMyOrder.setForeground(new java.awt.Color(47, 74, 60));
        lblMyOrder.setText("Payment");

        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(47, 74, 60)));

        lblDate.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblDate.setForeground(new java.awt.Color(110, 110, 110));
        lblDate.setText("Enter Cash Amount");

        confirmPayment.setBackground(new java.awt.Color(35, 140, 90));
        confirmPayment.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        confirmPayment.setForeground(new java.awt.Color(255, 255, 255));
        confirmPayment.setText("Confirm Payment");
        confirmPayment.setBorderPainted(false);
        confirmPayment.setFocusPainted(false);
        confirmPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmPaymentMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout ParentPanelLayout = new javax.swing.GroupLayout(ParentPanel);
        ParentPanel.setLayout(ParentPanelLayout);
        ParentPanelLayout.setHorizontalGroup(
            ParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ParentPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(ParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(ParentPanelLayout.createSequentialGroup()
                        .addComponent(lblTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(lblTotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMyOrder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(confirmPayment, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        ParentPanelLayout.setVerticalGroup(
            ParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ParentPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblMyOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(lblDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void confirmPaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmPaymentMouseClicked
        try {
            String totalText = lblTotalValue.getText().replace("₱", "").trim();
            double totalAmount = Double.parseDouble(totalText);

            String cashText = jTextField1.getText().trim();
            if (cashText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the cash amount.", "Input Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double cashEntered = Double.parseDouble(cashText);

            if (cashEntered < totalAmount) {
                JOptionPane.showMessageDialog(this, "Insufficient cash! The total amount is ₱" + String.format("%.2f", totalAmount), "Payment Error", JOptionPane.ERROR_MESSAGE);
            } else {
                double change = cashEntered - totalAmount;
                JOptionPane.showMessageDialog(this, "Payment Successful!\nChange: ₱" + String.format("%.2f", change), "Success", JOptionPane.INFORMATION_MESSAGE);
                
                if (dashboardRef != null) {
                    dashboardRef.clearOrderTable();
                }
                
                this.dispose();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric amount for cash.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_confirmPaymentMouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new PaymentGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ParentPanel;
    private javax.swing.JButton confirmPayment;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblMyOrder;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalValue;
    // End of variables declaration//GEN-END:variables
}
