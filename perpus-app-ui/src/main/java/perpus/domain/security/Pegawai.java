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
    
    @Column(name="user_name")
    private String userName;
    
    @Column(name="password")
    private String password;
    
    @ManyToOne
    private PegawaiRole pegawaiRole;

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
