/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.domain;

import java.math.BigDecimal;
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
import perpus.domain.security.Pegawai;

/**
 *
 * @author adi
 */
@Entity
@Table(name="pengembalian")
public class Pengembalian extends BaseEntity{
    
    @Column(name="total_denda", nullable=false)
    private BigDecimal totalDenda = BigDecimal.ZERO;
    
    @ManyToOne
    @JoinColumn(name="id_t_pinjam", nullable=false)
    private Peminjaman transaksiPeminjaman;
    
    @OneToMany(mappedBy="header")
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<PengembalianDetail> detailsPengembalian = new ArrayList<PengembalianDetail>();

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private Pegawai pegawai;
    
    public BigDecimal getTotalDenda() {
        return totalDenda;
    }

    public void setTotalDenda(BigDecimal totalDenda) {
        this.totalDenda = totalDenda;
    }

    public Peminjaman getTransaksiPeminjaman() {
        return transaksiPeminjaman;
    }

    public void setTransaksiPeminjaman(Peminjaman transaksiPeminjaman) {
        this.transaksiPeminjaman = transaksiPeminjaman;
    }

    public List<PengembalianDetail> getDetailsPengembalian() {
        return detailsPengembalian;
    }

    public void setDetailsPengembalian(List<PengembalianDetail> detailsPengembalian) {
        this.detailsPengembalian = detailsPengembalian;
    }

    public Pegawai getPegawai() {
        return pegawai;
    }

    public void setPegawai(Pegawai pegawai) {
        this.pegawai = pegawai;
    }
    
}
