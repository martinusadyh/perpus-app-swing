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
    public List<Buku> findAllBukus() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Buku bk order by bk.createdDate desc")
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
    @Transactional(readOnly=false)
    public Konfigurasi getKonfigurasi() {
        List<Konfigurasi> list = sessionFactory.getCurrentSession()
                .createQuery("from Konfigurasi k")
                .setMaxResults(1)
                .list();
        if(list.isEmpty()){
            Konfigurasi config = new Konfigurasi();
            config.setMaxLamaPinjam(2);
            config.setDendaPerHari(new BigDecimal(2000));
            save(config);
            return config;
        } else {
            return list.get(0);
        }
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
}
