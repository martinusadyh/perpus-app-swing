/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.ui.tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import perpus.domain.JenisBuku;

/**
 *
 * @author adi
 */
public class JenisBukuComboModel extends AbstractListModel implements ComboBoxModel {

    List<JenisBuku> listJenisBuku = new ArrayList<JenisBuku>();
    
    JenisBuku selection = null;

    public JenisBukuComboModel(List<JenisBuku> listJenisBuku) {
        this.listJenisBuku = listJenisBuku;
    }

    @Override
    public Object getElementAt(int index) {
        return listJenisBuku.get(index);
    }

    @Override
    public int getSize() {
        return listJenisBuku.size();
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (JenisBuku) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
}
