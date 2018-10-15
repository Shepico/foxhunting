public class Game {

    MatrixGame foxMap;

    public Game(int cols, int rows){
        Ranges.setSize(new Coord(cols, rows));
    }

    public Box getBox (Coord coord){
        //return Box.values()[(coord.getX() + coord.getY()) % Box.values().length];
        return foxMap.get(coord);
    }

    public void start(){
        foxMap = new MatrixGame(Box.fox);
    }
}
