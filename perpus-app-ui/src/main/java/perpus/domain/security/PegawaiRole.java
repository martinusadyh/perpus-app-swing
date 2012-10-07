/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.domain.security;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import perpus.domain.BaseEntity;

/**
 *
 * @author martinus
 */
@Entity
@Table(name="pegawai_role")
public class PegawaiRole extends BaseEntity {
    
    @Column(name="nama", nullable=false, unique=true)
    private String nama;
    
    @OneToMany(mappedBy = "pegawaiRole", cascade= CascadeType.ALL)
    private List<Screen> screens = new ArrayList<Screen>();
    
    @OneToMany(mappedBy = "pegawaiRole")
    private List<Pegawai> pegawais = new ArrayList<Pegawai>();

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }

    public List<Pegawai> getPegawais() {
        return pegawais;
    }

    public void setPegawais(List<Pegawai> pegawais) {
        this.pegawais = pegawais;
    }

    @Override
    public String toString() {
        return getNama();
    }
}
