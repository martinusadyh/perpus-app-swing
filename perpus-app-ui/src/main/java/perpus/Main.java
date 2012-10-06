package perpus;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import perpus.service.AdminService;
import perpus.service.MasterService;
import perpus.ui.MainForm;

/**
 * Hello world!
 *
 */
public class Main {
    
    private static ApplicationContext applicationContext;
    private static MasterService masterService;
    private static AdminService adminService;
    
    private static MainForm mainForm;
    
    private static void initContext() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        masterService = (MasterService) applicationContext.getBean("masterService");
        adminService = (AdminService) applicationContext.getBean("adminService");
    }

    public static MasterService getMasterService() {
        return masterService;
    }

    public static AdminService getAdminService() {
        return adminService;
    }

    public static MainForm getMainForm() {
        return mainForm;
    }
    
    public static void main(String[] args) {
        initContext();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainForm = new MainForm();
                mainForm.setVisible(true);
            }
        });
    }
}
