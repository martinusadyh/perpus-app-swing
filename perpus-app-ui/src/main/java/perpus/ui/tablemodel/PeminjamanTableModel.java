/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.ui.tablemodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import perpus.domain.Peminjaman;

/**
 *
 * @author adi
 */
public class PeminjamanTableModel extends AbstractTableModel{
    
    private List<Peminjaman> listPeminjaman = new ArrayList<Peminjaman>();
    private String[] header = {"Id Trx","Tgl Pinjam","Kode Anggota","Nama Anggota"};

    public PeminjamanTableModel(List<Peminjaman> listPeminjamans) {
        this.listPeminjaman = listPeminjamans;
    }

    @Override
    public int getRowCount() {
        return listPeminjaman.size();
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
        Peminjaman pinjam = listPeminjaman.get(rowIndex);
        switch(columnIndex){
            case 0: return pinjam.getId();
            case 1: return pinjam.getTglPinjam();
            case 2: return pinjam.getAnggota().getKodeAnggota();
            case 3: return pinjam.getAnggota().getNamaAnggota();
            default: return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 1: return Date.class;
            default: return String.class;
        }
    }
    
    
}
