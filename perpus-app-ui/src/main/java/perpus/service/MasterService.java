/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service;

import java.util.List;
import perpus.domain.Buku;

/**
 *
 * @author martinusadyh
 */
public interface MasterService {
    
    List<Buku> findAllBukus();
    
}
