/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package siptest.DataSearch;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import siptest.GetUIElement;

/**
 *
 * @author wuqm
 */
public class DeleteAlarmDialog extends AlarmDialog{
    

    Map DBmap = new HashMap();
    String businame;
    JComboBox busi_name_list = new JComboBox();
    String dbsql;
    String dbtable;

    DeleteAlarmDialog(String dialog, Map DBmap, String businame, JComboBox busi_name_list,String dbsql,String dbtable) {
        super(dialog);
        this.DBmap = DBmap;
        this.businame = businame;
        this.busi_name_list = busi_name_list;
        this.dbsql=dbsql;
        this.dbtable=dbtable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            Map datamap = new HashMap();
            datamap.put("busi_name", businame);
            SourceDataDao delete = new SourceDataDao();
            delete.DataDelete(DBmap, dbtable, datamap);
             GetUIElement getlistelement = new GetUIElement();
        try {
            getlistelement.GetElementList(DBmap, busi_name_list, dbsql);
        } catch (Exception e1) {
            e1.printStackTrace();
            AlarmDialog dialog = new AlarmDialog(e1.getMessage());
            dialog.setVisible(true);
        }
        }

        dispose();
    }
}
