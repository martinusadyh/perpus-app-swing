/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import perpus.domain.Anggota;
import perpus.domain.Peminjaman;
import perpus.domain.PeminjamanDetail;
import perpus.domain.Pengembalian;
import perpus.domain.PengembalianDetail;

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
        sessionFactory.getCurrentSession().save(p);
        Anggota a = masterService.findAnggotaById(p.getAnggota().getId());
        a.setStatus(Boolean.TRUE);
        masterService.save(a);
    }

    @Override
    @Transactional(readOnly=false)
    public void save(Pengembalian p) {
        sessionFactory.getCurrentSession().save(p);
        Anggota a = masterService.findAnggotaById(p.getTransaksiPeminjaman().getAnggota().getId());
        
        List<PengembalianDetail> listDetail = getTransaksiPengembalianByIdPinjam(p.getTransaksiPeminjaman().getId());
        if(listDetail.size() == p.getTransaksiPeminjaman().getDetailPeminjamans().size()){
            a.setStatus(Boolean.FALSE);
            masterService.save(a);
        }
    }

    @Override
    public List<Peminjaman> getTransaksiBelumKembali() {
        List<Peminjaman> result = sessionFactory.getCurrentSession()
                .createQuery("select distinct(p) from Peminjaman p join fetch p.detailPeminjamans "
                + "where p.id not in (select kembali.transaksiPeminjaman.id from Pengembalian kembali)")
                .list();
        result.addAll(sessionFactory.getCurrentSession()
                .createQuery("select distinct(p) from Peminjaman p join fetch p.detailPeminjamans "
                + "where p.id in (select kembali.transaksiPeminjaman.id from Pengembalian kembali) "
                + "and p.detailPeminjamans.size > (select count(*) from PengembalianDetail d "
                + "where d.header.transaksiPeminjaman.id = p.id)")
                .list());
        
        return result;
    }
    
    @Override
    public List<Peminjaman> getTransaksiBelumKembali(String criteria, String value) {
        if(!StringUtils.hasText(criteria) || !StringUtils.hasText(value)){
            return getTransaksiBelumKembali();
        }
        
        StringBuilder q1 = new StringBuilder();
        StringBuilder q2 = new StringBuilder();
        
        //build query 1
        q1.append("select distinct(p) from Peminjaman p ");
        q1.append("join   fetch p.detailPeminjamans ");
        q1.append("where p.id not in ");
        q1.append("      (select kembali.transaksiPeminjaman.id ");
        q1.append("             from Pengembalian kembali) ");
        
        //build query 2
        q2.append("select distinct(p) from Peminjaman p ");
        q2.append("join   fetch p.detailPeminjamans ");
        q2.append("where p.id in ");
        q2.append("      (select kembali.transaksiPeminjaman.id ");
        q2.append("       from Pengembalian kembali) ");
        q2.append("and   p.detailPeminjamans.size > ");
        q2.append("      (select count(*) from PengembalianDetail d ");
        q2.append("       where d.header.transaksiPeminjaman.id = p.id) ");
        
        //append extend criteria
        if(criteria.equalsIgnoreCase("ID_TRX")){
            q1.append("and p.id like '%" + value + "%' ");
            q2.append("and p.id like '%" + value + "%' ");
        } else if(criteria.equalsIgnoreCase("KODE")){
            q1.append("and p.anggota.kodeAnggota like '%" + value + "%' ");
            q2.append("and p.anggota.kodeAnggota like '%" + value + "%' ");
        } else {
            q1.append("and p.anggota.namaAnggota like '%" + value + "%' ");
            q2.append("and p.anggota.namaAnggota like '%" + value + "%' ");
        }
        
        List<Peminjaman> result = sessionFactory.getCurrentSession()
                .createQuery(q1.toString())
                .list();
        result.addAll(sessionFactory.getCurrentSession()
                .createQuery(q2.toString())
                .list());
        
        return result;
    }

    @Override
    public List<PengembalianDetail> getTransaksiPengembalianByIdPinjam(Integer id) {
        return sessionFactory.getCurrentSession()
                .createQuery("select d from PengembalianDetail d "
                + "where d.header.transaksiPeminjaman.id = :id")
                .setParameter("id", id)
                .list();
    }
    
}
