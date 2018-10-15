public class Fox {
    private MatrixGame foxMap;
    private int totalFox;

    Fox(int totalFox) {
        this.totalFox = totalFox;
    }

    void start(){
        foxMap = new MatrixGame(Box.empty);
        for (int i=0; i<totalFox; i++){
            placeFox();
        }

    }

    Box get (Coord coord) {
        return foxMap.get(coord);
    }

    private void placeFox(){
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (Box.fox == foxMap.get(coord)) {
                continue;
            }
            foxMap.set(coord, Box.fox);
            break;
        }

    }

    private void placeNum() {

    }

}
