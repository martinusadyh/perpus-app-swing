/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author adi
 */
@Entity
@Table(name="buku")
public class JenisBuku extends BaseEntity {
    
    @Column(unique=true, nullable=false, name="kode_jenis")
    private String kode;
    
    @Column(name="desc_jenis", nullable=false)
    private String keterangan;

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
    
}
