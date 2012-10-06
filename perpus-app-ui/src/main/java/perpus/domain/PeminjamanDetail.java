/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author adi
 */

@Entity
@Table(name="peminjaman_detail")
public class PeminjamanDetail extends BaseEntity {
    
    @ManyToOne
    @JoinColumn(name="id_header", nullable=false)
    private Peminjaman header;
    
    @ManyToOne
    @JoinColumn(name="id_buku", nullable=false)
    private Buku buku;

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
    
}
