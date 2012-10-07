/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.ui.tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import perpus.domain.PeminjamanDetail;

/**
 *
 * @author adi
 */
public class PeminjamanDetailTableModel extends AbstractTableModel{

    private List<PeminjamanDetail> details = new ArrayList<PeminjamanDetail>();
    private String[] header = {};

    public PeminjamanDetailTableModel(List<PeminjamanDetail> details, String[] header) {
        this.details = details;
        this.header = header;
    }
    
    @Override
    public int getRowCount() {
        return details.size();
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
        PeminjamanDetail d = details.get(rowIndex);
        switch(columnIndex){
            case 0: return d.getBuku().getKodeBuku();
            case 1: return d.getBuku().getJudulBuku();
            case 2: return d.getBuku().getJenisBuku();
            case 3: return d.getBuku().getPengarang();
            default: return "";
        }
    }
    
}
