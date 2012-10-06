/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author adi
 */

@Service("transaksiService")
@Transactional(readOnly=true)
public class TransaksiServiceImpl implements TransaksiService{
    
    @Autowired private SessionFactory sessionFactory;
    
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
}
