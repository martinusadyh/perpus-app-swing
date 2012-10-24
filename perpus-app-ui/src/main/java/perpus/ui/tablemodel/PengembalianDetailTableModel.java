/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.ui.tablemodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import perpus.Main;
import perpus.domain.Peminjaman;
import perpus.domain.PeminjamanDetail;
import perpus.domain.PengembalianDetail;

/**
 *
 * @author adi
 */
public class PengembalianDetailTableModel extends AbstractTableModel{
    
    private List<PengembalianDetail> list = new ArrayList<PengembalianDetail>();
    private String[] header = {"Kode Buku", "Judul Buku", "Tgl Kembali Seharusnya", "Tgl Kembali Sebenarnya", "Telat", "Denda"};
    private Peminjaman peminjaman;
    
    public PengembalianDetailTableModel(List<PengembalianDetail> list, Peminjaman pd) {
        this.list = list;
        this.peminjaman = pd;
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
            case 2: return Date.class;
            case 3: return Date.class;
            case 5: return BigDecimal.class;
            default: return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PengembalianDetail d = list.get(rowIndex);
        if(peminjaman!=null){
            PeminjamanDetail pd = Main.getTransaksiService().getTransaksiPeminjamanByIdAndBuku(
                    peminjaman.getId(), d.getBuku().getId());
            switch(columnIndex){
                case 0: return d.getBuku().getKodeBuku();
                case 1: return d.getBuku().getJudulBuku();
                case 2: return pd.getTglKembali();
                case 3: return d.getTglKembaliRealisasi();
                case 4: return d.getTelat().toString() + " hari";
                case 5: return d.getDenda();
                default: return "";
            }
        } else {
            switch(columnIndex){
                case 0: return d.getBuku().getKodeBuku();
                case 1: return d.getBuku().getJudulBuku();
                case 2: return null;
                case 3: return d.getTglKembaliRealisasi();
                case 4: return d.getTelat().toString() + " hari";
                case 5: return d.getDenda();
                default: return "";
            }
        }
    }
    
}
