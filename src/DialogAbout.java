import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogAbout extends JDialog {

    JTextArea rulesTxt;

    public DialogAbout(JFrame owner, String[] win) {
        super(owner,"About...", true);
        //setBackground(Color.WHITE);
        setUndecorated(true);

        rulesTxt = new JTextArea();
        setTxtAbout(win);

        /*for (int i=0; i < win.length; i++) {
            rulesTxt.append(win[i] + " \n");
        }
        rulesTxt.append("\n");
        rulesTxt.append("(c) SheP&Co \n");
        rulesTxt.append("E-Mail: Tols78@Inbox.ru \n");
        rulesTxt.setEditable(false);*/

        JPanel pnlRules = new JPanel();
        pnlRules.setBackground(Color.WHITE);
        pnlRules.setBorder(new EmptyBorder(10, 10, 0, 10));
        pnlRules.add(rulesTxt);
        add(pnlRules, BorderLayout.CENTER);

        JButton btnOK = new JButton("OK");

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.add(btnOK);

        add(panel, BorderLayout.SOUTH);
        setSize(280,270);
        setLocationRelativeTo(owner);

    }

    public void rereadAbout(String[] win){
        rulesTxt.setText(null);
        setTxtAbout(win);
    }

    private void setTxtAbout(String[] win){

        for (int i=0; i < win.length; i++) {
            rulesTxt.append(win[i] + " \n");
        }
        rulesTxt.append("\n");
        rulesTxt.append("(c) SheP&Co \n");
        rulesTxt.append("E-Mail: Tols78@Inbox.ru \n");
        rulesTxt.setEditable(false);
    }

}
