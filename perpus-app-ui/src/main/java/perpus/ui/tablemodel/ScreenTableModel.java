/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.ui.tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import perpus.domain.security.Screen;

/**
 *
 * @author martinusadyh
 */
public class ScreenTableModel extends AbstractTableModel {
    
    private List<Screen> screens = new ArrayList<Screen>();
    private String[] header = {"No#","Nama", "Enable", "Visible"};

    public ScreenTableModel(List<Screen> screens) {
        this.screens = screens;
    }

    @Override
    public int getRowCount() {
        return screens.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class tipe = super.getColumnClass(columnIndex);
        if (columnIndex == 2) {
            tipe = Boolean.class;
        } else if (columnIndex == 3) {
            tipe = Boolean.class;
        }

        return tipe;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 2) {
            return true;
        } else if (columnIndex == 3) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Screen model = screens.get(rowIndex);
        if (columnIndex == 2 && aValue instanceof Boolean) {
            model.setEnable((Boolean) aValue);
        } else if (columnIndex == 3 && aValue instanceof Boolean) {
            model.setVisible((Boolean) aValue);
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Screen screen = screens.get(rowIndex);
        switch (columnIndex) {
            case 0: return screen.getNomorScreen();
            case 1: return screen.getNamaScreen();
            case 2: return screen.getEnable();
            case 3: return screen.getVisible();
            default: return "";
        }
    }
}
