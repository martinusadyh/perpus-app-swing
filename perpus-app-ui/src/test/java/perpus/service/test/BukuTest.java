/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service.test;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import perpus.domain.Buku;
import perpus.domain.JenisBuku;

/**
 *
 * @author adi
 */
public class BukuTest extends BaseTest {
    
    @Test
    public void deleteTest(){
        List<Buku> list = masterService.findAllBukus(0, masterService.countBukus().intValue());
        for (Buku b : list) {
            masterService.delete(b);
        }
        list = masterService.findAllBukus(0, masterService.countBukus().intValue());
        assertEquals(list.size(), 0);
    }
    
    @Test
    public void insertTest(){
        List<JenisBuku> list = masterService.findAllJenisBuku(0, masterService.countAllJenisBuku().intValue());
        int i = 0;
        for (JenisBuku jb : list) {
            for (int j = i; j < i+300; j++) {
                i=j+1;
                Buku b = new Buku();
                b.setJenisBuku(jb);
                b.setJudulBuku("BUKU" + StringUtils.leftPad(String.valueOf(j), 5, "0"));
                b.setJumlahBuku(j);
                b.setKodeBuku("BKO" + StringUtils.leftPad(String.valueOf(j), 5, "0"));
                b.setKotaTerbit("KOTA" + StringUtils.leftPad(String.valueOf(j), 5, "0"));
                b.setPenerbit("PENERBIT" + StringUtils.leftPad(String.valueOf(j), 5, "0"));
                b.setPengarang("PENGARANG" + StringUtils.leftPad(String.valueOf(j), 5, "0"));
                b.setTahunTerbit(new DateTime().plusMonths(j).toDate());
                masterService.save(b);
            }
        }
    }
    
    @Test
    public void selectTest(){
        List<Buku> list = masterService.findAllBukus(0, masterService.countBukus().intValue());
        assertEquals(list.size(), 300);
    }
}
