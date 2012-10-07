/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service;

import java.util.List;
import perpus.domain.Peminjaman;
import perpus.domain.Pengembalian;
import perpus.domain.PengembalianDetail;

/**
 *
 * @author adi
 */
public interface TransaksiService {
    
    void save(Peminjaman p);
    void save(Pengembalian p);
    
    List<Peminjaman> getTransaksiBelumKembali();
    List<Peminjaman> getTransaksiBelumKembali(String criteria, String value);
    
    List<PengembalianDetail> getTransaksiPengembalianByIdPinjam(Integer id);
}
