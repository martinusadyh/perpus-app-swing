/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service.test;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import perpus.domain.JenisBuku;

/**
 *
 * @author adi
 */
public class JenisBukuTest extends BaseTest{
    
    @Test
    public void deleteTest() {
        List<JenisBuku> list = masterService.findAllJenisBuku(0, masterService.countAllJenisBuku().intValue());
        
        for (JenisBuku jb : list) {
            masterService.delete(jb);
        }
        
        list = masterService.findAllJenisBuku(0, masterService.countAllJenisBuku().intValue());
        assertEquals(list.size(), 0);
    }
    
    @Test
    public void insertTest(){
        String[] jenis = {"BOS","UMUM","PENUNJANG"};
        
        for (String s : jenis) {
            JenisBuku jb = new JenisBuku();
            jb.setKode(s);
            jb.setKeterangan("BUKU " + s);
            masterService.save(jb);
        }
    }
    
    @Test
    public void selectTest(){
        List<JenisBuku> list = masterService.findAllJenisBuku(0, masterService.countAllJenisBuku().intValue());
        assertEquals(list.size(), 0);
    }
}
