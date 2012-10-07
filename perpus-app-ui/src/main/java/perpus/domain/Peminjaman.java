/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author adi
 */

@Entity
@Table(name="peminjaman")
public class Peminjaman extends BaseEntity{
    
    @ManyToOne
    @JoinColumn(name="id_anggota", nullable=false)
    private Anggota anggota;
    
    @Column(name="tanggal_pinjam", nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tglPinjam;
    
    @Column(name="tanggal_kembali", nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tglKembali;
    
    @OneToMany(mappedBy="header")
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<PeminjamanDetail> detailPeminjamans = new ArrayList<PeminjamanDetail>();

    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public List<PeminjamanDetail> getDetailPeminjamans() {
        return detailPeminjamans;
    }

    public void setDetailPeminjamans(List<PeminjamanDetail> detailPeminjamans) {
        this.detailPeminjamans = detailPeminjamans;
    }

    public Date getTglKembali() {
        return tglKembali;
    }

    public void setTglKembali(Date tglKembali) {
        this.tglKembali = tglKembali;
    }

    public Date getTglPinjam() {
        return tglPinjam;
    }

    public void setTglPinjam(Date tglPinjam) {
        this.tglPinjam = tglPinjam;
    }
}
