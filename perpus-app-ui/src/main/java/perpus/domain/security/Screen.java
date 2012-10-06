/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.domain.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import perpus.domain.BaseEntity;

/**
 *
 * @author martinusadyh
 */
@Entity
@Table(name="screen")
public class Screen extends BaseEntity {
    
    @Column(name="nomor_screen")
    private String nomorScreen;
    
    @Column(name="nama_screen")
    private String namaScreen;
    
    @Column(name="enable")
    private Boolean enable = Boolean.TRUE;
    
    @Column(name="visible")
    private Boolean visible = Boolean.TRUE;
    
    @ManyToOne
    private PegawaiRole pegawaiRole;

    public String getNomorScreen() {
        return nomorScreen;
    }

    public void setNomorScreen(String nomorScreen) {
        this.nomorScreen = nomorScreen;
    }

    public String getNamaScreen() {
        return namaScreen;
    }

    public void setNamaScreen(String namaScreen) {
        this.namaScreen = namaScreen;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public PegawaiRole getPegawaiRole() {
        return pegawaiRole;
    }

    public void setPegawaiRole(PegawaiRole pegawaiRole) {
        this.pegawaiRole = pegawaiRole;
    }
}
