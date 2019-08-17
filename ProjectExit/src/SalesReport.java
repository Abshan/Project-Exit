
import Models.DatabaseConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class SalesReport extends javax.swing.JFrame {

    /**
     * Creates new form SalesReport
     */
    public SalesReport() {
        initComponents();
        fillMans();
        //fillReps();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    DatabaseConnection dbConnect = new DatabaseConnection();

    public void fillReps(String name) {
        try {
            ResultSet rs;
            PreparedStatement pst;
            sp.removeAllItems();
            sp.addItem("NONE");
            String query = "select a.repName from reps_tab a, assigned_tab b  where a.repID = b.repID and b.manID = (select userID from user_tab where userName = '" + name + "');";

            try (Connection con = dbConnect.getConnection()) {

                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                while (rs.next()) {
                    sp.addItem(rs.getString("repName"));
                }
            }
            pst.close();
            rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void fillMans() {

        try {
            ResultSet rs;
            PreparedStatement pst;
            sm.removeAllItems();
            sm.addItem("NONE");
            String query = "select userName from user_tab where role = 'SALES MANAGER' ";

            try (Connection con = dbConnect.getConnection()) {

                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                while (rs.next()) {
                    sm.addItem(rs.getString("userName"));
                }
            }
            pst.close();
            rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel2 = new javax.swing.JLabel();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jLabel4 = new javax.swing.JLabel();
        sm = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        sp = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        error = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AUXANO PVT LTD");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("SALES REPORT"));

        jLabel1.setText("DATE:");

        jXDatePicker1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXDatePicker1ActionPerformed(evt);
            }
        });

        jLabel2.setText("TO");

        jLabel4.setText("SALES MANAGER:");

        sm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smActionPerformed(evt);
            }
        });

        jLabel5.setText("SALES REP:");

        jButton1.setText("GENERATE REPORT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("CLEAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/AUXANO-Logo2.png"))); // NOI18N

        error.setForeground(new java.awt.Color(153, 0, 0));

        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("LEAVE BOTH DATES BLANK TO GENERATE FULL REPORT...");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 92, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(86, 86, 86)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(error, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(error))
                .addGap(7, 7, 7)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(sm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(124, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        Connection con = dbConnect.getConnection();

        Date today = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formated = sdf.format(today);

        String man = (String) sm.getSelectedItem();
        String rep = (String) sp.getSelectedItem();
        String d1 = "";
        String d2 = "";
        Date current, from, to;

        String query = "";

        if (jXDatePicker1.getDate() == null && jXDatePicker2.getDate() == null && man.equals("NONE") && rep.equals("NONE")) {
            query = "select * from sales_tab a, salesItems_tab b where a.soNumber = b.soNumber;";
        }
        if (jXDatePicker1.getDate() == null && jXDatePicker2.getDate() == null && !man.equals("NONE") && rep.equals("NONE")) {
            query = "select * from sales_tab a, salesItems_tab b where a.soNumber = b.soNumber AND a.orderCreatedBy = '" + man + "';";
        }
        if (jXDatePicker1.getDate() == null && jXDatePicker2.getDate() == null && !man.equals("NONE") && !rep.equals("NONE")) {
            query = "select * from sales_tab a, salesItems_tab b where a.soNumber = b.soNumber AND a.orderCreatedBy = '" + man + "' AND a.salesRep = '" + rep + "';";
        }
        if (jXDatePicker1.getDate() != null && jXDatePicker2.getDate() == null && man.equals("NONE") && rep.equals("NONE")) {
            try {
                d1 = sdf.format(jXDatePicker1.getDate());
                from = sdf.parse(d1);
                current = sdf.parse(formated);

                if (from.compareTo(current) <= 0) {
                    query = "select * from sales_tab a, salesItems_tab b where a.soNumber = b.soNumber AND a.orderedDate >= '" + d1 + "';";
                } else {
                    JOptionPane.showMessageDialog(null, "Starting date is not valid!");
                }

            } catch (ParseException e) {
            }
        }
        if (jXDatePicker1.getDate() != null && jXDatePicker2.getDate() == null && !man.equals("NONE") && rep.equals("NONE")) {
            try {
                d1 = sdf.format(jXDatePicker1.getDate());
                from = sdf.parse(d1);
                current = sdf.parse(formated);

                if (from.compareTo(current) <= 0) {
                    query = "select * from sales_tab a, salesItems_tab b where a.soNumber = b.soNumber AND a.orderedDate >= '" + d1 + "' AND a.orderCreatedBy = '" + man + "';";
                } else {
                    JOptionPane.showMessageDialog(null, "Starting date is not valid!");
                }

            } catch (ParseException e) {
            }
        }
        if (jXDatePicker1.getDate() != null && jXDatePicker2.getDate() == null && !man.equals("NONE") && !rep.equals("NONE")) {
            try {
                d1 = sdf.format(jXDatePicker1.getDate());
                from = sdf.parse(d1);
                current = sdf.parse(formated);

                if (from.compareTo(current) <= 0) {
                    query = "select * from sales_tab a, salesItems_tab b where a.soNumber = b.soNumber AND a.orderedDate >= '" + d1 + "' AND a.orderCreatedBy = '" + man + "' AND a.salesRep = '" + rep + "';";
                } else {
                    JOptionPane.showMessageDialog(null, "Starting date is not valid!");
                }

            } catch (ParseException e) {
            }
        }
        if (jXDatePicker1.getDate() != null && jXDatePicker2.getDate() != null && man.equals("NONE") && rep.equals("NONE")) {
            try {
                d1 = sdf.format(jXDatePicker1.getDate());
                d2 = sdf.format(jXDatePicker2.getDate());
                from = sdf.parse(d1);
                to = sdf.parse(d2);
                current = sdf.parse(formated);

                if (from.compareTo(current) <= 0 && from.compareTo(to) <= 0 && to.compareTo(current) <= 0) {
                    query = "select * from sales_tab a, salesItems_tab b where a.soNumber = b.soNumber AND a.orderedDate >= '" + d1 + "' AND a.orderedDate <= '" + d2 + "';";
                } else {
                    JOptionPane.showMessageDialog(null, "Please select valid Dates!");
                }

            } catch (ParseException e) {
            }
        }
        if (jXDatePicker1.getDate() != null && jXDatePicker2.getDate() != null && !man.equals("NONE") && rep.equals("NONE")) {
            try {
                d1 = sdf.format(jXDatePicker1.getDate());
                d2 = sdf.format(jXDatePicker2.getDate());
                from = sdf.parse(d1);
                to = sdf.parse(d2);
                current = sdf.parse(formated);

                if (from.compareTo(current) <= 0 && from.compareTo(to) <= 0 && to.compareTo(current) <= 0) {
                    query = "select * from sales_tab a, salesItems_tab b where a.soNumber = b.soNumber AND a.orderedDate >= '" + d1 + "' AND a.orderedDate <= '" + d2 + "' AND a.orderCreatedBy = '" + man + "';";
                } else {
                    JOptionPane.showMessageDialog(null, "Please select valid Dates!");
                }

            } catch (ParseException e) {
            }
        }
        if (jXDatePicker1.getDate() != null && jXDatePicker2.getDate() != null && !man.equals("NONE") && !rep.equals("NONE")) {
            try {
                d1 = sdf.format(jXDatePicker1.getDate());
                d2 = sdf.format(jXDatePicker2.getDate());
                from = sdf.parse(d1);
                to = sdf.parse(d2);
                current = sdf.parse(formated);

                if (from.compareTo(current) <= 0 && from.compareTo(to) <= 0 && to.compareTo(current) <= 0) {
                    query = "select * from sales_tab a, salesItems_tab b where a.soNumber = b.soNumber AND a.orderedDate >= '" + d1 + "' AND a.orderedDate <= '" + d2 + "' AND a.orderCreatedBy = '" + man + "' AND a.salesRep = '" + rep + "';";
                } else {
                    JOptionPane.showMessageDialog(null, "Please select valid Dates!");
                }

            } catch (ParseException e) {

                JOptionPane.showMessageDialog(null, e);
            }
        }
        if (jXDatePicker1.getDate() == null && jXDatePicker2.getDate() != null && man.equals("NONE") && rep.equals("NONE")) {
            try {
                d2 = sdf.format(jXDatePicker2.getDate());
                to = sdf.parse(d2);
                current = sdf.parse(formated);

                if (to.compareTo(current) <= 0) {
                    query = "select * from sales_tab a, salesItems_tab b where a.soNumber = b.soNumber AND a.orderedDate <= '" + d2 + "';";
                } else {
                    JOptionPane.showMessageDialog(null, "Please select valid Dates!");
                }

            } catch (ParseException e) {
            }
        }

        try {
            InputStream in = new FileInputStream(new File("C:\\Users\\User\\Documents\\GitHub\\Project-Exit\\ProjectExit\\src\\Reports\\SalesReport.jrxml"));
            JasperDesign jd = JRXmlLoader.load(in);
            String sql = query;
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint j = JasperFillManager.fillReport(jr, null, con);
            JasperViewer jv = new JasperViewer(j, false);
            jv.viewReport(j, false);
            con.close();
            error.setText("");
            jXDatePicker1.setDate(null);
            jXDatePicker2.setDate(null);
            sm.setSelectedIndex(0);
            sp.setSelectedIndex(0);

        } catch (FileNotFoundException | SQLException | JRException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jXDatePicker1.setDate(null);
        jXDatePicker2.setDate(null);
        sm.setSelectedIndex(0);
        sp.setSelectedIndex(0);
        error.setText("");

    }//GEN-LAST:event_jButton2ActionPerformed

    private void smActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smActionPerformed
        // TODO add your handling code here:
        fillReps(sm.getSelectedItem().toString());
    }//GEN-LAST:event_smActionPerformed

    private void jXDatePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDatePicker1ActionPerformed
        // TODO add your handling code here:
        jXDatePicker2.setDate(null);
    }//GEN-LAST:event_jXDatePicker1ActionPerformed

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
            java.util.logging.Logger.getLogger(SalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SalesReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel error;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private javax.swing.JComboBox<String> sm;
    private javax.swing.JComboBox<String> sp;
    // End of variables declaration//GEN-END:variables
}
