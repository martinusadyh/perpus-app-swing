/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormPeminjaman.java
 *
 * Created on Oct 7, 2012, 3:30:57 AM
 */
package perpus.ui.transaksi;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import org.joda.time.DateTime;
import perpus.Main;
import perpus.domain.Anggota;
import perpus.domain.Buku;
import perpus.domain.Konfigurasi;
import perpus.domain.Peminjaman;
import perpus.domain.PeminjamanDetail;
import perpus.ui.TableUtil;
import perpus.ui.tablemodel.PeminjamanDetailTableModel;
import perpus.util.ErrorDialog;

/**
 *
 * @author adi
 */
public class FormPeminjaman extends javax.swing.JPanel {

    public static final String PANEL_NAME = "Transaksi Peminjaman";
    private static FormPeminjaman panel;
    private Peminjaman peminjaman;
    private PeminjamanDetail detail;
    private List<PeminjamanDetail> detailsPeminjaman = new ArrayList<PeminjamanDetail>();
    private Anggota anggota;
    String[] headerTableDetail = {"Kode", "Judul", "Jenis", "Pengarang"};

    public static FormPeminjaman getPanel() {
        if (panel == null) {
            panel = new FormPeminjaman();
        }
        return panel;
    }

    /**
     * Creates new form FormPeminjaman
     */
    public FormPeminjaman() {
        initComponents();
        settingTanggalTransaksi();
        tbl.getSelectionModel().addListSelectionListener(new TableSelection());
    }

    void settingTanggalTransaksi() {
        Konfigurasi config = Main.getMasterService().getKonfigurasi();

        DateTime sekarang = new DateTime();
        jdcPinjam.setDate(sekarang.toDate());
        jdcKembali.setDate(sekarang.plusDays(config.getMaxLamaPinjam()).toDate());
    }

    void refreshTable(List<PeminjamanDetail> list) {
        tbl.setModel(new PeminjamanDetailTableModel(list, headerTableDetail));
        TableUtil.initColumn(tbl);
    }

    Boolean validateForm() {
        if (anggota != null
                && jdcPinjam != null
                && jdcKembali != null
                && detailsPeminjaman.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    void loadFormToDomain() {
        peminjaman = new Peminjaman();
        peminjaman.setAnggota(anggota);
        peminjaman.setTglKembali(jdcKembali.getDate());
        peminjaman.setTglPinjam(jdcPinjam.getDate());
        peminjaman.setPegawai(Main.getPegawai());
        for (PeminjamanDetail d : detailsPeminjaman) {
            d.setHeader(peminjaman);
        }
        peminjaman.setDetailPeminjamans(detailsPeminjaman);
    }

    void clearForm() {
        settingTanggalTransaksi();
        txtNamaAnggota.setText("");
        detailsPeminjaman = new ArrayList<PeminjamanDetail>();
        detail = null;
        anggota = null;
        peminjaman = null;
        refreshTable(detailsPeminjaman);
    }

    Boolean cekItemExisted(Buku buku) {
        Boolean retval = true;
        for (PeminjamanDetail d : detailsPeminjaman) {
            if (d.getBuku().getKodeBuku().equalsIgnoreCase(buku.getKodeBuku())) {
                JOptionPane.showMessageDialog(Main.getMainForm(),
                        "Buku sudah dipilih !");
                retval = false;
                break;
            }
        }
        return retval;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNamaAnggota = new javax.swing.JTextField();
        jdcPinjam = new com.toedter.calendar.JDateChooser();
        jdcKembali = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnLookupAnggota = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable() {
            public Component prepareRenderer(
                TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);

                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? getBackground() : new java.awt.Color(237, 243, 254));
                }

                return c;
            }
        };
        btnClose = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Anggota");

        jLabel2.setText("Tgl Pinjam");

        jLabel3.setText("Tgl Kembali");

        txtNamaAnggota.setEnabled(false);

        jdcPinjam.setEnabled(false);

        jdcKembali.setEnabled(false);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/img/add.gif"))); // NOI18N
        btnAdd.setToolTipText("Tambah Buku");
        btnAdd.setFocusable(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/img/delete.gif"))); // NOI18N
        btnDelete.setToolTipText("Hapus Buku");
        btnDelete.setFocusable(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/img/save.png"))); // NOI18N
        btnSave.setToolTipText("Simpan Transaksi");
        btnSave.setFocusable(false);
        btnSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/img/reload.png"))); // NOI18N
        btnBatal.setToolTipText("Batal Transaksi");
        btnBatal.setFocusable(false);
        btnBatal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBatal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnLookupAnggota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/img/lookup_icon.gif"))); // NOI18N
        btnLookupAnggota.setToolTipText("Lookup Anggota");
        btnLookupAnggota.setFocusable(false);
        btnLookupAnggota.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLookupAnggota.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLookupAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLookupAnggotaActionPerformed(evt);
            }
        });

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode", "Judul", "Jenis", "Pengarang"
            }
        ));
        tbl.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbl.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tbl);

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/img/close.gif"))); // NOI18N
        btnClose.setToolTipText("Tutup Form");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jdcPinjam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNamaAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLookupAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jdcKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(btnClose)))))
                        .addGap(287, 287, 287))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jdcKembali, jdcPinjam, txtNamaAnggota});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdd, btnBatal, btnClose, btnDelete, btnLookupAnggota, btnSave});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtNamaAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLookupAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jdcPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose)
                    .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdd, btnBatal, btnClose, btnDelete, btnLookupAnggota, btnSave, jLabel1, jLabel2, jLabel3, jdcKembali, jdcPinjam, txtNamaAnggota});

    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if(anggota == null){
            JOptionPane.showMessageDialog(Main.getMainForm(),
                    "Pilih anggota yang akan meminjam terlebih dahulu !");
            return;
        }
        
        if (detailsPeminjaman.size() + anggota.getCounterPinjam() == 3) {
            JOptionPane.showMessageDialog(Main.getMainForm(),
                    "Maximal 3 buku yang boleh dipinjam !");
        } else {
            Buku buku = new LookupBukuDialog().showDialog();

            if (buku != null) {
                if (cekItemExisted(buku)) {
                    PeminjamanDetail detail = new PeminjamanDetail();
                    detail.setBuku(buku);
                    detailsPeminjaman.add(detail);
                }
            }
        }

        refreshTable(detailsPeminjaman);
}//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (detail != null) {
            detailsPeminjaman.remove(detail);
        }
        refreshTable(detailsPeminjaman);
}//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            if (validateForm()) {
                loadFormToDomain();
                Main.getTransaksiService().save(peminjaman);
                clearForm();
                JOptionPane.showMessageDialog(Main.getMainForm(),
                        "Transaksi berhasil disimpan Save!");
            } else {
                JOptionPane.showMessageDialog(Main.getMainForm(),
                        "Data yang anda masukan tidak lengkap !");
            }
        } catch (Exception e) {
            ErrorDialog.showErrorDialog(e);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        clearForm();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnLookupAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLookupAnggotaActionPerformed
        anggota = new LookupAnggotaDialog().showDialog();
        if (anggota != null) {
            if (anggota.getCounterPinjam() < 3) {
                txtNamaAnggota.setText(anggota.getNamaAnggota());
            } else {
                JOptionPane.showMessageDialog(Main.getMainForm(),
                        "Jumlah buku yang di pinjam sudah mencapai batas maximum");
                anggota = null;
            }
        }
    }//GEN-LAST:event_btnLookupAnggotaActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        Main.getMainForm().getMainTabbedPane().remove(this);
        panel = null;
    }//GEN-LAST:event_btnCloseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLookupAnggota;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JDateChooser jdcKembali;
    private com.toedter.calendar.JDateChooser jdcPinjam;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtNamaAnggota;
    // End of variables declaration//GEN-END:variables

    private class TableSelection implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                return;
            }

            if (tbl.getSelectedRow() >= 0) {
                detail = detailsPeminjaman.get(tbl.getSelectedRow());
            }

        }
    }
}
