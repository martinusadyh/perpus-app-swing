package perpus;

import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.jgoodies.looks.plastic.theme.ExperienceBlue;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import perpus.domain.security.Pegawai;
import perpus.domain.security.PegawaiRole;
import perpus.domain.security.Screen;
import perpus.service.AdminService;
import perpus.service.MasterService;
import perpus.service.ReportService;
import perpus.service.TransaksiService;
import perpus.ui.MainMenu;
import perpus.util.PasswordHelper;
import perpus.util.SecurityHandler;

/**
 * Hello world!
 *
 */
public class Main {

    private static ApplicationContext applicationContext;
    private static MasterService masterService;
    private static AdminService adminService;
    private static TransaksiService transaksiService;
    private static ReportService reportService;
    private static List<Screen> screens;
    private static MainMenu mainForm;
    private static Pegawai pegawai;

    private static void initContext() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        masterService = (MasterService) applicationContext.getBean("masterService");
        adminService = (AdminService) applicationContext.getBean("adminService");
        transaksiService = (TransaksiService) applicationContext.getBean("transaksiService");
        reportService = (ReportService) applicationContext.getBean("reportService");
    }

    public static ReportService getReportService() {
        return reportService;
    }

    public static Pegawai getPegawai() {
        return pegawai;
    }

    public static void setPegawai(Pegawai pegawai) {
        Main.pegawai = pegawai;
    }

    public static MasterService getMasterService() {
        return masterService;
    }

    public static AdminService getAdminService() {
        return adminService;
    }

    public static MainMenu getMainForm() {
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

    private static void checkDefaultUser() {
        List<Pegawai> pegawais = masterService.findAllPegawai();
        PegawaiRole defaultPegawaiRole = masterService.findPegawaiRoleByName("SUPERVISOR");
        Pegawai pgw = masterService.findPegawaiByUserName("ama");

        if (pegawais != null || pegawais.isEmpty()) {
            if (defaultPegawaiRole == null) {
                PegawaiRole pr = new PegawaiRole();
                pr.setNama("SUPERVISOR");
                screens = SecurityHandler.getAvailableScreen();
                for (Screen screen : screens) {
                    screen.setPegawaiRole(pr);
                }
                pr.setScreens(screens);
                masterService.save(pr);
            }

            defaultPegawaiRole = masterService.findPegawaiRoleByName("SUPERVISOR");
            if (defaultPegawaiRole != null && pgw == null) {
                pgw = new Pegawai();
                pgw.setNipPegawai("085.693.717.147");
                pgw.setNamaPegawai("ama");
                pgw.setUserName("ama");
                pgw.setPassword(PasswordHelper.getEncryptedTextFromPlainText("amadoang"));
                pgw.setPegawaiRole(defaultPegawaiRole);

                defaultPegawaiRole.getPegawais().add(pgw);

                masterService.save(pgw);
            }
        }
    }

    public static void initLogin() {
        checkDefaultUser();
        if (mainForm == null) {
            mainForm = new MainMenu();
        }

        boolean notLogin = Boolean.TRUE;
        while (notLogin) {
            notLogin = new LoginDialog().showLogin();
        }
        mainForm.initSecurity();
        mainForm.setVisible(true);
    }

    public static void main(String[] args) {
        initContext();
        try {
            PlasticLookAndFeel laf = new PlasticXPLookAndFeel();
            PlasticLookAndFeel.setCurrentTheme(new ExperienceBlue());
            Options.setPopupDropShadowEnabled(true);
            UIManager.setLookAndFeel(laf);
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
