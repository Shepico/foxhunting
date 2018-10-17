import javax.swing.*;


class MatrixGame {

    private Box [][] matrix;

    MatrixGame(Box defaultBox) {
        matrix = new Box[Ranges.getSize().getX()][Ranges.getSize().getY()];
        for(Coord coord:Ranges.getAllCoord()){
            matrix[coord.getX()] [coord.getY()] = defaultBox;
        }
    }

    void set(Coord coord, Box box) {
        if (Ranges.inRange (coord)) {
            matrix [coord.getX()] [coord.getY()] = box;
        }
    }

    Box get (Coord coord) {
        if (Ranges.inRange (coord)) {
            return matrix [coord.getX()] [coord.getY()];
        }
        return null;
    }
}