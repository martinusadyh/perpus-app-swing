/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service;

import java.util.Date;
import java.util.List;
import perpus.domain.Peminjaman;
import perpus.domain.PeminjamanDetail;
import perpus.domain.Pengembalian;
import perpus.domain.PengembalianDetail;

/**
 *
 * @author adi
 */
public interface TransaksiService {
    
    void save(Peminjaman p);
    void save(Pengembalian p);
    
    Long countTransaksiPeminjaman(String option, String value);
    Long countTransaksiPeminjaman();
    
    List<Peminjaman> getTransaksiBelumKembali();
    List<Peminjaman> getTransaksiBelumKembali(String criteria, String value);
    List<PeminjamanDetail> getTransaksiBelumKembali(Date mulai, Date sampai);
    
    
    List<PengembalianDetail> getTransaksiPengembalian(Date mulai, Date sampai);
    List<PengembalianDetail> getTransaksiPengembalianByIdPinjam(Integer id);
    PengembalianDetail getPengembalianByIdPinjamAndKodeBuku(Integer id, Integer kode);
}
