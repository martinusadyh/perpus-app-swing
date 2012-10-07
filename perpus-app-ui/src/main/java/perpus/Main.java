package perpus;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import perpus.domain.security.Screen;
import perpus.service.AdminService;
import perpus.service.MasterService;
import perpus.service.TransaksiService;
import perpus.ui.MainForm;

/**
 * Hello world!
 *
 */
public class Main {
    
    private static ApplicationContext applicationContext;
    private static MasterService masterService;
    private static AdminService adminService;
    private static TransaksiService transaksiService;
    private static List<Screen> screens;
    
    private static MainForm mainForm;
    
    private static void initContext() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        masterService = (MasterService) applicationContext.getBean("masterService");
        adminService = (AdminService) applicationContext.getBean("adminService");
        transaksiService = (TransaksiService) applicationContext.getBean("transaksiService");
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

    public static TransaksiService getTransaksiService() {
        return transaksiService;
    }

    public static void setScreens(List<Screen> screens) {
        Main.screens = screens;
    }

    public static List<Screen> getScreens() {
        return screens;
    }
    
    private static void initLogin() {
        if (mainForm == null) mainForm = new MainForm();
        
        boolean notLogin = Boolean.TRUE;
        while (notLogin) {
            notLogin = new LoginDialog().showLogin();
        }
        mainForm.setVisible(true);
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
                initLogin();
            }
        });
    }
}
