import javax.swing.*;

public class GameField extends JFrame {
    private final  int SIZE = 500;
    private final String GAME_NAME = " Fox hunting";

    GameField(int sizePlay) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initFrame(sizePlay);
            }
            });
    }

    private void initFrame(int sizePlay){
            pack();
            setTitle(GAME_NAME);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //setSize(SIZE, SIZE);
            setResizable(false);
            //

            //
            setLocationRelativeTo(null);
            setVisible(true);
    }


    private JTable createMatrix(int sizePlay, boolean system){
        MatrixGame matrix = new MatrixGame(sizePlay, system);
        return matrix.getMatrix();
    }



}

