/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Service("masterService")
@Transactional(readOnly=true)
public class MasterServiceImpl implements MasterService {
    
    @Autowired private SessionFactory sessionFactory;
    
    @Override
    @Transactional(readOnly=false)
    public void save(Object obj) {
        sessionFactory.getCurrentSession().saveOrUpdate(obj);
    }
    
    @Override
    @Transactional(readOnly=false)
    public void delete(Object obj) {
        sessionFactory.getCurrentSession().delete(obj);
    }
    
    @Override
    public Long countBukus(){
        return (Long) sessionFactory.getCurrentSession()
                .createQuery("select count(b) from Buku b")
                .uniqueResult();
    }

    @Override
    public List<Buku> findAllBukus(Integer start, Integer rows) {
        if(start==null) start=0;
        if(rows==null) rows=25;
        return sessionFactory.getCurrentSession()
                .createQuery("from Buku bk order by bk.createdDate desc")
                .setFirstResult(start)
                .setMaxResults(rows)
                .list();
    }
    
    @Override
    public List<Anggota> findAllAnggota(Integer start, Integer rows) {
        if(start==null) start=0;
        if(rows==null) rows=25;
        return sessionFactory.getCurrentSession()
                .createQuery("from Anggota bk order by bk.createdDate desc")
                .setFirstResult(start)
                .setMaxResults(rows)
                .list();
    }
    
    @Override
    public List<Pegawai> findAllPegawai(Integer start, Integer rows) {
        if(start==null) start=0;
        if(rows==null) rows=25;
        return sessionFactory.getCurrentSession()
                .createQuery("from Pegawai bk order by bk.createdDate desc")
                .setFirstResult(start)
                .setMaxResults(rows)
                .list();
    }

    @Override
    public List<Pegawai> findAllPegawai() {
        List<Pegawai> pegawais = sessionFactory.getCurrentSession()
                .createQuery("from Pegawai pgw order by pgw.createdDate desc")
                .list();
        
        for (Pegawai pgw : pegawais) {
            Hibernate.initialize(pgw.getPegawaiRole());
        }
        
        return pegawais;
    }

    @Override
    public Anggota findAnggotaById(Integer id) {
        return (Anggota) sessionFactory.getCurrentSession()
                .createQuery("from Anggota ag where ag.id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }
    
    @Override
    public List<Anggota> findAllAnggota() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Anggota ag order by ag.namaAnggota, ag.kodeAnggota asc")
                .list();
    }

    @Override
    public List<Anggota> findAnggotaByKode(String kode) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Anggota ag where ag.kodeAnggota like :kode "
                + "order by ag.namaAnggota, ag.kodeAnggota asc")
                .setParameter("kode", "%" + kode + "%")
                .list();
    }

    @Override
    public List<Anggota> findAnggotaByNama(String nama) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Anggota ag where ag.namaAnggota like :nama "
                + "order by ag.namaAnggota, ag.kodeAnggota asc")
                .setParameter("nama", "%" + nama + "%")
                .list();
    }

    @Override
    public List<Buku> findBukuByKode(String kode) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Buku b where b.kodeBuku like :kode")
                .setParameter("kode", "%" + kode + "%")
                .list();
    }

    @Override
    public List<Buku> findBukuByNama(String nama) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Buku b where b.judulBuku like :nama")
                .setParameter("nama", "%" + nama + "%")
                .list();
    }

	@Override
    public List<Pegawai> findPegawaisByNIP(String nipPegawai) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Pegawai pgw where pgw.nipPegawai like :prmNIP")
                .setParameter("prmNIP", "%" + nipPegawai + "%")
                .list();
    }

    @Override
    public List<Pegawai> findPegawaisByNamaPegawai(String namaPegawai) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Pegawai pgw where pgw.namaPegawai like :prmNamaPgw")
                .setParameter("prmNamaPgw", "%" + namaPegawai + "%")
                .list();
    }

    @Override
    public List<Pegawai> findPegawaisByUserName(String userName) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Pegawai pgw where pgw.userName like :prmUserName")
                .setParameter("prmUserName", "%" + userName + "%")
                .list();
    }

    @Override
    public List<Pegawai> findAllPegawaiWithRole() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Pegawai pgw where pgw.pegawaiRole is not null")
                .list();
    }

    @Override
    public Pegawai findPegawaiByUserName(String userName) {
        return (Pegawai) sessionFactory.getCurrentSession()
                .createQuery("from Pegawai pgw where pgw.userName = :prmUserName")
                .setParameter("prmUserName", userName)
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    public PegawaiRole findPegawaiRoleByName(String supervisoR) {
        PegawaiRole pr = (PegawaiRole) sessionFactory.getCurrentSession()
                .createQuery("from PegawaiRole pr where pr.nama = :prmNama")
                .setParameter("prmNama", supervisoR)
                .setMaxResults(1)
                .uniqueResult();
        
        if (pr != null) Hibernate.initialize(pr.getPegawais());
        
        return pr;
    }

    @Override
    public Long countBukus(String option, String value) {
        StringBuilder sb = new StringBuilder("select count(b) from Buku b ");
        if(option.equals("KODE")){
            sb.append("where b.kodeBuku like '%" + value + "%' ");
        } else {
            sb.append("where b.judulBuku like '%" + value + "%' ");
        }
        
        return (Long) sessionFactory.getCurrentSession().createQuery(sb.toString()).uniqueResult();
    }
    
    @Override
    public Long countAnggota(String option, String value) {
        StringBuilder sb = new StringBuilder("select count(b) from Anggota b ");
        if(option.equals("KODE")){
            sb.append("where b.kodeAnggota like '%" + value + "%' ");
        } else {
            sb.append("where b.namaAnggota like '%" + value + "%' ");
        }
        
        return (Long) sessionFactory.getCurrentSession().createQuery(sb.toString()).uniqueResult();
    }
    
    @Override
    public Long countPegawai(String option, String value) {
        StringBuilder sb = new StringBuilder("select count(b) from Pegawai b ");
        if(option.equals("NIP")){
            sb.append("where b.nipPegawai like '%" + value + "%' ");
        } else {
            sb.append("where b.namaPegawai like '%" + value + "%' ");
        }
        
        return (Long) sessionFactory.getCurrentSession().createQuery(sb.toString()).uniqueResult();
    }

    @Override
    public List<Buku> findAllBukus(String option, String value, Integer start, Integer rows) {
        if(start==null) start=0;
        if(rows==null) rows=25;
        
        StringBuilder sb = new StringBuilder("from Buku b ");
        if(option.equals("KODE")){
            sb.append("where b.kodeBuku like '%" + value + "%' ");
        } else if (option.equals("JUDUL")) {
            sb.append("where b.judulBuku like '%" + value + "%' ");
        } else if (option.equals("PENGARANG")) {
            sb.append("where b.pengarang like '%" + value + "%' ");
        } else if (option.equals("PENERBIT")) {
            sb.append("where b.penerbit like '%" + value + "%' ");
        } else if (option.equals("KOTA TERBIT")) {
            sb.append("where b.kotaTerbit like '%" + value + "%' ");
        } else if (option.equals("TAHUN TERBIT")) {
            sb.append("where year(b.tahunTerbit) = '" + value + "'");
        } else if (option.equals("JENIS")) {
            sb.append("where b.jenisBuku like '%" + value + "%' ");
        } else {
            sb.append("where b.jumlahBuku like '%" + value + "%' ");
        }
        sb.append("order by b.createdDate desc ");
        
        return sessionFactory.getCurrentSession()
                .createQuery(sb.toString())
                .setFirstResult(start)
                .setMaxResults(rows)
                .list();
    }
    
    @Override
    public List<Anggota> findAllAnggota(String option, String value, Integer start, Integer rows) {
        if(start==null) start=0;
        if(rows==null) rows=25;
        
        StringBuilder sb = new StringBuilder("from Anggota b ");
        if(option.equals("KODE")){
            sb.append("where b.kodeAnggota like '%" + value + "%' ");
        } else {
            sb.append("where b.namaAnggota like '%" + value + "%' ");
        }
        sb.append("order by b.createdDate desc ");
        
        return sessionFactory.getCurrentSession()
                .createQuery(sb.toString())
                .setFirstResult(start)
                .setMaxResults(rows)
                .list();
    }
    
    @Override
    public List<Pegawai> findAllPegawai(String option, String value, Integer start, Integer rows) {
        if(start==null) start=0;
        if(rows==null) rows=25;
        
        StringBuilder sb = new StringBuilder("from Pegawai b ");
        if(option.equals("NIP")){
            sb.append("where b.nipPegawai like '%" + value + "%' ");
        } else {
            sb.append("where b.namaPegawai like '%" + value + "%' ");
        }
        sb.append("order by b.createdDate desc ");
        
        return sessionFactory.getCurrentSession()
                .createQuery(sb.toString())
                .setFirstResult(start)
                .setMaxResults(rows)
                .list();
    }

    @Override
    public Long countAnggota() {
        return (Long) sessionFactory.getCurrentSession()
                .createQuery("select count(a) from Anggota a")
                .uniqueResult();
    }

    @Override
    public Long countPegawai() {
        return (Long) sessionFactory.getCurrentSession()
                .createQuery("select count(a) from Pegawai a")
                .uniqueResult();
    }

    @Override
    public Long countAvailableBuku() {
        return (Long) sessionFactory.getCurrentSession()
                .createQuery("select count(bk) from Buku bk where bk.jumlahBuku > 0")
                .uniqueResult();
    }

    @Override
    public Long countAvailableBuku(String option, String value) {
        StringBuilder sb = new StringBuilder("select count(b) from Buku b ");
        if(option.equals("KODE")){
            sb.append("where b.kodeBuku like '%" + value + "%' ");
        } else {
            sb.append("where b.judulBuku like '%" + value + "%' ");
        }
        sb.append("and b.jumlahBuku > 0 ");
        return (Long) sessionFactory.getCurrentSession().createQuery(sb.toString()).uniqueResult();
    }

    @Override
    public List<Buku> findAllAvailableBukus(Integer start, Integer rows) {
        if(start==null) start=0;
        if(rows==null) rows=30;
        return sessionFactory.getCurrentSession()
                .createQuery("from Buku bk where bk.jumlahBuku > 0 order by bk.createdDate desc")
                .setFirstResult(start)
                .setMaxResults(rows)
                .list();
    }

    @Override
    public List<Buku> findAllAvailableBukus(String option, String value, Integer start, Integer rows) {
        if(start==null) start=0;
        if(rows==null) rows=30;
        
        StringBuilder sb = new StringBuilder("from Buku b ");
        if(option.equals("KODE")){
            sb.append("where b.kodeBuku like '%" + value + "%' ");
        } else {
            sb.append("where b.judulBuku like '%" + value + "%' ");
        }
        sb.append("and b.jumlahBuku > 0 order by b.createdDate desc ");
        
        return sessionFactory.getCurrentSession()
                .createQuery(sb.toString())
                .setFirstResult(start)
                .setMaxResults(rows)
                .list();
    }

    @Override
    public Long countAllJenisBuku() {
        return (Long) sessionFactory.getCurrentSession().createQuery("select count(jb) from JenisBuku jb").uniqueResult();
    }

    @Override
    public JenisBuku findJenisBukuById(Integer id) {
        return (JenisBuku) sessionFactory.getCurrentSession().createQuery("select jb from JenisBuku jb where jb.id=:id")
                .setParameter("id", id).uniqueResult();
    }

    @Override
    public JenisBuku findJenisBukuByKode(String kode) {
        return (JenisBuku) sessionFactory.getCurrentSession().createQuery("select jb from JenisBuku jb where jb.kode=:kode")
                .setParameter("kode", kode).uniqueResult();
    }

    @Override
    public List<JenisBuku> findAllJenisBuku(Integer start, Integer rows) {
        if(start==null) start = 0;
        if(rows==null) rows = 30;
        
        return sessionFactory.getCurrentSession().createQuery("from JenisBuku jb order by jb.createdDate desc")
                .setFirstResult(start)
                .setMaxResults(rows)
                .list();
    }

    @Override
    public Long countAllKonfigurasi(String option, String value) {
        StringBuilder sb = new StringBuilder("select count(k) from Konfigurasi k ");
        
        if(option !=null && option.equals("KD_JNS_BUKU")){
            sb.append("where k.jenisBuku.kode like '%" + value + "%' ");
        }
        
        return (Long) sessionFactory.getCurrentSession().createQuery(sb.toString()).uniqueResult();
    }
    
    @Override
    public Long countAllJenisBuku(String option, String value) {
        StringBuilder sb = new StringBuilder("select count(k) from JenisBuku k ");
        
        if (option !=null && option.equals("KODE")){
            sb.append("where k.kode like '%" + value + "%' ");
        } else if (option !=null && option.equals("JENIS")) {
            sb.append("where k.keterangan like '%" + value + "%' ");
        }
        
        return (Long) sessionFactory.getCurrentSession().createQuery(sb.toString()).uniqueResult();
    }

    @Override
    public Konfigurasi findKonfigurasiById(Integer id) {
        return (Konfigurasi) sessionFactory.getCurrentSession().createQuery("select k from Konfigurasi k where k.id=:id")
                .setParameter("id", id).uniqueResult();
    }

    @Override
    public Konfigurasi findKonfigurasiByKode(String kode) {
        return (Konfigurasi) sessionFactory.getCurrentSession().createQuery("select k from Konfigurasi k where k.jenisBuku.kode=:kode")
                .setParameter("kode", kode).uniqueResult();
    }

    @Override
    public List<Konfigurasi> findAllKonfigurasi(String option, String value, Integer start, Integer rows) {
        if(start==null) start = 0;
        if(rows==null) rows = 30;
        
        StringBuilder sb = new StringBuilder("from Konfigurasi k ");
        
        if(option !=null && option.equals("KD_JNS_BUKU")){
            sb.append("where k.jenisBuku.kode like '%" + value + "%' ");
        }
        
        sb.append("order by k.createdDate desc ");
        
        return sessionFactory.getCurrentSession().createQuery(sb.toString())
                .setFirstResult(start)
                .setMaxResults(rows)
                .list();
    }
    @Override
    public List<JenisBuku> findAllJenisBuku(String option, String value, Integer start, Integer rows) {
        if(start==null) start = 0;
        if(rows==null) rows = 30;
        
        StringBuilder sb = new StringBuilder("from JenisBuku k ");
        
        if(option !=null && option.equals("KODE")){
            sb.append("where k.kode like '%" + value + "%' ");
        } else if(option !=null && option.equals("JENIS")){
            sb.append("where k.keterangan like '%" + value + "%' ");
        }
        
        sb.append("order by k.createdDate desc ");
        
        return sessionFactory.getCurrentSession().createQuery(sb.toString())
                .setFirstResult(start)
                .setMaxResults(rows)
                .list();
    }
}
