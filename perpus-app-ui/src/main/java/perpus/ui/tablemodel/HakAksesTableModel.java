/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.ui.tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import perpus.domain.security.PegawaiRole;

/**
 *
 * @author martinusadyh
 */
public class HakAksesTableModel extends AbstractTableModel {
    
    private List<PegawaiRole> pegawaiRoles = new ArrayList<PegawaiRole>();
    private String[] header = {
        "Hak Akses"
    };

    public HakAksesTableModel(List<PegawaiRole> pegawaiRoles) {
        this.pegawaiRoles = pegawaiRoles;
    }
    
    @Override
    public int getRowCount() {
        return pegawaiRoles.size();
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
        PegawaiRole pegawaiRole = pegawaiRoles.get(rowIndex);
        switch (columnIndex) {
            case 0: return pegawaiRole.getNama();
            default: return "";
        }
    }
}
