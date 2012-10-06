/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perpus.domain.security.PegawaiRole;

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
        return sessionFactory.getCurrentSession()
                .createQuery("from PegawaiRole pr order by pr.createdDate desc")
                .list();
    }
}
