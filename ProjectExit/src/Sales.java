/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.DefaultCellEditor;
import Models.SalesModel;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Models.DatabaseConnection;
import Models.UserModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.codehaus.groovy.syntax.Types;
import org.krysalis.barcode4j.tools.Length;

//Check changes
/**
 *
 * @author it16350342
 */
public class Sales extends javax.swing.JFrame {

    /**
     * Creates new form
     */
    public Sales() {
        initComponents();
        lblQtySum.setText(Integer.toString(getTotalQuantity()));
        lblTotalAmt.setText(Integer.toString(getTotalAmount()));
        fillTableReview();
        fillTableSales();

        tblCreateSO.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.INSERT || e.getType() == TableModelEvent.DELETE || e.getType() == TableModelEvent.UPDATE) {
                    lblQtySum.setText(getTotalQuantity() + "");
                    lblTotalAmt.setText(getTotalAmount() + "");
                }
            }
        });

        tblReviewSales.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {

                    fillTableReview();
                    fillTableSales();

                }
            }
        });
        
        tblReviewSales.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.DELETE) {

                    fillTableReview();
                    fillTableSales();

                }
            }
        });

    }

    public void fillTableReview() {

        DefaultTableModel model = (DefaultTableModel) tblReviewSales.getModel();
        String[] results = new String[7];
        model.setRowCount(0);
        String query = "SELECT * FROM sales_tab";

        try {
            Connection con = dbConnect.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                results[0] = rs.getString("soNumber");
                results[1] = rs.getString("orderedDate");
                results[2] = rs.getString("reqDate");
                results[3] = rs.getString("customerName");
                results[4] = rs.getString("orderCreatedBy");
                results[5] = rs.getString("total");
                results[6] = rs.getString("orderStatus");

                model.addRow(results);
            }
            con.close();
            st.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problem Connectinf to DB");
        }
    }

    public void fillTableSales() {
        DefaultTableModel model = (DefaultTableModel) tblViewSalesOrders.getModel();
        String[] results2 = new String[9];
        model.setRowCount(0);
        String query2 = "SELECT * FROM sales_tab";
        try {
            Connection con = dbConnect.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query2);
            while (rs.next()) {
                results2[0] = rs.getString("soNumber");
                results2[1] = rs.getString("orderedDate");
                results2[2] = rs.getString("reqDate");
                results2[3] = rs.getString("customerName");
                results2[4] = rs.getString("orderCreatedBy");
                results2[5] = rs.getString("salesRep");
                results2[6] = rs.getString("region");
                results2[7] = rs.getString("orderStatus");
                results2[8] = rs.getString("total");

                model.addRow(results2);
            }
            con.close();
            st.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problem Connectinf to DB2");
        }
    }

    public boolean getValidation(int soNum) {

        Connection con = dbConnect.getConnection();
        Statement st;
        ResultSet rs;
        Boolean stat = false;
        String req = "SELECT soNumber FROM sales_tab where soNumber = " + soNum + "";
        try {
            st = con.createStatement();
            rs = st.executeQuery(req);
            rs.isBeforeFirst();
            stat = rs.isBeforeFirst();;

        } catch (Exception e) {
        }
        return stat;
    }

    public void searchFrom(String s) {
        Connection con = dbConnect.getConnection();

        Date dateFilterFromTemp = dpFrom.getDate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        search1 = df.format(dateFilterFromTemp);

        DefaultTableModel model = (DefaultTableModel) tblReviewSales.getModel();
        model.setRowCount(0);

        String[] results = new String[7];

        String query = "SELECT * FROM sales_tab WHERE CONCAT(reqDate) > '" + search1 + "';";
        try {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                results[0] = rs.getString("soNumber");
                results[1] = rs.getString("orderedDate");
                results[2] = rs.getString("reqDate");
                results[3] = rs.getString("customerName");
                results[4] = rs.getString("orderCreatedBy");
                results[5] = rs.getString("total");
                results[6] = rs.getString("orderStatus");

                model.addRow(results);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problem Connectinf to DB");
        }
    }

    public void searchTo(String s) {

        Connection con = dbConnect.getConnection();

        Date dateFilterToTemp = dpTo.getDate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        search2 = df.format(dateFilterToTemp);

        DefaultTableModel model = (DefaultTableModel) tblReviewSales.getModel();
        model.setRowCount(0);

        String[] results = new String[7];

        String query = "SELECT * FROM sales_tab WHERE CONCAT(reqDate) < '" + search2 + "';";
        try {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                results[0] = rs.getString("soNumber");
                results[1] = rs.getString("orderedDate");
                results[2] = rs.getString("reqDate");
                results[3] = rs.getString("customerName");
                results[4] = rs.getString("orderCreatedBy");
                results[5] = rs.getString("total");
                results[6] = rs.getString("orderStatus");

                model.addRow(results);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problem Connectinf to DB");
        }

    }

    public void searchFrom2(String s) {
        Connection con = dbConnect.getConnection();

        Date dateFilterFromTemp = dpFrom2.getDate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        search1 = df.format(dateFilterFromTemp);

        DefaultTableModel model = (DefaultTableModel) tblViewSalesOrders.getModel();
        model.setRowCount(0);

        String[] results = new String[9];

        String query = "SELECT * FROM sales_tab WHERE CONCAT(reqDate) > '" + search1 + "';";
        try {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                results[0] = rs.getString("soNumber");
                results[1] = rs.getString("orderedDate");
                results[2] = rs.getString("reqDate");
                results[3] = rs.getString("customerName");
                results[4] = rs.getString("orderCreatedBy");
                results[5] = rs.getString("salesRep");
                results[6] = rs.getString("region");
                results[7] = rs.getString("orderStatus");
                results[8] = rs.getString("total");

                model.addRow(results);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problem Connectinf to DB");
        }
    }

    public void searchTo2(String s) {

        Connection con = dbConnect.getConnection();

        Date dateFilterToTemp = dpTo2.getDate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        search2 = df.format(dateFilterToTemp);

        DefaultTableModel model = (DefaultTableModel) tblViewSalesOrders.getModel();
        model.setRowCount(0);

        String[] results = new String[9];

        String query = "SELECT * FROM sales_tab WHERE CONCAT(reqDate) < '" + search2 + "';";
        try {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                results[0] = rs.getString("soNumber");
                results[1] = rs.getString("orderedDate");
                results[2] = rs.getString("reqDate");
                results[3] = rs.getString("customerName");
                results[4] = rs.getString("orderCreatedBy");
                results[5] = rs.getString("salesRep");
                results[6] = rs.getString("region");
                results[7] = rs.getString("orderStatus");
                results[8] = rs.getString("total");

                model.addRow(results);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problem Connectinf to DB");
        }

    }

    DatabaseConnection dbConnect = new DatabaseConnection();

    public String search;
    public String searchCmb1;
    public String searchCmb2;
    public String searchCmb3;
    public String searchCmb4;
    public String search1;
    public String search2;

    public int count;
    public int index;

    public int getTotalQuantity() {
        int rowcount = tblCreateSO.getRowCount();
        int total = rowcount;
        return total;
    }

    public int getTotalAmount() {
        int rowcount = tblCreateSO.getRowCount();
        int total = 0;
        for (int i = 0; i < rowcount; i++) {
            total += Integer.parseInt(tblCreateSO.getValueAt(i, 5).toString());
        }
        return total;
    }

    SalesItemsView viewItems = new SalesItemsView();
    SalesItemsEdit editItems = new SalesItemsEdit();
    SalesItemsAdd addItems = new SalesItemsAdd();
    SalesReviewWindow reviewSales = new SalesReviewWindow();
    SalesReport saleReport = new SalesReport();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jComboBox9 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblUser = new javax.swing.JLabel();
        lblProducts = new javax.swing.JLabel();
        lblPurchase = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtCustomerName = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtCustomerPhone = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSONumber = new javax.swing.JTextField();
        dpReqDate = new org.jdesktop.swingx.JXDatePicker();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        cmbSalesRep = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblCreateSO = new javax.swing.JTable();
        btnCreate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        cmbRegion = new javax.swing.JComboBox<>();
        lblErrorCN = new javax.swing.JLabel();
        lblErrorCP = new javax.swing.JLabel();
        lblErrorRD = new javax.swing.JLabel();
        lblErrorSR = new javax.swing.JLabel();
        lblErrorR = new javax.swing.JLabel();
        lblErrorOS = new javax.swing.JLabel();
        btnAddTab = new javax.swing.JButton();
        btnEditTab = new javax.swing.JButton();
        btnDeleteTab = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblQtySum = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTotalAmt = new javax.swing.JLabel();
        cmbOrderStatus = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        lblErrorSON = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblReviewSales = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        txtSearchSONum = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        dpFrom = new org.jdesktop.swingx.JXDatePicker();
        jLabel16 = new javax.swing.JLabel();
        dpTo = new org.jdesktop.swingx.JXDatePicker();
        btnUpdateSales = new javax.swing.JButton();
        btnDeleteSales = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        cmbSearchSalesRep = new javax.swing.JComboBox<>();
        btnViewTab = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblViewSalesOrders = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        dpFrom2 = new org.jdesktop.swingx.JXDatePicker();
        jLabel26 = new javax.swing.JLabel();
        dpTo2 = new org.jdesktop.swingx.JXDatePicker();
        cmbFilterSalesMan = new javax.swing.JComboBox<>();
        cmbFilerStatus = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        cmbFilterSaleRep2 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblUser.setText("USER");
        lblUser.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUserMouseClicked(evt);
            }
        });

        lblProducts.setText("PRODUCTS");
        lblProducts.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProductsMouseClicked(evt);
            }
        });

        lblPurchase.setText("PURCHASE");
        lblPurchase.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblPurchase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPurchaseMouseClicked(evt);
            }
        });

        jLabel4.setText("SALES");
        jLabel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 102, 51), null, null));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        lblStock.setText("STOCK");
        lblStock.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStockMouseClicked(evt);
            }
        });

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/AUXANO-Logo2.png"))); // NOI18N

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lgoutS.png"))); // NOI18N
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPurchase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(lblUser)
                .addGap(18, 18, 18)
                .addComponent(lblProducts)
                .addGap(18, 18, 18)
                .addComponent(lblPurchase)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(lblStock)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addContainerGap())
        );

        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
            }
        });
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel22.setText("USE THE FORM BELOW TO CREATE SALES ORDERS");

        jPanel17.setBackground(new java.awt.Color(153, 153, 153));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel29.setText("SALES ORDER");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel15.setText("CUSTOMER NAME:");

        jLabel33.setText("CUSTOMER PHONE:");

        jLabel34.setText("REQUIRED DATE:");

        jLabel9.setText("S.O. NUMBER:");

        txtSONumber.setEditable(false);

        jLabel7.setText("SALES REPRESENTATIVE:");

        jLabel8.setText("ORDER CREATED BY:");

        txtUser.setEditable(false);
        txtUser.setText("Salesjid");

        cmbSalesRep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JUGATH", "NAMAL", "SILVA", "JONE" }));
        cmbSalesRep.setSelectedIndex(-1);

        tblCreateSO.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITEM CODE", "ITEM", "BATCH NO", "QUANTITY", "RATE", "SUB TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCreateSO.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblCreateSO);
        if (tblCreateSO.getColumnModel().getColumnCount() > 0) {
            tblCreateSO.getColumnModel().getColumn(0).setResizable(false);
            tblCreateSO.getColumnModel().getColumn(1).setResizable(false);
            tblCreateSO.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(jComboBox9));
            tblCreateSO.getColumnModel().getColumn(2).setResizable(false);
            tblCreateSO.getColumnModel().getColumn(3).setResizable(false);
            tblCreateSO.getColumnModel().getColumn(4).setResizable(false);
            tblCreateSO.getColumnModel().getColumn(5).setResizable(false);
        }

        btnCreate.setText("CREATE");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnClear.setText("CLEAR");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel13.setText("ORDER STATUS:");

        jLabel31.setText("REGION:");

        cmbRegion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NORTH", "SOUTH", "EAST", "WEST" }));
        cmbRegion.setSelectedIndex(-1);

        btnAddTab.setText("ADD");
        btnAddTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTabActionPerformed(evt);
            }
        });

        btnEditTab.setText("EDIT");
        btnEditTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditTabActionPerformed(evt);
            }
        });

        btnDeleteTab.setText("DELETE");
        btnDeleteTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTabActionPerformed(evt);
            }
        });

        jLabel1.setText("NO. OF ITEMS:");

        jLabel2.setText("TOTAL AMOUNT:");

        cmbOrderStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AWAITING FULFILLMENT", "COMPLETED", "CANCELLED" }));
        cmbOrderStatus.setSelectedIndex(-1);

        jButton1.setText("#");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel9)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34))
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCustomerPhone)
                    .addComponent(txtCustomerName)
                    .addComponent(txtSONumber, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dpReqDate, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblErrorCP)
                            .addComponent(lblErrorRD)
                            .addComponent(lblErrorCN)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblErrorSON)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(62, 62, 62))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel31)
                                    .addGap(133, 133, 133)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(87, 87, 87)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbSalesRep, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUser)
                            .addComponent(cmbRegion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbOrderStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblErrorSR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblErrorR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblErrorOS))
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDeleteTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblQtySum)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(lblTotalAmt))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(103, 103, 103))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(cmbSalesRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblErrorSR))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(cmbRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblErrorR))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(lblErrorOS)
                            .addComponent(cmbOrderStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSONumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblErrorSON)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblErrorCN))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(txtCustomerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblErrorCP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(dpReqDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblErrorRD))))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btnAddTab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditTab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteTab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblQtySum)
                    .addComponent(jLabel2)
                    .addComponent(lblTotalAmt))
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(15, 15, 15))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel30.setText("CREATE SALES ORDER");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(200, 200, 200))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("CREATE SALES ORDERS", jPanel4);

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel20.setText("USE THE FORM BELOW TO MANAGE SALES ORDERS");

        jPanel14.setBackground(new java.awt.Color(153, 153, 153));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel21.setText("REVIEW ORDERS");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblReviewSales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.O. NUMBER", "ORDER DATE", "REQUIRED DATE", "CUSTOMER", "SALES MANAGER", "TOTAL", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblReviewSales.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tblReviewSales);
        if (tblReviewSales.getColumnModel().getColumnCount() > 0) {
            tblReviewSales.getColumnModel().getColumn(0).setResizable(false);
            tblReviewSales.getColumnModel().getColumn(1).setResizable(false);
            tblReviewSales.getColumnModel().getColumn(2).setResizable(false);
            tblReviewSales.getColumnModel().getColumn(3).setResizable(false);
            tblReviewSales.getColumnModel().getColumn(4).setResizable(false);
            tblReviewSales.getColumnModel().getColumn(5).setResizable(false);
            tblReviewSales.getColumnModel().getColumn(6).setResizable(false);
        }

        jLabel12.setText("SEARCH:");

        txtSearchSONum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchSONumActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setText("FILTER BY REQUIRED DATE");

        jLabel11.setText("DATE:");

        dpFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dpFromActionPerformed(evt);
            }
        });

        jLabel16.setText("TO");

        dpTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dpToActionPerformed(evt);
            }
        });

        btnUpdateSales.setText("UPDATE");
        btnUpdateSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateSalesActionPerformed(evt);
            }
        });

        btnDeleteSales.setText("DELETE");
        btnDeleteSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSalesActionPerformed(evt);
            }
        });

        jLabel17.setText("SALES REP:");

        cmbSearchSalesRep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "JUGATH", "NAMAL", "SILVA", "JONE" }));
        cmbSearchSalesRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSearchSalesRepActionPerformed(evt);
            }
        });

        btnViewTab.setText("VIEW");
        btnViewTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewTabActionPerformed(evt);
            }
        });

        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dpFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dpTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(114, 114, 114))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 908, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addComponent(btnUpdateSales, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDeleteSales, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnViewTab, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbSearchSalesRep, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(txtSearchSONum, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(82, 82, 82))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txtSearchSONum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(dpFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(dpTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(cmbSearchSalesRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnViewTab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateSales, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteSales, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setText("REVIEW SALES ORDERS");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("REVIEW SALES ORDERS", jPanel7);

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel23.setText("VIEW ALL ORDERS BELOW");

        jPanel16.setBackground(new java.awt.Color(153, 153, 153));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel24.setText("VIEW ORDERS");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblViewSalesOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.O. NUMBER", "ORDERED DATE", "REQ DATE", "CUS NAME", "SALES MAN", "SALES REP", "REGION", "ORDER STATUS", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblViewSalesOrders.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(tblViewSalesOrders);
        if (tblViewSalesOrders.getColumnModel().getColumnCount() > 0) {
            tblViewSalesOrders.getColumnModel().getColumn(0).setResizable(false);
            tblViewSalesOrders.getColumnModel().getColumn(2).setResizable(false);
            tblViewSalesOrders.getColumnModel().getColumn(5).setResizable(false);
            tblViewSalesOrders.getColumnModel().getColumn(6).setResizable(false);
        }

        jButton8.setText("GENERATE SALES REPORT");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel18.setText("SALES MANAGER:");

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel19.setText("FILTER BY REQUIRED DATE");

        jLabel25.setText("DATE:");

        dpFrom2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dpFrom2ActionPerformed(evt);
            }
        });

        jLabel26.setText("TO");

        dpTo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dpTo2ActionPerformed(evt);
            }
        });

        cmbFilterSalesMan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "Salesjid" }));
        cmbFilterSalesMan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFilterSalesManActionPerformed(evt);
            }
        });

        cmbFilerStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "AWAITING FULFILLMENT", "COMPLETED", "CANCELLED" }));
        cmbFilerStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFilerStatusActionPerformed(evt);
            }
        });

        jLabel32.setText("FILTER BY STATUS:");

        jLabel35.setText("SALES REP:");

        cmbFilterSaleRep2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "JUGATH", "NAMAL", "SILVA", "JONE" }));
        cmbFilterSaleRep2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFilterSaleRep2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton8)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 908, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel19Layout.createSequentialGroup()
                                    .addComponent(jLabel25)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dpFrom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel26)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dpTo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel19))
                            .addGap(136, 136, 136)
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel35)
                                .addComponent(jLabel18)
                                .addComponent(jLabel32))
                            .addGap(70, 70, 70)
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbFilterSaleRep2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbFilerStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbFilterSalesMan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(cmbFilerStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(dpFrom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(dpTo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cmbFilterSalesMan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(cmbFilterSaleRep2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel27.setText("VIEW SALES ORDERS");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("VIEW SALES ORDERS", jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jTabbedPane1)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserMouseClicked
        if (UserModel.userRole.equals("ADMIN")) {
            CreateAccount frame = new CreateAccount();
            frame.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "You are not authorized to access this tab.");
        }
    }//GEN-LAST:event_lblUserMouseClicked

    private void lblProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProductsMouseClicked
        if ((UserModel.userRole.equals("STOCK CONTOLLER")) || (UserModel.userRole.equals("ADMIN"))) {
            AddProduct frame = new AddProduct();
            frame.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "You are not authorized to access this tab.");
        }
    }//GEN-LAST:event_lblProductsMouseClicked

    private void lblPurchaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPurchaseMouseClicked
        if ((UserModel.userRole.equals("STOCK CONTROLLER")) || (UserModel.userRole.equals("ADMIN"))) {
            Purchase frame = new Purchase();
            frame.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "You are not authorized to access this tab.");
        }
    }//GEN-LAST:event_lblPurchaseMouseClicked

    private void lblStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStockMouseClicked
        if ((UserModel.userRole.equals("STOCK CONTROLLER")) || (UserModel.userRole.equals("SALES MANAGER")) || (UserModel.userRole.equals("ADMIN"))) {
            Stock frame = new Stock();
            frame.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "You are not authorized to access this tab.");
        }
    }//GEN-LAST:event_lblStockMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:

        txtSONumber.setText("");
        txtCustomerName.setText("");
        txtCustomerPhone.setText("");
        cmbOrderStatus.setSelectedIndex(-1);
        cmbRegion.setSelectedIndex(-1);
        cmbSalesRep.setSelectedIndex(-1);
        dpReqDate.setDate(null);

        lblErrorSON.setText("");
        lblErrorCN.setText("");
        lblErrorCP.setText("");
        lblErrorSR.setText("");
        lblErrorR.setText("");
        lblErrorOS.setText("");
        lblErrorRD.setText("");

//        tblCreateSO.clearSelection();
        DefaultTableModel model = (DefaultTableModel) tblCreateSO.getModel();
        model.setRowCount(0);


    }//GEN-LAST:event_btnClearActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed

        Connection con = dbConnect.getConnection();

        int soNum;
        int cusPho;
        boolean soNo = false;
        boolean cusPno = false;
        boolean reqDat = false;

        String soNumber = txtSONumber.getText();
        String customerName = txtCustomerName.getText();
        String customerPhone = txtCustomerPhone.getText();
        String salesRep = (String) cmbSalesRep.getSelectedItem();
        String region = (String) cmbRegion.getSelectedItem();
        String orderStatus = (String) cmbOrderStatus.getSelectedItem();
        String orderCreatedBy = "Salesjid";
        Date reqDate = dpReqDate.getDate();

        Date soDate = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String orderDate = df.format(soDate);
        String rd = "";//df.format(reqDate);
        String r = "";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date requiredDate, ordDate;
        try {
            rd = sdf.format(reqDate);
            requiredDate = sdf.parse(rd);
            ordDate = sdf.parse(orderDate);

            if (requiredDate.before(ordDate)) {
                lblErrorRD.setText("*invalid");
            } else {
                r = rd;
                lblErrorRD.setText("");
                reqDat = true;
            }

        } catch (Exception e) {
            lblErrorRD.setText("*invalid");
        }

        try {
            soNum = Integer.parseInt(soNumber);
            if ((soNum > 10000) && (soNum < 1000000)) {
                soNo = true;
            }
        } catch (Exception e) {
        }
        try {
            cusPho = Integer.parseInt(soNumber);
            if (customerPhone.length() == 10) {
                cusPno = true;
                lblErrorCP.setText("");
            } else {
                lblErrorCP.setText("*invalid");
            }
        } catch (Exception e) {
            lblErrorCP.setText("*invalid");
        }

        if (cmbSalesRep.getSelectedIndex() == -1) {
            lblErrorSR.setText("*invalid");
        } else {
            lblErrorSR.setText("");
        }

        if (cmbRegion.getSelectedIndex() == -1) {
            lblErrorR.setText("*invalid");
        } else {
            lblErrorR.setText("");
        }

        if (cmbOrderStatus.getSelectedIndex() == -1) {
            lblErrorOS.setText("*invalid");
        } else {
            lblErrorOS.setText("");
        }

        if (txtCustomerName.getText().equals("")) {
            lblErrorCN.setText("*invalid");
        } else {
            lblErrorCN.setText("");
        }

        String total = lblTotalAmt.getText().toString();

        if (!(txtSONumber.getText().equals("")) && !(txtCustomerName.getText().equals("")) && !(txtCustomerPhone.getText().equals("")) && !(cmbSalesRep.getSelectedIndex() == -1)
                && !(cmbRegion.getSelectedIndex() == -1) && !(cmbOrderStatus.getSelectedIndex() == -1) && !(dpReqDate.getDate() == null)) {

            int rows = tblCreateSO.getRowCount();

            if ((getValidation(Integer.parseInt(soNumber)))) {

                JOptionPane.showMessageDialog(null, "SO Number Already Exists!");

            } else if ((soNo == true) && (cusPno == true) && (reqDat == true)) {

                try {

                    if (tblCreateSO.getRowCount() != 0) {
                        Statement st = con.createStatement();
                        String query = "INSERT INTO sales_tab(orderedDate,customerName,customerPhone,reqDate,salesRep,region,orderCreatedBy,orderStatus,total) VALUES('" + orderDate + "','" + customerName + "','" + customerPhone + "','" + r + "','" + salesRep + "','" + region + "','" + orderCreatedBy + "','" + orderStatus + "','" + total + "')";
                        int execute = st.executeUpdate(query);

                        for (int row = 0; row < rows; row++) {

                            String itemCode = tblCreateSO.getValueAt(row, 0).toString();
                            String itemName = tblCreateSO.getValueAt(row, 1).toString();
                            String batchNum = tblCreateSO.getValueAt(row, 2).toString();
                            int qty = Integer.parseInt(tblCreateSO.getValueAt(row, 3).toString());
                            double rate = Double.parseDouble(tblCreateSO.getValueAt(row, 4).toString());
                            double subt = Double.parseDouble(tblCreateSO.getValueAt(row, 5).toString());

                            String Query2 = "INSERT INTO salesItems_tab(soNumber, prodID, prodName, batchNo, unitPrice, quantity) VALUES('" + soNumber + "','" + itemCode + "','" + itemName + "','" + batchNum + "','" + rate + "','" + qty + "')";
//                    String Query3 = "INSERT INTO stocks_tab (prodID, prodName, quantity) VALUES('" + pId + "', '" + itemName + "', '" + quantity + "') ON DUPLICATE KEY UPDATE  quantity = quantity + '" + quantity + "' ";
                            int execute2 = st.executeUpdate(Query2);
//                    int execute3 = st.executeUpdate(Query3);
                        }

                        JOptionPane.showMessageDialog(rootPane, "Sales order recorded!");
                        txtSONumber.setText("");
                        txtCustomerName.setText("");
                        txtCustomerPhone.setText("");
                        cmbOrderStatus.setSelectedIndex(-1);
                        cmbRegion.setSelectedIndex(-1);
                        cmbSalesRep.setSelectedIndex(-1);
                        dpReqDate.setDate(null);
                        DefaultTableModel model = (DefaultTableModel) tblCreateSO.getModel();
                        model.setRowCount(0);
                    } else {
                        JOptionPane.showMessageDialog(null, "No Items Added");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Enter Correct Values!");
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Fill in the blanks");
        }


    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnAddTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTabActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) tblCreateSO.getModel();

        count = tblCreateSO.getRowCount();
        addItems.setVisible(true);
        addItems.pack();
        addItems.setLocationRelativeTo(null);

        addItems.txtBatchNo.setSelectedIndex(-1);
        addItems.txtItemName.setSelectedIndex(-1);
        addItems.txtQuantity.setText("");


    }//GEN-LAST:event_btnAddTabActionPerformed

    private void btnEditTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditTabActionPerformed
        // TODO add your handling code here:

        index = tblCreateSO.getSelectedRow();
        if (tblCreateSO.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Select a row to edit!");
        } else {

            DefaultTableModel model = (DefaultTableModel) tblCreateSO.getModel();
            editItems.txtItemName.setText(model.getValueAt(tblCreateSO.getSelectedRow(), 1).toString());
            editItems.txtQuantity.setText(model.getValueAt(tblCreateSO.getSelectedRow(), 3).toString());
            editItems.txtBatchNo.setText(model.getValueAt(tblCreateSO.getSelectedRow(), 2).toString());
//            editItems.d1.setText(model.getValueAt(jTable9.getSelectedRow(), 2).toString().substring(0, 2));
//            editItems.m1.setText(model.getValueAt(jTable9.getSelectedRow(), 2).toString().substring(3, 5));
//            editItems.y1.setText(model.getValueAt(jTable9.getSelectedRow(), 2).toString().substring(6, 10));
//
//            editItems.d2.setText(model.getValueAt(jTable9.getSelectedRow(), 3).toString().substring(0, 2));
//            editItems.m2.setText(model.getValueAt(jTable9.getSelectedRow(), 3).toString().substring(3, 5));
//            editItems.y2.setText(model.getValueAt(jTable9.getSelectedRow(), 3).toString().substring(6, 10));
//            editItems.txtBatchNo.setText(model.getValueAt(tblCreateSO.getSelectedRow(), 2).toString());

            editItems.setVisible(true);
            editItems.pack();
            editItems.setLocationRelativeTo(null);
        }

    }//GEN-LAST:event_btnEditTabActionPerformed

    private void btnDeleteTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTabActionPerformed
        // TODO add your handling code here:

        if (tblCreateSO.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Select a row delete!");
        } else {

            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete?", "Delete item", dialogButton);
            if (dialogResult == 0) {
                DefaultTableModel model = (DefaultTableModel) tblCreateSO.getModel();
                model.removeRow(tblCreateSO.getSelectedRow());
            }

        }

    }//GEN-LAST:event_btnDeleteTabActionPerformed

    private void btnViewTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewTabActionPerformed
        // TODO add your handling code here:

        if (tblReviewSales.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Select a row to view!");
        } else {

            DefaultTableModel model = (DefaultTableModel) tblReviewSales.getModel();
            DefaultTableModel model2 = (DefaultTableModel) viewItems.tblViewSalesOrder.getModel();
            String num = model.getValueAt(tblReviewSales.getSelectedRow(), 0).toString();

            viewItems.lblSONum.setText(model.getValueAt(tblReviewSales.getSelectedRow(), 0).toString());
            viewItems.lblDateOfOrder.setText(model.getValueAt(tblReviewSales.getSelectedRow(), 1).toString());
            viewItems.lblRequiredDate.setText(model.getValueAt(tblReviewSales.getSelectedRow(), 2).toString());
            viewItems.lblCustomerName.setText(model.getValueAt(tblReviewSales.getSelectedRow(), 3).toString());
            viewItems.lblSalesManager.setText(model.getValueAt(tblReviewSales.getSelectedRow(), 4).toString());
            viewItems.lblSum.setText(model.getValueAt(tblReviewSales.getSelectedRow(), 5).toString());
            viewItems.lblOrderStatus.setText(model.getValueAt(tblReviewSales.getSelectedRow(), 6).toString());

            String SONum = model.getValueAt(Sales.tblReviewSales.getSelectedRow(), 0).toString();

            String[] results = new String[4];

            String query = "SELECT * FROM salesItems_tab WHERE soNumber=" + SONum + ";";
            String query2 = "SELECT * FROM sales_tab WHERE soNumber=" + SONum + ";";
            try {
                Connection con = dbConnect.getConnection();
                Statement st = con.createStatement();
                Statement st2 = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                ResultSet rs2 = st2.executeQuery(query2);
                if (rs2.next()) {
                    viewItems.lblSalesRep.setText(rs2.getString("salesRep"));
                    viewItems.lblRegion.setText(rs2.getString("region"));
                    viewItems.lblCustomerPhone.setText(rs2.getString("customerPhone"));
                }

                while (rs.next()) {
                    results[0] = rs.getString("prodName");
                    results[1] = rs.getString("batchNo");
                    results[2] = rs.getString("quantity");

                    int price = rs.getInt("unitPrice");
                    int quant = rs.getInt("quantity");
                    int subtotal = price * quant;

                    String subt = Integer.toString(subtotal);

                    results[3] = subt;

                    model2.addRow(results);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

//            viewItems..setText(num);
            viewItems.setVisible(true);
            viewItems.pack();
            viewItems.setLocationRelativeTo(null);

        }

    }//GEN-LAST:event_btnViewTabActionPerformed

    private void btnUpdateSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSalesActionPerformed
        // TODO add your handling code here:

        index = tblReviewSales.getSelectedRow();
        if (tblReviewSales.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Select a row to update!");
        } else {

            DefaultTableModel model = (DefaultTableModel) tblReviewSales.getModel();
//            reviewSales.dpReqDate.setText(model.getValueAt(tblReviewSales.getSelectedRow(),2).toString());

            reviewSales.y1.setText(model.getValueAt(Sales.tblReviewSales.getSelectedRow(), 2).toString().substring(0, 4));
            reviewSales.m1.setText(model.getValueAt(Sales.tblReviewSales.getSelectedRow(), 2).toString().substring(5, 7));
            reviewSales.d1.setText(model.getValueAt(Sales.tblReviewSales.getSelectedRow(), 2).toString().substring(8, 10));
            reviewSales.dpReqDate.setText(model.getValueAt(Sales.tblReviewSales.getSelectedRow(), 0).toString());

            String chkStat = model.getValueAt(tblReviewSales.getSelectedRow(), 6).toString();
            if (chkStat.equals("AWAITING FULFILLMENT")) {

                reviewSales.cmbStatus.setSelectedIndex(0);

            } else if (chkStat.equals("COMPLETED")) {

                reviewSales.cmbStatus.setSelectedIndex(1);

            } else if (chkStat.equals("CANCELLED")) {

                reviewSales.cmbStatus.setSelectedIndex(2);

            }

            reviewSales.setVisible(true);
            reviewSales.pack();
            reviewSales.setLocationRelativeTo(null);
        }

    }//GEN-LAST:event_btnUpdateSalesActionPerformed

    private void btnDeleteSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSalesActionPerformed
        // TODO add your handling code here:

        Connection con = dbConnect.getConnection();

        if (tblReviewSales.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Select a row to delete!");
        } else {

            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Are sure you want to delete?", "Delete item", dialogButton);

            if (dialogResult == 0) {
                DefaultTableModel model = (DefaultTableModel) tblReviewSales.getModel();
                String SONum = model.getValueAt(Sales.tblReviewSales.getSelectedRow(), 0).toString();

                String query = "DELETE FROM sales_tab WHERE soNumber=" + SONum + ";";
                String query2 = "DELETE FROM salesItems_tab WHERE soNumber=" + SONum + ";";
                try {

                    Statement st = con.createStatement();
                    Statement st2 = con.createStatement();
                    int execute2 = st2.executeUpdate(query2);
                    int execute = st.executeUpdate(query);

                    model.removeRow(tblReviewSales.getSelectedRow());
                    JOptionPane.showMessageDialog(rootPane, "Sales Order Deleted Successfully.");
                    fillTableReview();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

            }

        }

    }//GEN-LAST:event_btnDeleteSalesActionPerformed

    private void dpFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dpFromActionPerformed
        // TODO add your handling code here:

        Connection con = dbConnect.getConnection();
        DefaultTableModel model = (DefaultTableModel) tblReviewSales.getModel();
        Date dateFilterFromTemp = dpFrom.getDate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        search1 = df.format(dateFilterFromTemp);
        Date given;

        if (search2 != null) {

            searchTo(search2);
            try {
                for (int i = 0; i < tblReviewSales.getRowCount(); i++) {
                    given = df.parse(tblReviewSales.getModel().getValueAt(i, 2).toString());
                    if (df.parse(search1).after(given)) {
                        model.removeRow(i);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        } else {
//            search1 = df.format(dateFilterFromTemp);

            searchFrom(search1);
        }
    }//GEN-LAST:event_dpFromActionPerformed

    private void dpToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dpToActionPerformed
        // TODO add your handling code here:

        Connection con = dbConnect.getConnection();
        DefaultTableModel model = (DefaultTableModel) tblReviewSales.getModel();
        Date dateFilterToTemp = dpTo.getDate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        search2 = df.format(dateFilterToTemp);
        Date given;

        if (search1 != null) {

            searchFrom(search1);
            try {
                for (int i = 0; i < tblReviewSales.getRowCount(); i++) {
                    given = df.parse(tblReviewSales.getModel().getValueAt(i, 2).toString());
                    if (df.parse(search2).before(given)) {
                        model.removeRow(i);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        } else {

//            search2 = df.format(dateFilterToTemp);
            searchTo(search2);

        }
    }//GEN-LAST:event_dpToActionPerformed

    private void txtSearchSONumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchSONumActionPerformed
//        // TODO add your handling code here:
//        
//        String searchSONumber = txtSearchSONum.getText();
//        
//        String[] results = new String[7];   
//        
//        try {
//      
//            String query = "select * from sales_tab where LIKE '%" + searchSONumber + "%';";
//            Connection con = dbConnect.getConnection();
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(query);
//            if (rs.next()) {
//                results[0] = rs.getString("soNumber");
//                results[1] = rs.getString("orderedDate");
//                results[2] = rs.getString("reqDate");
//                results[3] = rs.getString("customerName");
//                results[4] = rs.getString("orderCreatedBy");
//                results[5] = rs.getString("total");
//                results[6] = rs.getString("orderStatus");
//
//                DefaultTableModel model = (DefaultTableModel) tblReviewSales.getModel();
//
//                model.addRow(results);
//            }
//        } catch (Exception e) {
//        }

    }//GEN-LAST:event_txtSearchSONumActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        // TODO add your handling code here:
        search = txtSearchSONum.getText();
        if (search == "") {
            JOptionPane.showMessageDialog(rootPane, "Search box empty!");
        }
        DefaultTableModel model = (DefaultTableModel) tblReviewSales.getModel();
        model.setRowCount(0);

        String[] results = new String[7];

        String query = "SELECT * FROM sales_tab WHERE CONCAT(soNumber,customerName,customerPhone,orderCreatedBy,orderStatus) LIKE '%" + search + "%';";
        try {
            Connection con = dbConnect.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                results[0] = rs.getString("soNumber");
                results[1] = rs.getString("orderedDate");
                results[2] = rs.getString("reqDate");
                results[3] = rs.getString("customerName");
                results[4] = rs.getString("orderCreatedBy");
                results[5] = rs.getString("total");
                results[6] = rs.getString("orderStatus");

                model.addRow(results);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problem Connectinf to DB");
        }

    }//GEN-LAST:event_btnSearchActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        /* Connection con = dbConnect.getConnection();

        DefaultTableModel model = (DefaultTableModel) tblReviewSales.getModel();
        model.setRowCount(0);
        String[] results = new String[7];

        String query = "SELECT * FROM sales_tab";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                results[0] = rs.getString("soNumber");
                results[1] = rs.getString("orderedDate");
                results[2] = rs.getString("reqDate");
                results[3] = rs.getString("customerName");
                results[4] = rs.getString("orderCreatedBy");
                results[5] = rs.getString("total");
                results[6] = rs.getString("orderStatus");

                model.addRow(results);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problem Connectinf to DB");
        }

        DefaultTableModel model2 = (DefaultTableModel) tblViewSalesOrders.getModel();
        model2.setRowCount(0);
        String[] results2 = new String[9];
        String query2 = "SELECT * FROM sales_tab";
        try {
            Statement st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery(query2);
            while (rs2.next()) {
                results2[0] = rs2.getString("soNumber");
                results2[1] = rs2.getString("orderedDate");
                results2[2] = rs2.getString("reqDate");
                results2[3] = rs2.getString("customerName");
                results2[4] = rs2.getString("orderCreatedBy");
                results2[5] = rs2.getString("salesRep");
                results2[6] = rs2.getString("region");
                results2[7] = rs2.getString("orderStatus");
                results2[8] = rs2.getString("total");

                model2.addRow(results2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problem Connectinf to DB2");
        }

        dpTo.setDate(null);
        dpFrom.setDate(null);
        txtSearchSONum.setText("");
        cmbSearchSalesRep.setSelectedIndex(0);
         */
 /*DefaultTableModel model = (DefaultTableModel) tblReviewSales.getModel();
        DefaultTableModel model2 = (DefaultTableModel) tblViewSalesOrders.getModel();
        model.setRowCount(0);
        model2.setRowCount(0);*/

 /*dpTo.setDate(null);
        dpFrom.setDate(null);
        txtSearchSONum.setText("");
        cmbSearchSalesRep.setSelectedIndex(0);
        
        fillTableReview();
        fillTableSales();*/

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int pop = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", pop);
        if (result == 0) {

            UserModel.loginName = "";
            UserModel.userRole = "";

            Login frame = new Login();
            frame.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:

        saleReport.setVisible(true);
        saleReport.pack();
        saleReport.setLocationRelativeTo(null);

    }//GEN-LAST:event_jButton8ActionPerformed

    private void dpFrom2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dpFrom2ActionPerformed
        // TODO add your handling code here:

        Connection con = dbConnect.getConnection();
        DefaultTableModel model = (DefaultTableModel) tblViewSalesOrders.getModel();
        Date dateFilterFromTemp = dpFrom2.getDate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        search1 = df.format(dateFilterFromTemp);
        Date given;

        if (search2 != null) {

            searchTo2(search2);
            try {
                for (int i = 0; i < tblViewSalesOrders.getRowCount(); i++) {
                    given = df.parse(tblViewSalesOrders.getModel().getValueAt(i, 2).toString());
                    if (df.parse(search1).after(given)) {
                        model.removeRow(i);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        } else {
            //            search1 = df.format(dateFilterFromTemp);

            searchFrom2(search1);
        }
    }//GEN-LAST:event_dpFrom2ActionPerformed

    private void dpTo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dpTo2ActionPerformed
        // TODO add your handling code here:

        Connection con = dbConnect.getConnection();
        DefaultTableModel model = (DefaultTableModel) tblViewSalesOrders.getModel();
        Date dateFilterToTemp = dpTo2.getDate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        search2 = df.format(dateFilterToTemp);
        Date given;

        if (search1 != null) {

            searchFrom2(search1);
            try {
                for (int i = 0; i < tblViewSalesOrders.getRowCount(); i++) {
                    given = df.parse(tblViewSalesOrders.getModel().getValueAt(i, 2).toString());
                    if (df.parse(search2).before(given)) {
                        model.removeRow(i);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        } else {

            //            search2 = df.format(dateFilterToTemp);
            searchTo2(search2);

        }
    }//GEN-LAST:event_dpTo2ActionPerformed

    private void cmbSearchSalesRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSearchSalesRepActionPerformed
        // TODO add your handling code here:
        Connection con = dbConnect.getConnection();
        searchCmb1 = (String) cmbSearchSalesRep.getSelectedItem();
//        if (search == "") {
//            JOptionPane.showMessageDialog(rootPane, "Search box empty!");
//        }
        DefaultTableModel model = (DefaultTableModel) tblReviewSales.getModel();
        model.setRowCount(0);

        String[] results = new String[7];
        if (searchCmb1 != "NONE") {
            String query = "SELECT * FROM sales_tab WHERE CONCAT(salesRep) LIKE '%" + searchCmb1 + "%';";
            try {

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    results[0] = rs.getString("soNumber");
                    results[1] = rs.getString("orderedDate");
                    results[2] = rs.getString("reqDate");
                    results[3] = rs.getString("customerName");
                    results[4] = rs.getString("orderCreatedBy");
                    results[5] = rs.getString("total");
                    results[6] = rs.getString("orderStatus");

                    model.addRow(results);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Problem Connectinf to DB");
            }
        } else {
            model.setRowCount(0);
            String[] results2 = new String[7];

            String query = "SELECT * FROM sales_tab";
            try {
                Statement st = con.createStatement();
                ResultSet rs2 = st.executeQuery(query);
                while (rs2.next()) {
                    results2[0] = rs2.getString("soNumber");
                    results2[1] = rs2.getString("orderedDate");
                    results2[2] = rs2.getString("reqDate");
                    results2[3] = rs2.getString("customerName");
                    results2[4] = rs2.getString("orderCreatedBy");
                    results2[5] = rs2.getString("total");
                    results2[6] = rs2.getString("orderStatus");

                    model.addRow(results2);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Problem Connectinf to DB");
            }
        }

    }//GEN-LAST:event_cmbSearchSalesRepActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        int val, id = 0;

        try {
            Connection con = dbConnect.getConnection();

            String query = "select max(soNumber) from sales_tab";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No values in the database");
        }
        val = id + 1;
        txtSONumber.setText(val + "");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbFilterSaleRep2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFilterSaleRep2ActionPerformed
        // TODO add your handling code here:

        Connection con = dbConnect.getConnection();
        searchCmb4 = (String) cmbFilterSaleRep2.getSelectedItem();
//        if (search == "") {
//            JOptionPane.showMessageDialog(rootPane, "Search box empty!");
//        }
        DefaultTableModel model = (DefaultTableModel) tblViewSalesOrders.getModel();
        model.setRowCount(0);

        String[] results = new String[9];
        if (searchCmb4 != "NONE") {
            String query = "SELECT * FROM sales_tab WHERE CONCAT(salesRep) LIKE '%" + searchCmb4 + "%';";
            try {

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    results[0] = rs.getString("soNumber");
                    results[1] = rs.getString("orderedDate");
                    results[2] = rs.getString("reqDate");
                    results[3] = rs.getString("customerName");
                    results[4] = rs.getString("orderCreatedBy");
                    results[5] = rs.getString("salesRep");
                    results[6] = rs.getString("region");
                    results[7] = rs.getString("orderStatus");
                    results[8] = rs.getString("total");

                    model.addRow(results);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Problem Connectinf to DB");
            }
        } else {
            model.setRowCount(0);
            String[] results2 = new String[9];

            String query = "SELECT * FROM sales_tab";
            try {
                Statement st = con.createStatement();
                ResultSet rs2 = st.executeQuery(query);
                while (rs2.next()) {
                    results2[0] = rs2.getString("soNumber");
                    results2[1] = rs2.getString("orderedDate");
                    results2[2] = rs2.getString("reqDate");
                    results2[3] = rs2.getString("customerName");
                    results2[4] = rs2.getString("orderCreatedBy");
                    results2[5] = rs2.getString("salesRep");
                    results2[6] = rs2.getString("region");
                    results2[7] = rs2.getString("orderStatus");
                    results2[8] = rs2.getString("total");

                    model.addRow(results2);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Problem Connectinf to DB");
            }
        }

    }//GEN-LAST:event_cmbFilterSaleRep2ActionPerformed

    private void cmbFilerStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFilerStatusActionPerformed
        // TODO add your handling code here:
        Connection con = dbConnect.getConnection();
        searchCmb2 = (String) cmbFilerStatus.getSelectedItem();
//        if (search == "") {
//            JOptionPane.showMessageDialog(rootPane, "Search box empty!");
//        }
        DefaultTableModel model = (DefaultTableModel) tblViewSalesOrders.getModel();
        model.setRowCount(0);

        String[] results = new String[9];
        if (searchCmb2 != "NONE") {
            String query = "SELECT * FROM sales_tab WHERE CONCAT(orderStatus) LIKE '%" + searchCmb2 + "%';";
            try {

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    results[0] = rs.getString("soNumber");
                    results[1] = rs.getString("orderedDate");
                    results[2] = rs.getString("reqDate");
                    results[3] = rs.getString("customerName");
                    results[4] = rs.getString("orderCreatedBy");
                    results[5] = rs.getString("salesRep");
                    results[6] = rs.getString("region");
                    results[7] = rs.getString("orderStatus");
                    results[8] = rs.getString("total");

                    model.addRow(results);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Problem Connectinf to DB");
            }
        } else {
            model.setRowCount(0);
            String[] results2 = new String[9];

            String query = "SELECT * FROM sales_tab";
            try {
                Statement st = con.createStatement();
                ResultSet rs2 = st.executeQuery(query);
                while (rs2.next()) {
                    results2[0] = rs2.getString("soNumber");
                    results2[1] = rs2.getString("orderedDate");
                    results2[2] = rs2.getString("reqDate");
                    results2[3] = rs2.getString("customerName");
                    results2[4] = rs2.getString("orderCreatedBy");
                    results2[5] = rs2.getString("salesRep");
                    results2[6] = rs2.getString("region");
                    results2[7] = rs2.getString("orderStatus");
                    results2[8] = rs2.getString("total");

                    model.addRow(results2);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Problem Connectinf to DB");
            }
        }


    }//GEN-LAST:event_cmbFilerStatusActionPerformed

    private void cmbFilterSalesManActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFilterSalesManActionPerformed
        // TODO add your handling code here:
        Connection con = dbConnect.getConnection();
        searchCmb3 = (String) cmbFilterSalesMan.getSelectedItem();
//        if (search == "") {
//            JOptionPane.showMessageDialog(rootPane, "Search box empty!");
//        }
        DefaultTableModel model = (DefaultTableModel) tblViewSalesOrders.getModel();
        model.setRowCount(0);

        String[] results = new String[9];
        if (searchCmb3 != "NONE") {
            String query = "SELECT * FROM sales_tab WHERE CONCAT(orderCreatedBy) LIKE '%" + searchCmb3 + "%';";
            try {

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    results[0] = rs.getString("soNumber");
                    results[1] = rs.getString("orderedDate");
                    results[2] = rs.getString("reqDate");
                    results[3] = rs.getString("customerName");
                    results[4] = rs.getString("orderCreatedBy");
                    results[5] = rs.getString("salesRep");
                    results[6] = rs.getString("region");
                    results[7] = rs.getString("orderStatus");
                    results[8] = rs.getString("total");

                    model.addRow(results);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Problem Connectinf to DB");
            }
        } else {
            model.setRowCount(0);
            String[] results2 = new String[9];

            String query = "SELECT * FROM sales_tab";
            try {
                Statement st = con.createStatement();
                ResultSet rs2 = st.executeQuery(query);
                while (rs2.next()) {
                    results2[0] = rs2.getString("soNumber");
                    results2[1] = rs2.getString("orderedDate");
                    results2[2] = rs2.getString("reqDate");
                    results2[3] = rs2.getString("customerName");
                    results2[4] = rs2.getString("orderCreatedBy");
                    results2[5] = rs2.getString("salesRep");
                    results2[6] = rs2.getString("region");
                    results2[7] = rs2.getString("orderStatus");
                    results2[8] = rs2.getString("total");

                    model.addRow(results2);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Problem Connectinf to DB");
            }
        }


    }//GEN-LAST:event_cmbFilterSalesManActionPerformed

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
        // TODO add your handling code here:


    }//GEN-LAST:event_jTabbedPane1FocusGained

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        int pop = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", pop);
        if (result == 0) {

            UserModel.loginName = "";
            UserModel.userRole = "";

            Login frame = new Login();
            frame.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButton7MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddTab;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDeleteSales;
    private javax.swing.JButton btnDeleteTab;
    private javax.swing.JButton btnEditTab;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdateSales;
    private javax.swing.JButton btnViewTab;
    public javax.swing.JComboBox<String> cmbFilerStatus;
    public javax.swing.JComboBox<String> cmbFilterSaleRep2;
    public javax.swing.JComboBox<String> cmbFilterSalesMan;
    private javax.swing.JComboBox<String> cmbOrderStatus;
    private javax.swing.JComboBox<String> cmbRegion;
    private javax.swing.JComboBox<String> cmbSalesRep;
    private javax.swing.JComboBox<String> cmbSearchSalesRep;
    public org.jdesktop.swingx.JXDatePicker dpFrom;
    private org.jdesktop.swingx.JXDatePicker dpFrom2;
    private org.jdesktop.swingx.JXDatePicker dpReqDate;
    public org.jdesktop.swingx.JXDatePicker dpTo;
    private org.jdesktop.swingx.JXDatePicker dpTo2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblErrorCN;
    private javax.swing.JLabel lblErrorCP;
    private javax.swing.JLabel lblErrorOS;
    private javax.swing.JLabel lblErrorR;
    private javax.swing.JLabel lblErrorRD;
    private javax.swing.JLabel lblErrorSON;
    private javax.swing.JLabel lblErrorSR;
    private javax.swing.JLabel lblProducts;
    private javax.swing.JLabel lblPurchase;
    private javax.swing.JLabel lblQtySum;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblTotalAmt;
    private javax.swing.JLabel lblUser;
    public static javax.swing.JTable tblCreateSO;
    public static javax.swing.JTable tblReviewSales;
    public static javax.swing.JTable tblViewSalesOrders;
    public javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtCustomerPhone;
    public javax.swing.JTextField txtSONumber;
    private javax.swing.JTextField txtSearchSONum;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
