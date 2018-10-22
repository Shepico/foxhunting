import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogRules extends JDialog {

    public DialogRules(JFrame owner) {
        super(owner,"Rules game", true);

        JTextArea rulesTxt = new JTextArea();
        rulesTxt.append("На поле случайным неизвестным для игрока \n");
        rulesTxt.append("образом расставляются «лисы». Игрок задаёт \n");
        rulesTxt.append("своё положение, в ответ он получает количество \n");
        rulesTxt.append("«лис», которое пеленгуется из его нынешнего \n");
        rulesTxt.append("местоположения. Это число указывает, сколько лис\n");
        rulesTxt.append("расположено в одной вертикали, горизонтали и \n");
        rulesTxt.append("диагоналях с указанной клеткой.\n");
        rulesTxt.append("Если местоположение игрока совпало с положением \n");
        rulesTxt.append("«лисы», она считается найденной. \n");
        rulesTxt.append("Игра продолжается, пока не будут найдены все «лисы».");

        add(rulesTxt);

        JButton btnOK = new JButton("OK");

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JPanel panel = new JPanel();
        panel.add(btnOK);
        add(panel, BorderLayout.SOUTH);
        setSize(380,250);
    }

}
