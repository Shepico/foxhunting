public class Game {

    Fox fox;
    //MatrixGame foxMap;

    public Game(int cols, int rows, int foxs){
        Ranges.setSize(new Coord(cols, rows));
        fox = new Fox(foxs);
    }

    public Box getBox (Coord coord){
        //return Box.values()[(coord.getX() + coord.getY()) % Box.values().length];
        return fox.get(coord);
    }

    public void start(){
        fox.start();
        //foxMap = new MatrixGame(Box.empty);
        //foxMap.set(new Coord(0,1),Box.fox);
    }
}
