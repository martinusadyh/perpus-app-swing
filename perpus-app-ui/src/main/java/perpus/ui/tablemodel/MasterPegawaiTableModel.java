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
public class MasterPegawaiTableModel extends AbstractTableModel {
    
    private List<Pegawai> pegawais = new ArrayList<Pegawai>();
    private String[] header = {
        "NIP", "Nama Pegawai", "User Name"
    };

    public MasterPegawaiTableModel(List<Pegawai> pegawais) {
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
        Pegawai pegawai = pegawais.get(rowIndex);
        switch (columnIndex) {
            case 0: return pegawai.getNipPegawai();
            case 1: return pegawai.getNamaPegawai();
            case 2: return pegawai.getUserName();
            default: return "";
        }
    }
}
