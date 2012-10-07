/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.ui.admin;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import perpus.Main;
import perpus.domain.security.Pegawai;
import perpus.domain.security.PegawaiRole;

/**
 *
 * @author martinusadyh
 */
public class FormDialogGrupAkses extends javax.swing.JDialog {

    private List<PegawaiRole> pegawaiRoles;
    private PegawaiRole pegawaiRole;
    private Pegawai pegawai;

    /**
     * Creates new form FormDialogGrupAkses
     */
    public FormDialogGrupAkses() {
        super(new JFrame(), true);
        initComponents();
        setLocationRelativeTo(null);
        initDefaultData();
        
        // klo entri baru, boleh lookup data pegawai
        btnLookupPegawai.setEnabled(true);
    }

    public Pegawai showDialog() {
        setVisible(true);
        return pegawai;
    }
    
    public Pegawai editDialog(Pegawai pegawai) {
        this.pegawai = pegawai;
        loadDomainToForm();
        
        // klo edit, ga boleh lookup data pegawai
        btnLookupPegawai.setEnabled(false);
        
        setVisible(true);
        return pegawai;
    }

    private void initDefaultData() {
        pegawaiRoles = Main.getAdminService().findAllPegawaiRoles();
        if (!pegawaiRoles.isEmpty()) {
            for (PegawaiRole pr : pegawaiRoles) {
                cmbHakAkses.addItem(pr);
            }
        }
    }
    
    private void loadDomainToForm() {
        cmbHakAkses.setSelectedItem(pegawai.getPegawaiRole());
        txtPegawai.setText(pegawai.getNamaPegawai());
    }

    private void loadFormToDomain() {
        pegawaiRole = pegawaiRoles.get(cmbHakAkses.getSelectedIndex());
        pegawai.setPegawaiRole(pegawaiRole);
        System.out.println("pegawaiRole.getPegawais() ~> " + pegawaiRole.getPegawais());
        if (pegawaiRole.getPegawais() != null || !pegawaiRole.getPegawais().isEmpty()) {
            pegawaiRole.getPegawais().add(pegawai);
        } else {
            List<Pegawai> pegawais = new ArrayList<Pegawai>();
            pegawais.add(pegawai);
            
            pegawaiRole.setPegawais(pegawais);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPegawai = new javax.swing.JTextField();
        btnLookupPegawai = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnOK = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        cmbHakAkses = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tambah / Update Data Grup Akses");
        setResizable(false);

        jLabel1.setText("Hak Akses");

        jLabel2.setText("Pegawai *");

        btnLookupPegawai.setText("...");
        btnLookupPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLookupPegawaiActionPerformed(evt);
            }
        });

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLookupPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmbHakAkses, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOK)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBatal, btnOK});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbHakAkses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(txtPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLookupPegawai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOK)
                    .addComponent(btnBatal))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBatal, btnLookupPegawai, btnOK, cmbHakAkses, jLabel1, jLabel2, txtPegawai});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        if (txtPegawai.getText().length() > 0) {
            loadFormToDomain();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(Main.getMainForm(),
                    "Kolom bertanda * harus diisi !!",
                    "Terjadi Kesalahan !!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnLookupPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLookupPegawaiActionPerformed
        pegawai = new LookupDialogPegawai().showDialog();
        if (pegawai != null) {
            txtPegawai.setText(pegawai.getNamaPegawai());
        }
    }//GEN-LAST:event_btnLookupPegawaiActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnLookupPegawai;
    private javax.swing.JButton btnOK;
    private javax.swing.JComboBox cmbHakAkses;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtPegawai;
    // End of variables declaration//GEN-END:variables
}