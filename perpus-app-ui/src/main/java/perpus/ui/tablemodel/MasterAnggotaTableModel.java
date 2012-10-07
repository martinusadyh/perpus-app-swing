/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.ui.tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import perpus.domain.Anggota;

/**
 *
 * @author martinusadyh
 */
public class MasterAnggotaTableModel extends AbstractTableModel {

    private List<Anggota> anggotas = new ArrayList<Anggota>();
    private String[] header = {
        "Kode", "Nama", "Jenis Kelamin", "Alamat", "Email", "Agama", "No. Telp", "Thn. Masuk", "Status"
    };

    public MasterAnggotaTableModel(List<Anggota> anggotas1) {
        this.anggotas = anggotas1;
    }
    
    public MasterAnggotaTableModel(List<Anggota> anggotas1, String[] header) {
        this.anggotas = anggotas1;
        this.header = header;
    }

    @Override
    public int getRowCount() {
        return anggotas.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Anggota anggota = anggotas.get(rowIndex);
        switch (columnIndex) {
            case 0: return anggota.getKodeAnggota();
            case 1: return anggota.getNamaAnggota();
            case 2: return anggota.getJenisKelamin();
            case 3: return anggota.getAlamat();
            case 4: return anggota.getEmail();
            case 5: return anggota.getAgama();
            case 6: return anggota.getNoTelp();
            case 7: return anggota.getTahunMasuk();
            case 8: return anggota.getStatus() ? "Masih ada pinjaman buku" : "";
            default: return "";
        }
    }
}
