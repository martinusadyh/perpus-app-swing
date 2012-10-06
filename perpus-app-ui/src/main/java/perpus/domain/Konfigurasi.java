/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author adi
 */

@Entity
@Table(name="konfigurasi")
public class Konfigurasi extends BaseEntity{
    
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
    
}
