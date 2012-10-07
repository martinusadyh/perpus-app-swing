/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author adi
 */
public class LaporanPeminjamanDto {
    
    private String id;
    private String kodeAnggota;
    private String namaAnggota;
    private Date tglPinjam;
    private Date tglKembali;
    private Date tglKembaliSebenarnya;
    private String kodeBuku;
    private String judul;
    private String status;
    private Integer telat;
    private BigDecimal denda;

    public BigDecimal getDenda() {
        return denda;
    }

    public void setDenda(BigDecimal denda) {
        this.denda = denda;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKodeAnggota() {
        return kodeAnggota;
    }

    public void setKodeAnggota(String kodeAnggota) {
        this.kodeAnggota = kodeAnggota;
    }

    public String getKodeBuku() {
        return kodeBuku;
    }

    public void setKodeBuku(String kodeBuku) {
        this.kodeBuku = kodeBuku;
    }

    public String getNamaAnggota() {
        return namaAnggota;
    }

    public void setNamaAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTelat() {
        return telat;
    }

    public void setTelat(Integer telat) {
        this.telat = telat;
    }

    public Date getTglKembali() {
        return tglKembali;
    }

    public void setTglKembali(Date tglKembali) {
        this.tglKembali = tglKembali;
    }

    public Date getTglKembaliSebenarnya() {
        return tglKembaliSebenarnya;
    }

    public void setTglKembaliSebenarnya(Date tglKembaliSebenarnya) {
        this.tglKembaliSebenarnya = tglKembaliSebenarnya;
    }

    public Date getTglPinjam() {
        return tglPinjam;
    }

    public void setTglPinjam(Date tglPinjam) {
        this.tglPinjam = tglPinjam;
    }
    
}
