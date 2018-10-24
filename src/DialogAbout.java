import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogAbout extends JDialog {

    public DialogAbout(JFrame owner) {
        super(owner,"About...", true);
        //setBackground(Color.WHITE);
        setUndecorated(true);

        JTextArea rulesTxt = new JTextArea();
        rulesTxt.append("(c) SheP&Co \n");
        rulesTxt.append("E-Mail: Tols78@Inbox.ru \n");
        rulesTxt.setEditable(false);

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
        setSize(280,110);
        setLocationRelativeTo(owner);

    }

}
