/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.ui.tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import perpus.domain.security.Pegawai;

/**
 *
 * @author martinusadyh
 */
public class GrupAksesTableModel extends AbstractTableModel {
    
    private List<Pegawai> pegawais = new ArrayList<Pegawai>();
    private String[] header = {
        "Hak Akses", "User Name"
    };

    public GrupAksesTableModel(List<Pegawai> pegawais) {
        this.pegawais = pegawais;
    }

    @Override
    public int getRowCount() {
        return pegawais.size();
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
        Pegawai pgw = pegawais.get(rowIndex);
        switch (columnIndex) {
            case 0: return pgw.getPegawaiRole().getNama();
            case 1: return pgw.getUserName();
            default: return "";
        }
    }
}
