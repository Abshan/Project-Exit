/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Models.PurchaseModel;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import Models.DatabaseConnection;
import Models.UserModel;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Purchase extends javax.swing.JFrame {

    PurchaseItemsEdit editProduct = new PurchaseItemsEdit();
    PurchaseItemsAdd addProduct = new PurchaseItemsAdd();
    PurchaseItemsView viewOrder = new PurchaseItemsView();
    DatabaseConnection dbConnect = new DatabaseConnection();
    PurchaseReport purReport = new PurchaseReport();

    public Purchase() {
        initComponents();
        sum.setText(Double.toString(getSum()));
        ShowPurchases();
    }

    public int index;
    public int count;
    public int order;

    public double getSum() {

        int rowsCount = jTable9.getRowCount();
        double sum = 0;
        for (int i = 0; i < rowsCount; i++) {
            sum = sum + (Double.parseDouble(jTable9.getValueAt(i, 6).toString()));
        }
        return sum;
    }

    public boolean getValidation(int pidVal) {

        Connection con = dbConnect.getConnection();
        Statement st;
        ResultSet rs;
        Boolean r = false;
        String req = "SELECT purNo FROM purchase_tab where purNo = " + pidVal + "";
        try {
            st = con.createStatement();
            rs = st.executeQuery(req);
            rs.isBeforeFirst();
            r = rs.isBeforeFirst();;

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
        }
        return r;
    }

    public ArrayList<PurchaseModel> getOrderList() {

        ArrayList<PurchaseModel> orderList = new ArrayList<PurchaseModel>();
        Connection con = dbConnect.getConnection();
        String query = "SELECT * FROM purchase_tab";

        Statement st;
        ResultSet rs;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            PurchaseModel purchase;

            while (rs.next()) {
                purchase = new PurchaseModel(rs.getInt("purNo"), rs.getString("vendorName"), rs.getString("purchaseDate"), Double.parseDouble(rs.getString("amount")));
                orderList.add(purchase);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return orderList;
    }

    public void ShowPurchases() {

        ArrayList<PurchaseModel> list = getOrderList();
        DefaultTableModel model = (DefaultTableModel) jTable8.getModel();
        model.setRowCount(0);

        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getPurNo();
            row[1] = list.get(i).getVendorName();
            row[2] = list.get(i).getPurchaseDate();
            row[3] = list.get(i).getAmount();

            model.addRow(row);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        lblUser = new javax.swing.JLabel();
        lblProducts = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblSales = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        vn = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pn = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pd = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        sum = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jButton15 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

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

        jLabel3.setText("PURCHASE");
        jLabel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 102, 0), null, null));

        lblSales.setText("SALES");
        lblSales.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblSales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSalesMouseClicked(evt);
            }
        });

        lblStock.setText("STOCK");
        lblStock.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStockMouseClicked(evt);
            }
        });

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(lblUser)
                .addGap(18, 18, 18)
                .addComponent(lblProducts)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(lblSales)
                .addGap(18, 18, 18)
                .addComponent(lblStock)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel22.setText("USE THE FORM BELOW TO RECORD PURCHASE ORDERS");

        jPanel17.setBackground(new java.awt.Color(153, 153, 153));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel29.setText("PURCHASE ORDER");

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

        jLabel15.setText("VENDOR NAME:");

        jLabel9.setText("P.O. NUMBER:");

        jLabel7.setText("PURCHASE DATE:");

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BATCH NO", "PROD ID", "ITEM NAME", "MANF DATE", "EXP DATE", "QUANTITY", "UNIT PRICE", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable9MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTable9);
        if (jTable9.getColumnModel().getColumnCount() > 0) {
            jTable9.getColumnModel().getColumn(1).setResizable(false);
            jTable9.getColumnModel().getColumn(2).setResizable(false);
            jTable9.getColumnModel().getColumn(3).setResizable(false);
            jTable9.getColumnModel().getColumn(4).setResizable(false);
            jTable9.getColumnModel().getColumn(5).setResizable(false);
            jTable9.getColumnModel().getColumn(6).setResizable(false);
            jTable9.getColumnModel().getColumn(7).setResizable(false);
        }

        jButton2.setText("ADD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("EDIT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("DELETE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("CREATE");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel8.setText("SUM:");

        sum.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel9))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(vn, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(133, 133, 133)
                        .addComponent(jLabel7)
                        .addGap(58, 58, 58)
                        .addComponent(pd, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton5))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane10)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(sum, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(29, 29, 29))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel9))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(pn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(vn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)))))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(pd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(sum))
                        .addGap(31, 31, 31)
                        .addComponent(jButton5))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1066, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel30.setText("CREATE PURCHASE ORDER");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(89, 89, 89))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("CREATE PURCHASE ORDER", jPanel4);

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel20.setText("VIEW PURCHASE ORDERS ");

        jPanel14.setBackground(new java.awt.Color(153, 153, 153));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel21.setText("PURCHASE ORDER HISTORY");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(780, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "P.O NUMBER", "VENDOR NAME", "PURCHASE DATE", "AMOUNT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(jTable8);

        jButton15.setText("GENERATE PURCHASE REPORT");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel38.setText("FILTER BY DATE:");

        jLabel39.setText("DATE:");

        jLabel40.setText("TO");

        jLabel17.setText("SEARCH  BY  P.O NUMBER:");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jButton6.setText("VIEW");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("SEARCH");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton15))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addGap(17, 17, 17))))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40)
                    .addComponent(jLabel17)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton15)
                        .addContainerGap())
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jButton6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setText("VIEW PURCHASE ORDERS");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(114, 114, 114))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("VIEW PURCHASE ORDERS", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        DefaultTableModel model = (DefaultTableModel) jTable9.getModel();

        count = jTable9.getRowCount();
        addProduct.setVisible(true);
        addProduct.pack();
        addProduct.setLocationRelativeTo(null);

        addProduct.jTextField1.setText("");
        addProduct.jComboBox1.setSelectedIndex(0);
        addProduct.y1.setText("");
        addProduct.m1.setText("");
        addProduct.d1.setText("");
        addProduct.d2.setText("");
        addProduct.m2.setText("");
        addProduct.y2.setText("");
        addProduct.jTextField3.setText("");
        addProduct.pr.setText("");
        //com


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (jTable9.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Select a Row to Edit");
        } else {

            DefaultTableModel model = (DefaultTableModel) jTable9.getModel();
            editProduct.jTextField1.setText(model.getValueAt(jTable9.getSelectedRow(), 0).toString());
            editProduct.jTextField4.setText(model.getValueAt(jTable9.getSelectedRow(), 1).toString());
            editProduct.jTextField2.setText(model.getValueAt(jTable9.getSelectedRow(), 2).toString());
            editProduct.y1.setText(model.getValueAt(jTable9.getSelectedRow(), 3).toString().substring(0, 4));
            editProduct.m1.setText(model.getValueAt(jTable9.getSelectedRow(), 3).toString().substring(5, 7));
            editProduct.d1.setText(model.getValueAt(jTable9.getSelectedRow(), 3).toString().substring(8, 10));

            editProduct.y2.setText(model.getValueAt(jTable9.getSelectedRow(), 4).toString().substring(0, 4));
            editProduct.m2.setText(model.getValueAt(jTable9.getSelectedRow(), 4).toString().substring(5, 7));
            editProduct.d2.setText(model.getValueAt(jTable9.getSelectedRow(), 4).toString().substring(8, 10));
            editProduct.jTextField3.setText(model.getValueAt(jTable9.getSelectedRow(), 5).toString());
            editProduct.jTextField5.setText(model.getValueAt(jTable9.getSelectedRow(), 6).toString());
            editProduct.jLabel15.setText(model.getValueAt(jTable9.getSelectedRow(), 7).toString());

            editProduct.setVisible(true);
            editProduct.pack();
            editProduct.setLocationRelativeTo(null);
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (jTable9.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Select the row you want to delete!");
        } else {

            DefaultTableModel model = (DefaultTableModel) jTable9.getModel();
            model.removeRow(jTable9.getSelectedRow());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable9MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jTable9MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        Date dat = pd.getDate();                                                //Getting the date from the date picker
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");                     //Date Format setter
        String date = df.format(dat).toString();    //Assigning the date format to the selected Date

        double Sum = getSum();
        int pno, p = 0;

        int rows = jTable9.getRowCount();
        String pid = pn.getText();
        String ven = vn.getText();
        boolean dval = false;
        boolean pval = false;
        
        Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate= dateFormat.format(d);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1, current;
        try {
            date1 = sdf.parse(date); 
            current = sdf.parse(formattedDate);
            if(!(date1.after(current)) && (date1.before(current)) && (date1.equals(current))){
                dval = true;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Date format you have entered is wrong!");
        }

        try {
            pno = Integer.parseInt(pid);
            if(pno > 1000 && pno < 100000)
            p = pno;
            pval = true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Purchase Number is Invalid!");
        }

        if (pid == "" || ven == "" || date == "") {
            JOptionPane.showMessageDialog(null, "Please fill the blank fields!");
        } else if (rows == 0) {
            JOptionPane.showMessageDialog(null, "No Items Have Been Added!");
        } else if (getValidation(p)) {
            JOptionPane.showMessageDialog(null, "Purchase Order Number Already Exists!");
        } else if (!(dval == true)){
            JOptionPane.showMessageDialog(null, "Invalid Date!");
        } else if (!(pval == true)){
            JOptionPane.showMessageDialog(null, "Invalid Purchase Number");
        }else{
            
            try {

                Connection con = dbConnect.getConnection();
                Statement st;
                Statement st1;
                st = con.createStatement();
                st1 = con.createStatement();

                String Query = "INSERT INTO purchase_tab(purNo, vendorName, purchaseDate, amount)VALUES(" + p + ",'" + ven + "','" + date + "'," + Sum + ")";
                int execute = st.executeUpdate(Query);

                for (int row = 0; row < rows; row++) {

                    int batchNO = Integer.parseInt(jTable9.getValueAt(row, 0).toString());
                    int pId = Integer.parseInt(jTable9.getValueAt(row, 1).toString());
                    String itemName = jTable9.getValueAt(row, 2).toString();
                    String manfDate = jTable9.getValueAt(row, 3).toString();
                    String expDate = jTable9.getValueAt(row, 4).toString();
                    int quantity = Integer.parseInt(jTable9.getValueAt(row, 5).toString());
                    double price = Double.parseDouble(jTable9.getValueAt(row, 6).toString());

                    String Query2 = "INSERT INTO purchaseItems_tab(purNo, batchNo, prodID, prodName, manfDate, expDate, quantity, price) VALUES(" + p + ",'" + batchNO + "'," + pId + ",'" + itemName + "','" + manfDate + "','" + expDate + "'," + quantity + "," + price + ")";
                    //String Query3 = "INSERT INTO stocks_tab (prodID, prodName, quantity) VALUES(" + pId + ", '" + itemName + "', '" + quantity + "') ON DUPLICATE KEY UPDATE  quantity = quantity + " + quantity + " ";

                    int execute2 = st1.executeUpdate(Query2);
                    //int execute3 = st.executeUpdate(Query3);
                }

                JOptionPane.showMessageDialog(null, "Succefully Created");

                pn.setText("");
                vn.setText("");
                pd.setDate(null);
                DefaultTableModel model = (DefaultTableModel) jTable9.getModel();
                model.setRowCount(0);
                st.close();
                st1.close();
                con.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void lblUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserMouseClicked
        CreateAccount frame = new CreateAccount();
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblUserMouseClicked

    private void lblProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProductsMouseClicked
        AddProduct frame = new AddProduct();
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblProductsMouseClicked

    private void lblSalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalesMouseClicked
        Sales frame = new Sales();
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblSalesMouseClicked

    private void lblStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStockMouseClicked
        Stock frame = new Stock();
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblStockMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:

        int column = 0;

        if (jTable8.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Select the Purchase Order you want to view");
        } else {

            String[] results = new String[9];
            int row = jTable8.getSelectedRow();
            String value = jTable8.getModel().getValueAt(row, column).toString();
            int n = Integer.parseInt(value);

            String query = "select * from purchaseItems_tab where purNo = " + n + " ";
            try {

                Connection con = dbConnect.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    results[0] = rs.getString("purNo");
                    results[1] = rs.getString("batchNo");
                    results[2] = rs.getString("prodID");
                    results[3] = rs.getString("prodName");
                    results[4] = rs.getString("manfDate");
                    results[5] = rs.getString("expDate");
                    results[6] = rs.getString("quantity");
                    results[7] = rs.getString("unitPrice");
                    results[8] = rs.getString("price");

                    DefaultTableModel model3 = (DefaultTableModel) PurchaseItemsView.jTable1.getModel();

                    model3.addRow(results);

                }
                st.close();
                rs.close();
                con.close();
            } catch (Exception e) {

            }

            DefaultTableModel model = (DefaultTableModel) jTable8.getModel();

            viewOrder.jTextField1.setText(model.getValueAt(jTable8.getSelectedRow(), 0).toString());
            viewOrder.jTextField2.setText(model.getValueAt(jTable8.getSelectedRow(), 1).toString());
            viewOrder.jTextField3.setText(model.getValueAt(jTable8.getSelectedRow(), 2).toString());
            viewOrder.jLabel9.setText(model.getValueAt(jTable8.getSelectedRow(), 3).toString());

            viewOrder.setVisible(true);
            viewOrder.pack();
            viewOrder.setLocationRelativeTo(null);

        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        
       /* DefaultTableModel model = (DefaultTableModel) jTable8.getModel();
        model.setRowCount(0);

        ShowPurchases();*/
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        UserModel.loginName = "";
        UserModel.userRole = "";

        Login frame = new Login();
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        purReport.setVisible(true);
        purReport.pack();
        purReport.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel model = (DefaultTableModel) jTable8.getModel();
        
        if(jTextField1.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Search field is empty!");
        }else{
            
            model.setRowCount(0);
        
        try {
                

                String[] results = new String[4];
                Connection con = dbConnect.getConnection();
                Statement st = con.createStatement();
                
                String query = "select * from purchase_tab where purNo = '" + jTextField1.getText().toString() + "' ";
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    results[0] = rs.getString("purNo");
                    results[1] = rs.getString("vendorName");
                    results[2] = rs.getString("purchaseDate");
                    results[3] = rs.getString("amount");

                   // DefaultTableModel model3 = (DefaultTableModel) PurchaseItemsView.jTable1.getModel();

                    model.addRow(results);

                }
                st.close();
                rs.close();
                con.close();
                
                if(model.getRowCount()==0){
                    JOptionPane.showMessageDialog(null, "No results have been found");
                }
            } catch (Exception e) {

            }
        }
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        if(jTextField1.getText().equals("")){
            ShowPurchases();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

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
            java.util.logging.Logger.getLogger(Purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Purchase().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTable jTable8;
    public static javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private javax.swing.JLabel lblProducts;
    private javax.swing.JLabel lblSales;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblUser;
    private org.jdesktop.swingx.JXDatePicker pd;
    private javax.swing.JTextField pn;
    private javax.swing.JLabel sum;
    private javax.swing.JTextField vn;
    // End of variables declaration//GEN-END:variables
}
