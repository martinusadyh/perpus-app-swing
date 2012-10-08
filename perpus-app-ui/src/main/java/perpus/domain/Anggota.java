/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author martinusadyh
 */
@Entity
@Table(name="anggota")
public class Anggota extends BaseEntity {
    
    @Column(name="kode_anggota")
    private String kodeAnggota;
    
    @Column(name="nama_anggota")
    private String namaAnggota;
    
    @Column(name="jenis_kelamin")
    private String jenisKelamin;
    
    @Column(name="alamat")
    private String alamat;
    
    @Column(name="email")
    private String email;
    
    @Column(name="agama")
    private String agama;
    
    @Column(name="no_telp")
    private String noTelp;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="tahun_masuk")
    private Date tahunMasuk;
    
    /* keterangan status peminjaman anggota */
    @Column(name="status")
    private String status;
    
    
    @Column(name="counter_pinjam", nullable=false)
    private Integer counterPinjam = 0;

    public String getKodeAnggota() {
        return kodeAnggota;
    }

    public void setKodeAnggota(String kodeAnggota) {
        this.kodeAnggota = kodeAnggota;
    }

    public String getNamaAnggota() {
        return namaAnggota;
    }

    public void setNamaAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public Date getTahunMasuk() {
        return tahunMasuk;
    }

    public void setTahunMasuk(Date tahunMasuk) {
        this.tahunMasuk = tahunMasuk;
    }

    public Integer getCounterPinjam() {
        return counterPinjam;
    }

    public void setCounterPinjam(Integer counterPinjam) {
        this.counterPinjam = counterPinjam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
