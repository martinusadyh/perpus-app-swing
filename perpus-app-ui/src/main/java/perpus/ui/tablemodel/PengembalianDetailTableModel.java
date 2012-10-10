/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.ui.tablemodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import perpus.domain.PengembalianDetail;

/**
 *
 * @author adi
 */
public class PengembalianDetailTableModel extends AbstractTableModel{
    
    private List<PengembalianDetail> list = new ArrayList<PengembalianDetail>();
    private String[] header = {"Kode Buku", "Judul Buku", "Telat", "Denda"};

    public PengembalianDetailTableModel(List<PengembalianDetail> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
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
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 3: return BigDecimal.class;
            default: return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PengembalianDetail d = list.get(rowIndex);
        switch(columnIndex){
            case 0: return d.getBuku().getKodeBuku();
            case 1: return d.getBuku().getJudulBuku();
            case 2: return d.getTelat().toString() + " hari";
            case 3: return d.getDenda();
            default: return "";
        }
    }
    
}
