/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package siptest.DataSearch;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author wuqm
 */
public class AlarmDialog extends JDialog implements ActionListener{
    JButton ok = new JButton("OK");
    JLabel Tips = new JLabel();

    public AlarmDialog(String dialog) {
        int width = dialog.getBytes().length;
        this.setBounds(300, 100, width * 15 + 20, 120);
        this.setBackground(Color.white);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("提示框");

        this.add(ok);
        int oksize = ((width * 15 + 20) - 55) / 2;
        ok.setBounds(oksize, 60, 55, 25);
        ok.setFont(new Font("微软雅黑", 0, 14));
        ok.addActionListener(this);


        this.add(Tips);
        Tips.setText(dialog);
        int loc=((width * 15 + 20)-width*5)/2-(width*2/3);
        Tips.setBounds(loc, 20, width * 15, 15);
        Tips.setFont(new Font("微软雅黑", 0, 14));


    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == ok) {
        }

        dispose();

    }
}
