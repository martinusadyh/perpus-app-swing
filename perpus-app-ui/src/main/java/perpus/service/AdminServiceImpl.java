/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perpus.domain.security.PegawaiRole;
import perpus.domain.security.Screen;

/**
 *
 * @author martinusadyh
 */
@Service("adminService")
@Transactional(readOnly=true)
public class AdminServiceImpl implements AdminService {
    
    @Autowired private SessionFactory sessionFactory;

    @Override
    public List<PegawaiRole> findAllPegawaiRoles() {
        List<PegawaiRole> pegawaiRoles = sessionFactory.getCurrentSession()
                .createQuery("from PegawaiRole pr order by pr.createdDate desc")
                .list();
        
        for (PegawaiRole pr : pegawaiRoles) {
            Hibernate.initialize(pr.getPegawais());
            Hibernate.initialize(pr.getScreens());
        }
        
        return pegawaiRoles;
    }

    @Override
    @Transactional(readOnly=false)
    public void save(Object obj) {
        sessionFactory.getCurrentSession().saveOrUpdate(obj);
    }

    @Override
    @Transactional(readOnly=false)
    public void delete(Object obj) {
        sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public List<Screen> findAllScreenByPegawaiRoleId(Integer id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Screen screen where screen.pegawaiRole.id = :prmPegawaiRoleID")
                .setParameter("prmPegawaiRoleID", id)
                .list();
    }
}
