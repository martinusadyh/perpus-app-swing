/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.service;

import java.util.Date;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author adi
 */
public interface ReportService {
    
    public JasperPrint printLaporanPeminjaman(String mode, Date mulai, Date sampai);
            
}
