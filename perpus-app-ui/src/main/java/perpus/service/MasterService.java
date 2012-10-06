/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service;

import java.util.List;
import perpus.domain.Anggota;
import perpus.domain.Buku;

/**
 *
 * @author martinusadyh
 */
public interface MasterService {
    
    List<Buku> findAllBukus();
    
    List<Anggota> findAllAnggota();
    Anggota findAnggotaById(String id);

    void save(Object obj);
    void delete(Object obj);
    
}
