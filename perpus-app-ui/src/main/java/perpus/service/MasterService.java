/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service;

import java.util.List;
import perpus.domain.Anggota;
import perpus.domain.Buku;
import perpus.domain.Konfigurasi;
import perpus.domain.security.Pegawai;

/**
 *
 * @author martinusadyh
 */
public interface MasterService {
    
    List<Buku> findAllBukus();
    List<Buku> findBukuByKode(String kode);
    List<Buku> findBukuByNama(String nama);
    
    List<Anggota> findAllAnggota();
    Anggota findAnggotaById(Integer id);
    List<Anggota> findAnggotaByKode(String kode);
    List<Anggota> findAnggotaByNama(String nama);
    
    Konfigurasi getKonfigurasi();

    void save(Object obj);
    void delete(Object obj);

    List<Pegawai> findAllPegawai();

    List<Pegawai> findPegawaisByNIP(String nipPegawai);
    List<Pegawai> findPegawaisByNamaPegawai(String namaPegawai);
    List<Pegawai> findPegawaisByUserName(String userName);

    List<Pegawai> findAllPegawaiWithRole();

    Pegawai findPegawaiByUserNameAndPassword(String userName, String encryptPassword);
    
}
