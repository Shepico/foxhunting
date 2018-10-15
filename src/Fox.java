public class Fox {
    private MatrixGame foxMap;
    private int totalFox;

    Fox(int totalFox) {
        this.totalFox = totalFox;
    }

    void start(){
        foxMap = new MatrixGame(Box.empty);
        placeFox();
    }

    Box get (Coord coord) {
        return foxMap.get(coord);
    }

    private void placeFox(){
        foxMap.set(new Coord(5,5), Box.fox);
    }

}
