/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormPengembalian.java
 *
 * Created on Oct 7, 2012, 9:53:09 AM
 */
package perpus.ui.transaksi;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;
import perpus.Main;
import perpus.domain.Buku;
import perpus.domain.Konfigurasi;
import perpus.domain.Peminjaman;
import perpus.domain.PeminjamanDetail;
import perpus.domain.Pengembalian;
import perpus.domain.PengembalianDetail;
import perpus.ui.TableUtil;
import perpus.ui.tablemodel.PengembalianDetailTableModel;
import perpus.util.BigDecimalTableRenderer;
import perpus.util.ErrorDialog;
import perpus.util.TextComponentUtils;

/**
 *
 * @author adi
 */
public class FormPengembalian extends javax.swing.JPanel {

    public static final String PANEL_NAME = "Transaksi Pengembalian";
    private static FormPengembalian panel;
    private Peminjaman peminjaman;
    private Pengembalian pengembalian;
    private PengembalianDetail detail;
    private List<PengembalianDetail> detailPengembalians = new ArrayList<PengembalianDetail>();
    private List<PengembalianDetail> detailPengembaliansOrig = new ArrayList<PengembalianDetail>();

    /**
     * Creates new form FormPengembalian
     */
    public FormPengembalian() {
        initComponents();
        TextComponentUtils.setCurrency(txtTotalDenda);
        tbl.setDefaultRenderer(BigDecimal.class, new BigDecimalTableRenderer());
        tbl.getSelectionModel().addListSelectionListener(new TableSelection());
    }

    public static FormPengembalian getPanel() {
        if (panel == null) {
            panel = new FormPengembalian();
        }
        return panel;
    }

    void clearForm() {
        peminjaman = null;
        pengembalian = null;
        detailPengembalians = new ArrayList<PengembalianDetail>();
        detailPengembaliansOrig = new ArrayList<PengembalianDetail>();
        txtTransaksi.setText("");
        txtTotalDenda.setText("");
        tglPinjam.setDate(null);
        tglKembali1.setDate(null);
        tglKembali2.setDate(null);

        refreshTableDetail();
    }

    public static Integer hitungHari(Date from, Date to) {
        DateTime sampai = new DateTime(to);

        DateTimeComparator comparator = DateTimeComparator.getDateOnlyInstance();
        DateTime current = new DateTime(from);

        Integer jumlahHari = 0;
        if (comparator.compare(current, sampai) < 0) {
            while (comparator.compare(current, sampai) != 0) {
                jumlahHari++;
                current = current.plusDays(1);
            }
        }

        return jumlahHari;
    }

    void loadToDetailPengembalian() {
        List<PengembalianDetail> listSudahDikembalikan =
                Main.getTransaksiService().getTransaksiPengembalianByIdPinjam(peminjaman.getId());

        Integer telat = hitungHari(peminjaman.getTglKembali(), tglKembali2.getDate());
        System.out.println("Telat " + telat + " hari!");
        Konfigurasi config = Main.getMasterService().getKonfigurasi();

        for (PeminjamanDetail d1 : peminjaman.getDetailPeminjamans()) {
            boolean allowAdd = true;
            for (PengembalianDetail d2 : listSudahDikembalikan) {
                if (d1.getBuku().getKodeBuku().equalsIgnoreCase(d2.getBuku().getKodeBuku())) {
                    allowAdd = false;
                    break;
                }
            }
            if (allowAdd) {
                PengembalianDetail detail = new PengembalianDetail();
                detail.setBuku(d1.getBuku());
                detail.setTelat(telat);
                BigDecimal denda = config.getDendaPerHari()
                        .multiply(new BigDecimal(detail.getTelat()));
                detail.setDenda(denda);
                detailPengembalians.add(detail);
                detailPengembaliansOrig.add(detail);
            }
        }
        refreshTableDetail();
    }

    void refreshTableDetail() {
        tbl.setModel(new PengembalianDetailTableModel(detailPengembalians));
        TableUtil.initColumn(tbl);
        hitungTotalDenda();
    }

    void hitungTotalDenda() {
        BigDecimal total = BigDecimal.ZERO;
        for (PengembalianDetail d : detailPengembalians) {
            total = total.add(d.getDenda());
        }

        txtTotalDenda.setText(TextComponentUtils.formatNumber(total));
    }

    Boolean cekItemExisted(Buku buku) {
        Boolean retval = true;
        for (PengembalianDetail d : detailPengembalians) {
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

        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTransaksi = new javax.swing.JTextField();
        tglPinjam = new com.toedter.calendar.JDateChooser();
        tglKembali1 = new com.toedter.calendar.JDateChooser();
        tglKembali2 = new com.toedter.calendar.JDateChooser();
        txtTotalDenda = new javax.swing.JTextField();
        btnLookupTransaksi = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

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

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode", "Judul", "Telat", "Denda"
            }
        ));
        tbl.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbl.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tbl);

        jLabel1.setText("Transaksi Peminjaman");

        jLabel2.setText("Tgl Pinjam");

        jLabel3.setText("Tgl Kembali Seharusnya");

        jLabel4.setText("Tgl Kembali Realisasi");

        jLabel5.setText("Total Denda");

        txtTransaksi.setEnabled(false);

        tglPinjam.setEnabled(false);

        tglKembali1.setEnabled(false);

        tglKembali2.setEnabled(false);

        txtTotalDenda.setEnabled(false);

        btnLookupTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/img/lookup_icon.gif"))); // NOI18N
        btnLookupTransaksi.setToolTipText("Lookup Transaksi Peminjaman");
        btnLookupTransaksi.setFocusable(false);
        btnLookupTransaksi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLookupTransaksi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLookupTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLookupTransaksiActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(btnLookupTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tglPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTotalDenda, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tglKembali2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tglKembali1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(13, 13, 13)))
                .addGap(0, 0, 0))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tglKembali1, tglKembali2, tglPinjam, txtTotalDenda});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tglPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tglKembali1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tglKembali2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotalDenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(btnLookupTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdd, btnBatal, btnDelete, btnLookupTransaksi, btnSave, jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, tglKembali1, tglKembali2, tglPinjam, txtTotalDenda, txtTransaksi});

    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        PengembalianDetail detail = new TambahDetailPengembalianDialog(detailPengembaliansOrig).showDialog();
        if (detail != null) {
            if (cekItemExisted(detail.getBuku())) {
                detailPengembalians.add(detail);
            }
        }
        refreshTableDetail();
}//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        detailPengembalians.remove(detail);
        refreshTableDetail();
        hitungTotalDenda();
}//GEN-LAST:event_btnDeleteActionPerformed

    private void btnLookupTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLookupTransaksiActionPerformed
        peminjaman = new LookupTransaksiPeminjamanDialog().showDialog();
        if (peminjaman != null) {
            StringBuilder text = new StringBuilder();
            text.append(peminjaman.getId());
            text.append(" | ");
            text.append(peminjaman.getAnggota().getNamaAnggota());
            text.append(" | ");
            text.append(peminjaman.getDetailPeminjamans().size());
            text.append(" items");
            txtTransaksi.setText(text.toString());

            tglPinjam.setDate(peminjaman.getTglPinjam());
            tglKembali1.setDate(peminjaman.getTglKembali());
            tglKembali2.setDate(new Date());
            loadToDetailPengembalian();
        }
}//GEN-LAST:event_btnLookupTransaksiActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        clearForm();
}//GEN-LAST:event_btnBatalActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            if (validateForm()) {
                loadFormToDomain();
                Main.getTransaksiService().save(pengembalian);
                clearForm();
                JOptionPane.showMessageDialog(Main.getMainForm(),
                        "Transaksi berhasil disimpan !");
            } else {
                JOptionPane.showMessageDialog(Main.getMainForm(),
                        "Data yang anda masukan tidak lengkap !");
            }
        } catch (Exception e) {
            ErrorDialog.showErrorDialog(e);
        }
}//GEN-LAST:event_btnSaveActionPerformed

    void loadFormToDomain() {
        pengembalian = new Pengembalian();
        pengembalian.setTransaksiPeminjaman(peminjaman);
        pengembalian.setTotalDenda(TextComponentUtils.parseNumberToBigDecimal(txtTotalDenda.getText()));
        pengembalian.setTglKembaliRealisasi(tglKembali2.getDate());
        pengembalian.setPegawai(Main.getPegawai());
        for (PengembalianDetail d : detailPengembalians) {
            d.setHeader(pengembalian);
        }
        pengembalian.setDetailsPengembalian(detailPengembalians);
    }

    Boolean validateForm() {
        if (peminjaman != null
                && tglKembali2 != null
                && tglKembali1 != null
                && tglPinjam != null
                && StringUtils.hasText(txtTotalDenda.getText())
                && detailPengembalians.size() > 0) {
            return true;
        }
        return false;
    }

    private class TableSelection implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                return;
            }

            if (tbl.getSelectedRow() >= 0) {
                detail = detailPengembalians.get(tbl.getSelectedRow());
            }

        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLookupTransaksi;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbl;
    private com.toedter.calendar.JDateChooser tglKembali1;
    private com.toedter.calendar.JDateChooser tglKembali2;
    private com.toedter.calendar.JDateChooser tglPinjam;
    private javax.swing.JTextField txtTotalDenda;
    private javax.swing.JTextField txtTransaksi;
    // End of variables declaration//GEN-END:variables
}
