import com.sun.prism.image.Coords;

import javax.swing.*;
import java.awt.*;

public class FoxHuntingStart extends JFrame{
    private final String GAME_NAME = " Fox hunting";
    private Game game;
    private JPanel panel;
    private final int COLS = 10;
    private final int ROWS = 10;
    private final int IMAGE_SIZE = 50;

    public static void main (String[] args) {
        new FoxHuntingStart();
    }

    private FoxHuntingStart() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Ranges.setSize(new Coord(COLS, ROWS));
                game = new Game(COLS, ROWS);
                game.start();
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
                for (Coord coord:Ranges.getAllCoord()){

                    g.drawImage((Image)game.getBox(coord).image,coord.getX()*IMAGE_SIZE,coord.getY()*IMAGE_SIZE,this);
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
