/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import perpus.Main;

/**
 *
 * @author martinusadyh
 */
public class ErrorDialog {
    public static void showErrorDialog(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        pw.flush();
        sw.flush();
        
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        final JTextArea txtArea = new JTextArea();
        txtArea.setEditable(false);
        txtArea.setRows(8);
        txtArea.setColumns(25);
        txtArea.setText(sw.toString());
        jScrollPane1.setViewportView(txtArea);
        JOptionPane.showMessageDialog(Main.getMainForm(), 
                jScrollPane1,
                "Terjadi Kesalahan !!", 
                JOptionPane.ERROR_MESSAGE);
    }
}
