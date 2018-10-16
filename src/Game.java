public class Game {

    private Fox fox;
    private Flag flag;
    private int foxCount;

    public GameState getState() {
        return state;
    }

    private GameState state;
    //MatrixGame foxMap;

    public Game(int cols, int rows, int foxs){
        Ranges.setSize(new Coord(cols, rows));
        fox = new Fox(foxs);
        flag = new Flag();
        foxCount = foxs;
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
        state = GameState.PLAYED;
        //foxMap = new MatrixGame(Box.empty);
        //foxMap.set(new Coord(0,1),Box.fox);
    }

    public void pressLeftButton(Coord coord) {
        openBox (coord);
        //flag.setOpenedToBox(coord);
    }

    private void openBox(Coord coord) {
        switch (flag.get(coord)) {
            case open : return;
            case flag : return;
            case empty : {
                switch (fox.get(coord)) {
                    case fox : {
                        foxCount--;
                        flag.setOpenedToBox(coord);
                        return;
                    }
                    default : flag.setOpenedToBox(coord); return;

                }
            }

        }
    }

    public void pressRightButton(Coord coord) {
        flag.toggleFlagToBox(coord);
    }

    public int getFoxs() {
        return foxCount;
    }
}
