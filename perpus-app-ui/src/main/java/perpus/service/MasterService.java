/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service;

import java.util.List;
import perpus.domain.Anggota;
import perpus.domain.Buku;
import perpus.domain.JenisBuku;
import perpus.domain.Konfigurasi;
import perpus.domain.security.Pegawai;
import perpus.domain.security.PegawaiRole;

/**
 *
 * @author martinusadyh
 */
public interface MasterService {
    
    Long countBukus();
    Long countBukus(String option, String value);
    Long countAnggota();
    Long countAnggota(String option, String value);
    Long countPegawai();
    Long countPegawai(String option, String value);
    List<Buku> findAllBukus(Integer start, Integer rows);
    List<Anggota> findAllAnggota(Integer start, Integer rows);
    List<Pegawai> findAllPegawai(Integer start, Integer rows);
    List<Buku> findAllBukus(String option, String value, Integer start, Integer rows);
    List<Anggota> findAllAnggota(String option, String value, Integer start, Integer rows);
    List<Pegawai> findAllPegawai(String option, String value, Integer start, Integer rows);
    List<Buku> findBukuByKode(String kode);
    List<Buku> findBukuByNama(String nama);
    
    List<Anggota> findAllAnggota();
    Anggota findAnggotaById(Integer id);
    List<Anggota> findAnggotaByKode(String kode);
    List<Anggota> findAnggotaByNama(String nama);
    
    Long countAllKonfigurasi(String option, String value);
    Long countAllJenisBuku(String option, String value);
    Konfigurasi findKonfigurasiById(Integer id);
    Konfigurasi findKonfigurasiByKode(String kodeJenis);
    List<Konfigurasi> findAllKonfigurasi(String option, String value,Integer start, Integer rows);
    List<JenisBuku> findAllJenisBuku(String option, String value,Integer start, Integer rows);

    void save(Object obj);
    void delete(Object obj);

    List<Pegawai> findAllPegawai();

    List<Pegawai> findPegawaisByNIP(String nipPegawai);
    List<Pegawai> findPegawaisByNamaPegawai(String namaPegawai);
    List<Pegawai> findPegawaisByUserName(String userName);

    List<Pegawai> findAllPegawaiWithRole();

    Pegawai findPegawaiByUserName(String userName);

    PegawaiRole findPegawaiRoleByName(String supervisoR);

    Long countAvailableBuku();
    Long countAvailableBuku(String option, String value);
    List<Buku> findAllAvailableBukus(Integer start, Integer rows);
    List<Buku> findAllAvailableBukus(String option, String value, Integer start, Integer rows);
    
    Long countAllJenisBuku();
    JenisBuku findJenisBukuById(Integer id);
    JenisBuku findJenisBukuByKode(String kode);
    List<JenisBuku> findAllJenisBuku(Integer start, Integer rows);
    
}
