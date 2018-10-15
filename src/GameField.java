import javax.swing.*;
import java.awt.*;

public class GameField extends JFrame {
    private final  int SIZE = 500;
    private final String GAME_NAME = " Fox hunting";

    GameField(int sizePlay) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createFrame(sizePlay);
            }
            });
    }

    private void createFrame(int sizePlay){
            new JFrame();
            setTitle(GAME_NAME);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(SIZE, SIZE);
            //

            getContentPane().add(createMatrix(sizePlay,true), BorderLayout.CENTER);  //создаем системную матрицу

            //
            setLocationRelativeTo(null);
            setVisible(true);
    }

    private JTable createMatrix(int sizePlay, boolean system){
        MatrixGame matrix = new MatrixGame(sizePlay, system);
        return matrix.getMatrix();
    }

}

