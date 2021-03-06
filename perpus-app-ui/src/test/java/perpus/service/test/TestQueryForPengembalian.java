/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import perpus.domain.Buku;
import perpus.domain.Peminjaman;
import perpus.service.AdminService;
import perpus.service.MasterService;
import perpus.service.TransaksiService;
import static org.junit.Assert.*;

/**
 *
 * @author adi
 */
public class TestQueryForPengembalian extends BaseTest{
    
     //@Test
     public void hellowww() {
         List<Peminjaman> list = transaksiService.getTransaksiBelumKembali(0,30);
         System.out.println("size result : " + list.size());
//         assertEquals(list.size(), 1);
     }
     
     //@Test
     public void insertOneThousandBuku(){
         for(int i=0; i<1000; i++){
             Buku b = new Buku();
//             b.setJenisBuku("JENIS" + StringUtils.leftPad(String.valueOf(i), 5, "0"));
             b.setJudulBuku("BUKU" + StringUtils.leftPad(String.valueOf(i), 5, "0"));
             b.setJumlahBuku(i);
             b.setKodeBuku("BKO" + StringUtils.leftPad(String.valueOf(i), 5, "0"));
             b.setKotaTerbit("KOTA" + StringUtils.leftPad(String.valueOf(i), 5, "0"));
             b.setPenerbit("PENERBIT" + StringUtils.leftPad(String.valueOf(i), 5, "0"));
             b.setPengarang("PENGARANG" + StringUtils.leftPad(String.valueOf(i), 5, "0"));
             b.setTahunTerbit(new DateTime().plusMonths(i).toDate());
             masterService.save(b);
         }
     }
     
     @Test
     public void test(){
         Date d = new Date(1349767959028l);
         System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(d));
     }
}
