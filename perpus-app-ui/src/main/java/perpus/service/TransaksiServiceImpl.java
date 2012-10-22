/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import perpus.domain.Anggota;
import perpus.domain.Buku;
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
        a.setCounterPinjam(a.getCounterPinjam() + p.getDetailPeminjamans().size());
        a.setStatus("Anggota ini meminjam " + a.getCounterPinjam() + " buku");
        
        masterService.save(a);
        
        // kurangi jumlah buku
        for (PeminjamanDetail pd : p.getDetailPeminjamans()) {
            Buku bk = pd.getBuku();
            bk.setJumlahBuku(bk.getJumlahBuku()-1);
            
            masterService.save(bk);
        }
    }
    
    @Override
    public Long countTransaksiPeminjaman(String option, String value) {
        StringBuilder sb1 = new StringBuilder("select count(p) from Peminjaman p "
                + "where p.id not in (select kembali.transaksiPeminjaman.id from Pengembalian kembali) ");
        StringBuilder sb2 = new StringBuilder("select count(p) from Peminjaman p "
                + "where p.id in (select kembali.transaksiPeminjaman.id from Pengembalian kembali) "
                + "and p.detailPeminjamans.size > (select count(*) from PengembalianDetail d "
                + "where d.header.transaksiPeminjaman.id = p.id)");
        
        if(option.equals("ID_TRX")){
            sb1.append("where b.id like '%" + value + "%' ");
            sb2.append("where b.id like '%" + value + "%' ");
        } else if (option.equals("KODE")) {
            sb1.append("where b.anggota.kodeAnggota like '%" + value + "%' ");
            sb2.append("where b.anggota.kodeAnggota like '%" + value + "%' ");
        } else {
            sb1.append("where b.anggota.namaAnggota like '%" + value + "%' ");
            sb2.append("where b.anggota.namaAnggota like '%" + value + "%' ");
        }
        
        Long count = (Long) sessionFactory.getCurrentSession().createQuery(sb1.toString()).uniqueResult();
        count = count + (Long) sessionFactory.getCurrentSession().createQuery(sb2.toString()).uniqueResult();
        return count;
    }
    
    @Override
    public Long countTransaksiPeminjaman() {
        Long count = (Long) sessionFactory.getCurrentSession()
                .createQuery("select count(p) from Peminjaman p "
                + "where p.id not in (select kembali.transaksiPeminjaman.id from Pengembalian kembali)")
                .uniqueResult();
        count = count + (Long) sessionFactory.getCurrentSession()
                .createQuery("select count(p) from Peminjaman p "
                + "where p.id in (select kembali.transaksiPeminjaman.id from Pengembalian kembali) "
                + "and p.detailPeminjamans.size > (select count(*) from PengembalianDetail d "
                + "where d.header.transaksiPeminjaman.id = p.id)")
                .uniqueResult();
        return count;
    }

    @Override
    @Transactional(readOnly=false)
    public void save(Pengembalian p) {
        sessionFactory.getCurrentSession().save(p);
        Anggota a = masterService.findAnggotaById(p.getTransaksiPeminjaman().getAnggota().getId());
        a.setCounterPinjam(a.getCounterPinjam() - p.getDetailsPengembalian().size());
        if(a.getCounterPinjam() > 0){
            a.setStatus("Anggota ini meminjam " + a.getCounterPinjam() + " buku");
        } else {
            a.setStatus("");
        }
        masterService.save(a);
        
        // tambah jumlah buku yang dikembalikan
        for (PengembalianDetail pd : p.getDetailsPengembalian()) {
            Buku buku = pd.getBuku();
            buku.setJumlahBuku(buku.getJumlahBuku()+1);
            
            masterService.save(buku);
        }
    }

    @Override
    public List<Peminjaman> getTransaksiBelumKembali(Integer start, Integer rows) {
        if(start==null) start=0;
        if(rows==null) rows=30;
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
        Integer end = start+rows > result.size() ? result.size() : start+rows;
        return result.subList(start, end);
    }
    
    @Override
    public List<Peminjaman> getTransaksiBelumKembali(String criteria, String value, Integer start, Integer rows) {
        if(start==null) start=0;
        if(rows==null) rows=30;
        if(!StringUtils.hasText(criteria) || !StringUtils.hasText(value)){
            return getTransaksiBelumKembali(start,rows);
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
        
        return result.subList(start, start + rows);
    }

    @Override
    public List<PengembalianDetail> getTransaksiPengembalianByIdPinjam(Integer id) {
        return sessionFactory.getCurrentSession()
                .createQuery("select d from PengembalianDetail d "
                + "where d.header.transaksiPeminjaman.id = :id")
                .setParameter("id", id)
                .list();
    }

    @Override
    public List<PeminjamanDetail> getTransaksiBelumKembali(Date mulai, Date sampai) {
        List<PeminjamanDetail> result = sessionFactory.getCurrentSession()
                .createQuery("select dp from PeminjamanDetail dp "
                + "where date(dp.header.tglPinjam) between date(:mulai) and date(:sampai)")
                .setParameter("mulai", mulai)
                .setParameter("sampai", sampai)
                .list();
        
        return result;
    }

    @Override
    public List<PengembalianDetail> getTransaksiPengembalian(Date mulai, Date sampai) {
        return sessionFactory.getCurrentSession()
                .createQuery("select dp from PengembalianDetail dp "
                + "where date(dp.header.createdDate) between date(:mulai) and date(:sampai)")
                .setParameter("mulai", mulai)
                .setParameter("sampai", sampai)
                .list();
    }

    @Override
    public PengembalianDetail getPengembalianByIdPinjamAndKodeBuku(Integer id, Integer kode) {
        return (PengembalianDetail) sessionFactory.getCurrentSession()
                .createQuery("select d from PengembalianDetail d "
                + "where d.header.transaksiPeminjaman.id = :id "
                + "and d.buku.id = :idBuku")
                .setParameter("id", id)
                .setParameter("idBuku", kode)
                .uniqueResult();
    }
    
}
