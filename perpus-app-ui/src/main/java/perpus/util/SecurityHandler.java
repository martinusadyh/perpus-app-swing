/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.util;

import java.util.ArrayList;
import java.util.List;
import perpus.domain.security.Screen;

/**
 *
 * @author martinusadyh
 */
public class SecurityHandler {
    
    public static List<Screen> getAvailableScreen() {
        List<Screen> screens = new ArrayList<Screen>();
        // menu file
        Screen menuFile = new Screen();
        menuFile.setNomorScreen("1.0");
        menuFile.setNamaScreen("File");
        screens.add(menuFile);
        
        Screen menuLogin = new Screen();
        menuLogin.setNomorScreen("1.1");
        menuLogin.setNamaScreen("Login");
        screens.add(menuLogin);
        
        Screen menuLogout = new Screen();
        menuLogout.setNomorScreen("1.2");
        menuLogout.setNamaScreen("Logout");
        screens.add(menuLogout);
        
        Screen menuMaster = new Screen();
        menuMaster.setNomorScreen("2.0");
        menuMaster.setNamaScreen("Master");
        screens.add(menuMaster);
        
        Screen menuEntriPegawai = new Screen();
        menuEntriPegawai.setNomorScreen("2.1");
        menuEntriPegawai.setNamaScreen("Entri Pegawai");
        screens.add(menuEntriPegawai);
        
        Screen menuEntriAnggota = new Screen();
        menuEntriAnggota.setNomorScreen("2.2");
        menuEntriAnggota.setNamaScreen("Entri Anggota");
        screens.add(menuEntriAnggota);
        
        Screen menuEntriBuku = new Screen();
        menuEntriBuku.setNomorScreen("2.3");
        menuEntriBuku.setNamaScreen("Entri Buku");
        screens.add(menuEntriBuku);
        
        Screen menuTransaksi = new Screen();
        menuTransaksi.setNomorScreen("3.0");
        menuTransaksi.setNamaScreen("Transaksi");
        screens.add(menuTransaksi);
        
        Screen menuTransaksiPinjaman = new Screen();
        menuTransaksiPinjaman.setNomorScreen("3.1");
        menuTransaksiPinjaman.setNamaScreen("Peminjaman");
        screens.add(menuTransaksiPinjaman);
        
        Screen menuAdmin = new Screen();
        menuAdmin.setNomorScreen("4.0");
        menuAdmin.setNamaScreen("Admin");
        screens.add(menuAdmin);
        
        Screen menuKonfigurasiDenda = new Screen();
        menuKonfigurasiDenda.setNomorScreen("4.1");
        menuKonfigurasiDenda.setNamaScreen("Konfigurasi Denda");
        screens.add(menuKonfigurasiDenda);
        
        Screen menuUserManagemen = new Screen();
        menuUserManagemen.setNomorScreen("4.2");
        menuUserManagemen.setNamaScreen("User Managemen");
        screens.add(menuUserManagemen);
        
        Screen menuHakAkses = new Screen();
        menuHakAkses.setNomorScreen("4.2.1");
        menuHakAkses.setNamaScreen("Hak Akses");
        screens.add(menuHakAkses);
        
        Screen menuGrupAkses = new Screen();
        menuGrupAkses.setNomorScreen("4.2.2");
        menuGrupAkses.setNamaScreen("Grup Akses");
        screens.add(menuGrupAkses);
        
        Screen menuLaporan = new Screen();
        menuLaporan.setNomorScreen("5.0");
        menuLaporan.setNamaScreen("Laporan");
        screens.add(menuLaporan);

        return screens;
    }
}
