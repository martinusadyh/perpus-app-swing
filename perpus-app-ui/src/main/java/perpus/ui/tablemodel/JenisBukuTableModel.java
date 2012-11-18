/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.ui.tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import perpus.domain.JenisBuku;

/**
 *
 * @author martinusadyh
 */
public class JenisBukuTableModel extends AbstractTableModel {

    private List<JenisBuku> jenisBukus = new ArrayList<JenisBuku>();
    private String[] header = {
        "Kode", "Jenis"
    };

    public JenisBukuTableModel(List<JenisBuku> jenisBukus1) {
        this.jenisBukus = jenisBukus1;
    }
    
    public JenisBukuTableModel(List<JenisBuku> jenisBukus1, String[] header) {
        this.jenisBukus = jenisBukus1;
        this.header = header;
    }

    @Override
    public int getRowCount() {
        return jenisBukus.size();
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
        JenisBuku jenisBuku = jenisBukus.get(rowIndex);
        switch (columnIndex) {
            case 0: return jenisBuku.getKode();
            case 1: return jenisBuku.getKeterangan();
            default: return "";
        }
    }
}
