/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.domain;

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
@Table(name="peminjaman_detail",uniqueConstraints=@UniqueConstraint(columnNames={"id_header","id_buku"}))
public class PeminjamanDetail extends BaseEntity {
    
    @ManyToOne
    @JoinColumn(name="id_header", nullable=false)
    private Peminjaman header;
    
    @ManyToOne
    @JoinColumn(name="id_buku", nullable=false)
    private Buku buku;
    
    @Column(name="tanggal_kembali", nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tglKembali;

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public Peminjaman getHeader() {
        return header;
    }

    public void setHeader(Peminjaman header) {
        this.header = header;
    }

    public Date getTglKembali() {
        return tglKembali;
    }

    public void setTglKembali(Date tglKembali) {
        this.tglKembali = tglKembali;
    }
    
}
