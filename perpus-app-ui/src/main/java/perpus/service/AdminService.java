/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service;

import java.util.List;
import perpus.domain.security.PegawaiRole;
import perpus.domain.security.Screen;

/**
 *
 * @author martinusadyh
 */
public interface AdminService {

    List<PegawaiRole> findAllPegawaiRoles();

    void save(Object obj);

    void delete(Object obj);

    List<Screen> findAllScreenByPegawaiRoleId(Integer id);
    
}
