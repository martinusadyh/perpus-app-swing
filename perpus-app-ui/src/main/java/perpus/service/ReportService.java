/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service;

import java.util.Date;
import net.sf.jasperreports.engine.JasperPrint;
import perpus.domain.Anggota;

/**
 *
 * @author adi
 */
public interface ReportService {
    
    public JasperPrint printLaporanPeminjaman(String mode, Date mulai, Date sampai);
    
    /**
     * Method printLaporanBuku
     * @params
     * @param params[0] adalah nama kolom
     * @param params[1] adalah nama relational
     * @param params[2] adalah nama value
     * @return JasperPrint
     */
    public JasperPrint printLaporanBuku(String...params);
    public JasperPrint printKartuAnggota(Anggota anggota);
            
}
