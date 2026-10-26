
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CanteenOrderDashboard extends javax.swing.JFrame {

    Color beige = new Color(229, 221, 209);
    Color evergreen = new Color(47, 74, 60);
    Color white = new Color(255, 255, 255);
    DB_CONNECTION DB = new DB_CONNECTION();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CanteenOrderDashboard.class.getName());

    public CanteenOrderDashboard() {
        initComponents();
        setTitle("Canteen Order Dashboard");
        setResizable(false);
        getContentPane().setBackground(beige);
        loadTableData();
        allButton.setBackground(evergreen);
        allButton.setForeground(white);
        //
        menuTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = menuTable.getSelectedRow();
                if (selectedRow != -1) {
                    String itemName = menuTable.getValueAt(selectedRow, 1).toString();
                    double price = Double.parseDouble(menuTable.getValueAt(selectedRow, 3).toString());

                    DefaultTableModel initModel = (DefaultTableModel) initialOrder.getModel();
                    initModel.setRowCount(0); // Clear previous item (only one at a time)
                    initModel.addRow(new Object[]{itemName, price, 1}); // Default QTY set to 0
                }
            }
        });
        //
        orderTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = orderTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Show confirmation dialog
                    int choice = JOptionPane.showConfirmDialog(
                            CanteenOrderDashboard.this,
                            "Update order: Yes or No?",
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION
                    );

                    // Proceed only if the user clicks "Yes"
                    if (choice == JOptionPane.YES_OPTION) {
                        // 1. Get item details from orderTable
                        String itemName = orderTable.getValueAt(selectedRow, 0).toString();
                        double price = Double.parseDouble(orderTable.getValueAt(selectedRow, 1).toString());
                        int quantity = Integer.parseInt(orderTable.getValueAt(selectedRow, 2).toString());

                        // 2. Reflect item and its quantity into initialOrder table
                        DefaultTableModel initModel = (DefaultTableModel) initialOrder.getModel();
                        initModel.setRowCount(0); // Clear initialOrder to hold only one item at a time
                        initModel.addRow(new Object[]{itemName, price, quantity});

                        // 3. Remove the item from orderTable
                        DefaultTableModel orderModel = (DefaultTableModel) orderTable.getModel();
                        orderModel.removeRow(selectedRow);

                        // 4. Recalculate totals and discounts immediately
                        calculateTotals();
                    }
                }
            }
        });
        javax.swing.ToolTipManager.sharedInstance().setInitialDelay(100);
        javax.swing.ToolTipManager.sharedInstance().setDismissDelay(2500);
        eWalletPayment.setToolTipText("E-Wallet Payment");
        cashPayment.setToolTipText("Cash Payment");
        takeOUT.setToolTipText("Take-out Order");
        dineIN.setToolTipText("Dine-in Order");
        allButton.setToolTipText("All Menu");
        ricemealButton.setToolTipText("Rice Meal Menu");
        breakfastButton.setToolTipText("Breakfast Menu");
        noodlesButton.setToolTipText("Noodles Menu");
        meriendaButton.setToolTipText("Merienda Menu");
        beveragesButton.setToolTipText("Drinks / Beverages Menu");
        searchItemButton.setToolTipText("Search Item");
        minusQTY.setToolTipText("Dicrement Quantity");
        addQTY.setToolTipText("Increment Quantity");
        checkoutButton.setToolTipText("Checkout Order");
        removeItem.setToolTipText("Remove Item");
        addItem.setToolTipText("Add Item");
    }

    private void loadFilteredData(String category) {
        DefaultTableModel model = (DefaultTableModel) menuTable.getModel();
        model.setRowCount(0);

        try {
            String query = "SELECT * FROM menu_items WHERE category = ?";
            java.sql.PreparedStatement pstmt = DB.con.prepareStatement(query);
            pstmt.setString(1, category);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("item_id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("stock_quantity")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error Filtering Data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) menuTable.getModel();
        model.setRowCount(0);

        try {
            String query = "SELECT * FROM menu_items";
            ResultSet rs = DB.stat.executeQuery(query);

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("item_id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("stock_quantity")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error Loading Data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clickedButton(javax.swing.JButton button) {
        button.setBackground(evergreen);
        button.setForeground(white);
    }

    public void unClickedButton(javax.swing.JButton button) {
        button.setBackground(white);
        button.setForeground(evergreen);
    }

    private void calculateTotals() {
        DefaultTableModel orderModel = (DefaultTableModel) orderTable.getModel();
        double subtotal = 0.0;
        int totalQuantity = 0;

        // Calculate subtotal and total item count from orderTable
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            double price = Double.parseDouble(orderModel.getValueAt(i, 1).toString());
            int qty = Integer.parseInt(orderModel.getValueAt(i, 2).toString());
            subtotal += (price * qty);
            totalQuantity += qty;
        }

        // Apply 0.01 discount per total item quantity (e.g., 4 items = 0.04, 5 items = 0.05)
        double discountRate = totalQuantity * 0.01;

        double discountAmount = subtotal * discountRate;
        double totalAmount = subtotal - discountAmount;

        // Update labels
        lblSubtotalValue.setText(String.format("₱%.2f", subtotal));
        lblDiscountValue.setText(String.format("-₱%.2f", discountAmount));
        totalAmountLabel.setText(String.format("₱%.2f", totalAmount));
    }

    public void clearOrderTable() {
        DefaultTableModel orderModel = (DefaultTableModel) orderTable.getModel();
        orderModel.setRowCount(0);
        calculateTotals();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jToolBar1 = new javax.swing.JToolBar();
        sidebarPanel = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        btnNavHome = new javax.swing.JButton();
        btnNavMenu = new javax.swing.JButton();
        btnNavHistory = new javax.swing.JButton();
        btnNavOffers = new javax.swing.JButton();
        btnNavReports = new javax.swing.JButton();
        btnNavSettings = new javax.swing.JButton();
        ParentPanel = new javax.swing.JPanel();
        MenuPanel = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        allButton = new javax.swing.JButton();
        ricemealButton = new javax.swing.JButton();
        breakfastButton = new javax.swing.JButton();
        noodlesButton = new javax.swing.JButton();
        meriendaButton = new javax.swing.JButton();
        beveragesButton = new javax.swing.JButton();
        scrollMenu = new javax.swing.JScrollPane();
        menuTable = new javax.swing.JTable();
        searchItemButton = new javax.swing.JButton();
        myOrderPanel = new javax.swing.JPanel();
        lblMyOrder = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        lblSubtotalValue = new javax.swing.JLabel();
        lblDiscount = new javax.swing.JLabel();
        lblDiscountValue = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        totalAmountLabel = new javax.swing.JLabel();
        lblPayments = new javax.swing.JLabel();
        checkoutButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        initialOrder = new javax.swing.JTable();
        scrollOrder = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        addQTY = new javax.swing.JButton();
        minusQTY = new javax.swing.JButton();
        takeOUT = new javax.swing.JButton();
        dineIN = new javax.swing.JButton();
        eWalletPayment = new javax.swing.JButton();
        cashPayment = new javax.swing.JButton();
        addItem = new javax.swing.JButton();
        removeItem = new javax.swing.JButton();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sidebarPanel.setBackground(new java.awt.Color(47, 74, 60));
        sidebarPanel.setLayout(null);

        lblLogo.setBackground(new java.awt.Color(255, 255, 255));
        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(47, 74, 60));
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setText("C");
        lblLogo.setOpaque(true);
        sidebarPanel.add(lblLogo);
        lblLogo.setBounds(15, 25, 50, 49);

        btnNavHome.setBackground(new java.awt.Color(111, 139, 110));
        btnNavHome.setFont(new java.awt.Font("Segoe UI Symbol", 0, 16)); // NOI18N
        btnNavHome.setForeground(new java.awt.Color(255, 255, 255));
        btnNavHome.setText("⌂");
        btnNavHome.setBorderPainted(false);
        btnNavHome.setContentAreaFilled(false);
        btnNavHome.setFocusPainted(false);
        btnNavHome.setOpaque(true);
        sidebarPanel.add(btnNavHome);
        btnNavHome.setBounds(15, 99, 50, 50);

        btnNavMenu.setBackground(new java.awt.Color(111, 139, 110));
        btnNavMenu.setFont(new java.awt.Font("Segoe UI Symbol", 0, 16)); // NOI18N
        btnNavMenu.setForeground(new java.awt.Color(255, 255, 255));
        btnNavMenu.setText("☰");
        btnNavMenu.setBorderPainted(false);
        btnNavMenu.setContentAreaFilled(false);
        btnNavMenu.setFocusPainted(false);
        btnNavMenu.setOpaque(true);
        sidebarPanel.add(btnNavMenu);
        btnNavMenu.setBounds(15, 173, 50, 50);

        btnNavHistory.setBackground(new java.awt.Color(111, 139, 110));
        btnNavHistory.setFont(new java.awt.Font("Segoe UI Symbol", 0, 16)); // NOI18N
        btnNavHistory.setForeground(new java.awt.Color(255, 255, 255));
        btnNavHistory.setText("◷");
        btnNavHistory.setBorderPainted(false);
        btnNavHistory.setContentAreaFilled(false);
        btnNavHistory.setFocusPainted(false);
        btnNavHistory.setOpaque(true);
        sidebarPanel.add(btnNavHistory);
        btnNavHistory.setBounds(15, 248, 50, 50);

        btnNavOffers.setBackground(new java.awt.Color(111, 139, 110));
        btnNavOffers.setFont(new java.awt.Font("Segoe UI Symbol", 0, 16)); // NOI18N
        btnNavOffers.setForeground(new java.awt.Color(255, 255, 255));
        btnNavOffers.setText("✉");
        btnNavOffers.setBorderPainted(false);
        btnNavOffers.setContentAreaFilled(false);
        btnNavOffers.setFocusPainted(false);
        btnNavOffers.setOpaque(true);
        sidebarPanel.add(btnNavOffers);
        btnNavOffers.setBounds(15, 323, 50, 50);

        btnNavReports.setBackground(new java.awt.Color(111, 139, 110));
        btnNavReports.setFont(new java.awt.Font("Segoe UI Symbol", 0, 16)); // NOI18N
        btnNavReports.setForeground(new java.awt.Color(255, 255, 255));
        btnNavReports.setText("▤");
        btnNavReports.setBorderPainted(false);
        btnNavReports.setContentAreaFilled(false);
        btnNavReports.setFocusPainted(false);
        btnNavReports.setOpaque(true);
        sidebarPanel.add(btnNavReports);
        btnNavReports.setBounds(15, 397, 50, 50);

        btnNavSettings.setBackground(new java.awt.Color(111, 139, 110));
        btnNavSettings.setFont(new java.awt.Font("Segoe UI Symbol", 0, 16)); // NOI18N
        btnNavSettings.setForeground(new java.awt.Color(255, 255, 255));
        btnNavSettings.setText("⚙");
        btnNavSettings.setBorderPainted(false);
        btnNavSettings.setContentAreaFilled(false);
        btnNavSettings.setFocusPainted(false);
        btnNavSettings.setOpaque(true);
        sidebarPanel.add(btnNavSettings);
        btnNavSettings.setBounds(15, 471, 50, 50);

        ParentPanel.setBackground(new java.awt.Color(229, 220, 208));
        ParentPanel.setLayout(new java.awt.CardLayout());

        MenuPanel.setBackground(new java.awt.Color(255, 255, 255));

        mainPanel.setBackground(new java.awt.Color(229, 220, 208));

        lblDate.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblDate.setForeground(new java.awt.Color(110, 110, 110));
        lblDate.setText("June 15, 2026");

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(47, 74, 60));
        lblTitle.setText("Filipino Canteen Restaurant");

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txtSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));

        allButton.setBackground(new java.awt.Color(255, 255, 255));
        allButton.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        allButton.setForeground(new java.awt.Color(47, 74, 60));
        allButton.setText("All");
        allButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)));
        allButton.setContentAreaFilled(false);
        allButton.setFocusPainted(false);
        allButton.setFocusable(false);
        allButton.setOpaque(true);
        allButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allButtonMouseClicked(evt);
            }
        });

        ricemealButton.setBackground(new java.awt.Color(255, 255, 255));
        ricemealButton.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        ricemealButton.setForeground(new java.awt.Color(47, 74, 60));
        ricemealButton.setText("Rice Meals");
        ricemealButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)));
        ricemealButton.setContentAreaFilled(false);
        ricemealButton.setFocusPainted(false);
        ricemealButton.setFocusable(false);
        ricemealButton.setOpaque(true);
        ricemealButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ricemealButtonMouseClicked(evt);
            }
        });

        breakfastButton.setBackground(new java.awt.Color(255, 255, 255));
        breakfastButton.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        breakfastButton.setForeground(new java.awt.Color(47, 74, 60));
        breakfastButton.setText("Breakfast");
        breakfastButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)));
        breakfastButton.setContentAreaFilled(false);
        breakfastButton.setFocusPainted(false);
        breakfastButton.setFocusable(false);
        breakfastButton.setOpaque(true);
        breakfastButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                breakfastButtonMouseClicked(evt);
            }
        });

        noodlesButton.setBackground(new java.awt.Color(255, 255, 255));
        noodlesButton.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        noodlesButton.setForeground(new java.awt.Color(47, 74, 60));
        noodlesButton.setText("Noodles");
        noodlesButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)));
        noodlesButton.setContentAreaFilled(false);
        noodlesButton.setFocusPainted(false);
        noodlesButton.setFocusable(false);
        noodlesButton.setOpaque(true);
        noodlesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                noodlesButtonMouseClicked(evt);
            }
        });

        meriendaButton.setBackground(new java.awt.Color(255, 255, 255));
        meriendaButton.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        meriendaButton.setForeground(new java.awt.Color(47, 74, 60));
        meriendaButton.setText("Merienda");
        meriendaButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)));
        meriendaButton.setContentAreaFilled(false);
        meriendaButton.setFocusPainted(false);
        meriendaButton.setFocusable(false);
        meriendaButton.setOpaque(true);
        meriendaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meriendaButtonMouseClicked(evt);
            }
        });

        beveragesButton.setBackground(new java.awt.Color(255, 255, 255));
        beveragesButton.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        beveragesButton.setForeground(new java.awt.Color(47, 74, 60));
        beveragesButton.setText("Drinks / Beverages");
        beveragesButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)));
        beveragesButton.setContentAreaFilled(false);
        beveragesButton.setFocusPainted(false);
        beveragesButton.setFocusable(false);
        beveragesButton.setOpaque(true);
        beveragesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                beveragesButtonMouseClicked(evt);
            }
        });

        menuTable.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        menuTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Item Name", "Category", "Price (₱)", "Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        menuTable.setRowHeight(22);
        scrollMenu.setViewportView(menuTable);

        searchItemButton.setBackground(new java.awt.Color(47, 74, 60));
        searchItemButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        searchItemButton.setForeground(new java.awt.Color(255, 255, 255));
        searchItemButton.setText("Search");
        searchItemButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        searchItemButton.setFocusable(false);
        searchItemButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchItemButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(allButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ricemealButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(breakfastButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(noodlesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(meriendaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(beveragesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchItemButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(15, 15, 15))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchItemButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ricemealButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(breakfastButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noodlesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(meriendaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beveragesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        myOrderPanel.setBackground(new java.awt.Color(255, 255, 255));

        lblMyOrder.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblMyOrder.setForeground(new java.awt.Color(47, 74, 60));
        lblMyOrder.setText("My Order");

        lblSubtotal.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblSubtotal.setForeground(new java.awt.Color(47, 74, 60));
        lblSubtotal.setText("Subtotal");

        lblSubtotalValue.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblSubtotalValue.setForeground(new java.awt.Color(47, 74, 60));
        lblSubtotalValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSubtotalValue.setText("₱0.00");

        lblDiscount.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDiscount.setForeground(new java.awt.Color(47, 74, 60));
        lblDiscount.setText("Discount");

        lblDiscountValue.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDiscountValue.setForeground(new java.awt.Color(35, 140, 90));
        lblDiscountValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDiscountValue.setText("-₱0.00");

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(47, 74, 60));
        lblTotal.setText("Total Amount");

        totalAmountLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        totalAmountLabel.setForeground(new java.awt.Color(35, 140, 90));
        totalAmountLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalAmountLabel.setText("₱0.00");

        lblPayments.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblPayments.setForeground(new java.awt.Color(47, 74, 60));
        lblPayments.setText("Payments");

        checkoutButton.setBackground(new java.awt.Color(35, 140, 90));
        checkoutButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        checkoutButton.setForeground(new java.awt.Color(255, 255, 255));
        checkoutButton.setText("Checkout");
        checkoutButton.setBorderPainted(false);
        checkoutButton.setFocusPainted(false);
        checkoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkoutButtonMouseClicked(evt);
            }
        });

        initialOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Price (₱)", "QTY"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(initialOrder);

        orderTable.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Name", "Price", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderTable.setRowHeight(22);
        scrollOrder.setViewportView(orderTable);

        addQTY.setBackground(new java.awt.Color(102, 255, 102));
        addQTY.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        addQTY.setForeground(new java.awt.Color(0, 0, 0));
        addQTY.setText("+");
        addQTY.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)));
        addQTY.setFocusable(false);
        addQTY.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addQTYMouseClicked(evt);
            }
        });

        minusQTY.setBackground(new java.awt.Color(255, 102, 102));
        minusQTY.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        minusQTY.setForeground(new java.awt.Color(0, 0, 0));
        minusQTY.setText("-");
        minusQTY.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)));
        minusQTY.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        minusQTY.setFocusable(false);
        minusQTY.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minusQTYMouseClicked(evt);
            }
        });

        takeOUT.setBackground(new java.awt.Color(255, 255, 255));
        takeOUT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        takeOUT.setForeground(new java.awt.Color(47, 74, 60));
        takeOUT.setText("Take-out");
        takeOUT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        takeOUT.setFocusable(false);
        takeOUT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                takeOUTMouseClicked(evt);
            }
        });

        dineIN.setBackground(new java.awt.Color(47, 74, 60));
        dineIN.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dineIN.setForeground(new java.awt.Color(255, 255, 255));
        dineIN.setText("Dine-in");
        dineIN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        dineIN.setFocusable(false);
        dineIN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dineINMouseClicked(evt);
            }
        });

        eWalletPayment.setBackground(new java.awt.Color(255, 255, 255));
        eWalletPayment.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        eWalletPayment.setForeground(new java.awt.Color(47, 74, 60));
        eWalletPayment.setText("E Wallet");
        eWalletPayment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        eWalletPayment.setFocusable(false);
        eWalletPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eWalletPaymentMouseClicked(evt);
            }
        });

        cashPayment.setBackground(new java.awt.Color(47, 74, 60));
        cashPayment.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cashPayment.setForeground(new java.awt.Color(255, 255, 255));
        cashPayment.setText("Cash");
        cashPayment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        cashPayment.setFocusable(false);
        cashPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cashPaymentMouseClicked(evt);
            }
        });

        addItem.setBackground(new java.awt.Color(204, 255, 204));
        addItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addItem.setForeground(new java.awt.Color(51, 51, 51));
        addItem.setText("Add Item");
        addItem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        addItem.setFocusable(false);
        addItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addItemMouseClicked(evt);
            }
        });

        removeItem.setBackground(new java.awt.Color(255, 204, 204));
        removeItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        removeItem.setForeground(new java.awt.Color(51, 51, 51));
        removeItem.setText("Remove Item");
        removeItem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        removeItem.setFocusable(false);
        removeItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeItemMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout myOrderPanelLayout = new javax.swing.GroupLayout(myOrderPanel);
        myOrderPanel.setLayout(myOrderPanelLayout);
        myOrderPanelLayout.setHorizontalGroup(
            myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myOrderPanelLayout.createSequentialGroup()
                .addGroup(myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(myOrderPanelLayout.createSequentialGroup()
                        .addGroup(myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(myOrderPanelLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(myOrderPanelLayout.createSequentialGroup()
                                        .addComponent(lblSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(94, 94, 94)
                                        .addComponent(lblSubtotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(myOrderPanelLayout.createSequentialGroup()
                                        .addComponent(lblDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(94, 94, 94)
                                        .addComponent(lblDiscountValue, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(myOrderPanelLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(myOrderPanelLayout.createSequentialGroup()
                                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addComponent(totalAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblPayments, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMyOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, myOrderPanelLayout.createSequentialGroup()
                                            .addComponent(dineIN, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(takeOUT, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                                        .addGroup(myOrderPanelLayout.createSequentialGroup()
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(minusQTY, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                                .addComponent(addQTY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, myOrderPanelLayout.createSequentialGroup()
                                            .addComponent(cashPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(eWalletPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(checkoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myOrderPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollOrder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myOrderPanelLayout.createSequentialGroup()
                                .addComponent(removeItem, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addItem, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        myOrderPanelLayout.setVerticalGroup(
            myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myOrderPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblMyOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(takeOUT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dineIN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(myOrderPanelLayout.createSequentialGroup()
                        .addComponent(addQTY)
                        .addGap(5, 5, 5)
                        .addComponent(minusQTY))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addItem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeItem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSubtotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiscountValue, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(lblPayments, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eWalletPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cashPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(checkoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout MenuPanelLayout = new javax.swing.GroupLayout(MenuPanel);
        MenuPanel.setLayout(MenuPanelLayout);
        MenuPanelLayout.setHorizontalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myOrderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MenuPanelLayout.setVerticalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(myOrderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ParentPanel.add(MenuPanel, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidebarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidebarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void allButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allButtonMouseClicked
        loadTableData();
        clickedButton(allButton);
        unClickedButton(ricemealButton);
        unClickedButton(breakfastButton);
        unClickedButton(noodlesButton);
        unClickedButton(meriendaButton);
        unClickedButton(beveragesButton);
    }//GEN-LAST:event_allButtonMouseClicked

    private void ricemealButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ricemealButtonMouseClicked
        loadFilteredData("Rice Meals / Ulam");
        clickedButton(ricemealButton);
        unClickedButton(allButton);
        unClickedButton(breakfastButton);
        unClickedButton(noodlesButton);
        unClickedButton(meriendaButton);
        unClickedButton(beveragesButton);
    }//GEN-LAST:event_ricemealButtonMouseClicked

    private void breakfastButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_breakfastButtonMouseClicked
        loadFilteredData("All-Day Breakfast / Silog Meals");
        clickedButton(breakfastButton);
        unClickedButton(allButton);
        unClickedButton(ricemealButton);
        unClickedButton(noodlesButton);
        unClickedButton(meriendaButton);
        unClickedButton(beveragesButton);
    }//GEN-LAST:event_breakfastButtonMouseClicked

    private void noodlesButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noodlesButtonMouseClicked
        loadFilteredData("Short Orders / Noodles");
        clickedButton(noodlesButton);
        unClickedButton(allButton);
        unClickedButton(ricemealButton);
        unClickedButton(breakfastButton);
        unClickedButton(meriendaButton);
        unClickedButton(beveragesButton);
    }//GEN-LAST:event_noodlesButtonMouseClicked

    private void meriendaButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meriendaButtonMouseClicked
        loadFilteredData("Snacks / Merienda");
        clickedButton(meriendaButton);
        unClickedButton(allButton);
        unClickedButton(ricemealButton);
        unClickedButton(breakfastButton);
        unClickedButton(noodlesButton);
        unClickedButton(beveragesButton);
    }//GEN-LAST:event_meriendaButtonMouseClicked

    private void beveragesButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beveragesButtonMouseClicked
        loadFilteredData("Drinks / Beverages");
        clickedButton(beveragesButton);
        unClickedButton(allButton);
        unClickedButton(ricemealButton);
        unClickedButton(breakfastButton);
        unClickedButton(noodlesButton);
        unClickedButton(meriendaButton);
    }//GEN-LAST:event_beveragesButtonMouseClicked

    private void addQTYMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addQTYMouseClicked
        DefaultTableModel initModel = (DefaultTableModel) initialOrder.getModel();
        if (initModel.getRowCount() > 0) {
            int currentQty = Integer.parseInt(initModel.getValueAt(0, 2).toString());
            initModel.setValueAt(currentQty + 1, 0, 2);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item first from the menu.", "No Item Selected", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_addQTYMouseClicked

    private void minusQTYMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minusQTYMouseClicked
        DefaultTableModel initModel = (DefaultTableModel) initialOrder.getModel();
        if (initModel.getRowCount() > 0) {
            int currentQty = Integer.parseInt(initModel.getValueAt(0, 2).toString());
            if (currentQty > 0) {
                initModel.setValueAt(currentQty - 1, 0, 2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item first.", "No Item Selected", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_minusQTYMouseClicked

    private void dineINMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dineINMouseClicked
        clickedButton(dineIN);
        unClickedButton(takeOUT);
    }//GEN-LAST:event_dineINMouseClicked

    private void takeOUTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_takeOUTMouseClicked
        clickedButton(takeOUT);
        unClickedButton(dineIN);
    }//GEN-LAST:event_takeOUTMouseClicked

    private void cashPaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cashPaymentMouseClicked
        clickedButton(cashPayment);
        unClickedButton(eWalletPayment);

    }//GEN-LAST:event_cashPaymentMouseClicked

    private void eWalletPaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eWalletPaymentMouseClicked
        clickedButton(eWalletPayment);
        unClickedButton(cashPayment);
    }//GEN-LAST:event_eWalletPaymentMouseClicked

    private void checkoutButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkoutButtonMouseClicked
        String totalAmountToPay = totalAmountLabel.getText();
        PaymentGUI payment = new PaymentGUI(totalAmountToPay, this);
        payment.setVisible(true);
    }//GEN-LAST:event_checkoutButtonMouseClicked

    private void addItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addItemMouseClicked
        DefaultTableModel initModel = (DefaultTableModel) initialOrder.getModel();

        // Ensure there is an item in initialOrder
        if (initModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No item selected in initial order.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String itemName = initModel.getValueAt(0, 0).toString();
        double price = Double.parseDouble(initModel.getValueAt(0, 1).toString());
        int quantity = Integer.parseInt(initModel.getValueAt(0, 2).toString());

        if (quantity <= 0) {
            JOptionPane.showMessageDialog(this, "Quantity must be greater than 0 to add.", "Invalid Quantity", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel orderModel = (DefaultTableModel) orderTable.getModel();

        // Check if item already exists in orderTable, update quantity if it does
        boolean itemExists = false;
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            if (orderModel.getValueAt(i, 0).toString().equals(itemName)) {
                int existingQty = Integer.parseInt(orderModel.getValueAt(i, 2).toString());
                orderModel.setValueAt(existingQty + quantity, i, 2);
                itemExists = true;
                break;
            }
        }

        // Otherwise, add as a new row
        if (!itemExists) {
            orderModel.addRow(new Object[]{itemName, price, quantity});
        }

        // Clear initialOrder after adding
        initModel.setRowCount(0);

        // Recalculate Totals & Discounts
        calculateTotals();
    }//GEN-LAST:event_addItemMouseClicked

    private void removeItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeItemMouseClicked
        DefaultTableModel initModel = (DefaultTableModel) initialOrder.getModel();

        // If there is an item in initialOrder, clear/remove it from there
        if (initModel.getRowCount() > 0) {
            initModel.setRowCount(0);
        } else {
            // Otherwise, remove the selected item from orderTable
            int selectedRow = orderTable.getSelectedRow();
            if (selectedRow != -1) {
                DefaultTableModel orderModel = (DefaultTableModel) orderTable.getModel();
                orderModel.removeRow(selectedRow);

                // Recalculate totals and discounts after removal
                calculateTotals();
            } else {
                JOptionPane.showMessageDialog(this, "Please select an item to remove.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_removeItemMouseClicked

    private void searchItemButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchItemButtonMouseClicked
        String searchText = txtSearch.getText().trim();

        // If the search box is empty, reload all menu items
        if (searchText.isEmpty()) {
            loadTableData();
            return;
        }

        DefaultTableModel model = (DefaultTableModel) menuTable.getModel();
        model.setRowCount(0);

        try {
            // Search items where either the name or category matches the input text
            String query = "SELECT * FROM menu_items WHERE name LIKE ? OR category LIKE ?";
            java.sql.PreparedStatement pstmt = DB.con.prepareStatement(query);
            pstmt.setString(1, "%" + searchText + "%");
            pstmt.setString(2, "%" + searchText + "%");
            ResultSet rs = pstmt.executeQuery();

            boolean hasResults = false;
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("item_id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("stock_quantity")
                };
                model.addRow(row);
                hasResults = true;
            }

            // If no matching items are found, notify the user and restore the full menu
            if (!hasResults) {
                JOptionPane.showMessageDialog(this, "No items found matching \"" + searchText + "\"", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                loadTableData();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error Searching Data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_searchItemButtonMouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new CanteenOrderDashboard().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MenuPanel;
    private javax.swing.JPanel ParentPanel;
    private javax.swing.JButton addItem;
    private javax.swing.JButton addQTY;
    private javax.swing.JButton allButton;
    private javax.swing.JButton beveragesButton;
    private javax.swing.JButton breakfastButton;
    private javax.swing.JButton btnNavHistory;
    private javax.swing.JButton btnNavHome;
    private javax.swing.JButton btnNavMenu;
    private javax.swing.JButton btnNavOffers;
    private javax.swing.JButton btnNavReports;
    private javax.swing.JButton btnNavSettings;
    private javax.swing.JButton cashPayment;
    private javax.swing.JButton checkoutButton;
    private javax.swing.JButton dineIN;
    private javax.swing.JButton eWalletPayment;
    private javax.swing.JTable initialOrder;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDiscount;
    private javax.swing.JLabel lblDiscountValue;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMyOrder;
    private javax.swing.JLabel lblPayments;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblSubtotalValue;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTable menuTable;
    private javax.swing.JButton meriendaButton;
    private javax.swing.JButton minusQTY;
    private javax.swing.JPanel myOrderPanel;
    private javax.swing.JButton noodlesButton;
    private javax.swing.JTable orderTable;
    private javax.swing.JButton removeItem;
    private javax.swing.JButton ricemealButton;
    private javax.swing.JScrollPane scrollMenu;
    private javax.swing.JScrollPane scrollOrder;
    private javax.swing.JButton searchItemButton;
    private javax.swing.JPanel sidebarPanel;
    private javax.swing.JButton takeOUT;
    private javax.swing.JLabel totalAmountLabel;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
