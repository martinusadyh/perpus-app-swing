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
 * @author martinus
 */
@Entity
@Table(name="pegawai")
public class Pegawai extends BaseEntity {
    
    @Column(name="nip_pegawai")
    private String nipPegawai;
    
    @Column(name="nama_pegawai")
    private String namaPegawai;
    
    @Column(name="user_name")
    private String userName;
    
    @Column(name="password")
    private String password;
    
    @ManyToOne
    private PegawaiRole pegawaiRole;

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PegawaiRole getPegawaiRole() {
        return pegawaiRole;
    }

    public void setPegawaiRole(PegawaiRole pegawaiRole) {
        this.pegawaiRole = pegawaiRole;
    }
}
