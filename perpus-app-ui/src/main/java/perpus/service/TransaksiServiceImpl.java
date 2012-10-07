/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perpus.domain.Anggota;
import perpus.domain.Peminjaman;

/**
 *
 * @author adi
 */

@Service("transaksiService")
@Transactional(readOnly=true)
public class TransaksiServiceImpl implements TransaksiService{
    
    @Autowired private SessionFactory sessionFactory;
    @Autowired private MasterService masterService;
    
    @Override
    @Transactional(readOnly=false)
    public void save(Peminjaman p) {
        sessionFactory.getCurrentSession().saveOrUpdate(p);
        Anggota a = masterService.findAnggotaById(p.getAnggota().getId());
        a.setStatus(Boolean.TRUE);
        masterService.save(a);
    }
    
}
