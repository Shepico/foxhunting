import javax.swing.*;
import java.awt.*;

public class FoxHuntingStart extends JFrame{
    private final String GAME_NAME = " Fox hunting";
    private JPanel panel;
    private final int COLS = 14;
    private final int ROWS = 1;
    private final int IMAGE_SIZE = 50;

    public static void main (String[] args) {
        new FoxHuntingStart();
    }

    private FoxHuntingStart() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Ranges.setSize(new Coord(COLS, ROWS));
                setImages();
                initPanel();
                initFrame();
            }
        });
    }


    private void initFrame(){
        pack();
        setTitle(GAME_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(getImage("icon"));
        setResizable(false);
        //

        //
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initPanel() {
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Box box:Box.values()){
                    Coord coord = new Coord(box.ordinal()*IMAGE_SIZE, 0);
                    g.drawImage((Image)box.image,coord.getX(),coord.getY(),this);
                }

            }


        };

        panel.setPreferredSize(new Dimension(Ranges.getSize().getX()*IMAGE_SIZE,Ranges.getSize().getY()*IMAGE_SIZE));
        add(panel);
    }

    private void setImages() {
        for (Box box:Box.values()){
            box.image = getImage(box.name().toLowerCase());
        }
    }

    private Image getImage(String name) {
        String filename = name.toLowerCase()+".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}
