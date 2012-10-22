/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author adi
 */

@Entity
@Table(name="pengembalian_detail",uniqueConstraints=@UniqueConstraint(columnNames={"id_header","id_buku"}))
public class PengembalianDetail extends BaseEntity{
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="tgl_kembali_realisasi", nullable=false)
    private Date tglKembaliRealisasi;
    
    private Integer telat = 0;
    private BigDecimal denda = BigDecimal.ZERO;
    
    @ManyToOne
    @JoinColumn(name="id_header", nullable=false)
    private Pengembalian header;
    
    @ManyToOne
    @JoinColumn(name="id_buku", nullable=false)
    private Buku buku;

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public BigDecimal getDenda() {
        return denda;
    }

    public void setDenda(BigDecimal denda) {
        this.denda = denda;
    }

    public Pengembalian getHeader() {
        return header;
    }

    public void setHeader(Pengembalian header) {
        this.header = header;
    }

    public Integer getTelat() {
        return telat;
    }

    public void setTelat(Integer telat) {
        this.telat = telat;
    }

    public Date getTglKembaliRealisasi() {
        return tglKembaliRealisasi;
    }

    public void setTglKembaliRealisasi(Date tglKembaliRealisasi) {
        this.tglKembaliRealisasi = tglKembaliRealisasi;
    }
    
}
