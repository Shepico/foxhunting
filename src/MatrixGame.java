import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class MatrixGame {
    private int size_map;
    private JTable matrix;

    MatrixGame (int sizeField, boolean systemMatrix) {
        size_map = sizeField;
        createMatrixGame();
        if (systemMatrix){
            fillMatrix();
        }

    }
///////public/////////////////
    public JTable getMatrix() {
        return matrix;
    }

///////private////////////////
    private void createMatrixGame (){
        matrix = new JTable(size_map,size_map);
    }

    private void fillMatrix(){
        ClassLoader loader = this.getClass().getClassLoader(); // получили СlassLoader по умолчанию
        Icon ic = new ImageIcon(loader.getResource("fox.png"));
        System.out.println(ic);



    }
}
