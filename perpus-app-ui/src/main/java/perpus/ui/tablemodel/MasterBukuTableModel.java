/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.ui.tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import perpus.domain.Buku;

/**
 *
 * @author martinusadyh
 */
public class MasterBukuTableModel extends AbstractTableModel {

    private List<Buku> bukus = new ArrayList<Buku>();
    private String[] header = {
        "Kode", "Judul", "Pengarang", "Penerbit", "Kota Terbit", "Tahun Terbit", "Jenis"
    };

    public MasterBukuTableModel(List<Buku> bukus) {
        this.bukus = bukus;
    }

    @Override
    public int getRowCount() {
        return bukus.size();
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
        Buku buku = bukus.get(rowIndex);
        switch (columnIndex) {
            case 0: return buku.getKodeBuku();
            case 1: return buku.getJudulBuku();
            case 2: return buku.getPengarang();
            case 3: return buku.getPenerbit();
            case 4: return buku.getKotaTerbit();
            case 5: return buku.getTahunTerbit();
            case 6: return buku.getJenisBuku();
            default: return "";
        }
    }
}
