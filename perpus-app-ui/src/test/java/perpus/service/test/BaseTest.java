/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service.test;

import org.junit.*;
import static org.junit.Assert.*;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import perpus.service.AdminService;
import perpus.service.MasterService;
import perpus.service.TransaksiService;

/**
 *
 * @author adi
 */
public class BaseTest {
    
    static AbstractXmlApplicationContext context;
    static TransaksiService transaksiService;
    static AdminService adminService;
    static MasterService masterService;
    
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
}
