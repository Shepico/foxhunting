public class Game {

    Fox fox;
    Flag flag;
    //MatrixGame foxMap;

    public Game(int cols, int rows, int foxs){
        Ranges.setSize(new Coord(cols, rows));
        fox = new Fox(foxs);
        flag = new Flag();
    }

    public Box getBox (Coord coord){
        //return Box.values()[(coord.getX() + coord.getY()) % Box.values().length];
        //return fox.get(coord);
        if (flag.get(coord) == Box.open) {
            return fox.get(coord);
        } else {
            return flag.get(coord);
        }
    }

    public void start(){
        fox.start();
        flag.start();
        //foxMap = new MatrixGame(Box.empty);
        //foxMap.set(new Coord(0,1),Box.fox);
    }

    public void pressLeftButton(Coord coord) {
        flag.setOpenedToBox(coord);
    }

    public void pressRightButton(Coord coord) {
        flag.toggleFlagToBox(coord);
    }
}
