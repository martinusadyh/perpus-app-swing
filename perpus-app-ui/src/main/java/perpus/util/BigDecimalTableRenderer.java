/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package perpus.util;

import java.awt.Component;
import java.math.BigDecimal;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ifnu
 */
public class BigDecimalTableRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(value instanceof BigDecimal){
            label.setText(TextComponentUtils.formatNumber((BigDecimal)value));
            label.setAlignmentY(JTextField.RIGHT_ALIGNMENT);
        }
        return label;
    }


}
