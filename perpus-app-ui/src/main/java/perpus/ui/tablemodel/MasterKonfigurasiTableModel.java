/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.ui.tablemodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import perpus.domain.Konfigurasi;

/**
 *
 * @author adi
 */
public class MasterKonfigurasiTableModel extends AbstractTableModel {
    
    private List<Konfigurasi> konfigList = new ArrayList<Konfigurasi>();
    private String[] header = {
        "Jenis Buku", "Max Lama Pinjam", "Max Pinjam Buku", "Denda"
    };

    public MasterKonfigurasiTableModel(List<Konfigurasi> konfigList) {
        this.konfigList = konfigList;
    }

    @Override
    public int getRowCount() {
        return konfigList.size();
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
        Konfigurasi konfig = konfigList.get(rowIndex);
        switch (columnIndex) {
            case 0: return konfig.getJenisBuku().getKode();
            case 1: return konfig.getMaxLamaPinjam();
            case 2: return konfig.getMaxBukuPinjam();
            case 3: return konfig.getDendaPerHari();
            default: return "";
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0: return String.class;
            case 1: return Integer.class;
            case 2: return Integer.class;
            case 3: return BigDecimal.class;
            default: return String.class;
        }
    }
    
}
