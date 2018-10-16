import com.sun.prism.image.Coords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FoxHuntingStart extends JFrame{
    private final String GAME_NAME = " Fox hunting";
    private JLabel labelState;
    private Game game;
    private JPanel panel;
    private final int COLS = 10;
    private final int ROWS = 10;
    private final int IMAGE_SIZE = 50;
    private final int TOTAL_FOX = 8;

    public static void main (String[] args) {
        new FoxHuntingStart();
    }

    private FoxHuntingStart() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Ranges.setSize(new Coord(COLS, ROWS));
                game = new Game(COLS, ROWS, TOTAL_FOX);
                game.start();
                setImages();
                initLabelState();
                initPanel();
                initFrame();
            }
        });
    }


    private void initFrame(){

        setTitle(GAME_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(getImage("icon"));
        setResizable(false);
        //

        //

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void initLabelState() {
        labelState = new JLabel("Welcome");
        add(labelState, BorderLayout.SOUTH);
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
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x,y);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLeftButton(coord);
                }else if (e.getButton() == MouseEvent.BUTTON3) {
                    game.pressRightButton(coord);
                }else if (e.getButton() == MouseEvent.BUTTON2) { //перезапуск игры по средней кнопке
                    game.start();
                }
                labelState.setText(getMessage());
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(Ranges.getSize().getX()*IMAGE_SIZE,Ranges.getSize().getY()*IMAGE_SIZE));
        add(panel);
    }

    private String getMessage() {
        switch (game.getState()) {
            case PLAYED: return "Think twice!";
            case WINNER: return "Congratulation!";
            default: return "Welcome!";
        }
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
