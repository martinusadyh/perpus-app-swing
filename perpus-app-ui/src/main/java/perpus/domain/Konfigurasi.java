/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author adi
 */

@Entity
@Table(name="konfigurasi")
public class Konfigurasi extends BaseEntity{
    
    @OneToOne
    @JoinColumn(name="id_jenis_buku", unique=true, nullable=false)
    private JenisBuku jenisBuku;
    
    @Column(name="max_buku_pinjam", nullable=false)
    private Integer maxBukuPinjam = 0;
    
    @Column(name="max_lama_pinjam", nullable=false)
    private Integer maxLamaPinjam = 0;
    
    @Column(name="denda_per_hari", nullable=false)
    private BigDecimal dendaPerHari = BigDecimal.ZERO;

    public BigDecimal getDendaPerHari() {
        return dendaPerHari;
    }

    public void setDendaPerHari(BigDecimal dendaPerHari) {
        this.dendaPerHari = dendaPerHari;
    }

    public Integer getMaxLamaPinjam() {
        return maxLamaPinjam;
    }

    public void setMaxLamaPinjam(Integer maxLamaPinjam) {
        this.maxLamaPinjam = maxLamaPinjam;
    }

    public JenisBuku getJenisBuku() {
        return jenisBuku;
    }

    public void setJenisBuku(JenisBuku jenisBuku) {
        this.jenisBuku = jenisBuku;
    }

    public Integer getMaxBukuPinjam() {
        return maxBukuPinjam;
    }

    public void setMaxBukuPinjam(Integer maxBukuPinjam) {
        this.maxBukuPinjam = maxBukuPinjam;
    }
    
}
