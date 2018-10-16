class Flag {

    private MatrixGame flagMap;

    void start() {
        flagMap = new MatrixGame(Box.empty);
        flagMap.set(new Coord(4,4), Box.open );
    }

    Box get (Coord coord) {
        return flagMap.get(coord);
    }

    void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.open);
    }
}
