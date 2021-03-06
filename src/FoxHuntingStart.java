import com.sun.prism.image.Coords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;


public class FoxHuntingStart extends JFrame{
    private final String GAME_NAME = "Fox hunting";
    private JLabel labelState;
    private Game game;
    private JPanel panel;
    private JPanel scoreboard;
    private JLabel countStep;
    private JLabel countFox;
    private JLabel timerGame;
    private Thread timerExecute;
    private int cols = 0;
    private int rows = 0;
    private final int IMAGE_SIZE = 50;
    private int total_fox = 8;
    private JMenuBar topMenu;
    private DialogRules dialogRules;
    private DialogAbout dialogAbout;
    //Диалоги
    private JDialog dialog;
    private JDialog dialogStart;
    private ListWin listWin = new ListWin();
    /* private JInternalFrame selectDifficulty;
    JDesktopPane desktopPane;*/

    public static void main (String[] args) {
        new FoxHuntingStart();
    }

    private FoxHuntingStart() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Ranges.setSize(new Coord(COLS, ROWS));
                createDialogDifficulty();
                //dialog.setVisible(true);
                gameStart();
                //game = new Game(cols, rows, total_fox);
                //game.start();
                setImages();
                initLabelState();
                initScoreboard();
                initPanel();
                initMenuBar();
                initFrame();
                initListWin();


                //
                /*String[] resultWin = listWin.getStringAllWin();
                for (int i = 0; i < resultWin.length; i++) {
                    System.out.println(resultWin[i]);
                }*/
                //
                createStartDialog();
                dialogStart.setVisible(true);

                //Запускаем таймер
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true) {
                            timerGame.setText("Время (c): " + game.getTimer());
                            panel.repaint();
                        }
                    }
                }).start();

            }
        });

    }


    private void initFrame(){

        setTitle(GAME_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(getImage("icon"));
        setResizable(false);

        //setSize(COLS*IMAGE_SIZE, ROWS*IMAGE_SIZE);
        //
        //

        //
        //setSize(cols*IMAGE_SIZE, rows*IMAGE_SIZE);
        //

        setJMenuBar(topMenu);
        //
        /*desktopPane = new JDesktopPane();
        add(desktopPane);
        intiSelectDifficulty();*/

        //

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void initLabelState() {
        labelState = new JLabel("Welcome");
        add(labelState, BorderLayout.SOUTH);
    }

    private void initScoreboard() {
        scoreboard = new JPanel();
        scoreboard.setLayout(new GridLayout(1,4));
        countStep = new JLabel();
        countFox = new JLabel();
        timerGame = new JLabel();
        updateScoreboard();
        scoreboard.add(countFox);
        scoreboard.add(countStep);
        scoreboard.add(timerGame);
        add(scoreboard, BorderLayout.NORTH);
    }

    private void initMenuBar() {
        topMenu = new JMenuBar();
        //
        JMenu fileMenu = new JMenu("File");
        //{
        JMenuItem difficultyMenu = new JMenuItem("Difficulity");
        //{
        /*JMenuItem easyItem = new JMenuItem("Easy");
        JMenuItem mediumItem = new JMenuItem("Medium");
        JMenuItem hardItem = new JMenuItem("Hard");*/

        difficultyMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //createDialogDifficulty()
                //dialog.setVisible(true);
                gameStart();
                setSize(cols*IMAGE_SIZE, rows*IMAGE_SIZE + 90);
                updateScoreboard();
                dialogStart.setVisible(true);
            }
        });
        //}
        /*difficultyMenu.add(easyItem);
        difficultyMenu.add(mediumItem);
        difficultyMenu.add(hardItem);*/
        //
        JCheckBoxMenuItem assistantItem = new JCheckBoxMenuItem("Use assistant", false);

        // выход
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //
        fileMenu.add(difficultyMenu);
        fileMenu.add(assistantItem);
        fileMenu.add(exitItem);
        // }
        JMenu helpMenu = new JMenu("Help");
        JMenuItem rulesItem = new JMenuItem("Rules");
        rulesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dialogRules == null) {
                    dialogRules = new DialogRules(FoxHuntingStart.this);
                }
                dialogRules.setVisible(true);
            }
        });
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dialogAbout == null) {
                    dialogAbout = new DialogAbout(FoxHuntingStart.this, listWin.getStringAllWin());
                } else {
                    dialogAbout.rereadAbout(listWin.getStringAllWin());
                }
                dialogAbout.setVisible(true);
            }
        });
        helpMenu.add(rulesItem);
        helpMenu.add(aboutItem);
        //
        topMenu.add(fileMenu);
        topMenu.add(helpMenu);
    }

    private void updateScoreboard() {
        countStep.setText("Количество шагов - " + game.getStep());
        countFox.setText("Не найдено лис - " + game.getFoxs());
        timerGame.setText("Время (c): " + game.getTimer());
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
                updateScoreboard();
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
            case WINNER: {
                double rating = game.getRating();
                String msgWin = "Congratulation! \n" +
                                        "Your rating = " + rating;
                JOptionPane.showMessageDialog(this,msgWin,"You win",
                        JOptionPane.DEFAULT_OPTION, new ImageIcon(getClass().getResource("winner.png")));
                String name = JOptionPane.showInputDialog(this,"Ваше имя:");
                saveListWin(rating, name);

                return "Congratulation!";
            }
            default: return "Welcome!";
        }
    }

    private void initListWin() {
        //listWin = new ListWin();
        try {
            FileInputStream fis = new FileInputStream("ScoreFoxHunting.out");
            ObjectInputStream oin = new ObjectInputStream(fis);
            listWin = (ListWin) oin.readObject();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveListWin (Double raiting, String name) {
       /* if (listWin == null) {
            listWin = new ListWin();
        }*/
        if (name == null) {
            name = "Робот";
        }
        listWin.addListWin(raiting, name);
        FileOutputStream fos;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("ScoreFoxHunting.out");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listWin);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                oos.flush();
                oos.close();
            }catch (IOException e) {
                e.printStackTrace();
            }

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

    // реализация окон для меню
    //диалог выбора размера карты
    private void createDialogDifficulty(){

        dialog = new JDialog(this,"Select level", true);
        dialog.setIconImage(getImage("icon"));


        JPanel panelLevel = new JPanel();
        panelLevel.setLayout(new GridLayout(1,3));
        //
        JButton btnEasy = new JButton("EASY");
        JButton btnMedium = new JButton("MEDIUM");
        JButton btnHard = new JButton("HARD");
        //
        btnEasy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cols = 10;
                rows = 10;
                total_fox = 8;
                btnEasy.setBackground(Color.orange);
                btnMedium.setBackground(null);
                btnHard.setBackground(null);
            }
        });


        btnMedium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cols = 15;
                rows = 15;
                total_fox = 12;
                btnEasy.setBackground(null);
                btnMedium.setBackground(Color.orange);
                btnHard.setBackground(null);
            }
        });


        btnHard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cols = 20;
                rows = 20;
                total_fox = 16;
                btnEasy.setBackground(null);
                btnMedium.setBackground(null);
                btnHard.setBackground(Color.orange);
            }
        });
        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });

        JPanel panelDiff = new JPanel();

        panelLevel.add(btnEasy);
        panelLevel.add(btnMedium);
        panelLevel.add(btnHard);

        panelDiff.add(btnOK);

        dialog.add(panelLevel, BorderLayout.CENTER);
        dialog.add(panelDiff, BorderLayout.SOUTH);

        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(280, 120);
        dialog.setLocationRelativeTo(this);
        //setVisible(false);
        //return dialog;
    }

    //Диалог старт
    private void createStartDialog() {
        dialogStart = new JDialog(this,"",true);
        dialogStart.setSize(100,40);
        dialogStart.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialogStart.setUndecorated(true);
        JButton btnStart = new JButton("START");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogStart.setVisible(false);
                game.startTimer();
            }
        });
        dialogStart.add(btnStart);
        dialogStart.setLocationRelativeTo(this);
    }

  /*  private JDialog getDialogDifficuty(){
        return dialog.setVisible(true);
    }*/

    private void gameStart() {
        dialog.setVisible(true);
        game = new Game(cols, rows, total_fox);
        game.start();

    }

}
