/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service.test;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import perpus.domain.Peminjaman;
import perpus.service.AdminService;
import perpus.service.MasterService;
import perpus.service.TransaksiService;
import static org.junit.Assert.*;

/**
 *
 * @author adi
 */
public class TestQueryForPengembalian {
    
    private static AbstractXmlApplicationContext context;
    private static TransaksiService transaksiService;
    private static AdminService adminService;
    private static MasterService masterService;
    
    public TestQueryForPengembalian() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        transaksiService = (TransaksiService) context.getBean("transaksiService");
        adminService = (AdminService) context.getBean("adminService");
        masterService = (MasterService) context.getBean("masterService");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        context.stop();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hellowww() {
         List<Peminjaman> list = transaksiService.getTransaksiBelumKembali();
         System.out.println("size result : " + list.size());
//         assertEquals(list.size(), 1);
     }
}
